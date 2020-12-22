package com.arlania.world.content.ganodermic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.arlania.model.GroundItem;
import com.arlania.model.Item;
import com.arlania.model.Position;
import com.arlania.util.Misc;
import com.arlania.world.World;
import com.arlania.world.content.combat.CombatBuilder.CombatDamageCache;
import com.arlania.world.content.combat.CombatFactory;
import com.arlania.world.entity.impl.GroundItemManager;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.player.Player;

public class Ganodermic extends NPC {

	public static int[] COMMONLOOT = { 4461,15332,20453,20452,20451,20450,1543,19670};
	public static int[] RARELOOT = {7118,11533,7100 };
	public static int[] SUPERRARELOOT = { 3230,20089,20806,18911,18,9703,19061,18959,19060,18985,20837 };


	public static final int NPC_ID = 2070;
	
	public static boolean ganoAlive = false;
	private static long massMessageTimer = 0;
	public static int rng = 0, INTERVAL = 2700000 + Misc.random(3600000);


	public static final GanodermicLocation[] LOCATIONS = {
			new GanodermicLocation(3170, 2993, 0, "Gano")};


	/**
	 * 
	 */
	private static Ganodermic current;

	/**
	 * 
	 * @param position
	 */
	public Ganodermic(Position position) {

		super(NPC_ID, position);
	}

	/**
	 * 
	 */
    public static void initialize() {
    	
		if (massMessageTimer == 0 || (System.currentTimeMillis() - massMessageTimer >= INTERVAL)) { 
			massMessageTimer = System.currentTimeMillis();
			spawn();

		}

	}

	/**
	 * 
	 */
    public static void spawn() {
       if(ganoAlive == true) { 
		World.sendMessage("[<col=01701d>PVM</col>] @red@The beast has been spotted near " +LOCATIONS[rng].getLocation()+ "!");
           return;
       }

       	rng = Misc.randomMinusOne(LOCATIONS.length);
        GanodermicLocation location = LOCATIONS[rng];
        Ganodermic instance = new Ganodermic(location.copy());
       
 		instance.setConstitution(200000000);
        World.register(instance);
        setCurrent(instance);
        ganoAlive = true;
		World.sendMessage("[<col=01701d>PVM</col>] @red@The beast has been spotted near " +LOCATIONS[rng].getLocation()+ "!");
	}
	
	public static void sendHint(Player player) {
    	player.getPacketSender().sendMessage("[<col=01701d>PVM</col>] @red@The beast has been spotted near "+LOCATIONS[rng].getLocation()+"!");
    }

	/**
	 * 
	 * @param npc
	 */
	public static void handleDrop(NPC npc) {
		
		setCurrent(null);
		
		System.out.println(" GUEE ");

		if(npc.getCombatBuilder().getDamageMap().size() == 0) {
            System.out.println("size == 0");
            return;
		}

		Map<Player, Integer> killers = new HashMap<>();

		for(Entry<Player, CombatDamageCache> entry : npc.getCombatBuilder().getDamageMap().entrySet()) {

			if(entry == null) {
                    System.out.println("entry == null");
                    continue;
			}

			long timeout = entry.getValue().getStopwatch().elapsed();
	
			if(timeout > CombatFactory.DAMAGE_CACHE_TIMEOUT) {
                    System.out.println("timeout > cache timeout");
                    continue;
			}

			Player player = entry.getKey();

			if(player.getConstitution() <= 0 || !player.isRegistered()) {
                    System.out.println("hp < 0 or player is not logged in");
                    continue;
			}

			killers.put(player, entry.getValue().getDamage());

		}

		npc.getCombatBuilder().getDamageMap().clear();

		List<Entry<Player, Integer>> result = sortEntries(killers);
		int count = 0;

		for (Entry<Player, Integer> entry : result) {

			Player killer = entry.getKey();
			int damage = entry.getValue();

			handleDrop(npc, killer, damage);
			String toAppend = "th";

			if(count == 0){
				toAppend = "st";
			}else if(count==1){
				toAppend = "nd";
			}else if(count==2){
				toAppend = "rd";
			}

			killer.getPacketSender().sendMessage("You came in " + (count+1) + toAppend + " place with " + damage + " damage points.");
			
			System.out.println(killer + " GUEE " + damage);

			if (++count >= 15) {
				break;
			}

		}

	}

	public static void giveLoot(Player player, NPC npc, Position pos) {
		int chance = Misc.getRandom(100);
		int common = COMMONLOOT[Misc.getRandom(COMMONLOOT.length - 1)];
		int common1 = COMMONLOOT[Misc.getRandom(COMMONLOOT.length - 1)];
		int rare = RARELOOT[Misc.getRandom(RARELOOT.length - 1)];
		int superrare = SUPERRARELOOT[Misc.getRandom(SUPERRARELOOT.length - 1)];

		GroundItemManager.spawnGroundItem(player,
				new GroundItem(new Item(5022, 10000), pos, player.getUsername(), false, 150, true, 200));

		if (chance >= 100) {
			GroundItemManager.spawnGroundItem(player,
					new GroundItem(new Item(superrare), pos, player.getUsername(), false, 150, true, 200));
			String itemName = (new Item(superrare).getDefinition().getName());
			String itemMessage = Misc.anOrA(itemName) + " " + itemName;
			World.sendMessage(
					"[<col=01701d>PVM</col>] @red@" + player.getUsername() + " received " + itemMessage + " from the Ganodermic Beast!");
			return;
		}

		if (chance >= 93) {
			GroundItemManager.spawnGroundItem(player,
					new GroundItem(new Item(rare), pos, player.getUsername(), false, 150, true, 200));
			String itemName = (new Item(rare).getDefinition().getName());
			String itemMessage = Misc.anOrA(itemName) + " " + itemName;
			World.sendMessage(
					"[<col=01701d>PVM</col>] @red@" + player.getUsername() + " received " + itemMessage + " from the Ganodermic Beast!");
			return;
		}
		if (chance >= 0) {
			GroundItemManager.spawnGroundItem(player,
					new GroundItem(new Item(common, 5), pos, player.getUsername(), false, 150, true, 200));
			String itemName = (new Item(common).getDefinition().getName());
			/*commenting as it will spam the hell out if 15 players gets a drop at the same time

			World.sendMessage(
					"[<col=01701d>PVM</col>] @red@" + player.getUsername() + " received 5x " + itemName + " from the Ganodermic Beast!");
			*/
			return;

		}
	}

	/**
	 * 
	 * @param npc
	 * @param player
	 * @param damage
	 */
	private static void handleDrop(NPC npc, Player player, int damage) {
		Position pos = npc.getPosition();
		giveLoot(player, npc, pos);
	}

	/**
	 * 
	 * @param map
	 * @return
	 */
	static <K, V extends Comparable<? super V>> List<Entry<K, V>> sortEntries(Map<K, V> map) {

		List<Entry<K, V>> sortedEntries = new ArrayList<Entry<K, V>>(map.entrySet());

		Collections.sort(sortedEntries, new Comparator<Entry<K, V>>() {

			@Override
			public int compare(Entry<K, V> e1, Entry<K, V> e2) {
				return e2.getValue().compareTo(e1.getValue());
			}

		});

		return sortedEntries;

	}

	/**
	 * 
	 * @return
	 */
	public static Ganodermic getCurrent() {
		return current;
	}

	/**
	 * 
	 * @param current
	 */
	public static void setCurrent(Ganodermic current) {
		Ganodermic.current = current;
	}

	public static class GanodermicLocation extends Position {

		/**
		 * 
		 */
		private String location;

		/**
		 * 
		 * @param x
		 * @param y
		 * @param z
		 * @param location
		 */
		public GanodermicLocation(int x, int y, int z, String location) {
			super(x, y, z);
			setLocation(location);
		}

		/**
		 * 
		 * @return
		 */

		public String getLocation() {
			return location;
		}

		/**
		 * 
		 * @param location
		 */
		public void setLocation(String location) {
			this.location = location;
		}

	}
}
