package de.samdev.absgdx.framework.layer;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.framework.menu.GUITextureProvider;
import de.samdev.absgdx.framework.menu.agdxml.AgdxmlGridDefinitions;
import de.samdev.absgdx.framework.menu.agdxml.AgdxmlGridDefinitionsUnit;
import de.samdev.absgdx.framework.menu.agdxml.AgdxmlLayerBoundaryElement;
import de.samdev.absgdx.framework.menu.agdxml.AgdxmlValue;
import de.samdev.absgdx.framework.menu.agdxml.AgdxmlVectorValue;
import de.samdev.absgdx.framework.menu.elements.MenuButton;
import de.samdev.absgdx.framework.menu.elements.MenuElement;
import de.samdev.absgdx.framework.menu.elements.MenuPanel;
import de.samdev.absgdx.framework.util.AgdxmlParserHelper;
import de.samdev.absgdx.framework.util.exceptions.AgdxmlParsingException;

public class AgdxmlLayer extends MenuLayer {
	private final AgdxmlLayerBoundaryElement boundaryRootElement;
	
	public AgdxmlLayer(AgdxGame owner, BitmapFont bmpfont, FileHandle agdxmlFile) throws AgdxmlParsingException {
		super(owner, bmpfont);
		
		try {
			boundaryRootElement = calculate(new XmlReader().parse(agdxmlFile));	
		} catch (Exception e) {
			throw new AgdxmlParsingException(e);
		}
	}
	
	public AgdxmlLayer(AgdxGame owner, BitmapFont bmpfont, String agdxmlFileContent) throws AgdxmlParsingException {
		super(owner, bmpfont);

		try {
			boundaryRootElement = calculate(new XmlReader().parse(agdxmlFileContent));
		} catch (Exception e) {
			throw new AgdxmlParsingException(e);
		}
	}

	@Override
	public void onResize() {
		try {
			recalculate();
		} catch (AgdxmlParsingException e) {
			// Can not happen - because this XML element was already parsed in constructor
			e.printStackTrace();
		}
	}

	private void recalculate() throws AgdxmlParsingException {
		getRoot().setBoundaries(0, 0, owner.getScreenWidth(), owner.getScreenHeight());
		
		boundaryRootElement.position = new AgdxmlVectorValue(new AgdxmlValue(0, AgdxmlGridDefinitionsUnit.PIXEL), new AgdxmlValue(0, AgdxmlGridDefinitionsUnit.PIXEL));
		boundaryRootElement.width = new AgdxmlValue(owner.getScreenWidth(), AgdxmlGridDefinitionsUnit.PIXEL);
		boundaryRootElement.height = new AgdxmlValue(owner.getScreenHeight(), AgdxmlGridDefinitionsUnit.PIXEL);
		
		boundaryRootElement.updateRoot();
	}
	
	private AgdxmlLayerBoundaryElement calculate(Element xmlRootElement) throws AgdxmlParsingException {
		try {
			if (xmlRootElement == null ) throw new AgdxmlParsingException("root element not found");
			if (! xmlRootElement.getName().equals("frame")) throw new AgdxmlParsingException("root element must be <frame>");
			
			AgdxmlLayerBoundaryElement result = new AgdxmlLayerBoundaryElement(getRoot());
			result.position = new AgdxmlVectorValue(new AgdxmlValue(0, AgdxmlGridDefinitionsUnit.PIXEL), new AgdxmlValue(0, AgdxmlGridDefinitionsUnit.PIXEL));
			result.width = new AgdxmlValue(owner.getScreenWidth(), AgdxmlGridDefinitionsUnit.PIXEL);
			result.height = new AgdxmlValue(owner.getScreenHeight(), AgdxmlGridDefinitionsUnit.PIXEL);
			
			getRoot().setBoundaries(0, 0, owner.getScreenWidth(), owner.getScreenHeight());
			
			for (int i = 0; i < xmlRootElement.getChildCount(); i++) {
				Element child = xmlRootElement.getChild(i);
				
				MenuElement mchild = calculateGeneric(getRoot().getBoundaries(), child, result);
				if (mchild != null) getRoot().addChildren(mchild);
			}
			
			return result;
		} catch (Exception e) {
			throw new AgdxmlParsingException(e);
		}
	}
	
	private MenuElement calculateGeneric(Rectangle boundaries, Element xmlElement, AgdxmlLayerBoundaryElement parent) throws AgdxmlParsingException {
		if (xmlElement.getName().equals("grid.columndefinitions")) return null;
		if (xmlElement.getName().equals("grid.rowdefinitions")) return null;
		
		if (xmlElement.getName().equals("button")) return calculateButton(boundaries, xmlElement, parent);
		if (xmlElement.getName().equals("panel"))  return calculatePanel(boundaries, xmlElement, parent);
		if (xmlElement.getName().equals("grid"))   return calculateGrid(boundaries, xmlElement, parent);
		
		throw new AgdxmlParsingException("Unknown element <" + xmlElement.getName() + ">");
	}

	private MenuElement calculateButton(Rectangle boundaries, Element xmlElement, AgdxmlLayerBoundaryElement parent) throws AgdxmlParsingException {
		String id = xmlElement.getAttribute("id", "{" + java.util.UUID.randomUUID().toString() + "}");
		GUITextureProvider tprox = new GUITextureProvider();
		
		MenuButton elem = new MenuButton(id, tprox);
		AgdxmlLayerBoundaryElement boundelem = new AgdxmlLayerBoundaryElement(elem);
		
		boundelem.set(xmlElement);
		boundelem.update(boundaries);
		
		parent.children.add(boundelem);
		
		return elem;
	}

	private MenuElement calculatePanel(Rectangle boundaries, Element xmlElement, AgdxmlLayerBoundaryElement parent) throws AgdxmlParsingException {
		String id = xmlElement.getAttribute("id", "{" + java.util.UUID.randomUUID().toString() + "}");
		GUITextureProvider tprox = new GUITextureProvider();

		MenuPanel elem = new MenuPanel(id, tprox);
		AgdxmlLayerBoundaryElement boundelem = new AgdxmlLayerBoundaryElement(elem);
		
		boundelem.set(xmlElement);
		boundelem.update(boundaries);
		
		parent.children.add(boundelem);
		
		Rectangle bd = new Rectangle(0, 0, elem.getWidth(), elem.getHeight());
		
		for (int i = 0; i < xmlElement.getChildCount(); i++) {
			Element child = xmlElement.getChild(i);
			
			MenuElement mchild = calculateGeneric(new Rectangle(bd), child, boundelem);
			if (mchild != null) elem.addChildren(mchild);
		}
		
		return elem;
	}

	private MenuElement calculateGrid(Rectangle boundaries, Element xmlElement, AgdxmlLayerBoundaryElement parent) throws AgdxmlParsingException {
		String id = xmlElement.getAttribute("id", "{" + java.util.UUID.randomUUID().toString() + "}");
		GUITextureProvider tprox = new GUITextureProvider();

		MenuPanel elem = new MenuPanel(id, tprox);
		AgdxmlLayerBoundaryElement boundelem = new AgdxmlLayerBoundaryElement(elem);
		
		boundelem.set(xmlElement);
		boundelem.update(boundaries);
		
		parent.children.add(boundelem);
		
		boundelem.gridDefinitions = AgdxmlParserHelper.parseGridDefinitions(xmlElement);
		
		for (int i = 0; i < xmlElement.getChildCount(); i++) {
			Element child = xmlElement.getChild(i);
			
			int childGrid_x = child.getInt("grid.column", 0);
			int childGrid_y = child.getInt("grid.row", 0);
			
			MenuElement mchild = calculateGeneric(boundelem.gridDefinitions.getBoundaries(childGrid_x, childGrid_y, elem.getBoundaries()), child, boundelem);
			if (mchild != null) elem.addChildren(mchild);
		}
		
		return elem;
	}
}
