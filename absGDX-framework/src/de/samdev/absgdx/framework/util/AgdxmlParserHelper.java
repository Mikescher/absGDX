package de.samdev.absgdx.framework.util;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.XmlReader.Element;

import de.samdev.absgdx.framework.util.exceptions.AgdxmlParsingException;
import de.samdev.absgdx.framework.util.exceptions.NonInstantiableException;

public class AgdxmlParserHelper {

	private AgdxmlParserHelper() throws NonInstantiableException { throw new NonInstantiableException(); }

	public static Vector2 parseVector(Element element, String parameter, Rectangle parentBound) throws AgdxmlParsingException {
		String[] parts = parameter.split(",");
		
		if (parts.length != 2) throw new AgdxmlParsingException(element.getName() + " must have the for of \"a,b\"");

		String p1 = parts[0].trim();
		String p2 = parts[1].trim();

		return new Vector2(parseNumber(element, parts[0].trim(), parentBound), parseNumber(element, parts[1].trim(), parentBound));
	}
	
	public static float parseNumber(Element element, String parameter, Rectangle parentBound) throws AgdxmlParsingException {
		parameter = parameter.trim();
		
		if (parameter.endsWith("%")) {
			try {
				int val = Integer.parseInt(parameter.substring(0, parameter.length() - 1));				
				return parentBound.width * (val / 100f);
			} catch (NumberFormatException e) {
				throw new AgdxmlParsingException("In element" + element.getName() + " "+  parameter + " is not a number");
			}
		} else {
			try {
				return Integer.parseInt(parameter);
			} catch (NumberFormatException e) {
				throw new AgdxmlParsingException("In element" + element.getName() + " "+  parameter + " is not a number");
			}
		}
	}
}
