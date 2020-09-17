public class Student{
	
	private String navn;
	private int antOppg;
	
	public Student(String navn, int antOppg){
		this.navn = navn;
		this.antOppg = antOppg;
	}
	
	public String getNavn(){
		return navn;
	} 
	
	public int getAntOppg(){
		return antOppg;
	}
	
	public void økAntOppg(int økning){
		if(økning < 0){
			System.out.println("Økningen kan ikke være negativ");
		}
		antOppg += økning;
	}
	
	public String toString(){
		return navn + " har gjort " + antOppg + " Oppgaver";
		
	}
	public static void main(String[] args){
		
		Student test = new Student("Sabine", 6);
		System.out.println(test.toString());
	}
}	