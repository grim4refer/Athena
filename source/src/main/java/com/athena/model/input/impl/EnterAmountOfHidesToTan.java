package com.athena.model.input.impl;

import com.athena.model.input.EnterAmount;
import com.athena.world.content.skill.impl.crafting.Tanning;
import com.athena.world.entity.impl.player.Player;

public class EnterAmountOfHidesToTan extends EnterAmount {

	private int button;
	public EnterAmountOfHidesToTan(int button) {
		this.button = button;
	}
	
	@Override
	public void handleAmount(Player player, int amount) {
		Tanning.tanHide(player, button, amount);
	}

}
