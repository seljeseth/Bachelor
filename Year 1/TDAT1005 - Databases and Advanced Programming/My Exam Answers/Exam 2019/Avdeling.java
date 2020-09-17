package Eksamen_2019;

import java.util.ArrayList;

public class Avdeling {
    String avdelingsnavn;
    ArrayList<Ansatt> ansatte;
    ArrayList<Pasient> pasienter;

    public Avdeling(String avdelingsnavn) {
        this.avdelingsnavn = avdelingsnavn;
        ansatte = new ArrayList<>();
        pasienter = new ArrayList<>();
    }

    public String getAvdelingsnavn() {
        return avdelingsnavn;
    }

    public void setAvdelingsnavn(String avdelingsnavn) {
        this.avdelingsnavn = avdelingsnavn;
    }

    public ArrayList<Ansatt> getAnsatte() {
        return ansatte;
    }

    public ArrayList<Pasient> getPasienter() {
        return pasienter;
    }

    public void removePasient(Pasient p)
    {
        pasienter.remove(p);
    }

    public void removeAnsatt(Ansatt a)
    {
        pasienter.remove(a);
    }

    public int getantAnsatte(){
        return ansatte.size();
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Avdeling)) return false;
        Avdeling a = (Avdeling) obj;
        return this.avdelingsnavn.equalsIgnoreCase(a.avdelingsnavn);
    }
}
