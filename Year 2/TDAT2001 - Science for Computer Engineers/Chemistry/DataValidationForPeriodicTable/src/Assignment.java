
/*
The aim of the assignment is to validate the published densities’ data of elements in the periodic table by cross
checking them with atomic data. Specifically, you are required to write a program that reads the atomic size of each
element and its atomic weight, calculate the density of a single atom (atomic weight/ atomic volume) and compare the
results with the tabulated density of the element (see second link). Note that each element has a theoretical and an
empirical size: Your calculation should be based on both.
You are further, required to calculate the distances between the atoms of a given element that would result in an
accurate estimation of the tabulated densities. The results that you obtain should be compared with the published
data for number of atoms per unit volume (see the second link).
Atomic radii data: https://en.wikipedia.org/wiki/Atomic_radii_of_the_elements_(data_page)
Density data with number of atoms per volume: https://en.wikipedia.org/wiki/Talk%3AList_of_elements_by_density/Numeric_densities
*/

import java.io.File;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Assignment
{
    private static Atom[] elements = new Atom[35];



    public static void main(String[] args) throws Exception
    {
        // pass the path to the file as a parameter
        Scanner sc = new Scanner(new File("src/AtomData"));
        sc.nextLine();
        for(int i = 0 ; i < elements.length ; i++)
        {
            String[] atomInfo = sc.nextLine().split(" ");
            elements[i] = new Atom(Integer.parseInt(atomInfo[0]) , atomInfo[1].charAt(0) , atomInfo[2], Double.parseDouble(atomInfo[3]) , Double.parseDouble(atomInfo[4]) , Double.parseDouble(atomInfo[5]), Double.parseDouble(atomInfo[6]));
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("|  nr\t| symbol\t| name    \t|  weight\t|  theorethical density\t|  empirical density\t|  wikipediaDensity\t|  calculateDistanceBetweenAtoms\t|");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
        for(Atom a : elements)
        {
            System.out.println(a);
        }
    }

    //TODO: read atomic size (theorethical + empirical) and weight of each elemnt
    //TODO: calculate density of a single atom (weight/volume)
    //TODO: compare calculated density to data from table at:  https://en.wikipedia.org/wiki/Talk%3AList_of_elements_by_density/Numeric_densities
}




