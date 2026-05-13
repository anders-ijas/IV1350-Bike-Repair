package se.kth.iv1350.bikerepair.integration;

/**
 * Throws an exception when there is a failure to connect to a database.
 */
public class DataBaseFailureException extends Exception {

    public DataBaseFailureException(String message) {
        super(message);
    }
}
