package de.samdev.absgdx.framework.menu.elements;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

import de.samdev.absgdx.framework.layer.MenuLayer;
import de.samdev.absgdx.framework.menu.events.MenuElementListener;

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
	
	protected List<MenuElementListener> listeners = new ArrayList<MenuElementListener>();
	
	/**
	 * the unique identifier to identify this element
	 */
	public final String identifier;
	
	/**
	 * the owner *(can be null when not added to layer)*
	 */
	protected MenuLayer layer;
	
	/**
	 * Creates a new MenuElement (with a random identifier)
	 */
	public MenuElement() {
		super();
		
		this.identifier = "{" + java.util.UUID.randomUUID().toString() + "}";
	}
	
	/**
	 * Creates a new MenuElement
	 * @param ident the unique identifier
	 */
	public MenuElement(String ident) {
		super();
		
		this.identifier = ident;
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
	 * Called when the tree structure drastically changes
	 * (e.g. added to a MenuLayer)
	 * 
	 * @param owner
	 */
	public void pack(MenuLayer owner) {
		this.layer = owner;
	}
	
	/**
	 * Adds a new (general purpose) listener
	 * 
	 * @param l the new listener
	 */
	protected void addElementListener(MenuElementListener l) {
		listeners.add(l);
	}
	
	/**
	 * Removes a listener
	 * 
	 * @param l the to remove listener
	 * @return if the operation was successful
	 */
	public boolean removeListener(MenuElementListener l) {
		return listeners.remove(l);
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
	 * Return the element at this (x|y) position
	 * This is usually this, except for container elements
	 * 
	 * @param x the x position
	 * @param y the y position
	 * @return
	 */
	public abstract MenuElement getElementAt(int x, int y);
	
	/**
	 * Called on PointerDown event
	 * -> Touching on TouchDevice
	 * -> MouseDown on Desktop
	 */
	public void onPointerDown() {
		for (MenuElementListener lst : listeners) {
			lst.onPointerDown(this, this.identifier);
		}
	}
	
	/**
	 * Called on PointerUp event
	 * -> Touch-release on TouchDevice
	 * -> MouseUp on Desktop
	 */
	public void onPointerUp() {
		for (MenuElementListener lst : listeners) {
			lst.onPointerUp(this, this.identifier);
		}
	}
	
	/**
	 * Called on PointerClicked event
	 * -> Touch on TouchDevice
	 * -> MouseClick on Desktop
	 */
	public void onPointerClicked() {
		for (MenuElementListener lst : listeners) {
			lst.onClicked(this, this.identifier);
		}
	}
	
	/**
	 * Called when this element gains focus
	 */
	public void onFocusGained() {
		for (MenuElementListener lst : listeners) {
			lst.onFocus(this, this.identifier);
		}
	}

	/**
	 * Called when this element looses focus
	 */
	public void onFocusLost() {
		for (MenuElementListener lst : listeners) {
			lst.onFocusLost(this, this.identifier);
		}
	}
	
	/**
	 * Called when the pointer hovers over this element
	 */
	public void onStartHover() {
		for (MenuElementListener lst : listeners) {
			lst.onHover(this, this.identifier);
		}
	}
	
	/**
	 * Called when the pointer no longer hovers over this element
	 */
	public void onEndHover() {
		for (MenuElementListener lst : listeners) {
			lst.onHoverEnd(this, this.identifier);
		}
	}

	/**
	 * Called when a Keyboard character is typed
	 * @param key the character
	 */
	public void onKeyTyped(char key) {
		// NOP
	}

	/**
	 * Called when a Keyboard key is pressed down
	 * @param keycode the key code (see libgdx:Keys)
	 */
	public void onKeyDown(int keycode) {
		// NOP
	}
	
	/**
	 * @return the boundary rectangle
	 */
	public Rectangle getBoundaries() {
		return new Rectangle(getPositionX(), getPositionY(), getWidth(), getHeight());
	}
	
	/**
	 * @return the component width
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Sets the component width
	 * 
	 * @param width the width
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
	 * @param height the height
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
	 * @param positionX the x position
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
	 * @param positionY the y position
	 */
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	
	/**
	 * Set the X and Y position of the component
	 * 
	 * @param positionX the x position
	 * @param positionY the y position
	 */
	public void setPosition(int positionX, int positionY) {
		setPositionX(positionX);
		setPositionY(positionY);
	}
	
	/**
	 * Set width and height of the component
	 * 
	 * @param width the width
	 * @param height the height
	 */
	public void setSize(int width, int height) {
		setWidth(width);
		setHeight(height);
	}
	
	/**
	 * Set the X-position, Y-position, width and height of the component
	 * 
	 * @param x the x position
	 * @param y  the y position
	 * @param width the width 
	 * @param height the height
	 */
	public void setBoundaries(int x, int y, int width, int height) {
		setPosition(x, y);
		setSize(width, height);
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
	
	/**
	 * Returns if this element is hovered
	 * Only works when added to an MenuLayer (returns false otherwise)
	 * 
	 * @return if this element is hovered
	 */
	public boolean isHovered() {
		if (layer == null) return false;
		
		return layer.isHovered(this);
	}

	/**
	 * Returns if this element is pressed
	 * Only works when added to an MenuLayer (returns false otherwise)
	 * 
	 * @return if this element is pressed
	 */
	public boolean isPressed() {
		if (layer == null) return false;
		
		return layer.isPressed(this);
	}
	
	/**
	 * Returns if this element is focused
	 * Only works when added to an MenuLayer (returns false otherwise)
	 * 
	 * @return if this element is focused
	 */
	public boolean isFocused() {
		if (layer == null) return false;
		
		return layer.isFocused(this);
	}
}
