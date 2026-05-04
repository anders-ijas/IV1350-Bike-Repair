package se.kth.iv1350.bikerepair.controller;

import se.kth.iv1350.bikerepair.integration.CustomerRegistry;
import se.kth.iv1350.bikerepair.integration.Printer;
import se.kth.iv1350.bikerepair.integration.RepairOrderRegistry;
import se.kth.iv1350.bikerepair.model.*;

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
     */
    public CustomerDTO searchCustomerInfo(String phoneNumber) {
        Customer customer = custReg.findCustomer(phoneNumber);
        if (customer != null) {
            return customer.createDTO();
        }
        return null;
    }

    /**
     * Creates a repair order based on the customers description
     * @param customer Relevant customer
     * @param description Customers problem description
     * @param serialNumber Serial number of bike
     */
    public void createNewRepairOrder(CustomerDTO customer, String description, String serialNumber, LocalDate date) {
        BikeDTO bike = selectBike(serialNumber, customer);
        repOrdReg.createOrder(bike, description, date);
    }

    /**
     * Finding a customers bike based on serial number
     * @param serialNumber The serial number of the bike
     * @param customerDTO Customer
     * @return BikeDTO if found else null
     */
    public BikeDTO selectBike(String serialNumber, CustomerDTO customerDTO) {
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
     */
    public RepairOrderDTO getOrder(int orderId) {
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
     */
    public void addDiagnosticResult(int repairOrderId, String diagTestResult) {
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
     */
    public void addRepairTask(int repairOrderId, String repairTask, int cost) {
        RepairOrder repairOrder = repOrdReg.getRepairOrder(repairOrderId);
        if (repairOrder != null) {
            repairOrder.addRepairTask(repairTask, cost);
        }
    }

    /**
     * When technician is done with order the order state is changed to READY_FOR_APPROVAL
     * @param repairOrderId The order id
     */
    public void diagnosticsDone(int repairOrderId) {
        changeState(State.READY_FOR_APPROVAL,repairOrderId);
    }

    /**
     * Prints the repair order
     * @param repairOrderId Repair order id
     */
    public void printRepair(int repairOrderId, String date) {
        RepairOrder repairOrder = repOrdReg.getRepairOrder(repairOrderId);
        if (repairOrder != null) {
            RepairOrderDTO repairOrderDTO = repairOrder.createDTO();
            printer.printOrder(repairOrderDTO, date);
        }
    }

    /**
     * Changes repair order state to accepted
     * @param repairOrderId Repair order id
     */
    public void acceptOrder(int repairOrderId) {
        changeState(State.ACCEPTED,repairOrderId);
    }

    /**
     * Changes the state of the specified repair order
     * @param state What the state should change to
     * @param repairOrderId The repair order id
     */
    private void changeState(State state, int repairOrderId) {
        RepairOrder repairOrder = repOrdReg.getRepairOrder(repairOrderId);
        if (repairOrder != null) {
            repairOrder.setState(state);
        }
    }
}
