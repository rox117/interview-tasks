package org.example;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.*;

public class TransactionPricing {

    public static Map<Customer, Double> doPricing(
            Collection<Transaction> transactions,
            Collection<Customer> customers) {
        Map<Customer, Double> groupedTransactions = transactions.stream()
                .collect(groupingBy(transaction -> findById(customers, transaction.customerId), summingDouble(Transaction::getQuantity)));
        groupedTransactions.forEach((c, t) -> {
            double price = t * c.unitPrice;
            price = c.maxPrice != null && price > c.maxPrice ? c.maxPrice : price;
            groupedTransactions.put(c,price);
        });
        return groupedTransactions;
    }

    private static Customer findById(Collection<Customer> customers, int id){
        Optional<Customer> customer = customers.stream().filter(c -> c.customerId == id).findFirst();
        if (customer.isEmpty()){
            throw  new RuntimeException(String.format("Customer with id %s not found",id));
        }
        return customer.get();
    }

    public static class Transaction {
        private final int customerId;
        private final int quantity;

        public Transaction(int customerId, int quantity) {
            this.customerId = customerId;
            this.quantity = quantity;
        }

        public int getCustomerId() {
            return customerId;
        }

        public int getQuantity() {
            return quantity;
        }
    }

    public static class Customer {
        private final int customerId;
        private final String name;
        private final Double unitPrice;
        private final Double maxPrice;

        public Customer(int customerId, String name, Double unitPrice, Double maxPrice) {
            this.customerId = customerId;
            this.name = name;
            this.unitPrice = unitPrice;
            this.maxPrice = maxPrice;
        }

        public int getCustomerId() {
            return customerId;
        }

        public String getName() {
            return name;
        }

        public Double getUnitPrice() {
            return unitPrice;
        }

        public Double getMaxPrice() {
            return maxPrice;
        }
    }
}
