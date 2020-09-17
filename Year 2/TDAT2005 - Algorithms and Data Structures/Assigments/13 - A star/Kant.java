package AlgoritmerØvinger.A_star_13;

public class Kant
{
    Node fraNode, tilNode;
    int lengde;
    int kjøretid; //kjøretid = kantvekt (i hundredels sekunder)
    int fartsgrense;
    Kant neste;
    //Kjøretiden er i hundredels sekunder, lengden er i meter, fartsgrensen i km/h.


    public Kant(Node fraNode, Node tilNode, int kjøretid, int lengde, int fartsgrense)
    {
        this.fraNode = fraNode;
        this.tilNode = tilNode;
        this.kjøretid = kjøretid;
        this.lengde = lengde;
        this.fartsgrense = fartsgrense;


    }

    void neste_kant(Kant k)
    {

        if(neste == null)
        {
            neste = k;
            return;
        }
        neste.neste_kant(k);
    }
}
