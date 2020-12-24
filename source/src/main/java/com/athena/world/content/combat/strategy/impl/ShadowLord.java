package com.athena.world.content.combat.strategy.impl;

import com.athena.engine.task.Task;
import com.athena.engine.task.TaskManager;
import com.athena.model.*;
import com.athena.util.Misc;
import com.athena.world.World;
import com.athena.world.content.combat.CombatContainer;
import com.athena.world.content.combat.CombatType;
import com.athena.world.content.combat.strategy.CombatStrategy;
import com.athena.world.content.global.GlobalBoss;
import com.athena.world.entity.impl.Character;
import com.athena.world.entity.impl.npc.NPC;
import com.athena.world.entity.impl.player.Player;

public class ShadowLord implements CombatStrategy {

	private static final Animation anim = new Animation(10265);
	private static final Animation anim2 = new Animation(729);
	private static final Animation anim3 = new Animation(811);
	private static final Graphic gfx1 = new Graphic(1834, 3);
	private static final Graphic gfx2 = new Graphic(1679,3);

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

		NPC td;

		if(entity instanceof GlobalBoss) {
			 td = (GlobalBoss)entity;
		} else {
			 td = (NPC)entity;
		}

		if(victim.getConstitution() <= 0) {
			return true;
		}

		if(td.isChargingAttack()) {
			return true;
		}

		if(Locations.goodDistance(td.getPosition().copy(), victim.getPosition().copy(), 1) && Misc.getRandom(6) <= 4) {
			td.performAnimation(anim);
			td.performGraphic(gfx1);
			final int x = td.getPosition().getX(), y = td.getPosition().getY();
			TaskManager.submit(new Task(4) {
				@Override
				protected void execute() {
					for(Player p : World.getPlayers())
					{
						if(p != null)
						{

							if(p.getPosition().distanceToPoint(x, y) <= 2)
							{
								p.performGraphic(gfx2);
								td.forceChat("Feel my Force!");
								p.performGraphic(new Graphic(453));
								p.dealDamage(new Hit(Misc.random(400,850), Hitmask.RED, CombatIcon.NONE));
							}
						}
					}
					this.stop();
				}
			});

		} else if(Misc.getRandom(10) <= 6) {
			td.performAnimation(anim2);
			td.setChargingAttack(true);
			td.getCombatBuilder().setContainer(new CombatContainer(td, victim, 1, 2, CombatType.MAGIC, true));
			TaskManager.submit(new Task(1, td, false) {

				@Override
				protected void execute() {
					new Projectile(td, victim, 1166, 44, 3, 43, 31, 0).sendProjectile();
					td.setChargingAttack(false).getCombatBuilder().setAttackTimer(td.getDefinition().getAttackSpeed() - 1);
					stop();
				}
			});
		} else {
			td.performAnimation(anim3);
			final int x = td.getPosition().getX(), y = td.getPosition().getY();
			TaskManager.submit(new Task(4) {
				@Override
				protected void execute() {
					for(Player p : World.getPlayers())
					{
						if(p != null)
						{

						if(p.getPosition().distanceToPoint(x, y) <= 20)
						{
							p.performGraphic(new Graphic(453));
							td.forceChat("Burn!!!!");
							p.dealDamage(new Hit(Misc.random(400,850), Hitmask.RED, CombatIcon.NONE));
						}
						}
					}
					this.stop();
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

			// The default distance for all npcs using ranged is 6.
			if (entity instanceof GlobalBoss)  {
				return 12;
			} else {
				return 5;
			}
	}
	
	@Override
	public CombatType getCombatType() {
		return CombatType.MIXED;
	}
}
