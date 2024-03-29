package com.athena.world.content.skill.impl.prayer;

import com.athena.engine.task.Task;
import com.athena.engine.task.TaskManager;
import com.athena.model.Animation;
import com.athena.model.Graphic;
import com.athena.model.Skill;
import com.athena.model.definitions.ItemDefinition;
import com.athena.model.input.impl.EnterAmountOfBonesToSacrifice;
import com.athena.world.content.Achievements;
import com.athena.world.content.Achievements.AchievementData;
import com.athena.world.entity.impl.player.Player;


public class BonesOnAltar {

	public static void openInterface(Player player, int itemId) {
		player.getSkillManager().stopSkilling();
		player.setSelectedSkillingItem(itemId);
		player.setInputHandling(new EnterAmountOfBonesToSacrifice());
		player.getPacketSender().sendString(2799, ItemDefinition.forId(itemId).getName()).sendInterfaceModel(1746, itemId, 150) .sendChatboxInterface(4429);
		player.getPacketSender().sendString(2800, "How many would you like to offer?");
	}

	public static void offerBones(final Player player, final int amount) {
		final int boneId = player.getSelectedSkillingItem();
		player.getSkillManager().stopSkilling();
		final BonesData currentBone = BonesData.forId(boneId);
		if(currentBone == null)
			return;
		player.getPacketSender().sendInterfaceRemoval();
		player.setCurrentTask(new Task(2, player, true) {
			int amountSacrificed = 0;
			@Override
			public void execute() {
				if(amountSacrificed >= amount) {
					stop();
					return;
				}
				if(!player.getInventory().contains(boneId)) {
					player.getPacketSender().sendMessage("You have run out of "+ItemDefinition.forId(boneId).getName()+".");
					stop();
					return;
				}
				if(player.getInteractingObject() != null) {
					player.setPositionToFace(player.getInteractingObject().getPosition().copy());
					player.getInteractingObject().performGraphic(new Graphic(624));
				}
				if(currentBone == BonesData.INFERNALGROUDON)
					Achievements.finishAchievement(player, AchievementData.BURY_A_INFERNAL_GROUDON);
				else if(currentBone == BonesData.BAP) {
					Achievements.doProgress(player, AchievementData.BURY_25_BAP);
					Achievements.doProgress(player, AchievementData.BURY_500_BAP);
					//StarterTasks.doProgress(player, StarterTaskData.BURY_50_FROST_DRAGON_BONES);
				}
				amountSacrificed++;
				player.getInventory().delete(boneId, 1);
				player.performAnimation(new Animation(713));
				player.getSkillManager().addExperience(Skill.PRAYER, (int) (currentBone.getBuryingXP() * 1.5));
			}
			@Override
			public void stop() {
				setEventRunning(false);
				player.getPacketSender().sendMessage("You have pleased the gods with your "+(amountSacrificed == 1 ? "sacrifice" : "sacrifices")+".");
			}
		});
		TaskManager.submit(player.getCurrentTask());
	}
}
