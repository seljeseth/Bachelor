import java.io.File;
import java.util.Scanner;

public class Main
{
    private static Atom[] elements = new Atom[1];

    public static void main(String[] args) throws Exception
    {
        // pass the path to the file as a parameter
        Scanner sc = new Scanner(new File("src/AtomData.txt"));
        sc.nextLine();
        for(int i = 0 ; i < elements.length ; i++)
        {
            String[] atomInfo = sc.nextLine().split(" ");
           // elements[i] = new Atom(Integer.parseInt(atomInfo[0]) , atomInfo[1].charAt(0) , atomInfo[2], Double.parseDouble(atomInfo[3]) , Double.parseDouble(atomInfo[4]) , Double.parseDouble(atomInfo[5]));
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("|  nr\t| symbol\t|  name    \t|  weight\t|  theorethical density\t|  empirical density\t|  wikipediaDensity\t|  calculateDistanceBetweenAtoms\t|");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
        for(Atom a : elements)
        {
            System.out.println(a);
        }
    }
}
