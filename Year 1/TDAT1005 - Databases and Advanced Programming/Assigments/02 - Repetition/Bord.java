import java.util.Arrays;

class Bord{
	String[] bordliste;
	
	public Bord(int antallBord){
		this.bordliste = new String[antallBord];
	}
	
	public int antLedigeBord(){
		int antLedige = 0;
		for(int i = 0; i < bordliste.length; i++){
			if(bordliste[i] == null){
				antLedige++;
			}
		}return antLedige;
	}
	
	public int antOpptatteBord(){
		int antOpptatte = 0;
		for(int i = 0; i < bordliste.length;i++){
			if(bordliste[i]!= null){
				antOpptatte++;
			}
		}return antOpptatte;
	}
	
	public void frigjørBord(int bordIndex){
		bordliste[bordIndex] = null;
	}
		

		
	public boolean reserverBord(int bordAnt,String navn){
		if(bordAnt < bordliste.length || bordAnt < antLedigeBord()){
			int teller = 0;
				for(int i = 0; i < bordliste.length; i++){
					if(bordliste[i] == null && teller < bordAnt){
						bordliste[i] = navn;
						teller++;
					}	
				}	
				return true;		
							
			}
			return false;		
		}
				
		
		public int[] bordReservert(String sjekkNavn){
			int teller = 0;
			for(int i = 0; i < bordliste.length; i++){
				if(bordliste[i] != null){
					if(bordliste[i].equalsIgnoreCase(sjekkNavn)){
					teller++;
				}
			}
		}
		int[] reservasjonsliste = new int[teller];
		teller = 0;
		for(int j = 0; j < bordliste.length; j++){
			if(bordliste[j] != null){
				if(bordliste[j].equalsIgnoreCase(sjekkNavn)){
					reservasjonsliste[teller] = j;
					teller++;
				}
						
			}
		}
		return reservasjonsliste;
	}
				


				
		
		public void friBord(int[] ryddigeBord){
			for(int j = 0; j < ryddigeBord.length; j++){
				frigjørBord(ryddigeBord[j]);
			}
		}
		
		public String hentTabell(){
			return Arrays.toString(bordliste);
		}
			
		
			
		
		
		
		
	
	public static void main(String[] args){
		int bord = 5;
		Bord test = new Bord(bord);
		test.reserverBord(3, "sabine");
		test.reserverBord(1,"ss");
		int[] ass = {1,2};
		//test.friBord({0,1});
		int[] bordres = test.bordReservert("sabine");
		int[] bordx = test.bordReservert("ss");
		
		//test.frigjørBord(3);
		test.friBord(ass);
		System.out.println("bord ledig: " + test.antLedigeBord());
		System.out.println("bord opptatt: " + test.antOpptatteBord());
		System.out.println("Du har reservert bord" + Arrays.toString(bordres));
		System.out.println("Du har reservert bord" + Arrays.toString(bordx));
		System.out.println(test.hentTabell());
	}
}

	
