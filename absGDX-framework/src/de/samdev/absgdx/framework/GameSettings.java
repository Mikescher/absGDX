package de.samdev.absgdx.framework;

import de.samdev.absgdx.framework.util.dependentProperties.BooleanProperty;
import de.samdev.absgdx.framework.util.dependentProperties.ConstantBooleanProperty;
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
	public BooleanProperty debugEntitiesGridLines;

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
						debugTextFPS = new BooleanProperty("debugTextFPS", true, debugTextInfos);
						debugTextTiming = new BooleanProperty("debugTextTiming", true, debugTextInfos);
						debugTextMemory = new BooleanProperty("debugTextMemory", true, debugTextInfos);
						debugTextMap = new BooleanProperty("debugTextMap", true, debugTextMap);
						debugTextInput = new BooleanProperty("debugTextInput", true, debugTextMap);
					}

					debugVisualMap = new BooleanProperty("debugVisualMap", true, debugEnabled);
					{
						debugMapGridLines = new BooleanProperty("debugMapGridLines", true, debugVisualMap);
					}
					
					debugVisualEntities = new BooleanProperty("debugEntities", true, debugEnabled);
					{
						debugEntitiesGridLines = new BooleanProperty("debugEntitiesGridLines", true, debugVisualEntities);
					}
				}

			}
		}
		// ###########################

		// System.out.println(root.getTreeDebugString());
	}

}
