package com.athena.world.content.combat.strategy.impl;

import com.athena.engine.task.Task;
import com.athena.engine.task.TaskManager;
import com.athena.model.Animation;
import com.athena.model.Graphic;
import com.athena.model.GraphicHeight;
import com.athena.model.Locations;
import com.athena.model.Projectile;
import com.athena.util.Misc;
import com.athena.world.content.combat.CombatContainer;
import com.athena.world.content.combat.CombatType;
import com.athena.world.content.combat.strategy.CombatStrategy;
import com.athena.world.entity.impl.Character;
import com.athena.world.entity.impl.npc.NPC;

public class Graardor implements CombatStrategy {

	private static final Animation attack_anim2 = new Animation(7060);
	private static final Animation attack_anim = new Animation(7063);
	private static final Graphic graphic1 = new Graphic(1200, GraphicHeight.MIDDLE);
	private static final Graphic graphic2 = new Graphic(1450, GraphicHeight.MIDDLE);
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
		NPC td = (NPC)entity;
		if(victim.getConstitution() <= 0) {
			return true;
		}
		if(td.isChargingAttack()) {
			return true;
		}
		if(Locations.goodDistance(td.getPosition().copy(), victim.getPosition().copy(), 1) && Misc.getRandom(6) <= 4) {
			td.performAnimation(attack_anim);
			td.performGraphic(graphic1);
			td.getCombatBuilder().setContainer(new CombatContainer(td, victim, 1, 2, CombatType.MELEE, true));
		} else if(Misc.getRandom(10) <= 7) {
			new Projectile(td, victim, graphic2.getId(), 44, 3, 43, 43, 0).sendProjectile();
			td.performAnimation(attack_anim);
			td.setChargingAttack(true);
			td.getCombatBuilder().setContainer(new CombatContainer(td, victim, 1, 2, CombatType.MAGIC, true));
			TaskManager.submit(new Task(1, td, false) {
				@Override
				protected void execute() {
					new Projectile(td, victim, graphic2.getId(), 44, 3, 43, 43, 0).sendProjectile();
					td.setChargingAttack(false).getCombatBuilder().setAttackTimer(td.getDefinition().getAttackSpeed() - 1);
					td.performAnimation(attack_anim2);
					Misc.random(5);
					if (Misc.getRandom(6) == 1) {
						td.forceChat("GRAAAAAAAAAARDORRRRRRRRRRRRRRRRR!");
					}
					if (Misc.getRandom(6) == 1) {
						td.forceChat("GRAAAAAAAAAARDORRRRRRRRRRRRRRRRR!");
					}
					if(Misc.getRandom(6) == 1) {
						td.forceChat("GRAAAAAAAAAARDORRRRRRRRRRRRRRRRR!");
					}
					
					if(Misc.getRandom(6) == 1) {
						td.forceChat("GRAAAAAAAAAARDORRRRRRRRRRRRRRRRR!");
					}
					
					if(Misc.getRandom(6) == 1) {
						td.forceChat("GRAAAAAAAAAARDORRRRRRRRRRRRRRRRR!");
					}
					stop();
				}
			});
		} else {
			td.performAnimation(attack_anim);
			victim.performGraphic(graphic1);
			td.getCombatBuilder().setContainer(new CombatContainer(td, victim, 1, 2, CombatType.MAGIC, true));
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


