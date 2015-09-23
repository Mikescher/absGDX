package de.samdev.absgdx.framework.menu.texdef;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.tuple.Pair;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

import de.samdev.absgdx.framework.menu.GUITextureProvider;
import de.samdev.absgdx.framework.util.exceptions.AgdtexdefParsingException;

/**
 * Class to load the textures from an *.agdtexdef file
 *
 */
public class AgdxTextureDefinitionLoader {
	private final static String REGEX_COORDS = "^\\s*([0-9\\.]+\\s*,\\s*[0-9\\.]+)\\s+([0-9\\.]+\\s*,\\s*[0-9\\.]+)\\s*$"; //   ^\s*([0-9\.]+\s*,\s*[0-9\.]+)\s+([0-9\.]+\s*,\s*[0-9\.]+)\s*$
	private final static Pattern PATTERN_COORDS = Pattern.compile(REGEX_COORDS);
	
	private final static String TAG_TEXDEF_ROOT = "texturedefinitions";
	private final static String TAG_GUI         = "gui";
	private final static String TAG_TEXPROVIDER = "textureprovider";
	private final static String TAG_STATE       = "state";
	private final static String TAG_TEXTURE     = "texture";

	private final static String ATTR_OFFSET         = "coordinates_offset";
	private final static String ATTR_TEXPROVIDER_ID = "id";
	private final static String ATTR_TEXTURE_ID     = "identifier";
	private final static String ATTR_TEXTURE_COORDS = "coordinates";
	private final static String ATTR_APPENDIX       = "appendix";
	
	private final Texture texture;
	private final Element xmlRootElement;
	
	/** the GuiTextureProvider from the texdef file */
	public List<Pair<String, GUITextureProvider>> gui_provider = new ArrayList<Pair<String, GUITextureProvider>>();
	
	/**
	 * Creates a new AgdxTextureDefinitionLoader.
	 * Does not start parsing - use parse() for this
	 * 
	 * @param agdxmlFile the definition file
	 * @param tex the texture for the definition
	 * @throws AgdtexdefParsingException if there are xml errors in the agdtexdef file
	 */
	public AgdxTextureDefinitionLoader(FileHandle agdxmlFile, Texture tex) throws AgdtexdefParsingException {
		super();

		try {
			this.xmlRootElement = new XmlReader().parse(agdxmlFile);
			this.texture = tex;
		} catch (Exception e) {
			throw new AgdtexdefParsingException(e);
		}
	}

	private Vector2 parseVec2(String s) throws AgdtexdefParsingException {
		try {
			String[] parts = s.split(",");
			if (parts.length != 2) throw new AgdtexdefParsingException();

			int x = Integer.parseInt(parts[0].trim());
			int y = Integer.parseInt(parts[1].trim());
			
			return new Vector2(x, y);
		} catch (Exception e) {
			throw new AgdtexdefParsingException("Can't parse Vector2: " + s);
		}
	}

	private Vector2 parseOffset(Vector2 initial, Element gui) throws AgdtexdefParsingException {
		return parseVec2(gui.getAttribute(ATTR_OFFSET, "0,0")).add(initial);
	}

	private Rectangle parseCoordinates(Vector2 offset, String coords) throws AgdtexdefParsingException {
		Matcher match = PATTERN_COORDS.matcher(coords);

		if (! match.matches()) throw new AgdtexdefParsingException("Can't parse Coordinates: " + coords);
		if (match.groupCount() != 2) throw new AgdtexdefParsingException("Can't parse Coordinates: " + coords);
		
		Vector2 pos = parseVec2(match.group(1)).add(offset);
		Vector2 size = parseVec2(match.group(2));
		
		return new Rectangle(pos.x, pos.y, size.x, size.y);
	}
	
	/**
	 * Parses the in the constructor set agdtexdef file
	 * 
	 * @throws AgdtexdefParsingException if the file is not valid
	 */
	public void parse() throws AgdtexdefParsingException {
		if (! xmlRootElement.getName().equals(TAG_TEXDEF_ROOT)) throw new AgdtexdefParsingException("root node != <texturedefinitions>");
		
		Element gui = xmlRootElement.getChildByName(TAG_GUI);
		
		Vector2 offset = parseOffset(Vector2.Zero, gui);
		
		if (gui != null) {
			Array<Element> providers = gui.getChildrenByName(TAG_TEXPROVIDER);
			
			for (Element provider_element : providers) {
				String identifier = provider_element.get(ATTR_TEXPROVIDER_ID);
				
				GUITextureProvider provider = parseTextureProvider(offset, provider_element);
				
				gui_provider.add(Pair.of(identifier, provider));
			}
		}
	}

	private GUITextureProvider parseTextureProvider(Vector2 parent_offset, Element element) throws AgdtexdefParsingException {
		Vector2 offset = parseOffset(parent_offset, element);
		
		GUITextureProvider provider = new GUITextureProvider();
		
		for (int i = 0; i < element.getChildCount(); i++) {
			Element child = element.getChild(i);
			
			parseTextureProviderClassElement(provider, offset, child);
		}
		
		return provider;
	}

	private void parseTextureProviderClassElement(GUITextureProvider provider, Vector2 parent_offset, Element element) throws AgdtexdefParsingException {
		Vector2 offset = parseOffset(parent_offset, element);
		String classname = element.getName();
		
		for (Element state : element.getChildrenByName(TAG_STATE)) {
			parseTextureProviderClassElementState(provider, classname, offset, state);
		}

		for (Element texture : element.getChildrenByName(TAG_TEXTURE)) {
			parseTextureProviderTexture(provider, classname, null, offset, texture);
		}
	}

	private void parseTextureProviderClassElementState(GUITextureProvider provider, String classname, Vector2 parent_offset, Element element) throws AgdtexdefParsingException {
		Vector2 offset = parseOffset(parent_offset, element);
		String appendix = element.getAttribute(ATTR_APPENDIX);

		for (Element texture : element.getChildrenByName(TAG_TEXTURE)) {
			parseTextureProviderTexture(provider, classname, appendix, offset, texture);
		}
	}

	private void parseTextureProviderTexture(GUITextureProvider provider, String classname, String parent_appendix, Vector2 offset, Element element) throws AgdtexdefParsingException {
		String identifier = element.getAttribute(ATTR_TEXTURE_ID);
		Rectangle coordinates = parseCoordinates(offset, element.getAttribute(ATTR_TEXTURE_COORDS));
		String appendix = element.getAttribute(ATTR_APPENDIX, parent_appendix);
		
		TextureRegion region = new TextureRegion(texture, (int)coordinates.x, (int)coordinates.y, (int)coordinates.width, (int)coordinates.height);
		
		provider.set(classname, identifier, appendix, region);
	}
}
