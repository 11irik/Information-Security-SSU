package com.kirill.information_security.algorithms.steno.one;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Steno {
    private static final int BYTE_LENGTH = 8;
    private static final char SYMBOL = ' ';

    public static void encode(String sourcePath, String phrasePath, String containerPath) throws IOException {

        RandomAccessFile source = new RandomAccessFile(sourcePath, "r");
        RandomAccessFile container = new RandomAccessFile(containerPath, "rw");
        byte[] phrase = Files.readAllBytes(Paths.get(phrasePath));

        List<Integer> phraseBits = getBitsFromPhrase(phrase);
        String line = null;
        for (int i = 0; i < phrase.length * BYTE_LENGTH; ++i) {
            line = source.readLine();

            if (phraseBits.get(i) == 1) {
                line += SYMBOL;
            }
            line += "\n";

            container.writeBytes(line);
        }

        while ((line = source.readLine()) != null) {
            container.writeBytes(line + '\n');
        }
    }

    public static String decode(String containerPath) throws IOException {
        RandomAccessFile container = new RandomAccessFile(containerPath, "rw");
        List<Integer> bits = new ArrayList<>();
        String line = null;

        int maxNotSymbolCharsCount = BYTE_LENGTH;
        while ((line = container.readLine()) != null) {
            if (line.charAt(line.length() - 1) == SYMBOL) {
                bits.add(1);
                maxNotSymbolCharsCount = BYTE_LENGTH;
            } else {
                bits.add(0);
                maxNotSymbolCharsCount--;
            }

            if (maxNotSymbolCharsCount == 0) {
                break;
            }
        }

        int byteCount = bits.size() / BYTE_LENGTH;
        Integer[] bitsArray = new Integer[byteCount * BYTE_LENGTH];
        for (int i = 0; i < bitsArray.length; ++i) {
            bitsArray[i] = bits.get(i);
        }

        return parseWord(bitsArray);
    }

    private static List<Integer> getBitsFromByte(byte b) {
        List<Integer> bits = new ArrayList<>();
        for (int i = 0; i < BYTE_LENGTH; ++i) {
            bits.add(((b >> i) & 1));
        }

        return bits;
    }

    private static List<Integer> getBitsFromPhrase(byte[] phrase) {
        List<Integer> phraseBits = new ArrayList<>();

        for (byte b : phrase) {
            phraseBits.addAll(getBitsFromByte(b));
        }

        return phraseBits;
    }

    private static String parseWord(Integer[] bits) {
        StringBuffer word = new StringBuffer();

        StringBuffer charBits = new StringBuffer();
        int count = 0;
        for (Integer bit : bits) {
            charBits.append(bit);

            if (++count == BYTE_LENGTH) {
                byte charByte = Byte.parseByte(String.valueOf(charBits.reverse()), 2);
                word.append((char) charByte);

                count = 0;
                charBits = new StringBuffer();
            }
        }

        return String.valueOf(word);
    }
}
