package de.samdev.absgdx.tests.unittests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionBox;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionCircle;
import de.samdev.absgdx.framework.map.TileMap;
import de.samdev.absgdx.tests.BaseUnitTest;
import de.samdev.absgdx.tests.dummy.DummyCollisionCircle;
import de.samdev.absgdx.tests.dummy.DummyEntity;
import de.samdev.absgdx.tests.dummy.DummyGameLayer;

public class EntityCollisionTest extends BaseUnitTest {
    @Test
    public void testCollisionGeometriesAdd() {
    	DummyEntity e = new DummyEntity();
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	l.addEntity(e);
    	
    	assertEquals(1, l.getEntityCount());
    	
    	e.setPosition(20, 20);
    	e.addCollisionGeo(0.5f, 0.5f, new DummyCollisionCircle(1f));
    	
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
    	e.addCollisionGeo(0.5f, 0.5f, new DummyCollisionCircle(1f));
    	
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
    	e.addCollisionGeo(0.5f, 0.5f, new DummyCollisionCircle(1f));
    	
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

    @Test
    public void testAxisIndependentMovement() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e1, 1f, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e2, 1f, 20f));
    	
    	e1.setPosition(10f, 10f);
    	e2.setPosition(12f, 10f);
    	
    	for (int i = 0; i < 100; i++) 
    		e1.movePosition(0.1f, 0.1f);
    	
    	assertEqualsExt(new Vector2(11f, 20f), e1.getPosition(), 0.0001f);
    }

    @Test
    public void testMovementAgainstTwoGeometries_BoxBox() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0f, 1f, new CollisionBox(e1, 0.2f, 0.2f));
    	e1.addCollisionGeo(0.1f, 0f, new CollisionBox(e1, 0.2f, 0.2f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e2, 1f, 1f));
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(12f, 10f);
    	
    	for (int i = 0; i < 100; i++) 
    		e2.movePosition(0.1f, 0f);
    	
    	assertEqualsExt(new Vector2(10.9f, 10f), e2.getPosition(), 0.00001f);
    }

    @Test
    public void testMovementAgainstTwoGeometries_CircleCircle() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0f, 1f, new CollisionCircle(e1, 0.1f));
    	e1.addCollisionGeo(0.1f, 0f, new CollisionCircle(e1, 0.1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e2, 1f, 1f));
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(12f, 10f);
    	
    	for (int i = 0; i < 100; i++) 
    		e2.movePosition(0.1f, 0f);
    	
    	assertEqualsExt(new Vector2(10.9f, 10f), e2.getPosition(), 0.00001f);
    }

    @Test
    public void testMovementAgainstTwoGeometries_BoxCircle() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0f, 1f, new CollisionCircle(e1, 0.1f));
    	e1.addCollisionGeo(0.1f, 0f, new CollisionBox(e1, 0.2f, 0.2f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e2, 1f, 1f));
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(12f, 10f);
    	
    	for (int i = 0; i < 100; i++) 
    		e2.movePosition(0.1f, 0f);
    	
    	assertEqualsExt(new Vector2(10.9f, 10f), e2.getPosition(), 0.00001f);
    }

    @Test
    public void testMovementAgainstTwoGeometries_BoxBox_2() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0f, 1f, new CollisionBox(e1, 0.2f, 0.2f));
    	e1.addCollisionGeo(0f, 0f, new CollisionBox(e1, 0.2f, 0.2f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e2, 1f, 1f));
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(12f, 10f);
    	
    	for (int i = 0; i < 100; i++) 
    		e2.movePosition(0.1f, 0f);
    	
    	assertEqualsExt(new Vector2(10.9f, 10f), e2.getPosition(), 0.00001f);
    }

    @Test
    public void testMovementAgainstTwoGeometries_CircleCircle_2() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0f, 1f, new CollisionCircle(e1, 0.1f));
    	e1.addCollisionGeo(0f, 0f, new CollisionCircle(e1, 0.1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e2, 1f, 1f));
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(12f, 10f);
    	
    	for (int i = 0; i < 100; i++) 
    		e2.movePosition(0.1f, 0f);
    	
    	assertEqualsExt(new Vector2(10.9f, 10f), e2.getPosition(), 0.00001f);
    }

    @Test
    public void testMovementAgainstTwoGeometries_BoxCircle_2() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0f, 1f, new CollisionCircle(e1, 0.1f));
    	e1.addCollisionGeo(0f, 0f, new CollisionBox(e1, 0.2f, 0.2f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e2, 1f, 1f));
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(12f, 10f);
    	
    	for (int i = 0; i < 100; i++) 
    		e2.movePosition(0.1f, 0f);
    	
    	assertEqualsExt(new Vector2(10.9f, 10f), e2.getPosition(), 0.00001f);
    }
    
    @Test
    public void testMovementAgainstTwoGeometries_BoxBox_3() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 1f, new CollisionBox(e1, 1f, 1f));
    	e1.addCollisionGeo(0.49f, 0f, new CollisionBox(e1, 1f, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e2, 1f, 1f));
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(12f, 10f);
    	
    	for (int i = 0; i < 100; i++) 
    		e2.movePosition(0.1f, 0f);
    	
    	assertEqualsExt(new Vector2(10.99f, 10f), e2.getPosition(), 0.00001f);
    }
    
    @Test
    public void testMovementAgainstTwoGeometries_CircleCircle_3() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 1f, new CollisionCircle(e1, 0.5f));
    	e1.addCollisionGeo(0.49f, 0f, new CollisionCircle(e1, 0.5f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e2, 1f, 1f));
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(12f, 10f);
    	
    	for (int i = 0; i < 100; i++) 
    		e2.movePosition(0.1f, 0f);
    	
    	assertEqualsExt(new Vector2(10.99f, 10f), e2.getPosition(), 0.00001f);
    }    
    
    @Test
    public void testMovementAgainstTwoGeometries_CircleBox_3() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 1f, new CollisionCircle(e1, 0.5f));
    	e1.addCollisionGeo(0.49f, 0f, new CollisionBox(e1, 1f, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e2, 1f, 1f));
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(12f, 10f);
    	
    	for (int i = 0; i < 100; i++) 
    		e2.movePosition(0.1f, 0f);
    	
    	assertEqualsExt(new Vector2(10.99f, 10f), e2.getPosition(), 0.00001f);
    } 
    
    @Test
    public void testMovementAgainstTwoGeometries_BoxCircle_3() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 1f, new CollisionBox(e1, 1f, 1f));
    	e1.addCollisionGeo(0.49f, 0f, new CollisionCircle(e1, 0.5f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e2, 1f, 1f));
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(12f, 10f);
    	
    	for (int i = 0; i < 100; i++) 
    		e2.movePosition(0.1f, 0f);
    	
    	assertEqualsExt(new Vector2(10.99f, 10f), e2.getPosition(), 0.00001f);
    }

    @Test
    public void testNoCollisionMovement() {
    	assertEquals(false, doNoCollisionMovement(false, false, false, false));
    	assertEquals(false, doNoCollisionMovement(false, false, false, true));
    	assertEquals(false, doNoCollisionMovement(false, false, true,  false));  // <- canCollide() is not commutative (kinda illegal)
    	assertEquals(false, doNoCollisionMovement(false, false, true,  true));   // <- canCollide() is not commutative (kinda illegal)
    	assertEquals(false, doNoCollisionMovement(false, true,  false, false)); 
    	assertEquals(false, doNoCollisionMovement(false, true,  false, true));
    	assertEquals(true,  doNoCollisionMovement(false, true,  true,  false));  // <- canCollide() is not commutative (kinda illegal)
    	assertEquals(true,  doNoCollisionMovement(false, true,  true,  true));   // <- canCollide() is not commutative (kinda illegal)
    	assertEquals(false, doNoCollisionMovement(true,  false, false, false));  // <- canCollide() is not commutative (kinda illegal)
    	assertEquals(false, doNoCollisionMovement(true,  false, false, true));   // <- canCollide() is not commutative (kinda illegal)
    	assertEquals(false, doNoCollisionMovement(true,  false, true,  false));
    	assertEquals(false, doNoCollisionMovement(true,  false, true,  true));
    	assertEquals(true,  doNoCollisionMovement(true,  true,  false, false));  // <- canCollide() is not commutative (kinda illegal)
    	assertEquals(true,  doNoCollisionMovement(true,  true,  false, true));   // <- canCollide() is not commutative (kinda illegal)
    	assertEquals(true,  doNoCollisionMovement(true,  true,  true,  false));
    	assertEquals(true,  doNoCollisionMovement(true,  true,  true,  true));
    }
    
    private boolean doNoCollisionMovement(boolean e1_cc, boolean e1_cm, boolean e2_cc, boolean e2_cm) {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e1, 1f, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e2, 1f, 20f));
    	
    	e1.setPosition(10f, 10f);
    	e2.setPosition(12f, 10f);
    	
    	e1.canCollide = e1_cc;
    	e1.canMoveCollide = e1_cm;
    	
    	e2.canCollide = e2_cc;
    	e2.canMoveCollide = e2_cm;
    	
    	for (int i = 0; i < 100; i++) 
    		e1.movePosition(0.1f, 0f);
    	
    	return new Vector2(11f, 10f).epsilonEquals(e1.getPosition(), 0.0001f);
    }
}
