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

import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionListener;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionMap;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionGeometry;
import de.samdev.absgdx.framework.layer.AgdxLayer;
import de.samdev.absgdx.framework.layer.GameLayer;
import de.samdev.absgdx.framework.layer.MenuLayer;
import de.samdev.absgdx.framework.map.AutoTile;
import de.samdev.absgdx.framework.map.Tile;
import de.samdev.absgdx.framework.menu.MenuOwner;
import de.samdev.absgdx.framework.menu.elements.MenuBaseElement;
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
		if (settings.debugEnabled.isActive() || settings.debugBackgroundFPSCapture.isActive()){
			freqMeter.startCycle();

			freqMeter.startUpdate();
			doUpdate();
			freqMeter.endUpdate();

			freqMeter.startRender(settings.debugEnabled.isActive());
			doRender();
			freqMeter.endRender();
			
			freqMeter.startDebugRender();
			doRender_DebugOverlay();
			freqMeter.endDebugRender();
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
			layers.peek().render(layerSpriteRenderer, layerShapeRenderer, debugTextRenderer);
		}
	}
	
	private void doRender_DebugOverlay() {
		if (settings.debugTextInfos.isActive()) {
			renderDebugTextOverlay();
		}
	}

	private void renderDebugTextOverlay() {
		debugTextRenderer.begin(settings.debugTextSize.get());

		if (settings.debugTextFPS.isActive()) {
			debugTextRenderer.drawFormatted("FPS: %s / %s  (%s v%s)  (absGDX v%s)", DebugFormatter.fmtF(freqMeter.fps, 2), freqMeter.targetFPS, Gdx.app.getType(), Gdx.app.getVersion(), settings.versionNumber.get());
			debugTextRenderer.draw();
		}

		if (!layers.empty() && layers.peek() instanceof GameLayer) {
			GameLayer glayer = (GameLayer) layers.peek();
			
			if (settings.debugTextMap.isActive()) {
				Tile tile = glayer.getTileUnderMouse();
				CollisionGeometry tileGeo = glayer.getCollisionMap().getTileCollisionGeometry((int)glayer.getMouseOnMapPositionX(), (int)glayer.getMouseOnMapPositionY());
	
				debugTextRenderer.drawFormatted("Map: Scale=%s   Offset=%s   Visible=%s   Size=%s",
						DebugFormatter.fmtF(glayer.getTileScale(), 1),
						DebugFormatter.fmtV2(glayer.getMapOffset(), 1),
						DebugFormatter.fmtRectangle(glayer.getVisibleMapBox(), 1),
						DebugFormatter.fmtV2(glayer.getMap().getDimensions(), 0));
	
				if (tile == null)
					debugTextRenderer.drawFormatted("Tile: NULL");
				else
					debugTextRenderer.drawFormatted("Tile: %s", tile.getClass().getName());
	
				if (tile instanceof AutoTile)
					debugTextRenderer.drawFormatted("AutoTile: %s", DebugFormatter.fmtPropertiesMap(((AutoTile)tile).properties, 5));
	
				if (tileGeo != null) {
					StringBuilder b = new StringBuilder();
					b.append("[");
					boolean first = true;
					for (CollisionListener lst : tileGeo.listener) {
						if (! first) b.append(", ");
						first = false;
						b.append(lst.getClass().getSimpleName());
					}
					b.append("]");
					
					debugTextRenderer.drawFormatted("CollisionGeometry: %s   Listener=%s", DebugFormatter.fmtGeometry(tileGeo, 0), b.toString());
				}
				
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
				debugTextRenderer.drawFormatted("CollisionMap: Scale=%s   Size=%s", cmap.getScaleString(), DebugFormatter.fmtV2(cmap.getDimensions(), 0));
				debugTextRenderer.draw();
			}
		}
		
		if (!layers.empty() && layers.peek() instanceof MenuLayer && settings.debugMenuLayerTextInfos.isActive()) {
			MenuOwner mlayer = (MenuOwner) layers.peek();
			MenuBaseElement melem = mlayer.getMenuRoot().getElementAt(Gdx.input.getX(), Gdx.input.getY());
			
			debugTextRenderer.drawFormatted("MenuBaseElements: Count=%d",  mlayer.getMenuRoot().getElementCount());
			
			if (settings.debugMenuLayerElementInfo.isActive()) {
				if (melem != null)
					debugTextRenderer.drawFormatted(" - %s (%s)",  melem.getClass().getSimpleName(), melem.identifier);
			}

			if (settings.debugMenuLayerElementBoundaries.isActive()) {
				if (melem != null) {
					debugTextRenderer.drawFormatted(" - Boundaries: %s", DebugFormatter.fmtRectangle(melem.getBoundaries(), 0));
					debugTextRenderer.drawFormatted(" - Matrix Offset: %s", DebugFormatter.fmtV2(new Vector2(melem.getCoordinateOffsetX(), melem.getCoordinateOffsetY()), 0));
					debugTextRenderer.drawFormatted(" - Tree Depth: %d", melem.getDepth());
				}
			}

			if (settings.debugMenuLayerElementAttributes.isActive()) {
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

		if (!layers.empty() && layers.peek() instanceof GameLayer && settings.debugGameLayerMenuTextInfos.isActive()) {
			MenuOwner mlayer = (MenuOwner) layers.peek();
			MenuBaseElement melem = mlayer.getMenuRoot().getElementAt(Gdx.input.getX(), Gdx.input.getY());
			
			debugTextRenderer.drawFormatted("MenuBaseElements: Count=%d",  mlayer.getMenuRoot().getElementCount());
			
			if (settings.debugGameLayerMenuElementInfo.isActive()) {
				if (melem != null)
					debugTextRenderer.drawFormatted(" - %s (%s)",  melem.getClass().getSimpleName(), melem.identifier);
			}

			if (settings.debugGameLayerMenuElementBoundaries.isActive()) {
				if (melem != null) {
					debugTextRenderer.drawFormatted(" - Boundaries: %s", DebugFormatter.fmtRectangle(melem.getBoundaries(), 0));
					debugTextRenderer.drawFormatted(" - Matrix Offset: %s", DebugFormatter.fmtV2(new Vector2(melem.getCoordinateOffsetX(), melem.getCoordinateOffsetY()), 0));
					debugTextRenderer.drawFormatted(" - Tree Depth: %d", melem.getDepth());
				}
			}

			if (settings.debugGameLayerMenuElementAttributes.isActive()) {
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
			debugTextRenderer.drawFormatted("RenderTime: %sms (%d%%) (+ %sms)", DebugFormatter.fmtD(freqMeter.renderTime / 1000000d, 2), (int)freqMeter.getRenderPercentage(), DebugFormatter.fmtD(freqMeter.debugRenderTime / 1000000d, 2));
			debugTextRenderer.drawFormatted("RenderTime (last w/o debug): %sms (%d%%)", DebugFormatter.fmtD(freqMeter.lastNoDebugRenderTime / 1000000d, 2), (int)freqMeter.getLastNoDebugRenderPercentage());
			debugTextRenderer.drawFormatted("UpdateTime: %sms (%d%%)", DebugFormatter.fmtD(freqMeter.updateTime / 1000000d, 2), (int)freqMeter.getUpdatePercentage());
			debugTextRenderer.drawFormatted("TotalTime:  %sms (%d%%) (real: %sms)", DebugFormatter.fmtD(freqMeter.effectivetotalTime / 1000000d, 2), (int)freqMeter.getTotalPercentage(), DebugFormatter.fmtD(freqMeter.totalTime / 1000000d, 2));
			debugTextRenderer.drawFormatted("GDX-DeltaTime:  %sms", DebugFormatter.fmtF(Gdx.graphics.getDeltaTime()*1000f, 1));
			debugTextRenderer.draw();
		}

		if (settings.debugTextMemory.isActive()) {
			debugTextRenderer.drawFormatted("Allocated Memory:  %d MB / %d MB", (Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory()) / 1048576, Runtime.getRuntime().totalMemory() / 1048576);
			debugTextRenderer.drawFormatted("GC Call Count: %d ", freqMeter.gcCount);
			debugTextRenderer.drawFormatted("Last GC Call: %ss ago", freqMeter.gcTimeBetweenGC/1000);
			debugTextRenderer.draw();
		}

		if (settings.debugTextInput.isActive()) {
			debugTextRenderer.drawFormatted("Pointer: %s (delta: %s)", DebugFormatter.fmtV2(new Vector2(Gdx.input.getX(), Gdx.input.getY()), 1), DebugFormatter.fmtV2(new Vector2(Gdx.input.getDeltaX(), Gdx.input.getDeltaY()), 1));
			debugTextRenderer.drawFormatted("Accelerometer: %s", DebugFormatter.fmtV3(new Vector3(Gdx.input.getAccelerometerX(), Gdx.input.getAccelerometerY(), Gdx.input.getAccelerometerZ()), 2));
			debugTextRenderer.drawFormatted("(Azimuth, Pitch, Roll, Rotation): %s , %s", DebugFormatter.fmtV3(new Vector3(Gdx.input.getAzimuth(), Gdx.input.getPitch(), Gdx.input.getRoll()), 2), DebugFormatter.fmtF(Gdx.input.getRotation(), 2));
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
		// NOP
	}

	@Override
	public void resume() {
		// NOP
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
		if (! layers.isEmpty()) {
			layers.peek().onDeactivate();
		}
		
		layers.push(layer);
		Gdx.input.setInputProcessor(layers.peek());
		layers.peek().onActivate();
	}

	/**
	 * Set the stack to a new layer (and removes all other from it)
	 *
	 * @param layer
	 */
	public void setLayer(AgdxLayer layer) {
		while (! layers.isEmpty()) {
			layers.peek().onDeactivate();
			layers.pop();
		}

		layers.push(layer);
		Gdx.input.setInputProcessor(layers.peek());
		layers.peek().onActivate();
	}

	/**
	 * pops one layer of the stack
	 */
	public void popLayer() {
		if (! layers.isEmpty()) {
			layers.peek().onDeactivate();
		}
		
		layers.pop();

		if (! layers.isEmpty()) {
			Gdx.input.setInputProcessor(layers.peek());
			layers.peek().onActivate();
		} else {
			Gdx.input.setInputProcessor(null);
		}
	}

	/**
	 * Gets called after the initialization
	 */
	public abstract void onCreate();
}
