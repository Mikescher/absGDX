package de.samdev.absgdx.framework.menu.elements;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

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
	public MenuPanel(List<MenuElement> children) {
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
	public void render(SpriteBatch sbatch, ShapeRenderer srenderer, BitmapFont font) {
		if (getTextureProvider().hasGeneric9SideTextures(getClass())) {
			render9SideTexture(sbatch);
		} else {
			renderSimple(srenderer);
		}
	}

	private void renderSimple(ShapeRenderer srenderer) {
		srenderer.begin(ShapeType.Filled);
		{
			float grayValue = 1f - (getDepth() % 16) / 15f;
			srenderer.setColor(grayValue, grayValue, grayValue, 1f);
			srenderer.rect(getPositionX(), getPositionY(), getWidth(), getHeight());
		}
		srenderer.end();
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
