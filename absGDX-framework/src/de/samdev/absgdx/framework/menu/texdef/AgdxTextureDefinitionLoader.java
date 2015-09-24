package de.samdev.absgdx.framework.menu.texdef;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import de.samdev.absgdx.framework.util.TextureHelper;
import de.samdev.absgdx.framework.util.exceptions.AgdtexdefLoadException;
import de.samdev.absgdx.framework.util.exceptions.AgdtexdefParsingException;

/**
 * Class to load the textures from an *.agdtexdef file
 *
 */
public class AgdxTextureDefinitionLoader {
	private final static String REGEX_COORDS = "^\\s*([0-9\\.]+\\s*,\\s*[0-9\\.]+)\\s+([0-9\\.]+\\s*,\\s*[0-9\\.]+)\\s*$"; //   ^\s*([0-9\.]+\s*,\s*[0-9\.]+)\s+([0-9\.]+\s*,\s*[0-9\.]+)\s*$
	private final static Pattern PATTERN_COORDS = Pattern.compile(REGEX_COORDS);
	
	private final static String TAG_TEXDEF_ROOT  = "texturedefinitions";
	private final static String TAG_GUI          = "gui";
	private final static String TAG_TEXPROVIDER  = "textureprovider";
	private final static String TAG_STATE        = "state";
	private final static String TAG_TEXTURE      = "texture";
	private final static String TAG_GROUP        = "group";
	private final static String TAG_MANUAL_ARRAY = "array";
	private final static String TAG_1D_ARRAY     = "array_1d";
	private final static String TAG_2D_ARRAY     = "array_2d";
	
	private final static String TAG_OPERATION_FLATTEN  = "flatten_array";
	private final static String TAG_OPERATION_FLIP_1D  = "flip_array_1d";
	
	private final static String ATTR_OFFSET            = "coordinates_offset";
	private final static String ATTR_TEXPROVIDER_ID    = "identifier";
	private final static String ATTR_TEXTURE_ID        = "identifier";
	private final static String ATTR_TEXTURE_COORDS    = "coordinates";
	private final static String ATTR_APPENDIX          = "appendix";
	private final static String ATTR_ARRAY_WIDTH       = "width";
	private final static String ATTR_ARRAY_HEIGHT      = "height";
	private final static String ATTR_ARRAY_SIZE_X      = "size_x";
	private final static String ATTR_ARRAY_SIZE_Y      = "size_y";
	private final static String ATTR_ARRAY_ORIENTATION = "orientation";
	private final static String ATTR_TILE_COORDS       = "tile_coords";
	
	private final Texture texture;
	private final Element xmlRootElement;
	
	/** the GuiTextureProvider from the texdef file */
	public List<Pair<String, GUITextureProvider>> gui_provider = new ArrayList<Pair<String, GUITextureProvider>>();

	/** the (non-gui) texture objects defined in the file (Texture-Array, Textures, ...) */
	public Map<String, Object> texture_objects = new HashMap<String, Object>();
	
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

	/**
	 * Return the GuiTextureProivider with the specified identifier
	 * or NULL if there is no such provider
	 * 
	 * @param identifier the identifier (case-sensitive)
	 * @return the GUITextureProvider or NULL
	 */
	public GUITextureProvider GetGuiProvider(String identifier) {
		for (Pair<String, GUITextureProvider> pair : gui_provider) {
			if (pair.getKey().equals(identifier)) return pair.getValue();
		}
		return null;
	}

	private int parseInteger(String s, int fallback) {
		try {
			return Integer.parseInt(s);
		} catch (Exception e) {
			return fallback;
		}
	}
	
	private int parseInteger(String s) throws AgdtexdefParsingException {
		try {
			return Integer.parseInt(s);
		} catch (Exception e) {
			throw new AgdtexdefParsingException("Can't parse number: " + s);
		}
	}

	private boolean parseBoolean(String s) throws AgdtexdefParsingException {
		if (s.toLowerCase().equals("true")) return true;
		if (s.toLowerCase().equals("false")) return false;
		
		throw new AgdtexdefParsingException("Can't parse boolean: " + s);
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

	private Vector2 parseOffset(Vector2 initial, Element elem) throws AgdtexdefParsingException {
		return parseVec2(elem.getAttribute(ATTR_OFFSET, "0,0")).add(initial);
	}

	private Rectangle parseCoordinates(Vector2 offset, String coords) throws AgdtexdefParsingException {
		Matcher match = PATTERN_COORDS.matcher(coords);

		if (! match.matches()) throw new AgdtexdefParsingException("Can't parse Coordinates: " + coords);
		if (match.groupCount() != 2) throw new AgdtexdefParsingException("Can't parse Coordinates: " + coords);
		
		Vector2 pos = parseVec2(match.group(1)).add(offset);
		Vector2 size = parseVec2(match.group(2));
		
		return new Rectangle(pos.x, pos.y, size.x, size.y);
	}

	private ArrayOrientation parseOrientation(String orientation) throws AgdtexdefParsingException {
		if (orientation.equalsIgnoreCase("horizontal")) return ArrayOrientation.HORIZONTAL;
		else if (orientation.equalsIgnoreCase("vertical")) return ArrayOrientation.VERTICAL;
		else throw new AgdtexdefParsingException("Can't parse ArrayOrientation: " + orientation);
	}
	
	private TextureRegion composeTextureRegion(Rectangle r) {
		return new TextureRegion(texture, (int)r.x, (int)r.y, (int)r.width, (int)r.height);
	}
	
	/**
	 * Parses the in the constructor set agdtexdef file
	 * 
	 * @throws AgdtexdefParsingException if the file is not valid
	 */
	public void parse() throws AgdtexdefParsingException {
		if (! xmlRootElement.getName().equals(TAG_TEXDEF_ROOT)) throw new AgdtexdefParsingException("root node != <texturedefinitions>");
		
		Element gui = xmlRootElement.getChildByName(TAG_GUI);
		if (gui != null) {
			Vector2 offset = parseOffset(Vector2.Zero, gui);
			
			parseGUITextureProviders(gui, offset);
		}
		
		parseTextureObjectGroup(xmlRootElement, Vector2.Zero);
	}

	//#####################################################################################################
	//############################################## GUI TAG ##############################################
	//#####################################################################################################
	
	private void parseGUITextureProviders(Element gui, Vector2 offset) throws AgdtexdefParsingException {
		Array<Element> providers = gui.getChildrenByName(TAG_TEXPROVIDER);
		
		for (Element provider_element : providers) {
			String identifier = provider_element.get(ATTR_TEXPROVIDER_ID);
			
			GUITextureProvider provider = parseTextureProvider(offset, provider_element);
			
			gui_provider.add(Pair.of(identifier, provider));
		}
	}

	/** [textureprovider] */
	private GUITextureProvider parseTextureProvider(Vector2 parent_offset, Element element) throws AgdtexdefParsingException {
		Vector2 offset = parseOffset(parent_offset, element);
		
		GUITextureProvider provider = new GUITextureProvider();
		
		for (int i = 0; i < element.getChildCount(); i++) {
			Element child = element.getChild(i);

			parseTextureProviderClassElement(provider, child.getName(), offset, child);
		}
		
		return provider;
	}

	/** [???] (classname) */
	private void parseTextureProviderClassElement(GUITextureProvider provider, String classname, Vector2 parent_offset, Element element) throws AgdtexdefParsingException {
		Vector2 offset = parseOffset(parent_offset, element);
		
		for (Element state : element.getChildrenByName(TAG_STATE)) {
			String appendix = state.getAttribute(ATTR_APPENDIX);
			
			parseTextureProviderClassElementState(provider, classname, appendix, offset, state);
		}

		for (Element texture : element.getChildrenByName(TAG_TEXTURE)) {
			parseTextureProviderTexture(provider, classname, null, offset, texture);
		}

		for (Element group : element.getChildrenByName(TAG_GROUP)) {
			parseTextureProviderClassElement(provider, classname, offset, group);
		}
	}

	/** [state] */
	private void parseTextureProviderClassElementState(GUITextureProvider provider, String classname, String appendix, Vector2 parent_offset, Element element) throws AgdtexdefParsingException {
		Vector2 offset = parseOffset(parent_offset, element);

		for (Element texture : element.getChildrenByName(TAG_TEXTURE)) {
			parseTextureProviderTexture(provider, classname, appendix, offset, texture);
		}

		for (Element group : element.getChildrenByName(TAG_GROUP)) {
			parseTextureProviderClassElementState(provider, classname, appendix, offset, group);
		}
	}

	/** [texture] */
	private void parseTextureProviderTexture(GUITextureProvider provider, String classname, String parent_appendix, Vector2 offset, Element element) throws AgdtexdefParsingException {
		String identifier = element.getAttribute(ATTR_TEXTURE_ID);
		Rectangle coordinates = parseCoordinates(offset, element.getAttribute(ATTR_TEXTURE_COORDS));
		String appendix = element.getAttribute(ATTR_APPENDIX, parent_appendix);
		TextureRegion region = composeTextureRegion(coordinates);
		
		provider.set(classname, identifier, appendix, region);
	}

	//#####################################################################################################
	//############################################ OTHER TAGS #############################################
	//#####################################################################################################
	
	private void parseTextureObjectGroup(Element element, Vector2 offset) throws AgdtexdefParsingException {
		for (int i = 0; i < element.getChildCount(); i++) {
			Element child = element.getChild(i);
			String childName = child.getName();
			if (childName.equals(TAG_GUI)) continue;
			
			if (childName.equals(TAG_TEXTURE)) {
				parseSimpleTexture(child, offset);
			} else if (childName.equals(TAG_MANUAL_ARRAY)) {
				parseManualArrayTexture(child, offset);
			} else if (childName.equals(TAG_1D_ARRAY)) {
				parseArrayTexture_1D(child, offset);
			} else if (childName.equals(TAG_2D_ARRAY)) {
				parseArrayTexture_2D(child, offset);
			} else if (childName.equals(TAG_OPERATION_FLATTEN)) {
				parseOperationFlatten(child, offset);
			} else if (childName.equals(TAG_OPERATION_FLIP_1D)) {
				parseOperationFlip1D(child, offset);
			} else if (childName.equals(TAG_GROUP)) {
				parseTextureObjectGroup(child, parseOffset(offset, child));
			} else {
				throw new AgdtexdefParsingException("Unknown Tag: " + childName);
			}
		}
	}

	private TextureRegion parseSimpleTexture(Element element, Vector2 offset) throws AgdtexdefParsingException {
		String ident = element.getAttribute(ATTR_TEXTURE_ID, null);
		Rectangle coordinates = parseCoordinates(offset, element.getAttribute(ATTR_TEXTURE_COORDS));
		boolean tileCoords = parseBoolean(element.getAttribute(ATTR_TILE_COORDS, "false"));
		
		if (tileCoords) coordinates.setPosition(coordinates.x * coordinates.width, coordinates.y * coordinates.height);
		
		TextureRegion region = composeTextureRegion(coordinates);
		
		if (ident != null) texture_objects.put(ident, region);
		
		return region;
	}

	private TextureRegion[] parseManualArrayTexture(Element element, Vector2 parent_offset) throws AgdtexdefParsingException {
		Vector2 offset = parseOffset(parent_offset, element);
		String ident = element.getAttribute(ATTR_TEXTURE_ID, null);
		Array<Element> tex_childs = element.getChildrenByName(TAG_TEXTURE);
		
		TextureRegion[] result = new TextureRegion[tex_childs.size];
		
		for (int i = 0; i < tex_childs.size; i++) {
			result[i] = parseSimpleTexture(tex_childs.get(i), offset);
		}

		if (ident != null) texture_objects.put(ident, result);
		
		return result;
	}
	
	private TextureRegion[] parseArrayTexture_1D(Element element, Vector2 parent_offset) throws AgdtexdefParsingException {
		Vector2 offset = parseOffset(parent_offset, element);
		String ident = element.getAttribute(ATTR_TEXTURE_ID, null);
		
		int width  = parseInteger(element.getAttribute(ATTR_ARRAY_WIDTH));
		int height = parseInteger(element.getAttribute(ATTR_ARRAY_HEIGHT));
		ArrayOrientation orientation = parseOrientation(element.getAttribute(ATTR_ARRAY_ORIENTATION));
		
		TextureRegion[] result;
		if (orientation == ArrayOrientation.HORIZONTAL) {
			int size_x = parseInteger(element.getAttribute(ATTR_ARRAY_SIZE_X), -1);
			
			if (size_x > 0) {
				result = TextureHelper.load1DArray_horz(texture, width, height, (int)offset.x, (int)offset.y, size_x);
			} else {
				result = TextureHelper.load1DArray_horz(texture, width, height, (int)offset.x, (int)offset.y);
			}
		} else {
			int size_y = parseInteger(element.getAttribute(ATTR_ARRAY_SIZE_Y), -1);
			
			if (size_y > 0) {
				result = TextureHelper.load1DArray_vert(texture, width, height, (int)offset.x, (int)offset.y, size_y);
			} else {
				result = TextureHelper.load1DArray_vert(texture, width, height, (int)offset.x, (int)offset.y);
			}
		}

		if (ident != null) texture_objects.put(ident, result);
		
		return result;
	}

	private TextureRegion[][] parseArrayTexture_2D(Element element, Vector2 parent_offset) throws AgdtexdefParsingException {
		Vector2 offset = parseOffset(parent_offset, element);
		String ident = element.getAttribute(ATTR_TEXTURE_ID, null);
		
		int width  = parseInteger(element.getAttribute(ATTR_ARRAY_WIDTH));
		int height = parseInteger(element.getAttribute(ATTR_ARRAY_HEIGHT));
		int size_x = parseInteger(element.getAttribute(ATTR_ARRAY_SIZE_X), -1);
		int size_y = parseInteger(element.getAttribute(ATTR_ARRAY_SIZE_Y), -1);
		
		TextureRegion[][] result;
		if (size_x > 0 && size_y > 0) {
			result = TextureHelper.load2DArray(texture, width, height, (int)offset.x, (int)offset.y, size_x, size_y);
		} else {
			result = TextureHelper.load2DArray(texture, width, height, (int)offset.x, (int)offset.y);
		}

		if (ident != null) texture_objects.put(ident, result);
		
		return result;
	}
	
	private TextureRegion[] parseOperationFlatten(Element element, Vector2 parent_offset) throws AgdtexdefParsingException {
		Vector2 offset = parseOffset(parent_offset, element);
		String ident = element.getAttribute(ATTR_TEXTURE_ID, null);
		Array<Element> tex_childs = element.getChildrenByName(TAG_2D_ARRAY);
		
		if (tex_childs.size != 1) throw new AgdtexdefParsingException("<flatten_array> need a child <array_2d>");
		
		TextureRegion[][] result_2d = parseArrayTexture_2D(tex_childs.first(), offset);
		
		int size_1 = result_2d.length;
		int size_2 = result_2d[0].length;
		TextureRegion[] result = new TextureRegion[size_1 * size_2];
		
		for (int y = 0; y < size_2; y++) {
			for (int x = 0; x < size_1; x++) {
				result[y*size_1 + x] = result_2d[x][y];
			}
		}

		if (ident != null) texture_objects.put(ident, result);
		
		return result;
	}
	
	private TextureRegion[] parseOperationFlip1D(Element element, Vector2 parent_offset) throws AgdtexdefParsingException {
		Vector2 offset = parseOffset(parent_offset, element);
		String ident = element.getAttribute(ATTR_TEXTURE_ID, null);
		Array<Element> tex_childs = element.getChildrenByName(TAG_1D_ARRAY);
		
		if (tex_childs.size != 1) throw new AgdtexdefParsingException("<flip_array_2d> need a child <array_1d>");
		
		TextureRegion[] result_1d = parseArrayTexture_1D(tex_childs.first(), offset);
		
		TextureRegion[] result = new TextureRegion[result_1d.length];
		
		for (int x = 0; x < result_1d.length; x++) {
			result[result_1d.length - (x + 1)] = result_1d[x];
		}

		if (ident != null) texture_objects.put(ident, result);
		
		return result;
	}

	/**
	 * Get a texture object defined in the agdtexdef file
	 * (or NULL if not defined)
	 * 
	 * @param identifier the texture object identifier
	 * @return the object or NULL
	 */
	public Object getTextureObject(String identifier) {
		return texture_objects.get(identifier);
	}

	/**
	 * Get a texture defined in the agdtexdef file
	 * 
	 * @param identifier the texture identifier
	 * @return the texture
	 * @throws AgdtexdefLoadException if the object is not found
	 */
	public TextureRegion getSingleTexture(String identifier) throws AgdtexdefLoadException {
		Object result = getTextureObject(identifier);
		
		if (result != null && result instanceof TextureRegion)
			return (TextureRegion)result;
		else
			throw new AgdtexdefLoadException("texture not found: " + identifier);
	}

	/**
	 * Get a texture-array defined in the agdtexdef file
	 * 
	 * @param identifier the texture-array identifier
	 * @return the array
	 * @throws AgdtexdefLoadException if the object is not found
	 */
	public TextureRegion[] getTextureArray(String identifier) throws AgdtexdefLoadException {
		Object result = getTextureObject(identifier);
		
		if (result != null && result instanceof TextureRegion[])
			return (TextureRegion[])result;
		else
			throw new AgdtexdefLoadException("texture-array-1d not found: " + identifier);
	}

	/**
	 * Get a texture-2d-array defined in the agdtexdef file
	 * 
	 * @param identifier the texture-2d-array identifier
	 * @return the 2d-array
	 * @throws AgdtexdefLoadException if the object is not found
	 */
	public TextureRegion[][] getTextureArray2D(String identifier) throws AgdtexdefLoadException {
		Object result = getTextureObject(identifier);
		
		if (result != null && result instanceof TextureRegion[][])
			return (TextureRegion[][])result;
		else
			throw new AgdtexdefLoadException("texture-array-2d not found: " + identifier);
	}
}
