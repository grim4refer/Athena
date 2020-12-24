package com.athena.model.input.impl;

import com.athena.model.Item;
import com.athena.model.container.impl.PriceChecker;
import com.athena.model.input.EnterAmount;
import com.athena.world.entity.impl.player.Player;

public class EnterAmountToRemoveFromPriceCheck extends EnterAmount {


	public EnterAmountToRemoveFromPriceCheck(int item, int slot) {
		super(item, slot);
	}

	@Override
	public void handleAmount(Player player, int amount) {
		if(!player.getPriceChecker().isOpen() || player.getInterfaceId() != PriceChecker.INTERFACE_ID)
			return;
		if(!player.getPriceChecker().contains(getItem()))
			return;
		int invAmount = player.getPriceChecker().getAmount(getItem());
		if(amount > invAmount) 
			amount = invAmount;
		if(amount <= 0)
			return;
		player.getPriceChecker().switchItem(player.getInventory(), new Item(getItem(), amount), player.getPriceChecker().getSlot(getItem()), false, true);
	}
}
