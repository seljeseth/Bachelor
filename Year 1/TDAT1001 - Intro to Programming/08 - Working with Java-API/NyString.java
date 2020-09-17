import static javax.swing.JOptionPane.*;
public final class NyString{
	
	static String tekst; 
	
	public NyString (String tekst){
		this.tekst = tekst;
	}//end metode NyString
	
	public String forkorting(){
			String setning = "";  
			String[] arrTekst = tekst.split(" ");  
			for(int i = 0; i < arrTekst.length; i++){
				setning += arrTekst[i].charAt(0);
			} //end for løkke
			
			return setning;
			
		}//end forkorting metode
	   
	public static String fjerningAvTegn(String bokstav){
		
		return tekst.replaceAll(bokstav, "");
	}//end of fjerningAvTegn metoden*/

	public static void main(String []args){

	NyString test = new NyString ("Dette er en test");
	System.out.println(test.forkorting());
	System.out.println(test.fjerningAvTegn("e"));
	
	}//end main
}//end class
