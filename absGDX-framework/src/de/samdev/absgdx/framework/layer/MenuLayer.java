package de.samdev.absgdx.framework.layer;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.framework.menu.MenuOwner;
import de.samdev.absgdx.framework.menu.elements.MenuBaseElement;
import de.samdev.absgdx.framework.menu.elements.MenuFrame;

/**
 * A Menu Layer
 * 
 * Consists of multiple MenuBaseElements
 * 
 */
public abstract class MenuLayer extends AgdxLayer implements MenuOwner {

	private final MenuFrame root;
	private final BitmapFont font;
	
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
		root.updateRoot(delta);
	}
	
	@Override
	public boolean keyTyped(char character) {
		return getMenuRoot().keyTyped(character);
	}
	
	@Override
	public boolean scrolled(int amount) {
		return getMenuRoot().scrolled(amount);
	}
	
	@Override
	public boolean keyDown(int keycode) {
		return getMenuRoot().keyDown(keycode);
	}
	
	@Override
	public MenuFrame getMenuRoot() {
		return root;
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

	/**
	 * @return the count of all elements in this layer
	 */
	public int getElementCount() {
		return getMenuRoot().getElementCount();
	}
}
