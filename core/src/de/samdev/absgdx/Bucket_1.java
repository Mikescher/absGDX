package de.samdev.absgdx;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.framework.entities.Entity;

public class Bucket_1 extends Entity {

	public Bucket_1() {
		super(new TextureRegion[]{Textures.tex_Bucket_full, Textures.tex_Bucket_empty, Textures.tex_Bucket_hay}, 1000, 2, 2);
		
		setPosition(15f, 15f);
		
		this.speed.y = 5/1000f;
	}
	
	@Override
	public void beforeUpdate(float delta) {
		this.acceleration = new Vector2(20, 20).sub(getPosition()).nor().scl(1/1000f * 1/100f);
	}
}
