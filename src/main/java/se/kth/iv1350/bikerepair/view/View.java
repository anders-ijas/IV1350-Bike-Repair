package se.kth.iv1350.bikerepair.view;

import se.kth.iv1350.bikerepair.controller.Controller;
import se.kth.iv1350.bikerepair.model.BikeDTO;
import se.kth.iv1350.bikerepair.model.CustomerDTO;
import se.kth.iv1350.bikerepair.model.RepairOrderDTO;
import se.kth.iv1350.bikerepair.model.RepairTask;

import java.time.LocalDate;

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
     * Starts the simulated view. All calls from the view to the controller are hardcoded to show how an interaction could take place.
     *
      */
    public void start() {
        LocalDate date = LocalDate.now();
        // Receptionist asks customer for number
        CustomerDTO customer = contr.enterCustomerInfo("0732221113");

        // Shows the information (Prints to console since view layer not implemented)
        System.out.println("\nActive Customer: " + customer.getName() + "; " + customer.getEmail() + "; " + customer.getPhoneNumber() + "; ");
        for (BikeDTO bike : customer.getBikes()) {
            System.out.println(" - Brand: " + bike.getBrand() + " - " + bike.getModel() + " | SN: " + bike.getSerialNumber());
        }
        // Receptionist confirms with customer the information
        // Receptionist asks customer for a description of the problem with the bike and which bike
        contr.enterProblemDescription(customer,"Something wrong with everything!", "111222", date);

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
        System.out.println("Bike: " + repairOrder.getBike().getBrand() + " | " + repairOrder.getBike().getModel() + " | " + repairOrder.getBike().getSerialNumber());

        //Technician performs diagnostic
        contr.addDiagnosticResult(repairOrder.getId(), "Front wheel broken");
        contr.addDiagnosticResult(repairOrder.getId(), "Back wheel broken");
        contr.addDiagnosticResult(repairOrder.getId(), "No chain");

        //Technician adds proposed repair tasks
        contr.addRepairTask(repairOrder.getId(), "Buy new front wheel and install", 1000);
        contr.addRepairTask(repairOrder.getId(), "Buy new back wheel and install", 1200);
        contr.addRepairTask(repairOrder.getId(), "Buy new chain and install", 800);

        contr.diagnosticsDone(repairOrder.getId());

        //Receptionist informs customer about results and individual costs and total costs
        repairOrder = contr.getOrder(1);

        // (Prints to console since view layer not implemented)
        System.out.println("\nRepair Order Tasks and Costs:");
        for (RepairTask repairTask : repairOrder.getRepairTasks()) {
            System.out.println(" - " + repairTask.getTaskDescription() + " | " + repairTask.getCost() + " SEK");
        }
        System.out.println("Total Cost: " + repairOrder.getPrice());

        // Customer accepts proposed repair tasks and costs
        contr.acceptOrder(repairOrder.getId());
        //Since we dont have specified what determines the time we just add a day to the printout
        contr.printRepair(repairOrder.getId(), date.plusDays(1).toString());

        //Receptionist gives order to customer
        //Customer leaves
    }
}
