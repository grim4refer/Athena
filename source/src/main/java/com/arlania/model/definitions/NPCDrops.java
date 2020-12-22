package com.arlania.model.definitions;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import com.arlania.model.GameMode;
import com.arlania.model.Graphic;
import com.arlania.model.GroundItem;
import com.arlania.model.Item;
import com.arlania.model.PlayerRights;
import com.arlania.model.Position;
import com.arlania.model.Skill;
import com.arlania.model.Locations.Location;
import com.arlania.model.container.impl.Bank;
import com.arlania.model.container.impl.Equipment;
import com.arlania.util.JsonLoader;
import com.arlania.util.Misc;
import com.arlania.util.RandomUtility;
import com.arlania.world.World;
import com.arlania.world.content.*;
import com.arlania.world.content.DropLog.DropLogEntry;
//import com.arlania.world.content.auras.AuraData.AuraType;
import com.arlania.world.content.clan.ClanChatManager;
import com.arlania.world.content.combat.strategy.impl.Nex;
import com.arlania.world.content.minigames.impl.WarriorsGuild;
import com.arlania.world.content.skill.impl.prayer.BonesData;
import com.arlania.world.content.skill.impl.slayer.SlayerTasks;
import com.arlania.world.content.skill.impl.summoning.BossPets;
import com.arlania.world.content.skill.impl.summoning.CharmingImp;
import com.arlania.world.entity.impl.GroundItemManager;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.player.Player;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Controls the npc drops
 *
 * @author 2012 <http://www.rune-server.org/members/dexter+morgan/>, Gabbe &
 *         Samy
 *
 */
public class NPCDrops {

	/**
	 * The map containing all the npc drops.
	 */
	private static Map<Integer, NPCDrops> dropControllers = new HashMap<Integer, NPCDrops>();

	public static boolean doubleDrop = false;
	public static boolean yell = true;
	public static JsonLoader parseDrops() {

		ItemDropAnnouncer.init();

		return new JsonLoader() {

			@Override
			public void load(JsonObject reader, Gson builder) {
				int[] npcIds = builder.fromJson(reader.get("npcIds"),
						int[].class);
				NpcDropItem[] drops = builder.fromJson(reader.get("drops"),
						NpcDropItem[].class);

				NPCDrops d = new NPCDrops();
				d.npcIds = npcIds;
				d.drops = drops;
				for (int id : npcIds) {
					dropControllers.put(id, d);
//					System.out.println("put: "+id+" "+d);
				}
			}

			@Override
			public String filePath() {
				return "./data/def/json/drops.json";
			}
		};
	}

	/**
	 * The id's of the NPC's that "owns" this class.
	 */
	private int[] npcIds;

	/**
	 * All the drops that belongs to this class.
	 */
	private NpcDropItem[] drops;

	/**
	 * Gets the NPC drop controller by an id.
	 *
	 * @return The NPC drops associated with this id.
	 */
	public static NPCDrops forId(int id) {
		return dropControllers.get(id);
	}

	public static Map<Integer, NPCDrops> getDrops() {
		return dropControllers;
	}

	/**
	 * Gets the drop list
	 *
	 * @return the list
	 */
	public NpcDropItem[] getDropList() {
		return drops;
	}

	/**
	 * Gets the npcIds
	 *
	 * @return the npcIds
	 */
	public int[] getNpcIds() {
		return npcIds;
	}

	/**
	 * Represents a npc drop item
	 */
	public static class NpcDropItem {

		/**
		 * The id.
		 */
		private final int id;

		/**
		 * Array holding all the amounts of this item.
		 */
		private final int[] count;

		/**
		 * The chance of getting this item.
		 */
		private final int chance;

		/**
		 * New npc drop item
		 *
		 * @param id
		 *            the item
		 * @param count
		 *            the count
		 * @param chance
		 *            the chance
		 */
		public NpcDropItem(int id, int[] count, int chance) {
			this.id = id;
			this.count = count;
			this.chance = chance;
		}

		/**
		 * Gets the item id.
		 *
		 * @return The item id.
		 */
		public int getId() {
			return id;
		}

		/**
		 * Gets the chance.
		 *
		 * @return The chance.
		 */
		public int[] getCount() {
			return count;
		}

		/**
		 * Gets the chance.
		 *
		 * @return The chance.
		 */
		public DropChance getChance() {
			switch (chance) {
				case 1:
					return DropChance.ALMOST_ALWAYS; // 50% <-> 1/2
				case 2:
					return DropChance.VERY_COMMON; // 20% <-> 1/5
				case 3:
					return DropChance.COMMON; // 5% <-> 1/20
				case 4:
					return DropChance.UNCOMMON; // 2% <-> 1/50
				case 5:
					return DropChance.RARE; // 0.5% <-> 1/200
				case 6:
					return DropChance.LEGENDARY; // 0.2% <-> 1/500
				case 7:
					return DropChance.LEGENDARY_2;
				case 8:
					return DropChance.LEGENDARY_3;
				case 9:
					return DropChance.LEGENDARY_4;
				case 10:
					return DropChance.LEGENDARY_5;
				case 1000:
					return DropChance.PET;
				case 1500:
					return DropChance.GROUDON;

				default:
					return DropChance.ALWAYS; // 100% <-> 1/1
			}
		}

		public WellChance getWellChance() {
			switch (chance) {
				case 1:
					return WellChance.ALMOST_ALWAYS; // 50% <-> 1/2
				case 2:
					return WellChance.VERY_COMMON; // 20% <-> 1/5
				case 3:
					return WellChance.COMMON; // 5% <-> 1/20
				case 4:
					return WellChance.UNCOMMON; // 2% <-> 1/50
				case 5:
					return WellChance.RARE; // 0.5% <-> 1/200
				case 6:
					return WellChance.LEGENDARY; // 0.2% <-> 1/320
				case 7:
					return WellChance.LEGENDARY_2; // 1/410
				case 8:
					return WellChance.LEGENDARY_3; // 1/850
				case 9:
					return WellChance.LEGENDARY_4; // 1/680
				case 10:
					return WellChance.LEGENDARY_5; // 1/900
				case 1000:
					return WellChance.PET;
				case 1500:
					return WellChance.GROUDON;
				default:
					return WellChance.ALWAYS; // 100% <-> 1/1
			}
		}

		/**
		 * Gets the item
		 *
		 * @return the item
		 */
		public Item getItem() {
			int amount = 0;
			for (int i = 0; i < count.length; i++)
				amount += count[i];
			if (amount > count[0])
				amount = count[0] + RandomUtility.getRandom(count[1]);
			return new Item(id, amount);
		}
	}

	public enum DropChance {
		ALWAYS(0), ALMOST_ALWAYS(10), VERY_COMMON(20), COMMON(30), UNCOMMON(50), RARE(100), LEGENDARY(200), LEGENDARY_2(250), LEGENDARY_3(300), LEGENDARY_4(350), LEGENDARY_5(450),PET(1000),GROUDON(1500);


		DropChance(int randomModifier) {
			this.random = randomModifier;
		}

		private int random;

		public int getRandom() {
			return this.random;
		}
	}

	public enum WellChance {
		ALWAYS(0), ALMOST_ALWAYS(2), VERY_COMMON(3), COMMON(8), UNCOMMON(15), NOTTHATRARE(
				35), RARE(50), LEGENDARY(75), LEGENDARY_2(100), LEGENDARY_3(125), LEGENDARY_4(340), LEGENDARY_5(800),PET(1000),GROUDON(1500);


		WellChance(int randomModifier) {
			this.random = randomModifier;
		}

		private int random;

		public int getRandom() {
			return this.random;
		}
	}

	/**
	 * Drops items for a player after killing an npc. A player can max receive
	 * one item per drop chance.
	 *
	 * @param p
	 *            Player to receive drop.
	 * @param npc
	 *            NPC to receive drop FROM.
	 */
	public static void dropItems(Player p, NPC npc) {
		if (npc.getLocation() == Location.WARRIORS_GUILD)
			WarriorsGuild.handleDrop(p, npc);
		NPCDrops drops = NPCDrops.forId(npc.getId());
		if (drops == null)
			return;
		final boolean goGlobal = p.getPosition().getZ() >= 0 && p.getPosition().getZ() < 4;
		final boolean ringOfWealth = p.getEquipment().get(Equipment.RING_SLOT).getId() == 2572;
		final boolean ringOfWealth1 = p.getEquipment().get(Equipment.RING_SLOT).getId() == 11527;
		final boolean ringOfWealth2 = p.getEquipment().get(Equipment.RING_SLOT).getId() == 11529;
		final boolean ringOfWealth3 = p.getEquipment().get(Equipment.RING_SLOT).getId() == 11531;
		final boolean ringOfWealthLucky = p.getEquipment().get(Equipment.RING_SLOT).getId() == 11533;
		final boolean ringOfGods = p.getEquipment().get(Equipment.RING_SLOT).getId() == 12601;
		final boolean amuletOfInsanity = p.getEquipment().get(Equipment.AMULET_SLOT).getId() == 11422;
		final Position npcPos = npc.getPosition().copy();
		boolean[] dropsReceived = new boolean[30];


		if (npc.getId () == 3) {
			p.moveTo(new Position(2535, 2501, 1));
			p.getInventory().add(19101, 1 + Misc.getRandom(9)).add(5022, 1 + Misc.getRandom(75000));
			p.sendMessage("@mag@You have just defeated Luigi.");
			p.sendMessage("@mag@Congratulations!");
		}
		if (npc.getId () == 4329) {
			p.giveItem(21074, 1);
			p.giveItem(12852, 10);
			p.moveTo(new Position(2534, 2532, 1));
			p.sendMessage("@mag@Congratulations you have just received a Raids Key!");
			p.sendMessage("@mag@If you want to do raids again just type ::raids");
			p.sendMessage("@red@Final Wave");
		}
		if (npc.getId () == 4331) {
			p.giveItem(12852, 1);
			p.getInventory().add(5022, 1 + Misc.getRandom(1000));
			p.moveTo(new Position(3134, 3875, 0));
			p.sendMessage("@red@Wave 2");
		}
		if (npc.getId () == 4332) {
			p.giveItem(12852, 2);
			p.getInventory().add(5022, 1 + Misc.getRandom(2000));
			p.moveTo(new Position(3323, 3880, 0));
			p.sendMessage("@red@Wave 3");
		}
		if (npc.getId () == 4333) {
			p.giveItem(12852, 3);
			p.getInventory().add(5022, 1 + Misc.getRandom(3000));
			p.moveTo(new Position(3256, 3874, 0));
			p.sendMessage("@red@Wave 4");
		}
		if (npc.getId () == 4334) {
			p.giveItem(12852, 4);
			p.getInventory().add(5022, 1 + Misc.getRandom(4000));
			p.moveTo(new Position(3218, 3880, 0));
			p.sendMessage("@red@Wave 5");
		}
		if (npc.getId () == 4335) {
			p.giveItem(12852, 5);
			p.getInventory().add(5022, 1 + Misc.getRandom(5000));
			p.moveTo(new Position(3156, 3886, 0));
			p.sendMessage("@red@Wave 6");
		}
		if (npc.getId () == 4336) {
			p.giveItem(12852, 7);
			p.getInventory().add(5022, 1 + Misc.getRandom(6000));
			p.moveTo(new Position(3188, 3890, 0));
			p.sendMessage("@red@Wave 7");
		}
		if (npc.getId () == 4330) {
			p.giveItem(12852, 9);
			p.getInventory().add(5022, 1 + Misc.getRandom(7000));
			p.moveTo(new Position(3340, 3906, 0));
			p.sendMessage("@red@Wave 8");
		}
		
		if (drops.getDropList().length > 0 && p.getPosition().getZ() >= 0 && p.getPosition().getZ() < 4) {
			
			if(Misc.inclusiveRandom(1,100) == 1) {

				if (p.getEquipment().get(Equipment.AMULET_SLOT).getId() == 11423 || p.getEquipment().get(Equipment.AMULET_SLOT).getId() == 20871 || p.getEquipment().get(Equipment.AMULET_SLOT).getId() == 20872) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
					p.giveItem(7956, 1);
				} else {
					casketDrop(p, npc.getDefinition().getCombatLevel(), npcPos);
				}
			}
		}
		if (drops.getDropList().length > 0 && p.getPosition().getZ() >= 0 && p.getPosition().getZ() < 4) {
			
			if(Misc.inclusiveRandom(1,1) == 1) {
				if (p.getEquipment().get(Equipment.AMULET_SLOT).getId() == 11423 || p.getEquipment().get(Equipment.AMULET_SLOT).getId() == 20871 || p.getEquipment().get(Equipment.AMULET_SLOT).getId() == 20872) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
					p.giveItem(11316, 1);
				} else {
					p.giveItem(11316, 1);
					monsterfragmentDrop(p, npc.getDefinition().getCombatLevel(), npcPos);
				}
			}
		}
		if (drops.getDropList().length > 0 && p.getPosition().getZ() >= 0 && p.getPosition().getZ() < 4) {

			if(Misc.inclusiveRandom(1,100) == 1) {

				if (p.getEquipment().get(Equipment.AMULET_SLOT).getId() == 11423 || p.getEquipment().get(Equipment.AMULET_SLOT).getId() == 20871 || p.getEquipment().get(Equipment.AMULET_SLOT).getId() == 20872) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
					int clueId = CLUESBOY[Misc.getRandom(CLUESBOY.length - 1)];
					p.giveItem(clueId, 1);
				} else {
					clueDrop(p, npc.getDefinition().getCombatLevel(), npcPos);
				}
			}

		}

		if(Misc.inclusiveRandom(1,100) == 1) {
			if (p.getEquipment().get(Equipment.AMULET_SLOT).getId() == 11423 || p.getEquipment().get(Equipment.AMULET_SLOT).getId() == 20871 || p.getEquipment().get(Equipment.AMULET_SLOT).getId() == 20872) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
				p.giveItem(21006,1);
			} else {
				drop(p, new Item(21006, 1), npc, npc.getPosition(), false);
			}
//			World.sendMessage("<shad=0>@bla@[@red@Mythical@bla@] <shad=0>@red@"+ p.getUsername()
//					+ "@bla@ has just received <shad=0>@red@ a Mythical hilt shard@bla@ from <shad=0>@red@@bla@"+ npc.getDefinition().getName());
		}
		if(Misc.inclusiveRandom(1,100) == 1) {
			if (p.getEquipment().get(Equipment.AMULET_SLOT).getId() == 11423 || p.getEquipment().get(Equipment.AMULET_SLOT).getId() == 20871 || p.getEquipment().get(Equipment.AMULET_SLOT).getId() == 20872) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
				p.giveItem(21007,1);
			} else {
				drop(p, new Item(21007, 1), npc, npc.getPosition(), false);
			}
//			World.sendMessage("<shad=0>@bla@[@red@Mythical@bla@] <shad=0>@red@"+ p.getUsername()
//					+ "@bla@ has just received <shad=0>@red@ a Mythical edge shard@bla@ from <shad=0>@red@@@bla@"+ npc.getDefinition().getName());
		}
//		if(Misc.inclusiveRandom(1,1000) == 1) {
//			if (p.getEquipment().get(Equipment.AMULET_SLOT).getId() == 11423) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
//				p.giveItem(11425,1);
//			} else {
//				drop(p, new Item(11425, 1), npc, npc.getPosition(), false);
//			}
//			World.sendMessage("<shad=0>@bla@[@red@Scroll@bla@] <shad=0>@red@"+ p.getUsername()
//					+ "@bla@ has just received <shad=0>@red@ a Scroll of efficiency@bla@ from <shad=0>@red@@bla@"+ npc.getDefinition().getName());
//		}
		if (p.getSlayer().getSlayerTask().getNpcId() == npc.getId()) {

			if (Misc.inclusiveRandom(1, 75) == 1) {
				if (p.getEquipment().get(Equipment.AMULET_SLOT).getId() == 11423 || p.getEquipment().get(Equipment.AMULET_SLOT).getId() == 20871 || p.getEquipment().get(Equipment.AMULET_SLOT).getId() == 20872) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
					p.giveItem(2717, 1);
				} else {
					drop(p, new Item(2717, 1), npc, npc.getPosition(), false);
				}
				World.sendMessage("<shad=0>@red@[@bla@Slayer@red@] <shad=0>@bla@" + p.getUsername()
						+ "@red@ has just received <shad=0>@bla@ a Slayer casket@red@ from <shad=0>@bla@@bla@" + npc.getDefinition().getName());
			}

			if (Misc.inclusiveRandom(1, 75) == 1) {
				if (p.getEquipment().get(Equipment.AMULET_SLOT).getId() == 11423 || p.getEquipment().get(Equipment.AMULET_SLOT).getId() == 20871 || p.getEquipment().get(Equipment.AMULET_SLOT).getId() == 20872) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
					p.giveItem(20900, 1);
				} else {
					drop(p, new Item(20900, 1), npc, npc.getPosition(), false);
				}
				World.sendMessage("<shad=0>@red@[@bla@Slayer@red@] <shad=0>@bla@" + p.getUsername()
						+ "@red@ has just received <shad=0>@bla@ a Slayer key@red@ from <shad=0>@bla@@bla@" + npc.getDefinition().getName());
			}




	}

		for (int i = 0; i < drops.getDropList().length; i++) {
			if (drops.getDropList()[i].getItem().getId() <= 0 || drops.getDropList()[i].getItem().getId() > ItemDefinition.getMaxAmountOfItems() || drops.getDropList()[i].getItem().getAmount() <= 0) {
				continue;
			}

			DropChance dropChance = drops.getDropList()[i].getChance();


			if (dropChance == DropChance.ALWAYS) {
				drop(p, drops.getDropList()[i].getItem(), npc, npcPos, goGlobal);
			} else {
				if(shouldDrop(p,npc,drops.getDropList().length, drops.getDropList(), dropsReceived, dropChance, ringOfWealth, ringOfWealth1, ringOfWealth2, ringOfWealth3, ringOfWealthLucky, amuletOfInsanity, ringOfGods, p.getGameMode() == GameMode.IRONMAN || p.getGameMode() == GameMode.HARDCORE_IRONMAN, p.getRights())) {
					if(shouldDoubleDrop(p, dropsReceived, dropChance, ringOfWealth, ringOfWealth1, ringOfWealth2, ringOfWealth3, ringOfWealthLucky, amuletOfInsanity, ringOfGods, p.getGameMode() == GameMode.IRONMAN || p.getGameMode() == GameMode.HARDCORE_IRONMAN, p.getRights())) {
						doubleDrop = true;
						drop(p, drops.getDropList()[i].getItem(), npc, npcPos, goGlobal);
						yell = false;
						drop(p, drops.getDropList()[i].getItem(), npc, npcPos, goGlobal);
						doubleDrop = false;
						yell = true;
					} else {
						drop(p, drops.getDropList()[i].getItem(), npc, npcPos, goGlobal);
						dropsReceived[dropChance.ordinal()] = true;
					}
				}
			}
		}


		if (WellOfWealth.isActive()) {
			for (int i = 0; i < drops.getDropList().length; i++) {
				if (drops.getDropList()[i].getItem().getId() <= 0 || drops.getDropList()[i].getItem().getId() > ItemDefinition.getMaxAmountOfItems() || drops.getDropList()[i].getItem().getAmount() <= 0) {
					continue;
				}

				WellChance wellChance = drops.getDropList()[i].getWellChance();

				if (wellChance == WellChance.ALWAYS) {
					drop(p, drops.getDropList()[i].getItem(), npc, npcPos, goGlobal);
				} else {
					if(shouldRecieveDrop(dropsReceived, wellChance, ringOfWealth, ringOfWealth1, ringOfWealth2, ringOfWealth3, ringOfWealthLucky, amuletOfInsanity, ringOfGods, p.getGameMode() == GameMode.IRONMAN || p.getGameMode() == GameMode.HARDCORE_IRONMAN, p.getRights())) {
						drop(p, drops.getDropList()[i].getItem(), npc, npcPos, goGlobal);
						dropsReceived[wellChance.ordinal()] = true;
					}
				}
			}
		}

	}

	public static boolean shouldDrop(Player p,NPC npc, double drops,NpcDropItem[] c, boolean[] b, DropChance chance,
									 boolean ringOfWealth, boolean ringOfWealth1, boolean ringOfWealth2, boolean ringOfWealth3, boolean ringOfWealthLucky, boolean amuletOfInsanity, boolean ringOfGods, boolean extreme, PlayerRights rights) {

		int x = 0;
		double random = chance.getRandom(); //pull the chance from the table
		double drBoost = NPCDrops.getDroprate(p);
		for (int i = 0; i < drops; i++) {
			if (random == c[i].getChance().getRandom()) {
				x++;
			}
		}
		random *= x;
		p.setDroprate(drBoost);

		if (p.getTransform() == npc.getId()) {
		    drBoost += 5;
            p.sendMessage("Your soul boosted your droprate by 5% on this npc!");
        }
		if (p.getRights() == PlayerRights.DEDICATED_DONATOR)
			drBoost += 60;
		
		/*if (p.auraManager.isAura(AuraType.DROP_BOOST_T5))
			drBoost += 50;
		else if (p.auraManager.isAura(AuraType.DROP_BOOST_T4))
			drBoost += 25;
		else if (p.auraManager.isAura(AuraType.DROP_BOOST_T3))
			drBoost += 15;
		else if (p.auraManager.isAura(AuraType.DROP_BOOST_T2))
			drBoost += 10;
		else if (p.auraManager.isAura(AuraType.DROP_BOOST_T1))
			drBoost += 5;
		
		if (p.auraManager.isAura(AuraType.ULTRA_DROPS_T5))
			drBoost += 50;
		else if (p.auraManager.isAura(AuraType.ULTRA_DROPS_T4))
			drBoost += 25;
		else if (p.auraManager.isAura(AuraType.ULTRA_DROPS_T3))
			drBoost += 15;
		else if (p.auraManager.isAura(AuraType.ULTRA_DROPS_T2))
			drBoost += 10;
		else if (p.auraManager.isAura(AuraType.ULTRA_DROPS_T1))
			drBoost += 5;*/
		
		if (p.getSlayer().getSlayerTask().getNpcId() == npc.getId()) {
			if(p.getEquipment().get(Equipment.HEAD_SLOT).getId() == 11550){
					drBoost += 1;
				//p.sendMessage("Your slayer helm boosted your droprate by 1%");
			}if(p.getEquipment().get(Equipment.HEAD_SLOT).getId() == 11549){
				drBoost += 2;
				//p.sendMessage("Your slayer helm boosted your droprate by 2%");
			}if(p.getEquipment().get(Equipment.HEAD_SLOT).getId() == 11546){
				drBoost += 3;
				//p.sendMessage("Your slayer helm boosted your droprate by 3%");
			}if(p.getEquipment().get(Equipment.HEAD_SLOT).getId() == 11547){
				drBoost += 4;
				//p.sendMessage("Your slayer helm boosted your droprate by 4%");
			}if(p.getEquipment().get(Equipment.HEAD_SLOT).getId() == 11548){
				drBoost += 5;
				//p.sendMessage("Your slayer helm boosted your droprate by 5%");
			}
	}
			random = (int)random / ((double)(100+drBoost)/100);

		return (!b[chance.ordinal()] && Misc.getRandom((int) random) == 0) ; //return true if random between 0 & table value is 1.
	}

	public static double getDoubleDr(Player p){
		double drBoost = 0;
		final boolean ringOfWealth = p.getEquipment().get(Equipment.RING_SLOT).getId() == 2572;

		if(p.getRights() == PlayerRights.PLAYER) {
			drBoost = 2;
		}

		if(p.getRights() == PlayerRights.OWNER) {
			drBoost = 100;
		}

		if(p.getRights() == PlayerRights.DONATOR) {
			drBoost = 2;
		}
		if(p.getRights() == PlayerRights.EXTREME_DONATOR) {
			drBoost = 4;
		}
		if(p.getRights() == PlayerRights.SUPER_DONATOR) {
			drBoost = 4;
		}
		if(p.getRights() == PlayerRights.LEGENDARY_DONATOR) {
			drBoost = 5;
		}
		if(p.getRights() == PlayerRights.UBER_DONATOR) {
			drBoost = 10;
		}
		if(p.getRights() == PlayerRights.DELUXE_DONATOR) {
			drBoost = 25;
		}
		if(p.getRights() == PlayerRights.ADMINISTRATOR) {
			drBoost = 25;
		}
		if(p.getGameMode() == GameMode.IRONMAN) {
			drBoost += 5;
		}if(p.getGameMode() == GameMode.HARDCORE_IRONMAN) {
			drBoost += 10;
		}
		if(p.getSummoned() == 1300) {
			drBoost += 2;
		}
		

		//cerberus || spyro
		if(p.getSummoned() == 3063 || p.getSummoned() == 2075 || p.getSummoned() == 2073) {
			drBoost += 10;
		}
		if(p.getSummoned() == 1302) {
			drBoost += 15;
		}
		if(p.getSummoned() == 6301) {
			drBoost += 12;
		}


		
        if(p.getSummoned() > 6353) {
            drBoost += 8;
        }   if(p.getSummoned() > 6304) {
            drBoost += 4;
        }  if(p.getSummoned() > 3053) {
            drBoost += 3;
        }
		if (ringOfWealth) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 2;
		}
		if (p.getEquipment().get(Equipment.RING_SLOT).getId() == 11531) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 4;
		}if (p.getEquipment().get(Equipment.RING_SLOT).getId() == 11533) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 8;
		}
		if (p.getEquipment().get(Equipment.AMULET_SLOT).getId() == 11423) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 3;
		}
		if (p.getEquipment().get(Equipment.AMULET_SLOT).getId() == 20871) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 7;
		}
		if (p.getEquipment().get(Equipment.AMULET_SLOT).getId() == 20872) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 7;
		}
		if (p.getEquipment().get(Equipment.FEET_SLOT).getId() == 20942) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.FEET_SLOT).getId() == 20936) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.FEET_SLOT).getId() == 20934) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.FEET_SLOT).getId() == 20933) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.FEET_SLOT).getId() == 20935) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.FEET_SLOT).getId() == 20940) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.FEET_SLOT).getId() == 20941) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.FEET_SLOT).getId() == 20939) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.FEET_SLOT).getId() == 20937) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.FEET_SLOT).getId() == 20938) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.HEAD_SLOT).getId() == 20807) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 2;
		}
		if (p.getEquipment().get(Equipment.BODY_SLOT).getId() == 20808) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 2;
		}
		if (p.getEquipment().get(Equipment.LEG_SLOT).getId() == 20809) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 2;
		}
		if (p.getEquipment().get(Equipment.SHIELD_SLOT).getId() == 20080) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 2;
		}
		if (p.getEquipment().get(Equipment.FEET_SLOT).getId() == 13239) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 2;
		}
		if (p.getEquipment().get(Equipment.HEAD_SLOT).getId() == 20810) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 2;
		}
		if (p.getEquipment().get(Equipment.BODY_SLOT).getId() == 20811) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 2;
		}
		if (p.getEquipment().get(Equipment.LEG_SLOT).getId() == 20812) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 2;
		}
		if (p.getEquipment().get(Equipment.HANDS_SLOT).getId() == 20813) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 2;
		}
		if (p.getEquipment().get(Equipment.FEET_SLOT).getId() == 20814) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 2;
		}
		if (p.getEquipment().get(Equipment.SHIELD_SLOT).getId() == 18946) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 2;
		}
		if (p.getEquipment().get(Equipment.AMULET_SLOT).getId() == 20064) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.RING_SLOT).getId() == 18908) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.WEAPON_SLOT).getId() == 6196) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.WEAPON_SLOT).getId() == 3666) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 5;
		}
		if (p.getEquipment().get(Equipment.CAPE_SLOT).getId() == 20806) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 14;
		}
		if (p.getEquipment().get(Equipment.WEAPON_SLOT).getId() == 20815) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 20;
		}
		if (p.getEquipment().get(Equipment.WEAPON_SLOT).getId() == 20752) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 15;
		}
		if (p.getEquipment().get(Equipment.WEAPON_SLOT).getId() == 20751) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 15;
		}
		if (p.getEquipment().get(Equipment.WEAPON_SLOT).getId() == 20932) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 12;
		}
		if (p.getEquipment().get(Equipment.WEAPON_SLOT).getId() == 19057) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 7;
		}
		if (p.getEquipment().get(Equipment.WEAPON_SLOT).getId() == 12927) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 20;
		}
	
		if (p.getEquipment().get(Equipment.WEAPON_SLOT).getId() == 20805) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 20;
		}
		if (p.getEquipment().get(Equipment.RING_SLOT).getId() == 11556) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.RING_SLOT).getId() == 11555) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.RING_SLOT).getId() == 20054) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.CAPE_SLOT).getId() == 20750) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.WEAPON_SLOT).getId() == 20819 || p.getEquipment().get(Equipment.WEAPON_SLOT).getId() == 20820) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 15;
		}
		if (p.getEquipment().get(Equipment.SHIELD_SLOT).getId() == 59107 || p.getEquipment().get(Equipment.SHIELD_SLOT).getId() == 59105) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
			if (p.getBetaTester()) {
				drBoost += 4;
			}

		return drBoost;
	}
	public static double getDroprate(Player p){
		double drBoost = 0;
		final boolean ringOfWealth = p.getEquipment().get(Equipment.RING_SLOT).getId() == 2572 ;

		if(p.getRights() == PlayerRights.PLAYER) {
			drBoost += 2;
		}
		if(p.getRights() == PlayerRights.OWNER) {
			drBoost += 20000;
		}
		if(p.getRights() == PlayerRights.MODERATOR) {
			drBoost += 20;
		}
		if(p.getRights() == PlayerRights.SUPPORT) {
			drBoost += 20;
		}
		if(p.getRights() == PlayerRights.DONATOR) {
			drBoost += 2;
		}
		if(p.getRights() == PlayerRights.SUPER_DONATOR) {
			drBoost += 4;
		}
		if(p.getRights() == PlayerRights.EXTREME_DONATOR) {
			drBoost += 6;
		}
		if(p.getRights() == PlayerRights.LEGENDARY_DONATOR) {
			drBoost += 10;
		}
		if(p.getRights() == PlayerRights.UBER_DONATOR) {
			drBoost += 15;
		}
		if(p.getRights() == PlayerRights.DELUXE_DONATOR) {
			drBoost += 35;
		}
		if(p.getRights() == PlayerRights.ADMINISTRATOR) {
			drBoost += 35;
		}
		if(p.getGameMode() == GameMode.IRONMAN) {
			drBoost += 5;
		}if(p.getGameMode() == GameMode.HARDCORE_IRONMAN) {
			drBoost += 10;
		}

        if(p.getSummoned() > 1) {
		 if(p.getSummoned() > 6353) {
			drBoost += 8;
		} if(p.getSummoned() > 6304) {
			drBoost += 4;
		}
			if(p.getSummoned() > 3053) {

			drBoost += 3;
		}  else {
             drBoost += 2;
         }
         }
        
      /*  if (p.auraManager.isAura(AuraType.DROP_CHANCE_5))
        	drBoost += 50;
        else if (p.auraManager.isAura(AuraType.DROP_CHANCE_4))
        	drBoost += 25;
        else if (p.auraManager.isAura(AuraType.DROP_CHANCE_3))
        	drBoost += 15;
        else if (p.auraManager.isAura(AuraType.DROP_CHANCE_2))
        	drBoost += 10;
        else if (p.auraManager.isAura(AuraType.DROP_CHANCE_1))
        	drBoost += 5;
        
        
        if (p.auraManager.isAura(AuraType.ULTRA_DROPS_T5))
        	drBoost += 50;
        else if (p.auraManager.isAura(AuraType.ULTRA_DROPS_T4))
        	drBoost += 25;
        else if (p.auraManager.isAura(AuraType.ULTRA_DROPS_T3))
        	drBoost += 15;
        else if (p.auraManager.isAura(AuraType.ULTRA_DROPS_T2))
        	drBoost += 10;
        else if (p.auraManager.isAura(AuraType.ULTRA_DROPS_T1))
        	drBoost += 5;*/
        
        
        //cerberus || spyro
		if(p.getSummoned() == 3063 || p.getSummoned() == 2075 || p.getSummoned() == 2073) {
			drBoost += 20;
		}
		//king mount
		if(p.getSummoned() == 2072) {
			drBoost += 30;
		}
		if(p.getSummoned() == 16) {
			drBoost += 5;
		}
		if(p.getSummoned() == 14) {
			drBoost += 5;
		}
		if(p.getSummoned() == 5418) {
			drBoost += 3;
		}
		if(p.getSummoned() == 1302) {
			drBoost += 13;
		}
		if(p.getSummoned() == 6301) {
			drBoost += 12;
		}
		//BOSSES with DR and damage
		if(p.getSummoned() == 9 || p.getSummoned() == 4 || p.getSummoned() == 0 || p.getSummoned() == 1 ||  p.getSummoned() == 6 || p.getSummoned() == 5) {
			drBoost += 4;
		}

		//vorago no DR  just dmg
		if(p.getSummoned() == 2074) {
			drBoost += 0;
		}
		
		if (ringOfWealth) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 2;
		}		if (p.getEquipment().get(Equipment.RING_SLOT).getId() == 11527) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 4;
		}		if (p.getEquipment().get(Equipment.RING_SLOT).getId() == 11529) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 6;
		}if (p.getEquipment().get(Equipment.RING_SLOT).getId() == 11531) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 8;
		}if (p.getEquipment().get(Equipment.RING_SLOT).getId() == 11533) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 12;
		}
		if (p.getEquipment().get(Equipment.CAPE_SLOT).getId() == 18958 || p.getEquipment().get(Equipment.CAPE_SLOT).getId() == 20806) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 7;
		}
		if (p.getEquipment().get(Equipment.CAPE_SLOT).getId() == 20806) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 14;
		}
		if (p.getEquipment().get(Equipment.SHIELD_SLOT).getId() == 18905) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		
		if (p.getEquipment().get(Equipment.HEAD_SLOT).getId() == 20807) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 2;
		}
		if (p.getEquipment().get(Equipment.BODY_SLOT).getId() == 20808) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 2;
		}
		if (p.getEquipment().get(Equipment.LEG_SLOT).getId() == 20809) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 2;
		}
		if (p.getEquipment().get(Equipment.SHIELD_SLOT).getId() == 20080) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 2;
		}
		if (p.getEquipment().get(Equipment.FEET_SLOT).getId() == 13239) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 2;
		}
		if (p.getEquipment().get(Equipment.HEAD_SLOT).getId() == 20810) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 2;
		}
		if (p.getEquipment().get(Equipment.BODY_SLOT).getId() == 20811) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 2;
		}
		if (p.getEquipment().get(Equipment.LEG_SLOT).getId() == 20812) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 2;
		}
		if (p.getEquipment().get(Equipment.HANDS_SLOT).getId() == 20813) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 2;
		}
		if (p.getEquipment().get(Equipment.FEET_SLOT).getId() == 20814) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 2;
		}
		if (p.getEquipment().get(Equipment.CAPE_SLOT).getId() == 20852) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 3;
		}
		if (p.getEquipment().get(Equipment.HEAD_SLOT).getId() == 20847) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 3;
		}
		if (p.getEquipment().get(Equipment.BODY_SLOT).getId() == 20850) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 3;
		}
		if (p.getEquipment().get(Equipment.LEG_SLOT).getId() == 20851) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 3;
		}
		if (p.getEquipment().get(Equipment.HANDS_SLOT).getId() == 20848) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 3;
		}
		if (p.getEquipment().get(Equipment.FEET_SLOT).getId() == 20849) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 3;
		}
		if (p.getEquipment().get(Equipment.HEAD_SLOT).getId() == 20996) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.BODY_SLOT).getId() == 20994) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.LEG_SLOT).getId() == 20997) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.FEET_SLOT).getId() == 20995) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.HANDS_SLOT).getId() == 20999) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.WEAPON_SLOT).getId() == 20998) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 30;
		}
		if (p.getEquipment().get(Equipment.HEAD_SLOT).getId() == 4646) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.BODY_SLOT).getId() == 4644) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.LEG_SLOT).getId() == 4645) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.FEET_SLOT).getId() == 20090) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.HANDS_SLOT).getId() == 11552) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.HEAD_SLOT).getId() == 20980) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.BODY_SLOT).getId() == 20981) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.LEG_SLOT).getId() == 20982) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.HANDS_SLOT).getId() == 20984) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.FEET_SLOT).getId() == 20983) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.FEET_SLOT).getId() == 21000) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.HEAD_SLOT).getId() == 21016) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.BODY_SLOT).getId() == 21014) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.LEG_SLOT).getId() == 21005) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.HANDS_SLOT).getId() == 21015) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.WEAPON_SLOT).getId() == 21001) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 35;
		}
		if (p.getEquipment().get(Equipment.SHIELD_SLOT).getId() == 18946) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 2;
		}
		if (p.getEquipment().get(Equipment.WEAPON_SLOT).getId() == 20815) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 20;
		}
		if (p.getEquipment().get(Equipment.WEAPON_SLOT).getId() == 20853) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.SHIELD_SLOT).getId() == 20854) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.SHIELD_SLOT).getId() == 20863) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.CAPE_SLOT).getId() == 20750) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 25;
		}
		if (p.getEquipment().get(Equipment.AMULET_SLOT).getId() == 11423) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.WEAPON_SLOT).getId() == 20873) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.WEAPON_SLOT).getId() == 15665) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 2;
		}
		if (p.getEquipment().get(Equipment.HEAD_SLOT).getId() == 15667) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 4;
		}
		if (p.getEquipment().get(Equipment.HEAD_SLOT).getId() == 15662) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 2;
		}
		if (p.getEquipment().get(Equipment.CAPE_SLOT).getId() == 15664) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 12;
		}
		if (p.getEquipment().get(Equipment.CAPE_SLOT).getId() == 21017) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 25;
		}
		if (p.getEquipment().get(Equipment.AMULET_SLOT).getId() == 20871) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 15;
		}
		if (p.getEquipment().get(Equipment.AMULET_SLOT).getId() == 20872) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 15;
		}
		if (p.getEquipment().get(Equipment.FEET_SLOT).getId() == 20942) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.FEET_SLOT).getId() == 20936) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.FEET_SLOT).getId() == 20934) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.FEET_SLOT).getId() == 20933) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.FEET_SLOT).getId() == 20935) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.FEET_SLOT).getId() == 20940) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.FEET_SLOT).getId() == 20941) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.FEET_SLOT).getId() == 20939) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.FEET_SLOT).getId() == 20937) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.FEET_SLOT).getId() == 20938) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.SHIELD_SLOT).getId() == 83) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 7;
		}
		if (p.getEquipment().get(Equipment.SHIELD_SLOT).getId() == 18890) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 7;
		}
		if (p.getEquipment().get(Equipment.WEAPON_SLOT).getId() == 18931) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 13;
		}
		if (p.getEquipment().get(Equipment.AMULET_SLOT).getId() == 20064) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 5;
		}
		if (p.getEquipment().get(Equipment.WEAPON_SLOT).getId() == 12927) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.CAPE_SLOT).getId() == 20750) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 0;
		}
		if (p.getEquipment().get(Equipment.RING_SLOT).getId() == 18908) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		
		if (p.getEquipment().get(Equipment.WEAPON_SLOT).getId() == 20805) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 20;
		}
		
		if (p.getEquipment().get(Equipment.CAPE_SLOT).getId() == 20601) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.WEAPON_SLOT).getId() == 20089) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.WEAPON_SLOT).getId() == 19057) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 13;
		}
		if (p.getEquipment().get(Equipment.WEAPON_SLOT).getId() == 20752) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 15;
		}
		if (p.getEquipment().get(Equipment.WEAPON_SLOT).getId() == 20751) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 15;
		}
		if (p.getEquipment().get(Equipment.WEAPON_SLOT).getId() == 20932) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 24;
		}
		if (p.getEquipment().get(Equipment.WEAPON_SLOT).getId() == 20818) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 25;
		}
		if (p.getEquipment().get(Equipment.WEAPON_SLOT).getId() == 18911) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 5;
		}
		if (p.getEquipment().get(Equipment.WEAPON_SLOT).getId() == 18891) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 13;
		}
		if (p.getEquipment().get(Equipment.AMULET_SLOT).getId() == 20257) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 5;
		}
		if (p.getEquipment().get(Equipment.RING_SLOT).getId() == 11556) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.RING_SLOT).getId() == 20054) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}
		if (p.getEquipment().get(Equipment.RING_SLOT).getId() == 11555) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 10;
		}


	if (p.getEquipment().get(Equipment.WEAPON_SLOT).getId() == 18989) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
		drBoost += 12;
	}
	if (p.getEquipment().get(Equipment.SHIELD_SLOT).getId() == 20819 ) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 15;
		}
		if (p.getEquipment().get(Equipment.WEAPON_SLOT).getId() == 20815 || p.getEquipment().get(Equipment.WEAPON_SLOT).getId() == 20805) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 20;
		}

		// NEW ARMOR START
		//MISSING ARTEMIS GLOVE + BOOTS
		if (p.getEquipment().get(Equipment.HEAD_SLOT).getId() == 20821 || p.getEquipment().get(Equipment.HEAD_SLOT).getId() == 20807 || p.getEquipment().get(Equipment.HEAD_SLOT).getId() == 20810 ) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 2;
		}
		if (p.getEquipment().get(Equipment.BODY_SLOT).getId() == 20822 || p.getEquipment().get(Equipment.BODY_SLOT).getId() == 20808 || p.getEquipment().get(Equipment.BODY_SLOT).getId() == 20811) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 2;
		}
		if (p.getEquipment().get(Equipment.LEG_SLOT).getId() == 20823 || p.getEquipment().get(Equipment.LEG_SLOT).getId() == 20809 || p.getEquipment().get(Equipment.LEG_SLOT).getId() == 20812) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 2;
		}
		if (p.getEquipment().get(Equipment.HANDS_SLOT).getId() == 20813 || p.getEquipment().get(Equipment.SHIELD_SLOT).getId() == 20080 ) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 2;
		}
		if (p.getEquipment().get(Equipment.FEET_SLOT).getId() == 20814 || p.getEquipment().get(Equipment.FEET_SLOT).getId() == 20825) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			drBoost += 2;
		}

		//NEW ARMOR END

//		if (p.getEquipment().get(Equipment.WEAPON_SLOT).getId() == 20751) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
//		drBoost += 15;
//	}
		if(p.getBetaTester()) {
			drBoost +=4;
		}
		return drBoost;
	}

	public static boolean shouldDoubleDrop(Player p, boolean[] b, DropChance chance,
										   boolean ringOfWealth, boolean ringOfWealth1, boolean ringOfWealth2, boolean ringOfWealth3, boolean ringOfWealthLucky, boolean amuletOfInsanity, boolean ringOfGods, boolean extreme, PlayerRights rights) {
		double random = 100; //pull the chance from the table
		double drBoost = NPCDrops.getDoubleDr(p);

		p.setDoubleDropRate(drBoost);
		random = random / ((double)(100+drBoost)/100);

		return !b[chance.ordinal()] && Misc.getRandom((int) random) == 0 || p.getRights() == PlayerRights.DEDICATED_DONATOR; //return true if random between 0 & table value is 1.
	}

	public static boolean shouldRecieveDrop(boolean[] b, WellChance chance,
											boolean ringOfWealth, boolean ringOfWealth1, boolean ringOfWealth2, boolean ringOfWealth3, boolean ringOfWealthLucky, boolean amuletOfInsanity, boolean ringOfGods, boolean extreme, PlayerRights rights) {
		int random = chance.getRandom();
		if (ringOfWealth && random >= 100) {
			random -= (random / 5);
		}
		if (ringOfWealth1 && random >= 100) {
			random -= (random / 10);
		}
		if (ringOfWealth2 && random >= 100) {
			random -= (random / 15);
		}
		if (ringOfWealth3 && random >= 100) {
			random -= (random / 20);
		}
		if (ringOfWealthLucky && random >= 100) {
			random -= (random / 25);
		}
		if (ringOfGods && random >= 100) {
			random -= (random / 20);
		}
		if (amuletOfInsanity && random >= 100) {
			random -= (random / 20);
		}
		return !b[chance.ordinal()] && RandomUtility.getRandom(random) == 1;
	}

	public static void drop(Player player, Item item, NPC npc, Position pos,
							boolean goGlobal) {
		//	if (player.getEquipment().contains(12601)) {
		//	player.getPacketSender().sendMessage("@red@Your Ring of the Gods has boosted your drop rate by 25%.");
		//}

		//if (player.getEquipment().contains(2572)) {
		//player.getPacketSender().sendMessage("@red@Your Ring of Wealth has boosted your drop rate by 10%.");
		//}
		if(npc.getId() == 2042 || npc.getId() == 2043 || npc.getId() == 2044) {
			pos = player.getPosition().copy();
		}

		String npcName = Misc.formatText(npc.getDefinition().getName());



		if (player.getInventory().contains(18337)
				&& BonesData.forId(item.getId()) != null) {
			player.getPacketSender().sendGlobalGraphic(new Graphic(777), pos);
			player.getSkillManager().addExperience(Skill.PRAYER,
					BonesData.forId(item.getId()).getBuryingXP());
			return;
		}
		int itemId = item.getId();
		int amount = item.getAmount();


		if (itemId == 6731 ||  itemId == 6914 || itemId == 7158 ||  itemId == 6889 || itemId == 6733 || itemId == 15019 || itemId == 11235 || itemId == 15020 || itemId == 15018 || itemId == 15220 || itemId == 6735 || itemId == 6737 || itemId == 6585 || itemId == 4151 || itemId == 4087 || itemId == 2577 || itemId == 2581 || itemId == 11732 || itemId == 18782 ) {
			player.getPacketSender().sendMessage("@red@ YOU HAVE RECEIVED A MEDIUM DROP, CHECK THE GROUND!");

		}

		if (itemId == CharmingImp.GOLD_CHARM
				|| itemId == CharmingImp.GREEN_CHARM
				|| itemId == CharmingImp.CRIM_CHARM
				|| itemId == CharmingImp.BLUE_CHARM) {
			if (player.getInventory().contains(6500)
					&& CharmingImp.handleCharmDrop(player, itemId, amount)) {
				return;
			}
		}

		Player toGive = player;

		boolean ccAnnounce = false;
		if(Location.inMulti(player)) {
			if(player.getCurrentClanChat() != null && player.getCurrentClanChat().getLootShare()) {
				CopyOnWriteArrayList<Player> playerList = new CopyOnWriteArrayList<Player>();
				for(Player member : player.getCurrentClanChat().getMembers()) {
					if(member != null) {
						if(member.getPosition().isWithinDistance(player.getPosition())) {
							playerList.add(member);
						}
					}
				}
				if(playerList.size() > 0) {
					toGive = playerList.get(RandomUtility.getRandom(playerList.size() - 1));
					if(toGive == null || toGive.getCurrentClanChat() == null || toGive.getCurrentClanChat() != player.getCurrentClanChat()) {
						toGive = player;
					}
					ccAnnounce = true;
				}
			}
		}

		if(itemId == 18778) { //Effigy, don't drop one if player already has one
			if(toGive.getInventory().contains(18778) || toGive.getInventory().contains(18779) || toGive.getInventory().contains(18780) || toGive.getInventory().contains(18781)) {
				return;
			}
			for(Bank bank : toGive.getBanks()) {
				if(bank == null) {
					continue;
				}
				if(bank.contains(18778) || bank.contains(18779) || bank.contains(18780) || bank.contains(18781)) {
					return;
				}
			}
		}

		if (ItemDropAnnouncer.announce(itemId)) {
			String itemName = item.getDefinition().getName();
			String itemMessage = Misc.anOrA(itemName) + " " + itemName;

			if (player.getRights() == PlayerRights.OWNER) {
				GroundItemManager.spawnGroundItem(toGive, new GroundItem(item, pos,
						toGive.getUsername(), false, 150, goGlobal, 200));
			}
			switch (itemId) {
				case 14484:
					itemMessage = "a pair of Dragon Claws";
					break;
				case 3230:
				case 19036:
				case 19034:
				case 19035:
				case 20064:
				case 20000:
				case 20001:
				case 20002:
					itemMessage = itemName;
					break;
			}
			switch (npc.getId()) {
				case 81:
					npcName = "a Cow";
					break;
				case 50:
				case 3200:
				case 8133:
				case 4540:
				case 1160:
				case 8549:
					npcName = "The " + npcName + "";
					break;
				case 51:
				case 54:
				case 5363:
				case 8349:
				case 1592:
				case 1591:
				case 1590:
				case 1615:
				case 9463:
				case 9465:
				case 9467:
				case 1382:
				case 13659:
				case 11235:
					npcName = "" + Misc.anOrA(npcName) + " " + npcName + "";
					break;
			}
			ItemDefinition drop = ItemDefinition.forId(itemId);
			NpcDefinition npcDrop = NpcDefinition.forId(npc.getId());

			if (doubleDrop && yell) {
				String message = "@blu@[Double Drop] " + toGive.getUsername()
						+ " has just his @red@" + drop.getName() + "@blu@ Doubled from " + npcDrop.getName()
						+ "!";
				World.sendMessage(message);
				yell = false;
			} else if(!doubleDrop) {
				String message = "@blu@[RARE DROP] " + toGive.getUsername()
						+ " has just received @red@" + itemMessage + "@blu@ from " + npcName
						+ "!";
				World.sendMessage(message);
			}


			if(ccAnnounce) {
				ClanChatManager.sendMessage(player.getCurrentClanChat(), "<col=16777215>[<col=255>Lootshare<col=16777215>]<col=3300CC> "+toGive.getUsername()+" received " + itemMessage + " from "+npcName+"!");
			}

			PlayerLogs.log(toGive.getUsername(), "" + toGive.getUsername() + " received " + itemMessage + " from " + npcName + "");
		}
		//if (player.getEquipment().get(Equipment.RING_SLOT).getId() == 11556)
		if (player.getEquipment().get(Equipment.AMULET_SLOT).getId() == 11423 || player.getEquipment().get(Equipment.AMULET_SLOT).getId() == 20871 || player.getEquipment().get(Equipment.AMULET_SLOT).getId() == 20872) { //if the chance from the table is greater or equal to 60, and player is wearing ring of wealth
			player.giveItem(item.getId(),item.getAmount());
		} else {
			GroundItemManager.spawnGroundItem(toGive, new GroundItem(item, pos,
					toGive.getUsername(), false, 150, goGlobal, 200));
		}
		DropLog.submit(toGive, new DropLogEntry(itemId, item.getAmount()));
	}

	public static void casketDrop(Player player, int combat, Position pos) {
		int chance = (6 + (combat / 2));
		if (RandomUtility.getRandom(combat <= 50 ? 1300 : 5000) < chance) {
			if (player.getEquipment().get(Equipment.AMULET_SLOT).getId() == 11423 || player.getEquipment().get(Equipment.AMULET_SLOT).getId() == 20871 || player.getEquipment().get(Equipment.AMULET_SLOT).getId() == 20872) {
				player.giveItem(7956,1);
			} else
			GroundItemManager.spawnGroundItem(player, new GroundItem(new Item(7956), pos, player.getUsername(), false, 150, true, 200));

			player.getPacketSender().sendMessage("@or2@You have received a casket!");
		}
	}
	public static void monsterfragmentDrop(Player player, int combat, Position pos) {
		int chance = (6 + (combat / 2));
		if (RandomUtility.getRandom(combat <= 50 ? 1300 : 5000) < chance) {
			if (player.getEquipment().get(Equipment.AMULET_SLOT).getId() == 11423 || player.getEquipment().get(Equipment.AMULET_SLOT).getId() == 20871 || player.getEquipment().get(Equipment.AMULET_SLOT).getId() == 20872) {
				player.giveItem(11316, 25);
			} else
				GroundItemManager.spawnGroundItem(player, new GroundItem(new Item(11316, 25), pos, player.getUsername(), false, 150, true, 200));

			player.getPacketSender().sendMessage("@or2@You have received some Monster fragments!");
		}
	}
	private static final int[] CLUESBOY = new int[] { 2677, 2678, 2679, 2681, 2682, 2684};


	public static void clueDrop(Player player, int combat, Position pos) {
		int chance = (6 + (combat / 4));
		if (RandomUtility.getRandom(combat <= 80 ? 1300 : 1000) < chance) {
			int clueId = CLUESBOY[Misc.getRandom(CLUESBOY.length - 1)];
			if (player.getEquipment().get(Equipment.AMULET_SLOT).getId() == 11423 || player.getEquipment().get(Equipment.AMULET_SLOT).getId() == 20871 || player.getEquipment().get(Equipment.AMULET_SLOT).getId() == 20872) {
				player.giveItem(clueId, 1);
			}else
				GroundItemManager.spawnGroundItem(player, new GroundItem(new Item(clueId), pos, player.getUsername(), false, 150, true, 200));
			player.getPacketSender().sendMessage("@or2@You have received a clue scroll!");
		}
	}

	public static class ItemDropAnnouncer {

		private static List<Integer> ITEM_LIST;

		private static final int[] TO_ANNOUNCE = new int[] {20929, 20930, 20921, 11988, 2104, 18889,3230,11555,20054,18890,18891,18931,3642,3643,3644,3645,3646,3647,3648,4649,3656,3657,3658,3659,3660,14008,11556, 2097, 3651,3652,3653,3654,20511,20510,3655,3687,11425,2108, 2109, 2110, 2105, 2106, 2107, 3954, 17848, 3666, 3682 ,17849, 7614, 14009, 2094, 2093, 2099, 2100, 2101, 2102, 19670, 14010,
				6571, 19057, 2513, 15259,18899,13239, 19335,7118, 7100, 12421, 12422, 12423, 12424, 12425, 11981, 11982, 11983, 11984, 11985, 3961, 4784, 3981, 11986, 11987, 11988, 11989, 11990, 11991, 11992, 11993, 11994, 11995, 11996, 11997, 20089,14018,20825,20823,20822,20821,20824,20819,20820,20826,20827,20828,20829,20830,20831,20832,20833,20834,20836,20837,20838,83,20806,11533,
		5023,20818,18,19095,19096,19097,19099,19090,19091,19092,19093,19094,19098,20870,20931,11423,20810,20811,20812,20813,20814,20980,20981,20982,20983,20984,20932,3661,19031,19029,19030,19032,19033,20988,20989,20990,20991,20992,20993,20967,20968,20969,20970,2762,2760,2098,2759,20975,20977,20942,20936,20934,20933,20935,20940,20941,20939,20937,20938,19004,4646,4644,4645,20090,11552,20996,20994,20997,20995,20999,17488,799,3062,3064,3063,3066,3065};//All Rare Boss Drops};


		private static void init() {
			ITEM_LIST = new ArrayList<Integer>();
			for (int i : TO_ANNOUNCE) {
				ITEM_LIST.add(i);
			}
		}

		public static boolean announce(int item) {
			return ITEM_LIST.contains(item);
		}
	}
}