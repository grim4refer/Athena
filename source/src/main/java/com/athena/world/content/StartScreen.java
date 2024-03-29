package com.athena.world.content;

import com.athena.model.GameMode;
import com.athena.model.Item;
import com.athena.world.World;
import com.athena.world.content.clan.ClanChatManager;
import com.athena.world.entity.impl.player.Player;

/**
 * Start screen functionality.
 * 
 * @author Joey wijers
 */
public class StartScreen {
	public enum GameModes {
		NORMAL("Normal", 52761,-12780, 1, 0, new Item[] { 
				new Item(2441, 10),
				new Item(2437, 10),
				new Item(3025, 10),
				new Item(392, 200),
				new Item(5022, 1),
				new Item(11537, 1),
				new Item(11535, 1),
				new Item(11536, 1),
				(new Item(11538,1)),
				(new Item(11539,1)),
				(new Item (11543, 1)),
				(new Item(11540,1)),
				(new Item(11541,1)),
				(new Item(11542,1)),
				(new Item(11544,1)),
				(new Item (843, 1)),
				(new Item (884, 500)),
				(new Item (579, 1)),
				(new Item (577, 1)),
				(new Item (1011, 1)),
				(new Item (1381, 1)),
				(new Item (558, 1000)),
				(new Item (557, 1000)),
				(new Item (555, 1000)),
				(new Item (554, 1000)),
				(new Item(18702, 1)),
				(new Item (20979, 1)),
				(new Item (20978, 1))},
				"As a normal player you will be able to", "play the game without any restrictions.", "", "", "", "", ""),
		IRONMAN("  Ironman", 52762, -12779, 1, 1, new Item [] {
				new Item(2441, 10),
				new Item(2437, 10),
				new Item(3025, 10),
				new Item(392, 200),
				new Item(5022, 1),
				new Item(11537, 1),
				new Item(11535, 1),
				new Item(11536, 1),
				(new Item(11538,1)),
				(new Item(11539,1)),
				(new Item (11543, 1)),
				(new Item(11540,1)),
				(new Item(11541,1)),
				(new Item(11542,1)),
				(new Item(11544,1)),
				(new Item (843, 1)),
				(new Item (884, 500)),
				(new Item (579, 1)),
				(new Item (577, 1)),
				(new Item (1011, 1)),
				(new Item (1381, 1)),
				(new Item (558, 1000)),
				(new Item (557, 1000)),
				(new Item (555, 1000)),
				(new Item (554, 1000)),
				(new Item(18702, 1)),
				(new Item (20979, 1)),
				(new Item (20978, 1))},
				"Play Athena as an Iron man.", "You will be restricted from trading, staking and looting items from killed players.", "You will not get a npc drop if another player has done more damage.", "You will have to rely on your starter, skilling, pvming, and shops.", "This game mode is for players that love a challenge.", "", ""),
		ULTIMATE_IRON("  Ultimate Iron", 52763, -12778, 1, 2, new Item[] {
				new Item(2441, 10),
				new Item(2437, 10),
				new Item(3025, 10),
				new Item(392, 200),
				new Item(5022, 1),
				new Item(11537, 1),
				new Item(11535, 1),
				new Item(11536, 1),
				(new Item(11538,1)),
				(new Item(11539,1)),
				(new Item (11543, 1)),
				(new Item(11540,1)),
				(new Item(11541,1)),
				(new Item(11542,1)),
				(new Item(11544,1)),
				(new Item (843, 1)),
				(new Item (884, 500)),
				(new Item (579, 1)),
				(new Item (577, 1)),
				(new Item (1011, 1)),
				(new Item (1381, 1)),
				(new Item (558, 1000)),
				(new Item (557, 1000)),
				(new Item (555, 1000)),
				(new Item (554, 1000)),
				(new Item(18702, 1)),
				(new Item (20979, 1)),
				(new Item (20978, 1))},
				"Play Athena as a Ultimate Ironman.", "In addiction to the iron man rules you cannot use banks.", "This gamemode is for the players that love the impossible.", "", "", "", "");

		private String name;
		private int stringId;
		private int checkClick;
		private int textClick;
		private int configId;
		private Item[] starterPackItems;
		private String line1;
		private String line2;
		private String line3;
		private String line4;
		private String line5;
		private String line6;
		private String line7;

		private GameModes(String name, int stringId, int checkClick, int textClick, int configId, Item[] starterPackItems, String line1, String line2, String line3, String line4, String line5, String line6, String line7) {
			this.name = name;
			this.stringId = stringId;
			this.checkClick = checkClick;
			this.textClick = textClick;
			this.configId = configId;
			this.starterPackItems = starterPackItems;
			this.line1 = line1;
			this.line2 = line2;
			this.line3 = line3;
			this.line4 = line4;
			this.line5 = line5;
			this.line6 = line6;
			this.line7 = line7;
		}
	}

	public static void open(Player player) {
		sendNames(player);
		player.getPacketSender().sendInterface(52750);
		player.selectedGameMode = GameModes.NORMAL;
		check(player, GameModes.NORMAL);
		sendStartPackItems(player, GameModes.NORMAL);
		sendDescription(player, GameModes.NORMAL);
	}

	public static void sendDescription(Player player, GameModes mode) {
		int s = 52764;
		player.getPacketSender().sendString(s, mode.line1);
		player.getPacketSender().sendString(s+1, mode.line2);
		player.getPacketSender().sendString(s+2, mode.line3);
		player.getPacketSender().sendString(s+3, mode.line4);
		player.getPacketSender().sendString(s+4, mode.line5);
		player.getPacketSender().sendString(s+5, mode.line6);
		player.getPacketSender().sendString(s+6, mode.line7);
	}

	public static void sendStartPackItems(Player player, GameModes mode) {
		final int START_ITEM_INTERFACE = 59025;
		for (int i = 0; i < 28; i++) {
			int id = -1;
			int amount = 0;
			try {
				id = mode.starterPackItems[i].getId();
				amount = mode.starterPackItems[i].getAmount();
			} catch (Exception e) {

			}
			player.getPacketSender().sendItemOnInterface(START_ITEM_INTERFACE + i, id, amount);
		}
	}

	public static boolean handleButton(Player player, int buttonId) {
		final int CONFIRM = -29767;
		if (buttonId == CONFIRM) {
			if (player.didReceiveStarter() == true) {
			
				return true;
			}//ConnectionHandler.getStarters(player.getHostAddress()) <= GameSettings.MAX_STARTERS_PER_IP
			if(!PlayerPunishment.hasRecieved1stStarter(player.getHostAddress())) {
				player.getPacketSender().sendInterfaceRemoval();
				player.setReceivedStarter(true);
				handleConfirm(player);
				addStarterToInv(player);
				//ClanChatManager.join(player, "Saint");
				player.setPlayerLocked(false);
				player.getPacketSender().sendInterface(3559);
				player.getAppearance().setCanChangeAppearance(true);
				player.setNewPlayer(false);
				PlayerPunishment.addIpToStarterList1(player.getHostAddress());
				PlayerPunishment.addIpToStarter1(player.getHostAddress());		
				World.sendMessage("<col=6600CC>[NEW PLAYER]: "+player.getUsername()+" has logged into Athena for the first time!");
				ClanChatManager.join(player, "Athena");
			}
			else if(PlayerPunishment.hasRecieved1stStarter(player.getHostAddress()) && !PlayerPunishment.hasRecieved2ndStarter(player.getHostAddress())) {
				player.getPacketSender().sendInterfaceRemoval();
				player.setReceivedStarter(true);
				handleConfirm(player);
				addStarterToInv(player);
				//ClanChatManager.join(player, "Saint");
				player.setPlayerLocked(false);
				player.getPacketSender().sendInterface(3559);
				player.getAppearance().setCanChangeAppearance(true);
				player.setNewPlayer(false);
				PlayerPunishment.addIpToStarterList2(player.getHostAddress());
				PlayerPunishment.addIpToStarter2(player.getHostAddress());			
				World.sendMessage("<col=6600CC>[NEW PLAYER]: "+player.getUsername()+" has logged into Athena for the first time!");
				ClanChatManager.join(player, "Athena");
			}
			else if(PlayerPunishment.hasRecieved1stStarter(player.getHostAddress()) && PlayerPunishment.hasRecieved2ndStarter(player.getHostAddress())) {
				//player.getPacketSender().sendInterfaceRemoval();
				//ClanChatManager.join(player, "Saint");
				player.setPlayerLocked(false);
				player.getPacketSender().sendInterface(3559);
				player.getAppearance().setCanChangeAppearance(true);
				player.setNewPlayer(false);
				player.getPacketSender().sendMessage("You've received to many starters.");
				World.sendMessage("<col=6600CC>[NEW PLAYER]: "+player.getUsername()+" has logged into Athena for the first time!");
				ClanChatManager.join(player, "Athena");
			}
			//DialogueManager.start(player, 81);
			//return true;
		}
		for (GameModes mode : GameModes.values()) {
			if (mode.checkClick == buttonId || mode.textClick == buttonId) {
				selectMode(player, mode);
				return true;
			}
		}
		return false;
		
	}
	public static void handleConfirm(Player player) {
		if (player.selectedGameMode == GameModes.IRONMAN) {
			GameMode.set(player, GameMode.IRONMAN, false);
		} else if (player.selectedGameMode == GameModes.ULTIMATE_IRON) {
			GameMode.set(player, GameMode.HARDCORE_IRONMAN, false);
		} else {
			GameMode.set(player, GameMode.NORMAL, false);
		}
	}

	public static void addStarterToInv(Player player) {
		for (Item item : player.selectedGameMode.starterPackItems) {
			player.getInventory().add(item);
		}
	}

	public static void selectMode(Player player, GameModes mode) {
		player.selectedGameMode = mode;
		check(player, mode);
		sendStartPackItems(player, mode);
		sendDescription(player, mode);
	}

	public static void check(Player player, GameModes mode) {
		for (GameModes gameMode : GameModes.values()) {
			if (player.selectedGameMode == gameMode) {
				player.getPacketSender().sendConfig(gameMode.configId, 1);
				continue;
			}
			player.getPacketSender().sendConfig(gameMode.configId, 0);
		}
	}

	public static void sendNames(Player player) {
		for (GameModes mode : GameModes.values()) {
			player.getPacketSender().sendString(mode.stringId, mode.name);
		}
	}
}
