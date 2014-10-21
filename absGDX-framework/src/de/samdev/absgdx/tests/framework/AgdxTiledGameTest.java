package de.samdev.absgdx.tests.framework;

import org.junit.Test;

import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.tests.BaseUnitTest;

public class AgdxTiledGameTest extends BaseUnitTest {

    @Test
    public void testCreateAgdxTiledGame() {
    	new AgdxGame()
    	{
			@Override
			public void onCreate() {
				// nothing
			}
		};
    }

}
