package com.athena.engine.task.impl;

import com.athena.engine.task.Task;
import com.athena.model.Locations;
import com.athena.util.RandomUtility;
import com.athena.world.World;
import com.athena.world.content.minigames.impl.PestControl;

/**
 * @author Gabriel Hannason
 */
public class ServerTimeUpdateTask extends Task {

	public ServerTimeUpdateTask() {
		super(40);
	}

	private int tick = 0;

	@Override
	protected void execute() {
		World.updateServerTime();
		
		if(tick >= 6 && (Locations.PLAYERS_IN_WILD >= 5 || Locations.PLAYERS_IN_DUEL_ARENA >= 5 || PestControl.TOTAL_PLAYERS >= 5)) {
			if(Locations.PLAYERS_IN_WILD > Locations.PLAYERS_IN_DUEL_ARENA && Locations.PLAYERS_IN_WILD > PestControl.TOTAL_PLAYERS || RandomUtility.getRandom(3) == 1 && Locations.PLAYERS_IN_WILD >= 2) {
				World.sendMessage("<img=10> @blu@[Hotspot]@bla@ There are currently "+Locations.PLAYERS_IN_WILD+" players roaming the Wilderness!");
			} else if(Locations.PLAYERS_IN_DUEL_ARENA > Locations.PLAYERS_IN_WILD && Locations.PLAYERS_IN_DUEL_ARENA > PestControl.TOTAL_PLAYERS) {
				World.sendMessage("<img=10> @blu@[Hotspot]@bla@ There are currently "+Locations.PLAYERS_IN_DUEL_ARENA+" players at the Duel Arena!");
			} else if(PestControl.TOTAL_PLAYERS > Locations.PLAYERS_IN_WILD && PestControl.TOTAL_PLAYERS > Locations.PLAYERS_IN_DUEL_ARENA) {
				World.sendMessage("<img=10> @blu@[Hotspot]@bla@ There are currently "+PestControl.TOTAL_PLAYERS+" players at Pest Control!");
			}
			tick = 0;
		}

		tick++;
	}
}