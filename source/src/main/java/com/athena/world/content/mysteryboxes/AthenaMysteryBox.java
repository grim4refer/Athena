package com.athena.world.content.mysteryboxes;


import com.athena.model.Item;
import com.athena.util.Misc;
import com.athena.util.RandomUtility;
import com.athena.world.World;
import com.athena.world.entity.impl.player.Player;

/**
 * @author Tibo
 */
public class AthenaMysteryBox {

	/**
	 * The player object that will be triggering this event
	 */
	private final Player plr;
	private final int BOX = 20870;
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
	public AthenaMysteryBox(Player plr) {
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

		int rewards2[][] = {
				{11556,20257,18911, 20054, 20064, 11555, 20752}, //Common, 0
				{20750}, //Uncommon, 1
		};
		double numGen = Math.random();
		/** Chances
		 *  50% chance of Common Items - cheap gear, high-end consumables
		 *  40% chance of Uncommon Items - various high-end coin-bought gear
		 *  10% chance of Rare Items - Highest-end coin-bought gear, some voting-point/pk-point equipment
		 */
		int rewardGrade;
		int random = Misc.random(99)+1;
		if(random > 5) {
			rewardGrade = 0;
		} else if(random > 4) {
			rewardGrade = 1;
		} else {
			rewardGrade = 3;
		}


		int rewardPos = RandomUtility.getRandom(rewards2[rewardGrade].length-1);
		plr.getInventory().delete(20870, 1);
		plr.getInventory().add(rewards2[rewardGrade][rewardPos], 1).refreshItems();
		if(rewards2[rewardGrade][rewardPos] == 11556) {
			World.sendMessage("<shad=15664412>[Athena box] @or1@"+plr.getUsername()+"@whi@ Has just received @yel@Ring of <shad=15695415>DEATH!");
		}
		if(rewards2[rewardGrade][rewardPos] == 20257) {
			World.sendMessage("<shad=15664412>[Athena box] @or1@"+plr.getUsername()+"@whi@ Has just received @yel@Blood <shad=15695415>NECKLACE!");
		}
		if(rewards2[rewardGrade][rewardPos] == 18911) {
			World.sendMessage("<shad=15664412>[Athena box] @or1@"+plr.getUsername()+"@whi@ Has just received @yel@Creature <shad=15695415>WHIP!");
		}
		if(rewards2[rewardGrade][rewardPos] == 20054) {
			World.sendMessage("<shad=15664412>[Athena box] @or1@"+plr.getUsername()+"@whi@ Has just received @yel@Ring of <shad=15695415>DEVOTION!");
		}
		if(rewards2[rewardGrade][rewardPos] == 20064) {
			World.sendMessage("<shad=15664412>[Athena box] @or1@"+plr.getUsername()+"@whi@ Has just received @yel@ <shad=15695415>CRAMULET!");
		}
		if(rewards2[rewardGrade][rewardPos] == 11555) {
			World.sendMessage("<shad=15664412>[Athena box] @or1@"+plr.getUsername()+"@whi@ Has just received @yel@Ring of <shad=15695415>CHAOS!");
		}
		if(rewards2[rewardGrade][rewardPos] == 20752) {
			World.sendMessage("<shad=15664412>[Athena box] @or1@"+plr.getUsername()+"@whi@ Has just received @yel@The Sponsor <shad=15695415>GLAIVE!");
		}
		if(rewards2[rewardGrade][rewardPos] == 20750) {
			World.sendMessage("<shad=15664412>[Athena box] @or1@"+plr.getUsername()+"@whi@ Has just received @red@The Owner <shad=800000000>CAPE!");
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
		
		int[] common = { 3749, 3753, 10828, 1215, 4587, 20750, 20752,11555,20064,20054,18911,20257,11556
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