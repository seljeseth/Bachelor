package Eksamen_2019;

import java.util.ArrayList;

public class Sykehus implements ISykehus {
    String navn;
    ArrayList<Avdeling> avdelinger;

    public Sykehus(String navn)
    {
        this.navn = navn;
        avdelinger = new ArrayList<>();
    }

    public ArrayList<Avdeling> getAvdelinger()
    {
        return avdelinger;
    }

    @Override
    public boolean registrer(Object o) {
        if(!(o instanceof Avdeling)) return false;
        avdelinger.add((Avdeling)o);
        return true;
    }

    @Override
    public boolean fjern(Object o) {
        if(o instanceof Avdeling)
        {
            avdelinger.remove(o);
        }else if(o instanceof Ansatt)
        {
            for(Avdeling a : avdelinger)
            {
                a.removeAnsatt((Ansatt) o);
            }
        }else if(o instanceof Pasient)
        {
            for(Avdeling a : avdelinger)
            {
                a.removePasient((Pasient) o);
            }
        }
        return false;
    }
}
