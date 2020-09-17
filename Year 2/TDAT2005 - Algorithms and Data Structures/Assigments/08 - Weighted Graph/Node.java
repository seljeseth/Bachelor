package AlgoritmerØvinger.WeightedGraph_8;

    public class Node
    {
        int nr;
        Node forgjenger;
        Kant kantFra;

        public Node(int n)
        {
            nr = n;
        }

        public String toString()
        {
            return "" + nr;
        }

        //oppretter en lenket liste for en node
        public void leggTilKant(Kant ny)
        {
            if (kantFra == null)
            {
                kantFra = ny;

            }else{


                kantFra.neste_kant(ny);
            }
        }





    }
