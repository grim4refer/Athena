package com.athena.world.content;

import com.athena.engine.task.Task;
import com.athena.engine.task.TaskManager;
import com.athena.model.GameObject;
import com.athena.model.GroundItem;
import com.athena.model.Item;
import com.athena.model.Locations.Location;
import com.athena.model.Position;
import com.athena.world.World;
import com.athena.world.clip.region.RegionClipping;
import com.athena.world.entity.Entity;
import com.athena.world.entity.impl.GroundItemManager;
import com.athena.world.entity.impl.player.Player;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Handles customly spawned objects (mostly global but also privately for players)
 * @author Gabriel Hannason
 */
public class CustomObjects {
	
	private static final int DISTANCE_SPAWN = 70; //Spawn if within 70 squares of distance
	public static final CopyOnWriteArrayList<GameObject> CUSTOM_OBJECTS = new CopyOnWriteArrayList<>();

	public static void init() {
		for (int[] clientObject : CLIENT_OBJECTS) {
			int id = clientObject[0];
			int x = clientObject[1];
			int y = clientObject[2];
			int z = clientObject[3];
			int face = clientObject[4];
			GameObject object = new GameObject(id, new Position(x, y, z));
			object.setFace(face);
			RegionClipping.addObject(object);
		}
		for (int[] customObjectsSpawn : CUSTOM_OBJECTS_SPAWNS) {
			int id = customObjectsSpawn[0];
			int x = customObjectsSpawn[1];
			int y = customObjectsSpawn[2];
			int z = customObjectsSpawn[3];
			int face = customObjectsSpawn[4];
			GameObject object = new GameObject(id, new Position(x, y, z));
			object.setFace(face);
			CUSTOM_OBJECTS.add(object);
			World.register(object);
		}
	}
	
	private static void handleList(GameObject object, String handleType) {
		switch(handleType.toUpperCase()) {
		case "DELETE":
			CUSTOM_OBJECTS.removeIf(objects -> objects.getId() == object.getId() && object.getPosition().equals(objects.getPosition()));
			break;
		case "ADD":
			if(!CUSTOM_OBJECTS.contains(object)) {
				CUSTOM_OBJECTS.add(object);
			}
			break;
		case "EMPTY":
			CUSTOM_OBJECTS.clear();
			break;
		}
	}

	public static void spawnObject(Player p, GameObject object) {
		if(object.getId() != -1) {
			p.getPacketSender().sendObject(object);
			if(!RegionClipping.objectExists(object)) {
				RegionClipping.addObject(object);
			}
		} else {
			deleteObject(p, object);
		}
	}
	
	public static void deleteObject(Player p, GameObject object) {
		p.getPacketSender().sendObjectRemoval(object);
		if(RegionClipping.objectExists(object)) {
			RegionClipping.removeObject(object);
		}
	}
	
	public static void deleteGlobalObject(GameObject object) {
		handleList(object, "delete");
		World.deregister(object);
	}

	public static void spawnGlobalObject(GameObject object) {
		handleList(object, "add");
		World.register(object);
	}
	
	public static void spawnGlobalObjectWithinDistance(GameObject object) {
		for(Player player : World.getPlayers()) {
			if(player == null)
				continue;
			if(object.getPosition().isWithinDistance(player.getPosition(), DISTANCE_SPAWN)) {
				spawnObject(player, object);
			}
		}
	}
	
	public static void deleteGlobalObjectWithinDistance(GameObject object) {
		for(Player player : World.getPlayers()) {
			if(player == null)
				continue;
			if(object.getPosition().isWithinDistance(player.getPosition(), DISTANCE_SPAWN)) {
				deleteObject(player, object);
			}
		}
	}
	
		public static boolean objectExists(Position pos) {
			return getGameObject(pos) != null;
		}

		public static GameObject getGameObject(Position pos) {
			for(GameObject objects : CUSTOM_OBJECTS) {
				if(objects != null && objects.getPosition().equals(pos)) {
					return objects;
				}
			}
			return null;
		}

		public static void handleRegionChange(Player p) {
			for(GameObject object: CUSTOM_OBJECTS) {
				if(object == null)
					continue;
				if(object.getPosition().isWithinDistance(p.getPosition(), DISTANCE_SPAWN)) {
					spawnObject(p, object);
				}
			}
		}
	
		public static void objectRespawnTask(Player p, final GameObject tempObject, final GameObject mainObject, final int cycles) {
			deleteObject(p, mainObject);
			spawnObject(p, tempObject);
			TaskManager.submit(new Task(cycles) {
				@Override
				public void execute() {
					deleteObject(p, tempObject);
					spawnObject(p, mainObject);
					this.stop();
				}
			});
		}
		
		public static void globalObjectRespawnTask(final GameObject tempObject, final GameObject mainObject, final int cycles) {
			deleteGlobalObject(mainObject);
			spawnGlobalObject(tempObject);
			TaskManager.submit(new Task(cycles) {
				@Override
				public void execute() {
					deleteGlobalObject(tempObject);
					spawnGlobalObject(mainObject);
					this.stop();
				}
			});
		}

		public static void globalObjectRemovalTask(final GameObject object, final int cycles) {
			spawnGlobalObject(object);
			TaskManager.submit(new Task(cycles) {
				@Override
				public void execute() {
					deleteGlobalObject(object);
					this.stop();
				}
			});
		}

	public static void globalFiremakingTask(final GameObject fire, final Player player, final int cycles) {
		spawnGlobalObject(fire);
		TaskManager.submit(new Task(cycles) {
			@Override
			public void execute() {
				deleteGlobalObject(fire);
				if(player.getInteractingObject() != null && player.getInteractingObject().getId() == 2732) {
					player.setInteractingObject(null);
				}
				this.stop();
			}
			@Override
			public void stop() {
				setEventRunning(false);
				GroundItemManager.spawnGroundItem(player, new GroundItem(new Item(592), fire.getPosition(), player.getUsername(), false, 150, true, 100));
				GroundItemManager.spawnGroundItem(player, new GroundItem(new Item(20952,5), fire.getPosition(), player.getUsername(), false, 150, true, 100));
			}
		});
	}
	
	public static void addTimeBasedObject(final GameObject obj, final int cycles) {
		spawnGlobalObject(obj);
		
		TaskManager.submit(new Task(cycles) {
			@Override
			public void execute() {
				GameObject blank_obj = new GameObject(0, obj.getPosition());
				spawnGlobalObject(blank_obj);
				RegionClipping.removeClipping(blank_obj.getPosition().getX(), blank_obj.getPosition().getY(), blank_obj.getPosition().getZ(), 0x000000);
				this.stop();
			}
			
			@Override
			public void stop() {
				setEventRunning(false);
			}
		});
	}
	
	public static void forceRemoveObject(GameObject obj) {
		GameObject blank_obj = new GameObject(0, obj.getPosition());
		spawnGlobalObject(blank_obj);
		RegionClipping.removeClipping(blank_obj.getPosition().getX(), blank_obj.getPosition().getY(), blank_obj.getPosition().getZ(), 0x000000);
	}
	
	/**
	 * Contains
	 * @param ObjectId - The object ID to spawn
	 * @param absX - The X position of the object to spawn
	 * @param absY - The Y position of the object to spawn
	 * @param Z - The Z position of the object to spawn
	 * @param face - The position the object will face
	 */
	
	//Only adds clips to these objects, they are spawned clientsided
	//NOTE: You must add to the client's customobjects array to make them spawn, this is just clipping!
	private static final int[][] CLIENT_OBJECTS = {
	/*** Kourend home area oof ***/
			
			{-1, 1615, 3662, 0, 0}, //delete
			{-1, 1611, 3671, 0, 0}, //delete
			{-1, 1611, 3668, 0, 0}, //delete
			{-1, 1611, 3675, 0, 0}, //delete
			{-1, 1611, 3679, 0, 0}, //delete
			{-1, 1648, 3683, 0, 0}, //delete
			{-1, 1633, 3685, 0, 0}, //delete
			{-1, 1635, 3685, 0, 0}, //delete
			{-1, 1639, 3685, 0, 0}, //delete
			{-1, 1640, 3685, 0, 0}, //delete
			{-1, 1637, 3684, 0, 0}, //delete
			{-1, 1637, 3683, 0, 0}, //delete
			{-1, 1634, 3683, 0, 0}, //delete
			{-1, 1634, 3671, 0, 0}, //delete
			{-1, 1638, 3683, 0, 0}, //delete
			{-1, 1645, 3669, 0, 0}, //delete
			{-1, 1626, 3681, 0, 0}, //delete
			{-1, 2364, 3894, 0, 0}, //delete
			
			{-1, 2594, 3163, 0, 0}, //delete
			{-1, 2593, 3163, 0, 0}, //delete
			{-1, 2592, 3162, 0, 0}, //delete
			{-1, 2592, 3162, 0, 0}, //delete
			{-1, 2592, 3161, 0, 0}, //delete
			{-1, 2591, 3163, 0, 0}, //delete
			
			{-1, 2592, 3163, 0, 0}, //delete
			{-1, 2592, 3163, 0, 0}, //delete
			{-1, 2593, 3162, 0, 0}, //delete
			{-1, 2593, 3161, 0, 0}, //delete
			{-1, 2593, 3164, 0, 0}, //delete
			{-1, 2592, 3164, 0, 0}, //delete
			
			{-1, 2594, 3161, 0, 0}, //delete
			{-1, 2595, 3162, 0, 0}, //delete
			{-1, 2595, 3163, 0, 0}, //delete
			{-1, 2594, 3164, 0, 0}, //delete
			{-1, 2590, 3162, 0, 0}, //delete
			{-1, 2591, 3161, 0, 0}, //delete
			{-1, 2587, 3169, 0, 0}, //delete
			
			{-1, 2582, 3168, 0, 0}, //delete
			{-1, 2582, 3167, 0, 0}, //delete
			{-1, 2582, 3166, 0, 0}, //delete
			{-1, 2582, 3165, 0, 0}, //delete
			{-1, 2582, 3164, 0, 0}, //delete
			{-1, 2582, 3163, 0, 0}, //delete
			{-1, 2582, 3162, 0, 0}, //delete
			{-1, 2582, 3161, 0, 0}, //delete
			{-1, 2582, 3160, 0, 0}, //delete
			{-1, 2582, 3159, 0, 0}, //delete
			{-1, 2582, 3158, 0, 0}, //delete
			{-1, 2582, 3157, 0, 0}, //delete
			{-1, 2582, 3156, 0, 0}, //delete
			{-1, 3220, 3871, 0, 0}, //delete
			
			{-1, 2585, 3157, 0, 0}, //delete
			
			{-1, 2594, 3162, 0, 0}, //delete
			{-1, 2591, 3162, 0, 0}, //delete
			{-1, 2593, 3165, 0, 0}, //delete
			{-1, 2592, 3165, 0, 0}, //delete
			{-1, 2591, 3164, 0, 0}, //delete
			{-1, 2590, 3163, 0, 0}, //delete
			{-1, 2590, 3161, 0, 0}, //delete
			{-1, 2592, 3160, 0, 0}, //delete
			{-1, 2593, 3160, 0, 0}, //delete
			
			//{2213, 1635, 3670, 0, 2}, //bank
			//{2213, 1636, 3670, 0, 2}, //bank
			//{2213, 1637, 3670, 0, 2}, //bank
			
			//{2213, 1635, 3676, 0, 2}, //bank
			//{2213, 1636, 3676, 0, 2}, //bank
			//{2213, 1637, 3676, 0, 2}, //bank
			
			//{2213, 1639, 3672, 0, 1}, //bank
			//{2213, 1639, 3673, 0, 1}, //bank
			//{2213, 1639, 3674, 0, 1}, //bank
			
			//{2213, 1633, 3672, 0, 1}, //bank
			//{2213, 1633, 3673, 0, 1}, //bank
			//{2213, 1633, 3674, 0, 1}, //bank
			
			{13289, 2535, 2535, 1, 0}, //Raids Chest

			
			{2213, 3681, 2981, 0, 3},  //newHome Bank
			{2213, 3681, 2982, 0, 3},  //newHome Bank
			{2213, 3681, 2983, 0, 3},  //newHome Bank
			{409, 3678, 2990, 0, 3},  //newHome Bank
			{411, 3674, 2990, 0, 1},  //newHome Bank
			
			{366, 2709, 9991, 0, 2}, //crate
			{366, 2709, 9992, 0, 2}, //crate
			{366, 2709, 9993, 0, 2}, //crate
			
			/*** Raids ***/
			{362, 3284, 3883, 0, 2}, //Barrel
			{362, 3293, 3885, 0, 2}, //Barrel
			{362, 3293, 3886, 0, 2}, //Barrel
			{362, 3286, 3889, 0, 2}, //Barrel
			
			{362, 3140, 3871, 0, 2}, //Barrel
			{362, 3139, 3871, 0, 2}, //Barrel
			{362, 3138, 3871, 0, 2}, //Barrel
			{362, 3137, 3871, 0, 2}, //Barrel
			{362, 3136, 3871, 0, 2}, //Barrel
			{362, 3135, 3871, 0, 2}, //Barrel
			{362, 3134, 3871, 0, 2}, //Barrel
			{362, 3133, 3871, 0, 2}, //Barrel
			{362, 3132, 3871, 0, 2}, //Barrel
			{362, 3131, 3871, 0, 2}, //Barrel
			{362, 3130, 3871, 0, 2}, //Barrel
			{362, 3129, 3871, 0, 2}, //Barrel
			{362, 3128, 3871, 0, 2}, //Barrel
			{362, 3127, 3871, 0, 2}, //Barrel
			{362, 3126, 3871, 0, 2}, //Barrel
			{362, 3125, 3871, 0, 2}, //Barrel
			{362, 3124, 3871, 0, 2}, //Barrel
			{362, 3123, 3871, 0, 2}, //Barrel
			
			{362, 3123, 3872, 0, 2}, //Barrel
			{362, 3123, 3873, 0, 2}, //Barrel
			{362, 3123, 3874, 0, 2}, //Barrel
			{362, 3123, 3875, 0, 2}, //Barrel
			{362, 3123, 3876, 0, 2}, //Barrel
			{362, 3123, 3877, 0, 2}, //Barrel
			{362, 3123, 3878, 0, 2}, //Barrel
			{362, 3123, 3879, 0, 2}, //Barrel
			{362, 3123, 3880, 0, 2}, //Barrel
			{362, 3123, 3881, 0, 2}, //Barrel
			{362, 3123, 3882, 0, 2}, //Barrel
			{362, 3123, 3883, 0, 2}, //Barrel
			{362, 3123, 3884, 0, 2}, //Barrel
			{362, 3123, 3885, 0, 2}, //Barrel
			{362, 3123, 3886, 0, 2}, //Barrel
			{362, 3123, 3887, 0, 2}, //Barrel
			{362, 3123, 3888, 0, 2}, //Barrel
			{362, 3123, 3889, 0, 2}, //Barrel
			
			{362, 3140, 3872, 0, 2}, //Barrel
			{362, 3140, 3873, 0, 2}, //Barrel
			{362, 3140, 3874, 0, 2}, //Barrel
			{362, 3140, 3875, 0, 2}, //Barrel
			{362, 3140, 3876, 0, 2}, //Barrel
			{362, 3140, 3877, 0, 2}, //Barrel
			{362, 3140, 3878, 0, 2}, //Barrel
			{362, 3140, 3879, 0, 2}, //Barrel
			{362, 3140, 3880, 0, 2}, //Barrel
			{362, 3140, 3881, 0, 2}, //Barrel
			{362, 3140, 3882, 0, 2}, //Barrel
			{362, 3140, 3883, 0, 2}, //Barrel
			{362, 3140, 3884, 0, 2}, //Barrel
			{362, 3140, 3885, 0, 2}, //Barrel
			{362, 3140, 3886, 0, 2}, //Barrel
			{362, 3140, 3887, 0, 2}, //Barrel
			{362, 3140, 3888, 0, 2}, //Barrel
			{362, 3140, 3889, 0, 2}, //Barrel
			
			{362, 3139, 3889, 0, 2}, //Barrel
			{362, 3138, 3889, 0, 2}, //Barrel
			{362, 3137, 3889, 0, 2}, //Barrel
			{362, 3136, 3889, 0, 2}, //Barrel
			{362, 3135, 3889, 0, 2}, //Barrel
			{362, 3134, 3889, 0, 2}, //Barrel
			{362, 3133, 3889, 0, 2}, //Barrel
			{362, 3132, 3889, 0, 2}, //Barrel
			{362, 3131, 3889, 0, 2}, //Barrel
			{362, 3130, 3889, 0, 2}, //Barrel
			{362, 3129, 3889, 0, 2}, //Barrel
			{362, 3128, 3889, 0, 2}, //Barrel
			{362, 3127, 3889, 0, 2}, //Barrel
			{362, 3126, 3889, 0, 2}, //Barrel
			{362, 3125, 3889, 0, 2}, //Barrel
			{362, 3124, 3889, 0, 2}, //Barrel
			{362, 3123, 3889, 0, 2}, //Barrel
			
			{362, 3317, 3892, 0, 2}, //Barrel
			{362, 3317, 3891, 0, 2}, //Barrel
			{362, 3317, 3890, 0, 2}, //Barrel
			{362, 3317, 3889, 0, 2}, //Barrel
			{362, 3317, 3888, 0, 2}, //Barrel
			{362, 3317, 3887, 0, 2}, //Barrel
			{362, 3317, 3886, 0, 2}, //Barrel
			{362, 3317, 3885, 0, 2}, //Barrel
			{362, 3317, 3884, 0, 2}, //Barrel
			{362, 3317, 3883, 0, 2}, //Barrel
			{362, 3317, 3882, 0, 2}, //Barrel
			{362, 3317, 3881, 0, 2}, //Barrel
			{362, 3317, 3880, 0, 2}, //Barrel
			{362, 3317, 3879, 0, 2}, //Barrel
			{362, 3317, 3878, 0, 2}, //Barrel
			{362, 3317, 3877, 0, 2}, //Barrel
			{362, 3317, 3876, 0, 2}, //Barrel
			{362, 3317, 3875, 0, 2}, //Barrel
			
			{362, 3318, 3892, 0, 2}, //Barrel
			{362, 3319, 3892, 0, 2}, //Barrel
			{362, 3320, 3892, 0, 2}, //Barrel
			{362, 3321, 3892, 0, 2}, //Barrel
			{362, 3322, 3892, 0, 2}, //Barrel
			{362, 3323, 3892, 0, 2}, //Barrel
			{362, 3324, 3892, 0, 2}, //Barrel
			{362, 3325, 3892, 0, 2}, //Barrel
			{362, 3326, 3892, 0, 2}, //Barrel
			{362, 3327, 3892, 0, 2}, //Barrel
			{362, 3328, 3892, 0, 2}, //Barrel
			
			{362, 3318, 3875, 0, 2}, //Barrel
			{362, 3319, 3875, 0, 2}, //Barrel
			{362, 3320, 3875, 0, 2}, //Barrel
			{362, 3321, 3875, 0, 2}, //Barrel
			{362, 3322, 3875, 0, 2}, //Barrel
			{362, 3323, 3875, 0, 2}, //Barrel
			{362, 3324, 3875, 0, 2}, //Barrel
			{362, 3325, 3875, 0, 2}, //Barrel
			{362, 3326, 3875, 0, 2}, //Barrel
			{362, 3327, 3875, 0, 2}, //Barrel
			{362, 3328, 3875, 0, 2}, //Barrel
			
			{362, 3328, 3876, 0, 2}, //Barrel
			{362, 3328, 3877, 0, 2}, //Barrel
			{362, 3328, 3878, 0, 2}, //Barrel
			{362, 3328, 3879, 0, 2}, //Barrel
			{362, 3328, 3880, 0, 2}, //Barrel
			{362, 3328, 3881, 0, 2}, //Barrel
			{362, 3328, 3882, 0, 2}, //Barrel
			{362, 3328, 3883, 0, 2}, //Barrel
			{362, 3328, 3884, 0, 2}, //Barrel
			{362, 3328, 3885, 0, 2}, //Barrel
			{362, 3328, 3886, 0, 2}, //Barrel
			{362, 3328, 3887, 0, 2}, //Barrel
			{362, 3328, 3888, 0, 2}, //Barrel
			{362, 3328, 3889, 0, 2}, //Barrel
			{362, 3328, 3890, 0, 2}, //Barrel
			{362, 3328, 3891, 0, 2}, //Barrel
			{362, 3328, 3892, 0, 2}, //Barrel
			
			{362, 3263, 3886, 0, 2}, //Barrel
			{362, 3263, 3885, 0, 2}, //Barrel
			{362, 3263, 3884, 0, 2}, //Barrel
			{362, 3263, 3883, 0, 2}, //Barrel
			{362, 3263, 3882, 0, 2}, //Barrel
			{362, 3263, 3881, 0, 2}, //Barrel
			{362, 3263, 3880, 0, 2}, //Barrel
			{362, 3263, 3879, 0, 2}, //Barrel
			{362, 3263, 3878, 0, 2}, //Barrel
			{362, 3263, 3877, 0, 2}, //Barrel
			{362, 3263, 3876, 0, 2}, //Barrel
			{362, 3263, 3875, 0, 2}, //Barrel
			{362, 3263, 3874, 0, 2}, //Barrel
			{362, 3263, 3873, 0, 2}, //Barrel
			{362, 3263, 3872, 0, 2}, //Barrel
			{362, 3263, 3871, 0, 2}, //Barrel
			{362, 3263, 3870, 0, 2}, //Barrel
			
			{362, 3262, 3886, 0, 2}, //Barrel
			{362, 3261, 3886, 0, 2}, //Barrel
			{362, 3260, 3886, 0, 2}, //Barrel
			{362, 3259, 3886, 0, 2}, //Barrel
			{362, 3258, 3886, 0, 2}, //Barrel
			{362, 3257, 3886, 0, 2}, //Barrel
			{362, 3256, 3886, 0, 2}, //Barrel
			{362, 3255, 3886, 0, 2}, //Barrel
			{362, 3254, 3886, 0, 2}, //Barrel
			{362, 3253, 3886, 0, 2}, //Barrel
			{362, 3252, 3886, 0, 2}, //Barrel
			{362, 3251, 3886, 0, 2}, //Barrel
			{362, 3250, 3886, 0, 2}, //Barrel
			{362, 3249, 3886, 0, 2}, //Barrel
			{362, 3248, 3886, 0, 2}, //Barrel
			
			{362, 3248, 3885, 0, 2}, //Barrel
			{362, 3248, 3884, 0, 2}, //Barrel
			{362, 3248, 3883, 0, 2}, //Barrel
			{362, 3248, 3882, 0, 2}, //Barrel
			{362, 3248, 3881, 0, 2}, //Barrel
			{362, 3248, 3880, 0, 2}, //Barrel
			{362, 3248, 3879, 0, 2}, //Barrel
			{362, 3248, 3878, 0, 2}, //Barrel
			{362, 3248, 3877, 0, 2}, //Barrel
			{362, 3248, 3876, 0, 2}, //Barrel
			{362, 3248, 3875, 0, 2}, //Barrel
			{362, 3248, 3874, 0, 2}, //Barrel
			{362, 3248, 3873, 0, 2}, //Barrel
			{362, 3248, 3872, 0, 2}, //Barrel
			{362, 3248, 3871, 0, 2}, //Barrel
			{362, 3248, 3870, 0, 2}, //Barrel
			
			{362, 3249, 3870, 0, 2}, //Barrel
			{362, 3250, 3870, 0, 2}, //Barrel
			{362, 3251, 3870, 0, 2}, //Barrel
			{362, 3252, 3870, 0, 2}, //Barrel
			{362, 3253, 3870, 0, 2}, //Barrel
			{362, 3254, 3870, 0, 2}, //Barrel
			{362, 3255, 3870, 0, 2}, //Barrel
			{362, 3256, 3870, 0, 2}, //Barrel
			{362, 3257, 3870, 0, 2}, //Barrel
			{362, 3258, 3870, 0, 2}, //Barrel
			{362, 3259, 3870, 0, 2}, //Barrel
			{362, 3260, 3870, 0, 2}, //Barrel
			{362, 3261, 3870, 0, 2}, //Barrel
			{362, 3262, 3870, 0, 2}, //Barrel
			{362, 3263, 3870, 0, 2}, //Barrel
			
			{362, 3211, 3876, 0, 2}, //Barrel
			{362, 3212, 3876, 0, 2}, //Barrel
			{362, 3213, 3876, 0, 2}, //Barrel
			{362, 3214, 3876, 0, 2}, //Barrel
			{362, 3215, 3876, 0, 2}, //Barrel
			{362, 3216, 3876, 0, 2}, //Barrel
			{362, 3217, 3876, 0, 2}, //Barrel
			{362, 3218, 3876, 0, 2}, //Barrel
			{362, 3219, 3876, 0, 2}, //Barrel
			{362, 3220, 3876, 0, 2}, //Barrel
			{362, 3221, 3876, 0, 2}, //Barrel
			{362, 3222, 3876, 0, 2}, //Barrel
			{362, 3223, 3876, 0, 2}, //Barrel
			{362, 3224, 3876, 0, 2}, //Barrel
			{362, 3225, 3876, 0, 2}, //Barrel
			{362, 3226, 3876, 0, 2}, //Barrel
			
			{362, 3226, 3877, 0, 2}, //Barrel
			{362, 3226, 3878, 0, 2}, //Barrel
			{362, 3226, 3879, 0, 2}, //Barrel
			{362, 3226, 3880, 0, 2}, //Barrel
			{362, 3226, 3881, 0, 2}, //Barrel
			{362, 3226, 3882, 0, 2}, //Barrel
			{362, 3226, 3883, 0, 2}, //Barrel
			{362, 3226, 3884, 0, 2}, //Barrel
			{362, 3226, 3885, 0, 2}, //Barrel
			{362, 3226, 3886, 0, 2}, //Barrel
			{362, 3226, 3887, 0, 2}, //Barrel
			{362, 3226, 3888, 0, 2}, //Barrel
			{362, 3226, 3889, 0, 2}, //Barrel
			{362, 3226, 3890, 0, 2}, //Barrel
			{362, 3226, 3891, 0, 2}, //Barrel
			{362, 3226, 3892, 0, 2}, //Barrel
			
			{362, 3225, 3892, 0, 2}, //Barrel
			{362, 3224, 3892, 0, 2}, //Barrel
			{362, 3223, 3892, 0, 2}, //Barrel
			{362, 3222, 3892, 0, 2}, //Barrel
			{362, 3221, 3892, 0, 2}, //Barrel
			{362, 3220, 3892, 0, 2}, //Barrel
			{362, 3219, 3892, 0, 2}, //Barrel
			{362, 3218, 3892, 0, 2}, //Barrel
			{362, 3217, 3892, 0, 2}, //Barrel
			{362, 3216, 3892, 0, 2}, //Barrel
			{362, 3215, 3892, 0, 2}, //Barrel
			{362, 3214, 3892, 0, 2}, //Barrel
			{362, 3213, 3892, 0, 2}, //Barrel
			{362, 3212, 3892, 0, 2}, //Barrel
			{362, 3211, 3892, 0, 2}, //Barrel
			{362, 3210, 3892, 0, 2}, //Barrel
			
			{362, 3210, 3891, 0, 2}, //Barrel
			{362, 3210, 3890, 0, 2}, //Barrel
			{362, 3210, 3889, 0, 2}, //Barrel
			{362, 3210, 3888, 0, 2}, //Barrel
			{362, 3210, 3887, 0, 2}, //Barrel
			{362, 3210, 3886, 0, 2}, //Barrel
			{362, 3210, 3885, 0, 2}, //Barrel
			{362, 3210, 3884, 0, 2}, //Barrel
			{362, 3210, 3883, 0, 2}, //Barrel
			{362, 3210, 3882, 0, 2}, //Barrel
			{362, 3210, 3881, 0, 2}, //Barrel
			{362, 3210, 3880, 0, 2}, //Barrel
			{362, 3210, 3879, 0, 2}, //Barrel
			{362, 3210, 3878, 0, 2}, //Barrel
			{362, 3210, 3877, 0, 2}, //Barrel
			{362, 3210, 3876, 0, 2}, //Barrel
						
			{362, 3140, 3886, 0, 2}, //Barrel
			{362, 3140, 3875, 0, 2}, //Barrel
			{362, 3139, 3871, 0, 2}, //Barrel
			
			{362, 3155, 3881, 0, 2}, //Barrel
			{362, 3156, 3881, 0, 2}, //Barrel
			{362, 3157, 3881, 0, 2}, //Barrel
			{362, 3158, 3881, 0, 2}, //Barrel
			{362, 3159, 3881, 0, 2}, //Barrel
			{362, 3160, 3881, 0, 2}, //Barrel
			{362, 3161, 3881, 0, 2}, //Barrel
			{362, 3162, 3881, 0, 2}, //Barrel
			{362, 3163, 3881, 0, 2}, //Barrel
			
			{362, 3154, 3880, 0, 2}, //Barrel
			{362, 3153, 3881, 0, 2}, //Barrel
			{362, 3153, 3882, 0, 2}, //Barrel
			{362, 3153, 3883, 0, 2}, //Barrel
			{362, 3153, 3884, 0, 2}, //Barrel
			{362, 3153, 3885, 0, 2}, //Barrel
			{362, 3153, 3886, 0, 2}, //Barrel
			{362, 3153, 3887, 0, 2}, //Barrel
			{362, 3153, 3888, 0, 2}, //Barrel
			{362, 3153, 3889, 0, 2}, //Barrel
			{362, 3153, 3890, 0, 2}, //Barrel
			{362, 3153, 3891, 0, 2}, //Barrel
			
			{362, 3154, 3891, 0, 2}, //Barrel
			{362, 3155, 3891, 0, 2}, //Barrel
			{362, 3156, 3891, 0, 2}, //Barrel
			{362, 3157, 3891, 0, 2}, //Barrel
			{362, 3158, 3891, 0, 2}, //Barrel
			{362, 3159, 3891, 0, 2}, //Barrel
			{362, 3160, 3891, 0, 2}, //Barrel
			{362, 3161, 3891, 0, 2}, //Barrel
			{362, 3162, 3891, 0, 2}, //Barrel
			{362, 3163, 3891, 0, 2}, //Barrel
			{362, 3164, 3891, 0, 2}, //Barrel
			{362, 3165, 3891, 0, 2}, //Barrel
			{362, 3166, 3891, 0, 2}, //Barrel
			
			{362, 3166, 3890, 0, 2}, //Barrel
			{362, 3166, 3889, 0, 2}, //Barrel
			{362, 3166, 3888, 0, 2}, //Barrel
			{362, 3166, 3887, 0, 2}, //Barrel
			{362, 3166, 3886, 0, 2}, //Barrel
			{362, 3166, 3885, 0, 2}, //Barrel
			{362, 3166, 3884, 0, 2}, //Barrel
			{362, 3166, 3883, 0, 2}, //Barrel
			{362, 3166, 3882, 0, 2}, //Barrel
			{362, 3166, 3881, 0, 2}, //Barrel
			{362, 3165, 3881, 0, 2}, //Barrel
			{362, 3164, 3881, 0, 2}, //Barrel
			
			{362, 3195, 3892, 0, 2}, //Barrel
			{362, 3195, 3891, 0, 2}, //Barrel
			{362, 3195, 3890, 0, 2}, //Barrel
			{362, 3195, 3889, 0, 2}, //Barrel
			{362, 3195, 3888, 0, 2}, //Barrel
			{362, 3195, 3887, 0, 2}, //Barrel
			{362, 3195, 3886, 0, 2}, //Barrel
			{362, 3195, 3885, 0, 2}, //Barrel
			{362, 3195, 3884, 0, 2}, //Barrel
			{362, 3195, 3883, 0, 2}, //Barrel
			{362, 3195, 3882, 0, 2}, //Barrel
			{362, 3195, 3881, 0, 2}, //Barrel
			{362, 3195, 3880, 0, 2}, //Barrel
			{362, 3195, 3879, 0, 2}, //Barrel
			{362, 3195, 3878, 0, 2}, //Barrel
			
			{362, 3194, 3878, 0, 2}, //Barrel
			{362, 3193, 3878, 0, 2}, //Barrel
			{362, 3192, 3878, 0, 2}, //Barrel
			{362, 3191, 3878, 0, 2}, //Barrel
			{362, 3190, 3878, 0, 2}, //Barrel
			{362, 3189, 3878, 0, 2}, //Barrel
			{362, 3188, 3878, 0, 2}, //Barrel
			{362, 3187, 3878, 0, 2}, //Barrel
			{362, 3186, 3878, 0, 2}, //Barrel
			{362, 3185, 3878, 0, 2}, //Barrel
			{362, 3184, 3878, 0, 2}, //Barrel
			
			{362, 3184, 3879, 0, 2}, //Barrel
			{362, 3184, 3880, 0, 2}, //Barrel
			{362, 3184, 3881, 0, 2}, //Barrel
			{362, 3184, 3882, 0, 2}, //Barrel
			{362, 3184, 3883, 0, 2}, //Barrel
			{362, 3184, 3884, 0, 2}, //Barrel
			{362, 3184, 3885, 0, 2}, //Barrel
			{362, 3184, 3886, 0, 2}, //Barrel
			{362, 3184, 3887, 0, 2}, //Barrel
			{362, 3184, 3888, 0, 2}, //Barrel
			{362, 3184, 3889, 0, 2}, //Barrel
			{362, 3184, 3890, 0, 2}, //Barrel
			{362, 3184, 3891, 0, 2}, //Barrel
			{362, 3184, 3892, 0, 2}, //Barrel
			{362, 3184, 3893, 0, 2}, //Barrel
			{362, 3184, 3894, 0, 2}, //Barrel
			
			{362, 3185, 3894, 0, 2}, //Barrel
			{362, 3186, 3894, 0, 2}, //Barrel
			{362, 3187, 3894, 0, 2}, //Barrel
			{362, 3188, 3894, 0, 2}, //Barrel
			{362, 3189, 3894, 0, 2}, //Barrel
			{362, 3190, 3894, 0, 2}, //Barrel
			{362, 3191, 3894, 0, 2}, //Barrel
			{362, 3192, 3894, 0, 2}, //Barrel
			{362, 3193, 3894, 0, 2}, //Barrel
			{362, 3194, 3894, 0, 2}, //Barrel
			{362, 3195, 3894, 0, 2}, //Barrel
			{362, 3195, 3893, 0, 2}, //Barrel
			
			{362, 3320, 3905, 0, 2}, //Barrel
			{362, 3320, 3904, 0, 2}, //Barrel
			
			{362, 3350, 3911, 0, 2}, //Barrel
			{362, 3350, 3912, 0, 2}, //Barrel
			{362, 3350, 3913, 0, 2}, //Barrel
			{362, 3349, 3913, 0, 2}, //Barrel
			{362, 3349, 3914, 0, 2}, //Barrel
			{362, 3348, 3914, 0, 2}, //Barrel
			{362, 3347, 3914, 0, 2}, //Barrel
			{362, 3346, 3914, 0, 2}, //Barrel
			
			{362, 3336, 3907, 0, 2}, //Barrel
			{362, 3337, 3906, 0, 2}, //Barrel
			{362, 3338, 3905, 0, 2}, //Barrel
			{362, 3339, 3904, 0, 2}, //Barrel
			{362, 3340, 3903, 0, 2}, //Barrel
			{362, 3341, 3902, 0, 2}, //Barrel
			{362, 3343, 3901, 0, 2}, //Barrel
			{362, 3344, 3900, 0, 2}, //Barrel
			{362, 3346, 3900, 0, 2}, //Barrel
			{362, 3342, 3902, 0, 2}, //Barrel
			{362, 3345, 3900, 0, 2}, //Barrel
			
			{362, 3322, 3859, 0, 2}, //Barrel
			{362, 3323, 3859, 0, 2}, //Barrel
			{362, 3324, 3859, 0, 2}, //Barrel
			{362, 3325, 3859, 0, 2}, //Barrel
			{362, 3326, 3859, 0, 2}, //Barrel
			{362, 3327, 3859, 0, 2}, //Barrel
			{362, 3328, 3859, 0, 2}, //Barrel
			{362, 3329, 3859, 0, 2}, //Barrel
			{362, 3330, 3859, 0, 2}, //Barrel
			{362, 3331, 3859, 0, 2}, //Barrel
			{362, 3332, 3859, 0, 2}, //Barrel
			{362, 3333, 3859, 0, 2}, //Barrel
			{362, 3334, 3859, 0, 2}, //Barrel
			{362, 3335, 3859, 0, 2}, //Barrel
			{362, 3336, 3859, 0, 2}, //Barrel
			{362, 3337, 3859, 0, 2}, //Barrel
			{362, 3338, 3859, 0, 2}, //Barrel
			{362, 3339, 3859, 0, 2}, //Barrel
			{362, 3340, 3859, 0, 2}, //Barrel
			{362, 3341, 3859, 0, 2}, //Barrel
			{362, 3342, 3859, 0, 2}, //Barrel
			{362, 3343, 3859, 0, 2}, //Barrel
			{362, 3344, 3859, 0, 2}, //Barrel
			{362, 3345, 3859, 0, 2}, //Barrel
			{362, 3346, 3859, 0, 2}, //Barrel
			{362, 3347, 3859, 0, 2}, //Barrel
			{362, 3348, 3859, 0, 2}, //Barrel
			{362, 3349, 3859, 0, 2}, //Barrel
			
			{362, 3248, 3850, 0, 2}, //Barrel
			{362, 3249, 3850, 0, 2}, //Barrel
			{362, 3250, 3850, 0, 2}, //Barrel
			{362, 3251, 3850, 0, 2}, //Barrel
			{362, 3252, 3850, 0, 2}, //Barrel
			
			{362, 3124, 3855, 0, 2}, //Barrel
			{362, 3125, 3855, 0, 2}, //Barrel
			{362, 3126, 3855, 0, 2}, //Barrel
			{362, 3127, 3855, 0, 2}, //Barrel
			{362, 3128, 3855, 0, 2}, //Barrel
			{362, 3129, 3855, 0, 2}, //Barrel
			{362, 3130, 3855, 0, 2}, //Barrel
			{362, 3131, 3855, 0, 2}, //Barrel
			{362, 3132, 3855, 0, 2}, //Barrel
			{362, 3133, 3855, 0, 2}, //Barrel
			{362, 3134, 3855, 0, 2}, //Barrel
			{362, 3135, 3855, 0, 2}, //Barrel
			{362, 3136, 3855, 0, 2}, //Barrel
			{362, 3137, 3855, 0, 2}, //Barrel
			{362, 3138, 3855, 0, 2}, //Barrel
			
			{362, 3139, 3855, 0, 2}, //Barrel
			{362, 3140, 3855, 0, 2}, //Barrel
			{362, 3141, 3855, 0, 2}, //Barrel
			{362, 3142, 3855, 0, 2}, //Barrel
			{362, 3143, 3855, 0, 2}, //Barrel
			{362, 3144, 3855, 0, 2}, //Barrel
			{362, 3145, 3855, 0, 2}, //Barrel
			{362, 3146, 3855, 0, 2}, //Barrel
			{362, 3147, 3855, 0, 2}, //Barrel
			{362, 3148, 3855, 0, 2}, //Barrel
			{362, 3149, 3855, 0, 2}, //Barrel
			{362, 3150, 3855, 0, 2}, //Barrel
			{362, 3151, 3855, 0, 2}, //Barrel
			{362, 3152, 3855, 0, 2}, //Barrel
			{362, 3153, 3855, 0, 2}, //Barrel
			
			{362, 3154, 3855, 0, 2}, //Barrel
			{362, 3155, 3855, 0, 2}, //Barrel
			{362, 3156, 3855, 0, 2}, //Barrel
			{362, 3157, 3855, 0, 2}, //Barrel
			{362, 3158, 3855, 0, 2}, //Barrel
			{362, 3159, 3855, 0, 2}, //Barrel
			{362, 3160, 3855, 0, 2}, //Barrel
			{362, 3161, 3855, 0, 2}, //Barrel
			{362, 3162, 3855, 0, 2}, //Barrel
			{362, 3163, 3855, 0, 2}, //Barrel
			{362, 3164, 3855, 0, 2}, //Barrel
			{362, 3165, 3855, 0, 2}, //Barrel
			{362, 3166, 3855, 0, 2}, //Barrel
			{362, 3167, 3855, 0, 2}, //Barrel
			{362, 3168, 3855, 0, 2}, //Barrel
			
			{362, 3104, 3885, 0, 2}, //Barrel
			{362, 3104, 3886, 0, 2}, //Barrel
			{362, 3104, 3887, 0, 2}, //Barrel
			{362, 3104, 3888, 0, 2}, //Barrel
			{362, 3104, 3889, 0, 2}, //Barrel
			{362, 3104, 3890, 0, 2}, //Barrel
			{362, 3104, 3891, 0, 2}, //Barrel
			{362, 3104, 3892, 0, 2}, //Barrel
			{362, 3104, 3893, 0, 2}, //Barrel
			{362, 3104, 3894, 0, 2}, //Barrel
			{362, 3104, 3895, 0, 2}, //Barrel
			{362, 3104, 3896, 0, 2}, //Barrel
			{362, 3104, 3897, 0, 2}, //Barrel
			{362, 3104, 3898, 0, 2}, //Barrel
			{362, 3104, 3899, 0, 2}, //Barrel
			{362, 3104, 3900, 0, 2}, //Barrel
			{362, 3104, 3901, 0, 2}, //Barrel
			{362, 3104, 3902, 0, 2}, //Barrel
			{362, 3104, 3903, 0, 2}, //Barrel
			
			/*** Construction Portal***/
			{15481, 3107, 3509, 0, 4}, //Portal


			{6552, 2197, 3563, 0, 2}, //ancient
			{2213, 1633, 3674, 0, 1}, //bank

			{18491, 1608, 3667, 0, 1}, //bank
			{18491, 1608, 3668, 0, 1}, //bank
			{18491, 1608, 3669, 0, 1}, //bank
			{18491, 1608, 3670, 0, 1}, //bank
			{10781, 2536, 2528, 1, 1}, //shadowlord
			
			{18491, 1608, 3676, 0, 1}, //bank
			{18491, 1608, 3677, 0, 1}, //bank
			{18491, 1608, 3678, 0, 1}, //bank
			{18491, 1608, 3679, 0, 1}, //bank
			
			
			{365, 2563, 3116, 0, 2}, //Sacks
			{365, 2563, 3117, 0, 2}, //Sacks
			{365, 2563, 3118, 0, 2}, //Sacks
			{365, 2563, 3119, 0, 2}, //Sacks
			{365, 2563, 3121, 0, 2}, //Sacks
			{365, 2563, 3122, 0, 2}, //Sacks
			{365, 2563, 3123, 0, 2}, //Sacks
			{365, 2563, 3124, 0, 2}, //Sacks
			{365, 2563, 3125, 0, 2}, //Sacks
			{365, 2563, 3126, 0, 2}, //Sacks
			{365, 2563, 3127, 0, 2}, //Sacks
			{365, 2563, 3128, 0, 2}, //Sacks
			{365, 2563, 3129, 0, 2}, //Sacks
			{365, 2563, 3129, 0, 2}, //Sacks
			{365, 2563, 3120, 0, 2}, //Sacks
			
			{365, 2564, 3129, 0, 2}, //Sacks
			{365, 2565, 3129, 0, 2}, //Sacks
			{365, 2566, 3129, 0, 2}, //Sacks
			{365, 2567, 3129, 0, 2}, //Sacks
			{365, 2568, 3129, 0, 2}, //Sacks
			{365, 2569, 3129, 0, 2}, //Sacks
			{365, 2570, 3129, 0, 2}, //Sacks
			{365, 2571, 3129, 0, 2}, //Sacks
			{365, 2572, 3129, 0, 2}, //Sacks
			{365, 2573, 3129, 0, 2}, //Sacks
			{365, 2574, 3129, 0, 2}, //Sacks
			{365, 2575, 3129, 0, 2}, //Sacks
			{365, 2576, 3129, 0, 2}, //Sacks
			{365, 2576, 3128, 0, 2}, //Sacks
			{365, 2576, 3127, 0, 2}, //Sacks
			{365, 2576, 3126, 0, 2}, //Sacks
			{365, 2576, 3125, 0, 2}, //Sacks
			{365, 2576, 3124, 0, 2}, //Sacks
			{365, 2576, 3123, 0, 2}, //Sacks
			{365, 2576, 3122, 0, 2}, //Sacks
			{365, 2576, 3121, 0, 2}, //Sacks
			{365, 2576, 3120, 0, 2}, //Sacks
			{365, 2576, 3119, 0, 2}, //Sacks
			{365, 2576, 3118, 0, 2}, //Sacks
			
			{365, 2564, 3116, 0, 2}, //Sacks
			{365, 2565, 3116, 0, 2}, //Sacks
			{365, 2566, 3116, 0, 2}, //Sacks
			{365, 2567, 3116, 0, 2}, //Sacks
			{365, 2568, 3116, 0, 2}, //Sacks
			{365, 2569, 3116, 0, 2}, //Sacks
			{365, 2570, 3116, 0, 2}, //Sacks
			{365, 2571, 3116, 0, 2}, //Sacks
			{365, 2572, 3116, 0, 2}, //Sacks
			{365, 2573, 3116, 0, 2}, //Sacks
			{365, 2574, 3116, 0, 2}, //Sacks
			{365, 2575, 3116, 0, 2}, //Sacks
			{365, 2576, 3116, 0, 2}, //Sacks
			{365, 2576, 3116, 0, 2}, //Sacks
			{365, 2576, 3116, 0, 2}, //Sacks
			{365, 2576, 3116, 0, 2}, //Sacks
			{365, 2576, 3116, 0, 2}, //Sacks
			{365, 2576, 3116, 0, 2}, //Sacks
			{365, 2576, 3116, 0, 2}, //Sacks
			{365, 2576, 3116, 0, 2}, //Sacks
			{365, 2576, 3116, 0, 2}, //Sacks
			{365, 2576, 3116, 0, 2}, //Sacks
			{365, 2576, 3116, 0, 2}, //Sacks
			{365, 2576, 3116, 0, 2}, //Sacks
			{365, 2576, 3117, 0, 2}, //Sacks
			
			{365, 2568, 3122, 0, 2}, //Sacks
			{365, 2568, 3123, 0, 2}, //Sacks
			{365, 2568, 3124, 0, 2}, //Sacks
			{365, 2568, 3125, 0, 2}, //Sacks
			{365, 2569, 3122, 0, 2}, //Sacks
			{365, 2569, 3123, 0, 2}, //Sacks
			{365, 2569, 3124, 0, 2}, //Sacks
			{365, 2569, 3125, 0, 2}, //Sacks
			{365, 2571, 3122, 0, 2}, //Sacks
			{365, 2571, 3123, 0, 2}, //Sacks
			{365, 2571, 3124, 0, 2}, //Sacks
			{365, 2571, 3125, 0, 2}, //Sacks
			{365, 2570, 3125, 0, 2}, //Sacks
			
			
			{818, 1608, 3674, 0, 3}, //blockade of armour 
			{818, 1608, 3673, 0, 3}, //blockade of armour 
			{818, 1608, 3672, 0, 3}, //blockade of armour 
			
			//{409, 1611, 3668, 0, 1}, //altar
			{411, 1611, 3670, 0, 1}, //altar
			{6552, 1611, 3675, 0, 1}, //altar
			{13179, 1611, 3678, 0, 1}, //altar
			
			{172, 2529, 2535, 1, 2}, //chest
			{10620, 2530, 2534, 1, 2}, //chest
			{6420, 1625, 3682, 0, 2}, //chest
			
			/*** Bas Extreme Donator zone ***/
			
			{47180, 3413, 2919, 0, 0}, //Frost drags teleport
			
			{4875, 3451, 2881, 0, 0}, //Thief stalls
			{4874, 3450, 2881, 0, 0}, //Thief stalls
			{4876, 3449, 2881, 0, 0}, //Thief stalls
			{4877, 3448, 2881, 0, 0}, //Thief stalls
			{4878, 3447, 2881, 0, 0}, //Thief stalls
			{13493, 3446, 2881, 0, 0}, //Thief stalls
			{4875, 3200, 3431, 0, 0}, //theif stalls
			{4874, 3200, 3432, 0, 0}, //theif stalls
			{4876, 3200, 3433, 0, 0}, //theif stalls
			{4877, 3200, 3434, 0, 0}, //theif stalls
			{4878, 3200, 3435, 0, 0}, //theif stalls
			//theif stalls
			
			{10620, 3200, 3434, 0, 0}, //WildyWyrm chest
			
			//DRAPHT ZONE//
			{2213, 3425, 2930, 0, 0}, //Banks
			//END//
			/*
			* Remove Uber Zone Objects
			*/
			{-1, 2425, 4714, 0, 0},
			{-1, 2420, 4716, 0, 0},
			{-1, 2426, 4726, 0, 0},
			{-1, 2420, 4709, 0, 0},
			{-1, 2419, 4698, 0, 0},
			{-1, 2420, 4700, 0, 0},
			{-1, 2399, 4721, 0, 0},
			{-1, 2398, 4721, 0, 0},
			{-1, 2399, 4720, 0, 0},
			{-1, 2395, 4722, 0, 0},
			{-1, 2398, 4717, 0, 0},
			{-1, 2396, 4717, 0, 0},
			{-1, 2396, 4718, 0, 0},
			{-1, 2396, 4719, 0, 0},
			{-1, 2395, 4718, 0, 0},
			{-1, 2394, 4711, 0, 0},
			{-1, 2396, 4711, 0, 0},
			{-1, 2397, 4711, 0, 0},
			{-1, 2397, 4713, 0, 0},
			{-1, 2399, 4713, 0, 0},
			{-1, 2402, 4726, 0, 0},
			{-1, 2407, 4728, 0, 0},
			{-1, 2405, 4724, 0, 0},
			{-1, 2409, 4705, 0, 0},
			{-1, 2410, 4704, 0, 0},
			{-1, 2407, 4702, 0, 0},
			{-1, 2407, 4701, 0, 0},
			{-1, 2408, 4701, 0, 0},
			{-1, 2412, 4701, 0, 0},
			{-1, 2413, 4701, 0, 0},
			{-1, 2414, 4703, 0, 0},
			{-1, 2416, 4714, 0, 0},
			{-1, 2412, 4732, 0, 0},
			{-1, 2413, 4729, 0, 0},
			{-1, 2414, 4733, 0, 0},
			{-1, 2415, 4730, 0, 0},
			{-1, 2416, 4730, 0, 0},
			{-1, 2416, 4731, 0, 0},
			{-1, 2419, 4731, 0, 0},
			{-1, 2420, 4731, 0, 0},
			{-1, 2420, 4732, 0, 0},
			{-1, 2415, 4725, 0, 0},
			{-1, 2417, 4729, 0, 0},
			{-1, 2418, 4727, 0, 0},
			{-1, 2418, 4723, 0, 0},
			{-1, 2419, 4722, 0, 0},
			{-1, 2420, 4726, 0, 0},
			{-1, 2415, 4725, 0, 0},
			{-1, 2417, 4729, 0, 0},
			{-1, 2418, 4727, 0, 0},
			{-1, 2418, 4723, 0, 0},
			{-1, 2419, 4722, 0, 0},
			{-1, 2420, 4726, 0, 0},
			
			{13405, 3439, 2906, 0, 1}, //House teleport
			
			{4306, 3431, 2872, 0, 2}, //Anvil
			{6189, 3433, 2871, 0, 3}, //Furnace
			
			{10091, 3452, 2871, 0, 0}, //Rocktail fishing 
			{10091, 3449, 2867, 0, 0}, //Rocktail fishing 
			
			{14859, 3439, 2872, 0, 0}, //Rune ore 
			{14859, 3442, 2871, 0, 0}, //Rune ore 
			{14859, 3444, 2870, 0, 0}, //Rune ore 
			{14859, 3445, 2868, 0, 0}, //Rune ore 
			{14859, 3443, 2869, 0, 0}, //Rune ore 
			{14859, 3441, 2869, 0, 0}, //Rune ore 
			{14859, 3439, 2870, 0, 0}, //Rune ore 
			{14859, 3437, 2872, 0, 0}, //Rune ore 
			
			{1306, 3422, 2870, 0, 0}, //Magic tree's 
			{1306, 3422, 2872, 0, 0}, //Magic tree's 
			{1306, 3422, 2874, 0, 0}, //Magic tree's 
			{1306, 3422, 2876, 0, 0}, //Magic tree's 
			{1306, 3422, 2878, 0, 0}, //Magic tree's 
			{1306, 3422, 2880, 0, 0}, //Magic tree's 
			{1306, 3422, 2882, 0, 0}, //Magic tree's 

			//{880, 2535, 2533, 1, 0}, //Restore fountain
			
			{3192, 3434, 2922, 0, 2}, //Scoreboard
			
			{409, 3443, 2918, 0, 2}, //Altar 
			{6552, 3439, 2918, 0, 2}, //Altar 
			{8749, 3445, 2913, 0, 3}, //Altar 
			{411, 3441, 2910, 0, 0}, //Altar 
			{13179, 3439, 2912, 0, 1}, //Altar 

			{2213, 3425, 2930, 0, 0}, //Banks
			{2213, 3426, 2930, 0, 0}, //Banks
			{2213, 3427, 2930, 0, 0}, //Banks
			{2213, 3428, 2930, 0, 0}, //Banks
			
			{2213, 3426, 2894, 0, 1}, //Banks
			{2213, 3426, 2893, 0, 1}, //Banks
			{2213, 3426, 2892, 0, 1}, //Banks
			{2213, 3426, 2891, 0, 1}, //Banks
			{2213, 3426, 2890, 0, 1}, //Banks
			{2213, 3426, 2889, 0, 1}, //Banks


			{7171, 2525, 2537, 0, 0},//invention



			{10503, 3456, 2876, 0, 0}, //rocks to fix random wall
			{10503, 3456, 2877, 0, 0}, //rocks to fix random wall
			{10503, 3456, 2878, 0, 0}, //rocks to fix random wall
			{10503, 3456, 2879, 0, 0}, //rocks to fix random wall		
			{10503, 3446, 2889, 0, 0}, //rocks to fix random wall
			{10503, 3438, 2904, 0, 0}, //rocks to fix random wall
			{10503, 3415, 2917, 0, 0}, //rocks to fix random wall
			{10503, 3411, 2925, 0, 0}, //rocks to fix random wall
			
			/*** Bas Extreme Donator zone end ***/
			
			
			{10091, 2337, 3703, 0, 0}, //fishing rocktail @ fishing location
			
			/*** Bas Gambling area ***/
			{2213, 2729, 3467, 0, 3}, //bank
			{2213, 2729, 3468, 0, 3}, //bank
			{2213, 2729, 3469, 0, 3}, //bank
			{2213, 2729, 3470, 0, 3}, //bank
			{2213, 2729, 3471, 0, 3}, //bank
		
			
			/*** Bas Varrock home ***/

			{3422, 3208, 3438, 0, 2}, //crystal key chest
			
			
			{13179, 3226, 3433, 0, 3}, //veng
			{409, 3205, 3434, 0, 1}, //pray altar
			{411, 2524, 2503, 0, 1}, //turmoil
			{884, 3195, 3436, 0, 1}, //well
			{6420, 3091, 3500, 0, 0}, //key chest
			
			{3192, 3220, 3435, 0, 2}, //scoreboard
			
			{2213, 3211, 3438, 0, 2}, //bank
			{2213, 3212, 3438, 0, 2}, //bank
			{2213, 3213, 3438, 0, 2}, //bank
			{2213, 3214, 3438, 0, 2}, //bank
			{1746, 3215, 3438, 0, 0}, //ladder
			
			{-1, 3217, 3436, 0, 0}, //remove stall
			{-1, 3219, 3436, 0, 0}, //remove stall
			{-1, 3220, 3431, 0, 0}, //remove stall
			{-1, 3220, 3425, 0, 0}, //remove stall
			{-1, 3223, 3434, 0, 0}, //remove oak
			{-1, 3226, 3431, 0, 0}, //remove plant
			
			{-1, 3159, 3883, 0, 0}, //Barrel
			{-1, 3154, 3881, 0, 0}, //Barrel
			
			/*** Bas Varrock home end ***/


			{411, 3223, 3428, 0, 2},
			{409,2504,2503,0,1},
			//{411,3228,3432,0,1},
			{3422, 2510, 2519, 0, 1},
			
			/*** Bas UBER Donator zone ***/
			
			{8749, 2307, 9806, 0, 1}, //special attack altar 
			
//			{411, 2307, 9807, 0, 1}, //pray altar
//			{409, 2307, 9805, 0, 1}, //pray switch altar

			{2213, 2317, 9798, 0, 0}, //bank
			{2213, 2316, 9798, 0, 0}, //bank
			{2213, 2315, 9798, 0, 0}, //bank
			{2213, 2314, 9798, 0, 0}, //bank
			{2213, 2313, 9798, 0, 0}, //bank
			{2213, 2312, 9798, 0, 0}, //bank
			{2213, 2311, 9798, 0, 0}, //bank
			{2213, 2310, 9798, 0, 0}, //bank
			{2213, 2309, 9798, 0, 0}, //bank

			{38660, 2518, 2516, 1, 2}, //good rock
			//{3422, 2533, 2535, 1, 2}, //upgrade
			
			{-1, 2325, 9798, 0, 0}, //remove box
			{-1, 2324, 9798, 0, 0}, //remove barrel
			{-1, 2324, 9799, 0, 0}, //remove boxes
			{-1, 2320, 9798, 0, 0}, //remove chair
			{-1, 2319, 9799, 0, 0}, //remove workspace
			{-1, 2319, 9798, 0, 0}, //remove workspace
			{-1, 2318, 9798, 0, 0}, //remove workspace
			{-1, 2309, 9799, 0, 0}, //remove workspace
			{-1, 2321, 9800, 0, 0}, //remove workspace
			{-1, 2309, 9806, 0, 0}, //remove workspace
			{-1, 2318, 9801, 0, 0}, //remove workspace
			{-1, 2327, 9800, 0, 0}, //remove workspace
			{-1, 2327, 9799, 0, 0}, //remove workspace
			{-1, 2327, 9798, 0, 0}, //remove workspace
			{-1, 2326, 9798, 0, 0}, //remove workspace
			
			{-1, 1616, 3657, 0, 0}, //remove boil
			{-1, 1616, 3688, 0, 0}, //remove boil
			{-1, 1616, 3690, 0, 0}, //suit of armour
			{-1, 1617, 3662, 0, 0}, //box
			{-1, 1618, 3662, 0, 0}, //box
			{-1, 1618, 3662, 0, 0}, //box
			{-1, 1626, 3684, 0, 1}, //wooden cart
			{-1, 1642, 3682, 0, 1}, //wooden

			{14859, 2330, 9795, 0, 0}, //rune ore's
			{14859, 2329, 9794, 0, 0}, //rune ore's
			{14859, 2328, 9793, 0, 0}, //rune ore's
			{14859, 2327, 9793, 0, 0}, //rune ore's

			{6189, 2324, 9794, 0, 3}, //Smith bars
			{4306, 2331, 9798, 0, 1}, //Anvil
			{4306, 2331, 9800, 0, 1}, //Anvil

			{13493, 2317, 9801, 0, 2}, //thief stall
			{13493, 2309, 9802, 0, 2}, //thief stall

			{8702, 2323, 9799, 0, 0}, //fish spot

			{2732, 2316, 9809, 0, 0}, //fire

			{1306, 2318, 9807, 0, 0}, //magic trees
			{1306, 2320, 9807, 0, 0}, //magic trees
			{1306, 2322, 9807, 0, 0}, //magic trees

			
			/*** Bas UBER Donator zone end ***/
			
			/*** Bas Donator zone ***/
			
			{11933, 3353, 9622, 0, 0},//Tin Ore
			{11933, 3351, 9620, 0, 0},//Tin Ore
			{11936, 3349, 9622, 0, 0},//Copper Ore
			{11936, 3347, 9620, 0, 0},//Copper Ore
			{11954, 3344, 9620, 0, 0},//Iron Ore
			{11954, 3345, 9622, 0, 0},//Iron Ore
			{11954, 3343, 9623, 0, 0},//Iron Ore
			{11963, 3345, 9625, 0, 0},//Coal Ore
			{11963, 3344, 9627, 0, 0},//Coal Ore
			{11963, 3345, 9629, 0, 0},//Coal Ore
			{11963, 3346, 9631, 0, 0},//Coal Ore
			{11951, 3347, 9628, 0, 0},//Gold Ore
			{11951, 3347, 9628, 0, 0},//Gold Ore
			{11951, 3347, 9624, 0, 0},//Gold Ore
			{11947, 3351, 9623, 0, 0},//Mithril Ore
			{11947, 3350, 9626, 0, 0},//Mithril Ore
			{11947, 3349, 9628, 0, 0},//Mithril Ore
			
			{11941, 3373, 9622, 0, 0},//Adamant Ore
			{11941, 3376, 9621, 0, 0},//Adamant Ore
			{11941, 3379, 9622, 0, 0},//Adamant Ore
			{11941, 3383, 9623, 0, 0},//Adamant Ore
			{11941, 3382, 9626, 0, 0},//Adamant Ore
			{11941, 3381, 9629, 0, 0},//Adamant Ore
			{14859, 3378, 9627, 0, 0},//Rune Ore
			{14859, 3375, 9624, 0, 0},//Rune Ore
			
			{1307, 3382, 9651, 0, 0},//Tree's
			{1307, 3381, 9648, 0, 0},//Tree's
			{1307, 3383, 9655, 0, 0},//Tree's
			{1309, 3382, 9657, 0, 0},//Tree's
			{1309, 3378, 9658, 0, 0},//Tree's
			{1309, 3375, 9658, 0, 0},//Tree's
			{1309, 3371, 9657, 0, 0},//Tree's
			
			{1308, 3355, 9657, 0, 0},//Tree's
			{1308, 3351, 9659, 0, 0},//Tree's
			{1281, 3346, 9658, 0, 0},//Tree's
			{1281, 3344, 9656, 0, 0},//Tree's
			{1278, 3344, 9652, 0, 0},//Tree's
			{1278, 3345, 9648, 0, 0},//Tree's
			
			{28716, 3376, 9632, 0, 1},//Obelisk
			
			{13405, 3376, 9646, 0, 1},//Portal construction
			
			{4875, 3350, 9648, 0, 1},//Thief
			{4874, 3350, 9647, 0, 1},//Thief
			{4876, 3350, 9646, 0, 1},//Thief
			{4877, 3350, 9645, 0, 1},//Thief
			{4878, 3350, 9644, 0, 1},//Thief
			{13493, 3350, 9643, 0, 3},//Thief Donor
			
			{8702, 3350, 9636, 0, 0},//Fish barrel
			{12269, 3350, 9634, 0, 0},//Cook
			
			{6189, 3350, 9630, 0, 0},//Furnace
			{4306, 3350, 9632, 0, 1},//Anvil
			
			{14859, 3372, 9626, 0, 0},//Rune Ore
			{14859, 3371, 9626, 0, 0},//Rune Ore
			{14859, 3370, 9626, 0, 0},//Rune Ore
			{14859, 3369, 9626, 0, 0},//Rune Ore
			{14859, 3368, 9626, 0, 0},//Rune Ore
			{14859, 3367, 9626, 0, 0},//Rune Ore
			{14859, 3366, 9626, 0, 0},//Rune Ore
			{14859, 3365, 9626, 0, 0},//Rune Ore
			{14859, 3361, 9626, 0, 0},//Rune Ore
			{14859, 3360, 9626, 0, 0},//Rune Ore
			{14859, 3359, 9626, 0, 0},//Rune Ore
			{14859, 3358, 9626, 0, 0},//Rune Ore
			{14859, 3357, 9626, 0, 0},//Rune Ore
			{14859, 3356, 9626, 0, 0},//Rune Ore
			{14859, 3355, 9626, 0, 0},//Rune Ore
			{14859, 3354, 9626, 0, 0},//Rune Ore

			{210, 3361, 9642, 0, 0},//Ice Light
			{210, 3365, 9642, 0, 0},//Ice Light
			{210, 3361, 9638, 0, 0},//Ice Light
			{210, 3365, 9638, 0, 0},//Ice Light

			{586, 3363, 9640, 0, 2},//Statue

			{4483, 3363, 9652, 0, 0},//Bank North
			{4483, 3376, 9640, 0, 1},//Bank East
			{4483, 3363, 9627, 0, 4},//Bank South
			{4483, 3351, 9640, 0, 3},//Bank West

			{1306, 3355, 9652, 0, 0},//Magic trees
			{1306, 3357, 9652, 0, 0},//Magic trees
			{1306, 3359, 9652, 0, 0},//Magic trees

			{1306, 3370, 9652, 0, 0},//Magic trees
			{1306, 3368, 9652, 0, 0},//Magic trees
			{1306, 3366, 9652, 0, 0},//Magic trees
			
			/* REMOVE PORTALS */
			{-1, 3353, 9640, 0, 0}, //Delete Portals
			{-1, 3363, 9629, 0, 0}, //Delete Portals
			{-1, 3374, 9640, 0, 0}, //Delete Portals
			{-1, 3363, 9650, 0, 0}, //Delete Portals
			
			/* NORTH EAST REMOVE PILE'S */
			{-1, 3371, 9658, 0, 0}, //Delete Pile's corners
			{-1, 3375, 9657, 0, 0}, //Delete Pile's corners
			{-1, 3377, 9653, 0, 0}, //Delete Pile's corners
			{-1, 3379, 9655, 0, 0}, //Delete Pile's corners
			{-1, 3381, 9657, 0, 0}, //Delete Pile's corners
			{-1, 3381, 9653, 0, 0}, //Delete Pile's corners
			{-1, 3381, 9650, 0, 0}, //Delete Pile's corners
			
			/* NORTH WEST REMOVE PILE'S */
			{-1, 3346, 9651, 0, 0}, //Delete Pile's corners
			{-1, 3348, 9652, 0, 0}, //Delete Pile's corners
			{-1, 3345, 9654, 0, 0}, //Delete Pile's corners
			{-1, 3348, 9655, 0, 0}, //Delete Pile's corners
			{-1, 3352, 9654, 0, 0}, //Delete Pile's corners
			{-1, 3345, 9657, 0, 0}, //Delete Pile's corners
			{-1, 3350, 9657, 0, 0}, //Delete Pile's corners
			{-1, 3354, 9658, 0, 0}, //Delete Pile's corners
			{-1, 3356, 9657, 0, 0}, //Delete Pile's corners
			
			/* SOUTH EAST REMOVE PILE'S */
			{-1, 3381, 9727, 0, 0}, //Delete Pile's corners
			{-1, 3378, 9625, 0, 0}, //Delete Pile's corners
			{-1, 3376, 9624, 0, 0}, //Delete Pile's corners
			{-1, 3381, 9623, 0, 0}, //Delete Pile's corners
			{-1, 3379, 9621, 0, 0}, //Delete Pile's corners
			{-1, 3374, 9621, 0, 0}, //Delete Pile's corners
			{-1, 3370, 9621, 0, 0}, //Delete Pile's corners
			{-1, 3381, 9627, 0, 0}, //Delete Pile's corners
			
			/* SOUTH WEST REMOVE PILE'S */
			{-1, 3355, 9621, 0, 0}, //Delete Pile's corners
			{-1, 3352, 9622, 0, 0}, //Delete Pile's corners
			{-1, 3350, 9621, 0, 0}, //Delete Pile's corners
			{-1, 3348, 9625, 0, 0}, //Delete Pile's corners
			{-1, 3347, 9620, 0, 0}, //Delete Pile's corners
			{-1, 3345, 9623, 0, 0}, //Delete Pile's corners
			{-1, 3347, 9628, 0, 0}, //Delete Pile's corners
			{-1, 3345, 9628, 0, 0}, //Delete Pile's corners
			
			/* TREASURE CHEST */
			{ 18806, 2581, 2517, 1, 3},

			/*** Bas Donator zone end ***/
			
			//H'ween even chest
			{2079, 2576, 9876, 0, 0},
			/**Grand EXCHANGE**/
			{8799, 3091, 3495, 0, 3},
			
		//lumby cows gate
		{2344, 3253, 3266, 0, 0},
		{3192, 3084, 3485, 0, 4},
		{-1, 3084, 3487, 0, 2},
		
		
		/* ZULRAH */
		
		{1, 3038, 3415, 0, 0},
		{357, 3034, 3422, 0, 0},
		{ 25136, 2278, 3070, 0, 0 },
		{ 25136, 2278, 3065, 0, 0 },
		{ 25138, 2273, 3066, 0, 0 },
		{ 25136, 2272, 3065, 0, 0 },
		{ 25139, 2267, 3065, 0, 0 },
		{ 25136, 2260, 3081, 0, 0 },
		{401, 3503, 3567, 0, 0},
		{401, 3504, 3567, 0, 0},
		{1309, 2715, 3465, 0, 0},
		{1309, 2709, 3465, 0, 0},
		{1309, 2709, 3458, 0, 0},
		{1306, 2705, 3465, 0, 0},
		{1306, 2705, 3458, 0, 0},
		{-1, 2715, 3466, 0, 0},
		{-1, 2712, 3466, 0, 0},
		{-1, 2713, 3464, 0, 0},
		{-1, 2711, 3467, 0, 0},
		{-1, 2710, 3465, 0, 0},
		{-1, 2709, 3464, 0, 0},
		{-1, 2708, 3466, 0, 0},
		{-1, 2707, 3467, 0, 0},
		{-1, 2704, 3465, 0, 0},
		{-1, 2714, 3466, 0, 0},
		{-1, 2705, 3457, 0, 0},
		{-1, 2709, 3461, 0, 0},
		{-1, 2709, 3459, 0, 0},
		{-1, 2708, 3458, 0, 0},
		{-1, 2710, 3457, 0, 0},
		{-1, 2711, 3461, 0, 0},
		{-1, 2713, 3461, 0, 0},
		{-1, 2712, 3459, 0, 0},
		{-1, 2712, 3457, 0, 0},
		{-1, 2714, 3458, 0, 0},
		{-1, 2705, 3459, 0, 0},
		{-1, 2705, 3464, 0, 0},
		{2274, 2912, 5300, 2, 0},
		{2274, 2914, 5300, 1, 0},
		{2274, 2919, 5276, 1, 0},
		{2274, 2918, 5274, 0, 0},
		{2274, 3001, 3931, 0, 0},
		{-1, 2884, 9797, 0, 2},
		{4483, 2909, 4832, 0, 3},
		{4483, 2901, 5201, 0, 2},
		{4483, 2902, 5201, 0, 2},
		{9326, 3001, 3960, 0, 0},
		{1662, 3112, 9677, 0, 2},
		{1661, 3114, 9677, 0, 2},
		{1661, 3122, 9664, 0, 1},
		{1662, 3123, 9664, 0, 2},
		{1661, 3124, 9664, 0, 3},
		{4483, 2918, 2885, 0, 3},
		{12356, 1893, 5346, 0, 0},
		{2182, 1893, 5348, 0, 1},
		{10251, 2560, 2529, 1, 0},
		{1746, 3090, 3492, 0, 1},
		{2644, 2737, 3444, 0, 0},
		{-1, 2608, 4777, 0, 0},
		{-1, 2601, 4774, 0, 0},
		{-1, 2611, 4776, 0, 0},
		
		
		
		
		/**Oude ruse Member Zone*/
		
//		{2344, 3421, 2908, 0, 0}, //Rock blocking
//        {2345, 3438, 2909, 0, 0},
//        {2344, 3435, 2909, 0, 0},
//        {2344, 3432, 2909, 0, 0},
//        {2345, 3431, 2909, 0, 0},
//        {2344, 3428, 2921, 0, 1},
//        {2344, 3428, 2918, 0, 1},
//        {2344, 3428, 2915, 0, 1},
//        {2344, 3428, 2912, 0, 1},
//        {2345, 3428, 2911, 0, 1},
//        {2344, 3417, 2913, 0, 1},
//        {2344, 3417, 2916, 0, 1},
//        {2344, 3417, 2919, 0, 1},
//        {2344, 3417, 2922, 0, 1},
//        {2345, 3417, 2912, 0, 0},
//        {2346, 3418, 2925, 0, 0},
//        {10378, 3426, 2907, 0, 0},
//        {8749, 3426, 2923, 0, 2}, //Altar
//        {-1, 3420, 2909, 0, 10}, //Remove crate by mining
//        {-1, 3420, 2923, 0, 10}, //Remove Rockslide by Woodcutting
//        {14859, 3421, 2909, 0, 0}, //Mining
//        {14859, 3419, 2909, 0, 0},
//        {14859, 3418, 2910, 0, 0},
//        {14859, 3418, 2911, 0, 0},
//        {14859, 3422, 2909, 0, 0},
//        {1306, 3418, 2921, 0, 0}, //Woodcutting
//        {1306, 3421, 2924, 0, 0},
//        {1306, 3420, 2924, 0, 0},
//        {1306, 3419, 2923, 0, 0},
//        {1306, 3418, 2922, 0, 0},
//		{-1, 3430, 2912, 0, 2}, 
//		{13493, 3424, 2916, 0, 1},//Armour  stall
		
        /**Oude ruse Member Zone end*/
		
		
		{-1, 3098, 3496, 0, 1},
		{-1, 3095, 3498, 0, 1},
		{-1, 3088, 3509, 0, 1},
		{-1, 3095, 3499, 0, 1},
		{-1, 3085, 3506, 0, 1},
		{30205, 3085, 3509, 0, 3},
		{-1, 3206, 3263, 0, 0},
		{-1, 2794, 2773, 0, 0},
		{2, 2692, 3712, 0, 3},
		{2, 2688, 3712, 0, 1},
		{4483, 3081, 3496, 0, 1},
		{4483, 3081, 3495, 0, 1},
		{4875, 3094, 3500, 0, 0},
		{4874, 3095, 3500, 0, 0},
		{4876, 3096, 3500, 0, 0},
		{4877, 3097, 3500, 0, 0},
		{4878, 3098, 3500, 0, 0},
		{ 11758, 3019, 9740, 0, 0},
		{ 11758, 3020, 9739, 0, 1},
		{ 11758, 3019, 9738, 0, 2},
		{ 11758, 3018, 9739, 0, 3},
		{ 11933, 3028, 9739, 0, 1},
		{ 11933, 3032, 9737, 0, 2},
		{ 11933, 3032, 9735, 0, 0},
		{ 11933, 3035, 9742, 0, 3},
		{ 11933, 3034, 9739, 0, 0},
		{ 11936, 3028, 9737, 0, 1},
		{ 11936, 3029, 9734, 0, 2},
		{ 11936, 3031, 9739, 0, 0},
		{ 11936, 3032, 9741, 0, 3},
		{ 11936, 3035, 9734, 0, 0},
		{ 11954, 3037, 9739, 0, 1},
		{ 11954, 3037, 9735, 0, 2},
		{ 11954, 3037, 9733, 0, 0},
		{ 11954, 3039, 9741, 0, 3},
		{ 11954, 3039, 9738, 0, 0},
		{ 11963, 3039, 9733, 0, 1},
		{ 11964, 3040, 9732, 0, 2},
		{ 11965, 3042, 9734, 0, 0},
		{ 11965, 3044, 9737, 0, 3},
		{ 11963, 3042, 9739, 0, 0},
		{ 11963, 3045, 9740, 0, 1},
		{ 11965, 3043, 9742, 0, 2},
		{ 11964, 3045, 9744, 0, 0},
		{ 11965, 3048, 9747, 0, 3},
		{ 11951, 3048, 9743, 0, 0},
		{ 11951, 3049, 9740, 0, 1},
		{ 11951, 3047, 9737, 0, 2},
		{ 11951, 3050, 9738, 0, 0},
		{ 11951, 3052, 9739, 0, 3},
		{ 11951, 3051, 9735, 0, 0},
		{ 11947, 3049, 9735, 0, 1},
		{ 11947, 3049, 9734, 0, 2},
		{ 11947, 3047, 9733, 0, 0},
		{ 11947, 3046, 9733, 0, 3},
		{ 11947, 3046, 9735, 0, 0},
		{ 11941, 3053, 9737, 0, 1},
		{ 11939, 3054, 9739, 0, 2},
		{ 11941, 3053, 9742, 0, 0},
		{ 14859, 3038, 9748, 0, 3},
		{ 14859, 3044, 9753, 0, 0},
		{ 14859, 3048, 9754, 0, 1},
		{ 14859, 3054, 9746, 0, 2},
		{ 4306, 3026, 9741, 0, 0},
		{ 6189, 3022, 9742, 0, 1},
		{ 75 , 2914, 3452, 0, 2},
		{ 10091 , 2352, 3703, 0, 2},
		{ 11758, 3449, 3722, 0, 0},
		{ 11758, 3450, 3722, 0, 0},
		{ 50547, 3445, 3717, 0, 3},
		{2465, 3085, 3512, 0, 0},
		{ -1, 3090, 3496, 0, 0},
		{ -1, 3090, 3494, 0, 0},
		{ -1, 3092, 3496, 0, 0},
		
		{ -1, 3659, 3508, 0, 0},
		{ 4053, 3660, 3508, 0, 0},
		{ 4051, 3659, 3508, 0, 0},
		{ 1, 3649, 3506, 0, 0},
		{ 1, 3650, 3506, 0, 0},
		{ 1, 3651, 3506, 0, 0},
		{ 1, 3652, 3506, 0, 0},
		{ -1, 2860, 9734, 0, 1},
		{ -1, 2857, 9736, 0, 1},
		{ 664, 2859, 9742, 0, 1},
		{ 1167, 2860, 9742, 0, 1},
		{ 5277, 3691, 3465, 0, 2},
		{ 5277, 3690, 3465, 0, 2},
		{ 5277, 3688, 3465, 0, 2},
		{ 5277, 3687, 3465, 0, 2},
		{ 114, 3093, 3933, 0, 0},
		
	};
	
	
	/**
	 * Contains
	 * @param ObjectId - The object ID to spawn
	 * @param absX - The X position of the object to spawn
	 * @param absY - The Y position of the object to spawn
	 * @param Z - The Z position of the object to spawn
	 * @param face - The position the object will face
	 */
	
	//Objects that are handled by the server on regionchange
	private static final int[][] CUSTOM_OBJECTS_SPAWNS = {
			
	/*** Kourend home area oof ***/
			
			{-1, 1615, 3662, 0, 0}, //delete
			{-1, 1611, 3671, 0, 0}, //delete
			{-1, 1611, 3668, 0, 0}, //delete
			{-1, 1611, 3675, 0, 0}, //delete
			{-1, 1611, 3679, 0, 0}, //delete
			{-1, 1648, 3683, 0, 0}, //delete
			
			{-1, 1633, 3685, 0, 0}, //delete
			{-1, 1635, 3685, 0, 0}, //delete
			{-1, 1639, 3685, 0, 0}, //delete
			{-1, 1640, 3685, 0, 0}, //delete
			{-1, 1637, 3684, 0, 0}, //delete
			{-1, 1637, 3683, 0, 0}, //delete
			{-1, 1634, 3683, 0, 0}, //delete
			{-1, 1634, 3671, 0, 0}, //delete
			{-1, 1638, 3683, 0, 0}, //delete
			{-1, 1645, 3669, 0, 0}, //delete
			{-1, 1626, 3681, 0, 0}, //delete
			
			//{2213, 1635, 3670, 0, 2}, //bank
			//{2213, 1636, 3670, 0, 2}, //bank
			//{2213, 1637, 3670, 0, 2}, //bank
			
			//{2213, 1635, 3676, 0, 2}, //bank
			//{2213, 1636, 3676, 0, 2}, //bank
			//{2213, 1637, 3676, 0, 2}, //bank
			
			//{2213, 1639, 3672, 0, 1}, //bank
			//{2213, 1639, 3673, 0, 1}, //bank


			{2213, 2524, 2499, 0, 0}, //bank
			{2213, 2523, 2499, 0, 0}, //bank
			{2213, 2522, 2499, 0, 0}, //bank
			{2213, 2521, 2499, 0, 0}, //bank
			{2213, 2520, 2499, 0, 0}, //bank
			{2213, 2519, 2499, 0, 0}, //bank
			{2213, 2518, 2499, 0, 0}, //bank
			{2213, 2517, 2499, 0, 0}, //bank
			{2213, 2516, 2499, 0, 0}, //bank
			{2213, 2515, 2499, 0, 0}, //bank
			{2213, 2514, 2499, 0, 0}, //bank
			{2213, 2513, 2499, 0, 0}, //bank
			{2213, 2512, 2499, 0, 0}, //bank
			{2213, 2511, 2499, 0, 0}, //bank
			{2213, 2510, 2499, 0, 0}, //bank
			{2213, 2509, 2499, 0, 0}, //bank
			{2213, 2508, 2499, 0, 0}, //bank
			{2213, 2507, 2499, 0, 0}, //bank
			{2213, 2506, 2499, 0, 0}, //bank
			{2213, 2505, 2499, 0, 0}, //bank
			{2213, 2504, 2499, 0, 0}, //bank


			//{2213, 1639, 3674, 0, 1}, //bank
			
			//{2213, 1633, 3672, 0, 1}, //bank
			//{2213, 1633, 3673, 0, 1}, //bank
			//{2213, 1633, 3676, 0, 1}, //bank
			
			{18491, 1608, 3667, 0, 1}, //bank
			{18491, 1608, 3668, 0, 1}, //bank
			{18491, 1608, 3669, 0, 1}, //bank
			{18491, 1608, 3670, 0, 1}, //bank
			
			{18491, 1608, 3676, 0, 1}, //bank
			{18491, 1608, 3677, 0, 1}, //bank
			{18491, 1608, 3678, 0, 1}, //bank
			{18491, 1608, 3679, 0, 1}, //bank
			
			
			{818, 1608, 3674, 0, 3}, //blockade of armour 
			{818, 1608, 3673, 0, 3}, //blockade of armour 
			{818, 1608, 3672, 0, 3}, //blockade of armour 
			
			//{409, 2532, 2498, 1, 1}, //altar
			{411, 1611, 3670, 0, 1}, //altar
			{6552, 1611, 3675, 0, 1}, //altar
			{13179, 1611, 3678, 0, 1}, //altar
			
			{4875, 2518, 2502, 1, 1}, //thieving
			{4874, 2518, 2501, 1, 1}, //thieving
			{4876, 2518, 2500, 1, 1}, //thieving
			{4878, 2518, 2498, 1, 1}, //thieving
			{4877, 2518, 2499, 1, 1}, //thieving
			
			//{172, 2529, 2535, 1, 1}, //chest
			{10620, 2530, 2534, 1, 2}, //chest
			{6420, 1625, 3682, 0, 2}, //chest
			{38660, 2518, 2516, 1, 2}, //good rock
			{2654, 2539, 2516, 1, 0}, //Restore fountain
			{3422, 2532, 2534, 1, 1}, //upgrade
			{2213, 3333, 3333, 0, 1}, //bank test 3333 3333
			{2213, 2270, 5351, 0, 1}, //bank test 3333 3333
			
			{2079, 2576, 9876, 0, 0},
			{6420, 3091, 3500, 0, 0}, //key chest			
			{10620, 3200, 3434, 0, 3}, //WildyWyrm chest
			/*
			 * Wilderness resource area
			 */
			{-1, 2518, 2497, 1, 1},
			{-1, 3181, 3935, 0, 0},
			{-1, 3174, 3933, 0, 0},
			{-1, 3194, 3929, 0, 0},
			{-1, 3193, 3938, 0, 0},
			
			{-1, 3175, 3927, 0, 0},
			{-1, 3177, 3927, 0, 0},
			{-1, 3176, 3926, 0, 0},
			{-1, 3175, 3925, 0, 0},
			{-1, 3177, 3925, 0, 0},
			
			{-1, 3190, 3938, 0, 0},
			
			{-1, 3192, 3927, 0, 0},
			{-1, 3194, 3927, 0, 0},
			{-1, 3193, 3926, 0, 0},
			{-1, 3192, 3925, 0, 0},
			{-1, 3194, 3925, 0, 0},
			
			{-1, 3193, 3933, 0, 0},
			{-1, 3193, 3935, 0, 0},
			{-1, 3194, 3934, 0, 0},
			{-1, 3195, 3933, 0, 0},
			{-1, 3195, 3935, 0, 0},
			
			{-1, 3192, 3941, 0, 0},
			{-1, 3190, 3941, 0, 0},
			{-1, 3191, 3942, 0, 0},
			{-1, 3192, 3943, 0, 0},
			{-1, 3199, 3943, 0, 0},
			
			{-1, 3184, 3941, 0, 0},
			{-1, 3183, 3940, 0, 0},
			{-1, 3182, 3941, 0, 0},
			
			{-1, 3177, 3941, 0, 0},
			{-1, 3177, 3939, 0, 0},
			{-1, 3176, 3940, 0, 0},
			{-1, 3175, 3941, 0, 0},
			{-1, 3175, 3939, 0, 0},
			
			//^^^^^^^^^^^^
			/*
			 * Object deletions
			 */
			
			{1306, 3181, 3935, 0, 0}, //Magic tree
			{1306, 3193, 3938, 0, 0},
			
			{1309, 3174, 3933, 0, 0}, //Yew tree
			{1309, 3194, 3929, 0, 0},
			
			{4306, 3190, 3938, 0, 0}, //anvil
			
			{2732, 3186, 3934, 0, 0}, //Fire
			
			{14859, 3175, 3927, 0, 0}, //rune ores
			{14859, 3177, 3927, 0, 0},
			{14859, 3176, 3926, 0, 0},
			{14859, 3175, 3925, 0, 0},
			{14859, 3177, 3925, 0, 0}, //rune ores
			
			{11939, 3192, 3927, 0, 0}, //addy ores
			{11941, 3194, 3927, 0, 0},
			{11941, 3193, 3926, 0, 0},
			{11939, 3192, 3925, 0, 0},
			{11939, 3194, 3925, 0, 0},
			
			{11947, 3193, 3933, 0, 0}, //mith ores
			{11947, 3193, 3935, 0, 0},
			{11947, 3194, 3934, 0, 0},
			{11963, 3195, 3933, 0, 0}, //coal ores
			{11963, 3195, 3935, 0, 0},
			
			{11963, 3192, 3941, 0, 0}, //coal ores
			{11963, 3190, 3941, 0, 0},
			{11963, 3191, 3942, 0, 0},
			{11963, 3192, 3943, 0, 0},
			{11963, 3190, 3943, 0, 0},
			
			{4876, 3184, 3941, 0, 0}, //thieving stalls
			{4877, 3183, 3940, 0, 0}, //thieving stalls
			{4878, 3182, 3941, 0, 0}, //thieving stalls
			
			{10091, 3182, 3926, 0, 0}, //fishing spot
			{10091, 3186, 3926, 0, 0}, //fishing spot
			
			{9390, 3189, 3929, 0, 0}, //Forge
			
			{4926, 3174, 3930, 0, 0}, //crystals
			{4927, 3174, 3931, 0, 0}, //crystals
			{4928, 3176, 3931, 0, 0}, //crystals
			
			/*
			 * End of wilderness resource area
			 */
			
			
			/*
			 * mushroom plants objects
			 */
			{385, 2355, 3848, 0, 3}, //mushroom plants
			{385, 2420, 3846, 0, 3}, //mushroom plants
			{385, 2419, 3846, 0, 3}, //mushroom plants
			{385, 2314, 3848, 0, 3}, //mushroom plants
			
			{385, 2335, 3880, 0, 3}, //mushroom plants
			{385, 2335, 3881, 0, 3}, //mushroom plants
			{385, 2335, 3882, 0, 3}, //mushroom plants
			{385, 2335, 3883, 0, 3}, //mushroom plants
			{385, 2335, 3884, 0, 3}, //mushroom plants
			{385, 2335, 3885, 0, 3}, //mushroom plants
			{385, 2335, 3886, 0, 3}, //mushroom plants
			{385, 2335, 3887, 0, 3}, //mushroom plants
			{385, 2335, 3888, 0, 3}, //mushroom plants
			{385, 2335, 3889, 0, 3}, //mushroom plants
			{385, 2335, 3890, 0, 3}, //mushroom plants
			{385, 2335, 3891, 0, 3}, //mushroom plants
			{385, 2335, 3892, 0, 3}, //mushroom plants
			{385, 2335, 3893, 0, 3}, //mushroom plants
			{385, 2335, 3894, 0, 3}, //mushroom plants
			{385, 2335, 3895, 0, 3}, //mushroom plants
			{385, 2335, 3896, 0, 3}, //mushroom plants
			{385, 2335, 3897, 0, 3}, //mushroom plants
			{385, 2335, 3898, 0, 3}, //mushroom plants
			{385, 2335, 3899, 0, 3}, //mushroom plants
			
			{385, 2393, 3883, 0, 3}, //mushroom plants
			{385, 2393, 3884, 0, 3}, //mushroom plants
			{385, 2393, 3885, 0, 3}, //mushroom plants
			{385, 2393, 3886, 0, 3}, //mushroom plants
			{385, 2393, 3887, 0, 3}, //mushroom plants
			{385, 2393, 3888, 0, 3}, //mushroom plants
			{385, 2393, 3889, 0, 3}, //mushroom plants
			{385, 2393, 3890, 0, 3}, //mushroom plants
			{385, 2393, 3891, 0, 3}, //mushroom plants
			{385, 2393, 3892, 0, 3}, //mushroom plants
			
			{385, 2355, 3872, 0, 3}, //mushroom plants
			{385, 2354, 3872, 0, 3}, //mushroom plants
			{385, 2353, 3872, 0, 3}, //mushroom plants
			{385, 2352, 3872, 0, 3}, //mushroom plants
			{385, 2351, 3872, 0, 3}, //mushroom plants
			{385, 2350, 3872, 0, 3}, //mushroom plants
			{385, 2349, 3872, 0, 3}, //mushroom plants
			{385, 2348, 3872, 0, 3}, //mushroom plants
			{385, 2347, 3872, 0, 3}, //mushroom plants
			{385, 2346, 3872, 0, 3}, //mushroom plants
			{385, 2345, 3872, 0, 3}, //mushroom plants
			{385, 2344, 3872, 0, 3}, //mushroom plants
			/*
			 * Xmas objects at home
			 */
			{47748, 3223, 3439, 0, 3}, //Xmas tree
			/*
			 * End of Xmas Objects at home
			 */
			
			/**
			 * ZULRAH
			 */
			{ 25136, 2278, 3070, 0, 0 },
			{ 25136, 2278, 3065, 0, 0 },
			{ 25138, 2273, 3066, 0, 0 },
			{ 25136, 2272, 3065, 0, 0 },
			{ 25139, 2267, 3065, 0, 0 },
			{ 25136, 2260, 3081, 0, 0 },
			{1, 3038, 3415, 0, 0},
			{357, 3034, 3422, 0, 0},
			{ -1, 2265, 3071, 0, 0 },
			{ -1, 2271, 3071, 0, 0 },
			
	
			
			{-1, 3084, 3487, 0, 2},
			
			{ 2274, 3652, 3488, 0, 0 },
			{ 2274, 3039, 9555, 0, 0 },
			{ 2274, 3039, 9554, 0, 0 },
			
			
			
			
		
			/*
			* Remove Uber Zone Objects
			*/
			{-1, 2425, 4714, 0, 0},
			{-1, 2420, 4716, 0, 0},
			{-1, 2426, 4726, 0, 0},
			{-1, 2420, 4709, 0, 0},
			{-1, 2419, 4698, 0, 0},
			{-1, 2420, 4700, 0, 0},
			{-1, 2399, 4721, 0, 0},
			{-1, 2398, 4721, 0, 0},
			{-1, 2399, 4720, 0, 0},
			{-1, 2395, 4722, 0, 0},
			{-1, 2398, 4717, 0, 0},
			{-1, 2396, 4717, 0, 0},
			{-1, 2396, 4718, 0, 0},
			{-1, 2396, 4719, 0, 0},
			{-1, 2395, 4718, 0, 0},
			{-1, 2394, 4711, 0, 0},
			{-1, 2396, 4711, 0, 0},
			{-1, 2397, 4711, 0, 0},
			{-1, 2397, 4713, 0, 0},
			{-1, 2399, 4713, 0, 0},
			{-1, 2402, 4726, 0, 0},
			{-1, 2407, 4728, 0, 0},
			{-1, 2405, 4724, 0, 0},
			{-1, 2409, 4705, 0, 0},
			{-1, 2410, 4704, 0, 0},
			{-1, 2407, 4702, 0, 0},
			{-1, 2407, 4701, 0, 0},
			{-1, 2408, 4701, 0, 0},
			{-1, 2412, 4701, 0, 0},
			{-1, 2413, 4701, 0, 0},
			{-1, 2414, 4703, 0, 0},
			{-1, 2416, 4714, 0, 0},
			{-1, 2412, 4732, 0, 0},
			{-1, 2413, 4729, 0, 0},
			{-1, 2414, 4733, 0, 0},
			{-1, 2415, 4730, 0, 0},
			{-1, 2416, 4730, 0, 0},
			{-1, 2416, 4731, 0, 0},
			{-1, 2419, 4731, 0, 0},
			{-1, 2420, 4731, 0, 0},
			{-1, 2420, 4732, 0, 0},
			{-1, 2415, 4725, 0, 0},
			{-1, 2417, 4729, 0, 0},
			{-1, 2418, 4727, 0, 0},
			{-1, 2418, 4723, 0, 0},
			{-1, 2419, 4722, 0, 0},
			{-1, 2420, 4726, 0, 0},
			{-1, 2415, 4725, 0, 0},
			{-1, 2417, 4729, 0, 0},
			{-1, 2418, 4727, 0, 0},
			{-1, 2418, 4723, 0, 0},
			{-1, 2419, 4722, 0, 0},
			{-1, 2420, 4726, 0, 0},
			
			
		//lumby cows gate
		{2344, 3253, 3266, 0, 1},
		
		//gamble zone
		{2213, 2842, 5143, 0, 0},
		{2213, 2843, 5143, 0, 0},
		{2213, 2844, 5143, 0, 0},
		{2213, 2845, 5143, 0, 0},
		{2213, 2846, 5143, 0, 0},
		{2213, 2847, 5143, 0, 0},
		{2213, 2848, 5143, 0, 0},
		{2213, 2849, 5143, 0, 0},
		{2213, 2850, 5143, 0, 0},
		{2213, 2851, 5143, 0, 0},
		
		
		{2274, 3652, 3488, 0, 0},
		/**Jail Start*/
		{ 12269, 3093, 3933, 0, 0},
		{ 1864, 3093, 3932, 0, 1},//Cell 1
		{ 1864, 3094, 3932, 0, 1},
		{ 1864, 3095, 3932, 0, 1},
		{ 1864, 3096, 3932, 0, 1},
		{ 1864, 3097, 3932, 0, 1},
		{ 1864, 3097, 3931, 0, 2},
		{ 1864, 3097, 3930, 0, 2},
		{ 1864, 3097, 3929, 0, 2},
		{ 1864, 3097, 3928, 0, 3},
		{ 1864, 3096, 3928, 0, 3},
		{ 1864, 3095, 3928, 0, 3},
		{ 1864, 3094, 3928, 0, 3},
		{ 1864, 3093, 3928, 0, 3},
		{ 1864, 3093, 3929, 0, 4},
		{ 1864, 3093, 3930, 0, 4},
		{ 1864, 3093, 3931, 0, 4},
		{ 1864, 3098, 3932, 0, 1},//Cell 2
		{ 1864, 3099, 3932, 0, 1},
		{ 1864, 3100, 3932, 0, 1},
		{ 1864, 3101, 3932, 0, 1},
		{ 1864, 3102, 3932, 0, 1},
		{ 1864, 3102, 3931, 0, 2},
		{ 1864, 3102, 3930, 0, 2},
		{ 1864, 3102, 3929, 0, 2},
		{ 1864, 3102, 3928, 0, 3},
		{ 1864, 3101, 3928, 0, 3},
		{ 1864, 3100, 3928, 0, 3},
		{ 1864, 3099, 3928, 0, 3},
		{ 1864, 3098, 3928, 0, 3},
		{ 1864, 3098, 3929, 0, 4},
		{ 1864, 3098, 3930, 0, 4},
		{ 1864, 3098, 3931, 0, 4},
		{ 1864, 3093, 3939, 0, 1},//Cell 3
		{ 1864, 3094, 3939, 0, 1},
		{ 1864, 3095, 3939, 0, 1},
		{ 1864, 3096, 3939, 0, 1},
		{ 1864, 3097, 3939, 0, 1},
		{ 1864, 3097, 3938, 0, 2},
		{ 1864, 3097, 3937, 0, 2},
		{ 1864, 3097, 3936, 0, 2},
		{ 1864, 3097, 3935, 0, 3},
		{ 1864, 3096, 3935, 0, 3},
		{ 1864, 3095, 3935, 0, 3},
		{ 1864, 3094, 3935, 0, 3},
		{ 1864, 3093, 3935, 0, 3},
		{ 1864, 3093, 3936, 0, 4},
		{ 1864, 3093, 3937, 0, 4},
		{ 1864, 3093, 3938, 0, 4},
		{ 1864, 3098, 3939, 0, 1},//Cell 4
		{ 1864, 3099, 3939, 0, 1},
		{ 1864, 3100, 3939, 0, 1},
		{ 1864, 3101, 3939, 0, 1},
		{ 1864, 3102, 3939, 0, 1},
		{ 1864, 3102, 3938, 0, 2},
		{ 1864, 3102, 3937, 0, 2},
		{ 1864, 3102, 3936, 0, 2},
		{ 1864, 3102, 3935, 0, 3},
		{ 1864, 3101, 3935, 0, 3},
		{ 1864, 3100, 3935, 0, 3},
		{ 1864, 3099, 3935, 0, 3},
		{ 1864, 3098, 3935, 0, 3},
		{ 1864, 3098, 3936, 0, 4},
		{ 1864, 3098, 3937, 0, 4},
		{ 1864, 3098, 3938, 0, 4},
		{ 1864, 3103, 3932, 0, 1},//Cell 5
		{ 1864, 3104, 3932, 0, 1},
		{ 1864, 3105, 3932, 0, 1},
		{ 1864, 3106, 3932, 0, 1},
		{ 1864, 3107, 3932, 0, 1},
		{ 1864, 3107, 3931, 0, 2},
		{ 1864, 3107, 3930, 0, 2},
		{ 1864, 3107, 3929, 0, 2},
		{ 1864, 3107, 3928, 0, 3},
		{ 1864, 3106, 3928, 0, 3},
		{ 1864, 3105, 3928, 0, 3},
		{ 1864, 3104, 3928, 0, 3},
		{ 1864, 3103, 3928, 0, 3},
		{ 1864, 3103, 3929, 0, 4},
		{ 1864, 3103, 3930, 0, 4},
		{ 1864, 3103, 3931, 0, 4},
		{ 1864, 3108, 3932, 0, 1},//Cell 6
		{ 1864, 3109, 3932, 0, 1},
		{ 1864, 3110, 3932, 0, 1},
		{ 1864, 3111, 3932, 0, 1},
		{ 1864, 3112, 3932, 0, 1},
		{ 1864, 3112, 3931, 0, 2},
		{ 1864, 3112, 3930, 0, 2},
		{ 1864, 3112, 3929, 0, 2},
		{ 1864, 3112, 3928, 0, 3},
		{ 1864, 3111, 3928, 0, 3},
		{ 1864, 3110, 3928, 0, 3},
		{ 1864, 3109, 3928, 0, 3},
		{ 1864, 3108, 3928, 0, 3},
		{ 1864, 3108, 3929, 0, 4},
		{ 1864, 3108, 3930, 0, 4},
		{ 1864, 3108, 3931, 0, 4},
		{ 1864, 3103, 3939, 0, 1},//Cell 7
		{ 1864, 3104, 3939, 0, 1},
		{ 1864, 3105, 3939, 0, 1},
		{ 1864, 3106, 3939, 0, 1},
		{ 1864, 3107, 3939, 0, 1},
		{ 1864, 3107, 3938, 0, 2},
		{ 1864, 3107, 3937, 0, 2},
		{ 1864, 3107, 3936, 0, 2},
		{ 1864, 3107, 3935, 0, 3},
		{ 1864, 3106, 3935, 0, 3},
		{ 1864, 3105, 3935, 0, 3},
		{ 1864, 3104, 3935, 0, 3},
		{ 1864, 3103, 3935, 0, 3},
		{ 1864, 3103, 3936, 0, 4},
		{ 1864, 3103, 3937, 0, 4},
		{ 1864, 3103, 3938, 0, 4},
		{ 1864, 3108, 3939, 0, 1},//Cell 8
		{ 1864, 3109, 3939, 0, 1},
		{ 1864, 3110, 3939, 0, 1},
		{ 1864, 3111, 3939, 0, 1},
		{ 1864, 3112, 3939, 0, 1},
		{ 1864, 3112, 3938, 0, 2},
		{ 1864, 3112, 3937, 0, 2},
		{ 1864, 3112, 3936, 0, 2},
		{ 1864, 3112, 3935, 0, 3},
		{ 1864, 3111, 3935, 0, 3},
		{ 1864, 3110, 3935, 0, 3},
		{ 1864, 3109, 3935, 0, 3},
		{ 1864, 3108, 3935, 0, 3},
		{ 1864, 3108, 3936, 0, 4},
		{ 1864, 3108, 3937, 0, 4},
		{ 1864, 3108, 3938, 0, 4},
		{ 1864, 3113, 3932, 0, 1},//Cell 9
		{ 1864, 3114, 3932, 0, 1},
		{ 1864, 3115, 3932, 0, 1},
		{ 1864, 3116, 3932, 0, 1},
		{ 1864, 3117, 3932, 0, 1},
		{ 1864, 3117, 3931, 0, 2},
		{ 1864, 3117, 3930, 0, 2},
		{ 1864, 3117, 3929, 0, 2},
		{ 1864, 3117, 3928, 0, 3},
		{ 1864, 3116, 3928, 0, 3},
		{ 1864, 3115, 3928, 0, 3},
		{ 1864, 3114, 3928, 0, 3},
		{ 1864, 3113, 3928, 0, 3},
		{ 1864, 3113, 3929, 0, 4},
		{ 1864, 3113, 3930, 0, 4},
		{ 1864, 3113, 3931, 0, 4},
		{ 1864, 3113, 3939, 0, 1},//Cell 10
		{ 1864, 3114, 3939, 0, 1},
		{ 1864, 3115, 3939, 0, 1},
		{ 1864, 3116, 3939, 0, 1},
		{ 1864, 3117, 3939, 0, 1},
		{ 1864, 3117, 3938, 0, 2},
		{ 1864, 3117, 3937, 0, 2},
		{ 1864, 3117, 3936, 0, 2},
		{ 1864, 3117, 3935, 0, 3},
		{ 1864, 3116, 3935, 0, 3},
		{ 1864, 3115, 3935, 0, 3},
		{ 1864, 3114, 3935, 0, 3},
		{ 1864, 3113, 3935, 0, 3},
		{ 1864, 3113, 3936, 0, 4},
		{ 1864, 3113, 3937, 0, 4},
		{ 1864, 3113, 3938, 0, 4},
	};
	public static boolean cloudExists(Location loc) {
		return getCloudObject(loc);
	}

	
	
	public static boolean getCloudObject(Location loc) {
		for (GameObject objects : CUSTOM_OBJECTS) {
			System.out.println(loc);
			return Entity.inLocation(objects.getPosition().getX(), objects.getPosition().getY(), Location.ZULRAH_CLOUD_FIVE);
		}
		return false;
	}
	
	
	public static void zulrahToxicClouds(final GameObject cloud, final Player player, final int cycles) {
		player.setInteractingObject(cloud);
		spawnGlobalObject(cloud);
		TaskManager.submit(new Task(cycles) {
			@Override
			public void execute() {
				deleteGlobalObject(cloud);
				player.setCloudsSpawned(false);
				if (player.getInteractingObject() != null
						&& player.getInteractingObject().getId() == 11700) {
					player.setInteractingObject(null);
				}
				this.stop();
			}

			@Override
			public void stop() {
				setEventRunning(false);
			}
		});

	}
}
