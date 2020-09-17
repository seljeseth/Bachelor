package Eksamen_2020;

public abstract class Vehicle implements Constants, Payable
{
    String registrationPlate;
    public Vehicle(String registrationPlate)
    {
        this.registrationPlate = registrationPlate;
    }

    public String getRegistrationPlate() {
        return registrationPlate;
    }

    public void setRegistrationPlate(String registrationPlate) {
        this.registrationPlate = registrationPlate;
    }
}
