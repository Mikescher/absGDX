package de.samdev.absgdx.framework.menu.elements;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import de.samdev.absgdx.framework.layer.MenuLayer;
import de.samdev.absgdx.framework.menu.GUITextureProvider;
import de.samdev.absgdx.framework.menu.attributes.ImageBehavior;
import de.samdev.absgdx.framework.menu.events.MenuImageListener;

/**
 * A simple Texture Display
 */
public class MenuImage extends MenuElement {
	private TextureRegion[] animation = null;
	private int animationLength = 1;
	private float frameDuration = 0;
	protected float animationPos = 0f;
	protected boolean isAnimationPaused = false;
	
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
	
	/**
	 * Creates a new MenuImage
	 * 
	 * @param identifier the unique button identifier
	 * @param texprovider the texture provider for this element
	 */
	public MenuImage(String identifier, GUITextureProvider texprovider) {
		super(identifier, texprovider);
	}

	@Override
	public void renderElement(SpriteBatch sbatch, ShapeRenderer srenderer, BitmapFont defaultfont, MenuLayer owner) {
		super.renderElement(sbatch, srenderer, defaultfont, owner);
		
		if (owner.owner.settings.debugMenuImageAnimation.isActive() && isAnimated()) {
			srenderer.begin(ShapeType.Filled);
			{
				srenderer.setColor(owner.owner.settings.debugMenuBordersColor.get());
				
				srenderer.rect(getPositionX(), getPositionY(), (animationPos/animationLength) * getWidth(), 3);
				srenderer.rect(getPositionX() + getWidth(), getPositionY() + getHeight(), -(animationPos/animationLength) * getWidth(), -4);
				srenderer.rect(getPositionX(), getPositionY() + getHeight(), 3, -(animationPos/animationLength) * getHeight());
				srenderer.rect(getPositionX() + getWidth(), getPositionY(), -4, (animationPos/animationLength) * getHeight());
			}
			srenderer.end();
		}
	}

	@Override
	public void render(SpriteBatch sbatch, ShapeRenderer srenderer, BitmapFont font) {
		if (getWidth() == 0 || getHeight() == 0) return;
		
		TextureRegion image = getTexture();
		if (image == null || image.getTexture() == null) return;
		
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
			if (texWidth / texHeight > image.getRegionWidth() * 1f/ image.getRegionHeight()) {
				texWidth = texHeight * (image.getRegionWidth() * 1f / image.getRegionHeight());
			} else {
				texHeight = texWidth * (image.getRegionHeight() * 1f / image.getRegionWidth());
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
	
	/**
	 * Gets the current texture - the return value can change every cycle, don't cache this
	 * 
	 * @return the texture
	 */
	public TextureRegion getTexture() {
		if (animation == null) return null;
		
		return animation[(int)animationPos];
	}

	@Override
	public void update(float delta) {
		if (isAnimated()) {
			animationPos += (delta/frameDuration);
			animationPos %= animationLength;
		}
	}
	
	/**
	 * If the Image is animated
	 * 
	 * @return if animationLength > 1
	 */
	public boolean isAnimated() {
		return animationLength > 1 && ! isAnimationPaused;
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
	 * Set the displayed texture (not animated)
	 * 
	 * @param texture the new texture
	 */
	public void setImage(TextureRegion texture) {
		this.animation = new TextureRegion[]{texture};
		this.animationLength = 1;
		this.frameDuration = 0;
		
		this.animationPos = 0;
		this.isAnimationPaused = false;
	}

	/**
	 * Set the displayed texture (animation)
	 * 
	 * @param textures the new animation array
	 * @param animationDuration The duration for one *full* cycle (all frames) 
	 */
	public void setImage(TextureRegion[] textures, float animationDuration) {
		this.animation = textures;
		this.animationLength = textures.length;
		this.frameDuration = animationDuration / animationLength;
		
		this.animationPos = 0;
		this.isAnimationPaused = false;
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

	@Override
	public List<MenuElement> getDirectInnerElements() {
		List<MenuElement> result = new ArrayList<MenuElement>();
		return result;
	}
}
