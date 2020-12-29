package com.athena.net.packet;

import com.athena.world.entity.impl.player.Player;

public class AddGambleItemPacketListener implements PacketListener {

	@Override
	public void handleMessage(Player player, Packet packet) {
		
		int itemId = packet.readShort();
		
		System.out.println("Item id: " + itemId);

	}

}
