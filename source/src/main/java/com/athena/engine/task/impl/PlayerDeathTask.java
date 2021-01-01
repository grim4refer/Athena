package com.athena.engine.task.impl;

import java.util.ArrayList;

import java.util.concurrent.CopyOnWriteArrayList;

import com.athena.GameSettings;
import com.athena.engine.task.Task;
import com.athena.model.Animation;
import com.athena.model.Flag;
import com.athena.model.GameMode;
import com.athena.model.GroundItem;
import com.athena.model.Item;
import com.athena.model.PlayerRights;
import com.athena.model.Position;
import com.athena.model.Skill;
import com.athena.model.Locations.Location;
import com.athena.util.Misc;
import com.athena.util.RandomUtility;
import com.athena.world.World;
import com.athena.world.content.ItemsKeptOnDeath;
import com.athena.world.content.minigames.impl.FreeForAll;
import com.athena.world.content.raids.immortal.ImmortalRaidsManager;
import com.athena.world.entity.impl.GroundItemManager;
import com.athena.world.entity.impl.npc.NPC;
import com.athena.world.entity.impl.player.Player;

/**
 * Represents a player's death task, through which the process of dying is handled,
 * the animation, dropping items, etc.
 * 
 * @author relex lawl, redone by Gabbe.
 */

public class PlayerDeathTask extends Task {

	/**
	 * The PlayerDeathTask constructor.
	 * @param player	The player setting off the task.
	 */
	public PlayerDeathTask(Player player) {
		super(1, player, false);
		this.player = player;
	}

	private Player player;
	private int ticks = 5;
	private boolean dropItems = false;
	Position oldPosition;
	Location loc;
	ArrayList<Item> itemsToKeep = null; 
	NPC death;

	@Override
	public void execute() {
		if(player == null) {
			stop();
			return;
		}
		try {
			switch (ticks) {
			case 5:
				player.getPacketSender().sendInterfaceRemoval();
				player.getMovementQueue().setLockMovement(true).reset();
				
				break;
			case 3:
				player.performAnimation(new Animation(0x900));
				player.getPacketSender().sendMessage("Oh dear, you are dead!");
				//this.death = getDeathNpc(player);
				break;
			case 1:
				this.oldPosition = player.getPosition().copy();
				this.loc = player.getLocation();
				if (player.inFFA) {
					Player killer = player.getCombatBuilder().getKiller(true);
					FreeForAll.leaveGame(player);
					killer.getSkillManager().setCurrentLevel(Skill.CONSTITUTION, killer.getSkillManager().getMaxLevel(Skill.CONSTITUTION));
					killer.setSpecialPercentage(100);
				}
				if(loc != Location.DUNGEONEERING && !player.inFFA && loc != Location.PEST_CONTROL_GAME && loc != Location.DUEL_ARENA && loc != Location.FREE_FOR_ALL_ARENA && loc != Location.FREE_FOR_ALL_WAIT && loc != Location.SOULWARS && loc != Location.FIGHT_PITS && loc != Location.FIGHT_PITS_WAIT_ROOM && loc != Location.FIGHT_CAVES && loc != Location.RECIPE_FOR_DISASTER && loc != Location.GRAVEYARD) {
					Player killer = player.getCombatBuilder().getKiller(true);
					if(player.getUsername().equalsIgnoreCase("Arlania") || player.getRights().equals(PlayerRights.OWNER))
						dropItems = false;
					if(loc == Location.WILDERNESS) {
						if(killer != null && (killer.getRights().equals(PlayerRights.OWNER) || killer.getRights().equals(PlayerRights.PLAYER) || killer.getRights().equals(PlayerRights.DONATOR) || killer.getRights().equals(PlayerRights.SUPER_DONATOR) || killer.getRights().equals(PlayerRights.EXTREME_DONATOR) || killer.getRights().equals(PlayerRights.LEGENDARY_DONATOR) || killer.getRights().equals(PlayerRights.UBER_DONATOR) || killer.getRights().equals(PlayerRights.DELUXE_DONATOR) || killer.getRights().equals(PlayerRights.ADMINISTRATOR) || killer.getRights().equals(PlayerRights.MODERATOR) || killer.getRights().equals(PlayerRights.SUPPORT) || killer.getRights().equals(PlayerRights.YOUTUBER)))
							dropItems = false;
					}
					if(loc != Location.WILDERNESS) {
						dropItems = false;
					}
					if(killer != null) {
						if(killer.getRights().equals(PlayerRights.OWNER) || killer.getRights().equals(PlayerRights.PLAYER) || killer.getRights().equals(PlayerRights.DONATOR) || killer.getRights().equals(PlayerRights.SUPER_DONATOR) || killer.getRights().equals(PlayerRights.EXTREME_DONATOR) || killer.getRights().equals(PlayerRights.LEGENDARY_DONATOR) || killer.getRights().equals(PlayerRights.UBER_DONATOR) || killer.getRights().equals(PlayerRights.DELUXE_DONATOR) || killer.getRights().equals(PlayerRights.ADMINISTRATOR) || killer.getRights().equals(PlayerRights.MODERATOR) || killer.getRights().equals(PlayerRights.SUPPORT) || killer.getRights().equals(PlayerRights.YOUTUBER)) {
							dropItems = false;
						}
					}
					
					if (player.currentRaidSession != null) {
						ImmortalRaidsManager.handleDeath(player);
						dropItems = false;
					}
					boolean spawnItems = loc != Location.NOMAD && !(loc == Location.GODWARS_DUNGEON && player.getMinigameAttributes().getGodwarsDungeonAttributes().hasEnteredRoom());
					if(dropItems) {
						itemsToKeep = ItemsKeptOnDeath.getItemsToKeep(player);
						final CopyOnWriteArrayList<Item> playerItems = new CopyOnWriteArrayList<>();
						playerItems.addAll(player.getInventory().getValidItems());
						playerItems.addAll(player.getEquipment().getValidItems());
						final Position position = player.getPosition();
						for (Item item : playerItems) {
							if(!item.tradeable() || itemsToKeep.contains(item)) {
								if(!itemsToKeep.contains(item)) {
									itemsToKeep.add(item);
								}
								continue;
							}
							if(spawnItems) {
								if(item.getId() > 0 && item.getAmount() > 0) {
									GroundItemManager.spawnGroundItem((killer != null && killer.getGameMode() == GameMode.NORMAL ? killer : player), new GroundItem(item, position, killer != null ? killer.getUsername() : player.getUsername(), player.getHostAddress(), false, 150, true, 150));
								}
							}
						}
						if (killer != null) {
							killer.getPlayerKillingAttributes().add(player);
							player.getPlayerKillingAttributes()
									.setPlayerDeaths(player.getPlayerKillingAttributes().getPlayerDeaths() + 1);
							player.getPlayerKillingAttributes().setPlayerKillStreak(0);
							player.getPointsHandler().refreshPanel();
						}
						player.getInventory().resetItems().refreshItems();
						player.getEquipment().resetItems().refreshItems();
					}
				} else
					dropItems = false;
				player.getPacketSender().sendInterfaceRemoval();
				player.setEntityInteraction(null);
				player.getMovementQueue().setFollowCharacter(null);
				player.getCombatBuilder().cooldown(false);
				player.setTeleporting(false);
				player.setWalkToTask(null);
				player.getSkillManager().stopSkilling();
				break;
				
			case 0:
				if(dropItems) {
					if(player.getGameMode() == GameMode.HARDCORE_IRONMAN) {
						//GameMode.set(player, player.getGameMode(), true);
					} else { 
						if(itemsToKeep != null) {
							for(Item it : itemsToKeep) {
								player.getInventory().add(it.getId(), 1);
							}
							itemsToKeep.clear();
						}
					}
				}
				if(death != null) {
					World.deregister(death);
				}
				player.restart();
				player.getUpdateFlag().flag(Flag.APPEARANCE);
				loc.onDeath(player);
				if(loc != Location.DUNGEONEERING) {
					if(player.getPosition().equals(oldPosition))
						player.moveTo(GameSettings.DEFAULT_POSITION.copy());
				}
				player = null;
				oldPosition = null;
				stop();
				break;
			}
			
			ticks--;
		} catch(Exception e) {
			setEventRunning(false);
			e.printStackTrace();
			if(player != null) {
				player.moveTo(GameSettings.DEFAULT_POSITION.copy());
				player.setConstitution(player.getSkillManager().getMaxLevel(Skill.CONSTITUTION));
				
			
			}	
		}
	}


	public static NPC getDeathNpc(Player player) {
		NPC death = new NPC(2862, new Position(player.getPosition().getX() + 1, player.getPosition().getY() + 1));
		World.register(death);
		death.setEntityInteraction(player);
		death.performAnimation(new Animation(401));
		death.forceChat(randomDeath(player.getUsername()));
		return death;
	}
	

	public static String randomDeath(String name) {
		return switch (RandomUtility.getRandom(8)) {
			case 0 -> "There is no escape, " + Misc.formatText(name) + "...";
			case 1 -> "Muahahahaha!";
			case 2 -> "You belong to me!";
			case 3 -> "Beware mortals, " + Misc.formatText(name) + " travels with me!";
			case 4 -> "Your time here is over, " + Misc.formatText(name) + "!";
			case 5 -> "Now is the time you die, " + Misc.formatText(name) + "!";
			case 6 -> "I claim " + Misc.formatText(name) + " as my own!";
			case 7 -> "" + Misc.formatText(name) + " is mine!";
			case 8 -> "Let me escort you back to Edgeville, " + Misc.formatText(name) + "!";
			case 9 -> "I have come for you, " + Misc.formatText(name) + "!";
			default -> "";
		};
	}

}
