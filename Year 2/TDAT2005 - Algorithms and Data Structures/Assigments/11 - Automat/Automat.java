package AlgoritmerØvinger.Automata_11;

public class Automat
{
    char[] inputAlfabet;
    int[] aksept_Tilstander;
    int[][] neste_tilstand;
    int index;


    public Automat(char[] inputAlfabet, int[] akseptTilstander, int[][] neste_tilstand)
    {
        this.inputAlfabet = inputAlfabet;
        this.aksept_Tilstander = akseptTilstander;
        this.neste_tilstand = neste_tilstand;

    }

    public boolean sjekkInput(char[] input)
    {
        int tilstand = 0;
        boolean akseptert;

        for (int i = 0; i < input.length; i++)
        {
            for (int j = 0; j < inputAlfabet.length; j++)
            {
                if (input[i] == inputAlfabet[j])
                {
                    index = j;
                }
            }


            tilstand = neste_tilstand[tilstand][index];

        }
        akseptert = finnGodkjentTilstand(tilstand);

        return akseptert;
    }

    private boolean finnGodkjentTilstand(int tilstand)
    {
        for (int i = 0; i < aksept_Tilstander.length; i++)
        {
            if (tilstand == aksept_Tilstander[i])
            {
                return true;
            }
        }

        return false;
    }


    public static void main(String[] args)
    {

        char[] oppgave10_a_alfabet = new char[]{0, 1};
        int[] oppgabe10_a_aksepterteTilstander = new int[]{2};
        int[][] oppgabe10_a_nestetilstand = new int[][]{{1, 3}, {1, 2}, {2, 3}, {3, 3}};


        //oppgave 10 A
        Automat oppgave10_a = new Automat(oppgave10_a_alfabet, oppgabe10_a_aksepterteTilstander, oppgabe10_a_nestetilstand);
        System.out.println("Oppgave 10a\n010 er et akseptert input? " + oppgave10_a.sjekkInput(new char[]{0, 1, 0}));
        System.out.println("111 er et akseptert input? " + oppgave10_a.sjekkInput(new char[]{1, 1, 1}));
        System.out.println("010110 er et akseptert input? " + oppgave10_a.sjekkInput(new char[]{0, 1, 0, 1, 1, 0}));
        System.out.println("001000 er et akseptert input? " + oppgave10_a.sjekkInput(new char[]{0, 0, 1, 0, 0, 0}));
        System.out.println("1000 er et akseptert input? " + oppgave10_a.sjekkInput(new char[]{1, 0, 0, 0}));

        //oppgave 10 B

        char[] oppgave10_b_alfabet = new char[]{'a', 'b'};
        int[] oppgabe10_b_aksepterteTilstander = new int[]{2};
        int[][] oppgabe10_b_nestetilstand = new int[][]{{1, 3}, {4, 2}, {2, 2}, {2, 4}, {4, 4}};

        Automat oppgave10_b = new Automat(oppgave10_b_alfabet, oppgabe10_b_aksepterteTilstander, oppgabe10_b_nestetilstand);
        System.out.println("\nOppgave 10b\nabbb er et akseptert input? " + oppgave10_b.sjekkInput(new char[]{'a', 'b', 'b', 'b'}));
        System.out.println("aaab er et akseptert input? " + oppgave10_b.sjekkInput(new char[]{'a', 'a', 'a', 'b'}));
        System.out.println("babab er et akseptert input? " + oppgave10_b.sjekkInput(new char[]{'b', 'a', 'b', 'a',
                                                                                               'b'}));


    }

}
