package com.athena.world.content.combat.pvp;

import java.util.ArrayList;
import java.util.List;

import com.athena.model.Locations.Location;
import com.athena.util.Misc;
import com.athena.world.content.Artifacts;
import com.athena.world.entity.impl.player.Player;

public class PlayerKillingAttributes {

	private final Player player;
	private Player target;
	private int playerKills;
	private int playerKillStreak;
	private int playerDeaths;
	private int targetPercentage;
	private long lastPercentageIncrease;
	private int safeTimer;

	private final int WAIT_LIMIT = 2;
	private List<String> killedPlayers = new ArrayList<String>();

	public PlayerKillingAttributes(Player player) {
		this.player = player;
	}

	public void add(Player other) {

		if(other.getAppearance().getBountyHunterSkull() >= 0)
			other.getAppearance().setBountyHunterSkull(-1);

		boolean target = player.getPlayerKillingAttributes().getTarget() != null && player.getPlayerKillingAttributes().getTarget().getIndex() == other.getIndex() || other.getPlayerKillingAttributes().getTarget() != null && other.getPlayerKillingAttributes().getTarget().getIndex() == player.getIndex();
		if(target)
			killedPlayers.clear();

		if (killedPlayers.size() >= WAIT_LIMIT) {
			killedPlayers.clear();
			handleReward(other, target);
		} else {
			if (!killedPlayers.contains(other.getUsername()))
				handleReward(other, target);
			else
				player.getPacketSender().sendMessage("You were not given points because you have recently defeated " + other.getUsername() + ".");
		}

		if(target)
			BountyHunter.resetTargets(player, other, true, "You have defeated your target!");
	}

	/**
	 * Gives the player a reward for defeating his opponent
	 * @param other
	 */
	private void handleReward(Player o, boolean targetKilled) {
		if (/*!o.getSerialNumber().equals(player.getSerialNumber()) && !player.getHostAddress().equalsIgnoreCase(o.getHostAddress()) && */player.getLocation() == Location.WILDERNESS) {
			if(!killedPlayers.contains(o.getUsername()))
				killedPlayers.add(o.getUsername());
			player.getPacketSender().sendMessage(getRandomKillMessage(o.getUsername()));
			
			this.playerKills += 1;
			this.playerKillStreak +=1;
			
			player.getPointsHandler().setPkPoints(5, true);
			player.getPacketSender().sendMessage("You've recieved 5 Pk points.");
			/*if (player.getRights() == PlayerRights.PLAYER || player.getRights() == PlayerRights.YOUTUBER) {
				player.getPointsHandler().setPkPoints(GameLoader.getDay() == GameLoader.TUESDAY ? 1 * 2 : 1, true);
				player.getPacketSender().sendMessage(GameLoader.getDay() == GameLoader.TUESDAY ? "You've received 2 Pk points." : "You've received a Pk point.");
			}
			if (player.getRights() == PlayerRights.DONATOR) {
				if (Misc.getRandom(10) == 2) {
					player.getPointsHandler().setPkPoints(GameLoader.getDay() == GameLoader.TUESDAY ? 2 * 2 : 1, true);
					player.getPacketSender().sendMessage(GameLoader.getDay() == GameLoader.TUESDAY ? "You've received 4 Pk points." : "You've received 2 Pk points.");	
				} else {
				player.getPointsHandler().setPkPoints(GameLoader.getDay() == GameLoader.TUESDAY ? 1 * 2 : 1, true);
				player.getPacketSender().sendMessage(GameLoader.getDay() == GameLoader.TUESDAY ? "You've received 2 Pk points." : "You've received a Pk point.");
				}
			}
			if (player.getRights() == PlayerRights.SUPER_DONATOR || player.getRights() == PlayerRights.ADMINISTRATOR) {
				if (Misc.getRandom(7) == 2) {
					player.getPointsHandler().setPkPoints(GameLoader.getDay() == GameLoader.TUESDAY ? 2 * 2 : 1, true);
					player.getPacketSender().sendMessage(GameLoader.getDay() == GameLoader.TUESDAY ? "You've received 4 Pk points." : "You've received 2 Pk points.");	
				} else {
				player.getPointsHandler().setPkPoints(GameLoader.getDay() == GameLoader.TUESDAY ? 1 * 2 : 1, true);
				player.getPacketSender().sendMessage(GameLoader.getDay() == GameLoader.TUESDAY ? "You've received 2 Pk points." : "You've received a Pk point.");
				}
			}
			if (player.getRights() == PlayerRights.EXTREME_DONATOR || player.getRights() == PlayerRights.SUPPORT) {
				if (Misc.getRandom(5) == 2) {
					player.getPointsHandler().setPkPoints(GameLoader.getDay() == GameLoader.TUESDAY ? 2 * 2 : 1, true);
					player.getPacketSender().sendMessage(GameLoader.getDay() == GameLoader.TUESDAY ? "You've received 4 Pk points." : "You've received 2 Pk points.");	
				} else {
				player.getPointsHandler().setPkPoints(GameLoader.getDay() == GameLoader.TUESDAY ? 1 * 2 : 1, true);
				player.getPacketSender().sendMessage(GameLoader.getDay() == GameLoader.TUESDAY ? "You've received 2 Pk points." : "You've received a Pk point.");
				}
			}
			if (player.getRights() == PlayerRights.LEGENDARY_DONATOR || player.getRights() == PlayerRights.MODERATOR) {
				if (Misc.getRandom(4) == 2) {
					player.getPointsHandler().setPkPoints(GameLoader.getDay() == GameLoader.TUESDAY ? 2 * 2 : 1, true);
					player.getPacketSender().sendMessage(GameLoader.getDay() == GameLoader.TUESDAY ? "You've received 4 Pk points." : "You've received 2 Pk points.");	
				} else {
				player.getPointsHandler().setPkPoints(GameLoader.getDay() == GameLoader.TUESDAY ? 1 * 2 : 1, true);
				player.getPacketSender().sendMessage(GameLoader.getDay() == GameLoader.TUESDAY ? "You've received 2 Pk points." : "You've received a Pk point.");
				}
			}
			if (player.getRights() == PlayerRights.UBER_DONATOR || player.getRights() == PlayerRights.SECURITY_MANAGER) {
				if (Misc.getRandom(3) == 2) {
					player.getPointsHandler().setPkPoints(GameLoader.getDay() == GameLoader.TUESDAY ? 2 * 2 : 1, true);
					player.getPacketSender().sendMessage(GameLoader.getDay() == GameLoader.TUESDAY ? "You've received 4 Pk points." : "You've received 2 Pk points.");	
				} else {
				player.getPointsHandler().setPkPoints(GameLoader.getDay() == GameLoader.TUESDAY ? 1 * 2 : 1, true);
				player.getPacketSender().sendMessage(GameLoader.getDay() == GameLoader.TUESDAY ? "You've received 2 Pk points." : "You've received a Pk point.");
				}
			}*/
			
			Artifacts.handleDrops(player, o, targetKilled);
			if(player.getAppearance().getBountyHunterSkull() < 4)
				player.getAppearance().setBountyHunterSkull(player.getAppearance().getBountyHunterSkull()+1);
			player.getPointsHandler().refreshPanel();
			
			/** ACHIEVEMENTS AND LOYALTY TITLES **/
			//Achievements.doProgress(player, AchievementData.DEFEAT_10_PLAYERS);
			//Achievements.doProgress(player, AchievementData.DEFEAT_30_PLAYERS);
			}
		}

	public List<String> getKilledPlayers() {
		return killedPlayers;
	}

	public void setKilledPlayers(List<String> list) {
		killedPlayers = list;
	}

	/**
	 * Gets a random message after killing a player
	 * @param killedPlayer 		The player that was killed
	 */
	public static String getRandomKillMessage(String killedPlayer){
		int deathMsgs = Misc.getRandom(8);
		switch(deathMsgs) {
		case 0: return "With a crushing blow, you defeat "+killedPlayer+".";
		case 1: return "It's humiliating defeat for "+killedPlayer+".";
		case 2: return ""+killedPlayer+" didn't stand a chance against you.";
		case 3: return "You've defeated "+killedPlayer+".";
		case 4: return ""+killedPlayer+" regrets the day they met you in combat.";
		case 5: return "It's all over for "+killedPlayer+".";
		case 6: return ""+killedPlayer+" falls before you might.";
		case 7: return "Can anyone defeat you? Certainly not "+killedPlayer+".";
		case 8: return "You were clearly a better fighter than "+killedPlayer+".";
		}
		return null;
	}

	public int getPlayerKills() {
		return playerKills;
	}

	public void setPlayerKills(int playerKills) {
		this.playerKills = playerKills;
	}

	public int getPlayerKillStreak() {
		return playerKillStreak;
	}

	public void setPlayerKillStreak(int playerKillStreak) {
		this.playerKillStreak = playerKillStreak;
	}

	public int getPlayerDeaths() {
		return playerDeaths;
	}

	public void setPlayerDeaths(int playerDeaths) {
		this.playerDeaths = playerDeaths;
	}

	public Player getTarget() {
		return target;
	}

	public void setTarget(Player target) {
		this.target = target;
	}

	public int getTargetPercentage() {
		return targetPercentage;
	}

	public void setTargetPercentage(int targetPercentage) {
		this.targetPercentage = targetPercentage;
	}

	public long getLastTargetPercentageIncrease() {
		return lastPercentageIncrease;
	}

	public void setLastTargetPercentageIncrease(long lastPercentageIncrease) {
		this.lastPercentageIncrease = lastPercentageIncrease;
	}

	public int getSafeTimer() {
		return safeTimer;
	}

	public void setSafeTimer(int safeTimer) {
		this.safeTimer = safeTimer;
	}
}
