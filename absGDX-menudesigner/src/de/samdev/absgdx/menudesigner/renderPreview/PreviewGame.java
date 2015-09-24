package de.samdev.absgdx.menudesigner.renderPreview;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.framework.util.exceptions.AgdxmlParsingException;

public class PreviewGame extends AgdxGame {

	public FileHandle tex;
	public String texdef;
	
	public BitmapFont fnt;
	
	public String code;
	
	public PreviewGame() { /* */ }

	@Override
	public void onUpdate(float delta) {
		if (code != null) {
			String newcode = code;
			code = null;
			
			try {
				setLayer(new PreviewLayer(this, newcode));
			} catch (AgdxmlParsingException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onCreate() { /* */ }

}
