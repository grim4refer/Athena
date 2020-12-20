package com.athena.model.input.impl;

import com.athena.model.input.EnterAmount;
import com.athena.world.content.skill.impl.crafting.Gems;
import com.athena.world.entity.impl.player.Player;

public class EnterAmountOfGemsToCut extends EnterAmount {

	@Override
	public void handleAmount(Player player, int amount) {
		if(player.getSelectedSkillingItem() > 0)
			Gems.cutGem(player, amount, player.getSelectedSkillingItem());
	}

}
