package de.samdev.absgdx.entities;

import de.samdev.absgdx.DemoGameLayer;
import de.samdev.absgdx.Textures;
import de.samdev.absgdx.framework.entities.Entity;

public class Bush_1 extends Entity {

	public DemoGameLayer owner;
	
	public int tick = 0;
	
	public Bush_1(float x, float y) {
		super(Textures.tex_Bush_full, 2, 2);
		
		setPosition(x, y);
	}

	@Override
	public void beforeUpdate(float delta) {
		//
	}

}
