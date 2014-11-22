package de.samdev.absgdx.tests.unittests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionMap;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionBox;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionCircle;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionTriangle;
import de.samdev.absgdx.tests.BaseUnitTest;
import de.samdev.absgdx.tests.dummy.DummyCollisionBox;
import de.samdev.absgdx.tests.dummy.DummyCollisionCircle;
import de.samdev.absgdx.tests.dummy.DummyCollisionMap;
import de.samdev.absgdx.tests.dummy.DummyCollisionTriangle;

public class CollisionMapTest extends BaseUnitTest {

	@Test
	public void testAddGeometryCircle() {
		CollisionMap m = new DummyCollisionMap(100, 100);
		boolean[][] compare = new boolean[100][100];

		boolean[][] compareOuter = new boolean[3][3];

		CollisionCircle cg = new DummyCollisionCircle(2f);
		cg.setCenter(13.5f, 13.5f);

		m.addGeometry(cg);

		for (int x = 11; x <= 15; x++) {
			for (int y = 11; y <= 15; y++) {
				compare[x][y] = true;
			}
		}

		assertArrayEquals(compare, collMapToBoolMap(m));
		assertArrayEquals(compareOuter, collMapToBoolMapOuter(m));
		assertEquals(null, m.outerBorder[1][1]);
	}

	@Test
	public void testAddGeometryCircleBorder() {
		CollisionMap m = new DummyCollisionMap(100, 100);
		boolean[][] compare = new boolean[100][100];

		boolean[][] compareOuter = new boolean[3][3];

		CollisionCircle cg = new DummyCollisionCircle(2f);
		cg.setCenter(99.5f, 50.5f);

		m.addGeometry(cg);

		for (int x = 97; x <= 99; x++) {
			for (int y = 48; y <= 52; y++) {
				compare[x][y] = true;
			}
		}
		compareOuter[2][1] = true;

		assertArrayEquals(compare, collMapToBoolMap(m));
		assertArrayEquals(compareOuter, collMapToBoolMapOuter(m));
		assertEquals(null, m.outerBorder[1][1]);
	}
	
	@Test
	public void testMoveGeometryCircle() {
		CollisionMap m = new DummyCollisionMap(100, 100);
		boolean[][] compare = new boolean[100][100];

		boolean[][] compareOuter = new boolean[3][3];

		CollisionCircle cg = new DummyCollisionCircle(2f);
		cg.setCenter(55.5f, 55.5f);

		m.addGeometry(cg);

		cg.setCenter(13.5f, 13.5f);
		m.moveGeometry(55.5f, 55.5f, cg);

		for (int x = 11; x <= 15; x++) {
			for (int y = 11; y <= 15; y++) {
				compare[x][y] = true;
			}
		}

		assertArrayEquals(compare, collMapToBoolMap(m));
		assertArrayEquals(compareOuter, collMapToBoolMapOuter(m));
		assertEquals(null, m.outerBorder[1][1]);
	}

	@Test
	public void testMoveGeometryCircleBorder() {
		CollisionMap m = new DummyCollisionMap(100, 100);
		boolean[][] compare = new boolean[100][100];

		boolean[][] compareOuter = new boolean[3][3];

		CollisionCircle cg = new DummyCollisionCircle(2f);
		cg.setCenter(99.5f, 50.5f);

		m.addGeometry(cg);

		cg.setCenter(99.5f, 50.5f);
		m.moveGeometry(99.5f, 50.5f, cg);

		for (int x = 97; x <= 99; x++) {
			for (int y = 48; y <= 52; y++) {
				compare[x][y] = true;
			}
		}
		compareOuter[2][1] = true;

		assertArrayEquals(compare, collMapToBoolMap(m));
		assertArrayEquals(compareOuter, collMapToBoolMapOuter(m));
		assertEquals(null, m.outerBorder[1][1]);
	}

	//#########################################################################
	
	@Test
	public void testAddGeometryBox() {
		CollisionMap m = new DummyCollisionMap(100, 100);
		boolean[][] compare = new boolean[100][100];

		boolean[][] compareOuter = new boolean[3][3];

		CollisionBox cg = new DummyCollisionBox(2f, 4f);
		cg.setCenter(43.5f, 43.5f);

		m.addGeometry(cg);

		for (int x = 40; x <= 46; x++) {
			for (int y = 40; y <= 46; y++) {
				compare[x][y] = true;
			}
		}

		assertArrayEquals(compare, collMapToBoolMap(m));
		assertArrayEquals(compareOuter, collMapToBoolMapOuter(m));
		assertEquals(null, m.outerBorder[1][1]);
	}

	@Test
	public void testAddGeometryBoxBorder() {
		CollisionMap m = new DummyCollisionMap(100, 100);
		boolean[][] compare = new boolean[100][100];

		boolean[][] compareOuter = new boolean[3][3];

		CollisionBox cg = new DummyCollisionBox(1f, 1f);
		cg.setCenter(0.5f, 0.5f);

		m.addGeometry(cg);

		for (int x = 0; x <= 1; x++) {
			for (int y = 0; y <= 1; y++) {
				compare[x][y] = true;
			}
		}

		compareOuter[0][1] = true;
		compareOuter[1][0] = true;
		compareOuter[0][0] = true;
		
		assertArrayEquals(compare, collMapToBoolMap(m));
		assertArrayEquals(compareOuter, collMapToBoolMapOuter(m));
		assertEquals(null, m.outerBorder[1][1]);
	}

	@Test
	public void testMoveGeometryBox() {
		CollisionMap m = new DummyCollisionMap(100, 100);
		boolean[][] compare = new boolean[100][100];

		boolean[][] compareOuter = new boolean[3][3];

		CollisionBox cg = new DummyCollisionBox(2f, 4f);
		cg.setCenter(0f, 0f);

		m.addGeometry(cg);

		cg.setCenter(43.5f, 43.5f);
		m.moveGeometry(0f, 0f, cg);

		for (int x = 40; x <= 46; x++) {
			for (int y = 40; y <= 46; y++) {
				compare[x][y] = true;
			}
		}

		assertArrayEquals(compare, collMapToBoolMap(m));
		assertArrayEquals(compareOuter, collMapToBoolMapOuter(m));
		assertEquals(null, m.outerBorder[1][1]);
	}

	@Test
	public void testMoveGeometryBoxBorder() {
		CollisionMap m = new DummyCollisionMap(100, 100);
		boolean[][] compare = new boolean[100][100];

		boolean[][] compareOuter = new boolean[3][3];

		CollisionBox cg = new DummyCollisionBox(1f, 1f);
		cg.setCenter(110f, 110f);

		m.addGeometry(cg);

		cg.setCenter(0.5f, 0.5f);
		m.moveGeometry(110f, 110f, cg);

		for (int x = 0; x <= 1; x++) {
			for (int y = 0; y <= 1; y++) {
				compare[x][y] = true;
			}
		}

		compareOuter[0][1] = true;
		compareOuter[1][0] = true;
		compareOuter[0][0] = true;

		assertArrayEquals(compare, collMapToBoolMap(m));
		assertArrayEquals(compareOuter, collMapToBoolMapOuter(m));
		assertEquals(null, m.outerBorder[1][1]);
	}

	//#########################################################################

	@Test
	public void testAddGeometryTriangle() {
		CollisionMap m = new DummyCollisionMap(100, 100);
		boolean[][] compare = new boolean[100][100];

		boolean[][] compareOuter = new boolean[3][3];

		CollisionTriangle cg = new DummyCollisionTriangle(0f, 0f,   1f, 0f,   0f, 1f);
		cg.setCenter(13.5f, 13.5f);

		m.addGeometry(cg);

		for (int x = 12; x <= 14; x++) {
			for (int y = 12; y <= 14; y++) {
				compare[x][y] = true;
			}
		}

		assertArrayEquals(compare, collMapToBoolMap(m));
		assertArrayEquals(compareOuter, collMapToBoolMapOuter(m));
		assertEquals(null, m.outerBorder[1][1]);
	}

	@Test
	public void testAddGeometryTriangleBorder() {
		CollisionMap m = new DummyCollisionMap(100, 100);
		boolean[][] compare = new boolean[100][100];

		boolean[][] compareOuter = new boolean[3][3];

		CollisionTriangle cg = new DummyCollisionTriangle(0f, 0f,   1f, 0f,   0f, 1f);
		cg.setCenter(99.5f, 50.5f);

		m.addGeometry(cg);

		for (int x = 98; x <= 99; x++) {
			for (int y = 49; y <= 51; y++) {
				compare[x][y] = true;
			}
		}
		compareOuter[2][1] = true;

		assertArrayEquals(compare, collMapToBoolMap(m));
		assertArrayEquals(compareOuter, collMapToBoolMapOuter(m));
		assertEquals(null, m.outerBorder[1][1]);
	}
	
	@Test
	public void testMoveGeometryTriangle() {
		CollisionMap m = new DummyCollisionMap(100, 100);
		boolean[][] compare = new boolean[100][100];

		boolean[][] compareOuter = new boolean[3][3];

		CollisionTriangle cg = new DummyCollisionTriangle(0f, 0f,   1f, 0f,   0f, 1f);
		cg.setCenter(55.5f, 55.5f);

		m.addGeometry(cg);

		cg.setCenter(13.5f, 13.5f);
		m.moveGeometry(55.5f, 55.5f, cg);

		for (int x = 12; x <= 14; x++) {
			for (int y = 12; y <= 14; y++) {
				compare[x][y] = true;
			}
		}

		assertArrayEquals(compare, collMapToBoolMap(m));
		assertArrayEquals(compareOuter, collMapToBoolMapOuter(m));
		assertEquals(null, m.outerBorder[1][1]);
	}

	@Test
	public void testMoveGeometryTriangleBorder() {
		CollisionMap m = new DummyCollisionMap(100, 100);
		boolean[][] compare = new boolean[100][100];

		boolean[][] compareOuter = new boolean[3][3];

		CollisionTriangle cg = new DummyCollisionTriangle(0f, 0f,   1f, 0f,   0f, 1f);
		cg.setCenter(99.5f, 50.5f);

		m.addGeometry(cg);

		cg.setCenter(0.5f, 0.5f);
		m.moveGeometry(99.5f, 50.5f, cg);

		for (int x = 0; x <= 1; x++) {
			for (int y = 0; y <= 1; y++) {
				compare[x][y] = true;
			}
		}
		compareOuter[0][1] = true;
		compareOuter[1][0] = true;
		compareOuter[0][0] = true;

		assertArrayEquals(compare, collMapToBoolMap(m));
		assertArrayEquals(compareOuter, collMapToBoolMapOuter(m));
		assertEquals(null, m.outerBorder[1][1]);
	}
	
	//#########################################################################

	public static boolean[][] collMapToBoolMap(CollisionMap m) {
		boolean[][] arr = new boolean[m.width][m.height];

		for (int x = 0; x < m.width; x++) {
			for (int y = 0; y < m.height; y++) {
				arr[x][y] = !m.map[x][y].geometries.isEmpty();
			}
		}

		return arr;
	}

	public static boolean[][] collMapToBoolMapOuter(CollisionMap m) {
		boolean[][] arr = new boolean[3][3];

		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				if (x == 1 && y == 1)
					continue;
				arr[x][y] = !m.outerBorder[x][y].geometries.isEmpty();
			}
		}

		return arr;
	}
}
