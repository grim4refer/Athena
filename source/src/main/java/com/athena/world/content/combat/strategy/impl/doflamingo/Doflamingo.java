package com.athena.world.content.combat.strategy.impl.doflamingo;

import com.athena.engine.task.Task;
import com.athena.engine.task.TaskManager;
import com.athena.model.Animation;
import com.athena.model.CombatIcon;
import com.athena.model.Graphic;
import com.athena.model.Hit;
import com.athena.model.Hitmask;
import com.athena.model.Locations;
import com.athena.model.Locations.Location;
import com.athena.model.Skill;
import com.athena.util.Misc;
import com.athena.world.content.combat.CombatContainer;
import com.athena.world.content.combat.CombatHitTask;
import com.athena.world.content.combat.CombatType;
import com.athena.world.content.combat.strategy.CombatStrategy;
import com.athena.world.entity.impl.Character;
import com.athena.world.entity.impl.npc.NPC;
import com.athena.world.entity.impl.player.Player;
public class Doflamingo implements CombatStrategy {

	/** @author Lord Lewis **/

	private static final Animation single_poison_attack = new Animation(12153);
	private static final Graphic doflamingo_healing_graphic = new Graphic(1990);

	@Override
	public boolean canAttack(Character entity, Character victim) {
		return victim.isPlayer();
	}

	@Override
	public CombatContainer attack(Character entity, Character victim) {
		return null;
	}

	@Override
	public boolean customContainerAttack(Character entity, Character victim) {
		NPC doflamingo = (NPC)entity;
		if(doflamingo.isChargingAttack() || doflamingo.getConstitution() <= 0) {
			return true;
		}

		if(doflamingo.getConstitution() <= 200000 && !doflamingo.hasHealed()) {
			doflamingo.performGraphic(doflamingo_healing_graphic);
            doflamingo.forceChat("VERSATILE!");
			victim.forceChat("Aaaaaaaaah!");
			victim.dealDamage(new Hit(800, Hitmask.RED, CombatIcon.NONE));
			doflamingo.setConstitution(doflamingo.getConstitution() + Misc.getRandom(700000));
			doflamingo.setHealed(true);
		}

		Player target = (Player)victim;
		boolean crucio = false;
		for (Player t : Misc.getCombinedPlayerList(target)) {

			if (Locations.goodDistance(t.getPosition(), doflamingo.getPosition(), 1)) {
				crucio = true;
				doflamingo.getCombatBuilder().setVictim(t);
				new CombatHitTask(doflamingo.getCombatBuilder(), new CombatContainer(doflamingo, t, 1, CombatType.RANGED, true)).handleAttack();
			}
		}
		if (crucio) {
			doflamingo.performAnimation(single_poison_attack);
			//doflamingo.performGraphic(attack_graphic);
		}

		int attackStyle = Misc.getRandom(3);
		if (attackStyle == 0 || attackStyle == 1) { // poison blast
			int distanceX = target.getPosition().getX() - doflamingo.getPosition().getX();
			int distanceY = target.getPosition().getY() - doflamingo.getPosition().getY();
			if (distanceX > 4 || distanceX < -1 || distanceY > 4 || distanceY < -1)
				attackStyle = 2;
			else {

				doflamingo.performAnimation(new Animation(attackStyle == 0 ? 12153 : 12153));
				if(target.getLocation() == Location.GODWARS_DUNGEON)
					doflamingo.getCombatBuilder().setContainer(new CombatContainer(doflamingo, target, 1, 1, CombatType.RANGED, true));
				return true;
			}
		} else if (attackStyle == 2) { // Single Poison Blast

			doflamingo.performAnimation(single_poison_attack);
			doflamingo.getCombatBuilder().setContainer(new CombatContainer(doflamingo, target, 3, 2, CombatType.RANGED, true));
            doflamingo.forceChat("VERSATILE!");
			target.performGraphic(new Graphic(1990));
		} else if (attackStyle == 3) { // Skill Drain Attack
			doflamingo.performAnimation(single_poison_attack);
			doflamingo.getCombatBuilder().setContainer(new CombatContainer(doflamingo, target, 3, 2, CombatType.RANGED, true));
            doflamingo.forceChat("VERSATILE!");
			TaskManager.submit(new Task(1, target, false) {
				@Override
				public void execute() {
					int skill = (2);

					Skill skillT = Skill.forId(skill);
					Player player = (Player) target;
					int lvl = player.getSkillManager().getCurrentLevel(skillT);
					lvl -= 4 + Misc.getRandom(3);
					target.performGraphic(new Graphic(1990));
		            doflamingo.forceChat("VERSATILE BEYOND REACH!!");
					player.getSkillManager().setCurrentLevel(skillT, player.getSkillManager().getCurrentLevel(skillT) - lvl <= 0 ?  1 : lvl);
					target.getPacketSender().sendMessage("@mag@Doflamingo has drained your strength!");
					stop();
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
		return 20;
	}



	@Override
	public CombatType getCombatType() {
		return CombatType.RANGED;
	}
}