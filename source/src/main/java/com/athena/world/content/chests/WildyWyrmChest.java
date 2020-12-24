package com.athena.world.content.chests;

import com.athena.model.Animation;
import com.athena.model.GameObject;
import com.athena.model.Item;
import com.athena.model.PlayerRights;
import com.athena.util.Misc;
import com.athena.util.RandomUtility;
import com.athena.world.entity.impl.player.Player;

public class WildyWyrmChest {

	public static void handleChest(final Player p, final GameObject chest) {
		if(!p.getClickDelay().elapsed(2000)) 
			return;
		if(!p.getInventory().contains(19933)) {
			p.getPacketSender().sendMessage("This chest can only be opened with a WildyWyrm key.");
			return;
		}
		p.performAnimation(new Animation(827));
		if (p.getRights() == PlayerRights.DONATOR) {
			if (Misc.getRandom(15) == 5) {
				p.getPacketSender().sendMessage("WildyWyrm Key has been saved as a donator benefit");
			} else {
				p.getInventory().delete(19933, 1);
			}
		}
		if (p.getRights() == PlayerRights.SUPER_DONATOR || p.getRights() == PlayerRights.SUPPORT) {
			if (Misc.getRandom(12) == 5) {
				p.getPacketSender().sendMessage("WildyWyrm Key has been saved as a donator benefit");
			} else {
				p.getInventory().delete(19933, 1);
			}
		}
		if (p.getRights() == PlayerRights.EXTREME_DONATOR || p.getRights() == PlayerRights.MODERATOR) {
			if (Misc.getRandom(9) == 5) {
				p.getPacketSender().sendMessage("WildyWyrm Key has been saved as a donator benefit");
			} else {
				p.getInventory().delete(19933, 1);
			}
		}
		if (p.getRights() == PlayerRights.LEGENDARY_DONATOR  || p.getRights() == PlayerRights.ADMINISTRATOR) {
			if (Misc.getRandom(6) == 5) {
				p.getPacketSender().sendMessage("WildyWyrm Key has been saved as a donator benefit");
			} else {
				p.getInventory().delete(19933, 1);
			}
		}
		if (p.getRights() == PlayerRights.UBER_DONATOR || p.getRights() == PlayerRights.DELUXE_DONATOR) {
			if (Misc.getRandom(3) == 2) {
				p.getPacketSender().sendMessage("WildyWyrm Key has been saved as a donator benefit");
			} else {
				p.getInventory().delete(19933, 1);
			}
		}
		if (p.getRights() == PlayerRights.PLAYER || p.getRights() == PlayerRights.YOUTUBER) {
			p.getInventory().delete(19933, 1);
		}
		p.getPacketSender().sendMessage("You open the chest..");
	
					Item[] loot = itemRewards[Misc.getRandom(itemRewards.length - 1)];
					for(Item item : loot) {
						p.getInventory().add(item);
					}
					p.getInventory().add(5022, 1000 + RandomUtility.RANDOM.nextInt(100000));
				
					//CustomObjects.objectRespawnTask(p, new GameObject(173 , chest.getPosition().copy(), 10, 0), chest, 10);
				
	}

	private static final Item[][] itemRewards =  {
			{new Item(20080, 1)}, //INFERNAL PROTECTOR
			{new Item(20089, 1)}, //rainbow brutal whip
			{new Item(18889, 1)}, //ELITE CROSSBOW
			{new Item(11423, 1)}, //Collector Necklace
			{new Item(5022, 50000)}, //1B TICKETS
			{new Item(19933, 1)}, //WildyWyrm Key
			{new Item(2101, 1)}, //pet
			{new Item(18958, 1)}, //Golden death cape
			
		};
}
