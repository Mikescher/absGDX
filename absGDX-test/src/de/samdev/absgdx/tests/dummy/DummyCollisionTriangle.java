package de.samdev.absgdx.tests.dummy;

import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionTriangle;

public class DummyCollisionTriangle extends CollisionTriangle {

	public DummyCollisionTriangle(float p1x, float p1y, float p2x, float p2y, float p3x, float p3y) {
		super(new DummyEntity(), p1x, p1y, p2x, p2y, p3x, p3y);
	}

}
