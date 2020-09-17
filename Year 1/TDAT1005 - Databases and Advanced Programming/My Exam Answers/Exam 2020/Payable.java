package Eksamen_2020;

import java.time.LocalDateTime;

public interface Payable {

    public boolean isPayable();

    public double calculate(LocalDateTime datetime);

}