package com.arlania.world.content.combat.strategy.impl;

import com.arlania.engine.task.Task;
import com.arlania.engine.task.TaskManager;
import com.arlania.model.Animation;
import com.arlania.model.Graphic;
import com.arlania.model.GraphicHeight;
import com.arlania.util.Misc;
import com.arlania.world.content.combat.CombatContainer;
import com.arlania.world.content.combat.CombatType;
import com.arlania.world.content.combat.HitQueue.CombatHit;
import com.arlania.world.content.combat.strategy.CombatStrategy;
import com.arlania.world.entity.impl.Character;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.player.Player;
import com.arlania.world.World;

public class GanodermicNPC implements CombatStrategy {

	private static final Animation attack_anim = new Animation(7595);

	private static String[] messages = { "@red@Ganodermic Beast yells \"Ahhh... new victims!\"",
								"@red@Ganodermic Beast yells \"I have not come this far to be stopped!\"",
								"@red@Ganodermic Beast yells \"The future I have planned will not be jeopardized!\"",
								"@red@Ganodermic Beast yells \"Now, you will taste true power!\"",
								"@red@Ganodermic Beast yells \"DIE DIE DIE NOW!\""};
								
	public static boolean[] messagesSent = new boolean[5];
	public static int phase = 0;
	
	public int attackStyle(Character entity) {
		NPC gano = (NPC)entity;
		if(gano.getConstitution() > 11250000) {
			return 0;
		} else if (gano.getConstitution() > 7500000 && gano.getConstitution() < 11250000) {
			return 1;
		} else if (gano.getConstitution() > 3750000 && gano.getConstitution() < 7500000) {
			return 2;
		} else if(gano.getConstitution() > 7500000 && gano.getConstitution() < 3750000){
			return 3;
		} else if(gano.getConstitution() < 3750000){
			return 4;
		}
		return 0;
	}
	
	
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
		NPC ganodermicNPC = (NPC)entity;
		if(ganodermicNPC.isChargingAttack() || ganodermicNPC.getConstitution() <= 0) {
			return true;
		}
		if (attackStyle(ganodermicNPC) == 0) {
			if(!messagesSent[0]){		
				World.sendMessage(messages[0]);
				messagesSent[0] = true;
			}
			phase = 0;
			ganodermicNPC.performAnimation(new Animation(ganodermicNPC.getDefinition().getAttackAnimation()));
			ganodermicNPC.getCombatBuilder().setContainer(new CombatContainer(ganodermicNPC, victim, 1, 1, CombatType.MELEE, true));
		} if (attackStyle(ganodermicNPC) == 1) {
			if(!messagesSent[1]){		
				World.sendMessage(messages[1]);
				messagesSent[1] = true;
			}
			phase = 1;
			ganodermicNPC.performAnimation(attack_anim);
			ganodermicNPC.setChargingAttack(true);
			Player target = (Player)victim;
			for (Player t : Misc.getCombinedPlayerList(target)) {
				if(t == null)
					continue;
				if(t.getPosition().distanceToPoint(ganodermicNPC.getPosition().getX(), ganodermicNPC.getPosition().getY()) > 20)
					continue;
			}
			TaskManager.submit(new Task(1, target, false) {
				@Override
				public void execute() {
					for (Player t : Misc.getCombinedPlayerList(target)) {
						if(t == null)
							continue;
						ganodermicNPC.getCombatBuilder().setVictim(t);
						t.performGraphic(new Graphic(1194, GraphicHeight.MIDDLE));
						new CombatHit(ganodermicNPC.getCombatBuilder(), new CombatContainer(ganodermicNPC, t, 1, CombatType.GANO_MAGE, true)).handleAttack();
					}
					ganodermicNPC.setChargingAttack(false);
					stop();
				}
			});
		} else if (attackStyle(ganodermicNPC) == 2) {
			if(!messagesSent[2]){		
				World.sendMessage(messages[2]);
				messagesSent[2] = true;
			}
			phase = 2;
			ganodermicNPC.performAnimation(attack_anim);
			ganodermicNPC.setChargingAttack(true);
			Player target = (Player)victim;
			for (Player t : Misc.getCombinedPlayerList(target)) {
				if(t == null)
					continue;
				if(t.getPosition().distanceToPoint(ganodermicNPC.getPosition().getX(), ganodermicNPC.getPosition().getY()) > 20)
					continue;
			}
			TaskManager.submit(new Task(1, target, false) {
				@Override
				public void execute() {
					for (Player t : Misc.getCombinedPlayerList(target)) {
						if(t == null)
							continue;
						ganodermicNPC.getCombatBuilder().setVictim(t);
						t.performGraphic(new Graphic(542));
						new CombatHit(ganodermicNPC.getCombatBuilder(), new CombatContainer(ganodermicNPC, t, 1, CombatType.RANGED, true)).handleAttack();
					}
					ganodermicNPC.setChargingAttack(false);
					stop();
				}
			});
		} else if (attackStyle(ganodermicNPC) == 3) {
			if(!messagesSent[3]){		
				World.sendMessage(messages[3]);
				messagesSent[3] = true;
			}
			phase = 3;
			ganodermicNPC.performAnimation(attack_anim);
			ganodermicNPC.setChargingAttack(true);
			Player target = (Player)victim;
			for (Player t : Misc.getCombinedPlayerList(target)) {
				if(t == null)
					continue;
				if(t.getPosition().distanceToPoint(ganodermicNPC.getPosition().getX(), ganodermicNPC.getPosition().getY()) > 20)
					continue;
			}
			TaskManager.submit(new Task(1, target, false) {
				@Override
				public void execute() {
					for (Player t : Misc.getCombinedPlayerList(target)) {
						if(t == null)
							continue;
						int type = Misc.getRandom(1);
						if (type == 0) {
							ganodermicNPC.getCombatBuilder().setVictim(t);
							t.performGraphic(new Graphic(542));
							new CombatHit(ganodermicNPC.getCombatBuilder(), new CombatContainer(ganodermicNPC, t, 1, CombatType.RANGED, true)).handleAttack();
						} else if (type == 1) {
							ganodermicNPC.getCombatBuilder().setVictim(t);
							t.performGraphic(new Graphic(1194, GraphicHeight.MIDDLE));
							new CombatHit(ganodermicNPC.getCombatBuilder(), new CombatContainer(ganodermicNPC, t, 1, CombatType.GANO_MAGE, true)).handleAttack();
						}
					}
					ganodermicNPC.setChargingAttack(false);
					stop();
				}
			});
		} else if (attackStyle(ganodermicNPC) == 4) {
			if(!messagesSent[4]){		
				World.sendMessage(messages[4]);
				messagesSent[4] = true;
			}
			phase = 4;
			ganodermicNPC.performAnimation(attack_anim);
			ganodermicNPC.setChargingAttack(true);
			Player target = (Player)victim;
			for (Player t : Misc.getCombinedPlayerList(target)) {
				if(t == null)
					continue;
				if(t.getPosition().distanceToPoint(ganodermicNPC.getPosition().getX(), ganodermicNPC.getPosition().getY()) > 20)
					continue;
			}
			TaskManager.submit(new Task(1, target, false) {
				@Override
				public void execute() {
					for (Player t : Misc.getCombinedPlayerList(target)) {
						if(t == null)
							continue;
						int type = Misc.getRandom(2);
						if (type == 0) {
							ganodermicNPC.getCombatBuilder().setVictim(t);
							t.performGraphic(new Graphic(542));
							new CombatHit(ganodermicNPC.getCombatBuilder(), new CombatContainer(ganodermicNPC, t, 1, CombatType.RANGED, true)).handleAttack();
						} else if (type == 1) {
							ganodermicNPC.getCombatBuilder().setVictim(t);
							t.performGraphic(new Graphic(1194, GraphicHeight.MIDDLE));
							new CombatHit(ganodermicNPC.getCombatBuilder(), new CombatContainer(ganodermicNPC, t, 1, CombatType.GANO_MAGE, true)).handleAttack();
						} else if (type == 2) {
							ganodermicNPC.getCombatBuilder().setVictim(t);
							new CombatHit(ganodermicNPC.getCombatBuilder(), new CombatContainer(ganodermicNPC, t, 1, CombatType.MELEE, true)).handleAttack();
						}
					}
					ganodermicNPC.setChargingAttack(false);
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
		return 10;
	}

	@Override
	public CombatType getCombatType() {
		return CombatType.MIXED;
	}
}
