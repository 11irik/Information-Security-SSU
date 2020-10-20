package com.kirill.informationsecurity.algorithms.steno;

import com.google.common.collect.BiMap;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.kirill.informationsecurity.algorithms.steno.BinaryConverter.getBitsFromBytes;
import static com.kirill.informationsecurity.algorithms.steno.BinaryConverter.getBytesFromBits;

public class StenoAnalog {

    private static final int BYTE_LENGTH = 8;

    public static void encode(Path source, Path phrase, Path dest, Charset charset, BiMap<Character, Character> analogs) throws IOException {
        StringBuilder builder = new StringBuilder(new String(Files.readAllBytes(source), charset));
        byte[] phraseBytes = Files.readAllBytes(phrase);

        char[] bits = getBitsFromBytes(phraseBytes).toCharArray();

        int i = 0;
        int j = 0;
        while (j < bits.length) {
            while (!analogs.containsKey(builder.charAt(i))) {
                i++;
            }
            if (bits[j] == '1') {
                builder.replace(i, i + 1, String.valueOf(analogs.get(builder.charAt(i))));
            }
            i++;
            j++;
        }

        Files.write(dest, builder.toString().getBytes(charset));
    }

    public static String decode(Path text, Charset charset, BiMap<Character, Character> analogs) throws IOException {
        char[] txt = new String(Files.readAllBytes(text), charset).toCharArray();
        StringBuilder bits = new StringBuilder();
        int maxNotSymbolCharsCount = BYTE_LENGTH;
        int i = 0;

        while (true) {
            while (txt.length - 1 > i && !(analogs.containsKey(txt[i]) || analogs.containsValue(txt[i]))) {
                i++;
            }
            if (txt.length - i <= 1) {
                break;
            }

            if (analogs.containsValue(txt[i])) {
                bits.append('1');
                maxNotSymbolCharsCount = BYTE_LENGTH;
            }
            if (analogs.containsKey(txt[i])) {
                bits.append('0');
                maxNotSymbolCharsCount--;
            }
            i++;

            if (maxNotSymbolCharsCount == 0) {
                break;
            }
        }
        bits.setLength(bits.length() - bits.length() % 8);

        return new String(getBytesFromBits(bits.toString()), charset);
    }
}
