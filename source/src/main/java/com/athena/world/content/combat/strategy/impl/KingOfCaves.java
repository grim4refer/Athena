package com.athena.world.content.combat.strategy.impl;

import com.athena.model.Animation;
import com.athena.model.CombatIcon;
import com.athena.model.Graphic;
import com.athena.model.Hit;
import com.athena.model.Hitmask;
import com.athena.model.Locations;
import com.athena.util.Misc;
import com.athena.world.content.combat.CombatContainer;
import com.athena.world.content.combat.CombatType;
import com.athena.world.content.combat.strategy.CombatStrategy;
import com.athena.world.entity.impl.Character;
import com.athena.world.entity.impl.npc.NPC;

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
