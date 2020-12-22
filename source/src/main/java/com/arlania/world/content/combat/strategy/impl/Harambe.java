package com.arlania.world.content.combat.strategy.impl;

import com.arlania.util.Misc;
import com.arlania.world.content.combat.strategy.CombatStrategy;
import com.arlania.engine.task.Task;
import com.arlania.engine.task.TaskManager;
import com.arlania.model.Animation;
import com.arlania.model.Locations;
import com.arlania.world.content.combat.CombatContainer;
import com.arlania.world.content.combat.CombatType;
import com.arlania.world.entity.impl.Character;
import com.arlania.world.entity.impl.npc.NPC;

public class Harambe implements CombatStrategy {

	private static final Animation anim = new Animation(11968);

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
		NPC td = (NPC) entity;
		if (victim.getConstitution() <= 0) {
			return true;
		}
		if (td.isChargingAttack()) {
			return true;
		}
		if (Locations.goodDistance(td.getPosition().copy(), victim.getPosition().copy(), 1) && Misc.getRandom(6) <= 4) {
			td.getCombatBuilder().setContainer(new CombatContainer(td, victim, 1, 2, CombatType.MELEE, true));
			td.performAnimation(anim);
		} else if (Misc.getRandom(10) <= 7) {
			// td.performAnimation(anim2);
			td.setChargingAttack(true);
			td.getCombatBuilder().setContainer(new CombatContainer(td, victim, 1, 2, CombatType.MELEE, true));
			td.performAnimation(anim);
			TaskManager.submit(new Task(1, td, false) {
				@Override
				protected void execute() {
					td.performAnimation(anim);
					Misc.random(5);
					if (Misc.getRandom(6) == 1) {
						td.forceChat("stop attacking me before ill slap u with the quickness");
					}
					if (Misc.getRandom(6) == 1) {
						td.forceChat("ah you little goblin boy, can't seem to understand me, ight.");
					}
					if(Misc.getRandom(6) == 1) {
						td.forceChat("Are you lost little dumb ass jonny boy?");
					}
					
					if(Misc.getRandom(6) == 1) {
						td.forceChat("You are about to see 2 Savage robots destroy a stupid goblin boy!");
					}
					
					if(Misc.getRandom(6) == 1) {
						td.forceChat("I have no problem sending stupid goblin boys back to lumbridge!");
					}
					td.setChargingAttack(false).getCombatBuilder()
							.setAttackTimer(td.getDefinition().getAttackSpeed() - 1);
					stop();
				}
			});
		} else {
			// td.performAnimation(anim3);
			// victim.performGraphic(gfx2);
			td.getCombatBuilder().setContainer(new CombatContainer(td, victim, 1, 2, CombatType.MELEE, true));
			td.performAnimation(anim);
		}
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
		return CombatType.MIXED;
	}
}
