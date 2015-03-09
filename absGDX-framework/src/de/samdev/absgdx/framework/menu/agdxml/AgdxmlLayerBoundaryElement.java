package de.samdev.absgdx.framework.menu.agdxml;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.XmlReader.Element;

import de.samdev.absgdx.framework.menu.elements.MenuElement;
import de.samdev.absgdx.framework.util.AgdxmlParserHelper;
import de.samdev.absgdx.framework.util.exceptions.AgdxmlParsingException;

public class AgdxmlLayerBoundaryElement {
	public final MenuElement target;
	
	public AgdxmlVectorValue position;

	public AgdxmlValue width;
	public AgdxmlValue height;
	
	public int gridX = 0;
	public int gridY = 0;
	
	public AgdxmlGridDefinitions gridDefinitions = new AgdxmlGridDefinitions();
	
	public List<AgdxmlLayerBoundaryElement> children = new ArrayList<AgdxmlLayerBoundaryElement>();
	
	public AgdxmlLayerBoundaryElement(MenuElement element) {
		super();
		
		this.target = element;
	}

	public void set(Element xmlElement) throws AgdxmlParsingException {
		position = AgdxmlParserHelper.parseVectorValue(xmlElement, xmlElement.getAttribute("position", "0,0"));
		width = AgdxmlParserHelper.parseNumberValue(xmlElement, xmlElement.getAttribute("width", "100%"));
		height = AgdxmlParserHelper.parseNumberValue(xmlElement, xmlElement.getAttribute("height", "100%"));

		gridX = xmlElement.getInt("grid.column", 0);
		gridY = xmlElement.getInt("grid.row", 0);
	}

	public Vector2 getPosition(Rectangle parentboundaries) throws AgdxmlParsingException {
		float px = position.value_0.get(parentboundaries.width) + parentboundaries.x;
		float py = position.value_1.get(parentboundaries.height) + parentboundaries.y;
		
		return new Vector2(px, py);
	}

	public float getWidth(Rectangle parentboundaries) throws AgdxmlParsingException {
		Vector2 pos = getPosition(parentboundaries);
		
		float pw = width.get(parentboundaries.width) + parentboundaries.x;
		float max = (parentboundaries.x + parentboundaries.width) - pos.x;
		
		return Math.max(0, Math.min(pw, max));
	}

	public float getHeight(Rectangle parentboundaries) throws AgdxmlParsingException {
		Vector2 pos = getPosition(parentboundaries);
		
		float pw = height.get(parentboundaries.height) + parentboundaries.y;
		float max = (parentboundaries.y + parentboundaries.height) - pos.y;
		
		return Math.max(0, Math.min(pw, max));
	}
	
	public void updateRoot() throws AgdxmlParsingException {
		updateRecursive(new Rectangle(0, 0, Integer.MAX_VALUE, Integer.MAX_VALUE));
	}
	
	public void updateRecursive(Rectangle parentboundaries) throws AgdxmlParsingException {
		update(parentboundaries);
		
		for (AgdxmlLayerBoundaryElement child : children) {
			child.updateRecursive(gridDefinitions.getBoundaries(child.gridX, child.gridY, target.getBoundaries()));
		}
	}
	
	public void update(Rectangle parentboundaries) throws AgdxmlParsingException {
		target.setPosition(getPosition(parentboundaries));
		target.setWidth((int)getWidth(parentboundaries));
		target.setHeight((int)getHeight(parentboundaries));
	}
}
