package de.samdev.absgdx.tests;

import static org.junit.Assert.assertEquals;

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
		try {
			assertEquals(expected.x, actual.x, epsilon);
			assertEquals(expected.y, actual.y, epsilon);
		} catch (AssertionFailedError e) {
			throw new AssertionFailedError("expected:<" + expected.x + "|" + expected.y + "> but was:<" + actual.x + "|" + actual.y + ">" );
		}
	}

	public static void assertEqualsExt(Rectangle expected, Rectangle actual) {
		assertEqualsExt(expected, actual, 0.00001f);
	}
	
	public static void assertEqualsExt(Rectangle expected, Rectangle actual, float epsilon) {
		try {
			assertEquals(expected.x, actual.x, epsilon);
			assertEquals(expected.y, actual.y, epsilon);
			assertEquals(expected.width, actual.width, epsilon);
			assertEquals(expected.height, actual.height, epsilon);
		} catch (AssertionFailedError e) {
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
	
	//TODO UnitTests for: expMapScale
}