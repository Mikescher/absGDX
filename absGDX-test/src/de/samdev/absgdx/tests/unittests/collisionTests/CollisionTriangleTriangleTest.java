package de.samdev.absgdx.tests.unittests.collisionTests;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionTriangle;
import de.samdev.absgdx.tests.dummy.DummyGameLayer;
import de.samdev.absgdx.tests.dummy.DummyNoCollisionTileMap;
import de.samdev.absgdx.tests.dummy.DummyNoTileCollisionEntity;

@RunWith(Parameterized.class)
public class CollisionTriangleTriangleTest extends CollisionTest {

	@Parameters(name= "({1}|{2}) / {0}")
	public static Collection<Object[]> data() { return getParameters(); }
	
	public CollisionTriangleTriangleTest(int mapScale, int mapW, int mapH) {
		super(mapScale, mapW, mapH);
	}
	
    //#########################################################################
    
    /*
     *   +
     *   |\
     *   | \
     *   +--+
     *   
     *    |
     *    v
     *    
     *   +
     *   |\
     *   | \
     *   +--+
     */
	@Test
    public void testTriangleTriangleCollisionMovement_TopDown() {
    	DummyGameLayer l = new DummyGameLayer(this.mapWidth, this.mapHeight, new DummyNoCollisionTileMap(this.mapWidth, this.mapHeight), this.expMapScale);
    	
    	DummyNoTileCollisionEntity e1 = new DummyNoTileCollisionEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f, 0f, 0f, 1f, 1f, 0f));
    	
    	DummyNoTileCollisionEntity e2 = new DummyNoTileCollisionEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e2, 0f, 0f, 0f, 1f, 1f, 0f));
    	
    	e1.setPosition(00, 10);
    	e2.setPosition(00, 00);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	for (int i = 0; i < 8000; i++)
    		e1.movePosition(+0.0f, -0.01f);
    	
    	assertEqualsExt(new Vector2(00, 01), e1.getPosition(), 0.00005f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-1-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-1", e2.dummyCtrSignSummary());
    }

    /*
     *     +
     *     |\
     *     | \
     *     +--+
     *   
     *     |
     *     v
     *    
     *   +
     *   |\
     *   | \
     *   +--+
     */
    @Test
    public void testTriangleTriangleCollisionMovement_TopDownShifted() {
    	DummyGameLayer l = new DummyGameLayer(this.mapWidth, this.mapHeight, new DummyNoCollisionTileMap(this.mapWidth, this.mapHeight), this.expMapScale);
    	
    	DummyNoTileCollisionEntity e1 = new DummyNoTileCollisionEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f, 0f, 0f, 1f, 1f, 0f));
    	
    	DummyNoTileCollisionEntity e2 = new DummyNoTileCollisionEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e2, 0f, 0f, 0f, 1f, 1f, 0f));
    	
    	e1.setPosition(0.5f, 10);
    	e2.setPosition(00, 00);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	for (int i = 0; i < 8000; i++)
    		e1.movePosition(+0.0f, -0.01f);
    	
    	assertEqualsExt(new Vector2(00.5f, 0.5f), e1.getPosition(), 0.00005f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-1-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-1", e2.dummyCtrSignSummary());
    }

    /*    /
     *   /
     * |/
     * ---
     *       +--+
     *   +    \ |
     *   |\    \|
     *   | \    +
     *   +--+
     */
    @Test
    public void testTriangleTriangleCollisionMovement_Hypothenuse() {
    	DummyGameLayer l = new DummyGameLayer(this.mapWidth, this.mapHeight, new DummyNoCollisionTileMap(this.mapWidth, this.mapHeight), this.expMapScale);
    	
    	DummyNoTileCollisionEntity e1 = new DummyNoTileCollisionEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f, 0f, 0f, 1f, 1f, 0f));
    	
    	DummyNoTileCollisionEntity e2 = new DummyNoTileCollisionEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(2/3f, 2/3f, new CollisionTriangle(e2, 1f, 0f, 1f, 1f, 1f, 0f));
    	
    	e1.setPosition(10, 10);
    	e2.setPosition(00, 00);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	for (int i = 0; i < 8000; i++)
    		e1.movePosition(-0.01f, -0.01f);
    	
    	assertEqualsExt(new Vector2(00, 00), e1.getPosition(), 0.00005f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-1-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-1", e2.dummyCtrSignSummary());
    }

    /*    /
     *   /
     * |/      +
     * ---     |\
     *         | \
     *   +     +--+
     *   |\     
     *   | \    
     *   +--+
     */
    @Test
    public void testTriangleTriangleCollisionMovement_Corner() {
    	DummyGameLayer l = new DummyGameLayer(this.mapWidth, this.mapHeight, new DummyNoCollisionTileMap(this.mapWidth, this.mapHeight), this.expMapScale);
    	
    	DummyNoTileCollisionEntity e1 = new DummyNoTileCollisionEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f, 0f, 0f, 1f, 1f, 0f));
    	
    	DummyNoTileCollisionEntity e2 = new DummyNoTileCollisionEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e2, 0f, 0f, 0f, 1f, 1f, 0f));
    	
    	e1.setPosition(10, 10);
    	e2.setPosition(00, 00);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	for (int i = 0; i < 8000; i++)
    		e1.movePosition(-0.01f, -0.01f);
    	
    	assertEqualsExt(new Vector2(0.5f, 0.5f), e1.getPosition(), 0.00005f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-1-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-1", e2.dummyCtrSignSummary());
    }
    
    /*   
     *     <----  
     *   +         +
     *   |\       /|
     *   | \     / |
     *   +--+   +--+
     */
    @Test
    public void testTriangleTriangleCollisionMovement_Right() {
    	DummyGameLayer l = new DummyGameLayer(this.mapWidth, this.mapHeight, new DummyNoCollisionTileMap(this.mapWidth, this.mapHeight), this.expMapScale);
    	
    	DummyNoTileCollisionEntity e1 = new DummyNoTileCollisionEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f, 0f, 0f, 1f, 1f, 0f));
    	
    	DummyNoTileCollisionEntity e2 = new DummyNoTileCollisionEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(2/3f, 1/3f, new CollisionTriangle(e2, 0f, 0f, 1f, 1f, 1f, 0f));
    	
    	e1.setPosition(10, 00);
    	e2.setPosition(00, 00);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	for (int i = 0; i < 8000; i++)
    		e1.movePosition(-0.01f, 0.00f);
    	
    	assertEqualsExt(new Vector2(1.0f, 0.0f), e1.getPosition(), 0.00005f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-1-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-1", e2.dummyCtrSignSummary());
    }
    
    /*   
     *      
     *   +      
     *   |\  
     *   | \      +--
     *   +--+     |\
     *              \
     *               \ 
     *             +      
     *             |\  
     *             | \  
     *             +--+
     */
    @Test
    public void testTriangleTriangleCollisionMovement_BottomRight() {
    	DummyGameLayer l = new DummyGameLayer(this.mapWidth, this.mapHeight, new DummyNoCollisionTileMap(this.mapWidth, this.mapHeight), this.expMapScale);
    	
    	DummyNoTileCollisionEntity e1 = new DummyNoTileCollisionEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f, 0f, 0f, 1f, 1f, 0f));
    	
    	DummyNoTileCollisionEntity e2 = new DummyNoTileCollisionEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e2, 0f, 0f, 0f, 1f, 1f, 0f));
    	
    	e1.setPosition(10, -10);
    	e2.setPosition(00, 00);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	for (int i = 0; i < 8000; i++)
    		e1.movePosition(-0.01f, +0.01f);
    	
    	assertEqualsExt(new Vector2(+1.0f, -1.0f), e1.getPosition(), 0.00005f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-1-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-1", e2.dummyCtrSignSummary());
    }
    
    /*
     *   +      
     *   |\  
     *   | \     
     *   +--+    
     *      
     *     ^
     *     |
     *             
     *   +--+
     *   | /
     *   |/
     *   +
     */
    @Test
    public void testTriangleTriangleCollisionMovement_Bottom() {
    	DummyGameLayer l = new DummyGameLayer(this.mapWidth, this.mapHeight, new DummyNoCollisionTileMap(this.mapWidth, this.mapHeight), this.expMapScale);
    	
    	DummyNoTileCollisionEntity e1 = new DummyNoTileCollisionEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f, 0f, 0f, 1f, 1f, 0f));
    	
    	DummyNoTileCollisionEntity e2 = new DummyNoTileCollisionEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/3f, 2/3f, new CollisionTriangle(e2, 0f, 0f, 0f, 1f, 1f, 1f));
    	
    	e1.setPosition(00, -10);
    	e2.setPosition(00, 00);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	for (int i = 0; i < 8000; i++)
    		e1.movePosition(0.00f, +0.01f);
    	
    	assertEqualsExt(new Vector2(0, -1), e1.getPosition(), 0.00005f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-1-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-1", e2.dummyCtrSignSummary());
    }
    
    /*
     *    -->
     * 
     *   +         +     
     *   |\        |\    
     *   | \       | \    
     *   +--+      +--+   
     */
    @Test
    public void testTriangleTriangleCollisionMovement_Left() {
    	DummyGameLayer l = new DummyGameLayer(this.mapWidth, this.mapHeight, new DummyNoCollisionTileMap(this.mapWidth, this.mapHeight), this.expMapScale);
    	
    	DummyNoTileCollisionEntity e1 = new DummyNoTileCollisionEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f, 0f, 0f, 1f, 1f, 0f));
    	
    	DummyNoTileCollisionEntity e2 = new DummyNoTileCollisionEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e2, 0f, 0f, 0f, 1f, 1f, 0f));
    	
    	e1.setPosition(-10, 00);
    	e2.setPosition(00, 00);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	for (int i = 0; i < 8000; i++)
    		e1.movePosition(+0.01f, 0.00f);
    	
    	assertEqualsExt(new Vector2(-1, 0), e1.getPosition(), 0.00005f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-1-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-1", e2.dummyCtrSignSummary());
    }
    
    /*
     *    -->
     *  +    
     *  |\        +     
     *  | \       |\    
     *  +--+      | \    
     *            +--+   
     */
    @Test
    public void testTriangleTriangleCollisionMovement_LeftShifted() {
    	DummyGameLayer l = new DummyGameLayer(this.mapWidth, this.mapHeight, new DummyNoCollisionTileMap(this.mapWidth, this.mapHeight), this.expMapScale);
    	
    	DummyNoTileCollisionEntity e1 = new DummyNoTileCollisionEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f, 0f, 0f, 1f, 1f, 0f));
    	
    	DummyNoTileCollisionEntity e2 = new DummyNoTileCollisionEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e2, 0f, 0f, 0f, 1f, 1f, 0f));
    	
    	e1.setPosition(-10, 0.5f);
    	e2.setPosition(00, 00);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	for (int i = 0; i < 8000; i++)
    		e1.movePosition(+0.01f, 0.00f);
    	
    	assertEqualsExt(new Vector2(-1, 0.5f), e1.getPosition(), 0.00005f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-1-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-1", e2.dummyCtrSignSummary());
    }

    @Test
    public void testTriangleTriangleCollision_SingleIntersect() {
    	DummyGameLayer l = new DummyGameLayer(this.mapWidth, this.mapHeight, new DummyNoCollisionTileMap(this.mapWidth, this.mapHeight), this.expMapScale);
    	
    	DummyNoTileCollisionEntity e1 = new DummyNoTileCollisionEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f, 0f, 0f, 1f, 1f, 0f));
    	
    	DummyNoTileCollisionEntity e2 = new DummyNoTileCollisionEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e2, 0f, 0f, 0f, 1f, 1f, 0f));
    	
    	e1.setPosition(10f, 10f);
    	e2.setPosition(11f, 11f);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	e1.setPosition(10f, 10f);
    	e2.setPosition(10.25f, 10.25f);

    	assertEquals(e1.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(e2.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-1-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-1-0-0-0", e2.dummyCtrSignSummary());
    }

    @Test
    public void testTriangleTriangleCollision_DoubleIntersect() {
    	DummyGameLayer l = new DummyGameLayer(this.mapWidth, this.mapHeight, new DummyNoCollisionTileMap(this.mapWidth, this.mapHeight), this.expMapScale);
    	
    	DummyNoTileCollisionEntity e1 = new DummyNoTileCollisionEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f, 0f, 0f, 1f, 1f, 0f));
    	
    	DummyNoTileCollisionEntity e2 = new DummyNoTileCollisionEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(2/3f, 2/3f, new CollisionTriangle(e2, 1f, 0f, 1f, 1f, 0f, 1f));
    	
    	e1.setPosition(10f, 10f);
    	e2.setPosition(8.5f, 8.5f);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	e1.setPosition(10f, 10f);
    	e2.setPosition(9.25f, 9.25f);

    	assertEquals(e1.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(e2.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-1-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-1-0-0-0", e2.dummyCtrSignSummary());
    }

    @Test
    public void testTriangleTriangleCollision_Inside() {
    	DummyGameLayer l = new DummyGameLayer(this.mapWidth, this.mapHeight, new DummyNoCollisionTileMap(this.mapWidth, this.mapHeight), this.expMapScale);
    	
    	DummyNoTileCollisionEntity e1 = new DummyNoTileCollisionEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f, 0f, 0f, 5f, 5f, 0f));
    	
    	DummyNoTileCollisionEntity e2 = new DummyNoTileCollisionEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e2, 0f, 0f, 0f, 1f, 1f, 0f));
    	
    	e1.setPosition(10f, 10f);
    	e2.setPosition(0f, 0f);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	e1.setPosition(10f, 10f);
    	e2.setPosition(11f, 11f);

    	assertEquals(e1.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(e2.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-1-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-1-0-0-0", e2.dummyCtrSignSummary());
    }
}
