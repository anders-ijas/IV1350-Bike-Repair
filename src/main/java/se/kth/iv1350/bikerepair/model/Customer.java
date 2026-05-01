package se.kth.iv1350.bikerepair.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A Customer with a name, phone number and email
 */
public class Customer {
    private final String name;
    private final String phoneNumber;
    private final String email;
    private List<Bike> bikes = new ArrayList<>();

    /**
     * Creates a new instance.
     * @param name Name of customer
     * @param phoneNumber Phone number of customer
     * @param email Email of customer
     * @param bikes All customers bikes
     */
    public Customer(String name, String phoneNumber, String email, List<Bike> bikes) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.bikes = bikes;
    }
    /**
     * Creates a CustomerDTO for information transfer between layers.
     * @return CustomerDTO
     */
    public CustomerDTO createDTO() {
        List<BikeDTO> bikeDTOs = new ArrayList<>();
        for (Bike bike : this.bikes) {
            bikeDTOs.add(bike.createDTO());
        }
        return new CustomerDTO(name, phoneNumber, email, bikeDTOs);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
