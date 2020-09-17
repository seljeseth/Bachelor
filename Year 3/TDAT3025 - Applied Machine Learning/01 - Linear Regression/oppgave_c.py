import torch
import matplotlib.pyplot as plt
import csv

day = []
head_circumference = []
#Getting values from .csv files
with open(' day_head_circumference.csv') as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=',')
    line_count = 0
    for row in csv_reader:
        if line_count == 0:
            print(f'Column names are {", ".join(row)}')
            line_count += 1
        else:
            day.append(float(row[0]))
            head_circumference.append(float(row[1]))
            line_count += 1

x_train = torch.tensor(day).reshape(-1, 1)
y_train = torch.tensor(head_circumference).reshape(-1, 1)


class LinearRegressionModel:
    def __init__(self):
        # Model variables
        self.W = torch.tensor([[0.0]], requires_grad=True)  # requires_grad enables calculation of gradients
        self.b = torch.tensor([[0.0]], requires_grad=True)

    # Predictor
    def f(self, x):
        return (20 * torch.sigmoid(x @ self.W + self.b) + 31)

    # Uses Mean Squared Error
    def loss(self, x, y):
        return torch.mean(torch.square(self.f(x) - y))  # Can also use torch.nn.functional.mse_loss(self.f(x), y) to possibly increase numberical stability


model = LinearRegressionModel()

# Optimize: adjust W and b to minimize loss using stochastic gradient descent
optimizer = torch.optim.SGD([model.W, model.b], 0.000001)
for epoch in range(1000000):
    model.loss(x_train, y_train).backward()  # Compute loss gradients
    optimizer.step()  # Perform optimization by adjusting W and b,


    optimizer.zero_grad()

# Print model variables and loss
print("W = %s, b = %s, loss = %s" % (model.W, model.b, model.loss(x_train, y_train)))

# Visualize result
plt.plot(x_train, y_train, '.', label='$(\\hat x^{(i)},\\hat y^{(i)})$')
plt.xlabel('day \n' 'loss = %s"' % (model.loss(x_train, y_train)))
plt.ylabel('head_circumference')
plt.plot(x_train, model.f(x_train).detach(), 'o' ,label='$y = f(x) = 20*sigma(xW+b)+31$')
plt.legend()
plt.show()
