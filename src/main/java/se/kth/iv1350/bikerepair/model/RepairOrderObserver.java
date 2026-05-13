package se.kth.iv1350.bikerepair.model;

/**
 * Interface for repair order observers
 */
public interface RepairOrderObserver {

    /**
     * Called when a repair order is updated.
     * @param repairOrderDTO The DTO of the updated repair order.
     */
    void updateRepairOrder(RepairOrderDTO repairOrderDTO);
}
