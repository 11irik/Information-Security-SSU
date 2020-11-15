package com.kirill.informationsecurity.algorithms.crypto;

public class CryptoString {

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
}
