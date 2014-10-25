package de.samdev.absgdx.framework;

import de.samdev.absgdx.framework.util.dependentProperties.BooleanProperty;
import de.samdev.absgdx.framework.util.dependentProperties.ConstantBooleanProperty;
import de.samdev.absgdx.framework.util.dependentProperties.RootProperty;

public class GameSettings {
	public RootProperty root;
	
	public ConstantBooleanProperty debugmode;
	public BooleanProperty debugEnabled;
	public BooleanProperty debugTextInfos; // TopLeft Debug Txt
	public BooleanProperty debugVisualMap;
	public BooleanProperty debugMapGridLines;
	public BooleanProperty debugEntities;
	
	public GameSettings() {
		super();
		
		init();
	}
	
	private void init() {
		root = new RootProperty("absGDX");
		
		debugmode = new ConstantBooleanProperty("debugmode", true, root); // TODO debugmode ( ON | OFF )
		
		debugEnabled = new BooleanProperty("debugEnabled", false, debugmode);
		
		debugTextInfos = new BooleanProperty("debugTextInfos", true, debugEnabled);
		
		debugVisualMap = new BooleanProperty("debugVisualMap", true, debugEnabled);
		debugEntities = new BooleanProperty("debugEntities", true, debugEnabled);
		
		debugMapGridLines = new BooleanProperty("debugMapGridLines", true, debugVisualMap);
		
		//###########################
		
		//System.out.println(root.getTreeDebugString());
	}

}
