package de.samdev.absgdx.tests.unittests;

import org.junit.Test;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.framework.layer.GameLayer;
import de.samdev.absgdx.framework.map.TileMap;
import de.samdev.absgdx.framework.map.mapscaleresolver.MinimumBoundaryMapScaleResolver;
import de.samdev.absgdx.tests.BaseUnitTest;
import de.samdev.absgdx.tests.dummy.DummyEntity;
import de.samdev.absgdx.tests.dummy.DummyGameLayer;

public class GameLayerTest extends BaseUnitTest {

    @Test
    public void testGetVisibleMapBox() {
    	GameLayer layer = new DummyGameLayer(100, 100, TileMap.createEmptyMap(20, 20));
		
		layer.setMapScaleResolver(new MinimumBoundaryMapScaleResolver(10, 10));
		
		assertEqualsExt(new Rectangle(0, 0, 10, 10), layer.getVisibleMapBox());
    }

    @Test
    public void testGetVisibleMapBox2() {
    	GameLayer layer = new DummyGameLayer(200, 100, TileMap.createEmptyMap(20, 20));
		
		layer.setMapScaleResolver(new MinimumBoundaryMapScaleResolver(10, 10));
		
		assertEqualsExt(new Rectangle(0, 0, 10, 5), layer.getVisibleMapBox());
    }

    @Test
    public void testGetVisibleMapBox3() {
    	GameLayer layer = new DummyGameLayer(100, 200, TileMap.createEmptyMap(20, 20));
		
		layer.setMapScaleResolver(new MinimumBoundaryMapScaleResolver(10, 10));
		
		assertEqualsExt(new Rectangle(0, 0, 5, 10), layer.getVisibleMapBox());
    }

    @Test
    public void testSetBoundedOffset() {
    	GameLayer layer = new DummyGameLayer(100, 100, TileMap.createEmptyMap(100, 100));
		
		layer.setMapScaleResolver(new MinimumBoundaryMapScaleResolver(10, 10));
	
		layer.setBoundedOffset(new Vector2(0, 0));
		assertEqualsExt(new Rectangle(0, 0, 10, 10), layer.getVisibleMapBox());
	
		layer.setBoundedOffset(new Vector2(1, 1));
		assertEqualsExt(new Rectangle(1, 1, 10, 10), layer.getVisibleMapBox());
	
		layer.setBoundedOffset(new Vector2(-10, -10));
		assertEqualsExt(new Rectangle(0, 0, 10, 10), layer.getVisibleMapBox());
	
		layer.setBoundedOffset(new Vector2(-10, 10));
		assertEqualsExt(new Rectangle(0, 10, 10, 10), layer.getVisibleMapBox());
	
		layer.setBoundedOffset(new Vector2(10, -10));
		assertEqualsExt(new Rectangle(10, 0, 10, 10), layer.getVisibleMapBox());
	
		layer.setBoundedOffset(new Vector2(90, 90));
		assertEqualsExt(new Rectangle(90, 90, 10, 10), layer.getVisibleMapBox());
	
		layer.setBoundedOffset(new Vector2(100, 100));
		assertEqualsExt(new Rectangle(90, 90, 10, 10), layer.getVisibleMapBox());
    }

    @Test
    public void testScrollMapToEntity() {
    	GameLayer layer = new DummyGameLayer(10, 10, TileMap.createEmptyMap(100, 100));
    	
    	DummyEntity e1 = new DummyEntity();
    	
    	e1.setPosition(0, 0);
    	layer.scrollMapToEntity(e1, 0);
    	assertEqualsExt(new Vector2(0f, 0f), layer.getMapOffset());
    	
    	e1.setPosition(0, 0);
    	layer.scrollMapToEntity(e1, 10);
    	assertEqualsExt(new Vector2(0f, 0f), layer.getMapOffset());
    	
    	e1.setPosition(-1, -1);
    	layer.scrollMapToEntity(e1, 0);
    	assertEqualsExt(new Vector2(0f, 0f), layer.getMapOffset());
    	
    	e1.setPosition(-1, -1);
    	layer.scrollMapToEntity(e1, 1);
    	assertEqualsExt(new Vector2(0f, 0f), layer.getMapOffset());
    	
    	e1.setPosition(20, 3);
    	layer.scrollMapToEntity(e1, 0);
    	assertEqualsExt(new Vector2(11f, 0f), layer.getMapOffset());
    	
    	e1.setPosition(20, 3);
    	layer.scrollMapToEntity(e1, 1);
    	assertEqualsExt(new Vector2(12f, 0f), layer.getMapOffset());
    	
    	e1.setPosition(10, 3);
    	layer.scrollMapToEntity(e1, 0);
    	assertEqualsExt(new Vector2(10f, 0f), layer.getMapOffset());
    	
    	e1.setPosition(10, 3);
    	layer.scrollMapToEntity(e1, 1);
    	assertEqualsExt(new Vector2(9f, 0f), layer.getMapOffset());
    	
    	e1.setPosition(0, 0);
    	layer.scrollMapToEntity(e1, 0);
    	assertEqualsExt(new Vector2(0f, 0f), layer.getMapOffset());

    	
    	
    	e1.setPosition(3, 20);
    	layer.scrollMapToEntity(e1, 0);
    	assertEqualsExt(new Vector2(0f, 11f), layer.getMapOffset());
    	
    	e1.setPosition(3, 20);
    	layer.scrollMapToEntity(e1, 1);
    	assertEqualsExt(new Vector2(0f, 12f), layer.getMapOffset());
    	
    	e1.setPosition(3, 10);
    	layer.scrollMapToEntity(e1, 0);
    	assertEqualsExt(new Vector2(0f, 10f), layer.getMapOffset());
    	
    	e1.setPosition(3, 10);
    	layer.scrollMapToEntity(e1, 1);
    	assertEqualsExt(new Vector2(0f, 9f), layer.getMapOffset());
    	
    	e1.setPosition(0, 0);
    	layer.scrollMapToEntity(e1, 0);
    	assertEqualsExt(new Vector2(0f, 0f), layer.getMapOffset());
    	

    	
    	e1.setPosition(10, 10);
    	layer.scrollMapToEntity(e1, 1);
    	assertEqualsExt(new Vector2(2f, 2f), layer.getMapOffset());
    	

    	
    	e1.setPosition(100, 100);
    	layer.scrollMapToEntity(e1, 1);
    	assertEqualsExt(new Vector2(90f, 90f), layer.getMapOffset());
    	

    	
    	e1.setPosition(0, 0);
    	layer.scrollMapToEntity(e1, 0);
    	assertEqualsExt(new Vector2(0f, 0f), layer.getMapOffset());
    }
}
