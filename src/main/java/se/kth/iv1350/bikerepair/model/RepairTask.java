package se.kth.iv1350.bikerepair.model;

/**
 * A repair task has a task description and a cost to repair
 */
public final class RepairTask {
    private final String taskDescription;
    private final Amount cost;

    /**
     * Creates a new instance.
     * @param taskDescription Task description
     * @param cost Cost of repair
     */
    public RepairTask(String taskDescription, Amount cost) {
        this.taskDescription = taskDescription;
        this.cost = cost;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public Amount getCost() {
        return cost;
    }
}
