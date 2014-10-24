package de.samdev.absgdx.framework;

import de.samdev.absgdx.framework.util.dependentProperties.BooleanProperty;
import de.samdev.absgdx.framework.util.dependentProperties.ConstantBooleanProperty;
import de.samdev.absgdx.framework.util.dependentProperties.RootProperty;

public class GameSettings {

	public RootProperty root;
	
	public ConstantBooleanProperty debugmode;
	public BooleanProperty debugEnabled;
	public BooleanProperty debugTextLog;
	public BooleanProperty debugVisualMap;
	public BooleanProperty debugMapGridLines;
	public BooleanProperty debugVisualEntities;
	
	public GameSettings() {
		super();
		
		init();
	}
	
	private void init() {
		root = new RootProperty("absGDX");
		
		debugmode = new ConstantBooleanProperty("debugmode", true, root); // TODO debugmode ( ON | OFF )
		
		debugEnabled = new BooleanProperty("debugEnabled", false, debugmode);
	}

}
