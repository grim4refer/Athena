package com.arlania.world.content.minigames;

import com.arlania.engine.task.Task;
import com.arlania.model.Locations.Location;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.player.Player;
import com.arlania.world.entity.impl.player.Player;

public abstract class Minigame2 extends Task {

    protected int max_players;
    protected Player[] players;
    protected int playerIndex = 0;
    protected Location location;
    protected String name = "minigame";

    private int startTime = -1;

    protected int maxRunTime = 15 * 60; // 15 mins maximum run time

    protected boolean active = false;

    protected boolean activated = true;

    public Minigame2(int maxPlayers, Location location) {
        this.max_players = maxPlayers;
        this.players = new Player[max_players];
        this.location = location;
    }

    public boolean addPlayer(Player p) {
        if (playerIndex == max_players)
            return false;
        if (active)
            return false;
        if (this.findPlayerIndex(p) != -1)
            return false;
        this.players[playerIndex++] = p;
        System.out.println("player index is now: " + playerIndex);
        System.out.println("added " + p.getUsername() + " to " + getName());
        return true;
    }

    public int findPlayerIndex(Player p) {
        if (p == null)
            return -1;
        for (int i = 0; i < playerIndex; ++i) {
            if (players[i].equals(p))
                return i;
        }
        return -1;
    }

    public boolean removePlayer(Player p) {
        int idx = findPlayerIndex(p);
        if (idx == -1)
            return false;
        players[idx] = null;
        updatePlayersArray();
        System.out.println("player index is now: " + playerIndex);
        System.out.println("removed " + p.getUsername() + " from " + getName());
        return true;
    }

    public void updatePlayersArray() {
        for (int i = 0; i < playerIndex; ++i) {
            if (players[i] == null) {
                players[i] = players[playerIndex - 1];
                players[playerIndex - 1] = null;
                playerIndex--;
            }
        }
    }

    public void init() {
        for (int i = 0; i < playerIndex; ++i) {
            removePlayer(players[i]);
        }
        playerIndex = 0;
        stop();
    }

    public void stop() {
        this.active = false;
    }

    public void start() {
        this.startTime = (int)System.currentTimeMillis();
        this.active = true;
    }

    public void sendLocalMessage(String msg) {
        for (int i = 0; i < playerIndex; ++i)
            players[i].getPacketSender().sendMessage(msg);
    }

    public boolean isInGame(Player p) {
        return findPlayerIndex(p) != -1;
    }
    public boolean isInGame(NPC c) {

        return Location.inLocation(c, location);
    }


    public void setName(String name) { this.name = name; }
    public boolean isActivated() { return this.activated; }
    public boolean isActive() { return this.active; }
    public String getName() { return this.name; }
    public void setMaxRunTime(int max) { this.maxRunTime = max; }

    public void setActivated(boolean activated) { this.activated = activated; }

    @Override
    public void execute() {
        process();
    }

    public void process() {
        if (maxRunTime == -1)
            return;
        int currentTime = (int)System.currentTimeMillis();
        if (currentTime > startTime + maxRunTime * 1000) {
            end();
            sendLocalMessage(getName() + " was ended because it took too long");
        }
    }

    public abstract void handlePlayerDeath(Player p);
    public abstract boolean handleNpcDeath(NPC npc);
    public abstract void handleLogout(Player p);
    public abstract void handleLocationEntry(Player p);
    public abstract void handleLocationOutry(Player p);
    protected abstract void end();

}
