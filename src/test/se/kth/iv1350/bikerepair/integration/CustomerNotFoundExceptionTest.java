package se.kth.iv1350.bikerepair.integration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerNotFoundExceptionTest {

    @Test
    void customerNotFoundExceptionTest() {
        String nonExistingNumber = "123545647";
        CustomerRegistry custReg = new CustomerRegistry();

        assertThrows(CustomerNotFoundException.class, () -> {
           custReg.findCustomer(nonExistingNumber);
        }, "If a searching for non-existing customer, CustomerNotFoundException should be thrown");
    }

    @Test
    void customerNotFoundExceptionCorrectMessage() {
        String nonExistingNumber = "123545647";
        CustomerRegistry custReg = new CustomerRegistry();

        CustomerNotFoundException customerNotFoundException = assertThrows(CustomerNotFoundException.class, () ->{
           custReg.findCustomer(nonExistingNumber);
        });

        assertTrue(customerNotFoundException.getMessage().contains(nonExistingNumber));
    }
}