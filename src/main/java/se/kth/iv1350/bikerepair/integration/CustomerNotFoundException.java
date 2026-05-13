package se.kth.iv1350.bikerepair.integration;

/**
 * Throws an exception when a customer is not found in the customer registry.
 */
public class CustomerNotFoundException extends Exception {
    private String phoneNumber;

    /**
     * Throws exception that customer with specified phone number is not found
     * @param phoneNumber The phone number of customer not found
     */
    public CustomerNotFoundException(String phoneNumber) {
        super("Customer with phone number: " + phoneNumber + " was not found");
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets phone number of customer not found
     * @return String
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
}
