package se.kth.iv1350.bikerepair.integration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataBaseFailureExceptionTest {

    @Test
    void customerRegistryDataBaseFailureTest() {
        String hardcodedFailureId = "503";
        CustomerRegistry custReg = new CustomerRegistry();

        assertThrows(DataBaseFailureException.class, () -> {
            custReg.findCustomer(hardcodedFailureId);
        }, "Customer registry should throw DataBaseFailureException when ID is 503");
    }

    @Test
    void repairOrderRegistryDataBaseFailureTest() {
        int hardcodedFailureId = 503;
        RepairOrderRegistry repairOrderRegistry = new RepairOrderRegistry();

        assertThrows(DataBaseFailureException.class, () -> {
            repairOrderRegistry.getRepairOrder(hardcodedFailureId);
        }, "Repair order registry should throw DataBaseFailureException when ID is 503");
    }
}