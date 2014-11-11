package de.samdev.absgdx.entities;

import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.DemoGameLayer;
import de.samdev.absgdx.Textures;
import de.samdev.absgdx.framework.entities.Entity;

public class Bucket_2 extends Entity {

	public DemoGameLayer owner;

	public Bucket_2() {
		super(Textures.tex_Bucket_empty, 2, 2);
		
		setPosition(25f, 15f);
		
		this.speed.y = - 5/1000f;
	}

	@Override
	public void beforeUpdate(float delta) {
		this.acceleration = new Vector2(20, 20).sub(getPosition()).nor().scl(1/1000f * 1/100f);
	}

}
