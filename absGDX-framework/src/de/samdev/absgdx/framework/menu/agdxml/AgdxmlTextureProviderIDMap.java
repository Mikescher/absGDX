package de.samdev.absgdx.framework.menu.agdxml;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.samdev.absgdx.framework.menu.GUITextureProvider;

/**
 * This class maps the IDs used in an AGDXML file to specific objects
 * 
 * For example:
 *  - the field 'textures' gets mapped to an GUITextureProvider
 *  - the field 'texture' (in the image tag) gets mapped to an TextureRegion[]
 *
 */
public class AgdxmlTextureProviderIDMap {

	private final HashMap<String, GUITextureProvider> map_provider;
	private final HashMap<String, TextureRegion[]> map_textures;
	
	/**
	 * Create a new AgdxmlTextureProviderIDMap
	 */
	public AgdxmlTextureProviderIDMap() {
		this.map_provider = new HashMap<String, GUITextureProvider>();
		this.map_textures = new HashMap<String, TextureRegion[]>();
	}

	/**
	 * put a new TextureProvider in the Map
	 * 
	 * @param key the ID
	 * @param value the provider
	 */
	public void putGUITextureProvider(String key, GUITextureProvider value) {
		map_provider.put(key, value);
	}

	/**
	 * put a new Texture-array in the Map
	 * 
	 * @param key the ID
	 * @param value the texture-array
	 */
	public void putImageTexture(String key, TextureRegion[] value) {
		map_textures.put(key, value);
	}

	/**
	 * put a new Texture in the Map
	 * (will get converted to an one-length array)
	 * 
	 * @param key the ID
	 * @param value the texture
	 */
	public void putImageTexture(String key, TextureRegion value) {
		map_textures.put(key, new TextureRegion[]{value});
	}

	/**
	 * Get a texture from the map, or NULL if the ID is not mapped
	 * 
	 * @param key the ID
	 * @return the texture
	 */
	public TextureRegion[] getImageTexture(String key) {
		return map_textures.get(key);
	}

	/**
	 * Get a provider from the map, or NULL if the ID is not mapped
	 * 
	 * @param key the ID
	 * @return the provider
	 */
	public GUITextureProvider getGUITextureProvider(String key) {
		return map_provider.get(key); 
	}

}
