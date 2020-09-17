public final class Person{
	
	private final String fornavn;
	private final String etternavn;
	private final int fAar;
	
	public Person(String fornavn, String etternavn, int fAar){
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.fAar = fAar;
	}
	
	public String getEtternavn(){
		return etternavn;
	}
	
	public String getFornavn(){
		return fornavn;
	}
	
	public int getFodselsAar(){
		return fAar;
	}
	/*public static void main(String[] args){
		
		Person test = new Person("Sabine", "Seljeseth", 1999);
		System.out.println(test.getEtternavn());
		System.out.println(test.getFornavn());
		System.out.println(test.getFodselsdato());
	}*/
	
}