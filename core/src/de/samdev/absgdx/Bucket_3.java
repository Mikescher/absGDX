package de.samdev.absgdx;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.framework.entities.Entity;

public class Bucket_3 extends Entity {

	private Entity other_A;
	private Entity other_B;
	
	public Bucket_3(Entity a, Entity b) {
		super(new TextureRegion[]{Textures.tex_Bucket_full, Textures.tex_Bucket_empty, Textures.tex_Bucket_hay}, 1000, 2, 2);
		
		other_A = a;
		other_B = b;
		
		setPosition(15.0f, 15.0f);
	}

	@Override
	public void beforeUpdate(float delta) {
		Vector2 other = other_A.getMiddle().add(other_A.getMiddle().sub(other_B.getMiddle()).scl(0.5f));
		acceleration = getMiddle().sub(other).scl(-0.0000005f);
	}

}
