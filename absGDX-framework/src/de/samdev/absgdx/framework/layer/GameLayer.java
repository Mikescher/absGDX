package de.samdev.absgdx.framework.layer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.framework.map.TileMap;
import de.samdev.absgdx.framework.map.mapsizeresolver.AbstractMapScaleResolver;
import de.samdev.absgdx.framework.map.mapsizeresolver.ShowCompleteMapScaleResolver;

public abstract class GameLayer extends AgdxLayer {
	protected TileMap map;
	protected Vector2 map_offset = new Vector2(0, 0);

	private AbstractMapScaleResolver mapScaleResolver = new ShowCompleteMapScaleResolver();

	public GameLayer(AgdxGame owner) {
		super(owner);

		map = new TileMap(16, 16);
	}

	@Override
	public void render(SpriteBatch sbatch, ShapeRenderer srenderer) {
		float tilesize = mapScaleResolver.getTileSize(owner.getScreenWidth(), owner.getScreenHeight(), map.height, map.width);

		srenderer.begin(ShapeType.Line);
		srenderer.setColor(Color.MAGENTA);
		sbatch.disableBlending();
		sbatch.begin();

		for (int y = 0; y < map.height; y++) {
			for (int x = 0; x < map.width; x++) {
				if (owner.settings.debugMapGridLines.isActive()) {
					srenderer.rect((x - map_offset.x) * tilesize, (y - map_offset.y) * tilesize, tilesize, tilesize);
				}
				
				TextureRegion r = map.getTile(x, y).getTexture();
				
				if (r != null)
					sbatch.draw(r, (x - map_offset.x) * tilesize, (y - map_offset.y) * tilesize, tilesize, tilesize);
			}
		}

		sbatch.end();
		srenderer.end();
	}

	@Override
	public void update() {
		
		onUpdate();
	}

	@Override
	public void onResize() {
		setBoundedOffset(map_offset);
	}

	/**
	 * @return the currently visible tiles (in tile-coordinates : 1 tile = 1 unit)
	 */
	public Rectangle getVisibleMapBox() {
		float tilesize = mapScaleResolver.getTileSize(owner.getScreenWidth(), owner.getScreenHeight(), map.height, map.width);
		
		Rectangle view = new Rectangle(map_offset.x, map_offset.y, owner.getScreenWidth() / tilesize, owner.getScreenHeight() / tilesize);
		
		return view;
	}
	
	/**
	 * Fixes the offset - it gets re-adjusted in case the curret viewport has left the map
	 */
	private void limitOffset() {
		Rectangle viewport = getVisibleMapBox();
		
		if (viewport.x + viewport.width > map.width) {
			map_offset.x = map.width - viewport.width;
		}
		
		if (viewport.y + viewport.height > map.height) {
			map_offset.y = map.height - viewport.height;
		}
		
		viewport = getVisibleMapBox();
		
		if (viewport.x < 0) {
			map_offset.x = 0;
		}
		
		if (viewport.y < 0) {
			map_offset.y = 0;
		}
	}

	/**
	 * Sets the offset but limits it so that it can't leave the map boundaries
	 * 
	 * @param offset
	 */
	public void setBoundedOffset(Vector2 offset) {
		setRawOffset(offset);

		limitOffset();
	}

	/**
	 * Sets the offset, but unlike setBoundedOffset() it won't check the boundaries
	 * 
	 * @param offset
	 */
	public void setRawOffset(Vector2 offset) {
		map_offset = offset;
	}

	/**
	 * Loads a new map filled with EmptyTile
	 * 
	 * @param w width
	 * @param h height
	 */
	public void loadEmptyMap(int w, int h) {
		map = new TileMap(w, h);
	}

	/**
	 * Sets the mapScaleResolver - the component to determine the size of a tile
	 * 
	 * @param resolver
	 */
	public void setMapScaleResolver(AbstractMapScaleResolver resolver) {
		this.mapScaleResolver = resolver;
	}
	
	public abstract void onUpdate();
}
