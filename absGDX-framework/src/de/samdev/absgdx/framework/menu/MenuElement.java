package de.samdev.absgdx.framework.menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import de.samdev.absgdx.framework.GameSettings;
import de.samdev.absgdx.framework.layer.MenuLayer;

/**
 * A single element in a Menu Layer (e.g. Buttons, Panels, Checkboxes ...)
 *
 */
public abstract class MenuElement {
	private int depth = -1;
	
	private int positionX;
	private int positionY;
	private int height;
	private int width;

	private BitmapFont font = null;
	
	/**
	 * Creates a new MenuElement
	 */
	public MenuElement() {
		super();
	}

	/**
	 * Renders the element (always call this function instead of directly calling render )
	 * Performs preparation needed before rendering
	 * 
	 * @param sbatch the BatchRenderer (from LibGDX)
	 * @param srenderer the ShapeRenderer (from LibGDX)
	 * @param defaultfont the default font to use
	 * @param owner the Menu in which this element exists
	 */
	public void renderElement(SpriteBatch sbatch, ShapeRenderer srenderer, BitmapFont defaultfont, MenuLayer owner) {
		if (font != null) {
			render(sbatch, srenderer, font);
		} else {
			render(sbatch, srenderer, defaultfont);
		}
		
		if (owner.owner.settings.debugMenuBorders.isActive()) {
			srenderer.begin(ShapeType.Line);
			{
				srenderer.setColor(owner.owner.settings.debugMenuBordersColor.get());
				srenderer.rect(getPositionX(), getPositionY(), getWidth(), getHeight());
			}
			srenderer.end();
		}
	}
	
	/**
	 * Renders the Element
	 * 
	 * @param sbatch the BatchRenderer (from LibGDX)
	 * @param srenderer the ShapeRenderer (from LibGDX)
	 * @param font the font to use
	 */
	public abstract void render(SpriteBatch sbatch, ShapeRenderer srenderer, BitmapFont font);
	
	/**
	 * @param delta the time since the last update (in ms) - can be averaged over he last few cycles
	 */
	public abstract void update(float delta);
	
	/**
	 * @return the component width
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Sets the component width
	 * 
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the component height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Set the component height
	 * 
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * @return the X position of the component
	 */
	public int getPositionX() {
		return positionX;
	}
	
	/**
	 * Set the X position of the component
	 * 
	 * @param positionX
	 */
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	/**
	 * @return the Y position of the component
	 */
	public int getPositionY() {
		return positionY;
	}

	/**
	 * Set the Y position of the component
	 * 
	 * @param positionY
	 */
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	
	/**
	 * Set the X and Y position of the component
	 * 
	 * @param positionX
	 * @param positionY
	 */
	public void setPosition(int positionX, int positionY) {
		setPositionX(positionX);
		setPositionY(positionY);
	}
	
	/**
	 * Set width and height of the component
	 * 
	 * @param width
	 * @param height
	 */
	public void setSize(int width, int height) {
		setWidth(width);
		setHeight(height);
	}
	
	/**
	 * Changes the depth of the element (= recursion depth in element structure)
	 * Happens automatically when MenuFrame is used
	 * 
	 * @param elementdepth
	 */
	public void setDepth(int elementdepth) {
		this.depth = elementdepth;
	}
	
	/**
	 * @return the structural depth of this element
	 */
	public int getDepth() {
		return depth;
	}

	/**
	 * Get the current font for this element.
	 * NULL means that the default font is used
	 * 
	 * @return the font
	 */
	public BitmapFont getFont() {
		return font;
	}

	/**
	 * Set the current font for this element.
	 * NULL means that the default font is used
	 * 
	 * @param font the new font (or NULL)
	 */
	public void setFont(BitmapFont font) {
		this.font = font;
	}
}
