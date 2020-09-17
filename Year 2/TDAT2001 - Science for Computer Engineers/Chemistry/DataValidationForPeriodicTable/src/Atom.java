/*write a program that reads the atomic size of each element and its atomic weight,
 * calculate the density of a single atom (atomic weight/ atomic volume) and compare
 * the results with the tabulated density of the element (see second link).
 * Note that each element has a theoretical and an empirical size: Your calculation should be based on both.*/
/*
public class Atom
{
    private int atomic_nr;
    private char symbol;
    private String name;
    private double atomic_weight; //mass
    private double theoretical_atomic_size;
    private double empirical_atomic_size;
    private double avogadros_number =  (6.022140857*Math.pow(10,23));

    public Atom(int atomic_nr, char symbol, String name, double atomic_weight, double theoretical_atomic_size, double empirical_atomic_size)
    {
        this.atomic_nr                  = atomic_nr;
        this.symbol                     = symbol;
        this.name                       = name;
        this.atomic_weight              = atomic_weight; //mass
        this.theoretical_atomic_size    = theoretical_atomic_size;
        this.empirical_atomic_size      = empirical_atomic_size;

    }

    double calculateDensity(double atomic_weight, double atomic_size)
    {
        double density;
        return 0.0;
    }

    public static void main(String[] args) {
        for(int i = 0; i <= 103; i++)
        {
            System.out.println(i+1);
        }
    }


}
//TODO
/*
 * Calculate density
 * D = Mass/Volume
 */


import java.text.DecimalFormat;

class Atom
{
    private char symbol;
    private int nr;
    private String name;
    private double weight;
    private double theorethicalDensity;
    private double empiricalDensity;
    private double wikipediaDensity;
    private double avogadros_number =  (6.022140857*Math.pow(10,23));




    public Atom(int nr , char symbol, String name , double weight , double theorethicalRadii , double empiricalRadii , double wikipediaDensity)
    {
        this.nr = nr;
        this.symbol = symbol;
        this.name = name;
        this.weight = weight;
        this.theorethicalDensity = round(weight / calculateSize(theorethicalRadii)); //g/cm^3
        this.empiricalDensity = weight / calculateSize(empiricalRadii); //g/cm^3
        this.wikipediaDensity = wikipediaDensity; //g/cm^3
    }



    private double calculateSize(double radii)
    {
        return (4 * Math.PI * Math.pow(radii , 3)) / 3;
    }



    private double round(double num)
    {
        DecimalFormat df = new DecimalFormat("###.###");
//		return Double.parseDouble(df.format(num));
        return num;
    }



    public String toString(double raddi)
    {
        return "| " + nr + " \t| " + name + "\t| " + weight + "\t| " + theorethicalDensity + "\t| " + empiricalDensity + "\t| " + wikipediaDensity + "      \t| " + calculateDistanceBetweenAtoms(raddi);
    }



    private double calculateDistanceBetweenAtoms(double radii)
    {
        double nr_of_atoms = (avogadros_number*theorethicalDensity*calculateSize(radii))/weight;
        //TODO: calculate distance bewtween atoms of a given element that would result in an accurate estimation of the tabulated densities
        //dersom vi har denne tettheten, hvor langt unna vil atomene være hverandre??


        return nr_of_atoms;
        //TODO: compare with number of atoms per unit volume at: https://en.wikipedia.org/wiki/Talk%3AList_of_elements_by_density/Numeric_densities

    }
}

