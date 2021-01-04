package com.athena.world.content;

import com.athena.model.definitions.NPCDrops;
import com.athena.util.Misc;
import com.athena.world.World;

import com.athena.world.content.skill.impl.slayer.SlayerTasks;
import com.athena.world.entity.impl.player.Player;


public class PlayerPanel {
	private static int FIRST_STRING = 39159;
	private static int LAST_STRING = 39210;

	public static void handleSwitch(Player player, int index, boolean fromCurrent) {
		if (!fromCurrent) {
			resetStrings(player);
		}
		player.currentPlayerPanelIndex = index;
		switch (index) {
			case 1:
				refreshPanel(player); // first tab, cba rename just yet.
				break;

			case 2:
				sendSecondTab(player);
				break;
			case 3:
				sendThirdTab(player);
				break;
			case 4:
				sendForthTab(player);
				break;
		}
	}

	public static void refreshCurrentTab(Player player) {
		handleSwitch(player, player.clickindex, true);
	}

	public static void refreshPanel(Player player) {

		if (player.clickindex != 1) { // now it would update the other tab, if this is not the current tab
			refreshCurrentTab(player);
			return;
		}

		String[] information = {
				"<img=3>",
				" World & Events",
				"- Players Online: @whi@" + (int) (0 + World.getPlayers().size()),
				"- Well of Goodwill: @whi@"+(WellOfGoodwill.isActive() ? "On" : "Off")+"",
				"- Bonus XP: @whi@"+(player.getMinutesBonusExp() == -1 ? "0" : Misc.format(player.getMinutesBonusExp()))+" @or5@minutes left",
				"- Crashed Star: @whi@" + (ShootingStar.CRASHED_STAR == null ? "Off" : ShootingStar.CRASHED_STAR.getStarLocation().playerPanelFrame),
				"- Evil Tree: @whi@" + (EvilTrees.SPAWNED_TREE == null ? "Off" : EvilTrees.SPAWNED_TREE.getTreeLocation().playerPanelFrame),

		};

		for (int i = 0; i < information.length; i++)
			player.getPacketSender().sendString(38354 + i, information[i]);

		information = new String[] {
				"<img=4>",
				" Player Information",
				"- Mode: @whi@" + Misc.capitalizeString(player.getGameMode().toString().toLowerCase().replace("_", " ")),
				"- Amount Donated: @whi@" + player.getAmountDonated(),
				"- Donator Points: @whi@" + player.getPointsHandler().getDonationPoints(),
				"- Time Played: @whi@" + Misc.getTimePlayed((player.getTotalPlayTime() + player.getRecordedLogin().elapsed())),
				"- Droprate Bonus: @whi@"+NPCDrops.getDroprate(player)+"%",
				"- Double Drop Bonus: @whi@" + NPCDrops.getDoubleDr(player) + " %",
				"- Pk Points: @whi@" + player.getPointsHandler().getPkPoints(),
				"- Wilderness Killstreak: @whi@" + player.getPlayerKillingAttributes().getPlayerKillStreak(),
				"- Wilderness Kills: @whi@" + player.getPlayerKillingAttributes().getPlayerKills(),
				"- Wilderness Deaths: @whi@" + player.getPlayerKillingAttributes().getPlayerDeaths(),
				"",
				"",
				"",
				"",
				"",
				"",
				"",
				"",
				"",
				"",
				"",
				"",
		};

		for (int i = 0; i < information.length; i++)
			player.getPacketSender().sendString(38469 + i, information[i]);

		information = new String[] {
				"<img=4>",
				" Points & Statistics",
				"- Boss Points: @whi@" + player.getBossPoints(),
				"- Prestige Points: @whi@" + player.getPointsHandler().getPrestigePoints(),
				"- Commendations: @whi@" + player.getPointsHandler().getCommendations(),
				"- Loyalty Points: @whi@" + (int) player.getPointsHandler().getLoyaltyPoints(),
				"- Dung. Tokens: @whi@" + player.getPointsHandler().getDungeoneeringTokens(),
				"- Voting Points: @whi@" + player.getPointsHandler().getVotingPoints(),
				"- Arena Victories: @whi@" + player.getDueling().arenaStats[0],
				"- Arena Points: @whi@" + player.getDueling().arenaStats[1],
				"",
				"",
		};

		for (int i = 0; i < information.length; i++)
			player.getPacketSender().sendString(38595 + i, information[i]);

		information = new String[] {
				"<img=4>",
				" Slayer Information",
				"- Slayer Points: @whi@" + player.getPointsHandler().getSlayerPoints(),
				"- Master: @whi@" + Misc.formatText(player.getSlayer().getSlayerMaster().toString().toLowerCase().replaceAll("_", " ")),
				"- Task: @whi@" + (player.getSlayer().getSlayerTask() == SlayerTasks.NO_TASK ? "N/A" : Misc.formatText(player.getSlayer().getSlayerTask().toString().toLowerCase().replaceAll("_", " "))),
				"- Task Streak: @whi@" + player.getSlayer().getTaskStreak(),
				"- Task Amount: @whi@" + player.getSlayer().getAmountToSlay(),
				"- Duo Partner: @whi@" + (player.getSlayer().getDuoPartner() == null ? "N/A" : player.getSlayer().getDuoPartner()),
		};

		for (int i = 0; i < information.length; i++)
			player.getPacketSender().sendString(38726 + i, information[i]);
	}

	private static void sendSecondTab(Player player) {

		String[] Messages = new String[] { "  ", "<img=28>@lre@Player Information", "",
				"@lre@Mode:  @whi@"
						+ Misc.capitalizeString(player.getGameMode().toString().toLowerCase().replace("_", " ")),
				"@lre@Claimed: @whi@$" + player.getAmountDonated(),
				"@lre@Donator Points: @whi@" + player.getPointsHandler().getDonationPoints(),

				"@lre@Time played:  @whi@"
						+ Misc.getTimePlayed((player.getTotalPlayTime() + player.getRecordedLogin().elapsed())),
				"@lre@Droprate bonus: @whi@" + NPCDrops.getDroprate(player) + " %",
				"@lre@Double drop bonus: @whi@" + NPCDrops.getDoubleDr(player) + " %",
				"@lre@Pk Points: @whi@" + player.getPointsHandler().getPkPoints(),
				"@lre@Wilderness Killstreak: @whi@" + player.getPlayerKillingAttributes().getPlayerKillStreak(),
				"@lre@Wilderness Kills: @whi@" + player.getPlayerKillingAttributes().getPlayerKills(),
				"@lre@Wilderness Deaths: @whi@" + player.getPlayerKillingAttributes().getPlayerDeaths(), "",
		};

		for (int i = 0; i < Messages.length; i++) {
			if (i + FIRST_STRING > LAST_STRING) {
				System.out.println("PlayerPanel(" + player.getUsername() + "): " + i + " is larger than max string: "
						+ LAST_STRING + ". Breaking.");
				break;
			}

			player.getPacketSender().sendString(i + FIRST_STRING, Messages[i]);

		}

	}

	private static void sendThirdTab(Player player) {

		String[] Messages = new String[] { "  ", "<img=17>@lre@Points & Statistics", "",
				"@lre@Prestige Points: @whi@" + player.getPointsHandler().getPrestigePoints(),
				"@lre@Commendations: @whi@ " + player.getPointsHandler().getCommendations(),
				"@lre@Loyalty Points: @whi@" + (int) player.getPointsHandler().getLoyaltyPoints(),
				"@lre@Dung. Tokens: @whi@ " + player.getPointsHandler().getDungeoneeringTokens(),
				"@lre@Voting Points: @whi@ " + player.getPointsHandler().getVotingPoints(),
				"@lre@Slayer Points: @whi@" + player.getPointsHandler().getSlayerPoints(),
				"@lre@Pk Points: @whi@" + player.getPointsHandler().getPkPoints(),

				"@lre@Arena Victories: @whi@" + player.getDueling().arenaStats[0],
				"@lre@Arena Points: @whi@" + player.getDueling().arenaStats[1],

		};

		for (int i = 0; i < Messages.length; i++) {
			if (i + FIRST_STRING > LAST_STRING) {
				System.out.println("PlayerPanel(" + player.getUsername() + "): " + i + " is larger than max string: "
						+ LAST_STRING + ". Breaking.");
				break;
			}

			player.getPacketSender().sendString(i + FIRST_STRING, Messages[i]);

		}

	}

	private static void sendForthTab(Player player) {

		String[] Messages = new String[] { " ", "<img=15>@lre@Slayer Information", "",
				"@lre@Slayer Points: @whi@" + player.getPointsHandler().getSlayerPoints(),

				"@lre@Master:  @whi@" + Misc
						.formatText(player.getSlayer().getSlayerMaster().toString().toLowerCase().replaceAll("_", " ")),
				(player.getSlayer().getSlayerTask() == SlayerTasks.NO_TASK
						? "@lre@Task:  @red@" + Misc.formatText(
						player.getSlayer().getSlayerTask().toString().toLowerCase().replaceAll("_", " "))
						: "@lre@Task:  @whi@" + Misc.formatText(
						player.getSlayer().getSlayerTask().toString().toLowerCase().replaceAll("_", " "))
						+ "s"),

				"@lre@Task Streak:  @whi@" + player.getSlayer().getTaskStreak(),

				"@lre@Task Amount:  @whi@" + player.getSlayer().getAmountToSlay(),
				(player.getSlayer().getDuoPartner() != null
						? "@lre@Duo Partner:  @whi@" + player.getSlayer().getDuoPartner()
						: "@lre@Duo Partner:  @whi@N/A"),

				" ",

		};

		for (int i = 0; i < Messages.length; i++) {
			if (i + FIRST_STRING > LAST_STRING) {
				System.out.println("PlayerPanel(" + player.getUsername() + "): " + i + " is larger than max string: "
						+ LAST_STRING + ". Breaking.");
				break;
			}

			player.getPacketSender().sendString(i + FIRST_STRING, Messages[i]);

		}

	}

	private static void resetStrings(Player player) {
		for (int i = FIRST_STRING; i < LAST_STRING; i++) {
			player.getPacketSender().sendString(i, "");
		}
	}
}