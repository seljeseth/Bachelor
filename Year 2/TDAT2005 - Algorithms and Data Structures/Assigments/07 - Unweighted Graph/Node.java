package AlgoritmerØvinger.UnweightedGraph_7;

public class Node
{
    Node neste;
    int nr;
    int distanse;
    Node forgjenger;
    boolean merke = false;


    public Node(int n)
    {
        nr = n;
        distanse = -1;
    }


}