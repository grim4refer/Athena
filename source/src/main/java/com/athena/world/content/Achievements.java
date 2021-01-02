package com.athena.world.content;

import com.athena.model.Item;
import com.athena.util.Misc;
import com.athena.world.entity.impl.player.Player;

public class Achievements {

	public enum AchievementData {

		FILL_WELL_OF_GOODWILL_1M(Difficulty.EASY, "Pour 1M Into The Well", 45005, new int[]{1, 1000000}, "", "", "", "", "", "", new Item(5022, 100000), new Item(989, 5), new Item(11316, 5)),
		DEFEAT_10_CARS(Difficulty.EASY, "Defeat 10 Cars", 45006, new int[]{0, 10}, "", "", "", "", "", "", new Item(5022, 100000000), new Item(989, 5), new Item(11316, 5)),
		//FILL_WELL_OF_GOODWILL_1M(Difficulty.EASY, "Pour 1M Into The Well", 45006, new int[]{1, 1000000}, "", "", "", "", "", "", new Item(995, 100000000), new Item(989, 5), new Item(19936, 1)),
		CUT_AN_OAK_TREE(Difficulty.EASY, "Cut An Oak Tree", 45007, null, "", "", "", "", "", "", new Item(5022, 100000000), new Item(989, 5), new Item(11316, 5)),
		BURN_AN_OAK_LOG(Difficulty.EASY, "Burn An Oak Log", 45008, null, "", "", "", "", "", "", new Item(5022, 100000000), new Item(989, 5), new Item(11316, 5)),
		FISH_A_SALMON(Difficulty.EASY, "Fish A Salmon", 45009, null, "", "", "", "", "", "", new Item(5022, 100000000), new Item(989, 5), new Item(11316, 5)),
		COOK_A_SALMON(Difficulty.EASY, "Cook A Salmon", 45010, null, "", "", "", "", "", "", new Item(5022, 100000000), new Item(989, 5), new Item(11316, 5)),
		EAT_A_SALMON(Difficulty.EASY, "Eat A Salmon", 45011, null, "", "", "", "", "", "", new Item(5022, 100000000), new Item(989, 5), new Item(11316, 5)),
		MINE_SOME_IRON(Difficulty.EASY, "Mine Some Iron", 45012, null, "", "", "", "", "", "", new Item(5022, 100000000), new Item(989, 5), new Item(11316, 5)),
		SMELT_AN_IRON_BAR(Difficulty.EASY, "Smelt An Iron Bar", 45013, null, "", "", "", "", "", "", new Item(5022, 100000000), new Item(989, 5), new Item(11316, 5)),
		HARVEST_A_CROP(Difficulty.EASY, "Harvest A Crop", 45014, null, "", "", "", "", "", "", new Item(5022, 100000000), new Item(989, 5), new Item(11316, 5)),
		INFUSE_A_DREADFOWL_POUCH(Difficulty.EASY, "Infuse A Dreadfowl Pouch", 45015, null, "", "", "", "", "", "", new Item(5022, 100000000), new Item(989, 5), new Item(11316, 5)),
		CATCH_A_YOUNG_IMPLING(Difficulty.EASY, "Catch A Young Impling", 45016, null, "", "", "", "", "", "", new Item(5022, 100000000), new Item(989, 5), new Item(11316, 5)),
		CRAFT_A_PAIR_OF_LEATHER_BOOTS(Difficulty.EASY, "Craft A Pair of Leather Boots", 45017, null, "", "", "", "", "", "", new Item(5022, 100000000), new Item(989, 5), new Item(11316, 5)),
		CLIMB_AN_AGILITY_OBSTACLE(Difficulty.EASY, "Climb An Agility Obstacle", 45018, null, "", "", "", "", "", "", new Item(5022, 100000000), new Item(989, 5), new Item(11316, 5)),
		FLETCH_SOME_ARROWS(Difficulty.EASY, "Fletch Some Arrows", 45019, null, "", "", "", "", "", "", new Item(5022, 100000000), new Item(989, 5), new Item(11316, 5)),
		STEAL_A_RING(Difficulty.EASY, "Steal A Ring", 45020, null, "", "", "", "", "", "", new Item(5022, 100000000), new Item(989, 5), new Item(11316, 5)),
		MIX_A_POTION(Difficulty.EASY, "Mix A Potion", 45021, null, "", "", "", "", "", "", new Item(5022, 100000000), new Item(989, 5), new Item(11316, 5)),
		RUNECRAFT_SOME_RUNES(Difficulty.EASY, "Runecraft Some Runes", 45022, null, "", "", "", "", "", "", new Item(5022, 100000000), new Item(989, 5), new Item(19936, 1)),
		BURY_A_INFERNAL_GROUDON(Difficulty.EASY, "Bury A Infernal Groudon Bone", 45023, null, "", "", "", "", "", "", new Item(5022, 100000000), new Item(989, 5), new Item(11316, 5)),
		COMPLETE_A_SLAYER_TASK(Difficulty.EASY, "Complete A Slayer Task", 45024, null, "", "", "", "", "", "", new Item(5022, 100000000), new Item(989, 5), new Item(11316, 5)),
		KILL_A_MONSTER_USING_MELEE(Difficulty.EASY, "Kill a Monster Using Melee", 45025, null, "", "", "", "", "", "", new Item(5022, 100000000), new Item(989, 5), new Item(11316, 5)),
		KILL_A_MONSTER_USING_RANGED(Difficulty.EASY, "Kill a Monster Using Ranged", 45026, null, "", "", "", "", "", "", new Item(5022, 100000000), new Item(989, 5), new Item(11316, 5)),
		KILL_A_MONSTER_USING_MAGIC(Difficulty.EASY, "Kill a Monster Using Magic", 45027, null, "", "", "", "", "", "", new Item(5022, 100000000), new Item(989, 5), new Item(11316, 5)),
		DEAL_EASY_DAMAGE_USING_MELEE(Difficulty.EASY, "Deal 1000 Melee Damage", 45028, new int[]{2, 1000}, "", "", "", "", "", "", new Item(5022, 100000000), new Item(989, 5), new Item(11316, 5)),
		DEAL_EASY_DAMAGE_USING_RANGED(Difficulty.EASY, "Deal 1000 Ranged Damage", 45029, new int[]{3, 1000}, "", "", "", "", "", "", new Item(5022, 100000000), new Item(989, 5), new Item(11316, 5)),
		DEAL_EASY_DAMAGE_USING_MAGIC(Difficulty.EASY, "Deal 1000 Magic Damage", 45030, new int[]{4, 1000}, "", "", "", "", "", "", new Item(5022, 100000000), new Item(989, 5), new Item(11316, 5)),
		DEFEAT_100_CARS(Difficulty.EASY, "Defeat 100 Cars", 45031, new int[]{5, 100}, "", "", "", "", "", "", new Item(5022, 100000000), new Item(989, 5), new Item(11316, 5)),
		CUT_100_MAGIC_LOGS(Difficulty.EASY, "Cut 100 Magic Logs", 45032, new int[]{6, 100}, "", "", "", "", "", "", new Item(5022, 100000000), new Item(989, 5), new Item(11316, 5)),
		BURN_100_MAGIC_LOGS(Difficulty.EASY, "Burn 100 Magic Logs", 45033, new int[]{7, 100}, "", "", "", "", "", "", new Item(5022, 100000000), new Item(989, 5), new Item(11316, 5)),




		DEFEAT_GOKU(Difficulty.MEDIUM, "Defeat Goku", 45037, null, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		FISH_25_ROCKTAILS(Difficulty.MEDIUM, "Fish 25 Rocktails", 45038, new int[]{9, 25}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		COOK_25_ROCKTAILS(Difficulty.MEDIUM, "Cook 25 Rocktails", 45039, new int[]{10, 25}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		MINE_25_RUNITE_ORES(Difficulty.MEDIUM, "Mine 25 Runite Ores", 45040, new int[]{11, 25}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		SMELT_25_RUNE_BARS(Difficulty.MEDIUM, "Smelt 25 Rune Bars", 45041, new int[]{12, 25}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		HARVEST_10_TORSTOLS(Difficulty.MEDIUM, "Harvest 10 Torstols", 45042, new int[]{13, 10}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		INFUSE_25_TITAN_POUCHES(Difficulty.MEDIUM, "Infuse 25 Steel Titans", 45043, new int[]{14, 25}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		CATCH_5_KINGLY_IMPLINGS(Difficulty.MEDIUM, "Catch 5 Kingly Implings", 45044, new int[]{15, 5}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		COMPLETE_A_HARD_SLAYER_TASK(Difficulty.MEDIUM, "Complete A Hard Slayer Task", 45045, null, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		CRAFT_20_BLACK_DHIDE_BODIES(Difficulty.MEDIUM, "Craft 20 Black D'hide Bodies", 45046, new int[]{16, 20}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		FLETCH_450_RUNE_ARROWS(Difficulty.MEDIUM, "Fletch 450 Rune Arrows", 45047, new int[]{17, 450}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		STEAL_140_SCIMITARS(Difficulty.MEDIUM, "Steal 140 Scimitars", 45048, new int[]{18, 140}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		MIX_AN_OVERLOAD_POTION(Difficulty.MEDIUM, "Mix An Overload Potion", 45049, null, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		DEFEAT_500_BELERIONS(Difficulty.MEDIUM, "Defeat 500 Belerions", 45050, new int[]{19, 100}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		CLIMB_50_AGILITY_OBSTACLES(Difficulty.MEDIUM, "Climb 50 Agility Obstacles", 45051, new int[]{20, 50}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		RUNECRAFT_500_BLOOD_RUNES(Difficulty.MEDIUM, "Runecraft 500 Blood Runes", 45052, new int[]{21, 500}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		BURY_25_BAP(Difficulty.MEDIUM, "Bury 25 Baphomet Bones", 45053, new int[]{22, 25}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		DEFEAT_VEGETA(Difficulty.MEDIUM, "Defeat Vegeta", 45054, null, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		DEAL_MEDIUM_DAMAGE_USING_MELEE(Difficulty.MEDIUM, "Deal 100K Melee Damage", 45055, new int[]{23, 100000}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		DEAL_MEDIUM_DAMAGE_USING_RANGED(Difficulty.MEDIUM, "Deal 100K Ranged Damage", 45056, new int[]{24, 100000}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		DEAL_MEDIUM_DAMAGE_USING_MAGIC(Difficulty.MEDIUM, "Deal 100K Magic Damage", 45057, new int[]{25, 100000}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		DEFEAT_500_CARS(Difficulty.MEDIUM, "Defeat 500 Cars", 45058, new int[]{26, 500}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		DEFEAT_500_CRASH(Difficulty.MEDIUM, "Defeat 500 Crash", 45059, new int[]{27, 500}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		DEFEAT_PLANEFREEZER(Difficulty.MEDIUM, "Defeat PlaneFreezer", 45060, null, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		DEFEAT_THE_CULINAROMANCER(Difficulty.MEDIUM, "Defeat The Culinaromancer", 45061, null, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		DEFEAT_NOMAD(Difficulty.MEDIUM, "Defeat Nomad", 45062, null, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		DEFEAT_BROLY(Difficulty.MEDIUM, "Defeat Broly", 45063, null, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		DEFEAT_500_AURELIAS(Difficulty.MEDIUM, "Defeat 500 Aurelias", 45064, new int[]{28, 100}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		CUT_5000_MAGIC_LOGS(Difficulty.MEDIUM, "Cut 5000 Magic Logs", 45065, new int[]{30, 5000}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		BURN_2500_MAGIC_LOGS(Difficulty.MEDIUM, "Burn 2500 Magic Logs", 45066, new int[]{31, 2500}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),



		FISH_2000_ROCKTAILS(Difficulty.HARD, "Fish 2000 Rocktails", 45070, new int[]{32, 2000}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		COOK_1000_ROCKTAILS(Difficulty.HARD, "Cook 1000 Rocktails", 45071, new int[]{33, 1000}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		MINE_2000_RUNITE_ORES(Difficulty.HARD, "Mine 2000 Runite Ores", 45072, new int[]{34, 2000}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		SMELT_1000_RUNE_BARS(Difficulty.HARD, "Smelt 1000 Rune Bars", 45073, new int[]{35, 1000}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		HARVEST_1000_TORSTOLS(Difficulty.HARD, "Harvest 1000 Torstols", 45074, new int[]{36, 1000}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		INFUSE_500_STEEL_TITAN_POUCHES(Difficulty.HARD, "Infuse 500 Steel Titans", 45075, new int[]{37, 500}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		CRAFT_1000_DIAMOND_GEMS(Difficulty.HARD, "Craft 1000 Diamond Gems", 45076, new int[]{38, 1000}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		CATCH_100_KINGLY_IMPLINGS(Difficulty.HARD, "Catch 100 Kingly Imps", 45077, new int[]{39, 100}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		FLETCH_5000_RUNE_ARROWS(Difficulty.HARD, "Fletch 5000 Rune Arrows", 45078, new int[]{40, 5000}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		STEAL_5000_SCIMITARS(Difficulty.HARD, "Steal 5000 Scimitars", 45079, new int[]{41, 5000}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		RUNECRAFT_8000_BLOOD_RUNES(Difficulty.HARD, "Runecraft 8000 Blood Runes", 45080, new int[]{42, 8000}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		BURY_500_BAP(Difficulty.HARD, "Bury 500 Baphomet bones", 45081, new int[]{43, 500}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		MIX_100_OVERLOAD_POTIONS(Difficulty.HARD, "Mix 100 Overload Potions", 45082, new int[]{44, 100}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		COMPLETE_AN_ELITE_SLAYER_TASK(Difficulty.HARD, "Complete An Elite Slayer Task", 45083, null, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		DEFEAT_IKTOMI(Difficulty.HARD, "Defeat Iktomi", 45084, null, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		DEAL_HARD_DAMAGE_USING_MELEE(Difficulty.HARD, "Deal 10M Melee Damage", 45085, new int[]{45, 10000000}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		DEAL_HARD_DAMAGE_USING_RANGED(Difficulty.HARD, "Deal 10M Ranged Damage", 45086, new int[]{46, 10000000}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		DEAL_HARD_DAMAGE_USING_MAGIC(Difficulty.HARD, "Deal 10M Magic Damage", 45087, new int[]{47, 10000000}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		DEFEAT_LUCARIO(Difficulty.HARD, "Defeat Lucario", 45088, null, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		DEFEAT_MEWTWO(Difficulty.HARD, "Defeat MewTwo", 45089, null, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		DEFEAT_CHARMELEON(Difficulty.HARD, "Defeat Charmeleon", 45090, null, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		DEFEAT_SQUIRTLE(Difficulty.HARD, "Defeat Squirtle", 45091, null, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		DEFEAT_PIKACHU(Difficulty.HARD, "Defeat Pickachu", 45092, null, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		DEFEAT_SONIC(Difficulty.HARD, "Defeat Sonic", 45093, null, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		DEFEAT_DONKEYKONG(Difficulty.HARD, "Defeat Donkey Kong", 45094, null, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		DEFEAT_MR_KRABS(Difficulty.HARD, "Defeat Mr Krabs", 45095, null, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		DEFEAT_NUTELLA(Difficulty.HARD, "Defeat Nutella", 45096, null, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		DEFEAT_MAYONNAISE(Difficulty.HARD, "Defeat Mayonnaise", 45097, null, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		CUT_AN_ONYX_STONE(Difficulty.HARD, "Cut An Onyx Stone", 45098, null, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		REACH_MAX_EXP_IN_A_SKILL(Difficulty.HARD, "Reach Max Exp In A Skill", 45099, new int[]{49, 2000000000}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		REACH_LEVEL_99_IN_ALL_SKILLS(Difficulty.HARD, "Reach Level 99 In All Skills", 45100, null, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		FILL_WELL_OF_GOODWILL_250M(Difficulty.HARD, "Pour 250M Into The Well", 45101, new int[]{29, 250000000}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),

		DEFEAT_10000_MONSTERS(Difficulty.ELITE, "Defeat 10,000 Monsters", 45104, new int[]{49, 10000}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		DEFEAT_500_BOSSES(Difficulty.ELITE, "Defeat 500 Boss Monsters", 45105, new int[]{50, 500}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		REACH_50K_LOYALTY_POINTS(Difficulty.ELITE, "Reach 50k Loyalty Points", 45106, null, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		UNLOCK_ALL_LOYALTY_TITLES(Difficulty.ELITE, "Unlock All Loyalty Titles", 45107, new int[]{52, 11}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		//COMPLETE_ALL_HARD_TASKS(Difficulty.ELITE, "Complete All Hard Tasks", 45108, new int[]{48, 32}, "", "", "", "", "", "", new Item(995, 100000000), new Item(15371, 5), new Item(19936, 1)),
		;

		AchievementData(Difficulty difficulty, String interfaceLine, int interfaceFrame, int[] progressData,
						String desc1, String desc2, String desc3, String desc4, String desc5, String desc6, Item Rewards,
						Item Rewards1, Item Rewards2) {
			this.difficulty = difficulty;
			this.interfaceLine = interfaceLine;
			this.interfaceFrame = interfaceFrame;
			this.progressData = progressData;
			this.desc1 = desc1;
			this.desc2 = desc2;
			this.desc3 = desc3;
			this.desc4 = desc4;
			this.desc5 = desc5;
			this.desc6 = desc6;
			this.Rewards = Rewards;
			this.Rewards1 = Rewards1;
			this.Rewards2 = Rewards2;
		}

		private Difficulty difficulty;
		private String interfaceLine;
		private int interfaceFrame;
		private int[] progressData;
		private String desc1;
		private String desc2;
		private String desc3;
		private String desc4;
		private String desc5;
		private String desc6;
		private Item Rewards;
		private Item Rewards1;
		private Item Rewards2;

		public Difficulty getDifficulty() {
			return difficulty;
		}
	}

	public enum Difficulty {
		BEGINNER, EASY, MEDIUM, HARD, ELITE;
	}

	public static boolean handleButton(Player player, int button) {
		if(!(button >= -20531 && button <= -20429)) {
			return false;
		}
		int index = -1;
		if(button >= -20531 && button <= -20503) {
			index = 20531 + button;
		} else if(button >= -20499 && button <= -20468) {
			index = 29 + 20499 + button;
		} else if(button >= -20466 && button <= -20434) {
			index = 59 + 20466 + button;
		} else if(button >= -20432 && button <= -20427) {
			index = 91 + 20432 + button;
		}  else if (button >= -20426 && button <= -20425) {
			index = 92 + 20430 + button;
		}  else if (button >= -20426 && button <= -20425) {
			index = 93 + 20428 + button;
		}  else if (button >= -20424 && button <= -20423) {
			index = 94 + 20426 + button;
		}  else if (button >= -20426 && button <= -20425) {
			index = 95 + 20424 + button;
		} else if (button >= -23731 && button <= -23672) {
			if (player.difficulty == Difficulty.EASY) {
				index = 23731 + button;
			} else if (player.difficulty == Difficulty.MEDIUM) {
				index = 29 + 23731 + button;
			} else if (player.difficulty == Difficulty.HARD) {
				index = 59 + 23731 + button;
			} else if (player.difficulty == Difficulty.ELITE) {
				index = 91 + 23731 + button;
			}
		}
		if (index >= 0 && index < AchievementData.values().length) {
			AchievementData achievement = AchievementData.values()[index];
			openInterface(player, achievement);
		}
		if(index >= 0 && index < AchievementData.values().length) {
			AchievementData achievement = AchievementData.values()[index];
			if(player.getAchievementAttributes().getCompletion()[achievement.ordinal()]) {

				player.getPacketSender().sendMessage("<img=10> <col=339900>You have completed the achievement: "+achievement.interfaceLine+".");
			} else if(achievement.progressData == null) {
				player.getPacketSender().sendMessage("<img=10> <col=660000>You have not started the achievement: "+achievement.interfaceLine+".");
			} else {
				int progress = player.getAchievementAttributes().getProgress()[achievement.progressData[0]];
				int requiredProgress = achievement.progressData[1];
				if(progress == 0) {
					player.getPacketSender().sendMessage("<img=10> <col=660000>You have not started the achievement: "+achievement.interfaceLine+".");
				} else if(progress != requiredProgress) {
					player.getPacketSender().sendMessage("<img=10> <col=FFFF00>Your progress for this achievement is currently at: "+Misc.insertCommasToNumber(""+progress)+"/"+Misc.insertCommasToNumber(""+requiredProgress)+".");
				}
			}
		}
		return true;
	}

	public static void updateInterface(Player player) {
		for(AchievementData achievement : AchievementData.values()) {
			boolean completed = player.getAchievementAttributes().getCompletion()[achievement.ordinal()];
			boolean progress = achievement.progressData != null && player.getAchievementAttributes().getProgress()[achievement.progressData[0]] > 0;
			player.getPacketSender().sendString(achievement.interfaceFrame, (completed ? "@gre@" : progress ? "@yel@" : "@red@") + achievement.interfaceLine);
		}
		player.getPacketSender().sendString(45001, "Achievements: "+player.getPointsHandler().getAchievementPoints()+"/"+AchievementData.values().length);
	}
	public static void openInterface(Player player, AchievementData task) {
		int id = 41805;
		int id1 = 41805;
		if (task.difficulty == Difficulty.MEDIUM) {
			id -= 29;
		}
		if (task.difficulty == Difficulty.HARD) {
			id -= 59;
		}
		if (task.difficulty == Difficulty.ELITE) {
			id -= 91;
		}

		player.difficulty = task.difficulty;
		for (AchievementData achievement : AchievementData.values()) {
			player.getPacketSender().sendString(id1++, "");
			boolean completed = player.getAchievementAttributes().getCompletion()[achievement.ordinal()];
			boolean progress = achievement.progressData != null
					&& player.getAchievementAttributes().getProgress()[achievement.progressData[0]] > 0;
			if (achievement.difficulty == task.difficulty) {
				player.getPacketSender().sendString(id,
						(completed ? "@gre@" : progress ? "@yel@" : "@red@") + achievement.interfaceLine);
			}
			id++;
		}
		player.getPacketSender().sendString(41764, "Achievements");

		player.getPacketSender().sendString(41769, "Easy");
		player.getPacketSender().sendString(41770, "Medium");
		player.getPacketSender().sendString(41771, "Hard");
		player.getPacketSender().sendString(41772, "Elite");

		player.getPacketSender().sendString(41773, "" + task.difficulty);

		player.getPacketSender().sendString(41774, "" + task.interfaceLine);

		int points = 0;
		if (task.difficulty == Difficulty.EASY) {
			points = 3;
		}else if (task.difficulty == Difficulty.MEDIUM) {
			points = 10;
		}else if (task.difficulty == Difficulty.HARD) {
			points = 15;
		}else if (task.difficulty == Difficulty.ELITE) {
			points = 150;
		}
		//player.getPacketSender().sendString(41881, points + " Athena points");

		player.getPacketSender().sendString(41776, "" + task.desc1);
		player.getPacketSender().sendString(41777, "" + task.desc2);
		player.getPacketSender().sendString(41778, "" + task.desc3);
		player.getPacketSender().sendString(41779, "" + task.desc4);
		player.getPacketSender().sendString(41780, "" + task.desc5);
		player.getPacketSender().sendString(41781, "" + task.desc6);

		player.getPA().sendItemOnInterface(41901, task.Rewards.getId(), task.Rewards.getAmount());
		player.getPA().sendItemOnInterface(41902, task.Rewards1.getId(), task.Rewards1.getAmount());
		player.getPA().sendItemOnInterface(41903, task.Rewards2.getId(), task.Rewards2.getAmount());

		if (player.getAchievementAttributes().getCompletion()[task.ordinal()]) {
			player.getPacketSender().sendString(41775, "Progress: @gre@100% (1/1)");
		} else if (task.progressData == null) {
			player.getPacketSender().sendString(41775, "Progress: @gre@0% (0/0)");

		} else {
			int progressTask = player.getAchievementAttributes().getProgress()[task.progressData[0]];
			int progressTotal = task.progressData[1];
			long percent = (progressTask / progressTotal) * 100;
			if (progressTask == 0) {
				player.getPacketSender().sendString(41775,
						"Progress: @gre@0 (" + Misc.insertCommasToNumber("" + progressTask) + "/"
								+ Misc.insertCommasToNumber("" + progressTotal) + ")");
			} else if (progressTask != progressTotal) {
				player.getPacketSender().sendString(41775,
						"Progress: @gre@" + percent + " (" + Misc.insertCommasToNumber("" + progressTask) + "/"
								+ Misc.insertCommasToNumber("" + progressTotal) + ")");
			}
		}

		player.getPA().sendInterface(41750);
	}

	public static void setPoints(Player player) {
		int points = 0;
		for(AchievementData achievement : AchievementData.values()) {
			if(player.getAchievementAttributes().getCompletion()[achievement.ordinal()]) {
				points++;
			}
		}
		player.getPointsHandler().setAchievementPoints(points, false);
	}

	public static void doProgress(Player player, AchievementData achievement) {
		doProgress(player, achievement, 1);
	}

	public static void doProgress(Player player, AchievementData achievement, int amt) {
		if(player.getAchievementAttributes().getCompletion()[achievement.ordinal()])
			return;
		if(achievement.progressData != null) {
			int progressIndex = achievement.progressData[0];
			int amountNeeded = achievement.progressData[1];
			int previousDone = player.getAchievementAttributes().getProgress()[progressIndex];
			if((previousDone+amt) < amountNeeded) {
				player.getAchievementAttributes().getProgress()[progressIndex] = previousDone+amt;
				if(previousDone == 0)
					player.getPacketSender().sendString(achievement.interfaceFrame, "@yel@"+ achievement.interfaceLine);
			} else {
				finishAchievement(player, achievement);
			}
		}
	}

	public static void finishAchievement(Player player, AchievementData achievement) {
		if (player.getAchievementAttributes().getCompletion()[achievement.ordinal()])
			return;
		player.getAchievementAttributes().getCompletion()[achievement.ordinal()] = true;
		player.getPacketSender().sendString(achievement.interfaceFrame, ("@gre@") + achievement.interfaceLine)
				.sendMessage("<img=10> <col=339900>You have completed the achievement "
						+ Misc.formatText(achievement.toString().toLowerCase() + "."))
				.sendString(45001, "Achievements: " + player.getPointsHandler().getAchievementPoints() + "/"
						+ AchievementData.values().length);
		player.getInventory().add(achievement.Rewards);
		player.getInventory().add(achievement.Rewards1);
		player.getInventory().add(achievement.Rewards2);
		//player.getBank(player.getCurrentBankTab()).add(achievement.Rewards);
		//player.getBank(player.getCurrentBankTab()).add(achievement.Rewards1);
		//player.getBank(player.getCurrentBankTab()).add(achievement.Rewards2);
		player.getPointsHandler().setAchievementPoints(1, true);

		int points = 0;
		if (achievement.difficulty == Difficulty.EASY) {
			points = 3;
		}else if (achievement.difficulty == Difficulty.MEDIUM) {
			points = 10;
		}else if (achievement.difficulty == Difficulty.HARD) {
			points = 15;
		}else if (achievement.difficulty == Difficulty.ELITE) {
			points = 150;
		}
		player.getPointsHandler().setAchievementPoints(1, true);
	}

	public static class AchievementAttributes {

		public AchievementAttributes(){}

		/** ACHIEVEMENTS **/
		private boolean[] completed = new boolean[AchievementData.values().length];
		private int[] progress = new int[53];

		public boolean[] getCompletion() {
			return completed;
		}

		public void setCompletion(int index, boolean value) {
			this.completed[index] = value;
		}

		public void setCompletion(boolean[] completed) {
			this.completed = completed;
		}

		public int[] getProgress() {
			return progress;
		}

		public void setProgress(int index, int value) {
			this.progress[index] = value;
		}

		public void setProgress(int[] progress) {
			this.progress = progress;
		}

		/** MISC **/
		private int coinsGambled;
		private double totalLoyaltyPointsEarned;
		private boolean[] godsKilled = new boolean[5];

		public int getCoinsGambled() {
			return coinsGambled;
		}

		public void setCoinsGambled(int coinsGambled) {
			this.coinsGambled = coinsGambled;
		}

		public double getTotalLoyaltyPointsEarned() {
			return totalLoyaltyPointsEarned;
		}

		public void incrementTotalLoyaltyPointsEarned(double totalLoyaltyPointsEarned) {
			this.totalLoyaltyPointsEarned += totalLoyaltyPointsEarned;
		}

		public boolean[] getGodsKilled() {
			return godsKilled;
		}

		public void setGodKilled(int index, boolean godKilled) {
			this.godsKilled[index] = godKilled;
		}

		public void setGodsKilled(boolean[] b) {
			this.godsKilled = b;
		}
	}
}

