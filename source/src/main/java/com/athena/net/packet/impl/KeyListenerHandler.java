package com.athena.net.packet.impl;

import com.athena.net.packet.Packet;
import com.athena.net.packet.PacketListener;
import com.athena.world.entity.impl.player.Player;

public class KeyListenerHandler implements PacketListener {

    @Override
    public void handleMessage(Player player, Packet packet) {

        int keyId = packet.readShort();

        if (keyId == 8)
            player.getPacketSender().closeAllWindows();

    }

}