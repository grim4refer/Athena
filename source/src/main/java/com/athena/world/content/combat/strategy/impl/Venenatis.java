package com.athena.world.content.combat.strategy.impl;

import com.athena.model.Animation;
import com.athena.model.Graphic;
import com.athena.model.Skill;
import com.athena.util.Misc;
import com.athena.world.content.combat.CombatContainer;
import com.athena.world.content.combat.CombatHitTask;
import com.athena.world.content.combat.CombatType;
import com.athena.world.content.combat.strategy.CombatStrategy;
import com.athena.world.entity.impl.Character;
import com.athena.world.entity.impl.player.Player;

/** @author Lukafurlan **/

public class Venenatis implements CombatStrategy {

	public static int phase;
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
		Player player = (Player)victim;
		if(player.getConstitution() <= 0 || entity.getConstitution() <= 0) {
			return true;
		}

		if(phase == 0)
		{
			int rand = Misc.getRandom(10);
			int amountToDrain = Misc.getRandom(200);
			if(rand == 5) {
				new CombatHitTask(entity.getCombatBuilder(), new CombatContainer(entity, player, 1, CombatType.MAGIC, true)).handleAttack();
				entity.performAnimation(new Animation(5327));
				player.performGraphic(new Graphic(377));
				player.getPacketSender().sendMessage("@red@You were hit by powerfull magic attack!");
			} else if(rand == 2) {
				new CombatHitTask(entity.getCombatBuilder(), new CombatContainer(entity, player, 1, CombatType.MAGIC, true)).handleAttack();
				new CombatHitTask(entity.getCombatBuilder(), new CombatContainer(entity, player, 1, CombatType.MAGIC, true)).handleAttack();
				entity.performAnimation(new Animation(5327));
				player.performGraphic(new Graphic(377));
				player.getPacketSender().sendMessage("@red@Venenatis casts two powerfull spells at once!");
			} else if(rand == 4 || rand == 3){
				new CombatHitTask(entity.getCombatBuilder(), new CombatContainer(entity, player, 1, CombatType.MELEE, true)).handleAttack();
				player.getSkillManager().setCurrentLevel(Skill.PRAYER, player.getSkillManager().getCurrentLevel(Skill.PRAYER) - amountToDrain);
				player.getPacketSender().sendMessage("@blu@Venenatis has drained "+amountToDrain+" prayer points!");
				entity.performAnimation(new Animation(5327));
			} else {
				new CombatHitTask(entity.getCombatBuilder(), new CombatContainer(entity, player, 1, CombatType.MELEE, true)).handleAttack();
				entity.performAnimation(new Animation(5327));
			}
		}
		return true;
	}

	@Override
	public int attackDelay(Character entity) {
		return entity.getAttackSpeed();
	}

	@Override
	public int attackDistance(Character entity) {
		return 3;
	}


	

	@Override
	public CombatType getCombatType() {
		// TODO Auto-generated method stub
		return null;
	}

}