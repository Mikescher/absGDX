package de.samdev.absgdx.framework.layer;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.framework.renderer.DebugTextRenderer;

/**
 *  A layer is the current state of the game
 *  It handles the rendering, logic and input processing
 */
public abstract class AgdxLayer implements InputProcessor {
	/** the owner (AgdxGame) */
	public final AgdxGame owner;
	
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
	 * @param trenderer the debugTextRenderer - only used in Debug display
	 */
	public abstract void render(SpriteBatch sbatch, ShapeRenderer srenderer, DebugTextRenderer trenderer);
	
	/**
	 * @param delta the time since the last update (in ms) - can be averaged over he last few cycles
	 */
	public abstract void update(float delta);
	
	/**
	 * Gets called when the screen is resized (mostly on desktop)
	 */
	public abstract void onResize();

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	/**
	 * Called when this layer becomes the  active layer
	 * 
	 * (override if this event is needed)
	 */
	public void onActivate() {
		// NOP - override
	}

	/**
	 * Called when this layer is no longer the active layer
	 * 
	 * (override if this event is needed)
	 */
	public void onDeactivate() {
		// NOP - override
	}
	
	
}
