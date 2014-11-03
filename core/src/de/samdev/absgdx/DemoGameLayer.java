package de.samdev.absgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.framework.layer.GameLayer;
import de.samdev.absgdx.framework.map.mapscaleresolver.SectionMapScaleResolver;
import de.samdev.absgdx.framework.util.exceptions.TmxMapParsingException;
import de.samdev.absgdx.framework.util.tiled.TmxMapLoader;

public class DemoGameLayer extends GameLayer {

	private Player playerEntity;
	
	public DemoGameLayer(AgdxGame owner) {
		super(owner);

		TmxMapLoader loader = new TmxMapLoader(Gdx.files.internal("demomap.tmx"));
		
		loader.addDefaultMapping(StandardAutoTile.class);
		
		try {
			loadMap(loader.start());
		} catch (TmxMapParsingException e) {
			e.printStackTrace();
		}

		setMapScaleResolver(new SectionMapScaleResolver(64, 36, 0.5f, 20f));
//		setMapScaleResolver(new ShowCompleteMapScaleResolver());
//		setMapScaleResolver(new MaximumBoundaryMapScaleResolver(3, 3));
		
		playerEntity = new Player();
		addEntity(playerEntity);
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

		if (Gdx.input.isKeyJustPressed(Keys.D))
			owner.settings.debugEnabled.doSwitch();
	}
}
