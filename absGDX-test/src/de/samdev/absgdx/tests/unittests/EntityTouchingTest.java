package de.samdev.absgdx.tests.unittests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionBox;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionCircle;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionTriangle;
import de.samdev.absgdx.framework.map.TileMap;
import de.samdev.absgdx.tests.BaseUnitTest;
import de.samdev.absgdx.tests.dummy.DummyEntity;
import de.samdev.absgdx.tests.dummy.DummyGameLayer;

public class EntityTouchingTest extends BaseUnitTest {

	@Test
	public void testTouchingNORTH_CircleCircle_setPos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e1, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e2, 1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(0f, 1f);

    	assertTrue(e1.isTouchingNorth());
    	assertTrue(e1.isTouchingTop());
	}

	@Test
	public void testTouchingNORTH_CircleCircle_movePos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e1, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e2, 1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(0f, 5f);
    	
    	for (int i = 0; i < 1000; i++) 
    		e1.movePosition(0f, 0.01f);

    	assertTrue(e1.isTouchingNorth());
    	assertTrue(e1.isTouchingTop());
	}

	@Test
	public void testTouchingNORTH_CircleBox_setPos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e1, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addFullCollisionBox();
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(0f, 1f);

    	assertTrue(e1.isTouchingNorth());
    	assertTrue(e1.isTouchingTop());
	}

	@Test
	public void testTouchingNORTH_CircleBox_movePos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e1, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addFullCollisionBox();
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(0f, 5f);
    	
    	for (int i = 0; i < 1000; i++) 
    		e1.movePosition(0f, 0.01f);

    	assertTrue(e1.isTouchingNorth());
    	assertTrue(e1.isTouchingTop());
	}
	
	@Test
	public void testTouchingNORTH_BoxCircle_setPos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addFullCollisionBox();
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e2, 1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(0f, 1f);

    	assertTrue(e1.isTouchingNorth());
    	assertTrue(e1.isTouchingTop());
	}

	@Test
	public void testTouchingNORTH_BoxCircle_movePos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addFullCollisionBox();
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e2, 1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(0f, 5f);
    	
    	for (int i = 0; i < 1000; i++) 
    		e1.movePosition(0f, 0.01f);

    	assertTrue(e1.isTouchingNorth());
    	assertTrue(e1.isTouchingTop());
	}
	
	@Test
	public void testTouchingNORTH_BoxBox_setPos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e1, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e2, 1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(0f, 1f);

    	assertTrue(e1.isTouchingNorth());
    	assertTrue(e1.isTouchingTop());
	}

	@Test
	public void testTouchingNORTH_BoxBox_movePos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addFullCollisionBox();
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addFullCollisionBox();
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(0f, 5f);
    	
    	for (int i = 0; i < 1000; i++) 
    		e1.movePosition(0f, 0.01f);

    	assertTrue(e1.isTouchingNorth());
    	assertTrue(e1.isTouchingTop());
	}

	@Test
	public void testTouchingNORTH_TriangleTriangle_setPos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f,0f,  1f,0f,  0f,1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e2, 0f,0f,  1f,0f,  0f,1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(0f, 1f);

    	assertTrue(e1.isTouchingNorth());
    	assertTrue(e1.isTouchingTop());
	}
	
	@Test
	public void testTouchingNORTH_TriangleTriangle_movePos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f,0f,  1f,0f,  0f,1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e2, 0f,0f,  1f,0f,  0f,1f));

    	e1.setPosition(0f, 0f);
    	e2.setPosition(0f, 5f);
    	
    	for (int i = 0; i < 1000; i++) 
    		e1.movePosition(0f, 0.01f);

    	assertTrue(e1.isTouchingNorth());
    	assertTrue(e1.isTouchingTop());
	}
	
	@Test
	public void testTouchingNORTH_TriangleCircle_setPos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f,0f,  1f,0f,  0f,1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/2f, 1/2f, new CollisionCircle(e2, 1/2f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(-1/2f, 1f);

    	assertTrue(e1.isTouchingNorth());
    	assertTrue(e1.isTouchingTop());
	}
	
	@Test
	public void testTouchingNORTH_TriangleCircle_movePos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f,0f,  1f,0f,  0f,1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/2f, 1/2f, new CollisionCircle(e2, 1/2f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(0f, 5f);
    	
    	for (int i = 0; i < 1000; i++) 
    		e1.movePosition(0f, 0.01f);

    	assertTrue(e1.isTouchingNorth());
    	assertTrue(e1.isTouchingTop());
	}
	
	@Test
	public void testTouchingNORTH_TriangleBox_setPos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f,0f,  1f,0f,  0f,1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/2f, 1/2f, new CollisionBox(e2, 1f, 1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(-1/2f, 1f);

    	assertTrue(e1.isTouchingNorth());
    	assertTrue(e1.isTouchingTop());
	}
	
	@Test
	public void testTouchingNORTH_TriangleBox_movePos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f,0f,  1f,0f,  0f,1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/2f, 1/2f, new CollisionBox(e2, 1f, 1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(0f, 5f);
    	
    	for (int i = 0; i < 1000; i++) 
    		e1.movePosition(0f, 0.01f);

    	assertTrue(e1.isTouchingNorth());
    	assertTrue(e1.isTouchingTop());
	}	
	
	@Test
	public void testTouchingNORTH_CircleTriangle_setPos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/2f, 1/2f, new CollisionCircle(e1, 1/2f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e2, 0f,0f,  1f,0f,  0f,1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(1/2f, 1f);

    	assertTrue(e1.isTouchingNorth());
    	assertTrue(e1.isTouchingTop());
	}	
	
	@Test
	public void testTouchingNORTH_CircleTriangle_movePos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/2f, 1/2f, new CollisionCircle(e1, 1/2f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e2, 0f,0f,  1f,0f,  0f,1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(0f, 5f);
    	
    	for (int i = 0; i < 1000; i++) 
    		e1.movePosition(0f, 0.01f);

    	assertTrue(e1.isTouchingNorth());
    	assertTrue(e1.isTouchingTop());
	}	
	
	@Test
	public void testTouchingNORTH_BoxTriangle_setPos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/2f, 1/2f, new CollisionBox(e1, 1f, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e2, 0f,0f,  1f,0f,  0f,1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(0f, 1f);

    	assertTrue(e1.isTouchingNorth());
    	assertTrue(e1.isTouchingTop());
	}	
	
	@Test
	public void testTouchingNORTH_BoxTriangle_movePos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/2f, 1/2f, new CollisionBox(e1, 1f, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e2, 0f,0f,  1f,0f,  0f,1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(0f, 5f);
    	
    	for (int i = 0; i < 1000; i++) 
    		e1.movePosition(0f, 0.01f);

    	assertTrue(e1.isTouchingNorth());
    	assertTrue(e1.isTouchingTop());
	}	
	
	//#########################################################################
	
	@Test
	public void testTouchingEAST_CircleCircle_setPos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e1, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e2, 1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(1f, 0f);

    	assertTrue(e1.isTouchingEast());
    	assertTrue(e1.isTouchingRight());
	}

	@Test
	public void testTouchingEAST_CircleCircle_movePos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e1, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e2, 1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(5f, 0f);
    	
    	for (int i = 0; i < 1000; i++) 
    		e1.movePosition(0.01f, 0f);

    	assertTrue(e1.isTouchingEast());
    	assertTrue(e1.isTouchingRight());
	}

	@Test
	public void testTouchingEAST_CircleBox_setPos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e1, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addFullCollisionBox();
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(1f, 0f);

    	assertTrue(e1.isTouchingEast());
    	assertTrue(e1.isTouchingRight());
	}

	@Test
	public void testTouchingEAST_CircleBox_movePos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e1, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addFullCollisionBox();
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(5f, 0f);
    	
    	for (int i = 0; i < 1000; i++) 
    		e1.movePosition(0.01f, 0f);

    	assertTrue(e1.isTouchingEast());
    	assertTrue(e1.isTouchingRight());
	}
	
	@Test
	public void testTouchingEAST_BoxCircle_setPos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addFullCollisionBox();
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e2, 1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(1f, 0f);

    	assertTrue(e1.isTouchingEast());
    	assertTrue(e1.isTouchingRight());
	}

	@Test
	public void testTouchingEAST_BoxCircle_movePos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addFullCollisionBox();
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e2, 1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(5f, 0f);
    	
    	for (int i = 0; i < 1000; i++) 
    		e1.movePosition(0.01f, 0f);

    	assertTrue(e1.isTouchingEast());
    	assertTrue(e1.isTouchingRight());
	}
	
	@Test
	public void testTouchingEAST_BoxBox_setPos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e1, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e2, 1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(1f, 0f);

    	assertTrue(e1.isTouchingEast());
    	assertTrue(e1.isTouchingRight());
	}

	@Test
	public void testTouchingEAST_BoxBox_movePos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addFullCollisionBox();
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addFullCollisionBox();
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(5f, 0f);
    	
    	for (int i = 0; i < 1000; i++) 
    		e1.movePosition(0.01f, 0f);

    	assertTrue(e1.isTouchingEast());
    	assertTrue(e1.isTouchingRight());
	}

	@Test
	public void testTouchingEAST_TriangleTriangle_setPos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f,0f,  1f,0f,  0f,1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e2, 0f,0f,  1f,0f,  0f,1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(1f, 0f);

    	assertTrue(e1.isTouchingEast());
    	assertTrue(e1.isTouchingRight());
	}

	@Test
	public void testTouchingEAST_TriangleTriangle_movePos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f,0f,  1f,0f,  0f,1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e2, 0f,0f,  1f,0f,  0f,1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(5f, 0f);
    	
    	for (int i = 0; i < 1000; i++) 
    		e1.movePosition(0.01f, 0f);

    	assertTrue(e1.isTouchingEast());
    	assertTrue(e1.isTouchingRight());
	}
	
	@Test
	public void testTouchingEAST_TriangleCircle_setPos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f,0f,  1f,0f,  0f,1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/2f, 1/2f, new CollisionCircle(e2, 1/2f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(1f, -1/2f);

    	assertTrue(e1.isTouchingEast());
    	assertTrue(e1.isTouchingRight());
	}
	
	@Test
	public void testTouchingEAST_TriangleCircle_movePos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f,0f,  1f,0f,  0f,1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/2f, 1/2f, new CollisionCircle(e2, 1/2f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(5f, 0f);
    	
    	for (int i = 0; i < 1000; i++) 
    		e1.movePosition(0.01f, 0f);

    	assertTrue(e1.isTouchingEast());
    	assertTrue(e1.isTouchingRight());
	}
	
	@Test
	public void testTouchingEAST_TriangleBox_setPos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f,0f,  1f,0f,  0f,1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/2f, 1/2f, new CollisionBox(e2, 1f, 1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(1f, 0f);

    	assertTrue(e1.isTouchingEast());
    	assertTrue(e1.isTouchingRight());
	}
	
	@Test
	public void testTouchingEAST_TriangleBox_movePos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f,0f,  1f,0f,  0f,1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/2f, 1/2f, new CollisionBox(e2, 1f, 1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(5f, 0f);
    	
    	for (int i = 0; i < 1000; i++) 
    		e1.movePosition(0.01f, 0f);

    	assertTrue(e1.isTouchingEast());
    	assertTrue(e1.isTouchingRight());
	}
	
	@Test
	public void testTouchingEAST_CircleTriangle_setPos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/2f, 1/2f, new CollisionCircle(e1, 1/2f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e2, 0f,0f,  1f,0f,  0f,1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(1f, 0f);

    	assertTrue(e1.isTouchingEast());
    	assertTrue(e1.isTouchingRight());
	}
	
	@Test
	public void testTouchingEAST_CircleTriangle_movePos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/2f, 1/2f, new CollisionCircle(e1, 1/2f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e2, 0f,0f,  1f,0f,  0f,1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(5f, 0f);
    	
    	for (int i = 0; i < 1000; i++) 
    		e1.movePosition(0.01f, 0f);

    	assertTrue(e1.isTouchingEast());
    	assertTrue(e1.isTouchingRight());
	}
	
	@Test
	public void testTouchingEAST_BoxTriangle_setPos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/2f, 1/2f, new CollisionBox(e1, 1f, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e2, 0f,0f,  1f,0f,  0f,1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(1f, 0f);

    	assertTrue(e1.isTouchingEast());
    	assertTrue(e1.isTouchingRight());
	}
	
	@Test
	public void testTouchingEAST_BoxTriangle_movePos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/2f, 1/2f, new CollisionBox(e1, 1f, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e2, 0f,0f,  1f,0f,  0f,1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(5f, 0f);
    	
    	for (int i = 0; i < 1000; i++) 
    		e1.movePosition(0.01f, 0f);

    	assertTrue(e1.isTouchingEast());
    	assertTrue(e1.isTouchingRight());
	}
	
	//#########################################################################
	
	@Test
	public void testTouchingSOUTH_CircleCircle_setPos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e1, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e2, 1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(0f, -1f);

    	assertTrue(e1.isTouchingSouth());
    	assertTrue(e1.isTouchingBottom());
	}

	@Test
	public void testTouchingSOUTH_CircleCircle_movePos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e1, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e2, 1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(0f, -5f);
    	
    	for (int i = 0; i < 1000; i++) 
    		e1.movePosition(0f, -0.01f);

    	assertTrue(e1.isTouchingSouth());
    	assertTrue(e1.isTouchingBottom());
	}

	@Test
	public void testTouchingSOUTH_CircleBox_setPos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e1, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addFullCollisionBox();
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(0f, -1f);

    	assertTrue(e1.isTouchingSouth());
    	assertTrue(e1.isTouchingBottom());
	}

	@Test
	public void testTouchingSOUTH_CircleBox_movePos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e1, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addFullCollisionBox();
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(0f, -5f);
    	
    	for (int i = 0; i < 1000; i++) 
    		e1.movePosition(0f, -0.01f);

    	assertTrue(e1.isTouchingSouth());
    	assertTrue(e1.isTouchingBottom());
	}
	
	@Test
	public void testTouchingSOUTH_BoxCircle_setPos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addFullCollisionBox();
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e2, 1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(0f, -1f);

    	assertTrue(e1.isTouchingSouth());
    	assertTrue(e1.isTouchingBottom());
	}

	@Test
	public void testTouchingSOUTH_BoxCircle_movePos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addFullCollisionBox();
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e2, 1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(0f, -5f);
    	
    	for (int i = 0; i < 1000; i++) 
    		e1.movePosition(0f, -0.01f);

    	assertTrue(e1.isTouchingSouth());
    	assertTrue(e1.isTouchingBottom());
	}
	
	@Test
	public void testTouchingSOUTH_BoxBox_setPos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e1, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e2, 1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(0f, -1f);

    	assertTrue(e1.isTouchingSouth());
    	assertTrue(e1.isTouchingBottom());
	}

	@Test
	public void testTouchingSOUTH_BoxBox_movePos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addFullCollisionBox();
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addFullCollisionBox();
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(0f, -5f);
    	
    	for (int i = 0; i < 1000; i++) 
    		e1.movePosition(0f, -0.01f);

    	assertTrue(e1.isTouchingSouth());
    	assertTrue(e1.isTouchingBottom());
	}

	@Test
	public void testTouchingSOUTH_TriangleTriangle_setPos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f,0f,  1f,0f,  0f,1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e2, 0f,0f,  1f,0f,  0f,1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(0f, -1f);

    	assertTrue(e1.isTouchingSouth());
    	assertTrue(e1.isTouchingBottom());
	}

	@Test
	public void testTouchingSOUTH_TriangleTriangle_movePos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f,0f,  1f,0f,  0f,1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e2, 0f,0f,  1f,0f,  0f,1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(0f, -5f);
    	
    	for (int i = 0; i < 1000; i++) 
    		e1.movePosition(0f, -0.01f);

    	assertTrue(e1.isTouchingSouth());
    	assertTrue(e1.isTouchingBottom());
	}
	
	@Test
	public void testTouchingSOUTH_TriangleCircle_setPos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f,0f,  1f,0f,  0f,1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/2f, 1/2f, new CollisionCircle(e2, 1/2f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(0f, -1f);

    	assertTrue(e1.isTouchingSouth());
    	assertTrue(e1.isTouchingBottom());
	}
	
	@Test
	public void testTouchingSOUTH_TriangleCircle_movePos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f,0f,  1f,0f,  0f,1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/2f, 1/2f, new CollisionCircle(e2, 1/2f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(0f, -5f);
    	
    	for (int i = 0; i < 1000; i++) 
    		e1.movePosition(0f, -0.01f);

    	assertTrue(e1.isTouchingSouth());
    	assertTrue(e1.isTouchingBottom());
	}
	
	@Test
	public void testTouchingSOUTH_TriangleBox_setPos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f,0f,  1f,0f,  0f,1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/2f, 1/2f, new CollisionBox(e2, 1f, 1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(0f, -1f);

    	assertTrue(e1.isTouchingSouth());
    	assertTrue(e1.isTouchingBottom());
	}
	
	@Test
	public void testTouchingSOUTH_TriangleBox_movePos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f,0f,  1f,0f,  0f,1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/2f, 1/2f, new CollisionBox(e2, 1f, 1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(0f, -5f);
    	
    	for (int i = 0; i < 1000; i++) 
    		e1.movePosition(0f, -0.01f);

    	assertTrue(e1.isTouchingSouth());
    	assertTrue(e1.isTouchingBottom());
	}
	
	@Test
	public void testTouchingSOUTH_CircleTriangle_setPos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/2f, 1/2f, new CollisionCircle(e1, 1/2f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e2, 0f,0f,  1f,0f,  0f,1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(1/2f, -1f);

    	assertTrue(e1.isTouchingSouth());
    	assertTrue(e1.isTouchingBottom());
	}
	
	@Test
	public void testTouchingSOUTH_CircleTriangle_movePos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/2f, 1/2f, new CollisionCircle(e1, 1/2f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e2, 0f,0f,  1f,0f,  0f,1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(0f, -5f);
    	
    	for (int i = 0; i < 1000; i++) 
    		e1.movePosition(0f, -0.01f);

    	assertTrue(e1.isTouchingSouth());
    	assertTrue(e1.isTouchingBottom());
	}
	
	@Test
	public void testTouchingSOUTH_BoxTriangle_setPos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/2f, 1/2f, new CollisionBox(e1, 1f, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e2, 0f,0f,  1f,0f,  0f,1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(0f, -1f);

    	assertTrue(e1.isTouchingSouth());
    	assertTrue(e1.isTouchingBottom());
	}
	
	@Test
	public void testTouchingSOUTH_BoxTriangle_movePos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/2f, 1/2f, new CollisionBox(e1, 1f, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e2, 0f,0f,  1f,0f,  0f,1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(0f, -5f);
    	
    	for (int i = 0; i < 1000; i++) 
    		e1.movePosition(0f, -0.01f);

    	assertTrue(e1.isTouchingSouth());
    	assertTrue(e1.isTouchingBottom());
	}
	
	//#########################################################################
	
	@Test
	public void testTouchingWEST_CircleCircle_setPos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e1, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e2, 1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(-1f, 0f);

    	assertTrue(e1.isTouchingWest());
    	assertTrue(e1.isTouchingLeft());
	}

	@Test
	public void testTouchingWEST_CircleCircle_movePos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e1, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e2, 1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(-5f, 0f);
    	
    	for (int i = 0; i < 1000; i++) 
    		e1.movePosition(-0.01f, 0f);

    	assertTrue(e1.isTouchingWest());
    	assertTrue(e1.isTouchingLeft());
	}

	@Test
	public void testTouchingWEST_CircleBox_setPos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e1, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addFullCollisionBox();
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(-1f, 0f);

    	assertTrue(e1.isTouchingWest());
    	assertTrue(e1.isTouchingLeft());
	}

	@Test
	public void testTouchingWEST_CircleBox_movePos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e1, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addFullCollisionBox();
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(-5f, 0f);
    	
    	for (int i = 0; i < 1000; i++) 
    		e1.movePosition(-0.01f, 0f);

    	assertTrue(e1.isTouchingWest());
    	assertTrue(e1.isTouchingLeft());
	}
	
	@Test
	public void testTouchingWEST_BoxCircle_setPos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addFullCollisionBox();
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e2, 1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(-1f, 0f);

    	assertTrue(e1.isTouchingWest());
    	assertTrue(e1.isTouchingLeft());
	}

	@Test
	public void testTouchingWEST_BoxCircle_movePos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addFullCollisionBox();
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e2, 1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(-5f, 0f);
    	
    	for (int i = 0; i < 1000; i++) 
    		e1.movePosition(-0.01f, 0f);

    	assertTrue(e1.isTouchingWest());
    	assertTrue(e1.isTouchingLeft());
	}
	
	@Test
	public void testTouchingWEST_BoxBox_setPos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e1, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e2, 1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(-1f, 0f);

    	assertTrue(e1.isTouchingWest());
    	assertTrue(e1.isTouchingLeft());
	}

	@Test
	public void testTouchingWEST_BoxBox_movePos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addFullCollisionBox();
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addFullCollisionBox();
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(-5f, 0f);
    	
    	for (int i = 0; i < 1000; i++) 
    		e1.movePosition(-0.01f, 0f);

    	assertTrue(e1.isTouchingWest());
    	assertTrue(e1.isTouchingLeft());
	}

	@Test
	public void testTouchingWEST_TriangleTriangle_setPos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f,0f,  1f,0f,  0f,1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e2, 0f,0f,  1f,0f,  0f,1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(-1f, 0f);

    	assertTrue(e1.isTouchingWest());
    	assertTrue(e1.isTouchingLeft());
	}

	@Test
	public void testTouchingWEST_TriangleTriangle_movePos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f,0f,  1f,0f,  0f,1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e2, 0f,0f,  1f,0f,  0f,1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(-5f, 0f);
    	
    	for (int i = 0; i < 1000; i++) 
    		e1.movePosition(-0.01f, 0f);

    	assertTrue(e1.isTouchingWest());
    	assertTrue(e1.isTouchingLeft());
	}
	
	@Test
	public void testTouchingWEST_TriangleCircle_setPos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f,0f,  1f,0f,  0f,1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/2f, 1/2f, new CollisionCircle(e2, 1/2f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(-1f, 0f);

    	assertTrue(e1.isTouchingWest());
    	assertTrue(e1.isTouchingLeft());
	}
	
	@Test
	public void testTouchingWEST_TriangleCircle_movePos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f,0f,  1f,0f,  0f,1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/2f, 1/2f, new CollisionCircle(e2, 1/2f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(-5f, 0f);
    	
    	for (int i = 0; i < 1000; i++) 
    		e1.movePosition(-0.01f, 0f);

    	assertTrue(e1.isTouchingWest());
    	assertTrue(e1.isTouchingLeft());
	}

	@Test
	public void testTouchingWEST_TriangleBox_setPos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f,0f,  1f,0f,  0f,1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/2f, 1/2f, new CollisionBox(e2, 1f, 1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(-1f, 0f);

    	assertTrue(e1.isTouchingWest());
    	assertTrue(e1.isTouchingLeft());
	}

	@Test
	public void testTouchingWEST_TriangleBox_movePos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f,0f,  1f,0f,  0f,1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/2f, 1/2f, new CollisionBox(e2, 1f, 1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(-5f, 0f);
    	
    	for (int i = 0; i < 1000; i++) 
    		e1.movePosition(-0.01f, 0f);

    	assertTrue(e1.isTouchingWest());
    	assertTrue(e1.isTouchingLeft());
	}

	@Test
	public void testTouchingWEST_CircleTriangle_setPos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/2f, 1/2f, new CollisionCircle(e1, 1/2f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e2, 0f,0f,  1f,0f,  0f,1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(-1f, 1/2f);

    	assertTrue(e1.isTouchingWest());
    	assertTrue(e1.isTouchingLeft());
	}

	@Test
	public void testTouchingWEST_CircleTriangle_movePos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/2f, 1/2f, new CollisionCircle(e1, 1/2f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e2, 0f,0f,  1f,0f,  0f,1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(-5f, 0f);
    	
    	for (int i = 0; i < 1000; i++) 
    		e1.movePosition(-0.01f, 0f);

    	assertTrue(e1.isTouchingWest());
    	assertTrue(e1.isTouchingLeft());
	}

	@Test
	public void testTouchingWEST_BoxTriangle_setPos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/2f, 1/2f, new CollisionBox(e1, 1f, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e2, 0f,0f,  1f,0f,  0f,1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(-1f, 0f);

    	assertTrue(e1.isTouchingWest());
    	assertTrue(e1.isTouchingLeft());
	}

	@Test
	public void testTouchingWEST_BoxTriangle_movePos() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));

    	DummyEntity e1 = new DummyEntity();
    	e1.canCollide = true;
    	e1.canMoveCollide = true;
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/2f, 1/2f, new CollisionBox(e1, 1f, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	e2.canCollide = true;
    	e2.canMoveCollide = true;
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e2, 0f,0f,  1f,0f,  0f,1f));
    	
    	e1.setPosition(0f, 0f);
    	e2.setPosition(-5f, 0f);
    	
    	for (int i = 0; i < 1000; i++) 
    		e1.movePosition(-0.01f, 0f);

    	assertTrue(e1.isTouchingWest());
    	assertTrue(e1.isTouchingLeft());
	}
}
