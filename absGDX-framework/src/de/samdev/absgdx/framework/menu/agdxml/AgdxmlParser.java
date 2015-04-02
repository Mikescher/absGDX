package de.samdev.absgdx.framework.menu.agdxml;

import java.util.HashMap;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.badlogic.gdx.utils.reflect.Method;
import com.badlogic.gdx.utils.reflect.ReflectionException;

import de.samdev.absgdx.framework.menu.GUITextureProvider;
import de.samdev.absgdx.framework.menu.MenuOwner;
import de.samdev.absgdx.framework.menu.elements.MenuBaseElement;
import de.samdev.absgdx.framework.menu.elements.MenuButton;
import de.samdev.absgdx.framework.menu.elements.MenuCheckBox;
import de.samdev.absgdx.framework.menu.elements.MenuContainer;
import de.samdev.absgdx.framework.menu.elements.MenuEdit;
import de.samdev.absgdx.framework.menu.elements.MenuFrame;
import de.samdev.absgdx.framework.menu.elements.MenuImage;
import de.samdev.absgdx.framework.menu.elements.MenuLabel;
import de.samdev.absgdx.framework.menu.elements.MenuPanel;
import de.samdev.absgdx.framework.menu.elements.MenuRadioButton;
import de.samdev.absgdx.framework.menu.elements.MenuSettingsTree;
import de.samdev.absgdx.framework.menu.events.MenuButtonListener;
import de.samdev.absgdx.framework.menu.events.MenuCheckboxListener;
import de.samdev.absgdx.framework.menu.events.MenuContainerListener;
import de.samdev.absgdx.framework.menu.events.MenuEditListener;
import de.samdev.absgdx.framework.menu.events.MenuImageListener;
import de.samdev.absgdx.framework.menu.events.MenuLabelListener;
import de.samdev.absgdx.framework.menu.events.MenuRadioButtonListener;
import de.samdev.absgdx.framework.menu.events.MenuSettingsTreeListener;
import de.samdev.absgdx.framework.util.AgdxmlParserHelper;
import de.samdev.absgdx.framework.util.dependentProperties.DependentProperty;
import de.samdev.absgdx.framework.util.exceptions.AgdxmlParsingException;

/**
 * This class is used to parse an AGDXML file for the AGDXML Layer class
 * 
 * Use the parse() method to create an layout
 * and the update() method to update an layout (e.g. after an resize)
 *
 */
public class AgdxmlParser {

	private AgdxmlLayerBoundaryElement boundaryRootElement = null;
	
	private final Element xmlRootElement;
	private final MenuOwner layer;
	
	private final AgdxmlTextureProviderIDMap mapAgdxmlIDs;
	
	/**
	 * Create an new AGDXML parser
	 * 
	 * @param agdxmlFile the file handle to the AGDXML file
	 * @param layer the owner for the new layout (GameLayer, MenuLayer, ...)
	 * @param map_provider the map of used Texture Providers
	 * 
	 * @throws AgdxmlParsingException if the AGDXML file cannot be opened or is not well formed
	 */
	public AgdxmlParser(FileHandle agdxmlFile, MenuOwner layer, AgdxmlTextureProviderIDMap map_provider) throws AgdxmlParsingException {
		super();
		
		try {
			this.xmlRootElement = new XmlReader().parse(agdxmlFile);
			this.layer = layer;
			
			this.mapAgdxmlIDs = map_provider;	
		} catch (Exception e) {
			throw new AgdxmlParsingException(e);
		}
	}

	/**
	 * Create an new AGDXML parser
	 * 
	 * @param agdxmlFileContent the content of the AGDXML file
	 * @param layer the owner for the new layout (GameLayer, MenuLayer, ...)
	 * @param map_provider the map of used Texture Providers
	 * 
	 * @throws AgdxmlParsingException if the AGDXML file content is not well formed
	 */
	public AgdxmlParser(String agdxmlFileContent, MenuOwner layer, AgdxmlTextureProviderIDMap map_provider) throws AgdxmlParsingException {
		super();
		
		try {
			this.xmlRootElement = new XmlReader().parse(agdxmlFileContent);
			this.layer = layer;
			
			this.mapAgdxmlIDs = map_provider;		
		} catch (Exception e) {
			throw new AgdxmlParsingException(e);
		}
	}

	/**
	 * Parses the AGDXML file and creates the layout which it defines
	 * 
	 * @param root the root element on which we create our layout
	 * 
	 * @throws AgdxmlParsingException if there are errors in the AGDXML file
	 */
	public void parse(MenuFrame root) throws AgdxmlParsingException {
		try {
			if (xmlRootElement == null ) throw new AgdxmlParsingException("root element not found");
			if (! xmlRootElement.getName().equals("frame")) throw new AgdxmlParsingException("root element must be <frame>");
			
			AgdxmlLayerBoundaryElement result = new AgdxmlLayerBoundaryElement(root);
			
			root.setBoundaries(0, 0, layer.getAgdxGame().getScreenWidth(), layer.getAgdxGame().getScreenHeight());

			if (xmlRootElement.getAttribute("visible", null) != null) root.setVisible(xmlRootElement.getAttribute("visible").toLowerCase().equals("true"));
			if (xmlRootElement.getAttribute("renderTexture", null) != null) root.setRenderTexture(xmlRootElement.getAttribute("renderTexture").toLowerCase().equals("true"));
			
			GUITextureProvider rootProvider = getTextureProviderFromMap(xmlRootElement, new GUITextureProvider());
			
			for (int i = 0; i < xmlRootElement.getChildCount(); i++) {
				Element child = xmlRootElement.getChild(i);
				
				MenuBaseElement mchild = calculateGeneric(root.getBoundaries(), child, result, rootProvider);
				if (mchild != null) root.addChildren(mchild);
			}
			
			boundaryRootElement = result;
		} catch (Exception e) {
			throw new AgdxmlParsingException(e);
		}
	}
	
	/**
	 * Updates the layout (only call this after an layout was creates with parse() )
	 * Call this for example after an resize
	 * 
	 * @throws AgdxmlParsingException if there are errors in the AGDXML file (should almost never happen - because the file was already parsed once)
	 */
	public void update() throws AgdxmlParsingException {
		boundaryRootElement.updateRoot(layer.getAgdxGame().getScreenWidth(), layer.getAgdxGame().getScreenHeight());
	}
	
	private TextureRegion getSingleImageTextureFromMap(Element xmlElement) {
		TextureRegion[] result = mapAgdxmlIDs.getImageTexture(xmlElement.getAttribute("texture", null));
		if (result == null) return new TextureRegion();
		if (result.length < 1) return new TextureRegion();
		return result[0];
	}
	
	private TextureRegion[] getMultiImageTextureFromMap(Element xmlElement) {
		TextureRegion[] result = mapAgdxmlIDs.getImageTexture(xmlElement.getAttribute("texture", null));
		if (result == null) result = new TextureRegion[]{ new TextureRegion() };
		return result;
	}
	
	private GUITextureProvider getTextureProviderFromMap(Element xmlElement, GUITextureProvider defaultProvider) {
		String attr = xmlElement.getAttribute("textures", null);
		if (attr == null) return defaultProvider;
		
		GUITextureProvider result = mapAgdxmlIDs.getGUITextureProvider(attr);
		if (result == null) return new GUITextureProvider();
		
		return result;
	}
	
	private MenuBaseElement calculateGeneric(Rectangle boundaries, Element xmlElement, AgdxmlLayerBoundaryElement parent, GUITextureProvider rootProvider) throws AgdxmlParsingException {
		if (xmlElement.getName().equals("grid.columndefinitions")) return null;
		if (xmlElement.getName().equals("grid.rowdefinitions")) return null;
		
		try {
			if (xmlElement.getName().equals("panel"))        return calculatePanel(boundaries, xmlElement, parent, rootProvider);
			if (xmlElement.getName().equals("grid"))         return calculateGrid(boundaries, xmlElement, parent, rootProvider);
			if (xmlElement.getName().equals("button"))       return calculateButton(boundaries, xmlElement, parent, rootProvider);
			if (xmlElement.getName().equals("image"))        return calculateImage(boundaries, xmlElement, parent, rootProvider);
			if (xmlElement.getName().equals("checkbox"))     return calculateCheckBox(boundaries, xmlElement, parent, rootProvider);
			if (xmlElement.getName().equals("radiobutton"))  return calculateRadioButton(boundaries, xmlElement, parent, rootProvider);
			if (xmlElement.getName().equals("label"))        return calculateLabel(boundaries, xmlElement, parent, rootProvider);
			if (xmlElement.getName().equals("edit"))         return calculateEdit(boundaries, xmlElement, parent, rootProvider);
			if (xmlElement.getName().equals("settingstree")) return calculateSettingsTree(boundaries, xmlElement, parent, rootProvider);
		} catch (ReflectionException e) {
			throw new AgdxmlParsingException(e);
		}
		
		throw new AgdxmlParsingException("Unknown element <" + xmlElement.getName() + ">");
	}

	private MenuBaseElement calculateSettingsTree(Rectangle boundaries, Element xmlElement, AgdxmlLayerBoundaryElement parent, GUITextureProvider rootProvider) throws AgdxmlParsingException, ReflectionException {
		String id = xmlElement.getAttribute("id", "{" + java.util.UUID.randomUUID().toString() + "}");
		GUITextureProvider tprox = getTextureProviderFromMap(xmlElement, rootProvider);
		
		MenuSettingsTree elem = new MenuSettingsTree(id, tprox, layer.getAgdxGame().settings.root);
		AgdxmlLayerBoundaryElement boundelem = new AgdxmlLayerBoundaryElement(elem, xmlElement);
		boundelem.update(boundaries);
		
		if (xmlElement.getAttribute("visible", null) != null)        elem.setVisible(xmlElement.getAttribute("visible").toLowerCase().equals("true"));
		if (xmlElement.getAttribute("rowGap", null) != null)         elem.setRowGap(xmlElement.getIntAttribute("rowGap"));
		if (xmlElement.getAttribute("rowHeight", null) != null)      elem.setRowHeight(xmlElement.getIntAttribute("rowHeight"));
		if (xmlElement.getAttribute("fontColor", null) != null)      elem.setColor(AgdxmlParserHelper.parseColor(xmlElement.getAttribute("fontColor")));
		if (xmlElement.getAttribute("scrollbarColor", null) != null) elem.setScrollbarColor(AgdxmlParserHelper.parseColor(xmlElement.getAttribute("scrollbarColor")));
		if (xmlElement.getAttribute("scrollbarWidth",null) != null)  elem.setRowGap(xmlElement.getIntAttribute("scrollbarWidth"));
		
		final HashMap<String, Method> events = new HashMap<String, Method>();
		
		if (xmlElement.getAttribute("onPointerUp", null) != null)   events.put("onPointerUp",   layer.getDeclaredMethod(xmlElement.getAttribute("onPointerUp"),   MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onPointerDown", null) != null) events.put("onPointerDown", layer.getDeclaredMethod(xmlElement.getAttribute("onPointerDown"), MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onHoverEnd", null) != null)    events.put("onHoverEnd",    layer.getDeclaredMethod(xmlElement.getAttribute("onHoverEnd"),    MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onHover", null) != null)       events.put("onHover",       layer.getDeclaredMethod(xmlElement.getAttribute("onHover"),       MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onFocusLost", null) != null)   events.put("onFocusLost",   layer.getDeclaredMethod(xmlElement.getAttribute("onFocusLost"),   MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onFocus", null) != null)       events.put("onFocus",       layer.getDeclaredMethod(xmlElement.getAttribute("onFocus"),       MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onClicked", null) != null)     events.put("onClicked",     layer.getDeclaredMethod(xmlElement.getAttribute("onClicked"),     MenuBaseElement.class, String.class));
		
		if (xmlElement.getAttribute("onPropertyChanged", null) != null) events.put("onPropertyChanged", layer.getDeclaredMethod(xmlElement.getAttribute("onPropertyChanged"), MenuBaseElement.class, String.class, DependentProperty.class));
		
		elem.addSettingsTreeListener(new MenuSettingsTreeListener() {
			@Override
			public void onPointerUp(MenuBaseElement element, String identifier) {
				if (events.containsKey("onPointerUp")) try { events.get("onPointerUp").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onPointerDown(MenuBaseElement element, String identifier) {
				if (events.containsKey("onPointerDown")) try { events.get("onPointerDown").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onHoverEnd(MenuBaseElement element, String identifier) {
				if (events.containsKey("onHoverEnd")) try { events.get("onHoverEnd").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onHover(MenuBaseElement element, String identifier) {
				if (events.containsKey("onHover")) try { events.get("onHover").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onFocusLost(MenuBaseElement element, String identifier) {
				if (events.containsKey("onFocusLost")) try { events.get("onFocusLost").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onFocus(MenuBaseElement element, String identifier) {
				if (events.containsKey("onFocus")) try { events.get("onFocus").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onClicked(MenuBaseElement element, String identifier) {
				if (events.containsKey("onClicked")) try { events.get("onClicked").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onPropertyChanged(MenuBaseElement element, String identifier, DependentProperty property) {
				if (events.containsKey("onPropertyChanged")) try { events.get("onPropertyChanged").invoke(layer, element, identifier, property); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
		});
		
		parent.children.add(boundelem);
		
		return elem;
	}

	private MenuBaseElement calculateLabel(Rectangle boundaries, Element xmlElement, AgdxmlLayerBoundaryElement parent, GUITextureProvider rootProvider) throws AgdxmlParsingException, ReflectionException {
		String id = xmlElement.getAttribute("id", "{" + java.util.UUID.randomUUID().toString() + "}");
		GUITextureProvider tprox = getTextureProviderFromMap(xmlElement, rootProvider);
		
		MenuLabel elem = new MenuLabel(id, tprox);
		AgdxmlLayerBoundaryElement boundelem = new AgdxmlLayerBoundaryElement(elem, xmlElement);
		boundelem.update(boundaries);
		
		if (xmlElement.getAttribute("visible", null) != null)   elem.setVisible(xmlElement.getAttribute("visible").toLowerCase().equals("true"));
		if (xmlElement.getAttribute("content", null) != null)   elem.setContent(xmlElement.getAttribute("content"));
		if (xmlElement.getAttribute("halign", null) != null)    elem.setHorizontalAlign(AgdxmlParserHelper.parseHorizontalAlign(xmlElement.getAttribute("halign")));
		if (xmlElement.getAttribute("valign", null) != null)    elem.setVerticalAlign(AgdxmlParserHelper.parseVerticalAlign(xmlElement.getAttribute("valign")));
		if (xmlElement.getAttribute("fontScale", null) != null) elem.setFontScale(xmlElement.getFloat("fontScale"));
		if (xmlElement.getAttribute("fontColor", null) != null) elem.setColor(AgdxmlParserHelper.parseColor(xmlElement.getAttribute("fontColor")));
		if (xmlElement.getAttribute("autoScale", null) != null) elem.setAutoScale(AgdxmlParserHelper.parseTextAutoScaleMode(xmlElement.getAttribute("autoScale")));
		
		final HashMap<String, Method> events = new HashMap<String, Method>();
		
		if (xmlElement.getAttribute("onPointerUp", null) != null)   events.put("onPointerUp",   layer.getDeclaredMethod(xmlElement.getAttribute("onPointerUp"),   MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onPointerDown", null) != null) events.put("onPointerDown", layer.getDeclaredMethod(xmlElement.getAttribute("onPointerDown"), MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onHoverEnd", null) != null)    events.put("onHoverEnd",    layer.getDeclaredMethod(xmlElement.getAttribute("onHoverEnd"),    MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onHover", null) != null)       events.put("onHover",       layer.getDeclaredMethod(xmlElement.getAttribute("onHover"),       MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onFocusLost", null) != null)   events.put("onFocusLost",   layer.getDeclaredMethod(xmlElement.getAttribute("onFocusLost"),   MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onFocus", null) != null)       events.put("onFocus",       layer.getDeclaredMethod(xmlElement.getAttribute("onFocus"),       MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onClicked", null) != null)     events.put("onClicked",     layer.getDeclaredMethod(xmlElement.getAttribute("onClicked"),     MenuBaseElement.class, String.class));
		
		if (xmlElement.getAttribute("onContentChanged", null) != null) events.put("onContentChanged", layer.getDeclaredMethod(xmlElement.getAttribute("onContentChanged"), MenuBaseElement.class, String.class, String.class));
		
		elem.addLabelListener(new MenuLabelListener() {
			@Override
			public void onPointerUp(MenuBaseElement element, String identifier) {
				if (events.containsKey("onPointerUp")) try { events.get("onPointerUp").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onPointerDown(MenuBaseElement element, String identifier) {
				if (events.containsKey("onPointerDown")) try { events.get("onPointerDown").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onHoverEnd(MenuBaseElement element, String identifier) {
				if (events.containsKey("onHoverEnd")) try { events.get("onHoverEnd").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onHover(MenuBaseElement element, String identifier) {
				if (events.containsKey("onHover")) try { events.get("onHover").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onFocusLost(MenuBaseElement element, String identifier) {
				if (events.containsKey("onFocusLost")) try { events.get("onFocusLost").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onFocus(MenuBaseElement element, String identifier) {
				if (events.containsKey("onFocus")) try { events.get("onFocus").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onClicked(MenuBaseElement element, String identifier) {
				if (events.containsKey("onClicked")) try { events.get("onClicked").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onContentChanged(MenuBaseElement element, String identifier, String newtext) {
				if (events.containsKey("onContentChanged")) try { events.get("onContentChanged").invoke(layer, element, identifier, newtext); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
		});
		
		parent.children.add(boundelem);
		
		return elem;
	}

	private MenuBaseElement calculateRadioButton(Rectangle boundaries, Element xmlElement, AgdxmlLayerBoundaryElement parent, GUITextureProvider rootProvider) throws AgdxmlParsingException, ReflectionException {
		String id = xmlElement.getAttribute("id", "{" + java.util.UUID.randomUUID().toString() + "}");
		GUITextureProvider tprox = getTextureProviderFromMap(xmlElement, rootProvider);
		
		MenuRadioButton elem = new MenuRadioButton(id, tprox);
		AgdxmlLayerBoundaryElement boundelem = new AgdxmlLayerBoundaryElement(elem, xmlElement);
		boundelem.update(boundaries);
		
		if (xmlElement.getAttribute("visible", null) != null)      elem.setVisible(xmlElement.getAttribute("visible").toLowerCase().equals("true"));
		if (xmlElement.getAttribute("labelPadding", null) != null) elem.setLabelPadding(AgdxmlParserHelper.parsePadding(xmlElement.getAttribute("labelPadding")));
		if (xmlElement.getAttribute("itemPadding", null) != null)  elem.setLabelPadding(AgdxmlParserHelper.parsePadding(xmlElement.getAttribute("itemPadding")));
		if (xmlElement.getAttribute("content", null) != null)      elem.setContent(xmlElement.getAttribute("content"));
		if (xmlElement.getAttribute("halign", null) != null)       elem.setHorizontalAlign(AgdxmlParserHelper.parseHorizontalAlign(xmlElement.getAttribute("halign")));
		if (xmlElement.getAttribute("valign", null) != null)       elem.setVerticalAlign(AgdxmlParserHelper.parseVerticalAlign(xmlElement.getAttribute("valign")));
		if (xmlElement.getAttribute("fontScale", null) != null)    elem.setFontScale(xmlElement.getFloat("fontScale"));
		if (xmlElement.getAttribute("fontColor", null) != null)    elem.setColor(AgdxmlParserHelper.parseColor(xmlElement.getAttribute("fontColor")));
		if (xmlElement.getAttribute("autoScale", null) != null)    elem.setAutoScale(AgdxmlParserHelper.parseTextAutoScaleMode(xmlElement.getAttribute("autoScale")));
		if (xmlElement.getAttribute("imageVisible", null) != null) elem.setVisible(xmlElement.getAttribute("imageVisible").toLowerCase().equals("true"));
		if (xmlElement.getAttribute("labelVisible", null) != null) elem.setVisible(xmlElement.getAttribute("labelVisible").toLowerCase().equals("true"));
		if (xmlElement.getAttribute("checked", null) != null)      elem.setChecked(xmlElement.getAttribute("checked").toLowerCase().equals("true"));
		
		final HashMap<String, Method> events = new HashMap<String, Method>();
		
		if (xmlElement.getAttribute("onPointerUp", null) != null)   events.put("onPointerUp",   layer.getDeclaredMethod(xmlElement.getAttribute("onPointerUp"),   MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onPointerDown", null) != null) events.put("onPointerDown", layer.getDeclaredMethod(xmlElement.getAttribute("onPointerDown"), MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onHoverEnd", null) != null)    events.put("onHoverEnd",    layer.getDeclaredMethod(xmlElement.getAttribute("onHoverEnd"),    MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onHover", null) != null)       events.put("onHover",       layer.getDeclaredMethod(xmlElement.getAttribute("onHover"),       MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onFocusLost", null) != null)   events.put("onFocusLost",   layer.getDeclaredMethod(xmlElement.getAttribute("onFocusLost"),   MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onFocus", null) != null)       events.put("onFocus",       layer.getDeclaredMethod(xmlElement.getAttribute("onFocus"),       MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onClicked", null) != null)     events.put("onClicked",     layer.getDeclaredMethod(xmlElement.getAttribute("onClicked"),     MenuBaseElement.class, String.class));
		
		if (xmlElement.getAttribute("onChecked", null) != null) events.put("onChecked", layer.getDeclaredMethod(xmlElement.getAttribute("onChecked"), MenuBaseElement.class, String.class, Boolean.class));
		
		elem.addRadiobuttonListener(new MenuRadioButtonListener() {
			@Override
			public void onPointerUp(MenuBaseElement element, String identifier) {
				if (events.containsKey("onPointerUp")) try { events.get("onPointerUp").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onPointerDown(MenuBaseElement element, String identifier) {
				if (events.containsKey("onPointerDown")) try { events.get("onPointerDown").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onHoverEnd(MenuBaseElement element, String identifier) {
				if (events.containsKey("onHoverEnd")) try { events.get("onHoverEnd").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onHover(MenuBaseElement element, String identifier) {
				if (events.containsKey("onHover")) try { events.get("onHover").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onFocusLost(MenuBaseElement element, String identifier) {
				if (events.containsKey("onFocusLost")) try { events.get("onFocusLost").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onFocus(MenuBaseElement element, String identifier) {
				if (events.containsKey("onFocus")) try { events.get("onFocus").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onClicked(MenuBaseElement element, String identifier) {
				if (events.containsKey("onClicked")) try { events.get("onClicked").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onChecked(MenuBaseElement element, String identifier, boolean checked) {
				if (events.containsKey("onChecked")) try { events.get("onChecked").invoke(layer, element, identifier, checked); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
		});
		
		parent.children.add(boundelem);
		
		return elem;
	}

	private MenuBaseElement calculateCheckBox(Rectangle boundaries, Element xmlElement, AgdxmlLayerBoundaryElement parent, GUITextureProvider rootProvider) throws AgdxmlParsingException, ReflectionException {
		String id = xmlElement.getAttribute("id", "{" + java.util.UUID.randomUUID().toString() + "}");
		GUITextureProvider tprox = getTextureProviderFromMap(xmlElement, rootProvider);
		
		MenuCheckBox elem = new MenuCheckBox(id, tprox);
		AgdxmlLayerBoundaryElement boundelem = new AgdxmlLayerBoundaryElement(elem, xmlElement);
		boundelem.update(boundaries);
		
		if (xmlElement.getAttribute("visible", null) != null)      elem.setVisible(xmlElement.getAttribute("visible").toLowerCase().equals("true"));
		if (xmlElement.getAttribute("labelPadding", null) != null) elem.setLabelPadding(AgdxmlParserHelper.parsePadding(xmlElement.getAttribute("labelPadding")));
		if (xmlElement.getAttribute("itemPadding", null) != null)  elem.setLabelPadding(AgdxmlParserHelper.parsePadding(xmlElement.getAttribute("itemPadding")));
		if (xmlElement.getAttribute("content", null) != null)      elem.setContent(xmlElement.getAttribute("content"));
		if (xmlElement.getAttribute("halign", null) != null)       elem.setHorizontalAlign(AgdxmlParserHelper.parseHorizontalAlign(xmlElement.getAttribute("halign")));
		if (xmlElement.getAttribute("valign", null) != null)       elem.setVerticalAlign(AgdxmlParserHelper.parseVerticalAlign(xmlElement.getAttribute("valign")));
		if (xmlElement.getAttribute("fontScale", null) != null)    elem.setFontScale(xmlElement.getFloat("fontScale"));
		if (xmlElement.getAttribute("fontColor", null) != null)    elem.setColor(AgdxmlParserHelper.parseColor(xmlElement.getAttribute("fontColor")));
		if (xmlElement.getAttribute("autoScale", null) != null)    elem.setAutoScale(AgdxmlParserHelper.parseTextAutoScaleMode(xmlElement.getAttribute("autoScale")));
		if (xmlElement.getAttribute("imageVisible", null) != null) elem.setRenderImage(xmlElement.getAttribute("imageVisible").toLowerCase().equals("true"));
		if (xmlElement.getAttribute("labelVisible", null) != null) elem.setRenderLabel(xmlElement.getAttribute("labelVisible").toLowerCase().equals("true"));
		if (xmlElement.getAttribute("checked", null) != null)      elem.setChecked(xmlElement.getAttribute("checked").toLowerCase().equals("true"));
		
		final HashMap<String, Method> events = new HashMap<String, Method>();
		
		if (xmlElement.getAttribute("onPointerUp", null) != null)   events.put("onPointerUp",   layer.getDeclaredMethod(xmlElement.getAttribute("onPointerUp"),   MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onPointerDown", null) != null) events.put("onPointerDown", layer.getDeclaredMethod(xmlElement.getAttribute("onPointerDown"), MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onHoverEnd", null) != null)    events.put("onHoverEnd",    layer.getDeclaredMethod(xmlElement.getAttribute("onHoverEnd"),    MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onHover", null) != null)       events.put("onHover",       layer.getDeclaredMethod(xmlElement.getAttribute("onHover"),       MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onFocusLost", null) != null)   events.put("onFocusLost",   layer.getDeclaredMethod(xmlElement.getAttribute("onFocusLost"),   MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onFocus", null) != null)       events.put("onFocus",       layer.getDeclaredMethod(xmlElement.getAttribute("onFocus"),       MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onClicked", null) != null)     events.put("onClicked",     layer.getDeclaredMethod(xmlElement.getAttribute("onClicked"),     MenuBaseElement.class, String.class));
		
		if (xmlElement.getAttribute("onChecked", null) != null) events.put("onChecked", layer.getDeclaredMethod(xmlElement.getAttribute("onChecked"), MenuBaseElement.class, String.class, Boolean.class));
		
		elem.addCheckboxListener(new MenuCheckboxListener() {
			@Override
			public void onPointerUp(MenuBaseElement element, String identifier) {
				if (events.containsKey("onPointerUp")) try { events.get("onPointerUp").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onPointerDown(MenuBaseElement element, String identifier) {
				if (events.containsKey("onPointerDown")) try { events.get("onPointerDown").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onHoverEnd(MenuBaseElement element, String identifier) {
				if (events.containsKey("onHoverEnd")) try { events.get("onHoverEnd").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onHover(MenuBaseElement element, String identifier) {
				if (events.containsKey("onHover")) try { events.get("onHover").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onFocusLost(MenuBaseElement element, String identifier) {
				if (events.containsKey("onFocusLost")) try { events.get("onFocusLost").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onFocus(MenuBaseElement element, String identifier) {
				if (events.containsKey("onFocus")) try { events.get("onFocus").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onClicked(MenuBaseElement element, String identifier) {
				if (events.containsKey("onClicked")) try { events.get("onClicked").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onChecked(MenuBaseElement element, String identifier, boolean checked) {
				if (events.containsKey("onChecked")) try { events.get("onChecked").invoke(layer, element, identifier, checked); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
		});
		
		parent.children.add(boundelem);
		
		return elem;
	}

	private MenuBaseElement calculateButton(Rectangle boundaries, Element xmlElement, AgdxmlLayerBoundaryElement parent, GUITextureProvider rootProvider) throws AgdxmlParsingException, ReflectionException {
		String id = xmlElement.getAttribute("id", "{" + java.util.UUID.randomUUID().toString() + "}");
		GUITextureProvider tprox = getTextureProviderFromMap(xmlElement, rootProvider);
		
		MenuButton elem = new MenuButton(id, tprox);
		AgdxmlLayerBoundaryElement boundelem = new AgdxmlLayerBoundaryElement(elem, xmlElement);
		boundelem.update(boundaries);

		if (xmlElement.getAttribute("visible", null) != null)      elem.setVisible(xmlElement.getAttribute("visible").toLowerCase().equals("true"));
		if (xmlElement.getAttribute("padding", null) != null)      elem.setPadding(AgdxmlParserHelper.parsePadding(xmlElement.getAttribute("padding")));
		if (xmlElement.getAttribute("content", null) != null)      elem.setContent(xmlElement.getAttribute("content"));
		if (xmlElement.getAttribute("halign", null) != null)       elem.setHorizontalAlign(AgdxmlParserHelper.parseHorizontalAlign(xmlElement.getAttribute("halign")));
		if (xmlElement.getAttribute("valign", null) != null)       elem.setVerticalAlign(AgdxmlParserHelper.parseVerticalAlign(xmlElement.getAttribute("valign")));
		if (xmlElement.getAttribute("fontScale", null) != null)    elem.setFontScale(xmlElement.getFloat("fontScale"));
		if (xmlElement.getAttribute("fontColor", null) != null)    elem.setColor(AgdxmlParserHelper.parseColor(xmlElement.getAttribute("fontColor")));
		if (xmlElement.getAttribute("autoScale", null) != null)    elem.setAutoScale(AgdxmlParserHelper.parseTextAutoScaleMode(xmlElement.getAttribute("autoScale")));
		
		final HashMap<String, Method> events = new HashMap<String, Method>();
		
		if (xmlElement.getAttribute("onPointerUp", null) != null)   events.put("onPointerUp",   layer.getDeclaredMethod(xmlElement.getAttribute("onPointerUp"),   MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onPointerDown", null) != null) events.put("onPointerDown", layer.getDeclaredMethod(xmlElement.getAttribute("onPointerDown"), MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onHoverEnd", null) != null)    events.put("onHoverEnd",    layer.getDeclaredMethod(xmlElement.getAttribute("onHoverEnd"),    MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onHover", null) != null)       events.put("onHover",       layer.getDeclaredMethod(xmlElement.getAttribute("onHover"),       MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onFocusLost", null) != null)   events.put("onFocusLost",   layer.getDeclaredMethod(xmlElement.getAttribute("onFocusLost"),   MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onFocus", null) != null)       events.put("onFocus",       layer.getDeclaredMethod(xmlElement.getAttribute("onFocus"),       MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onClicked", null) != null)     events.put("onClicked",     layer.getDeclaredMethod(xmlElement.getAttribute("onClicked"),     MenuBaseElement.class, String.class));
		
		elem.addButtonListener(new MenuButtonListener() {
			@Override
			public void onPointerUp(MenuBaseElement element, String identifier) {
				if (events.containsKey("onPointerUp")) try { events.get("onPointerUp").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onPointerDown(MenuBaseElement element, String identifier) {
				if (events.containsKey("onPointerDown")) try { events.get("onPointerDown").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onHoverEnd(MenuBaseElement element, String identifier) {
				if (events.containsKey("onHoverEnd")) try { events.get("onHoverEnd").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onHover(MenuBaseElement element, String identifier) {
				if (events.containsKey("onHover")) try { events.get("onHover").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onFocusLost(MenuBaseElement element, String identifier) {
				if (events.containsKey("onFocusLost")) try { events.get("onFocusLost").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onFocus(MenuBaseElement element, String identifier) {
				if (events.containsKey("onFocus")) try { events.get("onFocus").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onClicked(MenuBaseElement element, String identifier) {
				if (events.containsKey("onClicked")) try { events.get("onClicked").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
		});
		
		parent.children.add(boundelem);
		
		return elem;
	}

	private MenuBaseElement calculateImage(Rectangle boundaries, Element xmlElement, AgdxmlLayerBoundaryElement parent, GUITextureProvider rootProvider) throws AgdxmlParsingException, ReflectionException {
		String id = xmlElement.getAttribute("id", "{" + java.util.UUID.randomUUID().toString() + "}");
		GUITextureProvider tprox = getTextureProviderFromMap(xmlElement, rootProvider);
		
		MenuImage elem = new MenuImage(id, tprox);
		AgdxmlLayerBoundaryElement boundelem = new AgdxmlLayerBoundaryElement(elem, xmlElement);
		boundelem.update(boundaries);
		
		if (xmlElement.getAttribute("visible", null) != null) elem.setVisible(xmlElement.getAttribute("visible").toLowerCase().equals("true"));
		if (xmlElement.getAttribute("texture", null) != null) {
			if (xmlElement.getIntAttribute("animation", 0) > 0) {
				elem.setImage(getMultiImageTextureFromMap(xmlElement), xmlElement.getIntAttribute("animation", 0));
			} else {
				elem.setImage(getSingleImageTextureFromMap(xmlElement));
			}
		}
		if (xmlElement.getAttribute("behavior", null) != null)  elem.setBehavior(AgdxmlParserHelper.parseImageBehavior(xmlElement.getAttribute("behavior")));
		
		final HashMap<String, Method> events = new HashMap<String, Method>();
		
		if (xmlElement.getAttribute("onPointerUp", null) != null)   events.put("onPointerUp",   layer.getDeclaredMethod(xmlElement.getAttribute("onPointerUp"),   MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onPointerDown", null) != null) events.put("onPointerDown", layer.getDeclaredMethod(xmlElement.getAttribute("onPointerDown"), MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onHoverEnd", null) != null)    events.put("onHoverEnd",    layer.getDeclaredMethod(xmlElement.getAttribute("onHoverEnd"),    MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onHover", null) != null)       events.put("onHover",       layer.getDeclaredMethod(xmlElement.getAttribute("onHover"),       MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onFocusLost", null) != null)   events.put("onFocusLost",   layer.getDeclaredMethod(xmlElement.getAttribute("onFocusLost"),   MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onFocus", null) != null)       events.put("onFocus",       layer.getDeclaredMethod(xmlElement.getAttribute("onFocus"),       MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onClicked", null) != null)     events.put("onClicked",     layer.getDeclaredMethod(xmlElement.getAttribute("onClicked"),     MenuBaseElement.class, String.class));
		
		elem.addImageListener(new MenuImageListener() {
			@Override
			public void onPointerUp(MenuBaseElement element, String identifier) {
				if (events.containsKey("onPointerUp")) try { events.get("onPointerUp").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onPointerDown(MenuBaseElement element, String identifier) {
				if (events.containsKey("onPointerDown")) try { events.get("onPointerDown").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onHoverEnd(MenuBaseElement element, String identifier) {
				if (events.containsKey("onHoverEnd")) try { events.get("onHoverEnd").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onHover(MenuBaseElement element, String identifier) {
				if (events.containsKey("onHover")) try { events.get("onHover").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onFocusLost(MenuBaseElement element, String identifier) {
				if (events.containsKey("onFocusLost")) try { events.get("onFocusLost").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onFocus(MenuBaseElement element, String identifier) {
				if (events.containsKey("onFocus")) try { events.get("onFocus").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onClicked(MenuBaseElement element, String identifier) {
				if (events.containsKey("onClicked")) try { events.get("onClicked").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
		});
		
		parent.children.add(boundelem);
		
		return elem;
	}

	private MenuBaseElement calculatePanel(Rectangle boundaries, Element xmlElement, AgdxmlLayerBoundaryElement parent, GUITextureProvider rootProvider) throws AgdxmlParsingException, ReflectionException {
		String id = xmlElement.getAttribute("id", "{" + java.util.UUID.randomUUID().toString() + "}");
		GUITextureProvider tprox = getTextureProviderFromMap(xmlElement, rootProvider);

		boolean iscontainer = xmlElement.getAttribute("iscontainer", "false").toLowerCase().equals("true");
		MenuContainer elem;
		if (iscontainer) 
			elem = new MenuContainer(id, tprox);
		else  
			elem = new MenuPanel(id, tprox);
		
		AgdxmlLayerBoundaryElement boundelem = new AgdxmlLayerBoundaryElement(elem, xmlElement);
		boundelem.update(boundaries);
		
		if (xmlElement.getAttribute("visible", null) != null) elem.setVisible(xmlElement.getAttribute("visible").toLowerCase().equals("true"));
		
		final HashMap<String, Method> events = new HashMap<String, Method>();
		
		if (xmlElement.getAttribute("onPointerUp", null) != null)   events.put("onPointerUp",   layer.getDeclaredMethod(xmlElement.getAttribute("onPointerUp"),   MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onPointerDown", null) != null) events.put("onPointerDown", layer.getDeclaredMethod(xmlElement.getAttribute("onPointerDown"), MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onHoverEnd", null) != null)    events.put("onHoverEnd",    layer.getDeclaredMethod(xmlElement.getAttribute("onHoverEnd"),    MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onHover", null) != null)       events.put("onHover",       layer.getDeclaredMethod(xmlElement.getAttribute("onHover"),       MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onFocusLost", null) != null)   events.put("onFocusLost",   layer.getDeclaredMethod(xmlElement.getAttribute("onFocusLost"),   MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onFocus", null) != null)       events.put("onFocus",       layer.getDeclaredMethod(xmlElement.getAttribute("onFocus"),       MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onClicked", null) != null)     events.put("onClicked",     layer.getDeclaredMethod(xmlElement.getAttribute("onClicked"),     MenuBaseElement.class, String.class));
		
		elem.addContainerListener(new MenuContainerListener() {
			@Override
			public void onPointerUp(MenuBaseElement element, String identifier) {
				if (events.containsKey("onPointerUp")) try { events.get("onPointerUp").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onPointerDown(MenuBaseElement element, String identifier) {
				if (events.containsKey("onPointerDown")) try { events.get("onPointerDown").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onHoverEnd(MenuBaseElement element, String identifier) {
				if (events.containsKey("onHoverEnd")) try { events.get("onHoverEnd").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onHover(MenuBaseElement element, String identifier) {
				if (events.containsKey("onHover")) try { events.get("onHover").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onFocusLost(MenuBaseElement element, String identifier) {
				if (events.containsKey("onFocusLost")) try { events.get("onFocusLost").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onFocus(MenuBaseElement element, String identifier) {
				if (events.containsKey("onFocus")) try { events.get("onFocus").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onClicked(MenuBaseElement element, String identifier) {
				if (events.containsKey("onClicked")) try { events.get("onClicked").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
		});
		
		parent.children.add(boundelem);
		
		Rectangle bd = new Rectangle(0, 0, elem.getWidth(), elem.getHeight());
		for (int i = 0; i < xmlElement.getChildCount(); i++) {
			Element child = xmlElement.getChild(i);
			
			MenuBaseElement mchild = calculateGeneric(new Rectangle(bd), child, boundelem, tprox);
			if (mchild != null) elem.addChildren(mchild);
		}
		
		return elem;
	}

	private MenuBaseElement calculateGrid(Rectangle boundaries, Element xmlElement, AgdxmlLayerBoundaryElement parent, GUITextureProvider rootProvider) throws AgdxmlParsingException, ReflectionException {
		String id = xmlElement.getAttribute("id", "{" + java.util.UUID.randomUUID().toString() + "}");
		GUITextureProvider tprox = getTextureProviderFromMap(xmlElement, rootProvider);

		boolean iscontainer = xmlElement.getAttribute("iscontainer", "false").toLowerCase().equals("true");
		MenuContainer elem;
		if (iscontainer) 
			elem = new MenuContainer(id, tprox);
		else  
			elem = new MenuPanel(id, tprox);
		
		AgdxmlLayerBoundaryElement boundelem = new AgdxmlLayerBoundaryElement(elem, xmlElement);
		boundelem.update(boundaries);
		
		if (xmlElement.getAttribute("visible", null) != null) elem.setVisible(xmlElement.getAttribute("visible").toLowerCase().equals("true"));
		
		final HashMap<String, Method> events = new HashMap<String, Method>();
		
		if (xmlElement.getAttribute("onPointerUp", null) != null)   events.put("onPointerUp",   layer.getDeclaredMethod(xmlElement.getAttribute("onPointerUp"),   MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onPointerDown", null) != null) events.put("onPointerDown", layer.getDeclaredMethod(xmlElement.getAttribute("onPointerDown"), MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onHoverEnd", null) != null)    events.put("onHoverEnd",    layer.getDeclaredMethod(xmlElement.getAttribute("onHoverEnd"),    MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onHover", null) != null)       events.put("onHover",       layer.getDeclaredMethod(xmlElement.getAttribute("onHover"),       MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onFocusLost", null) != null)   events.put("onFocusLost",   layer.getDeclaredMethod(xmlElement.getAttribute("onFocusLost"),   MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onFocus", null) != null)       events.put("onFocus",       layer.getDeclaredMethod(xmlElement.getAttribute("onFocus"),       MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onClicked", null) != null)     events.put("onClicked",     layer.getDeclaredMethod(xmlElement.getAttribute("onClicked"),     MenuBaseElement.class, String.class));
		
		elem.addContainerListener(new MenuContainerListener() {
			@Override
			public void onPointerUp(MenuBaseElement element, String identifier) {
				if (events.containsKey("onPointerUp")) try { events.get("onPointerUp").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onPointerDown(MenuBaseElement element, String identifier) {
				if (events.containsKey("onPointerDown")) try { events.get("onPointerDown").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onHoverEnd(MenuBaseElement element, String identifier) {
				if (events.containsKey("onHoverEnd")) try { events.get("onHoverEnd").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onHover(MenuBaseElement element, String identifier) {
				if (events.containsKey("onHover")) try { events.get("onHover").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onFocusLost(MenuBaseElement element, String identifier) {
				if (events.containsKey("onFocusLost")) try { events.get("onFocusLost").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onFocus(MenuBaseElement element, String identifier) {
				if (events.containsKey("onFocus")) try { events.get("onFocus").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onClicked(MenuBaseElement element, String identifier) {
				if (events.containsKey("onClicked")) try { events.get("onClicked").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
		});
		
		parent.children.add(boundelem);

		for (int i = 0; i < xmlElement.getChildCount(); i++) {
			Element child = xmlElement.getChild(i);
			
			int childGrid_x = child.getInt("grid.column", 0);
			int childGrid_y = child.getInt("grid.row", 0);
			
			MenuBaseElement mchild = calculateGeneric(boundelem.gridDefinitions.getBoundaries(childGrid_x, childGrid_y, elem.getBoundaries()), child, boundelem, tprox);
			if (mchild != null) elem.addChildren(mchild);
		}
		
		return elem;
	}
	
	private MenuBaseElement calculateEdit(Rectangle boundaries, Element xmlElement, AgdxmlLayerBoundaryElement parent, GUITextureProvider rootProvider) throws AgdxmlParsingException, ReflectionException {
		String id = xmlElement.getAttribute("id", "{" + java.util.UUID.randomUUID().toString() + "}");
		GUITextureProvider tprox = getTextureProviderFromMap(xmlElement, rootProvider);
		
		MenuEdit elem = new MenuEdit(id, tprox);
		AgdxmlLayerBoundaryElement boundelem = new AgdxmlLayerBoundaryElement(elem, xmlElement);
		boundelem.update(boundaries);
		
		if (xmlElement.getAttribute("visible", null) != null)     elem.setVisible(xmlElement.getAttribute("visible").toLowerCase().equals("true"));
		if (xmlElement.getAttribute("text", null) != null)        elem.setContent(xmlElement.getAttribute("text"));
		if (xmlElement.getAttribute("halign", null) != null)      elem.setHorizontalAlign(AgdxmlParserHelper.parseHorizontalAlign(xmlElement.getAttribute("halign")));
		if (xmlElement.getAttribute("valign", null) != null)      elem.setVerticalAlign(AgdxmlParserHelper.parseVerticalAlign(xmlElement.getAttribute("valign")));
		if (xmlElement.getAttribute("textColor", null) != null)   elem.setColor(AgdxmlParserHelper.parseColor(xmlElement.getAttribute("textColor")));
		if (xmlElement.getAttribute("textPadding", null) != null) elem.setPadding(AgdxmlParserHelper.parsePadding(xmlElement.getAttribute("textPadding")));
		
		final HashMap<String, Method> events = new HashMap<String, Method>();
		
		if (xmlElement.getAttribute("onPointerUp", null) != null)   events.put("onPointerUp",   layer.getDeclaredMethod(xmlElement.getAttribute("onPointerUp"),   MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onPointerDown", null) != null) events.put("onPointerDown", layer.getDeclaredMethod(xmlElement.getAttribute("onPointerDown"), MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onHoverEnd", null) != null)    events.put("onHoverEnd",    layer.getDeclaredMethod(xmlElement.getAttribute("onHoverEnd"),    MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onHover", null) != null)       events.put("onHover",       layer.getDeclaredMethod(xmlElement.getAttribute("onHover"),       MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onFocusLost", null) != null)   events.put("onFocusLost",   layer.getDeclaredMethod(xmlElement.getAttribute("onFocusLost"),   MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onFocus", null) != null)       events.put("onFocus",       layer.getDeclaredMethod(xmlElement.getAttribute("onFocus"),       MenuBaseElement.class, String.class));
		if (xmlElement.getAttribute("onClicked", null) != null)     events.put("onClicked",     layer.getDeclaredMethod(xmlElement.getAttribute("onClicked"),     MenuBaseElement.class, String.class));
		
		if (xmlElement.getAttribute("onTextChanged", null) != null) events.put("onTextChanged", layer.getDeclaredMethod(xmlElement.getAttribute("onTextChanged"), MenuBaseElement.class, String.class, String.class));
		
		elem.addEditListener(new MenuEditListener() {
			@Override
			public void onPointerUp(MenuBaseElement element, String identifier) {
				if (events.containsKey("onPointerUp")) try { events.get("onPointerUp").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onPointerDown(MenuBaseElement element, String identifier) {
				if (events.containsKey("onPointerDown")) try { events.get("onPointerDown").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onHoverEnd(MenuBaseElement element, String identifier) {
				if (events.containsKey("onHoverEnd")) try { events.get("onHoverEnd").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onHover(MenuBaseElement element, String identifier) {
				if (events.containsKey("onHover")) try { events.get("onHover").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onFocusLost(MenuBaseElement element, String identifier) {
				if (events.containsKey("onFocusLost")) try { events.get("onFocusLost").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onFocus(MenuBaseElement element, String identifier) {
				if (events.containsKey("onFocus")) try { events.get("onFocus").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onClicked(MenuBaseElement element, String identifier) {
				if (events.containsKey("onClicked")) try { events.get("onClicked").invoke(layer, element, identifier); } catch (ReflectionException e) {throw new RuntimeException(e);}
			}
			@Override
			public void onTextChanged(MenuBaseElement element, String identifier, String newtext) {
				if (events.containsKey("onTextChanged")) try { events.get("onTextChanged").invoke(layer, element, identifier, newtext); } catch (ReflectionException e) {throw new RuntimeException(e);}
				
			}
		});
		
		parent.children.add(boundelem);
		
		return elem;
	}

	/**
	 * get the boundary root element of the boundary tree
	 * in this all relative size information are stores
	 * 
	 * @return the root element
	 */
	public AgdxmlLayerBoundaryElement getBoundaryRootElement() {
		return boundaryRootElement;
	}
}
