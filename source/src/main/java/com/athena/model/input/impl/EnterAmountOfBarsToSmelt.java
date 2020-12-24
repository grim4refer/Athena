package com.athena.model.input.impl;

import com.athena.model.input.EnterAmount;
import com.athena.world.content.skill.impl.smithing.Smelting;
import com.athena.world.content.skill.impl.smithing.SmithingData;
import com.athena.world.entity.impl.player.Player;

public class EnterAmountOfBarsToSmelt extends EnterAmount {

	public EnterAmountOfBarsToSmelt(int bar) {
		this.bar = bar;
	}
	
	@Override
	public void handleAmount(Player player, int amount) {
		for(int barId : SmithingData.SMELT_BARS) {
			if(barId == bar) {
				Smelting.smeltBar(player, barId, amount);
				break;
			}
		}
	}
	
	private int bar;
	
	public int getBar() {
		return bar;
	}
	
}
