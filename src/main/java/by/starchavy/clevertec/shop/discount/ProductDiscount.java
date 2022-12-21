package by.starchavy.clevertec.shop.discount;

import by.starchavy.clevertec.shop.product.Product;

public class ProductDiscount extends ProductDiscountDecorator {

    int discount;

    public ProductDiscount(Product product, int discount) {
        super(product);
        this.discount = discount;
    }

    public int getDiscount(){
        return discount;
    }

    @Override
    public int getId() {
        return this.product.getId();
    }

    @Override
    public String getName() {
        return this.product.getName()+" с дисконтом "+this.discount+ " %";
    }

    @Override
    public double getPrise() {
        return this.product.getPrise() - Math.ceil(this.product.getPrise() * this.discount) / 100.0;
    }
}
