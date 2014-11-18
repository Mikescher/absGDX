package de.samdev.absgdx.tests.unittests;

import org.junit.Test;

import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.tests.BaseUnitTest;

public class AgdxTiledGameTest extends BaseUnitTest {

    @Test
    public void testCreateAgdxTiledGame() {
    	new AgdxGame()
    	{
			@Override
			public void onCreate() {/**/}

			@Override
			public void onUpdate(float delta) {/**/}
		};
    }

}
