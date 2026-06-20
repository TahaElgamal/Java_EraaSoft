package main.exception;

/**
 * Exception thrown when attempting to register a vehicle with a plate number
 * that already exists in the system.
 *
 * This is a runtime exception that indicates a duplicate plate number violation
 * during vehicle registration. It extends RuntimeException to allow for
 * unchecked exception handling.
 *
 * @author Your Name
 * @version 1.0
 */
public class DuplicatePlateException extends RuntimeException {

    /**
     * Constructs a new DuplicatePlateException with a detailed message.
     * The message includes the duplicate plate number for easy identification.
     *
     * @param plateNumber the plate number that caused the duplicate violation
     */
    public DuplicatePlateException(String plateNumber) {
        super("Plate number already registered: " + plateNumber);
    }
}