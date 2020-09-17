import torch
import matplotlib.pyplot as plt
import csv

length_list = []
weight_list = []
day = []
length_and_weight = []
with open(' day_length_weight.csv') as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=',')
    line_count = 0
    for row in csv_reader:
        if line_count == 0:
            print(f'Column names are {", ".join(row)}')
            line_count += 1
        else:
            day.append(float(row[0]))
            #Making lists so i can graph this later
            length_list.append(float(row[1]))
            weight_list.append(float(row[2]))

            length = float(row[1])
            weight = float(row[2])
            length_and_weight.append([weight, length])
            line_count += 1


x_train = torch.tensor(length_and_weight).reshape(-1, 2)
y_train = torch.tensor(day).reshape(-1, 1)


class LinearRegressionModel:
    def __init__(self):
        # Model variables
        self.W = torch.tensor([[0.0], [0.0]], requires_grad=True)  # requires_grad enables calculation of gradients
        self.b = torch.tensor([[0.0]], requires_grad=True)

    # Predictor
    def f(self, x):
        return x @ self.W + self.b  # @ corresponds to matrix multiplication

    # Uses Mean Squared Error
    def loss(self, x, y):
        return torch.mean(torch.square(self.f(x) - y))  # Can also use torch.nn.functional.mse_loss(self.f(x), y) to possibly increase numberical stability


model = LinearRegressionModel()

# Optimize: adjust W and b to minimize loss using stochastic gradient descent
optimizer = torch.optim.SGD([model.W, model.b], 0.0001)
for epoch in range(100000):
    model.loss(x_train, y_train).backward()  # Compute loss gradients
    optimizer.step()  # Perform optimization by adjusting W and b,
    # similar to:
    # model.W -= model.W.grad * 0.01
    # model.b -= model.b.grad * 0.01

    optimizer.zero_grad()  # Clear gradients for next step

# Print model variables and loss
print("W = %s, b = %s, loss = %s" % (model.W, model.b, model.loss(x_train, y_train)))

# Visualize result
fig = plt.figure()
ax = fig.add_subplot(111, projection='3d')
ax.scatter3D(length_list, weight_list, day, c="b", marker='.')
ax.scatter3D(length_list, weight_list, model.f(x_train).detach(), c="y", marker='.')
ax.set_xlabel('Length')
ax.set_ylabel('Weight')
ax.set_zlabel('Days')
plt.show()