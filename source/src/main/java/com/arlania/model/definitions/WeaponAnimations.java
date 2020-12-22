package com.arlania.model.definitions;

import com.arlania.model.CharacterAnimations;
import com.arlania.model.Item;
import com.arlania.model.container.impl.Equipment;
import com.arlania.world.entity.impl.player.Player;

/**
 * A static utility class that contains methods for changing the appearance
 * animation for a player whenever a new weapon is equipped or an existing item
 * is unequipped.
 * 
 * @author lare96
 */
public final class WeaponAnimations {

	/**
	 * Executes an animation for the argued player based on the animation of the
	 * argued item.
	 * 
	 * @param player
	 *            the player to animate.
	 * @param item
	 *            the item to get the animations for.
	 */
	public static void assign(Player player, Item item) {
		player.getCharacterAnimations().reset();
		if(player.getTransform() == 0) {
			player.setCharacterAnimations(getUpdateAnimation(item));
		}
	}

	public static CharacterAnimations getUpdateAnimation(Item item) {
		String weaponName = item.getDefinition().getName().toLowerCase();
		int playerStandIndex = 0x328;
		int playerWalkIndex = 0x333;
		int playerRunIndex = 0x338;
		if (weaponName.contains("halberd") || weaponName.contains("guthan")) {
			playerStandIndex = 809;
			playerWalkIndex = 1146;
			playerRunIndex = 1210;
		}
		else if (weaponName.startsWith("basket")) {
			playerStandIndex = 1836;
			playerWalkIndex = 1836;
			playerRunIndex = 1836;
		}

		else if (weaponName.startsWith("whip")) {
			playerStandIndex = 0x328;
			//playerStandIndex = 8980;
		playerWalkIndex = 1660;
		playerRunIndex = 1661;
		}
		else if (weaponName.contains("dharok") || weaponName.contains("bludgeon")) {
			playerStandIndex = 0x811;
			playerWalkIndex = 0x67F;
			playerRunIndex = 0x680;
		}
		else if (weaponName.contains("sled")) {
			playerStandIndex = 1461;
			playerWalkIndex = 1468;
			playerRunIndex = 1467;
		}
		else if (weaponName.contains("ahrim")) {
			playerStandIndex = 809;
			playerWalkIndex = 1146;
			playerRunIndex = 1210;
		}
		else if (weaponName.contains("verac")) {
			playerStandIndex = 0x328;
			playerWalkIndex = 0x333;
			playerRunIndex = 824;
		}
		else if (weaponName.contains("longsword") || weaponName.contains("scimitar")) {
			playerStandIndex = 15069;//12021;
			playerRunIndex = 15070;//12023;
			playerWalkIndex = 15073; //12024;
		} else if(weaponName.contains("silverlight")
				|| weaponName.contains("korasi's") || weaponName.contains("Nature") || weaponName.contains("Forest")||weaponName.contains("katana")) {
			playerStandIndex = 12021;
			playerRunIndex = 12023;
			playerWalkIndex = 12024;/*
			playerStandIndex = 8980;
			playerRunIndex = 1210;
			playerWalkIndex = 1146;*/
		}
		else if (weaponName.contains("wand") || weaponName.contains("staff")
				|| weaponName.contains("staff") || weaponName.contains("spear") || item.getId() == 21005 || item.getId() == 21010) {
			playerStandIndex = 8980;
			playerRunIndex = 1210;
			playerWalkIndex = 1146;
		}
		else if (weaponName.contains("karil")) {
			playerStandIndex = 2074;
			playerWalkIndex = 2076;
			playerRunIndex = 2077;
		}
		else if (weaponName.contains("2h sword") || weaponName.contains("godsword")
				|| weaponName.contains("saradomin sw")) {
			playerStandIndex = 7047;
			playerWalkIndex = 7046;
			playerRunIndex = 7039;
		}
		else if (weaponName.contains("bow")) {
			playerStandIndex = 808;
			playerWalkIndex = 819;
			playerRunIndex = 824;
		}
		if(weaponName.toLowerCase().contains("rapier")) {
			playerStandIndex = 12021;
			playerWalkIndex = 12024;
			playerRunIndex = 12023;
			/*
			playerStandIndex = 8980;
			playerRunIndex = 1210;
			playerWalkIndex = 1146;*/
		}
		switch (item.getId()) {
			case 21063:
			case 21060:
				playerStandIndex = 12021;
				playerRunIndex = 12023;
				playerWalkIndex = 12024;
				break;
			case 21013:
			case 20079:
			case 20862:	
				playerStandIndex = 809;
				playerWalkIndex = 1146;
				playerRunIndex = 1210;
				break;
		case 19467:
			playerStandIndex = 2074;
			playerWalkIndex = 2076;
			playerRunIndex = 2077;
			break;
			case 20102:
			case 20607:
			playerStandIndex = 71;
			playerWalkIndex = 70;
			playerRunIndex = 70;
			break;
		case 19938:
			playerStandIndex = 2074;
			playerWalkIndex = 2076;
			playerRunIndex = 2077;
			break;
		case 18353: // maul chaotic
		case 13212: // maul kryptic
		case 3954: // maul bandos
		case 3964: // maul saradomin
			playerStandIndex = 13217;
			playerWalkIndex = 13218;
			playerRunIndex = 13220;
			break;
		case 16184:
			playerStandIndex = 13217;
			playerWalkIndex = 13218;
			playerRunIndex = 13220;
			break;
		case 16425:
			playerStandIndex = 13217;
			playerWalkIndex = 13218;
			playerRunIndex = 13220;
			break;
		case 4151:
		case 13444:
		case 15441: // whip
		case 15442: // whip
		case 15443: // whip
		case 15444: // whip
		playerStandIndex = 0x328;
			//playerStandIndex = 8980;
		playerWalkIndex = 1660;
		playerRunIndex = 1661;
		break;
		case 20061: // vine whip
		case 20089:
		case 17849:
		case 17848:
		case 18902:
		case 18911:
		case 18930:
		case 18957:
		case 21001:
			playerStandIndex = 11973;
			playerWalkIndex = 11975;
			playerRunIndex = 1661;
			break;
			case 4705:
			playerStandIndex = 11973;
			playerWalkIndex = 11975;
			playerRunIndex = 1661;
			break;
		case 15039:
			playerStandIndex = 12000;
			playerWalkIndex = 1663;
			playerRunIndex = 1664;
			break;
		case 10887:
			playerStandIndex = 5869;
			playerWalkIndex = 5867;
			playerRunIndex = 5868;
			break;
		case 4450:
		case 6528:
		case 20084:
			playerStandIndex = 0x811;
			playerWalkIndex = 2064;
			playerRunIndex = 1664;
			break;
		case 4153:
			playerStandIndex = 1662;
			playerWalkIndex = 1663;
			playerRunIndex = 1664;
			break;
		case 15241:
		case 896:
			case 21082:
			case 20695:
			case 21062:
			case 4784:
			case 20932:
			//case 3961:
		case 19939:
			playerStandIndex = 12155;
			playerWalkIndex = 12154;
			playerRunIndex = 12154;
			break;
		case 20000:
		case 20001:
		case 20002:
		case 20003:
		case 11694:
		case 11696:
		case 11730:
		case 3953:
		case 11698:
		case 11700:
			break;
		case 1305:
		case 6196:
			playerStandIndex = 809;
			break;
		}
		return new CharacterAnimations(playerStandIndex, playerWalkIndex, playerRunIndex);
	}

	public static int getAttackGfx(Player c){
		int weaponId = c.getEquipment().getItems()[Equipment.WEAPON_SLOT].getId();
		String weaponName = ItemDefinition.forId(weaponId).getName().toLowerCase();
		String prop = c.getFightType().toString().toLowerCase();
		switch(weaponId){
			case 11539:
			case 19004:
				return 5;
			case 21002:
				return 129;
				case 21013:
				return 332;
			case 21030:
				return 127;
			case 21060:
				return 255;
			case 21063:
				return 762;
			case 21032:
				return 255;
			case 21031:
				return 128;
			case 20079:
			case 20862:	
				return 1310;
			case 21033:
				return 68;
            case 17847:
                return 2345;
			case 21003:
				return 482;
			case 20102:
			case 20607:
				return 188;
			case 20510:
			case 20751:
			case 20752:
			case 20601:
			case 3666:
				return 197;
			case 21072:
				return 481;
			case 21073:
				return 481;
			case 21001:
				return 2321;
			case 3065:
				return 79;
			case 20873:
				return 74;
			case 21004:
				return 1009;
			case 20504:
				return 1173;
			case 15668:
				return 184;
			case 20922:
				return 665;
			case 19005:
				return 2147;
		}
		return 0;
	}
	public static int getAttackAnimation(Player c) {
		int shieldId = c.getEquipment().getItems()[Equipment.SHIELD_SLOT].getId();
		int weaponId = c.getEquipment().getItems()[Equipment.WEAPON_SLOT].getId();
		String weaponName = ItemDefinition.forId(weaponId).getName().toLowerCase();
		String prop = c.getFightType().toString().toLowerCase();
		if(shieldId == 20854)
			return 1074;
		if(weaponId == 18373)
			return 1074;
		if(weaponId == 21013 || weaponId == 20079 || weaponId == 20862)
			return 440;
		if(weaponId == 21080)
			return 427;
		if(weaponId == 20605)
		    return 426;
		if(weaponId == 20102 || weaponId == 20607)
			return 2890;
		if(weaponId == 20604 || weaponId == 20602)
			return 2890;
		if (weaponId == 7684)
			return 582;//dtd
		if(weaponId == 21077)
				return 427;	
		if(weaponId == 21038)
			return 427;	
		if(weaponId == 21039)
			return 427;	
		if(weaponId == 21040)
			return 427;	
		if(weaponId == 21041)
			return 427;	
		if(weaponId == 21042)
			return 427;	
		if(weaponId == 21043)
			return 427;	
		if(weaponId == 21072)
			return 451;	
		if(weaponId == 21073)
			return 412;	
		if(weaponId == 3065)
			return 2876;	
		if(weaponId == 20873)
				return 2068;
		if(weaponId == 19005) {
				return 2068;

		} if(weaponId == 20504) {
			return 13044;
		}
		if(weaponId == 21002 || weaponId == 21003 || weaponId == 21004|| weaponId == 21030|| weaponId == 21031|| weaponId == 21032|| weaponId == 21033)
		return 14600;
		if(weaponId == 10033 || weaponId == 10034)
			return 2779;
		if(prop.contains("dart")) {
			if(prop.contains("long"))
				return 6600;
			return 582;
		}
		if (weaponName.contains("javelin") || weaponName.contains("thrownaxe")) {
			return 806;
		}
		if (weaponName.contains("halberd")) {
			return 440;
		}
		if (weaponName.startsWith("dragon dagger")) {
			if(prop.contains("slash"))
				return 377;
			return 376;
		}
		if (weaponName.endsWith("dagger")) {
			if(prop.contains("slash"))
				return 13048;
			return 13049;
		}
		if(weaponName.equals("staff of light") || weaponId == 21005 || weaponId == 21010) {
			if(prop.contains("stab"))
				return 13044;
			else if(prop.contains("lunge"))
				return 13047;
			else if(prop.contains("slash"))
				return 13048;
			else if(prop.contains("block"))
				return 13049;
		} else if(weaponName.startsWith("staff") || weaponName.endsWith("staff")) {
			//bash = 400
			return 401;
		}
		if(weaponName.endsWith("warhammer") || weaponName.endsWith("battleaxe"))
			return 401;
		if (weaponName.contains("2h sword") || weaponName.contains("godsword")
				|| weaponName.contains("saradomin sword")) {
			/*if(prop.contains("stab"))
				return 11981;
			else if(prop.contains("slash"))
				return 11980;*/
			return 11979;
		}
		if(weaponName.contains("brackish")) {
			if(prop.contains("lunge") || prop.contains("slash"))
				return 12029;
			return 12028;
		}
		if (weaponName.contains("scimitar") || weaponName.contains("longsword")
				|| weaponName.contains("korasi's") || weaponName.contains("katana")) {
			if(prop.contains("lunge"))
				return 15072;
			return 15071;
		}
		if(weaponName.contains("spear")) {
			if(prop.contains("lunge"))
				return 13045;
			else if(prop.contains("slash"))
				return 13047;
			return 13044;
		}
		if (weaponName.contains("rapier")) {
			if(prop.contains("slash"))
				return 12029;
			return 386;
		}
		if(weaponName.contains("claws"))
			return 393;
		if(weaponName.contains("maul") && !weaponName.contains("granite"))
			return 13055;
		if (weaponName.contains("dharok")) {
			if(prop.contains("block"))
				return 2067;
			return 2066;
		}
		if (weaponName.contains("sword")) {
			return prop.contains("slash") ? 12311 : 12310;
		}
		if (weaponName.contains("karil"))
			return 2075;
		else if (weaponName.contains("'bow") || weaponName.contains("crossbow"))
			return 4230;
		if (weaponName.contains("bow") && !weaponName.contains("'bow"))
			return 426;
		if (weaponName.contains("pickaxe")) {
			if(prop.contains("smash"))
				return 401;
			return 400;
		}
		if (weaponName.contains("mace")) {
			if(prop.contains("spike"))
				return 13036;
			return 13035;
		}
		switch (weaponId) { // if you don't want
		// to use strings
		case 20000:
		case 20001:
		case 20002:
		case 20003:
			return 7041;
		case 6522: //Obsidian throw
			return 2614;
		case 4153: // granite maul
			return 1665;
		case 13879:
		case 13883:
			return 806;
		case 16184:
			return 2661;
		case 16425:
			return 2661;
		case 15241:
		case 896:
			case 4784:
			case 21082:
			//case 3961:
				case 20695:
			case 21062:
		case 19939:
		case 20932:
			return 12153;
			case 894:
			case 21044:
			case 700:
			case 701:
			case 895:
			case 2867:
			return 427;
		case 4747: // torag
			return 0x814;
		case 4710: // ahrim
			return 406;
		case 18353:
			return 13055;
		case 18349:
			return 386;
		case 19146:
			return 386;
		case 4755: // verac
			return 2062;
		case 4734: // karil
		case 19467: // armadylian machine
		case 19938: // ak47
			return 2075;
		case 10887:
			return 5865;
		case 4151:
		case 13444:
		case 20089:
		case 15441: // whip
		case 15442: // whip
		case 15443: // whip
		case 15444: // whip
		case 20061:
		case 17849:
		case 17848:
		case 18902:
		case 18911:
		case 18930:
		case 18957:
		case 21001:
			if(prop.contains("flick"))
				return 11968;
			else if(prop.contains("lash"))
				return 11969;
			else if(prop.contains("deflect"))
				return 11970;
		case 4450:
		case 6528:
		case 20084:
			return 2661;
		default:
			return c.getFightType().getAnimation();
		}
	}

	public static int getBlockAnimation(Player c) {
		int weaponId = c.getEquipment().getItems()[Equipment.WEAPON_SLOT].getId();
		String shield = ItemDefinition.forId(c.getEquipment().getItems()[Equipment.SHIELD_SLOT].getId()).getName().toLowerCase();
		String weapon = ItemDefinition.forId(weaponId).getName().toLowerCase();
		if (shield.contains("defender"))
			return 4177;
		if (shield.contains("2h"))
			return 7050;
		if (shield.contains("book") && (weapon.contains("wand")))
			return 420;
		if (shield.contains("shield"))
			return 1156;
		if(weapon.contains("scimitar") || weapon.contains("longsword") || weapon.contains("katana") || weapon.contains("korasi"))
			return 15074;
		switch (weaponId) {
		case 4755:
			return 2063;
		case 15241:
		case 896:
			case 21082:
			case 20695:
			case 21062:
			case 4784:
		//	case 3961:
		case 19939:
		case 20932:
			return 12156;
		case 13899:
			return 13042;
		case 18355:
			return 13046;
		case 14484:
			return 397;
		case 19023:
			return 397;
		case 11716:
			return 12008;
		case 4153:
			return 1666;
		case 4151:
		case 20079:
		case 20862:	
		case 20089:
		case 13444:
		case 15441: // whip
		case 15442: // whip
		case 15443: // whip
		case 15444: // whip
		case 20061:
		case 17849:
		case 17848:
		case 18902:
		case 18911:
		case 18930:
		case 18957:
		case 21001:
			return 11974;
		case 21079:
		    return 427;
		case 15486:
		case 15502:
		case 22209:
		case 22211:
		case 22207:
		case 22213:
		case 21005:
		case 21010:
		case 14004:
		case 14005:
		case 14006:
		case 14007:
			return 12806;
		case 18349:
			return 12030;
		case 18353:
			return 13054;
		case 18351:
			return 13042;
		case 20000:
		case 20001:
		case 20002:
		case 20003:
		case 11694:
		case 3953:
		case 11698:
		case 11700:
		case 11696:
		case 11730:
		case 19028:
			return 7050;
		case -1:
			return 424;
		default:
			return 424;
		}
	}
}
