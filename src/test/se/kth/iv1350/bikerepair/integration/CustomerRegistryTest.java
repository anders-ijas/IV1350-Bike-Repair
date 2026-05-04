package se.kth.iv1350.bikerepair.integration;

import org.junit.jupiter.api.Test;
import se.kth.iv1350.bikerepair.integration.CustomerRegistry;
import se.kth.iv1350.bikerepair.model.Customer;

import static org.junit.jupiter.api.Assertions.*;

class CustomerRegistryTest {

    @Test
    void findCustomer() {
        CustomerRegistry customerRegistry = new CustomerRegistry();

        //From previous customers list that is added when creating registry (Example data)
        Customer customer = customerRegistry.findCustomer("0731112223");

        assertNotNull(customer);
        assertEquals("0731112223", customer.getPhoneNumber(),"Found customers phone number does not match");
    }

    @Test
    void noFindCustomer() {
        CustomerRegistry customerRegistry = new CustomerRegistry();

        //From previous customers list that is added when creating registry (Example data)
        Customer customer = customerRegistry.findCustomer("99999999999999");

        assertNull(customer, "Customer should not be found");
    }

    @Test
    void nullHandlingFindCustomer() {
        CustomerRegistry customerRegistry = new CustomerRegistry();

        //From previous customers list that is added when creating registry (Example data)
        Customer customer = customerRegistry.findCustomer(null);

        assertNull(customer, "Should return null");
    }
}