package se.kth.iv1350.bikerepair.model.discountstrategy;

import se.kth.iv1350.bikerepair.model.Amount;

public class BikeShopBirthdayDiscount implements DiscountStrategy{

    /**
     * Calculates total amount based on the bike shops birthday discount.
     * @param total The amount before discount
     * @return Amount
     */
    @Override
    public Amount calculateDiscount(Amount total) {
        return total.add(new Amount(-200));
    }
}
