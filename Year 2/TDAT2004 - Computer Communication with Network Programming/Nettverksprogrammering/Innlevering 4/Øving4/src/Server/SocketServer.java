package Server;

import Client.ThreadHandler;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class SocketServer
{
    public static void main(String[] args) throws IOException
    {

        final int PORTNR = 6969;
        final String IP = "192.168.0.136";
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(IP, PORTNR));
        System.out.println("Server is running...");
        while(true)
        {
            Socket connection = server.accept();  // venter inntil noen tar kontakt
            Thread clientThread = new ThreadHandler(connection);
            clientThread.start();
        }

    }
}

