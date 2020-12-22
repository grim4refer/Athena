package com.arlania.world.content.combat.strategy.impl;

import com.arlania.engine.task.Task;
import com.arlania.engine.task.TaskManager;
import com.arlania.model.Animation;
import com.arlania.model.CombatIcon;
import com.arlania.model.Graphic;
import com.arlania.model.Hit;
import com.arlania.model.Hitmask;
import com.arlania.model.Locations;
import com.arlania.model.Projectile;
import com.arlania.util.Misc;
import com.arlania.world.content.combat.CombatContainer;
import com.arlania.world.content.combat.CombatType;
import com.arlania.world.content.combat.strategy.CombatStrategy;
import com.arlania.world.entity.impl.Character;
import com.arlania.world.entity.impl.npc.NPC;

public class KingOfCaves implements CombatStrategy {

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
		NPC king = (NPC)entity;
		int msg = Misc.random(3);
		
		if(victim.getConstitution() <= 0) {
			return true;
		}
		
		if(king.isChargingAttack()) {
			return true;
		}
		
		if(Locations.goodDistance(king.getPosition().copy(), victim.getPosition().copy(), 1) && Misc.getRandom(6) <= 4) {
			king.performAnimation(new Animation(1658));
			//td.performGraphic(gfx1);
			king.getCombatBuilder().setContainer(new CombatContainer(king, victim, 1, 2, CombatType.MELEE, true));
		}else if(Misc.getRandom(10) <= 7 || !Locations.goodDistance(king.getPosition().copy(), victim.getPosition().copy(), 1)) {
			king.performAnimation(new Animation(1979));
			victim.performGraphic(new Graphic(369));
			if(Misc.random(10) >= 5)
				king.forceChat(messages(msg));
			victim.dealDamage(new Hit(Misc.random(1500), Hitmask.RED, CombatIcon.NONE));
			king.getCombatBuilder().setContainer(new CombatContainer(king, victim, 1, 2, CombatType.MAGIC, true));
			
		} else {
			king.getCombatBuilder().setContainer(new CombatContainer(king, victim, 1, 2, CombatType.RANGED, true));
		}
		return true;
	}
	
	String messages(int i) {
		if (i == 1) {
			return "Sneaky sneaky";
		} else if (i == 2) {
			return "How dare you run from me!!";
		} else if (i == 3) {
			return "Welcome to the game of the king!";
		}
		return "";
	}


	@Override
	public int attackDelay(Character entity) {
		return entity.getAttackSpeed()-1;
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
