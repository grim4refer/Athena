package com.athena.model.input.impl;

import com.athena.engine.task.Task;
import com.athena.engine.task.TaskManager;
import com.athena.model.Animation;
import com.athena.model.Graphic;
import com.athena.model.Item;
import com.athena.model.input.EnterAmount;
import com.athena.util.Misc;
import com.athena.world.content.PlayerLogs;
import com.athena.world.entity.impl.player.Player;
import com.google.common.collect.Range;

public class GambleAmount extends EnterAmount {

	private static final Range<Integer> DICE_RANGE = Range.closed(1, 100);
	
	@Override
	public void handleAmount(Player player, int amount) {
		if(amount > 1000000000) {
			player.getPacketSender().sendMessage("You can not gamble over 1b of any item");
			return;
		}
			
		player.getPacketSender().sendInterfaceRemoval();
		int cost = amount;
		
		if(player.getInventory().getAmount(5022) < cost) {
			player.getPacketSender().sendMessage("You do not have enough money in your @red@inventory@bla@ to gamble that amount.");
			return;
		}
		PlayerLogs.log(player.getUsername(), "Player gambled "  +amount + "Coins");
		player.getPacketSender().sendMessage("Rolling...");
		player.performAnimation(new Animation(11900));
		player.performGraphic(new Graphic(2075));
		player.getInventory().delete(5022, amount);
		//int roll = RandomUtility.inclusiveRandom(DICE_RANGE.lowerEndpoint(), DICE_RANGE.upperEndpoint());
		int roll = 30 + Misc.getRandom(50);

		TaskManager.submit(new Task(2, player, false) {
			@Override
			public void execute() {
				if (roll >= 60) {
					player.forceChat("I Rolled A " + roll + " And Have Won!");
					player.getInventory().add(new Item(5022, amount * 2));
				} else {
					player.forceChat("I Rolled A " + roll + " And Have Lost!");
				}
				this.stop();
			}
		});
	
	}
}