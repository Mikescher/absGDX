package de.samdev.absgdx.framework.util.tiled;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.zip.DataFormatException;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import de.samdev.absgdx.framework.util.exceptions.TmxMapParsingException;

/**
 * An Parser for tmx maps
 *
 */
public abstract class TmxParser {
	/** The width of the layer */
	public final static String PROPERTY_LAYER_WIDTH = "[absGDX]-layer_width";
	/** The height of the layer */
	public final static String PROPERTY_LAYER_HEIGHT = "[absGDX]-layer_height";
	/** The width of the map */
	public final static String PROPERTY_MAP_WIDTH = "[absGDX]-map_width";
	/** The height of the map */
	public final static String PROPERTY_MAP_HEIGHT = "[absGDX]-map_height";
	/** The current position of the layer (0 is the lowest layer) */
	public final static String PROPERTY_LAYER_LEVEL = "[absGDX]-layer_level";
	/** The name of the layer */
	public final static String PROPERTY_LAYER_NAME = "[absGDX]-layer_name";
	/** The GID of the used texture */
	public final static String PROPERTY_TEXTURE_GID = "[absGDX]-gid";
	/** The x position of the tile */
	public final static String PROPERTY_POSITION_X = "[absGDX]-pos_x";
	/** The y position of the tile */
	public final static String PROPERTY_POSITION_Y = "[absGDX]-pos_y";
	/** The used compression (none, gzip, zlib, ...)*/
	public final static String PROPERTY_COMPRESSION = "[absGDX]-compression";
	/** The used encoding (xml, csv, base64, ...) */
	public final static String PROPERTY_ENCODING = "[absGDX]-encoding";
	
	private String fileContent;
	
	/**
	 * Creates a new TmxParser with the given xml
	 * 
	 * @param xml
	 */
	public TmxParser(String xml) {
		super();
		
		this.fileContent = xml;

	}

	/**
	 * Starts parsing of the TMX file.
	 * Each found tile is redirected to the handleTile Method
	 * 
	 * @throws ParsingException
	 * @throws ValidityException
	 * @throws IOException
	 * @throws DataFormatException
	 * @throws TmxMapParsingException
	 */
	public void parse() throws ParsingException, ValidityException, IOException, DataFormatException, TmxMapParsingException {
		Builder xomBuilder = new Builder();
		
		Document doc = xomBuilder.build(this.fileContent, null);
		
		Element mapElement = doc.getRootElement();
		
		int mapWidth = Integer.parseInt(mapElement.getAttributeValue("width"));
		int mapHeight = Integer.parseInt(mapElement.getAttributeValue("height"));
		
		Elements layerList = mapElement.getChildElements("layer");
		for (int layerPos = 0; layerPos < layerList.size(); layerPos++) {
			Element layer = layerList.get(layerPos);
			
			int layerWidth = Integer.parseInt(layer.getAttributeValue("width"));
			int layerHeight = Integer.parseInt(layer.getAttributeValue("height"));
			
			HashMap<String, String> propertiesMap = parseProperties(layer, layerPos, layerWidth, layerHeight, mapWidth, mapHeight);

			Element layerdata = layer.getFirstChildElement("data");
			parseData(layerPos, layerdata, layerWidth, layerHeight, propertiesMap);
		}
	}

	private HashMap<String, String> parseProperties(Element layer, int layerPos, int layerWidth, int layerHeight, int mapWidth, int mapHeight) {
		Element layerprop = layer.getFirstChildElement("properties");
		HashMap<String, String> propertiesMap = new HashMap<String, String>();
		propertiesMap.put(PROPERTY_LAYER_WIDTH, "" + layerWidth);
		propertiesMap.put(PROPERTY_LAYER_HEIGHT, "" + layerHeight);
		propertiesMap.put(PROPERTY_MAP_WIDTH, "" + mapWidth);
		propertiesMap.put(PROPERTY_MAP_HEIGHT, "" + mapHeight);
		propertiesMap.put(PROPERTY_LAYER_LEVEL, "" + layerPos);
		propertiesMap.put(PROPERTY_LAYER_NAME, "" + layer.getAttributeValue("name"));
		
		if (layerprop != null) {
			Elements propertyList = layerprop.getChildElements("property");
			for (int i = 0; i < propertyList.size(); i++) {
				propertiesMap.put(propertyList.get(i).getAttributeValue("name"), propertyList.get(i).getAttributeValue("value"));
			}
		}
		return propertiesMap;
	}

	private void addTile(int gid, int layer, int posX, int posY, HashMap<String, String> propertiesMap) throws TmxMapParsingException {
		HashMap<String, String> map = new HashMap<String, String>(propertiesMap);
		
		map.put(PROPERTY_TEXTURE_GID, "" + gid);
		map.put(PROPERTY_POSITION_X, "" + posX);
		map.put(PROPERTY_POSITION_Y, "" + posY);
		
		handleTile(gid, layer, posX, posY, map);
	}
	
	protected abstract void handleTile(int gid, int layer, int posX, int posY, HashMap<String, String> propertiesMap) throws TmxMapParsingException;

	private void parseData(int layerPos, Element layerdata, int layerWidth, int layerHeight, HashMap<String, String> propertiesMap) throws ParsingException, IOException, DataFormatException, TmxMapParsingException {
		String encoding = layerdata.getAttributeValue("encoding");
		String compression = layerdata.getAttributeValue("compression");
		
		if (encoding == null) encoding = "xml";
		
		propertiesMap.put(PROPERTY_ENCODING, encoding);
		propertiesMap.put(PROPERTY_COMPRESSION, compression == null ? "none" : compression);
		
		if (encoding.equalsIgnoreCase("xml") && compression == null) 
			parseDataXML(layerPos, layerdata, layerWidth, layerHeight, propertiesMap);
		else if (encoding.equalsIgnoreCase("csv") && compression == null) 
			parseDataCSV(layerPos, layerdata, layerWidth, layerHeight, propertiesMap);
		else if (encoding.equalsIgnoreCase("base64") && compression == null) 
			parseDataBase64_uncompressed(layerPos, layerdata, layerWidth, layerHeight, propertiesMap);
		else if (encoding.equalsIgnoreCase("base64") && compression.equals("gzip")) 
			parseDataBase64_gzip(layerPos, layerdata, layerWidth, layerHeight, propertiesMap);
		else if (encoding.equalsIgnoreCase("base64") && compression.equals("zlib")) 
			parseDataBase64_zlib(layerPos, layerdata, layerWidth, layerHeight, propertiesMap);
		else 
			throw new ParsingException("Unknown Encoding+compression: " + encoding + " :: " + compression);
	}
	
	private void parseDataXML(int layerPos, Element layerdata, int layerWidth, int layerHeight, HashMap<String, String> propertiesMap) throws TmxMapParsingException{
		Elements tileList = layerdata.getChildElements("tile");
		
		for (int tilePos = 0; tilePos < tileList.size(); tilePos++) {
			Element tile = tileList.get(tilePos);
			
			int posX = tilePos % layerWidth;
			int posY = layerHeight - (1 + tilePos / layerWidth);
			int gid = Integer.parseInt(tile.getAttributeValue("gid"));
			
			if (gid == 0) continue;
			
			addTile(gid, layerPos, posX, posY, propertiesMap);
		}
	}
	
	private void parseDataCSV(int layerPos, Element layerdata, int layerWidth, int layerHeight, HashMap<String, String> propertiesMap) throws TmxMapParsingException{
		String[] csvContent = layerdata.getValue().replaceAll("[\r\n]", "").split(",");
		
		for (int tilePos = 0; tilePos < csvContent.length; tilePos++) {
			int posX = tilePos % layerWidth;
			int posY = layerHeight - (1 + tilePos / layerWidth);
			int gid = Integer.parseInt(csvContent[tilePos]);
			
			if (gid == 0) continue;
			
			addTile(gid, layerPos, posX, posY, propertiesMap);
		}
	}
	
	private void parseDataBase64_uncompressed(int layerPos, Element layerdata, int layerWidth, int layerHeight, HashMap<String, String> propertiesMap) throws TmxMapParsingException {
		parseDataBase64(layerPos, Base64.decodeBase64(layerdata.getValue().trim()), layerWidth, layerHeight, propertiesMap);
	}

	private void parseDataBase64_gzip(int layerPos, Element layerdata, int layerWidth, int layerHeight, HashMap<String, String> propertiesMap) throws IOException, TmxMapParsingException {
		byte[] compressed_data = Base64.decodeBase64(layerdata.getValue().trim());

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		IOUtils.copy(new GZIPInputStream(new ByteArrayInputStream(compressed_data)), out);
		out.close();
		
		byte[] uncompressed_data = out.toByteArray();

		parseDataBase64(layerPos, uncompressed_data, layerWidth, layerHeight, propertiesMap);
	}
	
	private void parseDataBase64_zlib(int layerPos, Element layerdata, int layerWidth, int layerHeight, HashMap<String, String> propertiesMap) throws IOException, DataFormatException, TmxMapParsingException {
		byte[] compressed_data = Base64.decodeBase64(layerdata.getValue().trim());
		
		Inflater inflater = new Inflater();  
		inflater.setInput(compressed_data); 
		 
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(compressed_data.length); 
		byte[] buffer = new byte[1024]; 
		while (!inflater.finished()) { 
			int count = inflater.inflate(buffer); 
			outputStream.write(buffer, 0, count); 
		} 
		outputStream.close(); 
		
		byte[] uncompressed_data = outputStream.toByteArray();
		
		parseDataBase64(layerPos, uncompressed_data, layerWidth, layerHeight, propertiesMap);
	}
	
	private void parseDataBase64(int layerPos, byte[] content, int layerWidth, int layerHeight, HashMap<String, String> propertiesMap) throws TmxMapParsingException {
		IntBuffer data = ByteBuffer.wrap(content).order(ByteOrder.LITTLE_ENDIAN).asIntBuffer();

		int tilePos = 0;
		while (data.hasRemaining()) {
			int posX = tilePos % layerWidth;
			int posY = layerHeight - (1 + tilePos / layerWidth);
			int gid = data.get();

			tilePos++;
			if (gid == 0) continue;
	
			addTile(gid, layerPos, posX, posY, propertiesMap);
		}
	}
}
