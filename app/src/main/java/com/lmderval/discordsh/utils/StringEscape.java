package com.lmderval.discordsh.utils;

import org.jspecify.annotations.NonNull;

public class StringEscape {
    public static String escape(@NonNull String string) {
        return string.replace("\t", "\\t")
                .replace("\b", "\\b")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\f", "\\f");
    }
}
