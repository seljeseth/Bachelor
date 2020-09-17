import java.io.*;
import static javax.swing.JOptionPane.*;
import java.util.*;
class Konto{
  public static void main(String[] args) throws IOException {
	  try{
		  String startSaldo = "saldo.txt";
		  String transaksjoner = "transaksjoner.txt";
		  FileReader leseSaldo = new FileReader(startSaldo);
		  BufferedReader leser = new BufferedReader(leseSaldo);
		  String stringSaldo = leser.readLine();
		  leser.close();
		  double saldo = Double.parseDouble(stringSaldo);
	
		  String[] alternativer = {"Ta ut penger", "Sette inn penger", "Avslutt"};
		  boolean aktivTransaksjon = true;
		  while(aktivTransaksjon){
			  int valg = showOptionDialog(null, "Hva vil du \n gjøre?", "Saldo: " + saldo, 0, PLAIN_MESSAGE, null, alternativer, alternativer[2]);
			  FileWriter skrivTilTransaksjoner = new FileWriter(transaksjoner, true);
			  PrintWriter skriver = new PrintWriter(new BufferedWriter(skrivTilTransaksjoner));
			  double uttak = 0;
			  double innskudd = 0;
			  switch(valg){
				  case 0: String stringUttak = showInputDialog("Hvor mye vil du ta ut? ");
						  uttak = Double.parseDouble(stringUttak);
						  skriver.println("U: "+ uttak);
						  saldo-=uttak;
						  break;
					
				  case 1: String nyttInnskudd = showInputDialog("Hvor mye vil du sette inn? ");
						  innskudd = Double.parseDouble(nyttInnskudd);
						  skriver.println("I: "+ innskudd);
						  saldo+=innskudd;
						  break;
				  default: 
						  aktivTransaksjon = false;			
					  } 
					  skriver.close();
				  }
				  if(saldo >= 0){
					  FileWriter skrivTilSaldo = new FileWriter(startSaldo);
					  PrintWriter skriverUt = new PrintWriter(new BufferedWriter(skrivTilSaldo));	
					  skriverUt.println(saldo);
					  skriverUt.close(); 
				  }
			  } catch (IOException e){
				  System.out.println("IO-Feil ved åpning/lukking av fil");		
		  }
	  }
  }
	  


	
