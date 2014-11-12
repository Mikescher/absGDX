package de.samdev.absgdx.tests.unittests;

import org.junit.Test;

import de.samdev.absgdx.tests.BaseUnitTest;
import de.samdev.absgdx.tests.dummy.DummyEntity;

public class EntityTest extends BaseUnitTest {

    @Test
    public void testMovement_speed() {
    	DummyEntity e1 = new DummyEntity();
    	DummyEntity e2 = new DummyEntity();
    	DummyEntity e3 = new DummyEntity();
    	
    	e1.setPosition(0, 0);
    	e2.setPosition(0, 0);
    	e3.setPosition(0, 0);
    	
    	for (int i = 0; i < 170 * 1 * 2 * 3; i++) {
    		e1.speed.set(0.5f, 1/3f);
    		e2.speed.set(0.5f, 1/3f);
    		e3.speed.set(0.5f, 1/3f);
    		
			if (i % 1 == 0) e1.update(66.6f * 1);
			if (i % 2 == 0) e2.update(66.6f * 2);
			if (i % 3 == 0) e3.update(66.6f * 3);
		}

    	assertEqualsExt(e1.getPosition(), e2.getPosition(), 0.5f);
    	assertEqualsExt(e1.getPosition(), e3.getPosition(), 0.5f);
    }

    @Test
    public void testMovement_acceleration() {
    	DummyEntity e1 = new DummyEntity();
    	DummyEntity e2 = new DummyEntity();
    	DummyEntity e3 = new DummyEntity();
    	
    	e1.setPosition(0, 0);
    	e2.setPosition(0, 0);
    	e3.setPosition(0, 0);
    	
    	for (int i = 0; i < 70 * 1 * 2 * 3; i++) {
    		e1.acceleration.set(0.00005f, 1/90000f);
    		e2.acceleration.set(0.00005f, 1/90000f);
    		e3.acceleration.set(0.00005f, 1/90000f);
    		
			if (i % 1 == 0) e1.update(66.6f * 1);
			if (i % 2 == 0) e2.update(66.6f * 2);
			if (i % 3 == 0) e3.update(66.6f * 3);
		}

    	assertEqualsExt(e1.getPosition(), e2.getPosition(), 100f);
    	assertEqualsExt(e1.getPosition(), e3.getPosition(), 100f);
    	
    	assertEqualsExt(e1.speed, e2.speed, 100f);
    	assertEqualsExt(e1.speed, e3.speed, 100f);
    }
}
