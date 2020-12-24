package com.athena.model.input.impl;

import com.athena.model.input.Input;
import com.athena.world.entity.impl.player.Player;

public class PosInput extends Input {

	@Override
	public void handleSyntax(Player player, String syntax) {
		player.getPacketSender().sendClientRightClickRemoval();
		player.getPlayerOwnedShopManager().updateFilter(syntax);
		
	}
}
