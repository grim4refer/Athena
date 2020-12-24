package com.athena.world.content.global;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.athena.GameSettings;
import com.athena.engine.task.Task;
import com.athena.engine.task.TaskManager;
import com.athena.world.World;
import com.athena.world.content.combat.CombatBuilder;
import com.athena.world.content.combat.CombatFactory;
import com.athena.world.content.global.impl.DragonVanquisher;
//import com.arlania.world.content.global.impl.ShadowLord;
//import com.arlania.world.content.global.impl.ChromeBot;
//import com.arlania.world.content.global.impl.Godzilla;
import com.athena.world.content.global.impl.Roy;
import com.athena.world.content.global.impl.Ultimatium;
import com.athena.world.entity.impl.player.Player;

/**
 * Created by Stan van der Bend on 16/12/2017.
 * project: runeworld-game-server
 * package: runeworld.world.entity.combat.strategy.global
 */
public abstract class GlobalBossHandler {

    private final static List<GlobalBoss> GLOBAL_BOSSES = new ArrayList<>();

    public static void init(){
    	register(new Roy());
    	register(new Ultimatium());
    	register(new DragonVanquisher());
    }

    private static void register(GlobalBoss globalBoss){
        GLOBAL_BOSSES.add(globalBoss);

        final long millisTillRespawn = TimeUnit.MINUTES.toMillis(globalBoss.minutesTillRespawn());
        final int cyclesTillRespawn = Math.toIntExact(millisTillRespawn / GameSettings.GAME_PROCESSING_CYCLE_RATE);

        System.out.println("A "+globalBoss.getDefinition().getName()+" will spawn in "+cyclesTillRespawn+" cycles.");

        TaskManager.submit(new Task(cyclesTillRespawn, false) {
            @Override
            protected void execute() {
                World.register(globalBoss);
                globalBoss.spawn();
                stop();
            }
        });
    }
    static void deRegister(GlobalBoss globalBoss){
        GLOBAL_BOSSES.remove(globalBoss);
        World.deregister(globalBoss);
    }

    public static void onDeath(GlobalBoss npc) {
        handleDrop(npc);
        deRegister(npc);
        register(npc);
    }
    private static void handleDrop(GlobalBoss npc) {

        World.getPlayers().forEach(p -> p.getPacketSender().sendString(26708, "<col=ff7000>Ganodermic Beast: <col=ff0000>N/A"));

        if(npc.getCombatBuilder().getDamageMap().size() == 0)
            return;

        final Map<Player, Integer> killers = new HashMap<>();

        for(Map.Entry<Player, CombatBuilder.CombatDamageCache> entry : npc.getCombatBuilder().getDamageMap().entrySet()) {

            if(entry == null)
                continue;

            final long timeout = entry.getValue().getStopwatch().elapsed();

            if(timeout > CombatFactory.DAMAGE_CACHE_TIMEOUT)
                continue;

            final Player player = entry.getKey();

            if(player.getConstitution() <= 0 || !player.isRegistered())
                continue;

            killers.put(player, entry.getValue().getDamage());
        }

        npc.getCombatBuilder().getDamageMap().clear();

        List<Map.Entry<Player, Integer>> result = sortEntries(killers);
        int count = 0;

        for(Map.Entry<Player, Integer> entry : result) {

            final Player killer = entry.getKey();

            npc.handleDrop(killer);

            if(++count >= npc.maximumDrops())
                break;
        }
    }



    private static <K, V extends Comparable<? super V>> List<Map.Entry<K, V>> sortEntries(Map<K, V> map) {
        final List<Map.Entry<K, V>> sortedEntries = new ArrayList<>(map.entrySet());
        sortedEntries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        return sortedEntries;
    }


    public static List<GlobalBoss> getBosses(){
        return GLOBAL_BOSSES;
    }
}