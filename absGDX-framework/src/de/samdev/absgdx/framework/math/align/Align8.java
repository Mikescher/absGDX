package de.samdev.absgdx.framework.math.align;

import com.badlogic.gdx.math.Vector2;

public enum Align8 {
	TOPLEFT,
	TOP,
	TOPRIGHT,
	RIGHT,
	BOTTOMRIGHT,
	BOTTOM,
	BOTTOMLEFT,
	LEFT;
	
	public Vector2 getDirectionVector()
	{
		switch (this) {
		case TOPLEFT:
			return new Vector2(-1, +1);
		case TOP:
			return new Vector2(00, +1);
		case TOPRIGHT:
			return new Vector2(+1, +1);
		case RIGHT:
			return new Vector2(+1, 00);
		case BOTTOMRIGHT:
			return new Vector2(+1, -1);
		case BOTTOM:
			return new Vector2(00, -1);
		case BOTTOMLEFT:
			return new Vector2(-1, -1);
		case LEFT:
			return new Vector2(-1, 00);

		default:
			throw new RuntimeException("Invalid enum value:" + this);
		}
	}
}
