package com.athena.model.input.impl;

import com.athena.model.input.EnterAmount;
import com.athena.world.content.skill.impl.fletching.Fletching;
import com.athena.world.entity.impl.player.Player;

public class EnterAmountOfBowsToString extends EnterAmount {

	@Override
	public void handleAmount(Player player, int amount) {
		Fletching.stringBow(player, amount);
	}

}
