package se.kth.iv1350.bikerepair.view;

import se.kth.iv1350.bikerepair.controller.Controller;
import se.kth.iv1350.bikerepair.model.BikeDTO;
import se.kth.iv1350.bikerepair.model.CustomerDTO;
import se.kth.iv1350.bikerepair.model.RepairOrderDTO;

/**
 * An abstraction of the View layer.
 *  Simulates the calls to the Controller.
 */
public class View {
    private Controller contr;

    /**
     * Creates a new instance.
     * @param contr The controller the view should interact with
     */
    public View(Controller contr) {
        this.contr = contr;
    }

    /**
     * Starts the simulated view
      */
    public void start() {
        // Receptionist asks customer for number
        CustomerDTO customer = contr.enterCustomerInfo("0732221113");

        // Shows the information (Prints to console since view layer not implemented)
        System.out.println("\nActive Customer: " + customer.getName() + "; " + customer.getEmail() + "; " + customer.getPhoneNumber() + "; ");
        for (BikeDTO bike : customer.getBikes()) {
            System.out.println(" - Brand: " + bike.getBrand() + " - " + bike.getModel() + " | SN: " + bike.getSerialNumber());
        }
        // Receptionist confirms with customer the information
        // Receptionist asks customer for a description of the problem with the bike and which bike
        contr.enterProblemDescription(customer,"Something wrong with everything!", "111222","Yestermorrow");

        //Technician asks for repair order (Somehow knowing the orders id)
        RepairOrderDTO repairOrder = contr.getOrder(1);
        //Shows information to technician (Prints to console since view layer not implemented)
        System.out.println("\nRepair Order:");
        System.out.println("ID: " + repairOrder.getId());
        System.out.println("Date: " + repairOrder.getDate());
        System.out.println("Desc: " + repairOrder.getCustomersProblemDescription());
        System.out.println("State: " + repairOrder.getState());
        System.out.println("Repair Tasks: " + repairOrder.getRepairTasks());
        System.out.println("Diagnostic Tasks: " + repairOrder.getDiagnosticResults());
        System.out.println("Price: " + repairOrder.getPrice());
    }
}
