package by.starchavy.clevertec.shop;

import by.starchavy.clevertec.shop.check.Check;
import by.starchavy.clevertec.shop.dao.DiscountCardDaoArray;
import by.starchavy.clevertec.shop.dao.DiscountCardDaoInterface;
import by.starchavy.clevertec.shop.dao.ProductDaoArray;
import by.starchavy.clevertec.shop.dao.ProductDaoInterface;
import by.starchavy.clevertec.shop.discount.ProductsWithDiscount;
import by.starchavy.clevertec.shop.out.PrintConsole;
import by.starchavy.clevertec.shop.out.PrintFile;
import by.starchavy.clevertec.shop.out.PrintInterface;
import by.starchavy.clevertec.shop.parse.ParseArg;

public class CheckRunner {
    public static void main(String[] args)  {

        ProductDaoInterface products = new ProductDaoArray();
        DiscountCardDaoInterface discountCards = new DiscountCardDaoArray();
        ProductsWithDiscount.getProductsWithDiscount();

        ParseArg.parseArg(args, products, discountCards);

        Check check = new Check();
        check.setProducts(ParseArg.getPurchases());
        check.setDiscountCard(ParseArg.getDiscountCard());
        check.printCheck();
        check.applyDiscountProduct();

        PrintInterface printOut;
        if ((ParseArg.getOutput() == null) || (ParseArg.getOutput().equals("console"))) {
            printOut = new PrintConsole();
        } else {
            printOut = new PrintFile(ParseArg.getOutput());
        }
        printOut.print(check.printCheck());
    }
}