
import static javax.swing.JOptionPane.*;

class RestaurantTest{
	public static void main(String[] args){
		String navn = showInputDialog("Hva heter restauranten?");
		String stringEtableringsaar = showInputDialog("Når ble restauranten opprettet?");
		int etableringsaar = Integer.parseInt(stringEtableringsaar);
		String stringAntallbord = showInputDialog("Hvor mange bord har resturanten?");
		int antallBord = Integer.parseInt(stringAntallbord);
		Bord bord = new Bord(antallBord);
		Restaurant restaurant = new Restaurant(navn, etableringsaar, bord);
		boolean avslutt = true;
		final String[] alternativer = {"Reserver bord","Vis reservasjoner","Frigi bord", "Hent info","Avslutt"};
		
		while(avslutt){
			int valg = showOptionDialog(null, "Hva vil du gjøre?", "Restaurant", 0, QUESTION_MESSAGE, null, alternativer, alternativer[4]);
			switch(valg){
				case 0: String navnres = showInputDialog("Hvilket navn skal reservasjonen stå på?");
						String stringantallBord = showInputDialog("Hvor mange bord skal reserveres?");
						int antBord = Integer.parseInt(stringantallBord);
						showMessageDialog(null,restaurant.resBord(antBord, navnres));
						break;
					
	        	case 1: String sjekknavn = showInputDialog("Hvem skal du sjekke reservasjonene til?");
						String resultat = restaurant.hentReservasjon(sjekknavn);
				  		showMessageDialog(null, resultat);
						break;
					
	        	case 2: String stringBordfrigjor = showInputDialog("Hvor mange bord skal frigjøres?");
						int bordfrigjor = Integer.parseInt(stringBordfrigjor);
						int[] tabell = new int[bordfrigjor]; 
						for(int i = 0; i < tabell.length; i++){
							String input = showInputDialog("Skriv inn bordnummerert til bordet du vil frigjøre");
							int bordNr = Integer.parseInt(input);
							tabell[i] = bordNr;
						}
						showMessageDialog(null, restaurant.frigjorBord(tabell));
						break;
						
	      		case 3: showMessageDialog(null, restaurant.toString());
						break;
	        
				case 4: avslutt = false;
						break;
				}
			}
		}
	}