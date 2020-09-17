package AlgoritmerØvinger.UnweightedGraph_7;

public class Kø {
    private Node[] tabell;
    private int start = 0;
    private int slutt = 0;
    private int antall = 0;


    public Kø(int str) {
        tabell = new Node[str];
    }

    public boolean tom() {
        return antall == 0;
    }

    public boolean full() {
        return antall == tabell.length;
    }

    public void leggIKø(Node e) {
        if (full()) return;
        tabell[slutt] = e;
        slutt = (slutt + 1) % tabell.length;
        ++antall;
    }

    public Node nesteIKø() {
        if (!tom()) {
            Node e = tabell[start];
            start = (start + 1) % tabell.length;
            --antall;
            return e;
        } else return null;
    }

}
