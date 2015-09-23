package de.samdev.absgdx.example.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import de.samdev.absgdx.example.Textures;
import de.samdev.absgdx.example.chessgame.ChessLayer;
import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.framework.layer.AgdxmlLayer;
import de.samdev.absgdx.framework.menu.elements.MenuBaseElement;
import de.samdev.absgdx.framework.menu.elements.MenuRadioButton;
import de.samdev.absgdx.framework.util.exceptions.AgdxmlParsingException;

public class ChessMenu extends AgdxmlLayer {

	public ChessMenu(AgdxGame owner) throws AgdxmlParsingException {
		super(owner, new BitmapFont(Gdx.files.internal("consolefont.fnt")), Gdx.files.internal("chessmenu.agdxml"));
	}

	@Override
	public void initialize() {
		loadGuiTextureProviderFromTextureDefinition(Textures.texdef_gui);
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
