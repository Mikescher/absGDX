package de.samdev.absgdx.framework.menu.elements;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.samdev.absgdx.framework.menu.GUITextureProvider;
import de.samdev.absgdx.framework.menu.events.MenuPanelListener;

/**
 * A panel containing multiple other elements
 */
public class MenuPanel extends MenuContainer {
	
	/**
	 * Creates a new Panel
	 * 
	 * @param children the children elements
	 */
	public MenuPanel(List<MenuBaseElement> children) {
		super(children);
	}

	/**
	 * Creates a new Panel (with no children)
	 */
	public MenuPanel() {
		super();
	}

	/**
	 * Creates a new Panel (with no children)
	 * 
	 * @param ident the unique identifier
	 * @param texprovider the texture provider for this element
	 */
	public MenuPanel(String ident, GUITextureProvider texprovider) {
		super(ident, texprovider);
	}

	/**
	 * Creates a new Panel (with no children)
	 * 
	 * @param texprovider the texture provider for this element
	 */
	public MenuPanel(GUITextureProvider texprovider) {
		super(texprovider);
	}
	
	@Override
	public void render(SpriteBatch sbatch, BitmapFont font, int offX, int offY) {
		if (getTextureProvider().hasGeneric9SideTextures(getClass())) {
			render9SideTexture(sbatch, offX, offY);
		}
		
		if (getTextureProvider().hasPaddingTextures(getClass())) {
			renderPaddingTexture(sbatch, offX, offY);
		} 
		
		renderChildren(sbatch, font, offX, offY);
	}
	
	/**
	 * Adds a new listener
	 * 
	 * @param l the new listener
	 */
	public void addPanelListener(MenuPanelListener l) {
		super.addElementListener(l);
	}
}
