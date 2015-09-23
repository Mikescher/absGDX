package de.samdev.absgdx.example.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import de.samdev.absgdx.example.Textures;
import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.framework.layer.AgdxmlLayer;
import de.samdev.absgdx.framework.menu.elements.MenuBaseElement;
import de.samdev.absgdx.framework.menu.elements.MenuImage;
import de.samdev.absgdx.framework.util.exceptions.AgdxmlParsingException;

public class DemoMenu extends AgdxmlLayer {

	public DemoMenu(AgdxGame owner) throws AgdxmlParsingException {
		super(owner, new BitmapFont(Gdx.files.internal("consolefont.fnt")), Gdx.files.internal("demomenu.agdxml"));
	}

	@Override
	public void initialize() {
		loadGuiTextureProviderFromTextureDefinition(Textures.texdef_gui);
		
		//#################################################################################################################################

		addAgdxmlImageTexture("img_01", Textures.tex_china_td[0]);
		addAgdxmlImageTexture("img_02", Textures.tex_player_td[0]);
		addAgdxmlImageTexture("img_03", Textures.tex_Anchorpoint_empty);
	}

	@SuppressWarnings("unused") // event listener
	public void settingsTreeClicked(MenuBaseElement element, String identifier) {
		//System.out.println("settingsTreeClicked");
	}

	private int rotationNazi = 0;
	private int rotationChinese = 0;
	
	@SuppressWarnings("unused") // event listener
	public void rotateRight(MenuBaseElement element, String identifier) {
		MenuImage imageNazi = ((MenuImage)getElementByID("imageNazi"));
		MenuImage imageChinese = ((MenuImage)getElementByID("imageChinese"));
		
		imageNazi.setImage(Textures.tex_player_td[rotationNazi =      (rotationNazi    + 3)%4], 750);
		imageChinese.setImage(Textures.tex_china_td[rotationChinese = (rotationChinese + 3)%4], 750);
	}

	@SuppressWarnings("unused") // event listener
	public void rotateLeft(MenuBaseElement element, String identifier) {
		MenuImage imageNazi = ((MenuImage)getElementByID("imageNazi"));
		MenuImage imageChinese = ((MenuImage)getElementByID("imageChinese"));
		
		imageNazi.setImage(Textures.tex_player_td[rotationNazi =      (rotationNazi    + 1)%4], 750);
		imageChinese.setImage(Textures.tex_china_td[rotationChinese = (rotationChinese + 1)%4], 750);
	}

	@SuppressWarnings("unused") // event listener
	public void onPleh(MenuBaseElement element, String identifier) {
		owner.popLayer();
	}

	@SuppressWarnings("unused") // event listener
	public void playChess(MenuBaseElement element, String identifier) {
		try {
			owner.pushLayer(new ChessMenu(owner));
		} catch (AgdxmlParsingException e) {
			throw new RuntimeException(e);
		}
	}
}
