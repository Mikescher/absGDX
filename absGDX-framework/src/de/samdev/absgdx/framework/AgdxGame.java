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

import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionMap;
import de.samdev.absgdx.framework.layer.AgdxLayer;
import de.samdev.absgdx.framework.layer.GameLayer;
import de.samdev.absgdx.framework.layer.MenuLayer;
import de.samdev.absgdx.framework.map.AutoTile;
import de.samdev.absgdx.framework.map.Tile;
import de.samdev.absgdx.framework.menu.elements.MenuElement;
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

	private final static float MAX_UPDATE_DELTA = 100;

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
		debugTextRenderer.begin(settings.debugTextSize.get());

		if (settings.debugTextFPS.isActive()) {
			debugTextRenderer.drawFormatted("FPS: %s / %s (%s v%s)", DebugFormatter.fmtF(freqMeter.fps, 100), freqMeter.targetFPS, Gdx.app.getType(), Gdx.app.getVersion());
			debugTextRenderer.draw();
		}

		if (!layers.empty() && layers.peek() instanceof GameLayer) {
			GameLayer glayer = (GameLayer) layers.peek();
			
			if (settings.debugTextMap.isActive()) {
				Tile tile = glayer.getTileUnderMouse();
	
				debugTextRenderer.drawFormatted("Map: Scale=%s   Offset=%s   Visible=%s   Size=%s",
						DebugFormatter.fmtF(glayer.getTileScale(), 2),
						DebugFormatter.fmtV2(glayer.getMapOffset(), 10),
						DebugFormatter.fmtRectangle(glayer.getVisibleMapBox(), 10),
						DebugFormatter.fmtV2(glayer.getMap().getDimensions(), 1));
	
				if (tile == null)
					debugTextRenderer.drawFormatted("Tile: NULL");
				else
					debugTextRenderer.drawFormatted("Tile: %s", tile.getClass().getName());
	
				if (tile instanceof AutoTile)
					debugTextRenderer.drawFormatted("AutoTile: %s", DebugFormatter.fmtPropertiesMap(((AutoTile)tile).properties, 5));
	
				debugTextRenderer.draw();
			}
	
			if (settings.debugTextEntities.isActive()) {
	
				debugTextRenderer.drawFormatted("Entities: Rendered/Count=%d/%d",
						glayer.getRenderingEntitiesCount(),
						glayer.getEntityCount());
				debugTextRenderer.draw();
			}
	
			if (settings.debugTextCollisionGeometries.isActive()) {
				CollisionMap cmap = glayer.getCollisionMap();
	
				debugTextRenderer.drawFormatted("CollisionGeos: Count=%d",  cmap.getGeometryCount());
				debugTextRenderer.drawFormatted("CollisionMap: Scale=%s   Size=%s", cmap.getScaleString(), DebugFormatter.fmtV2(cmap.getDimensions(), 1));
				debugTextRenderer.draw();
			}
		}
		

		if (!layers.empty() && layers.peek() instanceof MenuLayer && settings.debugMenuLayerTextInfos.isActive()) {
			MenuLayer mlayer = (MenuLayer) layers.peek();
			MenuElement melem = mlayer.getRoot().getElementAt(Gdx.input.getX(), Gdx.input.getY());
			
			debugTextRenderer.drawFormatted("MenuElements: Count=%d",  mlayer.getElementCount());
			
			if (settings.debugElementInfo.isActive()) {
				if (melem != null)
					debugTextRenderer.drawFormatted(" - %s (%s)",  melem.getClass().getSimpleName(), melem.identifier);
			}

			if (settings.debugElementBoundaries.isActive()) {
				if (melem != null) {
					debugTextRenderer.drawFormatted(" - Boundaries: %s", DebugFormatter.fmtRectangle(melem.getBoundaries(), 1));
					debugTextRenderer.drawFormatted(" - Matrix Offset: %s", DebugFormatter.fmtV2(new Vector2(melem.getCoordinateOffsetX(), melem.getCoordinateOffsetY()), 1));
					debugTextRenderer.drawFormatted(" - Tree Depth: %d", melem.getDepth());
				}
			}

			if (settings.debugElementAttributes.isActive()) {
				if (melem != null) {
					debugTextRenderer.drawFormatted(" - [Focused|Hovered|Pressed|Visible]: [%s|%s|%s|%s]", 
							melem.isFocused() ? "X":"O",
							melem.isHovered() ? "X":"O",
							melem.isPressed() ? "X":"O",
							melem.isVisible() ? "X":"O");
					debugTextRenderer.drawFormatted(" - Listener: %s; Font: %s; Provider: %s; composites: (direct := %d || all := %d)", 
							melem.getListenerCount(), 
							melem.getFont()==null ? "NULL" : "SET", 
							melem.getTextureProvider().getRegisteredTexturesCount()>0 ? "SET" : "EMPTY",
							melem.getDirectInnerElements().size(), melem.getAllInnerElements().size());
				}
			}
			
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
		float delta = Gdx.graphics.getDeltaTime() * 1000f;
		delta = Math.min(delta, MAX_UPDATE_DELTA); // TODO What do when delta > MAX_UPDATE_DELTA (Warning / abort / nothing  ???)

		onUpdate(delta);

		if (!layers.empty()) {
			layers.peek().update(delta);
		}
	}

	/**
	 * @param delta the time since the last update (in ms) - can be averaged over he last few cycles
	 */
	public abstract void onUpdate(float delta);

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
	 * @param file the filename
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
		Gdx.input.setInputProcessor(layers.peek());
	}

	/**
	 * Set the stack to a new layer (and removes all other from it)
	 *
	 * @param layer
	 */
	public void setLayer(AgdxLayer layer) {
		while (! layers.isEmpty()) 
			popLayer();
		
		pushLayer(layer);
	}

	/**
	 * pops one layer of the stack
	 */
	public void popLayer() {
		layers.pop();

		Gdx.input.setInputProcessor(layers.peek());
	}

	/**
	 * Gets called after the initialization
	 */
	public abstract void onCreate();
}
