package com.athena.world.content;

import com.athena.util.RandomUtility;
import com.athena.world.entity.impl.player.Player;

public class ChristmasPresent {


	public static void openBox (Player player) {
		player.getInventory().delete(6542, 1);
		
		if (RandomUtility.getRandom(10) == 5) {
			/*
			 * Landing on 5 and receive reward
			 */
			player.getInventory().add(1050, 1);
			player.getPacketSender().sendMessage("Congratulations you received the santa hat");
			
		} else {
			/*
			 * Not landing on 5
			 */
			player.getInventory().add(5022, 10000 + RandomUtility.getRandom(500000));
			player.getPacketSender().sendMessage("Sorry, you didn't get the santa hat. Try again");
		}
	}



}
