package main.model;

public class Car extends Vehicle{
    private int numberOfDoors=4;

    public Car(String plateNumber, String ownerName, int registrationYear, String status ,int numberOfDoors) {

        super(plateNumber, ownerName, "Car",registrationYear, status);
        this.numberOfDoors = numberOfDoors;
    }

    @Override
    public String getRegistrationLabel() {
        return "Passenger Car — Doors: " + numberOfDoors;
    }

    @Override
    public String toString() {
        return super.toString()+ " | Doors: " + numberOfDoors;
    }
}
