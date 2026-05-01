package se.kth.iv1350.bikeRepair.view;

import se.kth.iv1350.bikeRepair.controller.Controller;

/**
 * An abstraction of the View layer.
 *  Simulates the calls to the Controller.
 */
public class View {
    private Controller contr;

    /**
     * Sets the reference to the Controller
     */
    public View(Controller contr) {
        this.contr = contr;
    }

    public void start() {
    }
}
