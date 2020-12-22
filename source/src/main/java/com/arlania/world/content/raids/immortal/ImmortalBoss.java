package com.arlania.world.content.raids.immortal;

import java.util.*;

/**
 * 
 * @author Hennessy
 * 
 * 24/7/2019 <17:51PM>
 *
 */

public class ImmortalBoss {
	
	
	public static Map<String, BossList> bossList = new HashMap<String, BossList>();
	
	
	static {
		for (BossList list : BossList.values())//TODO
			bossList.put(list.type, list);
	}
	
	public static BossList getData(String data) {
		return bossList.get(data);
	}
	
	enum BossList {
		
		LUIGI(3, "Luigi", 80_000_000),
		
		;
		
		public int bossID, bossHP;
		
		public String type;
		
		BossList(int bossID, String type, int bossHP) {
			this.bossID = bossID;
			this.type = type;
			this.bossHP = bossHP;
		}
	}
}
