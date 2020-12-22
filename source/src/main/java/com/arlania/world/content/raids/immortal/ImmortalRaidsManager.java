/**
 * 
 */
package com.arlania.world.content.raids.immortal;

import java.util.*;

import com.arlania.model.GameObject;
import com.arlania.model.Position;
import com.arlania.model.container.impl.Equipment;
import com.arlania.world.content.CustomObjects;
import com.arlania.world.content.transportation.TeleportHandler;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.player.Player;

/**
 * @author Hennessy
 *
 *         Jul 29, 2019 - 5:53:29 PM
 */
public class ImmortalRaidsManager {

	public static Map<String, ImmortalRaid> ACTIVE_SESSIONS = new HashMap<String, ImmortalRaid>();

	public static List<String> PASSWORDS = new ArrayList<String>();

	private static int[] itemIds = { 7482 };

	public static boolean itemNotAllowed(Player player) {
		for (int i : itemIds)
			if (player.getInventory().contains(i))
				return true;
		return false;
	}

	public static boolean equipmentNotAllowed(Player p) {// TODO
		Equipment equip = p.getEquipment();
		for (int i = 0; i < equip.getItems().length; i++) {
			for (int x = 0; x < itemIds.length; x++) {
				if (equip.get(i) != null) {
					if (equip.get(i).getId() == itemIds[x])
						return true;
				}
			}
			return false;
		}

		return false;
	}

	public static ImmortalRaid getSession(String raidPassword) {
		return ACTIVE_SESSIONS.get(raidPassword);
	}

	public static void endCurrentSessions() {// TODO for updates ect
		for (int i = 0; i < PASSWORDS.size(); i++) {
			if (ACTIVE_SESSIONS.get(PASSWORDS.get(i)) != null)
				ACTIVE_SESSIONS.get(PASSWORDS.get(i)).endRaid(true);
		}
	}

	public static int getActiveSessions() {
		return ACTIVE_SESSIONS.size();
	}

	public static void handleNPCInteraction(Player player, int optionId) {

		if (player.currentRaidSession != null) {
			player.sendMessage("You are already in an active raids session!");
			return;
		}

		player.setAttribute("raidsCreation", false);
		player.setAttribute("raidsJoin", false);

		switch (optionId) {

		case 1:
		case 2:
			player.setInputHandling(new ImmortalRaidsPassword());
			player.getPacketSender().sendEnterInputPrompt(optionId == 1 ? "Create a Password for your raids session!"
					: "Enter a password to join an Active Raids Session");
			player.setAttribute(optionId == 1 ? "raidsCreation" : "raidsJoin", true);
			return;
		}
		return;

	}

	public static boolean handleObjectInteraction(Player player, GameObject object) {

		ImmortalRaid session = ACTIVE_SESSIONS.get(player.raidsPassword);
		
		if (session == null)
			return false;
		
		
		switch (object.getId()) {
		
		case 41371:
			CustomObjects.forceRemoveObject(object);
			session.destructionOrbSpawned = false;
			session.groupMessage(player.getUsername()+" has destroyed the destruction orb!"); 
			CustomObjects.deleteObject(player, object);
			return true;

		case 14_000:
			session.groupMessage(player.getUsername()+" has taken the Holy Grail!"); 
			CustomObjects.forceRemoveObject(object);
			player.raidsHeal(7000);
			player.sendMessage("As you drink from the grail it gives you 7,000 bonus hitpoints!");
			CustomObjects.deleteObject(player, object);
			return true;
		}
		return false;
	}

	public static void process() {
		for (int i = 0; i < PASSWORDS.size(); i++) {
			if (PASSWORDS.get(i) != null && ACTIVE_SESSIONS.get(PASSWORDS.get(i)) != null)
				ACTIVE_SESSIONS.get(PASSWORDS.get(i)).process();
		}
	}

	public static void createSession(Player player, String password) {
		ImmortalRaid session = getSession(password);

		if (session != null) {
			player.sendMessage("Password unacceptable please user another!");
			return;
		}
		ImmortalRaid newSession = new ImmortalRaid(player, password);
		player.currentRaidSession = newSession;
		ACTIVE_SESSIONS.put(password, newSession);
		PASSWORDS.add(password);
		player.currentRaidSession.teleportToInstance(player, false);
	}

	public static void joinSession(Player player, String password) {
		ImmortalRaid session = getSession(password);

		if (session == null) {
			player.sendMessage("No raid exists that is using the password: " + password);
			return;
		}
		if (session.getMembers().size() >= 4) {
			player.sendMessage("Sorry, this instance is currently full!");
			return;
		}
		if (session.raidStarted()) {
			player.sendMessage("The raid has already started! you're too late!");
			return;
		}
		if (itemNotAllowed(player) || equipmentNotAllowed(player)) {
			player.sendMessage("You're " + (equipmentNotAllowed(player) ? "wearing" : "carrying")
					+ " an item that you are NOT allowed to bring to this raid!");
			return;
		}
		player.currentRaidSession = session;
		player.currentRaidSession.teleportToInstance(player, true);
	}
	
	public static void handleLogout(Player player) {
		
		ImmortalRaid session = getSession(player.raidsPassword);

		if (session == null)
			return;
		
		session.getMembers().remove(player);
		session.groupMessage(player.getUsername()+" has left the current session! members left: "+session.getMembers().size());
		TeleportHandler.teleportPlayer(player, new Position(2206, 3545, 0), player.getSpellbook().getTeleportType());
	}

	public static void handleNPCDeath(NPC npc, Player killer) {

		ImmortalRaid session = getSession(killer.raidsPassword);

		if (session == null)
			return;

		session.endRaid(false);
		
		ACTIVE_SESSIONS.remove(killer.raidsPassword);
		PASSWORDS.remove(killer.raidsPassword);
		
	}

	public static void handleDeath(Player player) {

		ImmortalRaid session = getSession(player.raidsPassword);

		if (session == null)
			return;

		session.getMembers().remove(player);
		player.raidsPassword = null;
	    player.currentRaidSession = null;
		player.sendMessage("You have been removed from your raids session! better luck next time!");
		session.groupMessage(player.getUsername() + " has died! members remaining: " + session.getMembers().size());
	}
}
