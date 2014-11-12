package de.samdev.absgdx.tests.unittests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionBox;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionCircle;
import de.samdev.absgdx.framework.map.TileMap;
import de.samdev.absgdx.tests.BaseUnitTest;
import de.samdev.absgdx.tests.dummy.DummyEntity;
import de.samdev.absgdx.tests.dummy.DummyGameLayer;

public class CollisionTest extends BaseUnitTest {

    @Test
    public void testCircleCircleCollisionMovement_1D() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	
    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e1, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e2, 1f));
    	
    	e1.setPosition(10, 10);
    	e2.setPosition(40, 10);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	for (int i = 0; i < 400; i++)
    		e1.movePosition(0.1f, 0);
    	
    	assertEqualsExt(new Vector2(38, 10), e1.getPosition());

    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-1-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-1", e2.dummyCtrSignSummary());
    }
    
    @Test
    public void testCircleCircleCollisionMovement_2D() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	
    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e1, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e2, 1f));
    	
    	e1.setPosition(10, 10.5f);
    	e2.setPosition(40, 10);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	for (int i = 0; i < 400; i++)
    		e1.movePosition(0.1f, 0);
    	
    	assertEqualsExt(new Vector2(38.0635083f, 10.5f), e1.getPosition(), 0.00001f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-1-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-1", e2.dummyCtrSignSummary());
    }

    @Test
    public void testCircleCircleCollision() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	
    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e1, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e2, 1f));
    	
    	e1.setPosition(10f, 10f);
    	e2.setPosition(40f, 40f);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	e1.setPosition(10f, 10f);
    	e2.setPosition(10.5f, 10.5f);

    	assertEquals(e1.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(e2.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-1-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-1-0-0-0", e2.dummyCtrSignSummary());
    }
    
    @Test
    public void testCircleCircleNoCollision() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	
    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e1, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e2, 1f));
    	
    	e1.setPosition(10f, 10f);
    	e2.setPosition(40f, 40f);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	e1.setPosition(10f, 10f);
    	e2.setPosition(10f, 12.0001f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    }

    @Test
    public void testBoxBoxCollisionMovement_1D() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	
    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e1, 1f, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e2, 1f, 1f));
    	
    	e1.setPosition(10, 10);
    	e2.setPosition(40, 10);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	for (int i = 0; i < 400; i++)
    		e1.movePosition(0.1f, 0);
    	
    	assertEqualsExt(new Vector2(39, 10), e1.getPosition(), 0.00005f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-1-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-1", e2.dummyCtrSignSummary());
    }
    
    @Test
    public void testBoxBoxCollisionMovement_2D() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	
    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e1, 1f, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e2, 1f, 1f));
    	
    	e1.setPosition(10, 10.5f);
    	e2.setPosition(40, 10);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	for (int i = 0; i < 400; i++)
    		e1.movePosition(0.1f, 0);
    	
    	assertEqualsExt(new Vector2(39f, 10.5f), e1.getPosition(), 0.00005f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-1-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-1", e2.dummyCtrSignSummary());
    }

    @Test
    public void testBoxBoxCollision_T() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	
    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e1, 1f, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e2, 1f, 1f));
    	
    	e1.setPosition(10f, 10f);
    	e2.setPosition(40f, 40f);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	e1.setPosition(10f, 10f);
    	e2.setPosition(10f, 10.5f);

    	assertEquals(e1.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(e2.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-1-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-1-0-0-0", e2.dummyCtrSignSummary());
    }

    @Test
    public void testBoxBoxCollision_TR() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	
    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e1, 1f, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e2, 1f, 1f));
    	
    	e1.setPosition(10f, 10f);
    	e2.setPosition(40f, 40f);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	e1.setPosition(10f, 10f);
    	e2.setPosition(10.5f, 10.5f);

    	assertEquals(e1.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(e2.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-1-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-1-0-0-0", e2.dummyCtrSignSummary());
    }

    @Test
    public void testBoxBoxCollision_R() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	
    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e1, 1f, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e2, 1f, 1f));
    	
    	e1.setPosition(10f, 10f);
    	e2.setPosition(40f, 40f);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	e1.setPosition(10f, 10f);
    	e2.setPosition(10.5f, 10f);

    	assertEquals(e1.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(e2.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-1-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-1-0-0-0", e2.dummyCtrSignSummary());
    }

    @Test
    public void testBoxBoxCollision_BR() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	
    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e1, 1f, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e2, 1f, 1f));
    	
    	e1.setPosition(10f, 10f);
    	e2.setPosition(40f, 40f);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	e1.setPosition(10f, 10f);
    	e2.setPosition(10.5f, 9.5f);

    	assertEquals(e1.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(e2.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-1-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-1-0-0-0", e2.dummyCtrSignSummary());
    }

    @Test
    public void testBoxBoxCollision_B() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	
    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e1, 1f, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e2, 1f, 1f));
    	
    	e1.setPosition(10f, 10f);
    	e2.setPosition(40f, 40f);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	e1.setPosition(10f, 10f);
    	e2.setPosition(10f, 9.5f);

    	assertEquals(e1.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(e2.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-1-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-1-0-0-0", e2.dummyCtrSignSummary());
    }

    @Test
    public void testBoxBoxCollision_BL() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	
    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e1, 1f, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e2, 1f, 1f));
    	
    	e1.setPosition(10f, 10f);
    	e2.setPosition(40f, 40f);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	e1.setPosition(10f, 10f);
    	e2.setPosition(9.5f, 9.5f);

    	assertEquals(e1.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(e2.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-1-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-1-0-0-0", e2.dummyCtrSignSummary());
    }

    @Test
    public void testBoxBoxCollision_L() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	
    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e1, 1f, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e2, 1f, 1f));
    	
    	e1.setPosition(10f, 10f);
    	e2.setPosition(40f, 40f);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	e1.setPosition(10f, 10f);
    	e2.setPosition(9.5f, 10f);

    	assertEquals(e1.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(e2.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-1-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-1-0-0-0", e2.dummyCtrSignSummary());
    }

    @Test
    public void testBoxBoxCollision_TL() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	
    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e1, 1f, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e2, 1f, 1f));
    	
    	e1.setPosition(10f, 10f);
    	e2.setPosition(40f, 40f);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	e1.setPosition(10f, 10f);
    	e2.setPosition(9.5f, 10.5f);

    	assertEquals(e1.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(e2.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-1-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-1-0-0-0", e2.dummyCtrSignSummary());
    }

    @Test
    public void testBoxBoxCollision_C() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	
    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e1, 1f, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e2, 1f, 1f));
    	
    	e1.setPosition(10f, 10f);
    	e2.setPosition(40f, 40f);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	e1.setPosition(10f, 10f);
    	e2.setPosition(10f, 10f);

    	assertEquals(e1.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(e2.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-1-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-1-0-0-0", e2.dummyCtrSignSummary());
    }

    @Test
    public void testCircleBoxCollisionMovement_1D() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	
    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e1, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e2, 2f, 2f));
    	
    	e1.setPosition(10, 10);
    	e2.setPosition(40, 10);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	for (int i = 0; i < 400; i++)
    		e1.movePosition(0.1f, 0);
    	
    	assertEqualsExt(new Vector2(38, 10), e1.getPosition());

    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-1-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-1", e2.dummyCtrSignSummary());
    }
    
    @Test
    public void testCircleBoxCollisionMovement_2D() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	
    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e1, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e2, 2f, 2f));
    	
    	e1.setPosition(10, 8.5f);
    	e2.setPosition(40, 10);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	for (int i = 0; i < 400; i++)
    		e1.movePosition(0.1f, 0);
    	
    	assertEqualsExt(new Vector2(38.1339746f, 8.5f), e1.getPosition(), 0.0001f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-1-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-1", e2.dummyCtrSignSummary());
    }
    
    @Test
    public void testCircleBoxCollision_T() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	
    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e1, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e2, 2f, 2f));
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(10f, 12.5f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(10f, 10.5f);

    	assertEquals(e1.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(e2.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-1-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-1-0-0", e2.dummyCtrSignSummary());
    }
    
    @Test
    public void testCircleBoxCollision_TR() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	
    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e1, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e2, 2f, 2f));
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(12f, 12f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(10.5f, 10.5f);

    	assertEquals(e1.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(e2.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-1-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-1-0-0", e2.dummyCtrSignSummary());
    }
    
    @Test
    public void testCircleBoxCollision_R() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	
    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e1, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e2, 2f, 2f));
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(12.5f, 10f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(10.5f, 10f);

    	assertEquals(e1.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(e2.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-1-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-1-0-0", e2.dummyCtrSignSummary());
    }
    
    @Test
    public void testCircleBoxCollision_BR() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	
    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e1, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e2, 2f, 2f));
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(12f, 8f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(10.5f, 9.5f);

    	assertEquals(e1.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(e2.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-1-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-1-0-0", e2.dummyCtrSignSummary());
    }
    
    @Test
    public void testCircleBoxCollision_B() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	
    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e1, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e2, 2f, 2f));
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(10f, 7.5f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(10f, 9.5f);

    	assertEquals(e1.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(e2.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-1-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-1-0-0", e2.dummyCtrSignSummary());
    }
    
    @Test
    public void testCircleBoxCollision_BL() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	
    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e1, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e2, 2f, 2f));
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(8f, 8f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(9.5f, 9.5f);

    	assertEquals(e1.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(e2.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-1-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-1-0-0", e2.dummyCtrSignSummary());
    }
    
    @Test
    public void testCircleBoxCollision_L() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	
    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e1, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e2, 2f, 2f));
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(7.5f, 10f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(9.5f, 10f);

    	assertEquals(e1.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(e2.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-1-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-1-0-0", e2.dummyCtrSignSummary());
    }
    
    @Test
    public void testCircleBoxCollision_TL() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	
    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e1, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e2, 2f, 2f));
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(8f, 12f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(9.5f, 10.5f);

    	assertEquals(e1.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(e2.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-1-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-1-0-0", e2.dummyCtrSignSummary());
    }
    
    @Test
    public void testCircleBoxCollision_C() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	
    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e1, 1f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e2, 2f, 2f));
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(100f, 100f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(10f, 10f);

    	assertEquals(e1.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(e2.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-1-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-1-0-0", e2.dummyCtrSignSummary());
    }
    
    
    
    
    
    
    
    
    @Test
    public void testBoxCircleCollisionMovement_1D() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	
    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e1, 2f, 2f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e2, 1f));
    	
    	e1.setPosition(10, 10);
    	e2.setPosition(40, 10);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	for (int i = 0; i < 400; i++)
    		e1.movePosition(0.1f, 0);
    	
    	assertEqualsExt(new Vector2(38, 10), e1.getPosition());

    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-1-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-1", e2.dummyCtrSignSummary());
    }
    
    @Test
    public void testBoxCircleCollisionMovement_2D() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	
    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e1, 2f, 2f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e2, 1f));
    	
    	e1.setPosition(10, 8.5f);
    	e2.setPosition(40, 10);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	for (int i = 0; i < 400; i++)
    		e1.movePosition(0.1f, 0);
    	
    	assertEqualsExt(new Vector2(38.1339746f, 8.5f), e1.getPosition(), 0.0001f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-1-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-1", e2.dummyCtrSignSummary());
    }
    
    @Test
    public void testBoxCircleCollision_T() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	
    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e1, 2f, 2f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e2, 1f));
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(10f, 12.5f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(10f, 10.5f);

    	assertEquals(e1.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(e2.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-1-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-1-0-0", e2.dummyCtrSignSummary());
    }
    
    @Test
    public void testBoxCircleCollision_TR() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	
    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e1, 2f, 2f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e2, 1f));
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(12f, 12f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(10.5f, 10.5f);

    	assertEquals(e1.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(e2.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-1-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-1-0-0", e2.dummyCtrSignSummary());
    }
    
    @Test
    public void testBoxCircleCollision_R() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	
    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e1, 2f, 2f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e2, 1f));
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(12.5f, 10f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(10.5f, 10f);

    	assertEquals(e1.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(e2.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-1-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-1-0-0", e2.dummyCtrSignSummary());
    }
    
    @Test
    public void testBoxCircleCollision_BR() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	
    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e1, 2f, 2f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e2, 1f));
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(12f, 8f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(10.5f, 9.5f);

    	assertEquals(e1.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(e2.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-1-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-1-0-0", e2.dummyCtrSignSummary());
    }
    
    @Test
    public void testBoxCircleCollision_B() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	
    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e1, 2f, 2f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e2, 1f));
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(10f, 7.5f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(10f, 9.5f);

    	assertEquals(e1.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(e2.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-1-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-1-0-0", e2.dummyCtrSignSummary());
    }
    
    @Test
    public void testBoxCircleCollision_BL() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	
    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e1, 2f, 2f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e2, 1f));
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(8f, 8f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(9.5f, 9.5f);

    	assertEquals(e1.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(e2.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-1-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-1-0-0", e2.dummyCtrSignSummary());
    }
    
    @Test
    public void testBoxCircleCollision_L() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	
    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e1, 2f, 2f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e2, 1f));
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(7.5f, 10f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(9.5f, 10f);

    	assertEquals(e1.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(e2.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-1-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-1-0-0", e2.dummyCtrSignSummary());
    }
    
    @Test
    public void testBoxCircleCollision_TL() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	
    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e1, 2f, 2f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e2, 1f));
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(8f, 12f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(9.5f, 10.5f);

    	assertEquals(e1.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(e2.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-1-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-1-0-0", e2.dummyCtrSignSummary());
    }
    
    @Test
    public void testBoxCircleCollision_C() {
    	DummyGameLayer l = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
    	
    	DummyEntity e1 = new DummyEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(0.5f, 0.5f, new CollisionBox(e1, 2f, 2f));
    	
    	DummyEntity e2 = new DummyEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(0.5f, 0.5f, new CollisionCircle(e2, 1f));
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(100f, 100f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	e2.setPosition(10f, 10f);
    	e1.setPosition(10f, 10f);

    	assertEquals(e1.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(e2.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-1-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-1-0-0", e2.dummyCtrSignSummary());
    }
}
