package com.athena.world.content;

import com.athena.model.Item;
import com.athena.model.definitions.ItemDefinition;
import com.athena.world.entity.impl.player.Player;

import java.util.HashSet;
import java.util.Set;

public class ToolBelt {
    private Player player;
    private Set<ToolBeltData> tools = new HashSet<>(ToolBeltData.values().length);

    public ToolBelt(Player player) {
        this.player = player;
    }

    public void addToolToBelt(int id) {
        ToolBeltData toolbelt = ToolBeltData.forId(id);
        if(toolbelt == null) {
            player.sendMessage("This item cannot be added to your toolbelt.");
            return;
        }
        String itemName = ItemDefinition.forId(id).getName();
        if(getTools().contains(toolbelt)) {
            player.sendMessage("You already have a " + itemName.toLowerCase() + " on your toolbelt.");
            return;
        }
        if(player.getInventory().getAmount(id) < toolbelt.getItem().getAmount()) {
            player.sendMessage("You need at least " + (toolbelt.getItem().getAmount() - player.getInventory().getAmount(id)) + " more " + itemName + " to add this to your toolbelt.");
            return;
        }
        player.getInventory().delete(id, toolbelt.getItem().getAmount());
        player.getToolbelt().getTools().add(toolbelt);
        player.sendMessage("A " + itemName.toLowerCase() + " has been added to your toolbelt.");
    }

    public void listTools() {
        for(ToolBeltData tools : player.getToolbelt().getTools()) {
            player.sendMessage("You have a " + tools.getItem().getDefinition().getName() + " in your toolbelt!");
        }
    }

    public Set<ToolBeltData> getTools() {
        return this.tools;
    }

    public void setTools(Set<ToolBeltData> toolbelt) {
        this.tools = toolbelt;
    }

    public enum ToolBeltData {

        HAMMER(new Item(2347, 1)),
        CHISEL(new Item(1755, 1)),
        KNIFE(new Item(946, 1)),
        TINDERBOX(new Item(590, 1)),
        SEED_DIBBER(new Item(5341, 1)),
        RAKE(new Item(5343, 1)),
        SECATEURS(new Item(5329, 1)),
        RING_MOULD(new Item(1592, 1)),
        AMULET_MOULD(new Item(1595, 1)),
        NECKLACE_MOULD(new Item(1597, 1)),
        BRACELET_MOULD(new Item(11065, 1));

        private Item item;

        ToolBeltData(Item item) {
            this.item = item;
        }

        public Item getItem() {
            return this.item;
        }

        public static ToolBeltData forId(int id) {
            for(ToolBeltData toolbelt : ToolBeltData.values()) {
                if(toolbelt.getItem().getId() == id) {
                    return toolbelt;
                }
            }
            return null;
        }

        public static boolean hasItem(Player player, int id) {
            ToolBeltData data = forId(id);
            if(data == null)
                return false;
            if(player.getToolbelt().getTools().contains(data))
                return true;
            return false;
        }
    }
}
