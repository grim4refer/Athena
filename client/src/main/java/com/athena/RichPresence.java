package com.athena;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;

public class RichPresence {

    private final String CLIENT_ID = "740936369735729234";

    private DiscordRPC lib;
    private DiscordRichPresence presence;

    //Discord

    public void initiate() {
        lib = DiscordRPC.INSTANCE;
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        lib.Discord_Initialize(CLIENT_ID, handlers, true, "");
        presence = new DiscordRichPresence();
        presence.startTimestamp = System.currentTimeMillis() / 1000;
        presence.largeImageKey = "untitled-1";
        presence.smallImageKey = "";
        presence.details = "Athena: Development Stages";
        presence.state ="Release: 2021";
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

    public void reloadPresence(){
        presence.state = ("[ Logged in: " + getUsername() + " ]");
    }

    public static String getUsername(){
        return getUsername();
    }

    public static String getRank() {return getRank();}

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

    private void updatePresence() {
        lib.Discord_UpdatePresence(presence);
    }
}
