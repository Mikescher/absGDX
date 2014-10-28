package de.samdev.absgdx.framework.tmxloader;

import java.util.HashMap;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class TmxHandler extends DefaultHandler {

	private boolean inTileSet, inTile, inLayer, inData, inObjectGroup,
			inProperties;

	private String currentTileID;
	private String currentObjectGroupName;
	TileMapData.TMXObject currentObject;

	TileMapData.TileSet currentTileSet;
	TileMapData.Layer currentLayer;

	HashMap<String, HashMap<String, String>> currentTileSetProperties;
	HashMap<String, String> currentLayerProperties;

	private TileMapData data;

	private char buffer[];
	private int bufferIndex;
	private int currentX;
	private int currentY;
	public int MAX_INT_DECIMAL_LENGTH = 10;

	public TmxHandler() {
		super();
		buffer = new char[MAX_INT_DECIMAL_LENGTH];
		bufferIndex = 0;
		currentX = 0;
		currentY = 0;
	}

	public TileMapData getData() {
		return data;
	}

	@Override
	public void startDocument() throws SAXException {
		data = new TileMapData();
	}

	@Override
	public void endDocument() throws SAXException {

	}

	@Override
	public void startElement(String namespaceURI, String localName,
			String qName, Attributes atts) throws SAXException {
		if (localName.equals("map")) {

			if (!(atts.getValue("orientation").equals("orthogonal"))) {
				throw new SAXException(
						"Unsupported orientation. Parse Terminated.");
			}

			data.orientation = atts.getValue("orientation");
			// Android things.....
			// Log.d("Checking", data.orientation);
			// ///////////////////////////////////////
			data.height = Integer.parseInt(atts.getValue("height"));
			data.width = Integer.parseInt(atts.getValue("width"));
			data.tilewidth = Integer.parseInt(atts.getValue("tilewidth"));
			data.tileheight = Integer.parseInt(atts.getValue("tileheight"));

		} else if (localName.equals("tileset")) {
			inTileSet = true;
			currentTileSet = new TileMapData.TileSet();
			currentTileSet.firstGID = Integer.parseInt(atts
					.getValue("firstgid"));
			currentTileSet.tileWidth = Integer.parseInt(atts
					.getValue("tilewidth"));
			currentTileSet.tileHeight = Integer.parseInt(atts
					.getValue("tileheight"));
			currentTileSet.name = atts.getValue("name");
			currentTileSetProperties = new HashMap<String, HashMap<String, String>>();

		} else if (inTileSet && localName.equals("image")) {
			currentTileSet.ImageFilename = atts.getValue("source");
			currentTileSet.imageWidth = Integer
					.parseInt(atts.getValue("width"));
			currentTileSet.imageHeight = Integer.parseInt(atts
					.getValue("height"));

		} else if (inTileSet && localName.equals("tile")) {
			inTile = true;
			currentTileID = atts.getValue("id");

		} else if (inTileSet && localName.equals("properties")) {
			inProperties = true;
			// Android things :D
			// Log.d("Tile ID", currentTileID);
			// //////////////////////////////////
			currentTileSetProperties.put(currentTileID,
					new HashMap<String, String>());

		} else if (inLayer && localName.equals("properties")) {
			inProperties = true;

		} else if (inTile && inProperties && localName.equals("property")) {
			(currentTileSetProperties.get(currentTileID)).put(
					atts.getValue("name"), atts.getValue("value"));

		} else if (inLayer && inProperties && localName.equals("property")) {
			currentLayerProperties.put(atts.getValue("name"),
					atts.getValue("value"));
		} else if (localName.equals("layer")) {
			inLayer = true;

			currentLayer = new TileMapData.Layer();
			currentLayer.name = atts.getValue("name");
			currentLayer.width = Integer.parseInt(atts.getValue("width"));
			currentLayer.height = Integer.parseInt(atts.getValue("height"));
			if (atts.getValue("opacity") != null)
				currentLayer.opacity = Double.parseDouble(atts
						.getValue("opacity"));
			currentLayer.tiles = new long[currentLayer.height][currentLayer.width];

			currentLayerProperties = new HashMap<String, String>();
		} else if (localName.equals("data")) {
			inData = true;
			String encoding = atts.getValue("encoding");
			if (!encoding.equals("csv")) {
				throw new SAXException(
						"Unsupported encoding, Parse Terminated.");
			}

		} else if (localName.equals("objectgroup")) {
			inObjectGroup = true;
			currentObjectGroupName = atts.getValue("name");

		} else if (localName.equals("object")) {
			currentObject = new TileMapData.TMXObject();
			currentObject.name = atts.getValue("name");
			currentObject.type = atts.getValue("type");
			currentObject.x = Integer.parseInt(atts.getValue("x"));
			currentObject.y = Integer.parseInt(atts.getValue("y"));
			currentObject.width = Integer.parseInt(atts.getValue("width"));
			currentObject.height = Integer.parseInt(atts.getValue("height"));
			if (inObjectGroup) {
				currentObject.objectGroup = currentObjectGroupName;
			} else {
				currentObject.objectGroup = null;
			}

		}
	}
	
	
}
