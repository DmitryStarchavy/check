package by.starchavy.clevertec.shop.check;

import by.starchavy.clevertec.shop.discount.DiscountCard;
import by.starchavy.clevertec.shop.discount.ProductsWithDiscount;
import by.starchavy.clevertec.shop.product.Product;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CheckTest {
    @Test
    void addPurchase() {
        Check check = new Check();
        Product product = Product.bilder()
                .id(33)
                .name("сало")
                .prise(10.50)
                .bild();
        Purchase purchase = new Purchase(product, 5);
        check.addPurchase(purchase);

        assertEquals(check.getPurchasesById(0).getProduct().getId(), 33);
        assertEquals(check.getPurchasesById(0).getProduct().getName(), "сало");
        assertEquals(check.getPurchasesById(0).getProduct().getPrise(), 10.50);
    }

    @Test
    void addProducts() {
        Check check = new Check();
        List<Purchase> purchases = new ArrayList<>();
        Product product;

        DiscountCard discountCard = new DiscountCard(12, 20);

        product = Product.bilder().id(15).name("сало").prise(10.50).bild();
        purchases.add(new Purchase(product, 5));

        product = Product.bilder().id(25).name("хлеб").prise(1.25).bild();
        purchases.add(new Purchase(product, 25));

        product = Product.bilder().id(35).name("сыр").manufacturer("белакт").prise(15.5).bild();
        purchases.add(new Purchase(product, 35));

        check.setProducts(purchases);
        check.setDiscountCard(discountCard);

        assertEquals(check.getPurchasesById(0).getProduct().getId(), 15);
        assertEquals(check.getPurchasesById(0).getProduct().getName(), "сало");
        assertEquals(check.getPurchasesById(0).getProduct().getPrise(), 10.50);

        assertEquals(check.getPurchasesById(2).getProduct().getId(), 35);
        assertEquals(check.getPurchasesById(2).getProduct().getName(), "сыр");
        assertEquals(check.getPurchasesById(2).getProduct().getPrise(), 15.5);

        assertEquals(check.getDiscountCard().getId(), 12);
        assertEquals(check.getDiscountCard().getDiscount(), 20);

        List<Purchase> purchasesGet;
        purchasesGet = check.getPurchases();

        assertEquals(check.getPurchasesById(2).getProduct(), purchasesGet.get(2).getProduct());
    }


    @Test
    void applyDiscountProduct() {
        Product productDiscount = ProductsWithDiscount.getProductsWithDiscount().get(0);
        Check check = new Check();

        Product product = Product.bilder()
                .id(productDiscount.getId())
                .name(productDiscount.getName())
                .prise(productDiscount.getPrise())
                .bild();

        Purchase purchase6 = new Purchase(product, 6);
        Purchase purchase3 = new Purchase(product, 3);

        check.addPurchase(purchase6);
        check.addPurchase(purchase3);

        double priseOld3 = purchase3.getProduct().getPrise();
        double priseOld6 = purchase6.getProduct().getPrise();

        check.applyDiscountProduct();

        double priseNew6 = priseOld6 - Math.ceil(priseOld6 * 10) / 100.0;

        assertEquals(check.getPurchasesById(0).getProduct().getPrise(), priseNew6);
        assertEquals(check.getPurchasesById(1).getProduct().getPrise(), priseOld3);
    }


    @Test
    void printCheck() {
        Check check = new Check();
        List<Purchase> purchases = new ArrayList<>();
        Product product;
        int quantity;
        double summ;
        double total = 0;

        DiscountCard discountCard = new DiscountCard(12, 20);

        product = Product.bilder().id(15).name("сало").prise(10.50).bild();
        purchases.add(new Purchase(product, 5));

        product = Product.bilder().id(25).name("хлеб").prise(1.25).bild();
        purchases.add(new Purchase(product, 25));

        product = Product.bilder().id(35).name("сыр").manufacturer("белакт").prise(15.5).bild();
        purchases.add(new Purchase(product, 35));

        check.setProducts(purchases);
        check.setDiscountCard(discountCard);

        List<String> str = check.printCheck();

        assertEquals(str.get(0).contains("КАССОВЫЙ ЧЕК"), true);
        assertEquals(str.get(3).contains("тел."), true);
        assertEquals(str.get(4).contains("Дата:"), true);
        assertEquals(str.get(5).contains("Время:"), true);
        assertEquals(str.get(6).contains("______________________________________________________________________"), true);
        assertEquals(str.get(7).contains(" Кол Наименование                                    Цена        Сумма"), true);

        int number;
        for (number = 0; number < purchases.size(); number++) {
            double prise = purchases.get(number).getProduct().getPrise();
            String name = purchases.get(number).getProduct().getName();
            quantity = purchases.get(number).getQuantity();
            summ = Math.ceil(prise * quantity * 100.0) / 100.0;
            total += summ;

            assertEquals(str.get(number + 8).contains(String.valueOf(quantity)), true);

            assertEquals(str.get(number + 8).contains(name), true);

            assertEquals(str.get(number + 8).contains(String.format("%10.2f", prise)), true);

            assertEquals(str.get(number + 8).contains(String.format("%10.2f", summ)), true);

        }

        assertEquals(str.get(number + 8).contains("======================================================================"), true);

        total = Math.ceil(total * 100) / 100;

        assertEquals(str.get(number + 9).contains("ИТОГО"), true);
        assertEquals(str.get(number + 9).contains(String.format("%10.2f", total)), true);

        if (discountCard != null) {
            double discount = Math.ceil(total * discountCard.getDiscount()) / 100.0;
            double totalWihtDiscount = Math.ceil((total + discount) * 100) / 100;

            assertEquals(str.get(number + 10).contains("Скидка по карте"), true);
            assertEquals(str.get(number + 10).contains(String.format("%10.2f", discount)), true);

            assertEquals(str.get(number + 11).contains("ИТОГО СО СКИДКОЙ"), true);
            assertEquals(str.get(number + 11).contains(String.format("%10.2f", totalWihtDiscount)), true);

            System.setOut(null);
        }
    }
}