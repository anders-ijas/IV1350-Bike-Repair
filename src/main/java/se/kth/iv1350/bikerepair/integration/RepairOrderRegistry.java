package se.kth.iv1350.bikerepair.integration;

import se.kth.iv1350.bikerepair.model.BikeDTO;
import se.kth.iv1350.bikerepair.model.RepairOrder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Stores all repair orders
 */
public class RepairOrderRegistry {
    private List<RepairOrder> repairOrders = new ArrayList<>();

    /**
     * Creates a new repair order and stores it
     * @param bikeDTO The bikeDTO the repair order specifies
     * @param description Customer problem description
     * @param date The date of the order
     */
    public void createOrder(BikeDTO bikeDTO, String description, LocalDate date) {
        RepairOrder currentOrder = new RepairOrder(bikeDTO,description,date);
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
