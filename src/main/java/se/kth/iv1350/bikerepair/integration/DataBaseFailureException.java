package se.kth.iv1350.bikerepair.integration;

/**
 * Throws an exception when there is a failure to connect to a database.
 */
public class DataBaseFailureException extends Exception {

    /**
     * Exception for failure to connect to database. Hardcoded as of now.
     * @param message The message to be logged
     */
    public DataBaseFailureException(String message) {
        super(message);
    }
}
