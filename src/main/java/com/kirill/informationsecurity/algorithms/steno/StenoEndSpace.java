package com.kirill.informationsecurity.algorithms.steno;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static com.kirill.informationsecurity.algorithms.steno.BinaryConverter.getBitsFromBytes;
import static com.kirill.informationsecurity.algorithms.steno.BinaryConverter.getBytesFromBits;

public class StenoEndSpace {
    private static final char SYMBOL = ' ';
    private static final int BYTE_LENGTH = 8;

    public static void encode(Path source, Path phrase, Path dest, Charset charset) throws IOException {

        Stream<String> sourceLines = Files.lines(source, charset);
        byte[] phraseBytes = Files.readAllBytes(phrase);

        Files.write(dest, appendSymbols(sourceLines, getBitsFromBytes(phraseBytes).toCharArray(), SYMBOL).getBytes(charset));
    }

    public static String appendSymbols(Stream<String> lines, char[] bits, char symbol) {
        StringBuilder stringBuilder = new StringBuilder();
        AtomicInteger i = new AtomicInteger();

        lines.forEach(line -> {
            stringBuilder.append(line);
            if (i.get() < bits.length && bits[i.getAndIncrement()] == '1') {
                stringBuilder.append(symbol);
            }
            stringBuilder.append('\n');
        });

        return stringBuilder.toString();
    }

    public static String decode(Path file, Charset charset) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(String.valueOf(file), "rw");

        StringBuilder bits = new StringBuilder();
        String line = null;
        int maxNotSymbolCharsCount = BYTE_LENGTH;

        while ((line = raf.readLine()) != null) {
            if (line.charAt(line.length() - 1) == SYMBOL) {
                bits.append('1');
                maxNotSymbolCharsCount = BYTE_LENGTH;
            } else {
                bits.append('0');
                maxNotSymbolCharsCount--;
            }

            if (maxNotSymbolCharsCount == 0) {
                break;
            }
        }
        bits.setLength(bits.length() - bits.length() % 8);

        return new String(getBytesFromBits(bits.toString()), charset);
    }
}
