package de.samdev.absgdx.menudesigner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AGDXMLTagDefinition {

	public static class TagAttribute {
		public final String attr;
		public final String type;
		
		public TagAttribute(String a, String t) {
			super();
			
			this.attr = a;
			this.type = t;
		}


		@Override
		public String toString() {
			return attr + ": " + type;
		}
	}
	
	public final String tag;
	
	public final List<TagAttribute> attributes;
	
	public AGDXMLTagDefinition(String tag, TagAttribute... attributes) {
		super();
		
		this.tag = tag;
		
		ArrayList<TagAttribute> attr = new ArrayList<TagAttribute>();
		for (TagAttribute tattr : attributes) {
			attr.add(tattr);
		}
		
		this.attributes = Collections.unmodifiableList(attr);
	}

	@Override
	public String toString() {
		return "<" + tag + " />";
	}
	
	//#########################################################################

	public final static AGDXMLTagDefinition TAG_FRAME = new AGDXMLTagDefinition("frame", 
			new TagAttribute("visible", "bool"), 
			new TagAttribute("textures", "id"),
			new TagAttribute("renderTexture", "bool"));

	public final static AGDXMLTagDefinition TAG_EDIT = new AGDXMLTagDefinition("edit", 
			new TagAttribute("position", "vector"), 
			new TagAttribute("width", "value"), 
			new TagAttribute("height", "value"), 
			new TagAttribute("visible", "bool"), 
			new TagAttribute("textures", "id"),
			new TagAttribute("id", "string"),
			
			new TagAttribute("text", "string"),
			new TagAttribute("textPadding", "padding"),
			new TagAttribute("valign", "enum"),
			new TagAttribute("halign", "enum"),
			new TagAttribute("textColor", "color"),
			
			new TagAttribute("onTextChanged", "event"),
	
			new TagAttribute("onClicked", "event"),
			new TagAttribute("onPointerDown", "event"),
			new TagAttribute("onPointerUp", "event"),
			new TagAttribute("onHover", "event"),
			new TagAttribute("onHoverEnd", "event"),
			new TagAttribute("onFocus", "event"),
			new TagAttribute("onFocusLost", "event"));

	public final static AGDXMLTagDefinition TAG_LABEL = new AGDXMLTagDefinition("label", 
			new TagAttribute("position", "vector"), 
			new TagAttribute("width", "value"), 
			new TagAttribute("height", "value"), 
			new TagAttribute("visible", "bool"), 
			new TagAttribute("textures", "id"),
			new TagAttribute("id", "string"),
			
			new TagAttribute("content", "string"),
			new TagAttribute("halign", "enum"),
			new TagAttribute("valign", "enum"),
			new TagAttribute("fontScale", "float"),
			new TagAttribute("fontColor", "color"),
			new TagAttribute("autoScale", "enum"),
			
			new TagAttribute("onContentChanged", "event"),
	
			new TagAttribute("onClicked", "event"),
			new TagAttribute("onPointerDown", "event"),
			new TagAttribute("onPointerUp", "event"),
			new TagAttribute("onHover", "event"),
			new TagAttribute("onHoverEnd", "event"),
			new TagAttribute("onFocus", "event"),
			new TagAttribute("onFocusLost", "event"));

	public final static AGDXMLTagDefinition TAG_CHECKBOX = new AGDXMLTagDefinition("checkbox", 
			new TagAttribute("position", "vector"), 
			new TagAttribute("width", "value"), 
			new TagAttribute("height", "value"), 
			new TagAttribute("visible", "bool"), 
			new TagAttribute("textures", "id"),
			new TagAttribute("id", "string"),
			
			new TagAttribute("labelPadding", "padding"),
			new TagAttribute("imagePadding", "padding"),
			new TagAttribute("content", "string"),
			new TagAttribute("halign", "enum"),
			new TagAttribute("valign", "enum"),
			new TagAttribute("fontScale", "float"),
			new TagAttribute("fontColor", "color"),
			new TagAttribute("autoScale", "enum"),
			new TagAttribute("imageVisible", "bool"),
			new TagAttribute("labelVisible", "bool"),
			new TagAttribute("checked", "bool"),
			
			new TagAttribute("onChecked", "event"),
	
			new TagAttribute("onClicked", "event"),
			new TagAttribute("onPointerDown", "event"),
			new TagAttribute("onPointerUp", "event"),
			new TagAttribute("onHover", "event"),
			new TagAttribute("onHoverEnd", "event"),
			new TagAttribute("onFocus", "event"),
			new TagAttribute("onFocusLost", "event"));

	public final static AGDXMLTagDefinition TAG_RADIOBUTTON = new AGDXMLTagDefinition("radiobutton", 
			new TagAttribute("position", "vector"), 
			new TagAttribute("width", "value"), 
			new TagAttribute("height", "value"), 
			new TagAttribute("visible", "bool"), 
			new TagAttribute("textures", "id"),
			new TagAttribute("id", "string"),
			
			new TagAttribute("labelPadding", "padding"),
			new TagAttribute("imagePadding", "padding"),
			new TagAttribute("content", "string"),
			new TagAttribute("halign", "enum"),
			new TagAttribute("valign", "enum"),
			new TagAttribute("fontScale", "float"),
			new TagAttribute("fontColor", "color"),
			new TagAttribute("autoScale", "enum"),
			new TagAttribute("imageVisible", "bool"),
			new TagAttribute("labelVisible", "bool"),
			new TagAttribute("checked", "bool"),
			
			new TagAttribute("onChecked", "event"),
	
			new TagAttribute("onClicked", "event"),
			new TagAttribute("onPointerDown", "event"),
			new TagAttribute("onPointerUp", "event"),
			new TagAttribute("onHover", "event"),
			new TagAttribute("onHoverEnd", "event"),
			new TagAttribute("onFocus", "event"),
			new TagAttribute("onFocusLost", "event"));

	public final static AGDXMLTagDefinition TAG_BUTTON = new AGDXMLTagDefinition("button", 
			new TagAttribute("position", "vector"), 
			new TagAttribute("width", "value"), 
			new TagAttribute("height", "value"), 
			new TagAttribute("visible", "bool"), 
			new TagAttribute("textures", "id"),
			new TagAttribute("id", "string"),

			new TagAttribute("padding", "padding"),
			new TagAttribute("content", "string"),
			new TagAttribute("halign", "enum"),
			new TagAttribute("valign", "enum"),
			new TagAttribute("fontScale", "float"),
			new TagAttribute("fontColor", "color"),
			new TagAttribute("autoScale", "enum"),
			new TagAttribute("image", "id"),
			new TagAttribute("imagePadding", "int"),
	
			new TagAttribute("onClicked", "event"),
			new TagAttribute("onPointerDown", "event"),
			new TagAttribute("onPointerUp", "event"),
			new TagAttribute("onHover", "event"),
			new TagAttribute("onHoverEnd", "event"),
			new TagAttribute("onFocus", "event"),
			new TagAttribute("onFocusLost", "event"));

	public final static AGDXMLTagDefinition TAG_SETTINGSTREE = new AGDXMLTagDefinition("settingstree", 
			new TagAttribute("position", "vector"), 
			new TagAttribute("width", "value"), 
			new TagAttribute("height", "value"), 
			new TagAttribute("visible", "bool"), 
			new TagAttribute("textures", "id"),
			new TagAttribute("id", "string"),

			new TagAttribute("rowGap", "float"),
			new TagAttribute("fontColor", "color"),
			new TagAttribute("rowHeight", "float"),
			new TagAttribute("scrollbarColor", "color"),
			new TagAttribute("scrollbarWidth", "float"),
			
			new TagAttribute("onPropertyChanged", "event"),
	
			new TagAttribute("onClicked", "event"),
			new TagAttribute("onPointerDown", "event"),
			new TagAttribute("onPointerUp", "event"),
			new TagAttribute("onHover", "event"),
			new TagAttribute("onHoverEnd", "event"),
			new TagAttribute("onFocus", "event"),
			new TagAttribute("onFocusLost", "event"));

	public final static AGDXMLTagDefinition TAG_PANEL = new AGDXMLTagDefinition("panel", 
			new TagAttribute("position", "vector"), 
			new TagAttribute("width", "value"), 
			new TagAttribute("height", "value"), 
			new TagAttribute("visible", "bool"), 
			new TagAttribute("textures", "id"),
			new TagAttribute("id", "string"),

			new TagAttribute("iscontainer", "bool"),
	
			new TagAttribute("onClicked", "event"),
			new TagAttribute("onPointerDown", "event"),
			new TagAttribute("onPointerUp", "event"),
			new TagAttribute("onHover", "event"),
			new TagAttribute("onHoverEnd", "event"),
			new TagAttribute("onFocus", "event"),
			new TagAttribute("onFocusLost", "event"));

	public final static AGDXMLTagDefinition TAG_GRID = new AGDXMLTagDefinition("grid", 
			new TagAttribute("position", "vector"), 
			new TagAttribute("width", "value"), 
			new TagAttribute("height", "value"), 
			new TagAttribute("visible", "bool"), 
			new TagAttribute("textures", "id"),
			new TagAttribute("id", "string"),

			new TagAttribute("iscontainer", "bool"),
	
			new TagAttribute("onClicked", "event"),
			new TagAttribute("onPointerDown", "event"),
			new TagAttribute("onPointerUp", "event"),
			new TagAttribute("onHover", "event"),
			new TagAttribute("onHoverEnd", "event"),
			new TagAttribute("onFocus", "event"),
			new TagAttribute("onFocusLost", "event"));

	public final static AGDXMLTagDefinition TAG_IMAGE = new AGDXMLTagDefinition("image", 
			new TagAttribute("position", "vector"), 
			new TagAttribute("width", "value"), 
			new TagAttribute("height", "value"), 
			new TagAttribute("visible", "bool"), 
			new TagAttribute("textures", "id"),
			new TagAttribute("id", "string"),

			new TagAttribute("texture", "id"),
			new TagAttribute("animation", "int"),
			new TagAttribute("behaviour", "enum"),
	
			new TagAttribute("onClicked", "event"),
			new TagAttribute("onPointerDown", "event"),
			new TagAttribute("onPointerUp", "event"),
			new TagAttribute("onHover", "event"),
			new TagAttribute("onHoverEnd", "event"),
			new TagAttribute("onFocus", "event"),
			new TagAttribute("onFocusLost", "event"));
	
	public final static AGDXMLTagDefinition[] TAGS = new AGDXMLTagDefinition[]{TAG_FRAME, TAG_EDIT, TAG_LABEL, TAG_CHECKBOX, TAG_RADIOBUTTON, TAG_BUTTON, TAG_SETTINGSTREE, TAG_PANEL, TAG_GRID, TAG_IMAGE};
}
