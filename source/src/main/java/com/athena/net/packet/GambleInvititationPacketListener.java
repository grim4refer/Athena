package com.athena.net.packet;

import com.athena.engine.task.impl.WalkToTask;
import com.athena.model.Locations;
import com.athena.model.Locations.Location;
import com.athena.world.World;
import com.athena.world.entity.impl.player.Player;

//import com.athena.world.content.gamblinginterface.GamblingInterface;

public class GambleInvititationPacketListener implements PacketListener {

	public static final int GAMBLE_OPCODE = 191;
	public static final int CHATBOX_GAMBLE_OPCODE = 193;

	@Override
	public void handleMessage(Player player, Packet packet) {
		if (player.getConstitution() <= 0)
			return;
		if (player.isTeleporting())
			return;
		player.getSkillManager().stopSkilling();
		if (player.getLocation() == Location.FIGHT_PITS) {
			player.getPacketSender().sendMessage("You cannot trade other players here.");
			return;
		}
		int index = packet.getOpcode() == GAMBLE_OPCODE ? (packet.readShort() & 0xFF) : packet.readLEShort();
		System.out.println("Index from client: " + index);
		if (index < 0 || index > World.getPlayers().capacity())
			return;
		Player target = World.getPlayers().get(index);

		//System.out.println("Index: " + index);
		//System.out.println("Name: " + target.getUsername());
		if (target == null || !Locations.goodDistance(player.getPosition(), target.getPosition(), 13))
			return;
		player.setWalkToTask(
				new WalkToTask(player, target.getPosition(), target.getSize(), () -> {
					if (target.getIndex() != player.getIndex()) {
						if ((player.getTotalPlayTime() >= 1)) {
							player.getGambling().requestGamble(target);
						} else {
							player.sendMessage("@red@You need a gamblers pass and 5hours of playtime to gamble");
						}
					}
				}));
	}
}
