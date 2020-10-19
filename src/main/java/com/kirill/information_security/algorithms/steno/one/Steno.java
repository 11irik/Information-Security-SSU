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
                bits.append(1);
                maxNotSymbolCharsCount = BYTE_LENGTH;
            } else {
                bits.append(0);
                maxNotSymbolCharsCount--;
            }

            if (maxNotSymbolCharsCount == 0) {
                break;
            }
        }

        return new String(getBytesFromBits(bits.toString()), charset);
    }

    public static String getBitsFromByte(byte b) {
        StringBuilder bits = new StringBuilder(Integer.toBinaryString(b & 0xFF));

        while (bits.length() < BYTE_LENGTH) {
            bits.insert(0, '0');
        }
        bits.trimToSize();

        return bits.toString();
    }

    public static String getBitsFromBytes(byte[] bytes) {
        StringBuilder phraseBits = new StringBuilder();

        for (byte b : bytes) {
            phraseBits.append(getBitsFromByte(b));
        }
        phraseBits.trimToSize();

        return phraseBits.toString();
    }

    public static byte getByteFromBits(String bits) {
        if (bits.length() != BYTE_LENGTH) {
            //TODO: Add normal exception
            throw new RuntimeException();
        }

        return (byte)Integer.parseInt(bits, 2);
    }

    public static byte[] getBytesFromBits(String bits) {
        List<Byte> byteList = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 1; i <= bits.length(); ++i) {
            stringBuilder.append(bits.charAt(i-1));

            if (i % BYTE_LENGTH == 0) {
                stringBuilder.trimToSize();
                byteList.add(Steno.getByteFromBits(stringBuilder.toString()));
                stringBuilder = new StringBuilder();
            }
        }

        byte[] byteArray = new byte[byteList.size()];
        for(int i = 0; i < byteList.size(); i++) byteArray[i] = byteList.get(i);

        return byteArray;
    }
}
