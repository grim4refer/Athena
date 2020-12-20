package org.ruse.client.renderable;

import org.ruse.client.cache.definition.ItemDefinition;
import org.ruse.client.world.Model;

public final class Item extends Animable {

	public int amount;
	public int id;

	@Override
	public final Model getRotatedModel() {
		ItemDefinition definition = ItemDefinition.get(id);
		return definition.getInventoryModel(amount);
	}

}