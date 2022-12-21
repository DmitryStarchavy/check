package by.starchavy.clevertec.shop.parse;

import by.starchavy.clevertec.shop.dao.DiscountCardDaoArray;
import by.starchavy.clevertec.shop.dao.DiscountCardDaoInterface;
import by.starchavy.clevertec.shop.dao.ProductDaoArray;
import by.starchavy.clevertec.shop.dao.ProductDaoInterface;
import by.starchavy.clevertec.shop.discount.DiscountCard;
import by.starchavy.clevertec.shop.discount.ProductsWithDiscount;
import by.starchavy.clevertec.shop.product.Product;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParseArgTest {
    ProductDaoInterface productDao = new ProductDaoArray();
    List<Product> products = productDao.getProducts();
    DiscountCardDaoInterface discountCardDao = new DiscountCardDaoArray();
    List<DiscountCard> discountCards = discountCardDao.getDiscountCards();

    @Test
    void parseFile() {

        ProductsWithDiscount.getProductsWithDiscount();
        String args = "";
        int[] argsInt = new int[2];

        int discontCardId = discountCards.get(0).getId();

        int sizeProducts = products.size();
        argsInt[0] = products.get(0).getId();
        argsInt[1] = products.get(sizeProducts - 1).getId();

        args += argsInt[0] + "-3 ";
        args += argsInt[1] + "-10 ";
        args += "card-" + discontCardId;
        args += " output-console";

        OutputStream out;
        String path = "d:/CheckTest.txt";
        try {
            out = new FileOutputStream(path);
            PrintStream stream = new PrintStream(out);
            stream.println(args);
            stream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        ParseArg.parseFile(path, productDao, discountCardDao);
        assertEquals(ParseArg.getPurchases().get(0).getProduct().getId(), argsInt[0]);
        assertEquals(ParseArg.getPurchases().get(0).getQuantity(), 3);

        assertEquals(ParseArg.getPurchases().get(1).getProduct().getId(), argsInt[1]);
        assertEquals(ParseArg.getPurchases().get(1).getQuantity(), 10);

        assertEquals(ParseArg.getDiscountCard().getId(), discontCardId);
        assertEquals(ParseArg.getDiscountCard().getDiscount(), discountCards.get(0).getDiscount());

        assertEquals(ParseArg.getOutput(), "console");
        try {
            File file = new File(path);
            file.delete();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void parseArg() {
        ProductsWithDiscount.getProductsWithDiscount();
        String[] args = new String[4];
        int[] argsInt = new int[2];

        int discontCardId = discountCards.get(0).getId();

        int sizeProducts = products.size();
        argsInt[0] = products.get(0).getId();
        argsInt[1] = products.get(sizeProducts - 1).getId();

        args[0] = argsInt[0] + "-3";
        args[1] = argsInt[1] + "-10";
        args[2] = "card-" + discontCardId;
        args[3] = "output-console";

        ParseArg.parseArg(args, productDao, discountCardDao);

        assertEquals(ParseArg.getPurchases().get(0).getProduct().getId(), argsInt[0]);
        assertEquals(ParseArg.getPurchases().get(0).getQuantity(), 3);

        assertEquals(ParseArg.getPurchases().get(1).getProduct().getId(), argsInt[1]);
        assertEquals(ParseArg.getPurchases().get(1).getQuantity(), 10);

        assertEquals(ParseArg.getDiscountCard().getId(), discontCardId);
        assertEquals(ParseArg.getDiscountCard().getDiscount(), discountCards.get(0).getDiscount());

        assertEquals(ParseArg.getOutput(), "console");
    }
}