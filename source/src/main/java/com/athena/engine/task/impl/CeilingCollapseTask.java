package com.athena.engine.task.impl;


import com.athena.engine.task.Task;
import com.athena.model.CombatIcon;
import com.athena.model.Graphic;
import com.athena.model.Hit;
import com.athena.model.Hitmask;
import com.athena.model.Locations.Location;
import com.athena.util.RandomUtility;
import com.athena.world.entity.impl.player.Player;

/**
 * Barrows
 * @author Gabriel Hannason
 */
public class CeilingCollapseTask extends Task {

	public CeilingCollapseTask(Player player) {
		super(9, player, false);
		this.player = player;
	}

	private Player player;

	@Override
	public void execute() {
		if(player == null || !player.isRegistered() || player.getLocation() != Location.BARROWS || player.getLocation() == Location.BARROWS && player.getPosition().getY() < 8000) {
			assert player != null;
			player.getPacketSender().sendCameraNeutrality();
			stop();
			return;
		}
		player.performGraphic(new Graphic(60));
		player.getPacketSender().sendMessage("Some rocks fall from the ceiling and hit you.");
		player.forceChat("Ouch!");
		player.dealDamage(new Hit(30 + RandomUtility.getRandom(20), Hitmask.RED, CombatIcon.BLOCK));
	}
}
