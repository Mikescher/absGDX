package de.samdev.absgdx.tests.unittests;

import org.junit.Test;

import de.samdev.absgdx.framework.map.TileMap;
import de.samdev.absgdx.framework.util.tiled.TmxMapLoader;
import de.samdev.absgdx.tests.BaseUnitTest;
import de.samdev.absgdx.tests.DummyTile;

public class TmxMapLoaderTest extends BaseUnitTest {

	@Test
	public void testLoadXML() throws Exception {
		TmxMapLoader loader = new TmxMapLoader(readTextFileFromResource("/tmxmap_xml_0.tmx", this.getClass()));
		
		loader.addDefaultMapping(DummyTile.class);
		
		TileMap map = loader.start();

		assertEquals("xml", ((DummyTile)map.getTile(0, 0)).properties.get(TmxMapLoader.PROPERTY_ENCODING));
		assertEquals("none", ((DummyTile)map.getTile(0, 0)).properties.get(TmxMapLoader.PROPERTY_COMPRESSION));
		
		testDefaultResourceMapWithDummyTiles(map);
	}

	@Test
	public void testLoadBase64() throws Exception {
		TmxMapLoader loader = new TmxMapLoader(readTextFileFromResource("/tmxmap_b64_0.tmx", this.getClass()));
		
		loader.addDefaultMapping(DummyTile.class);
		
		TileMap map = loader.start();

		assertEquals("base64", ((DummyTile)map.getTile(0, 0)).properties.get(TmxMapLoader.PROPERTY_ENCODING));
		assertEquals("none", ((DummyTile)map.getTile(0, 0)).properties.get(TmxMapLoader.PROPERTY_COMPRESSION));
		
		testDefaultResourceMapWithDummyTiles(map);
	}
	
	@Test
	public void testLoadBase64_zlib() throws Exception {
		TmxMapLoader loader = new TmxMapLoader(readTextFileFromResource("/tmxmap_b64zlib_0.tmx", this.getClass()));
		
		loader.addDefaultMapping(DummyTile.class);
		
		TileMap map = loader.start();

		assertEquals("base64", ((DummyTile)map.getTile(0, 0)).properties.get(TmxMapLoader.PROPERTY_ENCODING));
		assertEquals("zlib", ((DummyTile)map.getTile(0, 0)).properties.get(TmxMapLoader.PROPERTY_COMPRESSION));
		
		testDefaultResourceMapWithDummyTiles(map);
	}
	
	@Test
	public void testLoadBase64_gzip() throws Exception {
		TmxMapLoader loader = new TmxMapLoader(readTextFileFromResource("/tmxmap_b64gzip_0.tmx", this.getClass()));
		
		loader.addDefaultMapping(DummyTile.class);
		
		TileMap map = loader.start();

		assertEquals("base64", ((DummyTile)map.getTile(0, 0)).properties.get(TmxMapLoader.PROPERTY_ENCODING));
		assertEquals("gzip", ((DummyTile)map.getTile(0, 0)).properties.get(TmxMapLoader.PROPERTY_COMPRESSION));
		
		testDefaultResourceMapWithDummyTiles(map);
	}
	
	@Test
	public void testLoadCSV() throws Exception {
		TmxMapLoader loader = new TmxMapLoader(readTextFileFromResource("/tmxmap_csv_0.tmx", this.getClass()));
		
		loader.addDefaultMapping(DummyTile.class);
		
		TileMap map = loader.start();

		assertEquals("csv", ((DummyTile)map.getTile(0, 0)).properties.get(TmxMapLoader.PROPERTY_ENCODING));
		assertEquals("none", ((DummyTile)map.getTile(0, 0)).properties.get(TmxMapLoader.PROPERTY_COMPRESSION));
		
		testDefaultResourceMapWithDummyTiles(map);
	}
	
	private void testDefaultResourceMapWithDummyTiles(TileMap map) {
		assertEquals(8, map.height);
		assertEquals(8, map.width);
		
		assertEquals("816", ((DummyTile)map.getTile(0, 0)).properties.get("Layer_0_Setting_0"));
		assertEquals("1199", ((DummyTile)map.getTile(0, 0)).properties.get("Layer_0_Setting_1"));
		assertEquals("8", ((DummyTile)map.getTile(0, 0)).properties.get(TmxMapLoader.PROPERTY_LAYER_HEIGHT));
		assertEquals("8", ((DummyTile)map.getTile(0, 0)).properties.get(TmxMapLoader.PROPERTY_LAYER_WIDTH));
		assertEquals("Kachelebene 0", ((DummyTile)map.getTile(0, 0)).properties.get(TmxMapLoader.PROPERTY_LAYER_NAME));
		assertEquals("8", ((DummyTile)map.getTile(0, 0)).properties.get(TmxMapLoader.PROPERTY_MAP_WIDTH));
		assertEquals("8", ((DummyTile)map.getTile(0, 0)).properties.get(TmxMapLoader.PROPERTY_MAP_HEIGHT));
		
		assertEquals("Test", ((DummyTile)map.getTile(3, 3)).properties.get("Layer_1_Setting_0"));
		assertEquals("Blah", ((DummyTile)map.getTile(3, 3)).properties.get("Layer_1_Setting_1"));
		assertEquals("8", ((DummyTile)map.getTile(3, 3)).properties.get(TmxMapLoader.PROPERTY_LAYER_HEIGHT));
		assertEquals("8", ((DummyTile)map.getTile(3, 3)).properties.get(TmxMapLoader.PROPERTY_LAYER_WIDTH));
		assertEquals("Kachelebene 1", ((DummyTile)map.getTile(3, 3)).properties.get(TmxMapLoader.PROPERTY_LAYER_NAME));
		assertEquals("8", ((DummyTile)map.getTile(3, 3)).properties.get(TmxMapLoader.PROPERTY_MAP_WIDTH));
		assertEquals("8", ((DummyTile)map.getTile(3, 3)).properties.get(TmxMapLoader.PROPERTY_MAP_HEIGHT));
		

		assertEquals("1", ((DummyTile)map.getTile(0, 0)).properties.get(TmxMapLoader.PROPERTY_TEXTURE_GID));
		assertEquals("7", ((DummyTile)map.getTile(1, 1)).properties.get(TmxMapLoader.PROPERTY_TEXTURE_GID));
		assertEquals("10", ((DummyTile)map.getTile(2, 2)).properties.get(TmxMapLoader.PROPERTY_TEXTURE_GID));
		assertEquals("8", ((DummyTile)map.getTile(3, 3)).properties.get(TmxMapLoader.PROPERTY_TEXTURE_GID));
		assertEquals("8", ((DummyTile)map.getTile(4, 4)).properties.get(TmxMapLoader.PROPERTY_TEXTURE_GID));
		assertEquals("10", ((DummyTile)map.getTile(5, 5)).properties.get(TmxMapLoader.PROPERTY_TEXTURE_GID));
		assertEquals("7", ((DummyTile)map.getTile(6, 6)).properties.get(TmxMapLoader.PROPERTY_TEXTURE_GID));
		assertEquals("1", ((DummyTile)map.getTile(7, 7)).properties.get(TmxMapLoader.PROPERTY_TEXTURE_GID));
	}

}
