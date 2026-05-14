package se.kth.iv1350.bikerepair.controller;

import se.kth.iv1350.bikerepair.integration.*;
import se.kth.iv1350.bikerepair.model.*;
import se.kth.iv1350.bikerepair.model.discountstrategy.DiscountStrategy;

import java.time.LocalDate;

/**
 *  The controller class that interacts with the view
 *  and model layer.
 */
public class Controller {
    private final CustomerRegistry custReg;
    private final RepairOrderRegistry repOrdReg;
    private final Printer printer;

    /**
     * Creates a new instance.
     * @param custReg The customer registry for the controller
     * @param repOrdReg The repair order registry for the controller
     */
    public Controller(CustomerRegistry custReg, RepairOrderRegistry repOrdReg, Printer printer) {
        this.custReg = custReg;
        this.repOrdReg = repOrdReg;
        this.printer = printer;
    }

    /**
     * Finds the Customer through their phone number
     * @param phoneNumber Customers phone number
     * @return CustomerDTO
     * @throws CustomerNotFoundException if no customer is found
     * @throws DataBaseFailureException If orderID is 503 (Hardcoded simulation)
     */
    public CustomerDTO searchCustomerInfo(String phoneNumber) throws CustomerNotFoundException, DataBaseFailureException {
        Customer customer = custReg.findCustomer(phoneNumber);
        return customer.createDTO();
    }

    /**
     * Creates a repair order based on the customers description
     * @param customer Relevant customer
     * @param description Customers problem description
     * @param serialNumber Serial number of bike
     * @throws CustomerNotFoundException If customer not found
     * @throws DataBaseFailureException If orderID is 503 (Hardcoded simulation)
     */
    public void createNewRepairOrder(CustomerDTO customer, String description, String serialNumber, LocalDate date) throws CustomerNotFoundException, DataBaseFailureException{
        BikeDTO bike = selectBike(serialNumber, customer);
        repOrdReg.createOrder(bike, description, date);
    }

    /**
     * Finding a customers bike based on serial number
     * @param serialNumber The serial number of the bike
     * @param customerDTO Customer
     * @return BikeDTO if found else null
     * @throws CustomerNotFoundException If customer not found
     * @throws DataBaseFailureException If orderID is 503 (Hardcoded simulation)
     */
    public BikeDTO selectBike(String serialNumber, CustomerDTO customerDTO) throws CustomerNotFoundException, DataBaseFailureException {
        if (customerDTO != null) {
            Customer customer = custReg.findCustomer(customerDTO.getPhoneNumber());
            return customer.findBikeBySerial(serialNumber);
        }
        return null;
    }

    /**
     * Provides a repair order based on a repair order id
     * @param orderId Repair order id
     * @return RepairOrderDTO
     * @throws DataBaseFailureException If orderID is 503 (Hardcoded simulation)
     */
    public RepairOrderDTO getOrder(int orderId) throws DataBaseFailureException {
        RepairOrder repairOrder = repOrdReg.getRepairOrder(orderId);
        if (repairOrder != null) {
            return repairOrder.createDTO();
        }
        return null;
    }

    /**
     * Adds a diagnostic result to the repair order based on id
     * @param repairOrderId repair order id
     * @param diagTestResult diagnostic test result
     * @throws DataBaseFailureException If orderID is 503 (Hardcoded simulation)
     */
    public void addDiagnosticResult(int repairOrderId, String diagTestResult) throws DataBaseFailureException {
        RepairOrder repairOrder = repOrdReg.getRepairOrder(repairOrderId);
        if (repairOrder != null) {
            repairOrder.addDiagnosticResult(diagTestResult);
        }
    }

    /**
     * Adds a repair task to the repair order based on id
     * @param repairOrderId Repair order id
     * @param repairTask Repair task description
     * @param cost Cost of repair
     * @throws DataBaseFailureException If orderID is 503 (Hardcoded simulation)
     */
    public void addRepairTask(int repairOrderId, String repairTask, int cost) throws DataBaseFailureException {
        RepairOrder repairOrder = repOrdReg.getRepairOrder(repairOrderId);
        if (repairOrder != null) {
            repairOrder.addRepairTask(repairTask, cost);
        }
    }

    /**
     * When technician is done with order the order state is changed to READY_FOR_APPROVAL
     * @param repairOrderId The order id
     * @throws DataBaseFailureException If orderID is 503 (Hardcoded simulation)
     */
    public void diagnosticsDone(int repairOrderId) throws DataBaseFailureException {
        repOrdReg.changeState(State.READY_FOR_APPROVAL,repairOrderId);
    }

    /**
     * Prints the repair order
     * @param repairOrderId Repair order id
     * @throws DataBaseFailureException If orderID is 503 (Hardcoded simulation)
     */
    public void printRepair(int repairOrderId, String date) throws DataBaseFailureException {
        RepairOrder repairOrder = repOrdReg.getRepairOrder(repairOrderId);
        if (repairOrder != null) {
            RepairOrderDTO repairOrderDTO = repairOrder.createDTO();
            printer.printOrder(repairOrderDTO, date);
        }
    }

    /**
     * Changes repair order state to accepted
     * @param repairOrderId Repair order id
     * @throws DataBaseFailureException If orderID is 503 (Hardcoded simulation)
     */
    public void acceptOrder(int repairOrderId) throws DataBaseFailureException {
        repOrdReg.changeState(State.ACCEPTED,repairOrderId);
    }


    /**
     * Adds a repair order observer to repair order registry so the observer is notified when a repair order is updated.
     * @param repairOrderObserver The repair order observer to add.
     */
    public void addRepairOrderObserver(RepairOrderObserver repairOrderObserver) {
        repOrdReg.addObserver(repairOrderObserver);
    }

    /**
     * Sets the discount strategy for specified repair order
     * @param repairOrderId The ID of repair order
     * @param discountStrategy Discount strategy to use
     * @throws DataBaseFailureException If orderID is 503 (Hardcoded simulation)
     */
    public void setDiscountStrategy(int repairOrderId, DiscountStrategy discountStrategy) throws DataBaseFailureException {
        RepairOrder repairOrder = repOrdReg.getRepairOrder(repairOrderId);
        if (repairOrder != null) {
            repairOrder.setDiscountingStrategy(discountStrategy);
        }
    }
}
