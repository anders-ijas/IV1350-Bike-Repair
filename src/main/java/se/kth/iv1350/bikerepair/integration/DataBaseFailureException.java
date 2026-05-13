package se.kth.iv1350.bikerepair.integration;

public class DataBaseFailureException extends RuntimeException {

    public DataBaseFailureException(String message) {
        super(message);
    }
}
