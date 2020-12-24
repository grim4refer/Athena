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

public class Troop1 implements CombatStrategy {

		private static final Animation anim = new Animation(427);
		private static final Animation anim2 = new Animation(427);
		private static final Animation anim3 = new Animation(427);
		private static final Graphic gfx1 = new Graphic(481, 3, GraphicHeight.MIDDLE); // 1890 // 1892
		private static final Graphic gfx2 = new Graphic(1209);

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
				td.performAnimation(anim);
				td.performGraphic(gfx1);
				td.getCombatBuilder().setContainer(new CombatContainer(td, victim, 1, 2, CombatType.RANGED, true));
			} else if(Misc.getRandom(1) <= 1) {
				td.performAnimation(anim2);
				td.setChargingAttack(true);
				td.getCombatBuilder().setContainer(new CombatContainer(td, victim, 1, 2, CombatType.RANGED, true));
				TaskManager.submit(new Task(1, td, false) {
					@Override
					protected void execute() {
						new Projectile(td, victim, 1209, 44, 3, 43, 31, 0).sendProjectile();
						td.setChargingAttack(false).getCombatBuilder().setAttackTimer(td.getDefinition().getAttackSpeed() - 1);
						td.performAnimation(anim);
						Misc.random(5);
						if (Misc.getRandom(6) == 1) {
							td.forceChat("I am Storm Troop Yellow GUNNER!");
						}
						if (Misc.getRandom(6) == 1) {
							td.forceChat("Don't even BOTHER!.");
						}
						if(Misc.getRandom(6) == 1) {
							td.forceChat("THINK TWICE BEFORE ATTACKING ME!!!");
						}
						
						if(Misc.getRandom(6) == 1) {
							td.forceChat("Is this a Joke???");
						}
						
						if(Misc.getRandom(6) == 1) {
							td.forceChat("SERIOUSLY!!!");
						}
						stop();
					}
				});
			} else {
				td.performAnimation(anim3);
				victim.performGraphic(gfx2);
				td.getCombatBuilder().setContainer(new CombatContainer(td, victim, 1, 2, CombatType.RANGED, true));
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
			return CombatType.RANGED;
		}
	}


