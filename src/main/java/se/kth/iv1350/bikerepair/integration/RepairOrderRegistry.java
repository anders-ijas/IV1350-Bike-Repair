package se.kth.iv1350.bikerepair.integration;

import se.kth.iv1350.bikerepair.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Stores all repair orders
 */
public class RepairOrderRegistry {
    private List<RepairOrder> repairOrders = new ArrayList<>();
    private List<RepairOrderObserver> observers = new ArrayList<>();

    /**
     * Creates a new repair order and stores it
     * @param bikeDTO The bikeDTO the repair order specifies
     * @param description Customer problem description
     * @param date The date of the order
     * @throws DataBaseFailureException If orderID is 503 (Hardcoded simulation)
     */
    public void createOrder(BikeDTO bikeDTO, String description, LocalDate date) throws DataBaseFailureException {
        RepairOrder currentOrder = new RepairOrder(bikeDTO,description,date);
        repairOrders.add(currentOrder);
        this.updateRepairOrder(currentOrder.getId());
    }

    /**
     * Finds repair order based on repair order id
     * @param orderID The repair order id
     * @return RepairOrder
     * @throws DataBaseFailureException If orderID is 503 (Hardcoded simulation)
     */
    public RepairOrder getRepairOrder(int orderID) throws DataBaseFailureException {
        if (orderID == 503) {
            throw new DataBaseFailureException("Database unreachable during getRepairOrder. ");
        }

        for (RepairOrder repairOrder : repairOrders) {
            if (repairOrder.getId() == (orderID)) {
                return repairOrder;
            }
        }
        return null;
    }

    /**
     * Changes the state of the specified repair order
     * @param state What the state should change to
     * @param repairOrderId The repair order id
     * @throws DataBaseFailureException If orderID is 503 (Hardcoded simulation)
     */
    public void changeState(State state, int repairOrderId) throws DataBaseFailureException{
        RepairOrder repairOrder = this.getRepairOrder(repairOrderId);
        if (repairOrder != null) {
            repairOrder.setState(state);
            this.updateRepairOrder(repairOrderId);
        }
    }

    /**
     * Adds observers to be notified when a repair order updates
     * @param repairOrderObserver The repair order observer to add
     */
    public void addObserver(RepairOrderObserver repairOrderObserver) {
        observers.add(repairOrderObserver);
    }

    /**
     * Updates all observers with the updated repair order
     * @param repairOrderId The repair order ID to update
     * @throws DataBaseFailureException If orderID is 503 (Hardcoded simulation)
     */
    public void updateRepairOrder(int repairOrderId) throws DataBaseFailureException{
        RepairOrder repairOrder = getRepairOrder(repairOrderId);
        RepairOrderDTO repairOrderDTO = repairOrder.createDTO();

        for (RepairOrderObserver repairOrderObserver: observers) {
            repairOrderObserver.updateRepairOrder(repairOrderDTO);
        }
    }

    /**
     * Used in testing to see how many repair orders exists.
     * @return int
     */
    public int getNumberOfRepairOrders() {
        int numberOfOrders = 0;
        for (RepairOrder repairOrder: repairOrders) {
            numberOfOrders++;
        }
        return numberOfOrders;
    }
}
