package com.arlania.world.content.combat.strategy.impl;

import com.arlania.engine.task.Task;
import com.arlania.engine.task.TaskManager;
import com.arlania.model.*;
import com.arlania.util.Misc;
import com.arlania.world.World;
import com.arlania.world.content.combat.CombatContainer;
import com.arlania.world.content.combat.CombatType;
import com.arlania.world.content.combat.strategy.CombatStrategy;
import com.arlania.world.content.global.GlobalBoss;
import com.arlania.world.entity.impl.Character;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.player.Player;

public class Vorago implements CombatStrategy {

    @Override
    public boolean canAttack(Character entity, Character victim) {
        return true;
    }

    @Override
    public CombatContainer attack(Character entity, Character victim) {
        return null;
    }

    @Override
    public boolean customContainerAttack(Character entity, Character victim) {

        NPC td;

        if(entity instanceof GlobalBoss) {
            td = (GlobalBoss)entity;
        } else {
            td = (NPC)entity;
        }

        if(victim.getConstitution() <= 0) {
            return true;
        }

        if(td.isChargingAttack()) {
            return true;
        }

        if(Locations.goodDistance(td.getPosition().copy(), victim.getPosition().copy(), 1) && Misc.getRandom(6) <= 4) {
            final int x = td.getPosition().getX(), y = td.getPosition().getY();
            TaskManager.submit(new Task(4) {
                @Override
                protected void execute() {
                    for(Player p : World.getPlayers())
                    {
                        if(p != null)
                        {

                            if(p.getPosition().distanceToPoint(x, y) <= 2)
                            {
                                td.forceChat("RAWRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR!");
                                p.performGraphic(new Graphic(453));
                                p.dealDamage(new Hit(Misc.random(200,550), Hitmask.RED, CombatIcon.NONE));
                            }
                        }
                    }
                    this.stop();
                }
            });

        } else if(Misc.getRandom(10) <= 6) {
            td.setChargingAttack(true);
            td.getCombatBuilder().setContainer(new CombatContainer(td, victim, 1, 2, CombatType.MAGIC, true));
            TaskManager.submit(new Task(1, td, false) {

                @Override
                protected void execute() {
                    new Projectile(td, victim, 1166, 44, 3, 43, 31, 0).sendProjectile();
                    td.setChargingAttack(false).getCombatBuilder().setAttackTimer(td.getDefinition().getAttackSpeed() - 1);
                    stop();
                }
            });
        } else {
            final int x = td.getPosition().getX(), y = td.getPosition().getY();
            TaskManager.submit(new Task(4) {
                @Override
                protected void execute() {
                    for(Player p : World.getPlayers())
                    {
                        if(p != null)
                        {

                            if(p.getPosition().distanceToPoint(x, y) <= 20)
                            {
                                p.performGraphic(new Graphic(453));
                                td.forceChat("RAWRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR!");
                                p.dealDamage(new Hit(Misc.random(990,2000), Hitmask.RED, CombatIcon.NONE));
                            }
                        }
                    }
                    this.stop();
                }
            });
        }
        return true;
    }


    @Override
    public int attackDelay(Character entity) {
        return entity.getAttackSpeed();
    }

    @Override
    public int attackDistance(Character entity) {

        // The default distance for all npcs using ranged is 6.
        if (entity instanceof GlobalBoss)  {
            return 12;
        } else {
            return 5;
        }
    }

    @Override
    public CombatType getCombatType() {
        return CombatType.MIXED;
    }
}
