package de.samdev.absgdx.entities;

import de.samdev.absgdx.DemoGameLayer;
import de.samdev.absgdx.Textures;
import de.samdev.absgdx.framework.entities.Entity;
import de.samdev.absgdx.framework.layer.GameLayer;

public class Bush_2 extends Entity {

	public DemoGameLayer owner;
	
	public int tick = 0;

	public float x, y;
	
	public Bush_2(float x, float y) {
		super(Textures.tex_Bush_empty, 2, 2);
		
		this.x = x;
		this.y = y;
	}

	@Override
	public void onLayerAdd(GameLayer layer) {
		setPosition(x, y);
	}
	
	@Override
	public void beforeUpdate(float delta) {
		//
	}

}
