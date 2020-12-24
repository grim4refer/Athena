package com.athena.world;

import com.athena.model.Item;
import com.athena.util.JsonLoader;
import com.athena.util.Misc;
import com.athena.world.content.SlotMachine;
import com.athena.world.entity.impl.player.Player;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;


public class SlotMachineRewards {


    public static final int TIER1_REWARD_CONTAINER = 29071;
    public static final int TIER2_REWARD_CONTAINER = 29072;
    public static final int TIER3_REWARD_CONTAINER = 29073;



    public static void open(Player player) {
        if(!player.getClickDelay().elapsed(600)) {
            return;
        }
        if(player.getInventory().containsAny(691, 692, 693)) {
            player.getPacketSender().sendInterface(29050);
            player.getPacketSender().sendItemsOnInterface(TIER1_REWARD_CONTAINER, 25,  TIER1_REWARDS, true);
            player.getPacketSender().sendItemsOnInterface(TIER2_REWARD_CONTAINER, 25,  TIER2_REWARDS, true);
            player.getPacketSender().sendItemsOnInterface(TIER3_REWARD_CONTAINER, 25,  TIER3_REWARDS, true);
        }
        else{
            player.getPacketSender().sendInterface(29050);
            player.getPacketSender().sendItemsOnInterface(TIER1_REWARD_CONTAINER, 25,  YanilleTier1_REWARDS, true);
            player.getPacketSender().sendItemsOnInterface(TIER2_REWARD_CONTAINER, 25,  YanilleTier2_REWARDS, true);
            player.getPacketSender().sendItemsOnInterface(TIER3_REWARD_CONTAINER, 25,  YanilleTier3_REWARDS, true);

        }

        }




    private static final List<Item> TIER1_REWARDS = new ArrayList<>();
    private static final List<Item> TIER2_REWARDS = new ArrayList<>();
    private static final List<Item> TIER3_REWARDS = new ArrayList<>();
    private static final List<Item> YanilleTier1_REWARDS  = new ArrayList<>();
    private static final List<Item> YanilleTier2_REWARDS = new ArrayList<>();
    private static final List<Item> YanilleTier3_REWARDS = new ArrayList<>();

    public static Item getRandomItem(SlotMachine.SlotGameType type) {
        switch (type) {
            case TIER_1:
                return Misc.randomElement(TIER1_REWARDS);
            case TIER_2:
                return Misc.randomElement(TIER2_REWARDS);
            case TIER_3:
                return Misc.randomElement(TIER3_REWARDS);
            case YanilleToken1:
                return Misc.randomElement(YanilleTier1_REWARDS);
            case YanilleToken2:
                return Misc.randomElement(YanilleTier2_REWARDS);
            case YanilleToken3:
                return Misc.randomElement(YanilleTier3_REWARDS);
            default:
                return null;
        }

    }

    public static void init() {
        new JsonLoader() {
            @Override
            public void load(JsonObject reader, Gson builder) {
                SlotMachine.SlotGameType gameType = SlotMachine.SlotGameType.valueOf(reader.get("TYPE").getAsString());
                int id = reader.get("ID").getAsInt();
                int amount = reader.get("AMOUNT").getAsInt();
                Item reward = new Item(id, amount);
                switch (gameType) {
                    case TIER_1:
                        TIER1_REWARDS.add(reward);
                        break;
                    case TIER_2:
                        TIER2_REWARDS.add(reward);
                        break;
                    case TIER_3:
                        TIER3_REWARDS.add(reward);
                        break;
                    case YanilleToken1:
                        YanilleTier1_REWARDS.add(reward);
                        break;
                    case YanilleToken2:
                        YanilleTier2_REWARDS.add(reward);
                        break;
                    case YanilleToken3:
                        YanilleTier3_REWARDS.add(reward);
                        break;
                }
            }
            @Override
            public String filePath() {
                return "./data/def/json/slots.json";
            }
        }.load();
        System.out.println("Loaded "+(YanilleTier1_REWARDS.size()+YanilleTier2_REWARDS.size()+YanilleTier3_REWARDS.size())+" slot rewards");
        System.out.println("Loaded "+(TIER1_REWARDS.size()+TIER2_REWARDS.size()+TIER3_REWARDS.size())+" slot rewards");
    }




}