package com.athena.world.content;

import com.athena.model.Item;
import com.athena.util.Misc;
import com.athena.util.RandomUtility;
import com.athena.world.World;
import com.athena.world.entity.impl.player.Player;

public class ClueScrolls {

	public static int CluesCompleted;
	public static String currentHint;

	public static final int[] ACTIVE_CLUES = { 2677, 2678, 2679, 2680, 2681, 2682, 2683, 2684, 2685 };
	// digging locations or show reward on reading clue??
	// to-do change name of clue scrolls in item def
	
	private static final Item[][] BASIC_STACKS =  {//Always get 1 of the following
			{new Item(380, 200)},//Noted Lobster
			{new Item(561, 150)},//Nature Runes
			{new Item(886, 275)},//Steel Arrow
			{new Item(374, 125)},//Noted Swordfish
			{new Item(563, 200)},//Law Runes
			{new Item(890, 200)},//Adamant Arrow
			{new Item(386, 55)},//Noted Shark
			{new Item(560, 135)},//Death Rune
			{new Item(892, 135)},//Rune Arrow
			{new Item(19152, 20)},//Saradomin Arrow
			{new Item(19157, 20)},//Guthix Arrow
			{new Item(19162, 20)},//Zamorak Arrow
			{new Item(15252, 20)},//Noted Rocktail
			
			{new Item(2, 500)},//Cannonballs		
	};
	
	private static final Item[][] LOW_LEVEL_REWARD =  {//Always get 2 of the following
			{new Item(454, 25)},//Coal
			{new Item(437, 50)},//Copper Ore
			{new Item(439, 50)},//Tin Ore
			{new Item(454, 50)},//Iron Ore
			{new Item(454, 30)},//Mithril Ore
			{new Item(454, 15)},//Addy Ore
			{new Item(454, 5)},//Rune Ore
			{new Item(2350, 25)},//Bronze Bar
			{new Item(2352, 25)},//Iron Bar
			{new Item(2354, 32)},//Steel Bar
			{new Item(2360, 25)},//Mithril Bar
			{new Item(2362, 12)},//Adamant Bar
			{new Item(2364, 8)},//Rune Bar
			{new Item(299, 12)},//Mithril Seeds
	};
	
	private static final Item[][] MEDIUM_LEVEL_REWARD =  {//1 in 3 chance to hit this table
			{new Item(7319, 1)},//Red Boater
			{new Item(7321, 1)},//Orange Boater
			{new Item(7323, 1)},//Green Boater
			{new Item(7325, 1)},//Blue Boater
			{new Item(7327, 1)},//Black Boater
			{new Item(537, 8)},//Dragon Bones
			{new Item(2599, 1)},//Addy (T)
			{new Item(2601, 1)},//Addy (T)
			{new Item(2603, 1)},//Addy (T)
			{new Item(2605, 1)},//Addy (T)
			{new Item(2607, 1)},//Addy (G)
			{new Item(2609, 1)},//Addy (G)
			{new Item(2611, 1)},//Addy (G)
			{new Item(2613, 1)},//Addy (G)
	};
	
	private static final Item[][] HIGH_LEVEL_REWARD =  {//1 in 10 chance to hit the table
			{new Item(18331, 2)},//Frost Dragon Bones
			{new Item(4151, 1)},//Abby Whip
			{new Item(6585, 1)},//Amulet of Fury
			{new Item(4151, 1)},//Amulet of Fury
			{new Item(5680, 1)},//Dds
			{new Item(19111, 1)},//Kiln Cape
			{new Item(2631, 1)},//Highway Mask
			{new Item(2661, 1)},//Saradomin Rune Equip
			{new Item(2663, 1)},//Saradomin Rune Equip
			{new Item(2665, 1)},//Saradomin Rune Equip
			{new Item(2667, 1)},//Saradomin Rune Equip
			{new Item(2615, 1)},//Rune (G)
			{new Item(2617, 1)},//Rune (G)
			{new Item(2619, 1)},//Rune (G)
			{new Item(2621, 1)},//Rune (G)
			{new Item(2623, 1)},//Rune (T)
			{new Item(2625, 1)},//Rune (T)
			{new Item(2627, 1)},//Rune (T)
			{new Item(2629, 1)},//Rune (T)
			{new Item(1543, 1)},//Red Key
			{new Item(2653, 1)},//Zamorak Rune Equip
			{new Item(2655, 1)},//Zamorak Rune Equip
			{new Item(2657, 1)},//Zamorak Rune Equip
			{new Item(2659, 1)},//Zamorak Rune Equip
			{new Item(2669, 1)},//Guthix Rune Equip
			{new Item(2671, 1)},//Guthix Rune Equip
			{new Item(2673, 1)},//Guthix Rune Equip
			{new Item(2675, 1)},//Guthix Rune Equip
			{new Item(8950, 1)},//Pirate Hat
			{new Item(8928, 1)},//Pirate Hat
			{new Item(13354, 1)},//Pirate Hat
	};
	private static final Item[][] GOD_LEVEL_REWARD =  {//1 in 800 chance to hit the table
			{new Item(19143, 1)},//Saradomin Bow
			{new Item(19146, 1)},//Guthix Bow
			{new Item(19149, 1)},//Zamorak Bow
			{new Item(11694, 1)},//Armadyl Godsword
			{new Item(11696, 1)},//Bandos Godsword
			{new Item(11698, 1)},//Saradomin Godsword
			{new Item(11670, 1)},//Zamorak Godsword
			
	};
	private static final Item[][] EXTREME_LEVEL_REWARD =  {//1 in 1500 chance to hit the table
			{new Item(10330, 1)},//3rd Age 
			{new Item(10332, 1)},//3rd Age 
			{new Item(10334, 1)},//3rd Age 
			{new Item(10336, 1)},//3rd Age 
			{new Item(10338, 1)},//3rd Age 
			{new Item(10340, 1)},//3rd Age 
			{new Item(10342, 1)},//3rd Age 
			{new Item(10344, 1)},//3rd Age 
			{new Item(10346, 1)},//3rd Age 
			{new Item(10348, 1)},//3rd Age 
			{new Item(10350, 1)},//3rd Age 
			{new Item(10352, 1)},//3rd Age 
			{new Item(19311, 1)},//3rd Age 
			{new Item(12926, 1)},//Blowpipe
			{new Item(14044, 1)},//Black Phat
			{new Item(14050, 1)},//Black Santa
			{new Item(4084, 1)},//Sled
			{new Item(19143, 1)},//Saradomin Bow
			{new Item(19146, 1)},//Guthix Bow
			{new Item(19149, 1)},//Zamorak Bow
	};

	private static final String[] HINTS = { "Dig somewhere in the edgeville bank",
			"Dig near the mining guild teleport", "Dig somewhere near the duel arena tele",
			"Dig near one of the slayer masters", "Dig in the area you might see fisherman",
			"Dig near the tele to get chaotics", "Dig near the king of dragons",
			"Dig near the second minigame teleport", "Dig where players plant flowers" };

	public static void addClueRewards(Player player) {
		if (player.getInventory().getFreeSlots() < 6) {
			player.getPacketSender().sendMessage("You must have atleast 6 free inventory spaces!");
			return;
		}
		player.getInventory().delete(2714, 1);//Deletes Clue Casket
		Item[] basicLoot = BASIC_STACKS[Misc.getRandom(200)];
		for(Item item : basicLoot) {
			player.getInventory().add(item);
		}

		RandomUtility.RANDOM.nextInt(1);
		if (RandomUtility.RANDOM.nextInt(3) == 2) {
		Item[] mediumLoot = MEDIUM_LEVEL_REWARD[Misc.getRandom(200)];
		for(Item item : mediumLoot) {
			player.getInventory().add(item);
		}		
		}
		
		else if (RandomUtility.RANDOM.nextInt(10) == 5) {
		Item[] highLoot = HIGH_LEVEL_REWARD[Misc.getRandom(HIGH_LEVEL_REWARD.length - 1)];
		for(Item item : highLoot) {
			player.getInventory().add(item);
		}		
		}
		
		else if (RandomUtility.RANDOM.nextInt(800) == 600) {
		Item[] godLoot = GOD_LEVEL_REWARD[Misc.getRandom(GOD_LEVEL_REWARD.length - 1)];
		for(Item item : godLoot) {
			player.getInventory().add(item);
			World.sendMessage("@or3@[Clue Scroll]@bla@ "+player.getUsername()+ " has recieved a gift from the Gods!");
		}		
		}
		else if (RandomUtility.RANDOM.nextInt(1500) == 1288) {
		Item[] extremeLoot = EXTREME_LEVEL_REWARD[Misc.getRandom(EXTREME_LEVEL_REWARD.length - 1)];
		for(Item item : extremeLoot) {
			player.getInventory().add(item);
			World.sendMessage("@or3@[Clue Scroll]@bla@ "+player.getUsername()+ " has recieved an Extreme Rare!");
		}		
		}

	}

	public static void giveHint(Player player, int itemId) {
		if (itemId == 2677) {
			int index = 0;
			currentHint = HINTS[index];
			player.getPacketSender().sendInterface(47700);
			player.getPacketSender().sendString(47704, currentHint);
			player.getPacketSender().sendString(47703, " " + CluesCompleted);

		}
		if (itemId == 2678) {
			int index = 1;
			currentHint = HINTS[index];
			player.getPacketSender().sendInterface(47700);
			player.getPacketSender().sendString(47704, currentHint);
			player.getPacketSender().sendString(47703, " " + CluesCompleted);

		}
		if (itemId == 2679) {
			int index = 2;
			currentHint = HINTS[index];
			player.getPacketSender().sendInterface(47700);
			player.getPacketSender().sendString(47704, currentHint);
			player.getPacketSender().sendString(47703, " " + CluesCompleted);

		}
		if (itemId == 2680) {
			int index = 3;
			currentHint = HINTS[index];
			player.getPacketSender().sendInterface(47700);
			player.getPacketSender().sendString(47704, currentHint);
			player.getPacketSender().sendString(47703, " " + CluesCompleted);

		}
		if (itemId == 2681) {
			int index = 4;
			currentHint = HINTS[index];
			player.getPacketSender().sendInterface(47700);
			player.getPacketSender().sendString(47704, currentHint);
			player.getPacketSender().sendString(47703, " " + CluesCompleted);

		}
		if (itemId == 2682) {
			int index = 5;
			currentHint = HINTS[index];
			player.getPacketSender().sendInterface(47700);
			player.getPacketSender().sendString(47704, currentHint);
			player.getPacketSender().sendString(47703, " " + CluesCompleted);

		}
		if (itemId == 2683) {
			int index = 6;
			currentHint = HINTS[index];
			player.getPacketSender().sendInterface(47700);
			player.getPacketSender().sendString(47704, currentHint);
			player.getPacketSender().sendString(47703, " " + CluesCompleted);

		}
		if (itemId == 2684) {
			int index = 7;
			currentHint = HINTS[index];
			player.getPacketSender().sendInterface(47700);
			player.getPacketSender().sendString(47704, currentHint);
			player.getPacketSender().sendString(47703, " " + CluesCompleted);

		}
		if (itemId == 2685) {
			int index = 8;
			currentHint = HINTS[index];
			player.getPacketSender().sendInterface(47700);
			player.getPacketSender().sendString(47704, currentHint);
			player.getPacketSender().sendString(47703, " " + CluesCompleted);

		}

	}

	public static void setCluesCompleted(int CluesCompleted, boolean add) {
		if (add)
			CluesCompleted += CluesCompleted;
		else
			ClueScrolls.CluesCompleted = CluesCompleted;
	}

	public static void incrementCluesCompleted(double amount) {
		CluesCompleted += amount;
	}

	public static int getCluesCompleted() {
		return CluesCompleted;
	}

}
