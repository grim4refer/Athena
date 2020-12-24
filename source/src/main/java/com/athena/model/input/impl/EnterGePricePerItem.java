package com.athena.model.input.impl;

import com.athena.model.input.EnterAmount;
import com.athena.world.content.grandexchange.GrandExchange;
import com.athena.world.entity.impl.player.Player;

public class EnterGePricePerItem extends EnterAmount {

	@Override
	public void handleAmount(Player player, int amount) {
		GrandExchange.setPricePerItem(player, amount);
	}

}
