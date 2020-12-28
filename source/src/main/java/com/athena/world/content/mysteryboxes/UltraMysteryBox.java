package com.athena.world.content.mysteryboxes;


import com.athena.model.Item;
import com.athena.util.Misc;
import com.athena.util.RandomUtility;
import com.athena.world.World;
import com.athena.world.entity.impl.player.Player;

/**
 * @author Tibo
 */
public class UltraMysteryBox {

	/**
	 * The player object that will be triggering this event
	 */
	private final Player plr;
	private final int BOX = 20931;
	private final int INTERFACE_ID = 47000;
	private final int ITEM_FRAME = 47101;
	private int spinNum = 0;
	private boolean canMysteryBox = true;
	private int mysteryPrize;
	private int mysteryPrizeTier;

	public int getSpinNum() {
		return spinNum;
	}

	public boolean canMysteryBox() {
		return canMysteryBox;
	}

	public int getMysteryPrize() {
		return mysteryPrize;
	}

	public int getMysteryPrizeTier() {
		return mysteryPrizeTier;
	}

	/**
	 * Constructs a new mystery box to handle item receiving for this player alone
	 * 
	 * @param plr
	 *            the player
	 */
	public UltraMysteryBox(Player plr) {
		this.plr = plr;
	}

	public void spin() {
		// Server side checks for spin
		if (!canMysteryBox) {
			plr.sendMessage("Please finish your current spin.");
			return;
		}
		if (!plr.getInventory().contains(BOX)) {
			plr.sendMessage("You require a mystery box to do this.");
			return;
		}
		plr.setSpinning(true);
		// Delete box
		plr.getInventory().delete(BOX, 1);
		// Initiate spin
		canMysteryBox = false;
		plr.sendMessage(":spin");
		process();
	}

	public void process() {
		if(plr.getInventory().hasItem(new Item(BOX,1))) {
			if (plr.getInventory().getFreeSlots() < 2) {
				plr.getPacketSender().sendMessage("You don't have enough free inventory space.");
				return;
			}

		int rewards0[][] = {
				{20847, 20850, 20851, 20849, 20848, 20810, 20811, 20812, 20813, 20814, 20821, 20822, 20823, 20824, 20825, 20828, 20842, 20845, 20844, 20846, 20871, 20872, 12424, 15668, 15667, 15664, 4646, 4644, 4645, 20090, 11552, 20750, 20854, 20853, 20818, 20838, 12425, 20873,20980,20981,20982,20983 }, //Common, 0
		};
		double numGen = Math.random();
		/** Chances
		 *  50% chance of Common Items - cheap gear, high-end consumables
		 *  40% chance of Uncommon Items - various high-end coin-bought gear
		 *  10% chance of Rare Items - Highest-end coin-bought gear, some voting-point/pk-point equipment
		 */
		int rewardGrade = 0;
		int random = Misc.random(99)+1;
		if(random > 1) {
			rewardGrade = 0;
		}


		int rewardPos = RandomUtility.getRandom(rewards0[rewardGrade].length-1);
		plr.getInventory().delete(20931, 1);
		plr.getInventory().add(rewards0[rewardGrade][rewardPos], 1).refreshItems();
		if(rewards0[rewardGrade][rewardPos] == 20847) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@gre@ has just received @mag@Yani <shad=48384383>MASK!");
		}
		if(rewards0[rewardGrade][rewardPos] == 20850) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@gre@ has just received @mag@Yani <shad=48384383>TOP!");
		}
		if(rewards0[rewardGrade][rewardPos] == 20851) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@gre@ has just received @mag@Yani <shad=48384383>PANTS!");
		}
		if(rewards0[rewardGrade][rewardPos] == 20849) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@gre@ has just received @mag@Yani <shad=48384383>BOOTS!");
		}
		if(rewards0[rewardGrade][rewardPos] == 20848) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@gre@ has just received @mag@Yani <shad=48384383>GLOVES!");
		}
		if(rewards0[rewardGrade][rewardPos] == 20810) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@gre@ has just received @mag@Dragon <shad=48384383>Vanquisher Helm!");
		}
		if(rewards0[rewardGrade][rewardPos] == 20811) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@gre@ has just received @mag@Dragon <shad=48384383>Vanquisher Body!");
		}
		if(rewards0[rewardGrade][rewardPos] == 20812) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@gre@ has just received @mag@Dragon <shad=48384383>Vanquisher Legs!");
		}
		if(rewards0[rewardGrade][rewardPos] == 20813) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@gre@ has just received @mag@Dragon <shad=48384383>Vanquisher Gauntlets!");
		}
		if(rewards0[rewardGrade][rewardPos] == 20814) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@gre@ has just received @mag@Dragon <shad=48384383>Vanquisher Boots!");
		}
		if(rewards0[rewardGrade][rewardPos] == 20821) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@gre@ has just received @bla@Hell Fiend Helm!");
		}
		if(rewards0[rewardGrade][rewardPos] == 20822) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@gre@ has just received @bla@Hell Fiend Body!");
		}
		if(rewards0[rewardGrade][rewardPos] == 20828) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@gre@ has just received @cya@AWESOME REWARD @red@Pet <shad=38384383>VORAGO!");
		}
		if(rewards0[rewardGrade][rewardPos] == 20842) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@gre@ has just received @bla@God Void Ranger Helm!");
		}
		if(rewards0[rewardGrade][rewardPos] == 20845) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@gre@ has just received @bla@God Void Knight Top!");
		}
		if(rewards0[rewardGrade][rewardPos] == 20844) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@gre@ has just received @bla@God Void Knight Robe!");
		}
		if(rewards0[rewardGrade][rewardPos] == 20846) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@gre@ has just received @bla@God Void Knight Gloves!");
		}
		if(rewards0[rewardGrade][rewardPos] == 20871) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@gre@ has just received @cya@AWESOME REWARD @red@Athena <shad=38384383>POWER A!");
		}
		if(rewards0[rewardGrade][rewardPos] == 20872) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@gre@ has just received @cya@AWESOME REWARD @red@Athena <shad=38384383>POWER B!");
		}
		if(rewards0[rewardGrade][rewardPos] == 20823) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@gre@ has just received @bla@Hell Fiend Chainskirt!");
		}
		if(rewards0[rewardGrade][rewardPos] == 20824) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@gre@ has just received @bla@Hell Fiend Gloves!");
		}
		if(rewards0[rewardGrade][rewardPos] == 12424) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@gre@ has just received @cya@AWESOME REWARD @red@Legendary <shad=38384383>DONATOR!");
		}
		if(rewards0[rewardGrade][rewardPos] == 15668) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@gre@ has just received @cya@AWESOME REWARD @red@Raids <shad=38384383>HAMMER!");
		}
		if(rewards0[rewardGrade][rewardPos] == 15667) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@gre@ has just received @cya@AWESOME REWARD @red@Luigi <shad=38384383>MASK!");
		}
		if(rewards0[rewardGrade][rewardPos] == 20825) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@gre@ has just received @bla@Hell Fiend Boots!");
		}
		if(rewards0[rewardGrade][rewardPos] == 15664) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@gre@ has just received @mag@Raids <shad=48384383>CAPE!");
		}
		if(rewards0[rewardGrade][rewardPos] == 4646) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@bla@ has just received @yel@SUPER RARE @red@Athena <shad=88384383>HELM!");
		}
		if(rewards0[rewardGrade][rewardPos] == 4644) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@bla@ has just received @yel@SUPER RARE @red@Athena <shad=88384383>PLATEBODY!");
		}
		if(rewards0[rewardGrade][rewardPos] == 4645) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@bla@ has just received @yel@SUPER RARE @red@Athena <shad=88384383>PLATELEGS!");
		}
		if(rewards0[rewardGrade][rewardPos] == 20090) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@bla@ has just received @yel@SUPER RARE @red@Athena <shad=88384383>Boots!");
		}
		if(rewards0[rewardGrade][rewardPos] == 11552) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@bla@ has just received @yel@SUPER RARE @red@Athena <shad=88384383>Gloves!");
		}
		if(rewards0[rewardGrade][rewardPos] == 20750) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@bla@ has just received @yel@SUPER RARE @red@Owner <shad=88384383>CAPE!");
		}
		if(rewards0[rewardGrade][rewardPos] == 20854) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@bla@ has just received @yel@SUPER RARE @red@Bfg <shad=88384383>LEFT HAND!");
		}
		if(rewards0[rewardGrade][rewardPos] == 20853) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@bla@ has just received @yel@SUPER RARE @red@Bfg <shad=88384383>RIGHT HAND!");
		}
		if(rewards0[rewardGrade][rewardPos] == 20818) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@bla@ has just received @yel@SUPER RARE @red@The Lucky <shad=88384383>ONE SWORD!");
		}
		if(rewards0[rewardGrade][rewardPos] == 20838) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@bla@ has just received @yel@SUPER RARE @red@Infernal <shad=88384383>UNDEAD STAFF!");
		}
		if(rewards0[rewardGrade][rewardPos] == 12425) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@bla@ has just received @yel@SUPER RARE @red@Uber <shad=88384383>DONATOR!");
		}
		if(rewards0[rewardGrade][rewardPos] == 20873) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@bla@ has just received @yel@SUPER RARE @red@Dual Gem <shad=88384383>SWORD!");
		}
		if(rewards0[rewardGrade][rewardPos] == 20980) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@bla@ has just received @yel@SUPER RARE @red@Doflamingo <shad=88384383>HELM!");
		}
		if(rewards0[rewardGrade][rewardPos] == 20981) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@bla@ has just received @yel@SUPER RARE @red@Doflamingo <shad=88384383>BODY!");
		}
		if(rewards0[rewardGrade][rewardPos] == 20982) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@bla@ has just received @yel@SUPER RARE @red@Doflamingo <shad=88384383>LEGS!");
		}
		if(rewards0[rewardGrade][rewardPos] == 20983) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@bla@ has just received @yel@SUPER RARE @red@Doflamingo <shad=88384383>BOOTS!");
		}
		if(rewards0[rewardGrade][rewardPos] == 20984) {
			World.sendMessage("<shad=58384383>[Ultra box] @blu@"+plr.getUsername()+"@bla@ has just received @yel@SUPER RARE @red@Doflamingo <shad=88384383>BOOTS!");
		}
		}
	}


	public void sendItem(int i, int prizeSlot, int PRIZE_ID, int NOT_PRIZE_ID) {
		if (i == prizeSlot) {
			plr.getPA().mysteryBoxItemOnInterface(PRIZE_ID, 1, ITEM_FRAME, i);
		} else {
			plr.getPA().mysteryBoxItemOnInterface(NOT_PRIZE_ID, 1, ITEM_FRAME, i);
		}
	}

	public void openInterface() {
		
		int[] common = { 3749, 3753, 10828, 1215, 4587, 20847, 20850, 20851, 20849, 20848, 20810, 20811, 20812, 20813, 20814, 20821, 20822, 20823, 20824, 20825, 20828, 20842, 20845, 20844, 20846, 20871, 20872, 12424, 15668, 15667, 15664, 4646, 4644, 4645, 20090, 11552, 20750, 20854, 20853, 20818, 20838, 12425, 20873,20980,20981,20982,20983
				,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
			plr.getPA().setScrollBar(47300, 0);
					for (int i = 0; i < common.length; i++) {
				plr.getPA().sendItemOnInterface(47305 + i, common[i], 1);
			}

		// Reset interface
		plr.sendMessage(":resetBox");
		spinNum = 0;
		// Open
		plr.getPA().sendInterface(INTERFACE_ID);
	}
}