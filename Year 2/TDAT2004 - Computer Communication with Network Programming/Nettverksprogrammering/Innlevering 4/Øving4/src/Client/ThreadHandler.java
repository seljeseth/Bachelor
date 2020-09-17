package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;


public class ThreadHandler extends Thread
{
    Socket connection;
    InputStreamReader readConnection;
    BufferedReader reader;
    PrintWriter writer;

    public ThreadHandler(Socket connection)
    {
        this.connection = connection;
    }

    public void run()
    {
        try
        {

            /* åpner strømmer for kommunikasjon med klientprogrammet */
            readConnection = new InputStreamReader(connection.getInputStream());
            reader = new BufferedReader(readConnection);
            writer = new PrintWriter(connection.getOutputStream(), true);

            /* Sender innledning til klienten */
            writer.println("Connected to server");
            writer.println("Write in what you want to calculate, example 2+2 or 2-2. This calculater is very basic so only give it on operator, example (only +) 2+2+2+2");

            /* Mottar data fra klienten */
            String input = reader.readLine();  // mottar en linje med tekst
            while (input != null)
            {
                System.out.println("The client asked to solve: " + input);
                try
                {
                    writer.println(input + " = " + calculateInput(input));  // sender svar til klienten
                } catch (NumberFormatException e)
                {
                    writer.println("OBS OBS! Det er kun lov å enten addere eller subtrahere, ikke begge samtidig!");
                }
                input = reader.readLine();
            }

        } catch (IOException e)
        {
            writer.println(Arrays.toString(e.getStackTrace()));
        } finally
        {
            try
            {
                reader.close();
                writer.close();
                connection.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    //2+2
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



