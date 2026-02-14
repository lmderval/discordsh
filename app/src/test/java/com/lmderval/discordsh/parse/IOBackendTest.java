package com.lmderval.discordsh.parse;

import com.lmderval.discordsh.utils.Position;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class IOBackendTest {
    @Test
    public void basicHelloWorld() {
        String input = "echo hello world\n";
        IOBackend io = new IOBackend(input, "stdin");
        Position position = new Position("stdin", 0, 0);
        for (int i = 0; i < input.length(); i++) {
            Optional<Character> current = io.peek();
            assertTrue(current.isPresent());
            assertEquals(input.charAt(i), current.get());
            assertEquals(position, io.getPosition());
            io.drop();
            if (input.charAt(i) == '\n') position.nextLine();
            else position.advance();
        }
        assertTrue(io.peek().isEmpty());
        assertEquals(position, io.getPosition());
        List<String> expected = List.of(
                "echo hello world",
                ""
        );
        assertLinesMatch(expected, io.getLines());
    }

    @Test
    public void twoLinesHelloWorld() {
        String input = "echo hello\necho world\n";
        IOBackend io = new IOBackend(input, "stdin");
        Position position = new Position("stdin", 0, 0);
        for (int i = 0; i < input.length(); i++) {
            Optional<Character> current = io.peek();
            assertTrue(current.isPresent());
            assertEquals(input.charAt(i), current.get());
            assertEquals(position, io.getPosition());
            io.drop();
            if (input.charAt(i) == '\n') position.nextLine();
            else position.advance();
        }
        assertTrue(io.peek().isEmpty());
        assertEquals(position, io.getPosition());
        List<String> expected = List.of(
                "echo hello",
                "echo world",
                ""
        );
        assertLinesMatch(expected, io.getLines());
    }
    @Test
    public void noNewlineAtEndOfFile() {
        String input = "echo hello world";
        IOBackend io = new IOBackend(input, "stdin");
        Position position = new Position("stdin", 0, 0);
        for (int i = 0; i < input.length(); i++) {
            Optional<Character> current = io.peek();
            assertTrue(current.isPresent());
            assertEquals(input.charAt(i), current.get());
            assertEquals(position, io.getPosition());
            io.drop();
            if (input.charAt(i) == '\n') position.nextLine();
            else position.advance();
        }
        assertTrue(io.peek().isEmpty());
        assertEquals(position, io.getPosition());
        List<String> expected = List.of(
                "echo hello world"
        );
        assertLinesMatch(expected, io.getLines());
    }
}
