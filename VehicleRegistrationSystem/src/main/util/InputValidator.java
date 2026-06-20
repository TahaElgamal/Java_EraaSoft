package main.util;

import main.exception.InvalidInputException;

import java.time.Year;

public class InputValidator {
    private InputValidator() {
    }

    // Plate Number Validation
    public static void validatePlateNumber(String plateNumber) {

        if (plateNumber == null
                || plateNumber.trim().isEmpty()
                || !plateNumber.matches("[A-Z0-9-]{3,10}")) {

            throw new InvalidInputException(
                    "Plate number must be 3-10 alphanumeric characters"
            );
        }
    }

    // Owner Name Validation
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

    // Vehicle Type Validation
    public static void validateVehicleType(String type) {

        if (!(type.equalsIgnoreCase("Car")
                || type.equalsIgnoreCase("Truck")
                || type.equalsIgnoreCase("Motorcycle"))) {

            throw new InvalidInputException(
                    "Invalid type. Use: Car, Truck, Motorcycle"
            );
        }
    }

    // Registration Year Validation
    public static void validateRegistrationYear(int year) {

        int currentYear = Year.now().getValue();

        if (year < 1990 || year > currentYear) {

            throw new InvalidInputException(
                    "Year must be between 1990 and "
                            + currentYear
            );
        }
    }

    public static void validateDoors(int doors) {

        if (doors <= 0 || doors > 10) {
            throw new InvalidInputException(
                    "Doors must be between 1 and 10"
            );
        }
    }

    public static void validateCargoCapacity(double capacity) {

        if (capacity <= 0 || capacity > 100) {
            throw new InvalidInputException(
                    "Cargo capacity must be greater than 0 and realistic value"
            );
        }
    }

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
