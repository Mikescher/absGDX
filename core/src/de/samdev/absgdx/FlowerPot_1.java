package de.samdev.absgdx;

import de.samdev.absgdx.framework.entities.Entity;

public class FlowerPot_1 extends Entity {

	public DemoGameLayer owner;
	
	public int tick = 0;
	
	public FlowerPot_1() {
		super(Textures.tex_Flowers_empty, 2, 2);
		
		setPosition(0.0f, 00.0f);
	}

	@Override
	public void beforeUpdate(float delta) {
		tick++;
		
		if (getPositionY() < 5) {
			acceleration.y =  0.00003f;
		} else {
			acceleration.y = -0.00003f;
		}
		
		speed.x = 0.0025f;
		
		if (tick > 60*22)
			this.alive = false;
	}

}
