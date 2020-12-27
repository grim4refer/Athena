package com.athena.net.packet.impl;

import com.athena.GameSettings;
import com.athena.model.PlayerRights;
import com.athena.model.Position;
import com.athena.model.Locations.Location;
import com.athena.model.container.impl.Bank;
import com.athena.model.container.impl.Bank.BankSearchAttributes;
import com.athena.model.definitions.NPCDrops;
import com.athena.model.definitions.WeaponInterfaces.WeaponInterface;
import com.athena.model.input.impl.*;
import com.athena.net.packet.Packet;
import com.athena.net.packet.PacketListener;
import com.athena.world.content.*;
import com.athena.util.Misc;
import com.athena.world.World;
import com.athena.world.content.TeleportInterface.Bosses;
import com.athena.world.content.TeleportInterface.Monsters;
import com.athena.world.content.clan.ClanChat;
import com.athena.world.content.clan.ClanChatManager;
import com.athena.world.content.combat.magic.Autocasting;
import com.athena.world.content.combat.magic.MagicSpells;
import com.athena.world.content.combat.prayer.CurseHandler;
import com.athena.world.content.combat.prayer.PrayerHandler;
import com.athena.world.content.combat.weapon.CombatSpecial;
import com.athena.world.content.combat.weapon.FightType;
import com.athena.world.content.dialogue.DialogueManager;
import com.athena.world.content.dialogue.DialogueOptions;
import com.athena.world.content.StarterTasks;
import com.athena.world.content.droppreview.AVATAR;
import com.athena.world.content.droppreview.BARRELS;
import com.athena.world.content.droppreview.BORKS;
import com.athena.world.content.droppreview.CERB;
import com.athena.world.content.droppreview.CORP;
import com.athena.world.content.droppreview.DAGS;
import com.athena.world.content.droppreview.GLAC;
import com.athena.world.content.droppreview.GWD;
import com.athena.world.content.droppreview.KALPH;
import com.athena.world.content.droppreview.KBD;
import com.athena.world.content.droppreview.LIZARD;
import com.athena.world.content.droppreview.NEXX;
import com.athena.world.content.droppreview.PHEON;
import com.athena.world.content.droppreview.SKOT;
import com.athena.world.content.droppreview.SLASHBASH;
import com.athena.world.content.droppreview.TDS;
import com.athena.world.content.grandexchange.GrandExchange;
import com.athena.world.content.minigames.impl.Dueling;
import com.athena.world.content.minigames.impl.Nomad;
import com.athena.world.content.minigames.impl.PestControl;
import com.athena.world.content.minigames.impl.RecipeForDisaster;
import com.athena.world.content.mysteryboxes.*;
import com.athena.world.content.skill.ChatboxInterfaceSkillAction;
import com.athena.world.content.skill.impl.construction.Construction;
import com.athena.world.content.skill.impl.crafting.LeatherMaking;
import com.athena.world.content.skill.impl.crafting.Tanning;
import com.athena.world.content.skill.impl.dungeoneering.Dungeoneering;
import com.athena.world.content.skill.impl.dungeoneering.DungeoneeringParty;
import com.athena.world.content.skill.impl.fletching.Fletching;
import com.athena.world.content.skill.impl.herblore.IngridientsBook;
import com.athena.world.content.skill.impl.slayer.Slayer;
import com.athena.world.content.skill.impl.smithing.SmithingData;
import com.athena.world.content.skill.impl.summoning.PouchMaking;
import com.athena.world.content.skill.impl.summoning.SummoningTab;
import com.athena.world.content.teleportation.Teleporting;
import com.athena.world.content.transportation.TeleportHandler;
import com.athena.world.content.upgrade.Upgrade;
import com.athena.world.entity.impl.player.Player;
import com.athena.world.SlotMachineRewards;

import java.util.List;

/**
 * This packet listener manages a button that the player has clicked upon.
 *
 * @author Gabriel Hannason
 */

public class ButtonClickPacketListener implements PacketListener {

    @Override
    public void handleMessage(Player player, Packet packet) {

        int id = packet.readShort();

        if (player.getRights() == PlayerRights.OWNER) {
            player.getPacketSender().sendMessage("Clicked button: " + id);
           
        }

        if (checkHandlers(player, id))
            return;

        if (id >= 32623 && id <= 32722) {
            player.getPlayerOwnedShopManager().handleButton(id);
        }
        
        if(id == 29037) {
        	SlotMachine.roll(player);
        }

        if(id == 29054) {
        	SlotMachine.openInterface(player);
        }

        if(id == 29041) {
        	SlotMachineRewards.open(player);
        }

        if (id >= 32410 && id <= 32460) {
            StaffList.handleButton(player, id);
        }
        if( id >= -27885 && id <= -27719) {

            int base_button = -27885;
            int modified_button = (base_button - id);
            int index = Math.abs(modified_button);

            List<Integer> key = player.getAttribute("DROP_KEY");
            if(!player.getClickDelay().elapsed(600)) {
                return;
            }
            player.getClickDelay().reset();
            if (key == null || key.isEmpty()) {
             DropLookup.resetInterface(player);
                return;
            }
            if(index >= key.size()) {
             DropLookup.resetInterface(player);
                return;
            }
            DropLookup.display(player, NPCDrops.forId(key.get(index)));
        }
        switch (id) {
		case -15024:
			player.getTeleportInterface().handleTeleports();
			break;
		case -15333:
			player.getPacketSender().sendString(1,
					"http://yanille.net/forums/index.php?/topic/24-yanille-rules/");
			break;

		case -15313:
			player.getPacketSender().sendString(1, "http://yanille.net/forums/index.php?/topic/26-starter-guide/");
			break;

		case -15293:
			player.getPacketSender().sendString(1,
					"http://yanille.net/forums/index.php?/topic/32-melee-gear-guide/");
			break;

		case -15332:
			player.getPacketSender().sendString(1,
					"http://yanille.net/forums/index.php?/topic/33-range-gear-guide/");
			break;

		case -15312:
			player.getPacketSender().sendString(1,
					"http://yanille.net/forums/index.php?/topic/34-mage-gear-guide/");
			break;

		case -15292:
			player.getPacketSender().sendString(1, "http://yanille.net/forums/index.php?/topic/27-drops-guide/");
			break;

		case -15331:
			player.getPacketSender().sendString(1,
					"http://yanille.net/forums/index.php?/topic/19-drop-rate-boost-in-progress/");
			break;

		case -15311:
			player.getPacketSender().sendString(1,
					"http://yanille.net/forums/index.php?/topic/38-price-guides/");
			break;

		case -15291:
			player.getPacketSender().sendString(1,
					"http://yanille.net/forums/index.php?/topic/29-clue-scroll-guide/");
			break;

		case -15330:
			player.getPacketSender().sendString(1,
					"http://yanille.net/forums/index.php?/topic/37-herblore-guide/");
			break;

		case -15310:
			player.getPacketSender().sendString(1,
					"http://yanille.net/forums/index.php?/topic/36-farming-guide/");
			break;

		case -15290:
			player.getPacketSender().sendString(1,
					"http://yanille.net/forums/index.php?/topic/35-woodcutting-guide/");
			break;

		case -15329:
			player.getPacketSender().sendString(1,
					"");
			break;

		case -15309:
			player.getPacketSender().sendString(1, "");
			break;

		case -15289:
			player.getPacketSender().sendString(1,
					"");
			break;

		case -15328:
			player.getPacketSender().sendString(1,
					"");
			break;

		case -15308:
			player.getPacketSender().sendString(1,
					"");
			break;

		case -15288:
			player.getPacketSender().sendString(1, "");
			break;
			
		case -15327:
			player.getPacketSender().sendString(1, "");
			break;
			
		case -15307:
			player.getPacketSender().sendString(1, "");
			break;
			
		case -15287:
			player.getPacketSender().sendString(1, "");
			break;
            case -27928:
                player.setInputHandling(new NpcSearch());
                player.getPacketSender().sendEnterInputPrompt("Enter an Npc name, or part of it.");
                break;
    		case -12307:
    			if (!StarterTasks.claimReward(player)) {
    				player.sendMessage("@red@You cannot claim the reward untill all tasks are complete.");
    				return;
    			}
    			player.sendMessage("Enjoy your reward");
    			break;
            case -18529:

                if (player.getBox() == 6199) {
                    int amount = player.getInventory().getAmount(6199);
                    if (amount >= 1) {
                        MysteryBox mysteryBox = new MysteryBox(player);
                        mysteryBox.process();
                    }
                }
                if (player.getBox() == 20870) {
                    int amount = player.getInventory().getAmount(20870);
                    if (amount >= 1) {
                        YanilleMysteryBox mysteryBox = new YanilleMysteryBox(player);
                        mysteryBox.process();
                    }
                }
                if (player.getBox() == 20931) {
                    int amount = player.getInventory().getAmount(20931);
                    if (amount >= 1) {
                        UltraMysteryBox mysteryBox = new UltraMysteryBox(player);
                        mysteryBox.process();
                    }
                }
                if (player.getBox() == 7102) {
                    int amount = player.getInventory().getAmount(7102);
                    if (amount >= 1) {
                        YodaBox mysteryBox = new YodaBox(player);
                        mysteryBox.process();
                    }
                }
                if (player.getBox() == 7104) {
                    int amount = player.getInventory().getAmount(7104);
                    if (amount >= 1) {
                        GodzillaBox mysteryBox = new GodzillaBox(player);
                        mysteryBox.process();
                    }
                }
                if (player.getBox() == 7106) {
                    int amount = player.getInventory().getAmount(7106);
                    if (amount >= 1) {
                        AssasinBox mysteryBox = new AssasinBox(player);
                        mysteryBox.process();
                    }
                }
                if (player.getBox() == 7108) {
                    int amount = player.getInventory().getAmount(7108);
                    if (amount >= 1) {
                        DonkeyBox mysteryBox = new DonkeyBox(player);
                        mysteryBox.process();
                    }
                }
                if (player.getBox() == 7110) {
                    int amount = player.getInventory().getAmount(7110);
                    if (amount >= 1) {
                        OblivionBox mysteryBox = new OblivionBox(player);
                        mysteryBox.process();
                    }
                }
                if (player.getBox() == 7112) {
                    int amount = player.getInventory().getAmount(7112);
                    if (amount >= 1) {
                        BandosBox mysteryBox = new BandosBox(player);
                        mysteryBox.process();
                    }
                }
                if (player.getBox() == 7114) {
                    int amount = player.getInventory().getAmount(7114);
                    if (amount >= 1) {
                        AbbadonBox mysteryBox = new AbbadonBox(player);
                        mysteryBox.process();
                    }
                }
                if (player.getBox() == 7116) {
                    int amount = player.getInventory().getAmount(7116);
                    if (amount >= 1) {
                        InfernalGroudonBox mysteryBox = new InfernalGroudonBox(player);
                        mysteryBox.process();
                    }
                }
                if (player.getBox() == 7118) {
                    int amount = player.getInventory().getAmount(7118);
                    if (amount >= 1) {
                        DonatorBox mysteryBox = new DonatorBox(player);
                        mysteryBox.process();
                    }
                }
                if (player.getBox() == 7120) {
                    int amount = player.getInventory().getAmount(7120);
                    if (amount >= 1) {
                        NeocortexBox mysteryBox = new NeocortexBox(player);
                        mysteryBox.process();
                    }
                }
                if (player.getBox() == 7124) {
                    int amount = player.getInventory().getAmount(7124);
                    if (amount >= 1) {
                        BaphometBox mysteryBox = new BaphometBox(player);
                        mysteryBox.process();
                    }
                }
                if (player.getBox() == 7122) {
                    int amount = player.getInventory().getAmount(7122);
                    if (amount >= 1) {
                        spiderBox mysteryBox = new spiderBox(player);
                        mysteryBox.process();
                    }
                }
                break;


            //all    !!@#$@$@
            case -18524:
                if (player.getBox() == 6199) {
                    int amount = player.getInventory().getAmount(6199);
                    for (int i = 0; i < amount; i++) {
                        if (player.getInventory().getFreeSlots() == 0) {
                            player.getPacketSender().sendMessage("You need more inventory spaces!");
                            return;
                        }
                        MysteryBox mysteryBox = new MysteryBox(player);
                        mysteryBox.process();
                    }

                }
                if (player.getBox() == 20870) {
                    int amount = player.getInventory().getAmount(20870);
                    for (int i = 0; i < amount; i++) {
                        if (player.getInventory().getFreeSlots() == 0) {
                            player.getPacketSender().sendMessage("You need more inventory spaces!");
                            return;
                        }
                        YanilleMysteryBox mysteryBox = new YanilleMysteryBox(player);
                        mysteryBox.process();
                    }

                }
                if (player.getBox() == 20931) {
                    int amount = player.getInventory().getAmount(20931);
                    for (int i = 0; i < amount; i++) {
                        if (player.getInventory().getFreeSlots() == 0) {
                            player.getPacketSender().sendMessage("You need more inventory spaces!");
                            return;
                        }
                        UltraMysteryBox mysteryBox = new UltraMysteryBox(player);
                        mysteryBox.process();
                    }

                }
                if (player.getBox() == 7102) {
                    int amount = player.getInventory().getAmount(7102);
                    for (int i = 0; i < amount; i++) {
                        if (player.getInventory().getFreeSlots() == 0) {
                            player.getPacketSender().sendMessage("You need more inventory spaces!");
                            return;
                        }
                        YodaBox mysteryBox = new YodaBox(player);
                        mysteryBox.process();
                    }

                }
                if (player.getBox() == 7104) {
                    int amount = player.getInventory().getAmount(7104);
                    for (int i = 0; i < amount; i++) {
                        if (player.getInventory().getFreeSlots() == 0) {
                            player.getPacketSender().sendMessage("You need more inventory spaces!");
                            return;
                        }
                        GodzillaBox mysteryBox = new GodzillaBox(player);
                        mysteryBox.process();
                    }
                }
                if (player.getBox() == 7106) {
                    int amount = player.getInventory().getAmount(7106);
                    for (int i = 0; i < amount; i++) {
                        if (player.getInventory().getFreeSlots() == 0) {
                            player.getPacketSender().sendMessage("You need more inventory spaces!");
                            return;
                        }
                        AssasinBox mysteryBox = new AssasinBox(player);
                        mysteryBox.process();
                    }
                }
                if (player.getBox() == 7108) {
                    int amount = player.getInventory().getAmount(7108);
                    for (int i = 0; i < amount; i++) {
                        if (player.getInventory().getFreeSlots() == 0) {
                            player.getPacketSender().sendMessage("You need more inventory spaces!");
                            return;
                        }
                        DonkeyBox mysteryBox = new DonkeyBox(player);
                        mysteryBox.process();
                    }
                }
                if (player.getBox() == 7110) {
                    int amount = player.getInventory().getAmount(7110);
                    for (int i = 0; i < amount; i++) {
                        if (player.getInventory().getFreeSlots() == 0) {
                            player.getPacketSender().sendMessage("You need more inventory spaces!");
                            return;
                        }
                        OblivionBox mysteryBox = new OblivionBox(player);
                        mysteryBox.process();
                    }
                }
                if (player.getBox() == 7122) {

                    int amount = player.getInventory().getAmount(7122);
                    for (int i = 0; i < amount; i++) {
                        if (player.getInventory().getFreeSlots() == 0) {
                            player.getPacketSender().sendMessage("You need more inventory spaces!");
                            return;
                        }
                        spiderBox mysteryBox = new spiderBox(player);
                        mysteryBox.process();
                    }
                }
                if (player.getBox() == 7120) {
                    int amount = player.getInventory().getAmount(7120);
                    for (int i = 0; i < amount; i++) {
                        if (player.getInventory().getFreeSlots() == 0) {
                            player.getPacketSender().sendMessage("You need more inventory spaces!");
                            return;
                        }
                        NeocortexBox mysterybox = new NeocortexBox(player);
                        mysterybox.process();
                    }
                }
                if (player.getBox() == 7112) {
                    int amount = player.getInventory().getAmount(7112);
                    for (int i = 0; i < amount; i++) {
                        if (player.getInventory().getFreeSlots() == 0) {
                            player.getPacketSender().sendMessage("You need more inventory spaces!");
                            return;
                        }
                        BandosBox mysteryBox = new BandosBox(player);
                        mysteryBox.process();
                    }
                }
                if (player.getBox() == 7114) {
                    int amount = player.getInventory().getAmount(7114);
                    for (int i = 0; i < amount; i++) {
                        if (player.getInventory().getFreeSlots() == 0) {
                            player.getPacketSender().sendMessage("You need more inventory spaces!");
                            return;
                        }
                        AbbadonBox mysteryBox = new AbbadonBox(player);
                        mysteryBox.process();
                    }
                }
                if (player.getBox() == 7116) {
                    int amount = player.getInventory().getAmount(7116);
                    for (int i = 0; i < amount; i++) {
                        if (player.getInventory().getFreeSlots() == 0) {
                            player.getPacketSender().sendMessage("You need more inventory spaces!");
                            return;
                        }
                        InfernalGroudonBox mysteryBox = new InfernalGroudonBox(player);
                        mysteryBox.process();
                    }
                }
                if (player.getBox() == 7118) {
                    int amount = player.getInventory().getAmount(7118);
                    for (int i = 0; i < amount; i++) {
                        if (player.getInventory().getFreeSlots() == 0) {
                            player.getPacketSender().sendMessage("You need more inventory spaces!");
                            return;
                        }
                        DonatorBox mysteryBox = new DonatorBox(player);
                        mysteryBox.process();
                    }
                }
                if (player.getBox() == 7124) {
                    int amount = player.getInventory().getAmount(7124);
                    for (int i = 0; i < amount; i++) {
                        if (player.getInventory().getFreeSlots() == 0) {
                            player.getPacketSender().sendMessage("You need more inventory spaces!");
                            return;
                        }
                        BaphometBox mysteryBox = new BaphometBox(player);
                        mysteryBox.process();
                    }
                }


                break;
                
        	case 26250:
    		case 27229:
    			DungeoneeringParty.create(player);
    			for (Bank b : player.getBanks()) {
    				if (b.contains(15707)) {
    					player.getPacketSender().sendMessage("You already have a Ring of Kinship in your bank.");
    					return;
    				}
    			}
    			if (player.getInventory().contains(15707)) {
    				player.getPacketSender().sendMessage("Use your Ring of Kinship to invite players!");
    				return;
    			} else {
    				player.getInventory().add(15707, 1);
    				player.getPacketSender().sendMessage("You can use your Ring of Kinship to invite others to your party!");
    			}
    			break;
    		case 26226:
    		case 26229:
    			if(Dungeoneering.doingDungeoneering(player)) {
    				DialogueManager.start(player, 114);
    				player.setDialogueActionId(71);
    			} else {
    				Dungeoneering.leave(player, false, true);
    			}
    			break;
    		case 26244:
    		case 26247:
    			if(player.getMinigameAttributes().getDungeoneeringAttributes().getParty() != null) {
    				if(player.getMinigameAttributes().getDungeoneeringAttributes().getParty().getOwner().getUsername().equals(player.getUsername())) {
    					DialogueManager.start(player, id == 26247 ? 106 : 105);
    					player.setDialogueActionId(id == 26247 ? 68 : 67);
    				} else {
    					player.getPacketSender().sendMessage("Only the party owner can change this setting.");
    				}
    			}
    			break;
    			
    		case -15033:
    			player.getTeleportInterface().sendBossTab();
    			break;

    		case -15034:
    			player.getTeleportInterface().sendMonsterTab();
    			break;

    		case -15032:
    			player.getTeleportInterface().sendMinigamesTab();
    			break;

    		case -15031:
    			player.getTeleportInterface().sendSkillingTab();
    			break;

    		case -15029:
    			player.getTeleportInterface().sendWildyTab();
    			break;

    		case -15030:
    			player.getTeleportInterface().sendCitiesTab();
    			break;
            case 26113:
                player.dropLogOrder = !player.dropLogOrder;
                if (player.dropLogOrder) {
                    player.getPA().sendFrame126(26113, "Oldest to Newest");
                } else {
                    player.getPA().sendFrame126(26113, "Newest to Oldest");
                }
                break;
            case -29031:
                ProfileViewing.rate(player, true);
                break;
            case -29028:
                ProfileViewing.rate(player, false);
                break;
            case 29009:
                Upgrade.doUpgrade(player);
                break;
            case -17611:
            case -17613:
                player.sendMessage("@red@Coming soon!");
                break;
            case -27454:
            case -27534:
            case -18519:
            case -17606:
            case 5384:
            case 12729:
            case -12286:
            case -15086:
                player.getPacketSender().sendInterfaceRemoval();
                break;
            case -17631:
                KBD.closeInterface(player);
                break;

//            case -11438:
//                player.getPlayerOwnedShopManager().openEditor();
//                break;

            case -17629:
                if (player.getLocation() == Location.KING_BLACK_DRAGON) {
                    KBD.nextItem(player);
                }
                if (player.getLocation() == Location.SLASH_BASH) {
                    SLASHBASH.nextItem(player);
                }
                if (player.getLocation() == Location.TORM_DEMONS) {
                    TDS.nextItem(player);
                }
                if (player.getLocation() == Location.CORPOREAL_BEAST) {
                    CORP.nextItem(player);
                }
                if (player.getLocation() == Location.DAGANNOTH_DUNGEON) {
                    DAGS.nextItem(player);
                }
                if (player.getLocation() == Location.BANDOS_AVATAR) {
                    AVATAR.nextItem(player);
                }
                if (player.getLocation() == Location.KALPHITE_QUEEN) {
                    KALPH.nextItem(player);
                }
                if (player.getLocation() == Location.PHOENIX) {
                    PHEON.nextItem(player);
                }
                if (player.getLocation() == Location.GLACORS) {
                    GLAC.nextItem(player);
                }
                if (player.getLocation() == Location.SKOTIZO) {
                    SKOT.nextItem(player);
                }
                if (player.getLocation() == Location.CERBERUS) {
                    CERB.nextItem(player);
                }
                if (player.getLocation() == Location.NEX) {
                    NEXX.nextItem(player);
                }
                if (player.getLocation() == Location.GODWARS_DUNGEON) {
                    GWD.nextItem(player);
                }
                if (player.getLocation() == Location.BORK) {
                    BORKS.nextItem(player);
                }
                if (player.getLocation() == Location.LIZARDMAN) {
                    LIZARD.nextItem(player);
                }
                if (player.getLocation() == Location.BARRELCHESTS) {
                    BARRELS.nextItem(player);
                }
                break;

            case -17630:
                if (player.getLocation() == Location.KING_BLACK_DRAGON) {
                    KBD.previousItem(player);
                }
                if (player.getLocation() == Location.SLASH_BASH) {
                    SLASHBASH.previousItem(player);
                }
                if (player.getLocation() == Location.TORM_DEMONS) {
                    TDS.previousItem(player);
                }
                if (player.getLocation() == Location.CORPOREAL_BEAST) {
                    CORP.previousItem(player);
                }
                if (player.getLocation() == Location.DAGANNOTH_DUNGEON) {
                    DAGS.previousItem(player);
                }
                if (player.getLocation() == Location.BANDOS_AVATAR) {
                    AVATAR.previousItem(player);
                }
                if (player.getLocation() == Location.KALPHITE_QUEEN) {
                    KALPH.previousItem(player);
                }
                if (player.getLocation() == Location.PHOENIX) {
                    PHEON.previousItem(player);
                }
                if (player.getLocation() == Location.GLACORS) {
                    GLAC.previousItem(player);
                }
                if (player.getLocation() == Location.SKOTIZO) {
                    SKOT.previousItem(player);
                }
                if (player.getLocation() == Location.CERBERUS) {
                    CERB.previousItem(player);
                }
                if (player.getLocation() == Location.NEX) {
                    NEXX.previousItem(player);
                }
                if (player.getLocation() == Location.GODWARS_DUNGEON) {
                    GWD.previousItem(player);
                }
                if (player.getLocation() == Location.BORK) {
                    BORKS.previousItem(player);
                }
                if (player.getLocation() == Location.LIZARDMAN) {
                    LIZARD.previousItem(player);
                }
                if (player.getLocation() == Location.BARRELCHESTS) {
                    BARRELS.previousItem(player);
                }
                break;

//		case -26373:
//			DropLog.open(player);
//			break;
            case 1036:
                EnergyHandler.rest(player);
                break;
//		case -26376:
            // PlayersOnlineInterface.showInterface(player);
//			break;
            case 26601:
                player.getPacketSender().sendTabInterface(GameSettings.QUESTS_TAB, 46343);
                StaffList.updateInterface(player);//staff online button
                break;
            case 32388:
                player.getPacketSender().sendTabInterface(GameSettings.QUESTS_TAB, 639); // 26600
                break;
            case -26359:
                player.setExperienceLocked(!player.experienceLocked());
                player.sendMessage("Your experience is now: " + (player.experienceLocked() ? "locked" : "unlocked"));
                break;
            case 3229:
                player.sendMessage("Pet Box Costs 1250 Yanille Points.");
                break;
            case 3218:
                player.sendMessage("Uncommon Package Costs 100 Yanille Points.");
                break;
            case 3215:
                player.sendMessage("Extreme Package Costs 200 Yanille Points.");
                break;
            case 3221:
                player.sendMessage("Rare Package Costs 150 Yanille Points.");
                break;
            case 3235:
                player.sendMessage("Donor Box Costs 2500 Yanille Points.");
                break;
            case 3204:
                if (player.getArcticPSPoints() >= 150) {
                    player.getInventory().add(15371, 1);
                    player.incrementArcticPSPoints(150);
                    PlayerPanel.refreshPanel(player);
                }
                break;
            case 3206:
                if (player.getArcticPSPoints() >= 200) {
                    player.getInventory().add(15372, 1);
                    player.incrementArcticPSPoints(200);
                    PlayerPanel.refreshPanel(player);
                }
                break;
            case 3260:
                player.getPacketSender().sendString(1, "www.yanille.net");
                player.getPacketSender().sendMessage("Attempting to open: www.yanille.net/forums");
                break;
            case 3208:

                if (player.getArcticPSPoints() >= 100) {
                    player.getInventory().add(15369, 1);
                    player.incrementArcticPSPoints(100);
                    PlayerPanel.refreshPanel(player);
                }
                break;
            case 3225:
                if (player.getArcticPSPoints() >= 1250) {
                    player.getInventory().add(19934, 1);
                    player.incrementArcticPSPoints(1250);
                    PlayerPanel.refreshPanel(player);
                }
                break;
            case 3240:
                if (player.getArcticPSPoints() >= 250) {
                    player.getInventory().add(7118, 1);
                    player.incrementArcticPSPoints(2500);
                    PlayerPanel.refreshPanel(player);
                }
                break;
            case 28180:
                player.getPacketSender().sendInterfaceRemoval();
                if (player.getSummoning().getFamiliar() != null) {
                    player.getPacketSender()
                            .sendMessage("You must dismiss your familiar before being allowed to enter a dungeon.");
                    player.getPacketSender().sendMessage("You must dismiss your familiar before joining the dungeon");
                    return;
                }

                TeleportHandler.teleportPlayer(player, new Position(3450, 3715), player.getSpellbook().getTeleportType());
                break;
            case 14176:
                player.setUntradeableDropItem(null);
                player.getPacketSender().sendInterfaceRemoval();
                break;
            case 1013:
                player.getSkillManager().setTotalGainedExp(0);
                break;
            case 391:
                if (WellOfGoodwill.isActive()) {
                    player.getPacketSender().sendMessage(
                            "<img=10> <col=008FB2>The Well of Goodwill is granting 30% bonus experience for another "
                                    + WellOfGoodwill.getMinutesRemaining() + " minutes.");
                } else {
                    player.getPacketSender()
                            .sendMessage("<img=10> <col=008FB2>The Well of Goodwill needs another "
                                    + Misc.insertCommasToNumber("" + WellOfGoodwill.getMissingAmount())
                                    + " coins before becoming full.");
                }
                break;
            case -26345:
                if(!player.getClickDelay().elapsed(600)) {
                    return;
                }
                player.getClickDelay().reset();
                DropLookup.open(player);
                break;
            case 26604:
                KillsTracker.open(player);
                break;//kill log tracker quest tab
            case -26346:
                DropLog.open(player);
                break;

            case -30281:
                player.getPacketSender().sendInterfaceRemoval();
                break;
            case 26614:
                DropLog.open(player);
                break;//quest tab drop log

            case -10531:
                if (player.isKillsTrackerOpen()) {
                    player.setKillsTrackerOpen(false);
                    player.getPacketSender().sendTabInterface(GameSettings.QUESTS_TAB, 639);
                    PlayerPanel.refreshPanel(player);
                }
                break;

            case 28177:
            	 TeleportHandler.teleportPlayer(player, new Position(3109, 3508, 0), player.getSpellbook().getTeleportType());
                if (!TeleportHandler.checkReqs(player, null)) {
                    player.getSkillManager().stopSkilling();
                    return;
                }
                if (!player.getClickDelay().elapsed(4500) || player.getMovementQueue().isLockMovement()) {
                    player.getSkillManager().stopSkilling();
                    return;
                }
                if (player.getLocation() == Location.CONSTRUCTION) {
                    player.getSkillManager().stopSkilling();
                    return;
                }
                Construction.newHouse(player);
                Construction.enterHouse(player, player, true, true);
                player.getSkillManager().stopSkilling();
                break;
            case -30282:
                KillsTracker.openBoss(player);
                break;
            case -10283:
                KillsTracker.open(player);
                break;

//		case -26333:
//			player.getPacketSender().sendString(1, "www.runeunity.org/forum");
//			player.getPacketSender().sendMessage("Attempting to open: www.runeunity.org/forum");
//			break;
            case -26330:
                player.getPacketSender().sendString(1, "www.yanille.net/forums");
                player.getPacketSender().sendMessage("Attempting to open: www.yanille.net/forums");
                break;
            case -26329:
                player.getPacketSender().sendString(1, "www.yanille.net");
                player.getPacketSender().sendMessage("Attempting to open: www.yanille.net");
                break;
            case -26344:
                player.setTimer(0);
                break;
            case -26328:
                player.getPacketSender().sendString(1, "www.yanille.net/forums");
                player.getPacketSender().sendMessage("Attempting to open: www.yanille.net/forums");
                break;
            case -26331:
                RecipeForDisaster.openQuestLog(player);
                break;
            case -26332:
                Nomad.openQuestLog(player);
                break;
            case 26611:
                ProfileViewing.view(player, player);
                break;//quest tab player planel
            case 350:
                player.getPacketSender()
                        .sendMessage("To autocast a spell, please right-click it and choose the autocast option.")
                        .sendTab(GameSettings.MAGIC_TAB).sendConfig(108, player.getAutocastSpell() == null ? 3 : 1);
                break;
            case 12162:
                DialogueManager.start(player, 61);
                player.setDialogueActionId(28);
                break;
            case 29335:
                if (player.getInterfaceId() > 0) {
                    player.getPacketSender()
                            .sendMessage("Please close the interface you have open before opening another one.");
                    return;
                }
                DialogueManager.start(player, 60);
                player.setDialogueActionId(27);
                break;
            case 29455:
                if (player.getInterfaceId() > 0) {
                    player.getPacketSender()
                            .sendMessage("Please close the interface you have open before opening another one.");
                    return;
                }
                ClanChatManager.toggleLootShare(player);
                break;
            case 8658:
                DialogueManager.start(player, 55);
                player.setDialogueActionId(26);
                break;
            case 11001:
                TeleportHandler.teleportPlayer(player, GameSettings.DEFAULT_POSITION.copy(),
                        player.getSpellbook().getTeleportType());
        		player.getPacketSender().sendMessage("@red@Note: @mag@THE UPGRADE MACHINE CAN UPGRADE YOUR ITEMS!");
                break;
            case 8667:
                TeleportHandler.teleportPlayer(player, new Position(2742, 3443), player.getSpellbook().getTeleportType());
                break;
            case 8672:
                TeleportHandler.teleportPlayer(player, new Position(2595, 4772), player.getSpellbook().getTeleportType());
                player.getPacketSender().sendMessage(
                        "<img=10> To get started with Runecrafting, buy a talisman and use the locate option on it.");
                break;
            case 15481:
                TeleportHandler.teleportPlayer(player, new Position(1917, 5117), player.getSpellbook().getTeleportType());
                break;
            case 8861:
                TeleportHandler.teleportPlayer(player, new Position(2914, 3450), player.getSpellbook().getTeleportType());
                break;
            case 8656:
                player.setDialogueActionId(47);
                DialogueManager.start(player, 86);
                break;
            case 8659:
                TeleportHandler.teleportPlayer(player, new Position(3024, 9741), player.getSpellbook().getTeleportType());
                break;
            case 8664:
                TeleportHandler.teleportPlayer(player, new Position(2521, 2501, 1), player.getSpellbook().getTeleportType());
                break;
            case 8666:
                TeleportHandler.teleportPlayer(player, new Position(2534, 2500, 1), player.getSpellbook().getTeleportType());
                break;


            /*
             * Teleporting Called Below
             */

            case -4914:
            case -4911:
            case -4908:
            case -4905:
            case -4899:
            case -4896:
            case -4893:
            case -4890:
            case -4845:
            case -4839:
            case -4842:
                Teleporting.teleport(player, id);
                break;

            case -4902:
                //if (player.getSummoning().getFamiliar() != null) {
                //    player.getPacketSender().sendMessage("You must dismiss your familiar before teleporting to the arena!");
               // } else {
                    Teleporting.teleport(player, id);
                //}
                break;

            case 10003:
            	player.getTeleportInterface().sendMonsterTab();
            	player.clickindex = 0;
            	player.currentTabs = 1;
            	Bosses bossData = Bosses.values()[player.clickindex];
            	Monsters monsterData = Monsters.values()[player.clickindex];
				//currentClickIndex = index;
            	player.getTeleportInterface().sendMonsterData(monsterData);

				
				player.getTeleportInterface().sendDrops(1265);
                //Teleporting.openTab(player, -4934);
                break;
            case -4934:
            	
                Teleporting.openTab(player, -4934);
                break;
            case -4931:
                Teleporting.openTab(player, -4931);
                break;
            case -4928:
                Teleporting.openTab(player, -4928);
                break;
            case -4925:
                Teleporting.openTab(player, -4925);
                break;
            case -4922:
                Teleporting.openTab(player, -4922);
                break;
            case -4919:
                Teleporting.openTab(player, -4919);
                break;

            /*
             * End Teleporting
             */
			
		/*case -4845:
			WeaponGame.addToLobby(player);
			player.getPacketSender().sendInterfaceRemoval();
			player.getPacketSender().sendMessage("Weapon Game is currently under development!");

			break;*/

            case 8671:
                player.setDialogueActionId(56);
                DialogueManager.start(player, 89);
                break;
            case 8670:
                TeleportHandler.teleportPlayer(player, new Position(2717, 3499), player.getSpellbook().getTeleportType());
                break;
            case 8668:
                TeleportHandler.teleportPlayer(player, new Position(2709, 3437), player.getSpellbook().getTeleportType());
                break;
            case 8665:
                TeleportHandler.teleportPlayer(player, new Position(3079, 3495), player.getSpellbook().getTeleportType());
                break;
            case 8662:
                TeleportHandler.teleportPlayer(player, new Position(2345, 3698), player.getSpellbook().getTeleportType());
                break;
            case 13928:
                TeleportHandler.teleportPlayer(player, new Position(3052, 3304), player.getSpellbook().getTeleportType());
                break;
            case 28179:
                TeleportHandler.teleportPlayer(player, new Position(2209, 5348), player.getSpellbook().getTeleportType());
                break;
            case 28178:
                DialogueManager.start(player, 54);
                player.setDialogueActionId(25);
                break;
            case 1159: // Bones to Bananas
            case 15877:// Bones to peaches
            case 30306:
                MagicSpells.handleMagicSpells(player, id);
                break;
            case 10001:
                if (player.getInterfaceId() == -1) {
                    Consumables.handleHealAction(player);
                } else {
                    player.getPacketSender().sendMessage("You cannot heal yourself right now.");
                }
                break;
            case 18025:
                if (PrayerHandler.isActivated(player, PrayerHandler.AUGURY)) {
                    PrayerHandler.deactivatePrayer(player, PrayerHandler.AUGURY);
                } else {
                    PrayerHandler.activatePrayer(player, PrayerHandler.AUGURY);
                }
                break;
            case 18018:
                if (PrayerHandler.isActivated(player, PrayerHandler.RIGOUR)) {
                    PrayerHandler.deactivatePrayer(player, PrayerHandler.RIGOUR);
                } else {
                    PrayerHandler.activatePrayer(player, PrayerHandler.RIGOUR);
                }
                break;
            case 10000:
            case 950:
                if (player.getInterfaceId() < 0)
                    player.getPacketSender().sendInterface(40030);
                else
                    player.getPacketSender().sendMessage("Please close the interface you have open before doing this.");
                break;
            case 3546:
            case 3420:
                if (System.currentTimeMillis() - player.getTrading().lastAction <= 300)
                    return;
                player.getTrading().lastAction = System.currentTimeMillis();
                
                if (player.getTrading().inTrade()) {
                    player.getTrading().acceptTrade(id == 3546 ? 2 : 1);
                } else if (player.getGambling().inGamble()) {
                	player.getGambling().acceptGamble(player);
                } else {
                    player.getPacketSender().sendInterfaceRemoval();
                }
                break;
            case 10162:
            case -18269:
            case 11729:
                player.getPacketSender().sendInterfaceRemoval();
                break;
            case 841:
                IngridientsBook.readBook(player, player.getCurrentBookPage() + 2, true);
                //TestBook.readBook(player, player.getCurrentBookPage() + 2, true);
                break;
            case 839:
                IngridientsBook.readBook(player, player.getCurrentBookPage() - 2, true);
                //TestBook.readBook(player, player.getCurrentBookPage() - 2, true);
                break;
            case 14922:
                player.getPacketSender().sendClientRightClickRemoval().sendInterfaceRemoval();
                break;
            case 14921:
                player.getPacketSender().sendMessage("Please visit the forums and ask for help in the support section.");
                break;
            case 5294:
                if (player.isBanking() && player.getInterfaceId() == 5292) {
                player.getPacketSender().sendClientRightClickRemoval().sendInterfaceRemoval();
                player.setDialogueActionId(player.getBankPinAttributes().hasBankPin() ? 8 : 7);
                DialogueManager.start(player,
                        DialogueManager.getDialogues().get(player.getBankPinAttributes().hasBankPin() ? 12 : 9));
                }
                break;
            case 2735:
            case 1511:
                if (player.getSummoning().getBeastOfBurden() != null) {
                    player.getSummoning().toInventory();
                    player.getPacketSender().sendInterfaceRemoval();
                } else {
                    player.getPacketSender().sendMessage("You do not have a familiar who can hold items.");
                }
                break;
            case -11501:
            case -11504:
            case -11498:
            case -11507:
            case 1020:
            case 1021:
            case 1019:
            case 1018:
                if (id == 1020 || id == -11504)
                    SummoningTab.renewFamiliar(player);
                else if (id == 1019 || id == -11501)
                    SummoningTab.callFollower(player);
                else if (id == 1021 || id == -11498)
                    SummoningTab.handleDismiss(player, false);
                else if (id == -11507)
                    player.getSummoning().store();
                else if (id == 1018)
                    player.getSummoning().toInventory();
                break;
            case 11004:
                player.getPacketSender().sendTab(GameSettings.SKILLS_TAB);
                DialogueManager.sendStatement(player, "Simply press on the skill you want to train in the skills tab.");
                player.setDialogueActionId(-1);
                break;
            case 8654:
            case 8657:
            case 8655:
            case 8663:
            case 8669:
            case 8660:
            	Teleporting.openTab(player, -4934);
            	break;
            case 11008:
            	player.getTeleportInterface().sendMonsterTab();
                break;
            case 11014:
            	player.getTeleportInterface().sendBossTab();
            	break;
            case 11017:
            	player.getTeleportInterface().sendMinigamesTab();
                break;
            case 11011:
            	player.getTeleportInterface().sendSkillingTab();
                break;
            case 11020:
            	player.getTeleportInterface().sendCitiesTab();
                break;
            case 2799:
            case 2798:
            case 1747:
            case 1748:
            case 8890:
            case 8886:
            case 8875:
            case 8871:
            case 8894:
                ChatboxInterfaceSkillAction.handleChatboxInterfaceButtons(player, id);
                break;
            case 14873:
            case 14874:
            case 14875:
            case 14876:
            case 14877:
            case 14878:
            case 14879:
            case 14880:
            case 14881:
            case 14882:
                BankPin.clickedButton(player, id);
                break;
            case 27005:
            case 22012:
                if (!player.isBanking() || player.getInterfaceId() != 5292)
                    return;
                Bank.depositItems(player, id == 27005 ? player.getEquipment() : player.getInventory(), false);
                break;           
            case 27023:
                if (!player.isBanking() || player.getInterfaceId() != 5292)
                    return;
                if (player.getSummoning().getBeastOfBurden() == null) {
                    player.getPacketSender().sendMessage("You do not have a familiar which can hold items.");
                    return;
                }
                Bank.depositItems(player, player.getSummoning().getBeastOfBurden(), false);
                break;
            case 22008:
                if (!player.isBanking() || player.getInterfaceId() != 5292)
                    return;
                player.setNoteWithdrawal(!player.withdrawAsNote());
                break;
            case 21000:
                if (!player.isBanking() || player.getInterfaceId() != 5292)
                    return;
                player.setSwapMode(false);
                player.getPacketSender().sendConfig(304, 0).sendMessage("This feature is coming soon!");
                // player.setSwapMode(!player.swapMode());
                break;
            /*case 27009:
                if (player.getClickDelay().elapsed(2000)) {
                    MoneyPouch.toBank(player);
                    player.getClickDelay().reset();
                } else {
                    System.err.println("User: " + player + " Last click: " + player.getClickDelay().elapsed());
                    player.sendMessage("@red@Please wait a few secs before doing this again.");
                }
                break;*/
            case 27009:
            	if (player.getClickDelay().elapsed(2000)) {
            	player.sendMessage("@red@Coming Soon!");
        	}
        	break;
            case 27014:
            case 27015:
            case 27016:
            case 27017:
            case 27018:
            case 27019:
            case 27020:
            case 27021:
            case 27022:
                if (!player.isBanking())
                    return;
                if (player.getBankSearchingAttribtues().isSearchingBank())
                    BankSearchAttributes.stopSearch(player, true);
                int bankId = id - 27014;
                boolean empty = bankId > 0 ? Bank.isEmpty(player.getBank(bankId)) : false;
                if (!empty || bankId == 0) {
                    player.setCurrentBankTab(bankId);
                    player.getPacketSender().sendString(5385, "scrollreset");
                    player.getPacketSender().sendString(27002, Integer.toString(player.getCurrentBankTab()));
                    player.getPacketSender().sendString(27000, "1");
                    player.getBank(bankId).open();
                } else
                    player.getPacketSender().sendMessage("To reincarnate a new tab, please drag an item here.");
                break;
            case 22004:
                if (!player.isBanking())
                    return;
                if (!player.getBankSearchingAttribtues().isSearchingBank()) {
                    player.getBankSearchingAttribtues().setSearchingBank(true);
                    player.setInputHandling(new EnterSyntaxToBankSearchFor());
                    player.getPacketSender().sendEnterInputPrompt("What would you like to search for?");
                } else {
                    BankSearchAttributes.stopSearch(player, true);
                }
                break;
            case 32602:
                player.setInputHandling(new PosInput());
                player.getPacketSender().sendEnterInputPrompt("What/Who would you like to search for?");
                break;
            case 22845:
            case 24115:
            case 24010:
            case 24041:
            case 150:
                player.setAutoRetaliate(!player.isAutoRetaliate());
                break;
            case 29332:
                ClanChat clan = player.getCurrentClanChat();
                if (clan == null) {
                    player.getPacketSender().sendMessage("You are not in a clanchat channel.");
                    return;
                }
                ClanChatManager.leave(player, false);
                player.setClanChatName(null);
                break;
            case 29329:
                if (player.getInterfaceId() > 0) {
                    player.getPacketSender()
                            .sendMessage("Please close the interface you have open before opening another one.");
                    return;
                }
                player.setInputHandling(new EnterClanChatToJoin());
                player.getPacketSender().sendEnterInputPrompt("Enter the name of the clanchat channel you wish to join:");
                break;
            case 19158:
            case 152:
                if (player.getRunEnergy() <= 1) {
                    player.getPacketSender().sendMessage("You do not have enough energy to do this.");
                    player.setRunning(false);
                } else
                    player.setRunning(!player.isRunning());
                player.getPacketSender().sendRunStatus();
                break;

            case -282:
                DropLog.openRare(player);
                break;

            case -26355:
                player.setExperienceLocked(!player.experienceLocked());
                String type = player.experienceLocked() ? "locked" : "unlocked";
                player.getPacketSender().sendMessage("Your experience is now " + type + ".");
                PlayerPanel.refreshPanel(player);
                break;
            case 27651:
            case 21341:
                if (player.getInterfaceId() == -1) {
                    player.getSkillManager().stopSkilling();
                    BonusManager.update(player);
                    player.getPacketSender().sendInterface(21172);
                } else
                    player.getPacketSender().sendMessage("Please close the interface you have open before doing this.");
                break;
            case 27654:
                if (player.getInterfaceId() > 0) {
                    player.getPacketSender()
                            .sendMessage("Please close the interface you have open before opening another one.");
                    return;
                }
                player.getSkillManager().stopSkilling();
                ItemsKeptOnDeath.sendInterface(player);
                break;
            case 2458: // Logout
                if (player.logout()) {
                     if (player.getSummoned() > 0) {
                    SummoningTab.handleDismiss(player, false);
                    // player.setSummoned(-1);
                         player.save();
                    }//wia remove it. bro u want me to dfo something then let me do it cus i know the problem now...... ok fine nvm fix
                    World.getPlayers().remove(player);
                }
                break;
            case 29138:
            case 29038:
            case 29063:
            case 29113:
            case 29163:
            case 29188:
            case 29213:
            case 29238:
            case 30007:
            case 48023:
            case 33033:
            case 30108:
            case 7473:
            case 7562:
            case 7487:
            case 7788:
            case 8481:
            case 7612:
            case 7587:
            case 7662:
            case 7462:
            case 7548:
            case 7687:
            case 7537:
            case 12322:
            case 7637:
            case 12311:
            case -24530:
                CombatSpecial.activate(player);
                break;
            case 1772: // shortbow & longbow
                if (player.getWeapon() == WeaponInterface.SHORTBOW) {
                    player.setFightType(FightType.SHORTBOW_ACCURATE);
                } else if (player.getWeapon() == WeaponInterface.LONGBOW) {
                    player.setFightType(FightType.LONGBOW_ACCURATE);
                } else if (player.getWeapon() == WeaponInterface.CROSSBOW) {
                    player.setFightType(FightType.CROSSBOW_ACCURATE);
                }
                break;
            case 1771:
                if (player.getWeapon() == WeaponInterface.SHORTBOW) {
                    player.setFightType(FightType.SHORTBOW_RAPID);
                } else if (player.getWeapon() == WeaponInterface.LONGBOW) {
                    player.setFightType(FightType.LONGBOW_RAPID);
                } else if (player.getWeapon() == WeaponInterface.CROSSBOW) {
                    player.setFightType(FightType.CROSSBOW_RAPID);
                }
                break;
            case 1770:
                if (player.getWeapon() == WeaponInterface.SHORTBOW) {
                    player.setFightType(FightType.SHORTBOW_LONGRANGE);
                } else if (player.getWeapon() == WeaponInterface.LONGBOW) {
                    player.setFightType(FightType.LONGBOW_LONGRANGE);
                } else if (player.getWeapon() == WeaponInterface.CROSSBOW) {
                    player.setFightType(FightType.CROSSBOW_LONGRANGE);
                }
                break;
            case 2282: // dagger & sword
                if (player.getWeapon() == WeaponInterface.DAGGER) {
                    player.setFightType(FightType.DAGGER_STAB);
                } else if (player.getWeapon() == WeaponInterface.SWORD) {
                    player.setFightType(FightType.SWORD_STAB);
                }
                break;
            case 2285:
                if (player.getWeapon() == WeaponInterface.DAGGER) {
                    player.setFightType(FightType.DAGGER_LUNGE);
                } else if (player.getWeapon() == WeaponInterface.SWORD) {
                    player.setFightType(FightType.SWORD_LUNGE);
                }
                break;
            case 2284:
                if (player.getWeapon() == WeaponInterface.DAGGER) {
                    player.setFightType(FightType.DAGGER_SLASH);
                } else if (player.getWeapon() == WeaponInterface.SWORD) {
                    player.setFightType(FightType.SWORD_SLASH);
                }
                break;
            case 2283:
                if (player.getWeapon() == WeaponInterface.DAGGER) {
                    player.setFightType(FightType.DAGGER_BLOCK);
                } else if (player.getWeapon() == WeaponInterface.SWORD) {
                    player.setFightType(FightType.SWORD_BLOCK);
                }
                break;
            case 2429: // scimitar & longsword
                if (player.getWeapon() == WeaponInterface.SCIMITAR) {
                    player.setFightType(FightType.SCIMITAR_CHOP);
                } else if (player.getWeapon() == WeaponInterface.LONGSWORD) {
                    player.setFightType(FightType.LONGSWORD_CHOP);
                }
                break;
            case 2432:
                if (player.getWeapon() == WeaponInterface.SCIMITAR) {
                    player.setFightType(FightType.SCIMITAR_SLASH);
                } else if (player.getWeapon() == WeaponInterface.LONGSWORD) {
                    player.setFightType(FightType.LONGSWORD_SLASH);
                }
                break;
            case 2431:
                if (player.getWeapon() == WeaponInterface.SCIMITAR) {
                    player.setFightType(FightType.SCIMITAR_LUNGE);
                } else if (player.getWeapon() == WeaponInterface.LONGSWORD) {
                    player.setFightType(FightType.LONGSWORD_LUNGE);
                }
                break;
            case 2430:
                if (player.getWeapon() == WeaponInterface.SCIMITAR) {
                    player.setFightType(FightType.SCIMITAR_BLOCK);
                } else if (player.getWeapon() == WeaponInterface.LONGSWORD) {
                    player.setFightType(FightType.LONGSWORD_BLOCK);
                }
                break;
            case 3802: // mace
                player.setFightType(FightType.MACE_POUND);
                break;
            case 3805:
                player.setFightType(FightType.MACE_PUMMEL);
                break;
            case 3804:
                player.setFightType(FightType.MACE_SPIKE);
                break;
            case 3803:
                player.setFightType(FightType.MACE_BLOCK);
                break;
            case 4454: // knife, thrownaxe, dart & javelin
                if (player.getWeapon() == WeaponInterface.KNIFE) {
                    player.setFightType(FightType.KNIFE_ACCURATE);
                } else if (player.getWeapon() == WeaponInterface.THROWNAXE) {
                    player.setFightType(FightType.THROWNAXE_ACCURATE);
                } else if (player.getWeapon() == WeaponInterface.DART) {
                    player.setFightType(FightType.DART_ACCURATE);
                } else if (player.getWeapon() == WeaponInterface.JAVELIN) {
                    player.setFightType(FightType.JAVELIN_ACCURATE);
                }
                break;
            case 4453:
                if (player.getWeapon() == WeaponInterface.KNIFE) {
                    player.setFightType(FightType.KNIFE_RAPID);
                } else if (player.getWeapon() == WeaponInterface.THROWNAXE) {
                    player.setFightType(FightType.THROWNAXE_RAPID);
                } else if (player.getWeapon() == WeaponInterface.DART) {
                    player.setFightType(FightType.DART_RAPID);
                } else if (player.getWeapon() == WeaponInterface.JAVELIN) {
                    player.setFightType(FightType.JAVELIN_RAPID);
                }
                break;
            case 4452:
                if (player.getWeapon() == WeaponInterface.KNIFE) {
                    player.setFightType(FightType.KNIFE_LONGRANGE);
                } else if (player.getWeapon() == WeaponInterface.THROWNAXE) {
                    player.setFightType(FightType.THROWNAXE_LONGRANGE);
                } else if (player.getWeapon() == WeaponInterface.DART) {
                    player.setFightType(FightType.DART_LONGRANGE);
                } else if (player.getWeapon() == WeaponInterface.JAVELIN) {
                    player.setFightType(FightType.JAVELIN_LONGRANGE);
                }
                break;
            case 4685: // spear
                player.setFightType(FightType.SPEAR_LUNGE);
                break;
            case 4688:
                player.setFightType(FightType.SPEAR_SWIPE);
                break;
            case 4687:
                player.setFightType(FightType.SPEAR_POUND);
                break;
            case 4686:
                player.setFightType(FightType.SPEAR_BLOCK);
                break;
            case 4711: // 2h sword
                player.setFightType(FightType.TWOHANDEDSWORD_CHOP);
                break;
            case 4714:
                player.setFightType(FightType.TWOHANDEDSWORD_SLASH);
                break;
            case 4713:
                player.setFightType(FightType.TWOHANDEDSWORD_SMASH);
                break;
            case 4712:
                player.setFightType(FightType.TWOHANDEDSWORD_BLOCK);
                break;
            case 5576: // pickaxe
                player.setFightType(FightType.PICKAXE_SPIKE);
                break;
            case 5579:
                player.setFightType(FightType.PICKAXE_IMPALE);
                break;
            case 5578:
                player.setFightType(FightType.PICKAXE_SMASH);
                break;
            case 5577:
                player.setFightType(FightType.PICKAXE_BLOCK);
                break;
            case 7768: // claws
                player.setFightType(FightType.CLAWS_CHOP);
                break;
            case 7771:
                player.setFightType(FightType.CLAWS_SLASH);
                break;
            case 7770:
                player.setFightType(FightType.CLAWS_LUNGE);
                break;
            case 7769:
                player.setFightType(FightType.CLAWS_BLOCK);
                break;
            case 8466: // halberd
                player.setFightType(FightType.HALBERD_JAB);
                break;
            case 8468:
                player.setFightType(FightType.HALBERD_SWIPE);
                break;
            case 8467:
                player.setFightType(FightType.HALBERD_FEND);
                break;
            case 5862: // unarmed
                player.setFightType(FightType.UNARMED_PUNCH);
                break;
            case 5861:
                player.setFightType(FightType.UNARMED_KICK);
                break;
            case 5860:
                player.setFightType(FightType.UNARMED_BLOCK);
                break;
            case 12298: // whip
                player.setFightType(FightType.WHIP_FLICK);
                break;
            case 12297:
                player.setFightType(FightType.WHIP_LASH);
                break;
            case 12296:
                player.setFightType(FightType.WHIP_DEFLECT);
                break;
            case 336: // staff
                player.setFightType(FightType.STAFF_BASH);
                break;
            case 335:
                player.setFightType(FightType.STAFF_POUND);
                break;
            case 334:
                player.setFightType(FightType.STAFF_FOCUS);
                break;
            case 433: // warhammer
                player.setFightType(FightType.WARHAMMER_POUND);
                break;
            case 432:
                player.setFightType(FightType.WARHAMMER_PUMMEL);
                break;
            case 431:
                player.setFightType(FightType.WARHAMMER_BLOCK);
                break;
            case 782: // scythe
                player.setFightType(FightType.SCYTHE_REAP);
                break;
            case 784:
                player.setFightType(FightType.SCYTHE_CHOP);
                break;
            case 785:
                player.setFightType(FightType.SCYTHE_JAB);
                break;
            case 783:
                player.setFightType(FightType.SCYTHE_BLOCK);
                break;
            case 1704: // battle axe
                player.setFightType(FightType.BATTLEAXE_CHOP);
                break;
            case 1707:
                player.setFightType(FightType.BATTLEAXE_HACK);
                break;
            case 1706:
                player.setFightType(FightType.BATTLEAXE_SMASH);
                break;
            case 1705:
                player.setFightType(FightType.BATTLEAXE_BLOCK);
                break;
        }
    }

    private boolean checkHandlers(Player player, int id) {
        if (Construction.handleButtonClick(id, player)) {
            return true;
        }
        if(player.getTeleportInterface().handleButton(id))
        	return true;
        switch (id) {
            case 2494:
            case 2495:
            case 2496:
            case 2497:
            case 2498:
            case 2471:
            case 2472:
            case 2473:
            case 2461:
            case 2462:
            case 2482:
            case 2483:
            case 2484:
            case 2485:
                DialogueOptions.handle(player, id);
                return true;
        }
        if (player.isPlayerLocked() && id != 2458 && id != -12780 && id != -12779 && id != -12778 && id != -29767) {
            return true;
        }
        if (Achievements.handleButton(player, id)) {
            return true;
        }
        if (Sounds.handleButton(player, id)) {
            return true;
        }
        if (PrayerHandler.isButton(id)) {
            PrayerHandler.togglePrayerWithActionButton(player, id);
            return true;
        }
        if (CurseHandler.isButton(player, id)) {
            return true;
        }
        if (StartScreen.handleButton(player, id)) {
            return true;
        }
        if (StarterTasks.handleButton(player, id)) {
            return true;
        }
        if (Autocasting.handleAutocast(player, id)) {
            return true;
        }
        if (SmithingData.handleButtons(player, id)) {
            return true;
        }
        if (PouchMaking.pouchInterface(player, id)) {
            return true;
        }
        if (LoyaltyProgramme.handleButton(player, id)) {
            return true;
        }
        if (Fletching.fletchingButton(player, id)) {
            return true;
        }
        if (LeatherMaking.handleButton(player, id) || Tanning.handleButton(player, id)) {
            return true;
        }
        if (Emotes.doEmote(player, id)) {
            return true;
        }
        if (PestControl.handleInterface(player, id)) {
            return true;
        }
        if (player.getLocation() == Location.DUEL_ARENA && Dueling.handleDuelingButtons(player, id)) {
            return true;
        }
        if (Slayer.handleRewardsInterface(player, id)) {
            return true;
        }
        if (ExperienceLamps.handleButton(player, id)) {
            return true;
        }
        if (PlayersOnlineInterface.handleButton(player, id)) {
            return true;
        }
        if (GrandExchange.handleButton(player, id)) {
            return true;
        }
        if (ClanChatManager.handleClanChatSetupButton(player, id)) {
            return true;
        }
        return false;
    }

    public static final int OPCODE = 185;
}
