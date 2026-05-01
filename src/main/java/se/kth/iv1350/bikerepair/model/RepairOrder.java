package se.kth.iv1350.bikerepair.model;

public class RepairOrder {
    private final int id;
    private String date;
    private String customersProblemDescription;
    private String state;
    private String[] diagnosticResults;
    private String[] repairTasks;
    private int price;
    private BikeDTO bike;

    private static int idCounter = 0;

    /**
     * Creates a new instance.
     */
    public RepairOrder(BikeDTO bike, String customersProblemDescription, String date) {
        this.id = ++idCounter;
        this.bike = bike;
        this.customersProblemDescription = customersProblemDescription;
        this.date = date;
        this.state = "Newly created";
    }

    public RepairOrderDTO createDTO() {
        return new RepairOrderDTO(id, date, customersProblemDescription, state, diagnosticResults, repairTasks, price, bike);
    }

    public int getId() {
        return id;
    }
}
