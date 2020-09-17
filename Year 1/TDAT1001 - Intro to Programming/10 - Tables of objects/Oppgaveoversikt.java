public class Oppgaveoversikt{
	
	private Student[] studenter;
	private int antStud = 0;
	
	public Oppgaveoversikt(Student[] studenter){
		this.studenter = studenter;
		this.antStud = studenter.length;		
	}
	public String studenterToString(){
		String stringStudentNavn= "";
		for(int i = 0; i < studenter.length; i++){
			stringStudentNavn += i + ": " + studenter[i].getNavn() + ", ";
		}
		return stringStudentNavn;
	}
	public String studentToString(){
		String studentNavn= "";
		for(int i = 0; i < studenter.length; i++){
			studentNavn += studenter[i].getNavn() + "\n";
		}
		return studentNavn;
	}
	public Student hentStudent(int indeks){
		return studenter[indeks];
	}
	public int antallOppgaverGjort(Student student){
		return student.getAntOppg();
	}
	public int setAntallOppgaver(Student student, int oppgaverGjort){
		oppgaverGjort += student.getAntOppg();
		return oppgaverGjort;
	}
	public boolean registrerNyStudent(String navn, int antOppg){
		if(antStud == studenter.length){
			utvidTabell();
		}
		studenter[antStud] = new Student(navn, antOppg);
		antStud++;
		return true ; 
	}	
	private void utvidTabell(){
		Student[] nyTab = new Student[studenter.length + 1];
		for(int i=0; i<studenter.length;i++){
			nyTab[i]=studenter[i];
		}
		studenter=nyTab;
	}
	public int finnAntallRegistrerteStudenter(){
		antStud = studenter.length;
		return antStud;
	}
}	
