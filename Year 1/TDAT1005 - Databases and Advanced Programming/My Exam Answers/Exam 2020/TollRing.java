package Eksamen_2020;

import java.util.ArrayList;

public class TollRing {
    ArrayList<TollPlaza> bomstasjoner;
    String navn;

    public void setBomstasjoner(ArrayList<TollPlaza> bomstasjoner) {
        this.bomstasjoner = bomstasjoner;
    }

    public TollRing(String navn)
    {
        this.navn = navn;
        bomstasjoner = new ArrayList<TollPlaza>();
    }

    public ArrayList<TollPlaza> getBomstasjoner() {
        return bomstasjoner;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }
}
