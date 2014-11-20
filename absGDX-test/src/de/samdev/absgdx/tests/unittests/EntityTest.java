package de.samdev.absgdx.tests.unittests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.framework.math.FloatMath;
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
    	
    	Vector2 a1 = e1.addNewAcceleration();
    	Vector2 a2 = e2.addNewAcceleration();
    	Vector2 a3 = e3.addNewAcceleration();
    	
    	for (int i = 0; i < 70 * 1 * 2 * 3; i++) {
    		a1.set(0.00005f, 1/90000f);
    		a2.set(0.00005f, 1/90000f);
    		a3.set(0.00005f, 1/90000f);
    		
			if (i % 1 == 0) e1.update(66.6f * 1);
			if (i % 2 == 0) e2.update(66.6f * 2);
			if (i % 3 == 0) e3.update(66.6f * 3);
		}

    	assertEqualsExt(e1.getPosition(), e2.getPosition(), 100f);
    	assertEqualsExt(e1.getPosition(), e3.getPosition(), 100f);
    	
    	assertEqualsExt(e1.speed, e2.speed, 100f);
    	assertEqualsExt(e1.speed, e3.speed, 100f);
    }

    @Test
    public void testGravity() {
    	DummyEntity e1 = new DummyEntity();
    	DummyEntity e2 = new DummyEntity();
    	DummyEntity e3 = new DummyEntity();
    	DummyEntity e4 = new DummyEntity();
    	
    	e1.setPosition(0, 0);
    	e2.setPosition(0, 0);
    	e3.setPosition(0, 0);
    	e4.setPosition(0, 0);
    	
    	e1.setMass(0.0f);
    	e2.setMass(10.0f);
    	e3.setMass(20.0f);
    	e4.setMass(-10.0f);
    	
    	for (int i = 0; i < 10*1000; i++) {
			e1.update(1f);
			e2.update(1f);
			e3.update(1f);
			e4.update(1f);
		}

    	assertTrue(e2.getPosition().y == -e4.getPosition().y);

    	assertEquals(-0.5f *   0f * DummyEntity.GRAVITY_CONSTANT * FloatMath.fsquare(10f * 1000f), e1.getPosition().y, 0.1f);
    	assertEquals(-0.5f *  10f * DummyEntity.GRAVITY_CONSTANT * FloatMath.fsquare(10f * 1000f), e2.getPosition().y, 0.1f);
    	assertEquals(-0.5f *  20f * DummyEntity.GRAVITY_CONSTANT * FloatMath.fsquare(10f * 1000f), e3.getPosition().y, 0.1f);
    	assertEquals(-0.5f * -10f * DummyEntity.GRAVITY_CONSTANT * FloatMath.fsquare(10f * 1000f), e4.getPosition().y, 0.1f);
    	
    	assertEquals(0f , e1.getPosition().x, 0f);
    	assertEquals(0f , e2.getPosition().x, 0f);
    	assertEquals(0f , e3.getPosition().x, 0f);
    	assertEquals(0f , e4.getPosition().x, 0f);
    	
    	assertEquals(false, e1.hasGravity());	
    	assertEquals(true, e2.hasGravity());	
    	assertEquals(true, e3.hasGravity());	
    	assertEquals(true, e4.hasGravity());
    }
}
