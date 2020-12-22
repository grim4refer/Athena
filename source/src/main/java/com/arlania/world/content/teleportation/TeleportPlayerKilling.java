package com.arlania.world.content.teleportation;



public class TeleportPlayerKilling extends Teleporting  {

	public static enum PlayerKilling {
		TELEPORT_1(new String[] {"@yel@Nex", ""}, new int[] {2905, 5204, 4}, 0),
		TELEPORT_2(new String[] {"@dre@Godzilla", ""}, new int[] {2900,3617,0}, 1500),
		TELEPORT_3(new String[] {"@bla@Unholy", "@whi@Cursebearer"}, new int[] {2525, 4777, 0}, 2000),
		TELEPORT_4(new String[] {"@red@I @whi@k @blu@t @gre@o @bla@m @yel@i", ""}, new int[] {1940, 5001, 0}, 3000),
		TELEPORT_5(new String[] {"@yel@B @whi@r @yel@o @whi@l @yel@y", ""}, new int[] {1941, 4687, 0}, 0),
		TELEPORT_6(new String[] {"@red@I @bla@n @red@f @bla@e @red@r @bla@n @red@o", ""}, new int[] {2720, 4777, 0},3500),
		TELEPORT_7(new String[] {"@dre@V @blu@o @dre@r @blu@a @dre@g @blu@o", ""}, new int[] {2257, 3292, 0},5000),
		TELEPORT_8(new String[] {"@blu@Y @bla@a @blu@n @bla@i", ""}, new int[] {2272, 4693, 0},10000),
		TELEPORT_9(new String[] {"", ""}, new int[] {2525, 4777, 0}, 0),
		TELEPORT_10(new String[] {"", ""}, new int[] {2525, 4777, 0}, 0),
		TELEPORT_11(new String[] {"", ""}, new int[] {2525, 4777, 0}, 0),
		TELEPORT_12(new String[] {"", ""}, new int[] {2525, 4777, 0}, 0);
		
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
		private PlayerKilling(final String[] teleportName, final int[] teleportCoordinates, int npcKills) {
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

