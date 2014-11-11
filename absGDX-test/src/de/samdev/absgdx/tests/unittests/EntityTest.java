package de.samdev.absgdx.tests.unittests;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionCircle;
import de.samdev.absgdx.framework.map.TileMap;
import de.samdev.absgdx.tests.BaseUnitTest;
import de.samdev.absgdx.tests.DummyEntity;
import de.samdev.absgdx.tests.DummyGameLayer;

public class EntityTest extends BaseUnitTest {

    @Test
    public void testMovement_speed() {
    	DummyEntity e1 = new DummyEntity();
    	DummyEntity e2 = new DummyEntity();
    	DummyEntity e3 = new DummyEntity();
    	
    	e1.setPosition(0, 0);
    	e2.setPosition(0, 0);
    	e3.setPosition(0, 0);
    	
    	for (int i = 0; i < 170 * 1 * 2 * 3; i++) {
    		e1.speed.set(0.5f, 1/3f);
    		e2.speed.set(0.5f, 1/3f);
    		e3.speed.set(0.5f, 1/3f);
    		
			if (i % 1 == 0) e1.update(66.6f * 1);
			if (i % 2 == 0) e2.update(66.6f * 2);
			if (i % 3 == 0) e3.update(66.6f * 3);
		}

    	assertEqualsExt(e1.getPosition(), e2.getPosition(), 0.5f);
    	assertEqualsExt(e1.getPosition(), e3.getPosition(), 0.5f);
    }

    @Test
    public void testMovement_acceleration() {
    	DummyEntity e1 = new DummyEntity();
    	DummyEntity e2 = new DummyEntity();
    	DummyEntity e3 = new DummyEntity();
    	
    	e1.setPosition(0, 0);
    	e2.setPosition(0, 0);
    	e3.setPosition(0, 0);
    	
    	for (int i = 0; i < 70 * 1 * 2 * 3; i++) {
    		e1.acceleration.set(0.00005f, 1/90000f);
    		e2.acceleration.set(0.00005f, 1/90000f);
    		e3.acceleration.set(0.00005f, 1/90000f);
    		
			if (i % 1 == 0) e1.update(66.6f * 1);
			if (i % 2 == 0) e2.update(66.6f * 2);
			if (i % 3 == 0) e3.update(66.6f * 3);
		}

    	assertEqualsExt(e1.getPosition(), e2.getPosition(), 100f);
    	assertEqualsExt(e1.getPosition(), e3.getPosition(), 100f);
    	
    	assertEqualsExt(e1.speed, e2.speed, 100f);
    	assertEqualsExt(e1.speed, e3.speed, 100f);
    }

    @Test
    public void testCollisionGeometriesAdd() {
    	DummyEntity e = new DummyEntity();
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	l.addEntity(e);
    	
    	assertEquals(1, l.getEntityCount());
    	
    	e.setPosition(20, 20);
    	e.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(1f));
    	
    	boolean[][] expected = new boolean[100][100];
    	boolean[][] expectedOuter = new boolean[3][3];
    	
    	expected[19][19] = true;
    	expected[19][20] = true;
    	expected[19][21] = true;
    	expected[20][19] = true;
    	expected[20][20] = true;
    	expected[20][21] = true;
    	expected[21][19] = true;
    	expected[21][20] = true;
    	expected[21][21] = true;
    	
    	assertArrayEquals(CollisionMapTest.collMapToBoolMap(l.collisionMap()), expected);
    	assertArrayEquals(CollisionMapTest.collMapToBoolMapOuter(l.collisionMap()), expectedOuter);
    }

    @Test
    public void testCollisionGeometriesMoveEntity() {
    	DummyEntity e = new DummyEntity();
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	l.addEntity(e);
    	
    	assertEquals(1, l.getEntityCount());
    	
    	e.setPosition(00, 00);
    	e.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(1f));
    	
    	e.movePosition(20f, 20f);
    	
    	boolean[][] expected = new boolean[100][100];
    	boolean[][] expectedOuter = new boolean[3][3];
    	
    	expected[19][19] = true;
    	expected[19][20] = true;
    	expected[19][21] = true;
    	expected[20][19] = true;
    	expected[20][20] = true;
    	expected[20][21] = true;
    	expected[21][19] = true;
    	expected[21][20] = true;
    	expected[21][21] = true;
    	
    	assertArrayEquals(CollisionMapTest.collMapToBoolMap(l.collisionMap()), expected);
    	assertArrayEquals(CollisionMapTest.collMapToBoolMapOuter(l.collisionMap()), expectedOuter);
    }

    @Test
    public void testCollisionGeometriesRemoveEntity() {
    	DummyEntity e = new DummyEntity();
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	l.addEntity(e);
    	
    	assertEquals(1, l.getEntityCount());
    	
    	e.setPosition(00, 00);
    	e.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(1f));
    	
    	e.movePosition(20f, 20f);
    	
    	boolean[][] expected = new boolean[100][100];
    	boolean[][] expectedOuter = new boolean[3][3];
    	
    	expected[19][19] = true;
    	expected[19][20] = true;
    	expected[19][21] = true;
    	expected[20][19] = true;
    	expected[20][20] = true;
    	expected[20][21] = true;
    	expected[21][19] = true;
    	expected[21][20] = true;
    	expected[21][21] = true;
    	
    	assertArrayEquals(CollisionMapTest.collMapToBoolMap(l.collisionMap()), expected);
    	assertArrayEquals(CollisionMapTest.collMapToBoolMapOuter(l.collisionMap()), expectedOuter);
    	
    	expected = new boolean[100][100];
    	e.alive = false;
    	l.update(16f);
    	 
    	assertArrayEquals(CollisionMapTest.collMapToBoolMap(l.collisionMap()), expected);
    	assertArrayEquals(CollisionMapTest.collMapToBoolMapOuter(l.collisionMap()), expectedOuter);
    }
}
