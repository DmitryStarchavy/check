package by.starchavy.clevertec.shop.parse;

import by.starchavy.clevertec.shop.check.Purchase;
import by.starchavy.clevertec.shop.dao.DiscountCardDaoInterface;
import by.starchavy.clevertec.shop.dao.ProductDaoInterface;
import by.starchavy.clevertec.shop.discount.DiscountCard;
import by.starchavy.clevertec.shop.product.Product;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParseArg {
    private static List<Purchase> purchases;
    private static DiscountCard discountCard;
    private static String output;

    private static void parseCard(String str, DiscountCardDaoInterface discountCards) {
        int idCard;
        try {
            idCard = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            idCard = 0;
            System.out.println("Номер дисконтной карты должно быть положительное число.");
        }
        discountCard = discountCards.getDiscountCardById(idCard);
        if (discountCard == null) {
            System.out.println("Дисконтная карта не найдена. Скидки не будет.");
        }
    }

    private static void parseProduct(String[] str, int numArg, ProductDaoInterface products) {
        int quantity = 0;
        int idProduct;
        Product product = null;

        try {
            idProduct = Integer.parseInt(str[0]);
            quantity = Integer.parseInt(str[1]);
            product = products.getProductById(idProduct);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Ошибка в " + numArg + "-ом аргументе.");
        }

        if (product == null) {
            System.out.println("Продукт в " + numArg + "-ом аргументе не найден, В чеке учтён не будет.");
        } else if (quantity <= 0) {
            System.out.println("Количество в " + numArg + "-ом аргументе должно быть больше ноля.");
        } else purchases.add(new Purchase(product, quantity));
    }

    public static void parseFile(String filename, ProductDaoInterface products, DiscountCardDaoInterface discountCards) {
        List<String> lines = new ArrayList<>();
        Path path = Paths.get(filename);

        try (Stream<String> lineStream = Files.lines(path)) {
            lines = lineStream.collect(Collectors.toList());
        } catch (IOException ignored) {
            System.out.println("Ошибка в файле: " + filename);
        }

        String[] str = lines.get(0).split(" ");
        parseArg(str, products, discountCards);
    }

    public static void parseArg(String[] args, ProductDaoInterface products, DiscountCardDaoInterface discountCards) {
        purchases = new ArrayList<>();
        String[] str;

        for (int i = 0; i < args.length; i++) {

            str = args[i].split("-");

            if (str[0].equals("card")) {
                parseCard(str[1], discountCards);
            } else if (str[0].equals("file")) {   //file-D:\check.txt
                parseFile(str[1], products, discountCards);
            } else if (str[0].equals("output")) {
                output=str[1];
            } else {
                parseProduct(str, i, products);
            }
        }

    }

    public static String getOutput() {
        return output;
    }

    public static List<Purchase> getPurchases() {
        return purchases;
    }

    public static DiscountCard getDiscountCard() {
        return discountCard;
    }
}
