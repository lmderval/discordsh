package com.lmderval.discordsh.parse;

import com.lmderval.discordsh.utils.Location;
import com.lmderval.discordsh.utils.StringEscape;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public record Token(@NonNull TokenTy ty, @Nullable String content, @NonNull Location location) {
    @Override
    public @NonNull String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ty);
        if (content != null) {
            sb.append("(");
            sb.append(StringEscape.escape(content));
            sb.append(")");
        }
        return sb.toString();
    }

    public enum TokenTy {
        WORD,
        EOF;
    }
}
