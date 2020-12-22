package com.arlania.world.content.teleportation;

import com.arlania.model.PlayerRights;
import com.arlania.model.Position;
import com.arlania.world.World;
import com.arlania.world.content.KillsTracker;
import com.arlania.world.content.minigames.impl.GodsRaid;
import com.arlania.world.content.transportation.TeleportHandler;
import com.arlania.world.entity.impl.player.Player;

public class Teleporting {

	private static int[][] LINE_IDS = {{60662, 60663},{60664, 60665},{60666, 60667},{60668, 60669},{60670, 60671},{60672, 60673},{60674, 60675},{60676, 60677},{60678, 60679},{18374, 60701},{60702, 60703},{60704, 60705}};
	private static int[] BUTTON_IDS = {-4914, -4911, -4908, -4905, -4902, -4899, -4896, -4893, -4890, -4845, -4842, -4839};
	private static int[] TAB_IDS = {-4934, -4931, -4928, -4925, -4922, -4919};
	private static int[] INTERFACE_IDS = {60600, 60700, 60800, 60900, 61000, 61100};

	/**
	 * Method that handles the destination teleporting.
	 * @param client
	 * 			The player.teleporting to the destination.    //sec
	 * @param button
	 * 			Button id being clicked to get the destination.
	 */
	public static void teleport(Player player, int button) {
		
		/*
		 * Player Controller
		 */
		for (int i = 0; i < BUTTON_IDS.length; i++) {
			if (button == BUTTON_IDS[i]) {
				player.destination = i;
			}
		}
		if (player.lastClickedTab == 1)
			teleportTraining(player, player.destination);
		else if (player.lastClickedTab == 2)
			teleportMinigames(player, player.destination);
		else if (player.lastClickedTab == 3)
			teleportBosses(player, player.destination);
		else if (player.lastClickedTab == 4)
			teleportPlayerKilling(player, player.destination);
		else if (player.lastClickedTab == 5)
			teleportSkilling(player, player.destination);
		else if (player.lastClickedTab == 6)
			teleportDonator(player, player.destination);
	}

	/**
	 * Training teleport method.
	 * @param client
	 * 			The player.teleporting to a training area.
	 * @param destination
	 * 			The destination being teleported to.
	 */
	public static void teleportTraining(Player player, int destination) {
		//int npckills = player.getNpcKills();
		int npckills = KillsTracker.getTotalKills(player);
		for (final TeleportTraining.Training t : TeleportTraining.Training.values()) {
			if (destination == t.ordinal()) {
				if (t.getCoordinates()[2] == 10) {
					player.sendMessage("This teleport is currently unavailable.");
					return;
				}
				if(player.getInstance()) {
					player.setInstance(false);
				}
				if(npckills < t.getReq()) {
					player.sendMessage("You need "+t.getReq()+" Npc kills to access this zone!");
					player.sendMessage("You have "+npckills+" Npc kills!");
					return;
				}
				TeleportHandler.teleportPlayer(player, new Position(t.getCoordinates()[0], t.getCoordinates()[1], t.getCoordinates()[2]), player.getSpellbook().getTeleportType());
			}
		}
	}

	/**
	 * Minigame teleport method.
	 * @param client
	 * 			The player.teleporting to a minigame area.
	 * @param destination
	 * 			The destination being teleported to.
	 */
	public static void teleportMinigames(Player player, int destination) {
		int npckills = KillsTracker.getTotalKills(player);
		for (final TeleportMinigames.Minigames m : TeleportMinigames.Minigames.values()) {
			if (destination == m.ordinal()) {
				TeleportHandler.teleportPlayer(player, new Position(m.getCoordinates()[0], m.getCoordinates()[1], m.getCoordinates()[2]), player.getSpellbook().getTeleportType());
			}
		}
	}


	public static void teleportBosses(Player player, int destination) {
		//int npckills = player.getNpcKills();
		int npckills = KillsTracker.getTotalKills(player);
		for (final TeleportBosses.Bosses b : TeleportBosses.Bosses.values()) {
			if (destination == b.ordinal()) {
				
				TeleportHandler.teleportPlayer(player, new Position(b.getCoordinates()[0], b.getCoordinates()[1], b.getCoordinates()[2]), player.getSpellbook().getTeleportType());
			}
		}
	}

	/**
	 * Player killing teleport method.
	 * @param client
	 * 			The player.teleporting to a player killing area.
	 * @param destination
	 * 			The destination being teleported to.
	 */
	public static void teleportPlayerKilling(Player player, int destination) {
		for (final TeleportPlayerKilling.PlayerKilling p : TeleportPlayerKilling.PlayerKilling.values()) {
			if (destination == p.ordinal()) {
				TeleportHandler.teleportPlayer(player, new Position(p.getCoordinates()[0], p.getCoordinates()[1], p.getCoordinates()[2]), player.getSpellbook().getTeleportType());
			}
		}
	}

	/**
	 * Skilling teleport method.
	 * @param client
	 * 			The player.teleporting to a skilling area.
	 * @param destination
	 * 			The destination being teleported to.
	 */
	public static void teleportSkilling(Player player, int destination) {
		for (final TeleportSkilling.Skilling s : TeleportSkilling.Skilling.values()) {
			if (destination == s.ordinal()) {
				if (s.getCoordinates()[2] == 10) {
					player.sendMessage("This teleport is currently unavailable.");
					return;
				}
				if(s.getCoordinates()[0] == 1823){
					if (player.getSummoned() != -1) {
						player.sendMessage("You cannot bring pets into the Marvel Raid!");
						return;
					} else {
					if(!GodsRaid.getStarted()) {
						World.minigameHandler.getMinigame(0).addPlayer(player);
					} else {
						player.sendMessage("@red@Marvel Raid is already started! wait till next round.");
						return;
					}
					}
				
				}
				
				if(s.getCoordinates()[0] == 3138){
						World.minigameHandler.getMinigame(1).addPlayer(player);
					}
				TeleportHandler.teleportPlayer(player, new Position(s.getCoordinates()[0], s.getCoordinates()[1], s.getCoordinates()[2]), player.getSpellbook().getTeleportType());
			}
		}
	}

	/**
	 * Donator teleport method.
	 * @param client
	 * 			The player.teleporting to a donator area.
	 * @param destination
	 * 			The destination being teleported to.
	 */
	public static void teleportDonator(Player player, int destination) {

		for (final TeleportDonator.Donator d : TeleportDonator.Donator.values()) {
			if (destination == d.ordinal()) {
				
				TeleportHandler.teleportPlayer(player, new Position(d.getCoordinates()[0], d.getCoordinates()[1], d.getCoordinates()[2]), player.getSpellbook().getTeleportType());
			}
		}
	}

	/**
	 * Opening a tab in the teleports interface.
	 * @param client
	 * 			player.opening the tab.
	 * @param button
	 * 			Tab id being opened.
	 */
	public static void openTab(Player player, int button) {
		for (int i = 0; i < TAB_IDS.length; i++) {
			if (button == TAB_IDS[i]) {
				player.lastClickedTab = i+1;
				player.getPacketSender().sendInterface(INTERFACE_IDS[i]);
			}
		}
		switch(player.lastClickedTab) {
		case 1:
			for (TeleportTraining.Training t : TeleportTraining.Training.values()) {
				player.getPacketSender().sendTeleString(t.getTeleportName()[0], LINE_IDS[t.ordinal()][0]);
				player.getPacketSender().sendTeleString(t.getTeleportName()[1], LINE_IDS[t.ordinal()][1]);
			}
			break;
		case 2:
			for (final TeleportMinigames.Minigames m : TeleportMinigames.Minigames.values()) {
				player.getPacketSender().sendTeleString(m.getTeleportName()[0], LINE_IDS[m.ordinal()][0]);
				player.getPacketSender().sendTeleString(m.getTeleportName()[1], LINE_IDS[m.ordinal()][1]);
			}
			break;
		case 3:
			for (final TeleportBosses.Bosses b : TeleportBosses.Bosses.values()) {
				player.getPacketSender().sendTeleString(b.getTeleportName()[0], LINE_IDS[b.ordinal()][0]);
				player.getPacketSender().sendTeleString(b.getTeleportName()[1], LINE_IDS[b.ordinal()][1]);
			}
			break;
		case 4:
			for (final TeleportPlayerKilling.PlayerKilling p : TeleportPlayerKilling.PlayerKilling.values()) {
				player.getPacketSender().sendTeleString(p.getTeleportName()[0], LINE_IDS[p.ordinal()][0]);
				player.getPacketSender().sendTeleString(p.getTeleportName()[1], LINE_IDS[p.ordinal()][1]);
			}
			break;
		case 5:
			for (final TeleportSkilling.Skilling s : TeleportSkilling.Skilling.values()) {
				player.getPacketSender().sendTeleString(s.getTeleportName()[0], LINE_IDS[s.ordinal()][0]);
				player.getPacketSender().sendTeleString(s.getTeleportName()[1], LINE_IDS[s.ordinal()][1]);
			}
			break;
		case 6:
			for (final TeleportDonator.Donator d : TeleportDonator.Donator.values()) {
				player.getPacketSender().sendTeleString(d.getTeleportName()[0], LINE_IDS[d.ordinal()][0]);
				player.getPacketSender().sendTeleString(d.getTeleportName()[1], LINE_IDS[d.ordinal()][1]);
			}
			break;
		}
	}



}
