package com.lmderval.discordsh.utils;

import org.jspecify.annotations.NonNull;

public class Position {
    private final @NonNull String input;
    private int line;
    private int column;

    public Position(@NonNull String input, int line, int column) {
        this.input = input;
        this.line = line;
        this.column = column;
    }

    public Position(@NonNull Position other) {
        input = other.input;
        line = other.line;
        column = other.column;
    }

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

    public void advance() {
        column++;
    }

    public void nextLine() {
        line++;
        column = 0;
    }

    public @NonNull String input() {
        return input;
    }

    public int line() {
        return line;
    }

    public int column() {
        return column;
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
