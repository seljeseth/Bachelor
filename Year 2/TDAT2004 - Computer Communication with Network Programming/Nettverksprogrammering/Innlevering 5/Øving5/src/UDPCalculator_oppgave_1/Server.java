package UDPCalculator_oppgave_1;

import java.io.*;
import java.net.*;

public class Server {
    private DatagramSocket socket;
    private DatagramPacket DPreceived = null;
    private int port;
    private byte[] receive = new byte[512];
    private String answerToEquation = "";
    final int PORT = 8000;



    public Server() throws SocketException
    {
        socket = new DatagramSocket(PORT);
    }

    public static void main(String[] args)
    {
        try
        {
            Server server = new Server();
            System.out.println("Server is running...");
            server.service();


        } catch (SocketException ex) {
            System.out.println("Socket error: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }

    private void service() throws IOException
    {
        while (true)
        {
            DPreceived = new DatagramPacket(receive, receive.length);
            socket.receive(DPreceived);
            System.out.println("Client wrote:" + data(receive));
            String i = data(receive).toString();
            try
            {
                answerToEquation = Integer.toString(calculateInput(i));

            } catch (NumberFormatException e)
            {
                answerToEquation = "OBS OBS! We only allow adding or subtracting, not both at the same time!";
            }

            byte[] buffer = answerToEquation.getBytes();

            //Using the datagrampacket we received we locate where the answer should go
            InetAddress clientAddress = DPreceived.getAddress();
            int clientPort = DPreceived.getPort();

            DatagramPacket response = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
            //send our calculated answer back to the client
            socket.send(response);

            //reset the byte array we receive so we can get new input
            receive = new byte[512];
        }
    }

    //converts the byte array we receive to a string which can than again be used in the method for calculating the input
    public static StringBuilder data(byte[] a)
    {
        if (a == null)
            return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0)
        {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }

    //Same method used here as in øving 4
    //only accepts easy calculations like 2+2 or 2-2
    static int calculateInput(String input)
    {

        if (input.contains("+"))
        {
            String[] numbers_string = input.split("\\+", 0);
            int sum = 0;

            for (String a : numbers_string)
            {
                sum += Integer.parseInt(a);
            }
            return sum;
        } else if (input.contains("-"))
        {
            String[] numbers = input.split("-", 0);
            int sum = Integer.parseInt(numbers[0]);

            for (int i = 1; i < numbers.length; i++)
            {
                sum -= Integer.parseInt(numbers[i]);
            }
            return sum;

        }
        return -1;
    }
}
