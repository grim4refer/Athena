package com.athena.world.content.teleportation.teleports;


import com.athena.model.Position;

public enum MinigamesTeleports implements Teleport {

	;
	
	private MinigamesTeleports(Position position,
                               String[] warning) {
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
