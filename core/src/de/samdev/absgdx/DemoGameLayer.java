package de.samdev.absgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.framework.layer.GameLayer;
import de.samdev.absgdx.framework.map.mapscaleresolver.SectionMapScaleResolver;
import de.samdev.absgdx.framework.util.exceptions.ConstructorNotFoundException;
import de.samdev.absgdx.framework.util.tiled.TmxMapLoader;

public class DemoGameLayer extends GameLayer {

	public DemoGameLayer(AgdxGame owner) {
		super(owner);

		TmxMapLoader loader = new TmxMapLoader(Gdx.files.internal("demomap.tmx"));
		
		loader.addMapping(118, SkyTile.class);
		loader.addMapping(21, GroundTile_TL.class);
		loader.addMapping(22, GroundTile_TR.class);
		loader.addMapping(53, GroundTile_BL.class);
		loader.addMapping(54, GroundTile_BR.class);
		
		try {
			loadMap(loader.start());
		} catch (ConstructorNotFoundException e) {
			e.printStackTrace();
		}

		setMapScaleResolver(new SectionMapScaleResolver(12, 8, 0.5f, 20f));
	}

	@Override
	public void onUpdate() {
		final float speed = 0.1f;

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
