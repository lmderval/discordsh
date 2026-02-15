package com.lmderval.discordsh.parse;

import com.lmderval.discordsh.ast.BaseAst;
import com.lmderval.discordsh.ast.SimpleCommandAst;
import com.lmderval.discordsh.utils.Location;
import com.lmderval.discordsh.utils.Position;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.lmderval.discordsh.parse.Token.*;

public class Parser {
    private final @NonNull Lexer lexer;

    public Parser(@NonNull Lexer lexer) {
        this.lexer = lexer;
    }

    public @NonNull Optional<BaseAst> parseInput() {
        Token token = lexer.peek(0);
        if (Rule.LIST.first(token)) return parseList();
        return Optional.empty();
    }

    public @NonNull Optional<BaseAst> parseList() {
        Token token = lexer.peek(0);
        if (Rule.AND_OR.first(token)) return parseAndOr();
        return Optional.empty();
    }

    private @NonNull Optional<BaseAst> parseAndOr() {
        Token token = lexer.peek(0);
        if (Rule.PIPELINE.first(token)) return parsePipeline();
        return Optional.empty();
    }

    private @NonNull Optional<BaseAst> parsePipeline() {
        Token token = lexer.peek(0);
        if (Rule.COMMAND.first(token)) return parseCommand();
        return Optional.empty();
    }

    private @NonNull Optional<BaseAst> parseCommand() {
        Token token = lexer.peek(0);
        if (Rule.SIMPLE_COMMAND.first(token)) return parseSimpleCommand();
        return Optional.empty();
    }

    private @NonNull Optional<BaseAst> parseSimpleCommand() {
        Token token = lexer.peek(0);
        if (token.ty() != TokenTy.WORD || token.content() == null)
            throw new RuntimeException("Invalid token " + token + " at " + token.location());

        Position start = token.location().start();
        Position end = token.location().end();

        List<String> args = new ArrayList<>();
        args.add(token.content());

        lexer.drop(0);
        token = lexer.peek(0);

        while (!Rule.SIMPLE_COMMAND.follow(token)) {
            if (token.ty() != TokenTy.WORD || token.content() == null)
                throw new RuntimeException("Invalid token " + token + " at " + token.location());

            args.add(token.content());
            end = token.location().end();

            lexer.drop(0);
            token = lexer.peek(0);
        }

        return Optional.of(new SimpleCommandAst(new Location(start, end), args));
    }

    private enum Rule {
        LIST(
                Set.of(TokenTy.WORD),
                Set.of(TokenTy.EOF)
        ),
        AND_OR(
                Set.of(TokenTy.WORD),
                Set.of(TokenTy.EOF)
        ),
        PIPELINE(
                Set.of(TokenTy.WORD),
                Set.of(TokenTy.EOF)
        ),
        COMMAND(
                Set.of(TokenTy.WORD),
                Set.of(TokenTy.EOF)
        ),
        SIMPLE_COMMAND(
                Set.of(TokenTy.WORD),
                Set.of(TokenTy.EOF)
        );

        final @NonNull Set<TokenTy> first;
        final @NonNull Set<TokenTy> follow;

        Rule(@NonNull Set<TokenTy> first, @NonNull Set<TokenTy> follow) {
            this.first = first;
            this.follow = follow;
        }

        public boolean first(@NonNull Token token) {
            return first.contains(token.ty());
        }

        public boolean follow(@NonNull Token token) {
            return follow.contains(token.ty());
        }
    }
}
