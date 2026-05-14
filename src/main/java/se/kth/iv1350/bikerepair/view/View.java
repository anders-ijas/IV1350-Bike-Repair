package se.kth.iv1350.bikerepair.view;

import se.kth.iv1350.bikerepair.controller.Controller;
import se.kth.iv1350.bikerepair.integration.CustomerNotFoundException;
import se.kth.iv1350.bikerepair.integration.DataBaseFailureException;
import se.kth.iv1350.bikerepair.integration.FileLogger;
import se.kth.iv1350.bikerepair.model.BikeDTO;
import se.kth.iv1350.bikerepair.model.CustomerDTO;
import se.kth.iv1350.bikerepair.model.RepairOrderDTO;
import se.kth.iv1350.bikerepair.model.RepairTask;
import se.kth.iv1350.bikerepair.model.discountstrategy.NoDiscount;

import java.time.LocalDate;

/**
 * An abstraction of the View layer.
 *  Simulates the calls to the Controller.
 */
public class View {
    private Controller contr;

    /**
     * Creates a new instance.
     *
     * @param contr The controller the view should interact with
     */
    public View(Controller contr) {
        this.contr = contr;
    }

    /**
     * Starts the simulated view. All calls from the view to the controller are hardcoded to show how an interaction could take place.
     *
     */
    public void start() {
        CustomerDTO customer = null;

        LocalDate date = LocalDate.now();
        // Receptionist asks customer for number
        System.out.println("\n--- Searching for Customer ---");
        try {
            customer = contr.searchCustomerInfo("0732221113");
        } catch (CustomerNotFoundException e) {
            System.out.println("ERROR: Could not find customer with phone number: " + e.getPhoneNumber());
        } catch (DataBaseFailureException e) {
            System.out.println("ERROR: Could not connect to server");
            FileLogger.getInstance().log(e.getMessage());
        }

        // Shows the information (Prints to console since view layer not implemented)
        System.out.println("\nActive Customer: " + customer.getName() + "; " + customer.getEmail() + "; " + customer.getPhoneNumber() + "; ");
        for (BikeDTO bike : customer.getBikes()) {
            System.out.println(" - Brand: " + bike.getBrand() + " - " + bike.getModel() + " | SN: " + bike.getSerialNumber());
        }
        // Receptionist confirms with customer the information
        // Receptionist asks customer for a description of the problem with the bike and which bike
        try {
            contr.createNewRepairOrder(customer, "Something wrong with everything!", "111222", date);
        } catch (CustomerNotFoundException e) {
            System.out.println("ERROR: Could not find customer with phone number: " + e.getPhoneNumber());
        } catch (DataBaseFailureException e) {
            System.out.println("ERROR: Could not connect to server");
            FileLogger.getInstance().log(e.getMessage());
        }

        //Technician knows order ID from the RepairOrderView
        RepairOrderDTO repairOrder;

        try {
            repairOrder = contr.getOrder(1);
            contr.setDiscountStrategy(repairOrder.getId(),new NoDiscount());

            //Technician performs diagnostic
            contr.addDiagnosticResult(repairOrder.getId(), "Front wheel broken");
            contr.addDiagnosticResult(repairOrder.getId(), "Back wheel broken");
            contr.addDiagnosticResult(repairOrder.getId(), "No chain");

            //Technician adds proposed repair tasks
            contr.addRepairTask(repairOrder.getId(), "Buy new front wheel and install", 1000);
            contr.addRepairTask(repairOrder.getId(), "Buy new back wheel and install", 1200);
            contr.addRepairTask(repairOrder.getId(), "Buy new chain and install", 800);

            contr.diagnosticsDone(repairOrder.getId());
        } catch (DataBaseFailureException e) {
            System.out.println("ERROR: Could not connect to server");
            FileLogger.getInstance().log(e.getMessage());
        }

        try {
            //Receptionist informs customer about results and individual costs and total costs
            repairOrder = contr.getOrder(1);

            // Customer accepts proposed repair tasks and costs
            contr.acceptOrder(repairOrder.getId());
            //Since we dont have specified what determines the time we just add a day to the printout
            contr.printRepair(repairOrder.getId(), date.plusDays(1).toString());

            //Receptionist gives order to customer
            //Customer leaves}
        } catch (DataBaseFailureException e) {
            System.out.println("ERROR: Could not connect to server");
            FileLogger.getInstance().log(e.getMessage());
        }

        //Showing what a DataBaseFailureException could look like
        System.out.println("\n --- Hardcoded DataBaseFailureException Showcase ---");
        try {
            contr.searchCustomerInfo("503");
        } catch (CustomerNotFoundException e) {
            System.out.println("ERROR: Could not find customer with phone number: " + e.getPhoneNumber());
        } catch (DataBaseFailureException e) {
            System.out.println("ERROR: Could not connect to server");
            FileLogger.getInstance().log(e.getMessage());
        }

        //Showing what a CustomerNotFoundException could look like
        System.out.println("\n --- Hardcoded CustomerNotFoundException Showcase ---");
        try {
            contr.searchCustomerInfo("9999999999999");
        } catch (CustomerNotFoundException e) {
            System.out.println("ERROR: Could not find customer with phone number: " + e.getPhoneNumber());
        } catch (DataBaseFailureException e) {
            System.out.println("ERROR: Could not connect to server");
            FileLogger.getInstance().log(e.getMessage());
        }
    }
}
