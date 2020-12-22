package com.arlania.world.content.itemopening;

import com.arlania.world.entity.impl.player.Player;

public class DonationBox {
	
	/*
	 * Handles the opening of the donation box
	 */
	public static void open (Player player) {
		if (player.getInventory().getFreeSlots() < 3) {
			player.getPacketSender().sendMessage("You need at least 3 inventory spaces");
			return;
		}
		//StarterBox
		player.getInventory().delete(6183, 1);
		player.giveItem(20095, 1);
		player.giveItem(20096, 1);
		player.giveItem(20097, 1);
		player.giveItem(20100, 1);
		player.giveItem(21063, 1);
		player.giveItem(896, 1);
		player.giveItem(21061, 1);
		player.giveItem(11978, 1);
		player.getPacketSender().sendMessage("Congratulations on your reward!");

	}

}
