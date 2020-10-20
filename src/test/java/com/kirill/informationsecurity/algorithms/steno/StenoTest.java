package com.kirill.informationsecurity.algorithms.steno;

import com.kirill.informationsecurity.algorithms.steno.Steno;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StenoTest {

    Path source = Paths.get("src/test/resources/steno/source.txt");
    Path phrase = Paths.get("src/test/resources/steno/phrase.txt");
    Path dest = Paths.get("src/test/resources/steno/container.txt");
    Charset charset = Charset.forName("cp1251");


    @Test
    public void writeTest() throws IOException {
        Steno.encode(source, phrase, dest, charset);
    }

    @Test
    public void readTest() throws IOException {
        String word = Steno.decode(dest, charset);

        System.out.println(word);
    }
}
