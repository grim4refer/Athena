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

public class DagannothSupreme implements CombatStrategy {

	@Override
	public boolean canAttack(Character entity, Character victim) {
		return true;
	}

	@Override
	public CombatContainer attack(Character entity, Character victim) {
		return null;
	}

	@Override
	public boolean customContainerAttack(Character entity, Character victim) {
		NPC prime = (NPC)entity;
		if(prime.getConstitution() <= 0 || victim.getConstitution() <= 0) {
			return true;
		}
		prime.performAnimation(new Animation(prime.getDefinition().getAttackAnimation()));
		TaskManager.submit(new Task(1, prime, false) {

			@Override
			protected void execute() {
				new Projectile(prime, victim, 1937, 44, 3, 43, 43, 0).sendProjectile();
				prime.getCombatBuilder().setContainer(new CombatContainer(prime, victim, 1, 2, CombatType.RANGED, true));
				stop();
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
		return 5;
	}
	
	@Override
	public CombatType getCombatType() {
		return CombatType.RANGED;
	}
}
