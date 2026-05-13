package se.kth.iv1350.bikerepair.model.discountstrategy;

import se.kth.iv1350.bikerepair.model.Amount;

public class BikeShopBirthdayDiscount implements DiscountStrategy{

    @Override
    public Amount calculateDiscount(Amount total) {
        return total.add(new Amount(-200));
    }
}
