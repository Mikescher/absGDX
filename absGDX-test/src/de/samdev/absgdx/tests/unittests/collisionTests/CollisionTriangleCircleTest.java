package de.samdev.absgdx.tests.unittests.collisionTests;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionCircle;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionTriangle;
import de.samdev.absgdx.framework.math.FloatMath;
import de.samdev.absgdx.tests.dummy.DummyGameLayer;
import de.samdev.absgdx.tests.dummy.DummyNoCollisionTileMap;
import de.samdev.absgdx.tests.dummy.DummyNoTileCollisionEntity;

@RunWith(Parameterized.class)
public class CollisionTriangleCircleTest extends CollisionTest {

	@Parameters(name= "({1}|{2}) / {0}")
	public static Collection<Object[]> data() { return getParameters(); }
	
	public CollisionTriangleCircleTest(int mapScale, int mapW, int mapH) {
		super(mapScale, mapW, mapH);
	}
	
    //#########################################################################
    
    /*
     *   +              __  
     *   |\    -->     /  \ 
     *   | \           \  / 
     *   +--+           ""  
     */
	@Test
    public void testTriangleCircleCollisionMovement_Left() {
    	DummyGameLayer l = new DummyGameLayer(this.mapWidth, this.mapHeight, new DummyNoCollisionTileMap(this.mapWidth, this.mapHeight), this.expMapScale);
    	
    	DummyNoTileCollisionEntity e1 = new DummyNoTileCollisionEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f, 0f, 0f, 1f, 1f, 0f));
    	
    	DummyNoTileCollisionEntity e2 = new DummyNoTileCollisionEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/2f, 1/2f, new CollisionCircle(e2, 1/2f));
    	
    	e1.setPosition(10, 10);
    	e2.setPosition(20, 10);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	for (int i = 0; i < 8000; i++)
    		e1.movePosition(+0.01f, 0.0f);
    	
    	assertEqualsExt(new Vector2(20 - FloatMath.fsin(FloatMath.toRadians(45)), 10), e1.getPosition(), 0.00005f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-1-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-1", e2.dummyCtrSignSummary());
    }
    
    /*
     *               __ 
     *              /  \
     *              \  /
     *   +     --+   "" 
     *   |\     /|     
     *   | \   /      
     *   +--+         
     */
	@Test
    public void testTriangleCircleCollisionMovement_BottomLeft_Edge() {
    	DummyGameLayer l = new DummyGameLayer(this.mapWidth, this.mapHeight, new DummyNoCollisionTileMap(this.mapWidth, this.mapHeight), this.expMapScale);
    	
    	DummyNoTileCollisionEntity e1 = new DummyNoTileCollisionEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f, 0f, 0f, 1f, 1f, 0f));
    	
    	DummyNoTileCollisionEntity e2 = new DummyNoTileCollisionEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/2f, 1/2f, new CollisionCircle(e2, 1/2f));
    	
    	e1.setPosition(10, 10);
    	e2.setPosition(20, 20);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	for (int i = 0; i < 8000; i++)
    		e1.movePosition(+0.01f, +0.01f);
    	
    	assertEqualsExt(new Vector2(19.65f, 19.643f), e1.getPosition(), 0.01f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-1-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-1", e2.dummyCtrSignSummary());
    }
    
    /*
     *               __ 
     *              /  \
     *              \  /
     *               ""
     *               
     *   +--+  --+   
     *    \ |   /|     
     *     \|  /      
     *      +         
     */
	@Test
    public void testTriangleCircleCollisionMovement_BottomLeft_Corner() {
    	DummyGameLayer l = new DummyGameLayer(this.mapWidth, this.mapHeight, new DummyNoCollisionTileMap(this.mapWidth, this.mapHeight), this.expMapScale);
    	
    	DummyNoTileCollisionEntity e1 = new DummyNoTileCollisionEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 1f, 0f, 1f, 1f, 0f, 1f));
    	
    	DummyNoTileCollisionEntity e2 = new DummyNoTileCollisionEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/2f, 1/2f, new CollisionCircle(e2, 1/2f));
    	
    	e1.setPosition(10, 10);
    	e2.setPosition(20, 20);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	for (int i = 0; i < 8000; i++)
    		e1.movePosition(+0.01f, +0.01f);
    	
    	assertEqualsExt(new Vector2(19.480f, 19.480f), e1.getPosition(), 0.002f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-1-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-1", e2.dummyCtrSignSummary());
    }

    @Test
    public void testTriangleCircleCollision_Equal() {
    	DummyGameLayer l = new DummyGameLayer(this.mapWidth, this.mapHeight, new DummyNoCollisionTileMap(this.mapWidth, this.mapHeight), this.expMapScale);

    	DummyNoTileCollisionEntity e1 = new DummyNoTileCollisionEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 1f, 0f, 1f, 1f, 0f, 1f));
    	
    	DummyNoTileCollisionEntity e2 = new DummyNoTileCollisionEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/2f, 1/2f, new CollisionCircle(e2, 1/2f));
    	
    	e1.setPosition(10f, 10f);
    	e2.setPosition(0f, 0f);
    	
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
    public void testTriangleCircleCollision_Outside() {
    	DummyGameLayer l = new DummyGameLayer(this.mapWidth, this.mapHeight, new DummyNoCollisionTileMap(this.mapWidth, this.mapHeight), this.expMapScale);

    	DummyNoTileCollisionEntity e1 = new DummyNoTileCollisionEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 1f, 0f, 1f, 1f, 0f, 1f));
    	
    	DummyNoTileCollisionEntity e2 = new DummyNoTileCollisionEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/2f, 1/2f, new CollisionCircle(e2, 10f));
    	
    	e1.setPosition(100f, 100f);
    	e2.setPosition(0f, 0f);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	e1.setPosition(10f, 10f);
    	e2.setPosition(7f, 7f);

    	assertEquals(e1.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(e2.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-1-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-1-0-0-0", e2.dummyCtrSignSummary());
    }

    @Test
    public void testTriangleCircleCollision_Inside() {
    	DummyGameLayer l = new DummyGameLayer(this.mapWidth, this.mapHeight, new DummyNoCollisionTileMap(this.mapWidth, this.mapHeight), this.expMapScale);

    	DummyNoTileCollisionEntity e1 = new DummyNoTileCollisionEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 1f, 0f, 1f, 1f, 0f, 1f));
    	
    	DummyNoTileCollisionEntity e2 = new DummyNoTileCollisionEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/2f, 1/2f, new CollisionCircle(e2, 0.1f));
    	
    	e1.setPosition(10f, 10f);
    	e2.setPosition(0f, 0f);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	e1.setPosition(10f, 10f);
    	e2.setPosition(10.12f, 10.12f);

    	assertEquals(e1.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(e2.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-1-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-1-0-0-0", e2.dummyCtrSignSummary());
    }
}
