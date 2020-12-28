package com.athena.world.content;

import com.athena.util.Misc;
import com.athena.world.entity.impl.player.Player;

/**
 *
 * @author Joker
 */

public class StarterTasks {

	public enum StarterTaskData {

		READ_THE_GUIDE_BOOK("Read The Guide Book", 53207, "", null, "Read the book", "That you received",
				"At the start", ""),
		DEFEAT_10_CARS("Destroy 10 Cars", 53208, "10 Cars", new int[] { 0, 10 }, "Destroy 10 Cars",
				"Drops Starter Items!", "", ""),
		DEFEAT_10_CRASH("Defeat 10 Crash", 53209, "10 Crash", new int[] { 1, 10 }, "Defeat 10 Athena Artillery", "Drops guns!", "", ""),
		DEFEAT_10_TERROR_DOGS("Defeat 10 Terror Dogs", 53210, "10 Terror Dogs", new int[] { 2, 10 }, "Defeat 10 Terror Dogs",
				"Drops Deadly Dragonslayer pieces!", "", ""),
		DEFEAT_10_PINGUINS("Defeat 10 Pinguins", 53211, "10 Pinguins", new int[] { 3, 10 }, "Defeat 10 Pinguins", "Drops Dark Predator pieces!",
				"", ""),
		DEFEAT_10_AURELIA("Defeat 10 Aurelias", 53212, "10 Aurelias", new int[] { 4, 10 }, "Defeat 10 Aurelias", "Drops Oblivion pieces!", "", ""),
		DEFEAT_10_NATURE_SWORDS("Defeat 10 Nature Swords", 53213, "10 Nature Swords", new int[] { 5,10 }, "Defeat 10 Nature Swords", "Drops Nature Torva Pieces!", "", ""),
		DEFEAT_10_BELERIONS("Defeat 10 Belerions", 53214, "10 Belerions!", new int[] { 6, 10 },
				"Defeat 10 Belerions", "Drops Dark Depature pieces!", "", ""),
		DEFEAT_10_LIVYATHANN("Defeat 10 Livyathanns", 53215, "10 Livyathanns", new int[] { 7, 10 },
				"Defeat 10 Livyathanns", "Drops Trio pieces!", "", ""),
		DEFEAT_10_DIRE_WOLFS("Defeat 10 Dire Wolfs", 53216, "10 Dire Wolfs", new int[] { 8, 10 }, "Defeat 10 Dire Wolfs", "Drops Boss fragments!", "", ""),
		DEFEAT_10_ABBADONS("Defeat 10 Abbadons", 53217, "10 Abaddons", new int[] { 9, 10 }, "Drops Abbadon Mystery Boxes!", "", "", ""),
		DEFEAT_10_INFERNAL_GROUDONS("Defeat 10 Groudons", 53218, "10 Infernal Groudons", new int[] { 10, 10 }, "Defeat 10 Infernal Groudons", "Drops Infernal Groudon Mystery Boxes!", "", ""),
		DEFEAT_10_BAPHOMET("Defeat 10 Baphomets", 53219, "10 Baphomets", new int[] { 11, 10 }, "Defeat 10 Baphomets", "Drops Baphomet Mystery Boxes!", "", "");
		
		StarterTaskData(String taskName, int interFaceId, String requirements, int[] progressData,
				String taskDescription, String taskDescription1, String taskDescription2, String taskDescription3) {
			this.taskName = taskName;
			this.interFaceId = interFaceId;
			this.requirements = requirements;
			this.progressData = progressData;
			this.taskDescription = taskDescription;
			this.taskDescription1 = taskDescription1;
			this.taskDescription2 = taskDescription2;
			this.taskDescription3 = taskDescription3;
		}

		private String taskName;
		private int interFaceId;
		private String requirements;
		private int[] progressData;
		private String taskDescription;
		private String taskDescription1;
		private String taskDescription2;
		private String taskDescription3;
	}

	static int[] rewards = { 6183 };

	public static boolean claimReward(Player player) {
		for (StarterTaskData tasks : StarterTaskData.values()) {
			if (!player.getStarterTaskAttributes().completed[tasks.ordinal()]) {
				player.sendMessage("Complete all the tasks first before claiming the reward.");
				return false;
			}
		}
		if (!player.starterClaimed) {
			for (int i = 0; i < rewards.length; i++) {
				player.getInventory().add(rewards[i], 1);
			}
		}
		player.sendMessage("Enjoy your reward");
		player.starterClaimed = true;
		return true;
	}
	

	public static boolean handleButton(Player player, int buttonID) {
		if (!(buttonID >= -12329 && buttonID <= -12317)) {
			return false;
		}
		int index = -1;

		if (buttonID >= -12329) {
			index = 12329 + buttonID;
		}

		if (index >= 0 && index < StarterTaskData.values().length) {
			StarterTaskData tasks = StarterTaskData.values()[index];
			openInterface(player, tasks);
		}
		return true;
	}

	public static void updateInterface(Player player) {

		for (StarterTaskData tasks : StarterTaskData.values()) {
			boolean completed = player.getStarterTaskAttributes().getCompletion()[tasks.ordinal()];
			boolean progress = tasks.progressData != null
					&& player.getStarterTaskAttributes().getProgress()[tasks.progressData[0]] > 0;
			player.getPacketSender().sendString(tasks.interFaceId,
					(completed ? "@gre@" : progress ? "@yel@" : "@red@") + tasks.taskName);
		}
	}

	public static void doProgress(Player player, StarterTaskData tasks) {
		doProgress(player, tasks, 1);
	}

	public static void doProgress(Player player, StarterTaskData taskData, int amount) {
		if (player.getStarterTaskAttributes().getCompletion()[taskData.ordinal()])
			return;
		if (taskData.progressData != null) {
			int progressIndex = taskData.progressData[0];
			int amountNeeded = taskData.progressData[1];
			int previousDone = player.getStarterTaskAttributes().getProgress()[progressIndex];
			if ((previousDone + amount) < amountNeeded) {
				player.getStarterTaskAttributes().getProgress()[progressIndex] = previousDone + amount;
				if (previousDone == 0)
					player.getPacketSender().sendString(taskData.interFaceId, "@yel@" + taskData.taskName);
			} else {
				finishTask(player, taskData);
			}
		}
	}

	public static void finishTask(Player player, StarterTaskData tasks) {
		if (player.getStarterTaskAttributes().getCompletion()[tasks.ordinal()])
			return;
		player.getStarterTaskAttributes().getCompletion()[tasks.ordinal()] = true;
	}

	public static void openInterface(Player player, StarterTaskData task) {
		player.getPacketSender().sendString(53220, "" + task.taskDescription);
		player.getPacketSender().sendString(53221, "" + task.taskDescription1);
		player.getPacketSender().sendString(53222, "" + task.taskDescription2);
		player.getPacketSender().sendString(53223, "" + task.taskDescription3);

		if (!task.requirements.equalsIgnoreCase("")) {
			player.getPacketSender().sendString(53226, "Task Requirements:@yel@ " + task.requirements);
		} else {
			player.getPacketSender().sendString(53226, "Task Requirements: @yel@None");
		}

		if (player.getStarterTaskAttributes().getCompletion()[task.ordinal()]) {
			player.getPacketSender().sendString(53225, "Progress: @gre@100% (1/1)");
		} else if (task.progressData == null) {
			player.getPacketSender().sendString(53225, "Progress: @gre@0% (0/0)");

		} else {
			int currentProgress = player.getStarterTaskAttributes().getProgress()[task.progressData[0]];
			int totalProgress = task.progressData[1];
			boolean completed = player.getStarterTaskAttributes().getCompletion()[task.ordinal()];
			double percent = ((double) currentProgress * 100) / (double) totalProgress;
			if (currentProgress == 0) {
				player.getPacketSender().sendString(53225,
						"Progress: @gre@0 (" + Misc.insertCommasToNumber("" + currentProgress) + "/"
								+ Misc.insertCommasToNumber("" + totalProgress) + ")");
			} else if (currentProgress != totalProgress) {
				player.getPacketSender().sendString(53225,
						"Progress: @yel@" + percent + "% (" + Misc.insertCommasToNumber("" + currentProgress) + "/"
								+ Misc.insertCommasToNumber("" + totalProgress) + ")");
			} else if (completed) {
				player.getPacketSender().sendString(53225,
						"Progress: @gre@" + percent + " (" + Misc.insertCommasToNumber("" + currentProgress) + "/"
								+ Misc.insertCommasToNumber("" + totalProgress) + ")");
			}
		}

		player.getPacketSender().sendInterface(53200);
	}

	public static class StarterTaskAttributes {

		public StarterTaskAttributes() {

		}

		/** Tasks **/
		private boolean[] completed = new boolean[StarterTaskData.values().length];
		private int[] progress = new int[55];

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

	}
}
