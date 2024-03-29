package com.athena.world.content.combat.strategy.impl.gwd2;

import com.athena.engine.task.Task;
import com.athena.engine.task.TaskManager;
import com.athena.model.Animation;
import com.athena.model.CombatIcon;
import com.athena.model.Graphic;
import com.athena.model.Hit;
import com.athena.model.Hitmask;
import com.athena.model.Locations;
import com.athena.model.Skill;
import com.athena.util.Misc;
import com.athena.world.content.combat.CombatContainer;
import com.athena.world.content.combat.CombatHitTask;
import com.athena.world.content.combat.CombatType;
import com.athena.world.content.combat.strategy.CombatStrategy;
import com.athena.world.entity.impl.Character;
import com.athena.world.entity.impl.npc.NPC;
import com.athena.world.entity.impl.player.Player;

public class ArmadylAbyzou implements CombatStrategy {
	
	/** @author Lord Lewis **/

	private static final Animation attackanim = new Animation(64);
	private static final Graphic HeavyAttackGFX = new Graphic(287);

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
		NPC abyzou = (NPC)entity;
		if(abyzou.isChargingAttack() || abyzou.getConstitution() <= 0) {
			return true;
		}
		
		if(abyzou.getConstitution() <= 1500 && !abyzou.hasHealed()) {
			victim.performGraphic(HeavyAttackGFX);
			abyzou.forceChat("Leave fool!");
			victim.dealDamage(new Hit(400, Hitmask.RED, CombatIcon.NONE));
			abyzou.setHealed(true);
		}
		
		Player target = (Player)victim;
		boolean crucio = false;
		for (Player t : Misc.getCombinedPlayerList(target)) {
			
			if (Locations.goodDistance(t.getPosition(), abyzou.getPosition(), 1)) {
				crucio = true;
				abyzou.getCombatBuilder().setVictim(t);
				new CombatHitTask(abyzou.getCombatBuilder(), new CombatContainer(abyzou, t, 1, CombatType.MAGIC, true)).handleAttack();
			}
		}
		if (crucio) {
			abyzou.performAnimation(attackanim);
			//abyzou.performGraphic(attack_graphic);
		}

		int attackStyle = Misc.getRandom(3);
	if (attackStyle == 0) { // Hand slash
			abyzou.performAnimation(attackanim);
			abyzou.getCombatBuilder().setContainer(new CombatContainer(abyzou, target, 1, 2, CombatType.MELEE, true));
	} else if (attackStyle == 1) { // Hand slash
				abyzou.performAnimation(attackanim);
				abyzou.getCombatBuilder().setContainer(new CombatContainer(abyzou, target, 1, 2, CombatType.MELEE, true));

		} else if (attackStyle == 2) { // Single Poison Blast
			
			abyzou.performAnimation(attackanim);
			abyzou.getCombatBuilder().setContainer(new CombatContainer(abyzou, target, 1, 2, CombatType.MELEE, true));
		} else if (attackStyle == 3) { // Skill Drain Attack
			abyzou.performAnimation(attackanim);
				abyzou.getCombatBuilder().setContainer(new CombatContainer(abyzou, target, 2, 2, CombatType.MELEE, true));
			TaskManager.submit(new Task(1, target, false) {
				@Override
				public void execute() {
					int skill = (6);
					Skill skillT = Skill.forId(skill);
					Player player = (Player) target;
					int lvl = player.getSkillManager().getCurrentLevel(skillT);
					lvl -= 40 + Misc.getRandom(3);
					 target.performGraphic(new Graphic(287));
					player.getSkillManager().setCurrentLevel(skillT, player.getSkillManager().getCurrentLevel(skillT) - lvl <= 0 ?  1 : lvl);
					target.getPacketSender().sendMessage("@red@Abyzou has drained your Magic!");
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
		return CombatType.MAGIC;
	}
}