package com.athena.world.content.mysteryboxes;


import com.athena.model.Animation;
import com.athena.model.Graphic;
import com.athena.model.Item;
import com.athena.model.definitions.ItemDefinition;
import com.athena.model.definitions.NPCDrops;
import com.athena.util.Misc;
import com.athena.util.RandomUtility;
import com.athena.world.entity.impl.player.Player;

/**
 * @author Tibo
 */
public class DonkeyBox {

	/**
	 * The player object that will be triggering this event
	 */
	private final Player plr;
	private final int BOX = 7108;
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
	public DonkeyBox(Player plr) {
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
		double numGen3 = Math.random();
		int rewardGrade3 = 0;
		int rewardPos = 0;

		if (plr.getInventory().hasItem(new Item(BOX, 1))) {
			if (plr.getInventory().getFreeSlots() < 2) {
				plr.getPacketSender().sendMessage("You don't have enough free inventory space.");
				return;
			}
			int donkey[][] = {
					{5022}, //Uncommon, 0
					{21060, 13196, 13197, 13198, 20254,13206, 13207, 13199}
			};

			double randomYoda = Misc.random(80);
			double drBoost = NPCDrops.getDroprate(plr);
			randomYoda = (int) randomYoda / ((100 + drBoost) / 100);
			int amount = 1;
			if (randomYoda == 0) {
				rewardGrade3 = 1;
				rewardPos = RandomUtility.getRandom(donkey[rewardGrade3].length - 1);
				plr.setAnimation(new Animation(6382));
				plr.setGraphic(new Graphic(127));
				plr.sendMessage("<shad=0>@bla@[@gre@Nature box@bla@] @gre@" + plr.getUsername() + "@bla@ Has just received a @gre@ " + ItemDefinition.forId(donkey[rewardGrade3][rewardPos]).getName() + " @bla@from @gre@Donkey Kong box!");
				double randomDouble = Misc.random(99);

				double ddrBoost = NPCDrops.getDoubleDr(plr);
				randomDouble = (int) randomDouble * ((100 - ddrBoost) / 100);
				if (randomDouble == 0) {
					amount *= 2;
				}
			} else {
				rewardGrade3 = 0;
				rewardPos = RandomUtility.getRandom(donkey[rewardGrade3].length - 1);
				double randomDouble = Misc.random(99);

				double ddrBoost = NPCDrops.getDoubleDr(plr);
				randomDouble = (int) randomDouble * ((100 - ddrBoost) / 100);
				amount = Misc.random(60);

				if (randomDouble == 0) {
					amount *= 2;
				}
			}
			plr.getInventory().delete(7108, 1);
			plr.getInventory().add(donkey[rewardGrade3][rewardPos], amount).refreshItems();
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
		
		int[] common = { -1,-1,-1,-1,-1,21060, 13196, 13197,20254, 13198, 13206, 13207, 13199, -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1
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