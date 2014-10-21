package de.samdev.absgdx.framework.layer;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.framework.map.TileMap;
import de.samdev.absgdx.framework.map.mapsizeresolver.AbstractMapScaleResolver;
import de.samdev.absgdx.framework.map.mapsizeresolver.ShowCompleteScreenScaleResolver;

public abstract class GameLayer extends AgdxLayer {
	
	protected TileMap map;
	protected AbstractMapScaleResolver mapScaleResolver = new ShowCompleteScreenScaleResolver();

	public GameLayer(AgdxGame owner) {
		super(owner);
		
		map = new TileMap(this.owner, 16, 16);
	}

	@Override
	public void render(SpriteBatch sbatch, ShapeRenderer srenderer) {
		Vector2 scale = mapScaleResolver.getTileSize(owner.getScreenWidth(), owner.getScreenHeight(), map.height, map.width);
		
		srenderer.begin(ShapeType.Filled);
		
		Random r = new Random(0);
		
		for (int y = 0; y < map.height; y++) {
			for (int x = 0; x < map.width; x++) {
				srenderer.setColor(r.nextFloat(), r.nextFloat(), r.nextFloat(), 1);
				
				srenderer.rect(x*scale.x, y*scale.y, scale.x, scale.y);
			}
		}
		
		srenderer.end();
	}

	protected void loadEmptyMap(int w, int h)
	{
		map = new TileMap(this.owner, w, h);
	}
}
