package se.kth.iv1350.bikerepair.startup;

import se.kth.iv1350.bikerepair.controller.Controller;
import se.kth.iv1350.bikerepair.integration.Printer;
import se.kth.iv1350.bikerepair.integration.RepairOrderLogger;
import se.kth.iv1350.bikerepair.view.RepairOrderView;
import se.kth.iv1350.bikerepair.view.View;
import se.kth.iv1350.bikerepair.integration.CustomerRegistry;
import se.kth.iv1350.bikerepair.integration.RepairOrderRegistry;

public class Main {

    /**
     * Initializes Registries, Controller and View then starts the view
     * @param args
     */
    public static void main(String[] args) {
        RepairOrderRegistry repOrdReg = new RepairOrderRegistry();
        CustomerRegistry custReg = new CustomerRegistry();
        Printer printer = new Printer();
        Controller contr = new Controller(custReg, repOrdReg, printer);
        View view = new View(contr);

        contr.addRepairOrderObserver(new RepairOrderView());
        contr.addRepairOrderObserver(new RepairOrderLogger());

        view.start();
    }
}
