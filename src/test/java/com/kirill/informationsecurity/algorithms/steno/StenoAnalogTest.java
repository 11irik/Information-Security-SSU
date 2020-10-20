package com.kirill.informationsecurity.algorithms.steno;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;


public class StenoAnalogTest {

    Path source = Paths.get("src/test/resources/steno/stenoAnalog.txt");
    Path phrase = Paths.get("src/test/resources/steno/phrase.txt");
    Path dest = Paths.get("src/test/resources/steno/analogContainer.txt");
    Charset charset = Charset.forName("cp1251");

    private static final BiMap<Character, Character> analogs = HashBiMap.create();
    {
        analogs.put('у','y');
        analogs.put('е','e');
        analogs.put('х','x');
        analogs.put('а','a');
        analogs.put('р','p');
        analogs.put('о','o');
        analogs.put('с','c');
        analogs.put('К','K');
        analogs.put('Е','E');
        analogs.put('Н','H');
        analogs.put('Х','X');
        analogs.put('В','B');
        analogs.put('А','A');
        analogs.put('Р','P');
        analogs.put('О','O');
        analogs.put('С','C');
        analogs.put('М','M');
        analogs.put('Т','T');
    }

    @Test
    public void writeTest() throws IOException {
        StenoAnalog.encode(source, phrase, dest, charset, analogs);
    }

    @Test
    public void readTest() throws IOException {
        System.out.println(StenoAnalog.decode(dest, charset, analogs));
    }
}
