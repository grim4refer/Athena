package com.athena.world.content;

import com.athena.model.GameMode;
import com.athena.util.Misc;
import com.athena.util.Stopwatch;
import com.athena.world.World;
import com.athena.world.entity.impl.player.Player;

import java.util.Comparator;
import java.util.concurrent.CopyOnWriteArrayList;

public class PlayersOnlineInterface {

	private static Stopwatch lastResort = new Stopwatch();
	private final static CopyOnWriteArrayList<Player> PLAYERS_ONLINE_LIST = new CopyOnWriteArrayList<Player>();

	public static void add(Player player) {
		PLAYERS_ONLINE_LIST.add(player);
	}

	public static void remove(Player player) {
		PLAYERS_ONLINE_LIST.remove(player);
	}

	private static void resort() {
		if(!lastResort.elapsed(1000)) {
			return;
		}
		lastResort.reset();
		PLAYERS_ONLINE_LIST.sort(new Comparator<Player>() {
			@Override
			public int compare(Player arg0, Player arg1) {
				int value1 = getValue(arg0);
				int value2 = getValue(arg1);
				return Integer.compare(value2, value1);
			}
		});
	}

	private static void clearInterface(Player player) {
		for(int i = 57042; i < 57141; i++) {
			player.getPacketSender().sendString(i, "");
		}
		int i = 57008;
		player.getPacketSender().sendString(i++, "Name:");
		player.getPacketSender().sendString(i++, "Rank:");
		player.getPacketSender().sendString(i++, "Time Played:");
		player.getPacketSender().sendString(i++, "Claimed:");
		player.getPacketSender().sendString(i++, "Game Mode:");
		player.getPacketSender().sendString(i++, "Combat Level:");
		player.getPacketSender().sendString(i++, "Total Level:");
		player.getPacketSender().sendString(i++, "Slayer Points:");
		player.getPacketSender().sendString(i++, "Voting Points:");
		player.getPacketSender().sendString(i++, "Commendations:");
		player.getPacketSender().sendString(i++, "Dung Points:");
		player.getPacketSender().sendString(i++, "Pk Points:");
		player.getPacketSender().sendString(i++, "Wild Streak:");
		player.getPacketSender().sendString(i++, "Wild Kills:");
		player.getPacketSender().sendString(i++, "Wil Deaths:");
		player.getPacketSender().sendString(i++, "Arena Victories:");
		player.getPacketSender().sendString(i++, "Arena Losses:");
	}

	private static void sendInterfaceData(Player player) {
		int child = 57042;
		int fakeCount = World.getPlayers().size();
		for(int i = 0; i < fakeCount; i++) {
			if(i >= PLAYERS_ONLINE_LIST.size()) {
				player.getPacketSender().sendString(child, "   N/A");
				child++;
				continue;
			}
			Player p = PLAYERS_ONLINE_LIST.get(i);
			if(p == null)
				continue;
			int rankId = p.getRights().ordinal();
			if(rankId == 0) {
				if(p.getGameMode() == GameMode.IRONMAN) {
					rankId = 33;
				} else if(p.getGameMode() == GameMode.HARDCORE_IRONMAN) {
					rankId = 32;
				}
			}
			player.getPacketSender().sendString(child, ""+(rankId > 0 ? "<img="+rankId+">" : "  ")+""+p.getUsername());
			child++;
		}
	}

	public static void showInterface(Player player) {
		resort();
		clearInterface(player);
		sendInterfaceData(player);
		player.getPacketSender().sendString(57003, "Players:  @gre@"+(int)(World.getPlayers().size() * 1.3)+"").sendInterface(57000);
	}

	private static void updateInterface(Player player, int index) {
		if(index >= PLAYERS_ONLINE_LIST.size() || PLAYERS_ONLINE_LIST.get(index) == null) {
			showInterface(player);
			player.getPacketSender().sendMessage("That player is currently unavailable.");
			return;
		}
		Player player2 = PLAYERS_ONLINE_LIST.get(index);
		player.setPlayerViewingIndex(index);
		player.getPacketSender().sendString(57008, "Name: @whi@"+player2.getUsername()).sendString(57009, "Rank: @whi@"+Misc.formatText(player2.getRights().name().toLowerCase())).
		sendString(57010, "Time Played: @whi@"+Misc.getHoursPlayed((player2.getTotalPlayTime() + player2.getRecordedLogin().elapsed()))).sendString(57011, "Claimed: @whi@$"+player2.getAmountDonated()).
		sendString(57012, "Game Mode: @whi@"+Misc.formatText(player2.getGameMode().name().toLowerCase())).sendString(57013, "Combat Level: @whi@"+player2.getSkillManager().getCombatLevel()).
		sendString(57014, "Total Level: @whi@ "+player2.getSkillManager().getTotalLevel()).sendString(57015, "Slayer Points: @whi@"+player2.getPointsHandler().getSlayerPoints()).
		sendString(57016, "Voting Points: @whi@"+player2.getPointsHandler().getVotingPoints()).sendString(57017, "Commendations: @whi@"+player2.getPointsHandler().getCommendations()).
		sendString(57018, "Dung Tokens: @whi@"+player2.getPointsHandler().getDungeoneeringTokens()).sendString(57019, "Pk Points: @whi@"+player2.getPointsHandler().getPkPoints()).
		sendString(57020, "Wild Streak: @whi@"+player2.getPlayerKillingAttributes().getPlayerKillStreak()).sendString(57021, "Player Kills: @whi@"+player2.getPlayerKillingAttributes().getPlayerKills()).
		sendString(57022, "Wild Deaths: @whi@"+player2.getPlayerKillingAttributes().getPlayerDeaths()).sendString(57023, "Arena Victories: @whi@"+player2.getDueling().arenaStats[0]).
		sendString(57024, "Arena Losses: @whi@"+player2.getDueling().arenaStats[1]);
	}

	public static boolean handleButton(Player player, int button) {
		if(button >= -8494 && button <= -8407) {
			int index = (button - (-8494));
			updateInterface(player, index);
			return true;
		}
		if(button == -8511 || button == -8508) {
			Player p = player.getPlayerViewingIndex() < PLAYERS_ONLINE_LIST.size() ? PLAYERS_ONLINE_LIST.get(player.getPlayerViewingIndex()) : null;
			if(p == null) {
				player.getPacketSender().sendMessage("Please select an active player.");
				return true;
			}
			player.getPacketSender().sendString(button == -8511 ? 57025 : 57028, p.getUsername());
			return true;
		}
		return false;
	}

	private static int getValue(Player p) {
		int value = switch (p.getRights()) {
			case PLAYER -> 0;
			case ADMINISTRATOR -> 11;
			case DONATOR -> 3;
			case DELUXE_DONATOR -> 13;
			case UBER_DONATOR -> 7;
			case EXTREME_DONATOR -> 5;
			case MODERATOR -> 10;
			case OWNER -> 12;
			case LEGENDARY_DONATOR -> 6;
			case SUPER_DONATOR -> 4;
			case SUPPORT -> 9;
			case YOUTUBER -> 8;
			default -> 0;
		};
		if(value == 0) {
			if(p.getGameMode() == GameMode.IRONMAN) {
				value = 1;
			} else if(p.getGameMode() == GameMode.HARDCORE_IRONMAN) {
				value = 2;
			}
		}
		return value;
	}
}
