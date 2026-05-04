package se.kth.iv1350.bikerepair.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A DTO to transfer information between MVC layers.
 */
public final class CustomerDTO {
    private final String name;
    private final String phoneNumber;
    private final String email;
    private List<BikeDTO> bikes = new ArrayList<>();

    /**
     * Creates a new instance.
     * @param name Name of customer
     * @param phoneNumber Phone number of customer
     * @param email Email of customer
     * @param bikes DTOs of customers Bike/s
     */
    public CustomerDTO(String name, String phoneNumber, String email, List<BikeDTO> bikes) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.bikes = bikes;
    }

    /**
     * Gets the name of customer
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the email of customer
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the phone number of customer
     * @return String
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Gets the bikes of customer
     * @return List<BikeDTO>
     */
    public List<BikeDTO> getBikes() {
        return bikes;
    }
}
