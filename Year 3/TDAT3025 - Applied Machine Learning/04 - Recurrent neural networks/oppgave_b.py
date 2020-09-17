import torch
import torch.nn as nn

class LongShortTermMemoryModel(nn.Module):
    def __init__(self, encoding_size, out_size):
        super(LongShortTermMemoryModel, self).__init__()

        self.lstm = nn.LSTM(encoding_size, 128)  # 128 is the state size
        self.dense = nn.Linear(128, out_size)  # 128 is the state size

    def reset(self, batch_size):  # Reset states prior to new input sequence
        zero_state = torch.zeros(1, batch_size, 128)  # Shape: (number of layers, batch size, state size)
        self.hidden_state = zero_state
        self.cell_state = zero_state

    def logits(self, x):  # x shape: (sequence length, batch size, encoding size)
        out, (self.hidden_state, self.cell_state) = self.lstm(x, (self.hidden_state, self.cell_state))
        return self.dense(out[-1].reshape(-1,128))

    def f(self, x):  # x shape: (sequence length, batch size, encoding size)
        return torch.softmax(self.logits(x), dim=1)

    def loss(self, x, y):  # x shape: (sequence length, batch size, encoding size), y shape: (sequence length, encoding size)
        return nn.functional.cross_entropy(self.logits(x), y.argmax(1))

def classifyLetters(letters):
    return [[0. if j != i else 1. for j in range(len(letters))] for i, c in enumerate(letters)]


emojies = ['⚽', '🐀️', '🧢️', '👶️', '💩', '🐈️', '🏢']
words = ['ball', 'rat ', 'cap ', 'son ', 'crap', 'cat ', 'flat']
chars = [c for c in ''.join(set(''.join(words)))]

char_encodings = classifyLetters(chars)
emoji_encodings = classifyLetters(emojies)


def encodeWord(word):
    return [char_encodings[chars.index(i)] for i in word]


x_train = torch.tensor([encodeWord(w) for w in words]).transpose(0, 1)
y_train = torch.tensor(emoji_encodings)

model = LongShortTermMemoryModel(len(chars), len(emojies))

optimizer = torch.optim.RMSprop(model.parameters(), 0.001)
for epoch in range(501):
    model.reset(7)
    model.loss(x_train, y_train).backward()
    optimizer.step()
    optimizer.zero_grad()

    if epoch % 200 == 0:

        print('\nEpoch:', epoch)
        tests = ['rt  ', 'rats', 'flat', 'caps', 'sn  ', 'cr ', 'cat ', 'fl  ', 'bl']

        for test in tests:
            model.reset(1)

            test_encoding = encodeWord(test)
            test_tensor = torch.tensor([test_encoding]).transpose(0, 1)
            y = model.f(test_tensor)
            index = y.argmax(1).numpy()[0]

            print("%-6s %-2s %-7s" % (test,"=", emojies[index]))

