package com.lmderval.discordsh.execution.builtins;

import com.lmderval.discordsh.execution.ShellEnv;
import org.jspecify.annotations.NonNull;

import java.util.List;

public interface IBuiltin {
    @NonNull String getName();
    void execute(@NonNull List<String> args, @NonNull ShellEnv env);
}
