package de.samdev.absgdx.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import de.samdev.absgdx.AGdxDemoGame;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
	    config.useAccelerometer = true; //TODO Set to false if not used
	    config.useCompass = true;
	      
		initialize(new AGdxDemoGame(), config);
	}
}
