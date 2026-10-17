package app.main;

import app.models.EnGrosProduct;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {
        EnGrosProduct bread = new EnGrosProduct("Bread", 20, 30);
        System.out.println(bread);
        System.out.println("Discount per piece: " + bread.getDiscount());

        EnGrosProduct copy = bread.clone();
        System.out.println("Clone: " + copy);

        EnGrosProduct.addProduct(new EnGrosProduct("Flour", 16, 30));
        EnGrosProduct.addProduct(new EnGrosProduct("Sugar", 9, 16));
        EnGrosProduct.addProduct(new EnGrosProduct("Flour", 16, 20));
        EnGrosProduct.addProduct(new EnGrosProduct("Rice", 12.5f, 8));

        System.out.println("\nStock:");
        System.out.println(EnGrosProduct.printStock());
    }
}
