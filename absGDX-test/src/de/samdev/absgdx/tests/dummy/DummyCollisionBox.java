package de.samdev.absgdx.tests.dummy;

import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionBox;

public class DummyCollisionBox extends CollisionBox {

	public DummyCollisionBox(float width, float height) {
		super(new DummyEntity(), width, height);
	}

}
