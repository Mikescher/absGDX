package de.samdev.absgdx.framework.math.align;

import com.badlogic.gdx.math.Vector2;

public enum AlignCorner4 {
	TOPLEFT,
	TOPRIGHT,
	BOTTOMRIGHT,
	BOTTOMLEFT;
	
	public Vector2 getDirectionVector()
	{
		switch (this) {
		case TOPLEFT:
			return new Vector2(-1, +1);
		case TOPRIGHT:
			return new Vector2(+1, +1);
		case BOTTOMRIGHT:
			return new Vector2(+1, -1);
		case BOTTOMLEFT:
			return new Vector2(-1, -1);

		default:
			throw new RuntimeException("Invalid enum value:" + this);
		}
	}
}
