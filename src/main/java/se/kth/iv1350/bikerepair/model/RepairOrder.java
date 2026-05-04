package se.kth.iv1350.bikerepair.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * A repair order for a bike
 */
public class RepairOrder {
    private final int id;
    private LocalDate date;
    private String customersProblemDescription;
    private State state;
    private List<String> diagnosticResults;
    private List<RepairTask> repairTasks;
    private Amount totalPrice;
    private BikeDTO bike;

    private static int idCounter = 0;

    /**
     * Creates a new instance with unique id.
     * @param bike Customers bike
     * @param date Date of repair Order
     * @param customersProblemDescription Customers problem description
     */
    public RepairOrder(BikeDTO bike, String customersProblemDescription, LocalDate date) {
        this.id = ++idCounter;
        this.bike = bike;
        this.customersProblemDescription = customersProblemDescription;
        this.date = date;
        this.state = State.NEWLY_CREATED;
        this.totalPrice = new Amount(0);
        this.diagnosticResults = new ArrayList<>();
        this.repairTasks = new ArrayList<>();
    }

    /**
     * Creates a DTO to transfer information between MVC layers.
     * @return RepairOrderDTO
     */
    public RepairOrderDTO createDTO() {
        return new RepairOrderDTO(id, date, customersProblemDescription, state, diagnosticResults, repairTasks, totalPrice, bike);
    }

    /**
     * Adds a diagnostic test result to the repair order
     * @param diagTestResult diagnostic test result
     */
    public void addDiagnosticResult(String diagTestResult) {
        this.diagnosticResults.add(diagTestResult);
    }

    /**
     * Adds a repair task to the repair order
     * @param task The repair task
     * @param cost The cost for the repair task
     */
    public void addRepairTask(String task, int cost) {
        this.repairTasks.add(new RepairTask(task, new Amount(cost)));
        this.totalPrice = this.totalPrice.add(new Amount(cost));
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    /**
     * Only used in testing to reset id counter
     */
    public static void resetIdCounter() {
        idCounter = 0;
    }
}
