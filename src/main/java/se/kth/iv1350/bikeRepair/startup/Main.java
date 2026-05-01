package se.kth.iv1350.bikeRepair.startup;

import se.kth.iv1350.bikeRepair.controller.Controller;
import se.kth.iv1350.bikeRepair.view.View;
import se.kth.iv1350.bikeRepair.integration.Printer;
import se.kth.iv1350.bikeRepair.integration.CustomerRegistry;
import se.kth.iv1350.bikeRepair.integration.RepairOrderRegistry;

public class Main {

    public static void main(String[] args) {
        RepairOrderRegistry repOrdReg = new RepairOrderRegistry();
        CustomerRegistry custReg = new CustomerRegistry();
        Controller contr = new Controller(custReg, repOrdReg);
        View view = new View(contr);

        view.start();
    }
}
