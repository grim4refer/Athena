package com.athena.world.content;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.athena.model.definitions.NPCDrops;
import com.athena.model.definitions.NpcDefinition;
import com.athena.world.entity.impl.player.Player;

/**
 * @Author Levi Patton
 * @rune-server.org/members/AuguryPS
 * 
 * @author Tyrant (tyrant.inquiries@gmail.com) -> Fixes
 *
 */
public class MonsterDrops {
	/**
	 * A method that initializes and fills in the cache of the drops. What this
	 * does is go by every {@link NPCDrops} and attaches it to its relevant npc
	 * name so a player can view the drop table for that name.
	 */
	public static void initialize() {
		try {
			NPCDrops.getDrops().entrySet().stream().filter(Objects::nonNull)
					.filter($d -> $d.getKey() > 0 && NpcDefinition.forId($d.getKey()) != null).forEach($d -> {
						npcDrops.put(NpcDefinition.forId($d.getKey()).getName().toLowerCase(), $d.getValue());
//						System.out.println("Added: " + $d.getValue().toString() + " " + $d.getKey());
					});
			System.out.println("MonsterDrops has been initialized: size " + npcDrops.size());
		} catch (Exception i) {
			i.printStackTrace();
		}
	}

	public static Map<String, NPCDrops> npcDrops = new HashMap<>();

	public static void sendNpcDrop(Player player, int id, String name) {
		NPCDrops drops = npcDrops.get(name);
		if (drops == null) {
			player.sendMessage(
					"No drop table found for " + name + " " + id + " " + NpcDefinition.forName(name).getId());
			return;
		}
		if(!player.getClickDelay().elapsed(600)) {
			return;
		}
		player.getClickDelay().reset();
		DropLookup.display(player, drops);
	}
}
