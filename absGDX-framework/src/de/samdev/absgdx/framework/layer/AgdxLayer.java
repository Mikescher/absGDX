package de.samdev.absgdx.framework.layer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import de.samdev.absgdx.framework.AgdxGame;

/**
 *  A layer is the current state of the game
 *  It handles the rendering, logic and input processing
 */
public abstract class AgdxLayer {
	protected AgdxGame owner;
	
	/**
	 * Creates the layer
	 * 
	 * @param owner
	 */
	public AgdxLayer(AgdxGame owner) {
		super();
		
		this.owner = owner;
	}

	/**
	 * Renders the layer
	 * 
	 * @param sbatch the BatchRenderer (from LibGDX)
	 * @param srenderer the ShapeRenderer (from LibGDX) - mostly used in Debug display
	 */
	public abstract void render(SpriteBatch sbatch, ShapeRenderer srenderer);
	
	/**
	 * @param delta the time since the last update (in ms) - can be averaged over he last few cycles
	 */
	public abstract void update(float delta);
	
	/**
	 * Gets called when the screen is resized (mostly on desktop)
	 */
	public abstract void onResize();
}
