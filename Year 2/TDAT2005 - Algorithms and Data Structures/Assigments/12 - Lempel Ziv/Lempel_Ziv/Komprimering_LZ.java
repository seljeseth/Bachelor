package AlgoritmerØvinger.Lempel_Ziv_12.Lempel_Ziv;

import AlgoritmerØvinger.Lempel_Ziv_12.Reader;
import AlgoritmerØvinger.Lempel_Ziv_12.Writer;

public class Komprimering_LZ
{
    private static final int DEFAULT_BUFFER_SIZE = 32768;

    private Reader reader;
    private Writer writer;



    public Komprimering_LZ(String filnavn)
    {

        String path = "/filer/";
        this.reader = new Reader(path + "original/" + filnavn + ".txt");
        this.writer = new Writer(path + "komprimert/" + filnavn + "_LZ");

        komprimer(DEFAULT_BUFFER_SIZE);

        reader.close();
        writer.close();
    }



    private void komprimer(int bufferSize)
    {
        StringBuffer searchBuffer = new StringBuffer(bufferSize);   //fungerer som en slider over teksten slik at vi kan sjekke om vi har vært borti like ord før
        char nextChar;                                              //det neste vi leser fra filen
        int matchIndex = 0, tempMatchIndex = 0;                     //forteller hvor en match er
        int curIndex = -1;                                          //indexen til char vi er på
        String currentMatch = "";                                   //chars som matcher i bufferen det vi leser
        String unMatched = "";                                      //chars som ikke matcher eller som ikke er lang nok

        // fortsetter så lenge det er tegn igjen å lese
        while ((nextChar = reader.readChar()) != 0)
        {
            curIndex++;
            tempMatchIndex = searchBuffer.indexOf(currentMatch + nextChar); //sjekker i bufferen om vi har en match

            searchBuffer.append(nextChar);

            //om den oppdager en match i med innholdet til bufferen
            //og denne matchen ikke er lengere enn 1 byte
            if (tempMatchIndex != -1 && currentMatch.length() < 127)
            {
                currentMatch += nextChar;       // legger charen til Stringen
                matchIndex = tempMatchIndex;    // oppdaterer match indexen

            }
            else
            {
                if (currentMatch.length() == 0) //fant ingen matchene chars i bufferen
                {
                    unMatched += nextChar; //legger charen til i unMatched
                }
                else if (currentMatch.length() > 3) //Om matchen er lang nok til at det er gunstig å erstatte den med en referanse
				{
					//skriver hvor mange chars som ikke er matchet i teksten
					//deretter stringen med chars den ikke kunne matche med noe i bufferen
					if (unMatched.length() > 0)
					{
						write_Unmatch(unMatched);
					}

                    //offseten sier noe om hvor mye vi må tilbake for å nå ordet som matchet
                    //length er så hvor langt ordet som matchet er
                    int offset = matchIndex - (curIndex - currentMatch.length());
                    int length = currentMatch.length();

                    //[<-, len]
                    write_match(offset, length);


                    if (searchBuffer.indexOf("" + nextChar) != -1)
                    {
                        unMatched = "";
                        currentMatch = "" + nextChar;
                    }
                    else
                    {
                        unMatched = "" + nextChar;
                        currentMatch = "";
                    }
                }
                else //om vi finner en match men den ikke er lang nok til at det er gunstig å erstatte med en referanse
                {
                    if (searchBuffer.indexOf("" + nextChar) != -1)
                    {
                        unMatched += currentMatch;
                        currentMatch = "" + nextChar;
                    }
                    else
                    {
                        unMatched += currentMatch + nextChar;
                        currentMatch = "";
                    }


                }
                //Tilpasser str til bufferen om den blir for stor
                trimBuffer(searchBuffer, bufferSize);
            }
        }

        curIndex++;
        //skriver ut hva vi har på enden av filen
        if (currentMatch.length() > 3)
        {
            write_Unmatch(unMatched);

            int offset = matchIndex - (curIndex - currentMatch.length());
            int length = currentMatch.length();
            write_match(offset, length);
        }
        else
        {
            if (currentMatch.length() > 0)
            {
                unMatched += currentMatch;
            }
            write_Unmatch(unMatched);
        }
    }



    //skulle bufferen bli for stor må vi kunne trimme den ned
    private void trimBuffer(StringBuffer buffer, int max_size)
    {
        if (buffer.length() > max_size)
        {
            buffer.delete(0, buffer.length() - max_size);
        }
    }



    //skriver ut referansen [offset, length]
    private void write_match(int offset, int length)
    {
        //System.out.println("[" + offset + "," + length + "]");
        writer.writeRef(offset, length);
    }



    //[ant_chars] antall bokstaver som kommer som ikke er funnet en match for
    private void write_Unmatch(String unMatched)
    {
        //System.out.println("[" + unMatched.length() + "]");
        writer.writeShort(unMatched.length());
        //System.out.println(unMatched);
        writer.print(unMatched);
    }



    public static void main(String[] args)
    {
        //Komprimering_LZ test = new Komprimering_LZ("test");
        Komprimering_LZ problemer = new Komprimering_LZ("problemer");
        Komprimering_LZ oppgavetekst = new Komprimering_LZ("oppgavetekst");
        Komprimering_LZ forelesningen = new Komprimering_LZ("forelesningen");
    }
}
