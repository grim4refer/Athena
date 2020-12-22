package com.arlania.world.content.combat.strategy.impl.doflamingo;

import com.arlania.engine.task.Task;
import com.arlania.engine.task.TaskManager;
import com.arlania.model.Animation;
import com.arlania.model.CombatIcon;
import com.arlania.model.Graphic;
import com.arlania.model.Hit;
import com.arlania.model.Hitmask;
import com.arlania.model.Locations;
import com.arlania.model.Locations.Location;
import com.arlania.model.Projectile;
import com.arlania.model.Skill;
import com.arlania.util.Misc;
import com.arlania.world.content.combat.CombatContainer;
import com.arlania.world.content.combat.CombatHitTask;
import com.arlania.world.content.combat.CombatType;
import com.arlania.world.content.combat.strategy.CombatStrategy;
import com.arlania.world.entity.impl.Character;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.player.Player;
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