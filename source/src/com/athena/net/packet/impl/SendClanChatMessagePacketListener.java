package com.athena.net.packet.impl;

import com.athena.net.packet.Packet;
import com.athena.net.packet.PacketListener;
import com.athena.util.Misc;
import com.athena.world.content.PlayerPunishment;
import com.athena.world.content.clan.ClanChatManager;
import com.athena.world.content.dialogue.DialogueManager;
import com.athena.world.entity.impl.player.Player;

public class SendClanChatMessagePacketListener implements PacketListener {

	@Override
	public void handleMessage(Player player, Packet packet) {
		String clanMessage = Misc.readString(packet.getBuffer());
		if(clanMessage == null || clanMessage.length() < 1)
			return;
		if(PlayerPunishment.muted(player.getUsername()) || PlayerPunishment.IPMuted(player.getHostAddress())) {
			player.getPacketSender().sendMessage("You are muted and cannot chat.");
			return;
		}
		if(Misc.blockedWord(clanMessage)) {
			DialogueManager.sendStatement(player, "A word was blocked in your sentence. Please do not repeat it!");
			return;
		}
		ClanChatManager.sendMessage(player, clanMessage);
	}

}
