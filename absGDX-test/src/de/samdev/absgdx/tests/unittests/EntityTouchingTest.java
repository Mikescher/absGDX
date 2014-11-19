package de.samdev.absgdx.tests.unittests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionCircle;
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
    	
    	for (int i = 0; i < 100; i++) 
    		e1.movePosition(0f, 0.1f);

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
    	
    	for (int i = 0; i < 100; i++) 
    		e1.movePosition(0f, 0.1f);

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
    	
    	for (int i = 0; i < 100; i++) 
    		e1.movePosition(0f, 0.1f);

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
    	
    	for (int i = 0; i < 100; i++) 
    		e1.movePosition(0f, 0.1f);

    	assertTrue(e1.isTouchingNorth());
    	assertTrue(e1.isTouchingTop());
	}

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
    	
    	for (int i = 0; i < 100; i++) 
    		e1.movePosition(0.1f, 0f);

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
    	
    	for (int i = 0; i < 100; i++) 
    		e1.movePosition(0.1f, 0f);

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
    	
    	for (int i = 0; i < 100; i++) 
    		e1.movePosition(0.1f, 0f);

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
    	
    	for (int i = 0; i < 100; i++) 
    		e1.movePosition(0.1f, 0f);

    	assertTrue(e1.isTouchingEast());
    	assertTrue(e1.isTouchingRight());
	}

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
    	
    	for (int i = 0; i < 100; i++) 
    		e1.movePosition(0f, -0.1f);

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
    	
    	for (int i = 0; i < 100; i++) 
    		e1.movePosition(0f, -0.1f);

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
    	
    	for (int i = 0; i < 100; i++) 
    		e1.movePosition(0f, -0.1f);

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
    	
    	for (int i = 0; i < 100; i++) 
    		e1.movePosition(0f, -0.1f);

    	assertTrue(e1.isTouchingSouth());
    	assertTrue(e1.isTouchingBottom());
	}
	
	
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
    	
    	for (int i = 0; i < 100; i++) 
    		e1.movePosition(-0.1f, 0f);

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
    	
    	for (int i = 0; i < 100; i++) 
    		e1.movePosition(-0.1f, 0f);

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
    	
    	for (int i = 0; i < 100; i++) 
    		e1.movePosition(-0.1f, 0f);

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
    	
    	for (int i = 0; i < 100; i++) 
    		e1.movePosition(-0.1f, 0f);

    	assertTrue(e1.isTouchingWest());
    	assertTrue(e1.isTouchingLeft());
	}

}
