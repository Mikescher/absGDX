package de.samdev.absgdx.example.sidescrollergame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.example.Textures;
import de.samdev.absgdx.example.sidescrollergame.entities.PlayerEntity;
import de.samdev.absgdx.example.sidescrollergame.tiles.DoorBottomTile;
import de.samdev.absgdx.example.sidescrollergame.tiles.DoorTopTile;
import de.samdev.absgdx.example.sidescrollergame.tiles.JumpPadTile;
import de.samdev.absgdx.example.sidescrollergame.tiles.LadderTile;
import de.samdev.absgdx.example.sidescrollergame.tiles.LadderTopTile;
import de.samdev.absgdx.example.sidescrollergame.tiles.LeverTile;
import de.samdev.absgdx.example.sidescrollergame.tiles.SidescrollerAutoTile;
import de.samdev.absgdx.example.sidescrollergame.tiles.SpikeTile;
import de.samdev.absgdx.example.sidescrollergame.tiles.TorchTile;
import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.framework.layer.GameLayer;
import de.samdev.absgdx.framework.map.TileMap;
import de.samdev.absgdx.framework.map.background.ParallaxBackground;
import de.samdev.absgdx.framework.map.mapscaleresolver.MaximumBoundaryMapScaleResolver;
import de.samdev.absgdx.framework.util.exceptions.TmxMapParsingException;
import de.samdev.absgdx.framework.util.tiled.TmxMapLoader;

public class SidescrollerGameLayer extends GameLayer {

	private PlayerEntity pe = null;
	
	public SidescrollerGameLayer(AgdxGame owner) {
		super(owner, loadMap());

		setMapScaleResolver(new MaximumBoundaryMapScaleResolver(20, 15));
		
		addEntity(pe = new PlayerEntity(1, 9));

		addBackground(new ParallaxBackground(Textures.texParallax_1, 24));
		addBackground(new ParallaxBackground(Textures.texParallax_2, 16));
		
		addOuterMapCollisionBoxes();
	}

	private static TileMap loadMap() {
		TmxMapLoader loader = new TmxMapLoader(Gdx.files.internal("demosidemap.tmx"));
		
		loader.addMapping(8,   LeverTile.class);
		loader.addMapping(123, SpikeTile.class);
		loader.addMapping(151, JumpPadTile.class);
		loader.addMapping(208, DoorBottomTile.class);
		loader.addMapping(211, LadderTile.class);
		loader.addMapping(236, DoorTopTile.class);
		loader.addMapping(264, LadderTopTile.class);
		loader.addMapping(105, TorchTile.class);
		
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
			setRawOffset(new Vector2(map_offset.x + speed, map_offset.y));
		if (Gdx.input.isKeyPressed(Keys.LEFT))
			setRawOffset(new Vector2(map_offset.x - speed, map_offset.y));
		if (Gdx.input.isKeyPressed(Keys.UP))
			setRawOffset(new Vector2(map_offset.x, map_offset.y + speed));
		if (Gdx.input.isKeyPressed(Keys.DOWN))
			setRawOffset(new Vector2(map_offset.x, map_offset.y - speed));
		
		scrollMapToEntity(pe, 5f, 2f);
		
		if (Gdx.input.isKeyPressed(Keys.BACK) || Gdx.input.isKeyPressed(Keys.ESCAPE)) owner.popLayer();
	}

}
