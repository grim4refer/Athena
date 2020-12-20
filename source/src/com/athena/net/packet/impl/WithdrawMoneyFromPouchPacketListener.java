package com.athena.net.packet.impl;

import com.athena.net.packet.Packet;
import com.athena.net.packet.PacketListener;
import com.athena.world.content.MoneyPouch;
import com.athena.world.entity.impl.player.Player;

public class WithdrawMoneyFromPouchPacketListener implements PacketListener {

	@Override
	public void handleMessage(Player player, Packet packet) {
		int amount = packet.readInt();
		if(player.getTrading().inTrade() || player.getDueling().inDuelScreen) {
			player.getPacketSender().sendMessage("You cannot withdraw money at the moment.");
		} else {
			MoneyPouch.withdrawMoney(player, amount);
		}
	}

}
