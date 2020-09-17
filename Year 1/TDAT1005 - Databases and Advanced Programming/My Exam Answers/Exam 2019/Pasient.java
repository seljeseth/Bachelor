package Eksamen_2019;

public class Pasient extends Person {
    String diagnose = "";
    public Pasient(String fornavn, String etternavn, int personnummer) {
        super(fornavn, etternavn, personnummer);
    }
    public String getDiagnose()
    {
        return diagnose;
    }

    public void setDiagnose(String d, Object o)
    {
        if(o instanceof Lege)
        {
            diagnose = d;
        }
        System.out.println("Vennligst kontakt en lege for å diagnosere pasienten!");
    }
}
