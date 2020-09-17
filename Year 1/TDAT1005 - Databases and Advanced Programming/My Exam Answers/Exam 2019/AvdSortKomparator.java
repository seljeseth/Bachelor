package Eksamen_2019;

import java.util.Comparator;

public class AvdSortKomparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        int p1 = ((Avdeling) o1).getantAnsatte();
        int p2 = ((Avdeling) o2).getantAnsatte();
        return p1-p2;

    }
}
