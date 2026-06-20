package main.util;

import main.exception.InvalidInputException;

import java.time.Year;

/**
 * Utility class for validating user input across the vehicle registration system.
 * Provides static validation methods for all input fields to ensure data integrity.
 * This class cannot be instantiated as it only contains static utility methods.
 *
 * @author Your Name
 * @version 1.0
 */
public class InputValidator {

    /**
     * Private constructor to prevent instantiation.
     * This is a utility class with only static methods.
     */
    private InputValidator() {
    }

    /**
     * Validates a vehicle plate number.
     * Plate number must be 3-10 alphanumeric characters and may contain hyphens.
     *
     * @param plateNumber the plate number to validate
     * @throws InvalidInputException if the plate number is null, empty,
     *         or does not match the required format
     */
    public static void validatePlateNumber(String plateNumber) {
        if (plateNumber == null
                || plateNumber.trim().isEmpty()
                || !plateNumber.matches("[A-Z0-9-]{3,10}")) {
            throw new InvalidInputException(
                    "Plate number must be 3-10 alphanumeric characters"
            );
        }
    }

    /**
     * Validates an owner's name.
     * Owner name must be at least 3 characters long and contain only letters and spaces.
     *
     * @param ownerName the owner name to validate
     * @throws InvalidInputException if the owner name is null, empty,
     *         too short (less than 3 characters), or contains non-letter characters
     */
    public static void validateOwnerName(String ownerName) {
        if (ownerName == null
                || ownerName.trim().isEmpty()
                || ownerName.length() < 3
                || !ownerName.matches("[a-zA-Z ]+")) {
            throw new InvalidInputException(
                    "Owner name must contain only letters"
            );
        }
    }

    /**
     * Validates a vehicle type.
     * Only accepts the three standard vehicle types: Car, Truck, or Motorcycle.
     * Comparison is case-insensitive.
     *
     * @param type the vehicle type to validate
     * @throws InvalidInputException if the type is not one of the accepted vehicle types
     */
    public static void validateVehicleType(String type) {
        if (!(type.equalsIgnoreCase("Car")
                || type.equalsIgnoreCase("Truck")
                || type.equalsIgnoreCase("Motorcycle"))) {
            throw new InvalidInputException(
                    "Invalid type. Use: Car, Truck, Motorcycle"
            );
        }
    }

    /**
     * Validates a registration year.
     * Year must be between 1990 and the current year inclusive.
     *
     * @param year the registration year to validate
     * @throws InvalidInputException if the year is less than 1990 or greater than the current year
     */
    public static void validateRegistrationYear(int year) {
        int currentYear = Year.now().getValue();
        if (year < 1990 || year > currentYear) {
            throw new InvalidInputException(
                    "Year must be between 1990 and "
                            + currentYear
            );
        }
    }

    /**
     * Validates the number of doors on a vehicle.
     * Doors must be between 1 and 10 inclusive.
     *
     * @param doors the number of doors to validate
     * @throws InvalidInputException if doors is less than 1 or greater than 10
     */
    public static void validateDoors(int doors) {
        if (doors <= 0 || doors > 10) {
            throw new InvalidInputException(
                    "Doors must be between 1 and 10"
            );
        }
    }

    /**
     * Validates cargo capacity for a truck.
     * Capacity must be greater than 0 and not exceed 100 tons (realistic maximum).
     *
     * @param capacity the cargo capacity in tons to validate
     * @throws InvalidInputException if capacity is less than or equal to 0,
     *         or greater than 100 tons
     */
    public static void validateCargoCapacity(double capacity) {
        if (capacity <= 0 || capacity > 100) {
            throw new InvalidInputException(
                    "Cargo capacity must be greater than 0 and realistic value"
            );
        }
    }

    /**
     * Validates the engine type of a motorcycle.
     * Engine type must contain only letters and spaces, and cannot be empty.
     *
     * @param engineType the engine type to validate
     * @throws InvalidInputException if engine type is null, empty,
     *         or contains non-letter characters
     */
    public static void validateEngineType(String engineType) {
        if (engineType == null
                || engineType.trim().isEmpty()
                || !engineType.matches("[a-zA-Z ]+")) {
            throw new InvalidInputException(
                    "Engine type must contain letters only"
            );
        }
    }
}