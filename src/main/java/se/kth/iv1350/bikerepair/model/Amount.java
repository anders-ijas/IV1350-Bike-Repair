package se.kth.iv1350.bikerepair.model;

public final class Amount {
    private final int value;

    public Amount(int value) {
        this.value = value;
    }

    /**
     * Adds two values together, however total value should never go below 0
     * @param other The other amount to add or subtract
     * @return Amount
     */
    public Amount add(Amount other) {
        Amount newAmount = new Amount(this.value + other.value);
        if (newAmount.value < 0) {
            return new Amount(0);
        }
        return newAmount;
    }

    /**
     * Returns the value as a string eg. this.value = 50 becomes "50 SEK"
     * @return String
     */
    @Override
    public String toString() {
        return Integer.toString(value) + " SEK";
    }
}
