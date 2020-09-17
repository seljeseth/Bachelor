package AlgoritmerØvinger.A_star_13;

public class Node
{
    double breddegrad_grader;
    double lengdegrad_grader;
    double breddegrad_radianer;
    double lengdegrad_radianer;
    double cos_bredde;

    Node forgjenger;
    Kant kantFra;

    int total_kjøretid;
    int total_distanse;
    int infinity = 1000000000;
    int nodenr;
    int avstand_til_destinasjon;






    public Node(int n, double bg, double lg)
    {
       nodenr = n;
       breddegrad_grader = bg;
       breddegrad_radianer = (bg * (Math.PI/180));
       lengdegrad_grader = lg;
       lengdegrad_radianer = (lg *(Math.PI/180));
       total_kjøretid = infinity;
       cos_bredde = Math.cos(breddegrad_radianer);

    }

    public void leggTilKant(Kant ny)
    {
        if (kantFra == null)
        {
            kantFra = ny;

        }else{


            kantFra.neste_kant(ny);
        }
    }

    public int getTotal_kjøretid()
    {
        return total_kjøretid;
    }
}
