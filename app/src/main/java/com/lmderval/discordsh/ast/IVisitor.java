package com.lmderval.discordsh.ast;

import org.jspecify.annotations.NonNull;

public interface IVisitor {
    void visit(@NonNull BaseAst e);
    void visit(@NonNull SimpleCommandAst e);
}
