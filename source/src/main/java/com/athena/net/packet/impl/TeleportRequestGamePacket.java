package com.athena.net.packet.impl;

import com.athena.net.packet.Packet;
import com.athena.net.packet.PacketListener;
import com.athena.world.content.KillsTracker;
import com.athena.world.content.teleportation.teleports.*;
import com.athena.world.content.transportation.TeleportHandler;
import com.athena.world.content.transportation.TeleportType;
import com.athena.world.entity.impl.player.Player;

public class TeleportRequestGamePacket implements PacketListener {

    @Override
    public void handleMessage(Player player, Packet packet) {
        final int parentHierarchy = packet.readUnsignedShort();
        final int childHierarchy = packet.readUnsignedShort();

        if (parentHierarchy < HIERARCHY_OPTIONS.length && childHierarchy < HIERARCHY_OPTIONS[parentHierarchy].length) {
            final Teleport teleport = HIERARCHY_OPTIONS[parentHierarchy][childHierarchy];
            if (teleport.getPosition() == null) {
                player.sendMessage("Coordinates are needed for this teleport: " + teleport.toString());
                return;
            }

            if(teleport.getPosition().getX() == 1) {
            	player.sendMessage("This teleport is temporarily disabled");
            	return;
            }

            if(teleport instanceof  TrainingTeleport &&  ((TrainingTeleport) teleport).getRequirement() != 0) {
                if(((TrainingTeleport) teleport).getRequirement() > KillsTracker.getTotalKills(player)) {
                    player.sendMessage("You require "+((TrainingTeleport) teleport).getRequirement()+" NPC kills to use this teleport.");
                    player.sendMessage("You only have "+KillsTracker.getTotalKills(player)+" kills.");
                    return;
                }
            }

            if (teleport.getWarning() == null) {
                player.getPacketSender().sendInterfaceRemoval();
                TeleportHandler.teleportPlayer(player, teleport.getPosition(), TeleportType.NORMAL);
            } else {
                player.getPacketSender().sendInterfaceRemoval();
                TeleportHandler.teleportPlayer(player, teleport.getPosition(), TeleportType.NORMAL);
            }
        } else {
            player.sendMessage("Could not teleport! Please contact an administrator with this message [" + parentHierarchy + ", " + childHierarchy + "]");
        }
    }

    private static final Teleport[][] HIERARCHY_OPTIONS = {
            TrainingTeleport.values(),
            MinigamesTeleports.values(),
            BossTeleport.values(),
           /* PvPTeleport.values(),
            CityTeleport.values(),
            MinigameTeleport.values(),

            SkillingTeleport.AGILITY.getTeleports(),
            SkillingTeleport.COOKING.getTeleports(),
            SkillingTeleport.CRAFTING.getTeleports(),
            SkillingTeleport.FISHING.getTeleports(),
            SkillingTeleport.HUNTER.getTeleports(),
            SkillingTeleport.MINING.getTeleports(),
            SkillingTeleport.SLAYER.getTeleports(),
            SkillingTeleport.SMITHING.getTeleports(),
            SkillingTeleport.THIEVING.getTeleports(),
            SkillingTeleport.WOODCUTTING.getTeleports(),
            SkillingTeleport.FARMING.getTeleports(),*/
            Boss2Teleport.values(),
    };

}
