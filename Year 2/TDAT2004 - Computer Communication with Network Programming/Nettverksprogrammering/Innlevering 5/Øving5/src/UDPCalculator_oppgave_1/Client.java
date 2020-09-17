package UDPCalculator_oppgave_1;

import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Client {
    final static int PORT = 8000;


    public static void main(String[] args)
    {
        Scanner terminalinput = new Scanner(System.in);


        try {
            InetAddress address = InetAddress.getByName("localhost");
            DatagramSocket socket = new DatagramSocket();
            System.out.println("Write in what you want to calculate, example 2+2 or 2-2. This calculater is very basic so only give it on operator, example (only +) 2+2+2+2");


            while (true) {
                //tar inn regnestykket og gjør det om til en bytearray slik at det kan sendes i en datagrampacket
                byte[] input = terminalinput.nextLine().getBytes();
                DatagramPacket request = new DatagramPacket(input, input.length, address, PORT);
                socket.send(request);

                byte[] buffer = new byte[512];
                DatagramPacket response = new DatagramPacket(buffer, buffer.length);
                socket.receive(response);

                String answer = new String(buffer, 0, response.getLength());

                System.out.println(answer);

                Thread.sleep(1000);
            }

        } catch (SocketTimeoutException e) {
            System.out.println("Timeout error: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}