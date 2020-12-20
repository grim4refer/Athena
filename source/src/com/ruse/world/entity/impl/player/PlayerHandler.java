package com.ruse.world.entity.impl.player;

import com.ruse.GameServer;
import com.ruse.GameSettings;
import com.ruse.engine.task.TaskManager;
import com.ruse.engine.task.impl.BonusExperienceTask;
import com.ruse.engine.task.impl.CombatSkullEffect;
import com.ruse.engine.task.impl.FireImmunityTask;
import com.ruse.engine.task.impl.OverloadPotionTask;
import com.ruse.engine.task.impl.PlayerSkillsTask;
import com.ruse.engine.task.impl.PlayerSpecialAmountTask;
import com.ruse.engine.task.impl.PrayerRenewalPotionTask;
import com.ruse.engine.task.impl.StaffOfLightSpecialAttackTask;
import com.ruse.model.Flag;
import com.ruse.model.Locations;
import com.ruse.model.MessageType;
import com.ruse.model.PlayerRights;
import com.ruse.model.Skill;
import com.ruse.model.container.impl.Bank;
import com.ruse.model.container.impl.Equipment;
import com.ruse.model.definitions.WeaponAnimations;
import com.ruse.model.definitions.WeaponInterfaces;
import com.ruse.net.PlayerSession;
import com.ruse.net.SessionState;
import com.ruse.net.security.ConnectionHandler;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.content.Achievements;
import com.ruse.world.content.BonusManager;
import com.ruse.world.content.Lottery;
import com.ruse.world.content.PlayerLogs;
import com.ruse.world.content.PlayerPanel;
import com.ruse.world.content.PlayersOnlineInterface;
import com.ruse.world.content.WellOfGoodwill;
import com.ruse.world.content.clan.ClanChatManager;
import com.ruse.world.content.combat.effect.CombatPoisonEffect;
import com.ruse.world.content.combat.effect.CombatTeleblockEffect;
import com.ruse.world.content.combat.magic.Autocasting;
import com.ruse.world.content.combat.prayer.CurseHandler;
import com.ruse.world.content.combat.prayer.PrayerHandler;
import com.ruse.world.content.combat.pvp.BountyHunter;
import com.ruse.world.content.combat.range.DwarfMultiCannon;
import com.ruse.world.content.combat.weapon.CombatSpecial;
import com.ruse.world.content.dialogue.DialogueManager;
import com.ruse.world.content.grandexchange.GrandExchange;
import com.ruse.world.content.minigames.impl.Barrows;
import com.ruse.world.content.skill.impl.hunter.Hunter;
import com.ruse.world.content.skill.impl.slayer.Slayer;

import mysql.impl.Hiscores;

public class PlayerHandler {

	public static void handleLogin(Player player) {
		//Register the player
		System.out.println("[World] Registering player - [username, host] : [" + player.getUsername() + ", " + player.getHostAddress() + "]");
		ConnectionHandler.add(player.getHostAddress());
		World.getPlayers().add(player);
		World.updatePlayersOnline();
		PlayersOnlineInterface.add(player);
		player.getSession().setState(SessionState.LOGGED_IN);

		//Packets
		player.getPacketSender().sendMapRegion().sendDetails();

		player.getRecordedLogin().reset();


		//Tabs
		player.getPacketSender().sendTabs();

		//Setting up the player's item containers..
		for(int i = 0; i < player.getBanks().length; i++) {
			if(player.getBank(i) == null) {
				player.setBank(i, new Bank(player));
			}
		}
		player.getInventory().refreshItems();
		player.getEquipment().refreshItems();

		//Weapons and equipment..
		WeaponAnimations.update(player);
		WeaponInterfaces.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
		CombatSpecial.updateBar(player);
		BonusManager.update(player);

		//Skills
		player.getSummoning().login();
		player.getFarming().load();
		Slayer.checkDuoSlayer(player, true);
		for (Skill skill : Skill.values()) {
			player.getSkillManager().updateSkill(skill);
		}

		//Relations
		player.getRelations().setPrivateMessageId(1).onLogin(player).updateLists(true);

		//Client configurations
		player.getPacketSender().sendConfig(172, player.isAutoRetaliate() ? 1 : 0)
		.sendTotalXp(player.getSkillManager().getTotalGainedExp())
		.sendConfig(player.getFightType().getParentId(), player.getFightType().getChildId())
		.sendRunStatus().sendRunEnergy(player.getRunEnergy()).sendRights()
		.sendString(8135, ""+player.getMoneyInPouch())
		.sendInteractionOption("Follow", 3, false)
		.sendInteractionOption("Trade With", 4, false)
		.sendInterfaceRemoval().sendString(39161, "@or2@Server time: @or2@[ @yel@"+Misc.getCurrentServerTime()+"@or2@ ]");

		Autocasting.onLogin(player);
		PrayerHandler.deactivateAll(player);
		CurseHandler.deactivateAll(player);
		BonusManager.sendCurseBonuses(player);
		Achievements.updateInterface(player);
		Barrows.updateInterface(player);

		//Tasks
		TaskManager.submit(new PlayerSkillsTask(player));
		if (player.isPoisoned()) {
			TaskManager.submit(new CombatPoisonEffect(player));
		}
		if(player.getPrayerRenewalPotionTimer() > 0) {
			TaskManager.submit(new PrayerRenewalPotionTask(player));
		}
		if(player.getOverloadPotionTimer() > 0) {
			TaskManager.submit(new OverloadPotionTask(player));
		}
		if (player.getTeleblockTimer() > 0) {
			TaskManager.submit(new CombatTeleblockEffect(player));
		}
		if (player.getSkullTimer() > 0) {
			player.setSkullIcon(1);
			TaskManager.submit(new CombatSkullEffect(player));
		}
		if(player.getFireImmunity() > 0) {
			FireImmunityTask.makeImmune(player, player.getFireImmunity(), player.getFireDamageModifier());
		}
		if(player.getSpecialPercentage() < 100) {
			TaskManager.submit(new PlayerSpecialAmountTask(player));
		}
		if(player.hasStaffOfLightEffect()) {
			TaskManager.submit(new StaffOfLightSpecialAttackTask(player));
		}
		if(player.getMinutesBonusExp() >= 0) {
			TaskManager.submit(new BonusExperienceTask(player));
		}

		//Update appearance
		

		//Others
		Lottery.onLogin(player);
		Locations.login(player);
		player.getPacketSender().sendMessage("@bla@Welcome to Ruse!");
		if(player.experienceLocked())
			player.getPacketSender().sendMessage(MessageType.SERVER_ALERT, "@red@Warning: your experience is currently locked.");
		

		if(GameSettings.BONUS_EXP) {
			player.getPacketSender().sendMessage("<img=10> <col=008FB2>Ruse currently has a bonus experience event going on, make sure to use it!");
		}
		if(WellOfGoodwill.isActive()) {
			player.getPacketSender().sendMessage(MessageType.SERVER_ALERT, "The Well of Goodwill is granting 30% bonus experience for another "+WellOfGoodwill.getMinutesRemaining()+" minutes.");
		}

		PlayerPanel.refreshPanel(player);


		//New player
		if(player.newPlayer()) {
			player.setClanChatName("Help");
			player.setPlayerLocked(true).setDialogueActionId(45);
			DialogueManager.start(player, 81);
		}
		
		ClanChatManager.handleLogin(player);
		
		player.getPacketSender().updateSpecialAttackOrb().sendIronmanMode(player.getGameMode().ordinal());

		if(player.getRights() == PlayerRights.SUPPORT || player.getRights() == PlayerRights.MODERATOR || player.getRights() == PlayerRights.ADMINISTRATOR)
			World.sendMessage(MessageType.PLAYER_ALERT, Misc.formatText(player.getRights().toString().toLowerCase())+" "+player.getUsername()+" has just logged in, feel free to message them for support.");
		
		//GrandExchange.onLogin(player);
		
		if(player.getPointsHandler().getAchievementPoints() == 0) {
			Achievements.setPoints(player);
		}
		
		player.getUpdateFlag().flag(Flag.APPEARANCE);
		PlayerLogs.log(player.getUsername(), "Login from host "+player.getHostAddress()+", serial number: "+player.getSerialNumber());
	}

	public static boolean handleLogout(Player player) {
		try {

			PlayerSession session = player.getSession();
			
			if(session.getChannel().isOpen()) {
				session.getChannel().close();
			}

			if(!player.isRegistered()) {
				return true;
			}

			boolean exception = GameServer.isUpdating() || World.getLogoutQueue().contains(player) && player.getLogoutTimer().elapsed(90000);
			if(player.logout() || exception) {
				System.out.println("[World] Deregistering player - [username, host] : [" + player.getUsername() + ", " + player.getHostAddress() + "]");
				player.getSession().setState(SessionState.LOGGING_OUT);
				ConnectionHandler.remove(player.getHostAddress());
				player.setTotalPlayTime(player.getTotalPlayTime() + player.getRecordedLogin().elapsed());
				player.getPacketSender().sendInterfaceRemoval();
				if(player.getCannon() != null) {
					DwarfMultiCannon.pickupCannon(player, player.getCannon(), true);
				}
				if(exception && player.getResetPosition() != null) {
					player.moveTo(player.getResetPosition());
					player.setResetPosition(null);
				}
				if(player.getRegionInstance() != null) {
					player.getRegionInstance().destruct();
				}
				Hunter.handleLogout(player);
				Locations.logout(player);
				player.getSummoning().unsummon(false, false);
				player.getFarming().save();
				Hiscores.save(player);
				BountyHunter.handleLogout(player);
				ClanChatManager.leave(player, false);
				player.getRelations().updateLists(false);
				PlayersOnlineInterface.remove(player);
				TaskManager.cancelTasks(player.getCombatBuilder());
				TaskManager.cancelTasks(player);
				player.save();
				World.getPlayers().remove(player);
				session.setState(SessionState.LOGGED_OUT);
				World.updatePlayersOnline();
				return true;
			} else {
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
