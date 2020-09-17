package AlgoritmerØvinger.A_star_13;

import java.io.File;
import java.util.Scanner;

public class Main
{
    static File noder_norden   = new File("/Users/sabineseljeseth/IdeaProjects/Øvinger/src/AlgoritmerØvinger/Øving13/filer/noder_norden.txt");
    static File kanter_norden  = new File("/Users/sabineseljeseth/IdeaProjects/Øvinger/src/AlgoritmerØvinger/Øving13/filer/kanter_norden.txt");
    static File noder_island   = new File("/Users/sabineseljeseth/IdeaProjects/Øvinger/src/AlgoritmerØvinger/Øving13/filer/noder_island.txt");
    static File kanter_island  = new File("/Users/sabineseljeseth/IdeaProjects/Øvinger/src/AlgoritmerØvinger/Øving13/filer/kanter_island.txt");



    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        int start;
        int stopp;

        Graf norden = null;
        Graf island = null;

        boolean norden_loaded = false;
        boolean island_loaded = false;


        boolean loop = true;
        while(loop)
        {
            System.out.println("\nHvor skal du reise? 1:Norden  2:Island  3:Avslutt");
            int input = scanner.nextInt();

            switch(input)
            {
                case 1:

                    if(!norden_loaded)
                    {
                        norden = new Graf(noder_norden, kanter_norden);
                        norden_loaded = true;
                    }

                    System.out.println("Hvor reiser du fra?");
                    start = scanner.nextInt();
                    System.out.println("\n");

                    System.out.println("Hvor reiser du til?");
                    stopp = scanner.nextInt();
                    System.out.println("\n");

                    norden.Dijkstras(start, stopp);
                    norden.Astjerne(start, stopp);
                    break;


                case 2:
                    if(!island_loaded)
                    {
                        island = new Graf(noder_island, kanter_island);
                        island_loaded = true;
                    }

                    System.out.println("Hvor reiser du fra?");
                    start = scanner.nextInt();
                    System.out.println("\n");


                    System.out.println("Hvor reiser du til?");
                    stopp = scanner.nextInt();
                    System.out.println("\n");


                    island.Dijkstras(start, stopp);
                    island.Astjerne(start, stopp);


                    break;

                case 3:
                    loop = false;
                    break;

            }

        }

    }
}
