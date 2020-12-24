package com.athena.world.content.combat.effect;

import com.athena.model.container.impl.Equipment;
import com.athena.world.entity.impl.player.Player;

public class EquipmentBonus {
	
	public static int[] helms = {11665, 11664, 11663}; //11665 == melee, 11664 == range, 11663 == mage
	public static int[] eHelms = {21058, 21057, 21056}; //21058 == mage, 21057 == range, 21056 == melee
	public static int[] normRobes = {8839, 8840};
	public static int[] eRobes = {19785, 19786, 19787, 19788};
	public static int gloves = 8842;
	public static int eGloves = 21059;
	public static int deflector = 19712;
	public static int mace = 8841;
	public static int gGloves = 20846;
	public static int gShirt = 20845;
	public static int gLegs = 20844;
	public static int[] gHelms = {20841, 20842, 20843}; //20841 == melee, 20842 == range, 20843 == mage
	public static int dGloves = 20813;
	public static int dShirt = 20811;
	public static int dLegs = 20812;
	public static int[] dHelms = {20810};
	public static int[] YHelms = {4646};
	public static int YShirt = 4644;
	public static int YLegs = 4645;
	public static int[] Yboots = {20090};
	public static int YGloves = 11552;
	public static int[] DHelms = {21016};
	public static int DShirt = 21014;
	public static int DLegs = 21005;
	public static int[] Dboots = {21000};
	public static int DGloves = 21015;
	public static int DVGloves = 21049;
	public static int DVShirt = 21046;
	public static int DVLegs = 21047;
	public static int[] DVHelms = {21048};
	public static int SPGloves = 21036;
	public static int SPShirt = 21018;
	public static int SPLegs = 21019;
	public static int[] SPHelms = {21035};
	public static int DOFGloves = 20980;
	public static int DOFShirt = 20981;
	public static int DOFLegs = 20982;
	public static int[] DOFHelms = {20983};
	public static int YANGloves = 20847;
	public static int YANShirt = 20850;
	public static int YANLegs = 20851;
	public static int[] YANHelms = {20848};
	public static int BCShirt = 11724;
	public static int BCLegs = 11726;
	public static int BCBoots = 11728;
	public static int SLGloves = 1668;
	public static int SLShirt = 1554;
	public static int SLLegs = 1647;
	public static int[] SLHelms = {1667};
	public static int UGloves = 3063;
	public static int UShirt = 799;
	public static int ULegs = 3062;
	public static int[] UHelms = {17488};
	
	public static boolean voidElite(Player player){ 
		if (wearingVoid(player) && (player.checkItem(Equipment.BODY_SLOT, eRobes[0]) && player.checkItem(Equipment.LEG_SLOT, eRobes[1]))) {
			return true;
		} else if (wearingVoid(player) && (player.checkItem(Equipment.BODY_SLOT, eRobes[2]) && player.checkItem(Equipment.LEG_SLOT, eRobes[3]))) {
			return true;
		}
		return false;
	}
	public static boolean voidGod(Player player){
		if ( (player.checkItem(Equipment.BODY_SLOT, gShirt) && player.checkItem(Equipment.LEG_SLOT, gLegs) && player.checkItem(Equipment.HANDS_SLOT, gGloves) )) {
			return true;
		}
		return false;
	}
	public static boolean dragonV(Player player){
		if ( (player.checkItem(Equipment.BODY_SLOT, dShirt) && player.checkItem(Equipment.LEG_SLOT, dLegs) && player.checkItem(Equipment.HANDS_SLOT, dGloves) )) {
			return true;
		}
		return false;
	}
	public static boolean DarthVader(Player player){
		if ( (player.checkItem(Equipment.BODY_SLOT, DVShirt) && player.checkItem(Equipment.LEG_SLOT, DVLegs) && player.checkItem(Equipment.HANDS_SLOT, DVGloves) )) {
			return true;
		}
		return false;
	}
	public static boolean SupremeLord(Player player){
		if ( (player.checkItem(Equipment.BODY_SLOT, SLShirt) && player.checkItem(Equipment.LEG_SLOT, SLLegs) && player.checkItem(Equipment.HANDS_SLOT, SLGloves) )) {
			return true;
		}
		return false;
	}
	public static boolean Ultimatium(Player player){
		if ( (player.checkItem(Equipment.BODY_SLOT, UShirt) && player.checkItem(Equipment.LEG_SLOT, ULegs) && player.checkItem(Equipment.HANDS_SLOT, UGloves) )) {
			return true;
		}
		return false;
	}
	public static boolean SupremeGraardor(Player player){
		if ( (player.checkItem(Equipment.BODY_SLOT, BCShirt) && player.checkItem(Equipment.LEG_SLOT, BCLegs) && player.checkItem(Equipment.FEET_SLOT, BCBoots) )) {
			return true;
		}
		return false;
	}
	public static boolean DRange(Player player){
		if ( (player.checkItem(Equipment.BODY_SLOT, DOFShirt) && player.checkItem(Equipment.LEG_SLOT, DOFLegs) && player.checkItem(Equipment.HANDS_SLOT, DOFGloves) )) {
			return true;
		}
		return false;
	}
	public static boolean YRange(Player player){
		if ( (player.checkItem(Equipment.BODY_SLOT, YANShirt) && player.checkItem(Equipment.LEG_SLOT, YANLegs) && player.checkItem(Equipment.HANDS_SLOT, YANGloves) )) {
			return true;
		}
		return false;
	}
		public static boolean StormTroop(Player player){
			if ( (player.checkItem(Equipment.BODY_SLOT, SPShirt) && player.checkItem(Equipment.LEG_SLOT, SPLegs) && player.checkItem(Equipment.HANDS_SLOT, SPGloves) )) {
				return true;
			}
			return false;
	}
	public static boolean Yanille(Player player){
		if ( (player.checkItem(Equipment.BODY_SLOT, YShirt) && player.checkItem(Equipment.LEG_SLOT, YLegs) && player.checkItem(Equipment.HANDS_SLOT, YGloves) )) {
			return true;
		}
		return false;
	}
	public static boolean Deluxe(Player player){
		if ( (player.checkItem(Equipment.BODY_SLOT, DShirt) && player.checkItem(Equipment.LEG_SLOT, DLegs) && player.checkItem(Equipment.HANDS_SLOT, DGloves) )) {
			return true;
		}
		return false;
	}
	public static boolean voidGRange(Player player){
		if (voidGod(player) && player.checkItem(Equipment.HEAD_SLOT, gHelms[1])) {
			return true;
		}
		return false;
	}
	public static boolean DoflamingoRange(Player player){
		if (DRange(player) && player.checkItem(Equipment.HEAD_SLOT, DOFHelms[0])) {
			return true;
		}
		return false;
	}
	public static boolean YaniRange(Player player){
		if (YRange(player) && player.checkItem(Equipment.HEAD_SLOT, YANHelms[0])) {
			return true;
		}
		return false;
	}
	public static boolean SupremeLordHelm(Player player){
		if (SupremeLord(player) && player.checkItem(Equipment.HEAD_SLOT, SLHelms[0])) {
			return true;
		}
		return false;
	}
	public static boolean UltimatiumHelm(Player player){
		if (Ultimatium(player) && player.checkItem(Equipment.HEAD_SLOT, UHelms[0])) {
			return true;
		}
		return false;
	}

	public static boolean voidGMelee(Player player){
		if (voidGod(player) && player.checkItem(Equipment.HEAD_SLOT, gHelms[0])) {
			return true;
		}
		return false;
	}

	public static boolean dragonVMelee(Player player){
		if (voidGod(player) && player.checkItem(Equipment.HEAD_SLOT, dHelms[0])) {
			return true;
		}
		return false;
	}
	
	public static boolean DarthVaderMelee(Player player){
		if (voidGod(player) && player.checkItem(Equipment.HEAD_SLOT, DVHelms[0])) {
			return true;
		}
		return false;
	}
	
	public static boolean SPMelee(Player player){
		if (voidGod(player) && player.checkItem(Equipment.HEAD_SLOT, SPHelms[0])) {
			return true;
		}
		return false;
	}

	public static boolean YanilleMelee(Player player){
		if (voidGod(player) && player.checkItem(Equipment.HEAD_SLOT, YHelms[0])) {
			return true;
		}
		return false;
	}

	public static boolean DMelee(Player player){
		if (voidGod(player) && player.checkItem(Equipment.HEAD_SLOT, DHelms[0])) {
			return true;
		}
		return false;
	}

	public static boolean YanilleFeet(Player player){
		if (voidGod(player) && player.checkItem(Equipment.FEET_SLOT, Yboots[0])) {
			return true;
		}
		return false;
	}

	public static boolean DFeet(Player player){
		if (voidGod(player) && player.checkItem(Equipment.FEET_SLOT, Dboots[0])) {
			return true;
		}
		return false;
	}

	public static boolean voidGMage(Player player){
		if (voidGod(player) && player.checkItem(Equipment.HEAD_SLOT, gHelms[2])) {
			return true;
		}
		return false;
	}
	public static boolean voidERange(Player player){
		if (voidElite(player) && player.checkItem(Equipment.HEAD_SLOT, eHelms[1])) {
			return true;
		}
		return false;
	}
	
	public static boolean voidEMelee(Player player){
		if (voidElite(player) && player.checkItem(Equipment.HEAD_SLOT, eHelms[2])) {
			return true;
		}
		return false;
	}
	
	public static boolean voidEMage(Player player){
		if (voidElite(player) && player.checkItem(Equipment.HEAD_SLOT, eHelms[0])) {
			return true;
		}
		return false;
	}
	
	public static boolean voidRange(Player player){
		if (wearingVoid(player) && player.checkItem(Equipment.HEAD_SLOT, helms[1])) {
			return true;
		}
		return false;
	}
	
	public static boolean voidMelee(Player player){
		if (wearingVoid(player) && player.checkItem(Equipment.HEAD_SLOT, helms[0])) {
			return true;
		}
		return false;
	}
	
	public static boolean voidMage(Player player){
		if (wearingVoid(player) && player.checkItem(Equipment.HEAD_SLOT, helms[2])) {
			return true;
		}
		return false;
	}
	
	public static boolean wearingVoid(Player player) {
		boolean hasHelm = false;
		int correctEquipment = 0;
		if (player.checkItem(Equipment.BODY_SLOT, eRobes[0]) || player.checkItem(Equipment.BODY_SLOT, eRobes[2])
				|| player.checkItem(Equipment.BODY_SLOT, normRobes[0])) {
			correctEquipment++;
		}
		if (player.checkItem(Equipment.LEG_SLOT, eRobes[1]) || player.checkItem(Equipment.LEG_SLOT, eRobes[3])
				|| player.checkItem(Equipment.LEG_SLOT, normRobes[1])) {
			correctEquipment++;
		}
		if (player.checkItem(Equipment.HEAD_SLOT, helms[0]) || player.checkItem(Equipment.HEAD_SLOT, helms[1]) || player.checkItem(Equipment.HEAD_SLOT, helms[2])) {
			hasHelm = true;
			correctEquipment++;
		}
		if (player.checkItem(Equipment.HEAD_SLOT, eHelms[0]) || player.checkItem(Equipment.HEAD_SLOT, eHelms[1]) || player.checkItem(Equipment.HEAD_SLOT, eHelms[2])) {
			hasHelm = true;
			correctEquipment++;
		}

		if (player.checkItem(Equipment.SHIELD_SLOT, deflector)) {
			correctEquipment++;
		}
		if (player.checkItem(Equipment.HANDS_SLOT, gloves) || player.checkItem(Equipment.HANDS_SLOT, eGloves)) {
			correctEquipment++;
		}
		if (player.checkItem(Equipment.WEAPON_SLOT, mace)) {
			correctEquipment++;
		}
		
		if (correctEquipment > 3 && hasHelm) {
			return true;
		} 
		return false;
	}
	
	
	public static boolean wearingArtemis(Player player) {
		int correctEquipment = 0;
		if (player.checkItem(Equipment.HEAD_SLOT, 20807)) {
			correctEquipment++;
		}
		if (player.checkItem(Equipment.BODY_SLOT, 20808)) {
			correctEquipment++;
		}
		if (player.checkItem(Equipment.LEG_SLOT, 20809)) {
			correctEquipment++;
		}
		if (player.checkItem(Equipment.SHIELD_SLOT, 20080)) {
			correctEquipment++;
		}
		if (player.checkItem(Equipment.FEET_SLOT, 13239)) {
			correctEquipment++;
		}
		
		if (correctEquipment >= 5) {
			return true;
		} 
		return false;
	}
}
