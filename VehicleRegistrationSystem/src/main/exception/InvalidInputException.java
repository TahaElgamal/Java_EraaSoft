package main.exception;

/**
 * Exception thrown when user input fails validation checks.
 *
 * This exception is used throughout the application to indicate that
 * provided input does not meet the required format, range, or validation
 * criteria. It extends IllegalArgumentException to leverage Java's
 * built-in exception handling for invalid arguments.
 *
 * Common use cases include:
 * - Invalid plate number format
 * - Invalid owner name (contains numbers or special characters)
 * - Invalid vehicle type
 * - Invalid registration year (out of range)
 * - Invalid number of doors, cargo capacity, or engine type
 *
 * @author Your Name
 * @version 1.0
 */
public class InvalidInputException extends IllegalArgumentException {

    /**
     * Constructs a new InvalidInputException with a detailed error message.
     * The message should clearly describe what validation rule was violated.
     *
     * @param message the detailed error message describing the validation failure
     */
    public InvalidInputException(String message) {
        super(message);
    }
}