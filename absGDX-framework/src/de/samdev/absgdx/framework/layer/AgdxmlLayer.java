package de.samdev.absgdx.framework.layer;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.framework.menu.GUITextureProvider;
import de.samdev.absgdx.framework.menu.agdxml.AgdxmlLayerBoundaryElement;
import de.samdev.absgdx.framework.menu.agdxml.AgdxmlParser;
import de.samdev.absgdx.framework.menu.agdxml.AgdxmlTextureProviderIDMap;
import de.samdev.absgdx.framework.menu.texdef.AgdxTextureDefinitionLoader;
import de.samdev.absgdx.framework.util.exceptions.AgdtexdefParsingException;
import de.samdev.absgdx.framework.util.exceptions.AgdxmlParsingException;

/**
 * A MenuLayer that loads the format from an AGDXML file
 * Can dynamically react on resize Events an different screen sizes
 *
 */
public abstract class AgdxmlLayer extends MenuLayer {
	private final AgdxmlParser parser;
	
	private AgdxmlTextureProviderIDMap mapAgdxmlIDs = new AgdxmlTextureProviderIDMap();
	
	/**
	 * Create a new AgdxmlLayer
	 * 
	 * @param owner the owner of the layer
	 * @param bmpfont The standard font to use
	 * @param agdxmlFile the file with the AGDXML description 
	 * 
	 * @throws AgdxmlParsingException if the agdxmlFile has Errors
	 */
	public AgdxmlLayer(AgdxGame owner, BitmapFont bmpfont, FileHandle agdxmlFile) throws AgdxmlParsingException {
		super(owner, bmpfont);

		initialize();
		
		try {
			parser = new AgdxmlParser(agdxmlFile, this, mapAgdxmlIDs);
			
			parser.parse(getMenuRoot());
		} catch (Exception e) {
			throw new AgdxmlParsingException(e);
		}
	}
	
	/**
	 * Creates a new AgdxmlLayer
	 * 
	 * @param owner the owner of the layer
	 * @param bmpfont The standard font to use
	 * @param agdxmlFileContent an XML file content with the AGDXML description 
	 * @throws AgdxmlParsingException if the agdxmlFile has Errors
	 */
	public AgdxmlLayer(AgdxGame owner, BitmapFont bmpfont, String agdxmlFileContent) throws AgdxmlParsingException {
		super(owner, bmpfont);

		initialize();
		
		try {
			parser = new AgdxmlParser(agdxmlFileContent, this, mapAgdxmlIDs);
			
			parser.parse(getMenuRoot());
		} catch (Exception e) {
			throw new AgdxmlParsingException(e);
		}
	}
	
	/**
	 * Is called before the AGDXML file is parsed
	 * you can use this method to initialize  the different maps
	 */
	public abstract void initialize();

	@Override
	public void onResize() {
		updateLayout();
	}

	private void updateLayout() {
		try {
			parser.update();
		} catch (AgdxmlParsingException e) {
			// Can not happen - because this XML element was already parsed in constructor
			e.printStackTrace();
		}
	}

	/**
	 * add a GUITextureProvider to the map (can be access with the AGDXML-property [textures] )
	 * 
	 * @param key the HashMap Key
	 * @param value the provider
	 */
	public void addAgdxmlGuiTextureProvider(String key, GUITextureProvider value) {
		mapAgdxmlIDs.putGUITextureProvider(key, value);
	}
	
	/**
	 * Load the AgdxmlGuiTextureprovider via a TextureDefinition file (and the AgdxTextureDefinitionLoader class)
	 * 
	 * @param definitionsFile the texdef file
	 * @param texture the texture
	 * @throws AgdtexdefParsingException if the texdef file cannot be parsed
	 */
	public void loadGuiTextureProviderFromTextureDefinition(FileHandle definitionsFile, Texture texture) throws AgdtexdefParsingException {
		AgdxTextureDefinitionLoader loader = new AgdxTextureDefinitionLoader(definitionsFile, texture);
		
		loader.parse();
		
		loadGuiTextureProviderFromTextureDefinition(loader);
	}
	
	/**
	 * Load the AgdxmlGuiTextureprovider via a TextureDefinition file (and the AgdxTextureDefinitionLoader class)
	 * 
	 * @param loader the loader of the texdef file (must be already parsed)
	 */
	public void loadGuiTextureProviderFromTextureDefinition(AgdxTextureDefinitionLoader loader) {
		loader.fillIdMap(mapAgdxmlIDs);
	}

	/**
	 * Add a Texture to the map (for &lt;image&gt; elements) (can can be acessed with the AGDXML-property [texture])
	 * 
	 * @param key the HashMap Key
	 * @param value the texture
	 */
	public void addAgdxmlImageTexture(String key, TextureRegion value) {
		mapAgdxmlIDs.putImageTexture(key, value);
	}

	/**
	 * Add a Texture to the map (for &lt;image&gt; elements) (can can be accessed with the AGDXML-property [texture])
	 * (this is the method needed for animated image tags - because you can supply multiple frames)
	 * 
	 * @param key the HashMap Key
	 * @param value the texture
	 */
	public void addAgdxmlImageTexture(String key, TextureRegion[] value) {
		mapAgdxmlIDs.putImageTexture(key, value);
	}

	/**
	 * get the boundary root element of the boundary tree
	 * in this all relative size information are stores
	 * 
	 * @return the root element
	 */
	public AgdxmlLayerBoundaryElement getBoundaryRootElement() {
		return parser.getBoundaryRootElement();
	}
	
	@Override
	public void onActivate() {
		super.onActivate();

		updateLayout();
	}
}
