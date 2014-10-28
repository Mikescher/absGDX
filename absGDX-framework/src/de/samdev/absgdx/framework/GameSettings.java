package de.samdev.absgdx.framework;

import de.samdev.absgdx.framework.util.dependentProperties.BooleanProperty;
import de.samdev.absgdx.framework.util.dependentProperties.ConstantBooleanProperty;
import de.samdev.absgdx.framework.util.dependentProperties.RootProperty;

public class GameSettings {
	private final static boolean DEBUG_ENABLED = true; // TODO debugmode ( ON | OFF )
	private final static boolean DEBUG_ACTIVE = true;

	// ########################################################

	private RootProperty root;

	private ConstantBooleanProperty debugmode;
	public BooleanProperty debugEnabled;
	public BooleanProperty debugTextInfos; // TopLeft Debug Text
	public BooleanProperty debugTextFPS;
	public BooleanProperty debugTextTiming;
	public BooleanProperty debugTextMemory;
	public BooleanProperty debugVisualMap;
	public BooleanProperty debugMapGridLines;
	public BooleanProperty debugEntities;

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
					}

					debugVisualMap = new BooleanProperty("debugVisualMap", true, debugEnabled);
					{
						debugMapGridLines = new BooleanProperty("debugMapGridLines", true, debugVisualMap);
					}
					
					debugEntities = new BooleanProperty("debugEntities", true, debugEnabled);
				}

			}
		}
		// ###########################

		// System.out.println(root.getTreeDebugString());
	}

}
