package com.arlania.world.content.skill.impl.invention;

public enum InventionTier {
    COMMON(15, 1_000),
    UNCOMMON(16, 1_750),
    RARE(17, 4_000),
    SECRET(18, 10_000);

    int dustId;
    int exp;

    InventionTier(int dustId, int exp) {
        this.dustId = dustId;
        this.exp = exp;
    }

    public int getDustId() {
        return dustId;
    }

    public int getExp() {
        return exp;
    }

}
