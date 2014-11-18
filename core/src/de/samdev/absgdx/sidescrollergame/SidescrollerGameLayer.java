package de.samdev.absgdx.sidescrollergame;

import com.badlogic.gdx.Gdx;

import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.framework.layer.GameLayer;
import de.samdev.absgdx.framework.map.TileMap;
import de.samdev.absgdx.framework.map.mapscaleresolver.MaximumBoundaryMapScaleResolver;
import de.samdev.absgdx.framework.util.exceptions.TmxMapParsingException;
import de.samdev.absgdx.framework.util.tiled.TmxMapLoader;
import de.samdev.absgdx.sidescrollergame.entities.PlayerEntity;
import de.samdev.absgdx.sidescrollergame.tiles.SidescrollerAutoTile;
import de.samdev.absgdx.sidescrollergame.tiles.SpawnTile;

public class SidescrollerGameLayer extends GameLayer {

	public SidescrollerGameLayer(AgdxGame owner) {
		super(owner, loadMap());

		setMapScaleResolver(new MaximumBoundaryMapScaleResolver(20, 15));
		
		addEntity(new PlayerEntity(1, 6));
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
		// TODO Auto-generated method stub

	}

}
