package AlgoritmerØvinger.Lempel_Ziv_12.Lempel_Ziv;

import AlgoritmerØvinger.Lempel_Ziv_12.Reader;
import AlgoritmerØvinger.Lempel_Ziv_12.Writer;

public class Utpakking_LZ
{
	private final int DEFAULT_BUFFER_SIZE = 32768;
	private Reader reader;
	private Writer writer;



	public Utpakking_LZ(String filnavn)
	{
		String path = "/filer/";
		this.reader = new Reader(path + "komprimert/" + filnavn);
		this.writer = new Writer(path + "utpakket/" + filnavn + ".txt");

		try
		{
			pakk_ut(DEFAULT_BUFFER_SIZE);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			reader.close();
			writer.close();
		}
	}



	private void pakk_ut(int bufferSize)
	{
		StringBuffer buffer = new StringBuffer(bufferSize);
		short b;
		int curIndex = 0; //holder styr på hvor vi er i den ukomprimerte filen
		int offset;
		//leser så lenge det er mer i fila
		while((b = reader.readShort()) != 0)
		{
			//om b er negativ vil det være en offset
			if(b > 0)
			{
				//b forteller hvor mange bokstaver som ikke er blitt skrevet som refereanser kommer
				//vil skrive charsene som ikke er blitt komprimert
				for(int i = 0 ; i < b ; i++)
				{
					char c = reader.readChar();
					writer.writeChar(c);
					buffer.append(c);
				}
				curIndex += b;
				offset = reader.readShort();
			}
			else
			{
				offset = b;
			}
			//etter dette vil vi få en referanse [<-, len]
			int length = reader.readChar();

			//start vil være indeksen til teksten som skal skrives

				int start = curIndex + offset; //hva om start er negativ?



			//skriver ordet som matcher til fil
			for(int indeks = start ; indeks < (start + length) ; indeks++)
			{
				char c = buffer.charAt(indeks);
				writer.writeChar(c);
				buffer.append(c);
			}

			trimBuffer(buffer , bufferSize);
			curIndex += length;

		}
	}



	private void trimBuffer(StringBuffer buffer , int max_size)
	{
		if(buffer.length() > max_size)
		{
			buffer.delete(0 , buffer.length() - max_size);
		}
	}



	public static void main(String[] args)
	{
		//Utpakking_LZ test = new Utpakking_LZ("test_LZ");
		Utpakking_LZ problemer_lz = new Utpakking_LZ("problemer_LZ");
		Utpakking_LZ oppgavetekst_lz = new Utpakking_LZ("oppgavetekst_LZ");
		Utpakking_LZ forelesningen_lz = new Utpakking_LZ("forelesningen_LZ");
	}
}
