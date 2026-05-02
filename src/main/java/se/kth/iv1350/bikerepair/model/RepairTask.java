package se.kth.iv1350.bikerepair.model;

/**
 * A repair task has a task description and a cost to repair
 */
public final class RepairTask {
    private final String taskDescription;
    private final int cost;

    /**
     * Creates a new instance.
     * @param taskDescription Task description
     * @param cost Cost of repair
     */
    public RepairTask(String taskDescription, int cost) {
        this.taskDescription = taskDescription;
        this.cost = cost;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public int getCost() {
        return cost;
    }
}
