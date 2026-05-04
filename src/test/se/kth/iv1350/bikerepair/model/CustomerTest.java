package se.kth.iv1350.bikerepair.model;

import org.junit.jupiter.api.Test;
import se.kth.iv1350.bikerepair.model.Bike;
import se.kth.iv1350.bikerepair.model.BikeDTO;
import se.kth.iv1350.bikerepair.model.Customer;
import se.kth.iv1350.bikerepair.model.CustomerDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void createDTO() {
        String name = "Bertil";
        String phoneNumber = "+36 111 222";
        String email = "a@a.a";
        List<Bike> bikes = new ArrayList<>();
        Bike bike = new Bike("A","A","A");
        bikes.add(bike);

        Customer customer = new Customer(name, phoneNumber, email, bikes);
        CustomerDTO customerDTO = customer.createDTO();

        assertNotNull(customerDTO, "CustomerDTO should not be null if values initialized");
        assertEquals(name,customerDTO.getName(), "Name mismatch in DTO");
        assertEquals(phoneNumber, customerDTO.getPhoneNumber(), "PhoneNumber mismatch in DTO");
        assertEquals(email, customerDTO.getEmail(), "Email mismatch in DTO");
        assertNotNull(customerDTO.getBikes(), "Bikes should exist if initialized");
    }

    @Test
    void findBikeBySerial() {
        String serialNumber = "1010101";

        String name = "Bertil";
        String phoneNumber = "+36 111 222";
        String email = "a@a.a";
        List<Bike> bikes = new ArrayList<>();
        Bike bike = new Bike("A","A",serialNumber);
        bikes.add(bike);

        Customer customer = new Customer(name, phoneNumber, email, bikes);
        BikeDTO originalBike = bike.createDTO();
        BikeDTO copyBike = customer.findBikeBySerial(serialNumber);

        assertNotNull(copyBike, "Bike should be found if serial number is the same");
        assertEquals(originalBike.getSerialNumber(), copyBike.getSerialNumber(), "Bike found should equal original bike");
    }

    @Test
    void createCustomerDTOWithNullValues() {
        String name = "";
        String phoneNumber = null;
        String email = "";
        List<Bike> bikes = null;

        Customer customer = new Customer(name, phoneNumber, email, bikes);
        CustomerDTO customerDTO = customer.createDTO();

        assertEquals("",customerDTO.getName(),"Name should be empty if initialized empty");
        assertNull(customerDTO.getPhoneNumber(),"PhoneNumber should be null if initialized null");
        assertEquals("", customerDTO.getEmail(),"Email should be empty if initialized empty");
        assertNotNull(customerDTO.getBikes(),"BikesDTO should be empty if Bikes initialized null");
    }

    @Test
    void findBikeBySerialWhenSeveralBikesExist() {
        List<Bike> bikes = new ArrayList<>();
        bikes.add(new Bike("BrandA", "ModelA", "111A"));
        bikes.add(new Bike("BrandB", "ModelB", "SN222"));

        Customer customer = new Customer("Bertil", "123", "a@a.a", bikes);
        BikeDTO foundBike = customer.findBikeBySerial("SN222");

        assertNotNull(foundBike);
        assertEquals("SN222", foundBike.getSerialNumber());
        assertEquals("BrandB", foundBike.getBrand(), "Should find the specific bike requested, not just the first one.");
    }

    @Test
    void findBikeBySerialWhenSeveralBikesHaveSameSerial() {
        String serialNumber = "1010101";

        String name = "Bertil";
        String phoneNumber = "+36 111 222";
        String email = "a@a.a";
        List<Bike> bikes = new ArrayList<>();
        Bike bike1 = new Bike("A","A",serialNumber);
        bikes.add(bike1);
        Bike bike2 = new Bike("B","B",serialNumber);
        bikes.add(bike2);

        Customer customer = new Customer(name, phoneNumber, email, bikes);
        BikeDTO bikeDTO = customer.findBikeBySerial(serialNumber);

        assertNotNull(bikeDTO, "Bike should exist even though there are two");
        assertEquals("A", bikeDTO.getModel(), "The first bike in the list should be chosen");
    }

    @Test
    void findBikeBySerialNotFound() {
        List<Bike> bikes = new ArrayList<>();
        bikes.add(new Bike("BrandA", "ModelA", "111A"));
        bikes.add(new Bike("BrandB", "ModelB", "SN222"));
        Customer customer = new Customer("Bertil", "123", "a@a.a", bikes);

        BikeDTO bikeDTO = customer.findBikeBySerial("somethingNonExistent");

        assertNull(bikeDTO, "BikeDTO should be null if bike not found");
    }
}