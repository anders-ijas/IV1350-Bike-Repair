package se.kth.iv1350.bikerepair.model.discountstrategy;

import se.kth.iv1350.bikerepair.model.Amount;

public class NoDiscount implements DiscountStrategy {

    @Override
    public Amount calculateDiscount(Amount total) {
        return total;
    }
}
