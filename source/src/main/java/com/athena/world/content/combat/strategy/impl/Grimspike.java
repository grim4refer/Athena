package com.athena.world.content.combat.strategy.impl;

import com.athena.engine.task.Task;
import com.athena.engine.task.TaskManager;
import com.athena.model.Animation;
import com.athena.model.Projectile;
import com.athena.world.content.combat.CombatContainer;
import com.athena.world.content.combat.CombatType;
import com.athena.world.content.combat.strategy.CombatStrategy;
import com.athena.world.entity.impl.Character;
import com.athena.world.entity.impl.npc.NPC;
import com.athena.world.entity.impl.player.Player;

public class Grimspike implements CombatStrategy {

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
		NPC grimspike = (NPC)entity;
		if(grimspike.isChargingAttack() || victim.getConstitution() <= 0) {
			return true;
		}
		
		grimspike.performAnimation(new Animation(grimspike.getDefinition().getAttackAnimation()));
		grimspike.setChargingAttack(true);

		grimspike.getCombatBuilder().setContainer(new CombatContainer(grimspike, victim, 1, 3, CombatType.RANGED, true));
		
		TaskManager.submit(new Task(1, grimspike, false) {
			int tick = 0;
			@Override
			public void execute() {
				if(tick == 1) {
					new Projectile(grimspike, victim, 37, 44, 3, 43, 43, 0).sendProjectile();
					grimspike.setChargingAttack(false);
					stop();
				}
				tick++;
			}
		});
		return true;
	}

	@Override
	public int attackDelay(Character entity) {
		return entity.getAttackSpeed();
	}

	@Override
	public int attackDistance(Character entity) {
		return 6;
	}

	@Override
	public CombatType getCombatType() {
		return CombatType.RANGED;
	}
}
