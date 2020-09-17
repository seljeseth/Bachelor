import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Arrays;
class Restaurant{
	String navn;
	int etableringsaar;
	Bord bordoversikt;
	int antallBord;
	GregorianCalendar kalender = new GregorianCalendar();
	
	
	public Restaurant(String navn, int etableringsaar, Bord bordoversikt){
		this.navn = navn;
		this.etableringsaar = etableringsaar;
		this.bordoversikt = bordoversikt;
	}
	
	public String getNavn(){
		return navn;
	}
	
	public void setNavn(String navn){
		this.navn = navn;
	}
	
	public int getEtableringsaar(){
		return etableringsaar;
	}
	
	public int getAlder(){
		int aar = kalender.getInstance().get(Calendar.YEAR);
		int alder = (aar - etableringsaar);
		return alder;
	}
	
	public int getLedigeBord(){
		return bordoversikt.antLedigeBord();
	}
	
	public int getOpptatteBord(){
		return bordoversikt.antOpptatteBord();
	}
	
	public String tabell(){
		return bordoversikt.hentTabell();
	}
	
	public String resBord(int antall, String navn){
		String print = "";
		if(antall < bordoversikt.antLedigeBord()){
			bordoversikt.reserverBord(antall, navn);
			print += antall + " bord er registrert på " + navn;
		}else{
			print += "Det er dessverre fullt!";
		} 
		return print;
	}
	
	public String hentReservasjon(String navn){
		int[] bordRes = bordoversikt.bordReservert(navn);
		String utskrift = "";
		String resBord;
		if(navn != null){
			resBord = Arrays.toString(bordRes);
			utskrift += navn + " har reservert bordene: " + resBord;
			}else{
				utskrift += "Du må fylle inn et navn!";
			}
			return utskrift;
		}
	
	public String frigjorBord(int[] ryddigeBord){
		bordoversikt.friBord(ryddigeBord);
		String utskrift = "Bord(ene) " + Arrays.toString(ryddigeBord) + " er frigjort og klar(e) for nye reservasjoner";
		return utskrift;
	}
		
	
	public String toString(){
		String print = "";
		print += "Restauranten heter " + getNavn();
		print += "\nDen ble etablert " + getEtableringsaar();
		print += "\nAlder: " + getAlder();
		print += "\nledige bord: " + getLedigeBord();
		print += "\nopptatte bord: " + getOpptatteBord();
		return print;
		
		 
	}
	
	public static void main(String[] args){
		int antBord = 10;
		Bord bord = new Bord(antBord);
		//int [] fri = {2,5};
		Restaurant restaurant = new Restaurant("ass", 1990, bord);
		restaurant.resBord(3, "Sabine");
		restaurant.resBord(2, "Sabine");
		restaurant.resBord(3, "Sivvi");
		//System.out.println(restaurant.frigjorBord(fri));
		//System.out.println(restaurant.toString());
		System.out.println(restaurant.hentReservasjon("Sabine"));
		System.out.println(restaurant.hentReservasjon("Sivvi"));
		//System.out.println(restaurant.resBord(12, "Sabine"));
		System.out.println(restaurant.tabell());
	}
}