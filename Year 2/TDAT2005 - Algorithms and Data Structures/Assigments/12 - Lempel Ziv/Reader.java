package AlgoritmerØvinger.Lempel_Ziv_12;


import java.io.*;

public class Reader
{
    private DataInputStream reader;



    public Reader(String filnavn)
    {
        try
        {
            String path = "src/AlgoritmerØvinger/Øving12/";
            System.out.print("\nLeser fil:  \t" + filnavn + " - ");
            this.reader = new DataInputStream(new BufferedInputStream(new FileInputStream(path + filnavn)));
            System.out.println(" OK");
        }
        catch(FileNotFoundException fnfe)
        {
            System.out.println("File not found\n");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }



    public char readChar()
    {
        byte b = 0;
        try
        {
            b = reader.readByte();
        }
        catch(EOFException eofe)
        {
            //reached end of file
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return (char) b;
    }



    public short readShort()
    {
        short sh = 0;
        try
        {
            sh = reader.readShort();
        }
        catch(EOFException eofe)
        {
            //reached end of file
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return sh;
    }



    public void close()
    {
        if (reader == null)
        {
            return;
        }
        try
        {
            reader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
