package de.samdev.absgdx.tests.unittests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import de.samdev.absgdx.framework.layer.AgdxmlLayer;
import de.samdev.absgdx.framework.menu.elements.MenuImage;
import de.samdev.absgdx.framework.util.exceptions.AgdxmlParsingException;
import de.samdev.absgdx.tests.BaseUnitTest;
import de.samdev.absgdx.tests.dummy.DummyAgdxmlMenuLayer;

public class AgdxmlMenuLayerTest extends BaseUnitTest {

    @Test
    public void testConstructor() throws AgdxmlParsingException, IOException {
    	AgdxmlLayer layer = new DummyAgdxmlMenuLayer(1024, 768, readTextFileFromResource("/testLayout.agdxml", this.getClass()));
		
		assertTrue(layer.getRoot() != null);
		
		assertEquals(15, layer.getElementCount());

		assertTrue(layer.getElementByID("imageChinese") instanceof MenuImage);
		
		assertTrue(layer.getElementByID("imageNazi") instanceof MenuImage);
    }

}
