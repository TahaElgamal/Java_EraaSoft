package main.exception;

/**
 * Exception thrown when attempting to find a vehicle that does not exist in the system.
 *
 * This exception is used throughout the application to indicate that a vehicle
 * with the specified plate number could not be found in the registration system.
 * It extends RuntimeException to allow for unchecked exception handling.
 *
 * Common use cases include:
 * - Searching for a vehicle by plate number that doesn't exist
 * - Attempting to update or delete a vehicle that has already been removed
 * - Looking up vehicle details for a non-existent plate
 * - Any operation that requires a valid vehicle reference by plate number
 *
 * @author Your Name
 * @version 1.0
 */
public class VehicleNotFoundException extends RuntimeException {

    /**
     * Constructs a new VehicleNotFoundException with a detailed message.
     * The message includes the plate number that was not found for easy identification.
     *
     * @param plateNumber the plate number that was searched for but not found
     */
    public VehicleNotFoundException(String plateNumber) {
        super("No vehicle found with plate: " + plateNumber);
    }
}