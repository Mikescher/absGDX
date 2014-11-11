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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import de.samdev.absgdx.framework.layer.AgdxLayer;
import de.samdev.absgdx.framework.layer.GameLayer;
import de.samdev.absgdx.framework.renderer.DebugTextRenderer;
import de.samdev.absgdx.framework.util.DebugFormatter;
import de.samdev.absgdx.framework.util.DebugFrequencyMeter;

/**
 * The basic class. 
 * 
 * Here is where the magic happens
 *
 */
public abstract class AgdxGame implements ApplicationListener {

	// ##### LibGDX Objects #####

	private OrthographicCamera camera;
	
	private SpriteBatch layerSpriteRenderer;
	private SpriteBatch debugSpriteRenderer;
	
	private ShapeRenderer layerShapeRenderer;
	private ShapeRenderer debugShapeRenderer;

	// ##### Debug #####
	
	private BitmapFont debugFont;		 // TODO Add API to render custom text / with custom font
	private DebugTextRenderer debugTextRenderer;
	private final DebugFrequencyMeter freqMeter = new DebugFrequencyMeter();
	
	// ##### Layer #####

	private final Stack<AgdxLayer> layers = new Stack<AgdxLayer>();

	// ##### Other #####

	/** the game settings */
	public final GameSettings settings = new GameSettings();
	
	private float debugFontSize = 1f;
	
	@Override
	public void create() {
		layerSpriteRenderer = new SpriteBatch();
		layerShapeRenderer = new ShapeRenderer();
		debugSpriteRenderer = new SpriteBatch();
		debugShapeRenderer = new ShapeRenderer();
		
		setDebugFont(new BitmapFont());
		
		debugTextRenderer = new DebugTextRenderer(this, debugFont, debugSpriteRenderer, debugShapeRenderer, 10, 10);
	
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
		Gdx.gl.glClearColor(0, 0, 0, 1); // MAGENTA
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		layerShapeRenderer.setProjectionMatrix(camera.combined);
		layerSpriteRenderer.setProjectionMatrix(camera.combined);
		debugShapeRenderer.setProjectionMatrix(camera.combined);
		debugSpriteRenderer.setProjectionMatrix(camera.combined);

		layerSpriteRenderer.disableBlending();
		debugSpriteRenderer.enableBlending();
		
		if (!layers.empty()) {
			layers.peek().render(layerSpriteRenderer, layerShapeRenderer);
		}
		
		if (settings.debugTextInfos.isActive()) {
			renderDebugTextOverlay();
		}
	}

	private void renderDebugTextOverlay() {
		debugTextRenderer.begin(debugFontSize);
		
		if (settings.debugTextFPS.isActive()) {
			debugTextRenderer.drawFormatted("FPS: %s / %s (%s v%s)", DebugFormatter.fmtF(freqMeter.fps, 100), freqMeter.targetFPS, Gdx.app.getType(), Gdx.app.getVersion());
			debugTextRenderer.draw();
		}
		
		if (settings.debugTextMap.isActive() && !layers.empty() && layers.peek() instanceof GameLayer) {
			GameLayer glayer = (GameLayer) layers.peek();
			
			debugTextRenderer.drawFormatted("Map: Scale=%s   Offset=%s   Visible=%s   Size=%s", 
					DebugFormatter.fmtF(glayer.getTileScale(), 2), 
					DebugFormatter.fmtV2(glayer.getMapOffset(), 10), 
					DebugFormatter.fmtRectangle(glayer.getVisibleMapBox(), 10),
					DebugFormatter.fmtV2(glayer.getMap().getDimensions(), 1));
			debugTextRenderer.draw();
		}

		if (settings.debugTextEntities.isActive() && !layers.empty() && layers.peek() instanceof GameLayer) {
			GameLayer glayer = (GameLayer) layers.peek();
			
			debugTextRenderer.drawFormatted("Entities: Count=%d", 
					glayer.getEntityCount());
			debugTextRenderer.draw();
		}
		
		if (settings.debugTextTiming.isActive()) {
			debugTextRenderer.drawFormatted("RenderTime: %sms (%d%%)", DebugFormatter.fmtD(freqMeter.renderTime / 1000000d, 100), (int)freqMeter.getRenderPercentage());
			debugTextRenderer.drawFormatted("UpdateTime: %sms (%d%%)", DebugFormatter.fmtD(freqMeter.updateTime / 1000000d, 100), (int)freqMeter.getUpdatePercentage());
			debugTextRenderer.drawFormatted("TotalTime:  %sms (%d%%)", DebugFormatter.fmtD(freqMeter.totalTime / 1000000d, 100), (int)freqMeter.getTotalPercentage());
			debugTextRenderer.drawFormatted("GDX-DeltaTime:  %sms", DebugFormatter.fmtF(Gdx.graphics.getDeltaTime()*1000f, 10));
			debugTextRenderer.draw();
		}

		if (settings.debugTextMemory.isActive()) {
			debugTextRenderer.drawFormatted("Allocated Memory:  %d MB / %d MB", (Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory()) / 1048576, Runtime.getRuntime().totalMemory() / 1048576);
			debugTextRenderer.drawFormatted("GC Call Count: %d ", freqMeter.gcCount);
			debugTextRenderer.drawFormatted("Last GC Call: %ss ago", freqMeter.gcTimeBetweenGC/1000);
			debugTextRenderer.draw();
		}
		
		if (settings.debugTextInput.isActive()) {
			debugTextRenderer.drawFormatted("Pointer: %s (delta: %s)", DebugFormatter.fmtV2(new Vector2(Gdx.input.getX(), Gdx.input.getY()), 10), DebugFormatter.fmtV2(new Vector2(Gdx.input.getDeltaX(), Gdx.input.getDeltaY()), 10));
			debugTextRenderer.drawFormatted("Accelerometer: %s", DebugFormatter.fmtV3(new Vector3(Gdx.input.getAccelerometerX(), Gdx.input.getAccelerometerY(), Gdx.input.getAccelerometerZ()), 100));
			debugTextRenderer.drawFormatted("(Azimuth, Pitch, Roll, Rotation): %s , %s", DebugFormatter.fmtV3(new Vector3(Gdx.input.getAzimuth(), Gdx.input.getPitch(), Gdx.input.getRoll()), 100), DebugFormatter.fmtF(Gdx.input.getRotation(), 100));
			debugTextRenderer.drawFormatted("Touched: %s (Just Touched: %s)", Gdx.input.isTouched(), Gdx.input.justTouched());
			debugTextRenderer.draw();
		}
		
		debugTextRenderer.end();
	}

	private void doUpdate() {
		if (!layers.empty()) {
			layers.peek().update(Gdx.graphics.getDeltaTime() * 1000f);
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
		layerShapeRenderer.dispose();
		layerSpriteRenderer.dispose();
		
		debugShapeRenderer.dispose();
		debugSpriteRenderer.dispose();
		
		debugFont.dispose();
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

	/**
	 * Sets the font size for the debug display
	 * 
	 * @param debugFontSize
	 */
	public void setDebugFontSize(float debugFontSize) {
		this.debugFontSize = debugFontSize;
	}
}
