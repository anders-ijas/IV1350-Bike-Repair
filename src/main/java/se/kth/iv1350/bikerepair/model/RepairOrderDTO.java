package se.kth.iv1350.bikerepair.model;

/**
 * A DTO of a RepairOrder in order to transfer information between MVC layers
 */
public class RepairOrderDTO {
    private final int id;
    private String date;
    private String customersProblemDescription;
    private String state;
    private String[] diagnosticResults;
    private String[] repairTasks;
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
    public RepairOrderDTO(int id, String date, String customersProblemDescription, String state, String[] diagnosticResults, String[] repairTasks, int price, BikeDTO bike) {
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

    public String getState() {
        return state;
    }

    public String[] getDiagnosticResults() {
        return diagnosticResults;
    }

    public String[] getRepairTasks() {
        return repairTasks;
    }
}
