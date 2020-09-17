package AlgoritmerØvinger.HashTable_5;

public class RandomTallTabell
{
    int[] tabell;

    public RandomTallTabell()
    {
        tabell = new int[5000000];
        for(int i = 0; i < tabell.length; i++)
        {
            tabell[i] = (int) (Math.random() * (100000000) + 1);
        }

    }

    public String toString()
    {
        String utskrift = "";
        for(int i = 0; i < tabell.length; i++)
        {
            utskrift += "\n"  + tabell[i];

        }
        return utskrift;

    }

    public static void main(String[] args)
    {
        RandomTallTabell test = new RandomTallTabell();
        System.out.println(test);
    }
}
