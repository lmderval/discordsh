package com.lmderval.discordsh.parse;

import com.lmderval.discordsh.utils.Location;
import com.lmderval.discordsh.utils.Position;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class LexerTest {
    private static @NonNull Token eofAt(@NonNull Position start) {
        return new Token(
                Token.TokenTy.EOF,
                null,
                new Location(start, start)
        );
    }

    private static @NonNull Token wordFromAt(@NonNull String content, @NonNull Position start) {
        Position end = new Position(start.input(), start.line(), start.column() + content.length());
        return new Token(
                Token.TokenTy.WORD,
                content,
                new Location(start, end)
        );
    }

    @Test
    public void basicHelloWorld() {
        IOBackend io = new IOBackend("echo hello world", "stdin");
        Lexer lexer = new Lexer(io);
        List<Token> expected = List.of(
                wordFromAt("echo", new Position("stdin", 0, 0)),
                wordFromAt("hello", new Position("stdin", 0, 5)),
                wordFromAt("world", new Position("stdin", 0, 11)),
                eofAt(new Position("stdin", 0, 16))
        );
        for (Token expectedToken : expected) {
            Token token = lexer.peek(0);
            assertEquals(expectedToken.ty(), token.ty());
            assertEquals(expectedToken.content(), token.content());
            assertEquals(expectedToken.location().start(), token.location().start());
            assertEquals(expectedToken.location().end(), token.location().end());
            lexer.drop(0);
        }
    }
}
