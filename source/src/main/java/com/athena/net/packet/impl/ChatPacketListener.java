package com.athena.net.packet.impl;

import com.athena.model.Flag;
import com.athena.model.ChatMessage.Message;
import com.athena.net.packet.Packet;
import com.athena.net.packet.PacketListener;
import com.athena.util.Misc;
import com.athena.world.content.PlayerPunishment;
import com.athena.world.entity.impl.player.Player;

/**
 * This packet listener manages the spoken text by a player.
 *
 * @author relex lawl
 */

public class ChatPacketListener implements PacketListener {

	@Override
	public void handleMessage(Player player, Packet packet) {
		int effects = packet.readUnsignedByteS();
		int color = packet.readUnsignedByteS();
		int size = packet.getSize();
		byte[] text = packet.readReversedBytesA(size);
		if(PlayerPunishment.muted(player.getUsername()) || PlayerPunishment.IPMuted(player.getHostAddress())) {
			player.getPacketSender().sendMessage("You are muted and cannot chat.");
			return;
		}
		String str = Misc.textUnpack(text, size).toLowerCase().replaceAll(";", ".");


		if (str == null || str.isEmpty() || player.newPlayer())
			return;

		player.getChatMessages().set(new Message(color, effects, text));
		player.getUpdateFlag().flag(Flag.CHAT);
	}

}
