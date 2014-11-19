package de.samdev.absgdx.sidescrollergame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.Textures;
import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.framework.layer.GameLayer;
import de.samdev.absgdx.framework.map.TileMap;
import de.samdev.absgdx.framework.map.background.ParallaxBackground;
import de.samdev.absgdx.framework.map.mapscaleresolver.MaximumBoundaryMapScaleResolver;
import de.samdev.absgdx.framework.util.exceptions.TmxMapParsingException;
import de.samdev.absgdx.framework.util.tiled.TmxMapLoader;
import de.samdev.absgdx.sidescrollergame.entities.PlayerEntity;
import de.samdev.absgdx.sidescrollergame.tiles.SidescrollerAutoTile;
import de.samdev.absgdx.sidescrollergame.tiles.SpawnTile;

public class SidescrollerGameLayer extends GameLayer {

	private PlayerEntity pe = null;
	
	public SidescrollerGameLayer(AgdxGame owner) {
		super(owner, loadMap());

		setMapScaleResolver(new MaximumBoundaryMapScaleResolver(20, 15));
//		setMapScaleResolver(new ShowCompleteMapScaleResolver());
		
		addEntity(pe = new PlayerEntity(1, 9));
//		addBackground(new ParallaxBackground(Textures.texParallax_1, 24));
//		addBackground(new ParallaxBackground(Textures.texParallax_2, 16));

		addBackground(new ParallaxBackground(Textures.texParallax_1, 24));
		addBackground(new ParallaxBackground(Textures.texParallax_2, 16));
	}

	private static TileMap loadMap() {
		TmxMapLoader loader = new TmxMapLoader(Gdx.files.internal("demosidemap.tmx"));
		
		loader.addMapping(208, SpawnTile.class);
		
		loader.addDefaultMapping(SidescrollerAutoTile.class);
		
		try {
			return loader.start();
		} catch (TmxMapParsingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public void onUpdate(float delta) {
		final float speed = 0.025f * delta;
		
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
			setBoundedOffset(new Vector2(map_offset.x + speed, map_offset.y));
		if (Gdx.input.isKeyPressed(Keys.LEFT))
			setBoundedOffset(new Vector2(map_offset.x - speed, map_offset.y));
		if (Gdx.input.isKeyPressed(Keys.UP))
			setBoundedOffset(new Vector2(map_offset.x, map_offset.y + speed));
		if (Gdx.input.isKeyPressed(Keys.DOWN))
			setBoundedOffset(new Vector2(map_offset.x, map_offset.y - speed));
		
		scrollMapToEntity(pe, 5f, 2f);
	}

}
