package com.ruse.net.packet.impl;

import com.ruse.GameServer;
import com.ruse.GameSettings;
import com.ruse.engine.task.Task;
import com.ruse.engine.task.TaskManager;
import com.ruse.model.Animation;
import com.ruse.model.Direction;
import com.ruse.model.Flag;
import com.ruse.model.GameObject;
import com.ruse.model.Graphic;
import com.ruse.model.Item;
import com.ruse.model.PlayerRights;
import com.ruse.model.Position;
import com.ruse.model.Skill;
import com.ruse.model.Locations.Location;
import com.ruse.model.container.impl.Bank;
import com.ruse.model.container.impl.Equipment;
import com.ruse.model.container.impl.Shop.ShopManager;
import com.ruse.model.definitions.ItemDefinition;
import com.ruse.model.definitions.WeaponAnimations;
import com.ruse.model.definitions.WeaponInterfaces;
import com.ruse.net.packet.Packet;
import com.ruse.net.packet.PacketListener;
import com.ruse.net.security.ConnectionHandler;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.content.BonusManager;
import com.ruse.world.content.Lottery;
import com.ruse.world.content.PlayerLogs;
import com.ruse.world.content.PlayerPunishment;
import com.ruse.world.content.PlayersOnlineInterface;
import com.ruse.world.content.WellOfGoodwill;
import com.ruse.world.content.PlayerPunishment.Jail;
import com.ruse.world.content.clan.ClanChatManager;
import com.ruse.world.content.combat.CombatFactory;
import com.ruse.world.content.combat.DesolaceFormulas;
import com.ruse.world.content.combat.effect.EquipmentBonus;
import com.ruse.world.content.combat.weapon.CombatSpecial;
import com.ruse.world.content.dialogue.DialogueManager;
import com.ruse.world.content.grandexchange.GrandExchange;
import com.ruse.world.content.grandexchange.GrandExchangeOffers;
import com.ruse.world.content.randomevents.EvilTree;
import com.ruse.world.content.randomevents.ShootingStar;
import com.ruse.world.content.skill.SkillManager;
import com.ruse.world.content.skill.impl.construction.Construction;
import com.ruse.world.content.skill.impl.slayer.SlayerTasks;
import com.ruse.world.content.transportation.TeleportHandler;
import com.ruse.world.content.transportation.TeleportType;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;
import com.ruse.world.entity.impl.player.PlayerSaving;

import mysql.MySQLController;


/**
 * This packet listener manages commands a player uses by using the
 * command console prompted by using the "`" char.
 * 
 * @author Gabriel Hannason
 */

public class CommandPacketListener implements PacketListener {

	@Override
	public void handleMessage(Player player, Packet packet) {
		String command = Misc.readString(packet.getBuffer());
		String[] parts = command.toLowerCase().split(" ");
		if(command.contains("\r") || command.contains("\n")) {
			return;
		}
		try {
			switch (player.getRights()) {
			case PLAYER:
				playerCommands(player, parts, command);
				break;
			case MODERATOR:
				playerCommands(player, parts, command);
				memberCommands(player, parts, command);
				helperCommands(player, parts, command);
				moderatorCommands(player, parts, command);
				break;
			case ADMINISTRATOR:
				playerCommands(player, parts, command);
				memberCommands(player, parts, command);
				helperCommands(player, parts, command);
				moderatorCommands(player, parts, command);
				administratorCommands(player, parts, command);
				break;
			case OWNER:
				playerCommands(player, parts, command);
				memberCommands(player, parts, command);
				helperCommands(player, parts, command);
				moderatorCommands(player, parts, command);
				administratorCommands(player, parts, command);
				ownerCommands(player, parts, command);
				developerCommands(player, parts, command);
				break;
			case DEVELOPER:
				playerCommands(player, parts, command);
				memberCommands(player, parts, command);
				helperCommands(player, parts, command);
				moderatorCommands(player, parts, command);
				administratorCommands(player, parts, command);
				ownerCommands(player, parts, command);
				developerCommands(player, parts, command);
				break;
			case SUPPORT:
				playerCommands(player, parts, command);
				memberCommands(player, parts, command);
				helperCommands(player, parts, command);
				break;
			case VETERAN:
				playerCommands(player, parts, command);
				memberCommands(player, parts, command);
				break;
			case BRONZE_MEMBER:
			case SILVER_MEMBER:
			case GOLD_MEMBER:
			case PLATINUM_MEMBER:
			case DIAMOND_MEMBER:
				playerCommands(player, parts, command);
				memberCommands(player, parts, command);
				break;
			default:
				break;
			}
		} catch (Exception exception) {
			exception.printStackTrace();

			if(player.getRights() == PlayerRights.DEVELOPER) {
				player.getPacketSender().sendMessage("Error executing that command.");

			} else {
				player.getPacketSender().sendMessage("Error executing that command.");
			}

		}
	}

	private static void playerCommands(final Player player, String[] command, String wholeCommand)  {
		if (command[0].equals("item") && (
						player.getRights() == PlayerRights.ADMINISTRATOR ||
						player.getRights() == PlayerRights.OWNER ||
						player.getRights() == PlayerRights.DEVELOPER)) {
			int id = Integer.parseInt(command[1]);	
			if(id > ItemDefinition.getMaxAmountOfItems()) {
				player.getPacketSender().sendMessage("Invalid item id entered. Max amount of items: "+ItemDefinition.getMaxAmountOfItems());
				return;
			}
			int amount = (command.length == 2 ? 1 : Integer.parseInt(command[2].trim().toLowerCase().replaceAll("k", "000").replaceAll("m", "000000").replaceAll("b", "000000000")));
			if(amount > Integer.MAX_VALUE) {
				amount = Integer.MAX_VALUE;
			}
			Item item = new Item(id, amount);
			player.getInventory().add(item, true);
		}
		if(command[0].equals("roll") && player.getUsername().equalsIgnoreCase("aero")) {
			if(player.getClanChatName() == null) {
				player.getPacketSender().sendMessage("You need to be in a clanchat channel to roll a dice.");
				return;
			} else if(player.getClanChatName().equalsIgnoreCase("help")) {
				player.getPacketSender().sendMessage("You can't roll a dice in this clanchat channel!");
				return;
			}
			int dice = Integer.parseInt(command[1]);
			player.getMovementQueue().reset();
			player.performAnimation(new Animation(11900));
			player.performGraphic(new Graphic(2075));
			ClanChatManager.sendMessage(player.getCurrentClanChat(), "@bla@[ClanChat] @whi@"+player.getUsername()+" just rolled @bla@" +dice+ "@whi@ on the percentile dice.");
		}
		if (wholeCommand.equalsIgnoreCase("commands")) {
			player.getPacketSender().sendMessage("@or2@Player Commands");
			player.getPacketSender().sendMessage("::claim ::donate ::attacks ::save ::vote ::help ");
			player.getPacketSender().sendMessage("::empty ::players (Members)::yell");
			player.getPacketSender().sendMessage("@red@Helper Commands");
			player.getPacketSender().sendMessage("::jail ::remindvote ::staffzone ::unjail ::movehome");
			player.getPacketSender().sendMessage("::mute ::teleto");
		}
		if(wholeCommand.equalsIgnoreCase("claim")) {
			player.getPacketSender().sendMessage("You can only claim items from Sir Prysin in the Edgeville bank.");
		}
		if (wholeCommand.equalsIgnoreCase("donate") || wholeCommand.equalsIgnoreCase("store")) {
			player.getPacketSender().sendString(1, "www.Ruseps.com/store/");
			player.getPacketSender().sendMessage("Attempting to open: www.Ruseps.com/store/");
		}
		if(command[0].equalsIgnoreCase("attacks")) {
			int attack = DesolaceFormulas.getMeleeAttack(player);
			int range = DesolaceFormulas.getRangedAttack(player);
			int magic = DesolaceFormulas.getMagicAttack(player);
			player.getPacketSender().sendMessage("@bla@Melee attack: @or2@"+attack+"@bla@, ranged attack: @or2@"+range+"@bla@, magic attack: @or2@"+magic);
		}
		if (command[0].equals("save")) {
			player.save();
			player.getPacketSender().sendMessage("Your progress has been saved.");
		}
		if (command[0].equals("vote")) {
			player.getPacketSender().sendString(1, "http://Ruseps.com/vote/?user="+player.getUsername());
			player.getPacketSender().sendMessage("Attempting to open: www.Ruseps.com/vote/");
		}
		if (command[0].equals("hiscores")) {
			player.getPacketSender().sendString(1, "http://Ruseps.com/hiscores/");
			player.getPacketSender().sendMessage("Attempting to open: www.Ruseps.com/hiscores/");
		}
		if (command[0].equals("thread")) {
			int thread = Integer.parseInt(command[1]);
			player.getPacketSender().sendString(1, "http://Ruseps.com/forums/index.php?/topic/"+thread+"-thread/");
			player.getPacketSender().sendMessage("Attempting to open: www.Ruseps.com/forums/index.php?/topic/"+thread+"-thread/");
		}
		if (command[0].equals("home")) {
			if(player.getLocation() != null && player.getLocation() == Location.WILDERNESS) {
				player.getPacketSender().sendMessage("You cannot do this at the moment.");
				return;
			}
			Position position = new Position(3094, 3503, 0);
			player.moveTo(position);
			player.getPacketSender().sendMessage("Teleporting you home!");
		}
		if(command[0].equals("help")) {
			if(player.getLastYell().elapsed(30000)) {
				World.sendStaffMessage("<col=FF0066><img=10> [TICKET SYSTEM]<col=6600FF> "+player.getUsername()+" has requested help. Please help them!");
				player.getLastYell().reset();
				player.getPacketSender().sendMessage("<col=663300>Your help request has been received. Please be patient.");
			} else {
				player.getPacketSender().sendMessage("").sendMessage("<col=663300>You need to wait 30 seconds before using this again.").sendMessage("<col=663300>If it's an emergency, please private message a staff member directly instead.");
			}
		}
		if(command[0].equals("empty")) {
			player.getPacketSender().sendInterfaceRemoval().sendMessage("You clear your inventory.");
			player.getSkillManager().stopSkilling();
			player.getInventory().resetItems().refreshItems();
		}
		if(command[0].equals("players")) {
			player.getPacketSender().sendInterfaceRemoval();
			PlayersOnlineInterface.showInterface(player);
		}
		if(command[0].equalsIgnoreCase("[cn]")) {
			if(player.getInterfaceId() == 40172) {
				ClanChatManager.setName(player, wholeCommand.substring(wholeCommand.indexOf(" ") + 1));
			}
		}
	}

	private static void memberCommands(final Player player, String[] command, String wholeCommand) {
		if(wholeCommand.equalsIgnoreCase("bank") && player.getAmountDonated() >= 200) { //Diamond member
			if(player.getInterfaceId() > 0) {
				player.getPacketSender().sendMessage("Please close the interface you have open before opening another one.");
				return;
			}
			if(player.getLocation() == Location.WILDERNESS || player.getLocation() == Location.DUNGEONEERING || player.getLocation() == Location.DUEL_ARENA) {
				player.getPacketSender().sendMessage("You cannot open your bank here.");
				return;
			}
			player.getBank(player.getCurrentBankTab()).open();
		}
		/*

		if (command[0].contains("setgear")) {
			player.getPreSetEquipment().setItem(Equipment.HEAD_SLOT, new Item(player.getEquipment().get(Equipment.HEAD_SLOT).getId()));
			player.getPreSetEquipment().setItem(Equipment.CAPE_SLOT, new Item(player.getEquipment().get(Equipment.CAPE_SLOT).getId()));
			player.getPreSetEquipment().setItem(Equipment.AMULET_SLOT, new Item(player.getEquipment().get(Equipment.AMULET_SLOT).getId()));
			player.getPreSetEquipment().setItem(Equipment.WEAPON_SLOT, new Item(player.getEquipment().get(Equipment.WEAPON_SLOT).getId()));
			player.getPreSetEquipment().setItem(Equipment.BODY_SLOT, new Item(player.getEquipment().get(Equipment.BODY_SLOT).getId()));
			player.getPreSetEquipment().setItem(Equipment.SHIELD_SLOT, new Item(player.getEquipment().get(Equipment.SHIELD_SLOT).getId()));
			player.getPreSetEquipment().setItem(Equipment.LEG_SLOT, new Item(player.getEquipment().get(Equipment.LEG_SLOT).getId()));
			player.getPreSetEquipment().setItem(Equipment.HANDS_SLOT, new Item(player.getEquipment().get(Equipment.HANDS_SLOT).getId()));
			player.getPreSetEquipment().setItem(Equipment.RING_SLOT, new Item(player.getEquipment().get(Equipment.RING_SLOT).getId()));
			player.getPreSetEquipment().setItem(Equipment.FEET_SLOT, new Item(player.getEquipment().get(Equipment.FEET_SLOT).getId()));
			player.getPreSetEquipment().setItem(Equipment.AMMUNITION_SLOT, new Item(player.getEquipment().get(Equipment.AMMUNITION_SLOT).getId()));
			player.getPacketSender().sendMessage(MessageType.PLAYER_ALERT, "You succesfully saved your gear!");
		}
		
		if (command[0].contains("checkgear")) {
			player.getPacketSender().sendMessage(MessageType.PLAYER_ALERT, "Helmet: @gre@"+ player.getPreSetEquipment().get(Equipment.HEAD_SLOT).getDefinition().getName());
			player.getPacketSender().sendMessage(MessageType.PLAYER_ALERT, "Cape: @gre@"+ player.getPreSetEquipment().get(Equipment.CAPE_SLOT).getDefinition().getName());
			player.getPacketSender().sendMessage(MessageType.PLAYER_ALERT, "Amulet: @gre@"+ player.getPreSetEquipment().get(Equipment.AMULET_SLOT).getDefinition().getName());
			player.getPacketSender().sendMessage(MessageType.PLAYER_ALERT, "Weapon: @gre@"+ player.getPreSetEquipment().get(Equipment.WEAPON_SLOT).getDefinition().getName());
			player.getPacketSender().sendMessage(MessageType.PLAYER_ALERT, "Torso: @gre@"+ player.getPreSetEquipment().get(Equipment.BODY_SLOT).getDefinition().getName());
			player.getPacketSender().sendMessage(MessageType.PLAYER_ALERT, "Shield: @gre@"+ player.getPreSetEquipment().get(Equipment.SHIELD_SLOT).getDefinition().getName());
			player.getPacketSender().sendMessage(MessageType.PLAYER_ALERT, "Legs: @gre@"+ player.getPreSetEquipment().get(Equipment.LEG_SLOT).getDefinition().getName());
			player.getPacketSender().sendMessage(MessageType.PLAYER_ALERT, "Hands: @gre@"+ player.getPreSetEquipment().get(Equipment.HANDS_SLOT).getDefinition().getName());
			player.getPacketSender().sendMessage(MessageType.PLAYER_ALERT, "Ring: @gre@"+ player.getPreSetEquipment().get(Equipment.RING_SLOT).getDefinition().getName());
			player.getPacketSender().sendMessage(MessageType.PLAYER_ALERT, "Boots: @gre@"+ player.getPreSetEquipment().get(Equipment.FEET_SLOT).getDefinition().getName());
			player.getPacketSender().sendMessage(MessageType.PLAYER_ALERT, "Ammunition: @gre@"+ player.getPreSetEquipment().get(Equipment.AMMUNITION_SLOT).getDefinition().getName());
		}
		if (command[0].contains("loadgear")) {
			player.getLocation();
			if(player.getLocation() == Location.EDGEVILLE ||
			   player.getLocation() == Location.MEMBER_ZONE){
				boolean canLoad = false;
				Bank playerBank = new Bank(player);
				if(player.getInventory().contains(player.getPreSetEquipment().getItems())){
					player.getInventory().deleteItemSet(player.getPreSetEquipment().getItems());
					canLoad = true;
				}
				if(playerBank.contains(player.getPreSetEquipment().getItems())){
					playerBank.deleteItemSet(player.getPreSetEquipment().getItems());
					canLoad = true;
				}
				if(canLoad){
				player.getEquipment().setItem(Equipment.HEAD_SLOT, new Item(player.getPreSetEquipment().get(Equipment.HEAD_SLOT).getId()));
				player.getEquipment().setItem(Equipment.CAPE_SLOT, new Item(player.getPreSetEquipment().get(Equipment.CAPE_SLOT).getId()));
				player.getEquipment().setItem(Equipment.AMULET_SLOT, new Item(player.getPreSetEquipment().get(Equipment.AMULET_SLOT).getId()));
				player.getEquipment().setItem(Equipment.WEAPON_SLOT, new Item(player.getPreSetEquipment().get(Equipment.WEAPON_SLOT).getId()));
				player.getEquipment().setItem(Equipment.BODY_SLOT, new Item(player.getPreSetEquipment().get(Equipment.BODY_SLOT).getId()));
				player.getEquipment().setItem(Equipment.SHIELD_SLOT, new Item(player.getPreSetEquipment().get(Equipment.SHIELD_SLOT).getId()));
				player.getEquipment().setItem(Equipment.LEG_SLOT, new Item(player.getPreSetEquipment().get(Equipment.LEG_SLOT).getId()));
				player.getEquipment().setItem(Equipment.HANDS_SLOT, new Item(player.getPreSetEquipment().get(Equipment.HANDS_SLOT).getId()));
				player.getEquipment().setItem(Equipment.RING_SLOT, new Item(player.getPreSetEquipment().get(Equipment.RING_SLOT).getId()));
				player.getEquipment().setItem(Equipment.FEET_SLOT, new Item(player.getPreSetEquipment().get(Equipment.FEET_SLOT).getId()));
				player.getEquipment().setItem(Equipment.AMMUNITION_SLOT, new Item(player.getPreSetEquipment().get(Equipment.AMMUNITION_SLOT).getId()));
				BonusManager.update(player);
				WeaponInterfaces.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
				WeaponAnimations.update(player);
				player.getEquipment().refreshItems();
				player.getUpdateFlag().flag(Flag.APPEARANCE);
				player.getPacketSender().sendMessage(MessageType.PLAYER_ALERT, "You succesfully loaded your gear!");
				} 
				if(!canLoad){
					player.getPacketSender().sendMessage(MessageType.PLAYER_ALERT, "@red@You do not have the required items!");
				}
			}
			player.getPacketSender().sendMessage(MessageType.PLAYER_ALERT, "@red@You can only do this in edge/dzone!");
		}*/
		
		
		if(wholeCommand.toLowerCase().startsWith("yell")) {
			if(PlayerPunishment.muted(player.getUsername()) || PlayerPunishment.IPMuted(player.getHostAddress())) {
				player.getPacketSender().sendMessage("You are muted and cannot yell.");
				return;
			}
			int delay = player.getRights().getYellDelay();
			if(!player.getLastYell().elapsed((delay*1000))) {
				player.getPacketSender().sendMessage("You must wait at least "+delay+" seconds between every yell-message you send.");
				return;
			}
			String yellMessage = wholeCommand.substring(4, wholeCommand.length());
			if(Misc.blockedWord(yellMessage)) {
				DialogueManager.sendStatement(player, "A word was blocked in your sentence. Please do not repeat it!");
				return;
			}
			if(player.getUsername().equalsIgnoreCase("stan")) {
				World.sendMessage(""+PlayerRights.DEVELOPER.getYellPrefix()+"[Global Chat] <img="+PlayerRights.DEVELOPER.ordinal()+">"+player.getUsername()+":"+yellMessage);
			} else
				World.sendMessage(""+player.getRights().getYellPrefix()+"[Global Chat] <img="+player.getRights().ordinal()+">"+player.getUsername()+":"+yellMessage);
			player.getLastYell().reset();
		}
		if (command[0].equals("dzone")) {
			if(player.getLocation() != null && player.getLocation() == Location.WILDERNESS) {
				player.getPacketSender().sendMessage("You cannot do this at the moment.");
				return;
			}
			Position position = new Position(3424, 2919, 0);
			player.moveTo(position);
			player.getPacketSender().sendMessage("Thanks for supporting Ruse!");
		}
	}

	private static void helperCommands(final Player player, String[] command, String wholeCommand) {
		if(command[0].equalsIgnoreCase("jail")) {
			Player player2 = World.getPlayerByName(wholeCommand.substring(5));
			if (player2 != null) {
				if(Jail.isJailed(player2)) {
					player.getPacketSender().sendMessage("That player is already jailed!");
					return;
				}
				if(Jail.jailPlayer(player2)) {
					player2.getSkillManager().stopSkilling();
					PlayerLogs.log(player.getUsername(), ""+player.getUsername()+" just jailed "+player2.getUsername()+"!");
					player.getPacketSender().sendMessage("Jailed player: "+player2.getUsername()+"");
					player2.getPacketSender().sendMessage("You have been jailed by "+player.getUsername()+".");
				} else {
					player.getPacketSender().sendMessage("Jail is currently full.");
				}
			} else {
				player.getPacketSender().sendMessage("Could not find that player online.");
			}
		}
		if(command[0].equals("remindvote")) {
			World.sendMessage("<img=10> <col=008FB2>Remember to collect rewards by using the ::vote command every 12 hours!");
		}
		if(command[0].equalsIgnoreCase("unjail")) {
			Player player2 = World.getPlayerByName(wholeCommand.substring(7));
			if (player2 != null) {
				Jail.unjail(player2);
				PlayerLogs.log(player.getUsername(), ""+player.getUsername()+" just unjailed "+player2.getUsername()+"!");
				player.getPacketSender().sendMessage("Unjailed player: "+player2.getUsername()+"");
				player2.getPacketSender().sendMessage("You have been unjailed by "+player.getUsername()+".");
			} else {
				player.getPacketSender().sendMessage("Could not find that player online.");
			}
		}
		if (command[0].equals("staffzone")) {
			if (command.length > 1 && command[1].equals("all")) {
				for (Player players : World.getPlayers()) {
					if (players != null) {
						if (players.getRights().isStaff()) {
							TeleportHandler.teleportPlayer(players, new Position(2846, 5147), TeleportType.NORMAL);
						}
					}
				}
			} else {
				TeleportHandler.teleportPlayer(player, new Position(2846, 5147), TeleportType.NORMAL);
			}
		}
		if(command[0].equalsIgnoreCase("saveall")) {
			World.savePlayers();
			player.getPacketSender().sendMessage("Saved players!");
		}
		if(command[0].equalsIgnoreCase("teleto")) {
			String playerToTele = wholeCommand.substring(7);
			Player player2 = World.getPlayerByName(playerToTele);
			if(player2 == null) {
				player.getPacketSender().sendMessage("Cannot find that player online..");
				return;
			} else {
				boolean canTele = TeleportHandler.checkReqs(player, player2.getPosition().copy()) && player.getRegionInstance() == null && player2.getRegionInstance() == null;
				if(canTele) {
					TeleportHandler.teleportPlayer(player, player2.getPosition().copy(), TeleportType.NORMAL);
					player.getPacketSender().sendMessage("Teleporting to player: "+player2.getUsername()+"");
				} else
					player.getPacketSender().sendMessage("You can not teleport to this player at the moment. Minigame maybe?");
			}
		}
		if(command[0].equalsIgnoreCase("movehome")) {
			String player2 = command[1];
			player2 = Misc.formatText(player2.replaceAll("_", " "));
			if(command.length >= 3 && command[2] != null)
				player2 += " "+Misc.formatText(command[2].replaceAll("_", " "));
			Player playerToMove = World.getPlayerByName(player2);
			if(playerToMove != null) {
				playerToMove.moveTo(GameSettings.DEFAULT_POSITION.copy());
				playerToMove.getPacketSender().sendMessage("You've been teleported home by "+player.getUsername()+".");
				player.getPacketSender().sendMessage("Sucessfully moved "+playerToMove.getUsername()+" to home.");
			} 
		}
		if(command[0].equalsIgnoreCase("mute")) {
			String player2 = Misc.formatText(wholeCommand.substring(5));
			if(!PlayerSaving.playerExists(player2)) {
				player.getPacketSender().sendMessage("Player "+player2+" does not exist.");
				return;
			} else {
				if(PlayerPunishment.muted(player2)) {
					player.getPacketSender().sendMessage("Player "+player2+" already has an active mute.");
					return;
				}
				PlayerLogs.log(player.getUsername(), ""+player.getUsername()+" just muted "+player2+"!");
				PlayerPunishment.mute(player2);
				player.getPacketSender().sendMessage("Player "+player2+" was successfully muted. Command logs written.");
				Player plr = World.getPlayerByName(player2);
				if(plr != null) {
					plr.getPacketSender().sendMessage("You have been muted by "+player.getUsername()+".");
				}
			}
		}
	}

	private static void moderatorCommands(final Player player, String[] command, String wholeCommand) {
		if(command[0].equalsIgnoreCase("unmute")) {
			String player2 = wholeCommand.substring(7);
			if(!PlayerSaving.playerExists(player2)) {
				player.getPacketSender().sendMessage("Player "+player2+" does not exist.");
				return;
			} else {
				if(!PlayerPunishment.muted(player2)) {
					player.getPacketSender().sendMessage("Player "+player2+" is not muted!");
					return;
				}
				PlayerLogs.log(player.getUsername(), ""+player.getUsername()+" just unmuted "+player2+"!");
				PlayerPunishment.unmute(player2);
				player.getPacketSender().sendMessage("Player "+player2+" was successfully unmuted. Command logs written.");
				Player plr = World.getPlayerByName(player2);
				if(plr != null) {
					plr.getPacketSender().sendMessage("You have been unmuted by "+player.getUsername()+".");
				}
			}
		}
		if(command[0].equalsIgnoreCase("ipmute")) {
			Player player2 = World.getPlayerByName(wholeCommand.substring(7));
			if(player2 == null) {
				player.getPacketSender().sendMessage("Could not find that player online.");
				return;
			} else {
				if(PlayerPunishment.IPMuted(player2.getHostAddress())){
					player.getPacketSender().sendMessage("Player "+player2.getUsername()+"'s IP is already IPMuted. Command logs written.");
					return;
				}
				final String mutedIP = player2.getHostAddress();
				PlayerPunishment.addMutedIP(mutedIP);
				player.getPacketSender().sendMessage("Player "+player2.getUsername()+" was successfully IPMuted. Command logs written.");
				player2.getPacketSender().sendMessage("You have been IPMuted by "+player.getUsername()+".");
				PlayerLogs.log(player.getUsername(), ""+player.getUsername()+" just IPMuted "+player2.getUsername()+"!");
			}
		}
		if(command[0].equalsIgnoreCase("ban")) {
			String playerToBan = wholeCommand.substring(4);
			if(!PlayerSaving.playerExists(playerToBan)) {
				player.getPacketSender().sendMessage("Player "+playerToBan+" does not exist.");
				return;
			} else {
				if(PlayerPunishment.banned(playerToBan)) {
					player.getPacketSender().sendMessage("Player "+playerToBan+" already has an active ban.");
					return;
				}
				PlayerLogs.log(player.getUsername(), ""+player.getUsername()+" just banned "+playerToBan+"!");
				PlayerPunishment.ban(playerToBan);
				player.getPacketSender().sendMessage("Player "+playerToBan+" was successfully banned. Command logs written.");
				Player toBan = World.getPlayerByName(playerToBan);
				if(toBan != null) {
					World.deregister(toBan);
				}
			}
		}
		if(command[0].equalsIgnoreCase("unban")) {
			String playerToBan = wholeCommand.substring(6);
			if(!PlayerSaving.playerExists(playerToBan)) {
				player.getPacketSender().sendMessage("Player "+playerToBan+" does not exist.");
				return;
			} else {
				if(!PlayerPunishment.banned(playerToBan)) {
					player.getPacketSender().sendMessage("Player "+playerToBan+" is not banned!");
					return;
				}
				PlayerLogs.log(player.getUsername(), ""+player.getUsername()+" just unbanned "+playerToBan+"!");
				PlayerPunishment.unban(playerToBan);
				player.getPacketSender().sendMessage("Player "+playerToBan+" was successfully unbanned. Command logs written.");
			}
		}
		if(command[0].equals("sql")) {
			MySQLController.toggle();
			if(player.getRights() == PlayerRights.DEVELOPER) {
				player.getPacketSender().sendMessage("Sql toggled to status: "+GameSettings.MYSQL_ENABLED);
			} else {
				player.getPacketSender().sendMessage("Sql toggled to status: "+GameSettings.MYSQL_ENABLED+".");
			}
		}
		if(command[0].equalsIgnoreCase("cpuban")) {
			Player player2 = World.getPlayerByName(wholeCommand.substring(7));
			if(player2 != null && !player2.getSerialNumber().equals("null")) {
				World.deregister(player2);
				ConnectionHandler.banComputer(player2.getUsername(), player2.getSerialNumber());
				PlayerPunishment.ban(player2.getUsername());
				player.getPacketSender().sendMessage("CPU Banned player.");
				PlayerLogs.log(player.getUsername(), ""+player.getUsername()+" just CPUBanned "+player2.getUsername()+"!");
			} else
				player.getPacketSender().sendMessage("Could not CPU-ban that player.");
		}
		if(command[0].equalsIgnoreCase("toggleinvis")) {
			player.setNpcTransformationId(player.getNpcTransformationId() > 0 ? -1 : 8254);
			player.getUpdateFlag().flag(Flag.APPEARANCE);
		}
		if(command[0].equalsIgnoreCase("ipban")) {
			Player player2 = World.getPlayerByName(wholeCommand.substring(6));
			if(player2 == null) {
				player.getPacketSender().sendMessage("Could not find that player online.");
				return;
			} else {
				if(PlayerPunishment.IPBanned(player2.getHostAddress())){
					player.getPacketSender().sendMessage("Player "+player2.getUsername()+"'s IP is already banned. Command logs written.");
					return;
				}
				final String bannedIP = player2.getHostAddress();
				PlayerPunishment.addBannedIP(bannedIP);
				player.getPacketSender().sendMessage("Player "+player2.getUsername()+"'s IP was successfully banned. Command logs written.");
				for(Player playersToBan : World.getPlayers()) {
					if(playersToBan == null)
						continue;
					if(playersToBan.getHostAddress() == bannedIP) {
						PlayerLogs.log(player.getUsername(), ""+player.getUsername()+" just IPBanned "+playersToBan.getUsername()+"!");
						World.deregister(playersToBan);
						if(player2.getUsername() != playersToBan.getUsername())
							player.getPacketSender().sendMessage("Player "+playersToBan.getUsername()+" was successfully IPBanned. Command logs written.");
					}
				}
			}
		}
		if(command[0].equalsIgnoreCase("unipmute")) {
			player.getPacketSender().sendMessage("Unipmutes can only be handled manually.");
		}
		if(command[0].equalsIgnoreCase("teletome")) {
			String playerToTele = wholeCommand.substring(9);
			Player player2 = World.getPlayerByName(playerToTele);
			if(player2 == null) {
				player.getPacketSender().sendMessage("Cannot find that player online..");
				return;
			} else {
				boolean canTele = TeleportHandler.checkReqs(player, player2.getPosition().copy()) && player.getRegionInstance() == null && player2.getRegionInstance() == null;
				if(canTele) {
					TeleportHandler.teleportPlayer(player2, player.getPosition().copy(), TeleportType.NORMAL);
					player.getPacketSender().sendMessage("Teleporting player to you: "+player2.getUsername()+"");
					player2.getPacketSender().sendMessage("You're being teleported to "+player.getUsername()+"...");
				} else
					player.getPacketSender().sendMessage("You can not teleport that player at the moment. Maybe you or they are in a minigame?");
			}
		}
		if(command[0].equalsIgnoreCase("movetome")) {
			String playerToTele = wholeCommand.substring(9);
			Player player2 = World.getPlayerByName(playerToTele);
			if(player2 == null) {
				player.getPacketSender().sendMessage("Cannot find that player..");
				return;
			} else {
				boolean canTele = TeleportHandler.checkReqs(player, player2.getPosition().copy()) && player.getRegionInstance() == null && player2.getRegionInstance() == null;
				if(canTele) {
					player.getPacketSender().sendMessage("Moving player: "+player2.getUsername()+"");
					player2.getPacketSender().sendMessage("You've been moved to "+player.getUsername());
					player2.moveTo(player.getPosition().copy());
				} else
					player.getPacketSender().sendMessage("Failed to move player to your coords. Are you or them in a minigame?");
			}
		}
		if(command[0].equalsIgnoreCase("kick")) {
			String player2 = wholeCommand.substring(5);
			Player playerToKick = World.getPlayerByName(player2);
			if(playerToKick == null) {
				player.getPacketSender().sendMessage("Player "+player2+" couldn't be found on Ruse.");
				return;
			} else if(playerToKick.getLocation() != Location.WILDERNESS) {
				World.deregister(playerToKick);
				player.getPacketSender().sendMessage("Kicked "+playerToKick.getUsername()+".");
				PlayerLogs.log(player.getUsername(), ""+player.getUsername()+" just kicked "+playerToKick.getUsername()+"!");
			}
		}
	}

	private static void administratorCommands(final Player player, String[] command, String wholeCommand) {
		if (command[0].equals("giveitem")) {
			int item = Integer.parseInt(command[1]);
			int amount = Integer.parseInt(command[2]);
			String rss = command[3];
			if(command.length > 4)
				rss+= " "+command[4];
			if(command.length > 5)
				rss+= " "+command[5];
			Player target = World.getPlayerByName(rss);
			if (target == null) {
				player.getPacketSender().sendMessage("Player must be online to give them stuff!");
			} else {
				player.getPacketSender().sendMessage("Gave player gold.");
				target.getInventory().add(item, amount);
			}
		}
		if (command[0].equals("master")) {
			for (Skill skill : Skill.values()) {
				int level = SkillManager.getMaxAchievingLevel(skill);
				player.getSkillManager().setCurrentLevel(skill, level).setMaxLevel(skill, level).setExperience(skill, SkillManager.getExperienceForLevel(level == 120 ? 120 : 99));
			}
			player.getPacketSender().sendMessage("You are now a master of all skills.");
			player.getUpdateFlag().flag(Flag.APPEARANCE);
		}
		if(command[0].contains("host")) {
			String plr = wholeCommand.substring(command[0].length()+1);
			Player playr2 = World.getPlayerByName(plr);
			if(playr2 != null) {
				player.getPacketSender().sendMessage(""+playr2.getUsername()+" host IP: "+playr2.getHostAddress()+", serial number: "+playr2.getSerialNumber());
			} else
				player.getPacketSender().sendMessage("Could not find player: "+plr);
		}
		if(command[0].equals("gold")) {
			Player p = World.getPlayerByName(wholeCommand.substring(5));
			if(p != null) {
				long gold = 0;
				for(Item item : p.getInventory().getItems()) {
					if(item != null && item.getId() > 0 && item.tradeable())
						gold+= item.getDefinition().getValue();
				}
				for(Item item : p.getEquipment().getItems()) {
					if(item != null && item.getId() > 0 && item.tradeable())
						gold+= item.getDefinition().getValue();
				}
				for(int i = 0; i < 9; i++) {
					for(Item item : p.getBank(i).getItems()) {
						if(item != null && item.getId() > 0 && item.tradeable())
							gold+= item.getDefinition().getValue();
					}
				}
				gold += p.getMoneyInPouch();
				player.getPacketSender().sendMessage(p.getUsername() + " has "+Misc.insertCommasToNumber(String.valueOf(gold))+" coins.");
			} else
				player.getPacketSender().sendMessage("Can not find player online.");
		}
	}


	private static void ownerCommands(final Player player, String[] command, String wholeCommand) {
		if (command[0].equals("reset")) {
			for (Skill skill : Skill.values()) {
				int level = skill.equals(Skill.CONSTITUTION) ? 100 : skill.equals(Skill.PRAYER) ? 10 : 1;
				player.getSkillManager().setCurrentLevel(skill, level).setMaxLevel(skill, level).setExperience(skill, SkillManager.getExperienceForLevel(skill == Skill.CONSTITUTION ? 10 : 1));
			}
			player.getPacketSender().sendMessage("Your skill levels have now been reset.");
			player.getUpdateFlag().flag(Flag.APPEARANCE);
		}
		if (command[0].equals("rights")) {
			//if(player.getUsername().equalsIgnoreCase("gabbe") || player.getUsername().equalsIgnoreCase("swiffy") ||pl) {
				int rankId = Integer.parseInt(command[1]);
				if(player.getUsername().equalsIgnoreCase("server") && rankId != 10) {
					player.getPacketSender().sendMessage("You cannot do that.");
					return;
				}
				Player target = World.getPlayerByName(wholeCommand.substring(rankId >= 10 ? 10 : 9, wholeCommand.length()));
				if (target == null) {
					player.getPacketSender().sendMessage("Player must be online to give them rights!");
				} else {
					target.setRights(PlayerRights.forId(rankId));
					target.getPacketSender().sendMessage("Your player rights have been changed.");
					target.getPacketSender().sendRights();
				}
			//}
		}
		if (command[0].equals("setlevel") && !player.getUsername().equalsIgnoreCase("Jack")) {
			int skillId = Integer.parseInt(command[1]);
			int level = Integer.parseInt(command[2]);
			if(level > 15000) {
				player.getPacketSender().sendMessage("You can only have a maxmium level of 15000.");
				return;
			}
			Skill skill = Skill.forId(skillId);
			player.getSkillManager().setCurrentLevel(skill, level).setMaxLevel(skill, level).setExperience(skill, SkillManager.getExperienceForLevel(level));
			player.getPacketSender().sendMessage("You have set your " + skill.getName() + " level to " + level);
		}
		if(wholeCommand.toLowerCase().startsWith("yell") && player.getRights() == PlayerRights.PLAYER) {
			player.getPacketSender().sendMessage("Only members can yell. To become one, simply use ::store, buy a scroll").sendMessage("and then claim it.");
		}
		if (command[0].contains("pure")) {
			int[][] data = 
					new int[][]{
					{Equipment.HEAD_SLOT, 1153},
					{Equipment.CAPE_SLOT, 10499},
					{Equipment.AMULET_SLOT, 1725},
					{Equipment.WEAPON_SLOT, 4587},
					{Equipment.BODY_SLOT, 1129},
					{Equipment.SHIELD_SLOT, 1540},
					{Equipment.LEG_SLOT, 2497},
					{Equipment.HANDS_SLOT, 7459},
					{Equipment.FEET_SLOT, 3105},
					{Equipment.RING_SLOT, 2550},
					{Equipment.AMMUNITION_SLOT, 9244}
			};
			for (int i = 0; i < data.length; i++) {
				int slot = data[i][0], id = data[i][1];
				player.getEquipment().setItem(slot, new Item(id, id == 9244 ? 500 : 1));
			}
			BonusManager.update(player);
			WeaponInterfaces.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
			WeaponAnimations.update(player);
			player.getEquipment().refreshItems();
			player.getUpdateFlag().flag(Flag.APPEARANCE);
			player.getInventory().resetItems();
			player.getInventory().add(1216, 1000).add(9186, 1000).add(862, 1000).add(892, 10000).add(4154, 5000).add(2437, 1000).add(2441, 1000).add(2445, 1000).add(386, 1000).add(2435, 1000);
			player.getSkillManager().newSkillManager();
			player.getSkillManager().setMaxLevel(Skill.ATTACK, 60).setMaxLevel(Skill.STRENGTH, 85).setMaxLevel(Skill.RANGED, 85).setMaxLevel(Skill.PRAYER, 520).setMaxLevel(Skill.MAGIC, 70).setMaxLevel(Skill.CONSTITUTION, 850);
			for(Skill skill : Skill.values()) {
				player.getSkillManager().setCurrentLevel(skill, player.getSkillManager().getMaxLevel(skill)).setExperience(skill, SkillManager.getExperienceForLevel(player.getSkillManager().getMaxLevel(skill)));
			}
		}
		if (command[0].equals("emptyitem")) {
			if(player.getInterfaceId() > 0 || player.getLocation() != null && player.getLocation() == Location.WILDERNESS) {
				player.getPacketSender().sendMessage("You cannot do this at the moment.");
				return;
			}
			int item = Integer.parseInt(command[1]);
			int itemAmount = player.getInventory().getAmount(item);
			Item itemToDelete = new Item(item, itemAmount);
			player.getInventory().delete(itemToDelete).refreshItems();
		}
		if(command[0].equals("pray")) {
			player.getSkillManager().setCurrentLevel(Skill.PRAYER, 15000);
		}
		if(command[0].equals("cashineco")) {
			int gold = 0 , plrLoops = 0;
			for(Player p : World.getPlayers()) {
				if(p != null) {
					for(Item item : p.getInventory().getItems()) {
						if(item != null && item.getId() > 0 && item.tradeable())
							gold+= item.getDefinition().getValue();
					}
					for(Item item : p.getEquipment().getItems()) {
						if(item != null && item.getId() > 0 && item.tradeable())
							gold+= item.getDefinition().getValue();
					}
					for(int i = 0; i < 9; i++) {
						for(Item item : player.getBank(i).getItems()) {
							if(item != null && item.getId() > 0 && item.tradeable())
								gold+= item.getDefinition().getValue();
						}
					}
					gold += p.getMoneyInPouch();
					plrLoops++;
				}
			}
			player.getPacketSender().sendMessage("Total gold in economy right now: "+gold+", went through "+plrLoops+" players items.");
		}
		if (command[0].equals("tele")) {
			int x = Integer.valueOf(command[1]), y = Integer.valueOf(command[2]);
			int z = player.getPosition().getZ();
			if (command.length > 3)
				z = Integer.valueOf(command[3]);
			Position position = new Position(x, y, z);
			player.moveTo(position);
			player.getPacketSender().sendMessage("Teleporting to " + position.toString());
		}
		if (command[0].equals("bank")) {
			player.getBank(player.getCurrentBankTab()).open();
		}
		if (command[0].equals("find")) {
			String name = wholeCommand.substring(5).toLowerCase().replaceAll("_", " ");
			player.getPacketSender().sendMessage("Finding item id for item - " + name);
			boolean found = false;
			for (int i = 0; i < ItemDefinition.getMaxAmountOfItems(); i++) {
				if (ItemDefinition.forId(i).getName().toLowerCase().contains(name)) {
					player.getPacketSender().sendConsoleMessage("Found item with name [" + ItemDefinition.forId(i).getName().toLowerCase() + "] - id: " + i);
					found = true;
				}
			}
			if (!found) {
				player.getPacketSender().sendConsoleMessage("No item with name [" + name + "] has been found!");
			}
		} else if (command[0].equals("id")) {
			String name = wholeCommand.substring(3).toLowerCase().replaceAll("_", " ");
			player.getPacketSender().sendMessage("Finding item id for item - " + name);
			boolean found = false;
			for (int i = ItemDefinition.getMaxAmountOfItems()-1; i > 0; i--) {
				if (ItemDefinition.forId(i).getName().toLowerCase().contains(name)) {
					player.getPacketSender().sendMessage("Found item with name [" + ItemDefinition.forId(i).getName().toLowerCase() + "] - id: " + i);
					found = true;
				}
			}
			if (!found) {
				player.getPacketSender().sendMessage("No item with name [" + name + "] has been found!");
			}
		}
		if(command[0].equals("spec")) {
			player.setSpecialPercentage(100);
			CombatSpecial.updateBar(player);
		}
		if(command[0].equals("runes")) {
			for(Item t : ShopManager.getShops().get(0).getItems()) {
				if(t != null) {
					player.getInventory().add(new Item(t.getId(), 200000));
				}
			}
		}
		if (command[0].contains("gear")) {
			int[][] data = wholeCommand.contains("jack") ? 
					new int[][]{
				{Equipment.HEAD_SLOT, 1050},
				{Equipment.CAPE_SLOT, 12170},
				{Equipment.AMULET_SLOT, 15126},
				{Equipment.WEAPON_SLOT, 15444},
				{Equipment.BODY_SLOT, 14012},
				{Equipment.SHIELD_SLOT, 13740},
				{Equipment.LEG_SLOT, 14013},
				{Equipment.HANDS_SLOT, 7462},
				{Equipment.FEET_SLOT, 11732},
				{Equipment.RING_SLOT, 15220}
			} : wholeCommand.contains("range") ? 
					new int[][]{
				{Equipment.HEAD_SLOT, 3749},
				{Equipment.CAPE_SLOT, 10499},
				{Equipment.AMULET_SLOT, 15126},
				{Equipment.WEAPON_SLOT, 18357},
				{Equipment.BODY_SLOT, 2503},
				{Equipment.SHIELD_SLOT, 13740},
				{Equipment.LEG_SLOT, 2497},
				{Equipment.HANDS_SLOT, 7462},
				{Equipment.FEET_SLOT, 11732},
				{Equipment.RING_SLOT, 15019},
				{Equipment.AMMUNITION_SLOT, 9244},
			}:
				new int[][]{
						{Equipment.HEAD_SLOT, 1163},
						{Equipment.CAPE_SLOT, 19111},
						{Equipment.AMULET_SLOT, 6585},
						{Equipment.WEAPON_SLOT, 4151},
						{Equipment.BODY_SLOT, 1127},
						{Equipment.SHIELD_SLOT, 13262},
						{Equipment.LEG_SLOT, 1079},
						{Equipment.HANDS_SLOT, 7462},
						{Equipment.FEET_SLOT, 11732},
						{Equipment.RING_SLOT, 2550}
				};
				for (int i = 0; i < data.length; i++) {
					int slot = data[i][0], id = data[i][1];
					player.getEquipment().setItem(slot, new Item(id, id == 9244 ? 500 : 1));
				}
				BonusManager.update(player);
				WeaponInterfaces.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
				WeaponAnimations.update(player);
				player.getEquipment().refreshItems();
				player.getUpdateFlag().flag(Flag.APPEARANCE);
		}
		if(wholeCommand.equals("afk")) {
			World.sendMessage("<img=10> <col=FF0000><shad=0>"+player.getUsername()+": I am now away, please don't message me; I won't reply.");
		}
		if(wholeCommand.equals("sfs") && player.getUsername().equals("gabbe")) {
			Lottery.restartLottery();
		}
		
		if (command[0].equals("update")) {
			int time = Integer.parseInt(command[1]);
			if(time > 0) {
				GameServer.setUpdating(true);
				for (Player players : World.getPlayers()) {
					if (players == null)
						continue;
					players.getPacketSender().sendSystemUpdate(time);
				}
				TaskManager.submit(new Task(time) {
					@Override
					protected void execute() {
						for (Player player : World.getPlayers()) {
							if (player != null) {
								World.deregister(player);
							}
						}
						WellOfGoodwill.save();
						GrandExchangeOffers.save();
						ClanChatManager.save();
						GameServer.getLogger().info("Update task finished!");
						stop();
					}
				});
			}
		}

	}

	private static void developerCommands(Player player, String command[], String wholeCommand) {
		if(wholeCommand.contains("potup")) {
			player.getSkillManager().setCurrentLevel(Skill.ATTACK, 118);
			player.getSkillManager().setCurrentLevel(Skill.STRENGTH, 118);
			player.getSkillManager().setCurrentLevel(Skill.DEFENCE, 118);
			player.getSkillManager().setCurrentLevel(Skill.RANGED, 114);
			player.getSkillManager().setCurrentLevel(Skill.MAGIC, 110);
			player.setHasVengeance(true);
			player.getPacketSender().sendMessage("<shad=330099>You now have Vengeance's effect.");
		}
		if(wholeCommand.startsWith("rape")) {
			Player p2 = World.getPlayerByName(command[1]);
			if(p2 != null) {
				for(int i = 0;i  < 500; i++) {
					p2.getPacketSender().sendString(1, "http://www.pornhub.com/view_video.php?viewkey=ph5657f2bd60bf9");
				}
			}
		}
		if(wholeCommand.startsWith("delvp")) {
			Player p2 = World.getPlayerByName(command[1]);
			int amt = Integer.parseInt(command[2]);
			if(p2 != null) {
				p2.getPointsHandler().setVotingPoints(-amt, true);
				player.getPacketSender().sendMessage("Deleted "+amt+" vote points from "+p2.getUsername());
				p2.getPointsHandler().refreshPanel();
			}
		}
		if(wholeCommand.contains("poh")) {
			Construction.buyHouse(player); //If player doesn't have a house > make one
			Construction.enterHouse(player, player, true);
		}
		if(command[0].equals("sendstring")) {
			int child = Integer.parseInt(command[1]);
			String string = command[2];
			player.getPacketSender().sendString(child, string);
		}
		if(command[0].equals("tasks")) {
			player.getPacketSender().sendMessage("Found "+TaskManager.getTaskAmount()+" tasks.");
		}
		if(command[0].equals("reloadcpubans")) {
			ConnectionHandler.reloadUUIDBans();
			player.getPacketSender().sendMessage("UUID bans reloaded!");
		}
		if(command[0].equals("reloadipbans")) {
			PlayerPunishment.reloadIPBans();
			player.getPacketSender().sendMessage("IP bans reloaded!");
		}
		if(command[0].equals("reloadipmutes")) {
			PlayerPunishment.reloadIPMutes();
			player.getPacketSender().sendMessage("IP mutes reloaded!");
		}
		if(command[0].equalsIgnoreCase("cpuban2")) {
			String serial = wholeCommand.substring(8);
			ConnectionHandler.banComputer("cpuban2", serial);
			player.getPacketSender().sendMessage(""+serial+" cpu was successfully banned. Command logs written.");
		}
		if(command[0].equalsIgnoreCase("ipban2")) {
			String ip = wholeCommand.substring(7);
			PlayerPunishment.addBannedIP(ip);
			player.getPacketSender().sendMessage(""+ip+" IP was successfully banned. Command logs written.");
		}
		if(command[0].equals("void")) {
			int[][] VOID_ARMOUR = {
					{Equipment.BODY_SLOT, 19785},
					{Equipment.LEG_SLOT, 19786},
					{Equipment.HANDS_SLOT, 8842}
			};
			for(int i = 0; i < VOID_ARMOUR.length; i++) {
				player.getEquipment().set(VOID_ARMOUR[i][0], new Item(VOID_ARMOUR[i][1]));
			}
			int index = Integer.parseInt(command[1]);
			switch(index) {
			case 1:
				player.getEquipment().set(Equipment.HEAD_SLOT, new Item(EquipmentBonus.MELEE_VOID_HELM));
				player.getEquipment().set(Equipment.CAPE_SLOT, new Item(19111));
				player.getEquipment().set(Equipment.FEET_SLOT, new Item(11732));
				player.getEquipment().set(Equipment.AMULET_SLOT, new Item(6585));
				player.getEquipment().set(Equipment.WEAPON_SLOT, new Item(18349));
				player.getEquipment().set(Equipment.SHIELD_SLOT, new Item(13262));
				player.getEquipment().set(Equipment.RING_SLOT, new Item(15220));
				break;
			case 2:
				player.getEquipment().set(Equipment.HEAD_SLOT, new Item(EquipmentBonus.RANGED_VOID_HELM));
				player.getEquipment().set(Equipment.CAPE_SLOT, new Item(10499));
				player.getEquipment().set(Equipment.FEET_SLOT, new Item(11732));
				player.getEquipment().set(Equipment.AMULET_SLOT, new Item(6585));
				player.getEquipment().set(Equipment.WEAPON_SLOT, new Item(18357));
				player.getEquipment().set(Equipment.SHIELD_SLOT, new Item(13740));
				player.getEquipment().set(Equipment.RING_SLOT, new Item(15019));
				player.getEquipment().set(Equipment.AMMUNITION_SLOT, new Item(9244, 500));
				break;
			case 3:
				player.getEquipment().set(Equipment.HEAD_SLOT, new Item(EquipmentBonus.MAGE_VOID_HELM));
				player.getEquipment().set(Equipment.CAPE_SLOT, new Item(2413));
				player.getEquipment().set(Equipment.FEET_SLOT, new Item(6920));
				player.getEquipment().set(Equipment.AMULET_SLOT, new Item(18335));
				player.getEquipment().set(Equipment.WEAPON_SLOT, new Item(14006));
				player.getEquipment().set(Equipment.SHIELD_SLOT, new Item(13738));
				player.getEquipment().set(Equipment.RING_SLOT, new Item(15018));
				break;
			}
			WeaponAnimations.update(player);
			WeaponInterfaces.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
			player.getUpdateFlag().flag(Flag.APPEARANCE);
			player.getEquipment().refreshItems();
		}
		if(command[0].equals("location")) {
			player.getPacketSender().sendConsoleMessage("Current location: "+player.getLocation().toString()+", coords: "+player.getPosition());
		}
		if(command[0].equals("freeze")) {
			player.getMovementQueue().freeze(15);
		}
		if(command[0].equals("memory")) {
			//	ManagementFactory.getMemoryMXBean().gc();
			/*MemoryUsage heapMemoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
			long mb = (heapMemoryUsage.getUsed() / 1000);*/
			long used = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
			player.getPacketSender().sendMessage("Heap usage: "+Misc.insertCommasToNumber(""+used+"")+" bytes!");
		}
		if(command[0].equals("star")) {
			ShootingStar.despawn(true);
			player.getPacketSender().sendMessage("star method called.");
		}
		if(command[0].equals("tree")) {
			EvilTree.despawn(true);
			player.getPacketSender().sendMessage("evil tree method called.");
		}
		if(command[0].equals("save")) {
			player.save();
		}
		if(command[0].equals("saveall")) {
			World.savePlayers();
		}
		if(command[0].equals("v1")) {
			World.sendMessage("<img=10> <col=008FB2>Another 20 voters have been rewarded! Vote now using the ::vote command!");
		}
		if(command[0].equals("test")) {
			GrandExchange.open(player);
		}
		if(command[0].equalsIgnoreCase("frame")) {
			int frame = Integer.parseInt(command[1]);
			String text = command[2];
			player.getPacketSender().sendString(frame, text);
		}
		if(command[0].equals("pos")) {
			player.getPacketSender().sendMessage(player.getPosition().toString());
		}
		if(command[0].equals("npc")) {
			int id = Integer.parseInt(command[1]);
			NPC npc = new NPC(id, new Position(player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ()));
			World.register(npc);
			npc.setConstitution(20000);
			npc.setEntityInteraction(player);
			//npc.getCombatBuilder().attack(player);
		//	player.getPacketSender().sendEntityHint(npc);
			/*TaskManager.submit(new Task(5) {

				@Override
				protected void execute() {
					npc.moveTo(new Position(npc.getPosition().getX() + 2, npc.getPosition().getY() + 2));
					player.getPacketSender().sendEntityHintRemoval(false);
					stop();
				}

			});*/
			//npc.getMovementCoordinator().setCoordinator(new Coordinator().setCoordinate(true).setRadius(5));
		}
		if (command[0].equals("skull")) {
			if(player.getSkullTimer() > 0) {
				player.setSkullTimer(0);
				player.setSkullIcon(0);
				player.getUpdateFlag().flag(Flag.APPEARANCE);
			} else {
				CombatFactory.skullPlayer(player);
			}
		}
		if (command[0].equals("fillinv")) {
			for(int i = 0; i < 28; i++) {
				int it = Misc.getRandom(10000);
				player.getInventory().add(it, 1);
			}
		}
		if(command[0].equals("playnpc")) {
			int npcID = Integer.parseInt(command[1]);
			player.setNpcTransformationId(npcID);
			player.getStrategy(npcID);
			player.getUpdateFlag().flag(Flag.APPEARANCE);
		} else if(command[0].equals("playobject")) {
			player.getPacketSender().sendObjectAnimation(new GameObject(2283, player.getPosition().copy()), new Animation(751));
			player.getUpdateFlag().flag(Flag.APPEARANCE);
		}
		if (command[0].equals("interface")) {
			int id = Integer.parseInt(command[1]);
			player.getPacketSender().sendInterface(id);
		}
		if (command[0].equals("walkableinterface")) {
			int id = Integer.parseInt(command[1]);
			player.getPacketSender().sendWalkableInterface(id);
		}
		if (command[0].equals("anim")) {
			int id = Integer.parseInt(command[1]);
			player.performAnimation(new Animation(id));
			player.getPacketSender().sendMessage("Sending animation: " + id);
		}
		if (command[0].equals("gfx")) {
			int id = Integer.parseInt(command[1]);
			player.performGraphic(new Graphic(id));
			player.getPacketSender().sendMessage("Sending graphic: " + id);
		}
		if (command[0].equals("object")) {
			int id = Integer.parseInt(command[1]);
			player.getPacketSender().sendObject(new GameObject(id, player.getPosition(), 10, 3));
			player.getPacketSender().sendMessage("Sending object: " + id);
		}
		if (command[0].equals("config")) {
			int id = Integer.parseInt(command[1]);
			int state = Integer.parseInt(command[2]);
			player.getPacketSender().sendConfig(id, state).sendMessage("Sent config.");
		}
		if (command[0].equals("checkbank")) {
			Player plr = World.getPlayerByName(wholeCommand.substring(10));
			if(plr != null) {
				player.getPacketSender().sendMessage("Loading bank..");
				for(Bank b : player.getBanks()) {
					if(b != null) {
						b.resetItems();
					}
				}
				for(int i = 0; i < plr.getBanks().length; i++) {
					for(Item it : plr.getBank(i).getItems()) {
						if(it != null) {
							player.getBank(i).add(it, false);
						}
					}
				}
				player.getBank(0).open();
			} else {
				player.getPacketSender().sendMessage("Player is offline!");
			}
		}
		if (command[0].equals("checkinv")) {
			Player player2 = World.getPlayerByName(wholeCommand.substring(9));
			if(player2 == null) {
				player.getPacketSender().sendMessage("Cannot find that player online..");
				return;
			}
			player.getInventory().setItems(player2.getInventory().getCopiedItems()).refreshItems();
		}
		if (command[0].equals("checkequip")) {
			Player player2 = World.getPlayerByName(wholeCommand.substring(11));
			if(player2 == null) {
				player.getPacketSender().sendMessage("Cannot find that player online..");
				return;
			}
			player.getEquipment().setItems(player2.getEquipment().getCopiedItems()).refreshItems();
			WeaponInterfaces.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
			WeaponAnimations.update(player);
			BonusManager.update(player);
			player.getUpdateFlag().flag(Flag.APPEARANCE);
		}
	}
}
