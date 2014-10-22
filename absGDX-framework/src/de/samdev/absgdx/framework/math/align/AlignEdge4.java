package de.samdev.absgdx.framework.math.align;

import com.badlogic.gdx.math.Vector2;

public enum AlignEdge4 {
	TOP,
	LEFT,
	RIGHT,
	BOTTOM;
	
	public Vector2 getDirectionVector()
	{
		switch (this) {
		case TOP:
			return new Vector2(00, +1);
		case RIGHT:
			return new Vector2(+1, 00);
		case BOTTOM:
			return new Vector2(00, -1);
		case LEFT:
			return new Vector2(-1, 00);

		default:
			return null;
		}
	}
}
