package com.arlania.net.packet.impl;

import com.arlania.net.packet.Packet;
import com.arlania.net.packet.PacketListener;
import com.arlania.world.entity.impl.player.Player;

public class KeyListenerHandler implements PacketListener {

    @Override
    public void handleMessage(Player player, Packet packet) {

        int keyId = packet.readShort();

        if (keyId == 8)
            player.getPacketSender().closeAllWindows();

    }

}