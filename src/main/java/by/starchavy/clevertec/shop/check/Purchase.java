package by.starchavy.clevertec.shop.check;

import by.starchavy.clevertec.shop.product.ProductInterface;

public class Purchase {
    private ProductInterface product;
    private int quantity;

    public Purchase(ProductInterface product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public ProductInterface getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
