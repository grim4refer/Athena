package com.athena.world.content.mysteryboxes;


import com.athena.model.Animation;
import com.athena.model.Graphic;
import com.athena.model.Item;
import com.athena.model.definitions.ItemDefinition;
import com.athena.model.definitions.NPCDrops;
import com.athena.util.Misc;
import com.athena.util.RandomUtility;
import com.athena.world.World;
import com.athena.world.entity.impl.player.Player;

/**
 * @author Tibo
 */
public class AbbadonBox {

	/**
	 * The player object that will be triggering this event
	 */
	private final Player plr;
	private final int BOX = 7114;
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
	public AbbadonBox(Player plr) {
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
			int abba[][] = {
					{5022}, //Uncommon, 0
					{21080,21079,21075,5018,20114,20257,20257,20257,20257,20112,20113,20114,20112,20113,20114,20112,20113,20114,20112,20113,20114,21080,21079,21075,20114,20112,20113,17847,21080,21079,21075,20114,20112,20113,17847,21080,21079,21075,20114,20112,20113,17847,21080,21079,21075,20114,20112,20113,17847,20112,20113,20114,20112,20113,20114,20112,20113,20114,20112,20113,17847}, //Rare, 1
					{2104,2758,2095,2096,2097,2098}
			};
			numGen3 = Math.random();

			double randomYoda = Misc.random(650);

			double drBoost = NPCDrops.getDroprate(player);
			randomYoda = (int)randomYoda * ((100-drBoost)/100);
			int amount = 1;
			if(randomYoda == 0) {
				rewardGrade3 = 1;
				rewardPos = RandomUtility.getRandom(abba[rewardGrade3].length-1);
				player.setAnimation(new Animation(6382));
				player.setGraphic(new Graphic(127));
				World.sendMessage("<shad=0>@bla@[@red@Abbadon box@bla@] @red@"+player.getUsername()+"@bla@ Has just received a @red@ "+ItemDefinition.forId(abba[rewardGrade3][rewardPos]).getName()+" @bla@from @red@Abbadon box!");
				double randomDouble = Misc.random(99);

				double ddrBoost = NPCDrops.getDoubleDr(player);
				randomDouble = (int)randomDouble * ((100-ddrBoost)/100);
				if(randomDouble == 0) {
					amount *= 2;
				}
			} else {
				rewardGrade3 = 0;
				rewardPos = RandomUtility.getRandom(abba[rewardGrade3].length-1);
				double randomDouble = Misc.random(99);
				if(Misc.random(150) == 1) {
					player.getInventory().add(new Item(20451,1));
					World.sendMessage("<shad=0>@bla@[@red@Abbadon box@bla@] @red@"+player.getUsername()+"@bla@ Has just received a @red@ Abbadon Soul @bla@from @red@Abbadon box!");

				}
				double ddrBoost = NPCDrops.getDoubleDr(player);
				randomDouble = (int)randomDouble * ((100-ddrBoost)/100);
				amount = Misc.random(125);

				if(randomDouble == 0) {
					amount *= 2;
				}
			}
			player.getInventory().delete(7114, 1);
			player.getInventory().add(abba[rewardGrade3][rewardPos], amount).refreshItems();
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
		
		int[] common = { -1,-1,-1,-1,-1,5022,20257,21080,21079,21075,20112,20113,20114,5018, 17847,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1
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