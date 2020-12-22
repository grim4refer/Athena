package com.arlania.world.content.itemopening;

import com.arlania.world.entity.impl.player.Player;

public class DoflamingoSetBox {
	
	/*
	 * Handles the opening of the donation box
	 */
	public static void open (Player player) {
		if (player.getInventory().getFreeSlots() < 3) {
			player.getPacketSender().sendMessage("You need at least 3 inventory spaces");
			return;
		}
		//DoflamingoSetBox
		player.getInventory().delete(20986, 1);
		player.giveItem(20980, 1);
		player.giveItem(20981, 1);
		player.giveItem(20982, 1);
		player.giveItem(20983, 1);
		player.giveItem(20984, 1);
		player.getPacketSender().sendMessage("@mag@C@yel@o@red@n@whi@g@gre@r@cya@a@dre@t@bla@u@whi@l@gre@a@blu@t@yel@i@red@o@mag@n@yel@s @mag@on your Price!");

	}

}
