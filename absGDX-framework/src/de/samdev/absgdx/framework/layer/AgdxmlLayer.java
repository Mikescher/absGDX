package de.samdev.absgdx.framework.layer;

import java.io.IOException;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.framework.menu.GUITextureProvider;
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
		} catch (IOException e) {
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
			
			getRoot().addChildren(calculateGeneric(getRoot(), getRoot().getBoundaries(), child));
		}
	}
	
	private MenuElement calculateGeneric(MenuElement parent, Rectangle boundaries, Element xmlElement) throws AgdxmlParsingException {
		if (xmlElement.getName().equals("button")) {
			return calculateButton(parent, boundaries, xmlElement);
		} 
		
		if (xmlElement.getName().equals("panel")) {
			return calculatePanel(parent, boundaries, xmlElement);
		} 
		
		if (xmlElement.getName().equals("grid")) {
			return calculateGrid(parent, boundaries, xmlElement);
		}
		
		throw new AgdxmlParsingException("Unknown element <" + xmlElement.getName() + ">");
	}

	private MenuElement calculateButton(MenuElement parent, Rectangle boundaries, Element xmlElement) throws AgdxmlParsingException {
		String id = xmlElement.getAttribute("id", "{" + java.util.UUID.randomUUID().toString() + "}");
		GUITextureProvider tprox = new GUITextureProvider();
		
		MenuButton elem = new MenuButton(id, tprox);
		
		elem.setPosition(AgdxmlParserHelper.parseVector(xmlElement, xmlElement.getAttribute("position", "0,0"), boundaries));
		elem.setWidth((int)AgdxmlParserHelper.parseNumber(xmlElement, xmlElement.getAttribute("width", "8"), boundaries));
		elem.setHeight((int)AgdxmlParserHelper.parseNumber(xmlElement, xmlElement.getAttribute("height", "8"), boundaries));
		
		return elem;
	}

	private MenuElement calculatePanel(MenuElement parent, Rectangle boundaries, Element xmlElement) throws AgdxmlParsingException {
		String id = xmlElement.getAttribute("id", "{" + java.util.UUID.randomUUID().toString() + "}");
		GUITextureProvider tprox = new GUITextureProvider();
		
		MenuPanel elem = new MenuPanel(id, tprox);
		
		elem.setPosition(AgdxmlParserHelper.parseVector(xmlElement, xmlElement.getAttribute("position", "0,0"), boundaries));
		elem.setWidth((int)AgdxmlParserHelper.parseNumber(xmlElement, xmlElement.getAttribute("width", "8"), boundaries));
		elem.setHeight((int)AgdxmlParserHelper.parseNumber(xmlElement, xmlElement.getAttribute("height", "8"), boundaries));
		
		for (int i = 0; i < xmlRootElement.getChildCount(); i++) {
			Element child = xmlRootElement.getChild(i);
			
			getRoot().addChildren(calculateGeneric(getRoot(), getRoot().getBoundaries(), child));
		}
		
		return elem;
	}

	private MenuElement calculateGrid(MenuElement parent, Rectangle boundaries, Element xmlElement) throws AgdxmlParsingException {
		
		return null;
	}
}
