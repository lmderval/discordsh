package com.lmderval.discordsh.listeners;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.internal.utils.tuple.ImmutablePair;
import net.dv8tion.jda.internal.utils.tuple.Pair;
import org.jspecify.annotations.NonNull;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class MessageListener extends ListenerAdapter {
    private static final @NonNull MessageListener INSTANCE;

    static {
        INSTANCE = new MessageListener();
    }

    public static MessageListener getInstance() {
        return INSTANCE;
    }

    private final @NonNull List<Pair<Consumer<Message>, MessageChannel>> subscribers;

    private MessageListener() {
        subscribers = new LinkedList<>();
    }

    public static void subscribe(@NonNull Consumer<Message> subscriber, @NonNull MessageChannel channel) {
        INSTANCE.subscribers.add(new ImmutablePair<>(subscriber, channel));
    }

    public static void unsubscribe(@NonNull Consumer<Message> subscriber) {
        INSTANCE.subscribers.removeIf(pair -> pair.getLeft().equals(subscriber));
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message message = event.getMessage();
        subscribers.stream()
                .filter(pair -> event.getChannel().getIdLong() == pair.getRight().getIdLong())
                .map(Pair::getLeft)
                .forEach(subscriber -> subscriber.accept(message));
    }
}
