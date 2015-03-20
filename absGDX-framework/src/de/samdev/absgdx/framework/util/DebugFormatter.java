package de.samdev.absgdx.framework.util;

import java.util.HashMap;
import java.util.Map.Entry;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionBox;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionCircle;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionGeometry;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionTriangle;
import de.samdev.absgdx.framework.util.tiled.TmxMapLoader;

/**
 * An util class for formatting values (used for debugging)
 *
 */
public class DebugFormatter {

	/**
	 * Formats an Rectangle
	 * 
	 * @param r the rectangle
	 * @param decimalPointsPot the amount of decimal places
	 * 
	 * @return the rectangle formatted as an debug string
	 */
	public static String fmtRectangle(Rectangle r, int decimalPointsPot) {
		if (r == null) return "NULL";
		
		StringBuilder b = new StringBuilder();
		
		b.append("[ ");
		
		if (r.x == (long)r.x || decimalPointsPot == 1)
			b.append((long)r.x);
		else
			b.append((int)(r.x * decimalPointsPot) * 1f / decimalPointsPot);

		b.append(" | ");
		
		if (r.x == (long)r.y || decimalPointsPot == 1)
			b.append((long)r.y);
		else
			b.append((int)(r.y * decimalPointsPot) * 1f / decimalPointsPot);

		b.append(" | ");
		
		if (r.x == (long)r.width || decimalPointsPot == 1)
			b.append((long)r.width);
		else
			b.append((int)(r.width * decimalPointsPot) * 1f / decimalPointsPot);

		b.append(" | ");
		
		if (r.x == (long)r.height || decimalPointsPot == 1)
			b.append((long)r.height);
		else
			b.append((int)(r.height * decimalPointsPot) * 1f / decimalPointsPot);

		b.append(" ]");
		
		return b.toString();
	}

	/**
	 * Formats an Vector2
	 * 
	 * @param r the Vector
	 * @param decimalPointsPot the amount of decimal places

	 * @return the vector formatted as an debug string
	 */
	public static String fmtV2(Vector2 r, int decimalPointsPot) {
		if (r == null) return "NULL";
		
		StringBuilder b = new StringBuilder();
		
		b.append("< ");
		
		if (r.x == (long)r.x || decimalPointsPot == 1)
			b.append((long)r.x);
		else
			b.append((int)(r.x * decimalPointsPot) * 1f / decimalPointsPot);

		b.append(" | ");
		
		if (r.x == (long)r.y || decimalPointsPot == 1)
			b.append((long)r.y);
		else
			b.append((int)(r.y * decimalPointsPot) * 1f / decimalPointsPot);

		b.append(" >");
		
		return b.toString();
	}

	/**
	 * Formats an Vector3
	 * 
	 * @param r the Vector
	 * @param decimalPointsPot the amount of decimal places

	 * @return the vector formatted as an debug string
	 */
	public static String fmtV3(Vector3 r, int decimalPointsPot) {
		if (r == null) return "NULL";
		
		StringBuilder b = new StringBuilder();
		
		b.append("< ");
		
		if (r.x == (long)r.x || decimalPointsPot == 1)
			b.append((long)r.x);
		else
			b.append((int)(r.x * decimalPointsPot) * 1f / decimalPointsPot);

		b.append(" | ");
		
		if (r.x == (long)r.y || decimalPointsPot == 1)
			b.append((long)r.y);
		else
			b.append((int)(r.y * decimalPointsPot) * 1f / decimalPointsPot);

		b.append(" | ");
		
		if (r.x == (long)r.z || decimalPointsPot == 1)
			b.append((long)r.z);
		else
			b.append((int)(r.z * decimalPointsPot) * 1f / decimalPointsPot);

		b.append(" >");
		
		return b.toString();
	}

	/**
	 * formats an float number
	 * 
	 * @param r the number
	 * @param decimalPointsPot the amount of decimal places

	 * @return the float formatted as an debug string
	 */
	public static String fmtF(float r, int decimalPointsPot) {
		if (r == (long)r || decimalPointsPot == 1)
			return "" + (long)r;
		else
			return "" + ((int)(r * decimalPointsPot) * 1f / decimalPointsPot);
	}

	/**
	 * formats an double number
	 * 
	 * @param r the number
	 * @param decimalPointsPot the amount of decimal places

	 * @return the double formatted as an debug string
	 */
	public static String fmtD(double r, int decimalPointsPot) {
		if (r == (long)r || decimalPointsPot == 1)
			return "" + (long)r;
		else
			return "" + ((int)(r * decimalPointsPot) * 1d / decimalPointsPot);
	}
	
	/**
	 * formats the propertiesMap from an TmxMapLoader
	 * 
	 * @param properties the map
	 * @param limit the max amount of properties in the output

	 * @return the map formatted as an debug string
	 */
	public static String fmtPropertiesMap(HashMap<String, String> properties, int limit) {
		if (properties == null) return "NULL";
		
		StringBuilder b = new StringBuilder();
		
		boolean first = true;
		for (Entry<String, String> entry : properties.entrySet()) {
			if (entry.getKey().equals(TmxMapLoader.PROPERTY_COMPRESSION)) continue;
			if (entry.getKey().equals(TmxMapLoader.PROPERTY_ENCODING)) continue;
			if (entry.getKey().equals(TmxMapLoader.PROPERTY_LAYER_WIDTH)) continue;
			if (entry.getKey().equals(TmxMapLoader.PROPERTY_LAYER_HEIGHT)) continue;
			if (entry.getKey().equals(TmxMapLoader.PROPERTY_LAYER_LEVEL)) continue;
			if (entry.getKey().equals(TmxMapLoader.PROPERTY_MAP_WIDTH)) continue;
			if (entry.getKey().equals(TmxMapLoader.PROPERTY_MAP_HEIGHT)) continue;
			
			if (! first) b.append(" ; ");
			b.append(entry.getKey().replaceAll("\\[absGDX\\]-", "") + "=" + entry.getValue());
			first = false;
			
			if (--limit == 0) {
				b.append(" ...");
				return b.toString();
			}
		}
		
		return b.toString();
	}

	/**
	 * Formats an CollisionGeometry
	 * 
	 * @param tileGeo the geometry
	 * @param decimalPointsPot the amount of decimal places

	 * @return the geometry formatted as an debug string
	 */
	public static String fmtGeometry(CollisionGeometry tileGeo, int decimalPointsPot) {
		if (tileGeo instanceof CollisionBox) {
			CollisionBox g = (CollisionBox) tileGeo;
			return fmtRectangle(new Rectangle(g.getX(), g.getY(), g.width, g.height), decimalPointsPot);
		} else if (tileGeo instanceof CollisionCircle) {
			CollisionCircle g = (CollisionCircle) tileGeo;
			return fmtV2(g.getCenter(), decimalPointsPot) + ": " +  fmtF(g.radius, decimalPointsPot);
		} else if (tileGeo instanceof CollisionTriangle) {
			CollisionTriangle g = (CollisionTriangle) tileGeo;
			return "{" + fmtV2(g.getPoint1(), decimalPointsPot) + "," + fmtV2(g.getPoint2(), decimalPointsPot) + "," +fmtV2(g.getPoint3(), decimalPointsPot) + "}";
		} else {
			return "{ #UNDEFINIED# }";
		}
	}
}
