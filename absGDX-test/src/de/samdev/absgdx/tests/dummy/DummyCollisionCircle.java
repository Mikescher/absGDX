package de.samdev.absgdx.tests.dummy;

import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionCircle;

public class DummyCollisionCircle extends CollisionCircle {

	public DummyCollisionCircle(float radius) {
		super(new DummyEntity(), radius);
	}

}
