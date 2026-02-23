package com.lmderval.discordsh.execution;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import org.jspecify.annotations.NonNull;
import com.lmderval.discordsh.execution.ChannelStream.Mode;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ShellEnv {
    public static final int STDIN = 0;
    public static final int STDOUT = 1;
    public static final int STDERR = 2;

    private final @NonNull Guild guild;
    private final @NonNull Map<Integer, ChannelStream> channels;

    public ShellEnv(@NonNull Guild guild) {
        this.guild = guild;
        channels = new HashMap<>();
    }

    public void openChannel(@NonNull MessageChannel channel, int descriptor, @NonNull Mode mode) {
        channels.put(descriptor, new ChannelStream(channel, mode));
    }

    public void duplicateChannelDescriptor(int channelDescriptor, int descriptor, @NonNull Mode mode) {
        ChannelStream channel = channels.get(channelDescriptor);
        if (channel == null)
            throw new RuntimeException("No channel open on descriptor " + channelDescriptor);
        if (mode != channel.mode() && channel.mode() != Mode.IN_OUT)
            throw new RuntimeException("Invalid mode for descriptor " + channelDescriptor);
        channels.put(descriptor, new ChannelStream(channel.channel(), mode));
    }

    public @NonNull Optional<ChannelStream> getChannel(int channelDescriptor) {
        return Optional.ofNullable(channels.get(channelDescriptor));
    }
}
