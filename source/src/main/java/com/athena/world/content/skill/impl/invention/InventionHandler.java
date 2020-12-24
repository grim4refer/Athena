package com.athena.world.content.skill.impl.invention;

import com.athena.model.Animation;
import com.athena.model.Item;
import com.athena.util.JsonLoader;
import com.athena.world.entity.impl.player.Player;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class InventionHandler {


    public static HashMap<Integer, InventionTier> items = new LinkedHashMap<>();

    public static final Animation anim = new Animation(3684);




    public static void dismantle(Player player, Item item) {
        player.sendMessage("Coming next week!");
        //if(!items.containsKey(item.getId())) {
        //    player.sendMessage("This item cannot be dismantled!");
        //    return;
        //}
        //if(!player.getInventory().hasItem(item))
        //    return;//Some extra verification, because why not
        //InventionTier tier = items.get(item.getId());
//
        //player.getInventory().delete(item);
        //int amt = 1;
        //if(player.getAmountDonated() >= 250)
        //    amt = 2;
        //player.performAnimation(anim);
        //player.sendMessage("You disassemble your "+ ItemDefinition.forId(item.getId()).getName()+" and receive some dust, and exp.");
        //player.getInventory().add(new Item(tier.getDustId(), amt));

    }

    public static void init() {
            new JsonLoader() {

                @Override
                public void load(JsonObject reader, Gson builder) {
                    int itemId = reader.get("itemId").getAsInt();
                    InventionTier inventionTier = InventionTier.valueOf(reader.get("tier").getAsString());
                    items.put(itemId, inventionTier);
                }

                @Override
                public String filePath() {
                    return "./data/def/json/invention/dismantling.json";
                }
            }.load();

            System.out.println("items:"+items.size());
    }


}
