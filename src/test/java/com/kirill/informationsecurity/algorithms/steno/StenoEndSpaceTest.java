package com.kirill.informationsecurity.algorithms.steno;

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
        StenoEndSpace.encode(source, phrase, dest, charset);
    }

    @Test
    public void readTest() throws IOException {
        String word = StenoEndSpace.decode(dest, charset);

        System.out.println(word);
    }
}
