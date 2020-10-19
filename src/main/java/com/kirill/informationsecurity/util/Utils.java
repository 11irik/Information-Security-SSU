package com.kirill.informationsecurity.util;

import java.nio.charset.StandardCharsets;

public class Utils {
    public static String bytesToStringUtf8(byte[] array) {
        return (new String(array, StandardCharsets.UTF_8));
    }
}
