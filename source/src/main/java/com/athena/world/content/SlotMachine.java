package com.athena.world.content;

import com.athena.model.Item;
import com.athena.model.definitions.ItemDefinition;
import com.athena.util.Misc;
import com.athena.world.SlotMachineRewards;
import com.athena.world.World;
import com.athena.world.entity.impl.player.Player;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

public class SlotMachine {


    public static final int INTERFACE_ID = 29030;

    private static final int REWARD_SLOT = 29036;

    private static final int SLOTS_ITEM_CONTAINER = 29033;

    private static final int RESULT_STRING = 29047;
    private static final int WINNER_ITEM = 3981;
    private static final int LOSER_ITEM = 6570;

    //we could make it construct this list everytime, but instead we'll just cache it and shuffle it.
    private static final List<Item> WINNING = List.of(new Item[]{new Item(WINNER_ITEM), new Item(WINNER_ITEM), new Item(WINNER_ITEM)});
    private static final List<Item> LOSING = List.of(new Item[]{new Item(WINNER_ITEM), new Item(WINNER_ITEM), new Item(LOSER_ITEM)});
    private static final List<Item> LOSING1 = List.of(new Item[]{new Item(WINNER_ITEM), new Item(LOSER_ITEM), new Item(LOSER_ITEM)});
    private static final List<Item> LOSING2 = List.of(new Item[]{new Item(LOSER_ITEM), new Item(LOSER_ITEM), new Item(LOSER_ITEM)});


    private static final List<List<Item>> LOSER_LISTS = List.of(LOSING, LOSING1, LOSING2);

    private static final String LOSER_MESSAGE = "@red@Bad luck! Try again";
    private static final String WINNER_MESSAGE = "@gr1@Winner! Congratulations";


    public static void openInterface(Player player) {
        if(!player.getClickDelay().elapsed(600)) {
            return;
        }
        player.getPacketSender().resetItemsOnInterface(SLOTS_ITEM_CONTAINER, 3);
        player.getPacketSender().sendInterface(INTERFACE_ID);
    }

    public static void roll(Player player) {

        if(!player.getInventory().containsAny(691, 692, 693,15471,15472,15473)) {
            player.sendMessage("You don't have any slot tickets! You can get them by donating!");
            return;
        }
        if(player.getInventory().getFreeSlots() <= 2) {
            player.sendMessage("You need atleast 2 free inventory slots free to do this!");
            return;
        }

        player.getPacketSender().sendItemOnInterface(REWARD_SLOT, 0, 0);

        SlotGameType type = SlotGameType.getType(player);
        if(type == null)
            return;
        boolean winner = false;
        if(Misc.getRandom(100) <= type.getRarity()) {
            winner = true;
        }
        player.getInventory().delete(type.getId(), 1);
        player.getPacketSender().sendFrame126(RESULT_STRING, winner ? WINNER_MESSAGE : LOSER_MESSAGE);
        if(winner) {
            reward(player, type);
            player.getPacketSender().sendItemsOnInterface(SLOTS_ITEM_CONTAINER, 3, WINNING, true);
        } else {
            List<Item> list = new ArrayList<>(Misc.randomElement(LOSER_LISTS));
            Collections.shuffle(list);
            player.getPacketSender().sendItemsOnInterface(SLOTS_ITEM_CONTAINER, 3, list, true);
        }
    }


    private static void reward(Player player, SlotGameType type) {
        Item reward = SlotMachineRewards.getRandomItem(type);
        if(reward == null) {
            return;
        }
        World.sendMessage("[global] @red@"+player.getUsername()+" Has received a "+ ItemDefinition.forId(reward.getId()).getName()+" from slots!");

        player.getInventory().add(reward);
        player.getPacketSender().sendItemOnInterface(REWARD_SLOT, reward.getId(), reward.getAmount());
    }


    public enum SlotGameType {
        TIER_1(691, 30),
        TIER_2(692, 20),
        TIER_3(693, 15),
        AthenaToken1(15471,30),
        AthenaToken2(15472,20),
        AthenaToken3(15473,15);

        private int id;
        private int rarity;

        SlotGameType(int id, int rarity) {
            this.id = id;
            this.rarity = rarity;
        }

        public int getId() {
            return id;
        }

        public int getRarity() {
            return rarity;
        }

        public static final ImmutableSet<SlotGameType> VALUES = Sets.immutableEnumSet(EnumSet.allOf(SlotGameType.class));


        public static SlotGameType of(int id) {
            for(SlotGameType type : VALUES) {
                if(id == type.getId())
                    return type;
            }
            return null;
        }
        private static SlotGameType getType(Player player) {

            for(SlotGameType type : SlotGameType.VALUES) {
                if(player.getInventory().contains(type.getId()))
                    return type;
            }

            return null;
        }
    }


}