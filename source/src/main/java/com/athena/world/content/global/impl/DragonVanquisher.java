package com.athena.world.content.global.impl;

import com.athena.model.Position;
import com.athena.model.definitions.NPCDrops;
import com.athena.world.content.global.GlobalBoss;
import com.athena.world.entity.impl.player.Player;

/**
 * Created by Stan van der Bend on 16/12/2017.
 * project: runeworld-game-server
 * package: runeworld.world.entity.combat.strategy.global.impl
 */
public class DragonVanquisher extends GlobalBoss {

    private final static int NPC_ID = 3915;



    public DragonVanquisher() {
        super(NPC_ID);

    }
    @Override
    public  void spawn() {
        super.spawn();
        this.setConstitution(200000000);
    }

    @Override
    protected void handleDrop(Player player) {
        NPCDrops.dropItems(player, this);
    }

    @Override
    protected Position[] spawnLocations() {
        return new Position[]{
                new Position(2702, 10001, 0)        
        };
    }

    @Override
    protected int minutesTillRespawn() {
        return 3;
    }

    @Override
    protected int minutesTillDespawn() {
        return 10000;
    }

    @Override
    public int maximumDrops() {
        return 5;
    }
}
