package de.samdev.absgdx.framework.layer;

import java.io.IOException;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.framework.menu.GUITextureProvider;
import de.samdev.absgdx.framework.menu.agdxml.AgdxmlGridDefinitions;
import de.samdev.absgdx.framework.menu.elements.MenuButton;
import de.samdev.absgdx.framework.menu.elements.MenuElement;
import de.samdev.absgdx.framework.menu.elements.MenuPanel;
import de.samdev.absgdx.framework.util.AgdxmlParserHelper;
import de.samdev.absgdx.framework.util.exceptions.AgdxmlParsingException;

public class AgdxmlLayer extends MenuLayer {
	private final Element xmlRootElement;
	
	public AgdxmlLayer(AgdxGame owner, BitmapFont bmpfont, FileHandle agdxmlFile) throws AgdxmlParsingException {
		super(owner, bmpfont);
		
		try {
			xmlRootElement = new XmlReader().parse(agdxmlFile);			
		} catch (Exception e) {
			throw new AgdxmlParsingException(e);
		}
		
		calculate();
	}
	
	public AgdxmlLayer(AgdxGame owner, BitmapFont bmpfont, String agdxmlFileContent) throws AgdxmlParsingException {
		super(owner, bmpfont);

		try {
			xmlRootElement = new XmlReader().parse(agdxmlFileContent);
		} catch (Exception e) {
			throw new AgdxmlParsingException(e);
		}
		
		calculate();
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
		
	}
	
	private void calculate() throws AgdxmlParsingException {
		try {
			if (xmlRootElement == null ) throw new AgdxmlParsingException("root element not found");
			if (! xmlRootElement.getName().equals("frame")) throw new AgdxmlParsingException("root element must be <frame>");
			
			getRoot().setBoundaries(0, 0, owner.getScreenWidth(), owner.getScreenHeight());
			
			for (int i = 0; i < xmlRootElement.getChildCount(); i++) {
				Element child = xmlRootElement.getChild(i);
				
				MenuElement mchild = calculateGeneric(getRoot().getBoundaries(), child);
				if (mchild != null) getRoot().addChildren(mchild);
			}
		} catch (Exception e) {
			throw new AgdxmlParsingException(e);
		}
	}
	
	private MenuElement calculateGeneric(Rectangle boundaries, Element xmlElement) throws AgdxmlParsingException {
		if (xmlElement.getName().equals("grid.columndefinitions")) return null;
		if (xmlElement.getName().equals("grid.rowdefinitions")) return null;
		
		if (xmlElement.getName().equals("button")) return calculateButton(boundaries, xmlElement);
		if (xmlElement.getName().equals("panel"))  return calculatePanel(boundaries, xmlElement);
		if (xmlElement.getName().equals("grid"))   return calculateGrid(boundaries, xmlElement);
		
		throw new AgdxmlParsingException("Unknown element <" + xmlElement.getName() + ">");
	}

	private MenuElement calculateButton(Rectangle boundaries, Element xmlElement) throws AgdxmlParsingException {
		String id = xmlElement.getAttribute("id", "{" + java.util.UUID.randomUUID().toString() + "}");
		GUITextureProvider tprox = new GUITextureProvider();
		
		MenuButton elem = new MenuButton(id, tprox);
		calculateGenericProperties(boundaries, xmlElement, elem);
		
		return elem;
	}

	private MenuElement calculatePanel(Rectangle boundaries, Element xmlElement) throws AgdxmlParsingException {
		String id = xmlElement.getAttribute("id", "{" + java.util.UUID.randomUUID().toString() + "}");
		GUITextureProvider tprox = new GUITextureProvider();
		
		MenuPanel elem = new MenuPanel(id, tprox);
		calculateGenericProperties(boundaries, xmlElement, elem);
		
		Rectangle bd = new Rectangle(0, 0, elem.getWidth(), elem.getHeight());
		
		for (int i = 0; i < xmlElement.getChildCount(); i++) {
			Element child = xmlElement.getChild(i);
			
			MenuElement mchild = calculateGeneric(new Rectangle(bd), child);
			if (mchild != null) elem.addChildren(mchild);
		}
		
		return elem;
	}

	private MenuElement calculateGrid(Rectangle boundaries, Element xmlElement) throws AgdxmlParsingException {
		String id = xmlElement.getAttribute("id", "{" + java.util.UUID.randomUUID().toString() + "}");
		GUITextureProvider tprox = new GUITextureProvider();
		
		MenuPanel elem = new MenuPanel(id, tprox);
		calculateGenericProperties(boundaries, xmlElement, elem);
		
		AgdxmlGridDefinitions gdef = AgdxmlParserHelper.parseGridDefinitions(xmlElement);
		
		for (int i = 0; i < xmlElement.getChildCount(); i++) {
			Element child = xmlElement.getChild(i);
			
			int childGrid_x = child.getInt("grid.column", 0);
			int childGrid_y = child.getInt("grid.row", 0);
			
			MenuElement mchild = calculateGeneric(gdef.getBoundaries(childGrid_x, childGrid_y, elem.getBoundaries()), child);
			if (mchild != null) elem.addChildren(mchild);
		}
		
		return elem;
	}

	protected void calculateGenericProperties(Rectangle boundaries, Element xmlElement, MenuElement elem) throws AgdxmlParsingException {
		elem.setPosition(AgdxmlParserHelper.parseVectorPosition(xmlElement, xmlElement.getAttribute("position", "0,0"), boundaries));
		elem.setWidth((int)AgdxmlParserHelper.parseNumberMax(xmlElement, "width", xmlElement.getAttribute("width", "" + boundaries.width), boundaries, (boundaries.x + boundaries.width) - elem.getPositionX()));
		elem.setHeight((int)AgdxmlParserHelper.parseNumberMax(xmlElement, "height", xmlElement.getAttribute("height", "" + boundaries.height), boundaries, (boundaries.y + boundaries.height) - elem.getPositionY()));
	}
}
