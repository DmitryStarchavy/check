package by.starchavy.clevertec.shop.out;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

public class PrintFile implements PrintInterface {
    String path;

    public PrintFile(String path) {
        this.path = path;
    }

    @Override
    public void print(List<String> checkOut) {

        OutputStream out;

        try {
            out = new FileOutputStream(path);
            PrintStream stream = new PrintStream(out);

            for (int i = 0; i < checkOut.size(); i++) {
                stream.print(checkOut.get(i));
            }
            stream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

