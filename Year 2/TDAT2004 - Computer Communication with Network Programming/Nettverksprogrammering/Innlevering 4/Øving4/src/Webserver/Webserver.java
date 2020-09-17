package Webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Webserver {
    public static void main(String[] args) throws IOException
    {
        final int PORTNR = 80;
        ServerSocket server = new ServerSocket(PORTNR);
        System.out.println("Server is running...");

        Socket connection = server.accept();
        System.out.println("Connection made with: " + connection.getInetAddress().getHostName());

        InputStreamReader readConnection = new InputStreamReader(connection.getInputStream());
        BufferedReader reader = new BufferedReader(readConnection);
        PrintWriter writer = new PrintWriter(connection.getOutputStream(), true);

        //HTTP request for browser
        StringBuilder display = new StringBuilder("HTTP/1.0 200 OK\n");
        display.append("Content-Type: text/html; charset=utf-8\n");
        display.append("\n");
        display.append("<html> <body> <h1> Hva skal man egentlig skrive i en velkommstmelding?? Jaja her er iallefall øvinga </h1>");

        //header from browser
        display.append("<ul>");
        String line = reader.readLine();
        while(!line.equals(""))
        {
            display.append("<li>").append(line).append("</li>");
            line = reader.readLine();

        }
        display.append("</ul>");


        display.append("</body> </html>");
        writer.println(display);

        reader.close();
        writer.close();
        connection.close();

    }
}
