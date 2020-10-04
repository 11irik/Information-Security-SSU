package com.kirill.information_security.algorithms.steno.one;

import org.testng.annotations.Test;

import java.io.IOException;

public class StenoTest {

    @Test
    public void writeTest() throws IOException {
        Steno.encode("src/test/resources/steno/source.txt", "src/test/resources/steno/phrase.txt", "src/test/resources/steno/container.txt");
    }

    @Test
    public void readTest() throws IOException {
        String word = Steno.decode("src/test/resources/steno/container.txt");

        System.out.println(word);
    }
}
