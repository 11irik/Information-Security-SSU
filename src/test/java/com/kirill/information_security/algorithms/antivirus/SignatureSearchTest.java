package com.kirill.information_security.algorithms.antivirus;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class SignatureSearchTest {
    @Test
    public void searchTest() throws IOException {

        List<String> dirPaths = SignatureSearch.getDirPaths("src/test/resources/antivirus/signature.txt", 6, 22, "src/test/resources/antivirus/dir");

        dirPaths.forEach(System.out::println);
    }
}
