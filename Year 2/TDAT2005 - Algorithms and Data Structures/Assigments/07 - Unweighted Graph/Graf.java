package AlgoritmerØvinger.UnweightedGraph_7;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Graf
{
    int N;
    int K;
    Node[] node;
    Node[] toputskrif;

    public Graf(File fil)
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(fil));
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); //antall noder
            node = new Node[N];
            toputskrif = new Node[N];

            for(int i = 0; i < N; i++)
            {
                node[i] = new Node(i);
            }

            K = Integer.parseInt(st.nextToken()); //antall kanter
            for(int i = 0; i < K; i++)
            {
                st = new StringTokenizer(br.readLine());
                int fra = Integer.parseInt(st.nextToken());
                int til = Integer.parseInt(st.nextToken());

                if (node[fra].neste == null)
                {

                    node[fra].neste = new Node(til);

                }else{

                    Node temp = node[fra];

                    while(temp.neste != null)
                    {
                        temp = temp.neste;
                    }

                    temp.neste = new Node(til);
                }
            }

        }catch(IOException e)
        {
            System.out.println (e.toString());
            System.out.println("Fant ikke fil " + fil);
        }
    }

    public void print()
    {
        for(Node n : node)
        {
            System.out.println(printNode(n));
        }
    }

    public String printNode(Node n)
    {
        if(n.neste == null)
        {
            return "" + n.nr;
        }
        return (n.nr + " --> " + printNode(n.neste));
    }

    public void bfs(int nodeNr)
    {
        Node start = node[nodeNr];
        //distansen til noden vi starter fra settes til 0
        //mens alle andre vil ha distanse -1
        start.distanse = 0;
        Kø kø = new Kø(N - 1);
        kø.leggIKø(start);

        while (!kø.tom())
        {
            Node n = kø.nesteIKø();
            Node nabo = n.neste;

            while(nabo != null)
            {
                //her brukes nabo for å ha oversikt over hvordan noder n er forbundet med
                //node[nabo.nr] vil være den faktisk noden i tabellen
                if (node[nabo.nr].distanse == -1)
                {
                    node[nabo.nr].distanse = n.distanse + 1;
                    node[nabo.nr].forgjenger = n;
                    kø.leggIKø(node[nabo.nr]);
                }

                nabo = nabo.neste;
            }
        }
    }

    public void printResultat()
    {
        System.out.println("Deloppgave 1:"+"\n" +"Node Forgj Dist");

        for(Node n : node)
        {
            if(n.forgjenger == null)
            {
                System.out.println("  " + n.nr + "         " + n.distanse);

            }else{

                System.out.println("  " + n.nr + "    " + n.forgjenger.nr + "    " + n.distanse );

            }
        }
    }

    public void topsøk()
    {
        System.out.println("\nDeloppgave 2:");
        for(Node n: node)
        {
            besøk(n);
        }
        for(Node n : toputskrif)
        {
            System.out.print(n.nr + " ");
        }
        System.out.println("\n");
    }


    public void besøk(Node n)
    {
        if(n.merke) return;
        n.merke = true;
        Node m = n.neste;
        while(m != null)
        {
            besøk(node[m.nr]);
            m = m.neste;
        }
        for(int i = N-1; i >= 0; i--)
        {
            if(toputskrif[i] == null)
            {
                toputskrif[i] = new Node(n.nr);
                break;
            }
        }
    }

    public void bfs(int fra, int til)
    {
        System.out.print("Ekstraoppgave:\n");
        Node start = node[fra];
        start.distanse = 0;
        Kø kø = new Kø(N - 1);
        kø.leggIKø(start);

        LOOP:
        while (!kø.tom())
        {
            Node n = kø.nesteIKø();
            Node nabo = n.neste;
            while(nabo != null)
            {
                if (node[nabo.nr].distanse == -1)
                {
                    node[nabo.nr].distanse = n.distanse + 1;
                    node[nabo.nr].forgjenger = n;
                    kø.leggIKø(node[nabo.nr]);
                }

                if(nabo.nr == til)
                {
                    System.out.println("\nLengde mellom node: " + fra +" til node: " + til + " er " + node[nabo.nr].distanse);
                    break LOOP;
                }

                nabo = nabo.neste;
            }
        }
    }

    public static void main(String[] args)
    {
        File L7g1 = new File("/Users/sabineseljeseth/Documents/2. året dataingeniør/1. semester/Algoritme og datastrukturer/Øving 7/L7g1");
        File L7g2 = new File("/Users/sabineseljeseth/Documents/2. året dataingeniør/1. semester/Algoritme og datastrukturer/Øving 7/L7g2");
        File L7g3 = new File("/Users/sabineseljeseth/Documents/2. året dataingeniør/1. semester/Algoritme og datastrukturer/Øving 7/L7g3");
        File L7g5 = new File("/Users/sabineseljeseth/Documents/2. året dataingeniør/1. semester/Algoritme og datastrukturer/Øving 7/L7g5");
        File L7Sandinavia = new File("/Users/sabineseljeseth/Documents/2. året dataingeniør/1. semester/Algoritme og datastrukturer/Øving 7/L7Skandinavia");


        Graf grafL7g1 = new Graf(L7g1);
        Graf grafL7g5 = new Graf(L7g5);
        Graf grafL7Skandinavia = new Graf(L7Sandinavia);
        Graf grafL7g2 = new Graf(L7g2);


        grafL7g1.bfs(5);
        grafL7g1.printResultat();


        grafL7g5.topsøk();

        grafL7Skandinavia.bfs(65205, 3378527);
        grafL7Skandinavia.bfs(37774,18058);
        System.out.println("\nKalvskinnet = Node 37774\nMoholt = Node 18058");

    }



}
