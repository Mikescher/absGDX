package de.samdev.absgdx.framework;

import java.util.Stack;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import de.samdev.absgdx.framework.layer.AgdxLayer;

public abstract class AgdxGame implements ApplicationListener {

	// ##### LibGDX Objects #####

	private OrthographicCamera camera;
	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;

	// ##### Layer #####

	private final Stack<AgdxLayer> layers = new Stack<AgdxLayer>();

	// ##### Other #####

	public final GameSettings settings = new GameSettings();
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		camera = new OrthographicCamera();

		camera.setToOrtho(false, getScreenWidth(), getScreenHeight());
		camera.update();
		
		onCreate();
	}

	@Override
	public void render() {
		doUpdate();

		doRender();
	}

	private void doRender() {
		Gdx.gl.glClearColor(1, 0, 1, 1); // MAGENTA
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		shapeRenderer.setProjectionMatrix(camera.combined);

		if (!layers.empty()) {
			layers.peek().render(batch, shapeRenderer);
		}
	}

	private void doUpdate() {
		if (!layers.empty()) {
			layers.peek().update();
		}
	}

	@Override
	public void resize(int width, int height) {
		camera.setToOrtho(false, getScreenWidth(), getScreenHeight());

		if (!layers.empty()) {
			layers.peek().onResize();
		}
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	/**
	 * Load a Texture from the resouces
	 * 
	 * @param file
	 *            the filename
	 * @return the texture
	 */
	public Texture loadTexture(String file) {
		return new Texture(Gdx.files.internal(file));
	}

	/**
	 * @return the width (in pixel) of the display
	 */
	public int getScreenWidth() {
		return Gdx.app.getGraphics().getWidth();
	}

	/**
	 * @return the height (in pixel) of the display
	 */
	public int getScreenHeight() {
		return Gdx.app.getGraphics().getHeight();
	}

	/**
	 * Pushes a new Layer ontop the stack
	 * 
	 * @param layer 
	 */
	public void pushLayer(AgdxLayer layer) {
		layers.push(layer);
	}

	/**
	 * Set the stack to a new layer (and removes all other from it)
	 * 
	 * @param layer
	 */
	public void setLayer(AgdxLayer layer) {
		layers.clear();
		layers.push(layer);
	}

	/**
	 * pops one layer of the stack
	 */
	public void popLayer() {
		layers.pop();
	}

	/**
	 * Gets called after the initialization
	 */
	public abstract void onCreate();
}
