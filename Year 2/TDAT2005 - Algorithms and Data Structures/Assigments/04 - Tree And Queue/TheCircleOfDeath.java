package AlgoritmerØvinger.TreeAndQueue_4;

//OPPGAVE 1
public class TheCircleOfDeath
{
    Node hode;


    public TheCircleOfDeath(int ant)
    {
        int start = 1;
        hode = new Node(start);
        start++;
        Node temp = hode;
        while(start <= ant)
        {
            Node me = new Node(start);
            temp.neste = me;
            temp = me;
            me.neste = hode;
            start++;
        }
    }

    public void execute(int intervall)
    {

        int teller = 0;
        Node person = hode;
        Node forrige = null;
        while(person != person.neste)
        {
            teller++;
            if(teller == intervall)
            {
                System.out.println("Person nr " + person.finnPerson() + "." + " drepes");
                forrige.neste = person.neste;
                teller = 0;
            }
            forrige = person;
            person = person.neste;

        }
        System.out.println("Josephus bør stå som person nr." + person.finnPerson() + " for å overleve");



    }

    public static void main(String[] args)
    {
        TheCircleOfDeath test = new TheCircleOfDeath(101);
        test.execute(7);


    }

}
