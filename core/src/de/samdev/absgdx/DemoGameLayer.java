package de.samdev.absgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.framework.entities.Entity;
import de.samdev.absgdx.framework.layer.GameLayer;
import de.samdev.absgdx.framework.map.mapscaleresolver.SectionMapScaleResolver;
import de.samdev.absgdx.framework.util.exceptions.TmxMapParsingException;
import de.samdev.absgdx.framework.util.tiled.TmxMapLoader;

public class DemoGameLayer extends GameLayer {

	private Bucket_1 bucket_1;
	private Bucket_2 bucket_2;
	private Bucket_3 bucket_3;
	
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

		addEntity(bucket_1 = new Bucket_1());
		addEntity(bucket_2 = new Bucket_2());
		addEntity(bucket_3 = new Bucket_3(bucket_1, bucket_2));
		
		bucket_1.owner = this;
		bucket_2.owner = this;
		bucket_3.owner = this;

		
		
		
		
		
		Bucket_1 b;
		
		
		System.out.print("[");for (Entity e : entities) System.out.print(e.zlayer +  ",    ");System.out.println("]");
		
		b = new Bucket_1();
		b.zlayer = 1;
		addEntity(b);
		
		
		System.out.print("[");for (Entity e : entities) System.out.print(e.zlayer +  ",    ");System.out.println("]");
		
		b = new Bucket_1();
		b.zlayer = 2;
		addEntity(b);
		
		
		System.out.print("[");for (Entity e : entities) System.out.print(e.zlayer +  ",    ");System.out.println("]");
		
		b = new Bucket_1();
		b.zlayer = 3;
		addEntity(b);
		
		
		System.out.print("[");for (Entity e : entities) System.out.print(e.zlayer +  ",    ");System.out.println("]");
		
		b = new Bucket_1();
		b.zlayer = 2;
		addEntity(b);
		
		
		System.out.print("[");for (Entity e : entities) System.out.print(e.zlayer +  ",    ");System.out.println("]");
		
		b = new Bucket_1();
		b.zlayer = 2;
		addEntity(b);
		
		
		System.out.print("[");for (Entity e : entities) System.out.print(e.zlayer +  ",    ");System.out.println("]");
		
		b = new Bucket_1();
		b.zlayer = 100;
		addEntity(b);
		
		
		System.out.print("[");for (Entity e : entities) System.out.print(e.zlayer +  ",    ");System.out.println("]");
		
		b = new Bucket_1();
		b.zlayer = -11;
		addEntity(b);
		
		
		System.out.print("[");for (Entity e : entities) System.out.print(e.zlayer +  ",    ");System.out.println("]");
		
		b = new Bucket_1();
		b.zlayer = 0;
		addEntity(b);
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
		
		if (Gdx.input.isTouched())
			setBoundedOffset(map_offset.sub(Gdx.input.getDeltaX()  / getTileScale(), -Gdx.input.getDeltaY()  / getTileScale()));
	}
}
