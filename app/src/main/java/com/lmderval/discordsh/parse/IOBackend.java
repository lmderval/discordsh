package com.lmderval.discordsh.parse;

import com.lmderval.discordsh.utils.Position;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IOBackend {
    private final @NonNull Reader reader;
    private final @NonNull Position position;
    private @Nullable Character current;
    private final @NotNull List<StringBuilder> lines;

    public IOBackend(@NonNull String string, @NonNull String from) {
        reader = new StringReader(string);
        position = new Position(from, 0, 0);
        current = null;
        lines = new ArrayList<>();
        lines.add(new StringBuilder());
    }

    public Optional<Character> peek() {
        if (current != null) return Optional.of(current);
        try {
            int read = reader.read();
            if (read == -1) return Optional.empty();
            current = (char)read;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (current != '\n') lines.getLast().append(current);
        return Optional.of(current);
    }

    public void drop() {
        if (current == null) return;
        if (current == '\n') {
            position.nextLine();
            lines.add(new StringBuilder());
        }
        else position.advance();
        current = null;
    }

    public @NonNull Position getPosition() {
        return position;
    }

    public @NonNull String getLine(int line) {
        return lines.get(line).toString();
    }

    public @NonNull List<String> getLines() {
        return lines.stream()
                .map(StringBuilder::toString)
                .toList();
    }
}
