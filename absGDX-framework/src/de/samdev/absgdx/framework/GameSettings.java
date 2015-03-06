package de.samdev.absgdx.framework;

import com.badlogic.gdx.graphics.Color;

import de.samdev.absgdx.framework.util.dependentProperties.BooleanProperty;
import de.samdev.absgdx.framework.util.dependentProperties.ColorProperty;
import de.samdev.absgdx.framework.util.dependentProperties.ConstantBooleanProperty;
import de.samdev.absgdx.framework.util.dependentProperties.IntegerProperty;
import de.samdev.absgdx.framework.util.dependentProperties.RootProperty;

/**
 * Here the configurations and settings are saved
 *
 */
public class GameSettings {
	private final static boolean DEBUG_ENABLED = true; // TODO debugmode ( ON | OFF )
	private final static boolean DEBUG_ACTIVE = false;

	// ########################################################

	private RootProperty root;

	private ConstantBooleanProperty debugmode;
	/** Activates/Deactivates all debug features */
	public BooleanProperty debugEnabled;
	/** SHow Debug Text info in the top left corner */
	public BooleanProperty debugTextInfos;
	/** SHow Debug Text info in the top left corner (GameLayer) */
	public BooleanProperty debugGameLayerTextInfos;
	/** SHow Debug Text info in the top left corner (MenuLayer) */
	public BooleanProperty debugMenuLayerTextInfos;
	/** Show FPS */
	public BooleanProperty debugTextFPS;
	/** Show timing information */
	public BooleanProperty debugTextTiming;
	/** Show input information */
	public BooleanProperty debugTextInput;
	/** Show memory information */
	public BooleanProperty debugTextMemory;
	/** Show TileMap information */
	public BooleanProperty debugTextMap;
	/** Show visual information on top of the map */
	public BooleanProperty debugVisualMap;
	/** Show grid lines on top of the map */
	public BooleanProperty debugMapGridLines;
	/** Show visual Information on top of the map */
	public BooleanProperty debugVisualEntities;
	/** Show boundary boxes for Entities */
	public BooleanProperty debugEntitiesBoundingBoxes;
	/** Show the collision Geometries */
	public BooleanProperty debugEntitiesCollisionGeometries;
	/** Show textual information about the entities */
	public BooleanProperty debugTextEntities;
	/** The size of the debug text  */
	public IntegerProperty debugTextSize;
	/** The color of the MapGridLines */
	public ColorProperty debugMapGridLinesColor;
	/** The color of the bounding boxes */
	public ColorProperty debugEntitiesBoundingBoxesColor;
	/** The color of the CollisionGeometries */
	public ColorProperty debugEntitiesCollisionGeometriesColor;
	/** Mark Tiles where CollisionGeometries are registered */
	public BooleanProperty debugCollisionMapMarkers;
	/** Show informations about the collisionGeometries and the collisionMap  */
	public BooleanProperty debugTextCollisionGeometries;
	/** Show they physical vectors of Entities (speed / acceleration)  */
	public BooleanProperty debugEntitiesPhysicVectors;
	/** The color of the speed vector of an entity  */
	public ColorProperty debugEntitiesPhysicSpeedVectorColor;
	/** The color of the acceleration vector of an entity  */
	public ColorProperty debugEntitiesPhysicAccelerationVectorColor;
	/** Show visual information in the MenuLayers  */
	public BooleanProperty debugMenu;
	/** Show the actual Dimensions of MenuElements  */
	public BooleanProperty debugMenuBorders;
	/** The debug border color of MenuElements  */
	public ColorProperty debugMenuBordersColor;
	/** The debug border color of inner MenuElements  */
	public ColorProperty debugMenuBordersColorL2;
	/** Show generic MenuElement info's */
	public BooleanProperty debugElementInfo;
	/** Show the MenuElement dimensions */
	public BooleanProperty debugElementBoundaries;
	/** Show special MenuElement attributes */
	public BooleanProperty debugElementAttributes;
	/** Show the animation progress in MenuImages */
	public BooleanProperty debugMenuImageAnimation;
	
	/**
	 * Creates a new instance of GameSettings
	 */
	public GameSettings() {
		super();

		init();
	}

	private void init() {
		root = new RootProperty("absGDX");
		{
			debugmode = new ConstantBooleanProperty("debugmode", DEBUG_ENABLED, root);
			{
				debugEnabled = new BooleanProperty("debugEnabled", DEBUG_ACTIVE, debugmode);
				{
					debugTextInfos = new BooleanProperty("debugTextInfos", true, debugEnabled);
					{
						debugGameLayerTextInfos = new BooleanProperty("debugGameLayerTextInfos", true, debugTextInfos);
						{
							debugTextSize = new IntegerProperty("debugTextSize", 1, debugGameLayerTextInfos);
							
							debugTextFPS = new BooleanProperty("debugTextFPS", true, debugGameLayerTextInfos);
							debugTextTiming = new BooleanProperty("debugTextTiming", true, debugGameLayerTextInfos);
							debugTextMemory = new BooleanProperty("debugTextMemory", true, debugGameLayerTextInfos);
							debugTextMap = new BooleanProperty("debugTextMap", true, debugGameLayerTextInfos);
							debugTextEntities = new BooleanProperty("debugTextEntities", true, debugGameLayerTextInfos);
							debugTextCollisionGeometries = new BooleanProperty("debugTextCollisionGeometries", true, debugGameLayerTextInfos);
							debugTextInput = new BooleanProperty("debugTextInput", true, debugGameLayerTextInfos);
						}

						debugMenuLayerTextInfos = new BooleanProperty("debugMenuLayerTextInfos", true, debugTextInfos);
						{
							debugElementInfo = new BooleanProperty("debugElementInfo", true, debugMenuLayerTextInfos);
							debugElementBoundaries = new BooleanProperty("debugElementBoundaries", true, debugMenuLayerTextInfos);
							debugElementAttributes = new BooleanProperty("debugElementAttributes", true, debugMenuLayerTextInfos);
						}
					}

					debugVisualMap = new BooleanProperty("debugVisualMap", true, debugEnabled);
					{
						debugMapGridLines = new BooleanProperty("debugMapGridLines", true, debugVisualMap);
						{
							debugCollisionMapMarkers = new BooleanProperty("debugCollisionMapMarkers", true, debugMapGridLines);
							debugMapGridLinesColor = new ColorProperty("debugMapGridLinesColor", Color.MAGENTA, debugMapGridLines);
						}
					}
					
					debugVisualEntities = new BooleanProperty("debugEntities", true, debugEnabled);
					{
						debugEntitiesBoundingBoxes = new BooleanProperty("debugEntitiesBoundingBoxes", true, debugVisualEntities);
						{
							debugEntitiesBoundingBoxesColor = new ColorProperty("debugEntitiesBoundingBoxesColor", Color.RED, debugEntitiesBoundingBoxes);
						}
						
						debugEntitiesCollisionGeometries = new BooleanProperty("debugEntitiesCollisionGeometries", true, debugVisualEntities);
						{
							debugEntitiesCollisionGeometriesColor = new ColorProperty("debugEntitiesCollisionGeometriesColor", Color.BLUE, debugEntitiesCollisionGeometries);
						}
						
						debugEntitiesPhysicVectors = new BooleanProperty("debugEntitiesPhysicVectors", true, debugVisualEntities);
						{
							debugEntitiesPhysicSpeedVectorColor = new ColorProperty("debugEntitiesPhysicSpeedVectorColor", Color.RED, debugEntitiesPhysicVectors);
							debugEntitiesPhysicAccelerationVectorColor = new ColorProperty("debugEntitiesPhysicAccelerationVectorColor", Color.BLUE, debugEntitiesPhysicVectors);
						}
					}

					debugMenu = new BooleanProperty("debugMenu", true, debugEnabled);
					{
						debugMenuBorders = new BooleanProperty("debugMenuBorders", true, debugMenu);
						{
							debugMenuBordersColor = new ColorProperty("debugMenuBordersColor", Color.MAGENTA, debugMenuBorders);
							debugMenuBordersColorL2 = new ColorProperty("debugMenuBordersColorL2", Color.CYAN, debugMenuBorders);
						}

						debugMenuImageAnimation = new BooleanProperty("debugMenuImageAnimation", true, debugMenu);
					}
				}
			}
		}
		
		// ###########################

		//System.out.println(root.getTreeDebugString());
	}

}
