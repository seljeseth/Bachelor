package AlgoritmerØvinger.WeightedGraph_8;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Vektagraf
{
    int N;
    int K;
    Node[] noder;
    int maksFlyt;

    public Vektagraf(File fil)
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(fil));
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); //antall noder
            noder = new Node[N];

            for (int i = 0; i < N; i++)
            {
                noder[i] = new Node(i);
            }

            K = Integer.parseInt(st.nextToken()); //antall kanter

            for (int i = 0; i < K; i++)
            {
                StringTokenizer nl = new StringTokenizer(br.readLine());
                int fra = Integer.parseInt(nl.nextToken());
                int til = Integer.parseInt(nl.nextToken());
                int kapasitet = Integer.parseInt(nl.nextToken());
                //bruker linket liste for kantene til en node
                noder[fra].leggTilKant(new Kant(noder[fra], noder[til], kapasitet));
            }

        }
        catch(IOException e)
        {
            System.out.println(e.toString());
            System.out.println("Fant ikke fil " + fil);
        }
    }

    public String toString()
    {
        String kanter = "";
        for (Node n : noder)
        {
            Kant k = n.kantFra;
            while (k != null)
            {
                if (k.kapasitet != 0)
                {
                    kanter += "\n" + n.nr + " --> " + k.tilNode.nr + " : " + k.flyt + "/" + k.kapasitet;
                    kanter += "  -  " + k.tilNode.nr + " -> " + k.motsattKant.tilNode.nr + " : " + k.motsattKant.flyt + "/" + k.motsattKant.kapasitet;
                }
                k = k.neste;
            }
        }

        return kanter;
    }



    public boolean bfs(int kilde, int sluk)
    {
        Node start = noder[kilde];
        Kø kø = new Kø(N - 1);
        kø.leggIKø(start);

        while (!kø.tom())
        {
            Node n = kø.nesteIKø();
            Kant kantFraN = n.kantFra;

            while (kantFraN != null)
            {
                //vil bare fortsette søket om vi har restkapasitet over 0, og vi har en vei til sluket som kan begynne i kilden
                if ((kantFraN.getRestKapasitet() > 0) && (kantFraN.tilNode.forgjenger == null) && (kantFraN.tilNode != noder[kilde]))
                {
                    kantFraN.tilNode.forgjenger = n;
                    kø.leggIKø(kantFraN.tilNode);

                    /*om vi har kommet til sluket øker vi flyten, og nullstiller forgjenger for å kunne utføre
                      bts på nytt uten at resultatet blir påvirket av forgjengere.*/
                    if (kantFraN.tilNode.nr == sluk)
                    {
                        økFlyt(sluk);
                        nullstillForgjenger();
                        return true;
                    }
                }
                kantFraN = kantFraN.neste;
            }
        }
        return false;
    }

    public int finnØkning(int sluk)
    {
        //finner minste kapasitet ved stien som er brukt
        int kapasitet = 100;
        Node node = noder[sluk];
        while (node.forgjenger != null)
        {
            Kant kantTilNode = node.forgjenger.kantFra;
            while (kantTilNode != null)
            {
                //sjekker at kanten til noden er den riktige kanten fra forgjenger noden slik at vi får riktig kapasitet.
                if (kantTilNode.tilNode == node)
                {
                    if (kantTilNode.getRestKapasitet() < kapasitet)
                    {
                        kapasitet = kantTilNode.getRestKapasitet();
                    }
                }
                kantTilNode = kantTilNode.neste;
            }
            node = node.forgjenger;
        }
        return kapasitet;
    }

    public int økFlyt(int sluk)
    {
        int kapasitet = finnØkning(sluk);
        maksFlyt += kapasitet;
        Node node = noder[sluk];
        StringBuilder utskrift = new StringBuilder();
        while (node.forgjenger != null)
        {
            Kant kantTilNode = node.forgjenger.kantFra;
            while (kantTilNode != null)
            {

                if (kantTilNode.tilNode == node)
                {
                    //her vil vi øke flyten til hver kant
                    utskrift.insert(0, "  " + node.nr);
                    kantTilNode.addFlyt(kapasitet);
                }
                kantTilNode = kantTilNode.neste;
            }
            node = node.forgjenger;
        }
        if (kapasitet >= 10)
        {
            System.out.println(" " + kapasitet + "      :     " + node.nr + utskrift);
        }
        else
        {
            System.out.println("  " + kapasitet + "      :     " + node.nr + utskrift);
        }
        return kapasitet;
    }

    public void nullstillForgjenger()
    {
        for (Node node : noder)
        {
            node.forgjenger = null;
        }
    }

    public void edmondKarp(int kilde, int sluk)
    {
        System.out.println( "Maksimum flyt fra " + kilde + " til " + sluk + " med Edmond-Karp\n" + "Økning   :  Flytøkende vei");

        //vil utføre bts til alle mulige paths er brukt opp
        while (bfs(kilde, sluk)) ;

        System.out.println("Maksimal flyt ble " + maksFlyt + "\n");

    }


    public static void main(String[] args)
    {
        File flytgraf1 = new File("/Users/sabineseljeseth/Documents/2. året dataingeniør/1. semester/Algoritme og datastrukturer/Øving 8/flytgraf1");
        File flytgraf2 = new File("/Users/sabineseljeseth/Documents/2. året dataingeniør/1. semester/Algoritme og datastrukturer/Øving 8/flytgraf2");
        File flytgraf3 = new File("/Users/sabineseljeseth/Documents/2. året dataingeniør/1. semester/Algoritme og datastrukturer/Øving 8/flytgraf3");
        Vektagraf FLYTGRAF1 = new Vektagraf(flytgraf1);
        Vektagraf FLYTGRAF2 = new Vektagraf(flytgraf2);
        Vektagraf FLYTGRAF3 = new Vektagraf(flytgraf3);


        FLYTGRAF1.edmondKarp(0, 7);
        FLYTGRAF2.edmondKarp(0, 1);
        FLYTGRAF3.edmondKarp(0, 15);

    }
}

