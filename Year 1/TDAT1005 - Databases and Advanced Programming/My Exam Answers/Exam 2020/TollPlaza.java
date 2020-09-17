package Eksamen_2020;
//En bomstasjon inneholder en liste med passeringer (TollPassage) og en ID. ID er av typen string og identifiserer bomstasjonen.

import java.util.ArrayList;

public class TollPlaza {
    ArrayList<TollPassage> passeringer;
    String id;

    public TollPlaza(String id)
    {
        this.id = id;
        passeringer = new ArrayList<TollPassage>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<TollPassage> getPasseringer() {
        return passeringer;
    }
}
