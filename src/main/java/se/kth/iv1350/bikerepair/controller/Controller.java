package se.kth.iv1350.bikerepair.controller;

import se.kth.iv1350.bikerepair.integration.CustomerRegistry;
import se.kth.iv1350.bikerepair.integration.RepairOrderRegistry;
import se.kth.iv1350.bikerepair.model.*;

import java.util.List;

/**
 *  The controller class that interacts with the view
 *  and model layer.
 */
public class Controller {
    private final CustomerRegistry custReg;
    private final RepairOrderRegistry repOrdReg;

    /**
     * Creates a new instance.
     * @param custReg The customer registry for the controller
     * @param repOrdReg The repair order registry for the controller
     */
    public Controller(CustomerRegistry custReg, RepairOrderRegistry repOrdReg) {
        this.custReg = custReg;
        this.repOrdReg = repOrdReg;
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
     * @param orderID Repair order id
     * @return RepairOrderDTO
     */
    public RepairOrderDTO getOrder(int orderID) {
        RepairOrder repairOrder = repOrdReg.getRepairOrder(orderID);
        return repairOrder.createDTO();
    }
}
