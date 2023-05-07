package org.example;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TransactionPricingTest {
    static TransactionPricing.Transaction[] t = {
            new TransactionPricing.Transaction(1, 100),
            new TransactionPricing.Transaction(2, 200),
            new TransactionPricing.Transaction(3, 100),
            new TransactionPricing.Transaction(3, 100),
            new TransactionPricing.Transaction(4, 100),
            new TransactionPricing.Transaction(4, 100),
            new TransactionPricing.Transaction(3, 100),
            new TransactionPricing.Transaction(4, 100),
            new TransactionPricing.Transaction(4, 100),
            new TransactionPricing.Transaction(5, 1),
            new TransactionPricing.Transaction(5, 2),
            new TransactionPricing.Transaction(5, 3),
            new TransactionPricing.Transaction(5, 42),
            new TransactionPricing.Transaction(5, 666),
    };

    static TransactionPricing.Customer[] c = {
            new TransactionPricing.Customer(1, "Peter", 11.1d, 1000d),
            new TransactionPricing.Customer(2, "Louis", 0.8d, 2000d),
            new TransactionPricing.Customer(3, "Brian", 2.3d, 500d),
            new TransactionPricing.Customer(4, "Stewie", 0.2d, 100d),
            new TransactionPricing.Customer(5, "Chris", 20.5d, null),
    };

    @Test
    void testPricing() {
        var prices = TransactionPricing.doPricing(Set.of(t), Set.of(c));
        assertEquals(14637.0, prices.get(c[4]));  // Chris
        assertEquals(80.0, prices.get(c[3]));  // Stewie
        assertEquals(500.0, prices.get(c[2]));  // Brian
        assertEquals(160.0, prices.get(c[1]));  // Louis
        assertEquals(1000.0, prices.get(c[0]));  // Peter
    }
}