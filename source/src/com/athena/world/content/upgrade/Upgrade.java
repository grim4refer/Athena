package com.athena.world.content.upgrade;

import com.athena.model.Item;
import com.athena.model.definitions.ItemDefinition;
import com.athena.util.Misc;
import com.athena.world.World;
import com.athena.world.entity.impl.player.Player;

public class Upgrade {

    public static enum UpgItem {
        RED_DARKLORD_FULLHELM(1153, new Item[] {new Item(11316,5)}, 2, 21050),
        RED_DARKLORD_PLATEBODY(1115, new Item[] {new Item(11316,5)}, 2, 21051),
        RED_DARKLORD1_PLATELEGS(1067, new Item[] {new Item(11316,5)}, 2, 21052),
        RED_DARKLORD_GLOVES(21011, new Item[] {new Item(11316,5)}, 2, 21053),
        RED_DARKLORD_BOOTS(21012, new Item[] {new Item(11316,5)}, 2, 21054),
        WATER_LASER_SWORD(21031, new Item[] {new Item(11316,5)}, 2, 21033),
        FIRE_LASER_SWORD(21002, new Item[] {new Item(11316,5)}, 2, 21003),
        GRASS_LASER_SWORD(21030, new Item[] {new Item(11316,5)}, 2, 21032),
        DARK_PREDATOR_HELM(20086, new Item[] {new Item(11316,8)}, 2, 20081),
        DARK_PREDATOR_BODY(20087, new Item[] {new Item(11316,8)}, 2, 20082),
        DARK_PREDATOR_LEGS(20088, new Item[] {new Item(11316,8)}, 2, 20083),
        DARK_PREDATOR_BOOST(21021, new Item[] {new Item(11316,8)}, 2, 20084),
        DARK_PREDATOR_GLOVES(21020, new Item[] {new Item(11316,8)}, 2, 20085),
        DARK_PREDATOR_SCYTHE(21013, new Item[] {new Item(11316,8)}, 2, 20079),
        DEADLY_ASSASIN_HELM(20104, new Item[] {new Item(11316,12)}, 2, 20012),
        DEADLY_ASSASIN_BODY(20105, new Item[] {new Item(11316,12)}, 2, 20010),
        DEADLY_ASSASIN_LEGS(20103, new Item[] {new Item(11316,12)}, 2, 20011),
        DEADLY_ASSASIN_GLOVES(20106, new Item[] {new Item(11316,12)}, 2, 20020),
        DEADLY_ASSASIN_BOOTS(20107, new Item[] {new Item(11316,12)}, 2, 20019),
        DEADLY_ASSASIN_BLADE(20102, new Item[] {new Item(11316,12)}, 2, 20607),
        NATURE_TORVA_DEFENDER(13199, new Item[] {new Item(11316,12)}, 2, 21064),
        NATURE_TORVA_HELM(13196, new Item[] {new Item(11316,15)}, 2, 14008),
        NATURE_TORVA_BODY(13197, new Item[] {new Item(11316,15)}, 2, 14009),
        NATURE_TORVA_LEGS(13198, new Item[] {new Item(11316,15)}, 2, 14010),
        NATURE_TORVA_SWORD(21060, new Item[] {new Item(11316,15)}, 2, 21063),
        NATURE_TORVA_GLOVES(13207, new Item[] {new Item(11316,15)}, 2, 21066),
        NATURE_TORVA_BOOTS(13206, new Item[] {new Item(11316,15)}, 2, 21065),
        ROW(2572, new Item[] {new Item(11425,1)}, 2, 11527),
        ROW1(11527, new Item[] {new Item(11425,1)}, 2, 11529),
        ROW2(11529, new Item[] {new Item(11425,1)}, 2, 11531),
        ROW3(11531, new Item[] {new Item(11425,1)}, 2, 11533),
        VOID_MELLEE_HELM(11665, new Item[] {new Item(11425,2)}, 3, 21056),
        VOID_RANGE_HELM(11664, new Item[] {new Item(11425,2)}, 3, 21057),
        VOID_MAGE_HELM(11663, new Item[] {new Item(11425,2)}, 3, 21058),
        VOID_BODY_HELM(8839, new Item[] {new Item(11425,2)}, 3, 19787),
        VOID_LEGS_HELM(8840, new Item[] {new Item(11425,2)}, 3, 19788),
        VOID_GLOVES_HELM(8842, new Item[] {new Item(11425,2)}, 3, 21059),
        SLAYER1(11550, new Item[] {new Item(11316,100),new Item(11320,50)}, 2, 11549),
        SLAYER2(11549, new Item[] {new Item(11316,150),new Item(11320,100)}, 2, 11546),
        SLAYER3(11546, new Item[] {new Item(11316,200),new Item(11320,150)}, 2, 11547),
        SLAYER4(11547, new Item[] {new Item(11316,300),new Item(11320,250)}, 2, 11548);

        private Item[] reqItem;
        private int startItem;
        private int chance;
        private int EndItem;

        UpgItem(int startItem, Item[] reqItem, int chance, int EndItem) {
            this.startItem = startItem;
            this.reqItem = reqItem;
            this.chance = chance;
            this.EndItem = EndItem;
        }

        public int getStartItem() {
            return this.startItem;//should work ye okay also the boolean?
        }

        public Item[] getReqItem() {
            return this.reqItem;
        }

        public int getChance() {
            return this.chance;
        }

        public int getEndItem() {
            return this.EndItem;
        }

        public static boolean checkReq(Player p, int itemId) { //test it and let me see
            for (UpgItem item : UpgItem.values()) {
                if (item.startItem == itemId) {
                    for (int i = 0; i < item.getReqItem().length; i++) {
                        if (p.getInventory().hasItem(item.reqItem[i])) {
                            p.getInventory().delete(item.reqItem[i].getId(),item.reqItem[i].getAmount());
                        } else {
                            return false;
                        }
                    }
                    return true;
                }
            }
            return false;
        }
        public static Item[] getItemsReq(int itemId) {
            for (UpgItem item : UpgItem.values()) {
                if (item.startItem == itemId) {
                    return item.reqItem;
                }
            }
            return new Item[-1];
        }
        public static int getEndItem(int itemId) {
            for (UpgItem item : UpgItem.values()) {
                if (item.startItem == itemId) {
                    return item.EndItem;
                }
            }
            return -1;
        }
        public static void getReqs(Player p, int itemId) { //test it and let me see
            for (UpgItem item : UpgItem.values()) {
                if (item.startItem == itemId) {
                    for (int i = 0; i < item.getReqItem().length; i++) {
                     p.sendMessage("<shad=0>you require "+item.reqItem[i].getAmount()+" x "+ItemDefinition.forId(item.reqItem[i].getId()).getName()+" To upgrade this item!");
                    }
                }
            }
        }

        public static int getRandom(int itemId) {
            for (UpgItem item : UpgItem.values()) {
                if (item.startItem == itemId) {
                    return item.chance;
                }
            }
            return 0;
        }

        public static int addEndItem(int itemId) {
            for (UpgItem item : UpgItem.values()) {
                if (item.startItem == itemId) {
                    return item.EndItem;
                }
            }
            return 0;
        }

        public static int checkItem(Player p, int itemId) {
            for (UpgItem item : UpgItem.values()) {

                if (item.startItem == itemId) {
                    return itemId;
                }
            }
            p.getPacketSender().sendMessage("This item can't be upgraded!");
            return 0;
        }
        public static boolean canUpgrade(Player p, int itemId) {
            for (UpgItem item : UpgItem.values()) {

                if (item.startItem == itemId) {
                    return true;
                }
            }
            p.getPacketSender().sendMessage("This item can't be upgraded!");
            return false;
        }
    }

    public static void init(Player p, int itemId) {
        if (UpgItem.checkReq(p, itemId)) {
            int random = Misc.inclusiveRandom(1,UpgItem.getRandom(itemId));
            if (random == 1) {
                p.getPacketSender().sendMessage("" + random);
                World.sendMessage("<shad=0>@bla@[@gr3@Upgrade@bla@]@gr3@ "+p.getUsername()+" @bla@ has upgraded his @gr2@" + ItemDefinition.forId(itemId).getName()+"@bla@ !");
                p.getInventory().delete(itemId, 1);
                p.getInventory().add(UpgItem.addEndItem(itemId), 1);
            } else {
                p.getPacketSender().sendMessage("@red@[Failed] @bla@You have failed to Upgrade your @red@" + ItemDefinition.forId(itemId).getName());
                p.getPacketSender().sendMessage("" + random);
                p.getInventory().delete(itemId, 1);
            }
        } else {
            UpgItem.getReqs(p,itemId);
        }
    }

    public static boolean getItems(Player p,int id) {
            return UpgItem.checkItem(p,id)!= 0;
    }
}
