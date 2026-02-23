package com.lmderval.discordsh.ast;

import com.lmderval.discordsh.utils.Location;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class SimpleCommandAst extends BaseAst {
    private final @NonNull List<String> args;

    public SimpleCommandAst(@NonNull Location location, @NonNull List<String> args) {
        super(location);
        this.args = args;
    }

    @Override
    public void accept(@NonNull IVisitor visitor) {
        visitor.visit(this);
    }

    public @NonNull List<String> getArgs() {
        return args;
    }
}
