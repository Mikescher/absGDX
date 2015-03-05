package de.samdev.absgdx.framework.menu;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.samdev.absgdx.framework.menu.attributes.VisualButtonState;
import de.samdev.absgdx.framework.menu.elements.MenuButton;

/**
 * Class that provides Textures for GUI objects
 * Textures are identified by target class + identifier
 *
 */
public class GUITextureProvider {
	
	/** Identifier for a specific texture (MenuButton :: TopLeft) */
	public final static String IDENT_TEX_BUTTON_TL = "topleft";
	/** Identifier for a specific texture (MenuButton :: Top) */
	public final static String IDENT_TEX_BUTTON_TT = "top";
	/** Identifier for a specific texture (MenuButton :: TopRight) */
	public final static String IDENT_TEX_BUTTON_TR = "topright";
	/** Identifier for a specific texture (MenuButton :: Right) */
	public final static String IDENT_TEX_BUTTON_RR = "right";
	/** Identifier for a specific texture (MenuButton :: BottomRight) */
	public final static String IDENT_TEX_BUTTON_BR = "bottomright";
	/** Identifier for a specific texture (MenuButton :: Bottom) */
	public final static String IDENT_TEX_BUTTON_BB = "bottom";
	/** Identifier for a specific texture (MenuButton :: BottomLeft) */
	public final static String IDENT_TEX_BUTTON_BL = "bottomleft";
	/** Identifier for a specific texture (MenuButton :: Left) */
	public final static String IDENT_TEX_BUTTON_LL = "left";
	/** Identifier for a specific texture (MenuButton :: Center) */
	public final static String IDENT_TEX_BUTTON_CC = "center";

	/** Identifier for all MenuButton textures */
	public final static String[] IDENT_TEX_BUTTON = {
		IDENT_TEX_BUTTON_TL, IDENT_TEX_BUTTON_TT, IDENT_TEX_BUTTON_TR, 
		IDENT_TEX_BUTTON_LL, IDENT_TEX_BUTTON_CC, IDENT_TEX_BUTTON_RR, 
		IDENT_TEX_BUTTON_BL, IDENT_TEX_BUTTON_BB, IDENT_TEX_BUTTON_BR
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
	public void set(Class<MenuButton> target, String identifier, TextureRegion texture) {
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
	 * Test for textures for MenuButton
	 * 
	 * @param state the button state to test
	 * @return if all 9 Textures for the VisualButtonState has been set
	 */
	public boolean hasButtonTextures(VisualButtonState state) {
		for (String ident : IDENT_TEX_BUTTON) {
			if (get(MenuButton.class, ident + "#" + state) == null) return false;
		}
		
		return true;
	}
	
	/**
	 * Set the textures for a MenuButton
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
	 * @param state the ButtonState for which this texture is used
	 */
	public void setMenuButton(TextureRegion tex_TL, TextureRegion tex_TT, TextureRegion tex_TR, TextureRegion tex_RR, TextureRegion tex_BR, TextureRegion tex_BB, TextureRegion tex_BL, TextureRegion tex_LL, TextureRegion tex_CC, VisualButtonState state) {
		set(MenuButton.class, IDENT_TEX_BUTTON_TL + "#" + state, tex_TL);
		set(MenuButton.class, IDENT_TEX_BUTTON_TT + "#" + state, tex_TT);
		set(MenuButton.class, IDENT_TEX_BUTTON_TR + "#" + state, tex_TR);
		set(MenuButton.class, IDENT_TEX_BUTTON_RR + "#" + state, tex_RR);
		set(MenuButton.class, IDENT_TEX_BUTTON_BR + "#" + state, tex_BR);
		set(MenuButton.class, IDENT_TEX_BUTTON_BB + "#" + state, tex_BB);
		set(MenuButton.class, IDENT_TEX_BUTTON_BL + "#" + state, tex_BL);
		set(MenuButton.class, IDENT_TEX_BUTTON_LL + "#" + state, tex_LL);
		set(MenuButton.class, IDENT_TEX_BUTTON_CC + "#" + state, tex_CC);
	}
	/**
	 * Set the textures for a MenuButton
	 * 
	 * @param texture the textures ordered in a 3x3 square ( TextureRegion[Y][X] )
	 * @param state the ButtonState for which this texture is used
	 */
	public void setMenuButton(TextureRegion[][] texture, VisualButtonState state) {
		set(MenuButton.class, IDENT_TEX_BUTTON_TL + "#" + state, texture[0][0]);
		set(MenuButton.class, IDENT_TEX_BUTTON_TT + "#" + state, texture[0][1]);
		set(MenuButton.class, IDENT_TEX_BUTTON_TR + "#" + state, texture[0][2]);
		set(MenuButton.class, IDENT_TEX_BUTTON_LL + "#" + state, texture[1][0]);
		set(MenuButton.class, IDENT_TEX_BUTTON_CC + "#" + state, texture[1][1]);
		set(MenuButton.class, IDENT_TEX_BUTTON_RR + "#" + state, texture[1][2]);
		set(MenuButton.class, IDENT_TEX_BUTTON_BL + "#" + state, texture[2][0]);
		set(MenuButton.class, IDENT_TEX_BUTTON_BB + "#" + state, texture[2][1]);
		set(MenuButton.class, IDENT_TEX_BUTTON_BR + "#" + state, texture[2][2]);
	}
}
