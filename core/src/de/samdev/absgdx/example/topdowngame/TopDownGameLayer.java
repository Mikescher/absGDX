package de.samdev.absgdx.example.topdowngame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.example.topdowngame.entities.Anchorpoint_1;
import de.samdev.absgdx.example.topdowngame.entities.Bucket_1;
import de.samdev.absgdx.example.topdowngame.entities.Bucket_2;
import de.samdev.absgdx.example.topdowngame.entities.Bucket_3;
import de.samdev.absgdx.example.topdowngame.entities.Slide_1;
import de.samdev.absgdx.example.topdowngame.tiles.AbyssTile;
import de.samdev.absgdx.example.topdowngame.tiles.StandardAutoTile;
import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.framework.layer.GameLayer;
import de.samdev.absgdx.framework.map.TileMap;
import de.samdev.absgdx.framework.map.mapscaleresolver.MaximumBoundaryMapScaleResolver;
import de.samdev.absgdx.framework.map.mapscaleresolver.SectionMapScaleResolver;
import de.samdev.absgdx.framework.map.mapscaleresolver.ShowCompleteMapScaleResolver;
import de.samdev.absgdx.framework.util.exceptions.TmxMapParsingException;
import de.samdev.absgdx.framework.util.tiled.TmxMapLoader;

public class TopDownGameLayer extends GameLayer {

	private Bucket_1 bucket_1;
	private Bucket_2 bucket_2;
	private Bucket_3 bucket_3;

	private Anchorpoint_1 ap_1;
	
	public TopDownGameLayer(AgdxGame owner) {
		super(owner, loadMap());

		setMapScaleResolver(new SectionMapScaleResolver(32, 18, 0.5f, 20f));

//		addEntity(bucket_1 = new Bucket_1());
//		addEntity(bucket_2 = new Bucket_2());
//		addEntity(bucket_3 = new Bucket_3(bucket_1, bucket_2));
		
//		bucket_1.owner = this;
//		bucket_2.owner = this;
//		bucket_3.owner = this;

//		addEntity(new Bush_1(33.0f, 19.0f));
//		addEntity(new Bush_2(34.0f, 19.5f));
		
//		addEntity(new Angel_1());

		addEntity(ap_1 = new Anchorpoint_1());

		addEntity(new Slide_1(ap_1));
	}
	
	private static TileMap loadMap() {
		TmxMapLoader loader = new TmxMapLoader(Gdx.files.internal("demomap.tmx"));
		
		loader.addMapping(1290, AbyssTile.class);
		loader.addMapping(1291, AbyssTile.class);
		loader.addMapping(1258, AbyssTile.class);
		loader.addMapping(1259, AbyssTile.class);
		
		loader.addDefaultMapping(StandardAutoTile.class);
		
		try {
			return loader.start();
		} catch (TmxMapParsingException e) {
			e.printStackTrace();
			return null;
		}
	}

	long last = 0;
	
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
		
		if (Gdx.input.isTouched())
			setBoundedOffset(map_offset.sub(Gdx.input.getDeltaX()  / getTileScale(), -Gdx.input.getDeltaY()  / getTileScale()));
		
		if (System.currentTimeMillis() - last > 33){
			last = System.currentTimeMillis();
//			addEntity(new FlowerPot_1());
		}

		if (Gdx.input.isKeyJustPressed(Keys.F10)) setMapScaleResolver(new SectionMapScaleResolver(32, 18, 0.5f, 20f));
		if (Gdx.input.isKeyJustPressed(Keys.F11)) setMapScaleResolver(new ShowCompleteMapScaleResolver());
		if (Gdx.input.isKeyJustPressed(Keys.F12)) setMapScaleResolver(new MaximumBoundaryMapScaleResolver(3, 3));
		
		scrollMapToEntity(ap_1, 2);
	}
}
