package com.arlania.world.content.skill.impl.slayer;

import com.arlania.model.Position;
import com.arlania.util.Misc;

/**
 * @author Gabriel Hannason 
 */

public enum SlayerTasks {

	NO_TASK(null, -1, null, -1, null),

	/**
	 * Easy tasks
	 */
	CARS(SlayerMaster.VANNAKA, 1265, "Cars can be found in the Monster Teleport.", 2100, new Position(2505, 2507, 1)),
	CARSS(SlayerMaster.VANNAKA, 1265, "Cars can be found in the Monster Teleport.", 2100, new Position(2505, 2507, 1)),
	CARSSS(SlayerMaster.VANNAKA, 1265, "Cars can be found in the Monster Teleport.", 2100, new Position(2505, 2507, 1)),
	CARSSSS(SlayerMaster.VANNAKA, 1265, "Cars can be found in the Monster Teleport.", 2100, new Position(2505, 2507, 1)),
	CARSSSSS(SlayerMaster.VANNAKA, 1265, "Cars can be found in the Monster Teleport.", 2100, new Position(2505, 2507, 1)),
	/**
	 * Medium tasks
	 */
	TERROR_DOG(SlayerMaster.DURADEL, 5417, "Terror dog can be found in the Monster teleport.", 6500, new Position(2503, 2539, 1)),
	NATURE_SWORD(SlayerMaster.DURADEL, 1459, "Nature Sword can be found in the Monster teleport.", 6500, new Position(2548, 2551, 1)),
	PINGUIN(SlayerMaster.DURADEL, 131, "Pinguin can be found in the Monster teleport.", 6500, new Position(2521, 2551, 1)),
	BELERION(SlayerMaster.DURADEL, 4392, "Belerion can be found in the Monster teleport.", 6500, new Position(2551, 2517, 1)),
	LIVYATHAN(SlayerMaster.DURADEL, 52, "Livyathan can be found in the Monster teleport.", 6500, new Position(2552, 2517, 2)),
	OBLIVION(SlayerMaster.DURADEL, 6102, "Aurelion can be found in the Monster Teleport", 6500, new Position(2568, 2550, 1)),
	
	BANDOS_AVATAR(SlayerMaster.KURADEL, 4540, "Bandos Avatar can be found in the boss teleport.", 14000, new Position(2867, 9946, 0)),
	ABBADON(SlayerMaster.KURADEL, 6303, "Abbadon can be found in the Bosses Teleport", 14000, new Position(2516, 5173, 0)),
	INFERAL_GORUDON(SlayerMaster.KURADEL, 1234, "Infernal groudon can be found in the Bosses :Teleport", 14000, new Position(1240, 1227, 0)),
	Alien(SlayerMaster.KURADEL, 4419, "Alien can be found in the Bosss Teleport", 14000, new Position(2420, 4690, 0)),
	BAPHOMET(SlayerMaster.KURADEL, 2236, "Baphomet can be found in the Bosses Teleport", 14000, new Position(2461, 10156, 0)),
	ABZYOU(SlayerMaster.KURADEL, 6313, "Abzyou can be found in the Bosses Teleport", 14000, new Position(2720, 9971, 0)),
	LEFOSH(SlayerMaster.KURADEL, 6309, "Le'fosh can be found in the Bosses Teleport", 14000, new Position(3620, 3534, 0)),
	GOKU(SlayerMaster.KURADEL, 6336, "Goku can be found in the Bosses Teleport", 14000, new Position(2706, 9632, 0)),
	VEGETA(SlayerMaster.KURADEL, 389, "Vegeta can be found in the Bosses Teleport", 14000, new Position(2706, 9632, 0)),
	BROLY(SlayerMaster.KURADEL, 7, "Broly can be found in the Bosses Teleport", 14000, new Position(1941, 4687, 0)),
	PLANEFREEZER(SlayerMaster.KURADEL, 9939, "Planefreezer can be found in Boss Teleports.", 14000, new Position(2490, 9947, 0)),
	NEX(SlayerMaster.KURADEL, 13447, "Nex can be found in the Boss Teleport", 14000, new Position(2903, 5203, 0)),
	YANILLE_GUARDIAN(SlayerMaster.KURADEL, 6305, "Yanille Guardian can be found in the Monster Teleport", 14000, new Position(2479, 10144, 0)),
	/**
	 * Hard tasks
	 */
	IKTOMI(SlayerMaster.SUMONA, 6307, "Iktomi can be found in the Boss Teleport", 8500, new Position(1940, 5001, 0)),
	CURSER_BEARER(SlayerMaster.SUMONA, 10126, "Unholy Curse Bearer can be found in Boss Teleports.", 9000, new Position(2525, 4777, 0)),
	VORAGO(SlayerMaster.SUMONA, 2071, "Vorago can be found in the Boss Teleport", 9000, new Position(2535, 2565, 1)),
	GODZILLA(SlayerMaster.SUMONA, 1299, "Godzilla can be found in the Minigames Teleport", 9000, new Position(2900, 3617, 0)),
	IUD(SlayerMaster.SUMONA, 53, "Infernal Undead Dragon can be found in the Minigames Teleport", 9000, new Position(2720, 4777, 0)),
	/**
	 *
	 * Elite
	 */
	YANI(SlayerMaster.SUMONA, 5592, "Yani can be found in the Bosss Teleport.", 100000, new Position(2272, 4693, 0))
	//PLANEFREEZER(SlayerMaster.SUMONA, 9939, "Planefreezer can be found in Boss Teleports 2.", 100000, new Position(2903, 5203));
	;

	private SlayerTasks(SlayerMaster taskMaster, int npcId, String npcLocation, int XP, Position taskPosition) {
		this.taskMaster = taskMaster;
		this.npcId = npcId;
		this.npcLocation = npcLocation;
		this.XP = XP;
		this.taskPosition = taskPosition;
	}

	private SlayerMaster taskMaster;
	private int npcId;
	private String npcLocation;
	private int XP;
	private Position taskPosition;

	public SlayerMaster getTaskMaster() {
		return this.taskMaster;
	}

	public int getNpcId() {
		return this.npcId;
	}

	public String getNpcLocation() {
		return this.npcLocation;
	}

	public int getXP() {
		return this.XP;
	}

	public Position getTaskPosition() {
		return this.taskPosition;
	}

	public static SlayerTasks forId(int id) {
		for (SlayerTasks tasks : SlayerTasks.values()) {
			if (tasks.ordinal() == id) {
				return tasks;
			}
		}
		return null;
	}

	public static int[] getNewTaskData(SlayerMaster master) {
		int slayerTaskId = 1, slayerTaskAmount = 20;
		int easyTasks = 0, mediumTasks = 0, hardTasks = 0, eliteTasks = 0;

		/*
		 * Calculating amount of tasks
		 */
		for (SlayerTasks task : SlayerTasks.values()) {
			if (task.getTaskMaster() == SlayerMaster.VANNAKA)
				easyTasks++;
			else if (task.getTaskMaster() == SlayerMaster.DURADEL)
				mediumTasks++;
			else if (task.getTaskMaster() == SlayerMaster.KURADEL)
				hardTasks++;
			else if (task.getTaskMaster() == SlayerMaster.SUMONA)
				eliteTasks++;
		}

		if (master == SlayerMaster.VANNAKA) {
			slayerTaskId = 1 + Misc.getRandom(easyTasks);
			if (slayerTaskId > easyTasks)
				slayerTaskId = easyTasks;
			slayerTaskAmount = 40 + Misc.getRandom(15);
		} else if (master == SlayerMaster.DURADEL) {
			slayerTaskId = easyTasks - 1 + Misc.getRandom(mediumTasks);
			slayerTaskAmount = 28 + Misc.getRandom(13);
		} else if (master == SlayerMaster.KURADEL) {
			slayerTaskId = 1 + easyTasks + mediumTasks + Misc.getRandom(hardTasks - 1);
			slayerTaskAmount = 37 + Misc.getRandom(20);
		} else if (master == SlayerMaster.SUMONA) {
			slayerTaskId = 1 + easyTasks + mediumTasks + hardTasks + Misc.getRandom(eliteTasks - 1);
			slayerTaskAmount = 7 + Misc.getRandom(10);
		}
		return new int[] { slayerTaskId, slayerTaskAmount };
	}
	
	@Override
	public String toString() {
		return Misc.ucFirst(name().toLowerCase().replaceAll("_", " "));
	}
}
