package com.athena.model.input.impl;

import com.athena.engine.task.Task;
import com.athena.engine.task.TaskManager;
import com.athena.model.definitions.ItemDefinition;
import com.athena.model.definitions.NPCDrops;
import com.athena.model.definitions.NpcDefinition;
import com.athena.model.definitions.NPCDrops.NpcDropItem;
import com.athena.model.input.Input;
import com.athena.world.content.dialogue.DialogueManager;
import com.athena.world.content.dialogue.impl.ExplorerJack;
import com.athena.world.entity.impl.player.Player;

public class ItemSearch extends Input {

	@Override
	public void handleSyntax(Player player, final String syntax) {
		DialogueManager.start(player, 72);
		player.getMovementQueue().setLockMovement(true);
		TaskManager.submit(new Task(5, player, false) {
			@Override
			protected void execute() {
				try {
					String syntaxCopy = syntax;
					Object[] data = getFixedSyntax(syntaxCopy);
					syntaxCopy = (String) data[0];

					player.getPacketSender().sendInterfaceRemoval();
					if(syntaxCopy.length() <= 3) {
						DialogueManager.start(player, 74);
						return;
					}

					int itemId = (int) data[1];
					int npcDropId = -1;
					
					for(ItemDefinition def : ItemDefinition.getDefinitions()) {
						if(def != null) {
							if(def.getName().contains(syntaxCopy) && itemId == -1) {
								itemId = def.getId();
							}
							if(def.getName().equalsIgnoreCase(syntaxCopy)) {
								itemId = def.getId();
								break;
							}
						}
					}
					
					if(itemId > 0 && ItemDefinition.forId(itemId).isNoted()) {
						itemId--;
					}
					
					if(itemId == 14486) {
						itemId = 19023;
					}

					if(itemId > 0) {
						for(NPCDrops npcDrops : NPCDrops.getDrops().values()) {
							if(npcDrops != null) {
								for(NpcDropItem item : npcDrops.getDropList()) {
									if(item != null && item.getId() == itemId) {
										for (int npcId : npcDrops.getNpcIds()) {
											if(npcId == -1)
												continue;
											if(NpcDefinition.forId(npcId) != null) {
												assert NpcDefinition.forId(npcId) != null;
												if (!NpcDefinition.forId(npcId).getName().equalsIgnoreCase("null")) {
													npcDropId = npcId;
													break;
												}
											}
										}
									}
								}
							}
						}
					}

					if(itemId == -1 || npcDropId == -1) {
						DialogueManager.start(player, 73);
					} else {
						assert NpcDefinition.forId(npcDropId) != null;
						DialogueManager.start(player, ExplorerJack.foundDrop(syntaxCopy, NpcDefinition.forId(npcDropId).getName()));
					}
				} catch(Exception ignored) {}

				stop();
			}

			@Override
			public void stop() {
				setEventRunning(false);
				player.getMovementQueue().setLockMovement(false);
			}
		});
	}


	public static Object[] getFixedSyntax(String searchSyntax) {
		searchSyntax = searchSyntax.toLowerCase();
		return switch (searchSyntax) {
			case "ags" -> new Object[]{"armadyl godsword", 11694};
			case "sgs" -> new Object[]{"saradomin godsword", 11698};
			case "bgs" -> new Object[]{"bandos godsword", 11696};
			case "zgs" -> new Object[]{"zamorak godsword", 11700};
			case "dclaws", "d claws" -> new Object[]{"dragon claws", 14484};
			case "bcp" -> new Object[]{"bandos chestplate", 11724};
			case "dds" -> new Object[]{"dragon dagger", 1215};
			case "sol" -> new Object[]{"staff of light", 15486};
			case "vls" -> new Object[]{"vesta's longsword", 13899};
			case "tassy" -> new Object[]{"bandos tassets", 11726};
			case "swh" -> new Object[]{"statius's warhammer", 13902};
			case "steads" -> new Object[]{"steadfast boots", 20000};
			case "obby maul" -> new Object[]{"Tthaar-ket-om", 6528};
			case "g maul", "gmaul" -> new Object[]{"granite maul", 4153};
			case "nat" -> new Object[]{"nature rune", 561};
			case "ely" -> new Object[]{"elysian spirit shield", 13742};
			case "dfs" -> new Object[]{"dragonfire shield", 11283};
			case "dkite" -> new Object[]{"dragon kiteshield", 11613};
			case "dbones" -> new Object[]{"dragon bones", 536};
			case "fury" -> new Object[]{"amulet of fury", 6585};
			case "dboots", "d boots" -> new Object[]{"dragon boots", 11732};
			case "whip", "abby whip", "abbysal whip", "abbyssal whip" -> new Object[]{"abyssal whip", 4151};
			case "abbyssal vine whip" -> new Object[]{"abyssal vine whip", 4705};
			case "vine whip" -> new Object[]{"vine whip", 4705};
			default -> new Object[]{searchSyntax, -1};
		};
	}
}
