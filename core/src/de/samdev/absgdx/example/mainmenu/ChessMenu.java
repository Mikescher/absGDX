package de.samdev.absgdx.example.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import de.samdev.absgdx.example.Textures;
import de.samdev.absgdx.example.chessgame.ChessLayer;
import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.framework.layer.AgdxmlLayer;
import de.samdev.absgdx.framework.menu.GUITextureProvider;
import de.samdev.absgdx.framework.menu.attributes.TristateBoolean;
import de.samdev.absgdx.framework.menu.attributes.VisualButtonState;
import de.samdev.absgdx.framework.menu.elements.MenuBaseElement;
import de.samdev.absgdx.framework.menu.elements.MenuRadioButton;
import de.samdev.absgdx.framework.util.exceptions.AgdxmlParsingException;

public class ChessMenu extends AgdxmlLayer {

	public ChessMenu(AgdxGame owner) throws AgdxmlParsingException {
		super(owner, new BitmapFont(Gdx.files.internal("consolefont.fnt")), Gdx.files.internal("chessmenu.agdxml"));
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

		prov.setMenuSettingsTreeButtonTexture(Textures.tex_gui_checkers[2][0], true);
		prov.setMenuSettingsTreeButtonTexture(Textures.tex_gui_checkers[2][1], false);
		prov.setMenuSettingsTreeValueTexture(Textures.tex_gui_checkers[1][0], TristateBoolean.FALSE);
		prov.setMenuSettingsTreeValueTexture(Textures.tex_gui_checkers[0][2], TristateBoolean.INTERMEDIATE);
		prov.setMenuSettingsTreeValueTexture(Textures.tex_gui_checkers[0][0], TristateBoolean.TRUE);
		prov.setMenuSettingsTreeLeafTexture(Textures.tex_gui_checkers[0][1]);

		prov.setMenuRadioButtonTexture(Textures.tex_gui_checkers[3][0], true);
		prov.setMenuRadioButtonTexture(Textures.tex_gui_checkers[3][1], false);
		
		addAgdxmlGuiTextureProvider("defaultprov_01", prov);
	}

	@SuppressWarnings("unused") // event listener
	public void startGame(MenuBaseElement element, String identifier) {
		boolean isP1Human = ((MenuRadioButton)getElementByID("rbP1Human")).isChecked();
		boolean isP2Human = ((MenuRadioButton)getElementByID("rbP2Human")).isChecked();

		owner.pushLayer(new ChessLayer(owner, isP1Human, isP2Human));
	}

	@SuppressWarnings("unused") // event listener
	public void menuBack(MenuBaseElement element, String identifier) {
		owner.popLayer();
	}

}
