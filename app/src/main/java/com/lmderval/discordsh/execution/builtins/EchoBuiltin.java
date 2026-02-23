package com.lmderval.discordsh.execution.builtins;

import com.lmderval.discordsh.execution.ChannelStream;
import com.lmderval.discordsh.execution.ShellEnv;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class EchoBuiltin implements IBuiltin {
    @Override
    public @NonNull String getName() {
        return "echo";
    }

    private static boolean parseOption(@NonNull String arg, @NonNull Options options) {
        if (arg.length() < 2 || arg.charAt(0) != '-') return false;
        Options newOptions = new Options(options);
        for (int i = 1; i < arg.length(); i++) {
            switch (arg.charAt(i)) {
                case 'e':
                    newOptions.escapes = true;
                    break;
                case 'E':
                    newOptions.escapes = false;
                    break;
                default:
                    return false;
            }
        }
        options.setOptions(newOptions);
        return true;
    }

    private static int parseOptions(@NonNull List<String> args, @NonNull Options options) {
        for (int i = 0; i < args.size(); i++) {
            if (!parseOption(args.get(i), options)) return i;
        }
        return args.size();
    }

    private static @NonNull String applyEscapes(@NonNull String s) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '\\') {
                res.append(s.charAt(i));
                continue;
            }
            if (++i >= s.length()) {
                res.append('\\');
                break;
            };
            switch (s.charAt(i)) {
                case 'n':
                    res.append('\n');
                    break;
                case 't':
                    res.append('\t');
                    break;
                default:
                    res.append(s.charAt(i));
                    break;
            }
        }
        return res.toString();
    }

    @Override
    public void execute(@NonNull List<String> args, @NonNull ShellEnv env) {
        ChannelStream stream = env.getChannel(ShellEnv.STDOUT).orElseThrow();
        StringBuilder res = new StringBuilder();
        Options options = new Options();
        int index = parseOptions(args, options);
        for (int i = index; i < args.size(); i++) {
            String arg = args.get(i);
            if (options.escapes) arg = applyEscapes(arg);
            if (i > 0) res.append(' ');
            res.append(arg);
        }
        stream.write(res.toString());
    }

    private static class Options {
        private boolean escapes;

        private Options() {
            escapes = false;
        }

        private Options(@NonNull Options other) {
            escapes = other.escapes;
        }

        private void setOptions(@NonNull Options other) {
            escapes = other.escapes;
        }
    }
}
