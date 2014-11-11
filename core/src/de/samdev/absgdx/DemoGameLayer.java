package de.samdev.absgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.entities.Anchorpoint_1;
import de.samdev.absgdx.entities.Angel_1;
import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.framework.layer.GameLayer;
import de.samdev.absgdx.framework.map.TileMap;
import de.samdev.absgdx.framework.map.mapscaleresolver.SectionMapScaleResolver;
import de.samdev.absgdx.framework.util.exceptions.TmxMapParsingException;
import de.samdev.absgdx.framework.util.tiled.TmxMapLoader;

public class DemoGameLayer extends GameLayer {

//	private Bucket_1 bucket_1;
//	private Bucket_2 bucket_2;
//	private Bucket_3 bucket_3;
	
	public DemoGameLayer(AgdxGame owner) {
		super(owner, loadMap());

		setMapScaleResolver(new SectionMapScaleResolver(64, 36, 0.5f, 20f));
//		setMapScaleResolver(new ShowCompleteMapScaleResolver());
//		setMapScaleResolver(new MaximumBoundaryMapScaleResolver(3, 3));

//		addEntity(bucket_1 = new Bucket_1());
//		addEntity(bucket_2 = new Bucket_2());
//		addEntity(bucket_3 = new Bucket_3(bucket_1, bucket_2));
//		
//		bucket_1.owner = this;
//		bucket_2.owner = this;
//		bucket_3.owner = this;
//
//		addEntity(new Bush_1(33.0f, 19.0f));
//		addEntity(new Bush_2(34.0f, 19.5f));
		
		addEntity(new Angel_1());
		
		addEntity(new Anchorpoint_1());
	}
	
	private static TileMap loadMap() {
		TmxMapLoader loader = new TmxMapLoader(Gdx.files.internal("demomap.tmx"));
		
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
		if (Gdx.input.isKeyJustPressed(Keys.F1))
			owner.settings.debugEnabled.doSwitch();

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
		
//		if (System.currentTimeMillis() - last > 100){
//			last = System.currentTimeMillis();
//			addEntity(new FlowerPot_1());
//		}
		
		if (Gdx.input.isKeyJustPressed(Keys.O)) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			
			
			StringBuilder b = new StringBuilder();
			for (int y = collisionMap.height - 1; y >= 0; y--) {
				for (int x = 0; x < collisionMap.width; x++) {
					b.append(collisionMap.map[x][y].geometries.size());
				}
				b.append(System.lineSeparator());
			}

			System.out.print(b.toString());
			
			System.out.println();
			System.out.println();
			
			for (int y = 3 - 1; y >= 0; y--) {
				for (int x = 0; x < 3; x++) {
					System.out.print(collisionMap.outerBorder[x][y] == null ? "X" : ("" + collisionMap.outerBorder[x][y].geometries.size()));
				}
				System.out.println();
			}
			
			System.out.println();
			System.out.println();
			System.out.println();
		}
	}
}
