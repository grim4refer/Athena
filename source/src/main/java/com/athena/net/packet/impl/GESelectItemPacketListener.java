package com.athena.net.packet.impl;

import com.athena.model.Item;
import com.athena.model.definitions.ItemDefinition;
import com.athena.net.packet.Packet;
import com.athena.net.packet.PacketListener;
import com.athena.world.content.DropLookup;
import com.athena.world.content.grandexchange.GrandExchange;
import com.athena.world.entity.impl.player.Player;

public class GESelectItemPacketListener implements PacketListener {

	@Override
	public void handleMessage(Player player, Packet packet) {
		System.out.println(packet.getLength());
		int item = packet.readShort();
		boolean ge = packet.readByte() == 1;

		if(item <= 0 || item >= ItemDefinition.getMaxAmountOfItems())
			return;
		ItemDefinition def = ItemDefinition.forId(item);
		if(def != null) {
			if(ge) {
				if (def.getValue() <= 0 || !Item.tradeable(item) || item == 995) {
					player.getPacketSender().sendMessage("This item can currently not be purchased or sold in the Grand Exchange.");
					return;
				}
				GrandExchange.setSelectedItem(player, item);
			} else {
				DropLookup.search(player, def.getName(), DropLookup.SearchPolicy.ITEM);
			}
		}
	}

}
