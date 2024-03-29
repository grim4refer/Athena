package com.athena.model.input.impl;

import com.athena.model.input.EnterAmount;
import com.athena.world.content.skill.impl.summoning.PouchMaking;
import com.athena.world.entity.impl.player.Player;

public class EnterAmountToInfuse extends EnterAmount {

	@Override
	public void handleAmount(Player player, int amount) {
		if(player.getInterfaceId() != 63471) {
			player.getPacketSender().sendInterfaceRemoval();
			return;
		}
		PouchMaking.infusePouches(player, amount);
	}

}
