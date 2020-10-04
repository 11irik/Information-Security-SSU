package com.kirill.information_security.algorithms.antivirus;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SignatureSearch {
    public static List<String> getDirPaths(String signatureFilePath, int offset, int length, String dirPath) throws IOException {

        RandomAccessFile raf = new RandomAccessFile(signatureFilePath, "r");
        raf.seek(offset);
        byte[] signature = new byte[length];
        raf.read(signature, 0, length);

        List<String> dirPaths = new ArrayList<>();

        Files.walk(Paths.get(dirPath))
                .filter(Files::isRegularFile)
                .forEach(f -> {
                    try {
                        byte[] file = Files.readAllBytes(f.toAbsolutePath());

                        if (indexOf(file, signature) != -1) {
                            dirPaths.add(f.toString());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        return dirPaths;
    }

    private static int indexOf(byte[] array, byte[] subArray) {
        for (int i = 0; i < array.length - subArray.length + 1; ++i) {
            boolean found = true;
            for (int j = 0; j < subArray.length; ++j) {
                if (array[i + j] != subArray[j]) {
                    found = false;
                    break;
                }
            }
            if (found) return i;
        }
        return -1;
    }
}