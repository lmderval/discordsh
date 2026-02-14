package com.lmderval.discordsh;

import com.lmderval.discordsh.listeners.CommandListener;
import com.lmderval.discordsh.listeners.ReadyListener;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class App {
    public static void main(String[] args) {
        String token = System.getenv().get("TOKEN");
        JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new ReadyListener())
                .addEventListeners(new CommandListener())
                .build();
    }
}
