package de.samdev.absgdx.framework.layer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.framework.entities.Entity;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryOwner;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionMap;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionBox;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionCircle;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionGeometry;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionTriangle;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.OuterMapCollisionOwner;
import de.samdev.absgdx.framework.map.Tile;
import de.samdev.absgdx.framework.map.TileMap;
import de.samdev.absgdx.framework.map.background.MapBackground;
import de.samdev.absgdx.framework.map.mapscaleresolver.AbstractMapScaleResolver;
import de.samdev.absgdx.framework.map.mapscaleresolver.ShowCompleteMapScaleResolver;
import de.samdev.absgdx.framework.math.SortedLinkedEntityList;
import de.samdev.absgdx.framework.util.ShapeRendererUtil;

/**
 * Game layer represents a level or the whole game.
 * It contains the TileMap and the Entities
 */
public abstract class GameLayer extends AgdxLayer {
	
	//######## MAP ########
	
	protected final TileMap map;
	protected Vector2 map_offset = new Vector2(0, 0);

	protected List<MapBackground> backgrounds = new ArrayList<MapBackground>();
	
	private AbstractMapScaleResolver mapScaleResolver = new ShowCompleteMapScaleResolver();

	//######## ENTITIES ########
	
	protected final SortedLinkedEntityList entities = new SortedLinkedEntityList();
	private final List<Entity> futureEntities = new ArrayList<Entity>();
	
	protected int renderedEntities = 0;
	protected final CollisionMap collisionMap;

	//######## INPUT ########
	
	private boolean isPointerDown = false;
	
	/**
	 * Creates a new GameLayer
	 * 
	 * @param owner
	 * @param map 
	 */
	public GameLayer(AgdxGame owner, TileMap map) {
		super(owner);

		this.map = map;
		this.collisionMap = new CollisionMap(map);
	}

	/**
	 * Creates a new GameLayer
	 * 
	 * The expScale is exponential. This means:
	 * 0  : MapTiles == CollisionTiles
	 * 1  : CollisionTiles are 2 times bigger
	 * 2  : CollisionTiles are 4 times bigger
	 * -1 : CollisionTiles are 2 times smaller
	 * etc
	 * 
	 * @param owner
	 * @param map 
	 * @param expCollisionTileScale the ratio collisionMapTileSize / mapTileSize in the form [2^n] 
	 */
	public GameLayer(AgdxGame owner, TileMap map, int expCollisionTileScale) {
		super(owner);

		this.map = map;
		this.collisionMap = new CollisionMap(map, expCollisionTileScale);
	}
	
	@Override
	public void render(SpriteBatch sbatch, ShapeRenderer srenderer) {
		float tilesize = mapScaleResolver.getTileSize(owner.getScreenWidth(), owner.getScreenHeight(), map.height, map.width);

		Rectangle visible = getVisibleMapBox();
		
		srenderer.identity();
		srenderer.scale(tilesize, tilesize, 1);
		srenderer.translate(-map_offset.x, -map_offset.y, 0);
		
		sbatch.getTransformMatrix().idt();
		sbatch.getTransformMatrix().scale(tilesize, tilesize, 1);
		sbatch.getTransformMatrix().translate(-map_offset.x, -map_offset.y, 0);
		
		//#####################################################################
		
		renderMap(sbatch, srenderer, visible);
		
		renderEntities(sbatch, srenderer, visible);
	}

	private void renderMap(SpriteBatch sbatch, ShapeRenderer srenderer, Rectangle visible) {
		sbatch.enableBlending();
		sbatch.begin();
		
		for (MapBackground background : this.backgrounds) {
			background.draw(sbatch, map_offset, map, visible);
		}
		
		for (int y = Math.max(0, (int) visible.y); y < Math.min(map.height, (int)(visible.y + visible.height + 1)); y++) {
			for (int x = Math.max(0, (int) visible.x); x < Math.min(map.width, (int)(visible.x + visible.width + 1)); x++) {
				Tile t = map.getTile(x, y);
				TextureRegion r = t.getTexture();
				
				if (r != null)
					sbatch.draw(r, x, y, 0.5f, 0.5f, 1.0f, 1.0f, t.getTextureScaleX(), t.getTextureScaleY(), t.getTextureRotation() + 90.0f, true);
			}
		}
		sbatch.end();
		
		if (owner.settings.debugVisualMap.isActive()) {
			renderMap_debug(srenderer, visible);
		}
	}

	private void renderEntities(SpriteBatch sbatch, ShapeRenderer srenderer, Rectangle visible) {
		renderedEntities = 0;

		sbatch.enableBlending();
		sbatch.begin();
		for( Iterator<Entity> it = entities.descendingIterator(); it.hasNext();) { // Iterate reverse (so z order is correct)
		    Entity entity = it.next();
		    //TODO only draw visible entities
			if (visible.overlaps(entity.getBoundings())) {
				sbatch.draw(
						entity.getTexture(), 
						entity.getPositionX(), entity.getPositionY(), 
						entity.getWidth()/2f, entity.getHeight()/2f, 
						entity.getWidth(), entity.getHeight(), 
						entity.getTextureScaleX(), entity.getTextureScaleY(), 
						entity.getTextureRotation());

				renderedEntities++;
			}
		}
		sbatch.end();
		
		if (owner.settings.debugVisualEntities.isActive()) {
			renderEntities_debug(srenderer, visible);
		}
	}

	private void renderMap_debug(ShapeRenderer srenderer, Rectangle visible) {
		if (owner.settings.debugMapGridLines.isActive()) {
			srenderer.begin(ShapeType.Line);
			srenderer.setColor(owner.settings.debugMapGridLinesColor.get());
			
			for (int y = Math.max(0, (int) visible.y); y < Math.min(map.height, (int)(visible.y + visible.height + 1)); y++) {
				for (int x = Math.max(0, (int) visible.x); x < Math.min(map.width, (int)(visible.x + visible.width + 1)); x++) {
					srenderer.rect(x, y, 1, 1);
				}
			}
			srenderer.end();
		}
		
		if (owner.settings.debugCollisionMapMarkers.isActive()) {
			srenderer.begin(ShapeType.Line);
			srenderer.setColor(owner.settings.debugMapGridLinesColor.get());
			
			double scale = Math.pow(2, -collisionMap.expTileScale);
			
			for (int y = Math.max(0, (int) visible.y); y < Math.min(map.height, (int)(visible.y + visible.height + 1)); y++) {
				for (int x = Math.max(0, (int) visible.x); x < Math.min(map.width, (int)(visible.x + visible.width + 1)); x++) {
					if (! collisionMap.map[(int) (x * scale)][(int) (y * scale)].geometries.isEmpty()) {
						srenderer.line(x, y, x+1, y+1);
						srenderer.line(x+1, y, x, y+1);
					}
				}
			}
			srenderer.end();
		}
	}

	private void renderEntities_debug(ShapeRenderer srenderer, Rectangle visible) {
		srenderer.setAutoShapeType(true);
		srenderer.begin(ShapeType.Line);
		
		for( Iterator<Entity> it = entities.descendingIterator(); it.hasNext();) {
		    Entity entity = it.next();
		    
		    if (visible.overlaps(entity.getBoundings())) {
			    if (owner.settings.debugEntitiesBoundingBoxes.isActive()) {
					srenderer.setColor(owner.settings.debugEntitiesBoundingBoxesColor.get());
			    	srenderer.rect(entity.getPositionX(), entity.getPositionY(), entity.getWidth(), entity.getHeight());			    	
			    }
			    
			    if (owner.settings.debugEntitiesCollisionGeometries.isActive()) {
			    	for( Iterator<CollisionGeometry> itc = entity.listCollisionGeometries(); itc.hasNext();) {
			    		CollisionGeometry collGeo = itc.next();
	
						srenderer.setColor(owner.settings.debugEntitiesCollisionGeometriesColor.get());
//						srenderer.circle(collGeo.getCenterX(), collGeo.getCenterY(), collGeo.getRadius(), 16);
						if (collGeo instanceof CollisionCircle) {
							srenderer.circle(collGeo.getCenterX(), collGeo.getCenterY(), collGeo.getRadius(), 16);
						} else if (collGeo instanceof CollisionBox) {
							CollisionBox collBox = (CollisionBox) collGeo;
							srenderer.rect(collBox.getX(), collBox.getY(), collBox.width, collBox.height);
						} else if (collGeo instanceof CollisionTriangle) {
							CollisionTriangle collT = (CollisionTriangle) collGeo;
							srenderer.triangle(collT.getPoint1_X(), collT.getPoint1_Y(), collT.getPoint2_X(), collT.getPoint2_Y(), collT.getPoint3_X(), collT.getPoint3_Y());
						}
			    	}
			    }
			    
			    if (owner.settings.debugEntitiesPhysicVectors.isActive()) {
			    	float cx = entity.getCenterX();
			    	float cy = entity.getCenterY();
			    	
			    	Vector2 speed = new Vector2(entity.speed);
			    	speed.scl(200);
			    	speed.limit(5);
			    	
			    	srenderer.setColor(owner.settings.debugEntitiesPhysicSpeedVectorColor.get());
			    	ShapeRendererUtil.arrowLine(srenderer, cx, cy, cx + speed.x, cy + speed.y, 0.3f);
			    	
			    	for (Vector2 eacc : entity.accelerations) {
			    		Vector2 acc = new Vector2(eacc);
			    		acc.scl(100*2000);
			    		acc.limit(5);
						
			    		srenderer.setColor(owner.settings.debugEntitiesPhysicAccelerationVectorColor.get());
			    		ShapeRendererUtil.arrowLine(srenderer, cx, cy, cx + acc.x, cy + acc.y, 0.3f);
					}
			    	
			    }
		    }
		}

		srenderer.end();
	}

	@Override
	public void update(float delta) {
		map.update(delta);

		entities.testIntegrity();
		for (Entity entity : entities) {
			entity.update(delta);
		}
		entities.removeDeadEntities();
		addFutureEntities();
		
		updateInput();
		
		onUpdate(delta);
	}

	private void updateInput() {
		if (Gdx.input == null) return;
		
		if (isPointerDown && !Gdx.input.isButtonPressed(Buttons.LEFT)) {
			float px = GetMouseOnMapPositionX();
			float py = GetMouseOnMapPositionY();
			
			for (Entity entity : entities) {
				if (entity.getPositionX() <= px && entity.getPositionY() <= py && entity.getPositionRightX() >= px && entity.getPositionTopY() >= py) {
					entity.onPointerUp();
				}
			}
			
			Tile tile = map.getTileChecked((int)px, (int)py);
			if (tile != null) tile.onPointerUp();
		} else if (!isPointerDown && Gdx.input.isButtonPressed(Buttons.LEFT)) {
			float px = GetMouseOnMapPositionX();
			float py = GetMouseOnMapPositionY();
			
			for (Entity entity : entities) {
				if (entity.getPositionX() <= px && entity.getPositionY() <= py && entity.getPositionRightX() >= px && entity.getPositionTopY() >= py) {
					entity.onPointerDown();
				}
			}
			
			Tile tile = map.getTileChecked((int)px, (int)py);
			if (tile != null) tile.onPointerDown();
		}
		isPointerDown = Gdx.input.isButtonPressed(Buttons.LEFT);
	}

	@Override
	public void onResize() {
		setBoundedOffset(map_offset);
	}

	/**
	 * @return the currently visible tiles (in tile-coordinates : 1 tile = 1 unit)
	 */
	public Rectangle getVisibleMapBox() { //TODO this is called often - cache it ?
		float tilesize = mapScaleResolver.getTileSize(owner.getScreenWidth(), owner.getScreenHeight(), map.height, map.width);
		
		Rectangle view = new Rectangle(map_offset.x, map_offset.y, owner.getScreenWidth() / tilesize, owner.getScreenHeight() / tilesize);
		
		return view;
	}
	
	/**
	 * Fixes the offset - it gets re-adjusted in case the current viewport has left the map
	 */
	private void limitOffset() {
		Rectangle viewport = getVisibleMapBox();
		
		if (viewport.x + viewport.width > map.width) {
			map_offset.x = map.width - viewport.width;
		}
		
		if (viewport.y + viewport.height > map.height) {
			map_offset.y = map.height - viewport.height;
		}
		
		viewport = getVisibleMapBox();
		
		if (viewport.x < 0) {
			map_offset.x = 0;
		}
		
		if (viewport.y < 0) {
			map_offset.y = 0;
		}
	}

	/**
	 * Sets the offset but limits it so that it can't leave the map boundaries
	 * 
	 * @param offset
	 */
	public void setBoundedOffset(Vector2 offset) {
		setRawOffset(offset);

		limitOffset();
	}

	/**
	 * Sets the offset, but unlike setBoundedOffset() it won't check the boundaries
	 * 
	 * @param offset
	 */
	public void setRawOffset(Vector2 offset) {
		this.map_offset = offset;
	}
	
	/**
	 * Sets the mapScaleResolver - the component to determine the size of a tile
	 * 
	 * @param resolver
	 */
	public void setMapScaleResolver(AbstractMapScaleResolver resolver) {
		this.mapScaleResolver = resolver;
	}
	
	/**
	 * return the map offset (in tile coordinates) (offset=1 :=> 1 tile offset)
	 * 
	 * @return the map offset
	 */
	public Vector2 getMapOffset() {
		return map_offset;
	}
	
	/**
	 * return the tile scale (= tile size)
	 * 
	 * @return the tile scale
	 */
	public float getTileScale() {
		return mapScaleResolver.getTileSize(owner.getScreenWidth(), owner.getScreenHeight(), map.height, map.width);
	}
	
	/**
	 * Gets after each update() called
	 * 
	 * @param delta the time since the last update (in ms) - can be averaged over he last few cycles
	 */
	public abstract void onUpdate(float delta);

	/**
	 * return the (tiled) map
	 * 
	 * @return the map
	 */
	public TileMap getMap() {
		return map;
	}
	
	/**
	 * Adds an Entity to the game
	 * 
	 * @param e the to add entity
	 */
	public void addEntity(Entity e) {
		futureEntities.add(e);
	}
	
	protected void addFutureEntities() {
		for (Entity e : futureEntities) {
			entities.add(e);
			
			e.collisionOwner = collisionMap;
			
			e.onLayerAdd(this);			
		}
		futureEntities.clear();
	}
	
	/**
	 * Gets an List of entities to iterate through (DONT CHANGE THE LIST !)
	 * 
	 * @return an iterator over all entities
	 */
	public List<Entity> iterateEntities() {
		return entities;
	}

	/**
	 * Gets the amount of currently registered Entities
	 * 
	 * @return entities.count
	 */
	public int getEntityCount() {
		return entities.size();
	}
	
	/**
	 * Get the collisionMap
	 * 
	 * @return the collision map
	 */
	public CollisionMap getCollisionMap() {
		return collisionMap;
	}
	
	/**
	 * Get the amount of Entities that where rendered last cycle
	 * 
	 * @return amount of rendered entities
	 */
	public int getRenderingEntitiesCount() {
		return renderedEntities;
	}

	/**
	 * Get the X position of the cursor (in map coordinates)
	 * 
	 * @return the X cursor position
	 */
	public float GetMouseOnMapPositionX() {
		Rectangle visible = getVisibleMapBox();
		return visible.x +  (Gdx.input.getX() * visible.width * 1f) / Gdx.graphics.getWidth();
	}

	/**
	 * Get the Y position of the cursor (in map coordinates)
	 * 
	 * @return the Y cursor position
	 */
	public float GetMouseOnMapPositionY() {
		Rectangle visible = getVisibleMapBox();
		return visible.y +  ((Gdx.graphics.getHeight() - Gdx.input.getY()) * visible.height * 1f) / Gdx.graphics.getHeight();
	}
	
	/**
	 * Get the Tile under the cursor
	 * 
	 * @return the tile under the mouse or NULL
	 */
	public Tile getTileUnderMouse() {
		return map.getTileChecked((int)GetMouseOnMapPositionX(), (int) GetMouseOnMapPositionY());
	}
	
	/**
	 * Adds a new background in front of the existing backgrounds
	 * 
	 * (Use the list 'backgrounds' to access the Background-List)
	 * 
	 * @param b the background to add
	 */
	public void addBackground(MapBackground b) {
		this.backgrounds.add(b);
	}
	
	/**
	 * Scrolls the map so that the Entity is visible (with the defined gaps around the borders)
	 * 
	 * @param e the visible entity
	 * @param gap the gap (in tile coordinates) around all 4 edges
	 */
	public void scrollMapToEntity(Entity e, float gap) {
		scrollMapToEntity(e, gap, gap);
	}

	/**
	 * Scrolls the map so that the Entity is visible (with the defined gaps around the borders)
	 * 
	 * @param e the visible entity
	 * @param gapLeftRight  the gap (in tile coordinates) around the two X edges
	 * @param gapTopBottom  the gap (in tile coordinates) around the two Y edges
	 */
	public void scrollMapToEntity(Entity e, float gapLeftRight, float gapTopBottom) {
		scrollMapToEntity(e, gapLeftRight, gapLeftRight, gapTopBottom, gapTopBottom);
	}

	/**
	 * Scrolls the map so that the Entity is visible (with the defined gaps around the borders)
	 * 
	 * @param e the visible entity
	 * @param gapLeft   the gap (in tile coordinates) around the LEFT edge
	 * @param gapRight   the gap (in tile coordinates) around the RIGHT edge
	 * @param gapTop   the gap (in tile coordinates) around the TOP edge
	 * @param gapBottom   the gap (in tile coordinates) around the BOTTOM edge
	 */
	public void scrollMapToEntity(Entity e, float gapLeft, float gapRight, float gapTop, float gapBottom) {
		Rectangle visible = getVisibleMapBox();
		
		boolean leftOut = e.getPositionX() < visible.x + gapLeft;
		boolean rightOut = e.getPositionRightX() > visible.x + visible.width - gapRight;

		boolean bottomOut = e.getPositionY() < visible.y + gapBottom;
		boolean topOut = e.getPositionTopY() > visible.y + visible.height - gapTop;
		
		if (leftOut && rightOut) map_offset.x = e.getCenterX() - visible.width/2;
		else if (leftOut)  map_offset.x = e.getPositionX() - gapLeft;
		else if (rightOut)  map_offset.x = (e.getPositionRightX() + gapRight) - visible.width;
		
		if (bottomOut && topOut) map_offset.y = e.getCenterY() - visible.height/2;
		else if (bottomOut)  map_offset.y = e.getPositionY() - gapBottom;
		else if (topOut)  map_offset.y = (e.getPositionTopY() + gapTop) - visible.height;
		
		setBoundedOffset(map_offset);
	}
	
	/**
	 * Adds CollisionBoxes outside of the map so that no Entities can escape
	 * 
	 * This uses the standard owner 'OuterMapCollisionOwner' as an owner.
	 */
	public void addOuterMapCollisionBoxes() {
		CollisionGeometryOwner owner = new OuterMapCollisionOwner();

		for (int x = -1; x <= map.width; x++) {
			collisionMap.addGeometry(new CollisionBox(owner, x, -0.5f, 1, 1));
			collisionMap.addGeometry(new CollisionBox(owner, x, map.height + 0.5f, 1, 1));
		}
		
		for (int y = 0; y < map.height; y++) {
			collisionMap.addGeometry(new CollisionBox(owner, -0.5f, y, 1, 1));
			collisionMap.addGeometry(new CollisionBox(owner, map.width+0.5f, y, 1, 1));
		}
	}
	
	/**
	 * Adds CollisionBoxes outside of the map so that no Entities can escape
	 * 
	 * @param owner The CollisionGeometryOwner of the collisionBoxes
	 */
	public void addOuterMapCollisionBoxes(CollisionGeometryOwner owner) {
		for (int x = -1; x <= map.width; x++) {
			collisionMap.addGeometry(new CollisionBox(owner, x, -0.5f, 1, 1));
			collisionMap.addGeometry(new CollisionBox(owner, x, map.height + 0.5f, 1, 1));
		}
		
		for (int y = 0; y < map.height; y++) {
			collisionMap.addGeometry(new CollisionBox(owner, -0.5f, y, 1, 1));
			collisionMap.addGeometry(new CollisionBox(owner, map.width+0.5f, y, 1, 1));
		}
	}
}
