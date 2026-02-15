package com.lmderval.discordsh.parse;

import com.lmderval.discordsh.ast.BaseAst;
import com.lmderval.discordsh.ast.DumpVisitor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

public class ParserTest {
    @Test
    public void parseHelloWorld() {
        IOBackend io = new IOBackend("echo hello world", "stdin");
        Lexer lexer = new Lexer(io);
        Parser parser = new Parser(lexer);
        Optional<BaseAst> ast = parser.parseInput();
        assertTrue(ast.isPresent());
        DumpVisitor visitor = new DumpVisitor();
        visitor.visit(ast.get());
        assertEquals("simple_command { \"echo\" \"hello\" \"world\" }",visitor.getDump());
    }
}
