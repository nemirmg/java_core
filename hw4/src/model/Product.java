package model;

public class Product {
    private String name;
    private double cost;
    
    public Product(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Товар [наименование='" + name + "', цена=" + cost + "]";
    }
}
