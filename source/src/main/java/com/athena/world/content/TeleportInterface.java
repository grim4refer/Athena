package com.athena.world.content;

import java.util.ArrayList;
import java.util.List;

import com.athena.model.Item;
import com.athena.model.Position;
import com.athena.model.definitions.NPCDrops;
import com.athena.world.World;
import com.athena.world.content.minigames.impl.GodsRaid;
import com.athena.world.content.transportation.TeleportHandler;
import com.athena.world.entity.impl.player.Player;

public class TeleportInterface {
	
	public Player player;
	public TeleportInterface(Player player) {
		this.player = player;
		
	}

	private final static int CATEGORY_NAME_ID = 50508;

	public enum Bosses {
		// this is 1st field etc
		TEST(50601, "Vegeta", "@red@Vegeta Npc!", "@yel@Drops Super Sayian items!", "", "", "", 389, new int[] { 2725, 9642, 0 }),
		GOKU(50602, "Goku", "@red@Goku Npc!", "@yel@Drops Goku items!", "", "", "", 6336, new int[] { 2725, 9624, 0 }),
		BROLY(50603, "Broly", "@red@Broly Npc!", "@yel@Drops Broly items!", "", "", "", 7, new int[] { 1941, 4687, 0 }),
		HULK(50604, "Hulk", "@red@Hulk Npc!", "@yel@Drops Enchanter items!", "", "", "", 2, new int[] { 2594, 2518, 1 }),
		SPIDERMAN(50605, "Spiderman", "@red@Spiderman Npc!", "@yel@Drops Spiderman items!", "", "", "", 2511, new int[] { 2585, 2518, 1 }),
		IRONMAN(50606, "Ironman", "@red@Ironman Npc!", "@yel@Drops Ironman items!", "", "", "", 2509, new int[] { 2585, 2534, 1 }),
		CAPTAIN_AMERICA(50607, "Captain America", "@red@Captain America Npc!", "@yel@Drops Captain America items!", "", "", "", 2506, new int[] { 2590, 2545, 1 }),
		ANT_MAN(50608, "Ant man", "@red@Ant man Npc!", "@yel@Drops Ant man items!", "", "", "", 2502, new int[] { 2591, 2545, 1 }),
		PENNYWISE(50609, "PennyWise", "@red@PennyWise Npc!", "@yel@Drops PennyWise items!", "", "", "", 8, new int[] { 2596, 2533, 1 }),
		PLANEFREEZER(50610, "Plane Freezer", "@red@Plane Freezer Npc!", "@yel@Drops Plane Freezer items!", "", "", "", 9939, new int[] { 3488, 9942, 0 }),
		//BADSANTA(50611, "Bad Santa", "@red@Bad Santa Npc!", "@yel@Drops Santa Tokens!", "", "", "", 25, new int[] { 2984, 9517, 1 }),
		CERBERUS(50611, "Cursebearer", "@red@Unholy Cursebearer Npc!", "@yel@Drops Cursebearer items!", "", "", "", 10126, new int[] { 2525, 4777, 0 }),
		CORPOREAL_BEAST(50612, "Nex", "@red@Nex Npc!", "@yel@Drops Nex items!", "", "", "", 13447, new int[] { 2905, 5204, 0 }),
		CUSTOM_HELLHOUND(50613, "Iktomi", "@Red@Iktomi Npc!", "@yel@Drops Iktomi items!", "", "", "", 6307, new int[] { 1940, 5001, 0 }),
		LUCARIO(50614, "Lucario", "@red@Lucario Npc!", "@yel@Drops Lucario items!", "", "", "", 170, new int[] { 2584, 2580, 1 }),
		MEWTWO(50615, "MewTwo", "@red@MewTwo Npc!", "@yel@Drops MewTwo items!", "", "", "", 360, new int[] { 2588, 2580, 1 },2000),
		CHARMELEON(50616, "Charmeleon", "@red@Charmeleon Npc!", "@yel@Drops Charmeleon items!", "", "", "", 359, new int[] { 2592, 2580, 1 },2000),
		SQUIRTLE(50617, "Squirtle", "@red@Squirtle Npc!", "@yel@Drops Squirtle items!", "", "", "", 361, new int[] { 2597, 2580, 1 }),
		PICKACHU(50618, "Pickachu", "@red@Pickachu Npc!", "@yel@Drops Pickachu items!", "", "", "", 365, new int[] { 2593, 2576, 1 }),
		SONIC(50619, "Sonic", "@red@Sonic Npc!", "@yel@Drops Sonic items!", "", "", "", 363, new int[] { 2593, 2570, 1 }),
		DONKEY_KONG(50620, "Donkey Kong", "@red@Donkey Kong Npc!", "@yel@Drops Donkey Kong items!", "", "", "", 370, new int[] { 2593, 2564, 1 }),
		MR_KRABS(50621, "Mr Krabs", "@red@Mr Krabs Npc!", "@yel@Drops Mr Krabs items!", "", "", "", 362, new int[] { 2593, 2558, 1 }),
		NUTELLA(50622, "Nutella", "@red@Nutella Npc!", "@yel@Drops Nutella items!", "", "", "", 368, new int[] { 2588, 2558, 1 }),
		MAYONAISE(50623, "Mayonnaise", "@red@Mayonnaise Npc!", "@yel@Drops Mayonaise items!", "", "", "", 367, new int[] { 2588, 2564, 1 }),
		INFERNO(50624, "Inferno", "@red@Undead Npc!", "@yel@Drops Inferno Undead items!", "@cya@THIS NPC IS IN MULTI!", "", "", 53, new int[] { 2724, 4777, 0 },3000),
		VORAGO(50625, "Vorago", "@red@Vorago Npc!", "@yel@Drops Vorago items!", "@cya@THIS IS EPIC BOSS!", "", "", 2071, new int[] { 2535, 2567, 1 },5000),
		YANI(50626, "Yani", "@red@Yani Npc!", "@yel@Drops Yani items!", "@cya@THIS NPC IS IN MULTI!", "", "", 515, new int[] { 2272, 4693, 0 }),
		GODZILLA(50627, "Godzilla", "@red@Godzilla", "@mag@Drops Godzilla items!", "", "", "", 1299, new int[] { 2900, 3617, 0 });

		Bosses(int textId, String name, String description1, String description2, String description3,
				String description4, String description5, int npcId, int[] teleportCords) {
			this.textId = textId;
			this.name = name;
			this.description1 = description1;
			this.description2 = description2;
			this.description3 = description3;
			this.description4 = description4;
			this.description5 = description5;
			this.npcId = npcId;
			this.teleportCords = teleportCords;
			

		}

		Bosses(int textId, String name, String description1, String description2, String description3,
				String description4, String description5, int npcId, int[] teleportCords, int adjustedZoom) {
			this.textId = textId;
			this.name = name;
			this.description1 = description1;
			this.description2 = description2;
			this.description3 = description3;
			this.description4 = description4;
			this.description5 = description5;
			this.npcId = npcId;
			this.teleportCords = teleportCords;
			this.adjustedZoom = adjustedZoom;

		}

		private int textId;
		private String name;
		private String description1, description2, description3, description4, description5;
		private int npcId;
		private int[] teleportCords;
		private int adjustedZoom;
	}

	public enum Monsters {

		CAR(50601, "Cars", "@mag@Starter Npc", "@cya@Good for Starter gear!", "", "", "", 1265, new int[] { 2504, 2506, 1 }),
		CRASH(50602, "Crash", "@mag@Gun Npc", "@cya@Drops Guns!", "", "", "", 2437, new int[] { 2504, 2523, 1 },1000),
		TERROR_DOG(50603, "Terror dog", "@mag@Assassin Npc", "@cya@Drops Assassin items!", "", "", "", 5417, new int[] { 2504, 2540, 1 }),
		PENGUIN(50604, "Penguin", "@mag@Predator Npc", "@cya@Drops Predator items!", "", "", "", 131, new int[] { 2521, 2551, 1 }),
		NATURE_SWORD(50605, "Nature Sword", "@mag@Nature Npc", "@cya@Drops Nature items!", "", "", "", 1459, new int[] { 2549, 2551, 1 }),
		AURLIA(50606, "Aurlia", "@mag@Oblivion Npc", "@cya@Drops Oblivion items!", "", "", "", 6102, new int[] { 2567, 2550, 1 }),
		BELERION(50607, "Belerion", "@mag@Depature Npc", "@cya@Drops dark depature items!", "", "", "", 4392, new int[] { 2551, 2517, 1 }),
		LIVYATHANN(50608, "Livyathann", "@mag@Trio Npc", "@cya@Drops Trio items!", "", "", "", 52, new int[] { 2551, 2517, 2 }),
		DIRE_WOLF(50609, "Dire Wolf", "@mag@Wolf Npc", "@cya@Drops Dire Wolf gloves!", "", "", "", 4413, new int[] { 2551, 2534, 2 }),
		YETI(50610, "Yeti", "@mag@Animal Npc", "@cya@Drops Animal items!", "", "", "", 130, new int[] { 2549, 2551, 2 }),
		TORVIAN(50611, "Torvian", "@mag@Electric Npc", "@cya@Drops Electric items!", "", "", "", 5472, new int[] { 2521, 2551, 2 }),
		// this is 1st field etc
		BANDOS_AVATAR(50612, "Bandos Avatar", "@red@Avatar Npc", "@yel@Drops Avatar items!", "", "", "", 4540, new int[] { 2867, 9946, 0 },2000),
		ABBADON(50613, "Abbadon", "@red@THIS NPC IS IN MULTI!", "@yel@Drops Abbadon items!", "", "", "", 6303, new int[] { 2517, 5172, 0 }, 4000),
		INFERNAL_GROUDON(50614, "Infernal Groudon", "@red@THIS NPC IS IN MULTI!", "@yel@Drops Infernal items!", "", "", "", 1234, new int[] { 1240, 1227, 0 }, 2000),
		BAPHOMET(50615, "Baphomet", "@red@Baphomet Npc!", "@yel@Drops Baphomet items!", "", "", "", 2236, new int[] { 2458, 10157, 0 }, 2000),
		ATHENA_GUARDIAN(50616, "Athena Guardian", "@red@Guardian Npc!", "@yel@Drops Guardian items!", "", "", "", 6305, new int[] { 2482, 10146, 0 }),
		HEARTWRENCHER(50617, "Heartwrencher", "@red@Wrencher Npc!", "@yel@Drops mainly 1b Tickets!", "", "", "", 6313, new int[] { 2732, 9976, 0 },2500),
		LEFOSH(50618, "Le'Fosh", "@red@Le'Fosh Npc!", "@yel@Drops Le'Fosh items!", "", "", "", 6309, new int[] { 3620, 3534, 0 }),
		ALIEN(50619, "Alien", "@red@Alien Npc!", "@yel@Drops Alien items!", "", "", "", 4419, new int[] { 2420, 4690, 0 }, 1800),
		VLADIMIR(50620, "Vladimir", "@red@ Draynor Npc!", "@yel@Drops Draynor items!", "", "", "", 9357, new int[] { 2395, 4681, 0 }),
		THREE_BROTHERS(50621, "3 Brothers", "@red@Brothers Npc!", "@yel@Drops Brothers items!", "", "", "", 9911, new int[] { 2505, 2540, 2 },3000);

		Monsters(int textId, String name, String description1, String description2, String description3,
				String description4, String description5, int npcId, int[] teleportCords) {
			this.textId = textId;
			this.name = name;
			this.description1 = description1;
			this.description2 = description2;
			this.description3 = description3;
			this.description4 = description4;
			this.description5 = description5;
			this.npcId = npcId;
			this.teleportCords = teleportCords;

		}

		Monsters(int textId, String name, String description1, String description2, String description3,
				String description4, String description5, int npcId, int[] teleportCords, int adjustedZoom) {
			this.textId = textId;
			this.name = name;
			this.description1 = description1;
			this.description2 = description2;
			this.description3 = description3;
			this.description4 = description4;
			this.description5 = description5;
			this.npcId = npcId;
			this.teleportCords = teleportCords;
			this.adjustedZoom = adjustedZoom;

		}

		private int textId;
		private String name;
		private String description1, description2, description3, description4, description5;
		private int npcId;
		private int[] teleportCords;
		private int adjustedZoom;
	}

	public enum Wilderness {
		MULTIRAIDS(50601, "Darth Vader", "@mag@Multi Raid", "@red@Be careful its @yel@dangerous!", "@cya@Good luck!", "", "", 4329, new int[] { 3287, 3882, 0 }),
		SUPREME(50602, "Supreme Raid", "@mag@Multi Raid @or1@which can", "@or1@reward you with very", "@or1@high tier gear.", "",
				"@red@Good Luck!", 6276, new int[] { 1823, 5154, 2 });
		Wilderness(int textId, String name, String description1, String description2, String description3,
				String description4, String description5, int npcId, int[] teleportCords) {
			this.textId = textId;
			this.name = name;
			this.description1 = description1;
			this.description2 = description2;
			this.description3 = description3;
			this.description4 = description4;
			this.description5 = description5;
			this.npcId = npcId;
			this.teleportCords = teleportCords;

		}

		Wilderness(int textId, String name, String description1, String description2, String description3,
				String description4, String description5, int npcId, int[] teleportCords, int adjustedZoom) {
			this.textId = textId;
			this.name = name;
			this.description1 = description1;
			this.description2 = description2;
			this.description3 = description3;
			this.description4 = description4;
			this.description5 = description5;
			this.npcId = npcId;
			this.teleportCords = teleportCords;
			this.adjustedZoom = adjustedZoom;

		}

		private int textId;
		private String name;
		private String description1, description2, description3, description4, description5;
		private int npcId;
		private int[] teleportCords;
		private int adjustedZoom;
	}

	public enum Skilling {
		PRAYER(50601, "Donator Store", "@gre@Donator Store", "@cya@This is the Donation Shop!", "", "", "", 212, new int[] { 2531, 2518, 1 }),
		DONATION_SHOP_2(50602, "Donator Store 2", "@gre@Donator Store 2", "@cya@The 2nd Donation Shop!", "", "", "", 548, new int[] { 2582, 2572, 1 }),
		RUNECRAFTING(50603, "Vote Store", "@gre@King Lathas", "@cya@This is the Voting Shop!", "", "", "", 364, new int[] { 2532, 2518, 1 }),
		CONSTRUCTION(50604, "Boss Point Store", "@gre@Boss Point Store", "@cya@This is the Boss Point Shop!", "", "", "", 273, new int[] { 2533, 2518, 1 }),
		FISHING(50605, "Trivia Point Store", "@gre@Trivia Point Store", "@cya@This Shop sells Trivia Boxes!", "", "", "", 2633, new int[] { 2532, 2502, 1 }),
		AGILITY(50606, "Prestige Store", "@gre@Prestige Point Store", "@cya@The Prestige Point Shop!", "", "", "", 4559, new int[] { 2535, 2518, 1 }),
		DUNGEONEERING(50607, "Cards Store", "@gre@Scratch Card Store", "@cya@The Scratch Card Shop!", "", "", "", 7969, new int[] { 2534, 2518, 1 }),
		HUNTER(50608, "Healer Store", "@gre@Healer Store", "@cya@The Shop has Consumables!", "", "", "", 961, new int[] { 2534, 2524, 1 }),
		HERBLORE(50609, "Loyalty Store", "@gre@Loyalty Point Store", "@cya@The Loyalty Point Shop!", "", "", "", 602, new int[] { 2536, 2518, 1 }),
		THIEVING(50610, "Slayer Store", "@gre@Slayer Store", "@cya@Vannaka has a Slayer Shop!", "", "", "", 1597, new int[] { 2526, 2518, 1 }),
		CRAFTING(50611, "Player Store", "@gre@Player Owned Shop!", "@cya@You can sell items here!", "", "", "", 947, new int[] { 2525, 2524, 1 }),
		COOKING(50612, "Guardian Store", "@gre@Graveyard Guardian", "@cya@This Shop sells items!", "", "", "", 3101, new int[] { 2539, 2534, 1 },2000),
		SKILLCAPESHOP(50613, "Skillcapes Store", "@gre@Skillcapes Store", "@cya@This is the Skillcape Shop!", "", "", "", 2253, new int[] { 2519, 2527, 1 }),
		SMITHING(50614, "Make-over", "@gre@Make-over Mage", "@cya@Mage changes your looks!", "", "", "", 2676, new int[] { 2522, 2502, 1 }),
		FLETCHING(50615, "Town Crier", "@gre@Town Crier", "@cya@He can change Passwords!", "", "", "", 6139, new int[] { 2539, 2529, 1 }),
		ROCNAR(50616, "Rocnar", "@gre@Rocnar", "@cya@He Buys and Sells items!", "", "", "", 3594, new int[] { 2526, 2528, 1 });

		Skilling(int textId, String name, String description1, String description2, String description3,
				String description4, String description5, int npcId, int[] teleportCords) {
			this.textId = textId;
			this.name = name;
			this.description1 = description1;
			this.description2 = description2;
			this.description3 = description3;
			this.description4 = description4;
			this.description5 = description5;
			this.npcId = npcId;
			this.teleportCords = teleportCords;

		}

		Skilling(int textId, String name, String description1, String description2, String description3,
				String description4, String description5, int npcId, int[] teleportCords, int adjustedZoom) {
			this.textId = textId;
			this.name = name;
			this.description1 = description1;
			this.description2 = description2;
			this.description3 = description3;
			this.description4 = description4;
			this.description5 = description5;
			this.npcId = npcId;
			this.teleportCords = teleportCords;
			this.adjustedZoom = adjustedZoom;

		}
		
	

		private int textId;
		private String name;
		private String description1, description2, description3, description4, description5;
		private int npcId;
		private int[] teleportCords;
		private int adjustedZoom;
	}

	public enum Minigames {
		INSTANCE_MANAGER(50601, "Instance Manager", "@red@Instance Manager", "@yel@Instance to SOLO Bosses!", "", "", "", 457, new int[] { 2525, 2502, 1 }),
		FIGHT_CAVE(50602, "Pest Control", "@red@Pest Control", "@yel@You enter the ship", "@mag@Reward is Commendations!", "", "", 6145, new int[] { 2658, 2660, 0 }, 4000),
		//FIGHT_PITS(50603, "Godzilla", "@red@Godzilla", "@mag@Drops Godzilla items!", "", "", "", 1299, new int[] { 2900, 3617, 0 }),
		PEST_CONTROL(50603, "Point Zone", "@red@Athena Warriors", "@mag@Drops Warriors items!", "", "", "", 2436, new int[] { 2766, 2799, 0 }),
		DUEL_ARENA(50604, "Warrior's Guild", "@red@Warrior's Guild!", "@mag@Trade Armour Store!", "", "", "", 650, new int[] { 2841, 3538, 0 }),
		WARRIOR_GUILD(50605, "Gambler Zone", "@red@Gambling Zone!", "@mag@Flower Poker vs PLAYERS!", "", "", "", 2998, new int[] { 2737, 3472, 0 }),
		RECIPE_FOR_DIASTER(50606, "AFK Evil Tree", "@red@AFK Evil Tree", "@mag@Chop Evil Tree!", "", "", "", 4906, new int[] { 2504, 2519, 2 }),
		CULINAROMANCER(50607, "Gypse", "@red@Enter the Culinar Portal", "@mag@Talk with the Gypse!", "", "", "", 3385, new int[] { 2561, 2532, 1 }),
		//NOMAD(50608, "Bad Santa", "@red@", "", "", "", "", 1, new int[] { 2977, 9519, 1 });
		LUIGI(50608, "Luigi", "@red@Luigi Npc!", "@mag@Reward is 7 Dragon balls!", "", "", "", 3, new int[] { 2535, 2501, 1 });
		//ZOMBIE_SLAUGHTER(50609, "Zombie Slaughter", "", "", "", "", "", 73, new int[] { 3503, 3563, 0 });

		Minigames(int textId, String name, String description1, String description2, String description3,
				String description4, String description5, int npcId, int[] teleportCords) {
			this.textId = textId;
			this.name = name;
			this.description1 = description1;
			this.description2 = description2;
			this.description3 = description3;
			this.description4 = description4;
			this.description5 = description5;
			this.npcId = npcId;
			this.teleportCords = teleportCords;

		}

		Minigames(int textId, String name, String description1, String description2, String description3,
				String description4, String description5, int npcId, int[] teleportCords, int adjustedZoom) {
			this.textId = textId;
			this.name = name;
			this.description1 = description1;
			this.description2 = description2;
			this.description3 = description3;
			this.description4 = description4;
			this.description5 = description5;
			this.npcId = npcId;
			this.teleportCords = teleportCords;
			this.adjustedZoom = adjustedZoom;

		}

		private int textId;
		private String name;
		private String description1, description2, description3, description4, description5;
		private int npcId;
		private int[] teleportCords;
		private int adjustedZoom;
	}
/*
 * Dungeons here
 */
	public enum Cities { //events tab
		TEST(50601, "Roy", "@bla@Roy", "@mag@It's a Multi Boss!", "@yel@Spawns every 1 minute!", "@yel@Teleport once he Spawns!", "", 2515, new int[] { 2580, 2572, 1 }),
		GANO(50602, "Gano", "@red@Take this boss down!", "@mag@Gano's drops are invisible.", "", "", "", /*Replace this ID*/2070/*Replace this id*/, new int[] { 3170, 2991, 0 }),
		Vanquisher(50603, "Vanquisher", "@bla@Vanquisher", "@mag@It's a Multi Boss!", "@yel@Spawns every 3 minutes!", "", "", 3915, new int[] { 2700, 9998, 0 }),
		Ultimatium(50604, "Ultimatium", "@red@Very Strong Boss!", "@mag@This Npc damages everyone.", "", "", "",4303, new int[] { 2905, 2780, 0 });
		
		Cities(int textId, String name, String description1, String description2, String description3,
				String description4, String description5, int npcId, int[] teleportCords) {
			this.textId = textId;
			this.name = name;
			this.description1 = description1;
			this.description2 = description2;
			this.description3 = description3;
			this.description4 = description4;
			this.description5 = description5;
			this.npcId = npcId;
			this.teleportCords = teleportCords;

		}

		Cities(int textId, String name, String description1, String description2, String description3,
				String description4, String description5, int npcId, int[] teleportCords, int adjustedZoom) {
			this.textId = textId;
			this.name = name;
			this.description1 = description1;
			this.description2 = description2;
			this.description3 = description3;
			this.description4 = description4;
			this.description5 = description5;
			this.npcId = npcId;
			this.teleportCords = teleportCords;
			this.adjustedZoom = adjustedZoom;

		}

		private int textId;
		private String name;
		private String description1, description2, description3, description4, description5;
		private int npcId;
		private int[] teleportCords;
		private int adjustedZoom;
	}

	public static void resetOldData() {
		currentTab = 0;
		currentClickIndex = 0;
	}

	public void handleTeleports() {
		switch (player.currentTabs) {
		case 0:
			Bosses bossData = Bosses.values()[player.clickindex];
			player.getTeleportInterface().handleBossTeleport(bossData);
			
			break;
		case 1:
			Monsters monsterData = Monsters.values()[player.clickindex];
			player.getTeleportInterface().handleMonsterTeleport(monsterData);
			break;
		case 2:
			player.setTimer(0);
			Wilderness wildyData = Wilderness.values()[player.clickindex];
			player.getTeleportInterface().handleWildyTeleport(wildyData);
			break;
		case 3:
			Skilling skillingData = Skilling.values()[player.clickindex];
			player.getTeleportInterface().handleSkillingTeleport(skillingData);
			break;
		case 4:
			Minigames minigameData = Minigames.values()[player.clickindex];
			player.getTeleportInterface().handleMinigameTeleport(player, minigameData);
			break;
		case 5:
			Cities cityData = Cities.values()[player.clickindex];
			player.getTeleportInterface().handleCityTeleport(cityData);
			break;//update
			
		}
	
	}

	public void handleBossTeleport(Bosses bossData) {

		TeleportHandler.teleportPlayer(player,
				new Position(bossData.teleportCords[0], bossData.teleportCords[1], bossData.teleportCords[2]),
				player.getSpellbook().getTeleportType());
	}

	public void handleMonsterTeleport( Monsters monsterData) {

		TeleportHandler.teleportPlayer(player,
				new Position(monsterData.teleportCords[0], monsterData.teleportCords[1], monsterData.teleportCords[2]),
				player.getSpellbook().getTeleportType());
	}

	public  void handleWildyTeleport( Wilderness wildyData) {

		TeleportHandler.teleportPlayer(player,
				new Position(wildyData.teleportCords[0], wildyData.teleportCords[1], wildyData.teleportCords[2]),
				player.getSpellbook().getTeleportType());
	}

	public  void handleSkillingTeleport( Skilling skillData) {

		TeleportHandler.teleportPlayer(player,
				new Position(skillData.teleportCords[0], skillData.teleportCords[1], skillData.teleportCords[2]),
				player.getSpellbook().getTeleportType());
	}

	public static void handleMinigameTeleport(Player player, Minigames minigameData) {

		if (minigameData.teleportCords[0] == 3138) {
			World.minigameHandler.getMinigame(1).addPlayer(player);
		}


		if (minigameData.teleportCords[0] == 1823) {
			if (player.getSummoned() != -1) {
				player.sendMessage("You cannot bring pets into the marvel raid!");
				return;
			} else {
				if (!GodsRaid.getStarted()) {
					World.minigameHandler.getMinigame(0).addPlayer(player);
				} else {
					player.sendMessage("@red@Marvel Raid is already started! wait till next round.");
					return;
				}
			}//TRY NOW OKAY WHY DID U CHANGE THAT TO 2904?
		}


		TeleportHandler.teleportPlayer(player, new Position(minigameData.teleportCords[0],
				minigameData.teleportCords[1], minigameData.teleportCords[2]), player.getSpellbook().getTeleportType());
		
		player.lastTeleportPosition = new Position(minigameData.teleportCords[0], minigameData.teleportCords[1], minigameData.teleportCords[2]);

	}

	public  void handleCityTeleport( Cities cityData) {

		TeleportHandler.teleportPlayer(player,
				new Position(cityData.teleportCords[0], cityData.teleportCords[1], cityData.teleportCords[2]),
				player.getSpellbook().getTeleportType());
	}

	private  void clearData() {
		for (int i = 50601; i < 50700; i++) {
			player.getPacketSender().sendString(i, "");
		}
	}

	private static int currentTab = 0; // 0 = Boss, 1 = Monsters, 2 = Wildy, 3 = Skilling, 4 = Minigame, 5 = Cities

	public boolean handleButton( int buttonID) {

		if (!(buttonID >= -14935 && buttonID <= -14836)) {
			return false;
		}
		int index;

		index = 14935 + buttonID;
		if (player.currentTabs == 0) {
			if (index < Bosses.values().length) {
				//System.out.println("Handled boss data [As index was 0]");
				Bosses bossData = Bosses.values()[index];
				//currentClickIndex = index;
				player.clickindex = index;
				player.getTeleportInterface().sendBossData(bossData);
				player.getTeleportInterface().sendDrops(bossData.npcId);
				
			}
		}
		if (player.currentTabs == 1) {
			if (index < Monsters.values().length) {
				//System.out.println("Handled monster data [As index was 1]");
				Monsters monsterData = Monsters.values()[index];
				//currentClickIndex = index;
				player.clickindex = index;
				player.getTeleportInterface().sendMonsterData(monsterData);
				player.getTeleportInterface().sendDrops(monsterData.npcId);
			}
		}
		if (player.currentTabs == 2) {
			if (index < Wilderness.values().length) {
				//System.out.println("Handled monster data [As index was 1]");
				Wilderness wildyData = Wilderness.values()[index];
				//currentClickIndex = index;
				player.clickindex = index;
				player.getTeleportInterface().sendWildyData(wildyData);
				player.getTeleportInterface().sendDrops(wildyData.npcId);
			}
		}
		if (player.currentTabs == 3) {
			if (index < Skilling.values().length) {
				//System.out.println("Handled monster data [As index was 1]");
				Skilling skillingData = Skilling.values()[index];
				//currentClickIndex = index;
				player.clickindex = index;
				player.getTeleportInterface().sendSkillingData(skillingData);
				player.getTeleportInterface().sendDrops(skillingData.npcId);
			}
		}
		if (player.currentTabs == 4) {
			if (index < Minigames.values().length) {
				//System.out.println("Handled monster data [As index was 1]");
				Minigames minigamesData = Minigames.values()[index];
				//currentClickIndex = index;
				player.clickindex = index;
				player.getTeleportInterface().sendMinigameData(minigamesData);
				player.getTeleportInterface().sendDrops(minigamesData.npcId);
			}
		}
		if (player.currentTabs == 5) {
			if (index < Cities.values().length) {
				//System.out.println("Handled monster data [As index was 1]");
				Cities cityData = Cities.values()[index];
				//currentClickIndex = index;
				player.clickindex = index;
				player.getTeleportInterface().sendCityData(cityData);
				player.getTeleportInterface().sendDrops(cityData.npcId);
			}
		}
		return true;

	}

	public static int currentClickIndex = 0;

	public  void sendBossData(Bosses data) {
		player.getPacketSender().sendString(51200, data.description1);
		player.getPacketSender().sendString(51201, data.description2);
		player.getPacketSender().sendString(51202, data.description3);
		player.getPacketSender().sendString(51203, data.description4);
		player.getPacketSender().sendString(51204, data.description5);
		player.getPacketSender().sendNpcOnInterface(50514, data.npcId, data.adjustedZoom != 0 ? data.adjustedZoom : 0);
	}

	public  void sendMonsterData( Monsters data) {
		player.getPacketSender().sendString(51200, data.description1);
		player.getPacketSender().sendString(51201, data.description2);
		player.getPacketSender().sendString(51202, data.description3);
		player.getPacketSender().sendString(51203, data.description4);
		player.getPacketSender().sendString(51204, data.description5);
		player.getPacketSender().sendNpcOnInterface(50514, data.npcId, data.adjustedZoom != 0 ? data.adjustedZoom : 0);
	}

	public  void sendWildyData( Wilderness data) {
		player.getPacketSender().sendString(51200, data.description1);
		player.getPacketSender().sendString(51201, data.description2);
		player.getPacketSender().sendString(51202, data.description3);
		player.getPacketSender().sendString(51203, data.description4);
		player.getPacketSender().sendString(51204, data.description5);
		player.getPacketSender().sendNpcOnInterface(50514, data.npcId, data.adjustedZoom != 0 ? data.adjustedZoom : 0);
	}

	public  void sendSkillingData( Skilling data) {
		player.getPacketSender().sendString(51200, data.description1);
		player.getPacketSender().sendString(51201, data.description2);
		player.getPacketSender().sendString(51202, data.description3);
		player.getPacketSender().sendString(51203, data.description4);
		player.getPacketSender().sendString(51204, data.description5);
		player.getPacketSender().sendNpcOnInterface(50514, data.npcId, data.adjustedZoom != 0 ? data.adjustedZoom : 0);
	}

	public  void sendMinigameData( Minigames data) {
		player.getPacketSender().sendString(51200, data.description1);
		player.getPacketSender().sendString(51201, data.description2);
		player.getPacketSender().sendString(51202, data.description3);
		player.getPacketSender().sendString(51203, data.description4);
		player.getPacketSender().sendString(51204, data.description5);
		player.getPacketSender().sendNpcOnInterface(50514, data.npcId, data.adjustedZoom != 0 ? data.adjustedZoom : 0);
	}

	public  void sendCityData( Cities data) {
		player.getPacketSender().sendString(51200, data.description1);
		player.getPacketSender().sendString(51201, data.description2);
		player.getPacketSender().sendString(51202, data.description3);
		player.getPacketSender().sendString(51203, data.description4);
		player.getPacketSender().sendString(51204, data.description5);
		player.getPacketSender().sendNpcOnInterface(50514, data.npcId, data.adjustedZoom != 0 ? data.adjustedZoom : 0);
	}

	public  void sendBossTab() {
		player.getPacketSender().sendInterface(50500);
		player.currentTabs = 0;
		player.getTeleportInterface().clearData();
		resetOldData();
		player.getPacketSender().sendString(CATEGORY_NAME_ID, "Bosses");
		for (Bosses data : Bosses.values()) {
			player.getPacketSender().sendString(data.textId, data.name);
		}

	}

	public  void sendMonsterTab() {
		player.getPacketSender().sendInterface(50500);
		player.currentTabs  = 1;
		player.getTeleportInterface().clearData();
		player.getPacketSender().sendString(CATEGORY_NAME_ID, "Monsters");
		for (Monsters data : Monsters.values()) {
			player.getPacketSender().sendString(data.textId, data.name);
		}
	}

	public  void sendWildyTab() {
		player.getPacketSender().sendInterface(50500);
		player.currentTabs = 2;
		player.getTeleportInterface().clearData();
		player.getPacketSender().sendString(CATEGORY_NAME_ID, "Multi Raids");
		for (Wilderness data : Wilderness.values()) {
			player.getPacketSender().sendString(data.textId, data.name);
		}
	}

	public  void sendSkillingTab() {
		player.getPacketSender().sendInterface(50500);
		player.currentTabs = 3;
		player.getTeleportInterface().clearData();
		player.getPacketSender().sendString(CATEGORY_NAME_ID, "Shops");
		for (Skilling data : Skilling.values()) {
			player.getPacketSender().sendString(data.textId, data.name);
		}
	}

	public  void sendMinigamesTab() {
		player.getPacketSender().sendInterface(50500);
		player.currentTabs = 4;
		player.getTeleportInterface().clearData();
		player.getPacketSender().sendString(CATEGORY_NAME_ID, "Minigames");
		for (Minigames data : Minigames.values()) {
			player.getPacketSender().sendString(data.textId, data.name);
		}
	}

	public  void sendCitiesTab() {
		player.getPacketSender().sendInterface(50500);
		player.currentTabs = 5;
		player.getTeleportInterface().clearData();
		player.getPacketSender().sendString(CATEGORY_NAME_ID, "Events");
		for (Cities data : Cities.values()) {
			player.getPacketSender().sendString(data.textId, data.name);
		}
	}

	public  void sendDrops( int npcId) {
		player.getPacketSender().resetItemsOnInterface(51251, 100);
		try {
			NPCDrops drops = NPCDrops.forId(npcId);
			if(drops == null) {
				System.out.println("Was null");
				return;
			}
			List<Item> npcDrops = new ArrayList<>();
			for (int i = 0; i < drops.getDropList().length; i++) {
				npcDrops.add(new Item(drops.getDropList()[i].getId(), drops.getDropList()[i].getItem().getAmount()));
			//	player.getPacketSender().sendItemOnInterface(51251, drops.getDropList()[i].getId(), i,
			//			drops.getDropList()[i].getItem().getAmount());
			}
			player.getPA().sendItemsOnInterface(51251, 100, npcDrops, true); //is this, put this in and test okay
		} catch (Exception e) { // so only this?
			e.printStackTrace();
		}
	}
}
