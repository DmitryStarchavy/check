package by.starchavy.clevertec.shop.dao;

import by.starchavy.clevertec.shop.product.Product;

import java.util.List;

public interface ProductDaoInterface {

    List<Product> getProducts();

    Product getProductById(int id);
}
