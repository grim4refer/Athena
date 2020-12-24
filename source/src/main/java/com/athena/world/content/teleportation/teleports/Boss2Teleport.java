package com.athena.world.content.teleportation.teleports;

import com.athena.model.Position;

import java.util.function.Consumer;

public enum Boss2Teleport implements Teleport {
	INFERNAL_GROUDON(new Position(1240, 1227), null),
	KingOfCaves(new Position(2535, 4655), null)


	;

	private Boss2Teleport(Position position,
                          String[] warning) {
		this.position = position;
		this.warning = warning;
	}
	private Boss2Teleport(Position position, String[] warning, Consumer consumer) {
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
