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
        int firstChar = (int)'А';
        int alphabetCount = 66;

        String enc = CryptoString.encode(text, key, firstChar, alphabetCount);
        System.out.println(enc);

        String dec = CryptoString.decode(enc, key, firstChar, alphabetCount);
        System.out.println(dec);

    }

    @Test
    public void writeTest() throws IOException {

        int a = 'А';
        for (int i = 1; i < 100; ++i) {
            System.out.print((char)a);
            System.out.print(' ');
            if (i % 20 == 0) {
                System.out.println();
            }
            a++;
        }

    }
}
