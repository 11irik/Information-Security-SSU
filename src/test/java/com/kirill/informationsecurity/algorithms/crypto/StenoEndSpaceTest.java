package com.kirill.informationsecurity.algorithms.crypto;

import com.kirill.informationsecurity.algorithms.steno.StenoEndSpace;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StenoEndSpaceTest {

    Path source = Paths.get("src/test/resources/steno/sourceEndSpace.txt");
    Path phrase = Paths.get("src/test/resources/steno/phrase.txt");
    Path dest = Paths.get("src/test/resources/steno/endSpaceContainer.txt");
    Charset charset = Charset.forName("cp1251");


    @Test
    public void keks() throws IOException {

        String text = "Арбуз";
        String key = "Ключ";
        int firstChar = (int)'!';
        int alphabetCount = 1070;

        String enc = CryptoString.encode(text, key, firstChar, alphabetCount);
        System.out.println(enc);

        String dec = CryptoString.decode(enc, key, firstChar, alphabetCount);
        System.out.println(dec);

    }

    @Test
    public void writeTest() throws IOException {

        int a = 0;
        int j = 0;
        for (int i = 0; i < 10000; ++i) {
            System.out.print((char)a);
            System.out.print(' ');
            if (i % 20 == 0) {
                System.out.println();
            }
            if (a == '!') {
                System.out.println("FIRST CHAR IS: " + a);
                j = a;
            }
            if (a == 'я') {
                System.out.println("LAST IS:" + a);
                break;
            }
            a++;
        }

        System.out.println(Character.toString((char) (j+3)));
    }
}
