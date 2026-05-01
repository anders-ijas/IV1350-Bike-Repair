package se.kth.iv1350.bikeRepair.controller;

import se.kth.iv1350.bikeRepair.integration.CustomerRegistry;
import se.kth.iv1350.bikeRepair.integration.RepairOrderRegistry;

/**
 *  The controller class that interacts with the view
 *  and model layer.
 */
public class Controller {
    private CustomerRegistry custReg;
    private RepairOrderRegistry repOrdReg;

    /**
     * Sets the reference to both CustomerRegistry and RepairOrderRegistry
     */
    public Controller(CustomerRegistry custReg, RepairOrderRegistry repOrdReg) {
        this.custReg = custReg;
        this.repOrdReg = repOrdReg;
    }
}
