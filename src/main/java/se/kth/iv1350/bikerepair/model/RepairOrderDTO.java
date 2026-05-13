package se.kth.iv1350.bikerepair.model;

import se.kth.iv1350.bikerepair.model.discountstrategy.DiscountStrategy;

import java.time.LocalDate;
import java.util.List;

/**
 * A DTO of a RepairOrder in order to transfer information between MVC layers
 */
public final class RepairOrderDTO {
    private final int id;
    private LocalDate date;
    private String customersProblemDescription;
    private State state;
    private List<String> diagnosticResults;
    private List<RepairTask> repairTasks;
    private Amount finalPrice;
    private BikeDTO bike;

    /**
     * Creates a new instance.
     * @param id Repair order id
     * @param date Repair order date
     * @param customersProblemDescription Repair order customers problem description
     * @param state Repair order state
     * @param diagnosticResults Repair orders diagnostic results
     * @param repairTasks Repair orders repair tasks
     * @param price Repair orders total price accounted for discounts
     * @param bike What bike the repair order is for
     */
    public RepairOrderDTO(int id, LocalDate date, String customersProblemDescription, State state, List<String> diagnosticResults, List<RepairTask> repairTasks, Amount price, BikeDTO bike) {
        this.id = id;
        this.date = date;
        this.customersProblemDescription = customersProblemDescription;
        this.state = state;
        this.diagnosticResults = diagnosticResults;
        this.repairTasks = repairTasks;
        this.finalPrice = price;
        this.bike = bike;
    }

    /**
     * Get the repair order id
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Get the bikeDTO
     * @return BikeDTO
     */
    public BikeDTO getBike() {
        return bike;
    }

    /**
     * Get the total price of repair order
     * @return Amount
     */
    public Amount getPrice() {
        return finalPrice;
    }

    /**
     * Get the date of the repair order
     * @return LocalDate
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets the customers problem description
     * @return String
     */
    public String getCustomersProblemDescription() {
        return customersProblemDescription;
    }

    /**
     * Gets the state of the repair order
     * @return State
     */
    public State getState() {
        return state;
    }

    /**
     * Gets the list of diagnostic results
     * @return List<String>
     */
    public List<String> getDiagnosticResults() {
        return diagnosticResults;
    }

    /**
     * Gets the list of repair tasks
     * @return List<RepairTask>
     */
    public List<RepairTask> getRepairTasks() {
        return repairTasks;
    }
}
