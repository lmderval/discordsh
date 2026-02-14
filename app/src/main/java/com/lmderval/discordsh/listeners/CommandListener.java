package com.lmderval.discordsh.listeners;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jspecify.annotations.NonNull;

public class CommandListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NonNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        Message message = event.getMessage();
        String content = message.getContentRaw();

        if (!content.startsWith("$"))
            return;

        String command = content.substring(1);
        System.out.println("Received command: " + command);
    }
}
