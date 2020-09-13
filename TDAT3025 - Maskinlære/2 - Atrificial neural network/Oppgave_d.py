import matplotlib.pyplot as plt
from mnist import getTrainingData, getTestData
import torch
# Initial Values
W_init = (784, 10)  # Number of images, number of classes
b_init = (1, 10)

# Training values
learning_rate = 0.1
epochs = 10000


# Creating model
class NumberModel:
    def __init__(self):
        self.m = torch.nn.Softmax(dim=1)
        self.W = torch.ones(W_init, requires_grad=True)
        self.b = torch.ones(b_init, requires_grad=True)

    def f(self, x):
        return self.m(x @ self.W + self.b)

    def loss(self, x, y):
        return torch.nn.functional.cross_entropy(self.f(x), y.argmax(1))

    def accuracy(self, x, y):
        return torch.mean(torch.eq(self.f(x).argmax(1), y.argmax(1)).float())


model = NumberModel()

# Trainging data
x_train, y_train = getTrainingData()

optimizer = torch.optim.SGD([model.b, model.W], learning_rate)
for epoch in range(epochs):
    model.loss(x_train, y_train).backward()
    optimizer.step()
    optimizer.zero_grad()

# Testdata
x_test, y_test = getTestData()

# Check accuracy
loss = model.loss(x_train, y_train).detach().numpy()
accuracy = model.accuracy(x_test, y_test).detach().numpy()

# Visualize W
fig = plt.figure(figsize=(8, 8))
for i in range(1, 11):
    img = model.W.detach().numpy()[:, i - 1].reshape(28, 28)
    fig.add_subplot(3, 4, i)
    plt.imshow(img)

# Show values
fig.text(0.55, 0.15, "loss         = %.3f\naccuracy = %.3f" % (loss, accuracy)).set_fontsize(25)
plt.savefig("images/d_results_with_10000_epochs.png")
plt.show()
