package Eksamen_2020;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public class Car extends Vehicle implements Constants, Payable {

    public Car(String regestreringsnr)
    {
        super(regestreringsnr);
    }

    @Override
    public boolean isPayable() {
        return false;
    }

    @Override
    public double calculate(LocalDateTime datetime) {
        //06.30–09.00 og 15.00–17.00
        String time = datetime.toLocalTime().toString();
        //if(datetime.isBefore("06.30")))
        return 0;
    }
    public static void main(String[] args)
    {

        LocalDateTime t = LocalDateTime.now();
        LocalDateTime w = LocalDateTime.of(2020, 5, 20, 12, 0);
        //OffsetDateTime.parse(t.toString());
        //System.out.println(OffsetDateTime.parse(t.toString()).toLocalTime().toString());
        System.out.println(t.getHour()  + t.getMinute()*1.0/60);
        System.out.println(w.getHour()  + w.getMinute()*1.0/60);

    }
}
