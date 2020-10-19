package com.kirill.informationsecurity.algorithms.steno;

import java.util.ArrayList;
import java.util.List;

public class BinaryConverter {
    private static final int BYTE_LENGTH = 8;

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
//            TODO: Add normal exception
            throw new RuntimeException();
        }

        return (byte)Integer.parseInt(bits, 2);
    }

    public static byte[] getBytesFromBits(String bits) {
        if (bits.length() % 8 != 0) {
//            TODO: Add normal exception
            throw new RuntimeException();
        }

        List<Byte> byteList = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 1; i <= bits.length(); ++i) {
            stringBuilder.append(bits.charAt(i-1));

            if (i % BYTE_LENGTH == 0) {
                stringBuilder.trimToSize();
                byteList.add(getByteFromBits(stringBuilder.toString()));
                stringBuilder = new StringBuilder();
            }
        }

        byte[] byteArray = new byte[byteList.size()];
        for (int i = 0; i < byteList.size(); i++) {
            byteArray[i] = byteList.get(i);
        }
        return byteArray;
    }
}
