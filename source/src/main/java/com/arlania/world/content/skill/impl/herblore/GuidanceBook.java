package com.arlania.world.content.skill.impl.herblore;

import com.arlania.model.Animation;
import com.arlania.model.Position;
import com.arlania.world.entity.impl.player.Player;

/**
 * Handles the Ingridient's book
 * @author Gabriel Hannason
 *
 */
public class GuidanceBook {

	public static void readBook1(Player player, int pageIndex, boolean interfaceAllowed) {
		if(player.getInterfaceId() != -1 && !interfaceAllowed) {
			player.getPacketSender().sendMessage("Please close the interface you have open before opening a new one.");
			return;
		}
		if(pageIndex < 0)
			pageIndex = 0;
		if(pageIndex > 10)
			pageIndex = 12;
		player.getMovementQueue().reset();
		player.getPacketSender().sendString(14165, "- "+pageIndex+" - ");
		player.getPacketSender().sendString(14166, "- "+(pageIndex+1)+" - ");
		player.moveTo(new Position(2505, 2507, 1));
		player.getPacketSender().sendMessage("@or2@WELCOME TO TRAINING!");
		player.getPacketSender().sendMessage("@or2@COLLECT BEGINNERS GEAR!");
		player.getPacketSender().sendMessage("@or2@YOU CAN USE THE WORLD MAP FOR MORE TELEPORTS.");
		player.getPacketSender().sendMessage("@or2@GOOD LUCK ON YOUR JOURNEY.");
		player.setCurrentBookPage1(pageIndex);
		};
}
