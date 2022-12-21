package by.starchavy.clevertec.shop.check;

import by.starchavy.clevertec.shop.discount.DiscountCard;
import java.util.List;

public interface CheckInterface {
    void addPurchase(Purchase purchase);

    void setProducts(List<Purchase> purchases);

    void setDiscountCard(DiscountCard discountCard);

    List<Purchase> getPurchases();

    Purchase getPurchasesById(int id);

    List<String> printCheck();

    void applyDiscountProduct();
}
