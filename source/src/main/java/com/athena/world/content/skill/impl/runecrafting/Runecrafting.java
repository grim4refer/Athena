package com.athena.world.content.skill.impl.runecrafting;

import com.athena.model.Animation;
import com.athena.model.Graphic;
import com.athena.model.Position;
import com.athena.model.Skill;
import com.athena.world.content.Achievements;
import com.athena.world.content.Achievements.AchievementData;
import com.athena.world.content.skill.impl.runecrafting.RunecraftingData.RuneData;
import com.athena.world.content.skill.impl.runecrafting.RunecraftingData.TalismanData;
import com.athena.world.content.transportation.TeleportHandler;
import com.athena.world.entity.impl.player.Player;

/**
 * Handles the Runecrafting skill
 * @author Gabriel Hannason
 */
public class Runecrafting {
	
	public static void craftRunes(final Player player, RunecraftingData.RuneData rune) {
		if(!canRuneCraft(player, rune))
			return;
		int essence = -1;
		if(player.getInventory().contains(1436) && !rune.pureRequired())
			essence = 1436;
		if(player.getInventory().contains(7936) && essence < 0)
			essence = 7936;
		if(essence == -1)
			return;
		player.performGraphic(new Graphic(186));
		player.performAnimation(new Animation(791));
		int amountToMake = RunecraftingData.getMakeAmount(rune, player);
		int amountMade = 0;
		for(int i = 28; i > 0; i--) {
			if(!player.getInventory().contains(essence))
				break;
			player.getInventory().delete(essence, 1);
			player.getInventory().add(rune.getRuneID(), amountToMake);
			amountMade += amountToMake;
			player.getSkillManager().addExperience(Skill.RUNECRAFTING, rune.getXP());
		}
		if(rune == RuneData.BLOOD_RUNE) {
			Achievements.doProgress(player, AchievementData.RUNECRAFT_500_BLOOD_RUNES, amountMade);
			Achievements.doProgress(player, AchievementData.RUNECRAFT_8000_BLOOD_RUNES, amountMade);
		}
		player.performGraphic(new Graphic(129));
		player.getSkillManager().addExperience(Skill.RUNECRAFTING, rune.getXP());
		player.getPacketSender().sendMessage("You bind the altar's power into "+rune.getName()+ "s..");
		Achievements.finishAchievement(player, AchievementData.RUNECRAFT_SOME_RUNES);
		player.getClickDelay().reset();
	}
	
	public static void handleTalisman(Player player, int ID) {
		TalismanData talisman = RunecraftingData.TalismanData.forId(ID);
		if(talisman == null)
			return;
		if(player.getSkillManager().getMaxLevel(Skill.RUNECRAFTING) < talisman.getLevelRequirement()) {
			player.getPacketSender().sendMessage("You need a Runecrafting level of at least " +talisman.getLevelRequirement()+ " to use this Talisman's teleport function.");
			return;
		}
		Position targetLocation = talisman.getLocation();
		TeleportHandler.teleportPlayer(player, targetLocation, player.getSpellbook().getTeleportType());
	}
	
	public static boolean canRuneCraft(Player player, RunecraftingData.RuneData rune) {
		if(rune == null)
			return false;
		if(player.getSkillManager().getMaxLevel(Skill.RUNECRAFTING) < rune.getLevelRequirement()) {
			player.getPacketSender().sendMessage("You need a Runecrafting level of at least " +rune.getLevelRequirement() + " to craft this.");
			return false;
		}
		if(rune.pureRequired() && !player.getInventory().contains(7936) && !player.getInventory().contains(1436)) {
			player.getPacketSender().sendMessage("You do not have any Pure essence in your inventory.");
			return false;
		} else if(rune.pureRequired() && !player.getInventory().contains(7936) && player.getInventory().contains(1436)) {
			player.getPacketSender().sendMessage("Only Pure essence has the power to bind this altar's energy.");
			return false;
		}
		if(!player.getInventory().contains(7936) && !player.getInventory().contains(1436)) {
			player.getPacketSender().sendMessage("You do not have any Rune or Pure essence in your inventory.");
			return false;
		}
		if(!player.getClickDelay().elapsed(4500))
			return false;
		return true;
	}
	
	public static boolean runecraftingAltar(Player player, int ID) {
		return ID >= 2478 && ID < 2489 || ID == 17010 || ID == 30624 || ID == 47120;
	}

}
