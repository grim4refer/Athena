package com.athena.net.packet.impl;

import org.jboss.netty.buffer.ChannelBuffer;

import com.athena.net.packet.*;
import com.athena.util.Misc;
import com.athena.world.content.PlayerPunishment;
import com.athena.world.content.clan.ClanChatManager;
import com.athena.world.entity.impl.player.Player;

import java.util.Arrays;
import java.util.List;

/***
 *
 * @author Coif
 *
 * Creation Date: Aug 31, 2018 - 2:12:52 AM
 */
public class SendClanChatMessagePacketListener implements PacketListener {

	@Override
	public void handleMessage(Player player, Packet packet) {
		/** Get method for the channel buffer. **/
		ChannelBuffer opcode = packet.getBuffer();
		/** Gets requested bytes from the buffer client > server **/
		int size = opcode.readableBytes();
		/** Check to flood **/
		if (size < 1 || size > 127) {
			System.err.println("blocked packet from sending from clan chat. Requested size="+size);
			return;
		}

		if (player.newPlayer())
			return;

		String clanMessage = packet.readString();
		/** Checks for null, invalid messages **/
		if(clanMessage == null || clanMessage.length() < 1 || clanMessage.length() > 127) //old code
			return;
		if(PlayerPunishment.muted(player.getUsername()) || PlayerPunishment.IPMuted(player.getHostAddress())) {
			player.getPacketSender().sendMessage("You are muted and cannot chat.");
			return;
		}

		List<String> blockedMessages = Arrays.asList("<img=>", "<img=", "<img=100>", "<img=101>", "<img=102>", "<img=103>", "<img=104>", "<img=105>", "<img=106>", "<img=107>", "<img=108>", "<img=108>", "<img=110>", "<img=111>", "<img=110>", "<img=112>", "<img=113>", "<img=114>", "<img=115>", "<img=116>", "<img=117>", "<img=120>", "<img=120>", "<img=121>", "<img=122>", "<img=123>", "<img=124>", "<img=125>", "<img=126>", "<img=127>", "<img=128>", "<img=129>",  "<img=119>", "<img=118>", "<img");
		if(blockedMessages.contains(clanMessage)) {
			player.sendMessage("You cant type this into the clan chat.");
			System.err.println("User: " + player.getUsername() + " Tried to send this message: [ '" + clanMessage + " ']");
			return;
		} else {
			ClanChatManager.sendMessage(player, Misc.filterMessage(player, clanMessage));
		}
	}

}
