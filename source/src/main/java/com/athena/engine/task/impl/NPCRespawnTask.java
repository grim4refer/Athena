package com.athena.engine.task.impl;

import com.athena.engine.task.Task;
import com.athena.model.Position;
import com.athena.util.RandomUtility;
import com.athena.world.World;
import com.athena.world.content.skill.impl.hunter.Hunter;
import com.athena.world.entity.impl.npc.NPC;

public class NPCRespawnTask extends Task {

	public NPCRespawnTask(NPC npc, int respawn) {
		super(respawn);
		this.npc = npc;
	}

	final NPC npc;

	@Override
	public void execute() {
		NPC npc_ = new NPC(npc.getId(), npc.getDefaultPosition());
		npc_.getMovementCoordinator().setCoordinator(npc.getMovementCoordinator().getCoordinator());
		
		if(npc_.getId() == 8022 || npc_.getId() == 8028) { //Desospan, respawn at random locations
			npc_.moveTo(new Position(2595 + RandomUtility.getRandom(12), 4772 + RandomUtility.getRandom(8)));
		} else if(npc_.getId() > 5070 && npc_.getId() < 5081) {
			Hunter.HUNTER_NPC_LIST.add(npc_);
		}
	
		World.register(npc_);
		stop();
	}

}
