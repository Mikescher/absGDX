package de.samdev.absgdx.tests.framework;

import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.framework.math.align.Align8;
import de.samdev.absgdx.framework.math.align.Align9;
import de.samdev.absgdx.framework.math.align.AlignCorner4;
import de.samdev.absgdx.framework.math.align.AlignEdge4;
import de.samdev.absgdx.framework.math.align.AlignEdge5;
import de.samdev.absgdx.tests.BaseUnitTest;

public class AlignTest extends BaseUnitTest {

    @Test
    public void testGetAlign8DirectionVector() {
    	Vector2 all = new Vector2();
    	
    	for (Align8 al : Align8.values()) {
			all.add(al.getDirectionVector());
		}
    	
    	assertEquals(all, new Vector2(), Float.MIN_NORMAL);
    }

    @Test
    public void testGetAlignEdge4DirectionVector() {
    	Vector2 all = new Vector2();
    	
    	for (AlignEdge4 al : AlignEdge4.values()) {
			all.add(al.getDirectionVector());
		}

    	assertEquals(all, new Vector2(), Float.MIN_NORMAL);
    }

    @Test
    public void testGetAlignCorner4DirectionVector() {
    	Vector2 all = new Vector2();
    	
    	for (AlignCorner4 al : AlignCorner4.values()) {
			all.add(al.getDirectionVector());
		}

    	assertEquals(all, new Vector2(), Float.MIN_NORMAL);
    }

    @Test
    public void testGetAlign9DirectionVector() {
    	Vector2 all = new Vector2();
    	
    	for (Align9 al : Align9.values()) {
			all.add(al.getDirectionVector());
		}

    	assertEquals(all, new Vector2(), Float.MIN_NORMAL);
    }

    @Test
    public void testGetAlignEdge5DirectionVector() {
    	Vector2 all = new Vector2();
    	
    	for (AlignEdge5 al : AlignEdge5.values()) {
			all.add(al.getDirectionVector());
		}

    	assertEquals(all, new Vector2(), Float.MIN_NORMAL);
    }

}
