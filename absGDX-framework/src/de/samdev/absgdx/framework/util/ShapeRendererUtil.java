package de.samdev.absgdx.framework.util;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

/**
 * Additional methods for the shape renderer
 *
 */
public class ShapeRendererUtil {

	/**
	 * Renders an arrow
	 * 
	 * @param r the ShapeRenderer to use (must have AutoShapeType set to true)
	 * @param x1 the start x position
	 * @param y1 the start y position
	 * @param x2 the end x position
	 * @param y2 the end y position
	 * @param arrowdepth the height of the arrow (= baseline/2)
	 */
	public static void arrowLine(ShapeRenderer r, float x1, float y1, float x2, float y2, float arrowdepth) {
		if (x1 == x2 && y1 == y2) return;
		
		
		Vector2 v = new Vector2(x2, y2);
		v.sub(x1,  y1).limit(arrowdepth);
		Vector2 c = new Vector2(x2, y2).sub(v);
		v.rotate90(1);

		r.set(ShapeType.Line);
		r.line(x1, y1, x2, y2);
		r.set(ShapeType.Filled);
		r.triangle(x2, y2, c.x - v.x, c.y - v.y, c.x + v.x, c.y + v.y);
		r.set(ShapeType.Line);
	}

}
