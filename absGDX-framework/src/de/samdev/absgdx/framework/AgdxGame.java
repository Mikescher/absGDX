package de.samdev.absgdx.framework;

import java.util.Stack;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import de.samdev.absgdx.framework.layer.AgdxLayer;
import de.samdev.absgdx.framework.renderer.DebugTextRenderer;
import de.samdev.absgdx.framework.util.DebugFrequencyMeter;

public abstract class AgdxGame implements ApplicationListener {

	// ##### LibGDX Objects #####

	private OrthographicCamera camera;
	
	private SpriteBatch mapRenderer;
	private SpriteBatch entityRenderer;
	private SpriteBatch fontRenderer;
	
	private ShapeRenderer shapeRenderer;

	// ##### Debug #####
	
	private BitmapFont debugFont;		 // TODO Add API to render custom text / with custom font
	private DebugTextRenderer debugTextRenderer;
	private final DebugFrequencyMeter freqMeter = new DebugFrequencyMeter();
	
	// ##### Layer #####

	private final Stack<AgdxLayer> layers = new Stack<AgdxLayer>();

	// ##### Other #####

	public final GameSettings settings = new GameSettings();
	
	private float debugFontSize = 1f;
	
	@Override
	public void create() {
		mapRenderer = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		setDebugFont(new BitmapFont());
		fontRenderer = new SpriteBatch();
		entityRenderer = new SpriteBatch();
		debugTextRenderer = new DebugTextRenderer(this, debugFont, fontRenderer, shapeRenderer, 10, 10);
	
		camera = new OrthographicCamera();

		camera.setToOrtho(false, getScreenWidth(), getScreenHeight());
		camera.update();
		
		onCreate();
	}

	@Override
	public void render() {
		if (settings.debugEnabled.isActive()) {
			freqMeter.startCycle();
			
			freqMeter.startUpdate();
			doUpdate();
			freqMeter.endUpdate();

			freqMeter.startRender();
			doRender();
			freqMeter.endRender();
		} else {
			doUpdate();
			
			doRender();
		}
		
	}

	private void doRender() {
		Gdx.gl.glClearColor(1, 0, 1, 1); // MAGENTA
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		mapRenderer.setProjectionMatrix(camera.combined);
		entityRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.setProjectionMatrix(camera.combined);
		fontRenderer.setProjectionMatrix(camera.combined);

		entityRenderer.enableBlending();
		mapRenderer.disableBlending();
		fontRenderer.enableBlending();
		
		if (!layers.empty()) {
			layers.peek().render(mapRenderer, shapeRenderer);
		}
		
		if (settings.debugTextInfos.isActive()) {
			renderDebugTextOverlay();
		}
		
		new Texture("consolefont.gif");
	}

	private void renderDebugTextOverlay() {
		debugTextRenderer.begin(debugFontSize);
		
		if (settings.debugTextFPS.isActive()) {
			debugTextRenderer.drawFormatted("FPS: %s / %f", (int)(freqMeter.fps*100)/100f, freqMeter.targetFPS);
			debugTextRenderer.draw();
		}
		
		if (settings.debugTextTiming.isActive()) {
			debugTextRenderer.drawFormatted("RenderTime: %sms (%d%%)", ((int)(freqMeter.renderTime / 10000))/100f, (int)freqMeter.getRenderPercentage());
			debugTextRenderer.drawFormatted("UpdateTime: %sms (%d%%)", ((int)(freqMeter.updateTime / 10000))/100f, (int)freqMeter.getUpdatePercentage());
			debugTextRenderer.drawFormatted("TotalTime:  %sms (%d%%)", ((int)(freqMeter.totalTime / 10000))/100f, (int)freqMeter.getTotalPercentage());
			debugTextRenderer.draw();
		}

		if (settings.debugTextTiming.isActive()) {
			debugTextRenderer.drawFormatted("Allocated Memory:  %d MB / %d MB", (Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory()) / 1048576, Runtime.getRuntime().totalMemory() / 1048576);
			debugTextRenderer.drawFormatted("GC Count: %d (%d ms)", freqMeter.gcCount, freqMeter.gcTime);
			debugTextRenderer.drawFormatted("GC Time per call: %sms (last %ds ago)", freqMeter.gcTimePerGC, freqMeter.gcTimeBetweenGC/1000);
			debugTextRenderer.draw();
		}
		
		debugTextRenderer.end();
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

	/**
	 * Sets the default font for rendering debug output
	 * 
	 * @param bfont
	 */
	public void setDebugFont(BitmapFont bfont) {
		this.debugFont = bfont;
		
		debugFont.setColor(Color.BLACK);
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
		mapRenderer.dispose();
		fontRenderer.dispose();
		shapeRenderer.dispose();
	}
	
	/**
	 * Load a Texture from the resources
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
	 * Pushes a new Layer on top of the stack
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

	public void setDebugFontSize(float debugFontSize) {
		this.debugFontSize = debugFontSize;
	}
}
