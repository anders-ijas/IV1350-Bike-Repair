package se.kth.iv1350.bikerepair.model;

import java.util.List;

/**
 * A DTO of a RepairOrder in order to transfer information between MVC layers
 */
public final class RepairOrderDTO {
    private final int id;
    private String date;
    private String customersProblemDescription;
    private State state;
    private List<String> diagnosticResults;
    private List<RepairTask> repairTasks;
    private int price;
    private BikeDTO bike;

    /**
     * Creates a new instance.
     * @param id Repair order id
     * @param date Repair order date
     * @param customersProblemDescription Repair order customers problem description
     * @param state Repair order state
     * @param diagnosticResults Repair orders diagnostic results
     * @param repairTasks Repair orders repair tasks
     * @param price Repair orders total price
     * @param bike What bike the repair order is for
     */
    public RepairOrderDTO(int id, String date, String customersProblemDescription, State state, List<String> diagnosticResults, List<RepairTask> repairTasks, int price, BikeDTO bike) {
        this.id = id;
        this.date = date;
        this.customersProblemDescription = customersProblemDescription;
        this.state = state;
        this.diagnosticResults = diagnosticResults;
        this.repairTasks = repairTasks;
        this.price = price;
        this.bike = bike;
    }

    public int getId() {
        return id;
    }

    public BikeDTO getBike() {
        return bike;
    }

    public int getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }

    public String getCustomersProblemDescription() {
        return customersProblemDescription;
    }

    public State getState() {
        return state;
    }

    public List<String> getDiagnosticResults() {
        return diagnosticResults;
    }

    public List<RepairTask> getRepairTasks() {
        return repairTasks;
    }
}
