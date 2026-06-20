package main.model;

/**
 * Represents a Car vehicle in the system.
 * This class extends the Vehicle class and adds specific attributes for cars.
 *
 * @author Your Name
 * @version 1.0
 */
public class Car extends Vehicle {

    /* The number of doors the car has (default is 4) */
    private int numberOfDoors = 4;

    /**
     * Parameterized constructor for creating a Car object.
     * Initializes all vehicle attributes and the car-specific door count.
     *
     * @param plateNumber      the license plate number of the car
     * @param ownerName        the name of the car owner
     * @param registrationYear the year the car was registered
     * @param status           the current status of the car (e.g., "Available", "Rented")
     * @param numberOfDoors    the number of doors on the car
     */
    public Car(String plateNumber, String ownerName, int registrationYear, String status, int numberOfDoors) {
        super(plateNumber, ownerName, "Car", registrationYear, status);
        this.numberOfDoors = numberOfDoors;
    }

    /**
     * Generates a registration label specific to cars.
     * Overrides the parent class method to provide car-specific information.
     *
     * @return a formatted string containing the vehicle type and door count
     */
    @Override
    public String getRegistrationLabel() {
        return "Passenger Car — Doors: " + numberOfDoors;
    }

    /**
     * Returns a string representation of the Car object.
     * Includes all vehicle information from the parent class plus the door count.
     *
     * @return a formatted string containing all car details
     */
    @Override
    public String toString() {
        return super.toString() + " | Doors: " + numberOfDoors;
    }
}