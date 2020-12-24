package com.athena.engine.task.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.athena.GameLoader;
import com.athena.engine.task.Task;
import com.athena.engine.task.TaskManager;
import com.athena.model.Animation;
import com.athena.model.Locations.Location;
import com.athena.model.definitions.NPCDrops;
import com.athena.world.World;
import com.athena.world.content.*;
import com.athena.world.content.Achievements.AchievementData;
import com.athena.world.content.LoyaltyProgramme.LoyaltyTitles;
import com.athena.world.content.StarterTasks.StarterTaskData;
import com.athena.world.content.combat.strategy.impl.GanodermicNPC;
import com.athena.world.content.combat.strategy.impl.KalphiteQueen;
import com.athena.world.content.combat.strategy.impl.Nex;
import com.athena.world.content.ganodermic.Ganodermic;
import com.athena.world.content.global.GlobalBoss;
import com.athena.world.content.global.GlobalBossHandler;
import com.athena.world.content.raids.immortal.ImmortalRaidsManager;
import com.athena.world.entity.impl.npc.NPC;
import com.athena.world.entity.impl.player.Player;

//import mysql.impl.Voting;

/**
 * Represents an npc's death task, which handles everything
 * an npc does before and after their death animation (including it), 
 * such as dropping their drop table items.
 * 
 * @author relex lawl
 */

public class NPCDeathTask extends Task {
	/*
	 * The array which handles what bosses will give a player points
	 * after death
	 */
	private Set<Integer> BOSSES = new HashSet<>(Arrays.asList(
			9939,6307,10126,3053,5588,13447,6306, 433,

			13458,132,2070,2071,3915,8528,7,6336,389,53,170,360,359,361,365,363,370,362,368,367, 2502, 2506, 2509, 8, 2511, 2, 2515, 2518, 3, 1299, 2088, 15, 18, 19,4331,4332,4333,4334,4335,4336,4330,4329,5592)); //use this array of npcs to change the npcs you want to give boss points
	
	/**
	 * The NPCDeathTask constructor.
	 * @param npc	The npc being killed.
	 */
	public NPCDeathTask(NPC npc) {
		super(2);
		this.npc = npc;
		this.ticks = 2;
	}

	/**
	 * The npc setting off the death task.
	 */
	private final NPC npc;

	/**
	 * The amount of ticks on the task.
	 */
	private int ticks = 2;

	/**
	 * The player who killed the NPC
	 */
	private Player killer = null;

	@SuppressWarnings("incomplete-switch")
	@Override
	public void execute() {
		try {
			npc.setEntityInteraction(null);
			
			if (npc.isRaidsBoss()) {
				ImmortalRaidsManager.handleNPCDeath(npc, npc.getCombatBuilder().getKiller(true));
				stop();
				return;
			}
			
			switch (ticks) {
			case 2:
				npc.getMovementQueue().setLockMovement(true).reset();
				if(npc.getId() == 2070) {
					Ganodermic.ganoAlive = false;
					/** not done yet **/
					Ganodermic.handleDrop(npc);
					World.sendMessage("[<col=01701d>PVM</col>] @red@The beast has been slain!");
					for(int i = 0; i < GanodermicNPC.messagesSent.length; i++) {
						GanodermicNPC.messagesSent[i] = false;
						GanodermicNPC.phase = 0;
					}
				}
				killer = npc.getCombatBuilder().getKiller(!(npc instanceof GlobalBoss));
				if(!(npc.getId() >= 6142 && npc.getId() <= 6145) && !(npc.getId() > 5070 && npc.getId() < 5081))
					npc.performAnimation(new Animation(npc.getDefinition().getDeathAnimation()));

				/** CUSTOM NPC DEATHS **/
				if(npc.getId() == 13447) {
					Nex.handleDeath();
				}

				break;
				
			case 0:
				if(killer != null) {
					//killer.setNpcKills(killer.getNpcKills() + 1);
					//killer.sendMessage("<img=0>You now have @red@" + killer.getNpcKills() + " NPC Points!");
					boolean boss = (npc.getDefaultConstitution() > 2000);
					if(!Nex.nexMinion(npc.getId()) && npc.getId() != 1158 && !(npc.getId() >= 3493 && npc.getId() <= 3497)) {
						KillsTracker.submitById(killer, npc.getId(), true, boss);
						KillsTracker.submitById(killer, npc.getId(), false, boss);
						if(boss) {
							Achievements.doProgress(killer, AchievementData.DEFEAT_500_BOSSES);
						}
					}
					killer.getPvMRanking().check();
					if (BOSSES.contains(npc.getId())) {
						killer.setBossPoints(killer.getBossPoints() + (GameLoader.doubleBossPoints() ? 2 : 1));
						killer.sendMessage("<img=0>You now have @red@" + killer.getBossPoints() + " Boss Points!");
					}
					if(npc.getId() == 2436) {
						killer.setArcticPSPoints(killer.getArcticPSPoints() + 10);
						killer.sendMessage("<img=0>You now have @red@" + killer.getArcticPSPoints() + " Yanille Points!");
					}
					if(npc.getId() == 6305) { //<-- CHANGE THIS.
						//Voting.VOTES = 0;
					//	Voting.handleKilledVotingBoss(killer);
					}
					Achievements.doProgress(killer, AchievementData.DEFEAT_10000_MONSTERS);
					if(npc.getId() == 8528) {
						Achievements.finishAchievement(killer, AchievementData.DEFEAT_NOMAD);
					} else if(npc.getId() == 3) {
						killer.getAchievementAttributes().setGodKilled(4, true);
					}	else if(npc.getId() == 2071) {
						LoyaltyProgramme.unlock(killer, LoyaltyTitles.ASSASSIN);
					} else if(npc.getId() == 1265) {
						StarterTasks.doProgress(killer, StarterTaskData.DEFEAT_10_CARS);
						Achievements.doProgress(killer, AchievementData.DEFEAT_10_CARS);
						Achievements.doProgress(killer, AchievementData.DEFEAT_100_CARS);
						Achievements.doProgress(killer, AchievementData.DEFEAT_500_CARS);
					} else if(npc.getId() == 2437) {
						StarterTasks.doProgress(killer, StarterTaskData.DEFEAT_10_CRASH);
						Achievements.doProgress(killer, AchievementData.DEFEAT_500_CRASH);
					} else if(npc.getId() == 5417) {
						StarterTasks.doProgress(killer, StarterTaskData.DEFEAT_10_TERROR_DOGS);
					} else if(npc.getId() == 131) {
						StarterTasks.doProgress(killer, StarterTaskData.DEFEAT_10_PINGUINS);
					} else if(npc.getId() == 6102) {
						StarterTasks.doProgress(killer, StarterTaskData.DEFEAT_10_AURELIA);
						Achievements.doProgress(killer, AchievementData.DEFEAT_500_AURELIAS);
					} else if(npc.getId() == 4392) {
						StarterTasks.doProgress(killer, StarterTaskData.DEFEAT_10_BELERIONS);
						Achievements.doProgress(killer, AchievementData.DEFEAT_500_BELERIONS);
					} else if(npc.getId() == 1459) {
						StarterTasks.doProgress(killer, StarterTaskData.DEFEAT_10_NATURE_SWORDS);
					} else if(npc.getId() == 52) {
						StarterTasks.doProgress(killer, StarterTaskData.DEFEAT_10_LIVYATHANN);
					} else if(npc.getId() == 4413) {
						StarterTasks.doProgress(killer, StarterTaskData.DEFEAT_10_DIRE_WOLFS);
					} else if(npc.getId() == 6303) {
						StarterTasks.doProgress(killer, StarterTaskData.DEFEAT_10_ABBADONS);
					} else if(npc.getId() == 1234) {
						StarterTasks.doProgress(killer, StarterTaskData.DEFEAT_10_INFERNAL_GROUDONS);
					} else if(npc.getId() == 2236) {
						StarterTasks.doProgress(killer, StarterTaskData.DEFEAT_10_BAPHOMET);
					} else if(npc.getId() == 6336) {
						Achievements.finishAchievement(killer, AchievementData.DEFEAT_GOKU);
					} else if(npc.getId() == 389) {
						Achievements.finishAchievement(killer, AchievementData.DEFEAT_VEGETA);
					} else if(npc.getId() == 9939) {
						Achievements.finishAchievement(killer, AchievementData.DEFEAT_PLANEFREEZER);
					} else if(npc.getId() == 3491) {
						Achievements.finishAchievement(killer, AchievementData.DEFEAT_THE_CULINAROMANCER);
					} else if(npc.getId() == 7) {
						Achievements.finishAchievement(killer, AchievementData.DEFEAT_BROLY);
					} else if(npc.getId() == 6307) {
						Achievements.finishAchievement(killer, AchievementData.DEFEAT_IKTOMI);
					} else if(npc.getId() == 170) {
						Achievements.finishAchievement(killer, AchievementData.DEFEAT_LUCARIO);
					} else if(npc.getId() == 360) {
						Achievements.finishAchievement(killer, AchievementData.DEFEAT_MEWTWO);
					} else if(npc.getId() == 359) {
						Achievements.finishAchievement(killer, AchievementData.DEFEAT_CHARMELEON);
					} else if(npc.getId() == 361) {
						Achievements.finishAchievement(killer, AchievementData.DEFEAT_SQUIRTLE);
					} else if(npc.getId() == 365) {
						Achievements.finishAchievement(killer, AchievementData.DEFEAT_PIKACHU);
					} else if(npc.getId() == 363) {
						Achievements.finishAchievement(killer, AchievementData.DEFEAT_SONIC);
					} else if(npc.getId() == 370) {
						Achievements.finishAchievement(killer, AchievementData.DEFEAT_DONKEYKONG);
					} else if(npc.getId() == 362) {
						Achievements.finishAchievement(killer, AchievementData.DEFEAT_MR_KRABS);
					} else if(npc.getId() == 368) {
						Achievements.finishAchievement(killer, AchievementData.DEFEAT_NUTELLA);
					} else if(npc.getId() == 367) {
						Achievements.finishAchievement(killer, AchievementData.DEFEAT_MAYONNAISE);
					}
					if (World.minigameHandler.handleNpcDeath(npc)) {//leave everything open please.you dont need this anymore
						stop();
						return;
					}
					/** ACHIEVEMENTS **/
					switch(killer.getLastCombatType()) {
					case MAGIC:
						Achievements.finishAchievement(killer, AchievementData.KILL_A_MONSTER_USING_MAGIC);
						break;
					case MELEE:
						Achievements.finishAchievement(killer, AchievementData.KILL_A_MONSTER_USING_MELEE);
						break;
					case RANGED:
						Achievements.finishAchievement(killer, AchievementData.KILL_A_MONSTER_USING_RANGED);
						break;
					}

					/** LOCATION KILLS **/
					if(npc.getLocation().handleKilledNPC(killer, npc)) {
						stop();
						return;
					}
					
					/*
					 * Halloween event dropping
					 */

					if(npc.getId() == 1973) {
						TrioBosses.handleSkeleton(killer, npc.getPosition());
					}
					if(npc.getId() == 75) {
						TrioBosses.handleZombie(killer, npc.getPosition());
					}
					if(npc.getId() == 103) {
						TrioBosses.handleGhost(killer, npc.getPosition());
					}
					
					if (npc.getId() == 1001)
                        killer.rollDedicatedBossExtras();
					
					if(npc instanceof GlobalBoss) {
						GlobalBossHandler.onDeath((GlobalBoss) npc);
						stop();
						break;
					}

					/*
					 * End Halloween event dropping
					 */
					/**
					 * Keys Event Monsters
									**/
									if(npc.getId() == 7286) {
										KeysEvent.handleSkotizo(killer, npc.getPosition());
									}
									if(npc.getId() == 8549) {
										KeysEvent.handlePhoenix(killer, npc.getPosition());
									}
									if(npc.getId() == 499) {
										KeysEvent.handleThermo(killer, npc.getPosition());
									}
									if(npc.getId() == 2060) {
										KeysEvent.handleSlashBash(killer, npc.getPosition());
									}
									if(npc.getId() == 2642) {
										KeysEvent.handleKBD(killer, npc.getPosition());
									}
									if(npc.getId() == 1999) {
										KeysEvent.handleCerb(killer, npc.getPosition());
									}
									if(npc.getId() == 7134) {
										KeysEvent.handleBork(killer, npc.getPosition());
									}
									if(npc.getId() == 1382) {
										KeysEvent.handleGlacor(killer, npc.getPosition());
									}
									if(npc.getId() == 6766) {
										KeysEvent.handleShaman(killer, npc.getPosition());
									}
									if(npc.getId() == 941) {
										KeysEvent.handleGreenDragon(killer, npc.getPosition());
									}
									if(npc.getId() == 55) {
										KeysEvent.handleBlueDragon(killer, npc.getPosition());
									}
									if(npc.getId() == 1615) {
										KeysEvent.handleAbbyDemon(killer, npc.getPosition());
									}

					//if (World.minigameHandler.handleNpcDeath(npc)) {
					//	stop();
					//	return;
					//}
					/** PARSE DROPS **/
					NPCDrops.dropItems(killer, npc);
					
					/** SLAYER **/
					killer.getSlayer().killedNpc(npc);
				}
				stop();
				break;
			}
			ticks--;
		} catch(Exception e) {
			e.printStackTrace();
			stop();
		}
	}

	@Override
	public void stop() {
		setEventRunning(false);

		npc.setDying(false);

		//respawn
		 if(npc.getId() != 1001  && !npc.isRaidsBoss() &&  npc.getDefinition().getRespawnTime() > 0 && npc.getLocation() != Location.GRAVEYARD && npc.getLocation() != Location.DUNGEONEERING) {
	            TaskManager.submit(new NPCRespawnTask(npc, npc.getDefinition().getRespawnTime()));
	        }

		World.deregister(npc);

		if(npc.getId() == 1158 || npc.getId() == 1160) {
			KalphiteQueen.death(npc.getId(), npc.getPosition());
		}
		if(Nex.nexMob(npc.getId())) {
			Nex.death(npc.getId());
		}
	}
}
