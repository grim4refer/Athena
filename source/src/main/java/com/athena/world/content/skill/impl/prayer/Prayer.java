package com.athena.world.content.skill.impl.prayer;

import com.athena.engine.task.Task;
import com.athena.engine.task.TaskManager;
import com.athena.model.Animation;
import com.athena.model.Item;
import com.athena.model.Skill;
import com.athena.world.content.Achievements;
import com.athena.world.content.Sounds;
import com.athena.world.content.Achievements.AchievementData;
import com.athena.world.content.Sounds.Sound;
import com.athena.world.entity.impl.player.Player;

/**
 * The prayer skill is based upon burying the corpses of enemies. Obtaining a higher level means
 * more prayer abilities being unlocked, which help out in combat.
 * 
 * @author Gabriel Hannason
 */

public class Prayer {
	
	public static boolean isBone(int bone) {
		return BonesData.forId(bone) != null;
	}
	
	public static void buryBone(final Player player, final int itemId) {
		if(!player.getClickDelay().elapsed(2000))
			return;
		final BonesData currentBone = BonesData.forId(itemId);
		if(currentBone == null)
			return;
		player.getSkillManager().stopSkilling();
		player.getPacketSender().sendInterfaceRemoval();
		player.performAnimation(new Animation(827));
		player.getPacketSender().sendMessage("You dig a hole in the ground..");
		final Item bone = new Item(itemId);
		player.getInventory().delete(bone);
		TaskManager.submit(new Task(3, player, false) {
			@Override
			public void execute() {
				player.getPacketSender().sendMessage("..and bury the "+bone.getDefinition().getName()+".");
				player.getSkillManager().addExperience(Skill.PRAYER, currentBone.getBuryingXP());
				Sounds.sendSound(player, Sound.BURY_BONE);
				if(currentBone == BonesData.INFERNALGROUDON)
					Achievements.finishAchievement(player, AchievementData.BURY_A_INFERNAL_GROUDON);
				else if(currentBone == BonesData.BAP) {
					Achievements.doProgress(player, AchievementData.BURY_25_BAP);
					Achievements.doProgress(player, AchievementData.BURY_500_BAP);
					//StarterTasks.doProgress(player, StarterTaskData.BURY_50_FROST_DRAGON_BONES);
				}
				stop();				
			}
		});
		player.getClickDelay().reset();
	}
}
