package com.kirill.information_security.util;

import java.nio.charset.StandardCharsets;

public class Utils {
    public static String bytesToStringUtf8(byte[] array) {
        return (new String(array, StandardCharsets.UTF_8));
    }
}
