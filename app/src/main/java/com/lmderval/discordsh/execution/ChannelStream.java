package com.lmderval.discordsh.execution;

import com.lmderval.discordsh.listeners.MessageListener;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public record ChannelStream(@NonNull MessageChannel channel, @NonNull Mode mode) {
    public void write(@NonNull String content) {
        if (mode == Mode.IN) throw new RuntimeException("Invalid write operation on read only channel");
        channel.sendMessage(content).queue();
    }

    public @NonNull String read() {
        if (mode == Mode.OUT) throw new RuntimeException("Invalid read operation on write only channel");
        final String[] content = {null};

        Consumer<Message> subscriber = message -> {
            synchronized (ChannelStream.this) {
                content[0] = message.getContentRaw();
                ChannelStream.this.notify();
            }
        };

        MessageListener.subscribe(subscriber, channel);
        try {
            synchronized (this) {
                while (content[0] == null) wait(20000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        MessageListener.unsubscribe(subscriber);

        return content[0];
    }

    public enum Mode {
        IN,
        OUT,
        IN_OUT
    }
}
