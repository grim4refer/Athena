package com.athena.net.packet.impl;

import com.athena.engine.task.Task;
import com.athena.engine.task.TaskManager;
import com.athena.model.*;
import com.athena.model.Locations.Location;
import com.athena.model.container.impl.Shop.ShopManager;
import com.athena.model.definitions.ItemDefinition;
import com.athena.net.packet.Packet;
import com.athena.net.packet.PacketListener;
import com.athena.util.Misc;
import com.athena.world.content.Gambling;
import com.athena.util.RandomUtility;
import com.athena.world.World;
import com.athena.world.content.*;
import com.athena.world.content.Sounds.Sound;
import com.athena.world.content.StarterTasks.StarterTaskData;
//import com.arlania.world.content.combat.range.DwarfMultiCannon;
import com.athena.world.content.dialogue.DialogueManager;
import com.athena.world.content.itemopening.CharmBox;
import com.athena.world.content.itemopening.DoflamingoSetBox;
import com.athena.world.content.itemopening.DonationBox;
import com.athena.world.content.itemopening.MemberScrolls;
import com.athena.world.content.itemopening.RUBoxes;
import com.athena.world.content.mysteryboxes.*;
import com.athena.world.content.skill.impl.construction.Construction;
import com.athena.world.content.skill.impl.dungeoneering.Dungeoneering;
import com.athena.world.content.skill.impl.herblore.GuidanceBook;
import com.athena.world.content.skill.impl.herblore.Herblore;
import com.athena.world.content.skill.impl.herblore.IngridientsBook;
import com.athena.world.content.skill.impl.hunter.BoxTrap;
import com.athena.world.content.skill.impl.hunter.Hunter;
import com.athena.world.content.skill.impl.hunter.JarData;
import com.athena.world.content.skill.impl.hunter.PuroPuro;
import com.athena.world.content.skill.impl.hunter.SnareTrap;
import com.athena.world.content.skill.impl.hunter.Trap.TrapState;
import com.athena.world.content.skill.impl.prayer.Prayer;
import com.athena.world.content.skill.impl.runecrafting.Runecrafting;
import com.athena.world.content.skill.impl.runecrafting.RunecraftingPouches;
import com.athena.world.content.skill.impl.runecrafting.RunecraftingPouches.RunecraftingPouch;
import com.athena.world.content.skill.impl.slayer.SlayerDialogues;
import com.athena.world.content.skill.impl.slayer.SlayerTasks;
import com.athena.world.content.skill.impl.summoning.CharmingImp;
import com.athena.world.content.skill.impl.summoning.SummoningData;
import com.athena.world.content.skill.impl.woodcutting.BirdNests;
import com.athena.world.content.transportation.JewelryTeleporting;
import com.athena.world.content.transportation.TeleportHandler;
import com.athena.world.content.transportation.TeleportType;
import com.athena.world.content.treasuretrails.ClueScroll;
import com.athena.world.content.treasuretrails.CoordinateScrolls;
import com.athena.world.content.treasuretrails.DiggingScrolls;
import com.athena.world.content.treasuretrails.MapScrolls;
import com.athena.world.content.treasuretrails.Puzzle;
import com.athena.world.content.treasuretrails.SearchScrolls;
import com.athena.world.entity.impl.npc.NPC;
import com.athena.world.entity.impl.player.Player;
import com.google.common.collect.Multiset.Entry;
import com.athena.world.content.StarterTasks;



public class ItemActionPacketListener implements PacketListener {
	
	
	
	public static void cancelCurrentActions(Player player) {
		player.getPacketSender().sendInterfaceRemoval();
		player.setTeleporting(false);
		player.setWalkToTask(null);
		player.setInputHandling(null);
		player.getSkillManager().stopSkilling();
		player.setEntityInteraction(null);
		player.getMovementQueue().setFollowCharacter(null);
		player.getCombatBuilder().cooldown(false);
		player.setResting(false);
	}
	public static boolean checkReqs(Player player, Location targetLocation) {
		if(player.getConstitution() <= 0)
			return false;
		if(player.getTeleblockTimer() > 0) {
			player.getPacketSender().sendMessage("A magical spell is blocking you from teleporting.");
			return false;
		}
		if(player.getLocation() != null && !player.getLocation().canTeleport(player))
			return false;
		if(player.isPlayerLocked() || player.isCrossingObstacle()) {
			player.getPacketSender().sendMessage("You cannot teleport right now.");
			return false;
		}
		return true;
	}

	private static void firstAction(final Player player,  Packet packet) {
		int interfaceId = packet.readUnsignedShort();
		int slot = packet.readShort();
		int itemId = packet.readShort();
		
		Location targetLocation = player.getLocation();
		
		if(interfaceId == 38274) {
			Construction.handleItemClick(itemId, player);
			return;
		}


        if(interfaceId == 47989) {
            player.getPacketSender().sendItemOnInterface(47900, itemId, 1);
            return;
        }

		/*if(itemId == 5023) {
			int amount = 0;

			for(int i = 0; i < player.getInventory().getAmount(5023); i++) {
				amount++;
			}
			player.getInventory().delete(5023,amount);
			player.getInventory().add(5023,1000000*amount); */
		//so it can't be username related, or type related stuff because no game messages were sent
		//maybe we've not searched for the right thing
		if(slot < 0 || slot > player.getInventory().capacity())
			return;
		if(player.getInventory().getItems()[slot].getId() != itemId)
			return;
		player.setInteractingItem(player.getInventory().getItems()[slot]);
		if (Prayer.isBone(itemId)) {
			Prayer.buryBone(player, itemId);
			return;
		}
		if (Consumables.isFood(player, itemId, slot))
			return;
		if(Consumables.isPotion(itemId)) {
			Consumables.handlePotion(player, itemId, slot);
			return;
		}
		if(BirdNests.isNest(itemId)) {
			BirdNests.searchNest(player, itemId);
			return;
		}
		if (Herblore.cleanHerb(player, itemId))
			return;
		if(MemberScrolls.handleScroll(player, itemId))
			return;
		if(ClueScroll.handleCasket(player, itemId) || SearchScrolls.loadClueInterface(player, itemId) || MapScrolls.loadClueInterface(player, itemId) || Puzzle.loadClueInterface(player, itemId) || CoordinateScrolls.loadClueInterface(player, itemId) || DiggingScrolls.loadClueInterface(player, itemId))
			return;
		if(Effigies.isEffigy(itemId)) {
			Effigies.handleEffigy(player, itemId);
			return;
		}
		if(ExperienceLamps.handleLamp(player, itemId)) {
			return;
		}
		
		switch(itemId) {
		
			case 691:
			case 692:
			case 693:
			case 15471:
			case 15472:
			case 15473:
				SlotMachine.openInterface(player);
				break;
			case 21007:
			case 21006:
				if(player.getInventory().hasItem(new Item(21007,100)) && player.getInventory().hasItem(new Item(21006, 100))){
					player.getInventory().delete(21007, 100);
					player.getInventory().delete(21006, 100);
					player.getInventory().addItem(18898, 1);
					World.sendMessage("<shad=0>@bla@[@whi@Mythical@bla@] <shad=0>@whi@"+ player.getUsername() + "@bla@ has just forged a mythical blade");
				} else {
					player.sendMessage("You need 100 shards to forge a mythical blade");
				}
				break;


			case 20979:
				StarterTasks.updateInterface(player);
				int[] ids = { 6183 };
				for (int i = 0; i < ids.length; i++) {
					player.getPacketSender().sendItemOnInterface(53205, ids[i], i, 1);
				}
				player.getPacketSender().sendInterfaceReset();
				player.getPacketSender().sendInterface(53200);
				break;
				
			case 20978:
				StarterTasks.finishTask(player, StarterTaskData.READ_THE_GUIDE_BOOK);
				player.sendMessage("@red@You've read the book!");
				player.getPacketSender().sendInterface(50200);
				break;
				
			case 18898:
			case 18:	
				if(player.getInventory().hasItem(new Item(18898,1)) && player.getInventory().hasItem(new Item(18, 4))){
					player.getInventory().delete(18898, 1);
					player.getInventory().delete(18, 4);
					player.getInventory().addItem(20873, 1);
					World.sendMessage("<shad=0>@bla@[@whi@Gemstone@bla@] <shad=0>@whi@"+ player.getUsername() + "@bla@ has just forged a @mag@Duel Gem Sword!");
				} else {
					player.sendMessage("@mag@You need 4 Secret Dust and Gemstone Sword to forge a Dual Sword");
				}
				break;
			case 20943:	
				if(player.getInventory().hasItem(new Item(20944,1000)) && player.getInventory().hasItem(new Item(20943, 1000))){
					player.getInventory().delete(20944, 1000);
					player.getInventory().delete(20943, 1000);
					player.getInventory().addItem(20810, 1);
					World.sendMessage("<shad=0>@bla@[@bla@Vanquisher@bla@] <shad=0>@mag@"+ player.getUsername() + "@red@ has just forged a @cya@Dragon vanquisher helm!");
				} else {
					player.sendMessage("@mag@You need 1000 Lucario and 1000 MewTwo Tokens to forge a Vanquisher helm.");
				}
				break;
			case 20944:	
				if(player.getInventory().hasItem(new Item(20943,1000)) && player.getInventory().hasItem(new Item(20944, 1000))){
					player.getInventory().delete(20943, 1000);
					player.getInventory().delete(20944, 1000);
					player.getInventory().addItem(20810, 1);
					World.sendMessage("<shad=0>@bla@[@bla@Vanquisher@bla@] <shad=0>@mag@"+ player.getUsername() + "@red@ has just forged a @cya@Dragon vanquisher body!");
				} else {
					player.sendMessage("@mag@You need 1000 MewTwo and 1000 Lucario Tokens to forge a Vanquisher helm.");
				}
				break;
			case 18702:
				for (int i = 8145; i <= 8195; i++) {
					player.getPacketSender().sendString(i, "");
				}
				player.getPacketSender().sendString(8146, "@dre@Athena`s Small Guide");
				player.getPacketSender().sendString(8147, "@dre@Start by going to ::train");
				player.getPacketSender().sendString(8148, "@dre@Make sure to ::vote and then type ::reward to claim it.");
				player.getPacketSender().sendString(8149, "@dre@Make sure to use the Forums Guide List.");
				player.getPacketSender().sendString(8150, "@dre@Once you got better gear, start doing the Starter Tasks.");
				player.getPacketSender().sendString(8151, "@dre@Lastly, talk to Vannaka for a Slayer task.");
				player.getPacketSender().sendString(8152, "@dre@Goodluck on your journey!");
				player.getPacketSender().sendInterface(8134);
				break;
			case 20945:	
				if(player.getInventory().hasItem(new Item(20946,1000)) && player.getInventory().hasItem(new Item(20945, 1000))){
					player.getInventory().delete(20946, 1000);
					player.getInventory().delete(20945, 1000);
					player.getInventory().addItem(20811, 1);
					World.sendMessage("<shad=0>@bla@[@bla@Vanquisher@bla@] <shad=0>@mag@"+ player.getUsername() + "@red@ has just forged a @cya@Dragon vanquisher body!");
				} else {
					player.sendMessage("@mag@You need 1000 Charmeleon and 1000 Squirtle Tokens to forge a Vanquisher body.");
				}
				break;
			case 20946:	
				if(player.getInventory().hasItem(new Item(20945,1000)) && player.getInventory().hasItem(new Item(20946, 1000))){
					player.getInventory().delete(20945, 1000);
					player.getInventory().delete(20946, 1000);
					player.getInventory().addItem(20811, 1);
					World.sendMessage("<shad=0>@bla@[@bla@Vanquisher@bla@] <shad=0>@mag@"+ player.getUsername() + "@red@ has just forged a @cya@Dragon vanquisher body!");
				} else {
					player.sendMessage("@mag@You need 1000 Squirtle and 1000 Charmeleon Tokens to forge a Vanquisher body.");
				}
				break;
			case 20947:	
				if(player.getInventory().hasItem(new Item(20948,1000)) && player.getInventory().hasItem(new Item(20947, 1000))){
					player.getInventory().delete(20948, 1000);
					player.getInventory().delete(20947, 1000);
					player.getInventory().addItem(20812, 1);
					World.sendMessage("<shad=0>@bla@[@bla@Vanquisher@bla@] <shad=0>@mag@"+ player.getUsername() + "@red@ has just forged a @cya@Dragon vanquisher legs!");
				} else {
					player.sendMessage("@mag@You need 1000 Pickachu and 1000 Sonic Tokens to forge a Vanquisher legs.");
				}
				break;
			case 20948:	
				if(player.getInventory().hasItem(new Item(20947,1000)) && player.getInventory().hasItem(new Item(20948, 1000))){
					player.getInventory().delete(20947, 1000);
					player.getInventory().delete(20948, 1000);
					player.getInventory().addItem(20812, 1);
					World.sendMessage("<shad=0>@bla@[@bla@Vanquisher@bla@] <shad=0>@mag@"+ player.getUsername() + "@red@ has just forged a @cya@Dragon vanquisher legs!");
				} else {
					player.sendMessage("@mag@You need 1000 Sonic and 1000 Pickachu Tokens to forge a Vanquisher legs.");
				}
				break;
			case 20949:	
				if(player.getInventory().hasItem(new Item(16599,1000)) && player.getInventory().hasItem(new Item(20949, 1000))){
					player.getInventory().delete(16599, 1000);
					player.getInventory().delete(20949, 1000);
					player.getInventory().addItem(20813, 1);
					World.sendMessage("<shad=0>@bla@[@bla@Vanquisher@bla@] <shad=0>@mag@"+ player.getUsername() + "@red@ has just forged a @cya@Dragon vanquisher gauntlets!");
				} else {
					player.sendMessage("@mag@You need 1000 Donkey Kong and 1000 Mr Krabs Tokens to forge Vanquish gauntlets.");
				}
				break;
			case 16599:	
				if(player.getInventory().hasItem(new Item(20949,1000)) && player.getInventory().hasItem(new Item(16599, 1000))){
					player.getInventory().delete(20949, 1000);
					player.getInventory().delete(16599, 1000);
					player.getInventory().addItem(20813, 1);
					World.sendMessage("<shad=0>@bla@[@bla@Vanquisher@bla@] <shad=0>@mag@"+ player.getUsername() + "@red@ has just forged a @cya@Dragon vanquisher gauntlets!");
				} else {
					player.sendMessage("@mag@You need 1000 Mr Krabs and 1000 Donkey Kong Tokens to forge Vanquish gauntlets.");
				}
				break;
			case 16600:	
				if(player.getInventory().hasItem(new Item(16601,1000)) && player.getInventory().hasItem(new Item(16600, 1000))){
					player.getInventory().delete(16601, 1000);
					player.getInventory().delete(16600, 1000);
					player.getInventory().addItem(20814, 1);
					World.sendMessage("<shad=0>@bla@[@bla@Vanquisher@bla@] <shad=0>@mag@"+ player.getUsername() + "@red@ has just forged a @cya@Dragon vanquisher boots!");
				} else {
					player.sendMessage("@mag@You need 1000 Mayonnaise and 1000 Nutella Tokens to forge a Vanquisher boots.");
				}
				break;
			case 16601:	
				if(player.getInventory().hasItem(new Item(16600,1000)) && player.getInventory().hasItem(new Item(16601, 1000))){
					player.getInventory().delete(16600, 1000);
					player.getInventory().delete(16601, 1000);
					player.getInventory().addItem(20814, 1);
					World.sendMessage("<shad=0>@bla@[@bla@Vanquisher@bla@] <shad=0>@mag@"+ player.getUsername() + "@red@ has just forged a @cya@Dragon vanquisher boots!");
				} else {
					player.sendMessage("@mag@You need 1000 Nutella and 1000 Mayonnaise Tokens to forge a Vanquisher boots.");
				}
				break;
			case 20891:	
				if(player.getInventory().hasItem(new Item(20891,1)) &&	player.getInventory().hasItem(new Item(20892,1)) &&  player.getInventory().hasItem(new Item(20893, 1))){
					player.getInventory().delete(20891, 1);
					player.getInventory().delete(20892, 1);
					player.getInventory().delete(20893, 1);
					player.getInventory().addItem(20966, 1);
					World.sendMessage("<shad=0>@bla@[@bla@Roy@bla@] <shad=0>@mag@"+ player.getUsername() + "@red@ has just forged a @cya@Roy Transformation!");
				} else {
					player.sendMessage("@mag@You need @red@1 Roy Mask - 1 Roy Body - 1 Roy Legs @mag@to forge a Roy Transformation.");
				}
				break;
			case 20892:	
				if(player.getInventory().hasItem(new Item(20892,1)) &&	player.getInventory().hasItem(new Item(20891,1)) &&  player.getInventory().hasItem(new Item(20893, 1))){
					player.getInventory().delete(20892, 1);
					player.getInventory().delete(20891, 1);
					player.getInventory().delete(20893, 1);
					player.getInventory().addItem(20966, 1);
					World.sendMessage("<shad=0>@bla@[@bla@Roy@bla@] <shad=0>@mag@"+ player.getUsername() + "@red@ has just forged a @cya@Roy Transformation!");
				} else {
					player.sendMessage("@mag@You need @red@1 Roy Body - 1 Roy Mask - 1 Roy Legs @mag@to forge a Roy Transformation.");
				}
				break;
			case 20893:	
				if(player.getInventory().hasItem(new Item(20893,1)) &&	player.getInventory().hasItem(new Item(20891,1)) &&  player.getInventory().hasItem(new Item(20892, 1))){
					player.getInventory().delete(20893, 1);
					player.getInventory().delete(20891, 1);
					player.getInventory().delete(20892, 1);
					player.getInventory().addItem(20966, 1);
					World.sendMessage("<shad=0>@bla@[@bla@Roy@bla@] <shad=0>@mag@"+ player.getUsername() + "@red@ has just forged a @cya@Roy Transformation!");
				} else {
					player.sendMessage("@mag@You need @red@1 Roy Legs - 1 Roy Mask - 1 Roy Body @mag@to forge a Roy Transformation.");
				}
				break;
			case 20966:	
				if(player.getInventory().hasItem(new Item(20966,100))){
					player.getInventory().delete(20966, 100);
					player.getInventory().addItem(19004, 1);
					World.sendMessage("<shad=0>@bla@[@bla@Roy@bla@] <shad=0>@blu@"+ player.getUsername() + "@red@ has just forged a @mag@Royal Blade!");
				} else {
					player.sendMessage("@mag@You need @red@100 Roy Transformations @mag@to forge a Royal Blade.");
				}
				break;
		case 13663:
			if(player.getInterfaceId() > 0) {
				player.getPacketSender().sendMessage("Please close the interface you have open before doing this.");
				return;
			}
			player.setUsableObject(new Object[2]).setUsableObject(0, "reset");
			player.getPacketSender().sendString(38006, "Choose stat to reset!").sendMessage("@red@Please select a skill you wish to reset and then click on the 'Confim' button.").sendString(38090, "Which skill would you like to reset?");
			player.getPacketSender().sendInterface(38000);
			break;
		case 19670:
			if(player.busy()) {
				player.getPacketSender().sendMessage("You can not do this right now.");
				return;
			}
			player.setDialogueActionId(70);
			DialogueManager.start(player, player.getGameMode() == GameMode.NORMAL ? 108 : 109);
			break;
		case 8007: //Varrock
			if (player.inFFALobby){
				player.sendMessage("Use the portal to leave the ffa lobby!");
				return;
			}
			if (player.inFFA) {
				player.sendMessage("You can not teleport out of FFA!");
				return;
			}
			if(!player.getClickDelay().elapsed(1200) || player.getMovementQueue().isLockMovement()) {
				return;
			}
			if(player.getLocation() == Location.CONSTRUCTION) {
				player.getPacketSender().sendMessage("Please use the portal to exit your house");
				return;
			}
			
			
			if(!checkReqs(player, targetLocation)) {
				return;
			}
			player.setTeleporting(true).getMovementQueue().setLockMovement(true).reset();
			cancelCurrentActions(player);
			player.performAnimation(new Animation(4731));
			player.performGraphic(new Graphic(678));
			player.getInventory().delete(8007, 1);
			player.getClickDelay().reset();
		
			TaskManager.submit(new Task(2, player, false) {
				@Override
				public void execute() {		
					Position position = new Position(3210, 3424, 0);
					player.moveTo(position);
					player.getMovementQueue().setLockMovement(false).reset();
					this.stop();
				}
			});
			 break;

			case 20450:
				 if(player.getLocation() == Location.WILDERNESS) {
			            player.getPacketSender().sendMessage("You cannot use Bandos Avatar Soul in the Wilderness.");
			             return;
			              }
				if(player.getTransform() == 4540) {
					player.setTimer(player.getTimer()+(100*30));
					player.getInventory().delete(20450, 1);
				} else {
					if (player.getTransform() < 1) {
						player.setNpcTransformationId(4540);
						player.setTransform(4540);
						player.setTimer(100 * 30);

						player.getInventory().delete(20450, 1);

						player.getUpdateFlag().flag(Flag.APPEARANCE);
						NPC npc = new NPC(4540, new Position(0, 0));
						player.setConstitution(npc.getConstitution());
						player.sendMessage("<col=ff0000>You transformed into a Bandos Avatar.");
					} else {
						player.sendMessage("Please wait till your timer is over!");
					}
				}
				break;

			case 20451:
				 if(player.getLocation() == Location.WILDERNESS) {
			            player.getPacketSender().sendMessage("You cannot use Abbadon Soul in the Wilderness.");
			             return;
			              }
				if(player.getTransform() == 6303) {
					player.setTimer(player.getTimer()+(100*30));
					player.getInventory().delete(20451, 1);

				} else {
					if(player.getTransform() < 1) {
						player.setNpcTransformationId(6303);
						player.setTransform(6303);
						player.setTimer(100 * 30);
						player.getInventory().delete(20451, 1);

						player.getUpdateFlag().flag(Flag.APPEARANCE);

						NPC npc = new NPC(6303, new Position(0, 0));
						player.setConstitution(npc.getConstitution());
						player.sendMessage("<col=ff0000>You transformed into a Abbadon.");
					} else {
						player.sendMessage("Please wait till your timer is over!");
					}
				}
				break;
					case 20452:
						 if(player.getLocation() == Location.WILDERNESS) {
					            player.getPacketSender().sendMessage("You cannot use Infernal Groudon Soul in the Wilderness.");
					             return;
					              }
						if(player.getTransform() == 1234) {
							player.setTimer(player.getTimer()+(100*30));
							player.getInventory().delete(20452, 1);

						} else {
							if(player.getTransform() < 1) {
				player.setNpcTransformationId(1234);
				player.setTransform(1234);
				player.setTimer(100*30);

								player.getInventory().delete(20452, 1);

								player.getUpdateFlag().flag(Flag.APPEARANCE);

                 NPC npc = new NPC(1234,new Position(0,0));
                player.setConstitution(npc.getConstitution());
				player.sendMessage("<col=ff0000>You transformed into a Infernal Groudon.");
		} else {
			player.sendMessage("Please wait till your timer is over!");
		}
	}
				break;
				case 20453:
					 if(player.getLocation() == Location.WILDERNESS) {
				            player.getPacketSender().sendMessage("You cannot use Baphomet Soul in the Wilderness.");
				             return;
				              }
					if(player.getTransform() == 2236) {
						player.setTimer(player.getTimer()+(100*30));
						player.getInventory().delete(20453, 1);

					} else {
						if(player.getTransform() < 1) {
				player.setNpcTransformationId(2236);
				player.setTransform(2236);
							player.getInventory().delete(20453, 1);

							player.setTimer(100*30);
							player.getUpdateFlag().flag(Flag.APPEARANCE);


                 NPC npc = new NPC(2236,new Position(0,0));
                player.setConstitution(npc.getConstitution());
				player.sendMessage("<col=ff0000>You transformed into a Baphomet.");
		} else {
			player.sendMessage("Please wait till your timer is over!");
		}
	}
				break;
				case 20956:
					if(player.getTransform() == 170) {
						player.setTimer(player.getTimer()+(100*30));
						player.getInventory().delete(20956, 1);

					} else {
						if(player.getTransform() < 1) {
				player.setNpcTransformationId(170);
				player.setTransform(170);
							player.getInventory().delete(20956, 1);

							player.setTimer(100*30);
							player.getUpdateFlag().flag(Flag.APPEARANCE);


                 NPC npc = new NPC(170,new Position(0,0));
                player.setConstitution(npc.getConstitution());
				player.sendMessage("<col=ff0000>You transformed into a Lucario.");
		} else {
			player.sendMessage("Please wait till your timer is over!");
		}
	}
				break;
				case 20957:
					if(player.getTransform() == 360) {
						player.setTimer(player.getTimer()+(100*30));
						player.getInventory().delete(20957, 1);

					} else {
						if(player.getTransform() < 1) {
				player.setNpcTransformationId(360);
				player.setTransform(360);
							player.getInventory().delete(20957, 1);

							player.setTimer(100*30);
							player.getUpdateFlag().flag(Flag.APPEARANCE);


                 NPC npc = new NPC(360,new Position(0,0));
                player.setConstitution(npc.getConstitution());
				player.sendMessage("<col=ff0000>You transformed into a MewTwo.");
		} else {
			player.sendMessage("Please wait till your timer is over!");
		}
	}
				break;
				case 20958:
					if(player.getTransform() == 359) {
						player.setTimer(player.getTimer()+(100*30));
						player.getInventory().delete(20958, 1);

					} else {
						if(player.getTransform() < 1) {
				player.setNpcTransformationId(359);
				player.setTransform(359);
							player.getInventory().delete(20958, 1);

							player.setTimer(100*30);
							player.getUpdateFlag().flag(Flag.APPEARANCE);


                 NPC npc = new NPC(359,new Position(0,0));
                player.setConstitution(npc.getConstitution());
				player.sendMessage("<col=ff0000>You transformed into a Charmeleon.");
		} else {
			player.sendMessage("Please wait till your timer is over!");
		}
	}
				break;
				case 20959:
					if(player.getTransform() == 361) {
						player.setTimer(player.getTimer()+(100*30));
						player.getInventory().delete(20959, 1);

					} else {
						if(player.getTransform() < 1) {
				player.setNpcTransformationId(361);
				player.setTransform(361);
							player.getInventory().delete(20959, 1);

							player.setTimer(100*30);
							player.getUpdateFlag().flag(Flag.APPEARANCE);


                 NPC npc = new NPC(361,new Position(0,0));
                player.setConstitution(npc.getConstitution());
				player.sendMessage("<col=ff0000>You transformed into a Squirtle.");
		} else {
			player.sendMessage("Please wait till your timer is over!");
		}
	}
				break;
				case 20960:
					if(player.getTransform() == 365) {
						player.setTimer(player.getTimer()+(100*30));
						player.getInventory().delete(20960, 1);

					} else {
						if(player.getTransform() < 1) {
				player.setNpcTransformationId(365);
				player.setTransform(365);
							player.getInventory().delete(20960, 1);

							player.setTimer(100*30);
							player.getUpdateFlag().flag(Flag.APPEARANCE);


                 NPC npc = new NPC(365,new Position(0,0));
                player.setConstitution(npc.getConstitution());
				player.sendMessage("<col=ff0000>You transformed into a Pickachu.");
		} else {
			player.sendMessage("Please wait till your timer is over!");
		}
	}
				break;
				case 20961:
					if(player.getTransform() == 363) {
						player.setTimer(player.getTimer()+(100*30));
						player.getInventory().delete(20961, 1);

					} else {
						if(player.getTransform() < 1) {
				player.setNpcTransformationId(363);
				player.setTransform(363);
							player.getInventory().delete(20961, 1);

							player.setTimer(100*30);
							player.getUpdateFlag().flag(Flag.APPEARANCE);


                 NPC npc = new NPC(363,new Position(0,0));
                player.setConstitution(npc.getConstitution());
				player.sendMessage("<col=ff0000>You transformed into a Pickachu.");
		} else {
			player.sendMessage("Please wait till your timer is over!");
		}
	}
				break;
				case 20962:
					if(player.getTransform() == 370) {
						player.setTimer(player.getTimer()+(100*30));
						player.getInventory().delete(20962, 1);

					} else {
						if(player.getTransform() < 1) {
				player.setNpcTransformationId(370);
				player.setTransform(370);
							player.getInventory().delete(20962, 1);

							player.setTimer(100*30);
							player.getUpdateFlag().flag(Flag.APPEARANCE);


                 NPC npc = new NPC(370,new Position(0,0));
                player.setConstitution(npc.getConstitution());
				player.sendMessage("<col=ff0000>You transformed into a Donkey Kong.");
		} else {
			player.sendMessage("Please wait till your timer is over!");
		}
	}
				break;
				case 20963:
					if(player.getTransform() == 362) {
						player.setTimer(player.getTimer()+(100*30));
						player.getInventory().delete(20963, 1);

					} else {
						if(player.getTransform() < 1) {
				player.setNpcTransformationId(362);
				player.setTransform(362);
							player.getInventory().delete(20963, 1);

							player.setTimer(100*30);
							player.getUpdateFlag().flag(Flag.APPEARANCE);


                 NPC npc = new NPC(362,new Position(0,0));
                player.setConstitution(npc.getConstitution());
				player.sendMessage("<col=ff0000>You transformed into a Mr Krabs.");
		} else {
			player.sendMessage("Please wait till your timer is over!");
		}
	}
				break;
				case 20964:
					if(player.getTransform() == 368) {
						player.setTimer(player.getTimer()+(100*30));
						player.getInventory().delete(20964, 1);

					} else {
						if(player.getTransform() < 1) {
				player.setNpcTransformationId(368);
				player.setTransform(368);
							player.getInventory().delete(20964, 1);

							player.setTimer(100*30);
							player.getUpdateFlag().flag(Flag.APPEARANCE);


                 NPC npc = new NPC(368,new Position(0,0));
                player.setConstitution(npc.getConstitution());
				player.sendMessage("<col=ff0000>You transformed into a Nutella.");
		} else {
			player.sendMessage("Please wait till your timer is over!");
		}
	}
				break;
				case 20965:
					if(player.getTransform() == 367) {
						player.setTimer(player.getTimer()+(100*30));
						player.getInventory().delete(20965, 1);

					} else {
						if(player.getTransform() < 1) {
				player.setNpcTransformationId(367);
				player.setTransform(367);
							player.getInventory().delete(20965, 1);

							player.setTimer(100*30);
							player.getUpdateFlag().flag(Flag.APPEARANCE);


                 NPC npc = new NPC(367,new Position(0,0));
                player.setConstitution(npc.getConstitution());
				player.sendMessage("<col=ff0000>You transformed into a Mayonnaise.");
		} else {
			player.sendMessage("Please wait till your timer is over!");
		}
	}
				break;

			case 5020:
				int random =  Misc.getRandom((10000));
				NPC bandosava = new NPC(4540, new Position(2865, 9954, random*100+8));
				bandosava.getDefinition().setRespawnTime(5);

				player.sendMessage("Bandos Ava instance");
				World.register(bandosava);
				TeleportHandler.teleportPlayer(player, new Position(2867,9946,random*100+8), player.getSpellbook().getTeleportType());
				player.setInstance(true);
				break;
				case 5018:
				 random =  Misc.getRandom((10000));
				 bandosava = new NPC(6303, new Position(2528, 5175, random*100+8));
				 bandosava.getDefinition().setRespawnTime(5);
					World.register(bandosava);
					bandosava = new NPC(6303, new Position(2504, 5174, random*100+8));
					bandosava.getDefinition().setRespawnTime(5);
					World.register(bandosava);
					player.sendMessage("Abbadon instance");
				World.register(bandosava);
				TeleportHandler.teleportPlayer(player, new Position(2516,5173,random*100+8), player.getSpellbook().getTeleportType());
				player.setInstance(true);
				break;
				case 5016:
					random =  Misc.getRandom((10000));
					bandosava = new NPC(1234, new Position(1236, 1241, random*100+8));
					bandosava.getDefinition().setRespawnTime(5);
					World.register(bandosava);
					bandosava = new NPC(1234, new Position(1246, 1241, random*100+8));
					bandosava.getDefinition().setRespawnTime(5);
					World.register(bandosava);
					player.sendMessage("Infernal Groudon instance");
					World.register(bandosava);
					TeleportHandler.teleportPlayer(player, new Position(1240,1227,random*100+8), player.getSpellbook().getTeleportType());
					player.setInstance(true);
				break;
		case 8008: //Lumbridge
			if (player.inFFALobby){
				player.sendMessage("Use the portal to leave the ffa lobby!");
				return;
			}
			if (player.inFFA) {
				player.sendMessage("You can not teleport out of FFA!");
				return;
			}
			if(!player.getClickDelay().elapsed(1200) || player.getMovementQueue().isLockMovement()) {
				return;
			}
			if(player.getLocation() == Location.CONSTRUCTION) {
				player.getPacketSender().sendMessage("Please use the portal to exit your house");
				return;
			}
		
			
			if(!checkReqs(player, targetLocation)) {
				return;
			}
			player.setTeleporting(true).getMovementQueue().setLockMovement(true).reset();
			cancelCurrentActions(player);
			player.performAnimation(new Animation(4731));
			player.performGraphic(new Graphic(678));
			player.getInventory().delete(8008, 1);
			player.getClickDelay().reset();
		
			TaskManager.submit(new Task(2, player, false) {
				@Override
				public void execute() {		
					Position position = new Position(3222, 3218, 0);
					player.moveTo(position);
					player.getMovementQueue().setLockMovement(false).reset();
					this.stop();
				}
			});
		
	
	
			
			 break;
		case 8009: //Falador
			if (player.inFFALobby){
				player.sendMessage("Use the portal to leave the ffa lobby!");
				return;
			}
			if (player.inFFA) {
				player.sendMessage("You can not teleport out of FFA!");
				return;
			}
			if(!player.getClickDelay().elapsed(1200) || player.getMovementQueue().isLockMovement()) {
				return;
			}
			if(player.getLocation() == Location.CONSTRUCTION) {
				player.getPacketSender().sendMessage("Please use the portal to exit your house");
				return;
			}
		
			
			if(!checkReqs(player, targetLocation)) {
				return;
			}
			player.setTeleporting(true).getMovementQueue().setLockMovement(true).reset();
			cancelCurrentActions(player);
			player.performAnimation(new Animation(4731));
			player.performGraphic(new Graphic(678));
			player.getInventory().delete(8009, 1);
			player.getClickDelay().reset();
		
			TaskManager.submit(new Task(2, player, false) {
				@Override
				public void execute() {		
					Position position = new Position(2964, 3378, 0);
					player.moveTo(position);
					player.getMovementQueue().setLockMovement(false).reset();
					this.stop();
				}
			});
		
	
	
			
			 break;
		case 8010: //Camelot
			if (player.inFFALobby){
				player.sendMessage("Use the portal to leave the ffa lobby!");
				return;
			}
			if (player.inFFA) {
				player.sendMessage("You can not teleport out of FFA!");
				return;
			}
			if(!player.getClickDelay().elapsed(1200) || player.getMovementQueue().isLockMovement()) {
				return;
			}
			if(player.getLocation() == Location.CONSTRUCTION) {
				player.getPacketSender().sendMessage("Please use the portal to exit your house");
				return;
			}
			
			
			if(!checkReqs(player, targetLocation)) {
				return;
			}
			player.setTeleporting(true).getMovementQueue().setLockMovement(true).reset();
			cancelCurrentActions(player);
			player.performAnimation(new Animation(4731));
			player.performGraphic(new Graphic(678));
			player.getInventory().delete(8010, 1);
			player.getClickDelay().reset();
		
			TaskManager.submit(new Task(2, player, false) {
				@Override
				public void execute() {		
					Position position = new Position(2757, 3477, 0);
					player.moveTo(position);
					player.getMovementQueue().setLockMovement(false).reset();
					this.stop();
				}
			});
		
			
			 break;
		case 8011: //Ardy
			if (player.inFFALobby){
				player.sendMessage("Use the portal to leave the ffa lobby!");
				return;
			}
			if (player.inFFA) {
				player.sendMessage("You can not teleport out of FFA!");
				return;
			}
			if(!player.getClickDelay().elapsed(1200) || player.getMovementQueue().isLockMovement()) {
				return;
			}
			if(player.getLocation() == Location.CONSTRUCTION) {
				player.getPacketSender().sendMessage("Please use the portal to exit your house");
				return;
			}
		
			
			if(!checkReqs(player, targetLocation)) {
				return;
			}
			player.setTeleporting(true).getMovementQueue().setLockMovement(true).reset();
			cancelCurrentActions(player);
			player.performAnimation(new Animation(4731));
			player.performGraphic(new Graphic(678));
			player.getInventory().delete(8011, 1);
			player.getClickDelay().reset();
		
			TaskManager.submit(new Task(2, player, false) {
				@Override
				public void execute() {		
					Position position = new Position(2662, 3305, 0);
					player.moveTo(position);
					player.getMovementQueue().setLockMovement(false).reset();
					this.stop();
				}
			});
		
	
			 break;
		case 8012: //Watchtower Tele
			if (player.inFFALobby){
				player.sendMessage("Use the portal to leave the ffa lobby!");
				return;
			}
			if (player.inFFA) {
				player.sendMessage("You can not teleport out of FFA!");
				return;
			}
			if(!player.getClickDelay().elapsed(1200) || player.getMovementQueue().isLockMovement()) {
				return;
			}
			if(player.getLocation() == Location.CONSTRUCTION) {
				player.getPacketSender().sendMessage("Please use the portal to exit your house");
				return;
			}
		
			
			if(!checkReqs(player, targetLocation)) {
				return;
			}
			player.setTeleporting(true).getMovementQueue().setLockMovement(true).reset();
			cancelCurrentActions(player);
			player.performAnimation(new Animation(4731));
			player.performGraphic(new Graphic(678));
			player.getInventory().delete(8012, 1);
			player.getClickDelay().reset();
		
			TaskManager.submit(new Task(2, player, false) {
				@Override
				public void execute() {		
					Position position = new Position(2728, 3349, 0);
					player.moveTo(position);
					player.getMovementQueue().setLockMovement(false).reset();
					this.stop();
				}
			});
		
	
			 break;
		case 8013: //Home Tele
			 TeleportHandler.teleportPlayer(player, new Position(3087, 3491), TeleportType.NORMAL);
			 break;
		case 13598: //Runecrafting Tele
			 TeleportHandler.teleportPlayer(player, new Position(2595, 4772), TeleportType.NORMAL);
			 break;
		case 13599: //Air Altar Tele
			 TeleportHandler.teleportPlayer(player, new Position(2845, 4832), TeleportType.NORMAL);
			 break;
		case 13600: //Mind Altar Tele
			 TeleportHandler.teleportPlayer(player, new Position(2796, 4818), TeleportType.NORMAL);
			 break;
		case 13601: //Water Altar Tele
			 TeleportHandler.teleportPlayer(player, new Position(2713, 4836), TeleportType.NORMAL);
			 break;
		case 13602: //Earth Altar Tele
			 TeleportHandler.teleportPlayer(player, new Position(2660, 4839), TeleportType.NORMAL);
			 break;
		case 13603: //Fire Altar Tele
			 TeleportHandler.teleportPlayer(player, new Position(2584, 4836), TeleportType.NORMAL);
			 break;
		case 13604: //Body Altar Tele
			 TeleportHandler.teleportPlayer(player, new Position(2527, 4833), TeleportType.NORMAL);
			 break;
		case 13605: //Cosmic Altar Tele
			 TeleportHandler.teleportPlayer(player, new Position(2162, 4833), TeleportType.NORMAL);
			 break;
		case 13606: //Chaos Altar Tele
			 TeleportHandler.teleportPlayer(player, new Position(2269,4843), TeleportType.NORMAL);
			 break;
		case 13607: //Nature Altar Tele
			 TeleportHandler.teleportPlayer(player, new Position(2398, 4841), TeleportType.NORMAL);
			 break;
		case 13608: //Law Altar Tele
			 TeleportHandler.teleportPlayer(player, new Position(2464, 4834), TeleportType.NORMAL);
			 break;
		case 13609: //Death Altar Tele
			 TeleportHandler.teleportPlayer(player, new Position(2207, 4836), TeleportType.NORMAL);
			 break;
		case 18809: //Rimmington Tele
			 TeleportHandler.teleportPlayer(player, new Position(2957, 3214), TeleportType.NORMAL);
			 break;
		case 18811: //Pollnivneach Tele
			 TeleportHandler.teleportPlayer(player, new Position(3359,2910), TeleportType.NORMAL);
			 break;
		case 18812: //Rellekka Tele
			 TeleportHandler.teleportPlayer(player, new Position(2659, 3661), TeleportType.NORMAL);
			 break;
		case 18814: //Yanielle Tele
			 TeleportHandler.teleportPlayer(player, new Position(2606, 3093), TeleportType.NORMAL);
			 break;
		case 6542:
			ChristmasPresent.openBox(player);
			break;
			
			
		case 10025:
			CharmBox.open(player);
			break;
			
			
		case 1959:
			TrioBosses.eatPumpkin(player);
			break;
		case 2677:
		case 2678:
		case 2679:
		case 2680:
		case 2681:
		case 2682:
		case 2683:
		case 2684:
		case 2685:
			ClueScrolls.giveHint(player, itemId);
			break;
		case 7956:
			if (player.getRights() == PlayerRights.DONATOR) {
				if (Misc.getRandom(15) == 5) {
					player.getPacketSender().sendMessage("Casket has been saved as a donator benefit");
				} else {
					player.getInventory().delete(7956, 1);
				}
			}
			if (player.getRights() == PlayerRights.SUPER_DONATOR || player.getRights() == PlayerRights.SUPPORT) {
				if (Misc.getRandom(12) == 5) {
					player.getPacketSender().sendMessage("Casket has been saved as a donator benefit");
				} else {
					player.getInventory().delete(7956, 1);
				}
			}
			if (player.getRights() == PlayerRights.EXTREME_DONATOR || player.getRights() == PlayerRights.MODERATOR) {
				if (Misc.getRandom(9) == 5) {
					player.getPacketSender().sendMessage("Casket has been saved as a donator benefit");
				} else {
					player.getInventory().delete(7956, 1);
				}
			}
			if (player.getRights() == PlayerRights.LEGENDARY_DONATOR  || player.getRights() == PlayerRights.ADMINISTRATOR) {
				if (Misc.getRandom(6) == 5) {
					player.getPacketSender().sendMessage("Casket has been saved as a donator benefit");
				} else {
					player.getInventory().delete(7956, 1);
				}
			}
			if (player.getRights() == PlayerRights.UBER_DONATOR) {
				if (Misc.getRandom(3) == 2) {
					player.getPacketSender().sendMessage("Casket has been saved as a donator benefit");
				} else {
					player.getInventory().delete(7956, 1);
				}
			}
			if (player.getRights() == PlayerRights.DELUXE_DONATOR) {
				if (Misc.getRandom(2) == 1) {
					player.getPacketSender().sendMessage("Casket has been saved as a donator benefit");
				} else {
					player.getInventory().delete(7956, 1);
				}
			}
			if (player.getRights() == PlayerRights.PLAYER || player.getRights() == PlayerRights.YOUTUBER) {
				player.getInventory().delete(7956, 1);
			}
			if (player.getRights() == PlayerRights.OWNER || player.getRights() == PlayerRights.YOUTUBER) {
				player.getInventory().delete(7956, 1);
			}
			int[] rewards = 		{5022,5022,5022,5022,5022,5022,5022,5022,5022,5022,5022,5022,5022,5022,5022,5022,5022,5022,5022,5022,5022};
			int[] rewardsAmount = 	{100,200,300,400,500,600,700,800,900,1000,1100,1200,1300,1400,100,200,100,200,300,200,300};
			int rewardPos = Misc.getRandom(rewards.length-1);
			player.getInventory().add(rewards[rewardPos], ((Misc.random(rewardsAmount[rewardPos])) + (Misc.getRandom(rewardsAmount[rewardPos]))));
			break;

			//Donation Box
		case 6183:
			DonationBox.open(player);
			break;
		case 20986:
			DoflamingoSetBox.open(player);
			break;
			case 15369:
			RUBoxes.openCommonBox(player);
			break;
		case 15370:
			RUBoxes.openUncommonBox(player);
			break;
		case 15371:
			RUBoxes.openRareBox(player);
			break;
		case 15372:
			RUBoxes.openExtremeBox(player);
			break;
		case 15373:
			RUBoxes.openLegendaryBox(player);
			break;
		case 19934:
			PetBox.OpenPetBox(player);
			break;
			//Clue Scroll
		case 2714:
			ClueScrolls.addClueRewards(player);
			break;
		case 621:
			player.getInventory().delete(621, 1);
			player.getPointsHandler().incrementTriviaPoints(10);
			player.getPacketSender().sendMessage("You have claimed 10 Trivia Points");
			break;
			
		case 13208:
			if (!player.getCorruptBandagesLoading().getContents().isEmpty()) {
				for (Entry<Integer> crystal : player.getCorruptBandagesLoading().getContents().entrySet()) {
					player.getCorruptBandagesLoading().getContents().remove(crystal.getElement());
					player.setConstitution(player.getConstitution() + 150);
					if(player.getConstitution() > 1190)
						player.setConstitution(1190);
					Sounds.sendSound(player, Sound.EAT_FOOD);
					player.getPacketSender().sendMessage("@red@The Corrupt bandage has healed you 15 Hitpoints");
					return;
				}
			} else {
				player.getPacketSender().sendMessage("@red@You have no crystals in your Bandage!");
			}
			break;
			
		

		case 15387:
			player.getInventory().delete(15387, 1);
			rewards = new int[] {1377, 1149, 7158, 3000, 219, 5016, 6293, 6889, 2205, 3051, 269, 329, 3779, 6371, 2442, 347, 247};
			player.getInventory().add(rewards[RandomUtility.getRandom(rewards.length-1)], 1);
			break;
		case 407:
			player.getInventory().delete(407, 1);
			if (RandomUtility.getRandom(3) < 3) {
				player.getInventory().add(409, 1);
			} else if(RandomUtility.getRandom(4) < 4) {
				player.getInventory().add(411, 1);
			} else
				player.getInventory().add(413, 1);
			break;
		case 405:
			player.getInventory().delete(405, 1);
			if (RandomUtility.getRandom(1) < 1) {
				int b = RandomUtility.getRandom(30000);
				player.getInventory().add(5022, b);
				player.getPacketSender().sendMessage("The casket contained "+b+" 1b Tickets!");
			} else
				player.getPacketSender().sendMessage("The casket was empty.");
			break;
		case 15084:
			if(player.getTotalPlayTime() >= 36000000 * 3) {
				Gambling.rollDice(player);
			} else {
				player.sendMessage("@red@You need to play for atleast 30 hours before u can gamble");
				return;
			}
			break;
		case 6307:
			player.setDialogueActionId(79);
			DialogueManager.start(player, 0);
			break;
		case 299:
			if(player.getTotalPlayTime() >= 36000000 * 3) {
				Gambling.plantSeed(player);
			} else {
				player.sendMessage("@red@You need to play for atleast 50 hours before u can gamble");
			}
			break;
		case 4155:
			if(player.getSlayer().getSlayerTask() == SlayerTasks.NO_TASK) {
				player.getPacketSender().sendInterfaceRemoval();
				player.getPacketSender().sendMessage("Your Enchanted gem will only work if you have a Slayer task.");
				return;
			}
			DialogueManager.start(player, SlayerDialogues.dialogue(player));
			break;
		case 11858:
		case 11860:
		case 11862:
		case 11848:
		case 11856:
		case 11850:
		case 11854:
		case 11852:
		case 11846:
			if(!player.getClickDelay().elapsed(2000) || !player.getInventory().contains(itemId))
				return;
			if(player.busy()) {
				player.getPacketSender().sendMessage("You cannot open this right now.");
				return;
			}

			int[] items = itemId == 11858 ? new int[] {10350, 10348, 10346, 10352} : 
				itemId == 11860 ? new int[]{10334, 10330, 10332, 10336} : 
					itemId == 11862 ? new int[]{10342, 10338, 10340, 10344} : 
						itemId == 11848 ? new int[]{4716, 4720, 4722, 4718} : 
							itemId == 11856 ? new int[]{4753, 4757, 4759, 4755} : 
								itemId == 11850 ? new int[]{4724, 4728, 4730, 4726} : 
									itemId == 11854 ? new int[]{4745, 4749, 4751, 4747} : 
										itemId == 11852 ? new int[]{4732, 4734, 4736, 4738} : 
											itemId == 11846 ? new int[]{4708, 4712, 4714, 4710} :
												new int[]{itemId};

											if(player.getInventory().getFreeSlots() < items.length) {
												player.getPacketSender().sendMessage("You do not have enough space in your inventory.");
												return;
											}
											player.getInventory().delete(itemId, 1);
											for(int i : items) {
												player.getInventory().add(i, 1);
											}
											player.getPacketSender().sendMessage("You open the set and find items inside.");
											player.getClickDelay().reset();
											break;
		case 952:
			Digging.dig(player);
			break;
		case 10006:
			// Hunter.getInstance().laySnare(client);
			Hunter.layTrap(player, new SnareTrap(new GameObject(19175, new Position(player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ())), TrapState.SET, 200, player));
			break;
		case 10008:			
			Hunter.layTrap(player, new BoxTrap(new GameObject(19187, new Position(player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ())), TrapState.SET, 200, player));
			break;
		case 5509:
		case 5510:
		case 5512:
			RunecraftingPouches.fill(player, RunecraftingPouch.forId(itemId));
			break;
		case 292:
			IngridientsBook.readBook(player, 0, false);
			break;
		case 600:
			GuidanceBook.readBook1(player, 0, false);
			break;
		case 6199:
			MysteryBox mysteryBox = new MysteryBox(player);
			mysteryBox.openInterface();
			player.setBox(6199);
			break;

		case 20870:
			AthenaMysteryBox AthenaMysteryBox = new AthenaMysteryBox(player);
			AthenaMysteryBox.openInterface();
			player.setBox(20870);
			break;	

		case 20931:
			UltraMysteryBox UltramysteryBox = new UltraMysteryBox(player);
			UltramysteryBox.openInterface();
			player.setBox(20931);
			break;
			
			case 7100:
				int rewards3[][] = {
						{11995,11996,11997,12001,12002,12003,12004,2771,2771,19935,12005,12006,11990,11981,11979,2090,2091}, //Uncommon, 0
						{11991,11992,11993,11994,11989,11988,11987,11986,11985,11984,2759,2760,2761,2762,11983,11982,}, //Rare, 1
						{2104,2758,2095,2096,2097,2098}
				};
				double numGen3 = Math.random();
				/** Chances
				 *  50% chance of Common Items - cheap gear, high-end consumables
				 *  40% chance of Uncommon Items - various high-end coin-bought gear
				 *  10% chance of Rare Items - Highest-end coin-bought gear, some voting-point/pk-point equipment
				 */
				int rewardGrade3;
				int random3 = Misc.random(99)+1;
				if(random3 > 50) {
					rewardGrade3 = 0;
				} else if(random3 > 4) {
					rewardGrade3 = 1;
				} else {
					rewardGrade3 = 2;
				}


				rewardPos = RandomUtility.getRandom(rewards3[rewardGrade3].length-1);
				player.getInventory().delete(7100, 1);
				player.getInventory().add(rewards3[rewardGrade3][rewardPos], 1).refreshItems();
					World.sendMessage("<shad=0>@bla@[@cya@Pet box@bla@] @cya@"+player.getUsername()+"@bla@ Has just received a @cya@ "+ItemDefinition.forId(rewards3[rewardGrade3][rewardPos]).getName()+" @bla@!");
				break;

				case 7102:
					YodaBox yodaBox = new YodaBox(player);
					yodaBox.openInterface();
					player.setBox(7102);
				break;

				case 7104:
					GodzillaBox godzillaBox = new GodzillaBox(player);
					godzillaBox.openInterface();
					player.setBox(7104);
				break;

				case 7106:
					AssasinBox assasinBox = new AssasinBox(player);
					assasinBox.openInterface();
					player.setBox(7106);
				break;

				case 7108:
					DonkeyBox donkeyBox = new DonkeyBox(player);
					donkeyBox.openInterface();
					player.setBox(7108);
				break;

				case 7110:
					OblivionBox oblivionBox = new OblivionBox(player);
					oblivionBox.openInterface();
					player.setBox(7110);
				break;

				case 7112:
					BandosBox bandosBox = new BandosBox(player);
					bandosBox.openInterface();
					player.setBox(7112);

				break;

				case 7124:
					BaphometBox baphometBox = new BaphometBox(player);
					baphometBox.openInterface();
					player.setBox(7124);

				break;

            case 7114:
				AbbadonBox abbadonBox = new AbbadonBox(player);
				abbadonBox.openInterface();
				player.setBox(7114);
				break;

            case 7122:
				spiderBox abbadonBox2 = new spiderBox(player);
				abbadonBox2.openInterface();
				player.setBox(7122);
				break;

				case 7116:
					InfernalGroudonBox infernalGroudonBox = new InfernalGroudonBox(player);
					infernalGroudonBox.openInterface();
					player.setBox(7116);
				break;

				case 7120:
					NeocortexBox neocortexBox = new NeocortexBox(player);
					neocortexBox.openInterface();
					player.setBox(7120);
//					if(player.getInventory().getFreeSlots() < 2) {
//						player.getPacketSeßßßßnder().sendMessage("You don't have enough free inventory space.");
//						return;
//					}
//					 int neo[][] = {
//							{5022}, //Uncommon, 0
//							{20702,20701,20700,20705,20706,20703,20704}, //Rare, 1
//							{2104,2758,2095,2096,2097,2098}
//					};
//				 numGen3 = Math.random();
//
//				  randomYoda = Misc.random(275);
//
//					 drBoost = NPCDrops.getDroprate(player);
//					randomYoda = (int)randomYoda * ((100-drBoost)/100);
//				 amount = 1;
//				if(randomYoda == 0) {
//					rewardGrade3 = 1;
//					rewardPos = RandomUtility.getRandom(neo[rewardGrade3].length-1);
//					player.setAnimation(new Animation(6382));
//					player.setGraphic(new Graphic(127));
//					World.sendMessage("<shad=0>@bla@[@whi@Neo Cortex box@bla@] @whi@"+player.getUsername()+"@bla@ Has just received a @whi@ "+ItemDefinition.forId(neo[rewardGrade3][rewardPos]).getName()+"@bla@!");
//					double randomDouble = Misc.random(99);
//
//					double ddrBoost = NPCDrops.getDoubleDr(player);
//					randomDouble = (int)randomDouble * ((100-ddrBoost)/100);
//					if(randomDouble == 0) {
//						amount *= 2;
//					}
//				} else {
//					rewardGrade3 = 0;
//					rewardPos = RandomUtility.getRandom(neo[rewardGrade3].length-1);
//					double randomDouble = Misc.random(99);
//
//					double ddrBoost = NPCDrops.getDoubleDr(player);
//					randomDouble = (int)randomDouble * ((100-ddrBoost)/100);
//					amount = Misc.random(225);
//
//					if(randomDouble == 0) {
//						amount *= 2;
//					}
//				}
//				player.getInventory().delete(7120, 1);
//				player.getInventory().add(neo[rewardGrade3][rewardPos], amount).refreshItems();
				break;

            case 3230:
// Legendary_Donator(12424, new Item[] {new Item(12424,34)}, 1, 12425),
//        Dark_Lord_Body(3652, new Item[] {new Item(11316,250)}, 10, 3687),
//        Dark_Lord_Legs(3653, new Item[] {new Item(11316,250)}, 10, 3688),
//        Dark_Lord_Boots(3654, new
                int rewards5[][] = {
                        {3651,3652,3653,3654,20511, 20257,11533}, //Uncommon, 0
                        { 18911,21059,21058,21057,21056,19787,19788,12424, 18989, 20089, 11423,11533, 3981, 20054, 18908, 11556 }, //Rare, 1
                        {18890,18891,18931,3692,2101,83,11533,3687,3688,3689, 19057,12424, 12425, 20089, 18989,13215, 3642, 3643,3644, 3645, 3647, 3648, 18890,11555}
                };
                numGen3 = Math.random();
                /** Chances
                 *  70% chance of Common Items - cheap gear, high-end consumables
                 *  26% chance of Uncommon Items - various high-end coin-bought gear
                 *  4% chance of Rare Items - Highest-end coin-bought gear, some voting-point/pk-point equipment
                 */
                random3 = Misc.random(99)+1;
				if(random3 > 30) {
					rewardGrade3 = 0;
				} else if(random3 > 4) {
					rewardGrade3 = 1;
				} else {
					rewardGrade3 = 2;
				}



                rewardPos = RandomUtility.getRandom(rewards5[rewardGrade3].length-1);
                player.getInventory().delete(3230, 1);
                player.getInventory().add(rewards5[rewardGrade3][rewardPos], 1).refreshItems();
                World.sendMessage("<shad=0>@bla@[@or2@Uber box@bla@] @or2@"+player.getUsername()+"@bla@ Has just received a @or2@ "+ItemDefinition.forId(rewards5[rewardGrade3][rewardPos]).getName()+" @bla@!");
                break;
			case 7118:
				DonatorBox donorbox = new DonatorBox(player);
				donorbox.openInterface();
				player.setBox(7118);
				break;
//			case 3230:
//				UberBox donorbox = new UberBBox(player);
//				donorbox.openInterface();
//			player.setBox(7118);
//			break;
//

			case 21045:

				int rewards4[][] = {
						{894}, //Uncommon, 0
						{895,700,906,907,908}, //Rare, 1
						{896,21044,2867}
				};
				 numGen3 = Math.random();
				/** Chances
				 *  50% chance of Common Items - cheap gear, high-end consumables
				 *  40% chance of Uncommon Items - various high-end coin-bought gear
				 *  10% chance of Rare Items - Highest-end coin-bought gear, some voting-point/pk-point equipment
				 */
				random3 = Misc.random(99)+1;
				if(random3 > 50) {
					rewardGrade3 = 0;
				} else if(random3 > 25) {
					rewardGrade3 = 1;
				} else {
					rewardGrade3 = 2;
				}


				rewardPos = RandomUtility.getRandom(rewards4[rewardGrade3].length-1);
				player.getInventory().delete(21045, 1);
				player.getInventory().add(rewards4[rewardGrade3][rewardPos], 1).refreshItems();
					World.sendMessage("<shad=0>@bla@[@cya@Weaponbox box@bla@] @cya@"+player.getUsername()+"@bla@ Has just received a @cya@ "+ItemDefinition.forId(rewards4[rewardGrade3][rewardPos]).getName()+" @bla@!");
				break;
		case 15501:
			int superiorRewards[][] = {
					{11995,11996,11997,12001,12002,12003,12004,2771,2771,19935,12005,12006,11990,11981,11979,2090,2091}, //Uncommon, 0
					{11991,11992,11993,11994,11989,11988,11987,11986,11985,11984,2759,2760,2761,2762,11983,11982,}, //Rare, 1
					{2104,2758,2095,2096,2097,2098} //Legendary, 3
			};
			double superiorNumGen = Math.random();
			/** Chances
			 *  40% chance of Uncommon Items - various high-end coin-bought gear
			 *  30% chance of Rare Items - Highest-end coin-bought gear, Some poor voting-point/pk-point equipment
			 *  20% chance of Epic Items -Better voting-point/pk-point equipment
			 *  10% chance of Legendary Items - Only top-notch voting-point/pk-point equipment
			 */
			int superiorRewardGrade = superiorNumGen >= 0.60 ? 0 : superiorNumGen >= 0.30 ? 1 : superiorNumGen >= 0.10 ? 2 : 3;
			int superiorRewardPos = RandomUtility.getRandom(superiorRewards[superiorRewardGrade].length-1);
			player.getInventory().delete(15501, 1);
			player.getInventory().add(superiorRewards[superiorRewardGrade][superiorRewardPos], 1).refreshItems();
			break;
		case 11882:
			player.getInventory().delete(11882, 1);
			player.getInventory().add(2595, 1).refreshItems();
			player.getInventory().add(2591, 1).refreshItems();
			player.getInventory().add(3473, 1).refreshItems();
			player.getInventory().add(2597, 1).refreshItems();
			break;
		case 11884:
			player.getInventory().delete(11884, 1);
			player.getInventory().add(2595, 1).refreshItems();
			player.getInventory().add(2591, 1).refreshItems();
			player.getInventory().add(2593, 1).refreshItems();
			player.getInventory().add(2597, 1).refreshItems();
			break;
		case 11906:
			player.getInventory().delete(11906, 1);
			player.getInventory().add(7394, 1).refreshItems();
			player.getInventory().add(7390, 1).refreshItems();
			player.getInventory().add(7386, 1).refreshItems();
			break;
		case 15262:
			if(!player.getClickDelay().elapsed(1000))
				return;
			player.getInventory().delete(15262, 1);
			player.getInventory().add(18016, 10000).refreshItems();
			player.getClickDelay().reset();
			break;
		case 6:
			//DwarfMultiCannon.setupCannon(player);
			break;
		}
	}

	public static void secondAction(Player player, Packet packet) {
		int interfaceId = packet.readLEShortA();
		int slot = packet.readLEShort();
		int itemId = packet.readShortA();


		if(slot < 0 || slot > player.getInventory().capacity())
			return;
		if(player.getInventory().getItems()[slot].getId() != itemId)
			return;
		if (SummoningData.isPouch(player, itemId, 2))
			return;


		/*if(itemId == 5023) {
			for(int i = 0; i < player.getInventory().getAmount(5023); i++) {
				player.setMoneyInPouch(player.getMoneyInPouch()+(long)1000000000*player.getInventory().getAmount(5023));
				player.getPacketSender().sendString(8135, ""+player.getMoneyInPouch());
				player.getInventory().delete(5023, player.getInventory().getAmount(5023));
			}
		}*/

		switch(itemId) {
		
		case 17489:
			if(player.getInventory().hasItem(new Item(18201, 10000))) {
				player.getInventory().delete(18201, 10000);
				if(Dungeoneering.doingDungeoneering(player) && player.getMinigameAttributes().getDungeoneeringAttributes().getParty().getComplexity() == 1) {
					ShopManager.getShops().get(200).open(player);
				} else if(Dungeoneering.doingDungeoneering(player) && player.getMinigameAttributes().getDungeoneeringAttributes().getParty().getComplexity() == 2) {
					ShopManager.getShops().get(201).open(player);
				} else if(Dungeoneering.doingDungeoneering(player) && player.getMinigameAttributes().getDungeoneeringAttributes().getParty().getComplexity() == 3) {
					ShopManager.getShops().get(202).open(player);
				} else if(Dungeoneering.doingDungeoneering(player) && player.getMinigameAttributes().getDungeoneeringAttributes().getParty().getComplexity() == 4) {
					ShopManager.getShops().get(203).open(player);
				}
			} else {
				player.sendMessage("You do not have <col=FF0000>10k </col>coins to open the store.");
			}
			break;

		case 21007:
		case 21006:
			if(player.getInventory().hasItem(new Item(21007,100)) && player.getInventory().hasItem(new Item(21006, 1))){
				player.getInventory().delete(21007, 100);
				player.getInventory().delete(21006, 1);
				player.getInventory().addItem(18898, 1);
				World.sendMessage("<shad=0>@bla@[@whi@Mythical@bla@] <shad=0>@whi@"+ player.getUsername() + "@bla@ has just forged a mythical blade");
			} else {
				player.sendMessage("You need 100 shards to forge a mythical blade");
			}
			break;
			
		case 18898:
		case 18:	
			if(player.getInventory().hasItem(new Item(18898,1)) && player.getInventory().hasItem(new Item(18, 4))){
				player.getInventory().delete(18898, 1);
				player.getInventory().delete(18, 4);
				player.getInventory().addItem(20873, 1);
				World.sendMessage("<shad=0>@bla@[@whi@Gemstone@bla@] <shad=0>@whi@"+ player.getUsername() + "@bla@ has just forged a @mag@Duel Gem Sword!");
			} else {
				player.sendMessage("@mag@You need 4 Secret Dust and Gemstone Sword to forge a Dual Sword");
			}
			break;
		case 20943:	
			if(player.getInventory().hasItem(new Item(20944,1000)) && player.getInventory().hasItem(new Item(20943, 1000))){
				player.getInventory().delete(20944, 1000);
				player.getInventory().delete(20943, 1000);
				player.getInventory().addItem(20810, 1);
				World.sendMessage("<shad=0>@bla@[@bla@Vanquisher@bla@] <shad=0>@mag@"+ player.getUsername() + "@red@ has just forged a @cya@Dragon vanquisher helm!");
			} else {
				player.sendMessage("@mag@You need 1000 Lucario and 1000 MewTwo Tokens to forge a Vanquisher helm.");
			}
			break;
		case 20944:	
			if(player.getInventory().hasItem(new Item(20943,1000)) && player.getInventory().hasItem(new Item(20944, 1000))){
				player.getInventory().delete(20943, 1000);
				player.getInventory().delete(20944, 1000);
				player.getInventory().addItem(20810, 1);
				World.sendMessage("<shad=0>@bla@[@bla@Vanquisher@bla@] <shad=0>@mag@"+ player.getUsername() + "@red@ has just forged a @cya@Dragon vanquisher body!");
			} else {
				player.sendMessage("@mag@You need 1000 MewTwo and 1000 Lucario Tokens to forge a Vanquisher helm.");
			}
			break;
		case 20945:	
			if(player.getInventory().hasItem(new Item(20946,1000)) && player.getInventory().hasItem(new Item(20945, 1000))){
				player.getInventory().delete(20946, 1000);
				player.getInventory().delete(20945, 1000);
				player.getInventory().addItem(20811, 1);
				World.sendMessage("<shad=0>@bla@[@bla@Vanquisher@bla@] <shad=0>@mag@"+ player.getUsername() + "@red@ has just forged a @cya@Dragon vanquisher body!");
			} else {
				player.sendMessage("@mag@You need 1000 Charmeleon and 1000 Squirtle Tokens to forge a Vanquisher body.");
			}
			break;
		case 20946:	
			if(player.getInventory().hasItem(new Item(20945,1000)) && player.getInventory().hasItem(new Item(20946, 1000))){
				player.getInventory().delete(20945, 1000);
				player.getInventory().delete(20946, 1000);
				player.getInventory().addItem(20811, 1);
				World.sendMessage("<shad=0>@bla@[@bla@Vanquisher@bla@] <shad=0>@mag@"+ player.getUsername() + "@red@ has just forged a @cya@Dragon vanquisher body!");
			} else {
				player.sendMessage("@mag@You need 1000 Squirtle and 1000 Charmeleon Tokens to forge a Vanquisher body.");
			}
			break;
		case 20947:	
			if(player.getInventory().hasItem(new Item(20948,1000)) && player.getInventory().hasItem(new Item(20947, 1000))){
				player.getInventory().delete(20948, 1000);
				player.getInventory().delete(20947, 1000);
				player.getInventory().addItem(20812, 1);
				World.sendMessage("<shad=0>@bla@[@bla@Vanquisher@bla@] <shad=0>@mag@"+ player.getUsername() + "@red@ has just forged a @cya@Dragon vanquisher legs!");
			} else {
				player.sendMessage("@mag@You need 1000 Pickachu and 1000 Sonic Tokens to forge a Vanquisher legs.");
			}
			break;
		case 20948:	
			if(player.getInventory().hasItem(new Item(20947,1000)) && player.getInventory().hasItem(new Item(20948, 1000))){
				player.getInventory().delete(20947, 1000);
				player.getInventory().delete(20948, 1000);
				player.getInventory().addItem(20812, 1);
				World.sendMessage("<shad=0>@bla@[@bla@Vanquisher@bla@] <shad=0>@mag@"+ player.getUsername() + "@red@ has just forged a @cya@Dragon vanquisher legs!");
			} else {
				player.sendMessage("@mag@You need 1000 Sonic and 1000 Pickachu Tokens to forge a Vanquisher legs.");
			}
			break;
		case 20949:	
			if(player.getInventory().hasItem(new Item(16599,1000)) && player.getInventory().hasItem(new Item(20949, 1000))){
				player.getInventory().delete(16599, 1000);
				player.getInventory().delete(20949, 1000);
				player.getInventory().addItem(20813, 1);
				World.sendMessage("<shad=0>@bla@[@bla@Vanquisher@bla@] <shad=0>@mag@"+ player.getUsername() + "@red@ has just forged a @cya@Dragon vanquisher gauntlets!");
			} else {
				player.sendMessage("@mag@You need 1000 Donkey Kong and 1000 Mr Krabs Tokens to forge Vanquish gauntlets.");
			}
			break;
		case 16599:	
			if(player.getInventory().hasItem(new Item(20949,1000)) && player.getInventory().hasItem(new Item(16599, 1000))){
				player.getInventory().delete(20949, 1000);
				player.getInventory().delete(16599, 1000);
				player.getInventory().addItem(20813, 1);
				World.sendMessage("<shad=0>@bla@[@bla@Vanquisher@bla@] <shad=0>@mag@"+ player.getUsername() + "@red@ has just forged a @cya@Dragon vanquisher gauntlets!");
			} else {
				player.sendMessage("@mag@You need 1000 Mr Krabs and 1000 Donkey Kong Tokens to forge Vanquish gauntlets.");
			}
			break;
		case 16600:	
			if(player.getInventory().hasItem(new Item(16601,1000)) && player.getInventory().hasItem(new Item(16600, 1000))){
				player.getInventory().delete(16601, 1000);
				player.getInventory().delete(16600, 1000);
				player.getInventory().addItem(20814, 1);
				World.sendMessage("<shad=0>@bla@[@bla@Vanquisher@bla@] <shad=0>@mag@"+ player.getUsername() + "@red@ has just forged a @cya@Dragon vanquisher boots!");
			} else {
				player.sendMessage("@mag@You need 1000 Mayonnaise and 1000 Nutella Tokens to forge a Vanquisher boots.");
			}
			break;
		case 16601:	
			if(player.getInventory().hasItem(new Item(16600,1000)) && player.getInventory().hasItem(new Item(16601, 1000))){
				player.getInventory().delete(16600, 1000);
				player.getInventory().delete(16601, 1000);
				player.getInventory().addItem(20814, 1);
				World.sendMessage("<shad=0>@bla@[@bla@Vanquisher@bla@] <shad=0>@mag@"+ player.getUsername() + "@red@ has just forged a @cya@Dragon vanquisher boots!");
			} else {
				player.sendMessage("@mag@You need 1000 Nutella and 1000 Mayonnaise Tokens to forge a Vanquisher boots.");
			}
			break;
		case 20891:	
			if(player.getInventory().hasItem(new Item(20891,1)) &&	player.getInventory().hasItem(new Item(20892,1)) &&  player.getInventory().hasItem(new Item(20893, 1))){
				player.getInventory().delete(20891, 1);
				player.getInventory().delete(20892, 1);
				player.getInventory().delete(20893, 1);
				player.getInventory().addItem(20966, 1);
				World.sendMessage("<shad=0>@bla@[@bla@Roy@bla@] <shad=0>@mag@"+ player.getUsername() + "@red@ has just forged a @cya@Roy Transformation!");
			} else {
				player.sendMessage("@mag@You need @red@1 Roy Mask - 1 Roy Body - 1 Roy Legs @mag@to forge a Roy Transformation.");
			}
			break;
		case 20892:	
			if(player.getInventory().hasItem(new Item(20892,1)) &&	player.getInventory().hasItem(new Item(20891,1)) &&  player.getInventory().hasItem(new Item(20893, 1))){
				player.getInventory().delete(20892, 1);
				player.getInventory().delete(20891, 1);
				player.getInventory().delete(20893, 1);
				player.getInventory().addItem(20966, 1);
				World.sendMessage("<shad=0>@bla@[@bla@Roy@bla@] <shad=0>@mag@"+ player.getUsername() + "@red@ has just forged a @cya@Roy Transformation!");
			} else {
				player.sendMessage("@mag@You need @red@1 Roy Body - 1 Roy Mask - 1 Roy Legs @mag@to forge a Roy Transformation.");
			}
			break;
		case 20893:	
			if(player.getInventory().hasItem(new Item(20893,1)) &&	player.getInventory().hasItem(new Item(20891,1)) &&  player.getInventory().hasItem(new Item(20892, 1))){
				player.getInventory().delete(20893, 1);
				player.getInventory().delete(20891, 1);
				player.getInventory().delete(20892, 1);
				player.getInventory().addItem(20966, 1);
				World.sendMessage("<shad=0>@bla@[@bla@Roy@bla@] <shad=0>@mag@"+ player.getUsername() + "@red@ has just forged a @cya@Roy Transformation!");
			} else {
				player.sendMessage("@mag@You need @red@1 Roy Legs - 1 Roy Mask - 1 Roy Body @mag@to forge a Roy Transformation.");
			}
			break;
		case 20966:	
			if(player.getInventory().hasItem(new Item(20966,100))){
				player.getInventory().delete(20966, 100);
				player.getInventory().addItem(19004, 1);
				World.sendMessage("<shad=0>@bla@[@bla@Roy@bla@] <shad=0>@blu@"+ player.getUsername() + "@red@ has just forged a @mag@Royal Blade!");
			} else {
				player.sendMessage("@mag@You need @red@100 Roy Transformations @mag@to forge a Royal Blade.");
			}
			break;
		case 6500:
			if(player.getCombatBuilder().isAttacking() || player.getCombatBuilder().isBeingAttacked()) {
				player.getPacketSender().sendMessage("You cannot configure this right now.");
				return;
			}
			player.getPacketSender().sendInterfaceRemoval();
			DialogueManager.start(player, 101);
			player.setDialogueActionId(60);
			break;
		case 12926:
			player.getBlowpipeLoading().handleUnloadBlowpipe();
			break;
		case 3962:
			player.getDragonRageLoading().handleUnloadDragonrage();
			break;
		case 896:
			player.getMinigunLoading().handleUnloadMinigun();
			break;
		case 13208:
			player.getCorruptBandagesLoading().handleUnloadCorruptBandages();
			break;

		case 1712:
		case 1710:
		case 1708:
		case 1706:
		case 11118:
		case 11120:
		case 11122:
		case 11124:
			JewelryTeleporting.rub(player, itemId);
			break;
		case 1704:
			player.getPacketSender().sendMessage("Your amulet has run out of charges.");
			break;
		case 11126:
			player.getPacketSender().sendMessage("Your bracelet has run out of charges.");
			break;
		case 13281:
		case 13282:
		case 13283:
		case 13284:
		case 13285:
		case 13286:
		case 13287:
		case 13288:
			player.getSlayer().handleSlayerRingTP(itemId);
			break;
		case 5509:
		case 5510:
		case 5512:
			RunecraftingPouches.check(player, RunecraftingPouch.forId(itemId));
			break;
		/*case 5023:
			player.setMoneyInPouch(player.getMoneyInPouch()+(long)1000000000*player.getInventory().getAmount(5023));
			player.getPacketSender().sendString(8135, ""+player.getMoneyInPouch());
			player.getInventory().delete(5023, player.getInventory().getAmount(5023));
			MoneyPouch.depositMoney(player, player.getInventory().getAmount(5023));
			break;*/
		case 1438:
		case 1448:
		case 1440:
		case 1442:
		case 1444:
		case 1446:
		case 1454:
		case 1452:
		case 1462:
		case 1458:
		case 1456:
		case 1450:
			Runecrafting.handleTalisman(player, itemId);
			break;
		}
	}

	public void thirdClickAction(Player player, Packet packet) {
		int itemId = packet.readShortA();
		int slot = packet.readLEShortA();
		int interfaceId = packet.readLEShortA();
		if(slot < 0 || slot > player.getInventory().capacity())
			return;
		if(player.getInventory().getItems()[slot].getId() != itemId)
			return;
		if(JarData.forJar(itemId) != null) {
			PuroPuro.lootJar(player, new Item(itemId, 1), JarData.forJar(itemId));
			return;
		}
		if (SummoningData.isPouch(player, itemId, 3)) {
			return;
		}
		/*if(itemId == 5023) {
			int amount = 0;
			for(int i = 0; i < player.getInventory().getAmount(5023); i++) {
				amount++;
			}
			player.getInventory().delete(5023,amount);
			player.setMoneyInPouch((long) 1000000000*amount);
		}*/
		switch(itemId) {
		
		case 21007:
		case 21006:
			if(player.getInventory().hasItem(new Item(21007,100)) && player.getInventory().hasItem(new Item(21006, 1))){
				player.getInventory().delete(21007, 100);
				player.getInventory().delete(21006, 1);
				player.getInventory().addItem(18898, 1);
				World.sendMessage("<shad=0>@bla@[@whi@Mythical@bla@] <shad=0>@whi@"+ player.getUsername() + "@bla@ has just forged a mythical blade");
			} else {
				player.sendMessage("You need 100 shards to forge a mythical blade");
			}
			break;
			
		case 18898:
		case 18:	
			if(player.getInventory().hasItem(new Item(18898,1)) && player.getInventory().hasItem(new Item(18, 4))){
				player.getInventory().delete(18898, 1);
				player.getInventory().delete(18, 4);
				player.getInventory().addItem(20873, 1);
				World.sendMessage("<shad=0>@bla@[@whi@Gemstone@bla@] <shad=0>@whi@"+ player.getUsername() + "@bla@ has just forged a @mag@Duel Gem Sword!");
			} else {
				player.sendMessage("@mag@You need 4 Secret Dust and Gemstone Sword to forge a Dual Sword");
			}
			break;
		case 20943:	
			if(player.getInventory().hasItem(new Item(20944,1000)) && player.getInventory().hasItem(new Item(20943, 1000))){
				player.getInventory().delete(20944, 1000);
				player.getInventory().delete(20943, 1000);
				player.getInventory().addItem(20810, 1);
				World.sendMessage("<shad=0>@bla@[@bla@Vanquisher@bla@] <shad=0>@mag@"+ player.getUsername() + "@red@ has just forged a @cya@Dragon vanquisher helm!");
			} else {
				player.sendMessage("@mag@You need 1000 Lucario and 1000 MewTwo Tokens to forge a Vanquisher helm.");
			}
			break;
		case 20944:	
			if(player.getInventory().hasItem(new Item(20943,1000)) && player.getInventory().hasItem(new Item(20944, 1000))){
				player.getInventory().delete(20943, 1000);
				player.getInventory().delete(20944, 1000);
				player.getInventory().addItem(20810, 1);
				World.sendMessage("<shad=0>@bla@[@bla@Vanquisher@bla@] <shad=0>@mag@"+ player.getUsername() + "@red@ has just forged a @cya@Dragon vanquisher body!");
			} else {
				player.sendMessage("@mag@You need 1000 MewTwo and 1000 Lucario Tokens to forge a Vanquisher helm.");
			}
			break;
		case 20945:	
			if(player.getInventory().hasItem(new Item(20946,1000)) && player.getInventory().hasItem(new Item(20945, 1000))){
				player.getInventory().delete(20946, 1000);
				player.getInventory().delete(20945, 1000);
				player.getInventory().addItem(20811, 1);
				World.sendMessage("<shad=0>@bla@[@bla@Vanquisher@bla@] <shad=0>@mag@"+ player.getUsername() + "@red@ has just forged a @cya@Dragon vanquisher body!");
			} else {
				player.sendMessage("@mag@You need 1000 Charmeleon and 1000 Squirtle Tokens to forge a Vanquisher body.");
			}
			break;
		case 20946:	
			if(player.getInventory().hasItem(new Item(20945,1000)) && player.getInventory().hasItem(new Item(20946, 1000))){
				player.getInventory().delete(20945, 1000);
				player.getInventory().delete(20946, 1000);
				player.getInventory().addItem(20811, 1);
				World.sendMessage("<shad=0>@bla@[@bla@Vanquisher@bla@] <shad=0>@mag@"+ player.getUsername() + "@red@ has just forged a @cya@Dragon vanquisher body!");
			} else {
				player.sendMessage("@mag@You need 1000 Squirtle and 1000 Charmeleon Tokens to forge a Vanquisher body.");
			}
			break;
		case 20947:	
			if(player.getInventory().hasItem(new Item(20948,1000)) && player.getInventory().hasItem(new Item(20947, 1000))){
				player.getInventory().delete(20948, 1000);
				player.getInventory().delete(20947, 1000);
				player.getInventory().addItem(20812, 1);
				World.sendMessage("<shad=0>@bla@[@bla@Vanquisher@bla@] <shad=0>@mag@"+ player.getUsername() + "@red@ has just forged a @cya@Dragon vanquisher legs!");
			} else {
				player.sendMessage("@mag@You need 1000 Pickachu and 1000 Sonic Tokens to forge a Vanquisher legs.");
			}
			break;
		case 20948:	
			if(player.getInventory().hasItem(new Item(20947,1000)) && player.getInventory().hasItem(new Item(20948, 1000))){
				player.getInventory().delete(20947, 1000);
				player.getInventory().delete(20948, 1000);
				player.getInventory().addItem(20812, 1);
				World.sendMessage("<shad=0>@bla@[@bla@Vanquisher@bla@] <shad=0>@mag@"+ player.getUsername() + "@red@ has just forged a @cya@Dragon vanquisher legs!");
			} else {
				player.sendMessage("@mag@You need 1000 Sonic and 1000 Pickachu Tokens to forge a Vanquisher legs.");
			}
			break;
		case 20949:	
			if(player.getInventory().hasItem(new Item(16599,1000)) && player.getInventory().hasItem(new Item(20949, 1000))){
				player.getInventory().delete(16599, 1000);
				player.getInventory().delete(20949, 1000);
				player.getInventory().addItem(20813, 1);
				World.sendMessage("<shad=0>@bla@[@bla@Vanquisher@bla@] <shad=0>@mag@"+ player.getUsername() + "@red@ has just forged a @cya@Dragon vanquisher gauntlets!");
			} else {
				player.sendMessage("@mag@You need 1000 Donkey Kong and 1000 Mr Krabs Tokens to forge Vanquish gauntlets.");
			}
			break;
		case 16599:	
			if(player.getInventory().hasItem(new Item(20949,1000)) && player.getInventory().hasItem(new Item(16599, 1000))){
				player.getInventory().delete(20949, 1000);
				player.getInventory().delete(16599, 1000);
				player.getInventory().addItem(20813, 1);
				World.sendMessage("<shad=0>@bla@[@bla@Vanquisher@bla@] <shad=0>@mag@"+ player.getUsername() + "@red@ has just forged a @cya@Dragon vanquisher gauntlets!");
			} else {
				player.sendMessage("@mag@You need 1000 Mr Krabs and 1000 Donkey Kong Tokens to forge Vanquish gauntlets.");
			}
			break;
		case 16600:	
			if(player.getInventory().hasItem(new Item(16601,1000)) && player.getInventory().hasItem(new Item(16600, 1000))){
				player.getInventory().delete(16601, 1000);
				player.getInventory().delete(16600, 1000);
				player.getInventory().addItem(20814, 1);
				World.sendMessage("<shad=0>@bla@[@bla@Vanquisher@bla@] <shad=0>@mag@"+ player.getUsername() + "@red@ has just forged a @cya@Dragon vanquisher boots!");
			} else {
				player.sendMessage("@mag@You need 1000 Mayonnaise and 1000 Nutella Tokens to forge a Vanquisher boots.");
			}
			break;
		case 16601:	
			if(player.getInventory().hasItem(new Item(16600,1000)) && player.getInventory().hasItem(new Item(16601, 1000))){
				player.getInventory().delete(16600, 1000);
				player.getInventory().delete(16601, 1000);
				player.getInventory().addItem(20814, 1);
				World.sendMessage("<shad=0>@bla@[@bla@Vanquisher@bla@] <shad=0>@mag@"+ player.getUsername() + "@red@ has just forged a @cya@Dragon vanquisher boots!");
			} else {
				player.sendMessage("@mag@You need 1000 Nutella and 1000 Mayonnaise Tokens to forge a Vanquisher boots.");
			}
			break;
		case 20891:	
			if(player.getInventory().hasItem(new Item(20891,1)) &&	player.getInventory().hasItem(new Item(20892,1)) &&  player.getInventory().hasItem(new Item(20893, 1))){
				player.getInventory().delete(20891, 1);
				player.getInventory().delete(20892, 1);
				player.getInventory().delete(20893, 1);
				player.getInventory().addItem(20966, 1);
				World.sendMessage("<shad=0>@bla@[@bla@Roy@bla@] <shad=0>@mag@"+ player.getUsername() + "@red@ has just forged a @cya@Roy Transformation!");
			} else {
				player.sendMessage("@mag@You need @red@1 Roy Mask - 1 Roy Body - 1 Roy Legs @mag@to forge a Roy Transformation.");
			}
			break;
		case 20892:	
			if(player.getInventory().hasItem(new Item(20892,1)) &&	player.getInventory().hasItem(new Item(20891,1)) &&  player.getInventory().hasItem(new Item(20893, 1))){
				player.getInventory().delete(20892, 1);
				player.getInventory().delete(20891, 1);
				player.getInventory().delete(20893, 1);
				player.getInventory().addItem(20966, 1);
				World.sendMessage("<shad=0>@bla@[@bla@Roy@bla@] <shad=0>@mag@"+ player.getUsername() + "@red@ has just forged a @cya@Roy Transformation!");
			} else {
				player.sendMessage("@mag@You need @red@1 Roy Body - 1 Roy Mask - 1 Roy Legs @mag@to forge a Roy Transformation.");
			}
			break;
		case 20893:	
			if(player.getInventory().hasItem(new Item(20893,1)) &&	player.getInventory().hasItem(new Item(20891,1)) &&  player.getInventory().hasItem(new Item(20892, 1))){
				player.getInventory().delete(20893, 1);
				player.getInventory().delete(20891, 1);
				player.getInventory().delete(20892, 1);
				player.getInventory().addItem(20966, 1);
				World.sendMessage("<shad=0>@bla@[@bla@Roy@bla@] <shad=0>@mag@"+ player.getUsername() + "@red@ has just forged a @cya@Roy Transformation!");
			} else {
				player.sendMessage("@mag@You need @red@1 Roy Legs - 1 Roy Mask - 1 Roy Body @mag@to forge a Roy Transformation.");
			}
			break;
		case 20966:	
			if(player.getInventory().hasItem(new Item(20966,100))){
				player.getInventory().delete(20966, 100);
				player.getInventory().addItem(19004, 1);
				World.sendMessage("<shad=0>@bla@[@bla@Roy@bla@] <shad=0>@blu@"+ player.getUsername() + "@red@ has just forged a @mag@Royal Blade!");
			} else {
				player.sendMessage("@mag@You need @red@100 Roy Transformations @mag@to forge a Royal Blade.");
			}
			break;
			
		case 14019:
			player.getPacketSender().sendInterface(60000);
			break;
		case 12926:
			player.getBlowpipeLoading().handleCheckBlowpipe();
			break;
		case 3962:
			player.getDragonRageLoading().handleCheckDragonrage();
			break;
		case 896:
			player.getMinigunLoading().handleCheckMinigun();
			break;
		case 13208:
			player.getCorruptBandagesLoading().handleCheckCorruptBandages();
			break;
		case 19670:
			if(player.busy()) {
				player.getPacketSender().sendMessage("You can not do this right now.");
				return;
			}
			player.setDialogueActionId(71);
			DialogueManager.start(player, player.getGameMode() == GameMode.NORMAL ? 108 : 109);
			break;
		case 6500:
			CharmingImp.sendConfig(player);
			break;
		case 4155:
			player.getPacketSender().sendInterfaceRemoval();
			DialogueManager.start(player, 103);
			player.setDialogueActionId(65);
			break;
		case 13281:
		case 13282:
		case 13283:
		case 13284:
		case 13285:
		case 13286:
		case 13287:
		case 13288:
			player.getPacketSender().sendInterfaceRemoval();
			player.getPacketSender().sendMessage(player.getSlayer().getSlayerTask() == SlayerTasks.NO_TASK ? ("You do not have a Slayer task.") : ("Your current task is to kill another "+(player.getSlayer().getAmountToSlay())+" "+Misc.formatText(player.getSlayer().getSlayerTask().toString().toLowerCase().replaceAll("_", " "))+"s."));
			break;
			case 12421:
				if(player.getRights() == PlayerRights.DELUXE_DONATOR || player.getRights() == PlayerRights.DONATOR || player.getRights() == PlayerRights.YOUTUBER || player.getRights() == PlayerRights.SUPER_DONATOR || player.getRights() == PlayerRights.EXTREME_DONATOR || player.getRights() == PlayerRights.LEGENDARY_DONATOR || player.getRights() == PlayerRights.UBER_DONATOR || player.getRights() == PlayerRights.SUPPORT || player.getRights() == PlayerRights.MODERATOR){
					player.sendMessage("You already have a higher tier donator rank!");
				} else {
					player.setRights(PlayerRights.DONATOR);
					player.getInventory().delete(12421,	1);
					PlayerPanel.refreshPanel(player);
					World.sendMessage("<shad=0>@bla@[@red@Donator@bla@] @red@"+player.getUsername()+" @bla@ Has just claimed his @red@donator rank@bla@!");
				}
				break;
				case 12422:
				if(player.getRights() == PlayerRights.DELUXE_DONATOR || player.getRights() == PlayerRights.EXTREME_DONATOR || player.getRights() == PlayerRights.YOUTUBER || player.getRights() == PlayerRights.SUPER_DONATOR || player.getRights() == PlayerRights.LEGENDARY_DONATOR || player.getRights() == PlayerRights.UBER_DONATOR  || player.getRights() == PlayerRights.SUPPORT || player.getRights() == PlayerRights.MODERATOR){
					player.sendMessage("You already have a higher tier donator rank!");
				} else {
					player.setRights(PlayerRights.SUPER_DONATOR);
					player.getInventory().delete(12422,	1);
					PlayerPanel.refreshPanel(player);
					World.sendMessage("<shad=0>@bla@[@gre@Super Donator@bla@] @gre@"+player.getUsername()+" @bla@ Has just claimed his @gre@super donator rank@bla@!");
				}
				break;
				case 12423:
				if(player.getRights() == PlayerRights.DELUXE_DONATOR || player.getRights() == PlayerRights.EXTREME_DONATOR || player.getRights() == PlayerRights.YOUTUBER || player.getRights() == PlayerRights.LEGENDARY_DONATOR ||player.getRights() == PlayerRights.UBER_DONATOR || player.getRights() == PlayerRights.SUPPORT || player.getRights() == PlayerRights.MODERATOR){
					player.sendMessage("You already have a higher tier donator rank!");
				} else {
					player.setRights(PlayerRights.EXTREME_DONATOR);
					player.getInventory().delete(12423,	1);
					PlayerPanel.refreshPanel(player);
					World.sendMessage("<shad=0>@bla@[@mag@Extreme Donator@bla@] @mag@"+player.getUsername()+" @bla@ Has just claimed his @mag@extreme donator rank@bla@!");
				}
				break;
				case 12424:
				if(player.getRights() == PlayerRights.DELUXE_DONATOR || player.getRights() == PlayerRights.LEGENDARY_DONATOR || player.getRights() == PlayerRights.YOUTUBER || player.getRights() == PlayerRights.UBER_DONATOR || player.getRights() == PlayerRights.SUPPORT || player.getRights() == PlayerRights.MODERATOR){
					player.sendMessage("You already have a higher tier donator rank!");
				} else {
					player.setRights(PlayerRights.LEGENDARY_DONATOR);
					player.getInventory().delete(12424,	1);
					PlayerPanel.refreshPanel(player);
					World.sendMessage("<shad=0>@bla@[@yel@Legendary Donator@bla@] @yel@"+player.getUsername()+" @bla@ Has just claimed his @yel@Legendary donator rank@bla@!");
				}
				break;

				case 12425:
				if(player.getRights() == PlayerRights.DELUXE_DONATOR || player.getRights() == PlayerRights.UBER_DONATOR || player.getRights() == PlayerRights.SUPPORT || player.getRights() == PlayerRights.YOUTUBER || player.getRights() == PlayerRights.MODERATOR){
					player.sendMessage("You already have a higher tier donator rank!");
				} else {
					player.setRights(PlayerRights.UBER_DONATOR);
					player.getInventory().delete(12425,	1);
					PlayerPanel.refreshPanel(player);
					World.sendMessage("<shad=0>@bla@[@cya@Uber Donator@bla@] @cya@"+player.getUsername()+" @bla@ Has just claimed his @cya@uber donator rank@bla@!");
				}
				break;
				case 20985:
				if(player.getRights() == PlayerRights.DELUXE_DONATOR || player.getRights() == PlayerRights.YOUTUBER || player.getRights() == PlayerRights.SUPPORT || player.getRights() == PlayerRights.MODERATOR){
					player.sendMessage("You already have a higher tier donator rank!");
				} else {
					player.setRights(PlayerRights.DELUXE_DONATOR);
					player.getInventory().delete(20985,	1);
					PlayerPanel.refreshPanel(player);
					World.sendMessage("<shad=0>@bla@[@blu@Deluxe Donator@bla@] @blu@"+player.getUsername()+" @bla@ Has just claimed his @blu@deluxe donator rank@bla@!");
				}
				break;

		case 15262:
			if(!player.getClickDelay().elapsed(1300))
				return;
			int amt = player.getInventory().getAmount(15262);
			if(amt > 0)
				player.getInventory().delete(15262, amt).add(18016, 10000 * amt);
			player.getClickDelay().reset();
			break;
		case 5509:
		case 5510:
		case 5512:
			RunecraftingPouches.empty(player, RunecraftingPouch.forId(itemId));
			break;
		case 11283: //DFS
			player.getPacketSender().sendMessage("Your Dragonfire shield has "+player.getDfsCharges()+"/20 dragon-fire charges.");
			break;
		case 11613: //dkite
			player.getPacketSender().sendMessage("Your Dragonfire shield has "+player.getDfsCharges()+"/20 dragon-fire charges.");
			break;
		}
	}

	@Override
	public void handleMessage(Player player, Packet packet) {
		if (player.getConstitution() <= 0)
			return;
		switch (packet.getOpcode()) {
		case SECOND_ITEM_ACTION_OPCODE:
			secondAction(player, packet);
			break;
		case FIRST_ITEM_ACTION_OPCODE:
			firstAction(player, packet);
			break;
		case THIRD_ITEM_ACTION_OPCODE:
			thirdClickAction(player, packet);
			break;
		}
	}

	public static final int SECOND_ITEM_ACTION_OPCODE = 75;

	public static final int FIRST_ITEM_ACTION_OPCODE = 122;

	public static final int THIRD_ITEM_ACTION_OPCODE = 16;

}