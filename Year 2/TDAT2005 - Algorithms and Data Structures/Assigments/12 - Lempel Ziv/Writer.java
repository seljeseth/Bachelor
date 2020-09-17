package AlgoritmerØvinger.Lempel_Ziv_12;



import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class Writer
{
	private DataOutputStream writer;



	public Writer(String filnavn)
	{
		try
		{
			String path = "src/AlgoritmerØvinger/Øving12/";
			System.out.print("Skriver til:\t" + filnavn);
			this.writer = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path + filnavn)));
			System.out.println(" OK");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}



	public void writeChar(char byte_value)
	{
		byte b = (byte)byte_value;
		try
		{
			writer.writeByte(b);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}



	public void writeShort(int byte_value)
	{
		try
		{
			writer.writeShort(byte_value);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}



	public void writeRef(int spaces_back , int number_of_bytes_long)
	{
		try
		{
			writer.writeShort(spaces_back);
			writer.writeByte((byte)number_of_bytes_long);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}



	public void print(String str)
	{
		try
		{
			char[] strArray = str.toCharArray();
			for(char c : strArray)
			{
				writer.writeByte((byte)c);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}



	public void close( )
	{
		if(writer == null)
		{
			return;
		}
		try
		{
			writer.flush();
			writer.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
