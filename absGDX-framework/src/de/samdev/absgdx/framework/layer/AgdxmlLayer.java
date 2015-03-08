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
		if (! xmlRootElement.getName().equals("frame")) throw new AgdxmlParsingException("root element must be <frame>");
		
		getRoot().setBoundaries(0, 0, owner.getScreenWidth(), owner.getScreenHeight());
		
		for (int i = 0; i < xmlRootElement.getChildCount(); i++) {
			Element child = xmlRootElement.getChild(i);
			
			getRoot().addChildren(calculateGeneric(getRoot().getBoundaries(), child));
		}
	}
	
	private MenuElement calculateGeneric(Rectangle boundaries, Element xmlElement) throws AgdxmlParsingException {
		if (xmlElement.getName().equals("button")) {
			return calculateButton(boundaries, xmlElement);
		} 
		
		if (xmlElement.getName().equals("panel")) {
			return calculatePanel(boundaries, xmlElement);
		} 
		
		if (xmlElement.getName().equals("grid")) {
			return calculateGrid(boundaries, xmlElement);
		}
		
		throw new AgdxmlParsingException("Unknown element <" + xmlElement.getName() + ">");
	}

	private MenuElement calculateButton(Rectangle boundaries, Element xmlElement) throws AgdxmlParsingException {
		String id = xmlElement.getAttribute("id", "{" + java.util.UUID.randomUUID().toString() + "}");
		GUITextureProvider tprox = new GUITextureProvider();
		
		MenuButton elem = new MenuButton(id, tprox);
		
		elem.setPosition(AgdxmlParserHelper.parseVector(xmlElement, xmlElement.getAttribute("position", boundaries.x + "," + boundaries.y), boundaries));
		elem.setWidth((int)AgdxmlParserHelper.parseNumber(xmlElement, xmlElement.getAttribute("width", "" + boundaries.width), boundaries));
		elem.setHeight((int)AgdxmlParserHelper.parseNumber(xmlElement, xmlElement.getAttribute("height", "" + boundaries.height), boundaries));
		
		return elem;
	}

	private MenuElement calculatePanel(Rectangle boundaries, Element xmlElement) throws AgdxmlParsingException {
		String id = xmlElement.getAttribute("id", "{" + java.util.UUID.randomUUID().toString() + "}");
		GUITextureProvider tprox = new GUITextureProvider();
		
		MenuPanel elem = new MenuPanel(id, tprox);
		
		elem.setPosition(AgdxmlParserHelper.parseVector(xmlElement, xmlElement.getAttribute("position", boundaries.x + "," + boundaries.y), boundaries));
		elem.setWidth((int)AgdxmlParserHelper.parseNumber(xmlElement, xmlElement.getAttribute("width", "" + boundaries.width), boundaries));
		elem.setHeight((int)AgdxmlParserHelper.parseNumber(xmlElement, xmlElement.getAttribute("height", "" + boundaries.height), boundaries));
		
		Rectangle bd = new Rectangle(0, 0, elem.getWidth(), elem.getHeight());
		
		for (int i = 0; i < xmlElement.getChildCount(); i++) {
			Element child = xmlElement.getChild(i);
			
			elem.addChildren(calculateGeneric(new Rectangle(bd), child));
		}
		
		return elem;
	}

	private MenuElement calculateGrid(Rectangle boundaries, Element xmlElement) throws AgdxmlParsingException {
		String id = xmlElement.getAttribute("id", "{" + java.util.UUID.randomUUID().toString() + "}");
		GUITextureProvider tprox = new GUITextureProvider();
		
		MenuPanel elem = new MenuPanel(id, tprox);
		
		elem.setPosition(AgdxmlParserHelper.parseVector(xmlElement, xmlElement.getAttribute("position", boundaries.x + "," + boundaries.y), boundaries));
		elem.setWidth((int)AgdxmlParserHelper.parseNumber(xmlElement, xmlElement.getAttribute("width", "" + boundaries.width), boundaries));
		elem.setHeight((int)AgdxmlParserHelper.parseNumber(xmlElement, xmlElement.getAttribute("height", "" + boundaries.height), boundaries));
		
		AgdxmlGridDefinitions gdef = AgdxmlParserHelper.parseGridDefinitions(xmlElement);
		
		for (int i = 0; i < xmlElement.getChildCount(); i++) {
			Element child = xmlElement.getChild(i);
			
			int childGrid_x = child.getInt("grid.row", 0);
			int childGrid_y = child.getInt("grid.row", 0);
			
			elem.addChildren(calculateGeneric(gdef.getBoundaries(childGrid_x, childGrid_y, elem.getBoundaries()), child));
		}
		
		return elem;
	}
}
