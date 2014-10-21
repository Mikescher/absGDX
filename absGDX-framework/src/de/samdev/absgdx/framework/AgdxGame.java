package de.samdev.absgdx.framework;

import java.util.Stack;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import de.samdev.absgdx.framework.layer.AgdxLayer;

public abstract class AgdxGame implements ApplicationListener {
	
	//##### LibGDX Objects #####
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;
	
	//##### Layer #####
	
	private final Stack<AgdxLayer> layers = new Stack<AgdxLayer>();
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		camera = new OrthographicCamera();
		
		camera.setToOrtho(false, getScreenWidth(), getScreenHeight());
		camera.update();
		
		onCreate();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		shapeRenderer.setProjectionMatrix(camera.combined);
		
		if (! layers.empty())
		{
			layers.peek().render(batch, shapeRenderer);
		}
	}

	@Override
	public void resize(int width, int height) {
		camera.setToOrtho(false, getScreenWidth(), getScreenHeight());
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
	 * @param file the filename
	 * @return the texture
	 */
	public Texture loadTexture(String file)
	{
		return new Texture(Gdx.files.internal(file));
	}
	
	public int getScreenWidth()
	{
		return Gdx.app.getGraphics().getWidth();
	}
	
	public int getScreenHeight()
	{
		return Gdx.app.getGraphics().getHeight();
	}
	
	public void pushLayer(AgdxLayer l)
	{
		layers.push(l);
	}
	
	public abstract void onCreate();
}
