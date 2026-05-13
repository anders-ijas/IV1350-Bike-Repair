package se.kth.iv1350.bikerepair.integration;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.bikerepair.model.Customer;
import se.kth.iv1350.bikerepair.model.Bike;


/**
 * A Registry that contains the customers
 */
public class CustomerRegistry {
    private List<Customer> customers = new ArrayList<>();

    /**
     * Adds previous customers with their respective bikes.
     */
    private void addPreviousCustomers() {
        List<Bike> alicesBikes = new ArrayList<Bike>();
        List<Bike> bobsBikes = new ArrayList<Bike>();

        alicesBikes.add(new Bike("SuperHypeBike", "2XS","123123"));
        bobsBikes.add(new Bike("NobodyCaresBike", "12", "321321"));
        bobsBikes.add(new Bike("BrokenBike", "4", "111222"));

        customers.add(new Customer("Alice", "0731112223", "alice123@kth.se", alicesBikes ));
        customers.add(new Customer("Bob", "0732221113", "bobtheking2@kth.se", bobsBikes ));
    }

    /**
     * Creates new instance with previous customers
     */
    public CustomerRegistry() {
        addPreviousCustomers();
    }

    /**
     * Finds Customer associated with phone number
     * @param phoneNumber Customers phone number
     * @return Customer
     * @throws CustomerNotFoundException if the customer is not found.
     * @throws DataBaseFailureException If orderID is 503 (Hardcoded simulation)
     */
    public Customer findCustomer(String phoneNumber) throws CustomerNotFoundException, DataBaseFailureException {
        if ("503".equals(phoneNumber)) {
            throw new DataBaseFailureException("Database unreachable during findCustomer");
        }

        for (Customer customer : customers) {
            if (customer.getPhoneNumber().equals(phoneNumber)) {
                return customer;
            }
        }
        throw new CustomerNotFoundException(phoneNumber);
    }

}
