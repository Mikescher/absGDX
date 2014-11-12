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
	private final static boolean DEBUG_ACTIVE = true;

	// ########################################################

	private RootProperty root;

	private ConstantBooleanProperty debugmode;
	/** Activates/Deactivates all debug features */
	public BooleanProperty debugEnabled;
	/** SHow Debug Text info in the top left corner */
	public BooleanProperty debugTextInfos;
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
						debugTextSize = new IntegerProperty("debugTextSize", 1, debugTextInfos);
						
						debugTextFPS = new BooleanProperty("debugTextFPS", true, debugTextInfos);
						debugTextTiming = new BooleanProperty("debugTextTiming", true, debugTextInfos);
						debugTextMemory = new BooleanProperty("debugTextMemory", true, debugTextInfos);
						debugTextMap = new BooleanProperty("debugTextMap", true, debugTextInfos);
						debugTextEntities = new BooleanProperty("debugTextEntities", true, debugTextInfos);
						debugTextCollisionGeometries = new BooleanProperty("debugTextCollisionGeometries", true, debugTextInfos);
						debugTextInput = new BooleanProperty("debugTextInput", true, debugTextInfos);
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
				}

			}
		}
		// ###########################

//		System.out.println(root.getTreeDebugString());
	}

}
