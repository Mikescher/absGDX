package de.samdev.absgdx.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import de.samdev.absgdx.Textures;
import de.samdev.absgdx.framework.entities.Entity;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionCircle;
import de.samdev.absgdx.framework.layer.GameLayer;

public class Anchorpoint_1 extends Entity {
	
	public Anchorpoint_1() {
		super(Textures.tex_Anchorpoint_empty, 2, 2);
	}
	
	@Override
	public void onLayerAdd(GameLayer layer) {
		setPosition(34.0f, 23.0f);
		
		addCollisionGeo(1f, 1f, new CollisionCircle(1f));
	}

	@Override
	public void beforeUpdate(float delta) {
		speed.set(0,0);
		
		if (Gdx.input.isKeyPressed(Keys.W)) speed.y += 0.01;
		if (Gdx.input.isKeyPressed(Keys.A)) speed.x -= 0.01;
		if (Gdx.input.isKeyPressed(Keys.S)) speed.y -= 0.01;
		if (Gdx.input.isKeyPressed(Keys.D)) speed.x += 0.01;
	}

}
