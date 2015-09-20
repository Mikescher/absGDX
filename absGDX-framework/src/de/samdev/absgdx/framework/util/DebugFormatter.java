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
 * An utility class for formatting values (used for debugging)
 *
 */
public class DebugFormatter {

	/**
	 * Formats an Rectangle
	 * 
	 * @param r the rectangle
	 * @param decimalPoints the amount of decimal places
	 * 
	 * @return the rectangle formatted as an debug string
	 */
	public static String fmtRectangle(Rectangle r, int decimalPoints) {
		if (r == null) return "NULL";
		
		return String.format(
				"[ %s | %s | %s | %s ]", 
				fmtF(r.x, decimalPoints), 
				fmtF(r.y, decimalPoints), 
				fmtF(r.width, decimalPoints), 
				fmtF(r.height, decimalPoints));
	}

	/**
	 * Formats an Vector2
	 * 
	 * @param r the Vector
	 * @param decimalPoints the amount of decimal places

	 * @return the vector formatted as an debug string
	 */
	public static String fmtV2(Vector2 r, int decimalPoints) {
		if (r == null) return "NULL";
		
		return String.format(
				"< %s | %s >", 
				fmtF(r.x, decimalPoints), 
				fmtF(r.y, decimalPoints));
	}

	/**
	 * Formats an Vector3
	 * 
	 * @param r the Vector
	 * @param decimalPoints the amount of decimal places

	 * @return the vector formatted as an debug string
	 */
	public static String fmtV3(Vector3 r, int decimalPoints) {
		if (r == null) return "NULL";
		
		return String.format(
				"< %s | %s | %s >", 
				fmtF(r.x, decimalPoints), 
				fmtF(r.y, decimalPoints), 
				fmtF(r.z, decimalPoints));
	}

	/**
	 * formats an float number
	 * 
	 * @param r the number
	 * @param decimalPoints the amount of decimal places

	 * @return the float formatted as an debug string
	 */
	public static String fmtF(float r, int decimalPoints) {
		return fmtF(r, decimalPoints, false);
	}
	
	/**
	 * formats an float number
	 * 
	 * @param r the number
	 * @param decimalPoints the amount of decimal places
	 * @param forceZeroes force appended zeroes to fill decimal places

	 * @return the float formatted as an debug string
	 */
	public static String fmtF(float r, int decimalPoints, boolean forceZeroes) {
		if (forceZeroes) {
			return String.format("%,."+decimalPoints+"f", r);
		}
		
		if (r == (long)r || decimalPoints == 1)
		{
			return "" + (long)r;
		} else {
			int decpot = 1;
			for (int i = 0; i < decimalPoints; i++) decpot *= 10;
			
			return "" + ((int)(r * decpot) * 1f / decpot);
		}
	}

	/**
	 * formats an double number
	 * 
	 * @param r the number
	 * @param decimalPoints the amount of decimal places

	 * @return the double formatted as an debug string
	 */
	public static String fmtD(double r, int decimalPoints) {
		return fmtD(r, decimalPoints, false);
	}
	
	/**
	 * formats an double number
	 * 
	 * @param r the number
	 * @param decimalPoints the amount of decimal places
	 * @param forceZeroes force appended zeroes to fill decimal places

	 * @return the double formatted as an debug string
	 */
	public static String fmtD(double r, int decimalPoints, boolean forceZeroes) {
		if (forceZeroes) {
			return String.format("%,."+decimalPoints+"f", r);
		}
		
		if (r == (long)r || decimalPoints == 1)
		{
			return "" + (long)r;
		} else {
			int decpot = 1;
			for (int i = 0; i < decimalPoints; i++) decpot *= 10;
			
			return "" + ((int)(r * decpot) * 1f / decpot);
		}
	}

	/**
	 * Simply return the integer as string 
	 * 
	 * @param  r the number
	 * @return the integer formatted as an base-10 string
	 */
	public static String fmtI(int r) {
		return "" + r;
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
	 * @param decimalPoints the amount of decimal places

	 * @return the geometry formatted as an debug string
	 */
	public static String fmtGeometry(CollisionGeometry tileGeo, int decimalPoints) {
		if (tileGeo instanceof CollisionBox) {
			CollisionBox g = (CollisionBox) tileGeo;
			return fmtRectangle(new Rectangle(g.getX(), g.getY(), g.width, g.height), decimalPoints);
		} else if (tileGeo instanceof CollisionCircle) {
			CollisionCircle g = (CollisionCircle) tileGeo;
			return fmtV2(g.getCenter(), decimalPoints) + ": " +  fmtF(g.radius, decimalPoints);
		} else if (tileGeo instanceof CollisionTriangle) {
			CollisionTriangle g = (CollisionTriangle) tileGeo;
			return "{" + fmtV2(g.getPoint1(), decimalPoints) + "," + fmtV2(g.getPoint2(), decimalPoints) + "," +fmtV2(g.getPoint3(), decimalPoints) + "}";
		} else {
			return "{ #UNDEFINIED# }";
		}
	}
}
