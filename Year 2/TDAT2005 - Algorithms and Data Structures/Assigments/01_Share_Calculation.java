package AlgoritmerØvinger;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


public class Øving1
{
    public static int salgsdag;
    public static int kjøpsdag;
    public static double tid_med_10000;

    public static int[] lagtabell(int antDager)
    {
        int[] a = new int[antDager];
        for(int i = 0; i < a.length; i++)
        {
            a[i] = new Random().nextInt(20)-10;
        }
        return a;
    }

    public static void printtabell(int[] tabell)
    {
        for(int i = 0; i < tabell.length; i++)
        {
            System.out.println(tabell[i]);
        }
    }

    public static ArrayList<Integer> bunnpunkt(int[] tabell)
    {
        ArrayList<Integer> bunnpunkter = new ArrayList<>();
        bunnpunkter.add(0);
        for(int i = 0; i < tabell.length - 1; i++) //n
        {
            if(tabell[i] < 0 && 0 < tabell[i+1])
            {
                bunnpunkter.add(i);
            }
        }
        return bunnpunkter;
    }

    public static ArrayList<Integer> toppunkt(int[] tabell)
    {
        ArrayList<Integer> toppunkter = new ArrayList<>();
        for(int i = 0; i < tabell.length - 1; i++) //n
        {
            if(tabell[i] > 0 && 0 > tabell[i+1])
            {
                toppunkter.add(i);

            }

        }
        toppunkter.add(tabell.length-1);
        return toppunkter;
    }



    public static void finnSalgsdag(int[] tabell)
    {
        ArrayList<Integer> toppunkter = toppunkt(tabell);

        ArrayList<Integer> bunnpunkter = bunnpunkt(tabell);

        int høyestVerdi = 0;
        int[] pris = new int[tabell.length];
        pris[0] = tabell[0];
        for(int i=1; i < tabell.length; i++)
        {
            pris[i] = pris[i-1] + tabell[i];
        }
        for(int dag_bunn : bunnpunkter)
        {
            for(int dag_topp : toppunkter) //n^2
            {
                if(dag_bunn < dag_topp)
                {
                    int salgsverdi = pris[dag_topp] - pris[dag_bunn];

                    if(salgsverdi > høyestVerdi)
                    {
                        høyestVerdi = salgsverdi;
                        kjøpsdag = dag_bunn + 1;
                        salgsdag = dag_topp + 1;

                    }

                }
            }

        }
    }

    public static double beregning(int antDager)
    {
        int[] tabell = lagtabell(antDager);

        Date start = new Date();
        int runder = 0;
        double tid;
        Date slutt;
        do {
        finnSalgsdag(tabell);
            slutt = new Date();
            ++runder;
        } while (slutt.getTime()-start.getTime() < 1000);
        tid = (double)
                (slutt.getTime()-start.getTime()) / runder;
        //System.out.println("\nMillisekund pr. runde: " + tid);

        double tidiSekn = (double)tid/1000;
        System.out.println("\nTid for " + antDager + " dager: " + tidiSekn + "s");
        if(antDager == 10000)
        {
            tid_med_10000 = tidiSekn;
        } else {
            System.out.println(antDager/10000 + " x mer dager: " + ((tidiSekn*10/tid_med_10000))/10 + "x så mye tid");
        }
        System.out.println("kjøp på dag: " + kjøpsdag + " Selg på dag: " + salgsdag);

        return tid;



    }


    public static void main(String[] args)
    {



        beregning(10000);
        beregning(20000);
        beregning(40000);
        beregning(2000000);



    }


}
