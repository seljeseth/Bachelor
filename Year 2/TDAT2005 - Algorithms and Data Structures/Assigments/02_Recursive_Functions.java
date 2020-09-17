package AlgoritmerØvinger;

import java.util.Date;

//implementing recursive functions
public class RecursiveFunctions_2 {

    public static double oppgave1(double x, int n) //n
    {
        if (n == 0)
        {
            return 1;
        }
        return oppgave1(x,n-1) * x;
    }

    public static double oppgave2(double x, int n)
    {
        if(n == 0)
        {
            return 1;
        }

        if(n % 2 == 0)
        {
            return oppgave2(x*x, n/2);
        }
        return oppgave2(x*x, ((n-1)/2)) * x;
    }
    public static double beregningTidOppgave1(double x, int n) //log n
    {
        Date start = new Date();
        int runder = 0;
        double tid;
        Date slutt;
        do {
            oppgave1(x,n);
            slutt = new Date();
            ++runder;
        } while (slutt.getTime()-start.getTime() < 1000);
        tid = (double)
                (slutt.getTime()-start.getTime()) / runder;
        System.out.println("Millisekund pr. runde for oppgave 1: " + tid);
        return tid;

    }

    public static double beregningTidOppgave2(double x, int n)
    {
        Date start = new Date();
        int runder = 0;
        double tid;
        Date slutt;
        do {
            oppgave2(x,n);
            slutt = new Date();
            ++runder;
        } while (slutt.getTime()-start.getTime() < 1000);
        tid = (double)
                (slutt.getTime()-start.getTime()) / runder;
        System.out.println("Millisekund pr. runde for oppgave 2: " + tid);
        return tid;


    }

    public static double beregningTidMathPow(double x, int n)
    {
        Date start = new Date();
        int runder = 0;
        double tid;
        Date slutt;
        do {
            Math.pow(x,n);
            slutt = new Date();
            ++runder;
        } while (slutt.getTime()-start.getTime() < 1000);
        tid = (double)
                (slutt.getTime()-start.getTime()) / runder;
        System.out.println("Millisekund pr. runde for mathpow: " + tid);
        return tid;

    }


    public static void main(String[] arg)
    {
       /* beregningTidOppgave1(1.001,5000);
        beregningTidOppgave2(1.001,5000);
        beregningTidMathPow(1.001,5000);*/

        System.out.println("Tidsforskjell på oppgave 1 og 2: " + beregningTidOppgave1(1.001,7000)/beregningTidOppgave2(1.001,7000));
        //utskrift = 433,6
        //7000/2log 7000 =



    }
}
