package com.lmderval.discordsh.utils;

import org.jspecify.annotations.NonNull;

public record Position(@NonNull String input, int line, int column) {
    public boolean isSameInput(@NonNull Position other) {
        return input.equals(other.input);
    }

    public boolean isSameLine(@NonNull Position other) {
        if (!isSameInput(other)) return false;
        return line == other.line;
    }

    public boolean isSame(Position other) {
        if (!isSameLine(other)) return false;
        return column == other.column;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Position other)) return false;
        return isSame(other);
    }

    @Override
    public @NonNull String toString() {
        return input + "@" + line + ":" + column;
    }
}
