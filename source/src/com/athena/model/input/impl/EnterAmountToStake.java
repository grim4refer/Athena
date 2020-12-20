package com.athena.model.input.impl;

import com.athena.model.input.EnterAmount;
import com.athena.world.content.minigames.impl.Dueling;
import com.athena.world.entity.impl.player.Player;

public class EnterAmountToStake extends EnterAmount {

	public EnterAmountToStake(int item, int slot) {
		super(item, slot);
	}
	
	@Override
	public void handleAmount(Player player, int amount) {
		if((Dueling.checkDuel(player, 1) || Dueling.checkDuel(player, 2)) && getItem() > 0 && getSlot() >= 0 && getSlot() < 28)
			player.getDueling().stakeItem(getItem(), amount, getSlot());
		else
			player.getPacketSender().sendInterfaceRemoval();
	}

}
