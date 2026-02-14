package com.lmderval.discordsh.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {
    @Test
    public void positionToString() {
        Position position = new Position("input", 2, 16);
        assertEquals("input@2:16", position.toString());
    }

    @Test
    public void positionSameInput() {
        Position position = new Position("input", 2, 16);
        Position other = new Position("input", 3, 18);
        assertTrue(position.isSameInput(other));
    }

    @Test
    public void positionNotSameInput() {
        Position position = new Position("input", 2, 16);
        Position other = new Position("file", 3, 18);
        assertFalse(position.isSameInput(other));
    }

    @Test
    public void positionSameLine() {
        Position position = new Position("input", 2, 16);
        Position other = new Position("input", 2, 18);
        assertTrue(position.isSameLine(other));
    }

    @Test
    public void positionNotSameLine() {
        Position position = new Position("input", 2, 16);
        Position other = new Position("input", 3, 18);
        assertFalse(position.isSameLine(other));
    }

    @Test
    public void positionSame() {
        Position position = new Position("input", 2, 16);
        Position other = new Position("input", 2, 16);
        assertTrue(position.isSame(other));
    }

    @Test
    public void positionNotSame() {
        Position position = new Position("input", 2, 16);
        Position other = new Position("input", 2, 18);
        assertFalse(position.isSame(other));
    }

    @Test
    public void positionEquals() {
        Position position = new Position("input", 2, 16);
        Position expected = new Position("input", 2, 16);
        assertEquals(expected, position);
    }

    @Test
    public void positionNotEquals() {
        Position position = new Position("input", 2, 16);
        Position expected = new Position("input", 3, 18);
        assertNotEquals(expected, position);
    }

    @Test
    public void positionAdvance() {
        Position position = new Position("input", 2, 16);
        position.advance();
        Position expected = new Position("input", 2, 17);
        assertEquals(expected, position);
    }

    @Test
    public void positionNextLine() {
        Position position = new Position("input", 2, 16);
        position.nextLine();
        Position expected = new Position("input", 3, 0);
        assertEquals(expected, position);
    }
}
