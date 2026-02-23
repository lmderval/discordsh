package com.lmderval.discordsh.execution;

import com.lmderval.discordsh.ast.BaseAst;
import com.lmderval.discordsh.ast.BaseVisitor;
import com.lmderval.discordsh.ast.SimpleCommandAst;
import com.lmderval.discordsh.execution.builtins.BaseBuiltin;
import com.lmderval.discordsh.execution.builtins.EchoBuiltin;
import org.jspecify.annotations.NonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExecutionVisitor extends BaseVisitor {
    private final @NonNull ShellEnv env;

    private static final Map<String, BaseBuiltin> BUILTINS = new HashMap<>();

    static {
        List.of(
                new EchoBuiltin()
        ).forEach(builtin -> BUILTINS.put(builtin.getName(), builtin));
    };

    public ExecutionVisitor(@NonNull ShellEnv env) {
        this.env = env;
    }

    @Override
    public void visit(@NonNull BaseAst e) {
        e.accept(this);
    }

    @Override
    public void visit(@NonNull SimpleCommandAst e) {
        String name = e.getArgs().getFirst();
        if (BUILTINS.containsKey(name)) {
            BUILTINS.get(name).execute(e.getArgs().stream().skip(1).toList(), env);
            return;
        }
        throw new RuntimeException("Unknown command '" + name + "'");
    }
}
