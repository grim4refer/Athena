package com.ruse.world.content.treasuretrails;

import com.ruse.model.Animation;
import com.ruse.util.Misc;
import com.ruse.world.entity.impl.player.Player;
/**
 * Class handling all the necessary actions for treasure trails
 * @author Stan
 *
 */
public class TreasureTrails {

	private Player p;
	
	private final Animation digAnimation = new Animation(831);
	
	private int itemId;
	private int[] clueScrolls = {};
	
	public void dig(TreasureLocations location){
		if(p.getPosition() == location.getDigPosition()){
			p.performAnimation(digAnimation);
			switch(p.getClueProgress()){
				case 0:
					itemId = getRandomClue();
					p.setClueProgress(1);
					break;
				case 1:
					itemId = getRandomClue();
					p.setClueProgress(2);
					break;
				case 2:
					itemId = getRandomClue();
					p.setClueProgress(3);
					break;
				case 3:
					itemId = getCasket();
					p.setClueProgress(0);
					break;
			}
			p.getPacketSender().createGroundItem(1, location.getDigPosition(), 1);
		}
	}
	
	private int getRandomClue(){
		return clueScrolls[Misc.getRandom(clueScrolls.length)];
	}
	
	private int getCasket(){
		return clueScrolls[Misc.getRandom(clueScrolls.length)];
	}
}
