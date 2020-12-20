package com.athena.model.input.impl;

import com.athena.model.input.EnterAmount;
import com.athena.world.content.grandexchange.GrandExchange;
import com.athena.world.entity.impl.player.Player;

public class EnterGeQuantity extends EnterAmount {

	@Override
	public void handleAmount(Player player, int amount) {
		GrandExchange.setQuantity(player, amount);
	}

}
