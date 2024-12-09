package model;

public class Order {
    private Customer customer;
    private Product product;
    private int amount;
    
    public Order(Customer customer, Product product, int amount) {
        this.customer = customer;
        this.product = product;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Order [customer=" + customer + ", product=" + product + ", amount=" + amount + "]";
    }
}
