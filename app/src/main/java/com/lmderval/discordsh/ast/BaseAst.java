package com.lmderval.discordsh.ast;

import com.lmderval.discordsh.utils.Location;
import org.jspecify.annotations.NonNull;

public abstract class BaseAst {
    protected @NonNull Location location;

    protected BaseAst(@NonNull Location location) {
        this.location = location;
    }

    public @NonNull Location getLocation() {
        return location;
    }
}
