package com.lmderval.discordsh.utils;

import org.jspecify.annotations.NonNull;

public record Location(@NonNull Position start, @NonNull Position end) {
    @Override
    public @NonNull String toString() {
        if (start.isSame(end)) return start.toString();
        StringBuilder sb = new StringBuilder();
        sb.append(start);
        sb.append("-");
        if (!start.isSameLine(end)) {
            sb.append(end.line());
            sb.append(":");
        }
        sb.append(end.column());
        return sb.toString();
    }
}
