package de.samdev.absgdx.tests.unittests;

import org.junit.Test;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.framework.layer.GameLayer;
import de.samdev.absgdx.framework.map.mapsizeresolver.MinimumBoundaryMapScaleResolver;
import de.samdev.absgdx.tests.BaseUnitTest;
import de.samdev.absgdx.tests.DummyGameLayer;

public class GameLayerTest extends BaseUnitTest {

    @Test
    public void testGetVisibleMapBox() {
    	GameLayer layer = new DummyGameLayer(100, 100);
		
		layer.loadEmptyMap(20, 20);
		layer.setMapScaleResolver(new MinimumBoundaryMapScaleResolver(10, 10));
		
		assertEquals(new Rectangle(0, 0, 10, 10), layer.getVisibleMapBox());
    }

    @Test
    public void testGetVisibleMapBox2() {
    	GameLayer layer = new DummyGameLayer(200, 100);
		
		layer.loadEmptyMap(20, 20);
		layer.setMapScaleResolver(new MinimumBoundaryMapScaleResolver(10, 10));
		
		assertEquals(new Rectangle(0, 0, 10, 5), layer.getVisibleMapBox());
    }

    @Test
    public void testGetVisibleMapBox3() {
    	GameLayer layer = new DummyGameLayer(100, 200);
		
		layer.loadEmptyMap(20, 20);
		layer.setMapScaleResolver(new MinimumBoundaryMapScaleResolver(10, 10));
		
		assertEquals(new Rectangle(0, 0, 5, 10), layer.getVisibleMapBox());
    }

    @Test
    public void testSetBoundedOffset() {
    	GameLayer layer = new DummyGameLayer(100, 100);
		
		layer.loadEmptyMap(100, 100);
		layer.setMapScaleResolver(new MinimumBoundaryMapScaleResolver(10, 10));
	
		layer.setBoundedOffset(new Vector2(0, 0));
		assertEquals(new Rectangle(0, 0, 10, 10), layer.getVisibleMapBox());
	
		layer.setBoundedOffset(new Vector2(1, 1));
		assertEquals(new Rectangle(1, 1, 10, 10), layer.getVisibleMapBox());
	
		layer.setBoundedOffset(new Vector2(-10, -10));
		assertEquals(new Rectangle(0, 0, 10, 10), layer.getVisibleMapBox());
	
		layer.setBoundedOffset(new Vector2(-10, 10));
		assertEquals(new Rectangle(0, 10, 10, 10), layer.getVisibleMapBox());
	
		layer.setBoundedOffset(new Vector2(10, -10));
		assertEquals(new Rectangle(10, 0, 10, 10), layer.getVisibleMapBox());
	
		layer.setBoundedOffset(new Vector2(90, 90));
		assertEquals(new Rectangle(90, 90, 10, 10), layer.getVisibleMapBox());
	
		layer.setBoundedOffset(new Vector2(100, 100));
		assertEquals(new Rectangle(90, 90, 10, 10), layer.getVisibleMapBox());
    }

}
