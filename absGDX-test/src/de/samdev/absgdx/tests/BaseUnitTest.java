package de.samdev.absgdx.tests;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

public abstract class BaseUnitTest extends TestCase {

	public static void assertEquals(Vector2 expected, Vector2 actual) {
		assertEquals(expected, actual, 0.00001f);
	}

	public static void assertEquals(Vector2 expected, Vector2 actual, float epsilon) {
		try {
			assertEquals(expected.x, actual.y, epsilon);
			assertEquals(expected.x, actual.y, epsilon);
		} catch (AssertionFailedError e) {
			throw new AssertionFailedError("expected:<" + expected.x + "|" + expected.y + "> but was:<" + actual.x + "|" + actual.y + ">" );
		}
	}

	public static void assertEquals(Rectangle expected, Rectangle actual) {
		assertEquals(expected, actual, 0.00001f);
	}
	
	public static void assertEquals(Rectangle expected, Rectangle actual, float epsilon) {
		try {
			assertEquals(expected.x, actual.x, epsilon);
			assertEquals(expected.y, actual.y, epsilon);
			assertEquals(expected.width, actual.width, epsilon);
			assertEquals(expected.height, actual.height, epsilon);
		} catch (AssertionFailedError e) {
			throw new AssertionFailedError("expected:<" + expected + "> but was:<" + actual + ">" );
		}
	}

}
