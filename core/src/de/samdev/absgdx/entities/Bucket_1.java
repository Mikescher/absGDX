package de.samdev.absgdx.entities;

import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.DemoGameLayer;
import de.samdev.absgdx.Textures;
import de.samdev.absgdx.framework.entities.Entity;
import de.samdev.absgdx.framework.layer.GameLayer;

public class Bucket_1 extends Entity {

	public DemoGameLayer owner;
	
	public Bucket_1() {
		super(Textures.tex_Bucket_full, 2, 2);
	}

	@Override
	public void onLayerAdd(GameLayer layer) {
		
		setPosition(15f, 15f);
		
		this.speed.y = 5/1000f;
	}
	
	@Override
	public void beforeUpdate(float delta) {
		this.acceleration = new Vector2(20, 20).sub(getPosition()).nor().scl(1/1000f * 1/100f);
	}
}
