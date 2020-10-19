package com.kirill.informationsecurity.algorithms.steno;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.kirill.informationsecurity.algorithms.steno.BinaryConverter.getBitsFromBytes;
import static com.kirill.informationsecurity.algorithms.steno.BinaryConverter.getBytesFromBits;

public class Steno {
    private static final char SYMBOL = ' ';
    private static final int BYTE_LENGTH = 8;

    public static void encode(String sourcePath, String phrasePath, String containerPath) throws IOException {

        RandomAccessFile source = new RandomAccessFile(sourcePath, "r");
        RandomAccessFile container = new RandomAccessFile(containerPath, "rw");
        byte[] phrase = Files.readAllBytes(Paths.get(phrasePath));

        String phraseBits = getBitsFromBytes(phrase);
        String line = null;
        for (char phraseBit : phraseBits.toCharArray()) {
            line = source.readLine();

            if (phraseBit == '1') {
                line += SYMBOL;
            }
            line += "\n";

            container.writeBytes(line);
        }

        while ((line = source.readLine()) != null) {
            container.writeBytes(line + '\n');
        }
    }

    public static String decode(String containerPath, String charset) throws IOException {
        RandomAccessFile container = new RandomAccessFile(containerPath, "rw");
        StringBuilder bits = new StringBuilder();
        String line = null;

        int maxNotSymbolCharsCount = BYTE_LENGTH;
        while ((line = container.readLine()) != null) {
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

        return new String(getBytesFromBits(bits.toString()), charset);
    }
}
