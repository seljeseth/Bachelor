import static javax.swing.JOptionPane.*;

class Oppgave1{
	public static void main(String[] args){
		
		Person person = new Person("Svettlana", "Olsen", 1990);
		ArbTaker arbtaker = new ArbTaker(person, 01, 2015, 16000, 30);
		
				
		final String[] alternativer = {"Hent ut info","Endre info","Annet", "Avslutt"};
		
		while(true){
			int valg = showOptionDialog(null, "Hva vil du gjøre?", "Arbeidstaker", 0, QUESTION_MESSAGE, null, alternativer, alternativer[0]);
			String print = "";
			switch(valg){
				case 0: 
						print = "Arbeidstakernummer: " + arbtaker.getArbeidsnr();
						print += "\nAnsettelsesår: " + arbtaker.getAnsettelsesAar();
						print += "\nEtternavn, Fornavn: " + arbtaker.etternavnOgFornavn();
						print += "\nAlder: " + arbtaker.alder(); 
						print += "\nMånedslønn: " + arbtaker.getMaanedslonn() + " NOK";
						print += "\nSkatteprosent: " + Double.toString(arbtaker.getSkatteprosent()).replace(".", ",") + "%";
						print += "\nSkatt pr. måned: " + arbtaker.skattTrukket() + " NOK";
						print += "\nBruttolønn pr. år: " + arbtaker.bruttoLonnprAar() + " NOK";
						print += "\nSkattetrekk pr. år: " + Double.toString(arbtaker.skatteTrekkprAAr()).replace(".", ",") + " NOK";
						showMessageDialog(null, print);
						break;
				case 1: 
						String[] nyeAlternativer = {"Endre månedslønn", "Endre skatteprosent"};
						int valgNyeAlternativer = showOptionDialog(null, "Hva vil du endre på?", "endringer" , 0, PLAIN_MESSAGE, null, nyeAlternativer, nyeAlternativer[0]);
						switch(valgNyeAlternativer){
							case 0:
									String nyMaanedsLonn = showInputDialog("Hva vil du endre til?");
									arbtaker.setMaanedslonn(Integer.parseInt(nyMaanedsLonn));
									break;
							case 1:
									String nySkatteProsent = showInputDialog("Hva vil du endre til?");
									arbtaker.setSkatteprosent(Integer.parseInt(nySkatteProsent));
									break;
								}
						break;
					
				case 2:
						String lesAar = showInputDialog("Sjekk om personen har vært ansatt lengre enn:");
						if(arbtaker.harVertAnsattLengreEnn(Integer.parseInt(lesAar))){
							showMessageDialog(null, arbtaker.personalia.getFornavn() + arbtaker.personalia.getEtternavn() + " har vært ansatt lengre enn " + lesAar);
						} else {
							showMessageDialog(null, arbtaker.personalia.getFornavn() + arbtaker.personalia.getEtternavn()  + " har ikke vært ansatt lengre enn " + lesAar);
						}
						
						break;
				default:
						return;
			}	
		}
	}
}