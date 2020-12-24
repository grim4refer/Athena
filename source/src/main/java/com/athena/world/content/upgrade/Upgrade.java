package com.athena.world.content.upgrade;

import com.athena.model.Item;
import com.athena.model.definitions.ItemDefinition;
import com.athena.util.JsonLoader;
import com.athena.util.Misc;
import com.athena.world.World;
import com.athena.world.entity.impl.player.Player;
import com.google.common.collect.ObjectArrays;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;

import java.util.Arrays;
import java.util.List;

/**
 * @author Tamatea#0001
 */
public class Upgrade {

    private static final ObjectList<Upgradeable> UPGRADEABLES = new ObjectArrayList<>();

    /**
     * Cache the items so we don't have to make a list of them every time a player views the interface
     */
    private static final ObjectArrayList<Item> UPGRADEABLES_ITEMS = new ObjectArrayList<>();

    private static final int SUCCESS_RATE_STRING = 29006;
    private static final int REQUIREMENT_ITEM_CONTAINER = 29013;
    private static final int END_ITEM_CHILD = 29014;
    private static final int LIST_ITEM_CONTAINER = 29021;

    public static void open(Player player, int index) {
        if(!player.getClickDelay().elapsed(600))
            return;
        player.getClickDelay().reset();
        if (index > UPGRADEABLES.size())
            return;

        player.setAttribute("UPGRADEABLE", index);
        player.getPacketSender().sendInterface(29000);
        Upgradeable upgradeable = UPGRADEABLES.get(index);
        player.getPacketSender().sendItemsOnInterface(REQUIREMENT_ITEM_CONTAINER, 4, List.of(upgradeable.getReqItem()), true);
        player.getPacketSender().sendFrame126(SUCCESS_RATE_STRING, "@gr1@Success rate: @whi@"+(upgradeable.getChance())+"%");
        player.getPacketSender().sendItemOnInterface(END_ITEM_CHILD, upgradeable.endItem, upgradeable.endAmount);
        player.getPacketSender().sendItemsOnInterface(LIST_ITEM_CONTAINER, 100, UPGRADEABLES_ITEMS, true);
    }

    public static void doUpgrade(Player player) {
        int index = player.getAttribute("UPGRADEABLE");
        if(index > UPGRADEABLES.size())
            return;
        Upgradeable upgradeable = UPGRADEABLES.get(index);
        if(upgradeable == null)
            return;
        if(upgradeable.hasRequirement(player)) {
            for(Item item : upgradeable.reqItem) {
                player.getInventory().delete(item);
            }
            if(Misc.random(100) <= upgradeable.getChance()) {
            	player.getInventory().add(upgradeable.endItem, upgradeable.endAmount);
            	World.sendMessage("@bla@"+player.getUsername()+" Just upgraded their "+ ItemDefinition.forId(upgradeable.startItem).getName()+"!");
            } else {
            	player.sendMessage("@red@The upgrade failed!");
            }
        } else {
            player.sendMessage("@red@You do not have the required items to make this upgrade!");
        }
    }


    public static void init() {
        Gson GSON = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

        new JsonLoader() {

            @Override
            public void load(JsonObject reader, Gson builder) {
                int startItem = reader.get("STARTER_ITEM").getAsInt();
                Item[] items, required = GSON.fromJson(reader.getAsJsonArray("REQUIREMENTS"), Item[].class);
                if(Arrays.stream(required).noneMatch(i -> i.getId() == startItem))
                items = ObjectArrays.concat(new Item(startItem, 1), GSON.fromJson(reader.getAsJsonArray("REQUIREMENTS"), Item[].class));
                else items = required;
                int chance = reader.get("SUCCESS_CHANCE").getAsInt();
                int endItem = reader.get("END_ITEM").getAsInt();
                int endAmount = 1;
                if(reader.has("END_AMOUNT")) {
                	endAmount = reader.get("END_AMOUNT").getAsInt();
                }
              
                UPGRADEABLES.add(new Upgradeable(startItem, items, chance, endItem, endAmount));
            }

            @Override
            public String filePath() {
                return "./data/def/json/upgrading.json";
            }
        }.load();
        System.out.println("loaded "+UPGRADEABLES.size()+" upgradeables");
        for(Upgradeable u : UPGRADEABLES) {
            UPGRADEABLES_ITEMS.add(new Item(u.startItem));
        }
    }

    public static class Upgradeable {

        private Item[] reqItem;
        private int startItem;
        private int chance;
        private int endItem;
        private int endAmount;

        Upgradeable(int startItem, Item[] reqItem, int chance, int EndItem, int endAmount) {
            this.startItem = startItem;
            this.reqItem = reqItem;
            this.chance = chance;
            this.endItem = EndItem;
            this.endAmount = endAmount;
        }
        
        public int getEndAmount() {
        	return this.endAmount;
        }

        public int getChance() {
            return chance;
        }

        public int getEndItem() {
            return endItem;
        }
        public int getStartItem() {
            return startItem;
        }

        public Item[] getReqItem() {
            return reqItem;
        }
        public boolean hasRequirement(Player player) {
            return player.getInventory().containsAll(reqItem);
        }

        public static Upgradeable of(int id) {
            for(Upgradeable upgradeable : UPGRADEABLES) {
                if(upgradeable.startItem == id)
                    return upgradeable;
            }
            return null;
        }
    }


}
