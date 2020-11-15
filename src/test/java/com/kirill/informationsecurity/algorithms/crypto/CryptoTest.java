package com.kirill.informationsecurity.algorithms.crypto;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CryptoTest {

    Path source = Paths.get("src/test/resources/steno/sourceEndSpace.txt");
    Path phrase = Paths.get("src/test/resources/steno/phrase.txt");
    Path dest = Paths.get("src/test/resources/steno/endSpaceContainer.txt");
    Charset charset = Charset.forName("cp1251");


    @Test
    public void cryptoTestOne() throws IOException {

        String text = "Арбуз";
        String key = "Ключ";
        int firstChar = (int)'!';
        int alphabetCount = 1070;


        String enc = Crypto.encrypt(text, key, firstChar, alphabetCount);
        String dec = Crypto.decrypt(enc, key, firstChar, alphabetCount);
        Assert.assertEquals(dec, text);
    }

    @Test
    public void cryptoTestTwo() throws IOException {
        String key = "Ключ";
        Path p = Paths.get("/Users/klukashin/IdeaProjects/university/Information-Security-SSU/./src/test/resources/antivirus/dir");
        Path tempFile = Paths.get("./temp");

        Crypto.encryptCatalog(p, key, tempFile);
        Crypto.decryptCatalog(tempFile, key);
    }

}
