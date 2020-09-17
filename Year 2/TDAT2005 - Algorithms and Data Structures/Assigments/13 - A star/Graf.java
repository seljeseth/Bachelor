package AlgoritmerØvinger.A_star_13;

import java.io.*;
import java.util.Comparator;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Graf
{
    int N; //antall noder
    int K; //antall kanter
    int antallNoderDijkstras;//hvor mange noder algoritmen trenger for å finne korteste vei
    int antallNoderAstjerne;
    int infinity = 1000000000;
    Node[] noder;




    public Graf(File nodeFil, File kantFil)
    {
        //henter noder fra nodefil
        try
        {
            System.out.println("\nLeser fra fil:" + nodeFil);
            BufferedReader nf = new BufferedReader(new FileReader(nodeFil));
            StringTokenizer stn = new StringTokenizer(nf.readLine());

            N = Integer.parseInt(stn.nextToken()); //antall noder
            noder = new Node[N];

            for (int i = 0; i < N; i++)
            {
                StringTokenizer lesNode = new StringTokenizer(nf.readLine());
                noder[i] = new Node(Integer.parseInt(lesNode.nextToken()), Double.parseDouble(lesNode.nextToken()), Double.parseDouble(lesNode.nextToken()));
            }

        }
        catch(IOException e)
        {
            System.out.println(e.toString());
            System.out.println("Fant ikke fil " + nodeFil);
        }

        //når nodene er lagt inn må vi hente ut kantene fra kantfilen
        try
        {
            System.out.println("Leser fra fil:" + kantFil + "\n");
            BufferedReader kf = new BufferedReader(new FileReader(kantFil));
            StringTokenizer stk = new StringTokenizer(kf.readLine());
            K = Integer.parseInt(stk.nextToken()); //antall kanter

            for (int i = 0; i < K; i++)
            {
                StringTokenizer lesKant = new StringTokenizer(kf.readLine());
                int fraNode = Integer.parseInt(lesKant.nextToken());
                int tilNode = Integer.parseInt(lesKant.nextToken());
                int kjøretid = Integer.parseInt(lesKant.nextToken());
                int lengde = Integer.parseInt(lesKant.nextToken());
                int fartsgrense = Integer.parseInt(lesKant.nextToken());

                //bruker linket liste for kantene til en node
                noder[fraNode].leggTilKant(new Kant(noder[fraNode], noder[tilNode], kjøretid, lengde, fartsgrense));
            }

        }
        catch(IOException e)
        {
            System.out.println(e.toString());
            System.out.println("Fant ikke fil " + kantFil);
        }


    }



    public void Dijkstras(int startnode, int sluttnode)
    {
        Date start_tid = new Date();
        double tid;
        Node start = noder[startnode];
        Node slutt = noder[sluttnode];

        //startnoden har en distanse på 0

        start.total_kjøretid = 0;
        //prioriteringskø som er prioritert med de med kortest distanse først
        PriorityQueue<Node> kø = new PriorityQueue<>(new NodeComparatorDijkstras());

        kø.add(start);

        LOOP:
        while (!kø.isEmpty())
        {
            //henter den første i køen
            Node n = kø.poll();
            antallNoderDijkstras++;

            if (n == slutt)
            {
                Date slutt_tid = new Date();
                tid = ((slutt_tid.getTime() - start_tid.getTime()) / 1000.0);
                System.out.println("Dijkstras bruker: " + tid + " sek \nBesøkte noder: " + antallNoderDijkstras + "\nAntall noder på veien " + finnAntNoder(n));
                beregnTid(n.total_kjøretid);


                nullstill();
                break LOOP;
            }

            Kant kantFraNode = n.kantFra;

            while (kantFraNode != null)
            {
                Node naboNode = kantFraNode.tilNode;

                if ((n.total_kjøretid + kantFraNode.kjøretid) < naboNode.total_kjøretid)
                {
                    kø.remove(naboNode);
                    naboNode.total_kjøretid = n.total_kjøretid + kantFraNode.kjøretid;
                    naboNode.total_distanse = n.total_distanse + kantFraNode.lengde;
                    //må legge til hvilken node som var tidligere
                    naboNode.forgjenger = n;
                    kø.add(naboNode);

                }
                //etter alle nabokantene er sjekket går noden ut av køen for den er ferdig
                kantFraNode = kantFraNode.neste;
            }
        }
        //finner veien med å se på tidligere node

    }



    public void nullstill()
    {
        for (Node node : noder)
        {
            node.forgjenger = null;
            node.total_kjøretid = infinity;
            antallNoderDijkstras = 0;
            antallNoderAstjerne = 0;
            node.total_distanse = 0;

        }
    }



    public void beregnTid(int tid)
    {

        double sekunder = ((1.0 * tid) / 100); //sekunder
        double minutter = (sekunder / 60); //minutter
        double timer = (minutter / 60); //timer

        minutter = (timer % 1) * 60;
        sekunder = (minutter % 1) * 60;

        int t = (int) timer;
        int m = (int) minutter;
        int s = (int) Math.round(sekunder);

        System.out.println("Beregnet tid for reiseruten: " + t + "t " + m + "m " + s + "s\n");

    }



    public int finnAntNoder(Node n)
    {
        int antNoder = 0;
        do
        {
            antNoder++;
            //System.out.println(n.breddegrad_grader + " ,  " + n.lengdegrad_grader);
        } while ((n = n.forgjenger) != null);

        return antNoder;
    }



    public int Haversine(Node start, Node slutt)
    {
        //Jordens radius er 6371 km, høyeste fartsgrense 130km/t, 3600 sek/time
        //For å få hundredels sekunder: 2*6371/130*3600*100 = 35285538.46153846153846153846
        double sin_bredde = Math.sin((start.breddegrad_radianer - slutt.breddegrad_radianer) / 2.0);
        double sin_lengde = Math.sin((start.lengdegrad_radianer - slutt.lengdegrad_radianer) / 2.0);

        return (int) (35285538.46153846153846153846 * Math.asin(Math.sqrt((sin_bredde * sin_bredde) + (start.cos_bredde * slutt.cos_bredde * sin_lengde * sin_lengde))));
    }



    public void Astjerne(int startnode, int sluttnode)
    {
        Date start_tid = new Date();
        double total_tid_for_algoritmen;

        Node start = noder[startnode];
        Node slutt = noder[sluttnode];

        //startnoden har en distanse på 0 npr den legges inn i køa
        start.total_kjøretid = 0;

        //prioriteringskø som er prioritert med de med kortest distanse først
        PriorityQueue<Node> kø = new PriorityQueue<>(new NodeComparatorAstjerne());
        kø.add(start);

        LOOP:
        while (!kø.isEmpty())
        {
            //henter den første i køen
            Node n = kø.poll();
            antallNoderAstjerne++;

            if (n == slutt)
            {
                Date slutt_tid = new Date();
                total_tid_for_algoritmen = ((slutt_tid.getTime() - start_tid.getTime()) / 1000.0);
                System.out.println("A* bruker: " + total_tid_for_algoritmen + "sek \nBesøkte noder: " + antallNoderAstjerne + " \nAntall noder på veien " + finnAntNoder(n));
                beregnTid(n.total_kjøretid);
                nullstill();
                break LOOP;
            }

            Kant kantFraNode = n.kantFra;

            while (kantFraNode != null)
            {
                Node naboNode = kantFraNode.tilNode;
                naboNode.avstand_til_destinasjon = Haversine(naboNode, slutt);

                if ((n.total_kjøretid + kantFraNode.kjøretid + naboNode.avstand_til_destinasjon) < (naboNode.avstand_til_destinasjon + naboNode.total_kjøretid))
                {
                    kø.remove(naboNode);
                    naboNode.total_kjøretid = n.total_kjøretid + kantFraNode.kjøretid;

                    //må legge til hvilken node som var tidligere
                    naboNode.forgjenger = n;
                    kø.add(naboNode);

                }
                //etter alle nabokantene er sjekket går noden ut av køen for den er ferdig
                kantFraNode = kantFraNode.neste;
            }
        }

    }

}





//må ha en comparator slik at det blir sortert i køen etter kjøretid
class NodeComparatorDijkstras implements Comparator<Node>
{
    public int compare(Node n1, Node n2)
    {
        return (n1.total_kjøretid - n2.total_kjøretid);

    }
}





class NodeComparatorAstjerne implements Comparator<Node>
{
    public int compare(Node n1, Node n2)
    {
        return ((n1.total_kjøretid + n1.avstand_til_destinasjon) - (n2.total_kjøretid + n2.avstand_til_destinasjon));

    }
}

