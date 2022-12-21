package by.starchavy.clevertec.shop.discount;

import by.starchavy.clevertec.shop.dao.ProductDaoArray;
import by.starchavy.clevertec.shop.product.Product;
import java.util.ArrayList;
import java.util.List;

public abstract class ProductsWithDiscount {
    static private List<Product> productsWithDiscount;

    public static List<Product> getProductsWithDiscount() {
        if (productsWithDiscount == null) {
            productsWithDiscount = new ArrayList<>();
            ProductDaoArray products = new ProductDaoArray();

            addProduct(products.getProductById(2));
            addProduct(products.getProductById(4));
            addProduct(products.getProductById(7));
            addProduct(products.getProductById(9));
            addProduct(products.getProductById(15));
            addProduct(products.getProductById(18));
            addProduct(products.getProductById(24));
            addProduct(products.getProductById(27));
            addProduct(products.getProductById(29));
        }
        return productsWithDiscount;
    }

    public static void addProduct(Product product) {
        productsWithDiscount.add(product);
    }

    public static boolean findProduct(Product product) {
        return productsWithDiscount.contains(product);
    }
}
