package se.kth.iv1350.bikerepair.model.discountstrategy;

import se.kth.iv1350.bikerepair.model.Amount;

public interface DiscountStrategy {

    /**
     * Calculates the price after discount.
     * @param total The amount before discount
     * @return Amount
     */
     Amount calculateDiscount(Amount total);
}
