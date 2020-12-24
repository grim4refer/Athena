package com.athena.net.packet.impl;

import com.athena.model.PlayerRelations.PrivateChatStatus;
import com.athena.net.packet.Packet;
import com.athena.net.packet.PacketListener;
import com.athena.world.entity.impl.player.Player;

public class ChangeRelationStatusPacketListener implements PacketListener {

	@Override
	public void handleMessage(Player player, Packet packet) {
		int actionId = packet.readInt();
		player.getRelations().setStatus(PrivateChatStatus.forActionId(actionId), true);
	}

}
