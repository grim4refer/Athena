package com.athena.world.content.teleportation;

public class TeleportMinigames extends Teleporting {
	public static enum Minigames {
		TELEPORT_1(new String[] {"@mag@Solo", "@cya@Raids"}, new int[] {2535,2501,1}),
		TELEPORT_2(new String[] {"Pest", "Control"}, new int[] {2658,2660,0}),
		TELEPORT_3(new String[] {"@red@Point", "@red@Zone"}, new int[] {2766,2799,0}),
		TELEPORT_4(new String[] {"Warrior's", "Test"}, new int[] {2841, 3538, 0}),
		TELEPORT_5(new String[] {"Gambler", "Zone"}, new int[] {2737, 3472, 0}),
		TELEPORT_6(new String[] {"AFK", "Evil Tree"}, new int[] {2535, 2531, 1}),
		TELEPORT_7(new String[] {"@bla@Bad", "Santa"}, new int[] {2977, 9519, 1}),
		TELEPORT_8(new String[] {"", ""}, new int[] {}),
		TELEPORT_9(new String[] {"", ""}, new int[] {}),
		TELEPORT_10(new String[] {" ", " "}, new int[] {}),
		TELEPORT_11(new String[] {" ", " "}, new int[] {}),
		TELEPORT_12(new String[] {" ", " "}, new int[] {});

		
		/**
		 * Initializing the teleport names.
		 */
		private String[] teleportName;
		/**
		 * Initializing the teleport coordinates.
		 */
		private int[] teleportCoordinates;
		
		/**
		 * Constructing the enumerator.
		 * @param teleportName
		 * 			The name of the teleport.
		 * @param teleportCoordinates
		 * 			The coordinates of the teleport.
		 */
		private Minigames(final String[] teleportName, final int[] teleportCoordinates) {
			this.teleportName = teleportName;
			this.teleportCoordinates = teleportCoordinates;
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
	}

}
