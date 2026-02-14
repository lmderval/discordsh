package com.lmderval.discordsh.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LocationTest {
    @Test
    public void locationToStringSameStartAndEnd() {
        Location location = new Location(
                new Position("input", 2, 16),
                new Position("input", 2, 16)
        );
        assertEquals("input@2:16", location.toString());
    }

    @Test
    public void locationToStringSameLineStartEnd() {
        Location location = new Location(
                new Position("input", 2, 16),
                new Position("input", 2, 18)
        );
        assertEquals("input@2:16-18", location.toString());
    }

    @Test
    public void locationToStringDifferentStartEnd() {
        Location location = new Location(
                new Position("input", 2, 16),
                new Position("input", 3, 18)
        );
        assertEquals("input@2:16-3:18", location.toString());
    }
}
