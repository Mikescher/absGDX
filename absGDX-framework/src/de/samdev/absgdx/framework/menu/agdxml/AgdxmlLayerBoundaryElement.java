package de.samdev.absgdx.framework.menu.agdxml;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.XmlReader.Element;

import de.samdev.absgdx.framework.menu.elements.MenuBaseElement;
import de.samdev.absgdx.framework.util.AgdxmlParserHelper;
import de.samdev.absgdx.framework.util.exceptions.AgdxmlParsingException;

/**
 * Contains size and position information of an AGDXML element
 */
public class AgdxmlLayerBoundaryElement {
	/** the target element */
	public final MenuBaseElement target;

	/** the position */
	public final AgdxmlVectorValue position;

	/** the element width */
	public final AgdxmlValue width;
	/** the element height */
	public final AgdxmlValue height;

	/** the grid position (column) */
	public final int gridX;
	/** the grid position (row) */
	public final int gridY;

	/** the grid definitions of this element */
	public final AgdxmlGridDefinitions gridDefinitions;

	/** the children of this element */
	public final List<AgdxmlLayerBoundaryElement> children = new ArrayList<AgdxmlLayerBoundaryElement>();

	/**
	 * Create a new AgdxmlLayerBoundaryElement (with standard properties - only useful for the root-frame)
	 * 
	 * @param element the target element
	 * @throws AgdxmlParsingException if some definitions are 
	 */
	public AgdxmlLayerBoundaryElement(MenuBaseElement element) throws AgdxmlParsingException {
		super();
		
		this.target = element;
		
		this.position = new AgdxmlVectorValue(new AgdxmlValue(0, AgdxmlValueUnit.PERCENTAGE), new AgdxmlValue(0, AgdxmlValueUnit.PERCENTAGE));
		this.width = new AgdxmlValue(100, AgdxmlValueUnit.PERCENTAGE);
		this.height = new AgdxmlValue(100, AgdxmlValueUnit.PERCENTAGE);
		
		this.gridX = 0;
		this.gridY = 0;
		
		this.gridDefinitions = new AgdxmlGridDefinitions();
	}

	/**
	 * Create a new AgdxmlLayerBoundaryElement
	 * 
	 * @param element the target element
	 * @param xmlElement the XMLElement with the properties for this BoundaryElement
	 * @throws AgdxmlParsingException if some definitions have errors
	 */
	public AgdxmlLayerBoundaryElement(MenuBaseElement element, Element xmlElement) throws AgdxmlParsingException {
		super();
		
		this.target = element;
		
		this.position = AgdxmlParserHelper.parseVectorValue(xmlElement, xmlElement.getAttribute("position", "0,0"));
		this.width = AgdxmlParserHelper.parseNumberValue(xmlElement, xmlElement.getAttribute("width", "100%"));
		this.height = AgdxmlParserHelper.parseNumberValue(xmlElement, xmlElement.getAttribute("height", "100%"));

		this.gridX = xmlElement.getInt("grid.column", 0);
		this.gridY = xmlElement.getInt("grid.row", 0);
		
		this.gridDefinitions = AgdxmlParserHelper.parseGridDefinitions(xmlElement);
	}

	/**
	 * Get the absolute element position
	 * 
	 * @param parentboundaries the boundaries of the parent
	 * @return the absolute position
	 * @throws AgdxmlParsingException if some definitions have errors
	 */
	public Vector2 getPosition(Rectangle parentboundaries) throws AgdxmlParsingException {
		float px = position.value_0.getStarless(parentboundaries.width) + parentboundaries.x;
		float py = position.value_1.getStarless(parentboundaries.height) + parentboundaries.y;
		
		return new Vector2(px, py);
	}

	/**
	 * Get the absolute element width
	 * 
	 * @param parentboundaries the boundaries of the parent
	 * @return the absolute width
	 * @throws AgdxmlParsingException if some definitions have errors
	 */
	public float getWidth(Rectangle parentboundaries) throws AgdxmlParsingException {
		Vector2 pos = getPosition(parentboundaries);
		
		float pw = width.getStarless(parentboundaries.width) + parentboundaries.x;
		float max = (parentboundaries.x + parentboundaries.width) - pos.x;
		
		return Math.max(0, Math.min(pw, max));
	}

	/**
	 * Get the absolute element height
	 * 
	 * @param parentboundaries the boundaries of the parent
	 * @return the absolute height
	 * @throws AgdxmlParsingException if some definitions have errors
	 */
	public float getHeight(Rectangle parentboundaries) throws AgdxmlParsingException {
		Vector2 pos = getPosition(parentboundaries);
		
		float pw = height.getStarless(parentboundaries.height) + parentboundaries.y;
		float max = (parentboundaries.y + parentboundaries.height) - pos.y;
		
		return Math.max(0, Math.min(pw, max));
	}
	
	/**
	 * Update this element and all recursive child elements
	 * (Call this on the root of the BoundaryTree)
	 * 
	 * @param screenwidth the width of the screen
	 * @param screenheight the height of the screen
	 * @throws AgdxmlParsingException if some definitions have errors
	 */
	public void updateRoot(int screenwidth, int screenheight) throws AgdxmlParsingException {
		updateRecursive(new Rectangle(0, 0, screenwidth, screenheight));
	}
	
	/**
	 * Update this element and all recursive child elements
	 * 
	 * @param parentboundaries the boundaries of the parent
	 * @throws AgdxmlParsingException if some definitions have errors
	 */
	public void updateRecursive(Rectangle parentboundaries) throws AgdxmlParsingException {
		update(parentboundaries);
		
		for (AgdxmlLayerBoundaryElement child : children) {
			child.updateRecursive(gridDefinitions.getBoundaries(child.gridX, child.gridY, target.getBoundaries()));
		}
	}
	
	/**
	 * Update (only) this element
	 * 
	 * @param parentboundaries the boundaries of the parent
	 * @throws AgdxmlParsingException if some definitions have errors
	 */
	public void update(Rectangle parentboundaries) throws AgdxmlParsingException {
		target.setPosition(getPosition(parentboundaries));
		target.setWidth((int)getWidth(parentboundaries));
		target.setHeight((int)getHeight(parentboundaries));
	}
}
