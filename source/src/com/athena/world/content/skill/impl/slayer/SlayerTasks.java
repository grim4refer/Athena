package com.athena.world.content.skill.impl.slayer;

import com.athena.model.Position;
import com.athena.util.Misc;

/**
 * @author Gabriel Hannason 
 */

public enum SlayerTasks {

	NO_TASK(null, -1, null, -1, null),

	/**
	 * Easy tasks
	 */
	ZIRCONIS(SlayerMaster.VANNAKA, 1265, "Yoda's can be found in the Monster Teleport.", 2100, new Position(2305, 4589, 0)),
	SKAIDRUM(SlayerMaster.VANNAKA, 1677, "Godzilla can be found in the Monster teleport.", 6500, new Position(3560, 9948)),

	/**
	 * Medium tasks
	 */
	WEISSLOGIA(SlayerMaster.DURADEL, 1880, "Deadly Assasin can be found in the Monster teleport.", 6500, new Position(3172, 2976)),
	ARTILLERY(SlayerMaster.DURADEL, 2437, "LiquidX Artillery can be found in the Monster teleport.", 6500, new Position(1824, 5163,2)),


	BANDOS_AVATAR(SlayerMaster.SUMONA, 4540, "Bandos Avatar can be found in the boss teleport.", 14000, new Position(2867, 9946)),
	ABBADON(SlayerMaster.SUMONA, 6303, "abbadon can be found in the Monster :Teleport", 14000, new Position(2576, 4849)),
	INFERAL_GORUDON(SlayerMaster.SUMONA, 1234, "infernal groudon can be found in the Monster :Teleport", 14000, new Position(1240, 1227)),

	/**
	 * Hard tasks
	 */
	DONKEY_KONG(SlayerMaster.KURADEL, 1459, "Donkey Kong can be found in the Monster Teleport", 8500, new Position(2795, 2775)),
	OBLIVION(SlayerMaster.KURADEL, 6102, "Oblivion can be found in the Monster Teleport", 8500, new Position(1747,5323));

	/**
	 * Elite
	 */
//	NEX(SlayerMaster.SUMONA, 13447, "Nex can be found in the Godwars Dungeon.", 100000, new Position(2903, 5203)),

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
