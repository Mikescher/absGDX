package de.samdev.absgdx.framework.util;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class DebugFormatter {

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

	public static String fmtF(float r, int decimalPointsPot) {
		if (r == (long)r || decimalPointsPot == 1)
			return "" + (long)r;
		else
			return "" + ((int)(r * decimalPointsPot) * 1f / decimalPointsPot);
	}

	public static String fmtD(double r, int decimalPointsPot) {
		if (r == (long)r || decimalPointsPot == 1)
			return "" + (long)r;
		else
			return "" + ((int)(r * decimalPointsPot) * 1d / decimalPointsPot);
	}
}
