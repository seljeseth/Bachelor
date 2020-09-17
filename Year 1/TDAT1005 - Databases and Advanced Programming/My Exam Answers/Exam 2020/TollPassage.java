package Eksamen_2020;

import java.time.LocalDateTime;

public class TollPassage {
    Vehicle passerende_bil;
    LocalDateTime tid_for_passering;

    public TollPassage(Vehicle bil)
    {
        passerende_bil = bil;
        tid_for_passering = LocalDateTime.now();
    }

    public Vehicle getPasserende_bil() {
        return passerende_bil;
    }

    public void setPasserende_bil(Vehicle passerende_bil) {
        this.passerende_bil = passerende_bil;
    }
}
