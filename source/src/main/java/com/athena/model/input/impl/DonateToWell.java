package com.athena.model.input.impl;

import com.athena.model.input.EnterAmount;
import com.athena.world.content.WellOfGoodwill;
import com.athena.world.entity.impl.player.Player;

public class DonateToWell extends EnterAmount {

	@Override
	public void handleAmount(Player player, int amount) {
		WellOfGoodwill.donate(player, amount);
	}

}
