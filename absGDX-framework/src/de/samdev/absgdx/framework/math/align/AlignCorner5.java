package de.samdev.absgdx.framework.math.align;

import com.badlogic.gdx.math.Vector2;

public enum AlignCorner5 {
	TOPLEFT,
	TOPRIGHT,
	BOTTOMRIGHT,
	BOTTOMLEFT,
	CENTER;
	
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
		case CENTER:
			return new Vector2(00, 00);

		default:
			throw new RuntimeException("Invalid enum value:" + this);
		}
	}
}
