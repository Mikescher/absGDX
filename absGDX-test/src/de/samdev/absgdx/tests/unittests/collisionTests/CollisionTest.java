package de.samdev.absgdx.tests.unittests.collisionTests;

import java.util.Arrays;
import java.util.Collection;

import de.samdev.absgdx.tests.BaseUnitTest;

public abstract class CollisionTest extends BaseUnitTest {

	protected final int expMapScale;
	protected final int mapWidth;
	protected final int mapHeight;
	
	public CollisionTest(int mapScale, int mapW, int mapH) {
		super();
		
		this.expMapScale = mapScale;
		this.mapWidth = mapW;
		this.mapHeight = mapH;
	}
	
	public static Collection<Object[]> getParameters() {
		return Arrays.asList(new Object[][] {
				{-3, 100, 100}, 
				{-2, 100, 100}, 
				{-1, 100, 100}, 
				{0, 100, 100}, 
				{1, 100, 100}, 
				{2, 100, 100},
				
				{0, 128, 128},
				{7, 128, 128},
				
				{0, 1, 1},				
				{0, 0, 0}, // No idea how this works - and I wouldn't even care if its would fail 
				{-2, 1, 1},
		});
	}
    
}
