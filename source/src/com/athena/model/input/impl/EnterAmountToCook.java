package com.athena.model.input.impl;

import com.athena.model.input.EnterAmount;
import com.athena.world.content.skill.impl.cooking.Cooking;
import com.athena.world.entity.impl.player.Player;

public class EnterAmountToCook extends EnterAmount {

	@Override
	public void handleAmount(Player player, int amount) {
		if(player.getSelectedSkillingItem() > 0)
			Cooking.cook(player, player.getSelectedSkillingItem(), amount);
	}

}
