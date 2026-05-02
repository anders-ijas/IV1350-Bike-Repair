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

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<BikeDTO> getBikes() {
        return bikes;
    }
}
