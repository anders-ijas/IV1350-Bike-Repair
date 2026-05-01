package se.kth.iv1350.bikerepair.integration;

import se.kth.iv1350.bikerepair.model.BikeDTO;
import se.kth.iv1350.bikerepair.model.RepairOrder;

import java.util.ArrayList;
import java.util.List;


/**
 * Stores all repair orders
 */
public class RepairOrderRegistry {
    private List<RepairOrder> repairOrders = new ArrayList<>();

    /**
     * Creates a new repair order and stores it
     * @param bike The bike the repair order specifies
     * @param description Customer problem description
     * @param date The date of the order
     */
    public void createOrder(BikeDTO bike, String description, String date) {
        RepairOrder currentOrder = new RepairOrder(bike, description,date);
        repairOrders.add(currentOrder);
    }


    /**
     * Finds repair order based on repair order id
     * @param orderID The repair order id
     * @return RepairOrder
     */
    public RepairOrder getRepairOrder(int orderID) {
        for (RepairOrder repairOrder : repairOrders) {
            if (repairOrder.getId() == (orderID)) {
                return repairOrder;
            }
        }
        return null;
    }
}
