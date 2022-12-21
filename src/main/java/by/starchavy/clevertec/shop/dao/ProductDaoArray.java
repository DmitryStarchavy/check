package by.starchavy.clevertec.shop.dao;

import by.starchavy.clevertec.shop.product.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoArray implements ProductDaoInterface {
    private List<Product> products;

    {
        if (products == null) {
            products = new ArrayList<>();

            products.add(Product.bilder().id(1).name("сало").prise(10.50).bild());
            products.add(Product.bilder().id(2).name("хлеб").prise(1.25).bild());
            products.add(Product.bilder().id(3).name("сыр").manufacturer("белакт").prise(15.5).bild());
            products.add(Product.bilder().id(4).name("колбаса московская").prise(21.50).bild());
            products.add(Product.bilder().id(5).name("мандарин").certificate("СУ 1251 35111").prise(4.60).bild());
            products.add(Product.bilder().id(6).name("киви").prise(4.12).bild());
            products.add(Product.bilder().id(7).name("молоко").prise(1.20).bild());
            products.add(Product.bilder().id(8).name("кефир").manufacturer("МинскМолоко").prise(1.10).bild());
            products.add(Product.bilder().id(9).name("печенье").prise(1.33).bild());
            products.add(Product.bilder().id(10).name("сухари").certificate("МУ 45234 344").prise(0.75).bild());
            products.add(Product.bilder().id(11).name("сок томатный").prise(2.55).bild());
            products.add(Product.bilder().id(12).name("сок мультифруктовый").prise(2.55).bild());
            products.add(Product.bilder().id(13).name("кофе в зернах").prise(40.2).bild());
            products.add(Product.bilder().id(14).name("мука рисовая").manufacturer("РФ").prise(2.50).bild());
            products.add(Product.bilder().id(15).name("макаронные изделия").prise(1.70).bild());
            products.add(Product.bilder().id(16).name("гречка").prise(1.57).bild());
            products.add(Product.bilder().id(17).name("масло подсолнечное").prise(2.55).bild());
            products.add(Product.bilder().id(18).name("майонез провансаль").prise(1.45).bild());
            products.add(Product.bilder().id(19).name("консерва рыбная").certificate("ММ 2353 5353").prise(2.67).bild());
            products.add(Product.bilder().id(20).name("кукуруза консервированная").prise(4.50).bild());
            products.add(Product.bilder().id(21).name("шоколад нестле").prise(3.19).bild());
            products.add(Product.bilder().id(22).name("яйца куриные").prise(3.10).bild());
            products.add(Product.bilder().id(23).name("творог белакт").manufacturer("белакт").prise(2.50).bild());
            products.add(Product.bilder().id(24).name("йогурт греческий").prise(2.90).bild());
            products.add(Product.bilder().id(25).name("мороженное 28 копеек").prise(1.55).bild());
            products.add(Product.bilder().id(26).name("масло").manufacturer("белакт").prise(3.12).bild());
            products.add(Product.bilder().id(27).name("чай зелёный").prise(3.25).bild());
            products.add(Product.bilder().id(28).name("квас хатни").prise(2.40).bild());
            products.add(Product.bilder().id(29).name("креветки морские").prise(21.50).bild());
            products.add(Product.bilder().id(30).name("мясо мидии").manufacturer("Тайвань").prise(14.40).bild());
        }
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public Product getProductById(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (id == products.get(i).getId())
                return products.get(i);
        }
        return null;
    }

}
