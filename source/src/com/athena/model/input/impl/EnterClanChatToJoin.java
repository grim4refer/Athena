package com.athena.model.input.impl;

import com.athena.model.input.Input;
import com.athena.world.content.clan.ClanChatManager;
import com.athena.world.entity.impl.player.Player;

public class EnterClanChatToJoin extends Input {

	@Override
	public void handleSyntax(Player player, String syntax) {
		if(syntax.length() <= 1) {
			player.getPacketSender().sendMessage("Invalid syntax entered.");
			return;
		}
		ClanChatManager.join(player, syntax);
	}
}
