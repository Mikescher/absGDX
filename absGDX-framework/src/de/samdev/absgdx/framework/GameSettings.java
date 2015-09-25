package de.samdev.absgdx.framework;

import com.badlogic.gdx.graphics.Color;

import de.samdev.absgdx.framework.util.dependentProperties.BooleanProperty;
import de.samdev.absgdx.framework.util.dependentProperties.ColorProperty;
import de.samdev.absgdx.framework.util.dependentProperties.ConstantBooleanProperty;
import de.samdev.absgdx.framework.util.dependentProperties.ConstantStringProperty;
import de.samdev.absgdx.framework.util.dependentProperties.IntegerProperty;
import de.samdev.absgdx.framework.util.dependentProperties.RootProperty;

/**
 * Here the configurations and settings are saved
 *
 */
public class GameSettings {
	private final static boolean DEBUG_ENABLED = true; // DEBUGMODE GLOBAL ( ON | OFF )
	private final static boolean DEBUG_ACTIVE = false;

	// ########################################################

	/** the root node */
	public RootProperty root;

	/** the framework version */
	public ConstantStringProperty versionNumber;

	private ConstantBooleanProperty debugmode;
	/** Activates/Deactivates all debug features */
	public BooleanProperty debugEnabled;
	/** Show Debug Text info in the top left corner */
	public BooleanProperty debugTextInfos;
	/** Show Debug Text info in the top left corner (GameLayer) */
	public BooleanProperty debugGameLayerTextInfos;
	/** Show Debug Text info in the top left corner (MenuLayer) */
	public BooleanProperty debugMenuLayerTextInfos;
	/** Show Debug Text info in the top left corner (GameLayerMenu) */
	public BooleanProperty debugGameLayerMenuTextInfos;
	/** Show FPS */
	public BooleanProperty debugTextFPS;
	/** Show timing information */
	public BooleanProperty debugTextTiming;
	/** Show input information */
	public BooleanProperty debugTextInput;
	/** Show memory information */
	public BooleanProperty debugTextMemory;
	/** Show render information */
	public BooleanProperty debugTextRenderCalls;
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
	public BooleanProperty debugVisualMenu;
	/** Show the actual Dimensions of MenuElements  */
	public BooleanProperty debugMenuBorders;
	/** The debug border color of MenuElements  */
	public ColorProperty debugMenuBordersColor;
	/** The debug border color of inner MenuElements  */
	public ColorProperty debugMenuBordersColorL2;
	/** Show MenuLayers MenuElement info's */
	public BooleanProperty debugMenuLayerElementInfo;
	/** Show MenuLayers MenuElement dimensions */
	public BooleanProperty debugMenuLayerElementBoundaries;
	/** Show MenuLayers MenuElement special attributes */
	public BooleanProperty debugGameLayerMenuElementAttributes;
	/** Show MenuLayers GameLayerMenu info's */
	public BooleanProperty debugGameLayerMenuElementInfo;
	/** Show MenuLayers GameLayerMenu dimensions */
	public BooleanProperty debugGameLayerMenuElementBoundaries;
	/** Show MenuLayers GameLayerMenu special attributes */
	public BooleanProperty debugMenuLayerElementAttributes;
	/** Show the animation progress in MenuImages */
	public BooleanProperty debugMenuImageAnimation;
	/** Show Entity informations directly on the entity */
	public BooleanProperty debugInlineEntityInformation;
	/** Show the UUID for entities */
	public BooleanProperty debugEntitiesUniqueID;
	/** The Color of the entities UUID */
	public ColorProperty debugEntitiesUniqueIDColor;
	/** Show the entity position */
	public BooleanProperty debugEntitiesPosition;
	/** The color of the entity position */
	public ColorProperty debugEntitiesPositionColor;
	/** Show the entity speed */
	public BooleanProperty debugEntitiesSpeed;
	/** The color of the entity speed */
	public ColorProperty debugEntitiesSpeedColor;
	/** Track FPS, RenderTime, UpdateTime etc when debugEnabled = false */
	public BooleanProperty debugBackgroundFPSCapture;
	/** Show warning if the delta Time is greater AgdxGame.MAX_UPDATE_DELTA  */
	public BooleanProperty warnOnDeltaExplode;
	/** The amount consecutive delta trims to trigger an warning  */
	public IntegerProperty warnOnDeltaExplodeBuffer;
	
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
			versionNumber = new ConstantStringProperty("versionNumber", "1.1", root);
			
			warnOnDeltaExplode = new BooleanProperty("warnOnDeltaExplode", true, root);
			{
				warnOnDeltaExplodeBuffer = new IntegerProperty("warnOnDeltaExplodeBuffer", 5, warnOnDeltaExplode);
			}
			
			debugmode = new ConstantBooleanProperty("debugmode", DEBUG_ENABLED, root);
			{
				debugBackgroundFPSCapture = new BooleanProperty("backgroundFPSCapture", true, debugmode);
				
				debugEnabled = new BooleanProperty("debugEnabled", DEBUG_ACTIVE, debugmode);
				{
					debugTextInfos = new BooleanProperty("debugTextInfos", true, debugEnabled);
					{
						debugTextSize = new IntegerProperty("debugTextSize", 1, debugTextInfos);
						
						debugTextFPS = new BooleanProperty("debugTextFPS", true, debugTextInfos);
						debugTextTiming = new BooleanProperty("debugTextTiming", true, debugTextInfos);
						debugTextMemory = new BooleanProperty("debugTextMemory", true, debugTextInfos);
						debugTextInput = new BooleanProperty("debugTextInput", true, debugTextInfos);
						debugTextRenderCalls = new BooleanProperty("debugTextRenderCalls", true, debugTextInfos);
						
						debugGameLayerTextInfos = new BooleanProperty("debugGameLayerTextInfos", true, debugTextInfos);
						{
							debugTextMap = new BooleanProperty("debugTextMap", true, debugGameLayerTextInfos);
							debugTextEntities = new BooleanProperty("debugTextEntities", true, debugGameLayerTextInfos);
							debugTextCollisionGeometries = new BooleanProperty("debugTextCollisionGeometries", true, debugGameLayerTextInfos);
						}

						debugMenuLayerTextInfos = new BooleanProperty("debugMenuLayerTextInfos", true, debugTextInfos);
						{
							debugMenuLayerElementInfo = new BooleanProperty("debugMenuLayerElementInfo", true, debugMenuLayerTextInfos);
							debugMenuLayerElementBoundaries = new BooleanProperty("debugMenuLayerElementBoundaries", true, debugMenuLayerTextInfos);
							debugMenuLayerElementAttributes = new BooleanProperty("debugMenuLayerElementAttributes", true, debugMenuLayerTextInfos);
						}

						debugGameLayerMenuTextInfos = new BooleanProperty("debugGameLayerMenuTextInfos", true, debugTextInfos);
						{
							debugGameLayerMenuElementInfo = new BooleanProperty("debugGameLayerMenuElementInfo", true, debugGameLayerMenuTextInfos);
							debugGameLayerMenuElementBoundaries = new BooleanProperty("debugGameLayerMenuElementBoundaries", true, debugGameLayerMenuTextInfos);
							debugGameLayerMenuElementAttributes = new BooleanProperty("debugGameLayerMenuElementAttributes", true, debugGameLayerMenuTextInfos);
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

						debugInlineEntityInformation = new BooleanProperty("debugInlineEntityInformation", true, debugVisualEntities);
						{
							debugEntitiesUniqueID = new BooleanProperty("debugEntitiesUniqueID", true, debugInlineEntityInformation);
							{
								debugEntitiesUniqueIDColor = new ColorProperty("debugEntitiesUniqueIDColor", Color.BLUE, debugEntitiesUniqueID);
							}

							debugEntitiesPosition = new BooleanProperty("debugEntitiesPosition", true, debugInlineEntityInformation);
							{
								debugEntitiesPositionColor = new ColorProperty("debugEntitiesPositionColor", Color.TEAL, debugEntitiesPosition);
							}

							debugEntitiesSpeed = new BooleanProperty("debugEntitiesSpeed", true, debugInlineEntityInformation);
							{
								debugEntitiesSpeedColor = new ColorProperty("debugEntitiesSpeedColor", Color.RED, debugEntitiesSpeed);
							}
						}
					}

					debugVisualMenu = new BooleanProperty("debugVisualMenu", true, debugEnabled);
					{
						debugMenuBorders = new BooleanProperty("debugMenuBorders", true, debugVisualMenu);
						{
							debugMenuBordersColor = new ColorProperty("debugMenuBordersColor", Color.MAGENTA, debugMenuBorders);
							debugMenuBordersColorL2 = new ColorProperty("debugMenuBordersColorL2", Color.CYAN, debugMenuBorders);
							debugMenuImageAnimation = new BooleanProperty("debugMenuImageAnimation", true, debugMenuBorders);
						}
					}
				}
			}
		}
		
		// ###########################

		//System.out.println(root.getTreeDebugString());
	}

}
