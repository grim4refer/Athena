package com.athena.world.content.teleportation.teleports;

import com.athena.model.Position;

public enum TrainingTeleport implements Teleport {
	
	ZIRCONIS(new Position(2505, 2507, 1), null, 0),
	
	ARTILLERY(new Position(2506, 2523, 1), null, 10),
	
	SKIADRUM(new Position(2503, 2539, 1), null, 20),

	WEISSLOGIA(new Position(2521, 2551, 1), null, 40),
	
	ALLAN(new Position(2548, 2551, 1), null, 75),

	AURELIA(new Position(2568, 2550, 1), null, 150),

	BELERION(new Position(2551, 2517, 1), null, 300),

	LEVIATHAN(new Position(2552, 2517, 2), null, 600),

	DIRE_WOLF(new Position(2551, 2534, 2), null, 1200),
	
	YETI(new Position(2549, 2551, 2), null, 2400),
	
	CHARMING_IMPS(new Position(2521, 2551, 2), null, 0),

	GANO(new Position(3170, 2993, 0), null, 0)
	;
	
	private TrainingTeleport(Position position,
			String[] warning, int kills) {
		this.position = position;
		this.warning = warning;
		this.requirement = kills;
	}
	
	private final Position position;
	
	private final String[] warning;

	private final int requirement;
	@Override
	public String[] getWarning() {
		return warning;
	}

	@Override
	public Position getPosition() {
		return position;
	}

	public int getRequirement() {
		return requirement;
	}
}
