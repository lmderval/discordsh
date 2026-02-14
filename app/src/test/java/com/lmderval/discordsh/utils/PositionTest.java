package com.lmderval.discordsh.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {
    @Test
    void positionToString() {
        Position position = new Position("input", 2, 16);
        assertEquals("input@2:16", position.toString());
    }

    @Test
    void positionSameInput() {
        Position position = new Position("input", 2, 16);
        Position other = new Position("input", 3, 18);
        assertTrue(position.isSameInput(other));
    }

    @Test
    void positionNotSameInput() {
        Position position = new Position("input", 2, 16);
        Position other = new Position("file", 3, 18);
        assertFalse(position.isSameInput(other));
    }

    @Test
    void positionSameLine() {
        Position position = new Position("input", 2, 16);
        Position other = new Position("input", 2, 18);
        assertTrue(position.isSameLine(other));
    }

    @Test
    void positionNotSameLine() {
        Position position = new Position("input", 2, 16);
        Position other = new Position("input", 3, 18);
        assertFalse(position.isSameLine(other));
    }

    @Test
    void positionSame() {
        Position position = new Position("input", 2, 16);
        Position other = new Position("input", 2, 16);
        assertTrue(position.isSame(other));
    }

    @Test
    void positionNotSame() {
        Position position = new Position("input", 2, 16);
        Position other = new Position("input", 2, 18);
        assertFalse(position.isSame(other));
    }

    @Test
    void positionEquals() {
        Position position = new Position("input", 2, 16);
        Position expected = new Position("input", 2, 16);
        assertEquals(expected, position);
    }

    @Test
    void positionNotEquals() {
        Position position = new Position("input", 2, 16);
        Position expected = new Position("input", 3, 18);
        assertNotEquals(expected, position);
    }
}
