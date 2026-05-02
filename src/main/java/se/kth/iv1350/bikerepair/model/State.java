package se.kth.iv1350.bikerepair.model;

/**
 * A state can only be one of six alternatives;
 * NEWLY_CREATED, READY_FOR_APPROVAL, REJECTED, ACCEPTED, COMPLETED, PAID
 */
public enum State {
    NEWLY_CREATED,
    READY_FOR_APPROVAL,
    REJECTED,
    ACCEPTED,
    COMPLETED,
    PAID
}
