package main.model;

/**
 * Represents a Motorcycle vehicle in the system.
 * This class extends the Vehicle class and adds specific attributes for motorcycles.
 *
 * @author Your Name
 * @version 1.0
 */
public class Motorcycle extends Vehicle {

    /* The type of engine used in the motorcycle (e.g., "V-Twin", "Inline-4") */
    private String engineType;

    /**
     * Parameterized constructor for creating a Motorcycle object.
     * Initializes all vehicle attributes and the motorcycle-specific engine type.
     *
     * @param plateNumber      the license plate number of the motorcycle
     * @param ownerName        the name of the motorcycle owner
     * @param registrationYear the year the motorcycle was registered
     * @param status           the current status of the motorcycle (e.g., "Available", "Rented")
     * @param engineType       the type of engine the motorcycle has
     */
    public Motorcycle(String plateNumber, String ownerName, int registrationYear, String status, String engineType) {
        super(plateNumber, ownerName, "Motorcycle", registrationYear, status);
        this.engineType = engineType;
    }

    /**
     * Generates a registration label specific to motorcycles.
     * Overrides the parent class method to provide motorcycle-specific information.
     *
     * @return a formatted string containing the vehicle type and engine type
     */
    @Override
    public String getRegistrationLabel() {
        return "Motorcycle — Engine: " + engineType;
    }

    /**
     * Returns a string representation of the Motorcycle object.
     * Includes all vehicle information from the parent class plus the engine type.
     *
     * @return a formatted string containing all motorcycle details
     */
    @Override
    public String toString() {
        return super.toString() + " | Engine Type: " + engineType;
    }
}