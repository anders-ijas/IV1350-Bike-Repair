package se.kth.iv1350.bikerepair.view;

import se.kth.iv1350.bikerepair.model.RepairOrderDTO;
import se.kth.iv1350.bikerepair.model.RepairOrderObserver;
import se.kth.iv1350.bikerepair.model.RepairTask;

public class RepairOrderView implements RepairOrderObserver {

    @Override
    public void updateRepairOrder(RepairOrderDTO repairOrder) {
        System.out.println("\n--- New Update to Repair Order ---");
        System.out.println("ID: " + repairOrder.getId());
        System.out.println("Date: " + repairOrder.getDate());
        System.out.println("Desc: " + repairOrder.getCustomersProblemDescription());
        System.out.println("State: " + repairOrder.getState());
        System.out.println("Diagnostic Tasks: " + repairOrder.getDiagnosticResults());
        for (RepairTask repairTask : repairOrder.getRepairTasks()) {
            System.out.println(" - " + repairTask.getTaskDescription() + " | " + repairTask.getCost());
        }
        System.out.println("Total Cost: " + repairOrder.getPrice());
        System.out.println("Bike: " + repairOrder.getBike().getBrand() + " | " + repairOrder.getBike().getModel() + " | " + repairOrder.getBike().getSerialNumber());
    }
}
