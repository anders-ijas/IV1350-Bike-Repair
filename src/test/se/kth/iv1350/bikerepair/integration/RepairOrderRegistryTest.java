package se.kth.iv1350.bikerepair.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.bikerepair.model.RepairOrder;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RepairOrderRegistryTest {

    @BeforeEach
    void setUp() {
        RepairOrder.resetIdCounter();
    }

    @Test
    void createOrderAndGetOrder() throws DataBaseFailureException {
        RepairOrderRegistry repairOrderRegistry = new RepairOrderRegistry();
        LocalDate localDate = LocalDate.now();

        repairOrderRegistry.createOrder(null,"", localDate);
        RepairOrder repairOrder = repairOrderRegistry.getRepairOrder(1);

        assertNotNull(repairOrder,"Repair order not accessible");
    }

    @Test
    void getNonExistentOrder() throws DataBaseFailureException {
        RepairOrderRegistry repairOrderRegistry = new RepairOrderRegistry();
        RepairOrder repairOrder = repairOrderRegistry.getRepairOrder(999);

        assertNull(repairOrder,"A repair order that doesnt exist should return null");
    }

    @Test
    void stateShouldNotChangeOnDatabaseFailure() {
        RepairOrderRegistry registry = new RepairOrderRegistry();
        int expectedSize = 0;
        int hardcodedFailureId = 503;

        try {
            registry.getRepairOrder(hardcodedFailureId);
        } catch (DataBaseFailureException e) {

        }

        assertEquals(expectedSize, registry.getNumberOfRepairOrders());
    }
}