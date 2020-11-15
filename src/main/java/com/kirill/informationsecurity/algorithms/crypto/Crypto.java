package com.kirill.informationsecurity.algorithms.crypto;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.MissingFormatArgumentException;

public class Crypto {
    private static final int firstChar = 0;
    private static final int alphabetCount = 2000;
    private static final String filespacer = "SPACER:::FILE:::SPACER";
    private static final String spacer = "SPACER:::SPACER:::SPACER";

    public static String encrypt(String text, String key, int offset, int count) {
        String encrypt = "";
        int keyLen = key.length();
        if (keyLen == 0) {
            throw new IllegalArgumentException("Key size should be 1 or more");
        }
        for (int i = 0, len = text.length(); i < len; i++) {
            encrypt += (char) (((text.charAt(i) + key.charAt(i % keyLen) - 2 * offset) % count) + offset);
        }
        return encrypt;
    }

    public static String decrypt(String text, String key, int offset, int count) {
        String decrypt = "";
        int keyLen = key.length();
        if (keyLen == 0) {
            throw new IllegalArgumentException("Key size should be 1 or more");
        }
        for (int i = 0, len = text.length(); i < len; i++) {
            decrypt += (char) (((text.charAt(i) - key.charAt(i % keyLen) + count) % count) + offset);
        }
        return decrypt;
    }

    public static void encryptCatalog(Path catalog, String key, Path output) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(key).append('\n');
        Files.walk(catalog)
                .filter(Files::isDirectory)
                .forEach(f -> {
                    stringBuilder
                            .append(catalog.relativize(f.toAbsolutePath()))
                            .append('\n');
                });

        stringBuilder.append(filespacer);

        Files.walk(catalog)
                .filter(Files::isRegularFile)
                .forEach(file -> {
                    try {
                        stringBuilder
                                .append(catalog.relativize(file.toAbsolutePath())).append('\n')
                                .append(new String(Files.readAllBytes(file), StandardCharsets.UTF_8))
                                .append(spacer);
                    } catch (IOException exception) {
                        throw new RuntimeException(exception);
                    }
                });

        String paths = encrypt(stringBuilder.toString(), key, firstChar, alphabetCount);
        Files.write(output, Collections.singleton(paths));
    }

    public static void decryptCatalog(Path encryptedFile, String key, Path targetDir) throws IOException {
        String str = new String(Files.readAllBytes(encryptedFile), StandardCharsets.UTF_8);
        str = decrypt(str, key, firstChar, alphabetCount);
        String[] arr = str.split(filespacer);

        String[] keyAndDirs = arr[0].split("\\R", 2);
        String fileKey = keyAndDirs[0];
        String dirs = keyAndDirs[1];

        if (!fileKey.equals(key)) {
            throw new IllegalArgumentException("Key is not correct");
        }

        for (String s1 : dirs.split("\n")) {
            Files.createDirectories(Paths.get(String.valueOf(targetDir), s1));
        }

        for (String s1 : arr[1].split(spacer)) {
            String[] file = s1.split("\\R", 2);
            if (file.length > 1) {
                String filePath = file[0];
                String text = file[1];
                Path f = Paths.get(String.valueOf(targetDir), filePath);
                Files.write(f, (text).getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            }
        }
    }
}
