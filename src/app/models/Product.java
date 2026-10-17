package app.models;

import app.contracts.Saleable;

public abstract class Product implements Saleable {
    protected String name;
    protected float price;
    protected int quantity;

    public Product(String name, float price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public float getPrice() {
        return price;
    }

    public abstract float getDiscount();
}
