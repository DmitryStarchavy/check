package by.starchavy.clevertec.shop.out;

import java.util.List;

public class PrintConsole implements PrintInterface{

    @Override
    public void print(List<String> checkOut) {
        for (int i = 0; i < checkOut.size(); i++)
            System.out.print(checkOut.get(i));
        System.out.println();
    }
}
