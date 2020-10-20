package com.kirill.informationsecurity.algorithms.steno;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.kirill.informationsecurity.algorithms.steno.BinaryConverter.getBitsFromBytes;
import static com.kirill.informationsecurity.algorithms.steno.BinaryConverter.getBytesFromBits;

public class StenoSecond {
    private static final char SYMBOL = ' ';
    private static final int BYTE_LENGTH = 8;

    public static void encode(String sourcePath, String phrasePath, String containerPath) throws IOException {

        RandomAccessFile source = new RandomAccessFile(sourcePath, "r");
        RandomAccessFile container = new RandomAccessFile(containerPath, "rw");
        byte[] phrase = Files.readAllBytes(Paths.get(phrasePath));

        String phraseBits = getBitsFromBytes(phrase);



    }

    public static String decode(String containerPath, String charset) throws IOException {
        RandomAccessFile container = new RandomAccessFile(containerPath, "rw");
        StringBuilder bits = new StringBuilder();

        return "";
    }
}
