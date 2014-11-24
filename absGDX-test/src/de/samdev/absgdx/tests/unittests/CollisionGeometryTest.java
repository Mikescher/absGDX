package de.samdev.absgdx.tests.unittests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionBox;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionCircle;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionTriangle;
import de.samdev.absgdx.framework.math.FloatMath;
import de.samdev.absgdx.tests.BaseUnitTest;

public class CollisionGeometryTest extends BaseUnitTest {

	@Test
	public void testCollisionCircle() {
		CollisionCircle c = new CollisionCircle(null, 1f);
		
		assertEquals(1f, c.radius, 0f);
	}

	@Test
	public void testCollisionBox() {
		CollisionBox b = new CollisionBox(null, 1f, 1f);
		
		assertEquals(1, b.width, 0f);
		assertEquals(1, b.height, 0f);
		assertEquals(1, b.area(), 0f);
	}

	@Test
	public void testCollisionTriangle_1() {
		CollisionTriangle t1 = new CollisionTriangle(null, new Vector2(0, 0), new Vector2(1, 0), new Vector2(0, 1));
		
		assertEquals(FloatMath.fsqrt(5/9.0), t1.circumRadius, 0.0001f);
		
		assertEquals(-1/3f, t1.point1_x, 0.0001f);
		assertEquals(-1/3f, t1.point1_y, 0.0001f);

		assertEquals(+2/3f, t1.point2_x, 0.0001f);
		assertEquals(-1/3f, t1.point2_y, 0.0001f);

		assertEquals(-1/3f, t1.point3_x, 0.0001f);
		assertEquals(+2/3f, t1.point3_y, 0.0001f);
		
		float len = t1.circumRadius;
		assertTrue(len >= new Vector2(t1.point1_x, t1.point1_y).len());
		assertTrue(len >=  new Vector2(t1.point2_x, t1.point2_y).len());
		assertTrue(len >=  new Vector2(t1.point3_x, t1.point3_y).len());
		assertEquals(len, FloatMath.fmax(new Vector2(t1.point1_x, t1.point1_y).len(), new Vector2(t1.point2_x, t1.point2_y).len(), new Vector2(t1.point3_x, t1.point3_y).len()), 0.0001f);

		assertEquals(len, t1.circumRadius, 0.0001f);
		
		assertEquals(0.5f, t1.area(), 0.0001f);
	}

	@Test
	public void testCollisionTriangle_2() {
		CollisionTriangle t1 = new CollisionTriangle(null, new Vector2(0, 0), new Vector2(0, 10), new Vector2(10, 0));
		
		assertEquals(FloatMath.fsqrt((5*10*10)/9.0), t1.circumRadius, 0.0001f);
		
		float len = t1.circumRadius;
		assertTrue(len >= new Vector2(t1.point1_x, t1.point1_y).len());
		assertTrue(len >=  new Vector2(t1.point2_x, t1.point2_y).len());
		assertTrue(len >=  new Vector2(t1.point3_x, t1.point3_y).len());
		assertEquals(len, FloatMath.fmax(new Vector2(t1.point1_x, t1.point1_y).len(), new Vector2(t1.point2_x, t1.point2_y).len(), new Vector2(t1.point3_x, t1.point3_y).len()), 0.0001f);

		assertEquals(len, t1.circumRadius, 0.0001f);
		
		assertEquals(50f, t1.area(), 0.0001f);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testCollisionTriangle_3() {
		new CollisionTriangle(null, new Vector2(0, 0), new Vector2(10, 10), new Vector2(5, 5));
	}

	@Test
	public void testCollisionTriangle_4() {
		CollisionTriangle t1 = new CollisionTriangle(null, new Vector2(99, 12), new Vector2(-20, 10), new Vector2(10, 11.11f));
		
		float len = t1.circumRadius;
		assertTrue(len >= new Vector2(t1.point1_x, t1.point1_y).len());
		assertTrue(len >=  new Vector2(t1.point2_x, t1.point2_y).len());
		assertTrue(len >=  new Vector2(t1.point3_x, t1.point3_y).len());
		assertEquals(len, FloatMath.fmax(new Vector2(t1.point1_x, t1.point1_y).len(), new Vector2(t1.point2_x, t1.point2_y).len(), new Vector2(t1.point3_x, t1.point3_y).len()), 0.0001f);

		assertEquals(len, t1.circumRadius, 0.0001f);
	}

	@Test
	public void testCollisionTriangle_5() {
		CollisionTriangle t1 = new CollisionTriangle(null, new Vector2(-20, -88.7654321f), new Vector2(-0.555555f, 42), new Vector2(89.89898989f, -40.00001f));
		
		float len = t1.circumRadius;
		assertTrue(len >= new Vector2(t1.point1_x, t1.point1_y).len());
		assertTrue(len >= new Vector2(t1.point2_x, t1.point2_y).len());
		assertTrue(len >= new Vector2(t1.point3_x, t1.point3_y).len());
		assertEquals(len, FloatMath.fmax(new Vector2(t1.point1_x, t1.point1_y).len(), new Vector2(t1.point2_x, t1.point2_y).len(), new Vector2(t1.point3_x, t1.point3_y).len()), 0.0001f);

		assertEquals(len, t1.circumRadius, 0.0001f);
		
		CollisionTriangle tsub1 = new CollisionTriangle(null, new Vector2(), new Vector2(t1.point1_x, t1.point1_y), new Vector2(t1.point2_x, t1.point2_y));
		CollisionTriangle tsub2 = new CollisionTriangle(null, new Vector2(), new Vector2(t1.point2_x, t1.point2_y), new Vector2(t1.point3_x, t1.point3_y));
		CollisionTriangle tsub3 = new CollisionTriangle(null, new Vector2(), new Vector2(t1.point3_x, t1.point3_y), new Vector2(t1.point1_x, t1.point1_y));

		assertEquals(t1.area(), tsub1.area() + tsub2.area() + tsub3.area(), 0.001f);
	}

	@Test
	public void testsLSAEJDF_WEQJFUOOOWEAN() { // TODO FUCKING REM ME !!!!!!!!!1
		CollisionCircle c = new CollisionCircle(null, 1/2f);
		CollisionTriangle t = new CollisionTriangle(null, new Vector2(0, 0), new Vector2(1, 0), new Vector2(1, 1));
		
		c.setCenter(1/2f, 1/2f);		
		t.setCenter(4f + 2/3f, 1/3f);
		System.out.println("> -3.5 = " + t.getXTouchDistance(c));
		
		
		c.setCenter(1/2f, 0f);		
		t.setCenter(4f + 2/3f, 1/3f);
		System.out.println("> -3 = " + t.getXTouchDistance(c));
	}

}
