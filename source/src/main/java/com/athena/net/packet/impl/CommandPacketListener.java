package com.athena.net.packet.impl;

import com.athena.GameServer;
import com.athena.GameSettings;
import com.athena.engine.task.Task;
import com.athena.engine.task.TaskManager;
import com.athena.engine.task.impl.PlayerDeathTask;
import com.athena.model.Animation;
import com.athena.model.Flag;
import com.athena.model.GameObject;
import com.athena.model.Graphic;
import com.athena.model.Item;
import com.athena.model.PlayerRights;
import com.athena.model.Position;
import com.athena.model.Skill;
import com.athena.model.Locations.Location;
import com.athena.model.container.impl.Bank;
import com.athena.model.container.impl.Equipment;
import com.athena.model.container.impl.Shop.ShopManager;
import com.athena.model.definitions.ItemDefinition;
import com.athena.model.definitions.NPCDrops;
import com.athena.model.definitions.NpcDefinition;
import com.athena.model.definitions.WeaponAnimations;
import com.athena.model.definitions.WeaponInterfaces;
import com.athena.net.packet.Packet;
import com.athena.net.packet.PacketListener;
import com.athena.net.security.ConnectionHandler;
import com.athena.util.CommandTypes;
import com.athena.util.Misc;
import com.athena.util.NameUtils;
import com.athena.util.RandomUtility;
import com.athena.util.StaffCommand;
import com.athena.util.banning.BanHammer;
import com.athena.util.banning.BanType;
import com.athena.world.World;
import com.athena.world.content.*;
import com.athena.world.content.PlayerPunishment.Jail;
import com.athena.world.content.TeleportInterface.Bosses;
import com.athena.world.content.clan.ClanChatManager;
import com.athena.world.content.combat.CombatFactory;
import com.athena.world.content.combat.DesolaceFormulas;
import com.athena.world.content.combat.strategy.CombatStrategies;
import com.athena.world.content.combat.weapon.CombatSpecial;
import com.athena.world.content.global.GlobalBossHandler;
import com.athena.world.content.grandexchange.GrandExchange;
import com.athena.world.content.grandexchange.GrandExchangeOffers;
import com.athena.world.content.minigames.impl.FreeForAll;
import com.athena.world.content.skill.SkillManager;
import com.athena.world.content.skill.impl.herblore.Decanting;
import com.athena.world.content.transportation.TeleportHandler;
import com.athena.world.content.transportation.TeleportType;
import com.athena.world.entity.impl.npc.NPC;
import com.athena.world.entity.impl.npc.NPCMovementCoordinator.Coordinator;
import com.athena.world.entity.impl.player.Player;
import com.athena.world.entity.impl.player.PlayerHandler;
import com.athena.world.entity.impl.player.PlayerLoading;
import com.athena.world.entity.impl.player.PlayerSaving;
import com.athena.world.content.StarterTasks;
import mysql.impl.Donation;
import mysql.impl.Vote;

import mysql.MySQLController;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * This packet listener manages commands a player uses by using the command
 * console prompted by using the "`" char.
 *
 * @author Gabriel Hannason
 */

public class CommandPacketListener implements PacketListener {

	public static int config;
	private static final File CONTESTERS_FILE_LOCATION = new File("./data/saves/voting/votes.txt");
	public static final List<String> CONTESTERS = new ArrayList<String>();

	@Override
	public void handleMessage(Player player, Packet packet) {
		String command = Misc.readString(packet.getBuffer());
		String[] parts = command.toLowerCase().split(" ");
		if (command.contains("\r") || command.contains("\n")) {
			return;
		}
		try {
			switch (player.getRights()) {
			case PLAYER:
				playerCommands(player, parts, command);
				break;
			case FORUM_DEVELOPER:
				playerCommands(player, parts, command);
				superDonator(player, parts, command);
				extremeDonator(player, parts, command);
				legendaryDonator(player, parts, command);
				uberDonator(player, parts, command);
				memberCommands(player, parts, command);
				//helperCommands(player, parts, command);
				break;
			case MODERATOR:
				playerCommands(player, parts, command);
				superDonator(player, parts, command);
				extremeDonator(player, parts, command);
				legendaryDonator(player, parts, command);
				uberDonator(player, parts, command);
				memberCommands(player, parts, command);
				helperCommands(player, parts, command);
				moderatorCommands(player, parts, command);
				break;
			case ADMINISTRATOR:
				playerCommands(player, parts, command);
				superDonator(player, parts, command);
				extremeDonator(player, parts, command);
				legendaryDonator(player, parts, command);
				uberDonator(player, parts, command);
				memberCommands(player, parts, command);
				helperCommands(player, parts, command);
				moderatorCommands(player, parts, command);
				DeluDonator(player, parts, command);
				//administratorCommands(player, parts, command);
				break;
			case OWNER:
				playerCommands(player, parts, command);
				superDonator(player, parts, command);
				extremeDonator(player, parts, command);
				legendaryDonator(player, parts, command);
				uberDonator(player, parts, command);
				memberCommands(player, parts, command);
				helperCommands(player, parts, command);
				moderatorCommands(player, parts, command);
				administratorCommands(player, parts, command);
				ownerCommands(player, parts, command);
				SECURITY_MANAGERCommands(player, parts, command);
				dedicatedDonator(player, parts, command);
				DeluDonator(player, parts, command);
				break;
			case DELUXE_DONATOR:// so wait, let me do it im pretty fast, correct me
				// if im wrong
				playerCommands(player, parts, command);
				superDonator(player, parts, command);
				extremeDonator(player, parts, command);
				legendaryDonator(player, parts, command);
				uberDonator(player, parts, command);
				memberCommands(player, parts, command);
				//helperCommands(player, parts, command);
				DeluDonator(player, parts, command);
				break;
			case SUPPORT:
				playerCommands(player, parts, command);
				superDonator(player, parts, command);
				extremeDonator(player, parts, command);
				legendaryDonator(player, parts, command);
				uberDonator(player, parts, command);
				memberCommands(player, parts, command);
				helperCommands(player, parts, command);
				break;
			case YOUTUBER:
				playerCommands(player, parts, command);
				memberCommands(player, parts, command);
				superDonator(player, parts, command);
				extremeDonator(player, parts, command);
				legendaryDonator(player, parts, command);
				youtuberCommands(player, parts, command);
				break;
			case DONATOR:
				playerCommands(player, parts, command);
				memberCommands(player, parts, command);
				break;
			case SUPER_DONATOR:
				playerCommands(player, parts, command);
				memberCommands(player, parts, command);
				superDonator(player, parts, command);
				break;
			case EXTREME_DONATOR:
				playerCommands(player, parts, command);
				memberCommands(player, parts, command);
				extremeDonator(player, parts, command);
				break;
			case LEGENDARY_DONATOR:
				playerCommands(player, parts, command);
				memberCommands(player, parts, command);
				legendaryDonator(player, parts, command);
				extremeDonator(player, parts, command);
				break;
			case UBER_DONATOR:
				playerCommands(player, parts, command);
				memberCommands(player, parts, command);
				uberDonator(player, parts, command);
				legendaryDonator(player, parts, command);
				extremeDonator(player, parts, command);
				break;
			case DEDICATED_DONATOR:
				playerCommands(player, parts, command);
				memberCommands(player, parts, command);
				dedicatedDonator(player, parts, command);
				uberDonator(player, parts, command);
				legendaryDonator(player, parts, command);
				extremeDonator(player, parts, command);
				break;

			default:
				break;
			}
		} catch (Exception exception) {
			exception.printStackTrace();

			if (player.getRights() == PlayerRights.OWNER) {
				player.getPacketSender().sendConsoleMessage("Error executing that command.");
			} else {
				player.getPacketSender().sendMessage("Error executing that command.");
			}

		}
	}

	private static void dedicatedDonator(final Player player, String[] command, String wholeCommand) {

		switch (command[0]) {

		case "dedizone":
			if (player.getRights().isStaff() || player.getRights() == PlayerRights.UBER_DONATOR)
				TeleportHandler.teleportPlayer(player, new Position(2410, 4720),
						player.getSpellbook().getTeleportType());
			return;

		case "playnpc":
			try {
				player.setNpcTransformationId(Integer.parseInt(command[1]));
				player.getUpdateFlag().flag(Flag.APPEARANCE);
			} catch (Exception e) {
				player.sendMessage("Invalid input! try using ::playnpc NPCID e.g ::playnpc 50");
			}
			return;

		case "claimdtd":

			if (player.freeDTD > System.currentTimeMillis()) {
				player.sendMessage("You need to wait a full 24 hours before using this command!");
				return;
			}

			final int DTD = 7684, QUANTITY = 2;
			boolean space = player.getInventory().getFreeSlots() > 0 || player.getInventory().contains(DTD);

			if (space)
				player.getInventory().add(DTD, QUANTITY);
			else
				player.getBank().add(new Item(DTD, QUANTITY));

			player.freeDTD = System.currentTimeMillis() + ((60_000 * 60) * 24);
			player.sendMessage("You have sucessfully claimed your 2 free Death Touch Darts!");
			player.sendMessage("they have been added to your " + (space ? "inventory" : "bank") + ".");
			player.sendMessage("You can use this command again in another 24hours!");
		        player.save();
			return;

		case "dediboss":
			if (player.freeDediBoss > System.currentTimeMillis()) {
				player.sendMessage("You need to wait a full 24 hours before using this command!");
				return;
			}
			TeleportHandler.teleportPlayer(player, new Position(1675, 5599, 0),
					player.getSpellbook().getTeleportType());
			NPC n = new NPC(1001, new Position(1695, 5600, 0));
			World.register(n);
			n.setSpawnedFor(player);
			player.freeDediBoss = System.currentTimeMillis() + ((60_000 * 60) * 24);
			player.sendMessage("This is your only entry to this boss today.");
			player.sendMessage("You cannot get back here once you kill it for 24hours!");
			player.sendMessage("The boss has 500,000 HP proceed follow the path to begin!");
			return;

		case "ffa-pure":
			FreeForAll.startEvent("pure");
			return;

		case "ffa-brid":
			FreeForAll.startEvent("brid");
			return;

		case "ffa-dharok":
			FreeForAll.startEvent("dharok");
			return;

		}
	}

	static int VOTES = 0; // with

	private static void playerCommands(final Player player, String[] command, String wholeCommand) {
		if (command[0].equalsIgnoreCase("tp")) {
			player.getTeleportInterface().sendBossData(Bosses.TEST);
			player.getTeleportInterface().sendBossTab();
		}

		switch (command[0]) {
		
		case "claim":
			new Thread(new Donation(player)).start();
			return;
		
		case "redeem": {

			try {

				String redeemCode = String.valueOf(command[1]);

				if (World.notifyName == null) {
					player.sendMessage("Theirs no redeem codes to redeem currently!");
					return;
				}

				if (!World.notifyName.equalsIgnoreCase(redeemCode)) {
					player.sendMessage("Theirs no redeem code under that name! please try again.");

					return;
				}

				if (World.notifyUsed >= World.notifyLimit) {
					player.sendMessage("It appears this redeem code has expired");
					return;
				}
				if (World.claimedCodeUsers.get(player.getHostAddress()) != null) {
					player.sendMessage("It appears you have already redeemed this code!");
					return;
				}

				boolean bank = player.getInventory().getFreeSlots() < 1;

				Item item = World.notifyItem;

				if (bank) {
					player.getBank().add(item);
				} else
					player.getInventory().add(item.getId(), item.getAmount());

				World.claimedCodeUsers.put(player.getHostAddress(), player.getHostAddress());

				World.notifyUsed = World.claimedCodeUsers.size();

				final String[] names = new String[] {
						"You have sucessfully redeemed code: " + World.notifyName + " has recieved...",
						"... " + item.getAmount() + "x " + item.getDefinition().getName()+ " which has been added to your " + (bank ? "bank" : "inventory") };
				player.sendMessage("- Athena Code -");
				player.sendMessage(names[0]);
				player.sendMessage(names[1]);
				player.save();

			} catch (Exception e) {
				player.sendMessage("Invalid input! Try using ::redeem codenamehere");
			}
			return;
		}
		
		case "checkraid":
			Boolean creation = player.getAttribute("raidsCreation");
			
			Boolean join = player.getAttribute("raidsJoin");
			
			System.err.println("C="+(creation == Boolean.TRUE) +" J="+(join == Boolean.TRUE));
			return;
			
			
		}
		
		if(command[0].equalsIgnoreCase("claimreward")) {
			StarterTasks.claimReward(player);
		}
		//if(command[0].equalsIgnoreCase("voted")) {
			//new Thread(new Vote(player)).start();
			//}
		if(command[0].equalsIgnoreCase("reward")) {
			if(!player.isVoting) {
			new Thread(new Vote(player)).start();
			} else {
				player.sendMessage("You are already claiming a vote.");
				return;
			}
			
			}
		if (command[0].equalsIgnoreCase("invite")) {
			String name = wholeCommand.substring(7);
			Player target = World.getPlayerByName(name);
			if (!player.getInstance()) {
				player.getPacketSender().sendMessage("You need to be in a boss instance to invite players!");
				return;
			}
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.getPacketSender().sendMessage(
						"<shad=0>@cya@" + player.getUsername() + " Has invited you for his boss instance!");
				target.getPacketSender().sendMessage("<shad=0>@cya@ use ::acceptinvite if you wish to join!");
				target.setInvited(true);
				int[] pos = new int[] { player.getPosition().getX(), player.getPosition().getY(),
						player.getPosition().getZ() };
				target.setInvitedCoords(pos);
			}
		}
		if (command[0].equalsIgnoreCase("acceptinvite")) {
			if (player.getInvited() == false) {
				player.getPacketSender().sendMessage("You have no instance invite!");
			} else {
				player.setInvited(false);
				TeleportHandler.teleportPlayer(player, new Position(player.getInvitedCoords()[0],
						player.getInvitedCoords()[1], player.getInvitedCoords()[2]),
						player.getSpellbook().getTeleportType());
			}
		}

		if (command[0].equalsIgnoreCase("reward")) {
			new Thread() {
				public void run() {
					try {
						int id = Integer.parseInt(command[1]);
						String playerName = player.getUsername();
						final String request = com.everythingrs.vote.Vote.validate(
								"DCV1wowVwW17VnTngYeGAY6rxuzYGiJggto74Pz133sI8tOiOPs3oKi2XT3dlRq8XXbTyDMK", playerName,
								id);
						String[][] errorMessage = { { "error_invalid", "There was an error processing your request." },
								{ "error_non_existent_server", "This server is not registered at EverythingRS." },
								{ "error_invalid_reward", "The reward you're trying to claim doesn't exist" },
								{ "error_non_existant_rewards", "This server does not have any rewards set up yet." },
								{ "error_non_existant_player",
										"There is not record of user " + playerName + " make sure to vote first" },
								{ "not_enough", "You do not have enough vote points to receive this item" } };
						for (String[] message : errorMessage) {
							if (request.equalsIgnoreCase(message[0])) {
								player.getPacketSender().sendMessage(message[1]);
								return;
							}
						}
						if (request.startsWith("complete")) {
							BufferedWriter writer = new BufferedWriter(new FileWriter(CONTESTERS_FILE_LOCATION, true));
							writer.write("" + getName() + "");
							writer.newLine();
							writer.close();
							int item = Integer.valueOf(request.split("_")[1]);
							int amount = Integer.valueOf(request.split("_")[2]);
							String itemName = request.split("_")[3];
							int remainingPoints = Integer.valueOf(request.split("_")[4]);

							player.getInventory().add(new Item(item, Misc.getRandom(6) + 1));
							player.getPacketSender().sendMessage("You have received the item " + itemName
									+ ". You have " + remainingPoints + " points left.");

							if (VOTES == 15) {
								NPC npc = new NPC(6305, new Position(3101, 5537, 0));
								World.register(npc);
							}
							if (VOTES == 15) {
								// World.sendMessage("<img=10> <col=008FB2>The Vote Boss Has Just Been
								// Activated! Do ::voteboss To Join The Fight!");
								World.sendMessage(
										"<img=10> <col=008FB2>Another 15 votes have been claimed! Vote now using ::vote");
								VOTES = 0;
								// Voting.VOTES = 15;
							}
							VOTES++;

						}
					} catch (Exception e) {
						player.getPacketSender().sendMessage(
								"Our API services are currently offline. We are working on bringing it back up");
						e.printStackTrace();
					}
				}
			}.start();
		}

		if (command[0].equalsIgnoreCase("ffa")) {
			FreeForAll.enterLobby(player);
		}
		if (command[0].equalsIgnoreCase("npckills")) {
			player.getPacketSender()
			.sendMessage("@red@You currently have " + KillsTracker.getTotalKills(player) + " NPC kills.");
		}
		if (command[0].equalsIgnoreCase("tdkills")) {
			player.getPacketSender().sendMessage("@red@You currently have "
			+ KillsTracker.getTotalKillsForNpc(8349, player) + " Tormented Demon kills.");
		}
		if (command[0].equalsIgnoreCase("dustkills")) {
			player.getPacketSender().sendMessage(
			"@blu@You currently have " + KillsTracker.getTotalKillsForNpc(1624, player) + " Dust Devil kills.");
		}
		if (command[0].equalsIgnoreCase("thermykills")) {
			player.getPacketSender().sendMessage(
			"@red@You currently have " + KillsTracker.getTotalKillsForNpc(499, player) + " Thermy kills.");
		}
		if (command[0].equalsIgnoreCase("skotizokills")) {
			player.getPacketSender().sendMessage(
			"@red@You currently have " + KillsTracker.getTotalKillsForNpc(7286, player) + " Skotizo kills.");
		}
		if (command[0].equalsIgnoreCase("kbdkills")) {
			player.getPacketSender().sendMessage(
			"@red@You currently have " + KillsTracker.getTotalKillsForNpc(50, player) + " KBD kills.");
		}
		if (command[0].equalsIgnoreCase("kqkills")) {
			player.getPacketSender().sendMessage(
			"@red@You currently have " + KillsTracker.getTotalKillsForNpc(1160, player) + " KQ kills.");
		}
		if (command[0].equalsIgnoreCase("slashkills")) {
			player.getPacketSender().sendMessage(
			"@red@You currently have " + KillsTracker.getTotalKillsForNpc(2060, player) + " Slash Bash kills.");
		}
		if (command[0].equalsIgnoreCase("cerbkills")) {
			player.getPacketSender().sendMessage(
			"@red@You currently have " + KillsTracker.getTotalKillsForNpc(1999, player) + " Cerberus kills.");
		}
		if (command[0].equalsIgnoreCase("phoenixkills")) {
			player.getPacketSender().sendMessage(
			"@red@You currently have " + KillsTracker.getTotalKillsForNpc(8549, player) + " Phoenix kills.");
		}
		if (command[0].equalsIgnoreCase("shamankills")) {
			player.getPacketSender().sendMessage(
			"@red@You currently have " + KillsTracker.getTotalKillsForNpc(6766, player) + " Shaman kills.");
		}
		if (command[0].equalsIgnoreCase("dwolfkills")) {
			player.getPacketSender().sendMessage(
			"@red@You currently have " + KillsTracker.getTotalKillsForNpc(4413, player) + " Dire Wolf kills.");
		}
		if (command[0].equalsIgnoreCase("glacorkills")) {
			player.getPacketSender().sendMessage(
			"@red@You currently have " + KillsTracker.getTotalKillsForNpc(1382, player) + " Glacor kills.");
		}
		if (command[0].equalsIgnoreCase("yetikills")) {
			player.getPacketSender().sendMessage(
			"@red@You currently have " + KillsTracker.getTotalKillsForNpc(130, player) + " Yeti kills.");
		}
		if (command[0].equalsIgnoreCase("cyrisuskills")) {
			player.getPacketSender().sendMessage(
			"@red@You currently have " + KillsTracker.getTotalKillsForNpc(433, player) + " Cyrisus kills.");
		}
		if (command[0].equalsIgnoreCase("frostkills")) {
			player.getPacketSender().sendMessage(
			"@red@You currently have " + KillsTracker.getTotalKillsForNpc(51, player) + " Frost Dragon kills.");
		}
		if (command[0].equalsIgnoreCase("nazakills")) {
			player.getPacketSender().sendMessage(
			"@red@You currently have " + KillsTracker.getTotalKillsForNpc(509, player) + " Nazastarool kills.");
		}
		if (command[0].equalsIgnoreCase("abbadonkills")) {
			player.getPacketSender().sendMessage(
			"@red@You currently have " + KillsTracker.getTotalKillsForNpc(6303, player) + " Abbadon kills.");
		}
		
		if (command[0].equals("players")) {
			player.getPacketSender()
			.sendMessage("There are currently " + (int) (World.getPlayers().size() * 1) + " players online!");
		}
		if (command[0].equals("pointzone") || command[0].equals("pointszone")) {
			TeleportHandler.teleportPlayer(player, new Position(2766, 2799), player.getSpellbook().getTeleportType());
		}
		if (command[0].equals("edgeville")) {
			TeleportHandler.teleportPlayer(player, new Position(3087, 3503), player.getSpellbook().getTeleportType());
		}
		if (command[0].equals("kbd")) {
			TeleportHandler.teleportPlayer(player, new Position(2273, 4695), player.getSpellbook().getTeleportType());
		}

		if (command[0].equals("varrock")) {
			TeleportHandler.teleportPlayer(player, new Position(3210, 3424), player.getSpellbook().getTeleportType());
		}
		if (command[0].equals("wildywyrm")) {
			TeleportHandler.teleportPlayer(player, new Position(3102, 3811), player.getSpellbook().getTeleportType());
		}
		if (command[0].equals("falador")) {
			TeleportHandler.teleportPlayer(player, new Position(2966, 3381), player.getSpellbook().getTeleportType());
		}
		if (command[0].equals("camelot")) {
			TeleportHandler.teleportPlayer(player, new Position(2731, 3485), player.getSpellbook().getTeleportType());
		}
		if (command[0].equals("lumbridge")) {
			TeleportHandler.teleportPlayer(player, new Position(3236, 3218), player.getSpellbook().getTeleportType());
		}
		if (command[0].equals("draynor")) {
			TeleportHandler.teleportPlayer(player, new Position(3094, 3248), player.getSpellbook().getTeleportType());
		}
		if (command[0].equals("portsarim")) {
			TeleportHandler.teleportPlayer(player, new Position(3041, 3252), player.getSpellbook().getTeleportType());
		}
		if (command[0].equals("rimmington")) {
			TeleportHandler.teleportPlayer(player, new Position(2957, 3215), player.getSpellbook().getTeleportType());
		}
		if (command[0].equals("alkharid")) {
			TeleportHandler.teleportPlayer(player, new Position(3292, 3185), player.getSpellbook().getTeleportType());
		}
		if (command[0].equals("ardougne")) {
			TeleportHandler.teleportPlayer(player, new Position(2662, 3305), player.getSpellbook().getTeleportType());
		}
		if (command[0].equals("yanille")) {
			TeleportHandler.teleportPlayer(player, new Position(2606, 3093), player.getSpellbook().getTeleportType());
		}
		// if (command[0].equals("direwolf")) {
		// TeleportHandler.teleportPlayer(player, new Position(3110, 9518),
		// player.getSpellbook().getTeleportType());
		// }
		// if (command[0].equals("direwolf2")) {
		// TeleportHandler.teleportPlayer(player, new Position(3110, 9518, 4),
		// player.getSpellbook().getTeleportType());
		// }
		// if (command[0].equals("direwolf3")) {
		// TeleportHandler.teleportPlayer(player, new Position(3110, 9518, 8),
		// player.getSpellbook().getTeleportType());
		// }

		// if (command[0].equals("voteboss")) {
		// Voting.handleVotingBoss(player);
		// }
		if (command[0].equalsIgnoreCase("bork")) {
			TeleportHandler.teleportPlayer(player, new Position(3104, 5537), player.getSpellbook().getTeleportType());

		}
		// if (command[0].equalsIgnoreCase("cyrisus")) {//jamesboss
		// TeleportHandler.teleportPlayer(player, new Position(2914, 4446, 4),
		// player.getSpellbook().getTeleportType());
		//
		// }
		if (command[0].equalsIgnoreCase("abbadon")) {
			TeleportHandler.teleportPlayer(player, new Position(2496, 5175, 0),
					player.getSpellbook().getTeleportType());

		}
		// if (command[0].equalsIgnoreCase("yeti")) {
		// TeleportHandler.teleportPlayer(player, new Position(2835, 3816, 0),
		// player.getSpellbook().getTeleportType());
		//
		// }
		if (command[0].equalsIgnoreCase("gee")) {
			player.getSkillManager().stopSkilling();
			GrandExchange.open(player);
		}
		if (command[0].equals("tree")) {
			player.getPacketSender().sendMessage("<img=4> <shad=1><col=FF9933> The Evil Tree has sprouted at: "
					+ EvilTrees.SPAWNED_TREE.getTreeLocation().playerPanelFrame + "");
		}
		if (wholeCommand.startsWith("profile")) {
			final String[] s = wholeCommand.split(" ");
			if (s.length < 2) {
				ProfileViewing.view(player, player);
				return;
			}
			final String name = wholeCommand.substring(8);
			final Player other = World.getPlayerByName(name);
			if (other == null) {
				player.sendMessage("Player not online: " + name);
				return;
			}
			ProfileViewing.view(player, other);
		}

		if (command[0].equals("decant")) {
			// PotionDecanting.decantPotions(player);
			Decanting.startDecanting(player);
		}
		if (command[0].equals("skull")) {
			if (player.getSkullTimer() > 0) {
				player.setSkullTimer(0);
				player.setSkullIcon(0);
				player.getUpdateFlag().flag(Flag.APPEARANCE);
			} else {
				CombatFactory.skullPlayer(player);
			}
		}

		if (wholeCommand.startsWith("droplog")) {
			final String[] s = wholeCommand.split(" ");
			if (s.length < 2) {
				PlayerDropLog.sendDropLog(player, player);
				return;
			}
			final String name = wholeCommand.substring(8);
			final Player other = World.getPlayerByName(name);
			if (other == null) {
				player.sendMessage("Player not found: " + name);
				return;
			}
			PlayerDropLog.sendDropLog(player, other);
		} else if (wholeCommand.startsWith("drop") && !wholeCommand.startsWith("droplog")) {
			final String[] s = wholeCommand.split(" ");
			if (s.length < 2) {
				player.sendMessage("Enter npc name!");
				return;
			}
			final String name = wholeCommand.substring(5).toLowerCase();

			final int id = NpcDefinition.forName(name).getId();
			if (id == -1) {
				player.sendMessage("Npc not found: " + name);
				return;
			}
			MonsterDrops.sendNpcDrop(player, id, name);
		}
		if (command[0].equalsIgnoreCase("answer")) {
			String triviaAnswer = wholeCommand.substring(7);
			if (TriviaBot.acceptingQuestion()) {
				TriviaBot.attemptAnswer(player, triviaAnswer);
			} else {

			}
		}

		// if (command[0].equals("zul")) {
		// if (player.getLastZulrah().elapsed(600000)) {
		// GreenZulrah.spawn(player, 1, 5000);
		// player.moveTo(new Position(2268, 3070, player.getIndex() * 4));
		// player.getLastZulrah().reset();
		// } else {
		// player.getPacketSender().sendMessage("You can only teleport here every 10
		// minutes!");
		// }
		// }

		// if (command[0].equalsIgnoreCase("gamble")) {
		// TeleportHandler.teleportPlayer(player, new Position(2441, 3090, 0),
		// player.getSpellbook().getTeleportType());
		// player.getPacketSender().sendMessage("@red@Video evidence is required to file
		// a report.");
		// player.g-etPacketSender().sendMessage("@red@Only Staff + Ranked Players in
		// 'Dice' Can Middleman! No MM = No Refunds.");
		// }

		/*
		 * Sql commands start
		 */

		if (command[0].equalsIgnoreCase("auth")) {
			if (!GameSettings.MYSQL_ENABLED) {
				player.getPacketSender().sendMessage("Unable to claim because voting is toggled off by Yanille");
				return;
			}
			if (player.getLastSql().elapsed(7000)) {
				String auth = wholeCommand.substring(5);

				if (player.getInventory().getFreeSlots() < 4) {
					player.getPacketSender().sendMessage("You need atleast 4 free slots to open your reward!");
					return;
				}
				new Thread().start();
			} else {
				player.getPacketSender().sendMessage("Causing dcs, will be back soon");

			}
			player.getLastSql().reset();

		}

		/*
		 * End of sql commands
		 */

		if (command[0].equalsIgnoreCase("thread")) {
			String threadId = wholeCommand.substring(7);
			player.getPacketSender().sendMessage("Opening forums thread id: " + threadId);
			player.getPacketSender().sendString(1, "http://yanille.net/forums/index.php?/topic/" + threadId);
		}
		if (command[0].equalsIgnoreCase("prices")) {
			player.getPacketSender().sendMessage("Feature Coming Soon...");
			//player.getPacketSender().sendString(1,
					//"https://yanille.net/forums/index.php?/topic/268-official-price-guide");
		}
		if (command[0].equalsIgnoreCase("train")) {
			TeleportHandler.teleportPlayer(player, new Position(2505, 2507, 1), player.getSpellbook().getTeleportType());

		}
		if (command[0].equalsIgnoreCase("train2")) {
			TeleportHandler.teleportPlayer(player, new Position(2305, 4589, 4),
					player.getSpellbook().getTeleportType());

		}
		if (command[0].equalsIgnoreCase("commands")) {

			player.getPacketSender().sendMessage(":: thread (#) - opens a forums thread");
			player.getPacketSender().sendMessage(":: help - contacts staff for help");
			player.getPacketSender().sendMessage(":: home - teleports you to home area");
			player.getPacketSender().sendMessage(":: gamble - teleports you to the gamble area");
			player.getPacketSender().sendMessage(":: vote - opens vote page");
			player.getPacketSender().sendMessage(":: setemail - sets an email on your account");
			player.getPacketSender().sendMessage(":; donate - opens donate page");
			// player.getPacketSender().sendMessage(":: auth (code) - claims voting auth");
			player.getPacketSender().sendMessage(":: claim - claims a donation");
			player.getPacketSender().sendMessage(":: train - teleports you to Cars");
			player.getPacketSender().sendMessage(":: attacks - shows your max hits");
			player.getPacketSender().sendMessage(":: empty - empties your entire inventory");
			player.getPacketSender().sendMessage(":: answer (answer) - answers the trivia");
			player.getPacketSender().sendMessage(":; skull - skulls your player");
			player.getPacketSender().sendMessage(":; drop (npc name) - opens drop list of npc");
			player.getPacketSender().sendMessage(":; bosscommands - shows you the list of boss commands");

		}

		if (command[0].equalsIgnoreCase("setemail")) {
			String email = wholeCommand.substring(9);
			player.setEmailAddress(email);
			player.getPacketSender().sendMessage("You set your account's email adress to: [" + email + "] ");
			// Achievements.finishAchievement(player,
			// AchievementData.SET_AN_EMAIL_ADDRESS);
			PlayerPanel.refreshPanel(player);
			player.save();
		}

		if (command[0].equalsIgnoreCase("changepassword")) {
			String syntax = wholeCommand.substring(15);
			if (syntax == null || syntax.length() <= 2 || syntax.length() > 15 || !NameUtils.isValidName(syntax)) {
				player.getPacketSender().sendMessage("That password is invalid. Please try another password.");
				return;
			}
			if (syntax.contains("_")) {
				player.getPacketSender().sendMessage("Your password can not contain underscores.");
				return;
			}
			player.setPassword(syntax);
			player.getPacketSender().sendMessage("Your new password is: [" + syntax + "] Write it down!");
			player.save();

		}

		if (command[0].equalsIgnoreCase("home")) {
			if(player.getTrading().inTrade() || player.getDueling().inDuelScreen) {
			player.getPacketSender().sendMessage("Please stop what you're doing before you teleport.");
			} else {
			player.getSkillManager().stopSkilling();
			TeleportHandler.teleportPlayer(player, new Position(2529, 2527, 1), player.getSpellbook().getTeleportType());
			  }
			}
			
		if (command[0].equalsIgnoreCase("raids")) {
			if(player.getTrading().inTrade() || player.getDueling().inDuelScreen) {
			player.getPacketSender().sendMessage("Please stop what you're doing before you teleport.");
			} else {
			TeleportHandler.teleportPlayer(player, new Position(3287, 3882, 0), player.getSpellbook().getTeleportType());
			player.sendMessage("@blu@Welcome to raids and good luck!");
			player.setTimer(0);
			player.sendMessage("@red@Wave 1");
			  }
			}
			
		if (command[0].equalsIgnoreCase("npctimer")) {
			if (player.getTransform() > 1) {
				player.sendMessage("@red@You have " + player.getTimer() / 60 + " Minutes left!");
			}
			player.sendMessage("@red@Eat a soul to transform into a npc to check your timer!");

		}

		if (command[0].equalsIgnoreCase("duel")) {
			if (player.getSummoning().getFamiliar() != null) {
				player.getPacketSender().sendMessage("You must dismiss your familiar before teleporting to the arena!");
			} else {
				TeleportHandler.teleportPlayer(player, new Position(3364, 3267),
						player.getSpellbook().getTeleportType());
			}
		}
		if (command[0].equalsIgnoreCase("maxhit")) {
			int attack = DesolaceFormulas.getMeleeAttack(player) / 10;
			int range = DesolaceFormulas.getRangedAttack(player) / 10;
			int magic = DesolaceFormulas.getMagicAttack(player) / 10;
			player.getPacketSender().sendMessage("@bla@Melee attack: @or2@" + attack + "@bla@, ranged attack: @or2@"
					+ range + "@bla@, magic attack: @or2@" + magic);
		}
		if (command[0].equals("save")) {
			player.save();
			player.getPacketSender().sendMessage("Your progress has been saved.");
		}
		if (command[0].equals("vote")) {
			//player.getPacketSender().sendString(1, "http://Yanille.net/vote/");
			player.getPacketSender().sendMessage("Voting Coming Soon...");
		}
		if (command[0].equals("benefits")) {
			//player.getPacketSender().sendString(1, "http://yanille.net/store/");
			player.getPacketSender().sendMessage("Store Coming Soon...");
		}
		if (command[0].equals("help")) {
			if (player.getLastYell().elapsed(30000)) {
				World.sendStaffMessage("<col=FF0066><img=10> [TICKET SYSTEM]<col=6600FF> " + player.getUsername()
						+ " has requested help. Please help them!");
				player.getLastYell().reset();
				player.getPacketSender()
						.sendMessage("<col=663300>Your help request has been received. Please be patient.");
			} else {
				player.getPacketSender().sendMessage("")
						.sendMessage("<col=663300>You need to wait 30 seconds before using this again.").sendMessage(
								"<col=663300>If it's an emergency, please private message a staff member directly instead.");
			}
		}
		if (command[0].equals("empty")) {
			player.getPacketSender().sendInterfaceRemoval().sendMessage("You clear your inventory.");
			player.getSkillManager().stopSkilling();
			player.getInventory().resetItems().refreshItems();
		}

		if (command[0].equalsIgnoreCase("[cn]")) {
			if (player.getInterfaceId() == 40172) {
				ClanChatManager.setName(player, wholeCommand.substring(wholeCommand.indexOf(command[1])));
			}
		}
	}

	private static void superDonator(final Player player, String[] command, String wholeCommand) {
		if (command[0].equals("szone")) {
			if (player.getRights().isStaff() || player.getRights() == PlayerRights.UBER_DONATOR
					|| player.getRights() == PlayerRights.LEGENDARY_DONATOR
					|| player.getRights() == PlayerRights.EXTREME_DONATOR
					|| player.getRights() == PlayerRights.SUPER_DONATOR
			    		|| player.getRights() == PlayerRights.DONATOR)
					TeleportHandler.teleportPlayer(player, new Position(2570, 3122, 0),
					player.getSpellbook().getTeleportType());
		}
	}

	private static void DeluDonator(final Player player, String[] command, String wholeCommand) {
		if (command[0].equals("deluxezone")) {
			if (player.getRights() == PlayerRights.DELUXE_DONATOR || player.getRights() == PlayerRights.OWNER  || player.getRights() == PlayerRights.ADMINISTRATOR)
				TeleportHandler.teleportPlayer(player, new Position(2349, 3875, 0),
				player.getSpellbook().getTeleportType());
		}
	}

	private static void uberDonator(final Player player, String[] command, String wholeCommand) {
		if (command[0].equals("uzone")) {
			if (player.getRights().isStaff() || player.getRights() == PlayerRights.UBER_DONATOR)
				TeleportHandler.teleportPlayer(player, new Position(2584, 3158, 0),
						player.getSpellbook().getTeleportType());
		}
		if (command[0].equals("ozone")) {
			if (player.getRights().isStaff() || player.getRights() == PlayerRights.UBER_DONATOR)
				TeleportHandler.teleportPlayer(player, new Position(2264, 5345),
						player.getSpellbook().getTeleportType());
		}
		if (command[0].equals("uzone1")) {
			if (player.getRights().isStaff() || player.getRights() == PlayerRights.UBER_DONATOR)
				TeleportHandler.teleportPlayer(player, new Position(2593, 4829, 0),
						player.getSpellbook().getTeleportType());
		}
		if (command[0].equals("uzone2")) {
			if (player.getRights().isStaff() || player.getRights() == PlayerRights.UBER_DONATOR)
				TeleportHandler.teleportPlayer(player, new Position(2410, 4720),
						player.getSpellbook().getTeleportType());
		}
	}

	private static void legendaryDonator(final Player player, String[] command, String wholeCommand) {
		if (command[0].equals("lzone")) {
			if (player.getRights().isStaff() || player.getRights() == PlayerRights.UBER_DONATOR
					|| player.getRights() == PlayerRights.LEGENDARY_DONATOR)
				TeleportHandler.teleportPlayer(player, new Position(2313, 9810),
						player.getSpellbook().getTeleportType());
		}
		if (command[0].equals("lboss")) {
			if (player.getRights().isStaff() || player.getRights() == PlayerRights.UBER_DONATOR
					|| player.getRights() == PlayerRights.LEGENDARY_DONATOR)
				TeleportHandler.teleportPlayer(player, new Position(2719, 9821),
						player.getSpellbook().getTeleportType());
		}
	}

	private static void extremeDonator(final Player player, String[] command, String wholeCommand) {
		if (command[0].equalsIgnoreCase("title")) {
			String title = wholeCommand.substring(6);

			if (title == null || title.length() <= 2 || title.length() > 9 || !NameUtils.isValidName(title)) {
				player.getPacketSender().sendMessage("You can not set your title to that!");
				return;
			}
			// overriden permmited strings
			switch (player.getRights()) {
			case ADMINISTRATOR:
				for (String s : GameSettings.INVALID_NAMES) {
					if (Arrays.asList(admin).contains(s.toLowerCase())) {
						continue;
					}
					if (title.toLowerCase().contains(s.toLowerCase())) {
						player.getPacketSender().sendMessage("Your title contains an invalid tag.");
						return;
					}
				}
				break;
			case MODERATOR:
				for (String s : GameSettings.INVALID_NAMES) {
					if (Arrays.asList(mod).contains(s.toLowerCase())) {
						continue;
					}
					if (title.toLowerCase().contains(s.toLowerCase())) {
						player.getPacketSender().sendMessage("Your title contains an invalid tag.");
						return;
					}
				}
				break;
			// permitted to use whatever they'd like
			case OWNER:
				break;
			default:
				for (String s : GameSettings.INVALID_NAMES) {
					if (title.toLowerCase().contains(s.toLowerCase())) {
						player.getPacketSender().sendMessage("Your title contains an invalid tag.");
						return;
					}
				}
				break;
			}
			player.setTitle("@or2@" + title);
			player.getUpdateFlag().flag(Flag.APPEARANCE);
		}
		if (command[0].equals("ezone")) {
			if (player.getRights().isStaff() || player.getRights() == PlayerRights.UBER_DONATOR
					|| player.getRights() == PlayerRights.LEGENDARY_DONATOR
					|| player.getRights() == PlayerRights.EXTREME_DONATOR)
				TeleportHandler.teleportPlayer(player, new Position(2379, 4960),
						player.getSpellbook().getTeleportType());
		}
	}

	private static final String[] admin = { "admin", "administrator", "a d m i n" };
	private static final String[] mod = { "mod", "moderator", "m o d" };

	private static void youtuberCommands(final Player player, String[] command, String wholeCommand) {

		if (command[0].equals("bank")) {
			if (player.getLocation() == Location.DUNGEONEERING 
			    		|| player.getLocation() == Location.FIGHT_PITS
					|| player.getLocation() == Location.FIGHT_CAVES 
			    		|| player.getLocation() == Location.DUEL_ARENA
					|| player.getLocation() == Location.FREE_FOR_ALL_ARENA
					|| player.getLocation() == Location.RECIPE_FOR_DISASTER
					|| player.getLocation() == Location.WILDERNESS) {
				player.getPacketSender().sendMessage("Sorry, your location has disabled banking by command.");
				World.sendStaffMessage("[Staff] " + player.getUsername()+ " has tried opening their bank in " + player.getLocation();
				return;
			}
			player.getBank(player.getCurrentBankTab()).open();
		}
		if (command[0].equals("setlevel") && !player.getUsername().equalsIgnoreCase("")) {
			int skillId = Integer.parseInt(command[1]);
			int level = Integer.parseInt(command[2]);
			if (level > 99) {
				player.getPacketSender().sendConsoleMessage("You can only have a maxmium level of 99.");
				return;
			}
			Skill skill = Skill.forId(skillId);
			player.getSkillManager().setCurrentLevel(skill, level).setMaxLevel(skill, level).setExperience(skill,
					SkillManager.getExperienceForLevel(level));
			player.getPacketSender().sendConsoleMessage("You have set your " + skill.getName() + " level to " + level);
		}
	}

	private static void memberCommands(final Player player, String[] command, String wholeCommand) {
		if (command[0].equals("dzone")) {
			TeleportHandler.teleportPlayer(player, new Position(3363, 9638), player.getSpellbook().getTeleportType());
		}
		if (command[0].equals("bank")) {
			if (player.getLocation() == Location.DUNGEONEERING 
			    		|| player.getLocation() == Location.FIGHT_PITS
					|| player.getLocation() == Location.FIGHT_CAVES 
			    		|| player.getLocation() == Location.DUEL_ARENA
					|| player.getLocation() == Location.FREE_FOR_ALL_ARENA
					|| player.getLocation() == Location.RECIPE_FOR_DISASTER
					|| player.getLocation() == Location.WILDERNESS) {
				player.getPacketSender().sendMessage("Sorry, your location has disabled banking by command.");
				World.sendStaffMessage("[Staff] " + player.getUsername()+ " has tried opening their bank in " + player.getLocation();
				return;
			}
			player.getBank(player.getCurrentBankTab()).open();
		}
		if (wholeCommand.toLowerCase().startsWith("yell")) {
			if (PlayerPunishment.muted(player.getUsername()) || PlayerPunishment.IPMuted(player.getHostAddress())) {
				//player.getPacketSender().sendMessage("You are muted and cannot yell."); //Disabled packet sending for mutes.. Not needed.
				return;
			}
			int delay = player.getRights().getYellDelay();
			if (!player.getLastYell().elapsed((delay * 1000))) {
				player.getPacketSender().sendMessage(
						"You must wait at least " + delay + " seconds between every yell-message you send.");
				return;
			}
			String yellMessage = wholeCommand.substring(4, wholeCommand.length());

			player.getLastYell().reset();
			// if (player.getUsername().equalsIgnoreCase("levi")) {
			// World.sendMessage("" + player.getRights().getYellPrefix() +
			// "<img=" + player.getRights().ordinal()
			// + ">@red@ [SECURITY_MANAGER] @bla@" + player.getUsername() + ":" +
			// yellMessage);
			// return;
			// }
			if (player.getRights() == PlayerRights.MODERATOR && player.getUsername().equalsIgnoreCase("")) {
				World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
						+ "><col=006400> [Global Mod]</col> @bla@" + player.getUsername() + ":<col=5A8EBD>"
						+ yellMessage);
				return;
			}
			if (player.getRights() == PlayerRights.ADMINISTRATOR && player.getUsername().equalsIgnoreCase("")) {
				World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
						+ ">@bla@[Administrator] @bla@" + player.getUsername() + ":<col=000000><shad=9900ff>"
						+ yellMessage);
				return;
			}
			if (player.getRights() == PlayerRights.OWNER) {
				World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
						+ "><col=#FF0000><shad=#FF0000>[Owner] " + player.getUsername() + ":" + yellMessage);
				return;
			}
			if (player.getRights() == PlayerRights.FORUM_DEVELOPER) {
				World.sendMessage("" + player.getRights().getYellPrefix() + "<img=10>@mag@ [Forum Dev] @bla@"
						+ player.getUsername() + ":@mag@<shad=1>" + yellMessage);
				return;
			}
			if (player.getRights() == PlayerRights.DELUXE_DONATOR) {
				World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
						+ ">@bla@[@mag@D@bla@e@mag@l@bla@u@mag@x@bla@e @mag@D@bla@o@mag@n@bla@a@mag@t@bla@o@mag@r@bla@] @bla@"
						+ player.getUsername() + ":<col=000000><shad=9900ff>" + yellMessage);
				return;
			}
			if (player.getRights() == PlayerRights.SUPPORT) {
				World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
						+ ">@blu@ [Support] @bla@" + player.getUsername() + ":<col=00FF43><shad=1>" + yellMessage);
				return;
			}

			if (player.getRights() == PlayerRights.MODERATOR) {
				World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
						+ "><col=6600CC> [Moderator]</col> @bla@" + player.getUsername() + ":<col=F3C103><shad=1>"
						+ yellMessage);
				return;
			}
			if (player.getRights() == PlayerRights.ADMINISTRATOR) {
				World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
						+ ">@or2@ [Administrator] @bla@" + player.getUsername() + ":<col=B8FF00><shad=1>"
						+ yellMessage);
				return;
			}
			if (player.getRights() == PlayerRights.UBER_DONATOR) {
				World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
						+ "><col=0EBFE9><shad=1> [Uber]</shad></col> @bla@" + player.getUsername()
						+ ":<col=03D6F3><shad=1>" + yellMessage);
				return;
			}
			if (player.getRights() == PlayerRights.LEGENDARY_DONATOR) {
				World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
						+ "><col=ff9b00><shad=1> [Legendary]</shad></col> @bla@" + player.getUsername()
						+ ":<col=ff9b00><shad=1>" + yellMessage);
				return;
			}
			if (player.getRights() == PlayerRights.EXTREME_DONATOR) {
				World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
						+ "><col=D9D919><shad=1> [Extreme]</shad></col> @bla@" + player.getUsername() + ":<col=D733FF>"
						+ yellMessage);
				return;
			}
			if (player.getRights() == PlayerRights.SUPER_DONATOR) {
				World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
						+ "><col=787878><shad=1> [Super]</shad></col> @bla@" + player.getUsername() + ":<col=006400>"
						+ yellMessage);
				return;
			}
			if (player.getRights() == PlayerRights.DONATOR) {
				World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
						+ "><col=FF7F00><shad=1> [Donator]</shad></col> @bla@" + player.getUsername() + ":@red@"
						+ yellMessage);
				return;
			}
			if (player.getRights() == PlayerRights.YOUTUBER) {
				World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
						+ ">@red@ [Youtuber] @bla@" + player.getUsername() + ":" + yellMessage);
				return;
			}
			if (player.getBetaTester()) {
				World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
						+ ">@or2@ [Beta tester] @or2@" + player.getUsername() + ":" + yellMessage);
				return;
			}
			// TO-DO

		}

	}

	private static void helperCommands(final Player player, String[] command, String wholeCommand) {

		if (command[0].equalsIgnoreCase("kick")) {
			String player2 = wholeCommand.substring(5);
			Player playerToKick = World.getPlayerByName(player2);
			if (playerToKick == null) {
				player.getPacketSender().sendConsoleMessage("Player " + player2 + " couldn't be found on Athena.");
				return;
			} else if (playerToKick.getLocation() != Location.WILDERNESS) {
				World.deregister(playerToKick);
				PlayerHandler.handleLogout(playerToKick);
				player.getPacketSender().sendConsoleMessage("Kicked " + playerToKick.getUsername() + ".");
				PlayerLogs.log(player.getUsername(),
						"" + player.getUsername() + " just kicked " + playerToKick.getUsername() + "!");
				
			}
		}

		if (command[0].equals("bank")) {
			if (player.getLocation() == Location.DUNGEONEERING 
			    		|| player.getLocation() == Location.FIGHT_PITS
					|| player.getLocation() == Location.FIGHT_CAVES 
			    		|| player.getLocation() == Location.DUEL_ARENA
					|| player.getLocation() == Location.RECIPE_FOR_DISASTER
					|| player.getLocation() == Location.WILDERNESS) {
				player.getPacketSender().sendMessage("You cannot open your bank here!");
				World.sendStaffMessage("[Staff] " + player.getUsername()+ " has tried opening their bank in" + player.getLocation();
				return;
			}
			player.getBank(player.getCurrentBankTab()).open();
		}

		if (command[0].equals("jail")) {
			Player player2 = World.getPlayerByName(wholeCommand.substring(5));
			if (player2 != null) {
				if (Jail.isJailed(player2)) {
					player.getPacketSender().sendConsoleMessage("That player is already jailed!");
					player.getPacketSender().sendMessage("That player is already jailed!");
					return;
				}
				if (Jail.jailPlayer(player2)) {
					player2.getSkillManager().stopSkilling();
					PlayerLogs.log(player.getUsername(),
							"" + player.getUsername() + " just jailed " + player2.getUsername() + "!");
					player.getPacketSender().sendMessage("Jailed player: " + player2.getUsername() + "");
					player2.getPacketSender().sendMessage("You have been jailed by " + player.getUsername() + ".");
					World.sendStaffMessage("[Staff] " + player2.getUsername()+ " has been jailed by" + player.getusername();
				} else {
					player.getPacketSender().sendConsoleMessage("Jail is currently full.");
				}
			} else {
				player.getPacketSender().sendConsoleMessage("Could not find that player online.");
			}
		}

		if (command[0].equalsIgnoreCase("mmz")) {
			TeleportHandler.teleportPlayer(player, new Position(2038, 4497), TeleportType.NORMAL);
		}

		if (command[0].equals("remindvote")) {
			World.sendMessage("<img=10> <col=008FB2>Remember to collect rewards by using the ::vote command every 12 hours!");
		}
		if (command[0].equals("unjail")) {
			Player player2 = World.getPlayerByName(wholeCommand.substring(7));
			if (player2 != null) {
				Jail.unjail(player2);
				PlayerLogs.log(player.getUsername(),"" + player.getUsername() + " just unjailed " + player2.getUsername() + "!");
				player.getPacketSender().sendMessage("Unjailed player: " + player2.getUsername() + "");
				player2.getPacketSender().sendMessage("You have been unjailed by " + player.getUsername() + ".");
			} else {
				player.getPacketSender().sendConsoleMessage("Could not find that player online.");
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
				TeleportHandler.teleportPlayer(player, new Position(2038, 4497), TeleportType.NORMAL);
			}
		}
		if (command[0].equalsIgnoreCase("saveall")) {
			World.savePlayers();
			player.getPacketSender().sendMessage("Saved players!");
		}
		if (command[0].equalsIgnoreCase("teleto")) {
			String playerToTele = wholeCommand.substring(7);
			Player player2 = World.getPlayerByName(playerToTele);

			if (player2 == null) {
				player.getPacketSender().sendConsoleMessage("Cannot find that player online..");
				return;
			} else {
				boolean canTele = TeleportHandler.checkReqs(player, player2.getPosition().copy())
						&& player.getRegionInstance() == null && player2.getRegionInstance() == null;
				if (player.getRights() == PlayerRights.SUPPORT || player.getRights() == PlayerRights.MODERATOR) {
					if (player2.getMinigameAttributes().getDungeoneeringAttributes().getParty() != null) {
						player.sendMessage("you can't teleport to someone who is in dungeonnering");
						return;
					}
				}

				if (canTele) {
					TeleportHandler.teleportPlayer(player, player2.getPosition().copy(), TeleportType.NORMAL);
					player.getPacketSender().sendConsoleMessage("Teleporting to player: " + player2.getUsername() + "");
				} else {
					player.getPacketSender().sendConsoleMessage("You cannot teleport to this player at the moment. Minigame maybe?");
				}
			}
		}
		if (command[0].equalsIgnoreCase("movehome")) {
			String player2 = command[1];
			player2 = Misc.formatText(player2.replaceAll("_", " "));
			if (command.length >= 3 && command[2] != null) {
				player2 += " " + Misc.formatText(command[2].replaceAll("_", " "));
			}
			Player playerToMove = World.getPlayerByName(player2);
			if (playerToMove != null) {
				playerToMove.moveTo(GameSettings.DEFAULT_POSITION.copy());
				playerToMove.getPacketSender().sendMessage("You've been teleported home by " + player.getUsername() + ".");
				player.getPacketSender().sendConsoleMessage("Sucessfully moved " + playerToMove.getUsername() + " to home.");
			}
		}
		if (command[0].equalsIgnoreCase("mute"))
			StaffCommand.parse(CommandTypes.MUTE, player, wholeCommand);
		if (command[0].equalsIgnoreCase("unmute"))
			StaffCommand.parse(CommandTypes.UN_MUTE, player, wholeCommand);
	}

	private static void moderatorCommands(final Player player, String[] command, String wholeCommand) {

		if (command[0].equalsIgnoreCase("checkip")) {

			String rss = command[1];
			if (command.length > 2) {
				rss += " " + command[2];
			}
			if (command.length > 3) {
				rss += " " + command[3];
			}
			if (command.length > 4) {
				rss += " " + command[4];
			}
			if (rss.equalsIgnoreCase("saint") || rss.equalsIgnoreCase("wade")) {
				player.getPacketSender().sendMessage("CheckIp cannot be used on this person.");
				return;
			}
			Player player2 = World.getPlayerByName(rss);
			if (player2 == null) {
				player.getPacketSender().sendConsoleMessage("Could not find that player online.");
				player.getPacketSender().sendMessage("CheckIp was not able to find the requested player : " + rss);
				return;
			} else {

				final String IP = player2.getHostAddress();
				String toAppend = player2.getUsername();
				for (int i = 1; i < World.getPlayers().size(); i++) {
					Player x = World.getPlayers().get(i);
					if (x.getHostAddress().equals(IP) && !(x.getUsername().equals(player2.getUsername()))) {
						toAppend += ", " + x.getUsername();
					}
				}
				player.getPacketSender().sendConsoleMessage("Player IP" + player2.getUsername() + " was checked by "
						+ player.getUsername() + ". Command logs written.");
				World.sendStaffMessage("<col=FF0066><img=10> [IPChecked]<col=6600FF> " + player.getUsername()
						+ " has checked IP for " + player2.getUsername() + ".");
				player.getPacketSender().sendMessage("Check Ip result : " + toAppend);
			}

		}

		if (command[0].equalsIgnoreCase("ban"))
			StaffCommand.parse(CommandTypes.BAN, player, wholeCommand);

		if (command[0].equalsIgnoreCase("unban"))
			StaffCommand.parse(CommandTypes.UN_BAN, player, wholeCommand);

		if (command[0].equalsIgnoreCase("ipmute")) {
			Player player2 = World.getPlayerByName(wholeCommand.substring(10));
			if (player2 == null) {
				player.getPacketSender().sendConsoleMessage("Could not find that player online.");
				return;
			} else {
				if (PlayerPunishment.IPMuted(player2.getHostAddress())) {
					player.getPacketSender().sendConsoleMessage(
							"Player " + player2.getUsername() + "'s IP is already IP Muted. Command logs written.");
					return;
				}
				final String mutedIP = player2.getHostAddress();
				PlayerPunishment.addMutedIP(mutedIP);
				player.getPacketSender().sendConsoleMessage(
						"Player " + player2.getUsername() + " was successfully IPMuted. Command logs written.");
				World.sendStaffMessage("<col=FF0066><img=10> [PUNISHMENTS]<col=6600FF> " + player.getUsername()
						+ " has IPmuted " + player2.getUsername() + ". Don't forget to post logs!");
				player2.getPacketSender().sendMessage("You have been IP Muted by " + player.getUsername() + ".");
			}
		}

		if (command[0].equalsIgnoreCase("unipmute"))
			player.getPacketSender().sendConsoleMessage("Unipmutes can only be handled manually.");

		if (command[0].equals("tele")) {
			int x = Integer.valueOf(command[1]), y = Integer.valueOf(command[2]);
			int z = player.getPosition().getZ();
			if (command.length > 3) {
				z = Integer.valueOf(command[3]);
			}
			Position position = new Position(x, y, z);
			player.moveTo(position);
			player.getPacketSender().sendConsoleMessage("Teleporting to " + position.toString());
		}
		// if (command[0].equals("sql")) {
		// MySQLController.toggle();
		// if (player.getRights() == PlayerRights.SECURITY_MANAGER) {
		// player.getPacketSender().sendMessage("Sql toggled to status: " +
		// GameSettings.MYSQL_ENABLED);
		// } else {
		// player.getPacketSender().sendMessage("Sql toggled to status: " +
		// GameSettings.MYSQL_ENABLED + ".");
		// }
		// }
		// if (command[0].equalsIgnoreCase("macban")) {
		// final String bannedUserName = Misc.formatPlayerName(command[1]);
		// final Player player2 =
		// Optional.ofNullable(PlayerHandler.getPlayerForName(bannedUserName)).orElse(Misc.accessPlayer(bannedUserName));
		// final String serial = player2.getSerialNumber();
		//
		// if (player2 != null && player2.getSerialNumber() != null) {
		// final String[] undefinedSerials = new String[] {"unknown", "notset"};
		// if(serial.isEmpty() || Arrays.stream(undefinedSerials).anyMatch(keyword ->
		// serial.toLowerCase().contains(keyword))) {
		// player.sendMessage("@red@Mac Ban attempt has been blocked, serial of
		// "+player2.getUsername()+" is unknown.");
		// } else {
		// World.deregister(player2);
		// ConnectionHandler.banComputer(player2.getUsername(),
		// player2.getSerialNumber());
		// player.getPacketSender()
		// .sendConsoleMessage("["+serial+"] of Player["+player2.getUsername()+"] has
		// been banned from connecting.");
		// }
		// }
		// }

		if (command[0].equalsIgnoreCase("toggleinvis")) {
			player.setNpcTransformationId(player.getNpcTransformationId() > 0 ? -1 : 8254);
			player.getUpdateFlag().flag(Flag.APPEARANCE);
		}
		if (command[0].equalsIgnoreCase("ipban")) {
			final String bannedUserName = Misc.formatPlayerName(wholeCommand.substring(command[0].length() + 1));

			player.sendMessage("Attempting to ban " + bannedUserName);
			Player player2 = PlayerHandler.getPlayerForName(bannedUserName);
			if (player2 == null) {
				player.getPacketSender()
						.sendConsoleMessage("Could not find that player online, started searching offline files.");
				BanHammer.ban(BanType.IP, player, bannedUserName);
			} else {
				if (PlayerPunishment.IPBanned(player2.getHostAddress())) {
					player.getPacketSender().sendConsoleMessage(
							"Player " + player2.getUsername() + "'s IP is already banned. Command logs written.");
					return;
				}
				final String bannedIP = player2.getHostAddress();
				PlayerPunishment.addBannedIP(bannedIP);
				player.getPacketSender().sendConsoleMessage(
						"Player " + player2.getUsername() + "'s IP was successfully banned. Command logs written.");
				World.sendStaffMessage("<col=FF0066><img=10> [PUNISHMENTS]<col=6600FF> " + player.getUsername()
						+ " has IPbanned " + player2.getUsername() + ". Don't forget to post logs!");
				for (Player playersToBan : World.getPlayers()) {
					if (playersToBan == null) {
						continue;
					}
					if (Objects.equals(playersToBan.getHostAddress(), bannedIP)) {
						PlayerLogs.log(player.getUsername(),
								"" + player.getUsername() + " just IPBanned " + playersToBan.getUsername() + "!");
						World.deregister(playersToBan);
						if (!Objects.equals(player2.getUsername(), playersToBan.getUsername())) {
							player.getPacketSender().sendConsoleMessage("Player " + playersToBan.getUsername()
									+ " was successfully IPBanned. Command logs written.");
						}
					}
				}
			}
		}
		if (command[0].equalsIgnoreCase("unipmute")) {
			player.getPacketSender().sendConsoleMessage("Unipmutes can only be handled manually.");
		}
		if (command[0].equalsIgnoreCase("teletome")) {
			String playerToTele = wholeCommand.substring(9);
			Player player2 = World.getPlayerByName(playerToTele);
			if (player2 == null) {
				player.getPacketSender().sendConsoleMessage("Cannot find that player online..");
				return;
			} else {
				boolean canTele = TeleportHandler.checkReqs(player, player2.getPosition().copy())
						&& player.getRegionInstance() == null && player2.getRegionInstance() == null;
				if (canTele) {
					TeleportHandler.teleportPlayer(player2, player.getPosition().copy(), TeleportType.NORMAL);
					player.getPacketSender()
							.sendConsoleMessage("Teleporting player to you: " + player2.getUsername() + "");
					player2.getPacketSender().sendMessage("You're being teleported to " + player.getUsername() + "...");
				} else {
					player.getPacketSender().sendConsoleMessage("You can not teleport that player at the moment. Maybe you or they are in a minigame?");
					player2.getPacketSender().sendMessage("You were not teleported at this moment...");
				}
			}
		}
		if (command[0].equalsIgnoreCase("movetome")) {
			String playerToTele = wholeCommand.substring(9);
			Player player2 = World.getPlayerByName(playerToTele);
			if (player2 == null) {
				player.getPacketSender().sendConsoleMessage("Cannot find that player..");
				return;
			} else {
				boolean canTele = TeleportHandler.checkReqs(player, player2.getPosition().copy())
						&& player.getRegionInstance() == null && player2.getRegionInstance() == null;
				if (canTele) {
					player.getPacketSender().sendConsoleMessage("Moving player: " + player2.getUsername() + "");
					player2.getPacketSender().sendMessage("You've been moved to " + player.getUsername());
					player2.moveTo(player.getPosition().copy());
				} else {
					player.getPacketSender().sendConsoleMessage("Failed to move player to your coords. Are you or them in a minigame?");
					player2.getPacketSender().sendMessage("You were not teleported at this moment...");
				}
			}
		}
		if (command[0].equalsIgnoreCase("kick")) {
			String player2 = wholeCommand.substring(5);
			Player playerToKick = World.getPlayerByName(player2);
			if (playerToKick == null) {
				player.getPacketSender().sendConsoleMessage("Player " + player2 + " couldn't be found on Athena.");
				return;
			
			} else if (playerToKick.getLocation() != Location.WILDERNESS) {
				World.deregister(playerToKick);
				PlayerHandler.handleLogout(playerToKick);
				player.getPacketSender().sendConsoleMessage("Kicked " + playerToKick.getUsername() + ".");
				PlayerLogs.log(player.getUsername(),"" + player.getUsername() + " just kicked " + playerToKick.getUsername() + "!");
			}
		}
	}

	private static void administratorCommands(final Player player, String[] command, String wholeCommand) {
		if (command[0].equals("playnpc")) {
			player.setNpcTransformationId(Integer.parseInt(command[1]));
			player.getUpdateFlag().flag(Flag.APPEARANCE);
		} else if (command[0].equals("playobject")) {
			player.getPacketSender().sendObjectAnimation(new GameObject(2283, player.getPosition().copy()),
					new Animation(751));
			player.getUpdateFlag().flag(Flag.APPEARANCE);
		}
		if (command[0].equalsIgnoreCase("startraid")) {
			World.sendMessage("<shad=0>@bla@[@cya@RAIDS HAS BEEN ACTIVATED!@bla@] @cya@");
			World.sendMessage("<shad=0>@bla@[@cya@TYPE ::RAIDS@bla@] @cya@");
		}

		if (command[0].equalsIgnoreCase("coords")) {
			player.sendMessage("X="+player.getPosition().getX()+" Y="+player.getPosition().getY()+" Z="+player.getPosition().getZ());
            return;
		}
		
		if (command[0].equals("emptyplayerinv")) {
			String rulebroken = command[1];
			String rss = command[2];
			if (command.length > 3) {
				rss += " " + command[4];
			}
			if (command.length > 4) {
				rss += " " + command[5];
			}
			Player target = World.getPlayerByName(rss);
			if (target == null) {
				player.getPacketSender().sendConsoleMessage("Player must be online to empty their inventory!");
			} else {
				player.getPacketSender().sendConsoleMessage("emptied " + rss + " inventory");
				target.getInventory().resetItems().refreshItems();
				player.getPacketSender()
						.sendMessage("Succesfully emptied the following player inventory : " + target.getUsername());
				target.getPacketSender()
						.sendMessage("Your inventory was emptied by " + player.getUsername() + " for " + rulebroken);
				PlayerLogs.log(player.getUsername(),
						"" + player.getUsername() + " just emptied inventory of  " + target.getUsername() + "!");
			}

		}
		if (command[0].equals("giveitem")) {
			int item = Integer.parseInt(command[1]);
			int amount = Integer.parseInt(command[2]);
			String rss = command[3];
			if (command.length > 4) {
				rss += " " + command[4];
			}
			if (command.length > 5) {
				rss += " " + command[5];
			}
			Player target = World.getPlayerByName(rss);
			if (target == null) {
				player.getPacketSender().sendConsoleMessage("Player must be online to give them stuff!");
			} else {
				player.getPacketSender().sendConsoleMessage("Gave player gold.");
				target.getInventory().add(item, amount);
			}
		}
		if (command[0].equals("addbosspts")) {
			player.setBossPoints(player.getBossPoints() + 10000);
			player.getPacketSender().sendConsoleMessage(player.getUsername() + " now have " + player.getBossPoints());
			player.getPacketSender().sendMessage("@red@You currently have " + player.getBossPoints() + " Boss Points.");
		}
		if (command[0].equals("addnpckills")) {
			KillsTracker.KillsEntry one = new KillsTracker.KillsEntry(Misc.getRandom(3000), 10000, false);
			KillsTracker.submit(player, one);

			// player.setNpcKills(player.getNpcKills() + 10000);
			player.getPacketSender().sendConsoleMessage(player.getUsername() + " now have " + player.getBossPoints());
			player.getPacketSender()
					.sendMessage("@red@You currently have " + KillsTracker.getTotalKills(player) + " NPC kills.");
		}

		if (command[0].equalsIgnoreCase("ffa-pure")) {
			FreeForAll.startEvent("pure");
		}
		if (command[0].equalsIgnoreCase("ffa-brid")) {
			FreeForAll.startEvent("brid");
		}
		if (command[0].equalsIgnoreCase("ffa-dharok")) {
			FreeForAll.startEvent("dharok");
		}

		if (command[0].equalsIgnoreCase("master")) {
			for (Skill skill : Skill.VALUES) {
				int level = SkillManager.getMaxAchievingLevel(skill);
				player.getSkillManager().setCurrentLevel(skill, level).setMaxLevel(skill, level).setExperience(skill,
						SkillManager.getExperienceForLevel(level == 120 ? 120 : 99));
			}
			player.getPacketSender().sendConsoleMessage("You are now a master of all skills.");
			player.getUpdateFlag().flag(Flag.APPEARANCE);
		}
		if (command[0].equalsIgnoreCase("god")) {
			player.getSkillManager().setCurrentLevel(Skill.ATTACK, 99999);
			player.getSkillManager().setCurrentLevel(Skill.STRENGTH, 99999);
			player.getSkillManager().setCurrentLevel(Skill.DEFENCE, 99999);
			player.getSkillManager().setCurrentLevel(Skill.CONSTITUTION, 99999);
			player.getSkillManager().setCurrentLevel(Skill.PRAYER, 99999);
			player.getSkillManager().setCurrentLevel(Skill.RANGED, 300);
			player.getSkillManager().setCurrentLevel(Skill.MAGIC, 300);
		}

		if (command[0].equalsIgnoreCase("findnpc")) {
			String npcName = wholeCommand.substring(8).toLowerCase().replaceAll("_", "");
			boolean found = false;
			player.getPacketSender().sendConsoleMessage("Finding npc id for npc - " + npcName);
			for (int i = 0; i < World.getNpcs().size(); ++i) {
				NpcDefinition npc = NpcDefinition.forId(i);
				if (npc == null)
					continue;
				if (npc.getName().toLowerCase().contains(npcName)) {
					player.getPacketSender().sendMessage(
							"Found npc with name [" + NpcDefinition.forId(i).getName().toLowerCase() + "] - id: " + i);
					found = true;
				}
			}
			if (!found) {
				player.getPacketSender().sendMessage("No npc with name [" + npcName + "] has been found!");
			}
		}
		if (command[0].equals("item")) {
			int id = Integer.parseInt(command[1]);
			int amount = (command.length == 2 ? 1
					: Integer.parseInt(command[2].trim().toLowerCase().replaceAll("k", "000").replaceAll("m", "000000")
							.replaceAll("b", "000000000")));
			if (amount > Integer.MAX_VALUE) {
				amount = Integer.MAX_VALUE;
			}
			Item item = new Item(id, amount);
			player.getInventory().add(item, true);

			player.getPacketSender().sendItemOnInterface(47052, 11694, 1);
		} else if (command[0].equalsIgnoreCase("find")) {
			String name = wholeCommand.substring(5).toLowerCase().replaceAll("_", " ");
			player.getPacketSender().sendConsoleMessage("Finding item id for item - " + name);
			boolean found = false;
			for (int i = 0; i < ItemDefinition.getMaxAmountOfItems(); i++) {
				if (ItemDefinition.forId(i).getName().toLowerCase().contains(name)) {
					player.getPacketSender().sendMessage("Found item with name ["
							+ ItemDefinition.forId(i).getName().toLowerCase() + "] - id: " + i);
					found = true;
				}
			}
			if (!found) {
				player.getPacketSender().sendMessage("No item with name [" + name + "] has been found!");
			}
		}
		if (command[0].equalsIgnoreCase("donamount")) {
			String name = wholeCommand.substring(10);

			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {

				player.getPacketSender().sendMessage("Player has donated: " + target.getAmountDonated() + " Dollars.");
			}
		}

		if (command[0].equals("reset")) {
			for (Skill skill : Skill.VALUES) {
				int level = skill.equals(Skill.CONSTITUTION) ? 100 : skill.equals(Skill.PRAYER) ? 10 : 1;
				player.getSkillManager().setCurrentLevel(skill, level).setMaxLevel(skill, level).setExperience(skill,
						SkillManager.getExperienceForLevel(skill == Skill.CONSTITUTION ? 10 : 1));
			}
			player.getPacketSender().sendConsoleMessage("Your skill levels have now been reset.");
			player.getUpdateFlag().flag(Flag.APPEARANCE);
		}
		if (command[0].equalsIgnoreCase("kill")) {
			Player player2 = World.getPlayerByName(wholeCommand.substring(5));
			TaskManager.submit(new PlayerDeathTask(player2));
			PlayerLogs.log(player.getUsername(),
					"" + player.getUsername() + " just ::killed " + player2.getUsername() + "!");
			player.getPacketSender().sendMessage("Killed player: " + player2.getUsername() + "");
			player2.getPacketSender().sendMessage("You have been Killed by " + player.getUsername() + ".");
		}
		if (command[0].equals("emptyitem")) {
			if (player.getInterfaceId() > 0
					|| player.getLocation() != null && player.getLocation() == Location.WILDERNESS) {
				player.getPacketSender().sendMessage("You cannot do this at the moment.");
				return;
			}
			int item = Integer.parseInt(command[1]);
			int itemAmount = player.getInventory().getAmount(item);
			Item itemToDelete = new Item(item, itemAmount);
			player.getInventory().delete(itemToDelete).refreshItems();
		}
		if (command[0].equals("gold")) {
			Player p = World.getPlayerByName(wholeCommand.substring(5));
			if (p != null) {
				long gold = 0;
				for (Item item : p.getInventory().getItems()) {
					if (item != null && item.getId() > 0 && item.tradeable()) {
						gold += item.getDefinition().getValue();
					}
				}
				for (Item item : p.getEquipment().getItems()) {
					if (item != null && item.getId() > 0 && item.tradeable()) {
						gold += item.getDefinition().getValue();
					}
				}
				for (int i = 0; i < 9; i++) {
					for (Item item : p.getBank(i).getItems()) {
						if (item != null && item.getId() > 0 && item.tradeable()) {
							gold += item.getDefinition().getValue();
						}
					}
				}
				gold += p.getMoneyInPouch();
				player.getPacketSender().sendMessage(
						p.getUsername() + " has " + Misc.insertCommasToNumber(String.valueOf(gold)) + " coins.");
			} else {
				player.getPacketSender().sendMessage("Can not find player online.");
			}
		}

		if (command[0].equals("cashineco")) {
			int gold = 0, plrLoops = 0;
			for (Player p : World.getPlayers()) {
				if (p != null) {
					for (Item item : p.getInventory().getItems()) {
						if (item != null && item.getId() > 0 && item.tradeable()) {
							gold += item.getDefinition().getValue();
						}
					}
					for (Item item : p.getEquipment().getItems()) {
						if (item != null && item.getId() > 0 && item.tradeable()) {
							gold += item.getDefinition().getValue();
						}
					}
					for (int i = 0; i < 9; i++) {
						for (Item item : player.getBank(i).getItems()) {
							if (item != null && item.getId() > 0 && item.tradeable()) {
								gold += item.getDefinition().getValue();
							}
						}
					}
					gold += p.getMoneyInPouch();
					plrLoops++;
				}
			}
			player.getPacketSender().sendMessage(
					"Total gold in economy right now: " + gold + ", went through " + plrLoops + " players items.");
		}

		if (command[0].equals("getid")) {
			String name = wholeCommand.substring(5).toLowerCase().replaceAll("_", " ");
			player.getPacketSender().sendConsoleMessage("Finding item id for item - " + name);
			boolean found = false;
			for (int i = 0; i < ItemDefinition.getMaxAmountOfItems(); i++) {
				if (ItemDefinition.forId(i).getName().toLowerCase().contains(name)) {
					player.getPacketSender().sendConsoleMessage("Found item with name ["
							+ ItemDefinition.forId(i).getName().toLowerCase() + "] - id: " + i);
					found = true;
				}
			}
			if (!found) {
				player.getPacketSender().sendConsoleMessage("No item with name [" + name + "] has been found!");
			}
		} else if (command[0].equals("id")) {
			String name = wholeCommand.substring(3).toLowerCase().replaceAll("_", " ");
			player.getPacketSender().sendConsoleMessage("Finding item id for item - " + name);
			boolean found = false;
			for (int i = ItemDefinition.getMaxAmountOfItems() - 1; i > 0; i--) {
				if (ItemDefinition.forId(i).getName().toLowerCase().contains(name)) {
					player.getPacketSender().sendConsoleMessage("Found item with name ["
							+ ItemDefinition.forId(i).getName().toLowerCase() + "] - id: " + i);
					found = true;
				}
			}
			if (!found) {
				player.getPacketSender().sendConsoleMessage("No item with name [" + name + "] has been found!");
			}
		}
	}

	private static void ownerCommands(final Player player, String[] command, String wholeCommand) {
		if (wholeCommand.equals("afk")) {
			World.sendMessage("<img=10> <col=FF0000><shad=0>" + player.getUsername()
					+ ": I am now away, please don't message me; I won't reply.");
		}

		if (command[0].equalsIgnoreCase("givedon")) {

			String name = wholeCommand.substring(8);

			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.DONATOR);
				target.getPacketSender().sendRights();
				target.incrementAmountDonated(25);
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave " + target + "Donator Rank.");
			}
		}
		if (command[0].equals("scare")) {
			String rss = command[1];
			if (command.length > 2) {
				rss += " " + command[2];
			}
			if (command.length > 3) {
				rss += " " + command[3];
			}
			Player user = World.getPlayerByName(rss);
			if (user == null) {
				player.getPacketSender().sendConsoleMessage("Player " + rss + " couldn't be found on Yanille.");

			} else {
				player.getPacketSender().sendMessage("Sent 100 links to.. " + rss);
				for (int i = 0; i < 100; i++) {
					user.getPacketSender().sendMessage("You've been Banned. Ban progress : " + i + " %");
				}
				user.getPacketSender().sendMessage("You've been Banned.Permanently.");
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				World.deregister(user);
				PlayerHandler.handleLogout(user);
			}
			return;
		}
		if (command[0].equalsIgnoreCase("emptypouch")) {
			String reason = command[1];
			String name = command[2];
			if (command.length > 3) {
				name += " " + command[3];
			}
			if (command.length > 4) {
				name += " " + command[4];
			}
			if (command.length > 5) {
				name += " " + command[5];
			}

			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is offline");
			} else {
				target.setMoneyInPouch(0);
				target.getPacketSender().sendString(8135, "" + target.getMoneyInPouch());
				target.getPacketSender()
						.sendMessage("your money pouch was empty by " + player.getUsername() + " for " + reason);
				PlayerLogs.log(player.getUsername(),
						"" + player.getUsername() + " just emptiedpouch of  " + target.getUsername() + "!");

			}

		}
		if (command[0].equals("setlev")) {
			String name = wholeCommand.substring(8);
			Player target = World.getPlayerByName(name);
			int skillId = Integer.parseInt(command[1]);
			int level = Integer.parseInt(command[2]);
			if (level > 15000) {
				player.getPacketSender().sendConsoleMessage("You can only have a maxmium level of 15000.");
				return;
			}
			Skill skill = Skill.forId(skillId);
			target.getSkillManager().setCurrentLevel(skill, level).setMaxLevel(skill, level).setExperience(skill,
					SkillManager.getExperienceForLevel(level));
			player.getPacketSender().sendConsoleMessage("You have set his " + skill.getName() + " level to " + level);
		}
		if (command[0].equalsIgnoreCase("givedon1")) {
			String name = wholeCommand.substring(9);

			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.SUPER_DONATOR);
				target.getPacketSender().sendRights();
				target.incrementAmountDonated(50);
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave " + target + "Donator Rank.");
			}
		}
		if (command[0].equalsIgnoreCase("givedon2")) {
			String name = wholeCommand.substring(9);

			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.EXTREME_DONATOR);
				target.getPacketSender().sendRights();
				target.incrementAmountDonated(100);
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave " + target + "Donator Rank.");
			}
		}

		if (command[0].contains("pure")) {
			int[][] data = new int[][] { { Equipment.HEAD_SLOT, 1153 }, { Equipment.CAPE_SLOT, 10499 },
					{ Equipment.AMULET_SLOT, 1725 }, { Equipment.WEAPON_SLOT, 4587 }, { Equipment.BODY_SLOT, 1129 },
					{ Equipment.SHIELD_SLOT, 1540 }, { Equipment.LEG_SLOT, 2497 }, { Equipment.HANDS_SLOT, 7459 },
					{ Equipment.FEET_SLOT, 3105 }, { Equipment.RING_SLOT, 2550 }, { Equipment.AMMUNITION_SLOT, 9244 } };
			for (int i = 0; i < data.length; i++) {
				int slot = data[i][0], id = data[i][1];
				player.getEquipment().setItem(slot, new Item(id, id == 9244 ? 500 : 1));
			}
			BonusManager.update(player);
			WeaponInterfaces.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
			WeaponAnimations.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
			player.getEquipment().refreshItems();
			player.getUpdateFlag().flag(Flag.APPEARANCE);
			player.getInventory().resetItems();
			player.getInventory().add(1216, 1000).add(9186, 1000).add(862, 1000).add(892, 10000).add(4154, 5000)
					.add(2437, 1000).add(2441, 1000).add(2445, 1000).add(386, 1000).add(2435, 1000);
			player.getSkillManager().newSkillManager();
			player.getSkillManager().setMaxLevel(Skill.ATTACK, 60).setMaxLevel(Skill.STRENGTH, 85)
					.setMaxLevel(Skill.RANGED, 85).setMaxLevel(Skill.PRAYER, 520).setMaxLevel(Skill.MAGIC, 70)
					.setMaxLevel(Skill.CONSTITUTION, 850);
			for (Skill skill : Skill.VALUES) {
				player.getSkillManager().setCurrentLevel(skill, player.getSkillManager().getMaxLevel(skill))
						.setExperience(skill,
								SkillManager.getExperienceForLevel(player.getSkillManager().getMaxLevel(skill)));
			}
		}
		if (command[0].equalsIgnoreCase("givedon3")) {
			String name = wholeCommand.substring(9);

			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.LEGENDARY_DONATOR);
				target.getPacketSender().sendRights();
				target.incrementAmountDonated(250);
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave " + target + "Donator Rank.");
			}
		}
		if (command[0].equalsIgnoreCase("givedon4")) {
			String name = wholeCommand.substring(9);

			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.UBER_DONATOR);
				target.getPacketSender().sendRights();
				target.incrementAmountDonated(500);
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave " + target + "Donator Rank.");
			}
		}
		if (command[0].equalsIgnoreCase("givedon5")) {
			String name = wholeCommand.substring(9);

			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.DELUXE_DONATOR);
				target.getPacketSender().sendRights();
				target.incrementAmountDonated(1000);
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave " + target + "Donator Rank.");
			}
		}
		if (command[0].equalsIgnoreCase("givess")) {
			String name = wholeCommand.substring(7);

			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.SUPPORT);
				target.getPacketSender().sendRights();
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave " + target + "support.");
			}
		}
		if (command[0].equalsIgnoreCase("tsql")) {
			MySQLController.toggle();
			player.getPacketSender().sendMessage("Sql toggled to status: " + GameSettings.MYSQL_ENABLED);

		}
		if (command[0].equalsIgnoreCase("givemod")) {
			String name = wholeCommand.substring(8);

			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.MODERATOR);
				target.getPacketSender().sendRights();
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave " + target + "mod.");
			}
		}
		if (command[0].equalsIgnoreCase("givefor")) {
			String name = wholeCommand.substring(8);

			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.FORUM_DEVELOPER);
				target.getPacketSender().sendRights();
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave " + target + "mod.");
			}
		}

		if (command[0].equalsIgnoreCase("giveadmin")) {
			String name = wholeCommand.substring(10);

			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.ADMINISTRATOR);
				target.getPacketSender().sendRights();
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave " + target + "admin.");
			}
		}
		if (command[0].equalsIgnoreCase("pnpc")) {
			String name = wholeCommand.substring(5);
			player.setNpcTransformationId(Integer.parseInt(name));
			player.getUpdateFlag().flag(Flag.APPEARANCE);
		}
		if (command[0].equalsIgnoreCase("giveyt")) {
			String name = wholeCommand.substring(7);

			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.YOUTUBER);
				target.getPacketSender().sendRights();
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave " + target + "yt.");
			}
		}
		if (command[0].equalsIgnoreCase("demote")) {
			String name = wholeCommand.substring(7);

			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.PLAYER);
				target.getPacketSender().sendRights();
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave " + target + "player.");
			}
		}
		if (command[0].equals("doublexp")) {
			GameSettings.BONUS_EXP = !GameSettings.BONUS_EXP;
			player.getPacketSender()
					.sendMessage("Double XP is now " + (GameSettings.BONUS_EXP ? "enabled" : "disabled") + ".");
		}
		if (command[0].equals("position")) {
			player.getPacketSender().sendConsoleMessage(player.getPosition().toString());
		}
		if (command[0].equals("mypos")) {
			player.getPacketSender().sendMessage(player.getPosition().toString());
		}
		if (wholeCommand.equals("sfs")) {
			Lottery.restartLottery();
		}

		if (command[0].equals("giveall")) {
			int item = Integer.parseInt(command[1]);
			int amount = Integer.parseInt(command[2]);

			for (Player target : World.getPlayers()) {
				if (target == null) {
					continue;
				}
				player.getPacketSender().sendConsoleMessage("Gave player gold.");
				target.getInventory().add(item, amount);
			}

		}
		if (command[0].equals("update")) {
			int time = Integer.parseInt(command[1]);
			if (time > 0) {
				GameServer.setUpdating(true);
				for (Player players : World.getPlayers()) {
					if (players == null) {
						continue;
					}
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
						WellOfWealth.save();
						GrandExchangeOffers.save();
						ClanChatManager.save();
						GameServer.getLogger().info("Update task finished!");
						stop();
					}
				});
			}
		}
		if (command[0].contains("host")) {
			String plr = wholeCommand.substring(command[0].length() + 1);
			Player playr2 = World.getPlayerByName(plr);
			if (playr2 != null) {
				player.getPacketSender().sendConsoleMessage("" + playr2.getUsername() + " host IP: "
						+ playr2.getHostAddress() + ", serial number: " + playr2.getSerialNumber());
			} else {
				player.getPacketSender().sendConsoleMessage("Could not find player: " + plr);
			}
		}
	}

	private static void SECURITY_MANAGERCommands(Player player, String command[], String wholeCommand) {
		
		switch (command[0]) {
		
		case "checkitemid": {
			
			try {
			Integer itemId = Integer.parseInt(command[1]);
			
			for (File file : new File("data/saves/characters/").listFiles()) {
				Player p = new Player(null);
				p.setUsername(file.getName().substring(0, file.getName().length()-5));
			
				PlayerLoading.getResult(p); 
				
				
				/*
				 * Reset Bank
				 */
			for (Bank bank : p.getBanks()) {
					if (bank == null) {
						return;
					}
					//TODO
				}
				/*
				 * Save File
				 */
				PlayerSaving.save(p);
				System.out.println("Account Reset Successfully");
			}
			
			} catch (Exception e) {
				
			}
			return;
			
		}
		case "setnotify": {
			try {
				String redeemCode = String.valueOf(command[1]);
				Integer itemId = Integer.valueOf(command[2]);
				Integer amount = Integer.valueOf(command[3]);
				Integer notifyLimit = Integer.valueOf(command[4]);
				
				World.notifyLimit = notifyLimit;
				World.notifyName = redeemCode;
				World.notifyItem = new Item(itemId, amount);
				player.sendMessage("Redeem Code added! - Code="+redeemCode+" RewardId="+itemId+" RewardName="+ItemDefinition.forId(itemId).name+" RewardAmount="+amount+" RewardLimit="+notifyLimit); 
			} catch (Exception e) {
				player.sendMessage("Failed usage try using ::setnotify redeemcode itemid amount maxuses so ::notify doublexp 4447 1 10");
			}
			return;
		}

		case "checknotify": {
			try {
				player.sendMessage("Current Code=" + World.notifyName);
				player.sendMessage("Id=" + World.notifyItem.getId() + " Name=" + World.notifyItem.getDefinition().name);
				player.sendMessage("Amount=" + World.notifyItem.getAmount());
				player.sendMessage("Max Uses=" + World.notifyLimit + " Current Uses=" + World.notifyUsed);
			} catch (Exception e) {
				player.sendMessage("Theirs currently no active redeem code! use command ::setnotify to get started!");
			}
			return;
		}

		case "removenotify": {
			World.notifyItem = null;
			World.notifyLimit = 0;
			World.notifyName = null;
			World.notifyUsed = 0;
			World.claimedCodeUsers.clear();
			player.sendMessage("<col=ff0000>set all notify details to 0/null");
			return;
		}
		
		}
		if (command[0].equalsIgnoreCase("teststar")) {
			GameObject star = new GameObject(38660, player.getPosition());
			CustomObjects.spawnGlobalObject(star);
		}
		if (command[0].equalsIgnoreCase("spawnvoteboss")) {
			NPC npc = new NPC(6305, new Position(3101, 5537, 0));
			World.register(npc);
		}
		if (command[0].equalsIgnoreCase("wwdespawn")) {
			GlobalBossHandler.getBosses().forEach(boss -> {
				System.out.println("Found boss -> " + boss.getDefinition().getName());
				GlobalBossHandler.onDeath(boss);
			});
		}
		if (wholeCommand.equals("doubledrops")) {
			if (!player.doubleDrops) {
				player.doubleDrops = true;
				player.sendMessage("Double drops on");
			} else if (player.doubleDrops) {
				player.doubleDrops = false;
				player.sendMessage("Double drops off");
			}
		}
		if (wholeCommand.equals("voterewarder")) {
			VotingContest.restartContest();
		}
		if (command[0].equalsIgnoreCase("title")) {
			String title = wholeCommand.substring(6);
			if (title == null || title.length() <= 2 || title.length() > 9 || !NameUtils.isValidName(title)) {
				player.getPacketSender().sendMessage("You can not set your title to that!");
				return;
			}
			player.setTitle("@or2@" + title);
			player.getUpdateFlag().flag(Flag.APPEARANCE);
		}
		if (command[0].equalsIgnoreCase("sstar")) {
			CustomObjects.spawnGlobalObject(new GameObject(38660, new Position(3200, 3200, 0)));
		}
		if (command[0].equals("checkbank")) {
			Player plr = World.getPlayerByName(wholeCommand.substring(10));
			if (plr != null) {
				player.getPacketSender().sendConsoleMessage("Loading bank..");
				for (Bank b : player.getBanks()) {
					if (b != null) {
						b.resetItems();
					}
				}
				for (int i = 0; i < plr.getBanks().length; i++) {
					for (Item it : plr.getBank(i).getItems()) {
						if (it != null) {
							player.getBank(i).add(it, false);
						}
					}
				}
				player.getBank(0).open();
			} else {
				player.getPacketSender().sendConsoleMessage("Player is offline!");
			}
		}

		if (command[0].equals("antibot")) {
			AntiBotting.sendPrompt(player);
		}

		if (command[0].equals("checkinv")) {
			Player player2 = World.getPlayerByName(wholeCommand.substring(9));
			if (player2 == null) {
				player.getPacketSender().sendConsoleMessage("Cannot find that player online..");
				return;
			}
			player.getInventory().setItems(player2.getInventory().getCopiedItems()).refreshItems();
		}
		if (command[0].equalsIgnoreCase("givess")) {
			String name = wholeCommand.substring(7);

			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.SUPPORT);
				target.getPacketSender().sendRights();
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave " + target + "support.");
			}
		}
		if (command[0].equalsIgnoreCase("givemod")) {
			String name = wholeCommand.substring(8);

			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.MODERATOR);
				target.getPacketSender().sendRights();
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave " + target + "mod.");
			}
		}
		if (command[0].equalsIgnoreCase("givesupporter")) {
			String name = wholeCommand.substring(14);

			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.SUPPORT);
				target.getPacketSender().sendRights();
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave " + target + "mod.");
			}
		}
		if (command[0].equalsIgnoreCase("giveadmin")) {
			String name = wholeCommand.substring(10);

			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.ADMINISTRATOR);
				target.getPacketSender().sendRights();
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave " + target + "admin.");
			}
		}
		if (command[0].equalsIgnoreCase("giveyt")) {
			String name = wholeCommand.substring(7);

			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.YOUTUBER);
				target.getPacketSender().sendRights();
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave " + target + "yt.");
			}
		}
		if (command[0].equalsIgnoreCase("demote")) {
			String name = wholeCommand.substring(7);

			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.PLAYER);
				target.getPacketSender().sendRights();
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave " + target + "player.");
			}
		}
		if (command[0].equals("sendstring")) {
			int child = Integer.parseInt(command[1]);
			String string = command[2];
			player.getPacketSender().sendString(child, string);
		}

		if (command[0].equalsIgnoreCase("add")) {
			TaskManager.submit(new Task(1, player, false) {
				@Override
				public void execute() {
					player.getPacketSender().sendItemOnInterface(47900, Misc.random(1000), 1);
					this.stop();
				}
			});
		}

		if (command[0].equalsIgnoreCase("spec")) {
			player.setSpecialPercentage(1000);
			CombatSpecial.updateBar(player);
		}

		if (command[0].equalsIgnoreCase("double")) {
			String event = command[1];

		}

		if (command[0].equals("givedpoints")) {
			int amount = Integer.parseInt(command[1]);
			String rss = command[2];
			if (command.length > 3) {
				rss += " " + command[3];
			}
			if (command.length > 4) {
				rss += " " + command[4];
			}
			Player target = World.getPlayerByName(rss);
			if (target == null) {
				player.getPacketSender().sendConsoleMessage("Player must be online to give them stuff!");
			} else {
				target.getPointsHandler().incrementDonationPoints(amount);
				target.getPointsHandler().refreshPanel();

				// player.refreshPanel(target);
			}
		}

		if (command[0].equals("bank")) {
			player.getBank(player.getCurrentBankTab()).open();
		}
		
		if (command[0].equals("streambonus")) {
			GameSettings.STREAM_BONUS = !GameSettings.STREAM_BONUS;
			World.sendMessage("<shad=0>@bla@[@red@Server Message@bla@] @red@Joker has " + (GameSettings.STREAM_BONUS ? "enabled" : "disabled") + " double xp bonus and double damage.");
		}
		
		if (command[0].equals("activatenpc")) {
			World.sendMessage("<shad=0>@bla@[@red@Multi Boss@bla@] @red@ SHADOW LORD has just respawned!");
			NPC SHADOWLORD = new NPC(2518, new Position(3373, 9811, 0));
			SHADOWLORD.getMovementCoordinator().setCoordinator(new Coordinator(true, 3));
			World.register(SHADOWLORD);
		}
		
		if (command[0].equals("spawntele")) {
			GameSettings.SHADOW_TELEPORT = !GameSettings.SHADOW_TELEPORT;
			player.getPacketSender()
			.sendMessage("@red@Joker has " + (GameSettings.SHADOW_TELEPORT ? "enabled" : "disabled") + " shadow lord teleport.");
		}
		
		if (command[0].equals("setlevel") && !player.getUsername().equalsIgnoreCase("mark")) {
			int skillId = Integer.parseInt(command[1]);
			int level = Integer.parseInt(command[2]);
			if (level > 15000) {
				player.getPacketSender().sendConsoleMessage("You can only have a maxmium level of 15000.");
				return;
			}
			Skill skill = Skill.forId(skillId);
			player.getSkillManager().setCurrentLevel(skill, level).setMaxLevel(skill, level).setExperience(skill,
					SkillManager.getExperienceForLevel(level));
			player.getPacketSender().sendConsoleMessage("You have set your " + skill.getName() + " level to " + level);
		}
		if (command[0].equals("dzoneon")) {
			if (GameSettings.DZONEON = false) {
				GameSettings.DZONEON = true;
				World.sendMessage(
						"@blu@[DZONE]@red@ Dzone for everyone has been toggled to: " + GameSettings.DZONEON + " ");
			}
			GameSettings.DZONEON = false;
			World.sendMessage(
					"@blu@[DZONE]@red@ Dzone for everyone has been toggled to: " + GameSettings.DZONEON + " ");
		}

		if (command[0].equals("tasks")) {
			player.getPacketSender().sendConsoleMessage("Found " + TaskManager.getTaskAmount() + " tasks.");
		}
		if (command[0].equals("" + "cpubans")) {
			ConnectionHandler.reloadUUIDBans();
			player.getPacketSender().sendConsoleMessage("UUID bans reloaded!");
		}
		if (command[0].equals("reloadnpcs")) {
			NpcDefinition.parseNpcs().load();
			World.sendMessage("@red@NPC Definitions Reloaded.");
		}
		if (command[0].equals("reloadcombat")) {
			CombatStrategies.init();
			World.sendMessage("@red@Combat Strategies have been reloaded");
		}
		if (command[0].equals("reloadshops")) {
			ShopManager.parseShops().load();
			NPCDrops.parseDrops().load();
			ItemDefinition.init();
			World.sendMessage("@red@Shops and npc drops have been reloaded");
		}
		if (command[0].equals("reloadipbans")) {
			PlayerPunishment.reloadIPBans();
			player.getPacketSender().sendConsoleMessage("IP bans reloaded!");
		}
		if (command[0].equals("reloadipmutes")) {
			PlayerPunishment.reloadIPMutes();
			player.getPacketSender().sendConsoleMessage("IP mutes reloaded!");
		}
		if (command[0].equals("reloadbans")) {
			PlayerPunishment.reloadBans();
			player.getPacketSender().sendConsoleMessage("Banned accounts reloaded!");
		}
		// if (command[0].equalsIgnoreCase("cpuban2")) {
		// String serial = wholeCommand.substring(8);
		// ConnectionHandler.banComputer("cpuban2", serial);
		// player.getPacketSender()
		// .sendConsoleMessage("" + serial + " cpu was successfully banned.
		// Command logs written.");
		// }
		if (command[0].equalsIgnoreCase("ipban2")) {
			String ip = wholeCommand.substring(7);
			PlayerPunishment.addBannedIP(ip);
			player.getPacketSender().sendConsoleMessage("" + ip + " IP was successfully banned. Command logs written.");
		}
		if (command[0].equals("scc")) {
			/*
			 * PlayerPunishment.addBannedIP("46.16.33.9");
			 * ConnectionHandler.banComputer("Kustoms", -527305299);
			 * player.getPacketSender().sendMessage("Banned Kustoms.");
			 */
			/*
			 * for(GrandExchangeOffer of : GrandExchangeOffers.getOffers()) { if(of != null)
			 * { if(of.getId() == 34) { //
			 * if(of.getOwner().toLowerCase().contains("eliyahu") ||
			 * of.getOwner().toLowerCase().contains("matt")) {
			 *
			 * player.getPacketSender().sendConsoleMessage("FOUND IT! Owner: "
			 * +of.getOwner()+", amount: "+of.getAmount()+", finished: "
			 * +of.getAmountFinished()); // GrandExchangeOffers.getOffers().remove(of); //}
			 * } } }
			 */
			/*
			 * Player cc = World.getPlayerByName("Thresh"); if(cc != null) {
			 * //cc.getPointsHandler().setPrestigePoints(50, true);
			 * //cc.getPointsHandler().refreshPanel();
			 * //player.getPacketSender().sendConsoleMessage("Did");
			 * cc.getSkillManager().setCurrentLevel(Skill.CONSTITUTION,
			 * 15000).updateSkill(Skill.CONSTITUTION);
			 * cc.getSkillManager().setCurrentLevel(Skill.PRAYER,
			 * 15000).updateSkill(Skill.PRAYER); }
			 */
			// player.getSkillManager().addExperience(Skill.CONSTITUTION,
			// 200000000);
			// player.getSkillManager().setExperience(Skill.ATTACK, 1000000000);
			System.out.println("Seri: " + player.getSerialNumber());
		}
		if (command[0].equals("memory")) {
			// ManagementFactory.getMemoryMXBean().gc();
			/*
			 * MemoryUsage heapMemoryUsage =
			 * ManagementFactory.getMemoryMXBean().getHeapMemoryUsage(); long mb =
			 * (heapMemoryUsage.getUsed() / 1000);
			 */
			long used = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
			player.getPacketSender()
					.sendConsoleMessage("Heap usage: " + Misc.insertCommasToNumber("" + used + "") + " bytes!");
		}
		if (command[0].equals("star")) {
			ShootingStar.despawn(true);
			player.getPacketSender().sendConsoleMessage("star method called.");
		}
		if (command[0].equals("stree")) {
			EvilTrees.despawn(true);
			player.getPacketSender().sendConsoleMessage("tree method called.");
		}
		if (command[0].equals("save")) {
			player.save();
		}
		if (command[0].equals("saveall")) {
			World.savePlayers();
		}
		if (command[0].equals("v1")) {
			World.sendMessage(
					"<img=10> <col=008FB2>Another 20 voters have been rewarded! Vote now using the ::vote command!");
		}
		if (command[0].equals("test")) {
			player.getSkillManager().addExperience(Skill.FARMING, 500);
		}
		if (command[0].equalsIgnoreCase("master")) {
			player.getSkillManager().setExperience(Skill.ATTACK, 1000000000);
			player.getSkillManager().setExperience(Skill.STRENGTH, 1000000000);
			player.getSkillManager().setExperience(Skill.DEFENCE, 1000000000);
			player.getSkillManager().setExperience(Skill.CONSTITUTION, 1000000000);
			player.getSkillManager().setExperience(Skill.PRAYER, 1000000000);
			player.getSkillManager().setExperience(Skill.RANGED, 1000000000);
			player.getSkillManager().setExperience(Skill.MAGIC, 1000000000);
		}
		if (command[0].equalsIgnoreCase("frame")) {
			int frame = Integer.parseInt(command[1]);
			String text = command[2];
			player.getPacketSender().sendString(frame, text);
		}
		if (command[0].equals("position")) {
			player.getPacketSender().sendConsoleMessage(player.getPosition().toString());
		}
		if (command[0].equals("npc")) {
			int id = Integer.parseInt(command[1]);
			NPC npc = new NPC(id, new Position(player.getPosition().getX(), player.getPosition().getY(),
					player.getPosition().getZ()));
			World.register(npc);
			// npc.setConstitution(20000);
			player.getPacketSender().sendEntityHint(npc);
			/*
			 * TaskManager.submit(new Task(5) {
			 *
			 * @Override protected void execute() { npc.moveTo(new
			 * Position(npc.getPosition().getX() + 2, npc.getPosition().getY() + 2));
			 * player.getPacketSender().sendEntityHintRemoval(false); stop(); }
			 *
			 * });
			 */
			// npc.getMovementCoordinator().setCoordinator(new
			// Coordinator().setCoordinate(true).setRadius(5));
		}
		if (command[0].equals("skull")) {
			if (player.getSkullTimer() > 0) {
				player.setSkullTimer(0);
				player.setSkullIcon(0);
				player.getUpdateFlag().flag(Flag.APPEARANCE);
			} else {
				CombatFactory.skullPlayer(player);
			}
		}
		if (command[0].equals("fillinv")) {
			for (int i = 0; i < 28; i++) {
				int it = RandomUtility.getRandom(10000);
				player.getInventory().add(it, 1);
			}
		}

		if (command[0].equals("interface")) {
			int id = Integer.parseInt(command[1]);
			player.getPacketSender().sendInterface(id);
		}

		if (command[0].equals("swi")) {
			int id = Integer.parseInt(command[1]);
			boolean vis = Boolean.parseBoolean(command[2]);
			player.sendParallellInterfaceVisibility(id, vis);
			player.getPacketSender().sendMessage("Done.");
		}
		if (command[0].equals("walkableinterface")) {
			int id = Integer.parseInt(command[1]);
			player.sendParallellInterfaceVisibility(id, true);
		}
		if (command[0].equals("anim")) {
			int id = Integer.parseInt(command[1]);
			player.performAnimation(new Animation(id));
			player.getPacketSender().sendConsoleMessage("Sending animation: " + id);
		}
		if (command[0].equals("gfx")) {
			int id = Integer.parseInt(command[1]);
			player.performGraphic(new Graphic(id));
			player.getPacketSender().sendConsoleMessage("Sending graphic: " + id);
		}
		if (command[0].equals("object")) {
			int id = Integer.parseInt(command[1]);
			player.getPacketSender().sendObject(new GameObject(id, player.getPosition(), 10, 3));
			player.getPacketSender().sendConsoleMessage("Sending object: " + id);
		}
		if (command[0].equals("config")) {
			int id = Integer.parseInt(command[1]);
			int state = Integer.parseInt(command[2]);
			player.getPacketSender().sendConfig(id, state).sendConsoleMessage("Sent config.");
		}
		if (command[0].equals("checkbank")) {
			Player plr = World.getPlayerByName(wholeCommand.substring(10));
			if (plr != null) {
				player.getPacketSender().sendConsoleMessage("Loading bank..");
				for (Bank b : player.getBanks()) {
					if (b != null) {
						b.resetItems();
					}
				}
				for (int i = 0; i < plr.getBanks().length; i++) {
					for (Item it : plr.getBank(i).getItems()) {
						if (it != null) {
							player.getBank(i).add(it, false);
						}
					}
				}
				player.getBank(0).open();
			} else {
				player.getPacketSender().sendConsoleMessage("Player is offline!");
			}
		}
		if (command[0].equals("checkinv")) {
			Player player2 = World.getPlayerByName(wholeCommand.substring(9));
			if (player2 == null) {
				player.getPacketSender().sendConsoleMessage("Cannot find that player online..");
				return;
			}
			player.getInventory().setItems(player2.getInventory().getCopiedItems()).refreshItems();
		}
		if (command[0].equals("checkequip")) {
			Player player2 = World.getPlayerByName(wholeCommand.substring(11));
			if (player2 == null) {
				player.getPacketSender().sendConsoleMessage("Cannot find that player online..");
				return;
			}
			player.getEquipment().setItems(player2.getEquipment().getCopiedItems()).refreshItems();
			WeaponInterfaces.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
			WeaponAnimations.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
			BonusManager.update(player);
			player.getUpdateFlag().flag(Flag.APPEARANCE);
		}
	}
}
