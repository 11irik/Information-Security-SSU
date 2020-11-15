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
    public void writeTest() throws IOException {
        final String key = "ру";

        String enc = CryptoString.encode("русский", key, 1072, 33);
        System.out.println(enc);
        String dec = CryptoString.decode(enc, key, 1072, 33);
        System.out.println(dec);
    }

    @Test
    public void readTest() throws IOException {
        String word = StenoEndSpace.decode(dest, charset);

        System.out.println(word);
    }
}
