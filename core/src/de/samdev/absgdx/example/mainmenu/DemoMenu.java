package de.samdev.absgdx.example.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import de.samdev.absgdx.example.Textures;
import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.framework.layer.AgdxLayer;
import de.samdev.absgdx.framework.layer.AgdxmlLayer;
import de.samdev.absgdx.framework.menu.GUITextureProvider;
import de.samdev.absgdx.framework.menu.attributes.TristateBoolean;
import de.samdev.absgdx.framework.menu.attributes.VisualButtonState;
import de.samdev.absgdx.framework.util.exceptions.AgdxmlParsingException;


public class DemoMenu extends AgdxmlLayer {

	public DemoMenu(AgdxGame owner) throws AgdxmlParsingException {
		super(owner, new BitmapFont(Gdx.files.internal("consolefont.fnt")), Gdx.files.internal("demomenu.agdxml"));
	}

	@Override
	public void initialize() {
		GUITextureProvider prov = new GUITextureProvider();
		prov.setMenuButtonTexture(Textures.tex_buttongui[0], VisualButtonState.NORMAL);
		prov.setMenuButtonTexture(Textures.tex_buttongui[1], VisualButtonState.HOVERED);
		prov.setMenuButtonTexture(Textures.tex_buttongui[2], VisualButtonState.PRESSED);
		prov.setMenuButtonTexture(Textures.tex_buttongui[3], VisualButtonState.DISABLED);

		prov.setMenuPanelTexture(Textures.tex_panelgui);
		
		prov.setMenuEditTexture(Textures.tex_textfield, false);
		prov.setMenuEditTexture(Textures.tex_textfield_focus, true);
		
		prov.setMenuCheckBoxTexture(Textures.tex_gui_checkers[0][0], true);
		prov.setMenuCheckBoxTexture(Textures.tex_gui_checkers[0][1], false);
		
		prov.setMenuRadioButtonTexture(Textures.tex_gui_checkers[3][0], true);
		prov.setMenuRadioButtonTexture(Textures.tex_gui_checkers[3][1], false);
		
//		prov.setMenuSettingsTreeTexture(Textures.tex_panelgui);
		prov.setMenuSettingsTreeButtonTexture(Textures.tex_gui_checkers[2][0], true);
		prov.setMenuSettingsTreeButtonTexture(Textures.tex_gui_checkers[2][1], false);
		prov.setMenuSettingsTreeValueTexture(Textures.tex_gui_checkers[1][0], TristateBoolean.FALSE);
		prov.setMenuSettingsTreeValueTexture(Textures.tex_gui_checkers[0][2], TristateBoolean.INTERMEDIATE);
		prov.setMenuSettingsTreeValueTexture(Textures.tex_gui_checkers[0][0], TristateBoolean.TRUE);
		prov.setMenuSettingsTreeLeafTexture(Textures.tex_gui_checkers[0][1]);

		prov.set(MenuProgressbar.class, "0", Textures.tex_gui_progressbar[0]);
		prov.set(MenuProgressbar.class, "1", Textures.tex_gui_progressbar[1]);
		prov.set(MenuProgressbar.class, "2", Textures.tex_gui_progressbar[2]);
		prov.set(MenuProgressbar.class, "3", Textures.tex_gui_progressbar[3]);
		prov.set(MenuProgressbar.class, "4", Textures.tex_gui_progressbar[4]);
		
		addAgdxmlGuiTextureProvider("defaultprov_01", prov);
		
		//#################################################################################################################################
		
		addAgdxmlImageTexture("img_01", Textures.tex_china_td[0][0]);
		addAgdxmlImageTexture("img_02", Textures.tex_animation);
		
	}

}
