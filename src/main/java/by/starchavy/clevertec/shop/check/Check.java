package by.starchavy.clevertec.shop.check;

import by.starchavy.clevertec.shop.discount.DiscountCard;
import by.starchavy.clevertec.shop.discount.ProductDiscount;
import by.starchavy.clevertec.shop.discount.ProductsWithDiscount;
import by.starchavy.clevertec.shop.product.Product;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Check implements CheckInterface {
    private List<Purchase> purchases;
    private DiscountCard discountCard;

    @Override
    public void addPurchase(Purchase purchase) {
        if (purchases == null) {
            purchases = new ArrayList<>();
        }
        purchases.add(purchase);
    }

    @Override
    public void setProducts(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    @Override
    public void setDiscountCard(DiscountCard discountCard) {
        this.discountCard = discountCard;
    }

    @Override
    public List<Purchase> getPurchases() {
        return purchases;
    }

    @Override
    public Purchase getPurchasesById(int id) {
        return purchases.get(id);
    }

    public DiscountCard getDiscountCard() {
        return discountCard;
    }


    @Override
    public void applyDiscountProduct() {
        Product product;
        int quantity;
        for (int i = 0; i < purchases.size(); i++) {

            product = (Product) purchases.get(i).getProduct();
            quantity = purchases.get(i).getQuantity();

            if ((ProductsWithDiscount.findProduct(product)) & (quantity) > 4) {

                purchases.set(i, new Purchase(new ProductDiscount(product, 10), quantity));
            }
        }
    }

    @Override
    public List<String> printCheck() {
        List<String> checkOut = new ArrayList<>();

        int quantity;
        double total = 0;
        double summ;

        Date date = new Date();

        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        String stringDate = DateFor.format(date);

        DateFor = new SimpleDateFormat("hh:mm:ss");
        String stringTime = DateFor.format(date);


        checkOut.add("                         КАССОВЫЙ ЧЕК\n");
        checkOut.add("                       Магазин clivertec\n");
        checkOut.add("                     Гомель, ул. Ланге, 17\n");
        checkOut.add("                     тел. +375-23-239-04-53\n");
        checkOut.add("                                                     Дата:" + stringDate + "\n");
        checkOut.add("                                                      Время:" + stringTime + "\n");
        checkOut.add("______________________________________________________________________\n");
        checkOut.add(" Кол Наименование                                    Цена        Сумма\n");

        for (int i = 0; i < purchases.size(); i++) {

            double prise = purchases.get(i).getProduct().getPrise();
            String name = purchases.get(i).getProduct().getName();
            quantity = purchases.get(i).getQuantity();

            summ = Math.ceil(prise * quantity * 100.0) / 100.0;

            total += summ;

            checkOut.add(String.format("%3d %-40s %12.2f %12.2f %n", quantity, " " + name, prise, summ));
        }

        total = Math.ceil(total * 100) / 100;
        checkOut.add("======================================================================\n");
        checkOut.add(String.format("%-57s %12.2f %n", "ИТОГО", total));

        if (discountCard != null) {
            double discount = Math.ceil(total * discountCard.getDiscount()) / 100.0;
            double totalWihtDiscount = Math.ceil((total + discount) * 100) / 100;

            checkOut.add(String.format("%-57s %12.2f %n", "Скидка по карте " + discountCard.getDiscount() + " %", discount));
            checkOut.add(String.format("%-57s %12.2f %n", "ИТОГО СО СКИДКОЙ", totalWihtDiscount));
        }

        return checkOut;
    }
}
