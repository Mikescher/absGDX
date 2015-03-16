package de.samdev.absgdx.framework.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.samdev.absgdx.framework.util.exceptions.NonInstantiableException;

/**
 * Consists of static methods useful when rendering textures in the UI
 *
 */
public class MenuRenderHelper {

	private MenuRenderHelper() throws NonInstantiableException { throw new NonInstantiableException(); }

	/**
	 * Draws a texture
	 * (MenuTexture need to be pre-flipped)
	 * 
	 * @param batch the Spritebatch to render
	 * @param texture the texture
	 * @param x x position
	 * @param y y position
	 */
	public static void drawTexture(SpriteBatch batch, TextureRegion texture, float x, float y) {
		batch.draw(texture, x, y + texture.getRegionHeight(), texture.getRegionWidth(), -texture.getRegionHeight());
	}
	
	/**
	 * Draws a texture stretching
	 * (MenuTexture need to be pre-flipped)
	 * 
	 * @param batch the Sprite Batch to render
	 * @param region the texture
	 * @param x x position
	 * @param y y position
	 * @param width the target width
	 * @param height the target height
	 */
	public static void drawTextureStretched(SpriteBatch batch, TextureRegion region, float x, float y, float width, float height) {
		batch.draw(region, x, y + height, width, -height);
	}
	
	/**
	 * Draws a texture repating
	 * (MenuTexture need to be pre-flipped)
	 * 
	 * !!! WARNING !!!  This can result in A LOT of draw calls - use with care
	 * 
	 * @param batch the Sprite Batch to render
	 * @param region the texture
	 * @param x x position
	 * @param y y position
	 * @param width the target width
	 * @param height the target height
	 */
	public static void drawTextureRepeated(SpriteBatch batch, TextureRegion region, float x, float y, float width, float height) {
		float regionWidth = region.getRegionWidth(), regionHeight = region.getRegionHeight();
		float remainingX = width % regionWidth, remainingY = height % regionHeight;
		float startX = x, startY = y;
		float endX = x + width - remainingX, endY = y + height - remainingY;
		while (x < endX) {
			y = startY;
			while (y < endY) {
				batch.draw(region, x, y + regionHeight, regionWidth, -regionHeight);
				y += regionHeight;
			}
			x += regionWidth;
		}
		Texture texture = region.getTexture();
		float u = region.getU();
		float v2 = region.getV2();
		if (remainingX > 0) {
			// Right edge.
			float u2 = u + remainingX / texture.getWidth();
			float v = region.getV();
			y = startY;
			while (y < endY) {
				batch.draw(texture, x, y+regionHeight, remainingX, -regionHeight, u, v2, u2, v);
				y += regionHeight;
			}
			// Upper right corner.
			if (remainingY > 0) {
				v2 = v + remainingY / texture.getHeight();
				batch.draw(texture, x, y+remainingY, remainingX, -remainingY, u, v2, u2, v);
			}
		}
		if (remainingY > 0) {
			// Top edge.
			float u2 = region.getU2();
			float v = region.getV();
			v2 = v + remainingY / texture.getHeight();
			x = startX;
			while (x < endX) {
				batch.draw(texture, x, y+remainingY, regionWidth, -remainingY, u, v2, u2, v);
				x += regionWidth;
			}
		}
	}
}
