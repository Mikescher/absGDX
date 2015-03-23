absGDX - Quick Guide (First Steps)
==================================

In this short guide you will learn the basic skill set to develop a mobile game with absGDX.

This guide is nowhere near complete - it is only a starting point for your development. If you want to get deeper into absGDX look at the javadoc, the example programs and perhaps the (documented) source code.


##Set up 

First download the LibGDX Setup app from their [website](http://libgdx.badlogicgames.com/). Create a LibGDX project with the sub projects `Desktop` and `Android`.

Then download the `absGDX.jar` from the [github repository](https://github.com/Mikescher/absGDX) and put it into the LibGDX project folder *(I put it into an sub-folder named "lib")*.

Now you need to add the absGDX dependency to our core project, open the `build.gradle` file in the project root folder and add to the core project a new dependency:

~~~groovy
project(":core") {
    apply plugin: "java"

    dependencies {
        compile "com.badlogicgames.gdx:gdx:$gdxVersion"
        compile "com.badlogicgames.gdx:gdx-box2d:$gdxVersion"
    	compile files("lib/absGDX-framework-??.??.jar")
    }
}
~~~

Now execute `gradlew cleanEclipse eclipse` to generate the new eclipse project files or `gradlew cleanIdea idea` to generate IntelliJ Idea project files.

You can now program your project on the desktop (and also execute it there). If you want to build it for android run `gradlew android:assembleRelease`

## absGDX Basics

absGDX is only an extension to LibGDX, so your code will still be in the core project. It is generally recommended to have at least a little bit of knowledge of LibGDX.

For the basics you need *(at least)* these three files:

 - A font-file to display the debugtext (you can use the consolefont.fnt from this [repository](https://github.com/Mikescher/absGDX/tree/master/android/assets)). Put this file in the `android/assets` folder to put it into the assembly resources
 - A `Textures.java` file to organize and load all your texture-assets:
~~~java
public final class Textures {
	public static Texture exampleTex;
	public static TextureRegion[][] example;

	public static void init() {
		exampleTex = new Texture("example.png");

		example = TextureHelper.load2DArray(exampleTex, 64, 32);
	}
~~~
 - A class derived from `AgdxGame`. This will be your application object and everything you will do happens inside of this class:
~~~java
public class DemoGame extends AgdxGame {	
	@Override
	public void onCreate() {
		Textures.init();
		
		setDebugFont(new BitmapFont(Gdx.files.internal("consolefont.fnt")));
	}

	@Override
	public void onUpdate(float delta) {
		// Show debug stuff via [F1]
		if (Gdx.input.isKeyJustPressed(Keys.F1)) settings.debugEnabled.doSwitch();
	}
}
~~~

## A top-down game

Now to the basic game building. Derive a class from `GameLayer` for your game and push this layer in the AgdxGame constructor onto the layer stack *(the layers are organized in an stack - only the top-most layer will be active)*

~~~java
public void onCreate() {
	Textures.init();
	
	pushLayer(new TDLayer(this));
	
	setDebugFont(new BitmapFont(Gdx.files.internal("consolefont.fnt")));
}
~~~

### The map

A game is build out of tiles *(= maptiles)* and entities. You need to supply the GameLayer super-constructor with the map, either you create the map manually in the code or you load it from the resources.

First the generation via code:

~~~java
public TDLayer(AgdxGame owner) {
	super(owner, loadMap());
}

private static TileMap loadMap() {
	TileMap t = TileMap.createEmptyMap(16, 16);

	for (int x = 0; x < 16; x++) {
		for (int y = 0; y < 16; y++) {
			t.setTile(x, y, new TDTile());
		}
	}
}
~~~

This is not really feasible for bigger maps, so you can also create you maps with the [Tiled Map Editor](http://www.mapeditor.org/) and save them as tmx-files in the assets folder.

Now you need to map the TMX-GID's to different TileClasses. But if you have a lot of different tiles with no special functionality (except the different textures) you can add a default mapping. And if you use an AutoTile as the fallback value, it will automatically find its textures from an provided tileset (the same as you used in Tiled)

**loadMap:**

~~~java
private static TileMap loadMap() {
	TmxMapLoader loader = new TmxMapLoader(Gdx.files.internal("demomap.tmx"));
	
	loader.addMapping(1290, MyTile.class);
	loader.addMapping(1291, OtherTile.class);
	loader.addMapping(1258, PlayerTile.class);
	
	loader.addDefaultMapping(MyAutoTile.class);
	
	try {
		return loader.start();
	} catch (TmxMapParsingException e) {
		e.printStackTrace();
		return null;
	}
}
~~~

**MyAutoTile.java**

~~~java
public class MyAutoTile extends AutoTile {
	public MyAutoTile(HashMap<String, String> properties) {
		super(Textures.texmap, 16, 16, properties);
	}

	@Override
	public void update(float delta) {
		// NOP
	}

	@Override
	public boolean canMoveCollideWith(CollisionGeometryOwner other) {
		return false;
	}
}
~~~

**MyTile.java**

~~~java
public class MyTile extends Tile {
	public MyTile() {
		super(Textures.tex_mytile);
	}

	@Override
	public void update(float delta) {
		// NOP
	}

	@Override
	public boolean canMoveCollideWith(CollisionGeometryOwner other) {
		return false;
	}
}
~~~

If you want to only partially show the map you need to set the MapScaleResolver:

~~~java
	public TDLayer(AgdxGame owner) {
		super(owner, loadMap());

		setMapScaleResolver(new SectionMapScaleResolver(32, 18, 0.5f, 20f));
	}
~~~

The MapScaleResolver determines how much of the Map is shown, you can either define your own, or use one of the premade ones:

 - **FixedMapScaleResolver:** Use a fixed size
 - **ShowCompleteMapScaleResolver:** Always show the complete map
 - **MaximumBoundaryMapScaleResolver:** Always show at least n tiles, if the aspect ratio forces it show more ... but never less
 - **MinimumBoundaryMapScaleResolver:** Never show a more than tiles, if the aspect ratio forces it show less ... but never more
 - **LimitedMinimumBoundaryMapScaleResolver:** A MinimumBoundaryMapScaleResolver, but it always show at least x% of the boundary
 - **SectionMapScaleResolver** A LimitedMinimumBoundaryMapScaleResolver, but with an fixed minimum size of a single tile.

For more information you can look at the documentary of these classes. In our example we never show more than 32x18 tiles. We never cut away more than 50% of this boundary and one tile is at least 20x20 pixel in size.

### The entities

Tiles are pretty static, to have dynamic objects you need to add entities to your gameworld. To create entities derive fromthe class `Entity`.

~~~java
public class MyEntity extends Entity {
	public MyEntity() {
		super(Textures.tex_entity, 2, 2);

		setPosition(7, 7);
	}

	@Override
	public void onLayerAdd(GameLayer layer) {}
	
	@Override
	public void beforeUpdate(float delta) {}
}
~~~

And to add the Entity call the `addEntity()` method on the GameLayer:

~~~java
public TDGameLayer(AgdxGame owner) {
	super(owner, loadMap());

	setMapScaleResolver(new SectionMapScaleResolver(32, 18, 0.5f, 20f));

	addEntity(new MyEntity());
}
~~~

Entities have an integrated frame-independent movement system. You can set their speed an acceleration and their movement is calculated by the framework:

~~~java
private Vector2 acceleration = addNewAcceleration();

public MyEntity() {
	super(Textures.tex_entity, 2, 2);

	setPosition(7, 7);
}

@Override
public void onLayerAdd(GameLayer layer) {
	speed.set(0.001f, 0);
}

@Override
public void beforeUpdate(float delta) {
	if (getPositionX() < 20)
		acceleration.set(0.00001f, 0f);
	else
		acceleration.setZero();
}
~~~

Entities have a lot of different properties and features, it is recommended to read the documentary for this class *(an the other ones you want to use)*. Here a few examples:

You can provide the entity an animation instead of an static texture:

~~~java
super(Textures.tex_animation, 500f, 2, 2.53f); // tex_animation is an array
~~~

You can dynamically scale/rotate/flip the texture

~~~java
@Override
public float getTextureScaleX() { 
	return 1f;
}

@Override
public float getTextureScaleY() {
	return -1f;
}

@Override
public float getTextureRotation() {
	return 45;
}
~~~

You can automatically scroll the map to an entity (so this entity is on-screen)

~~~java
public class TDGameLayer extends GameLayer {

	/*  ...  */
	
	@Override
	public void onUpdate(float delta) {
		scrollMapToEntity(myEntity, 2);
	}
}
~~~

### Collision Detection

absGDX features an own collision-detection and movement-collision system.

In every tile and entity you have the methods `canCollideWith(CollisionGeometryOwner other)` and `canMoveCollideWith(CollisionGeometryOwner other)`. With `canCollideWith` you set determine if two objects can generally collide with each other *(= throw the collision event)* and with `canMoveCollideWith` you determine if two objects can move into each other. If they can't then the object will stop when they "crash" into each other. It is your responsibility to assure that `a.canCollideWith(b) == b.canCollideWith(a)`, otherwise strange and unwanted behaviour can occur.

Tiles have an rectangular collisionGeometry by default, entities have no collision-geometry by default. To add a new geometry call the `addGeometry()` method. There are three different types of geometries:

 - CollisionRectangle
 - CollisionCircle
 - CollisionTriangle

One Entity can have multiple geometries, and by combination you can create arbitrary complex collision models.

If you want only a simple geometry use teh helper method `addFullCollisionBox()`, `addFullCollisionCircle` or `addFullCollisionTriangle`:

~~~java
public class MyEntity extends Entity {
	public MyEntity() {
		super(Textures.tex_entity, 2, 2);

		setPosition(7, 7);
		addFullCollisionBox();
	}

	@Override
	public void onLayerAdd(GameLayer layer) {}
	
	@Override
	public void beforeUpdate(float delta) {}
}
~~~

The collision events are provided through methods in the Tile and Entity class. If an collision occurs between two objects on the source object the *active* method is called and on the other the *passive* method. Also if it is an movement collision (`canMoveCollideWith == true`) the method with the suffix `Movement` is called:

~~~java
	@Override
	public void onActiveCollide(CollisionGeometryOwner passiveCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
		// COLLISION
	}

	@Override
	public void onPassiveCollide(CollisionGeometryOwner activeCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
		// COLLISION
	}

	@Override
	public void onActiveMovementCollide(CollisionGeometryOwner passiveCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
		// COLLISION
	}

	@Override
	public void onPassiveMovementCollide(CollisionGeometryOwner activeCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
		// COLLISION
	}
~~~

## A sidescroller

It looks like a sidescroller should be generally different from a top-down game. But everything stays the same except you have gravity on your entities.
To have objects with gravity derive your entities from `PhysicsEntity` and set their mass to any value except zero.

~~~java
public class MyEntity2 extends PhysicsEntity {
	public MyEntity2() {
		super(Textures.tex_entity, 2, 2);

		setPosition(x, y);
	}

	@Override
	public void onLayerAdd(GameLayer layer) {
		addFullCollisionBox();
		setMass(10);
	}
}
~~~

The gravity is just an constant acceleration downwards with `mass * PhysicsEntity.GRAVITY_CONSTANT`

The other important thing about an sidescroller is  that most tiles should be impassable by the player-entity (`canMoveCollideWith() == true`). So the player and NPC entities actually collide with the floor etc.

## A simple menu

In addition to the concrete game you will need most of the time an menu to display before and after the actual gameplay.

### A manual menu

If you derive your layer from `MenuLayer` you get an Menu. A Menu is build from multiple MenuElements *(all elements have the base class MenuBaseElement)*.

The framework provides you with an basic set of elements (buttons, edits, panels etc). Every MenuLayer has the root element, you can start adding your elements on this root element:

~~~java
public class DemoMenu extends MenuLayer {
	public ManualMenu(AgdxGame owner) {
		super(owner, new BitmapFont(Gdx.files.internal("consolefont.fnt")));

		final MenuLabel l = new MenuLabel();
		l.setBoundaries(75, 30, 450, 40);
		l.setContent("menu demo");
		getRoot().addChildren(l);

		final MenuRadioButton rb1 = new MenuRadioButton(prov);
		rb1.setBoundaries(25, 145, 250, 30);
		rb1.setContent("Option 1");
		getRoot().addChildren(rb1);

		final MenuRadioButton rb2 = new MenuRadioButton(prov);
		rb2.setBoundaries(25, 185, 250, 30);
		rb2.setContent("Option 2");
		getRoot().addChildren(rb2);
		
		final MenuButton bnext = new MenuButton(prov);
		bnext.setBoundaries(400, 25, 175, 40);
		bnext.setContent("example");
		bnext.setColor(Color.WHITE);
		bnext.addButtonListener(new MenuButtonListener() {
			@Override
			public void onClicked(MenuBaseElement element, String identifier) { System.out.println("TRIGGERED"); }
		});
		getRoot().addChildren(bnext);
	}

	@Override
	public void onResize() {
		getRoot().setWidth(owner.getScreenWidth());
		getRoot().setHeight(owner.getScreenHeight());
		
		root.setPosition((getRoot().getWidth() - root.getWidth())/2, (getRoot().getHeight() - root.getHeight())/2);
	}
}
~~~

Most elements need an set of textures to display them. For this purpose you can give every element an `GUITextureProvider` which contains the needed texture (for example from an UI Kit).

~~~java
GUITextureProvider prov = new GUITextureProvider();

prov.setMenuButtonTexture(Textures.tex_buttongui[0], VisualButtonState.NORMAL);
prov.setMenuButtonTexture(Textures.tex_buttongui[1], VisualButtonState.HOVERED);
prov.setMenuButtonTexture(Textures.tex_buttongui[2], VisualButtonState.PRESSED);
prov.setMenuButtonTexture(Textures.tex_buttongui[3], VisualButtonState.DISABLED);

prov.setMenuPanelTexture(Textures.tex_panelgui);

prov.setMenuEditTexture(Textures.tex_textfield, false);
prov.setMenuEditTexture(Textures.tex_textfield_focus, true);
~~~

You should set the needed textures for every component you use *(see javadoc)*, otherwise the default style will be used.

### An AGDXML menu

Instead of defining the menu layout in code, with a lot of instructions, you can create an AGDXML file as an layout definition and load it from the assets.

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<frame textures="provider1" >
	<grid container="true">
		<grid.columndefinitions width="20, 2*, 10, 1*, 20" />
		<grid.rowdefinitions   height="8%, 40, 4%, 1*, 4%" />
		
		<label grid.row="1" grid.column="1" content="read me"/>
		<image grid.row="3" grid.column="3" texture="ani_01" animation="750" id="myImage001"/>
		<edit grid.row="3" grid.column="1" text="edit me" textColor="BLACK" halign="LEFT" />
		<button grid.row="2" gird.column="3" text="click me" onCLicked="onBtnClicked" />
	</grid>
</frame>
~~~

For simpler usage you can use the menudesigner tool (int this repository) to create these files. It shows you syntactical errors and provides a live preview of your layout. It also displays the possible tags and parameters.

On other big advantage of this method is that you can make dynamic layout that automatically fit into different screen sizes. In the above example you can see a panel-definition that is grid-seperated. The sub-components are aligned at these rows and columns. *(For more information see the AGDXML documentation and teh menudesigner)*.

To load an AGDXML file you need to derivate your MenuLayer from the class `AgdxmlLayer`. Be aware that you still need to provide an GUITextureProvider to show the elements with their respective textures:

~~~java
public class DemoMenu extends AgdxmlLayer {

	public DemoMenu(AgdxGame owner) throws AgdxmlParsingException {
		super(owner, new BitmapFont(Gdx.files.internal("consolefont.fnt")), Gdx.files.internal("demomenu.agdxml"));
	}

	@Override
	public void initialize() {
		GUITextureProvider prov = new GUITextureProvider();
		/* ... */
		
		addAgdxmlGuiTextureProvider("provider1", prov);
		addAgdxmlImageTexture("ani_01", Textures.tex_animation);
	}

	@SuppressWarnings("unused") // event listener
	public void onBtnClicked(MenuBaseElement element, String identifier) {
		System.out.println("button clicked");
	}
~~~