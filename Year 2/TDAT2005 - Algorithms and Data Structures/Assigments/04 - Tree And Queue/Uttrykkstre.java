package AlgoritmerØvinger.TreeAndQueue_4;

//OPPGAVE 2
public class Uttrykkstre
{
    TreNode rot;

    public Uttrykkstre()
    {
        rot = new TreNode('*');

        rot.venstre = new TreNode('*');
        rot.venstre.venstre = new TreNode(3);
        rot.venstre.høyre = new TreNode('+');
        rot.venstre.høyre.venstre = new TreNode(2);
        rot.venstre.høyre.høyre = new TreNode(4);

        rot.høyre = new TreNode('-');
        rot.høyre.venstre = new TreNode(7);
        rot.høyre.høyre = new TreNode('*');
        rot.høyre.høyre.høyre = new TreNode(2);
        rot.høyre.høyre.venstre = new TreNode(2);
    }

    public boolean sjekkLøvnode(TreNode n)
    {
        if(n.venstre == null && n.høyre == null)
        {
            return true;
        }
        return false;
    }

    public String printFormel(TreNode n)
    {
        String utskrift = "";
        if(sjekkLøvnode(n) == true)
        {
            utskrift += n.element;
            return utskrift;
        }
        utskrift += "(" + printFormel(n.venstre);
        utskrift += n.element;
        utskrift += printFormel(n.høyre) + ")";
        return utskrift;
    }

    public int finnVerdi(TreNode n)
    {
        if (sjekkLøvnode(n) == true)
        {
            return (Integer) n.element;
        }
        int venstre = finnVerdi(n.venstre);
        int høyre = finnVerdi(n.høyre);
        char tegn = (Character) n.getElement();

        switch(tegn)
        {
            case '+': return venstre + høyre;
            case '-': return venstre - høyre;
            case '*': return venstre * høyre;
            case '/': return venstre / høyre;
        }
        return -1;
    }

    public static void main(String[] args)
    {
        Uttrykkstre test = new Uttrykkstre();
        System.out.println(test.printFormel(test.rot) + " = " + test.finnVerdi(test.rot));

    }
}
