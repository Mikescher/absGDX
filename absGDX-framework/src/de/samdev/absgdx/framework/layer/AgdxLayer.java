package de.samdev.absgdx.framework.layer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import de.samdev.absgdx.framework.AgdxGame;

public abstract class AgdxLayer {

	protected AgdxGame owner;
	
	public AgdxLayer(AgdxGame owner) {
		super();
		
		this.owner = owner;
	}

	public abstract void render(SpriteBatch sbatch, ShapeRenderer srenderer);
}
