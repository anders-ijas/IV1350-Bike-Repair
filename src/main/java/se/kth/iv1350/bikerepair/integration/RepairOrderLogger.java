package se.kth.iv1350.bikerepair.integration;

import se.kth.iv1350.bikerepair.model.RepairOrderDTO;
import se.kth.iv1350.bikerepair.model.RepairOrderObserver;

public class RepairOrderLogger implements RepairOrderObserver {

    @Override
    /**
     * Called when a repair order is updated.
     * @param repairOrderDTO The DTO of the updated repair order.
     */
    public void updateRepairOrder(RepairOrderDTO repairOrderDTO) {
        String message = "--- Repair Order Update --- \n" +
                "Repair Order ID: " + repairOrderDTO.getId() + "\n" +
                "Repair Order Date: " + repairOrderDTO.getDate() + "\n" +
                "Repair Order Description: " + repairOrderDTO.getCustomersProblemDescription() + "\n" +
                "Repair Order State: " + repairOrderDTO.getState() + "\n" +
                "--- ---\n";
        FileLogger.log(message);
    }
}
