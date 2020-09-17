package AlgoritmerØvinger.HashTable_5;

public class Hashtabell
{
    Node[] navnliste;
    int antKollisjoner = 0;

    public Hashtabell(int str)
    {
        navnliste = new Node[str];
    }

    public int BeregnIndex(int key)
    {
        return key % navnliste.length;
    }

    public int Finnkey(String navn)
    {
        int ascii = 0;
        String navnUtenSpace = navn.replaceAll("\\s","");
        char[] arrayOfsplit = navnUtenSpace.toCharArray();

        for(int i = 0; i < arrayOfsplit.length; i++)

        {
            ascii += (int) arrayOfsplit[i] * (i+3) ;
        }

        return ascii;

    }

    public void insertItabell(String navn)
    {
        int key = Finnkey(navn);
        int index = BeregnIndex(key);

        if(navnliste[index] == null)
        {
            navnliste[index] = new Node(navn);


        }else {
            kollisjon(navnliste[index], navn);
        }

    }

    public void kollisjon(Node n, String navn)
    {
        System.out.println("\n" + navn + " kolliderer med " + n.navn);
        antKollisjoner++;

        if (n.neste == null)
        {
            n.neste = new Node(navn);
            return;
        }
        kollisjon(n.neste, navn);

    }

   public boolean sjekk_om_person_er_i_tabellen(String person)
    {
        String navn = person.replaceAll("\\s","");
        int key = Finnkey(navn);
        int index = BeregnIndex(key);
        if(navnliste[index] == null)
        {
            return false;

        }
        if(navnliste[index].navn.equals(navn))
        {
            return true;
        }
        if(navnliste[index].neste == null)
        {
            return false;
        }
        return kollisjonIsøk(navnliste[index], navn);
    }

    public boolean kollisjonIsøk(Node n, String navn)
    {
        if(n.navn.equals(navn))
        {
            return true;
        }
        System.out.println( "\n" + navn + " kolliderer under søk med " + n.navn);
        if(n.neste == null)
        {
            return false;
        }
        return kollisjonIsøk(n.neste, navn);
    }

    public String printNode(Node n)
    {
        String utskrift = "";
        if(n == null)
        {
            return "[]";
        }
        utskrift += n.navn;
        if(n.neste != null)
        {
            utskrift +=  " --> " + printNode(n.neste);

        }
        return utskrift;
    }


    public void print()
    {
        System.out.println("\n");
        for(int i = 0; i < navnliste.length; i++)
        {
            System.out.println(i + ". " + printNode(navnliste[i]));

        }

    }

    public static void main(String[] args)
    {
        Hashtabell klasseliste = new Hashtabell(128);
        Navn navneliste = new Navn();
        for(int i = 0; i < navneliste.navn.length; i++ )
        {
            klasseliste.insertItabell(navneliste.navn[i]);


        }

        klasseliste.print();
        System.out.println("\nSabine Seljeseth er i navnlista: " + klasseliste.sjekk_om_person_er_i_tabellen("Seljeseth, Sabine"));
        System.out.println("Antall kollisjoner ved innsett: " + klasseliste.antKollisjoner);
        System.out.println("Antall kollisjoner pr person: " + Math.round(1000.0 * klasseliste.antKollisjoner) / (navneliste.getLength()) / 1000.0);
        System.out.println("Last faktoren: " + Math.round(1000.0 * navneliste.getLength()) / (klasseliste.navnliste.length)/1000.0);




    }



}
