package com.kirill.informationsecurity.algorithms.steno;

import com.kirill.informationsecurity.algorithms.steno.Steno;
import org.testng.annotations.Test;

import java.io.IOException;

public class StenoTest {

    @Test
    public void writeTest() throws IOException {
        Steno.encode("src/test/resources/steno/source.txt", "src/test/resources/steno/phrase.txt", "src/test/resources/steno/container.txt");
    }

    @Test
    public void readTest() throws IOException {
        String word = Steno.decode("src/test/resources/steno/container.txt", "cp1251");

        System.out.println(word);
    }
}
