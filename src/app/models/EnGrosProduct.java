package app.models;

import java.util.LinkedHashMap;
import java.util.Map;

public class EnGrosProduct extends Product implements Cloneable {
    private static final Map<String, EnGrosProduct> stock = new LinkedHashMap<>();

    private float discount;

    public EnGrosProduct(String name, float price, int quantity) {
        super(name, price, quantity);
        this.discount = computeDiscount(quantity);
    }

    private static float computeDiscount(int quantity) {
        if (quantity >= 20) {
            return 0.1f;
        }
        if (quantity >= 10) {
            return 0.05f;
        }
        return 0f;
    }

    private float discountRate() {
        return discount;
    }

    @Override
    public float getDiscount() {
        return price * discountRate();
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getValueBeforeDiscount() {
        return price * quantity;
    }

    public float getValueAfterDiscount() {
        return getValueBeforeDiscount() * (1 - discountRate());
    }

    @Override
    public EnGrosProduct clone() throws CloneNotSupportedException {
        return (EnGrosProduct) super.clone();
    }

    @Override
    public String toString() {
        return "> " + name + " " + price + " " + quantity + " " + Math.round(discountRate() * 100) + "% "
                + getValueBeforeDiscount() + " " + getValueAfterDiscount();
    }

    public static void addProduct(EnGrosProduct product) {
        if (product == null) {
            return;
        }
        String key = product.name + "|" + product.price;
        EnGrosProduct existing = stock.get(key);
        if (existing == null) {
            try {
                stock.put(key, product.clone());
            } catch (CloneNotSupportedException e) {
                throw new IllegalStateException(e);
            }
        } else {
            existing.quantity += product.quantity;
            existing.discount = computeDiscount(existing.quantity);
        }
    }

    public static String printStock() {
        StringBuilder sb = new StringBuilder();
        float total = 0;
        for (EnGrosProduct product : stock.values()) {
            sb.append(product).append("\n");
            total += product.getValueBeforeDiscount();
        }
        sb.append("\nTotal value of the stock without discount: ").append(total);
        return sb.toString();
    }
}
