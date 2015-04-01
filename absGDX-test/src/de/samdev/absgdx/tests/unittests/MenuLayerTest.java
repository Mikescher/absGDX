package de.samdev.absgdx.tests.unittests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.samdev.absgdx.framework.layer.MenuLayer;
import de.samdev.absgdx.tests.BaseUnitTest;
import de.samdev.absgdx.tests.dummy.DummyMenuLayer;

public class MenuLayerTest extends BaseUnitTest {

    @Test
    public void testConstructor() {
    	MenuLayer layer = new DummyMenuLayer(1024, 768);
		
		assertTrue(layer.getMenuRoot() != null);
		
		assertEquals(1, layer.getElementCount());
    }

}
