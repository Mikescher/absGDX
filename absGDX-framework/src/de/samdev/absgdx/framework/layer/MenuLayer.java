package de.samdev.absgdx.framework.layer;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.framework.menu.elements.MenuBaseElement;
import de.samdev.absgdx.framework.menu.elements.MenuFrame;

/**
 * A Menu Layer
 * 
 * Consists of multiple MenuBaseElements
 * 
 */
public abstract class MenuLayer extends AgdxLayer {

	private final MenuFrame root;
	private final BitmapFont font;
	
	private MenuBaseElement element_hovered = null;
	private MenuBaseElement element_pressed = null;
	private MenuBaseElement element_focused = null;
	private boolean last_cycle_mouse_state = false;
	
	/**
	 * Creates a new (empty) MenuLayer
	 * 
	 * Only Element is the surrounding Frame
	 * 
	 * @param owner the layer owner
	 * @param bmpfont The default font used for Labels/Buttons etc
	 */
	public MenuLayer(AgdxGame owner, BitmapFont bmpfont) {
		super(owner);
		
		this.root = new MenuFrame(new ArrayList<MenuBaseElement>());
		this.root.setPosition(0, 0);
		this.root.setSize(owner.getScreenWidth(), owner.getScreenHeight());
		this.root.pack(this, null);
		
		this.font = bmpfont;
	}
	
	/**
	 * Creates a new MenuLayer with the root element 'layerroot'
	 * 
	 * @param owner the layer owner
	 * @param layerroot the menu root element
	 * @param bmpfont The default font used for Labels/Buttons etc
	 */
	public MenuLayer(AgdxGame owner, MenuFrame layerroot, BitmapFont bmpfont) {
		super(owner);
		
		this.root = layerroot;
		this.root.setPosition(0, 0);
		this.root.setSize(owner.getScreenWidth(), owner.getScreenHeight());
		this.root.pack(this, null);
		
		this.font = bmpfont;
	}

	@Override
	public void render(SpriteBatch sbatch, ShapeRenderer srenderer) {
		srenderer.identity();
		sbatch.getTransformMatrix().idt();
		
		srenderer.translate(0, owner.getScreenHeight(), 0);
		srenderer.scale(1, -1, 1);

		sbatch.getTransformMatrix().translate(0, owner.getScreenHeight(), 0);
		sbatch.getTransformMatrix().scale(1, -1, 1);
		
		sbatch.enableBlending();
		srenderer.setAutoShapeType(true);
		
		root.renderElement(sbatch, srenderer, font, this);
	}

	@Override
	public void update(float delta) {
		updateInput();
		
		root.update(delta);
	}

	private void updateInput() {
		MenuBaseElement mouseElement = getRoot().getElementAt(Gdx.input.getX(), Gdx.input.getY());
		boolean mdown = Gdx.input.isButtonPressed(Buttons.LEFT);
		boolean mdownflank = !last_cycle_mouse_state && mdown;
		boolean mupflank = last_cycle_mouse_state && !mdown;
		
		//########### HOVERING ###########
		
		if (mouseElement != element_hovered) {
			if (element_hovered != null) element_hovered.onEndHover();
			if (mouseElement != null) mouseElement.onStartHover();
			
			element_hovered = mouseElement;
		}
		
		//########### FOCUSING ###########
		
		if (mdownflank) {
			if (element_focused != null) element_focused.onFocusLost();
			if (mouseElement != null) mouseElement.onFocusGained();

			element_focused = mouseElement;
		}

		//########### CLICKING ###########
		
		if (mupflank && element_pressed != null) {
			element_pressed.onPointerUp();
			
			if (element_pressed == mouseElement)
				element_pressed.onPointerClicked();
			
			element_pressed = null;
		}
		
		if (mdownflank) {
			if (element_pressed != null) element_pressed.onPointerUp();
			
			if (mouseElement != null) {
				mouseElement.onPointerDown();
				
				element_pressed = mouseElement;
			}
		}
		
		if (mdown && element_pressed != mouseElement) {
			if (element_pressed != null) element_pressed.onPointerUp();
			
			element_pressed = null;
		}
		
		last_cycle_mouse_state = mdown;
	}
	
	@Override
	public boolean keyTyped(char character) {
		if (element_focused != null) {
			element_focused.onKeyTyped(character);
			
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean scrolled(int amount) {
		MenuBaseElement mouseElement = getRoot().getElementAt(Gdx.input.getX(), Gdx.input.getY());
		
		if (mouseElement != null) {
			mouseElement.onScroll(amount);
			
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if (element_focused != null) {
			element_focused.onKeyDown(keycode);
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * @return the root element (a MenuFrame)
	 */
	public MenuFrame getRoot() {
		return root;
	}
	
	/**
	 * @param el the to test element
	 * @return if the element is focused
	 */
	public boolean isFocused(MenuBaseElement el) {
		return element_focused == el;
	}

	/**
	 * @param el the to test element
	 * @return if the element is pressed
	 */
	public boolean isPressed(MenuBaseElement el) {
		return element_pressed == el;
	}

	/**
	 * @param el the to test element
	 * @return if the element is hovered
	 */
	public boolean isHovered(MenuBaseElement el) {
		return element_hovered == el;
	}

	/**
	 * @return the count of all elements in this layer
	 */
	public int getElementCount() {
		return 1 + root.getAllChildElements().size();
	}
	
	/**
	 * Get the element defined by this ID 
	 * (or NULL if there is no element with this ID)
	 * 
	 * @param id the id of the element
	 * @return the found element or NULL
	 */
	public MenuBaseElement getElementByID(String id) {
		return root.getElementByID(id);
	}
}
