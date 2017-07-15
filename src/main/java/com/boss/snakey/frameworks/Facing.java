package com.boss.snakey.frameworks;

public enum Facing {

	NORTH, EAST, SOUTH, WEST;
	
	public boolean isOpposite(Facing side) {
		switch (this) {
		case NORTH:
			return side == Facing.SOUTH ? true : false;
		case SOUTH:
			return side == Facing.NORTH ? true : false;
		case EAST:
			return side == Facing.WEST ? true : false;
		default:
			return side == Facing.EAST ? true : false;
		}
	}
}
