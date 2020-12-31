package com.athena.world.entity.impl.player;

import com.athena.GameServer;
import com.athena.GameSettings;
import com.athena.engine.task.TaskManager;
import com.athena.engine.task.impl.BonusExperienceTask;
import com.athena.engine.task.impl.CombatSkullEffect;
import com.athena.engine.task.impl.FireImmunityTask;
import com.athena.engine.task.impl.OverloadPotionTask;
import com.athena.engine.task.impl.PlayerSkillsTask;
import com.athena.engine.task.impl.PlayerSpecialAmountTask;
import com.athena.engine.task.impl.PrayerRenewalPotionTask;
import com.athena.engine.task.impl.StaffOfLightSpecialAttackTask;
import com.athena.model.*;
import com.athena.model.Locations.Location;
import com.athena.model.container.impl.Bank;
import com.athena.model.container.impl.Equipment;
import com.athena.model.definitions.WeaponAnimations;
import com.athena.model.definitions.WeaponInterfaces;
import com.athena.net.PlayerSession;
import com.athena.net.SessionState;
import com.athena.net.security.ConnectionHandler;
import com.athena.util.Misc;
import com.athena.world.World;
import com.athena.world.content.Achievements;
import com.athena.world.content.BonusManager;
import com.athena.world.content.Lottery;
import com.athena.world.content.PlayerLogs;
import com.athena.world.content.PlayerPanel;
import com.athena.world.content.PlayersOnlineInterface;
import com.athena.world.content.StaffList;
import com.athena.world.content.VotingContest;
import com.athena.world.content.WellOfGoodwill;
import com.athena.world.content.WellOfWealth;
import com.athena.world.content.clan.ClanChatManager;
import com.athena.world.content.combat.effect.CombatPoisonEffect;
import com.athena.world.content.combat.effect.CombatTeleblockEffect;
import com.athena.world.content.combat.magic.Autocasting;
import com.athena.world.content.combat.prayer.CurseHandler;
import com.athena.world.content.combat.prayer.PrayerHandler;
import com.athena.world.content.combat.pvp.BountyHunter;
//import com.arlania.world.content.combat.range.DwarfMultiCannon;
import com.athena.world.content.combat.weapon.CombatSpecial;
import com.athena.world.content.dialogue.DialogueManager;
import com.athena.world.content.ganodermic.Ganodermic;
import com.athena.world.content.grandexchange.GrandExchange;
import com.athena.world.content.minigames.impl.Barrows;
import com.athena.world.content.raids.immortal.ImmortalRaidsManager;
import com.athena.world.content.skill.impl.hunter.Hunter;
import com.athena.world.content.skill.impl.slayer.Slayer;
import com.athena.world.entity.impl.npc.NPC;


public class PlayerHandler {

    public static Player getPlayerForName(String name) {
        for (Player player : World.getPlayers()) {
            if (player == null)
                continue;
            if (player.getUsername().equalsIgnoreCase(name))
                return player;
        }
        return null;
    }

    public static void handleLogin(Player player) {
        System.out.println("[World] Registering player - [username, host] : [" + player.getUsername() + ", "
                + player.getHostAddress() + "]");
        player.getPlayerOwnedShopManager().hookShop();
        player.setActive(true);
        ConnectionHandler.add(player.getHostAddress());
        World.getPlayers().add(player);
        World.updatePlayersOnline();
        //System.out.println("Donation Thread Reached");
       // new Thread(new Donation(player)).start();
        PlayersOnlineInterface.add(player);
        player.getSession().setState(SessionState.LOGGED_IN);

        player.getPacketSender().sendMapRegion().sendDetails();

        player.getRecordedLogin().reset();

        player.getPacketSender().sendTabs();

        for (int i = 0; i < player.getBanks().length; i++) {
            if (player.getBank(i) == null) {
                player.setBank(i, new Bank(player));
            }
        }
        player.getInventory().refreshItems();
        player.getEquipment().refreshItems();

        WeaponAnimations.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
        WeaponInterfaces.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
        CombatSpecial.updateBar(player);
        BonusManager.update(player);

        player.getSummoning().login();
        
        //player.setAttribute("raidsCreation", Boolean.FALSE);
       // player.setAttribute("raidsJoin", Boolean.FALSE);

        if (player.getTimer() > 0) {
            NPC npc = new NPC(player.getTransform(), new Position(0, 0));
            player.setNpcTransformationId(player.getTransform());
            player.setTransform(player.getTransform());
            player.getUpdateFlag().flag(Flag.APPEARANCE);
            player.getPacketSender().sendInterfaceRemoval();
        }
        player.getFarming().load();
        Slayer.checkDuoSlayer(player, true);
        for (Skill skill : Skill.VALUES) {
            player.getSkillManager().updateSkill(skill);
        }

        player.getRelations().setPrivateMessageId(1).onLogin(player).updateLists(true);

        player.getPacketSender().sendConfig(172, player.isAutoRetaliate() ? 1 : 0)
                .sendTotalXp(player.getSkillManager().getTotalGainedExp())
                .sendConfig(player.getFightType().getParentId(), player.getFightType().getChildId()).sendRunStatus()
                .sendRunEnergy(player.getRunEnergy()).sendString(8135, "" + player.getMoneyInPouch())
                .sendInteractionOption("Follow", 3, false).sendInteractionOption("Trade With", 4, false)
                .sendInteractionOption("Gamble With", 5, false).sendInterfaceRemoval()
                .sendString(39173, "@or2@Server time: @or2@[ @yel@" + Misc.getCurrentServerTime() + "@or2@ ]");

        Autocasting.onLogin(player);
        PrayerHandler.deactivateAll(player);
        CurseHandler.deactivateAll(player);
        BonusManager.sendCurseBonuses(player);
        Achievements.updateInterface(player);
        Barrows.updateInterface(player);

        TaskManager.submit(new PlayerSkillsTask(player));
        if (player.isPoisoned()) {
            TaskManager.submit(new CombatPoisonEffect(player));
        }
        if (player.getPrayerRenewalPotionTimer() > 0) {
            TaskManager.submit(new PrayerRenewalPotionTask(player));
        }
        if (player.getOverloadPotionTimer() > 0) {
            TaskManager.submit(new OverloadPotionTask(player));
        }
        if (player.getTeleblockTimer() > 0) {
            TaskManager.submit(new CombatTeleblockEffect(player));
        }
        if (player.getSkullTimer() > 0) {
            player.setSkullIcon(1);
            TaskManager.submit(new CombatSkullEffect(player));
        }
        if (player.getFireImmunity() > 0) {
            FireImmunityTask.makeImmune(player, player.getFireImmunity(), player.getFireDamageModifier());
        }
        if (player.getSpecialPercentage() < 100) {
            TaskManager.submit(new PlayerSpecialAmountTask(player));
        }
        if (player.hasStaffOfLightEffect()) {
            TaskManager.submit(new StaffOfLightSpecialAttackTask(player));
        }
        if (player.getMinutesBonusExp() >= 0) {
            TaskManager.submit(new BonusExperienceTask(player));
        }

        player.getUpdateFlag().flag(Flag.APPEARANCE);
		
        Lottery.onLogin(player);
        VotingContest.onLogin(player);
        Locations.login(player);

        if (!player.didReceiveStarter()) {
            // player.getInventory().add(995, 1000000).add(15501, 1).add(1153, 1).add(1115,
            // 1).add(1067, 1).add(1323, 1).add(1191, 1).add(841, 1).add(882, 50).add(1167,
            // 1).add(1129, 1).add(1095, 1).add(1063, 1).add(579, 1).add(577, 1).add(1011,
            // 1).add(1379, 1).add(556, 50).add(558, 50).add(557, 50).add(555, 50).add(1351,
            // 1).add(1265, 1).add(1712, 1).add(11118, 1).add(1007, 1).add(1061,
            // 1).add(1419, 1);

            // player.setReceivedStarter(true);
        }
        // DialogueManager.start(player, 177);
        player.getPacketSender().sendMessage("@blu@Welcome to Athena!@bla@");
        player.getPacketSender().sendMessage("@red@We hope you enjoy your stay at Athena");

        if (player.getBetaTester()) {
            World.sendMessage(
                    "@bla@[@or2@Beta tester@bla@] @or2@" + player.getUsername() + "@bla@ has just logged in!");
        }
        if (player.experienceLocked()) {
            player.getPacketSender().sendMessage("@red@Warning: your experience is currently locked.");
        }

        if (!player.newPlayer()) {
            ClanChatManager.handleLogin(player);
            ClanChatManager.join(player, "Athena");
        }
        if (GameSettings.BONUS_EXP) {
            player.getPacketSender().sendMessage(
                    "<img=10> <col=008FB2>Athena currently has a bonus experience event going on, make sure to use it!");
        }
        if (WellOfWealth.isActive()) {
            player.getPacketSender()
                    .sendMessage("<img=10> @blu@The Well of Wealth is granting x2 Easier Droprates for another "
                            + WellOfWealth.getMinutesRemaining() + " minutes.");
        }
        if (WellOfGoodwill.isActive()) {
            player.getPacketSender()
                    .sendMessage("<img=10> @blu@The Well of Goodwill is granting 30% Bonus xp for another "
                            + WellOfGoodwill.getMinutesRemaining() + " minutes.");
        }
        PlayerPanel.refreshPanel(player);

        // New player
        if (player.newPlayer()) {
            //StartScreen.open(player);
        	player.setDialogueActionId(200);
        	DialogueManager.start(player, 200);
            player.setPlayerLocked(true);
        }
        
		if (Ganodermic.ganoAlive) {
			Ganodermic.sendHint(player);
		}

        player.getPacketSender().updateSpecialAttackOrb().sendIronmanMode(player.getGameMode().ordinal());

        if (player.getRights() == PlayerRights.OWNER || player.getRights() == PlayerRights.SUPPORT
                || player.getRights() == PlayerRights.MODERATOR || player.getRights() == PlayerRights.FORUM_DEVELOPER
                || player.getRights() == PlayerRights.ADMINISTRATOR) {
            World.sendMessage("<img=" + player.getRights().ordinal() + "><col=6600CC> "
                    + Misc.formatText(player.getRights().toString().toLowerCase()) + " " + player.getUsername()
                    + " has just logged in, feel free to message me for support!");
        }
        if (player.getRights() == PlayerRights.DELUXE_DONATOR) {
            World.sendMessage("<img=" + player.getRights().ordinal() + "<shad=000066CC>"
                    + Misc.formatText(player.getRights().toString().toLowerCase()) + " " + player.getUsername()
                    + " has just logged in, a dearly Respected Member.");
        }
        if (player.getRights() == PlayerRights.MODERATOR || player.getRights() == PlayerRights.ADMINISTRATOR
                || player.getRights() == PlayerRights.SUPPORT || player.getRights() == PlayerRights.DELUXE_DONATOR
                || player.getRights() == PlayerRights.FORUM_DEVELOPER || player.getRights() == PlayerRights.OWNER) {
            if (!StaffList.staff.contains(player.getUsername())) {
                StaffList.login(player);
            }
        }
        
		if (player.isInDung()) {
			System.out.println(player.getUsername()+" logged in from a bad dungeoneering session.");
			PlayerLogs.log(player.getUsername(), " logged in from a bad dungeoneering session. Inv/equipment wiped.");
			player.getInventory().resetItems().refreshItems();
			player.getEquipment().resetItems().refreshItems();
			if (player.getLocation() == Location.DUNGEONEERING) {
				player.moveTo(GameSettings.DEFAULT_POSITION.copy());
			}
			player.getPacketSender().sendMessage("Your Dungeon has been disbanded.");
			player.setInDung(false);
		}
		
        GrandExchange.onLogin(player);

        if (player.getPointsHandler().getAchievementPoints() == 0) {
            Achievements.setPoints(player);
        }

        if (player.getPlayerOwnedShopManager().getEarnings() > 0) {
            player.sendMessage("<col=FF0000>You have unclaimed earnings in your player owned shop!");
        }

        PlayerLogs.log(player.getUsername(),
                "Login from host " + player.getHostAddress() + ", serial number: " + player.getSerialNumber());
    }

    public static boolean handleLogout(Player player) {
        try {

            PlayerSession session = player.getSession();

            if (session.getChannel().isOpen()) {
                session.getChannel().close();
            }

            if (!player.isRegistered()) {
                return true;
            }

            ImmortalRaidsManager.handleLogout(player);
            
            boolean exception = GameServer.isUpdating()
                    || World.getLogoutQueue().contains(player) && player.getLogoutTimer().elapsed(600000);
            if (player.logout() || exception) {
                // new Thread(new HighscoresHandler(player)).start();
                System.out.println("[World] Deregistering player - [username, host] : [" + player.getUsername() + ", "
                        + player.getHostAddress() + ", " + player.getSerialNumber() + "]");
                player.getSession().setState(SessionState.LOGGING_OUT);
                ConnectionHandler.remove(player.getHostAddress());
                player.setTotalPlayTime(player.getTotalPlayTime() + player.getRecordedLogin().elapsed());
                player.getPacketSender().sendInterfaceRemoval();
//                if (player.getCannon() != null) {
//                    // DwarfMultiCannon.pickupCannon(player, player.getCannon(), true);
//                }
                if (exception && player.getResetPosition() != null) {
                    player.moveTo(player.getResetPosition());
                    player.setResetPosition(null);
                }
                if (player.getRegionInstance() != null) {
                    player.getRegionInstance().destruct();
                }

                if (player.getRights() == PlayerRights.MODERATOR || player.getRights() == PlayerRights.ADMINISTRATOR
                        || player.getRights() == PlayerRights.SUPPORT
                        || player.getRights() == PlayerRights.DELUXE_DONATOR
                        || player.getRights() == PlayerRights.OWNER) {
                    StaffList.logout(player);
                }
    			if(player.getSummoning().getFamiliar() != null) {
    				player.getSummoning().unsummon(true, false);
    			}
                Hunter.handleLogout(player);
                Locations.logout(player);
                player.getFarming().save();
                player.getPlayerOwnedShopManager().unhookShop();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
