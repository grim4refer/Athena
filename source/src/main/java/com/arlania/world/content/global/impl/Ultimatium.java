package com.arlania.world.content.global.impl;

import com.arlania.model.Position;
import com.arlania.model.definitions.NPCDrops;
import com.arlania.world.content.global.GlobalBoss;
import com.arlania.world.entity.impl.player.Player;

/**
 * Created by Stan van der Bend on 16/12/2017.
 * project: runeworld-game-server
 * package: runeworld.world.entity.combat.strategy.global.impl
 */
public class Ultimatium extends GlobalBoss {

    private final static int NPC_ID = 8507;



    public Ultimatium() {
        super(NPC_ID);

    }
    @Override
    public  void spawn() {
        super.spawn();
        this.setConstitution(500000000);
    }

    @Override
    protected void handleDrop(Player player) {
        NPCDrops.dropItems(player, this);
    }

    @Override
    protected Position[] spawnLocations() {
        return new Position[]{
                new Position(2905, 2781, 0)
        };
    }

    @Override
    protected int minutesTillRespawn() {
        return 1;
    }

    @Override
    protected int minutesTillDespawn() {
        return 10000;
    }

    @Override
    public int maximumDrops() {
        return 15;
    }
}
