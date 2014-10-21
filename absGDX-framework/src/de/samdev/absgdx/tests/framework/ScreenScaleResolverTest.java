package de.samdev.absgdx.tests.framework;

import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.framework.map.mapsizeresolver.FixedScreenScaleResolver;
import de.samdev.absgdx.framework.map.mapsizeresolver.MaximumBoundaryScreenScaleResolver;
import de.samdev.absgdx.framework.map.mapsizeresolver.MinimumBoundaryScreenScaleResolver;
import de.samdev.absgdx.framework.map.mapsizeresolver.ShowCompleteScreenScaleResolver;
import de.samdev.absgdx.tests.BaseUnitTest;

public class ScreenScaleResolverTest extends BaseUnitTest {

	@Test
	public void testShowCompleteScreenScaleResolver() {
		ShowCompleteScreenScaleResolver resolver = new ShowCompleteScreenScaleResolver();

		assertEquals(01, resolver.getTileSize(100, 100, 100, 100));
		assertEquals(10, resolver.getTileSize(100, 100, 10, 10));
		assertEquals(10, resolver.getTileSize(100, 100, 10, 100));
		assertEquals(10, resolver.getTileSize(100, 100, 100, 10));
	}

	@Test
	public void testFixedScreenScaleResolver() {
		FixedScreenScaleResolver resolver = new FixedScreenScaleResolver(9);

		assertEquals(9, resolver.getTileSize(100, 100, 100, 100));
		assertEquals(9, resolver.getTileSize(100, 100, 10, 10));
		assertEquals(9, resolver.getTileSize(100, 100, 10, 100));
		assertEquals(9, resolver.getTileSize(100, 100, 100, 10));
		assertEquals(9, resolver.getTileSize(100, 100, 1, 1));
		assertEquals(9, resolver.getTileSize(1, 1, 100, 100));
	}

	@Test
	public void testLimitedMinimumBoundaryScreenScaleResolver() {
		MinimumBoundaryScreenScaleResolver resolver = new MinimumBoundaryScreenScaleResolver(10, 10);

		assertEquals(01,  resolver.getTileSize(100, 100, 100, 100));
		assertEquals(10,  resolver.getTileSize(100, 100, 10, 10));
		assertEquals(.1f, resolver.getTileSize(10, 10, 100, 100));
		
		assertEquals(100,  resolver.getTileSize(100, 100, 100, 1));
		assertEquals(10,  resolver.getTileSize(100, 100, 100, 10));
		
		assertEquals(100,  resolver.getTileSize(100, 100, 1, 100));
		assertEquals(10,  resolver.getTileSize(100, 100, 10, 100));
		
		assertEquals(.1f,  resolver.getTileSize(100, 1, 100, 100));
		assertEquals(.1f,  resolver.getTileSize(1, 100, 100, 100));
	}

	@Test
	public void testMaximumBoundaryScreenScaleResolver() {
		//
	}

	@Test
	public void testMinimumBoundaryScreenScaleResolver() {
		//
	}
}
