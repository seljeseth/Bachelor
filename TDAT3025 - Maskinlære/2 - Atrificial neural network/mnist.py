import torch
import torchvision


def getTrainingData():
    mnist_train = torchvision.datasets.MNIST('./data', train=True, download=True)
    x_train = mnist_train.data.reshape(-1, 784).float()  # Reshape input
    y_train = torch.zeros((mnist_train.targets.shape[0], 10))  # Create output tensor
    y_train[torch.arange(mnist_train.targets.shape[0]), mnist_train.targets] = 1  # Populate output
    return [x_train, y_train]


def getTestData():
    mnist_test = torchvision.datasets.MNIST('./data', train=False, download=True)
    x_test = mnist_test.data.reshape(-1, 784).float()
    y_test = torch.zeros((mnist_test.targets.shape[0], 10))
    y_test[torch.arange(mnist_test.targets.shape[0]), mnist_test.targets] = 1
    return [x_test, y_test]
