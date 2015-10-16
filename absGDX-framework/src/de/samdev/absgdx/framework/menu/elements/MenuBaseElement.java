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

import de.samdev.absgdx.framework.GameSettings;
import de.samdev.absgdx.framework.math.AbsMath;
import de.samdev.absgdx.framework.menu.GUITextureProvider;
import de.samdev.absgdx.framework.menu.MenuOwner;
import de.samdev.absgdx.framework.menu.attributes.ExtendedRectangleRadius;
import de.samdev.absgdx.framework.menu.attributes.RectangleRadius;
import de.samdev.absgdx.framework.menu.events.MenuBaseElementListener;
import de.samdev.absgdx.framework.util.MenuRenderHelper;

/**
 * A single element in a Menu Layer (e.g. Buttons, Panels, Checkboxes ...)
 *
 */
public abstract class MenuBaseElement {
	private int depth = -1;
	
	private int positionX;
	private int positionY;
	private int height;
	private int width;
	
	private boolean visible = true;

	private BitmapFont font = null;
	
	protected GUITextureProvider textureprovider;
	protected List<MenuBaseElementListener> listeners = new ArrayList<MenuBaseElementListener>();
	
	/**
	 * the unique identifier to identify this element
	 */
	public final String identifier;
	
	/**
	 * the owner-layer *(can be null when not added to layer)*
	 */
	protected MenuOwner owner;
	
	/**
	 * the owner *(can be null when not added to an owner)*
	 */
	protected MenuBaseElement parent;
	
	/**
	 * Creates a new MenuBaseElement (with a random identifier)
	 */
	public MenuBaseElement() {
		this("{" + java.util.UUID.randomUUID().toString() + "}");
	}
	
	/**
	 * Creates a new MenuBaseElement (with a random identifier)
	 * 
	 * @param texprovider the texture provider for this element
	 */
	public MenuBaseElement(GUITextureProvider texprovider) {
		this("{" + java.util.UUID.randomUUID().toString() + "}", texprovider);
	}
	
	/**
	 * Creates a new MenuBaseElement
	 * 
	 * @param ident the unique identifier
	 */
	public MenuBaseElement(String ident) {
		this(ident, new GUITextureProvider());
	}
	
	/**
	 * Creates a new MenuBaseElement
	 * 
	 * @param ident the unique identifier
	 * @param texprovider the texture provider for this element
	 */
	public MenuBaseElement(String ident, GUITextureProvider texprovider) {
		super();
		
		this.identifier = ident;
		this.textureprovider = texprovider;
	}

	/**
	 * Renders the generic (9 sided) texture for this element
	 * 
	 * @param sbatch the spritebatch used to render
	 * @param offX render offset in X
	 * @param offY render offset in Y
	 */
	protected void render9SideTexture(SpriteBatch sbatch, int offX, int offY) {
		render9SideTexture(sbatch, null, offX, offY);
	}
	
	/**
	 * Renders the generic (9 sided) texture for this element
	 * 
	 * @param sbatch the spritebatch used to render
	 * @param appendix an optional appendix to the identifier
	 * @param offX render offset in X
	 * @param offY render offset in Y
	 */
	protected void render9SideTexture(SpriteBatch sbatch, Object appendix, int offX, int offY) {
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

		// ########################

		float coordX = offX + getPositionX();
		float coordY = offY + getPositionY();
		
		// ######## Corners ########
		
		MenuRenderHelper.drawTexture(sbatch, tex_TL, coordX, coordY);
		MenuRenderHelper.drawTexture(sbatch, tex_TR, coordX + getWidth() - tex_TR.getRegionWidth(), coordY);
		MenuRenderHelper.drawTexture(sbatch, tex_BL, coordX, coordY + getHeight() - tex_BL.getRegionHeight());
		MenuRenderHelper.drawTexture(sbatch, tex_BR, coordX + getWidth() - tex_BR.getRegionWidth(), coordY + getHeight() - tex_BR.getRegionHeight());
		
		// ######## Edges ########

		MenuRenderHelper.drawTextureStretched(sbatch, tex_TT, coordX + tex_TL.getRegionWidth(), coordY, getWidth() - tex_TL.getRegionWidth() - tex_TR.getRegionWidth(), tex_TT.getRegionHeight());
		MenuRenderHelper.drawTextureStretched(sbatch, tex_LL, coordX, coordY + tex_TL.getRegionHeight(), tex_LL.getRegionWidth(), getHeight() - tex_TL.getRegionHeight() - tex_BL.getRegionHeight());
		MenuRenderHelper.drawTextureStretched(sbatch, tex_BB, coordX + tex_TL.getRegionWidth(), coordY + getHeight() - tex_BB.getRegionHeight(), getWidth() - tex_TL.getRegionWidth() - tex_TR.getRegionWidth(), tex_BB.getRegionHeight());
		MenuRenderHelper.drawTextureStretched(sbatch, tex_RR, coordX + getWidth() - tex_RR.getRegionWidth(), coordY + tex_TL.getRegionHeight(), tex_LL.getRegionWidth(), getHeight() - tex_TL.getRegionHeight() - tex_BL.getRegionHeight());

		// ######## Center ########

		MenuRenderHelper.drawTextureStretched(sbatch, tex_CC, coordX + tex_TL.getRegionWidth(), coordY + tex_TL.getRegionHeight(), getWidth() - tex_BR.getRegionWidth() - tex_TL.getRegionWidth(), getHeight() - tex_BR.getRegionHeight() - tex_TL.getRegionHeight());

		// ########################
	}

	/**
	 * Gets the size of the textures for the rendering of a generic 9-sided texture
	 * 
	 * @param appendix an optional appendix to the identifier
	 */
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

		int pad_top = AbsMath.imax(pad_top_1, pad_top_2, pad_top_3);
		int pad_lef = AbsMath.imax(pad_lef_1, pad_lef_2, pad_lef_3);
		int pad_bot = AbsMath.imax(pad_bot_1, pad_bot_2, pad_bot_3);
		int pad_rig = AbsMath.imax(pad_rig_1, pad_rig_2, pad_rig_3);
		
		return new RectangleRadius(pad_top, pad_lef, pad_bot, pad_rig);
	}

	/**
	 * Renders the generic (9 sided) texture for this element
	 * 
	 * @param sbatch the spritebatch used to render
	 * @param offX render offset in X
	 * @param offY render offset in Y
	 */
	protected void renderPaddingTexture(SpriteBatch sbatch, int offX, int offY) {
		renderPaddingTexture(sbatch, null, offX, offY);
	}

	/**
	 * Renders the generic (9 sided) texture for this element
	 * 
	 * @param sbatch the spritebatch used to render
	 * @param appendix an optional appendix to the identifier
	 * @param offX render offset in X
	 * @param offY render offset in Y
	 */
	protected void renderPaddingTexture(SpriteBatch sbatch, Object appendix, int offX, int offY) {
		String app = (appendix == null || appendix.toString().isEmpty()) ? "" : ("#" + appendix.toString());
		
		TextureRegion tex_TL = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_TL + app);
		TextureRegion tex_TT = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_TT + app);
		TextureRegion tex_TR = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_TR + app);
		TextureRegion tex_LL = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_LL + app);
		TextureRegion tex_RR = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_RR + app);
		TextureRegion tex_BL = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_BL + app);
		TextureRegion tex_BB = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_BB + app);
		TextureRegion tex_BR = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_BR + app);
		TextureRegion tex_NL = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_NL + app);
		TextureRegion tex_NR = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_NR + app);
		TextureRegion tex_ET = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_ET + app);
		TextureRegion tex_EB = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_EB + app);
		TextureRegion tex_SL = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_SL + app);
		TextureRegion tex_SR = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_SR + app);
		TextureRegion tex_WT = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_WT + app);
		TextureRegion tex_WB = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_WB + app);
		
		ExtendedRectangleRadius padding = getPaddingTexturePadding(appendix);

		// ########################

		float coordX = offX + getPositionX();
		float coordY = offY + getPositionY();
		
		// ######## Corners ########
		
		MenuRenderHelper.drawTexture(sbatch, tex_TL, coordX - padding.left, coordY - padding.top);
		MenuRenderHelper.drawTexture(sbatch, tex_TR, coordX + getWidth(),   coordY - padding.top);
		MenuRenderHelper.drawTexture(sbatch, tex_BL, coordX - padding.left, coordY + getHeight());
		MenuRenderHelper.drawTexture(sbatch, tex_BR, coordX + getWidth(),   coordY + getHeight());

		// ######## Corner Extensions ########

		MenuRenderHelper.drawTexture(sbatch, tex_NL, coordX, coordY - padding.top);
		MenuRenderHelper.drawTexture(sbatch, tex_NR, coordX + getWidth() - padding.offsetE, coordY - padding.top);
		MenuRenderHelper.drawTexture(sbatch, tex_WT, coordX - padding.left, coordY);
		MenuRenderHelper.drawTexture(sbatch, tex_WB, coordX - padding.left, coordY + getHeight() - padding.offsetS);
		MenuRenderHelper.drawTexture(sbatch, tex_SL, coordX, coordY + getHeight());
		MenuRenderHelper.drawTexture(sbatch, tex_SR, coordX + getWidth() - padding.offsetE, coordY + getHeight());
		MenuRenderHelper.drawTexture(sbatch, tex_ET, coordX + getWidth(), coordY);
		MenuRenderHelper.drawTexture(sbatch, tex_EB, coordX + getWidth(), coordY + getHeight() - padding.offsetS);
	
		// ######## Edges ########

		MenuRenderHelper.drawTextureStretched(sbatch, tex_TT, coordX + padding.offsetW, coordY - padding.top, padding.innerWidth, padding.top);
		MenuRenderHelper.drawTextureStretched(sbatch, tex_LL, coordX - padding.left, coordY + padding.offsetN, padding.left, padding.innerHeight);
		MenuRenderHelper.drawTextureStretched(sbatch, tex_BB, coordX + padding.offsetW, coordY + getHeight(), padding.innerWidth, padding.top);
		MenuRenderHelper.drawTextureStretched(sbatch, tex_RR, coordX + getWidth(), coordY + padding.offsetN, padding.left, padding.innerHeight);
	}

	/**
	 * Gets the size of the textures for the rendering of a padding texture
	 * 
	 * @param appendix an optional appendix to the identifier
	 */
	protected ExtendedRectangleRadius getPaddingTexturePadding(Object appendix) {
		String app = (appendix == null || appendix.toString().isEmpty()) ? "" : ("#" + appendix.toString());
		
		int pad_top_1 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_TL + app).getRegionHeight();
		int pad_top_2 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_WT + app).getRegionHeight();
		int pad_top_3 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_TT + app).getRegionHeight();
		int pad_top_4 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_WB + app).getRegionHeight();
		int pad_top_5 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_TR + app).getRegionHeight();

		int pad_lef_1 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_LL + app).getRegionWidth();
		int pad_lef_2 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_NL + app).getRegionWidth();
		int pad_lef_3 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_TL + app).getRegionWidth();
		int pad_lef_4 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_NR + app).getRegionWidth();
		int pad_lef_5 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_BL + app).getRegionWidth();

		int pad_bot_1 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_BL + app).getRegionHeight();
		int pad_bot_2 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_SL + app).getRegionHeight();
		int pad_bot_3 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_BB + app).getRegionHeight();
		int pad_bot_4 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_SR + app).getRegionHeight();
		int pad_bot_5 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_BR + app).getRegionHeight();

		int pad_rig_1 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_RR + app).getRegionWidth();
		int pad_rig_2 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_ET + app).getRegionWidth();
		int pad_rig_3 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_TR + app).getRegionWidth();
		int pad_rig_4 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_EB + app).getRegionWidth();
		int pad_rig_5 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_BR + app).getRegionWidth();

		int pad_n_1 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_WT + app).getRegionHeight();
		int pad_n_2 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_ET + app).getRegionHeight();

		int pad_e_1 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_NR + app).getRegionWidth();
		int pad_e_2 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_SR + app).getRegionWidth();

		int pad_s_1 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_WB + app).getRegionHeight();
		int pad_s_2 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_EB + app).getRegionHeight();

		int pad_w_1 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_NL + app).getRegionWidth();
		int pad_w_2 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_PADDING_SL + app).getRegionWidth();
		
		int pad_top = AbsMath.imax(pad_top_1, pad_top_2, pad_top_3, pad_top_4, pad_top_5);
		int pad_lef = AbsMath.imax(pad_lef_1, pad_lef_2, pad_lef_3, pad_lef_4, pad_lef_5);
		int pad_bot = AbsMath.imax(pad_bot_1, pad_bot_2, pad_bot_3, pad_bot_4, pad_bot_5);
		int pad_rig = AbsMath.imax(pad_rig_1, pad_rig_2, pad_rig_3, pad_rig_4, pad_rig_5);

		int pad_N = AbsMath.imax(pad_n_1, pad_n_2);
		int pad_E = AbsMath.imax(pad_e_1, pad_e_2);
		int pad_S = AbsMath.imax(pad_s_1, pad_s_2);
		int pad_W = AbsMath.imax(pad_w_1, pad_w_2);
		
		return new ExtendedRectangleRadius(pad_top, pad_lef, pad_bot, pad_rig, pad_N, pad_E, pad_S, pad_W, getWidth(), getHeight());
	}

	/**
	 * Called when the tree structure drastically changes
	 * (e.g. added to a MenuLayer)
	 * 
	 * @param layer the owner of the menutree
	 * @param owner the parent element
	 */
	public void pack(MenuOwner layer, MenuBaseElement owner) {
		this.owner = layer;
		this.parent = owner;
		
		for (MenuBaseElement element : getDirectInnerElements()) {
			element.pack(layer, this);
		}
	}
	
	/**
	 * Adds a new (general purpose) listener
	 * 
	 * @param l the new listener
	 */
	protected void addElementListener(MenuBaseElementListener l) {
		listeners.add(l);
	}
	
	/**
	 * Removes a listener
	 * 
	 * @param l the to remove listener
	 * @return if the operation was successful
	 */
	public boolean removeListener(MenuBaseElementListener l) {
		return listeners.remove(l);
	}

	/**
	 * Renders the element (always call this function instead of directly calling render )
	 * Performs preparation needed before rendering
	 * 
	 * @param sbatch the BatchRenderer (from LibGDX)
	 * @param defaultfont the default font to use
	 * @param owner the Menu in which this element exists
	 * @param offX render offset in X
	 * @param offY render offset in Y
	 */
	public final void renderElement(SpriteBatch sbatch, BitmapFont defaultfont, MenuOwner owner, int offX, int offY) {
		if (visible) {
			if (font != null) {
				render(sbatch, font, offX, offY);
			} else {
				render(sbatch, defaultfont, offX, offY);
			}
		}
	}

	/**
	 * Renders the element (always call this function instead of directly calling render )
	 * Performs preparation needed before rendering
	 * 
	 * @param sbatch the BatchRenderer (from LibGDX)
	 * @param srenderer the ShapeRenderer (from LibGDX)
	 * @param defaultfont the default font to use
	 * @param owner the Menu in which this element exists
	 * @param offX render offset in X
	 * @param offY render offset in Y
	 */
	public final void renderElementCustom(SpriteBatch sbatch, ShapeRenderer srenderer, BitmapFont defaultfont, MenuOwner owner, int offX, int offY) {
		if (visible) {
			if (font != null) {
				renderCustom(sbatch, srenderer, font, offX, offY);
			} else {
				renderCustom(sbatch, srenderer, defaultfont, offX, offY);
			}
		}
	}

	/**
	 * Renders the debug overlay of the element (always call this function instead of directly calling render)
	 * Performs preparation needed before rendering
	 * 
	 * @param srenderer the ShapeRenderer (from LibGDX)
	 * @param owner the Menu in which this element exists
	 */
	public final void renderElementDebug(ShapeRenderer srenderer, MenuOwner owner) {
		if (visible) {
			renderDebug(srenderer);
		}
		
		if (owner != null && owner.getAgdxGame().settings.debugMenuBorders.isActive()) {
			renderDebugGridLines(srenderer, owner.getAgdxGame().settings);
		}
	}

	protected void renderDebugGridLines(ShapeRenderer srenderer, GameSettings settings) {
		srenderer.begin(ShapeType.Line);
		{
			srenderer.setColor(settings.debugMenuBordersColor.get());
			srenderer.rect(getPositionX(), getPositionY(), getWidth(), getHeight());
		}
		srenderer.end();
	}

	/**
	 * Renders the Element
	 * 
	 * Don't call begin end in this method - this is done by the caller
	 * 
	 * @param sbatch the BatchRenderer (from LibGDX)
	 * @param font the font to use
	 * @param offX render offset in X
	 * @param offY render offset in Y
	 */
	public abstract void render(SpriteBatch sbatch, BitmapFont font, int offX, int offY);

	/**
	 * Renders additional elements (you must call begin end on your own here)
	 * 
	 * @param sbatch the BatchRenderer (from LibGDX)
	 * @param srenderer the ShapeRenderer (from LibGDX)
	 * @param font the font to use
	 * @param offX render offset in X
	 * @param offY render offset in Y
	 */
	public abstract void renderCustom(SpriteBatch sbatch, ShapeRenderer srenderer, BitmapFont font, int offX, int offY);

	/**
	 * Renders the debug overlay of the element
	 * 
	 * Don't call begin end in this method - this is done by the caller
	 * 
	 * @param srenderer the ShapeRenderer (from LibGDX)
	 */
	public abstract void renderDebug(ShapeRenderer srenderer);
	
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
	 * 
	 * @return the element as [x|y]
	 */
	public abstract MenuBaseElement getElementAt(int x, int y);
	
	/**
	 * @return all children of this element (includes composite elements, no nesting)
	 */
	public abstract List<MenuBaseElement> getDirectInnerElements();

	/**
	 * @return all children of this element (includes composite elements, includes children of children)
	 */
	public List<MenuBaseElement> getAllInnerElements() {
		List<MenuBaseElement> result = new ArrayList<MenuBaseElement>();

		for (MenuBaseElement elem : getDirectInnerElements()) {
			result.add(elem);
			result.addAll(elem.getAllInnerElements());
		}
		
		return result;
	}
	
	/**
	 * @return all children of this element (excludes composite elements, no nesting)
	 */
	public List<MenuBaseElement> getDirectChildElements() {
		List<MenuBaseElement> result = new ArrayList<MenuBaseElement>();
		return result;
	}

	/**
	 * @return all children of this element (excludes composite elements, includes children of children)
	 */
	public List<MenuBaseElement> getAllChildElements() {
		List<MenuBaseElement> result = new ArrayList<MenuBaseElement>();

		for (MenuBaseElement elem : getDirectChildElements()) {
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
		for (MenuBaseElementListener lst : listeners) {
			lst.onPointerDown(this, this.identifier);
		}
	}
	
	/**
	 * Called on PointerUp event
	 * -> Touch-release on TouchDevice
	 * -> MouseUp on Desktop
	 */
	public void onPointerUp() {
		for (MenuBaseElementListener lst : listeners) {
			lst.onPointerUp(this, this.identifier);
		}
	}
	
	/**
	 * Called on PointerClicked event
	 * -> Touch on TouchDevice
	 * -> MouseClick on Desktop
	 */
	public void onPointerClicked() {
		for (MenuBaseElementListener lst : listeners) {
			lst.onClicked(this, this.identifier);
		}
	}
	
	/**
	 * Called when this element gains focus
	 */
	public void onFocusGained() {
		for (MenuBaseElementListener lst : listeners) {
			lst.onFocus(this, this.identifier);
		}
	}

	/**
	 * Called when this element looses focus
	 */
	public void onFocusLost() {
		for (MenuBaseElementListener lst : listeners) {
			lst.onFocusLost(this, this.identifier);
		}
	}
	
	/**
	 * Called when the pointer hovers over this element
	 */
	public void onStartHover() {
		for (MenuBaseElementListener lst : listeners) {
			lst.onHover(this, this.identifier);
		}
	}
	
	/**
	 * Called when the pointer no longer hovers over this element
	 */
	public void onEndHover() {
		for (MenuBaseElementListener lst : listeners) {
			lst.onHoverEnd(this, this.identifier);
		}
	}

	/**
	 * Called when a Keyboard character is typed
	 * 
	 * @param key the character
	 */
	public void onKeyTyped(char key) {
		// NOP
	}

	/**
	 * Called when a Keyboard key is pressed down
	 * 
	 * @param keycode the key code (see libgdx:Keys)
	 */
	public void onKeyDown(int keycode) {
		// NOP
	}

	/**
	 * Called when the user scrolls
	 * 
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
		if (owner == null) return false;
		
		return owner.getMenuRoot().isHovered(this);
	}

	/**
	 * Returns if this element is pressed
	 * Only works when added to an MenuLayer (returns false otherwise)
	 * 
	 * @return if this element is pressed
	 */
	public boolean isPressed() {
		if (owner == null) return false;
		
		return owner.getMenuRoot().isPressed(this);
	}
	
	/**
	 * Returns if this element is focused
	 * Only works when added to an MenuLayer (returns false otherwise)
	 * 
	 * @return if this element is focused
	 */
	public boolean isFocused() {
		if (owner == null) return false;
		
		return owner.getMenuRoot().isFocused(this);
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
		return (parent == null ? 0 : parent.getPositionX() + parent.getCoordinateOffsetX());
	}
	
	/**
	 * When (eg) rendering the coordinate matrix is translated by a specific amount
	 * This method returns this amount
	 * 
	 * @return the absolute Y coordinate system offset (relative to the layer)
	 */
	public int getCoordinateOffsetY() {
		return (parent == null ? 0 : parent.getPositionY() + parent.getCoordinateOffsetY());
	}

	/**
	 * Get the element defined by this ID 
	 * (or NULL if there is no element with this ID)
	 * 
	 * @param id the id of the element
	 * 
	 * @return the found element or NULL
	 */
	public abstract MenuBaseElement getElementByID(String id);
}
