package de.samdev.absgdx.tests.unittests;

import org.junit.Test;

import de.samdev.absgdx.framework.map.EmptyTile;
import de.samdev.absgdx.framework.map.TileMap;
import de.samdev.absgdx.tests.BaseUnitTest;

public class TileMapTest extends BaseUnitTest {

    @Test
    public void testSetTile() {
    	TileMap t = new TileMap(10, 10);
    	
    	t.setTile(0, 0, new EmptyTile());
    	t.setTile(0, 9, new EmptyTile());
    	t.setTile(9, 0, new EmptyTile());
    	t.setTile(9, 9, new EmptyTile());
    }

}
