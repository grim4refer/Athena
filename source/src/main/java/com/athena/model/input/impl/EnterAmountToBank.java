package com.athena.model.input.impl;

import com.athena.model.Item;
import com.athena.model.container.impl.Bank;
import com.athena.model.input.EnterAmount;
import com.athena.world.entity.impl.player.Player;

public class EnterAmountToBank extends EnterAmount {

	public EnterAmountToBank(int item, int slot) {
		super(item, slot);
	}

	@Override
	public void handleAmount(Player player, int amount) {
		if(!player.isBanking())
			return;
		int item = player.getInventory().getItems()[getSlot()].getId();
		if(item != getItem())
			return;
		int invAmount = player.getInventory().getAmount(item);
		if(amount > invAmount) 
			amount = invAmount;
		if(amount <= 0)
			return;
		player.getInventory().switchItem(player.getBank(Bank.getTabForItem(player, item)), new Item(item, amount), getSlot(), false, true);
	}
}
