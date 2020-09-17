import static javax.swing.JOptionPane.*;
public class Tekstbehandling{
	
	String teksten; 
	
	
	public Tekstbehandling(String teksten){
		this.teksten = teksten;
	} //end Tekstbehandling
	
	public int antallOrd(){
		String setning = "";
		String[]antOrd = teksten.split(" "); 
		return antOrd.length;
			
	}//end antallOrd
	
	public String hentTeksten(){
		return teksten;
	} //end hentTeksten
	
	public String hentUppercaseTekst(){
		return teksten.toUpperCase();
	} //end hentLowercaseTekst
	
	public double gjennomsnittligOrdlengde(){
		String cleanTekst = teksten.replaceAll("[;,.?!]", "");
		String[] arrTekst = cleanTekst.split(" "); 
		int antBokstaver = 0;
		for(int i = 0; i < arrTekst.length; i++){
			
			antBokstaver += arrTekst[i].length();
		
		} //end for løkke
		
		double gjennomsnitt = (double)antBokstaver/(double)arrTekst.length;
		return gjennomsnitt;
	
	 }//end gjennomsnittligOrdlengde
		
	public String erstattOrd(String ord, String nyttOrd){
		return teksten.replaceAll(ord, nyttOrd);
		
	} //end erstattOrd
	
	public double antallOrdPerPeriode(){
		String[] antOrd = teksten.split(" ");
		String[] arrPeriode = teksten.split("[;.?!]");
		double antOrd1 = antOrd.length;
		double arrPeriode1 = arrPeriode.length;
		double gjennomsnitt =  antOrd1/arrPeriode1;
		return gjennomsnitt;
		
	}//end antallOrdPerPeriode*/
			
			
	
	
	public static void main(String[] args){
		
		Tekstbehandling test = new Tekstbehandling("test test");
		
		System.out.println(test.hentTeksten()i);
		System.out.println(test.hentUppercaseTekst());
		System.out.println("Setningen inneholder" + " " + test.antallOrd()+ " " + "ord");
		System.out.println(test.erstattOrd(" "," " ));
		System.out.println(test.gjennomsnittligOrdlengde());
		System.out.println(test.antallOrdPerPeriode());
	} //end main*/
} //end class


	
	
