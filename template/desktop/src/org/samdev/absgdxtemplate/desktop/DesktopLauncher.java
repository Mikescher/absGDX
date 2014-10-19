package org.samdev.absgdxtemplate.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.math.GridPoint2;

import de.samdev.absgdx.AndroidResolutions;

import org.samdev.absgdxtemplate.AbsGdxTemplateGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		GridPoint2 resolution = AndroidResolutions.RES__16_9;
		
		config.width  = resolution.x;
		config.height = resolution.y;
		
		new LwjglApplication(new AbsGdxTemplateGame(), config);
	}
}
