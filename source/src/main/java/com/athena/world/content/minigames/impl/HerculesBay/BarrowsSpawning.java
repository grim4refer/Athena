package com.athena.world.content.minigames.impl.HerculesBay;

import com.athena.engine.task.Task;
import com.athena.engine.task.TaskManager;
import com.athena.model.Animation;
import com.athena.model.GameObject;
import com.athena.model.Graphic;
import com.athena.model.GroundItem;
import com.athena.model.Item;
import com.athena.model.PlayerRights;
import com.athena.model.Position;
import com.athena.model.RegionInstance;
import com.athena.model.RegionInstance.RegionInstanceType;
import com.athena.world.World;
import com.athena.world.entity.impl.GroundItemManager;
import com.athena.world.entity.impl.npc.NPC;
import com.athena.world.entity.impl.player.Player;

public class BarrowsSpawning {

	/*
	 * The armors required to animate an NPC
	 */
	private static final int[][] ARMOR_DATA = {{1075, 1117, 1155, 4278}, {1067, 1115, 1153, 4279}, {1069, 1119, 1157, 4280}, {1077, 1125, 1165, 4281}, {1071, 1121, 1159, 4282}, {1073, 1123, 1161, 4283}, {1079, 1127, 1163, 4284}};
	private static final int[] ANIMATED_ARMOUR_NPCS = {4278, 4279, 4280, 4281, 4282, 4283, 4284};
	private static final int[] HERCULES = {5, 10, 15, 20, 26, 32, 40};
	
	/**
	 * Handles what happens when a player uses an item on the animator.
	 * @param player	The player
	 * @param item		The item the player is using
	 * @param object	That animator object which the player is using an item on
	 */
	
	public static boolean itemOnAnimator(final Player player, final Item item, final GameObject object) {
		if(player.getMinigameAttributes().getBSAttributes().hasSpawnedArmour() && player.getRights() != PlayerRights.OWNER) {
			player.getPacketSender().sendMessage("You have already spawned some reanimated barrows.");
			return true;
		} else {
			for(int i = 0; i < ARMOR_DATA.length; i++) {
				for(int f = 0; f < ARMOR_DATA[0].length; f++) {
					if(item.getId() == ARMOR_DATA[i][f]) {
						if(player.getInventory().contains(ARMOR_DATA[i][0]) && player.getInventory().contains(ARMOR_DATA[i][1]) && player.getInventory().contains(ARMOR_DATA[i][2])) {
							final int items[] = new int[] {ARMOR_DATA[i][0], ARMOR_DATA[i][1], ARMOR_DATA[i][2]};
							if(items != null) {
								for(int a = 0; a < items.length; a++)
									player.getInventory().delete(items[a], 1);
								player.setRegionInstance(new RegionInstance(player, RegionInstanceType.WARRIORS_GUILD));
								player.getMinigameAttributes().getBSAttributes().setSpawnedArmour(true);
								player.performAnimation(new Animation(827));
								player.getPacketSender().sendMessage("You place some barrows on the animator..");
								object.performGraphic(new Graphic(1930));
								final int index = i;
								TaskManager.submit(new Task(2) {
									@Override
									public void execute() {
										NPC npc_ = new NPC(ARMOR_DATA[index][3], new Position(player.getPosition().getX(), player.getPosition().getY() + 1));
										npc_.forceChat("THANK THE GODS I'M FINALLY ALIVE!!!!").setEntityInteraction(player).getCombatBuilder().setAttackTimer(2);
										npc_.setSpawnedFor(player).getCombatBuilder().attack(player);
										player.setPositionToFace(npc_.getPosition());
										World.register(npc_);
										player.getRegionInstance().getNpcsList().add(npc_);
										stop();
									}
								});
							}
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * Handles a drop after an NPC is slain in the Barrows Spawning Area
	 * @param player	The player to handle the drop for
	 * @param npc		The npc which will drop something
	 */
	
	public static void handleDrop(Player player, NPC npc) {
		if(player == null || npc == null)
			return;
		/*
		 * Hercules
		 */
		if(npc.getId() >= 7 && npc.getId() <= 13) {
			if(player.getRegionInstance() != null)
				player.getRegionInstance().getNpcsList().remove(npc);
			int[] armour = null;
			for(int i = 0; i < ARMOR_DATA.length; i++) {
				if(ARMOR_DATA[i][3] == npc.getId()) {
					armour = new int[] {ARMOR_DATA[i][0], ARMOR_DATA[i][1], ARMOR_DATA[i][2]};
					break;
				}
			}
			if(armour != null) {
				for(int i : armour)
					GroundItemManager.spawnGroundItem(player, new GroundItem(new Item(i), npc.getPosition().copy(), player.getUsername(), false, 80, true, 80));
				player.getMinigameAttributes().getWarriorsGuildAttributes().setSpawnedArmour(false);
				GroundItemManager.spawnGroundItem(player, new GroundItem(new Item(8851, getHerculesAmount(npc.getId())), npc.getPosition().copy(), player.getUsername(), false, 80, true, 80));
				armour = null;
			}
		}
	}
		

	/**
	 * Gets the amount of hercules to receive from an npc
	 * @param npc	The npc to check how many tokens to receive from
	 * @return		The amount of tokens to receive as a drop
	 */
	
	private static int getHerculesAmount(int npc) {
		for(int f = 0; f < ANIMATED_ARMOUR_NPCS.length; f++) {
			if (npc == ANIMATED_ARMOUR_NPCS[f]) {
				return HERCULES[f];
			}
		}
		return 5;
	}
}
