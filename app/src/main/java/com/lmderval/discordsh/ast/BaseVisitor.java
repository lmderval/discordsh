package com.lmderval.discordsh.ast;

import org.jspecify.annotations.NonNull;

public abstract class BaseVisitor {
    public abstract void visit(@NonNull BaseAst e);
    public abstract void visit(@NonNull SimpleCommandAst e);
}
