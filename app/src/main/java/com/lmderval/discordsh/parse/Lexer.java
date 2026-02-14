package com.lmderval.discordsh.parse;

import com.lmderval.discordsh.parse.Token.TokenTy;
import com.lmderval.discordsh.utils.Location;
import com.lmderval.discordsh.utils.Position;
import org.jspecify.annotations.NonNull;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Lexer {
    private final @NonNull IOBackend io;
    private final @NonNull List<Token> tokens;
    private @NonNull Position start;
    private @NonNull Position end;

    public Lexer(@NonNull IOBackend io) {
        this.io = io;
        tokens = new LinkedList<>();
        start = new Position(io.getPosition());
        end = new Position(io.getPosition());
    }

    private static boolean isBlank(char c) {
        return c == ' ' || c == '\t';
    }

    private void addEOF() {
        tokens.add(new Token(
                TokenTy.EOF,
                null,
                new Location(start, start)
        ));
    }

    private void addWord(@NonNull String content) {
        tokens.add(new Token(
                TokenTy.WORD,
                content,
                new Location(start, end)
        ));
    }

    private void skipBlank() {
        Optional<Character> current = io.peek();
        while (current.isPresent() && isBlank(current.get())) {
            io.drop();
            current = io.peek();
        }
    }

    private void process() {
        skipBlank();
        Optional<Character> current = io.peek();
        start = new Position(io.getPosition());

        if (current.isEmpty()) {
            addEOF();
            return;
        }

        StringBuilder content = new StringBuilder();
        while (true) {
            current = io.peek();

            if (current.isEmpty()) {
                end = new Position(io.getPosition());
                addWord(content.toString());
                return;
            }

            char c = current.get();
            if (isBlank(c)) {
                end = new Position(io.getPosition());
                addWord(content.toString());
                return;
            }

            content.append(c);
            io.drop();
        }
    }

    public @NonNull Token peek(int lookahead) {
        while (tokens.size() <= lookahead) process();
        return tokens.get(lookahead);
    }

    public void drop(int lookahead) {
        while (tokens.size() <= lookahead) process();
        for (int i = 0; i <= lookahead; i++) tokens.removeFirst();
    }
}
