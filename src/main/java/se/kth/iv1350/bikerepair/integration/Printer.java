package se.kth.iv1350.bikerepair.integration;

import se.kth.iv1350.bikerepair.model.RepairOrder;
import se.kth.iv1350.bikerepair.model.RepairOrderDTO;
import se.kth.iv1350.bikerepair.model.RepairTask;

/**
 * Represents a printer in order to be able to simulate printing orders
 */
public class Printer {

    /**
     * Prints out the entire repair order (Since the printer is simulated we print to console instead)
     * @param repairOrderDTO The repair order to print
     */
    public void printOrder(RepairOrderDTO repairOrderDTO, String date) {
        System.out.println("\n --- Printing ----");
        System.out.println("\nRepair Order:");
        System.out.println("ID: " + repairOrderDTO.getId());
        System.out.println("Date: " + repairOrderDTO.getDate());
        System.out.println("Date Complete: " + date);
        System.out.println("Desc: " + repairOrderDTO.getCustomersProblemDescription());
        System.out.println("State: " + repairOrderDTO.getState());
        for (RepairTask repairTask : repairOrderDTO.getRepairTasks())
            System.out.println(" - " + repairTask.getTaskDescription() + " | " + repairTask.getCost());
        System.out.println("Total cost: " + repairOrderDTO.getPrice());
        System.out.println("Bike: " + repairOrderDTO.getBike().getBrand() + " | " + repairOrderDTO.getBike().getModel() + " | " + repairOrderDTO.getBike().getSerialNumber());
    }
}
