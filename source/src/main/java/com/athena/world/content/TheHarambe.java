package com.athena.world.content;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.athena.engine.task.Task;
import com.athena.engine.task.TaskManager;
import com.athena.model.Position;
import com.athena.util.Misc;
import com.athena.world.content.combat.CombatBuilder.CombatDamageCache;
import com.athena.world.World;
import com.athena.world.content.combat.CombatFactory;
import com.athena.world.entity.impl.npc.NPC;
import com.athena.world.entity.impl.player.Player;

public class TheHarambe extends NPC {
	/**
	 * 
	 */
	public static final int NPC_ID = 19;

	/**
	 * add your maps to that folder open me your client.java in client
	 */
	public static final HarambeLocation[] LOCATIONS = { new HarambeLocation(2597, 3157, 0, "Fight arena! @ ::arena") };

	/**
	 * 
	 */
	private static TheHarambe current;

	/**
	 * 
	 * @param position
	 */
	public TheHarambe(Position position) {

		super(NPC_ID, position);
	}

	/**
	 * 
	 */
	public static void initialize() {

		TaskManager.submit(new Task(180, false) { // 6000

			@Override
			public void execute() {
				spawn();
			}

		});

	}

	/**
	 * 
	 */
	public static void spawn() {

		if (getCurrent() != null) {
			return;
		}

		HarambeLocation location = Misc.randomElement(LOCATIONS);
		TheHarambe instance = new TheHarambe(location.copy());

		// System.out.println(instance.getPosition());

		World.register(instance);
		setCurrent(instance);
		// System.out.print("spawned.");

		World.sendMessage("<img=10>@red@The Harambe has spawned at the " + location.getLocation() + "!");
	}

	/**
	 * 
	 * @param npc
	 */
	public static void handleDrop(NPC npc) {

		setCurrent(null);

		if (npc.getCombatBuilder().getDamageMap().size() == 0) {
			return;
		}

		Map<Player, Integer> killers = new HashMap<>();

		for (Entry<Player, CombatDamageCache> entry : npc.getCombatBuilder().getDamageMap().entrySet()) {

			if (entry == null) {
				continue;
			}

			long timeout = entry.getValue().getStopwatch().elapsed();

			if (timeout > CombatFactory.DAMAGE_CACHE_TIMEOUT) {
				continue;
			}

			Player player = entry.getKey();

			if (player.getConstitution() <= 0 || !player.isRegistered()) {
				continue;
			}

			killers.put(player, entry.getValue().getDamage());

		}

		npc.getCombatBuilder().getDamageMap().clear();

		List<Entry<Player, Integer>> result = sortEntries(killers);
		for (@SuppressWarnings("unused") Entry<Player, Integer> entry : result) {

			break;
			}

		}

	/**
	 * 
	 * @param npc
	 * @param player
	 * @param damage
	 */

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
	public static TheHarambe getCurrent() {
		return current;
	}

	/**
	 * 
	 * @param current
	 */
	public static void setCurrent(TheHarambe current) {
		TheHarambe.current = current;
	}

	/**
	 * 
	 * @author Levi <levi.patton69 @ skype>
	 *
	 */
	public static class HarambeLocation extends Position {

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
		public HarambeLocation(int x, int y, int z, String location) {
			super(x, y, z);
			setLocation(location);
		}

		/**
		 * 
		 * @return
		 */

		String getLocation() {
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