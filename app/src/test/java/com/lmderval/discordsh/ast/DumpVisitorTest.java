package com.lmderval.discordsh.ast;

import com.lmderval.discordsh.utils.Location;
import com.lmderval.discordsh.utils.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class DumpVisitorTest {
    private static final Position DUMMY_POSITION = new Position("stdin", 0, 0);
    private static final Location DUMMY_LOCATION = new Location(DUMMY_POSITION, DUMMY_POSITION);

    @Test
    public void dumpHelloWorld() {
        BaseAst ast = new SimpleCommandAst(DUMMY_LOCATION, List.of("echo", "hello", "world"));
        DumpVisitor visitor = new DumpVisitor();
        visitor.visit(ast);
        assertEquals("simple_command { \"echo\" \"hello\" \"world\" }", visitor.getDump());
    }
}
