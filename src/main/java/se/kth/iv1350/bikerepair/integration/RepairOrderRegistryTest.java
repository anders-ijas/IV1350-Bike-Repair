package se.kth.iv1350.bikerepair.integration;

import org.junit.jupiter.api.Test;
import se.kth.iv1350.bikerepair.model.RepairOrder;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RepairOrderRegistryTest {

    @Test
    void createOrderAndGetOrder() {
        RepairOrderRegistry repairOrderRegistry = new RepairOrderRegistry();
        LocalDate localDate = LocalDate.now();

        repairOrderRegistry.createOrder(null,"", localDate);
        RepairOrder repairOrder = repairOrderRegistry.getRepairOrder(1);

        assertNotNull(repairOrder,"Repair order not accessible");
    }

    @Test
    void getNonExistentOrder() {
        RepairOrderRegistry repairOrderRegistry = new RepairOrderRegistry();
        RepairOrder repairOrder = repairOrderRegistry.getRepairOrder(999);

        assertNull(repairOrder,"A repair order that doesnt exist should return null");
    }
}