package com.athena.world.content.combat.magic;


import com.athena.model.Graphic;
import com.athena.model.container.impl.Equipment;
import com.athena.world.content.combat.CombatContainer;
import com.athena.world.content.combat.CombatType;
import com.athena.world.entity.impl.Character;
import com.athena.world.entity.impl.player.Player;

public class CustomMagicStaff {

    public static enum CustomStaff {
    	BALOON_STAFF(new int[] { 3665 }, CombatSpells.BALOON_STAFF.getSpell()),
        KRYPTIC_STAFF(new int[] { 13215 }, CombatSpells.KYRPTIC_SPELL.getSpell()),
        ANGELIC_STAFF(new int[] { 20988 }, CombatSpells.ANGELIC_SPELL.getSpell()),
        INFERNAL_BATTLESTAFF(new int[] { 20998 }, CombatSpells.INFERNAL_BATTLESTAFF_SPELL.getSpell()),
        DRACONIAN_STAFF(new int[] { 3661 }, CombatSpells.DRACONIAN_SPELL.getSpell()),
        OBLIVIONSTAFF(new int[] { 21061 }, CombatSpells.OBLIVION.getSpell()),
        WATER_STAFF(new int[] { 20604 }, CombatSpells.WATER_STAFF.getSpell()),
        INFERNAL_STAFF(new int[] { 20602 }, CombatSpells.INFERNAL_STAFF.getSpell()),
        ELITE_STAFF(new int[] { 18891 }, CombatSpells.ELITE_SPELL.getSpell()),
        InfernalUndead_STAFF(new int[] { 20838 }, CombatSpells.OBLIVION2.getSpell());

        private int[] itemIds;
        private CombatSpell spell;

        CustomStaff(int[] itemIds, CombatSpell spell) {
            this.itemIds = itemIds;
            this.spell = spell;
        }

        public int[] getItems() {
            return this.itemIds;
        }

        public CombatSpell getSpell() {
            return this.spell;
        }

        public static CombatSpell getSpellForWeapon(int weaponId) {
            for (CustomStaff staff : CustomStaff.values()) {
                for (int itemId : staff.getItems())
                    if (weaponId == itemId)
                        return staff.getSpell();
            }
            return null;
        }
    }

    public static boolean checkCustomStaff(Character c) {
        int weapon;
        if (!c.isPlayer())
            return false;
        Player player = (Player)c;
        weapon = player.getEquipment().getItems()[Equipment.WEAPON_SLOT].getId();
        return CustomStaff.getSpellForWeapon(weapon) != null;
    }

    public static void handleCustomStaff(Character c) {
        Player player = (Player) c;
        int weapon = player.getEquipment().getItems()[Equipment.WEAPON_SLOT].getId();
        CombatSpell spell = CustomStaff.getSpellForWeapon(weapon);
        player.setCastSpell(spell);
        player.setAutocast(true);
        player.setAutocastSpell(spell);
        player.setCurrentlyCasting(spell);
        player.setLastCombatType(CombatType.MAGIC);

    }
    public static CombatContainer getCombatContainer(Character player, Character target) {
        ((Player)player).setLastCombatType(CombatType.MAGIC);
        return new CombatContainer(player, target, 1, 1, CombatType.MAGIC, true) {
            @Override
            public void onHit(int damage, boolean accurate) {

                target.performGraphic(new Graphic(1730));
            }
        };
    }

}
