package com.athena.model;

/**
 * Represents a single movement direction.
 * 
 * @author Graham
 */
public enum Direction {

	/**
	 * North movement.
	 */
	NORTH(1),

	/**
	 * North east movement.
	 */
	NORTH_EAST(2),

	/**
	 * East movement.
	 */
	EAST(4),

	/**
	 * South east movement.
	 */
	SOUTH_EAST(7),

	/**
	 * South movement.
	 */
	SOUTH(6),

	/**
	 * South west movement.
	 */
	SOUTH_WEST(5),

	/**
	 * West movement.
	 */
	WEST(3),

	/**
	 * North west movement.
	 */
	NORTH_WEST(0),

	/**
	 * No movement.
	 */
	NONE(-1);

	/**
	 * An empty direction array.
	 */
	public static final Direction[] EMPTY_DIRECTION_ARRAY = new Direction[0];

	/**
	 * Creates a direction from the differences between X and Y.
	 * 
	 * @param deltaX
	 *            The difference between two X coordinates.
	 * @param deltaY
	 *            The difference between two Y coordinates.
	 * @return The direction.
	 */
	public static Direction fromDeltas(int deltaX, int deltaY) {
		if (deltaY == 1) {
			if (deltaX == 1)
				return Direction.NORTH_EAST;
			else if (deltaX == 0)
				return Direction.NORTH;
			else
				return Direction.NORTH_WEST;
		} else if (deltaY == -1) {
			if (deltaX == 1)
				return Direction.SOUTH_EAST;
			else if (deltaX == 0)
				return Direction.SOUTH;
			else
				return Direction.SOUTH_WEST;
		} else if (deltaX == 1)
			return Direction.EAST;
		else if (deltaX == -1)
			return Direction.WEST;
		return Direction.NONE;
	}
	
	/**
	 * Checks if the direction represented by the two delta values can connect
	 * two points together in a single direction.
	 * 
	 * @param deltaX
	 *            The difference in X coordinates.
	 * @param deltaY
	 *            The difference in X coordinates.
	 * @return {@code true} if so, {@code false} if not.
	 */
	public static boolean isConnectable(int deltaX, int deltaY) {
		return Math.abs(deltaX) == Math.abs(deltaY) || deltaX == 0
				|| deltaY == 0;
	}

	/**
	 * The direction as an integer.
	 */
	private final int intValue;

	/**
	 * Creates the direction.
	 * 
	 * @param intValue
	 *            The direction as an integer.
	 */
	Direction(int intValue) {
		this.intValue = intValue;
	}

	public int[] getDirectionDelta() {
		return switch (this) {
			case NORTH -> new int[]{0, 1};
			case NORTH_EAST -> new int[]{1, 1};
			case EAST -> new int[]{1, 0};
			case SOUTH_EAST -> new int[]{1, -1};
			case SOUTH -> new int[]{0, -1};
			case SOUTH_WEST -> new int[]{-1, -1};
			case WEST -> new int[]{-1, 0};
			case NORTH_WEST -> new int[]{-1, 1};
			default -> new int[]{0, 0};
		};
	}

	/**
	 * Gets the direction as an integer which the client can understand.
	 * 
	 * @return The movement as an integer.
	 */
	public int toInteger() {
		return intValue;
	}
	public int getX() {
		return intValue;
	}
	public int getY() {
		return intValue;
	}

}