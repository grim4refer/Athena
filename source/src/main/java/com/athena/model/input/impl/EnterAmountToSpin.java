package com.athena.model.input.impl;

import com.athena.model.input.EnterAmount;
import com.athena.world.content.skill.impl.crafting.Flax;
import com.athena.world.entity.impl.player.Player;

public class EnterAmountToSpin extends EnterAmount {

	@Override
	public void handleAmount(Player player, int amount) {
		Flax.spinFlax(player, amount);
	}

}
