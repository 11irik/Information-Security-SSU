package com.kirill.informationsecurity.algorithms.crypto;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CryptoTest {

    @Test
    public void cryptoTestOne() throws IOException, IllegalAccessException {

        String text = "Арбуз";
        String key = "Ключ";

        String enc = Crypto.encrypt(text, key);
        String dec = Crypto.decrypt(enc, key);
        Assert.assertEquals(dec, text);
    }

    @Test
    public void cryptoTestTwo() throws IOException {
        String key = "Ключ";
        Path dirPath = Paths.get("./src/test/resources/crypto/dir").toAbsolutePath();
        Path tempFile = Paths.get("./src/test/resources/crypto/temp.kl").toAbsolutePath();
        Path targetDir = Paths.get("./src/test/resources/crypto/testDir").toAbsolutePath();

        Crypto.encryptCatalog(dirPath, key, tempFile);
        Crypto.decryptCatalog(tempFile, key, targetDir);
    }

}
