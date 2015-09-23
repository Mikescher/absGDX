package de.samdev.absgdx.framework.menu;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.samdev.absgdx.framework.menu.attributes.CheckState;
import de.samdev.absgdx.framework.menu.attributes.FocusState;
import de.samdev.absgdx.framework.menu.attributes.TristateBoolean;
import de.samdev.absgdx.framework.menu.attributes.VisualButtonState;
import de.samdev.absgdx.framework.menu.elements.MenuButton;
import de.samdev.absgdx.framework.menu.elements.MenuCheckBox;
import de.samdev.absgdx.framework.menu.elements.MenuEdit;
import de.samdev.absgdx.framework.menu.elements.MenuPanel;
import de.samdev.absgdx.framework.menu.elements.MenuRadioButton;
import de.samdev.absgdx.framework.menu.elements.MenuSettingsTree;

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
	/** Identifier for the checkbox/radiobutton [on|off] texture */
	public final static String IDENT_TEX_CHECK_IMG = "checkimg";
	/** Identifier for the MenuSettingsTree boolean checkbox */
	public final static String IDENT_TEX_DEPTREE_BOOL = "settingstreevalue";
	/** Identifier for the MenuSettingsTree leaf checkbox */
	public final static String IDENT_TEX_DEPTREE_LEAF = "settingstreeleaf";

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
		set(target, identifier, null, texture);
	}
	
	/**
	 * Add a Texture to the provider, textures are identified by a class and an identifier
	 * 
	 * @param target the identifier class
	 * @param identifier the identifier string
	 * @param appendix an optional appendix to the identifier
	 * @param texture the texture to use
	 */
	public void set(Class<?> target, String identifier, Object appendix, TextureRegion texture) {
		set(target.getSimpleName(), identifier, appendix, texture);
	}
	
	/**
	 * Add a Texture to the provider, textures are identified by a class and an identifier
	 * 
	 * @param target the identifier classname
	 * @param identifier the identifier string
	 * @param appendix an optional appendix to the identifier
	 * @param texture the texture to use
	 */
	public void set(String target, String identifier, Object appendix, TextureRegion texture) {
		String app = (appendix == null || appendix.toString().isEmpty()) ? "" : ("#" + appendix.toString());

		String key = (target + ":" + identifier + app).toLowerCase();
		
		map.put(key, texture);
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
		return get(target, identifier, null);
	}
	
	/**
	 * Get a Texture from the provider, textures are identified by a class and an identifier
	 * Will return NULL if texture not found
	 * 
	 * @param target the identifier class
	 * @param identifier the identifier string
	 * @param appendix an optional appendix to the identifier
	 * @return the texture identified by {target + ':' + identifier + '#' + appendix}
	 */
	public TextureRegion get(Class<?> target, String identifier, Object appendix) {
		String app = (appendix == null || appendix.toString().isEmpty()) ? "" : ("#" + appendix.toString());
		
		String key = (target.getSimpleName() + ":" + identifier + app).toLowerCase();
		
		if (map.containsKey(key)) 
			return map.get(key);
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
		String key_start = (target.getSimpleName() + ":").toLowerCase();
		
		for (String key : map.keySet()) {
			if (key.startsWith(key_start))
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
		for (String ident : IDENT_TEX_GENERIC) {
			if (get(tclass, ident, appendix) == null) return false;
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
		set(tclass, IDENT_TEX_GENERIC_TL, appendix, tex_TL);
		set(tclass, IDENT_TEX_GENERIC_TT, appendix, tex_TT);
		set(tclass, IDENT_TEX_GENERIC_TR, appendix, tex_TR);
		set(tclass, IDENT_TEX_GENERIC_RR, appendix, tex_RR);
		set(tclass, IDENT_TEX_GENERIC_BR, appendix, tex_BR);
		set(tclass, IDENT_TEX_GENERIC_BB, appendix, tex_BB);
		set(tclass, IDENT_TEX_GENERIC_BL, appendix, tex_BL);
		set(tclass, IDENT_TEX_GENERIC_LL, appendix, tex_LL);
		set(tclass, IDENT_TEX_GENERIC_CC, appendix, tex_CC);
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
		set(tclass, IDENT_TEX_GENERIC_TL, appendix, texture[0][0]);
		set(tclass, IDENT_TEX_GENERIC_TT, appendix, texture[0][1]);
		set(tclass, IDENT_TEX_GENERIC_TR, appendix, texture[0][2]);
		set(tclass, IDENT_TEX_GENERIC_LL, appendix, texture[1][0]);
		set(tclass, IDENT_TEX_GENERIC_CC, appendix, texture[1][1]);
		set(tclass, IDENT_TEX_GENERIC_RR, appendix, texture[1][2]);
		set(tclass, IDENT_TEX_GENERIC_BL, appendix, texture[2][0]);
		set(tclass, IDENT_TEX_GENERIC_BB, appendix, texture[2][1]);
		set(tclass, IDENT_TEX_GENERIC_BR, appendix, texture[2][2]);
	}
	
	/**
	 * Set the textures for MenuButton
	 * 
	 * @param texture the textures ordered in a 3x3 square ( TextureRegion[Y][X] )
	 * @param state The button state
	 */
	public void setMenuButtonTexture(TextureRegion[][] texture, VisualButtonState state) {
		setGeneric9SideTexture(MenuButton.class, texture, state);
	}
	
	/**
	 * Set the textures for MenuButton
	 * 
	 * @param texture the textures ordered in a 3x3 square ( TextureRegion[Y][X] )
	 */
	public void setMenuPanelTexture(TextureRegion[][] texture) {
		setGeneric9SideTexture(MenuPanel.class, texture, null);
	}
	
	/**
	 * Set the textures for MenuButton
	 * 
	 * @param texture the textures ordered in a 3x3 square ( TextureRegion[Y][X] )
	 * @param focused If the Edit has focus
	 */
	public void setMenuEditTexture(TextureRegion[][] texture, FocusState focused) {
		setGeneric9SideTexture(MenuEdit.class, texture, focused);
	}
	
	/**
	 * Set the textures for MenuCheckbox
	 * 
	 * @param texture the texture
	 * @param checked if it is checked
	 */
	public void setMenuCheckBoxTexture(TextureRegion texture, CheckState checked) {
		set(MenuCheckBox.class, IDENT_TEX_CHECK_IMG, checked, texture);
	}
	
	/**
	 * Set the textures for MenuRadioButton
	 * 
	 * @param texture the texture
	 * @param checked if it is checked
	 */
	public void setMenuRadioButtonTexture(TextureRegion texture, CheckState checked) {
		set(MenuRadioButton.class, IDENT_TEX_CHECK_IMG, checked, texture);
	}
	
	/**
	 * Set the textures for MenuSettingsTree (check boxes)
	 * 
	 * @param texture the texture
	 * @param checked if it is checked
	 */
	public void setMenuSettingsTreeButtonTexture(TextureRegion texture, CheckState checked) {
		set(MenuSettingsTree.class, IDENT_TEX_CHECK_IMG, checked, texture);
	}
	
	/**
	 * Set the textures for MenuSettingsTree (check boxes - values (= isActive))
	 * 
	 * @param texture the texture
	 * @param state the check state
	 */
	public void setMenuSettingsTreeValueTexture(TextureRegion texture, TristateBoolean state) {
		set(MenuSettingsTree.class, IDENT_TEX_DEPTREE_BOOL, state, texture);
	}
	
	/**
	 * Set the textures for MenuSettingsTree (check boxes - leafs)
	 * 
	 * @param texture the texture
	 */
	public void setMenuSettingsTreeLeafTexture(TextureRegion texture) {
		set(MenuSettingsTree.class, IDENT_TEX_DEPTREE_LEAF, texture);
	}
	
	/**
	 * Set the textures for MenuSettingsTree
	 * 
	 * @param texture the textures ordered in a 3x3 square ( TextureRegion[Y][X] )
	 */
	public void setMenuSettingsTreeTexture(TextureRegion[][] texture) {
		setGeneric9SideTexture(MenuSettingsTree.class, texture);
	}

	/**
	 * @return the amount of included textures
	 */
	public int getRegisteredTexturesCount() {
		return map.size();
	}
}
