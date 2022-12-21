package by.starchavy.clevertec.shop.discount;

import by.starchavy.clevertec.shop.product.Product;
import by.starchavy.clevertec.shop.product.ProductInterface;

abstract class ProductDiscountDecorator implements ProductInterface {
    Product product;

    public ProductDiscountDecorator(Product product) {
        this.product = product;
    }
}
