package AlgoritmerØvinger.Lempel_Ziv_12;


import java.io.File;

public class Main
{
	private final static String PATH = "src/AlgoritmerØvinger/Øving12/filer/";



	public static void main(String[] args)
	{
		printFilRatio("problemer");
		printFilRatio("oppgavetekst");
		printFilRatio("forelesningen");
	}



	private static void printFilRatio(String filnavn)
	{

		File original = new File(PATH + "original/" + filnavn + ".txt");
		File komprimert = new File(PATH + "komprimert/" + filnavn + "_LZ");
		File utpakket = new File(PATH + "utpakket/" + filnavn + "_LZ.txt");
		int org = (int)original.length();
		int komp = (int)komprimert.length();
		int utpa = (int)utpakket.length();

		System.out.println("\n\n\n" + filnavn);
		System.out.println("original størrelse: \t" + org + " byte");

		System.out.print("komprimert størrelse: \t" + komp + " byte \t - ");
		System.out.println((int)((100.0 * (org - komp)) / org) + "% spart");

		if(org == utpa)
		{
			System.out.print("utpakket størrelse: \t" + utpa + " byte\t - LIKT");
		}
		else
		{
			System.out.print("utpakket størrelse: \t" + utpa + " byte\t - ULIKT ");
		}
	}
}
