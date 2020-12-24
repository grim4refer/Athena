package com.athena.world.content.mysteryboxes;


import com.athena.model.Animation;
import com.athena.model.Graphic;
import com.athena.model.Item;
import com.athena.model.definitions.ItemDefinition;
import com.athena.util.Misc;
import com.athena.util.RandomUtility;
import com.athena.world.World;
import com.athena.world.entity.impl.player.Player;

/**
 * @author Tibo
 */
public class DonatorBox {

	/**
	 * The player object that will be triggering this event
	 */
	private final Player plr;
	private final int BOX = 7118;
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
	public DonatorBox(Player plr) {
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
		Player player = plr;

		if (player.getInventory().hasItem(new Item(BOX, 1))) {
			if (plr.getInventory().getFreeSlots() < 2) {
				plr.getPacketSender().sendMessage("You don't have enough free inventory space.");
				return;
			}
			int donor[][] = {
					{7100, 12421, 12422, 11425, 11527, 11529, 20604,20095,20096,20097}, //Uncommon, 0
					{11531, 20604, 20603, 20605, 17849}, //Rare, 1
					{21077,12423, 21078, 21082, 12424, 20089, 11423, 11533, 20601, 11423}
			};
			numGen3 = Math.random();

			double randomYoda = Misc.random(75);

			int amount = 1;
			if (randomYoda == 0) {
				rewardGrade3 = 2;
				rewardPos = RandomUtility.getRandom(donor[rewardGrade3].length - 1);
				player.setAnimation(new Animation(6382));
				player.setGraphic(new Graphic(127));
				World.sendMessage("<shad=0>@bla@[@cya@Donor box] [@mag@Legendary@bla@] @cya@" + player.getUsername() + "@bla@ Has just received a @cya@ " + ItemDefinition.forId(donor[rewardGrade3][rewardPos]).getName() + " @bla@!");

			} else if (randomYoda < 25) {
				rewardGrade3 = 1;
				rewardPos = RandomUtility.getRandom(donor[rewardGrade3].length - 1);
				player.setAnimation(new Animation(6382));
				player.setGraphic(new Graphic(127));
				World.sendMessage("<shad=0>@bla@[@cya@Donor box] [@blu@Uncommon@bla@] @cya@" + player.getUsername() + "@bla@ Has just received a @cya@ " + ItemDefinition.forId(donor[rewardGrade3][rewardPos]).getName() + " @bla@!");

			} else {
				rewardGrade3 = 0;
				rewardPos = RandomUtility.getRandom(donor[rewardGrade3].length - 1);
				player.setAnimation(new Animation(6382));
				player.setGraphic(new Graphic(127));
				World.sendMessage("<shad=0>@bla@[@cya@Donor box] [@gre@common@bla@] @cya@" + player.getUsername() + "@bla@ Has just received a @cya@ " + ItemDefinition.forId(donor[rewardGrade3][rewardPos]).getName() + " @bla@!");
				double randomDouble = Misc.random(125);


			}
			player.getInventory().delete(7118, 1);
			player.getInventory().add(donor[rewardGrade3][rewardPos], amount).refreshItems();

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

		int[] common = { -1,-1,-1,-1,-1,7100, 12421, 12422, 11425, 11527, 11529, 20604,21077, 21078, 21082, 12424, 20089, 11423, 11533, 20601,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
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