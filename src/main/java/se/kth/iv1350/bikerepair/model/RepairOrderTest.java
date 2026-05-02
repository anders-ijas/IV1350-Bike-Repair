package se.kth.iv1350.bikerepair.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RepairOrderTest {
    RepairOrder repairOrder;
    RepairOrderDTO repairOrderDTO;
    LocalDate localDate;

    @BeforeEach
    void setUp() {
        RepairOrder.resetIdCounter();
        BikeDTO bikeDTO = new BikeDTO("SuperBike","XS-2021","SN1002");
        localDate = LocalDate.now();
        repairOrder = new RepairOrder(bikeDTO, "Something wrong", localDate);
        repairOrderDTO = repairOrder.createDTO();
    }

    @Test
    void createDTO() {

        RepairOrderDTO repairOrderDTO = repairOrder.createDTO();

        assertNotNull(repairOrderDTO, "RepairOrderDTO should not be null if created");
        assertEquals(1,repairOrderDTO.getId(),"ID should equal 1");
        assertEquals(localDate, repairOrderDTO.getDate(), "DTO date should equal the non-DTO date");
    }

    @Test
    void testInitialStateIsNew() {
        assertEquals(State.NEWLY_CREATED, repairOrderDTO.getState(), "A new order should start in NEWLY_CREATED state.");
        assertTrue(repairOrderDTO.getRepairTasks().isEmpty(), "New order should have no tasks.");
    }

    @Test
    void addDiagnosticResult() {
        repairOrder.addDiagnosticResult("Bla bla bla");
        repairOrder.addDiagnosticResult("Bla bla bla2");

        repairOrderDTO = repairOrder.createDTO();

        assertNotNull(repairOrderDTO.getDiagnosticResults());
        assertEquals("Bla bla bla", repairOrderDTO.getDiagnosticResults().get(0),"String should match at index 0");
        assertEquals("Bla bla bla2", repairOrderDTO.getDiagnosticResults().get(1), "String should match at index 1");
    }

    @Test
    void addRepairTask() {
        repairOrder.addRepairTask("fix1", 20);
        repairOrder.addRepairTask("fix2", 30);

        repairOrderDTO = repairOrder.createDTO();

        assertNotNull(repairOrderDTO.getRepairTasks());
        assertEquals("fix1", repairOrderDTO.getRepairTasks().get(0).getTaskDescription(), "Task Description does not match at index 0");
        assertEquals("fix2", repairOrderDTO.getRepairTasks().get(1).getTaskDescription(), "Task Description does not match at index 1");
        assertEquals(new Amount(20).toString(), repairOrderDTO.getRepairTasks().get(0).getCost().toString(), "Cost does not match at index 0");
        assertEquals(new Amount(30).toString(), repairOrderDTO.getRepairTasks().get(1).getCost().toString(), "Cost does not match at index 1");
        assertEquals(new Amount (50).toString(), repairOrderDTO.getPrice().toString(),"Price does not equal total cost of individual items");
    }

    @Test
    void setState() {
        repairOrder.setState(State.COMPLETED);

        repairOrderDTO = repairOrder.createDTO();

        assertNotNull(repairOrderDTO.getState());
        assertEquals(State.COMPLETED, repairOrderDTO.getState(),"State should update");
    }

    @Test
    void addNegativeCostRepairTaskNotBelowZero() {
        repairOrder.addRepairTask("fix1", 20);
        repairOrder.addRepairTask("MassiveDiscount", -3000);

        repairOrderDTO = repairOrder.createDTO();

        assertNotNull(repairOrderDTO.getRepairTasks());
        assertEquals(new Amount(20).toString(), repairOrderDTO.getRepairTasks().get(0).getCost().toString(), "Cost does not match at index 0");
        assertEquals(new Amount(-3000).toString(), repairOrderDTO.getRepairTasks().get(1).getCost().toString(), "Cost does not match at index 1");
        assertEquals(new Amount (0).toString(), repairOrderDTO.getPrice().toString(),"Price cannot go below 0");
    }
}