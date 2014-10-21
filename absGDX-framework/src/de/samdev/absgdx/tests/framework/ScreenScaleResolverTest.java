package de.samdev.absgdx.tests.framework;

import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.framework.map.mapsizeresolver.ShowCompleteScreenScaleResolver;
import de.samdev.absgdx.tests.BaseUnitTest;

public class ScreenScaleResolverTest extends BaseUnitTest {

	@Test
	public void testShowCompleteScreenScaleResolver() {
		ShowCompleteScreenScaleResolver resolver = new ShowCompleteScreenScaleResolver();


		assertEquals(new Vector2(1, 1),   resolver.getTileSize  (100, 100, 100, 100));
			
		assertEquals(new Vector2(10, 10), resolver.getTileSize  (100, 100, 10, 10));
		
		assertEquals(new Vector2(1, 1),   resolver.getTileSize  (100, 100, 10, 100));
		
		assertEquals(new Vector2(1, 1),   resolver.getTileSize  (100, 100, 100, 10));
	}

}
