package de.samdev.absgdx.framework.menu.elements;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import de.samdev.absgdx.framework.layer.MenuLayer;

/**
 * A panel containing multiple other elements
 */
public class MenuPanel extends MenuElement {
	private List<MenuElement> elements;
	
	/**
	 * Creates a new Panel
	 * 
	 * @param children the children elements
	 */
	public MenuPanel(List<MenuElement> children) {
		super();
		
		this.elements = children;
	}

	/**
	 * Creates a new Panel (with no children)
	 */
	public MenuPanel() {
		super();
		
		this.elements = new ArrayList<MenuElement>();
	}
	
	@Override
	public void renderElement(SpriteBatch sbatch, ShapeRenderer srenderer, BitmapFont defaultfont, MenuLayer owner) {
		super.renderElement(sbatch, srenderer, defaultfont, owner);
		
		srenderer.translate(getPositionX(), getPositionY(), 0);
		sbatch.getTransformMatrix().translate(getPositionX(), getPositionY(), 0);
		
		for (MenuElement element : elements) {
			element.renderElement(sbatch, srenderer, defaultfont, owner);
		}

		sbatch.getTransformMatrix().translate(-getPositionX(), -getPositionY(), 0);
		srenderer.translate(-getPositionX(), -getPositionY(), 0);
	}
	
	@Override
	public void render(SpriteBatch sbatch, ShapeRenderer srenderer, BitmapFont font) {
		srenderer.begin(ShapeType.Filled);
		{
			float grayValue = 1f - (getDepth() % 16) / 15f;
			srenderer.setColor(grayValue, grayValue, grayValue, 1f);
			srenderer.rect(getPositionX(), getPositionY(), getWidth(), getHeight());
		}
		srenderer.end();
	}

	@Override
	public void update(float delta) {
		for (MenuElement element : elements) {
			element.update(delta);
		}
	}

	@Override
	public void setDepth(int elementdepth) {
		super.setDepth(elementdepth);
		
		for (MenuElement element : elements) {
			element.setDepth(elementdepth + 1);
		}
	}
	
	@Override
	public void pack(MenuLayer owner) {
		super.pack(owner);
		
		for (MenuElement element : elements) {
			element.layer = this.layer;
		}
		
		setDepth(getDepth());
		
		for (MenuElement element : elements) {
			element.pack(owner);
		}
	}
	
	/**
	 * Adds a new child to the panel
	 * ( pack() is called )
	 * 
	 * @param element the new child
	 */
	public void addChildren(MenuElement element) {
		elements.add(element);
		
		pack(this.layer);
	}
	
	/**
	 * Removes a child from the panel
	 * ( pack() is called )
	 * 
	 * @param element the child to remove
	 * @return true if the child was a child and was removed
	 */
	public boolean removeChildren(MenuElement element) {
		boolean success = elements.remove(element);
		
		pack(this.layer);
		
		return success;
	}

	@Override
	public MenuElement getElementAt(int x, int y) {
		for (MenuElement element : elements) {
			if (element.getBoundaries().contains(x, y))
				return element.getElementAt(x - element.getPositionX(), y - element.getPositionY());
		}
		
		return this;
	}

	@Override
	public void onPointerDown() {
		// NOP
	}

	@Override
	public void onPointerUp() {
		// NOP
	}

	@Override
	public void onPointerClicked() {
		// NOP
	}

	@Override
	public void onFocusGained() {
		// NOP
	}

	@Override
	public void onFocusLost() {
		// NOP
	}

	@Override
	public void onStartHover() {
		// NOP
	}

	@Override
	public void onEndHover() {
		// NOP
	}

	@Override
	public void onKeyTyped(char key) {
		// NOP
	}

	@Override
	public void onKeyDown(int keycode) {
		// NOP
	}
}
