package de.samdev.absgdx.framework.menu.elements;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.framework.layer.MenuLayer;
import de.samdev.absgdx.framework.menu.GUITextureProvider;
import de.samdev.absgdx.framework.menu.attributes.RectangleRadius;
import de.samdev.absgdx.framework.menu.events.MenuElementListener;
import de.samdev.absgdx.framework.util.MenuRenderHelper;

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
	
	private boolean visible = true;

	private BitmapFont font = null;
	
	protected final GUITextureProvider textureprovider;
	protected List<MenuElementListener> listeners = new ArrayList<MenuElementListener>();
	
	/**
	 * the unique identifier to identify this element
	 */
	public final String identifier;
	
	/**
	 * the owner-layer *(can be null when not added to layer)*
	 */
	protected MenuLayer layer;
	
	/**
	 * the owner *(can be null when not added to an owner)*
	 */
	protected MenuElement owner;
	
	/**
	 * Creates a new MenuElement (with a random identifier)
	 */
	public MenuElement() {
		this("{" + java.util.UUID.randomUUID().toString() + "}");
	}
	
	/**
	 * Creates a new MenuElement (with a random identifier)
	 * 
	 * @param texprovider the texture provider for this element
	 */
	public MenuElement(GUITextureProvider texprovider) {
		this("{" + java.util.UUID.randomUUID().toString() + "}", texprovider);
	}
	
	/**
	 * Creates a new MenuElement
	 * 
	 * @param ident the unique identifier
	 */
	public MenuElement(String ident) {
		this(ident, new GUITextureProvider());
	}
	
	/**
	 * Creates a new MenuElement
	 * 
	 * @param ident the unique identifier
	 * @param texprovider the texture provider for this element
	 */
	public MenuElement(String ident, GUITextureProvider texprovider) {
		super();
		
		this.identifier = ident;
		this.textureprovider = texprovider;
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
		if (visible) {
			if (font != null) {
				render(sbatch, srenderer, font);
			} else {
				render(sbatch, srenderer, defaultfont);
			}
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

	protected void render9SideTexture(SpriteBatch sbatch) {
		render9SideTexture(sbatch, null);
	}
	
	protected void render9SideTexture(SpriteBatch sbatch, Object appendix) {
		String app = (appendix == null || appendix.toString().isEmpty()) ? "" : ("#" + appendix.toString());
		
		TextureRegion tex_TL = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_GENERIC_TL + app);
		TextureRegion tex_TT = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_GENERIC_TT + app);
		TextureRegion tex_TR = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_GENERIC_TR + app);
		TextureRegion tex_LL = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_GENERIC_LL + app);
		TextureRegion tex_CC = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_GENERIC_CC + app);
		TextureRegion tex_RR = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_GENERIC_RR + app);
		TextureRegion tex_BL = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_GENERIC_BL + app);
		TextureRegion tex_BB = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_GENERIC_BB + app);
		TextureRegion tex_BR = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_GENERIC_BR + app);
		
		sbatch.getTransformMatrix().translate(getPositionX(), getPositionY(), 0);
		sbatch.begin();
		
		// ######## Corners ########
		
		MenuRenderHelper.drawTexture(sbatch, tex_TL, 0, 0);
		MenuRenderHelper.drawTexture(sbatch, tex_TR, getWidth() - tex_TR.getRegionWidth(), 0);
		MenuRenderHelper.drawTexture(sbatch, tex_BL, 0, getHeight() - tex_BL.getRegionHeight());
		MenuRenderHelper.drawTexture(sbatch, tex_BR, getWidth() - tex_BR.getRegionWidth(), getHeight() - tex_BR.getRegionHeight());
		
		// ######## Edges ########

		MenuRenderHelper.drawTextureStretched(sbatch, tex_TT, tex_TL.getRegionWidth(), 0, getWidth() - tex_TL.getRegionWidth() - tex_TR.getRegionWidth(), tex_TT.getRegionHeight());
		MenuRenderHelper.drawTextureStretched(sbatch, tex_LL, 0, tex_TL.getRegionHeight(), tex_LL.getRegionWidth(), getHeight() - tex_TL.getRegionHeight() - tex_BL.getRegionHeight());
		MenuRenderHelper.drawTextureStretched(sbatch, tex_BB, tex_TL.getRegionWidth(), getHeight() -tex_BB.getRegionHeight(), getWidth() - tex_TL.getRegionWidth() - tex_TR.getRegionWidth(), tex_BB.getRegionHeight());
		MenuRenderHelper.drawTextureStretched(sbatch, tex_RR, getWidth() - tex_RR.getRegionWidth(), tex_TL.getRegionHeight(), tex_LL.getRegionWidth(), getHeight() - tex_TL.getRegionHeight() - tex_BL.getRegionHeight());

		// ######## Center ########

		MenuRenderHelper.drawTextureStretched(sbatch, tex_CC, tex_TL.getRegionWidth(), tex_TL.getRegionHeight(), getWidth() - tex_BR.getRegionWidth() - tex_TL.getRegionWidth(), getHeight() - tex_BR.getRegionHeight() - tex_TL.getRegionHeight());
	
		sbatch.end();
		sbatch.getTransformMatrix().translate(-getPositionX(), -getPositionY(), 0);
	}
	
	protected RectangleRadius get9SidePadding(Object appendix) {
		String app = (appendix == null || appendix.toString().isEmpty()) ? "" : ("#" + appendix.toString());
		
		int pad_top_1 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_GENERIC_TL + app).getRegionHeight();
		int pad_top_2 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_GENERIC_TT + app).getRegionHeight();
		int pad_top_3 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_GENERIC_TR + app).getRegionHeight();

		int pad_lef_1 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_GENERIC_LL + app).getRegionWidth();
		int pad_lef_2 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_GENERIC_TL + app).getRegionWidth();
		int pad_lef_3 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_GENERIC_BL + app).getRegionWidth();

		int pad_bot_1 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_GENERIC_BL + app).getRegionHeight();
		int pad_bot_2 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_GENERIC_BB + app).getRegionHeight();
		int pad_bot_3 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_GENERIC_BR + app).getRegionHeight();

		int pad_rig_1 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_GENERIC_RR + app).getRegionWidth();
		int pad_rig_2 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_GENERIC_TR + app).getRegionWidth();
		int pad_rig_3 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_GENERIC_BR + app).getRegionWidth();
		
		int pad_top = Math.max(Math.max(pad_top_1, pad_top_2), pad_top_3);
		int pad_lef = Math.max(Math.max(pad_lef_1, pad_lef_2), pad_lef_3);
		int pad_bot = Math.max(Math.max(pad_bot_1, pad_bot_2), pad_bot_3);
		int pad_rig = Math.max(Math.max(pad_rig_1, pad_rig_2), pad_rig_3);
		
		return new RectangleRadius(pad_top, pad_lef, pad_bot, pad_rig);
	}

	/**
	 * Called when the tree structure drastically changes
	 * (e.g. added to a MenuLayer)
	 * 
	 * @param layer the parent layer
	 * @param owner the parent element
	 */
	public void pack(MenuLayer layer, MenuElement owner) {
		this.layer = layer;
		this.owner = owner;
		
		for (MenuElement element : getDirectInnerElements()) {
			element.pack(layer, this);
		}
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
	 * @return all children of this element (includes composite elements, no nesting)
	 */
	public abstract List<MenuElement> getDirectInnerElements();

	/**
	 * @return all children of this element (includes composite elements, includes children of children)
	 */
	public List<MenuElement> getAllInnerElements() {
		List<MenuElement> result = new ArrayList<MenuElement>();

		for (MenuElement elem : getDirectInnerElements()) {
			result.add(elem);
			result.addAll(elem.getAllInnerElements());
		}
		
		return result;
	}
	
	/**
	 * @return all children of this element (excludes composite elements, no nesting)
	 */
	public List<MenuElement> getDirectChildElements() {
		List<MenuElement> result = new ArrayList<MenuElement>();
		return result;
	}

	/**
	 * @return all children of this element (excludes composite elements, includes children of children)
	 */
	public List<MenuElement> getAllChildElements() {
		List<MenuElement> result = new ArrayList<MenuElement>();

		for (MenuElement elem : getDirectChildElements()) {
			result.add(elem);
			result.addAll(elem.getAllChildElements());
		}
		
		return result;
	}
	
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
	 * Called when the user scrolls
	 * @param amount the scroll width
	 */
	public void onScroll(int amount) {
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
	 * Set the X and Y position of the component
	 * 
	 * @param position the position (will be rounded to int)
	 */
	public void setPosition(Vector2 position) {
		setPositionX((int)position.x);
		setPositionY((int)position.y);
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

	/**
	 * @return if this component is visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * Set if this component shall be rendered
	 * 
	 * @param visible
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	/**
	 * @return the texture provider
	 */
	public GUITextureProvider getTextureProvider() {
		return textureprovider;
	}
	
	/**
	 * @return the count of listener
	 */
	public int getListenerCount() {
		return listeners.size();
	}
	
	/**
	 * When (eg) rendering the coordinate matrix is translated by a specific amount
	 * This method returns this amount
	 * 
	 * @return the absolute X coordinate system offset (relative to the layer)
	 */
	public int getCoordinateOffsetX() {
		return (owner == null ? 0 : owner.getPositionX() + owner.getCoordinateOffsetX());
	}
	
	/**
	 * When (eg) rendering the coordinate matrix is translated by a specific amount
	 * This method returns this amount
	 * 
	 * @return the absolute Y coordinate system offset (relative to the layer)
	 */
	public int getCoordinateOffsetY() {
		return (owner == null ? 0 : owner.getPositionY() + owner.getCoordinateOffsetY());
	}
}
