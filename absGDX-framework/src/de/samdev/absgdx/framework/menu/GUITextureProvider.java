package de.samdev.absgdx.framework.menu;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Class that provides Textures for GUI objects
 * Textures are identified by target class + identifier
 *
 */
public class GUITextureProvider {
	
	/** Identifier for a specific texture (??? :: TopLeft) */
	public final static String IDENT_TEX_GENERIC_TL = "topleft";
	/** Identifier for a specific texture (??? :: Top) */
	public final static String IDENT_TEX_GENERIC_TT = "top";
	/** Identifier for a specific texture (??? :: TopRight) */
	public final static String IDENT_TEX_GENERIC_TR = "topright";
	/** Identifier for a specific texture (??? :: Right) */
	public final static String IDENT_TEX_GENERIC_RR = "right";
	/** Identifier for a specific texture (??? :: BottomRight) */
	public final static String IDENT_TEX_GENERIC_BR = "bottomright";
	/** Identifier for a specific texture (??? :: Bottom) */
	public final static String IDENT_TEX_GENERIC_BB = "bottom";
	/** Identifier for a specific texture (??? :: BottomLeft) */
	public final static String IDENT_TEX_GENERIC_BL = "bottomleft";
	/** Identifier for a specific texture (??? :: Left) */
	public final static String IDENT_TEX_GENERIC_LL = "left";
	/** Identifier for a specific texture (??? :: Center) */
	public final static String IDENT_TEX_GENERIC_CC = "center";

	/** Identifier for all MenuButton textures */
	public final static String[] IDENT_TEX_GENERIC = {
		IDENT_TEX_GENERIC_TL, IDENT_TEX_GENERIC_TT, IDENT_TEX_GENERIC_TR, 
		IDENT_TEX_GENERIC_LL, IDENT_TEX_GENERIC_CC, IDENT_TEX_GENERIC_RR, 
		IDENT_TEX_GENERIC_BL, IDENT_TEX_GENERIC_BB, IDENT_TEX_GENERIC_BR
	};
	
	private final HashMap<String, TextureRegion> map = new HashMap<String, TextureRegion>();
	
	/**
	 * Create a new (empty) GUITextureProvider
	 */
	public GUITextureProvider() {
		//
	}
	
	/**
	 * Add a Texture to the provider, textures are identified by a class and an identifier
	 * 
	 * @param target the identifier class
	 * @param identifier the identifier string
	 * @param texture the texture to use
	 */
	public void set(Class<?> target, String identifier, TextureRegion texture) {
		map.put(target.getName() + ":" + identifier, texture);
	}

	/**
	 * Get a Texture from the provider, textures are identified by a class and an identifier
	 * Will return NULL if texture not found
	 * 
	 * @param target the identifier class
	 * @param identifier the identifier string
	 * @return the texture identified by {target + ':' + identifier}
	 */
	public TextureRegion get(Class<?> target, String identifier) {
		if (map.containsKey(target.getName() + ":" + identifier)) 
			return map.get(target.getName() + ":" + identifier);
		else 
			return null;
	}
	
	/**
	 * Test if their any registered Textures for this class
	 * 
	 * @param target the target class
	 * @return true if textures are found
	 */
	public boolean hasTextures(Class<?> target) {
		for (String key : map.keySet()) {
			if (key.startsWith(target.getName() + ":"))
				return true;
		}
		
		return false;
	}
	
	/**
	 * Test for existing generic 9Side Textures for a class
	 * @param tclass the class to test
	 * 
	 * @return if all 9 Textures for the class has been set
	 */
	public boolean hasGeneric9SideTextures(Class<?> tclass) {
		return hasGeneric9SideTextures(tclass, null);
	}
	
	/**
	 * Test for existing generic 9Side Textures for a class
	 * @param tclass the class to test
	 * @param appendix an optional appendix to the identifier
	 * 
	 * @return if all 9 Textures for the class has been set
	 */
	public boolean hasGeneric9SideTextures(Class<?> tclass, Object appendix) {
		String app = (appendix == null || appendix.toString().isEmpty()) ? "" : ("#" + appendix.toString());
		
		for (String ident : IDENT_TEX_GENERIC) {
			if (get(tclass, ident + app) == null) return false;
		}
		
		return true;
	}
	
	/**
	 * Set the textures for a specific class
	 * 
	 * @param tclass the class to test
	 * 
	 * @param tex_TL the top left corner texture
	 * @param tex_TT the top edge texture (can be repeated)
	 * @param tex_TR the top right corner texture
	 * @param tex_RR the right edge texture (can be repeated)
	 * @param tex_BR the bottom right corner texture
	 * @param tex_BB the bottom edge texture (can be repeated)
	 * @param tex_BL the bottom left corner texture
	 * @param tex_LL the left edge texture (can be repeated)
	 * @param tex_CC teh center texture (can be repeated in both axis)
	 * 
	 * @param appendix an optional appendix to the identifier
	 */
	public void setGeneric9SideTexture(Class<?> tclass, TextureRegion tex_TL, TextureRegion tex_TT, TextureRegion tex_TR, TextureRegion tex_RR, TextureRegion tex_BR, TextureRegion tex_BB, TextureRegion tex_BL, TextureRegion tex_LL, TextureRegion tex_CC, Object appendix) {
		String app = (appendix == null || appendix.toString().isEmpty()) ? "" : ("#" + appendix.toString());
		
		set(tclass, IDENT_TEX_GENERIC_TL + app, tex_TL);
		set(tclass, IDENT_TEX_GENERIC_TT + app, tex_TT);
		set(tclass, IDENT_TEX_GENERIC_TR + app, tex_TR);
		set(tclass, IDENT_TEX_GENERIC_RR + app, tex_RR);
		set(tclass, IDENT_TEX_GENERIC_BR + app, tex_BR);
		set(tclass, IDENT_TEX_GENERIC_BB + app, tex_BB);
		set(tclass, IDENT_TEX_GENERIC_BL + app, tex_BL);
		set(tclass, IDENT_TEX_GENERIC_LL + app, tex_LL);
		set(tclass, IDENT_TEX_GENERIC_CC + app, tex_CC);
	}
	
	/**
	 * Set the textures for a specific class
	 * 
	 * @param tclass the class to test
	 * @param texture the textures ordered in a 3x3 square ( TextureRegion[Y][X] )
	 */
	public void setGeneric9SideTexture(Class<?> tclass, TextureRegion[][] texture) {
		setGeneric9SideTexture(tclass, texture, null);
	}
	
	/**
	 * Set the textures for a specific class
	 * 
	 * @param tclass the class to test
	 * @param texture the textures ordered in a 3x3 square ( TextureRegion[Y][X] )
	 * 
	 * @param appendix an optional appendix to the identifier
	 */
	public void setGeneric9SideTexture(Class<?> tclass, TextureRegion[][] texture, Object appendix) {
		String app = (appendix == null || appendix.toString().isEmpty()) ? "" : ("#" + appendix.toString());
		
		set(tclass, IDENT_TEX_GENERIC_TL + app, texture[0][0]);
		set(tclass, IDENT_TEX_GENERIC_TT + app, texture[0][1]);
		set(tclass, IDENT_TEX_GENERIC_TR + app, texture[0][2]);
		set(tclass, IDENT_TEX_GENERIC_LL + app, texture[1][0]);
		set(tclass, IDENT_TEX_GENERIC_CC + app, texture[1][1]);
		set(tclass, IDENT_TEX_GENERIC_RR + app, texture[1][2]);
		set(tclass, IDENT_TEX_GENERIC_BL + app, texture[2][0]);
		set(tclass, IDENT_TEX_GENERIC_BB + app, texture[2][1]);
		set(tclass, IDENT_TEX_GENERIC_BR + app, texture[2][2]);
	}
}
