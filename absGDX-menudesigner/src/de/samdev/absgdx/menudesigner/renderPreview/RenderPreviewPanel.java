package de.samdev.absgdx.menudesigner.renderPreview;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;

public class RenderPreviewPanel extends JPanel  {
	private static final long serialVersionUID = -7235606196735881349L;
	
	public PreviewGame game;
	public LwjglAWTCanvas canvas;
	
	public RenderPreviewPanel() {
		super();
		
		this.game = new PreviewGame();
		this.canvas = new LwjglAWTCanvas(game);
		
		this.setLayout(new BorderLayout());
		
		this.add(canvas.getCanvas(), BorderLayout.CENTER);
		
		game.pause();
	}

	public void refresh(String agdxml, String agdtexdef, String tex_path) throws Exception {
		game.tex = Gdx.files.absolute(tex_path);
		game.texdef = agdtexdef;
		
		game.code = agdxml;
	}

	public void switchDebug() {
		game.settings.debugEnabled.doSwitch();
	}
}
