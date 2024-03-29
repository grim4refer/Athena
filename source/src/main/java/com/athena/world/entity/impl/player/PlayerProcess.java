package com.athena.world.entity.impl.player;

import com.athena.model.RegionInstance.RegionInstanceType;
import com.athena.world.content.BublleGum;
import com.athena.world.content.LoyaltyProgramme;
import com.athena.world.content.PlayerPanel;
import com.athena.world.content.combat.pvp.BountyHunter;
import com.athena.world.content.skill.impl.construction.House;
import com.athena.world.entity.impl.GroundItemManager;

public class PlayerProcess {

	/*
	 * The player (owner) of this instance
	 */
	private Player player;

	/*
	 * The loyalty tick, once this reaches 6, the player
	 * will be given loyalty points.
	 * 6 equals 3.6 seconds.
	 */
	private int loyaltyTick;

	/*
	 * The timer tick, once this reaches 2, the player's
	 * total play time will be updated.
	 * 2 equals 1.2 seconds.
	 */
	private int timerTick;

	/*
	 * Makes sure ground items are spawned on height change
	 */
	private int previousHeight;

	public PlayerProcess(Player player) {
		this.player = player;
		this.previousHeight = player.getPosition().getZ();
	}

	public void sequence() {

		/** SKILLS **/
		if (player.shouldProcessFarming()) {
			player.getFarming().sequence();
		}
		if (player.inFFA) {
			player.getPacketSender().sendInteractionOption("Attack", 2, true);
		}
		if (player.getCombatBuilder().isAttacking() || player.getCombatBuilder().isBeingAttacked()) {

		} else {
			if (player.walkableInterfaceList.contains(41020)) {
				player.sendParallellInterfaceVisibility(41020, false);
			}
		}

		/** MISC **/

		if (previousHeight != player.getPosition().getZ()) {
			GroundItemManager.handleRegionChange(player);
			previousHeight = player.getPosition().getZ();
		}

		if (!player.isInActive()) {
			if (loyaltyTick >= 6) {
				LoyaltyProgramme.incrementPoints(player);
				loyaltyTick = 0;
			}
			loyaltyTick++;
		}

		if (timerTick >= 1) {
//			player.getPacketSender().sendString(39166, "@or2@Time played:  @yel@"+Misc.getTimePlayed((player.getTotalPlayTime() + player.getRecordedLogin().elapsed())));
			timerTick = 0;
		}
		timerTick++;

		BountyHunter.sequence(player);
		BublleGum.sequence(player);

		PlayerPanel.refreshPanel(player);
		
		if (player.dmgPotionTime > System.currentTimeMillis()) 
		    player.applyMultiplierPotion();
		
		
		if (player.getRegionInstance() != null && (player.getRegionInstance().getType() == RegionInstanceType.CONSTRUCTION_HOUSE || player.getRegionInstance().getType() == RegionInstanceType.CONSTRUCTION_DUNGEON)) {
			((House) player.getRegionInstance()).process();
		}
	}
}