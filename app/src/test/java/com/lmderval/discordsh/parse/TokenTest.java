package com.lmderval.discordsh.parse;

import com.lmderval.discordsh.utils.Location;
import com.lmderval.discordsh.utils.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TokenTest {
    @Test
    public void emptyToken() {
        Token token = new Token(Token.TokenTy.EOF, null, new Location(
                new Position("input", 0, 0),
                new Position("input", 0, 0)
        ));
        assertEquals("EOF", token.toString());
    }

    @Test
    public void echoToken() {
        Token token = new Token(Token.TokenTy.WORD, "echo", new Location(
                new Position("input", 0, 0),
                new Position("input", 0, 4)
        ));
        assertEquals("WORD(echo)", token.toString());
    }

    @Test
    public void stringToken() {
        Token token = new Token(Token.TokenTy.WORD, "'hello\nworld'", new Location(
                new Position("input", 0, 0),
                new Position("input", 1, 6)
        ));
        assertEquals("WORD('hello\\nworld')", token.toString());
    }
}
