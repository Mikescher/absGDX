package de.samdev.absgdx.tests.unittests.collisionTests;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionTriangle;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionBox;
import de.samdev.absgdx.tests.dummy.DummyGameLayer;
import de.samdev.absgdx.tests.dummy.DummyNoCollisionTileMap;
import de.samdev.absgdx.tests.dummy.DummyNoTileCollisionEntity;

@RunWith(Parameterized.class)
public class CollisionBoxTriangleTest extends CollisionTest {

	@Parameters(name= "({1}|{2}) / {0}")
	public static Collection<Object[]> data() { return getParameters(); }
	
	public CollisionBoxTriangleTest(int mapScale, int mapW, int mapH) {
		super(mapScale, mapW, mapH);
	}
	
    //#########################################################################
    
    /*
     *   +            +----+
     *   |\    <--    |    |
     *   | \          |    |
     *   +--+         +----+
     */
	@Test
    public void testBoxTriangleCollisionMovement_CornerCorner() {
    	DummyGameLayer l = new DummyGameLayer(this.mapWidth, this.mapHeight, new DummyNoCollisionTileMap(this.mapWidth, this.mapHeight), this.expMapScale);
    	
    	DummyNoTileCollisionEntity e1 = new DummyNoTileCollisionEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f, 0f, 0f, 1f, 1f, 0f));
    	
    	DummyNoTileCollisionEntity e2 = new DummyNoTileCollisionEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/2f, 1/2f, new CollisionBox(e2, 1f, 1f));
    	
    	e1.setPosition(10, 10);
    	e2.setPosition(20, 10);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	for (int i = 0; i < 8000; i++)
    		e2.movePosition(-0.01f, 0.0f);
    	
    	assertEqualsExt(new Vector2(11, 10), e2.getPosition(), 0.00005f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-0-1", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-1-0", e2.dummyCtrSignSummary());
    }
    
    /*
     *   +            
     *   |\    <--    +----+
     *   | \          |    |
     *   +--+         |    |
     *                +----+
     */
	@Test
    public void testBoxTriangleCollisionMovement_CornerEdge() {
    	DummyGameLayer l = new DummyGameLayer(this.mapWidth, this.mapHeight, new DummyNoCollisionTileMap(this.mapWidth, this.mapHeight), this.expMapScale);
    	
    	DummyNoTileCollisionEntity e1 = new DummyNoTileCollisionEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f, 0f, 0f, 1f, 1f, 0f));
    	
    	DummyNoTileCollisionEntity e2 = new DummyNoTileCollisionEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/2f, 1/2f, new CollisionBox(e2, 1f, 1f));
    	
    	e1.setPosition(10, 10);
    	e2.setPosition(20, 9.5f);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	for (int i = 0; i < 8000; i++)
    		e2.movePosition(-0.01f, 0.0f);
    	
    	assertEqualsExt(new Vector2(11f, 9.5f), e2.getPosition(), 0.00005f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-0-1", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-1-0", e2.dummyCtrSignSummary());
    }
    
    /*
     *      +         +----+
     *     /|  <--    |    |
     *    / |         |    |
     *   +--+         +----+
     */
	@Test
    public void testBoxTriangleCollisionMovement_EdgeEdge() {
    	DummyGameLayer l = new DummyGameLayer(this.mapWidth, this.mapHeight, new DummyNoCollisionTileMap(this.mapWidth, this.mapHeight), this.expMapScale);
    	
    	DummyNoTileCollisionEntity e1 = new DummyNoTileCollisionEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(2/3f, 1/3f, new CollisionTriangle(e1, 0f, 0f, 1f, 1f, 1f, 0f));
    	
    	DummyNoTileCollisionEntity e2 = new DummyNoTileCollisionEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/2f, 1/2f, new CollisionBox(e2, 1f, 1f));
    	
    	e1.setPosition(10, 10);
    	e2.setPosition(20, 10);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	for (int i = 0; i < 8000; i++)
    		e2.movePosition(-0.01f, 0.0f);
    	
    	assertEqualsExt(new Vector2(11, 10), e2.getPosition(), 0.00005f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-0-1", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-1-0", e2.dummyCtrSignSummary());
    }
    
    /*
     *      +         
     *     /|  <--    +----+
     *    / |         |    |
     *   +--+         |    |
     *                +----+
     */
	@Test
    public void testBoxTriangleCollisionMovement_EdgeEdgeShifted() {
    	DummyGameLayer l = new DummyGameLayer(this.mapWidth, this.mapHeight, new DummyNoCollisionTileMap(this.mapWidth, this.mapHeight), this.expMapScale);
    	
    	DummyNoTileCollisionEntity e1 = new DummyNoTileCollisionEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(2/3f, 1/3f, new CollisionTriangle(e1, 0f, 0f, 1f, 1f, 1f, 0f));
    	
    	DummyNoTileCollisionEntity e2 = new DummyNoTileCollisionEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/2f, 1/2f, new CollisionBox(e2, 1f, 1f));
    	
    	e1.setPosition(10, 10);
    	e2.setPosition(20, 9.5f);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	for (int i = 0; i < 8000; i++)
    		e2.movePosition(-0.01f, 0.0f);
    	
    	assertEqualsExt(new Vector2(11f, 9.5f), e2.getPosition(), 0.00005f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-0-1", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-1-0", e2.dummyCtrSignSummary());
    }
    
    /*
     *        
     *        +----+
     *        |    |
     *        |    |
     *        +----+
     *        
     * +--+
     *  \ |  
     *   \|    / 
     *    +  |/
     *       +--
     */
	@Test
    public void testBoxTriangleCollisionMovement_CornerCornerDiagonal() {
    	DummyGameLayer l = new DummyGameLayer(this.mapWidth, this.mapHeight, new DummyNoCollisionTileMap(this.mapWidth, this.mapHeight), this.expMapScale);
    	
    	DummyNoTileCollisionEntity e1 = new DummyNoTileCollisionEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(2/3f, 2/3f, new CollisionTriangle(e1, 1f, 0f, 1f, 1f, 0f, 1f));
    	
    	DummyNoTileCollisionEntity e2 = new DummyNoTileCollisionEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/2f, 1/2f, new CollisionBox(e2, 1f, 1f));
    	
    	e1.setPosition(10, 10);
    	e2.setPosition(20, 20);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	for (int i = 0; i < 8000; i++)
    		if (e2.movePosition(-0.01f, -0.01f)) break;
    	
    	assertEqualsExt(new Vector2(11, 11), e2.getPosition(), 0.01f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-0-1", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-1-0", e2.dummyCtrSignSummary());
    }
    
    /*
     *        
     *        +----+
     *        |    |
     *        |    |
     *        +----+
     *        
     * +
     * |\    
     * | \     /
     * +--+  |/
     *       +--
     */
	@Test
    public void testBoxTriangleCollisionMovement_CornerEdgeDiagonal() {
    	DummyGameLayer l = new DummyGameLayer(this.mapWidth, this.mapHeight, new DummyNoCollisionTileMap(this.mapWidth, this.mapHeight), this.expMapScale);
    	
    	DummyNoTileCollisionEntity e1 = new DummyNoTileCollisionEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 1f, 0f, 0f, 0f, 0f, 1f));
    	
    	DummyNoTileCollisionEntity e2 = new DummyNoTileCollisionEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/2f, 1/2f, new CollisionBox(e2, 1f, 1f));
    	
    	e1.setPosition(10, 10);
    	e2.setPosition(20, 20);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	for (int i = 0; i < 8000; i++)
    		e2.movePosition(-0.01f, -0.01f);
    	
    	assertEqualsExt(new Vector2(10.5f, 10.5f), e2.getPosition(), 0.01f);

    	assertEquals(null, l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	assertEquals(null, l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-0-0-1", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-1-0", e2.dummyCtrSignSummary());
    }

    @Test
    public void testBoxTriangleCollision_Corner() {
    	DummyGameLayer l = new DummyGameLayer(this.mapWidth, this.mapHeight, new DummyNoCollisionTileMap(this.mapWidth, this.mapHeight), this.expMapScale);
    	
    	DummyNoTileCollisionEntity e1 = new DummyNoTileCollisionEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f, 0f, 0f, 1f, 1f, 0f));
    	
    	DummyNoTileCollisionEntity e2 = new DummyNoTileCollisionEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/2f, 1/2f, new CollisionBox(e2, 1f, 1f));
    	
    	e1.setPosition(10f, 10f);
    	e2.setPosition(0f, 0f);
    	
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
    public void testBoxTriangleCollision_Edge() {
    	DummyGameLayer l = new DummyGameLayer(this.mapWidth, this.mapHeight, new DummyNoCollisionTileMap(this.mapWidth, this.mapHeight), this.expMapScale);
    	
    	DummyNoTileCollisionEntity e1 = new DummyNoTileCollisionEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f, 0f, 0f, 1f, 1f, 0f));
    	
    	DummyNoTileCollisionEntity e2 = new DummyNoTileCollisionEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/2f, 1/2f, new CollisionBox(e2, 1f, 1f));
    	
    	e2.setPosition(0f, 0f);
    	e1.setPosition(10f, 10f);
    	
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
    public void testBoxTriangleCollision_Inside_1() {
    	DummyGameLayer l = new DummyGameLayer(this.mapWidth, this.mapHeight, new DummyNoCollisionTileMap(this.mapWidth, this.mapHeight), this.expMapScale);
    	
    	DummyNoTileCollisionEntity e1 = new DummyNoTileCollisionEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f, 0f, 0f, 1f, 1f, 0f));
    	
    	DummyNoTileCollisionEntity e2 = new DummyNoTileCollisionEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/2f, 1/2f, new CollisionBox(e2, 10f, 10f));
    	
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
    public void testBoxTriangleCollision_Inside_2() {
    	DummyGameLayer l = new DummyGameLayer(this.mapWidth, this.mapHeight, new DummyNoCollisionTileMap(this.mapWidth, this.mapHeight), this.expMapScale);
    	
    	DummyNoTileCollisionEntity e1 = new DummyNoTileCollisionEntity();
    	l.addEntity(e1);
    	e1.addCollisionGeo(1/3f, 1/3f, new CollisionTriangle(e1, 0f, 0f, 0f, 10f, 10f, 0f));
    	
    	DummyNoTileCollisionEntity e2 = new DummyNoTileCollisionEntity();
    	l.addEntity(e2);
    	e2.addCollisionGeo(1/2f, 1/2f, new CollisionBox(e2, 1f, 1f));
    	
    	e1.setPosition(10f, 10f);
    	e2.setPosition(0f, 0f);
    	
    	assertEquals("0-1-0-0-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-0-0-0-0", e2.dummyCtrSignSummary());
    	
    	e1.setPosition(10f, 10f);
    	e2.setPosition(10.5f, 10.5f);

    	assertEquals(e1.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e2.listCollisionGeometries().next()));
    	assertEquals(e2.listCollisionGeometries().next(), l.collisionMap().getFirstCollider(e1.listCollisionGeometries().next()));
    	
    	assertEquals("0-1-0-1-0-0", e1.dummyCtrSignSummary());
    	assertEquals("0-1-1-0-0-0", e2.dummyCtrSignSummary());
    }
}
