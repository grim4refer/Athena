package com.arlania.world.content.teleportation.teleports;

import com.arlania.model.Position;

import java.util.function.Consumer;

public enum BossTeleport implements Teleport {
	
	BANDOWS_AVA(new Position(2867, 9946), null),
	ABBADON(new Position(2516, 5173), null),
	BAPHOMET(new Position(2461, 10156), null),
	GUARDIAN(new Position(2489, 10147, 0), null),
	ABZYOU(new Position(2720, 9971), null),
	LEFOSH(new Position(3620, 3534), null),
	ALIEN(new Position(2420, 4690), null),
	THREE(new Position(2501, 2544, 2), null),
	PLANE_FREEZER(new Position(3490, 9947), null),
	DBZ(new Position(2706, 9632), null),
	BROLY(new Position(1941, 4687), null),
	NEX(new Position(2901, 5204), null),
	CURSE(new Position(2525, 4777), null),
	IKTOMI(new Position(1940, 5001), null),
	YANI(new Position(2272, 4693, 0), null),

	;

	private BossTeleport(Position position,
			String[] warning) {
		this.position = position;
		this.warning = warning;
	}
	private BossTeleport(Position position, String[] warning, Consumer consumer) {
		this.position = position;
		this.warning = warning;
	}
	
	private final Position position;
	
	private final String[] warning;
	@Override
	public String[] getWarning() {
		return warning;
	}

	@Override
	public Position getPosition() {
		return position;
	}
}
