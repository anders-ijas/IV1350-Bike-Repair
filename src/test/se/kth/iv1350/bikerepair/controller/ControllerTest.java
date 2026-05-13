package se.kth.iv1350.bikerepair.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.bikerepair.integration.*;
import se.kth.iv1350.bikerepair.model.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    Controller contr;
    RepairOrderRegistry repOrdReg;

    @BeforeEach
    void setUp() {
        RepairOrder.resetIdCounter();
        repOrdReg = new RepairOrderRegistry();
        contr = new Controller(new CustomerRegistry(), repOrdReg, new Printer());
    }

    @Test
    void searchCustomerInfo() throws CustomerNotFoundException, DataBaseFailureException {
        String phoneNumber = "0732221113"; //Bob

        CustomerDTO customerDTO = contr.searchCustomerInfo(phoneNumber);

        assertNotNull(customerDTO, "Should return customer if customer exists");
        assertEquals(phoneNumber,customerDTO.getPhoneNumber(),"Customers phone number should equal searched number");
    }

    @Test
    void searchNonExistentCustomerInfo() {
        String phoneNumber = "99999999999";

        assertThrows(CustomerNotFoundException.class, () -> {
            contr.searchCustomerInfo(phoneNumber);
        }, "If customer does not exist, CustomerNotFoundException should be thrown");
    }

    @Test
    void searchNullCustomerInfo() {
        String phoneNumber = null;

        assertThrows(CustomerNotFoundException.class, () -> {
            contr.searchCustomerInfo(phoneNumber);
        }, "If phone number is null, CustomerNotFoundException should be thrown");
    }

    @Test
    void createNewRepairOrder() throws CustomerNotFoundException, DataBaseFailureException {
        String phoneNumber = "0732221113"; //Bob
        CustomerDTO customerDTO = contr.searchCustomerInfo(phoneNumber);
        String serialNumber = customerDTO.getBikes().get(1).getSerialNumber();
        LocalDate localDate = LocalDate.now();
        String problemDescription = "I dont know";

        contr.createNewRepairOrder(customerDTO, problemDescription, serialNumber, localDate);
        RepairOrder repairOrder = repOrdReg.getRepairOrder(1);

        assertNotNull(repairOrder, "Repair order should exist if created");
        assertEquals(problemDescription,repairOrder.createDTO().getCustomersProblemDescription(),"Customers problem description should match");
        assertEquals(serialNumber,repairOrder.createDTO().getBike().getSerialNumber(), "Repair order should have the same bike as initialized with");
    }

    @Test
    void selectBike() throws CustomerNotFoundException, DataBaseFailureException{
        String phoneNumber = "0732221113"; //Bob
        CustomerDTO customerDTO = contr.searchCustomerInfo(phoneNumber);
        String serialNumber = customerDTO.getBikes().get(1).getSerialNumber();

        BikeDTO bikeDTO = contr.selectBike(serialNumber,customerDTO);

        assertNotNull(bikeDTO,"Bike should be returned not null");
        assertEquals(serialNumber,bikeDTO.getSerialNumber(),"Serial number should be equal to bike searched for");
    }

    @Test
    void selectNonExistentBike() throws CustomerNotFoundException, DataBaseFailureException{
        String phoneNumber = "0732221113"; //Bob
        CustomerDTO customerDTO = contr.searchCustomerInfo(phoneNumber);
        String serialNumber = "AAAAAAAAAAAAAAAAA";
        BikeDTO bikeDTO = contr.selectBike(serialNumber,customerDTO);

        assertNull(bikeDTO,"Should return null if bike doesnt exist");
    }

    @Test
    void searchBikeNullCustomer() throws CustomerNotFoundException, DataBaseFailureException{
        String serialNumber = "123";

        BikeDTO bikeDTO = contr.selectBike(serialNumber,null);

        assertNull(bikeDTO,"Should return null if customer doesnt exist");
    }

    @Test
    void searchBikeNullSerialNumber() throws CustomerNotFoundException, DataBaseFailureException{
        String serialNumber = null;
        String phoneNumber = "0732221113"; //Bob
        CustomerDTO customerDTO = contr.searchCustomerInfo(phoneNumber);

        BikeDTO bikeDTO = contr.selectBike(null,customerDTO);

        assertNull(bikeDTO,"Should return null if bike doesnt exist");
    }

    @Test
    void getOrder() throws CustomerNotFoundException,DataBaseFailureException{
        String phoneNumber = "0732221113"; //Bob
        CustomerDTO customerDTO = contr.searchCustomerInfo(phoneNumber);
        BikeDTO bikeDTO = customerDTO.getBikes().get(1);
        LocalDate localDate = LocalDate.now();
        String problemDescription = "I dont know";

        repOrdReg.createOrder(bikeDTO,problemDescription,localDate);
        RepairOrderDTO repairOrderDTO = contr.getOrder(1);

        assertNotNull(repairOrderDTO,"Repair order not provided correctly");
        assertEquals(bikeDTO.getSerialNumber(),repairOrderDTO.getBike().getSerialNumber(), "Bikes should be the same");
    }

    @Test
    void getNonExistentOrder() throws DataBaseFailureException {
        RepairOrderDTO repairOrderDTO = contr.getOrder(999999);

        assertNull(repairOrderDTO, "Should be provided with null if order doesnt exist");
    }

    @Test
    void addDiagnosticResult() throws DataBaseFailureException {
        repOrdReg.createOrder(new BikeDTO("A","B","C"),"Something",LocalDate.now());
        String diagnosticResult = "Broken";

        contr.addDiagnosticResult(1, diagnosticResult);

        assertNotNull(repOrdReg.getRepairOrder(1).createDTO().getDiagnosticResults().get(0));
        assertEquals(diagnosticResult, repOrdReg.getRepairOrder(1).createDTO().getDiagnosticResults().get(0),"Diagnostic result should be added to the repair order");
    }

    @Test
    void addDiagnosticForNonExistentOrder(){
        assertDoesNotThrow(() -> {
            contr.addDiagnosticResult(99999999,"asdf");
        }, "The system should handle non existing order without failing");
    }

    @Test
    void addRepairTask() throws DataBaseFailureException {
        repOrdReg.createOrder(new BikeDTO("A","B","C"),"Something",LocalDate.now());
        int cost = 123;
        Amount amountCost = new Amount(cost);
        String taskDescription = "something";

        contr.addRepairTask(1,taskDescription,cost);
        RepairOrderDTO repairOrderDTO = repOrdReg.getRepairOrder(1).createDTO();

        assertNotNull(repairOrderDTO.getRepairTasks().get(0), "Repair order should exist");
        assertEquals(taskDescription, repairOrderDTO.getRepairTasks().get(0).getTaskDescription(), "Task descriptions should match");
        assertEquals(amountCost.toString(), repairOrderDTO.getRepairTasks().get(0).getCost().toString(), "Error in amount, Amounts should equal");
    }

    @Test
    void addRepairTaskForNonExistentOrder(){
        assertDoesNotThrow(() -> {
            contr.addRepairTask(99999999,"asdf",20);
        }, "The system should handle non existing order without failing");
    }

    @Test
    void diagnosticsDone() throws DataBaseFailureException {
        repOrdReg.createOrder(new BikeDTO("A","B","C"),"Something",LocalDate.now());

        contr.diagnosticsDone(1);

        assertEquals(State.READY_FOR_APPROVAL, repOrdReg.getRepairOrder(1).createDTO().getState(),"State should equal READY_FOR_APPROVAL");
    }

    @Test
    void diagnosticsDoneForNonExistentOrder() {
        assertDoesNotThrow(() -> {
            contr.diagnosticsDone(99999999);
        }, "The system should handle non existing order without failing");
    }

    @Test
    void printRepair() {
        assertDoesNotThrow(() -> {
        String phoneNumber = "0732221113"; //Bob
        CustomerDTO customerDTO = contr.searchCustomerInfo(phoneNumber);
        BikeDTO bikeDTO = customerDTO.getBikes().get(1);
        LocalDate localDate = LocalDate.now();
        String problemDescription = "This is a test printout";

        repOrdReg.createOrder(bikeDTO,problemDescription,localDate);
        contr.printRepair(1,localDate.toString());
        }, "The system should handle printing order without failing");
    }

    @Test
    void printNonExistentOrder() {
        assertDoesNotThrow(() -> {
        contr.printRepair(999999,LocalDate.now().toString());
        }, "The system should handle non existent order without crashing");
    }

    @Test
    void acceptOrder() throws DataBaseFailureException {
        repOrdReg.createOrder(new BikeDTO("A","B","C"),"Something",LocalDate.now());

        contr.acceptOrder(1);

        assertEquals(State.ACCEPTED, repOrdReg.getRepairOrder(1).createDTO().getState(),"State should equal ACCEPTED");
    }

    @Test
    void acceptNonExistentOrder() {
        assertDoesNotThrow(() -> {
            contr.acceptOrder(999999);
        }, "The system should handle non existent order without crashing");
    }
}