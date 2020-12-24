package com.athena.net.packet.impl;

import com.athena.model.Locations.Location;
import com.athena.net.packet.Packet;
import com.athena.net.packet.PacketListener;
import com.athena.world.World;
import com.athena.world.content.combat.magic.CombatSpell;
import com.athena.world.content.combat.magic.CombatSpells;
import com.athena.world.content.skill.impl.dungeoneering.UltimateIronmanHandler;
import com.athena.world.entity.impl.player.Player;

public class MagicOnPlayerPacketListener implements PacketListener {

	@Override
	public void handleMessage(Player player, Packet packet) {
		int playerIndex = packet.readShortA();
		if(playerIndex < 0 || playerIndex > World.getPlayers().capacity())
			return;
		int spellId = packet.readLEShort();
		if (spellId < 0) {
			return;
		}

		Player attacked = World.getPlayers().get(playerIndex);
		
		if (UltimateIronmanHandler.hasItemsStored(player) && player.getLocation() != Location.DUNGEONEERING) {
			player.getPacketSender().sendMessage("You must claim your stored items at Dungeoneering first.");
			return;
		}

		if (attacked == null || attacked.equals(player)) {
			player.getMovementQueue().reset();
			return;
		}

		CombatSpell spell = CombatSpells.getSpell(spellId);
		if(spell == null) {
			player.getMovementQueue().reset();
			return;
		}
		
		if(attacked.getConstitution() <= 0) {
			player.getMovementQueue().reset();
			return;
		}
		
		// Start combat!
		player.setPositionToFace(attacked.getPosition());
		player.getCombatBuilder().resetCooldown();
		player.setCastSpell(spell);
		player.getCombatBuilder().attack(attacked);
	}

}
