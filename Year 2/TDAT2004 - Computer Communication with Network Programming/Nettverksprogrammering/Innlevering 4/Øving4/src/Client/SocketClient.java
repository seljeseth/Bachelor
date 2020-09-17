package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient
{
    final static String server = "192.168.0.136";
    final static String sivert = "192.168.0.197";



    public static void main(String[] args) throws IOException
    {
        final int PORTNR = 6969;
        Scanner terminalinput = new Scanner(System.in);
        Socket connection = new Socket(server,PORTNR);
        System.out.println("Connection made.");


        /* åpner en forbindelse for kommunikasjon med tjenerprogrammet */
        InputStreamReader readingConnection = new InputStreamReader(connection.getInputStream());
        BufferedReader reader = new BufferedReader(readingConnection);
        PrintWriter writer = new PrintWriter(connection.getOutputStream(), true);

        /* Leser innledning fra tjeneren og skriver den til kommandovinduet */
        String innledning1 = reader.readLine();
        String innledning2 = reader.readLine();
        System.out.println(innledning1 + "\n" + innledning2);

        /* Leser tekst fra kommandovinduet (brukeren) */
        String enLinje = terminalinput.nextLine();
        while (!enLinje.equals("")) {
            writer.println(enLinje);  // sender teksten til tjeneren
            String respons = reader.readLine();  // mottar respons fra tjeneren
            System.out.println("Server: " + respons);
            enLinje = terminalinput.nextLine();
        }

        /* Lukker forbindelsen */
        reader.close();
        writer.close();
        connection.close();
    }
}




