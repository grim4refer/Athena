package com.arlania.world.content.teleportation.teleports;

import com.arlania.model.Position;


public class TeleportSlot {

    private final int slotIndex;
    private Position position;

    public TeleportSlot(int slotIndex, Position position) {
        this.slotIndex = slotIndex;
        this.position = position;
    }

    public int getSlotIndex() {
        return slotIndex;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

}
