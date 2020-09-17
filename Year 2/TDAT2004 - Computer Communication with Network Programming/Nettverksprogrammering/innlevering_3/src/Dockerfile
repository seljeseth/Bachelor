# Get the GCC preinstalled image from Docker Hub
FROM gcc:4.9

# Copy the current folder which contains C++ source code to the Docker image under /usr/src
COPY . /usr/src/innlevering_3/src

# Specify the working directory
WORKDIR /usr/src/innlevering_3/src

# Use GCC to compile the Test.cpp source file
RUN g++ -o Main Main.cpp -pthread

# Run the program output from the previous step
CMD ["./Main"]
