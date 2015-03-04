package de.samdev.absgdx.framework.menu.elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import de.samdev.absgdx.framework.menu.attributes.HorzAlign;
import de.samdev.absgdx.framework.menu.attributes.ImageBehavior;
import de.samdev.absgdx.framework.menu.attributes.RectangleRadius;
import de.samdev.absgdx.framework.menu.attributes.TextAutoScaleMode;
import de.samdev.absgdx.framework.menu.attributes.VertAlign;
import de.samdev.absgdx.framework.menu.attributes.VisualButtonState;
import de.samdev.absgdx.framework.menu.events.MenuImageListener;

/**
 * A simple Texture Display
 */
public class MenuImage extends MenuElement {
	private TextureRegion image = null;
	
	private ImageBehavior behavior = ImageBehavior.FIT;
	
	/**
	 * Creates a new MenuImage
	 */
	public MenuImage() {
		super();
	}
	
	/**
	 * Creates a new MenuImage
	 * 
	 * @param identifier the unique button identifier
	 */
	public MenuImage(String identifier) {
		super(identifier);
	}

	@Override
	public void render(SpriteBatch sbatch, ShapeRenderer srenderer, BitmapFont font) {
		if (image == null) return;
		
		sbatch.begin();

		float texWidth;
		float texHeight;

		switch (behavior) {
		case STRETCH:
			texWidth = getWidth();
			texHeight = getHeight();
			sbatch.draw(image, getPositionX(), getPositionY() + getHeight(), getWidth(), -getHeight());
			break;

		case FIT:
			texWidth = getWidth();
			texHeight = getHeight();
			if (texWidth / texHeight > image.getRegionWidth() / 1f * image.getRegionHeight()) {
				texHeight = texWidth * (image.getRegionHeight() * 1f / image.getRegionWidth());
			} else {
				texWidth = texHeight * (image.getRegionWidth() * 1f / image.getRegionHeight());
			}
			break;

		case NOSCALE:
			texWidth = image.getRegionWidth();
			texHeight = image.getRegionHeight();
			break;

		default:
			texWidth = image.getRegionWidth();
			texHeight = image.getRegionHeight();
			break;
		}

		sbatch.draw(image, getPositionX(), getPositionY() + texHeight, texWidth, -texHeight);
		
		sbatch.end();
	}

	@Override
	public void update(float delta) {
		// NOP
	}
	
	/**
	 * Adds a new listener
	 * 
	 * @param l the new listener
	 */
	public void addImageListener(MenuImageListener l) {
		super.addElementListener(l);
	}

	@Override
	public MenuElement getElementAt(int x, int y) {
		return this;
	}

	/**
	 * @return the displayed texture
	 */
	public TextureRegion getImage() {
		return image;
	}

	/**
	 * Set the displayed texture
	 * 
	 * @param image the texture
	 */
	public void setImage(TextureRegion image) {
		this.image = image;
	}

	/**
	 * @return the current display behavior
	 */
	public ImageBehavior getBehavior() {
		return behavior;
	}

	/**
	 * Set the texture behavior (resize)
	 * 
	 * @param behavior the new behavior
	 */
	public void setBehavior(ImageBehavior behavior) {
		this.behavior = behavior;
	}
}
