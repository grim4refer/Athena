package com.athena.discord;

import com.athena.discord.DiscordEventHandlers;
import com.athena.discord.DiscordRPC;
import com.athena.discord.DiscordRichPresence;

/**
 * @author Arham 4
 */
public class RichPresence {

    private final static String CLIENT_ID = "740936369735729234";

    private static DiscordRPC lib;
    private static DiscordRichPresence presence;
    public static void initiate() {
        lib = DiscordRPC.INSTANCE;
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        lib.Discord_Initialize(CLIENT_ID, handlers, true, "");
        presence = new DiscordRichPresence();
        presence.startTimestamp = System.currentTimeMillis() / 1000;
        presence.largeImageKey = "untitled-1";
        presence.smallImageKey = "";
        presence.details = "Happy Christmas :)";
        presence.state = "Stage: Development";
        updatePresence();
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                lib.Discord_RunCallbacks();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {}
            }
        }, "RPC-Callback-Handler").start();
    }

    public boolean presenceIsNull() {
        return presence == null;
    }

    public void updateDetails(String details) {
        presence.details = details;
        updatePresence();
    }

    public void updateState(String state) {
        presence.state = state;
        updatePresence();
    }

    public void updateSmallImageKey(String key) {
        presence.smallImageKey = key;
        updatePresence();
    }

    private static void updatePresence() {
        lib.Discord_UpdatePresence(presence);
    }
}