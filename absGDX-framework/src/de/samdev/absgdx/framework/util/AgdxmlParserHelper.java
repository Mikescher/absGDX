package de.samdev.absgdx.framework.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.XmlReader.Element;

import de.samdev.absgdx.framework.menu.agdxml.AgdxmlGridDefinitions;
import de.samdev.absgdx.framework.menu.agdxml.AgdxmlGridDefinitionsUnit;
import de.samdev.absgdx.framework.menu.agdxml.AgdxmlValue;
import de.samdev.absgdx.framework.menu.agdxml.AgdxmlVectorValue;
import de.samdev.absgdx.framework.util.exceptions.AgdxmlParsingException;
import de.samdev.absgdx.framework.util.exceptions.NonInstantiableException;

public class AgdxmlParserHelper {

	private static HashMap<String, Color> COLORS = getColorMap();
	
	private AgdxmlParserHelper() throws NonInstantiableException { throw new NonInstantiableException(); }
	
	public static AgdxmlVectorValue parseVectorValue(Element element, String parameter) throws AgdxmlParsingException {
		String[] parts = parameter.split(",");
		
		if (parts.length != 2) throw new AgdxmlParsingException(element.getName() + " must have the for of \"a,b\"");

		String p1 = parts[0].trim();
		String p2 = parts[1].trim();

		return new AgdxmlVectorValue(parseNumberValue(element, p1), parseNumberValue(element, p2));
	}
	
	public static AgdxmlValue parseNumberValue(Element element, String parameter) throws AgdxmlParsingException {
		parameter = parameter.trim();
		
		if (parameter.endsWith("%")) {
			try {
				float val = Float.parseFloat(parameter.substring(0, parameter.length() - 1));
				return new AgdxmlValue(val, AgdxmlGridDefinitionsUnit.PERCENTAGE);
			} catch (NumberFormatException e) {
				throw new AgdxmlParsingException("In element" + element.getName() + " "+  parameter + " is not a number");
			}
		} else if (parameter.endsWith("*")) {
			try {
				float val = Float.parseFloat(parameter.substring(0, parameter.length() - 1));
				return new AgdxmlValue(val, AgdxmlGridDefinitionsUnit.WEIGHT);
			} catch (NumberFormatException e) {
				throw new AgdxmlParsingException("In element" + element.getName() + " "+  parameter + " is not a number");
			}
		} else {
			try {
				return new AgdxmlValue( Float.parseFloat(parameter), AgdxmlGridDefinitionsUnit.PIXEL);
			} catch (NumberFormatException e) {
				throw new AgdxmlParsingException("In element" + element.getName() + " "+  parameter + " is not a number");
			}
		}
	}
	
	public static AgdxmlGridDefinitions parseGridDefinitions(Element element) throws AgdxmlParsingException {
		Element element_cdef = element.getChildByName("grid.columndefinitions");
		Element element_rdef = element.getChildByName("grid.rowdefinitions");
		
		List<AgdxmlValue> cdef = new ArrayList<AgdxmlValue>();
		List<AgdxmlValue> rdef = new ArrayList<AgdxmlValue>();
		
		if (element_cdef == null || element_cdef.getChildrenByName("columndefinition").size == 0) {
			cdef.add(new AgdxmlValue(1, AgdxmlGridDefinitionsUnit.WEIGHT));
		} else {
			for (Element child : element_cdef.getChildrenByName("columndefinition")) {
				cdef.add(parseSingleGridDefinition(child, "width"));
			}
		}
		
		if (element_rdef == null || element_rdef.getChildrenByName("rowdefinition").size == 0) {
			rdef.add(new AgdxmlValue(1, AgdxmlGridDefinitionsUnit.WEIGHT));
		} else {
			for (Element child : element_rdef.getChildrenByName("rowdefinition")) {
				rdef.add(parseSingleGridDefinition(child, "height"));
			}
		}
		
		return new AgdxmlGridDefinitions(rdef, cdef);
	}

	private static AgdxmlValue parseSingleGridDefinition(Element element, String parametername) throws AgdxmlParsingException {
		String value = element.getAttribute(parametername, "");
		
		if (value.isEmpty()) throw new AgdxmlParsingException("Element " + element.getName() + " need the attribute " + parametername);
		
		if (value.endsWith("%")) {
			try {
				return new AgdxmlValue(Float.parseFloat(value.substring(0, value.length() - 1)), AgdxmlGridDefinitionsUnit.PERCENTAGE);
			} catch (NumberFormatException e) {
				throw new AgdxmlParsingException("In element" + element.getName() + " "+  parametername + " is not a number");
			}
		} else if (value.endsWith("*")) {
			try {
				return new AgdxmlValue(Float.parseFloat(value.substring(0, value.length() - 1)), AgdxmlGridDefinitionsUnit.WEIGHT);
			} catch (NumberFormatException e) {
				throw new AgdxmlParsingException("In element" + element.getName() + " "+  parametername + " is not a number");
			}
		} else {
			try {
				return new AgdxmlValue(Float.parseFloat(value), AgdxmlGridDefinitionsUnit.PIXEL);
			} catch (NumberFormatException e) {
				throw new AgdxmlParsingException("In element" + element.getName() + " "+  parametername + " is not a number");
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
		result.put("LIGHT_GRAY ", Color.LIGHT_GRAY);
		result.put("GRAY", Color.GRAY);
		result.put("DARK_GRAY", Color.DARK_GRAY);
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
		result.put("Light_Gray ", Color.LIGHT_GRAY);
		result.put("Gray", Color.GRAY);
		result.put("Dark_Gray", Color.DARK_GRAY);
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
		result.put("light_gray ", Color.LIGHT_GRAY);
		result.put("gray", Color.GRAY);
		result.put("dark_gray", Color.DARK_GRAY);
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
	
	public static Color getColor(String parameter) throws AgdxmlParsingException {
		if (COLORS.containsKey(parameter)) 
			return COLORS.get(parameter);
		
		if (Pattern.matches("#[0-9A-F]{6}", parameter)) {
			long v = Long.parseLong(parameter.substring(1), 16);
			return new Color(((v >> 16) & 0xFF)/255f, ((v >> 8) & 0xFF)/255f, ((v >> 0) & 0xFF)/255f, 1f);
		}
		
		throw new AgdxmlParsingException("Can't parse color: '" + parameter + "'");
	}
}
