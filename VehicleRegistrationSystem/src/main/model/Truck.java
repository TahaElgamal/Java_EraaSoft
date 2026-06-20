package main.model;

public class Truck extends Vehicle{
    private double cargoCapacityTons;

    public Truck(String plateNumber, String ownerName,  int registrationYear, String status, double cargoCapacityTons) {
        super(plateNumber, ownerName, "Truck", registrationYear, status);
        this.cargoCapacityTons = cargoCapacityTons;
    }

    @Override
    public String getRegistrationLabel() {
        return null;
    }

    @Override
    public String toString() {
        return super.toString()+ " | Cargo Capacity: "
                + cargoCapacityTons + " tons";
    }
}
