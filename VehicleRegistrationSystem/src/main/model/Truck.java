package main.model;

/**
 * Represents a Truck vehicle in the system.
 * This class extends the Vehicle class and adds specific attributes for trucks.
 *
 * @author Your Name
 * @version 1.0
 */
public class Truck extends Vehicle {

    /* The cargo capacity of the truck measured in tons */
    private double cargoCapacityTons;

    /**
     * Parameterized constructor for creating a Truck object.
     * Initializes all vehicle attributes and the truck-specific cargo capacity.
     *
     * @param plateNumber        the license plate number of the truck
     * @param ownerName          the name of the truck owner
     * @param registrationYear   the year the truck was registered
     * @param status             the current status of the truck (e.g., "Available", "Rented")
     * @param cargoCapacityTons  the cargo capacity of the truck in tons
     */
    public Truck(String plateNumber, String ownerName, int registrationYear, String status, double cargoCapacityTons) {
        super(plateNumber, ownerName, "Truck", registrationYear, status);
        this.cargoCapacityTons = cargoCapacityTons;
    }

    /**
     * Generates a registration label specific to trucks.
     * Overrides the parent class method to provide truck-specific information.
     *
     * @return a formatted string containing the vehicle type and cargo capacity
     */
    @Override
    public String getRegistrationLabel() {
        return "Truck — Cargo Capacity: " + cargoCapacityTons + " tons";
    }

    /**
     * Returns a string representation of the Truck object.
     * Includes all vehicle information from the parent class plus the cargo capacity.
     *
     * @return a formatted string containing all truck details
     */
    @Override
    public String toString() {
        return super.toString() + " | Cargo Capacity: "
                + cargoCapacityTons + " tons";
    }
}