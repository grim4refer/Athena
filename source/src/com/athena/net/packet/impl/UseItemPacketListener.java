package com.athena.net.packet.impl;

import com.athena.engine.task.impl.WalkToTask;
import com.athena.engine.task.impl.WalkToTask.FinalizedMovementTask;
import com.athena.model.Animation;
import com.athena.model.GameMode;
import com.athena.model.GameObject;
import com.athena.model.Graphic;
import com.athena.model.Item;
import com.athena.model.PlayerRights;
import com.athena.model.Position;
import com.athena.model.Skill;
import com.athena.model.Locations.Location;
import com.athena.model.definitions.GameObjectDefinition;
import com.athena.model.definitions.ItemDefinition;
import com.athena.model.definitions.NPCDrops;
import com.athena.net.packet.Packet;
import com.athena.net.packet.PacketListener;
import com.athena.util.Misc;
import com.athena.util.RandomUtility;
import com.athena.world.World;
import com.athena.world.clip.region.RegionClipping;
import com.athena.world.content.ItemForging;
import com.athena.world.content.dialogue.DialogueManager;
import com.athena.world.content.gambling.GamblingNpc;
import com.athena.world.content.minigames.impl.HerculesBay.BarrowsSpawning;
import com.athena.world.content.skill.impl.cooking.Cooking;
import com.athena.world.content.skill.impl.cooking.CookingData;
import com.athena.world.content.skill.impl.crafting.Gems;
import com.athena.world.content.skill.impl.crafting.LeatherMaking;
import com.athena.world.content.skill.impl.firemaking.Firemaking;
import com.athena.world.content.skill.impl.fletching.Fletching;
import com.athena.world.content.skill.impl.herblore.Herblore;
import com.athena.world.content.skill.impl.herblore.PotionCombinating;
import com.athena.world.content.skill.impl.herblore.WeaponPoison;
import com.athena.world.content.skill.impl.prayer.BonesOnAltar;
import com.athena.world.content.skill.impl.prayer.Prayer;
import com.athena.world.content.skill.impl.slayer.SlayerDialogues;
import com.athena.world.content.skill.impl.slayer.SlayerTasks;
import com.athena.world.content.skill.impl.smithing.EquipmentMaking;
import com.athena.world.entity.impl.player.Player;

/**
 * This packet listener is called when a player 'uses' an item on another
 * entity.
 * 
 * @author relex lawl
 */

public class UseItemPacketListener implements PacketListener {

	/**
	 * The PacketListener logger to debug information and print out errors.
	 */
	// private final static Logger logger =
	// Logger.getLogger(UseItemPacketListener.class);

	private static void useItem(Player player, Packet packet) {
		if (player.isTeleporting() || player.getConstitution() <= 0)
			return;
		int interfaceId = packet.readLEShortA();
		int slot = packet.readShortA();
		int id = packet.readLEShort();
	}

	private static void itemOnItem(Player player, Packet packet) {
		int usedWithSlot = packet.readUnsignedShort();
		int itemUsedSlot = packet.readUnsignedShortA();
		if (usedWithSlot < 0 || itemUsedSlot < 0 || itemUsedSlot > player.getInventory().capacity()
				|| usedWithSlot > player.getInventory().capacity())
			return;
		Item usedWith = player.getInventory().getItems()[usedWithSlot];
		Item itemUsedWith = player.getInventory().getItems()[itemUsedSlot];
		if (usedWith.getId() == 6573 || itemUsedWith.getId() == 6573) {
			player.getPacketSender().sendMessage("To make an Amulet of Fury, you need to put an onyx in a furnace.");
			return;
		}
		if (usedWith.getId() == 2717 || itemUsedWith.getId() == 20900) {
			if (player.getInventory().getFreeSlots() < 2) {
				player.getPacketSender().sendMessage("You don't have enough free inventory space.");
				return;
			}
			int godzilla[][] = {
					{11316,11320}, //Uncommon, 0
					{11550,11550,11550,11550,11550,11549,11549,11549,11549,11546,11546,11546,11547,11547,11548,11550,11550,11550,11550,11550,11549,11549,11549,11549,11546,11546,11546,11547,11547,11548}, //Rare, 1
					{2104, 2758, 2095, 2096, 2097, 2098}
			};
			double numGen3 = Math.random();
			int rewardGrade3 = 0;
			int rewardPos = 0;
			double randomYoda = Misc.random(50);

			double drBoost = NPCDrops.getDroprate(player);
			randomYoda = (int) randomYoda * ((100 - drBoost) / 100);
			int amount = 1;
			if (randomYoda == 0) {
				rewardGrade3 = 1;
				rewardPos = RandomUtility.getRandom(godzilla[rewardGrade3].length - 1);
				player.setAnimation(new Animation(6382));
				player.setGraphic(new Graphic(127));
				World.sendMessage("<shad=0>@bla@[@whi@Slayer casket@bla@] @whi@" + player.getUsername() + "@bla@ Has just received a @whi@" + ItemDefinition.forId(godzilla[rewardGrade3][rewardPos]).getName() + " @bla@!");
				double randomDouble = Misc.random(99);

				double ddrBoost = NPCDrops.getDoubleDr(player);
				randomDouble = (int) randomDouble * ((100 - ddrBoost) / 100);
				if (randomDouble == 0) {
					amount *= 2;
				}
			} else {
				rewardGrade3 = 0;
				rewardPos = RandomUtility.getRandom(godzilla[rewardGrade3].length - 1);
				double randomDouble = Misc.random(99);

				double ddrBoost = NPCDrops.getDoubleDr(player);
				randomDouble = (int) randomDouble * ((100 - ddrBoost) / 100);
				amount = Misc.random(5)+1;

				if (randomDouble == 0) {
					amount *= 2;
				}
			}
			player.getInventory().delete(20900, 1);
			player.getInventory().delete(2717, 1);
			player.getInventory().add(godzilla[rewardGrade3][rewardPos], amount).refreshItems();
		}
		if (usedWith.getId() == 12926) {
			player.getBlowpipeLoading().handleLoadBlowpipe(itemUsedWith);
			return;
		}
		if (itemUsedWith.getId() == 12926) {
			player.getBlowpipeLoading().handleLoadBlowpipe(usedWith);
			return;
		}
		if (usedWith.getId() == 3962) {
			player.getDragonRageLoading().handleLoadDragonrage(itemUsedWith);
			return;
		}
		if (itemUsedWith.getId() == 3962) {
			player.getDragonRageLoading().handleLoadDragonrage(usedWith);
			return;
		}
		if (usedWith.getId() == 13208) {
			player.getCorruptBandagesLoading().handleLoadCorruptBandages(itemUsedWith);
			return;
		}
		if (itemUsedWith.getId() == 13208) {
			player.getCorruptBandagesLoading().handleLoadCorruptBandages(usedWith);
			return;
		}
		if (usedWith.getId() == 896) {
			player.getMinigunLoading().handleLoadMinigun(itemUsedWith);
			return;
		}
		if (itemUsedWith.getId() == 896) {
			player.getMinigunLoading().handleLoadMinigun(usedWith);
			return;
		}
		WeaponPoison.execute(player, itemUsedWith.getId(), usedWith.getId());
		if (itemUsedWith.getId() == 590 || usedWith.getId() == 590)
			Firemaking.lightFire(player, itemUsedWith.getId() == 590 ? usedWith.getId() : itemUsedWith.getId(), false,
					1);
		if (itemUsedWith.getDefinition().getName().contains("(") && usedWith.getDefinition().getName().contains("("))
			PotionCombinating.combinePotion(player, usedWith.getId(), itemUsedWith.getId());
		if (usedWith.getId() == Herblore.VIAL || itemUsedWith.getId() == Herblore.VIAL) {
			if (Herblore.makeUnfinishedPotion(player, usedWith.getId())
					|| Herblore.makeUnfinishedPotion(player, itemUsedWith.getId()))
				return;
		}
		if (Herblore.finishPotion(player, usedWith.getId(), itemUsedWith.getId())
				|| Herblore.finishPotion(player, itemUsedWith.getId(), usedWith.getId()))
			return;
		if (usedWith.getId() == 946 || itemUsedWith.getId() == 946)
			Fletching.openSelection(player, usedWith.getId() == 946 ? itemUsedWith.getId() : usedWith.getId());
		if (usedWith.getId() == 1777 || itemUsedWith.getId() == 1777)
			Fletching.openBowStringSelection(player,
					usedWith.getId() == 1777 ? itemUsedWith.getId() : usedWith.getId());
		if (usedWith.getId() == 53 || itemUsedWith.getId() == 53 || usedWith.getId() == 52
				|| itemUsedWith.getId() == 52)
			Fletching.makeArrows(player, usedWith.getId(), itemUsedWith.getId());
		if (itemUsedWith.getId() == 1755 || usedWith.getId() == 1755)
			Gems.selectionInterface(player, usedWith.getId() == 1755 ? itemUsedWith.getId() : usedWith.getId());
		if (usedWith.getId() == 1733 || itemUsedWith.getId() == 1733)
			LeatherMaking.craftLeatherDialogue(player, usedWith.getId(), itemUsedWith.getId());
		Herblore.handleSpecialPotion(player, itemUsedWith.getId(), usedWith.getId());
		ItemForging.forgeItem(player, itemUsedWith.getId(), usedWith.getId());
		if (player.getRights() == PlayerRights.DEVELOPER)
			player.getPacketSender().sendMessage(
					"ItemOnItem - [usedItem, usedWith] : [" + usedWith.getId() + ", " + itemUsedWith + "]");
	}

	@SuppressWarnings("unused")
	private static void itemOnObject(Player player, Packet packet) {
		@SuppressWarnings("unused")
		int interfaceType = packet.readShort();
		final int objectId = packet.readShort();
		final int objectY = packet.readLEShortA();
		final int itemSlot = packet.readLEShort();
		final int objectX = packet.readLEShortA();
		final int itemId = packet.readShort();


		if (itemSlot < 0 || itemSlot > player.getInventory().capacity())
			return;
		final Item item = player.getInventory().getItems()[itemSlot];
		if (item == null)
			return;
		final GameObject gameObject = new GameObject(objectId,
				new Position(objectX, objectY, player.getPosition().getZ()));
		if (objectId > 0 && objectId != 6 && !RegionClipping.objectExists(gameObject)) {
			// player.getPacketSender().sendMessage("An error occured. Error
			// code: "+id).sendMessage("Please report the error to a staff
			// member.");
			return;
		}
		player.setInteractingObject(gameObject);
		player.setWalkToTask(new WalkToTask(player, gameObject.getPosition().copy(), gameObject.getSize(),
				new FinalizedMovementTask() {
					@Override
					public void execute() {
						if (CookingData.forFish(item.getId()) != null && CookingData.isRange(objectId)) {
							player.setPositionToFace(gameObject.getPosition());
							Cooking.selectionInterface(player, CookingData.forFish(item.getId()));
							return;
						}
						if (Prayer.isBone(itemId) && objectId == 409) {
							BonesOnAltar.openInterface(player, itemId);
							return;

						}
						if (player.getFarming().plant(itemId, objectId, objectX, objectY))
							return;
						if (player.getFarming().useItemOnPlant(itemId, objectX, objectY))
							return;
						if (objectId == 15621) { // Warriors guild
							// animator
							if (!BarrowsSpawning.itemOnAnimator(player, item, gameObject))
								player.getPacketSender().sendMessage("Nothing interesting happens..");
				return;
					}
						if (player.getGameMode() == GameMode.HARDCORE_IRONMAN) {
							if (GameObjectDefinition.forId(objectId) != null) {
								GameObjectDefinition def = GameObjectDefinition.forId(objectId);
								if (def.name != null && def.name.toLowerCase().contains("bank") && def.actions != null
										&& def.actions[0] != null && def.actions[0].toLowerCase().contains("use")) {
									ItemDefinition def1 = ItemDefinition.forId(itemId);
									ItemDefinition def2;
									int newId = def1.isNoted() ? itemId - 1 : itemId + 1;
									def2 = ItemDefinition.forId(newId);
									if (def2 != null && def1.getName().equals(def2.getName())) {
										int amt = player.getInventory().getAmount(itemId);
										if (!def2.isNoted()) {
											if (amt > player.getInventory().getFreeSlots())
												amt = player.getInventory().getFreeSlots();
										}
										if (amt == 0) {
											player.getPacketSender().sendMessage(
													"You do not have enough space in your inventory to do that.");
											return;
										}
										player.getInventory().delete(itemId, amt).add(newId, amt);

									} else {
										player.getPacketSender().sendMessage("You cannot do this with that item.");
									}
									return;
								}
							}
						}
						switch (objectId) {
							case 3422:

								break;
						case 6189:
							if (player.getSkillManager().getCurrentLevel(Skill.CRAFTING) < 1) {
								player.getPacketSender()
										.sendMessage("You need a Crafting level of at least 80 to make that item.");
								return;
							}
							if(player.getInventory().contains(20952) && player.getInventory().contains(20951)) {
								int amount;
								if(player.getInventory().getAmount(20952) > player.getInventory().getAmount(20951)) {
									amount = player.getInventory().getAmount(20951);
								} else {
									amount = player.getInventory().getAmount(20952);
								}
								for(int i = 0; i < amount; i++) {
									player.getInventory().delete(new Item(20952)).delete(new Item(20951))
											.add(new Item(20950));
									player.performAnimation(new Animation(896));
									player.getSkillManager().addExperience(Skill.CRAFTING, 250);
								}
							}
							if (player.getInventory().contains(6573)) {
								if (player.getInventory().contains(1597)) {
									if (player.getInventory().contains(1759)) {
										player.performAnimation(new Animation(896));
										player.getInventory().delete(new Item(1759)).delete(new Item(6573))
												.add(new Item(6585));
										player.getPacketSender().sendMessage(
												"You put the items into the furnace to forge an Amulet of Fury.");
									} else {
										player.getPacketSender().sendMessage("You need some Ball of Wool to do this.");
									}
								} else {
									player.getPacketSender().sendMessage("You need a Necklace mould to do this.");
								}
							}
							break;
						case 7836:
						case 7808:
							if (itemId == 6055) {
								int amt = player.getInventory().getAmount(6055);
								if (amt > 0) {
									player.getInventory().delete(6055, amt);
									player.getPacketSender().sendMessage("You put the weed in the compost bin.");
									player.getSkillManager().addExperience(Skill.FARMING, 20 * amt);
								}
							}
							break;
						case 4306:
							EquipmentMaking.handleAnvil(player);
							// player.getPacketSender().sendMessage("Temporarily
							// Disabled");

							break;
						}
					}
				}));
	}

	private static void itemOnNpc(final Player player, Packet packet) {
		int itemId = packet.readUnsignedShortA();
		int index = packet.readUnsignedShortA(); //OK I WILL BE FURIOUS IF THIS DOESNT WORK, R U SURE? no this is what i had first time HM !
		final int slot = packet.readLEShort();
		int lastItemSelectedInterface = packet.readUnsignedShortA();
		
		Item usedItem = player.getInventory().forSlot(slot);
		switch (index) {
		case 648:

			GamblingNpc.init(player, usedItem.getId(), usedItem.getAmount(),index);
			break;
		}
	}

	@SuppressWarnings("unused")
	private static void itemOnPlayer(Player player, Packet packet) {
		int interfaceId = packet.readUnsignedShortA();
		int targetIndex = packet.readUnsignedShort();
		int itemId = packet.readUnsignedShort();
		int slot = packet.readLEShort();
		if (slot < 0 || slot > player.getInventory().capacity() || targetIndex > World.getPlayers().capacity())
			return;
		Player target = World.getPlayers().get(targetIndex);
		if (target == null)
			return;

		switch (itemId) {
		case 962:
			if (!player.getInventory().contains(962) || player.getRights() == PlayerRights.ADMINISTRATOR)
				return;
			player.setPositionToFace(target.getPosition());
			player.performGraphic(new Graphic(1006));
			player.performAnimation(new Animation(451));
			player.getPacketSender().sendMessage("You pull the Christmas cracker...");
			target.getPacketSender().sendMessage("" + player.getUsername() + " pulls a Christmas cracker on you..");
			player.getInventory().delete(962, 1);
			player.getPacketSender().sendMessage("The cracker explodes and you receive a Party hat!");
			player.getInventory().add(1038 + RandomUtility.getRandom(10), 1);
			target.getPacketSender().sendMessage("" + player.getUsername() + " has received a Party hat!");
			/*
			 * if(RandomUtility.getRandom(1) == 1) {
			 * target.getPacketSender().sendMessage(
			 * "The cracker explodes and you receive a Party hat!");
			 * target.getInventory().add((1038 + RandomUtility.getRandom(5)*2), 1);
			 * player.getPacketSender().sendMessage(""+target.getUsername()+
			 * " has received a Party hat!"); } else {
			 * player.getPacketSender().sendMessage(
			 * "The cracker explodes and you receive a Party hat!");
			 * player.getInventory().add((1038 + RandomUtility.getRandom(5)*2), 1);
			 * target.getPacketSender().sendMessage(""+player.getUsername()+
			 * " has received a Party hat!"); }
			 */
			break;

		case 4155:
			if (player.getSlayer().getDuoPartner() != null) {
				player.getPacketSender().sendMessage("You already have a duo partner.");
				return;
			}
			if (player.getSlayer().getSlayerTask() != SlayerTasks.NO_TASK) {
				player.getPacketSender().sendMessage("You already have a Slayer task. You must reset it first.");
				return;
			}
			Player duoPartner = World.getPlayers().get(targetIndex);
			if (duoPartner != null) {
				if (duoPartner.getSlayer().getDuoPartner() != null) {
					player.getPacketSender().sendMessage("This player already has a duo partner.");
					return;
				}
				if (duoPartner.getSlayer().getSlayerTask() != SlayerTasks.NO_TASK) {
					player.getPacketSender().sendMessage("This player already has a Slayer task.");
					return;
				}
				if (duoPartner.getSlayer().getSlayerMaster() != player.getSlayer().getSlayerMaster()) {
					player.getPacketSender().sendMessage("You do not have the same Slayer master as that player.");
					return;
				}
				if (duoPartner.busy() || duoPartner.getLocation() == Location.WILDERNESS) {
					player.getPacketSender().sendMessage("This player is currently busy.");
					return;
				}
				DialogueManager.start(duoPartner, SlayerDialogues.inviteDuo(duoPartner, player));
				player.getPacketSender()
						.sendMessage("You have invited " + duoPartner.getUsername() + " to join your Slayer duo team.");
			}
			break;
		}
	}

	@Override
	public void handleMessage(Player player, Packet packet) {
		if (player.getConstitution() <= 0)
			return;
		switch (packet.getOpcode()) {
		case ITEM_ON_ITEM:
			itemOnItem(player, packet);
			break;
		case USE_ITEM:
			useItem(player, packet);
			break;
		case ITEM_ON_OBJECT:
			itemOnObject(player, packet);
			break;
		case ITEM_ON_GROUND_ITEM:
			// TODO
			break;
		case ITEM_ON_NPC:
			itemOnNpc(player, packet);
			break;
		case ITEM_ON_PLAYER:
			itemOnPlayer(player, packet);
			break;
		}
	}

	public final static int USE_ITEM = 122;

	public final static int ITEM_ON_NPC = 57;

	public final static int ITEM_ON_ITEM = 53;

	public final static int ITEM_ON_OBJECT = 192;

	public final static int ITEM_ON_GROUND_ITEM = 25;

	public static final int ITEM_ON_PLAYER = 14;
}
