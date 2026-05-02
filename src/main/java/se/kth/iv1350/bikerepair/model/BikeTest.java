package se.kth.iv1350.bikerepair.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BikeTest {

    @Test
    void createDTO() {
        String brand = "BadBike";
        String model = "PowerMAX";
        String serialNumber = "ABC123";
        Bike bike = new Bike(brand, model,serialNumber);

        BikeDTO bikeDTO = bike.createDTO();

        assertNotNull(bikeDTO, "DTO should not be null");
        assertEquals(brand, bikeDTO.getBrand(), "Brand mismatch in DTO");
        assertEquals(model, bikeDTO.getModel(), "Model mismatch in DTO");
        assertEquals(serialNumber, bikeDTO.getSerialNumber(), "Serial number mismatch in DTO");
    }

    @Test
    void testCreateBikeDTOWithNullValues() {
        Bike bike = new Bike(null, "", null);

        BikeDTO bikeDTO = bike.createDTO();
        assertNull(bikeDTO.getBrand(), "Brand should be null if created as null");
        assertEquals("",bikeDTO.getModel(), "Model should be empty if created empty");
        assertNull(bikeDTO.getSerialNumber(), "SerialNumber should be null if created as null");
    }
}