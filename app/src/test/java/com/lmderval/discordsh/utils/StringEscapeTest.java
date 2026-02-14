package com.lmderval.discordsh.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringEscapeTest {
    @Test
    public void escapeString() {
        String string = "\thello\n\tworld!";
        String escaped = StringEscape.escape(string);
        assertEquals("\\thello\\n\\tworld!", escaped);
    }
}
