package de.samdev.absgdx.framework.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * An AnimationTile.
 * 
 * Can be used as base class for tiles with an simple animation
 *
 */
public abstract class AnimationTile extends Tile {
	private final TextureRegion[] animation;
	private final int animationLength;
	private final float frameDuration;
	
	private float animationPos = 0f;
	
	/**
	 * The constructor
	 * 
	 * @param textures The single frames
	 * @param animationDuration The duration for one *full* cycle (all frames)
	 */
	public AnimationTile(TextureRegion[] textures, float animationDuration) {
		super((TextureRegion)null);
		
		this.animation = textures;
		this.animationLength = textures.length;
		this.frameDuration = animationDuration / animationLength;
	}

	/**
	 * The constructor
	 * 
	 * @param textures The single frames
	 * @param animationDuration The duration for one *full* cycle (all frames)
	 */
	public AnimationTile(Texture[] textures, float animationDuration) {
		this(convertToRegion(textures), animationDuration);
	}

	private static TextureRegion[] convertToRegion(Texture[] texture) {
		TextureRegion[] result = new TextureRegion[texture.length];
		
		for (int i = 0; i < texture.length; i++) {
			result[i] = new TextureRegion(texture[i]);
		}
		
		return result;
	}
	
	@Override
	public TextureRegion getTexture() {
		return animation[(int)animationPos];
	}

	@Override
	public void update(float delta) {
		animationPos += (delta/frameDuration);
		animationPos %= animationLength;
	}
}
