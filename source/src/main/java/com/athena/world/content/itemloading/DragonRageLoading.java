package com.athena.world.content.itemloading;

import com.athena.model.Item;
import com.athena.model.definitions.ItemDefinition;
import com.athena.world.entity.impl.player.Player;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multiset.Entry;

/**
 * Handles the action of loading darts into the Dragonrage.
 * 
 * @author Yo Daddi
 */
public class DragonRageLoading {

	/**
	 * The player participating in this action.
	 */
	private final Player player;

	/**
	 * Constructs a new instance of this class which assigns {@link #player} to
	 * the parameterized value.
	 * 
	 * @param player
	 *            The player participating in this action.
	 */
	public DragonRageLoading(Player player) {
		this.player = player;
	}

	/**
	 * An {@link ImmutableSet} which specifies which {@link Item#getId}'s are
	 * considered loadable darts.
	 */
	public static final ImmutableSet<Integer> LOADABLE_DARTS = ImmutableSet.of(806, 807, 808, 809, 810, 811,
			11230);

	/**
	 * An {@link Multiset} of {@link Item}'s which handles the contents of the
	 * Toxic Dragonrage.
	 */
	private final Multiset<Integer> dragonrageContents = HashMultiset.create();

	/**
	 * Returns {@link #DragonrageContents} for public(global) use.
	 * 
	 * @return {@link #DragonrageContents}.
	 */
	public Multiset<Integer> getContents() {
		return dragonrageContents;
	}

	/**
	 * Handles the action of loading darts into the Toxic Dragonrage.
	 */
	public void handleLoadDragonrage(Item item) {
		if (LOADABLE_DARTS.contains(item.getId())) {
			for (int dart : dragonrageContents) {
				if (item.getId() != dart) {
					player.getPacketSender().sendMessage("There are already darts loaded in your Dragonrage blowpipe.");
					return;
				}
			}
			dragonrageContents.setCount(item.getId(), dragonrageContents.count(item.getId()) + item.getAmount());
			player.getPacketSender().sendMessage("Darts: <col=329500>" + ItemDefinition.forId(item.getId()).getName()
					+ " x " + dragonrageContents.count(item.getId()) + "</col>.");
			player.getInventory().delete(item);
		}
	}

	/**
	 * Handles the action of checking the contents of the Toxic dragonrage.
	 */
	public void handleCheckDragonrage() {
		if (dragonrageContents.isEmpty()) {
			player.getPacketSender().sendMessage("The Dragonrage blowpipe has no darts in it.");
			return;
		}

		for (Entry<Integer> dart : dragonrageContents.entrySet()) {
			player.getPacketSender()
					.sendMessage("Darts: <col=329500>" + ItemDefinition.forId(dart.getElement()).getName() + " x "
							+ dragonrageContents.count(dart.getElement()) + "</col>.");
		}

	}

	/**
	 * Handles the action of unloading the contents of the Toxic dragonrage.
	 */
	public void handleUnloadDragonrage() {
		if (dragonrageContents.isEmpty()) {
			player.getPacketSender().sendMessage("Your Dragonrage blowpipe is already empty!");
			return;
		}

		for (Entry<Integer> dart : dragonrageContents.entrySet()) {
			player.getInventory().add(new Item(dart.getElement(), dragonrageContents.count(dart.getElement())));
			dragonrageContents.remove(dart.getElement(), dragonrageContents.count(dart.getElement()));
		}

	}

}
