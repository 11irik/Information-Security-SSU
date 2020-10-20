package com.kirill.informationsecurity.algorithms.steno;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StenoTextSpacesTest {

    Path source = Paths.get("src/test/resources/steno/stenoTextSpaces.txt");
    Path phrase = Paths.get("src/test/resources/steno/phrase.txt");
    Path dest = Paths.get("src/test/resources/steno/textSpacesContainer.txt");
    Charset charset = Charset.forName("cp1251");


    @Test
    public void writeTest() throws IOException {
        StenoTextSpaces.encode(source, phrase, dest, charset);
    }

    @Test
    public void readTest() throws IOException {
        System.out.println(StenoTextSpaces.decode(dest, charset));
    }
}
