package com.athena.world.content.combat.strategy.impl;

import com.athena.engine.task.Task;
import com.athena.engine.task.TaskManager;
import com.athena.model.*;
import com.athena.util.Misc;
import com.athena.world.World;
import com.athena.world.content.combat.CombatContainer;
import com.athena.world.content.combat.CombatType;
import com.athena.world.content.combat.strategy.CombatStrategy;
import com.athena.world.content.global.GlobalBoss;
import com.athena.world.entity.impl.Character;
import com.athena.world.entity.impl.npc.NPC;
import com.athena.world.entity.impl.player.Player;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;

public class InfernoDragon implements CombatStrategy {


    public static final Animation ATTACK = new Animation(12252);
    public static final Animation ATTACK_SPECIAL = new Animation(12259);

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
        NPC boss;

        if(entity instanceof GlobalBoss) {
            boss = (GlobalBoss)entity;
        } else {
            boss = (NPC)entity;
        }


        if(victim.getConstitution() <= 0) {
            return true;
        }

        if(boss.isChargingAttack()) {
            return true;
        }
        final int x = boss.getPosition().getX(), y = boss.getPosition().getY();
        ObjectList<Player> playerObjectList = new ObjectArrayList<>();

        for(Player p : World.getPlayers())
            if(p!= null && p.getPosition().distanceToPoint(x, y) <= 40)
                playerObjectList.add(p);
        boss.getCombatBuilder().setVictim(Misc.randomElement(playerObjectList));

        int attack = 0;
        int ran = Misc.random(0, 2);


        switch (ran) {
            case 0:
                System.out.println("Special");
                boss.forceChat("Burn in my eternal flames!");
                TaskManager.submit(new Task(4) {
                    @Override
                    protected void execute() {
                        boss.performAnimation(ATTACK_SPECIAL);
                        for (Player player : playerObjectList) {
                            player.performGraphic(new Graphic(1618));
                            player.dealDamage(new CombatContainer(boss, victim, 1, 2, CombatType.MAGIC, true).getHits()[0].getHit());
                            player.dealDamage(new CombatContainer(boss, victim, 1, 2, CombatType.MAGIC, true).getHits()[0].getHit());
                            player.getPacketSender().sendCameraShake(3, 2, 3, 2);
                        }
                        this.stop();
                    }
                });


                TaskManager.submit(new Task(7) {
                    @Override
                    protected void execute() {
                        for (Player player : playerObjectList) {
                            player.getPacketSender().sendCameraNeutrality();
                        }
                        this.stop();
                    }
                });
                break;
            case 1:
            case 3:
            case 4:
                boss.performAnimation(ATTACK);
                for(Player p : playerObjectList) {
                    p.dealDamage(new CombatContainer(boss, victim, 1, 2, CombatType.MELEE, true).getHits()[0].getHit());
                }
            break;

            case 2:
                boss.forceChat("BRING IT ON!");
                int amt = Misc.random(15, 20);
                ObjectList<Position> positions = new ObjectArrayList<>();
                for(int i = 0; i < amt; i++)
                    positions.add(boss.getPosition().randomAround(Misc.random(-10, 10), Misc.random(-10, 10)));

                for(Position position : positions) {
                    new Projectile(boss.getPosition(), position, 0, 2735, 60, 3, 43, 31, 0).sendProjectile();
                }
                TaskManager.submit(new Task(1) {
                    @Override
                    protected void execute() {
                        for(Player p : playerObjectList) {
                            if(positions.contains(p.getPosition()))
                                    p.dealDamage(new Hit(Misc.random(200, 700)));
                        }
                        this.stop();
                    }
                });

                break;
                default:
                    System.out.println(ran);
        }



        return false;
    }

    @Override
    public int attackDelay(Character entity) {
        return 7;
    }

    @Override
    public int attackDistance(Character entity) {
        return 40;
    }

    @Override
    public CombatType getCombatType() {
        return CombatType.MIXED;
    }
}
