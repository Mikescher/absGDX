package de.samdev.absgdx.tests.framework;

import org.junit.Test;

import de.samdev.absgdx.framework.map.mapsizeresolver.AbstractMapScaleResolver;
import de.samdev.absgdx.framework.map.mapsizeresolver.FixedScreenScaleResolver;
import de.samdev.absgdx.framework.map.mapsizeresolver.LimitedMinimumBoundaryScreenScaleResolver;
import de.samdev.absgdx.framework.map.mapsizeresolver.MaximumBoundaryScreenScaleResolver;
import de.samdev.absgdx.framework.map.mapsizeresolver.MinimumBoundaryScreenScaleResolver;
import de.samdev.absgdx.framework.map.mapsizeresolver.ShowCompleteScreenScaleResolver;
import de.samdev.absgdx.tests.BaseUnitTest;

public class ScreenScaleResolverTest extends BaseUnitTest {

	@Test
	public void testShowCompleteScreenScaleResolver() {
		AbstractMapScaleResolver resolver = new ShowCompleteScreenScaleResolver();

		assertEquals(01f, resolver.getTileSize(100, 100, 100, 100));
		assertEquals(10f, resolver.getTileSize(100, 100, 10, 10));
		assertEquals(01f, resolver.getTileSize(100, 100, 10, 100));
		assertEquals(01f, resolver.getTileSize(100, 100, 100, 10));
	}

	@Test
	public void testFixedScreenScaleResolver() {
		AbstractMapScaleResolver resolver = new FixedScreenScaleResolver(9);

		assertEquals(9f, resolver.getTileSize(100, 100, 100, 100));
		assertEquals(9f, resolver.getTileSize(100, 100, 10, 10));
		assertEquals(9f, resolver.getTileSize(100, 100, 10, 100));
		assertEquals(9f, resolver.getTileSize(100, 100, 100, 10));
		assertEquals(9f, resolver.getTileSize(100, 100, 1, 1));
		assertEquals(9f, resolver.getTileSize(1, 1, 100, 100));
	}

	@Test
	public void testMinimumBoundaryScreenScaleResolver() {
		AbstractMapScaleResolver resolver = new MinimumBoundaryScreenScaleResolver(10, 10);

		assertEquals(10f,  resolver.getTileSize(100, 100, 100, 100));
		assertEquals(10f,  resolver.getTileSize(100, 100, 10, 10));
		assertEquals(1f,   resolver.getTileSize(10, 10, 100, 100));
		assertEquals(10f,  resolver.getTileSize(10, 10, 1, 1));
		assertEquals(.1f,  resolver.getTileSize(1, 1, 123, 200));

		assertEquals(100f, resolver.getTileSize(100, 100, 100, 1));
		assertEquals(10f,  resolver.getTileSize(100, 100, 100, 10));

		assertEquals(100f, resolver.getTileSize(100, 100, 1, 100));
		assertEquals(10f,  resolver.getTileSize(100, 100, 10, 100));

		assertEquals(10f,  resolver.getTileSize(100, 1, 100, 100));
		assertEquals(10f,  resolver.getTileSize(1, 100, 100, 100));
	}

	@Test
	public void testMaximumBoundaryScreenScaleResolver() {
		AbstractMapScaleResolver resolver = new MaximumBoundaryScreenScaleResolver(10, 10);

		assertEquals(10f, resolver.getTileSize(100, 100, 100, 100));
		assertEquals(10f, resolver.getTileSize(100, 100, 10, 10));
		assertEquals(1f, resolver.getTileSize(10, 10, 100, 100));

		assertEquals(100f, resolver.getTileSize(100, 100, 100, 1));
		assertEquals(100f, resolver.getTileSize(100, 100, 10, 1));
		assertEquals(10f, resolver.getTileSize(100, 100, 10, 10));
		assertEquals(1f, resolver.getTileSize(10, 10, 100, 100));

		assertEquals(10f,  resolver.getTileSize(100, 100, 1, 100));
		assertEquals(10f,  resolver.getTileSize(100, 100, 1, 10));
		assertEquals(100f, resolver.getTileSize(100, 100, 1, 1));

		assertEquals(.1f, resolver.getTileSize(100, 1, 100, 100));
		assertEquals(.1f, resolver.getTileSize(1, 100, 100, 100));
	}

	@Test
	public void testLimitedMinimumBoundaryScreenScaleResolver() {
		AbstractMapScaleResolver resolver = new LimitedMinimumBoundaryScreenScaleResolver(10, 10, 0.5f);

		assertEquals(10f, resolver.getTileSize(100, 100, 100, 100));
		assertEquals(10f, resolver.getTileSize(100, 100, 10, 10));
		assertEquals(1f,  resolver.getTileSize(10, 10, 100, 100));

		assertEquals(20f, resolver.getTileSize(100, 100, 100, 1));
		assertEquals(10f, resolver.getTileSize(100, 100, 100, 10));

		assertEquals(20f, resolver.getTileSize(100, 100, 1, 100));
		assertEquals(10f, resolver.getTileSize(100, 100, 10, 100));

		assertEquals(.2f, resolver.getTileSize(100, 1, 100, 100));
		assertEquals(.2f, resolver.getTileSize(1, 100, 100, 100));
	}
}
