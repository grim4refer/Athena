package com.arlania.world.content.chests;

import com.arlania.engine.task.Task;
import com.arlania.engine.task.TaskManager;
import com.arlania.model.Animation;
import com.arlania.model.GameObject;
import com.arlania.model.Item;
import com.arlania.model.PlayerRights;
import com.arlania.util.Misc;
import com.arlania.util.RandomUtility;
import com.arlania.world.entity.impl.player.Player;

public class BossChest {

	public static void handleChest(final Player p, final GameObject chest) {
		if(!p.getClickDelay().elapsed(2000)) 
			return;
		if(!p.getInventory().contains(20901)) {
			p.getPacketSender().sendMessage("This chest can only be opened with a Yanille key.");
			return;
		}
		p.performAnimation(new Animation(827));
		if (p.getRights() == PlayerRights.DONATOR) {
			if (Misc.getRandom(15) == 5) {
				p.getPacketSender().sendMessage("Yanille Key has been saved as a donator benefit");
			} else {
				p.getInventory().delete(20901, 1);
			}
		}
		if (p.getRights() == PlayerRights.SUPER_DONATOR || p.getRights() == PlayerRights.SUPPORT) {
			if (Misc.getRandom(12) == 5) {
				p.getPacketSender().sendMessage("Yanille Key has been saved as a donator benefit");
			} else {
				p.getInventory().delete(20901, 1);
			}
		}
		if (p.getRights() == PlayerRights.EXTREME_DONATOR || p.getRights() == PlayerRights.MODERATOR) {
			if (Misc.getRandom(9) == 5) {
				p.getPacketSender().sendMessage("Yanille Key has been saved as a donator benefit");
			} else {
				p.getInventory().delete(20901, 1);
			}
		}
		if (p.getRights() == PlayerRights.LEGENDARY_DONATOR  || p.getRights() == PlayerRights.ADMINISTRATOR) {
			if (Misc.getRandom(6) == 5) {
				p.getPacketSender().sendMessage("Yanille Key has been saved as a donator benefit");
			} else {
				p.getInventory().delete(20901, 1);
			}
		}
		if (p.getRights() == PlayerRights.UBER_DONATOR || p.getRights() == PlayerRights.DELUXE_DONATOR) {
			if (Misc.getRandom(3) == 2) {
				p.getPacketSender().sendMessage("Yanille Key has been saved as a donator benefit");
			} else {
				p.getInventory().delete(20901, 1);
			}
		}
		if (p.getRights() == PlayerRights.PLAYER || p.getRights() == PlayerRights.YOUTUBER) {
			p.getInventory().delete(20901, 1);
		}
		p.getPacketSender().sendMessage("You open the chest..");
	
					Item[] loot = itemRewards[Misc.getRandom(itemRewards.length - 1)];
					for(Item item : loot) {
						p.getInventory().add(item);
					}
				
					//CustomObjects.objectRespawnTask(p, new GameObject(173 , chest.getPosition().copy(), 10, 0), chest, 10);
				
	}

	private static final Item[][] itemRewards =  {
			{new Item(20874, 1)},
			{new Item(20875, 1)},
			{new Item(20876, 1)},
			{new Item(20877, 1)},
			{new Item(20878, 1)},
			{new Item(20884, 1)},
			{new Item(20882, 1)},
			{new Item(20883, 1)},
			{new Item(20885, 1)},
			{new Item(20886, 1)},
			{new Item(20903, 1)},
			{new Item(20887, 1)},
			{new Item(20904, 1)},
			{new Item(20905, 1)},
			{new Item(20906, 1)},
			{new Item(20907, 1)},
			{new Item(20908, 1)},
			{new Item(20909, 1)},
			{new Item(20910, 1)},
			{new Item(20884, 1)},
			{new Item(20916, 1)},
			{new Item(20917, 1)},
			{new Item(20918, 1)},
			{new Item(20919, 1)},
			{new Item(20920, 1)},
			{new Item(20888, 1)},
			{new Item(20889, 1)},
			{new Item(20890, 1)},
			{new Item(20913, 1)},
			{new Item(20914, 1)},
			{new Item(20891, 1)},
			{new Item(20892, 1)},
			{new Item(20893, 1)},
			{new Item(20894, 1)},
			{new Item(20897, 1)},
			{new Item(20895, 1)},
			{new Item(20896, 1)},
		};
}
