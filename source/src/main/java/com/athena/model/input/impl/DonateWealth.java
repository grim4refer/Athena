package com.athena.model.input.impl;

import com.athena.model.input.EnterAmount;
import com.athena.world.content.WellOfWealth;
import com.athena.world.entity.impl.player.Player;

public class DonateWealth extends EnterAmount {

	@Override
	public void handleAmount(Player player, int amount) {
		WellOfWealth.donate(player, amount);
	}

}
