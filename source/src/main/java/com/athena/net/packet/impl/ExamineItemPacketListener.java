package com.athena.net.packet.impl;

import com.athena.model.PlayerRights;
import com.athena.model.Skill;
import com.athena.model.definitions.ItemDefinition;
import com.athena.net.packet.Packet;
import com.athena.net.packet.PacketListener;
import com.athena.util.Misc;
import com.athena.world.content.skill.impl.herblore.FinishedPotions;
import com.athena.world.entity.impl.player.Player;

public class ExamineItemPacketListener implements PacketListener {

	@Override
	public void handleMessage(Player player, Packet packet) {
		int item = packet.readShort();
		if (item == 995 || item == 18201) {
			player.getPacketSender().sendMessage("Mhmm... Shining coins...");
			return;
		}

		//ItemDefinition itemDef = ItemDefinition.forId(item);
		if (ItemDefinition.forId(item) != null && ItemDefinition.forId(item).getName() != null
				&& ItemDefinition.forId(item).getName().toLowerCase().contains("(unf)")) {
			for (int i = 0; i < FinishedPotions.values().length; i++) {
				if (item == FinishedPotions.values()[i].getUnfinishedPotion()) {
					player.getPacketSender().sendMessage("Finish this potion with a "
							+ ItemDefinition.forId(FinishedPotions.values()[i].getItemNeeded()).getName() + ".");
					return;
				}
			}
		}
		ItemDefinition itemDef = ItemDefinition.forId(item);
		if (itemDef != null) {
			// player.getPacketSender().sendMessage(itemDef.getDescription());
			for (Skill skill : Skill.values()) {
				if (itemDef.getRequirement()[skill.ordinal()] > player.getSkillManager().getMaxLevel(skill)) {
					player.getPacketSender().sendMessage("@red@Attention: You need "
							+ new StringBuilder()
							.append(skill.getName().startsWith("a") || skill.getName().startsWith("e")
									|| skill.getName().startsWith("i") || skill.getName().startsWith("o")
									|| skill.getName().startsWith("u") ? "an " : "a ")
							.toString()
							+ Misc.formatText(skill.getName()) + " level of at least "
							+ itemDef.getRequirement()[skill.ordinal()] + " to wear this.");
				}
			}
		}
		handleExaminationInterface(player, item);

		if (player.getRights() == PlayerRights.OWNER) {
			player.getPacketSender().sendMessage("Ths ItemID is: " + itemDef.getId());
		}
	}
	public void handleExaminationInterface(Player player, int itemId) {
		int count = 52103;
		ItemDefinition itemDef = ItemDefinition.forId(itemId);
		/*
		 * player.getPacketSender().sendItemOnInterface(52102, itemId, 1);
		 * player.getPacketSender().sendString(52113, itemDef.getName());
		 *
		 * player.getPacketSender().sendString( count++, "Equipement: " +
		 * itemDef.getEquipmentType()); player.getPacketSender().sendString( count++,
		 * "Stackable: " + itemDef.isStackable()); player.getPacketSender().sendString(
		 * count++, "Noted: " + itemDef.isNoted()); player.getPacketSender().sendString(
		 * count++, "High Alch: " + itemDef.getValue() / 2);
		 * player.getPacketSender().sendString( count++, "Low Alch: " +
		 * itemDef.getValue() / 4); player.getPacketSender().sendString( count++,
		 * "Weapon: " + itemDef.isWeapon()); player.getPacketSender().sendString(
		 * count++, "Two Handed: " + itemDef.isTwoHanded());
		 *
		 * player.getPacketSender().sendString(52143, "Value: " +
		 * formatCoins(itemDef.getValue()) + ".");
		 *
		 * String[] text = { "Stab", "Slash", "Crush", "Magic", "Range" }; for (int i =
		 * 0; i < 5; i++) { player.getPacketSender().sendString(52131 + i, text[i] +
		 * ": " + String.valueOf(itemDef.getBonus()[0 + i]));
		 * player.getPacketSender().sendString(52138 + i,text[i] + ": " +
		 * String.valueOf(itemDef.getBonus()[5 + i])); }
		 *
		 * player.getPacketSender().sendString(52144, "Strength: " +
		 * String.valueOf(itemDef.getBonus()[10]));
		 * player.getPacketSender().sendString(52145, "Prayer: " +
		 * String.valueOf(itemDef.getBonus()[11]));
		 * player.getPacketSender().sendInterface(52100); for(PriceGuideData data :
		 * values) { if(data.getItemid() == itemId) { player.sendMessage("" +
		 * itemDef.getName() + " price is currently estimated at " + data.getPrice()); }
		 * }
		 */
		player.sendMessage("@bla@<img=30>[PRICE CHECK] <col=5e0606>" + itemDef.getName()
				+ ": <col=06195e>Value of this item is : <col=065e16> " + formatCoins(itemDef.getValue()) + ".");
	}
	public static String formatCoins(int amount) {
		if (amount > 9999 && amount <= 9999999) {
			return (amount / 1000) + "K";
		} else if (amount > 9999999 && amount <= 999999999) {
			return (amount / 1000000) + "M";
		} else if (amount > 999999999) {
			return (amount / 1000000000) + "B";
		}
		return String.valueOf(amount);
	}

}

