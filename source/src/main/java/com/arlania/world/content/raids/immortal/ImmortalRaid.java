package com.arlania.world.content.raids.immortal;

import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.arlania.model.*;
import com.arlania.model.definitions.ItemDefinition;
import com.arlania.util.Misc;
import com.arlania.world.World;
import com.arlania.world.content.CustomObjects;
import com.arlania.world.content.raids.immortal.ImmortalBoss.BossList;
import com.arlania.world.content.transportation.TeleportHandler;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.npc.NPCMovementCoordinator.Coordinator;
import com.arlania.world.entity.impl.player.Player;

/**
 * 
 * @author Hennessy
 *
 *         Jul 23, 2019 - 2:17:12 PM
 */
public class ImmortalRaid {

	private Player player;

	private List<Player> members;

	private String leader, raidPassword;

	public BossList randomBoss;

	private NPC currentBoss;

	private int countDown, holyGrailTime, npcRandomChatTime, destructionOrbTime;

	private Position instancedLocation;
	
	private static final int HOLY_GRAIL = 14_000, DESTRUCTION_ORB = 41371;
	
	private static int[] possible_rewards = { 20121, 20122, 20123 };
	
	private Position groupStartLocation = new Position(2272, 4688), bossStartLocation = new Position(2273, 4699);
	
	private String[] randomChats = { "I will kill you all!", "You think you can defeat me?! no no", "Ha! that gear.. try harder!" };
	
	private Position random_raids_object_spawn() {
		return new Position(Misc.random(2273 - 8, 2273 + 8), Misc.random(4696 - 8, 4696 + 8), instancedLocation.getZ());
	}

	public NPC getCurrentBoss() {
		return currentBoss;
	}

	public String formatNumber(long number) {
		return DecimalFormat.getInstance().format(number);
	}
	public String convertTicksToShortTime(long ms) {
    	ms *= 600;
    	return String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(ms) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(ms)), TimeUnit.MILLISECONDS.toSeconds(ms) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(ms)));
    }

	public ImmortalRaid(Player player, String raidPassword) {
		this.members = new ArrayList<Player>(4);
		this.player = player;
		this.leader = player.getUsername();
		this.raidPassword = raidPassword;
		this.player.setAttribute("raidCreation", false);
		this.destructionOrbs = new boolean[10];
	}
	
	/*private boolean spawnDestructionOrb() {
		for (int i = 10; i < 90; i+= 10) {
			if (findPercent(randomBoss.bossHP, i) <= currentBoss.getConstitution())
				return true;
		}
		return false;
	}*/
	
	private boolean[] destructionOrbs;

	public boolean destructionOrbSpawned;
	
	public void process() {
		if (currentBoss != null) {
			
			/*if (spawnDestructionOrb()) {
				
			}*/
			
			if (currentBoss.getConstitution() >= 60_000_000 && currentBoss.getConstitution() <= 70_000_000 && !destructionOrbs[1])
				spawnDestructionOrb(1);
			else if (currentBoss.getConstitution() >= 50_000_000 && currentBoss.getConstitution() < 60_000_000 && !destructionOrbs[2])
				spawnDestructionOrb(2);
			else if (currentBoss.getConstitution() >= 40_000_000 && currentBoss.getConstitution() < 50_000_000 && !destructionOrbs[3])
				spawnDestructionOrb(3);
			else if (currentBoss.getConstitution() >= 30_000_000 && currentBoss.getConstitution() < 40_000_000 && !destructionOrbs[4])
				spawnDestructionOrb(4);
			else if (currentBoss.getConstitution() >= 20_000_000 && currentBoss.getConstitution() < 30_000_000 && !destructionOrbs[5])
				spawnDestructionOrb(5);
			else if (currentBoss.getConstitution() >= 10_000_000 && currentBoss.getConstitution() < 20_000_000 && !destructionOrbs[6])
				spawnDestructionOrb(6);
			else if (currentBoss.getConstitution() >= 5_000_000 && currentBoss.getConstitution() < 10_000_000 && !destructionOrbs[7])
				spawnDestructionOrb(7);
			
			
			if (destructionOrbTime > 0) {
				destructionOrbTime--;
				
				if (destructionOrbTime == 0 && destructionOrbSpawned) {
					groupMessage("Your group failed to remove the destruction orb!");
					groupMessage("The lords have dropped missiles on you and healed the boss!");
					currentBoss.setForcedChat("Ha! you fools! the gods have blessed me! I cannot be defeated!");
					for (Player p : members) {
						if (p != null) {
							p.dealDamage(new Hit(6_000));
							currentBoss.heal(randomBoss.bossHP);
						}
					}
				}
			}
			
			if (npcRandomChatTime > 0) {
				npcRandomChatTime--;
				
				if (npcRandomChatTime == 0) {
					currentBoss.forceChat(randomChats[Misc.random(randomChats.length - 1)]);
					npcRandomChatTime = 50;
				}
			}
		}
		
		if (countDown > 0) {
			countDown--;
			if (countDown == 100) 
				groupMessage("60 seconds before a random raids boss will spawn!");
			else if (countDown == 50) 
				groupMessage("30 seconds before a random raids boss will spawn!");
			else if (countDown == 15) 
				groupMessage("10 seconds before a random raids boss will spawn!");
			else if (countDown == 7) 
				groupMessage("5 seconds before a random raids boss will spawn!");
			else if (countDown == 0) {
				spawnBoss(randomBoss.bossID, new Position(bossStartLocation.getX(),
						bossStartLocation.getY(), instancedLocation.getZ()), randomBoss.bossHP);
				groupMessage("A " + currentBoss.getDefinition().getName() + " has spawned with "
						+ formatNumber(currentBoss.getConstitution()) + " HP!");
				groupMessage("remember once you die you are out! kill it for a reward!");
				holyGrailTime = 100;
			}
			return;
		}
		
		if (holyGrailTime > 0) {
			
			holyGrailTime--;
			
			if (holyGrailTime == 0) {
				groupMessage("A holy grail has spawn somewhere in the lair!");
				groupMessage("Click it and receive a 7,000 HP boost!");
				CustomObjects.addTimeBasedObject(new GameObject(HOLY_GRAIL, random_raids_object_spawn()), 20);
				holyGrailTime = 115;
			}
		}
		
	}

	/**
	 * @param i
	 */
	private void spawnDestructionOrb(int id) {
		Position pos = random_raids_object_spawn();
		CustomObjects.addTimeBasedObject(new GameObject(DESTRUCTION_ORB, pos), 20);
		groupMessage("A destruction orb has spawned! click it within 10 seconds or..");
		groupMessage(".. pay the penalty of the "+currentBoss.getDefinition().getName()+"!");
		destructionOrbs[id] = true;
		destructionOrbSpawned = true;
		destructionOrbTime = 18;
		
	}

	public void groupMessage(String message) {
	
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i) != null) 
				members.get(i).sendMessage("(<col=0000ff>Group Message</col>): " + message);
		}
	}

	public boolean inArea() {
		final int BOUNDS_XNW = 2250, BOUNDS_XNE = 2292, BOUNDS_YNW = 4675, BOUNDS_YNE = 4715;
		return player.getPosition().getX() >= BOUNDS_XNW && player.getPosition().getX() <= BOUNDS_XNE
				&& player.getPosition().getY() >= BOUNDS_YNW && player.getPosition().getY() <= BOUNDS_YNE;
	}

	public void teleportToInstance(Player player, boolean joining) {

		player.raidsPassword = raidPassword;
		
		Player lead = World.getPlayerByName(leader);
		
		if (!joining) {
			randomBoss = BossList.values()[Misc.random(BossList.values().length - 1)];
			player.sendMessage("Your Immortal Raids instance will begin in 2 Minutes!");
			countDown = 200;
			instancedLocation = new Position(lead.getPosition().getX(), lead.getPosition().getY(), lead.getIndex() * 4);
			player.setAttribute("raidsCreation", false);
		} else {
			player.sendMessage("The Immortal Raid will begin in another: "+this.convertTicksToShortTime(countDown));
			player.setAttribute("raidsJoin", false);
		}
		Position startLoc = groupStartLocation;

		// teleports
		TeleportHandler.teleportPlayer(player,
				new Position(Misc.random(startLoc.getX() - 3, startLoc.getX() + 3),
						Misc.random(startLoc.getY() - 2, startLoc.getY() + 2), lead.getIndex() * 4),
				player.getSpellbook().getTeleportType());
		
		this.members.add(player);
		return;
	}
	
	public boolean joinedRaid(Player player) {
		return this.getMembers() != null && this.getMembers().contains(player);
	}

	public void spawnBoss(int bossId, Position bossStartLocation, int hitPoints) {
		currentBoss = new NPC(bossId, hitPoints, bossStartLocation, true);
		currentBoss.movementCoordinator.setCoordinator(new Coordinator(true, 10));
		currentBoss.forceChat("Prepare to die slaves!");
		npcRandomChatTime = 50;
	}

	public List<Player> getMembers() {
		return members;
	}
	
	public void endRaid(boolean died) {
		
		World.sendMessage("(<col=ff0000>Raids</col>): The team of "+leader+" has slain one of the Immortal Raids bosses!");
		
		ImmortalRaid session = ImmortalRaidsManager.getSession(player.raidsPassword);
		
		if (session == null) {
			destroy(player, false);
			return;
		}
		
		for (Player p : session.members) {
			if (p != null)
				destroy(p, true);
		}
		
	}
	
	private void destroy(Player p, boolean sucessfull) {
		p.raidsPassword = null;
	    p.currentRaidSession = null;
	    if (sucessfull) {
	    	rollLoot(p);
	        p.immortalRaidsCompleted++;
	    }
		TeleportHandler.teleportPlayer(p, new Position(2529, 2527, 1), p.getSpellbook().getTeleportType());
	}
	
	public boolean raidStarted() {
		return this.countDown == 0;
	}
	
	public int findPercent(int hp, int percent) {
		return (int)(hp*(percent/100.0f));
	}
	
	public void rollLoot(Player p) {
		
		double percent = Math.random() * 100;
		
		if (percent > 1.25) {
			player.sendMessage("better luck next time!"); 
			return;
		}
		
		Item reward = new Item(possible_rewards[Misc.random(possible_rewards.length - 1)], 1);
		
		String rewardName = ItemDefinition.forId(reward.getId()).getName();
		
		boolean space = player.getInventory().getFreeSlots() > 0;
		
		if (space)
			player.getInventory().add(reward);
		else
			player.getBank().add(reward);
		
		player.sendMessage("Your reward "+rewardName+" has been added into your "+(space ? "inventory" : "bank")+".");
		World.sendMessage("(<col=ff0000>Rare Drop</col>): "+p.getUsername()+" received a "+rewardName+" from the Immortal Raids!");
	}

}
