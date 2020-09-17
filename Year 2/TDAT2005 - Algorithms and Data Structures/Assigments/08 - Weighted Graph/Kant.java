package AlgoritmerØvinger.WeightedGraph_8;

public class Kant
{
    Node tilNode;
    int kapasitet;
    int flyt;
    Kant motsattKant = null;
    Kant neste;

    public Kant(Node f,Node t, int k)
    {
        tilNode = t;
        kapasitet = k;
        motsattKant = new Kant(f,this);
        t.leggTilKant(motsattKant);
    }

    private Kant(Node t, Kant motsatt)
    {
        tilNode = t;
        kapasitet = 0;
        motsattKant = motsatt;
    }

    public int getRestKapasitet()
    {
        return (kapasitet - flyt);
    }


    public void addFlyt(int i)
    {
        flyt += i;
        motsattKant.flyt -= i;
    }

    //Hjelpemetode for å opprette en lenket liste
    void neste_kant(Kant k)
    {
        /*om vi allerede har en motsatt kant i grafen vil vi
         sette kapasiteten som samsvarer med den motsatte kanten som ble opprettet*/
        if(k.tilNode == tilNode)
        {
            kapasitet = k.kapasitet;
            return;
        }
        if(neste == null)
        {
            neste = k;
            return;
        }
        neste.neste_kant(k);


    }




}

