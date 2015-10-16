package de.samdev.absgdx.framework.menu.elements;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.samdev.absgdx.framework.menu.GUITextureProvider;
import de.samdev.absgdx.framework.menu.MenuOwner;
import de.samdev.absgdx.framework.menu.events.MenuFrameListener;

/**
 * this is the root element of every menu
 *
 */
public class MenuFrame extends MenuPanel {
	
	private MenuBaseElement element_hovered = null;
	private MenuBaseElement element_pressed = null;
	private MenuBaseElement element_focused = null;
	private boolean last_cycle_mouse_state = false;
	
	private boolean renderTexture = false;
	
	/**
	 * Create a new (empty) MenuFrame
	 */
	public MenuFrame() {
		this(new ArrayList<MenuBaseElement>());
	}
	
	/**
	 * Create a new MenuFrame (with children)
	 * 
	 * @param children the children of this frame
	 */
	public MenuFrame(List<MenuBaseElement> children) {
		super(children);
		
		pack(null, null);
	}
	
	@Override
	public void pack(MenuOwner owner, MenuBaseElement parent) {
		super.pack(owner, parent);
		
		setDepth(0);
	}
	
	@Override
	public void render(SpriteBatch sbatch, BitmapFont font) {
		if (isRenderTexture()) {
			super.render(sbatch, font);
		} else {
			renderChildren(sbatch, font);
		}
	}
	
	/**
	 * Adds a new listener
	 * 
	 * @param l the new listener
	 */
	public void addFrameListener(MenuFrameListener l) {
		super.addElementListener(l);
	}
	
	/**
	 * Update the whole menu tree (this is the root node)
	 * 
	 * @param delta the delta time since the last update
	 */
	public void updateRoot(float delta) {
		updateInput();
		
		update(delta);
	}

	private void updateInput() {
		if (Gdx.input == null) return;
		
		MenuBaseElement mouseElement = getElementAt(Gdx.input.getX(), Gdx.input.getY());
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
	
	/**
	 * This method gets called when the parent layer gets an event and forwards it to the menu root
	 * 
	 * @param character the typed character
	 * @return true if the input was processed
	 */
	public boolean keyTyped(char character) {
		if (element_focused != null) {
			element_focused.onKeyTyped(character);
			
			return true;
		}
		
		return false;
	}

	/**
	 * This method gets called when the parent layer gets an event and forwards it to the menu root
	 * 
	 * @param amount the scroll amount
	 * @return true if the input was processed
	 */
	public boolean scrolled(int amount) {
		MenuBaseElement mouseElement = getElementAt(Gdx.input.getX(), Gdx.input.getY());
		
		if (mouseElement != null) {
			mouseElement.onScroll(amount);
			
			return true;
		}
		
		return false;
	}

	/**
	 * This method gets called when the parent layer gets an event and forwards it to the menu root
	 * 
	 * @param keycode the typed key-code
	 * @return true if the input was processed
	 */
	public boolean keyDown(int keycode) {
		if (element_focused != null) {
			element_focused.onKeyDown(keycode);
			
			return true;
		}
		
		return false;
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
	 * @return the count of all elements in this menu tree
	 */
	public int getElementCount() {
		return 1 + getAllChildElements().size();
	}

	/**
	 * return if this component has its texture rendered
	 * 
	 * @return true if the texture has to be rendered
	 */
	public boolean isRenderTexture() {
		return renderTexture;
	}

	/**
	 * Set if this component has its texture rendered
	 * 
	 * Set this to true and its the equivalent to an MenuPanel
	 * Set this to false and its the equivalent to an MenuContainer
	 * 
	 * @param renderTexture true if the texture has to be rendered
	 */
	public void setRenderTexture(boolean renderTexture) {
		this.renderTexture = renderTexture;
	}

	
	/**
	 * Changes the textureprovider
	 * (only used by AGDXML loading - because its possible to set the texprov on an frame root element)
	 * 
	 * @param prov teh new texture provider
	 */
	public void setTextureProvider(GUITextureProvider prov) {
		this.textureprovider = prov;
	}
}
