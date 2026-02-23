package com.lmderval.discordsh.execution.builtins;

import com.lmderval.discordsh.execution.ShellEnv;
import org.jspecify.annotations.NonNull;

import java.util.List;

public abstract class BaseBuiltin {
    public abstract @NonNull String getName();
    public abstract void execute(@NonNull List<String> args, @NonNull ShellEnv env);
}
