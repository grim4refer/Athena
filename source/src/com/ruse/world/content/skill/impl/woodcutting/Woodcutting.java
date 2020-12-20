package com.ruse.world.content.skill.impl.woodcutting;

import com.ruse.engine.task.Task;
import com.ruse.engine.task.TaskManager;
import com.ruse.model.Animation;
import com.ruse.model.GameObject;
import com.ruse.model.Skill;
import com.ruse.model.container.impl.Equipment;
import com.ruse.util.Misc;
import com.ruse.world.content.Achievements;
import com.ruse.world.content.CustomObjects;
import com.ruse.world.content.Sounds;
import com.ruse.world.content.Achievements.AchievementData;
import com.ruse.world.content.Sounds.Sound;
import com.ruse.world.content.randomevents.EvilTree.EvilTreeDef;
import com.ruse.world.content.skill.impl.firemaking.Logdata;
import com.ruse.world.content.skill.impl.firemaking.Logdata.logData;
import com.ruse.world.content.skill.impl.woodcutting.WoodcuttingData.Hatchet;
import com.ruse.world.content.skill.impl.woodcutting.WoodcuttingData.Trees;
import com.ruse.world.entity.impl.player.Player;

public class Woodcutting {

	public static void cutWood(final Player player, final GameObject object, boolean restarting) {
		if(!restarting)
			player.getSkillManager().stopSkilling();
		if(player.getInventory().getFreeSlots() == 0) {
			player.getPacketSender().sendMessage("You don't have enough free inventory space.");
			return;
		}
		player.setPositionToFace(object.getPosition());
		final int objId = object.getId();
		final Hatchet h = Hatchet.forId(WoodcuttingData.getHatchet(player));
		if (h != null) {
			if (player.getSkillManager().getCurrentLevel(Skill.WOODCUTTING) >= h.getRequiredLevel()) {
				final Trees t = Trees.forId(objId);
				final EvilTreeDef t2 = EvilTreeDef.forId(objId);
				final boolean isEvilTree = t2 != null;
				if (t != null || t2 != null) {
					player.setEntityInteraction(object);
					if (player.getSkillManager().getCurrentLevel(Skill.WOODCUTTING) >= (isEvilTree ? t2.getWoodcuttingLevel() : t.getReq())) {
						player.performAnimation(new Animation(h.getAnim()));
						int delay = Misc.getRandom((isEvilTree ? 8 : t.getTicks()) - WoodcuttingData.getChopTimer(player, h)) +1;
						player.setCurrentTask(new Task(1, player, false) {
							int cycle = 0, reqCycle = delay >= 2 ? delay : Misc.getRandom(1) + 1;
							@Override
							public void execute() {
								if(player.getInventory().getFreeSlots() == 0) {
									player.performAnimation(new Animation(65535));
									player.getPacketSender().sendMessage("You don't have enough free inventory space.");
									this.stop();
									return;
								}
								if (cycle != reqCycle) {
									cycle++;
									player.performAnimation(new Animation(h.getAnim()));
								} else if (cycle >= reqCycle) {
									int xp = isEvilTree ? t2.getWoodcuttingXp() : t.getXp();
									if(lumberJack(player))
										xp *= 1.5;
									player.getSkillManager().addExperience(Skill.WOODCUTTING, xp);
									cycle = 0;
									BirdNests.dropNest(player);
									this.stop();
									if (!isEvilTree && (!t.isMulti() || Misc.getRandom(15) >= 2)) {
										treeRespawn(player, object);
										player.getPacketSender().sendMessage("You've chopped the tree down.");
										player.performAnimation(new Animation(65535));
									} else {
										cutWood(player, object, true);
										player.getPacketSender().sendMessage("You get some logs..");
									}
									Sounds.sendSound(player, Sound.WOODCUT);
									if(!(infernoAdze(player) && Misc.getRandom(5) <= 2)) {
										player.getInventory().add(isEvilTree ? t2.getLog() : t.getReward(), 1);
									} else if(Misc.getRandom(5) <= 2) {
										logData fmLog = Logdata.getLogData(player, isEvilTree ? t2.getLog() : t.getReward());
										if(fmLog != null) {
											player.getSkillManager().addExperience(Skill.FIREMAKING, fmLog.getXp());
											player.getPacketSender().sendMessage("Your Inferno Adze burns the log, granting you Firemaking experience.");
											if(fmLog == Logdata.logData.OAK) {
												Achievements.finishAchievement(player, AchievementData.BURN_AN_OAK_LOG);
											} else if(fmLog == Logdata.logData.MAGIC) {
												Achievements.doProgress(player, AchievementData.BURN_100_MAGIC_LOGS);
												Achievements.doProgress(player, AchievementData.BURN_2500_MAGIC_LOGS);
											}
										}
									}
									if(t2 != null && t2 == EvilTreeDef.EVIL_OAK_TREE || t != null && t == Trees.OAK) {
										Achievements.finishAchievement(player, AchievementData.CUT_AN_OAK_TREE);
									} else if(t2 != null && t2 == EvilTreeDef.EVIL_MAGIC_TREE || t != null && t == Trees.MAGIC) {
										Achievements.doProgress(player, AchievementData.CUT_100_MAGIC_LOGS);
										Achievements.doProgress(player, AchievementData.CUT_5000_MAGIC_LOGS);
									}
								}
							}
						});
						TaskManager.submit(player.getCurrentTask());
					} else {
						player.getPacketSender().sendMessage("You need a Woodcutting level of at least "+t.getReq()+" to cut this tree.");
					}
				}
			} else {
				player.getPacketSender().sendMessage("You do not have a hatchet which you have the required Woodcutting level to use.");
			}
		} else {
			player.getPacketSender().sendMessage("You do not have a hatchet that you can use.");
		}
	}
	
	public static boolean lumberJack(Player player) {
		return player.getEquipment().get(Equipment.HEAD_SLOT).getId() == 10941 && player.getEquipment().get(Equipment.BODY_SLOT).getId() == 10939 && player.getEquipment().get(Equipment.LEG_SLOT).getId() == 10940 && player.getEquipment().get(Equipment.FEET_SLOT).getId() == 10933; 
	}
	
	public static boolean infernoAdze(Player player) {
		return player.getEquipment().get(Equipment.WEAPON_SLOT).getId() == 13661;
	}

	public static void treeRespawn(final Player player, final GameObject oldTree) {
		if(oldTree == null || oldTree.getPickAmount() >= 1)
			return;
		oldTree.setPickAmount(1);
		for(Player players : player.getLocalPlayers()) {
			if(players == null)
				continue;
			if(players.getInteractingObject() != null && players.getInteractingObject().getPosition().equals(player.getInteractingObject().getPosition().copy())) {
				players.getSkillManager().stopSkilling();
				players.getPacketSender().sendClientRightClickRemoval();
			}
		}
		player.getPacketSender().sendClientRightClickRemoval();
		player.getSkillManager().stopSkilling();
		CustomObjects.globalObjectRespawnTask(new GameObject(1343, oldTree.getPosition().copy(), 10, 0), oldTree, 20 + Misc.getRandom(10));
	}

}
