package com.kirill.informationsecurity.algorithms.crypto;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

public class CryptoString {
    private static final int firstChar = 0;
    private static  final int alphabetCount = 2000;
    private static final String filespacer = "SPACER:::FILE:::SPACER\n";
    private static final String spacer = "SPACER:::SPACER:::SPACER\n";

    public static String encode(String text, String key, int offset, int count) {
        String encrypt = "";
        final int keyLen = key.length();
        for (int i = 0, len = text.length(); i < len; i++) {
            encrypt += (char) (((text.charAt(i) + key.charAt(i % keyLen) - 2 * offset) % count) + offset);
        }
        return encrypt;
    }

    public static String decode(String text, String key, int offset, int count) {
        String decrypt = "";
        final int keyLen = key.length();
        for (int i = 0, len = text.length(); i < len; i++) {
            decrypt += (char) (((text.charAt(i) - key.charAt(i % keyLen) + count) % count) + offset);
        }
        return decrypt;
    }

    public static void encodeCatalog(Path path, String key, Path tmpPath) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            Files.walk(path)
                    .filter(Files::isDirectory)
                    .forEach(f -> {
                        stringBuilder
                                .append(path.relativize(f.toAbsolutePath()))
                                .append('\n');
                    });

            stringBuilder.append(filespacer);

            Files.walk(path)
                    .filter(Files::isRegularFile)
                    .forEach(f -> {
                        try {
                            stringBuilder
                                    .append(spacer)
                                    .append(path.relativize(f.toAbsolutePath())).append('\n')
                                    .append(new String(Files.readAllBytes(f), StandardCharsets.UTF_8)).append('\n');
                        } catch (IOException exception) {
                            exception.printStackTrace();
                        }
                    });


            String pths = stringBuilder.toString();

//            pths = encode(pths, key, firstChar, alphabetCount);
            Files.write(tmpPath, Collections.singleton(pths));
        } catch (Exception ignored) {
        }
    }

    public static void decodeCatalog(Path tmpPath, String key) {
        try {
            String str = new String(Files.readAllBytes(tmpPath), StandardCharsets.UTF_8);
//            str = decode(str, key, firstChar, alphabetCount);
            for (String s1 : str.split("\n")) {
                Files.createDirectories(Paths.get("./dirsTest", s1));
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
