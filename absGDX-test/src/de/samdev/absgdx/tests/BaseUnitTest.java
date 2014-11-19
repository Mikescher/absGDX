package de.samdev.absgdx.tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import junit.framework.AssertionFailedError;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class BaseUnitTest {

	public static void assertEqualsExt(Vector2 expected, Vector2 actual) {
		assertEqualsExt(expected, actual, 0.00001f);
	}

	public static void assertEqualsExt(Vector2 expected, Vector2 actual, float epsilon) {
		boolean a = !fcomp(expected.x, actual.x, epsilon);
		boolean b = !fcomp(expected.y, actual.y, epsilon);
		
		if (a || b) {
			throw new AssertionFailedError("expected:<" + expected.x + "|" + expected.y + "> but was:<" + actual.x + "|" + actual.y + ">" );
		}
	}

	public static void assertEqualsExt(Rectangle expected, Rectangle actual) {
		assertEqualsExt(expected, actual, 0.00001f);
	}
	
	public static void assertEqualsExt(Rectangle expected, Rectangle actual, float epsilon) {
		boolean a = !fcomp(expected.x, actual.x, epsilon);
		boolean b = !fcomp(expected.y, actual.y, epsilon);
		boolean c = !fcomp(expected.width, actual.width, epsilon);
		boolean d = !fcomp(expected.height, actual.height, epsilon);
		
		if (a || b || c || d) {
			throw new AssertionFailedError("expected:<" + expected + "> but was:<" + actual + ">" );
		}
	}

	public String readTextFileFromResource(String resourcename, Class<?> c) throws IOException {
		BufferedReader reader = null;
		StringBuffer content = new StringBuffer();
		
		try {
			InputStream is = c.getResourceAsStream(resourcename);
			if (is == null) throw new IOException();
			reader = new BufferedReader(new InputStreamReader(is));
			String s = null;
			boolean first = true;

			while ((s = reader.readLine()) != null) {
				if (!first) {
					content.append(System.getProperty("line.separator")); //$NON-NLS-1$
				}
				content.append(s);
				first = false;
			}
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		return content.toString();
	}
	
	private static boolean fcomp(float expected, float actual, float delta) {
		return Float.compare(expected, actual) == 0 || (Math.abs(expected - actual) <= delta);
	}
}
