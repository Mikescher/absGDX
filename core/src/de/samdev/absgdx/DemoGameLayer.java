package de.samdev.absgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.framework.layer.GameLayer;
import de.samdev.absgdx.framework.map.mapscaleresolver.SectionMapScaleResolver;

public class DemoGameLayer extends GameLayer {

	public DemoGameLayer(AgdxGame owner) {
		super(owner);

		loadEmptyMap(20, 20);
		for (int x = 0; x < 20; x++) {
			for (int y = 0; y < 20; y++) {
				map.setTile(x, y, new SkyTile());
			}
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
