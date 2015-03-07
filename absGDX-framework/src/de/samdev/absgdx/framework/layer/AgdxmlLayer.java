package de.samdev.absgdx.framework.layer;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.framework.menu.elements.MenuFrame;

public class AgdxmlLayer extends MenuLayer {

	public AgdxmlLayer(AgdxGame owner, BitmapFont bmpfont, FileHandle agdxmlFile) {
		super(owner, bmpfont);
	}

	@Override
	public void onResize() {
		// TODO Auto-generated method stub

	}

}
