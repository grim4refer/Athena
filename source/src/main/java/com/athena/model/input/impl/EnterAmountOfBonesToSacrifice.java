package com.athena.model.input.impl;

import com.athena.model.input.EnterAmount;
import com.athena.world.content.skill.impl.prayer.BonesOnAltar;
import com.athena.world.entity.impl.player.Player;

public class EnterAmountOfBonesToSacrifice extends EnterAmount {

	@Override
	public void handleAmount(Player player, int amount) {
		BonesOnAltar.offerBones(player, amount);
	}

}
