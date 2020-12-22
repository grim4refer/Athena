package com.arlania.world.content.teleportation;

public class TeleportBosses extends Teleporting  {

	public static enum Bosses {
		TELEPORT_1(new String[] {"Bandos", "Avatar"}, new int[] {2867,9946,0}),
		TELEPORT_2(new String[] {"Abbadon", ""}, new int[] {2516,5173,0}),
		TELEPORT_3(new String[] {"Infernal", "Groudon"}, new int[] {1240,1227,0}),
		TELEPORT_4(new String[] {"Baphomet", ""}, new int[] {2461,10156,0}),
		TELEPORT_5(new String[] {"Yanille Guardian", ""}, new int[] {2489, 10147, 0}),
		TELEPORT_6(new String[] {"@red@Abyzou", "Heartwrencher"}, new int[] {2720, 9971, 0 }),
		TELEPORT_7(new String[] {"Le'Fosh", ""}, new int[] {3620, 3534, 0}),//3495, 9937, 0
		TELEPORT_8(new String[] {"Alien", ""}, new int[] {2420, 4690, 0}),
		TELEPORT_9(new String[] {"Vladimir", ""}, new int[] {2395, 4681, 0}),
		TELEPORT_10(new String[] {"@blu@3", "@red@Brothers"}, new int[] {2504, 2544, 2}),
		TELEPORT_11(new String[] {"Plane", "Freezer"}, new int[] {3490, 9947, 0}),
		TELEPORT_12(new String[] {"Dragon Ball", "Zone"}, new int[] {2706, 9632, 0});
		//TELEPORT_13(new String[] {"f", ""}, new int[] {});
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
		private Bosses(final String[] teleportName, final int[] teleportCoordinates) {
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
