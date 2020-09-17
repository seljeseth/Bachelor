package Eksamen_2019;

public abstract class Person
{
    String fornavn;
    String etternavn;
    int personnummer;

    public Person(String fornavn, String etternavn, int personnummer)
    {
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.personnummer = personnummer;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public int getPersonnummer() {
        return personnummer;
    }

    public void setPersonnummer(int personnummer) {
        this.personnummer = personnummer;
    }
    // fornavn, etternavn og personnummer. Klasse skal ha metoder for å hente og
    //sette disse attributtene

}
