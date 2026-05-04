package se.kth.iv1350.bikerepair.model;

import org.junit.jupiter.api.Test;
import se.kth.iv1350.bikerepair.model.Amount;

import static org.junit.jupiter.api.Assertions.*;

class AmountTest {

    @Test
    void testToString() {
        Amount a = new Amount(5);

        assertNotNull(a);
        assertEquals("5 SEK",a.toString(),"Conversion to string failed");
    }

    @Test
    void testAddPositive() {
        Amount a1 = new Amount(10);
        Amount a2 = new Amount(5);

        Amount a3 = a1.add(a2);

        assertNotNull(a3);
        assertEquals(new Amount(15).toString(), a3.toString(), "Adding positive amounts failed");
    }

    @Test
    void testAddNegative() {
        Amount a1 = new Amount(10);
        Amount a2 = new Amount(-5);

        Amount a3 = a1.add(a2);

        assertNotNull(a3);
        assertEquals(new Amount(5).toString(), a3.toString(), "Adding negative amounts failed");
    }

    @Test
    void testTotalNotGoingBelowZero() {
        Amount a1 = new Amount(10);
        Amount a2 = new Amount(-50);

        Amount a3 = a1.add(a2);

        assertNotNull(a3);
        assertEquals(new Amount(0).toString(), a3.toString(), "Amount was not equal to 0 which suggest it went below 0");
    }

    @Test
    void testImmutability() {
        Amount a1 = new Amount(10);
        Amount a2 = new Amount(5);

        a1.add(a2);

        assertEquals(new Amount(10).toString(), a1.toString(), "add function should not change existing object.");
    }
}