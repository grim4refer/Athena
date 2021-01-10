package com.athena.world.content;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.athena.model.Item;
import com.athena.model.definitions.NpcDefinition;
import com.athena.model.definitions.NPCDrops;
import com.athena.model.definitions.NPCDrops.DropChance;
import com.athena.model.definitions.NPCDrops.NpcDropItem;
import com.athena.world.entity.impl.player.Player;
//drop
public class DropSimulator {

    private Player player;

    public DropSimulator(Player player) {
        this.player = player;
    }

    public int npcId = 0, amount = 0;

    private Map<Integer, Integer> simulatedDrops = new HashMap<>();

    public void simulateDrops() {

        NPCDrops npcDrops = NPCDrops.forId(npcId);
        NpcDropItem[] drops = npcDrops.getDropList();
        // player.getPacketSender().resetItemsOnInterface(57400, simulatedDrops.size());
        simulatedDrops.clear();
        for (int i = 0; i < amount; i++) {

            getDrop(drops);
        }
    }

    private static List<NpcDefinition> validNpcs = new ArrayList<>();

    public void open() {
        player.getPacketSender().sendInterface(57392);
        displayNpcs();
    }

    public boolean handleButton(int id) {

        if (!(id >= -8116 && id <= -8068)) {
            return false;
        }
        int index = -1;

        if (id >= -8116) {
            index = 8116 + id;
        }

        npcId = validNpcs.get(index).getId();

        return true;

    }

    public static void initializeNpcs() {

        for (int i = 0; i < NpcDefinition.getDefinitions().length; i++) {

            NpcDefinition def = NpcDefinition.getDefinitions()[i];

            if (def == null) {
                continue;
            }

            NPCDrops npcDrops = NPCDrops.forId(def.getId());
            if (npcDrops == null) {
                System.out.println(def.getId() + " Has no drops");
                continue;
            }

            if (def.getHitpoints() >= 10000) {
                validNpcs.add(NpcDefinition.getDefinitions()[i]);
            }
        }

        final Comparator<NpcDefinition> hitpointsDescComp = Comparator.comparing(NpcDefinition::getHitpoints)
                .reversed();

        Collections.sort(validNpcs, hitpointsDescComp);

    }

    private void displayNpcs() {
        int id = 57420;

        for (NpcDefinition npc : validNpcs) {
            player.getPacketSender().sendString(id++, npc.getName());
        }
    }

    private void getDrop(NpcDropItem[] drops) {
        boolean hasRecievedDrop = false;


        for (int i = 0; i < drops.length; i++) {
            int chance = drops[i].getChance().getRandom();
            int adjustedDr;
            if (drops[i].getChance() == DropChance.ALWAYS) {
                Item drop = drops[i].getItem();

                simulatedDrops.merge(drop.getId(), drop.getAmount(), Integer::sum);

            } else if (!hasRecievedDrop) {
                Item drop = drops[i].getItem();

                simulatedDrops.merge(drop.getId(), drop.getAmount(), Integer::sum);

                hasRecievedDrop = true;
            }

        }

    }

}

