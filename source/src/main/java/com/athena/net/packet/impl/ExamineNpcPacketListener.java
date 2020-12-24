package com.athena.net.packet.impl;

import com.athena.model.PlayerRights;
import com.athena.model.definitions.NpcDefinition;
import com.athena.net.packet.Packet;
import com.athena.net.packet.PacketListener;
import com.athena.world.content.MonsterDrops;
import com.athena.world.entity.impl.player.Player;

public class ExamineNpcPacketListener implements PacketListener {

	@Override
	public void handleMessage(Player player, Packet packet) {
		int npc = packet.readShort();
		if(npc <= 0) {
			return;
		}
		NpcDefinition npcDef = NpcDefinition.forId(npc);
		if(npcDef != null) {
			player.getPacketSender().sendMessage(npcDef.getExamine());
			MonsterDrops.sendNpcDrop(player, npcDef.getId(), npcDef.getName().toLowerCase());
			
			if(player.getRights() == PlayerRights.OWNER) {
				player.getPacketSender().sendMessage("This NPC's ID is: " + npcDef.getId());
			}
			
	}
   }

}

