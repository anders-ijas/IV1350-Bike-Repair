package se.kth.iv1350.bikerepair.model.discountstrategy;

import se.kth.iv1350.bikerepair.model.Amount;

public class NoDiscount implements DiscountStrategy {

    @Override
    /**
     * Calculates total discount in the case of no discount
     */
    public Amount calculateDiscount(Amount total) {
        return total;
    }
}
