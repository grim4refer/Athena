package com.athena.world.content.combat.strategy.impl;

import com.athena.engine.task.Task;
import com.athena.engine.task.TaskManager;
import com.athena.model.Animation;
import com.athena.model.Graphic;
import com.athena.model.Locations;
import com.athena.model.Projectile;
import com.athena.util.Misc;
import com.athena.world.content.combat.CombatContainer;
import com.athena.world.content.combat.CombatType;
import com.athena.world.content.combat.strategy.CombatStrategy;
import com.athena.world.entity.impl.Character;
import com.athena.world.entity.impl.npc.NPC;
import com.athena.world.entity.impl.player.Player;

public class Growler implements CombatStrategy {

	private static final Animation anim = new Animation(7019);
	private static final Graphic graphic = new Graphic(384);
	
	@Override
	public boolean canAttack(Character entity, Character victim) {
		return victim.isPlayer() && ((Player)victim).getMinigameAttributes().getGodwarsDungeonAttributes().hasEnteredRoom();
	}

	@Override
	public CombatContainer attack(Character entity, Character victim) {
		return null;
	}

	@Override
	public boolean customContainerAttack(Character entity, Character victim) {
		NPC growler = (NPC)entity;
		if(growler.isChargingAttack() || victim.getConstitution() <= 0) {
			return true;
		}
		if(Locations.goodDistance(growler.getPosition().copy(), victim.getPosition().copy(), 1) && Misc.getRandom(5) <= 3) {
			growler.performAnimation(new Animation(growler.getDefinition().getAttackAnimation()));
			growler.getCombatBuilder().setContainer(new CombatContainer(growler, victim, 1, 1, CombatType.MELEE, true));
		} else {
			growler.setChargingAttack(true);
			growler.performAnimation(anim);
			growler.getCombatBuilder().setContainer(new CombatContainer(growler, victim, 1, 3, CombatType.MAGIC, true));
			TaskManager.submit(new Task(1, growler, false) {
				int tick = 0;
				@Override
				public void execute() {
					if(tick == 1) {
						new Projectile(growler, victim, graphic.getId(), 44, 3, 43, 43, 0).sendProjectile();
						growler.setChargingAttack(false);
						stop();
					}
					tick++;
				}
			});
		}
		return true;
	}


	@Override
	public int attackDelay(Character entity) {
		return entity.getAttackSpeed();
	}

	@Override
	public int attackDistance(Character entity) {
		return 8;
	}
	
	@Override
	public CombatType getCombatType() {
		return CombatType.MIXED;
	}
}
