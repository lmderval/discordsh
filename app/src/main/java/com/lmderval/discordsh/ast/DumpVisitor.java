package com.lmderval.discordsh.ast;

import com.lmderval.discordsh.utils.StringEscape;
import org.jspecify.annotations.NonNull;

public class DumpVisitor extends BaseVisitor {
    private final @NonNull StringBuilder dump;

    public DumpVisitor() {
        dump = new StringBuilder();
    }

    @Override
    public void visit(@NonNull BaseAst e) {
        e.accept(this);
    }

    @Override
    public void visit(@NonNull SimpleCommandAst e) {
        dump.append("simple_command { ");
        for (String arg : e.getArgs()) {
            dump.append("\"");
            dump.append(StringEscape.escape(arg).replace("\"", "\\\""));
            dump.append("\" ");
        }
        dump.append("}");
    }

    public @NonNull String getDump() {
        return dump.toString();
    }
}
