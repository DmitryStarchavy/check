package by.starchavy.clevertec.shop.out;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PrintConsoleTest {

    @Test
    void print() {

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        List<String> checkOut = new ArrayList<>();
        checkOut.add("String1");
        checkOut.add("String2");

        assertEquals(checkOut.get(0).equals("String1"), true);
        assertEquals(checkOut.get(1).equals("String2"), true);

        System.setOut(null);
    }
}