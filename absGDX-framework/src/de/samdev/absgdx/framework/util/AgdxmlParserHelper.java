package de.samdev.absgdx.framework.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.XmlReader.Element;

import de.samdev.absgdx.framework.menu.agdxml.AgdxmlGridDefinitions;
import de.samdev.absgdx.framework.menu.agdxml.AgdxmlValue;
import de.samdev.absgdx.framework.menu.agdxml.AgdxmlValueUnit;
import de.samdev.absgdx.framework.menu.agdxml.AgdxmlVectorValue;
import de.samdev.absgdx.framework.menu.attributes.HorzAlign;
import de.samdev.absgdx.framework.menu.attributes.ImageBehavior;
import de.samdev.absgdx.framework.menu.attributes.RectangleRadius;
import de.samdev.absgdx.framework.menu.attributes.TextAutoScaleMode;
import de.samdev.absgdx.framework.menu.attributes.VertAlign;
import de.samdev.absgdx.framework.util.exceptions.AgdxmlParsingException;
import de.samdev.absgdx.framework.util.exceptions.NonInstantiableException;

/**
 * A collection of helper methods for the AgdmxLayer
 */
public class AgdxmlParserHelper {

	private static HashMap<String, Color> COLORS = getColorMap();
	
	private AgdxmlParserHelper() throws NonInstantiableException { throw new NonInstantiableException(); }
	
	/**
	 * parses a 2-Tupel value
	 * 
	 * @param element the XML element
	 * @param parameter the parameter value
	 * @return the 2-tupel
	 * @throws AgdxmlParsingException throw an exception if the value can't be parsed
	 */
	public static AgdxmlVectorValue parseVectorValue(Element element, String parameter) throws AgdxmlParsingException {
		String[] parts = parameter.split(",");
		
		if (parts.length != 2) throw new AgdxmlParsingException(element.getName() + " must have the for of \"a,b\"");

		String p1 = parts[0].trim();
		String p2 = parts[1].trim();

		return new AgdxmlVectorValue(parseNumberValue(element, p1), parseNumberValue(element, p2));
	}

	/**
	 * parses a single value (PIXEL | PERCENTAGE | WEIGHT)
	 * 
	 * @param element the XML element
	 * @param parameter the parameter value
	 * @return the value
	 * @throws AgdxmlParsingException throw an exception if the value can't be parsed
	 */
	public static AgdxmlValue parseNumberValue(Element element, String parameter) throws AgdxmlParsingException {
		parameter = parameter.trim();
		
		if (parameter.endsWith("%")) {
			try {
				float val = Float.parseFloat(parameter.substring(0, parameter.length() - 1));
				return new AgdxmlValue(val, AgdxmlValueUnit.PERCENTAGE);
			} catch (NumberFormatException e) {
				throw new AgdxmlParsingException("In element" + element.getName() + " "+  parameter + " is not a number");
			}
		} else if (parameter.endsWith("*")) {
			try {
				float val = Float.parseFloat(parameter.substring(0, parameter.length() - 1));
				return new AgdxmlValue(val, AgdxmlValueUnit.WEIGHT);
			} catch (NumberFormatException e) {
				throw new AgdxmlParsingException("In element" + element.getName() + " "+  parameter + " is not a number");
			}
		} else {
			try {
				return new AgdxmlValue( Float.parseFloat(parameter), AgdxmlValueUnit.PIXEL);
			} catch (NumberFormatException e) {
				throw new AgdxmlParsingException("In element" + element.getName() + " "+  parameter + " is not a number");
			}
		}
	}

	/**
	 * parses the grid definitions of an element (the definitions are defined as child elements)
	 * 
	 * @param element the XML element
	 * @return the value
	 * @throws AgdxmlParsingException throw an exception if the value can't be parsed
	 */
	public static AgdxmlGridDefinitions parseGridDefinitions(Element element) throws AgdxmlParsingException {
		Element element_cdef = element.getChildByName("grid.columndefinitions");
		Element element_rdef = element.getChildByName("grid.rowdefinitions");
		
		List<AgdxmlValue> cdef = new ArrayList<AgdxmlValue>();
		List<AgdxmlValue> rdef = new ArrayList<AgdxmlValue>();
		
		if (element_cdef == null) {
			cdef.add(new AgdxmlValue(1, AgdxmlValueUnit.WEIGHT));
		}else if (element_cdef.getAttribute("width", "").length() > 0) {
			for (String def : element_cdef.getAttribute("width").split(",")) {
				cdef.add(parseSingleGridDefinition(def.trim(), element.getName(), "width"));
			}
		} else if (element_cdef.getChildrenByName("columndefinition").size == 0) {
			cdef.add(new AgdxmlValue(1, AgdxmlValueUnit.WEIGHT));
		} else {
			for (Element child : element_cdef.getChildrenByName("columndefinition")) {
				cdef.add(parseSingleGridDefinition(child, "width"));
			}
		}
		
		if (element_rdef == null) {
			rdef.add(new AgdxmlValue(1, AgdxmlValueUnit.WEIGHT));
		}else if (element_rdef.getAttribute("height", "").length() > 0) {
			for (String def : element_rdef.getAttribute("height").split(",")) {
				rdef.add(parseSingleGridDefinition(def.trim(), element.getName(), "height"));
			}
		} else if (element_rdef.getChildrenByName("rowdefinition").size == 0) {
			rdef.add(new AgdxmlValue(1, AgdxmlValueUnit.WEIGHT));
		} else {
			for (Element child : element_rdef.getChildrenByName("rowdefinition")) {
				rdef.add(parseSingleGridDefinition(child, "height"));
			}
		}
		
		return new AgdxmlGridDefinitions(rdef, cdef);
	}

	private static AgdxmlValue parseSingleGridDefinition(Element element, String parametername) throws AgdxmlParsingException {
		return parseSingleGridDefinition(element.getAttribute(parametername, ""), element.getName(), parametername);
	}
	
	private static AgdxmlValue parseSingleGridDefinition(String value, String elementname, String parametername) throws AgdxmlParsingException {
		if (value.isEmpty()) throw new AgdxmlParsingException("Element " + elementname + " need the attribute " + parametername);
		
		if (value.endsWith("%")) {
			try {
				return new AgdxmlValue(Float.parseFloat(value.substring(0, value.length() - 1)), AgdxmlValueUnit.PERCENTAGE);
			} catch (NumberFormatException e) {
				throw new AgdxmlParsingException("In element" + elementname + " "+  parametername + " is not a number");
			}
		} else if (value.endsWith("*")) {
			try {
				return new AgdxmlValue(Float.parseFloat(value.substring(0, value.length() - 1)), AgdxmlValueUnit.WEIGHT);
			} catch (NumberFormatException e) {
				throw new AgdxmlParsingException("In element" + elementname + " "+  parametername + " is not a number");
			}
		} else {
			try {
				return new AgdxmlValue(Float.parseFloat(value), AgdxmlValueUnit.PIXEL);
			} catch (NumberFormatException e) {
				throw new AgdxmlParsingException("In element" + elementname + " "+  parametername + " is not a number");
			}
		}
	}
	
	private static HashMap<String, Color> getColorMap() {
		HashMap<String, Color> result = new HashMap<String, Color>();
		
		result.put("BLUE", Color.BLUE);
		
		result.put("CLEAR", Color.CLEAR);
		result.put("WHITE", Color.WHITE);
		result.put("BLACK", Color.BLACK);
		result.put("RED", Color.RED);
		result.put("GREEN", Color.GREEN);
		result.put("BLUE", Color.BLUE);
		result.put("LIGHT_GRAY", Color.LIGHT_GRAY);
		result.put("LIGHTGRAY", Color.LIGHT_GRAY);
		result.put("GRAY", Color.GRAY);
		result.put("DARK_GRAY", Color.DARK_GRAY);
		result.put("DARKGRAY", Color.DARK_GRAY);
		result.put("PINK", Color.PINK);
		result.put("ORANGE", Color.ORANGE);
		result.put("YELLOW", Color.YELLOW);
		result.put("MAGENTA", Color.MAGENTA);
		result.put("CYAN", Color.CYAN);
		result.put("OLIVE", Color.OLIVE);
		result.put("PURPLE", Color.PURPLE);
		result.put("MAROON", Color.MAROON);
		result.put("TEAL", Color.TEAL);
		result.put("NAVY", Color.NAVY);
		
		result.put("Clear", Color.CLEAR);
		result.put("White", Color.WHITE);
		result.put("Black", Color.BLACK);
		result.put("Red", Color.RED);
		result.put("Green", Color.GREEN);
		result.put("Blue", Color.BLUE);
		result.put("Light_Gray", Color.LIGHT_GRAY);
		result.put("LightGray", Color.LIGHT_GRAY);
		result.put("Gray", Color.GRAY);
		result.put("Dark_Gray", Color.DARK_GRAY);
		result.put("DarkGray", Color.DARK_GRAY);
		result.put("Pink", Color.PINK);
		result.put("Orange", Color.ORANGE);
		result.put("Yellow", Color.YELLOW);
		result.put("Magenta", Color.MAGENTA);
		result.put("Cyan", Color.CYAN);
		result.put("Olive", Color.OLIVE);
		result.put("Purple", Color.PURPLE);
		result.put("Maroon", Color.MAROON);
		result.put("Teal", Color.TEAL);
		result.put("Navy", Color.NAVY);
		
		result.put("clear", Color.CLEAR);
		result.put("white", Color.WHITE);
		result.put("black", Color.BLACK);
		result.put("red", Color.RED);
		result.put("green", Color.GREEN);
		result.put("blue", Color.BLUE);
		result.put("light_gray", Color.LIGHT_GRAY);
		result.put("lightgray", Color.LIGHT_GRAY);
		result.put("gray", Color.GRAY);
		result.put("dark_gray", Color.DARK_GRAY);
		result.put("darkgray", Color.DARK_GRAY);
		result.put("pink", Color.PINK);
		result.put("orange", Color.ORANGE);
		result.put("yellow", Color.YELLOW);
		result.put("magenta", Color.MAGENTA);
		result.put("cyan", Color.CYAN);
		result.put("olive", Color.OLIVE);
		result.put("purple", Color.PURPLE);
		result.put("maroon", Color.MAROON);
		result.put("teal", Color.TEAL);
		result.put("navy", Color.NAVY);
		
		return result;
	}
	
	/**
	 * Parse an Color value
	 * 
	 * @param parameter the parse-able string
	 * @return the parsed color
	 * @throws AgdxmlParsingException throw an exception if the value can't be parsed
	 */
	public static Color parseColor(String parameter) throws AgdxmlParsingException {
		if (COLORS.containsKey(parameter)) 
			return COLORS.get(parameter);
		
		if (Pattern.matches("#[0-9A-F]{6}", parameter)) {
			long v = Long.parseLong(parameter.substring(1), 16);
			return new Color(((v >> 16) & 0xFF)/255f, ((v >> 8) & 0xFF)/255f, ((v >> 0) & 0xFF)/255f, 1f);
		}
		
		throw new AgdxmlParsingException("Can't parse color: '" + parameter + "'");
	}
	
	/**
	 * Parse an Horizontal-Align value
	 * 
	 * @param parameter the parse-able string
	 * @return the parsed HAlign
	 * @throws AgdxmlParsingException throw an exception if the value can't be parsed
	 */
	public static HorzAlign parseHorizontalAlign(String parameter) throws AgdxmlParsingException {
		if (parameter.equalsIgnoreCase("LEFT"))   return HorzAlign.LEFT;
		if (parameter.equalsIgnoreCase("RIGHT"))  return HorzAlign.RIGHT;
		if (parameter.equalsIgnoreCase("CENTER")) return HorzAlign.CENTER;

		throw new AgdxmlParsingException("Can't parse H-ALign: '" + parameter + "'");
	}
	
	/**
	 * Parse an Vertical-Align value
	 * 
	 * @param parameter the parse-able string
	 * @return the parsed VAlign
	 * @throws AgdxmlParsingException throw an exception if the value can't be parsed
	 */
	public static VertAlign parseVerticalAlign(String parameter) throws AgdxmlParsingException {
		if (parameter.equalsIgnoreCase("TOP"))    return VertAlign.TOP;
		if (parameter.equalsIgnoreCase("BOTTOM")) return VertAlign.BOTTOM;
		if (parameter.equalsIgnoreCase("CENTER")) return VertAlign.CENTER;

		throw new AgdxmlParsingException("Can't parse V-ALign: '" + parameter + "'");
	}

	/**
	 * Parse an ImageBehavior value
	 * 
	 * @param parameter the parse-able string
	 * @return the parsed ImageBehavior
	 * @throws AgdxmlParsingException throw an exception if the value can't be parsed
	 */
	public static ImageBehavior parseImageBehavior(String parameter) throws AgdxmlParsingException {
		if (parameter.equalsIgnoreCase("FIT"))     return ImageBehavior.FIT;
		if (parameter.equalsIgnoreCase("NOSCALE")) return ImageBehavior.NOSCALE;
		if (parameter.equalsIgnoreCase("STRETCH")) return ImageBehavior.STRETCH;

		throw new AgdxmlParsingException("Can't parse image behaviour: '" + parameter + "'");
	}

	/**
	 * Parse an TextAutoScaleMode value
	 * 
	 * @param parameter the parse-able string
	 * @return the parsed TextAutoScaleMode
	 * @throws AgdxmlParsingException throw an exception if the value can't be parsed
	 */
	public static TextAutoScaleMode parseTextAutoScaleMode(String parameter) throws AgdxmlParsingException {
		if (parameter.equalsIgnoreCase("BOTH"))       return TextAutoScaleMode.BOTH;
		if (parameter.equalsIgnoreCase("HORIZONTAL")) return TextAutoScaleMode.HORIZONTAL;
		if (parameter.equalsIgnoreCase("VERTICAL"))   return TextAutoScaleMode.VERTICAL;
		if (parameter.equalsIgnoreCase("NONE"))       return TextAutoScaleMode.NONE;

		throw new AgdxmlParsingException("Can't parse image behaviour: '" + parameter + "'");
	}

	/**
	 * Parse an Padding value
	 * 
	 * @param parameter the parse-able string
	 * @return the parsed RectangleRadius
	 * @throws AgdxmlParsingException throw an exception if the value can't be parsed
	 */
	public static RectangleRadius parsePadding(String parameter) throws AgdxmlParsingException {
		try {
			String[] strelements = parameter.split(",");
			
			int[] elements = new int[strelements.length];
			for (int i = 0; i < strelements.length; i++) {
				elements[i] = Integer.parseInt(strelements[i].trim());
			}

			if (elements.length == 1) return new RectangleRadius(elements[0], elements[0], elements[0], elements[0]);
			if (elements.length == 2) return new RectangleRadius(elements[1], elements[0], elements[0], elements[1]);
			if (elements.length == 4) return new RectangleRadius(elements[1], elements[0], elements[2], elements[3]);
			
			throw new AgdxmlParsingException("Can't parse padding: " + parameter);
			
		} catch (NumberFormatException e) {
			throw new AgdxmlParsingException(e);
		}
	}
}
