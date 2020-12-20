package com.ruse.world.content.randomevents;

import java.util.HashMap;
import java.util.Map;

import com.ruse.model.Animation;
import com.ruse.model.GameObject;
import com.ruse.model.Position;
import com.ruse.util.Misc;
import com.ruse.util.Stopwatch;
import com.ruse.world.World;
import com.ruse.world.content.CustomObjects;
import com.ruse.world.entity.impl.player.Player;

public final class EvilTree{

	private static final int TIME = 1000000;
	private static int maxCut;
	private static Stopwatch timer = new Stopwatch().reset();
	public static SpawnedTree SPAWNED_TREE = null;
	private static LocationData LAST_LOCATION = null;

	public static class SpawnedTree {

		public SpawnedTree(GameObject treeObject, LocationData treeLocation) {
			this.treeObject = treeObject;
			this.treeLocation = treeLocation;
		}

		private GameObject treeObject;
		private LocationData treeLocation;

		public GameObject getTreeObject() {
			return treeObject;
		}

		public LocationData getTreeLocation() {
			return treeLocation;
		}
	}
	
	public static enum EvilTreeDef {

		NORMAL_EVIL_TREE("Evil tree", 11435, 1511, 1, 1, 1, 200, 15, 20, 200),
		EVIL_OAK_TREE("Oak Evil Tree", 11437, 1521, 15, 7, 15, 300, 32, 45, 300),
		EVIL_WILLOW_TREE("Willow Evil Tree", 11441, 1519, 30, 15, 30, 400, 45, 66, 450),
		EVIL_MAPLE_TREE("Maple Evil Tree", 11444, 1517, 45, 22, 45, 500, 55, 121, 675),
		EVIL_YEW_TREE("Yew Evil Tree", 11916, 1515, 60, 30, 60, 650, 64, 172, 1012),
		EVIL_MAGIC_TREE("Magic Evil Tree", 11919, 1513, 75, 37, 75, 800, 70, 311, 1517),
		EVIL_ELDER_TREE("Elder Evil Tree", 11922, 1513, 90, 42, 90, 1000, 77, 687, 2000);

		private String treeName;
		private int id;
		private int log;
		private int woodcuttingLevel;
		private int farmingLevel;
		private int firemakingLevel;
		private int maximumCuttingAmount;
		private int woodcuttingXp;
		private int farmingXp;
		private int firemakingXp;

		private EvilTreeDef(String treeName, int id, int log, int woodcuttingLevel, int farmingLevel, int firemakingLevel, int maxCutAmount, int woodcuttingXp,
				int farmingXp, int firemakingXp) {
			this.treeName = treeName;
			this.id = id;
			this.log = log;
			this.woodcuttingLevel = woodcuttingLevel;
			this.farmingLevel = farmingLevel;
			this.firemakingLevel = firemakingLevel;
			this.maximumCuttingAmount = maxCutAmount;
			this.woodcuttingXp = woodcuttingXp;
			this.farmingXp = farmingXp;
			this.firemakingXp = firemakingXp;
		}

		public String getTreeName(){
			return treeName;
		}
		public int getId() {
			return id;
		}

		/**
		 * @return the log
		 */
		public int getLog() {
			return log;
		}

		public int getWoodcuttingLevel() {
			return woodcuttingLevel;
		}

		public int getFarmingLevel() {
			return farmingLevel;
		}

		public int getFiremakingLevel() {
			return firemakingLevel;
		}

		public int getMaximumCutAmount() {
			return maximumCuttingAmount;
		}

		public int getWoodcuttingXp() {
			return woodcuttingXp;
		}

		public int getFarmingXp() {
			return farmingXp;
		}

		public int getFiremakingXp() {
			return firemakingXp;
		}
		private static final Map<Integer, EvilTreeDef> tree = new HashMap<Integer, EvilTreeDef>();

		public static EvilTreeDef forId(int id) {
			return tree.get(id);
		}
		
		static {
			for (EvilTreeDef w : EvilTreeDef.values())

				tree.put(w.id, w);

		}

	}
	public static enum LocationData {
		LOCATION_1(new Position(2711, 3462), "next to a bunch of Yew trees.", "Seers"),
		LOCATION_2(new Position(2694, 9564), "close to a dungeon entrance and within a tropical jungle.", "Brimhaven"),
		LOCATION_3(new Position(2709, 3507), "next to a bunch of Willow trees.", "Seers"),
		LOCATION_4(new Position(2132, 4899), "near a place often called Draynor.", "Draynor"),
		LOCATION_5(new Position(2578, 4843), "near a hot Runecrafting altar.", "Hot"),
		LOCATION_6(new Position(2711, 3325), "close to the guild for legends.", "Legends Guild"),
		LOCATION_7(new Position(3165, 3248), "near the entrance to HAM.", "Lumbridge");

		private LocationData(Position spawnPos, String clue, String playerPanelFrame) {
			this.spawnPos = spawnPos;
			this.clue = clue;
			this.playerPanelFrame = playerPanelFrame;
		}

		private Position spawnPos;
		private String clue;
		public String playerPanelFrame;
	}
	public static LocationData getRandom() {
		LocationData tree = LocationData.values()[Misc.getRandom(LocationData.values().length - 1)];
		return tree;
	}

	public static EvilTreeDef getRandomTree() {
		EvilTreeDef tree = EvilTreeDef.values()[Misc.getRandom(EvilTreeDef.values().length - 1)];
		return tree;
	}
	public static void sequence() {
		if(SPAWNED_TREE == null) {
			if(timer.elapsed(TIME)) {
				LocationData locationData = getRandom();
				EvilTreeDef tree = getRandomTree();
				if(LAST_LOCATION != null) {
					if(locationData == LAST_LOCATION) {
						locationData = getRandom();
					}
				}
				LAST_LOCATION = locationData;
				maxCut = tree.maximumCuttingAmount;
				SPAWNED_TREE = new SpawnedTree(new GameObject(tree.getId(), locationData.spawnPos), locationData);
				CustomObjects.spawnGlobalObject(SPAWNED_TREE.treeObject);
				World.sendMessage("<img=10> <shad=1><col=FF9933>An Evil tree has spawned "+locationData.clue+"");
				//World.getPlayers().forEach(p -> p.getPacketSender().sendString(39162, "@or2@Crashed star: @yel@"+ShootingStar.CRASHED_STAR.getStarLocation().playerPanelFrame+""));
				timer.reset();
			}
		} else {
			if(SPAWNED_TREE.treeObject.getPickAmount() >= maxCut) {
				despawn(false);
				timer.reset();
			}
		}
	}

	public static void despawn(boolean respawn) {
		if(respawn) {
			timer.reset(0);
		} else {
			timer.reset();
		}
		if(SPAWNED_TREE != null) {
			for(Player p : World.getPlayers()) {
				if(p == null) {
					continue;
				}
				//p.getPacketSender().sendString(39162, "@or2@Evil Tree: @or2@[ @yel@N/A@or2@ ]");
				if(p.getInteractingObject() != null && p.getInteractingObject().getId() == SPAWNED_TREE.treeObject.getId()) {
					p.performAnimation(new Animation(65535));
					p.getPacketSender().sendClientRightClickRemoval();
					p.getSkillManager().stopSkilling();
					p.getPacketSender().sendMessage("The evil tree has been cut down.");
				}
			}
			CustomObjects.deleteGlobalObject(SPAWNED_TREE.treeObject);
			SPAWNED_TREE = null;
		}
	}
}