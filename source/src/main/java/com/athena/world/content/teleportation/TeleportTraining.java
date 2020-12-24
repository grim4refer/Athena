package com.athena.world.content.teleportation;



public class TeleportTraining extends Teleporting {
	
	public static enum Training {
		TELEPORT_1(new String[] {"Cars",""}, new int[] {2505, 2507, 1}, 0),
		TELEPORT_2(new String[] {"Crash", ""}, new int[] {2506, 2523, 1},10),
		TELEPORT_3(new String[] {"Terror dog", ""}, new int[] {2503, 2539, 1},20),
		TELEPORT_4(new String[] {"Pinguin", ""}, new int[] {2521, 2551, 1},40),
		TELEPORT_5(new String[] {"Nature Sword", ""}, new int[] {2548, 2551, 1},80),
		TELEPORT_6(new String[] {"Aurelia", ""}, new int[] {2568, 2550, 1},150),
		TELEPORT_7(new String[] {"Belerion", ""}, new int[] {2551, 2517, 1},300),
		TELEPORT_8(new String[] {"Livyathann", ""}, new int[] {2552, 2517, 2},600),
		TELEPORT_9(new String[] {"Dire Wolf", ""}, new int[] {2551, 2534, 2},1200),
		TELEPORT_10(new String[] {"Yeti", ""}, new int[] {2549, 2551, 2},2400),

		TELEPORT_11(new String[] {"Charming", "Imps"}, new int[] {2521, 2551, 2},0),
		TELEPORT_12(new String[] {"@red@Gano", ""}, new int[] {3170, 2993, 0,},0);
		
		/**
		 * Initializing the teleport names.
		 */
		private String[] teleportName;
		/**
		 * Initializing the teleport coordinates.
		 */
		private int[] teleportCoordinates;
		private int npcKills;

		/**
		 * Constructing the enumerator.
		 * @param teleportName
		 * 			The name of the teleport.
		 * @param teleportName2
		 * 			The secondary name of the teleport.
		 * @param teleportCoordinates
		 * 			The coordinates of the teleport.
		 */
		private Training(final String[] teleportName, final int[] teleportCoordinates, int npcKills) {
			this.teleportName = teleportName;
			this.teleportCoordinates = teleportCoordinates;
			this.npcKills = npcKills;
		}
		
		/**
		 * Setting the teleport name.
		 * @return
		 */
		public String[] getTeleportName() {
			return teleportName;
		}
		/**
		 * Setting the teleport coordinates.
		 * @return
		 */
		public int[] getCoordinates() {
			return teleportCoordinates;
		}

		public int getReq() {
			return npcKills;
		}
	}

}

