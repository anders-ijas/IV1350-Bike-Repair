package se.kth.iv1350.bikerepair.controller;

import se.kth.iv1350.bikerepair.integration.CustomerRegistry;
import se.kth.iv1350.bikerepair.integration.Printer;
import se.kth.iv1350.bikerepair.integration.RepairOrderRegistry;
import se.kth.iv1350.bikerepair.model.*;

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
     */
    public CustomerDTO enterCustomerInfo(String phoneNumber) {
        Customer customer = custReg.findCustomer(phoneNumber);
        return customer.createDTO();
    }

    /**
     * Creates a repair order based on the customers description
     * @param customer Relevant customer
     * @param description Customers problem description
     * @param serialNumber Serial number of bike
     */
    public void enterProblemDescription(CustomerDTO customer, String description, String serialNumber, String date) {
        BikeDTO bike = selectBike(serialNumber, customer);
        repOrdReg.createOrder(bike, description, date);
    }

    /**
     * Finding a customers bike based on serial number
     * @param serialNumber The serial number of the bike
     * @param customer Customer
     * @return BikeDTO if found else null
     */
    public BikeDTO selectBike(String serialNumber, CustomerDTO customer) {
        for (BikeDTO bike : customer.getBikes()) {
            if (bike.getSerialNumber().equals(serialNumber )) {
                return bike;
            }
        }
        return null;
    }

    /**
     * Provides a repair order based on a repair order id
     * @param orderId Repair order id
     * @return RepairOrderDTO
     */
    public RepairOrderDTO getOrder(int orderId) {
        RepairOrder repairOrder = repOrdReg.getRepairOrder(orderId);
        return repairOrder.createDTO();
    }

    /**
     * Adds a diagnostic result to the repair order based on id
     * @param repairOrderId repair order id
     * @param diagTestResult diagnostic test result
     */
    public void addDiagnosticResult(int repairOrderId, String diagTestResult) {
        RepairOrder repairOrder = repOrdReg.getRepairOrder(repairOrderId);
        repairOrder.addDiagnosticResult(diagTestResult);
    }

    /**
     * Adds a repair task to the repair order based on id
     * @param repairOrderId Repair order id
     * @param repairTask Repair task description
     * @param cost Cost of repair
     */
    public void addRepairTask(int repairOrderId, String repairTask, int cost) {
        RepairOrder repairOrder = repOrdReg.getRepairOrder(repairOrderId);
        repairOrder.addRepairTask(repairTask, cost);
    }

    /**
     * When technician is done with order the order state is changed to READY_FOR_APPROVAL
     * @param repairOrderId The order id
     */
    public void diagnosticsDone(int repairOrderId) {
        RepairOrder repairOrder = repOrdReg.getRepairOrder(repairOrderId);
        repairOrder.setState(State.READY_FOR_APPROVAL);
    }

    /**
     * Prints the repair order
     * @param repairOrderId Repair order id
     */
    public void printRepair(int repairOrderId) {
        RepairOrder repairOrder = repOrdReg.getRepairOrder(repairOrderId);
        RepairOrderDTO repairOrderDTO = repairOrder.createDTO();
        printer.printOrder(repairOrderDTO);
    }

    /**
     * Changes repair order state to accepted
     * @param repairOrderId Repair order id
     */
    public void acceptOrder(int repairOrderId) {
        RepairOrder repairOrder = repOrdReg.getRepairOrder(repairOrderId);
        repairOrder.setState(State.ACCEPTED);
    }
}
