package de.samdev.absgdx.tests.unittests;

import org.junit.Test;

import de.samdev.absgdx.framework.map.mapsizeresolver.AbstractMapScaleResolver;
import de.samdev.absgdx.framework.map.mapsizeresolver.FixedMapScaleResolver;
import de.samdev.absgdx.framework.map.mapsizeresolver.LimitedMinimumBoundaryMapScaleResolver;
import de.samdev.absgdx.framework.map.mapsizeresolver.MaximumBoundaryMapScaleResolver;
import de.samdev.absgdx.framework.map.mapsizeresolver.MinimumBoundaryMapScaleResolver;
import de.samdev.absgdx.framework.map.mapsizeresolver.SectionMapScaleResolver;
import de.samdev.absgdx.framework.map.mapsizeresolver.ShowCompleteMapScaleResolver;
import de.samdev.absgdx.tests.BaseUnitTest;

public class MapScaleResolverTest extends BaseUnitTest {

	@Test
	public void testShowCompleteMapScaleResolver() {
		AbstractMapScaleResolver resolver = new ShowCompleteMapScaleResolver();

		assertEquals(01f, resolver.getTileSize(100, 100, 100, 100));
		assertEquals(10f, resolver.getTileSize(100, 100, 10, 10));
		assertEquals(01f, resolver.getTileSize(100, 100, 10, 100));
		assertEquals(01f, resolver.getTileSize(100, 100, 100, 10));
	}

	@Test
	public void testFixedMapScaleResolver() {
		AbstractMapScaleResolver resolver = new FixedMapScaleResolver(9);

		assertEquals(9f, resolver.getTileSize(100, 100, 100, 100));
		assertEquals(9f, resolver.getTileSize(100, 100, 10, 10));
		assertEquals(9f, resolver.getTileSize(100, 100, 10, 100));
		assertEquals(9f, resolver.getTileSize(100, 100, 100, 10));
		assertEquals(9f, resolver.getTileSize(100, 100, 1, 1));
		assertEquals(9f, resolver.getTileSize(1, 1, 100, 100));
	}

	@Test
	public void testMinimumBoundaryMapScaleResolver() {
		AbstractMapScaleResolver resolver = new MinimumBoundaryMapScaleResolver(10, 10);

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
	public void testMaximumBoundaryMapScaleResolver() {
		AbstractMapScaleResolver resolver = new MaximumBoundaryMapScaleResolver(10, 10);

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
	public void testLimitedMinimumBoundaryMapScaleResolver() {
		AbstractMapScaleResolver resolver = new LimitedMinimumBoundaryMapScaleResolver(10, 10, 0.5f);

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

	@Test
	public void testSectionBoundaryMapScaleResolver() {
		AbstractMapScaleResolver resolver = new SectionMapScaleResolver(10, 10, 0.5f, 1f);

		assertEquals(10f, resolver.getTileSize(100, 100, 100, 100));
		assertEquals(10f, resolver.getTileSize(100, 100, 10, 10));
		assertEquals(1f,  resolver.getTileSize(10, 10, 100, 100));

		assertEquals(20f, resolver.getTileSize(100, 100, 100, 1));
		assertEquals(10f, resolver.getTileSize(100, 100, 100, 10));

		assertEquals(20f, resolver.getTileSize(100, 100, 1, 100));
		assertEquals(10f, resolver.getTileSize(100, 100, 10, 100));

		assertEquals(1f, resolver.getTileSize(100, 1, 100, 100));
		assertEquals(1f, resolver.getTileSize(1, 100, 100, 100));
	}
}
