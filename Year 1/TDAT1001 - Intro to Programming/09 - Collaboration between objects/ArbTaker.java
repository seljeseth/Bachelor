import java.util.GregorianCalendar;
import java.util.*;
public class ArbTaker{
	
	Person personalia;
	private int arbtakernr;
	private int ansettelsesaar;
	private int maanedslonn;
	private double skatteprosent;
	private GregorianCalendar kalender = new GregorianCalendar();
	int aar = kalender.get(Calendar.YEAR);
	
	
	public ArbTaker(Person personalia, int arbtakernr, int ansettelsesaar, int maanedslonn, double skatteprosent){
		String fornavn = personalia.getFornavn();
		String etternavn = personalia.getEtternavn();
		int fdato = personalia.getFodselsAar();
		this.personalia = new Person(fornavn, etternavn, fdato);
		this.arbtakernr = arbtakernr;
		this.ansettelsesaar = ansettelsesaar;
		this.maanedslonn = maanedslonn;
		this.skatteprosent = skatteprosent;
	}

	public int getArbeidsnr(){
		return arbtakernr;
	}
	
	public int getAnsettelsesAar(){
		return ansettelsesaar;
	}
	
	public int getMaanedslonn(){
		return maanedslonn;
	}
	
	public void setMaanedslonn(int maanedslonn){
		this.maanedslonn = maanedslonn;
	}
	
	public double getSkatteprosent(){
		return skatteprosent;
	}
	
	public void setSkatteprosent(double skatteprosent){
		this.skatteprosent = skatteprosent;
	}
	
	public double skattTrukket(){
		double skattprMaaned = maanedslonn * (skatteprosent/100);
		return skattprMaaned;
	}
	
	public int bruttoLonnprAar(){
		int bruttolonnprAar = maanedslonn*12;
		return bruttolonnprAar;
	}
	
	public double skatteTrekkprAAr(){
		double skattetrekk = maanedslonn * 10.5 * (skatteprosent/100); //juni er skattefri og desember er halv skatt
		return skattetrekk;
	}
	
	public String etternavnOgFornavn(){
		return personalia.getEtternavn() + ", "  + personalia.getFornavn();
	}
	
	public int alder(){
		return aar - personalia.getFodselsAar();
	}
	
	public int antallAArAnsatt(){
		int aarAnsatt = aar - ansettelsesaar;
		return aarAnsatt;
	}
	
	public boolean harVertAnsattLengreEnn(int sjekkAar){
		if(ansettelsesaar <  sjekkAar){
			return true;
		}else{
			return false;
		}
	}
}