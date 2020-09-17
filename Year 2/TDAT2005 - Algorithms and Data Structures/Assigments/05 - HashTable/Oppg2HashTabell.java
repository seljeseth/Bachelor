package AlgoritmerØvinger.HashTable_5;

import java.util.HashMap;

public class Oppg2HashTabell
{
    int[] tabell;
    int antKollisjoner = 0;

    public Oppg2HashTabell(int str)
    {
        tabell = new int[str];
    }

    public void settInn(int tall)
    {
        int index = hash(tall);
        tabell[index] = tall;
    }


    public int hash(int tall)
    {
        int index =  tall % tabell.length;
        if(tabell[index] == 0 || tabell[index] == tall)
        {
            return index;
        }
        return kollisjon(index, 1, tall);
    }



    public int kollisjon(int index,  int kollisjonNr, int tall)
    {
        antKollisjoner++;
        int nyIndex = (index + kollisjonNr + tall) % tabell.length;
        if(tabell[nyIndex] == 0 || tabell[nyIndex] == tall)
        {
            return nyIndex;
        }
        return kollisjon(nyIndex, kollisjonNr + 1, tall);
    }

    public int tid_java(int[] random_tabell)
    {
        Long start = System.currentTimeMillis();
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < random_tabell.length; i++)
        {
            map.put(random_tabell[i], random_tabell[i]);
        }
        Long slutt = System.currentTimeMillis();
        return  (int)(slutt - start);
    }


    public int tid_egen_tabell(int[] random_tabell)
    {
        Long start = System.currentTimeMillis();
        for(int i = 0; i < random_tabell.length; i++)
        {
            settInn(random_tabell[i]);
        }
        Long slutt = System.currentTimeMillis();
        return  (int)(slutt - start);
    }

    public static void main(String[] args)
    {
        RandomTallTabell tabell = new RandomTallTabell();
        Oppg2HashTabell hashtabell = new Oppg2HashTabell(5500011);


        int tid_egen = hashtabell.tid_egen_tabell(tabell.tabell);
        System.out.println("Tid for min hashtabell i ms: " + tid_egen);
        int tid_java = hashtabell.tid_java(tabell.tabell);
        System.out.println("Tid for javas hashtabell i ms: " + tid_java);
        System.out.println("Min hashtabell er " + Math.round(1000.0 * tid_java) / (tid_egen)/1000.0 + " ganger raskere enn java sin");
        System.out.println("Antall kollisjoner: " + hashtabell.antKollisjoner);




    }
}
