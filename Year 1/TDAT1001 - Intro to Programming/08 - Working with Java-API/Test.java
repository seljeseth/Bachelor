import static javax.swing.JOptionPane.*;
class Test{
public static void main(String[] args){
	
			String tekst = showInputDialog("Skriv inn en tekst");
			String bytteOrd = showInputDialog("Skriv inn et ord du vil bytte ut");
			String nyttOrd = showInputDialog("Skiv inn et ord å bytte med");

			Tekstbehandling test = new Tekstbehandling (tekst);
			System.out.println("Setningen inneholder" + " " + test.antallOrd()+ " " + "ord");
			System.out.println("Gjennomsnittlig ordlengde er: " + test.gjennomsnittligOrdlengde());
			System.out.println("Gjennomsnittlig antall ord per periode er: " + test.antallOrdPerPeriode());
			System.out.println("Teksten med ordet erstattet: " + test.erstattOrd(bytteOrd, nyttOrd));
			System.out.println("Teksten du skrev inn: " + test.hentTeksten());
			System.out.println("TEKSTEN DU SKREV INN: " + test.hentUppercaseTekst());
			
		
	
	}
}
		
	
	
	