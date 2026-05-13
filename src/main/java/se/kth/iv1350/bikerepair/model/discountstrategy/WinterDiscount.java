package se.kth.iv1350.bikerepair.model.discountstrategy;

import se.kth.iv1350.bikerepair.model.Amount;

public class WinterDiscount implements DiscountStrategy{

    /**
     * Calculates total amount based on winter discount
     * @param total The amount before discount
     * @return Amount
     */
    @Override
    public Amount calculateDiscount(Amount total) {
        return total.add(new Amount(-100));
    }
}
