package com.athena.world.content.droppreview;

import com.athena.engine.task.Task;
import com.athena.engine.task.TaskManager;
import com.athena.world.entity.impl.player.Player;

public class CORP {
	
public static int config;
public static int item;
public static int index = 0;

public static final int[] CORP = {13748, 13746, 13750, 13752, 12001, 13754};

	
/*
 * Get rare drop(s) for that boss location 
 */
public static void getDrop(Player player) {
	
}

public static void startPreview(Player player) {
	TaskManager.submit(new Task(1, player, false) {
		@Override
		public void execute() {
			sendInterface(player);
			this.stop();
		}
	});
}
/*
 * Sends the next item from array
 */
public static void nextItem(Player player) {
	if (index == CORP.length) {
		index = 0;
		player.getPacketSender().sendItemOnInterface(47900, CORP[index++], 1);
	} else {
		player.getPacketSender().sendItemOnInterface(47900, CORP[index++], 1);
	}
	
}

/*
 * Sends the previous item from the array
 */
public static void previousItem(Player player) {
	if (index == -1) {
		index = CORP.length-1;
		player.getPacketSender().sendItemOnInterface(47900, CORP[index--], 1);
	} else {
		player.getPacketSender().sendItemOnInterface(47900, CORP[index--], 1);
	}
}

/*
 * Sends the interface with the item on interface method
 */
public static void sendInterface(Player player) {
	TaskManager.submit(new Task(2, player, false) {
		@Override
		public void execute() {
			player.sendParallellInterfaceVisibility(47989, true);
			player.getPacketSender().sendString(47904, "Rare Drops:");
			player.getPacketSender().sendItemOnInterface(47900, 5022, 1);
			this.stop();
		}
	});
	
}

/*
 * Handles the closing of the walkable interface
 */
	
public static void closeInterface(Player player) {
	player.sendParallellInterfaceVisibility(47989, false);
	player.getPacketSender().sendMessage("Good luck with your drops!");
	
}

}