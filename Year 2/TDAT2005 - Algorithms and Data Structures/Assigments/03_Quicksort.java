package AlgoritmerØvinger;

import java.util.Date;
import java.util.Random;

public class Quicksort_3
{
    public static boolean sjekkSortering(int[] tabell)
    {
        for (int i = 0; i < tabell.length - 1; i++)
        {
            if (tabell[i + 1] < tabell[i])
            {
                return false;
            }
        }
        return true;
    }



    public static int[] kopierTabell(int[] tabell)
    {
        int[] nyTabell = new int[tabell.length];
        for (int i = 0; i < tabell.length; i++)
        {
            nyTabell[i] = tabell[i];
        }
        return nyTabell;
    }



    public static int sumAvTabell(int[] tabell)
    {
        int sum = 0;
        for (int i = 0; i < tabell.length; i++)
        {
            sum += tabell[i];
        }
        return sum;
    }



    public static String toString(int[] t)
    {
        String utskrift = "";
        for (int i = 0; i < t.length; i++)
        {
            utskrift += t[i] + "\n";
        }
        return utskrift;

    }



    public static void bytt(int[] tabell, int i, int j)
    {
        int k = tabell[j];
        tabell[j] = tabell[i];
        tabell[i] = k;
    }



    public static int[] lagtabell(int lengde)
    {
        int[] a = new int[lengde];
        for (int i = 0; i < a.length; i++)
        {
            a[i] = new Random().nextInt(1000);
        }
        return a;
    }



    public static int median3sort(int[] tabell, int v, int h)
    {
        int m = (v + h) / 2;
        if (tabell[v] > tabell[m]) bytt(tabell, v, m);
        if (tabell[m] > tabell[h])
        {
            bytt(tabell, m, h);
            if (tabell[v] > tabell[m]) bytt(tabell, v, m);
        }
        return m;
    }



    public static int splitt(int[] tabell, int v, int h)
    {
        int iv, ih;
        int m = median3sort(tabell, v, h);
        int dv = tabell[m];
        bytt(tabell, m, h - 1);
        for (iv = v, ih = h - 1; ; )
        {
            while (tabell[++iv] < dv) ;
            while (tabell[--ih] > dv) ;
            if (iv >= ih) break;
            bytt(tabell, iv, ih);
        }
        bytt(tabell, iv, h - 1);
        return iv;
    }



    public static void innsettingssort(int[] tabell, int fra, int til)
    {
        for (int j = fra + 1; j < til + 1; j++)
        {
            int bytt = tabell[j];
            int i = j - 1;
            while (i >= fra && tabell[i] > bytt)
            {
                tabell[i + 1] = tabell[i];
                i--;
            }
            tabell[i + 1] = bytt;
        }
    }



    public static void quicksort_medInsettingSortering(int[] tabell, int v, int h, int grense)
    {
        if (h - v > grense)
        {
            int delepos = splitt(tabell, v, h);
            quicksort_medInsettingSortering(tabell, v, delepos - 1, grense);
            quicksort_medInsettingSortering(tabell, delepos + 1, h, grense);

        }
        else
        {
            innsettingssort(tabell, v, h);

        }
    }



    public static double beregningTidQuicksortMedHjelpealgoritme(int[] tabell, int grense)
    {
        Date start = new Date();
        double tid;
        quicksort_medInsettingSortering(tabell, 0, tabell.length - 1, grense);
        Date slutt = new Date();

        tid = slutt.getTime() - start.getTime();

        return tid;

    }



    public static double beregningTidQuicksort(int[] tabell)
    {
        Date start = new Date();
        double tid;
        quicksort(tabell, 0, tabell.length - 1);
        Date slutt = new Date();

        tid = slutt.getTime() - start.getTime();

        return tid;

    }



    public static void quicksort(int[] tabell, int v, int h)
    {
        if ((h - v) > 2)
        {
            int delepos = splitt(tabell, v, h);
            quicksort(tabell, v, delepos - 1);
            quicksort(tabell, delepos + 1, h);

        }
        else
        {
            median3sort(tabell, v, h);

        }
    }



    public static void main(String[] args)
    {

        int[] test = lagtabell(10000000);
        System.out.println("ORGINAL TABELL:");
        System.out.println("Sortert: " + sjekkSortering(test));
        System.out.println("Sum: " + sumAvTabell(test) + "\n");

        System.out.println("QUICKSORT: ");
        int[] kopiTestForQuicksort = kopierTabell(test);
        System.out.println("Tid i ms: " + beregningTidQuicksort(kopiTestForQuicksort));
        System.out.println("Sum: " + sumAvTabell(kopiTestForQuicksort));
        System.out.println("Sortert: " + sjekkSortering(kopiTestForQuicksort) + "\n");

        System.out.println("QUICKSORT MED HJELPEMETODE:");
        int[] kopiTestForQuicksortMedHjelpealgoritme = kopierTabell(test);
        int grense = 350;
        System.out.println("Tid i ms: " + beregningTidQuicksortMedHjelpealgoritme(kopiTestForQuicksortMedHjelpealgoritme, grense));
        System.out.println("Grense: " + grense);
        System.out.println("Sum: " + sumAvTabell(kopiTestForQuicksortMedHjelpealgoritme));
        System.out.println("Sortert: " + sjekkSortering(kopiTestForQuicksortMedHjelpealgoritme) + "\n");
        System.out.println("Tid i ms etter at tabellen allerede er sortert: " + beregningTidQuicksortMedHjelpealgoritme(kopiTestForQuicksortMedHjelpealgoritme, grense));
        //forbedring: mål tiden i sekunder for mer nøyaktig måling av tiden


        /*Tider
         * QuicksortMedHjelpeALgoritme: 567 ms (grense 10)
         * QuicksortMedHjelpeALgoritme: 616 ms (grense 25)
         * QuicksortMedHjelpeALgoritme: 611 ms (grense 50)
         * QuicksortMedHjelpeALgoritme: 551 ms (grense 75)
         * QuicksortMedHjelpeALgoritme: 531 ms (grense 100)
         * QuicksortMedHjelpeALgoritme: 553 ms (grense 125)
         * QuicksortMedHjelpeALgoritme: 575 ms (grense 150)
         * QuicksortMedHjelpeALgoritme: 535 ms (grense 175)
         * QuicksortMedHjelpeALgoritme: 533 ms (grense 200)
         * QuicksortMedHjelpeALgoritme: 521 ms (grense 250)
         * QuicksortMedHjelpeALgoritme: 534 ms (grense 275)
         * QuicksortMedHjelpeALgoritme: 516 ms (grense 350)
         * QuicksortMedHjelpeALgoritme: 543 ms (grense 400)
         * QuicksortMedHjelpeALgoritme: 547 ms (grense 450)*/
    }
}
