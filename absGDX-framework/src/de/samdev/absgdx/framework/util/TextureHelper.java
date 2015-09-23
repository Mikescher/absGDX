package de.samdev.absgdx.framework.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.samdev.absgdx.framework.util.exceptions.NonInstantiableException;

/**
 * Consists of static methods useful when loading Textures
 *
 */
public class TextureHelper {

	private TextureHelper() throws NonInstantiableException { throw new NonInstantiableException(); }

	/**
	 * Loads a single tile from an tileset Texture (that is flattened it if needed)
	 * 
	 * @param tex The tileset texture (as an resource name)
	 * @param x the x position of the tile (= the n-th tile from the left)
	 * @param y the y position of the tile (= the n-th tile from the top)
	 * @param width the width of a single tile
	 * @param height the height of a single tile
	 * @return the specific tile at position [x|y]
	 */
	public static TextureRegion loadSingleTile(String tex, int x, int y, int width, int height) {
		return loadSingleTile(new Texture(tex), x, y, width, height);
	}
	
	/**
	 * Loads a single tile from an tileset Texture (that is flattened it if needed)
	 * 
	 * @param tex The tileset texture
	 * @param x the x position of the tile (= the n-th tile from the left)
	 * @param y the y position of the tile (= the n-th tile from the top)
	 * @param width the width of a single tile
	 * @param height the height of a single tile
	 * @return the specific tile at position [x|y]
	 */
	public static TextureRegion loadSingleTile(Texture tex, int x, int y, int width, int height) {
		return new TextureRegion(tex, x*width, y*height, width, height);
	}
	
	/**
	 * Loads a Tileset Texture into an one-dimensional array (flattening it if needed)
	 * 
	 * @param tex The tileset texture (as an resource name)
	 * @param width the width of a single tile
	 * @param height the height of a single tile
	 * @return an array with the distinct tiles
	 */
	public static TextureRegion[] load1DArray(String tex, int width, int height) {
		return load1DArray(new Texture(tex), width, height);
	}
	
	/**
	 * Loads a Tileset Texture into an one-dimensional array (flattening it if needed)
	 * 
	 * @param tex The tileset texture
	 * @param width the width of a single tile
	 * @param height the height of a single tile
	 * @return an array with the distinct tiles
	 */
	public static TextureRegion[] load1DArray(Texture tex, int width, int height) {
		return load1DArray(tex, width, height, (tex.getWidth()/width) * (tex.getHeight()/height));
	}
	
	/**
	 * Loads a Tileset Texture into an one-dimensional array (flattening it if needed)
	 * 
	 * @param tex The tileset texture (as an resource name)
	 * @param width the width of a single tile
	 * @param height the height of a single tile
	 * @param count the amount of tiles to load
	 * @return an array with the distinct tiles
	 */
	public static TextureRegion[] load1DArray(String tex, int width, int height, int count) {
		return load1DArray(new Texture(tex), width, height, count);
	}
	
	/**
	 * Loads a Tileset Texture into an one-dimensional array (flattening it if needed)
	 * 
	 * @param tex The tileset texture
	 * @param width the width of a single tile
	 * @param height the height of a single tile
	 * @param count the amount of tiles to load
	 * @return an array with the distinct tiles
	 */
	public static TextureRegion[] load1DArray(Texture tex, int width, int height, int count) {
		int xc = tex.getWidth()/width;
		int yc = tex.getHeight()/height;
		
		TextureRegion[] result = new TextureRegion[count];
		
		for (int y = 0; y < yc; y++) {
			for (int x = 0; x < xc; x++) {
				result[y*xc + x] = loadSingleTile(tex, x, y, width, height);
				
				if (y*xc + x == count-1)
					return result;
			}
		}
		
		return result;
	}

	/**
	 * Loads a Tileset Texture into an two-dimensional array
	 * 
	 * @param tex the tileset texture (as an resource name)
	 * @param width the width of a single tile
	 * @param height the height of a single tile
	 * @return an array with the distinct tiles
	 */
	public static TextureRegion[][] load2DArray(String tex, int width, int height) {
		return load2DArray(new Texture(tex), width, height);
	}

	/**
	 * Loads a Tileset Texture into an two-dimensional array
	 * 
	 * @param tex the tileset texture
	 * @param width the width of a single tile
	 * @param height the height of a single tile
	 * @return an array with the distinct tiles
	 */
	public static TextureRegion[][] load2DArray(Texture tex, int width, int height) {
		return load2DArray(tex, width, height, 0, 0, (tex.getWidth()/width), (tex.getHeight()/height));
	}

	/**
	 * Loads a Tileset Texture into an two-dimensional array
	 * 
	 * @param tex the tileset texture
	 * @param width the width of a single tile
	 * @param height the height of a single tile
	 * @param offsetX the X-offset of all tiles
	 * @param offsetY the Y-offset of all tiles
	 * @return an array with the distinct tiles
	 */
	public static TextureRegion[][] load2DArray(Texture tex, int offsetX, int offsetY, int width, int height) {
		return load2DArray(tex, width, height, offsetX, offsetY, ((tex.getWidth() - offsetX)/width), ((tex.getHeight() - offsetY)/height));
	}

	/**
	 * Loads a Tileset Texture into an two-dimensional array
	 * 
	 * @param tex the tileset texture (as an resource name)
	 * @param width the width of a single tile
	 * @param height the height of a single tile
	 * @param offsetX the X-offset of all tiles
	 * @param offsetY the Y-offset of all tiles
	 * @param countX the amount of tiles horizontally
	 * @param countY the amount of tiles vertically
	 * @return an array with the distinct tiles
	 */
	public static TextureRegion[][] load2DArray(String tex, int width, int height, int offsetX, int offsetY, int countX, int countY) {
		return load2DArray(new Texture(tex), width, height, offsetX, offsetY, countX, countY);
	}
	
	/**
	 * Loads a Tileset Texture into an two-dimensional array
	 * 
	 * @param tex the tileset texture
	 * @param width the width of a single tile
	 * @param height the height of a single tile
	 * @param offsetX the X-offset of all tiles
	 * @param offsetY the Y-offset of all tiles
	 * @param countX the amount of tiles horizontally
	 * @param countY the amount of tiles vertically
	 * @return an array with the distinct tiles
	 */
	public static TextureRegion[][] load2DArray(Texture tex, int width, int height, int offsetX, int offsetY, int countX, int countY) {
		int xc = Math.min((tex.getWidth() - offsetX)  / width,  countX);
		int yc = Math.min((tex.getHeight() - offsetY) / height, countY);
		
		TextureRegion[][] result = new TextureRegion[countY][countX];
		
		for (int y = 0; y < yc; y++) {
			for (int x = 0; x < xc; x++) {
				result[y][x] = new TextureRegion(tex, offsetX + x*width, offsetY + y*height, width, height);
			}
		}
		
		return result;
	}
	
	/**
	 * Loads a Tileset Texture into an one-dimensional array (no flattening)
	 * The textures are side-by-side in the texture-file
	 * 
	 * @param tex the tileset texture
	 * @param width the width of a single tile
	 * @param height the height of a single tile
	 * @param offsetX the X-offset of all tiles
	 * @param offsetY the Y-offset of all tiles
	 * @return an array with the distinct tiles
	 */
	public static TextureRegion[] load1DArray_horz(Texture tex, int width, int height, int offsetX, int offsetY) {
		return load1DArray_horz(tex, width, height, offsetX, offsetY, (tex.getWidth() - offsetX)  / width);
	}
	
	/**
	 * Loads a Tileset Texture into an one-dimensional array (no flattening)
	 * The textures are side-by-side in the texture-file
	 * 
	 * @param tex the tileset texture
	 * @param width the width of a single tile
	 * @param height the height of a single tile
	 * @param offsetX the X-offset of all tiles
	 * @param offsetY the Y-offset of all tiles
	 * @param countX the amount of tiles horizontally
	 * @return an array with the distinct tiles
	 */
	public static TextureRegion[] load1DArray_horz(Texture tex, int width, int height, int offsetX, int offsetY, int countX) {
		int xc = Math.min((tex.getWidth() - offsetX)  / width,  countX);
		
		TextureRegion[] result = new TextureRegion[countX];
		for (int x = 0; x < xc; x++) {
			result[x] = new TextureRegion(tex, offsetX + x*width, offsetY, width, height);
		}
		
		return result;
	}
	
	/**
	 * Loads a Tileset Texture into an one-dimensional array (no flattening)
	 * The textures are on-top-of-each-other in the texture-file
	 * 
	 * @param tex the tileset texture
	 * @param width the width of a single tile
	 * @param height the height of a single tile
	 * @param offsetX the X-offset of all tiles
	 * @param offsetY the Y-offset of all tiles
	 * @return an array with the distinct tiles
	 */
	public static TextureRegion[] load1DArray_vert(Texture tex, int width, int height, int offsetX, int offsetY) {
		return load1DArray_horz(tex, width, height, offsetX, offsetY, (tex.getHeight() - offsetY) / height);
	}
	
	/**
	 * Loads a Tileset Texture into an one-dimensional array (no flattening)
	 * The textures are on-top-of-each-other in the texture-file
	 * 
	 * @param tex the tileset texture
	 * @param width the width of a single tile
	 * @param height the height of a single tile
	 * @param offsetX the X-offset of all tiles
	 * @param offsetY the Y-offset of all tiles
	 * @param countY the amount of tiles vertically
	 * @return an array with the distinct tiles
	 */
	public static TextureRegion[] load1DArray_vert(Texture tex, int width, int height, int offsetX, int offsetY, int countY) {
		int yc = Math.min((tex.getHeight() - offsetY) / height, countY);
		
		TextureRegion[] result = new TextureRegion[countY];
		for (int y = 0; y < yc; y++) {
			result[y] = new TextureRegion(tex, offsetX, offsetY + y*height, width, height);
		}
		
		return result;
	}
}
