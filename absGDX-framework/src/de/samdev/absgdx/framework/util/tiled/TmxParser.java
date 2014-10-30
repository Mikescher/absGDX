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

public class TmxParser {
	private String fileContent;
	
	public TmxParser(String xml) {
		super();
		
		this.fileContent = xml;
	}

	public void parse() throws ParsingException, ValidityException, IOException, DataFormatException {
		Builder xomBuilder = new Builder();
		
		Document doc = xomBuilder.build(this.fileContent, null);
		
		Element mapElement = doc.getRootElement();
		
		Elements layerList = mapElement.getChildElements("layer");
		for (int layerPos = 0; layerPos < layerList.size(); layerPos++) {
			Element layer = layerList.get(layerPos);
			
			int layerWidth = Integer.parseInt(layer.getAttributeValue("width"));
			int layerHeight = Integer.parseInt(layer.getAttributeValue("height"));
			
			HashMap<String, String> propertiesMap = parseProperties(layer, layerWidth, layerHeight);

			Element layerdata = layer.getFirstChildElement("data");
			parseData(layerPos, layerdata, layerWidth, propertiesMap);
		}
	}

	private HashMap<String, String> parseProperties(Element layer, int layerWidth, int layerHeight) {
		Element layerprop = layer.getFirstChildElement("properties");
		HashMap<String, String> propertiesMap = new HashMap<String, String>();
		propertiesMap.put("width", "" + layerWidth);
		propertiesMap.put("height", "" + layerHeight);
		
		if (layerprop != null) {
			Elements propertyList = layerprop.getChildElements("property");
			for (int i = 0; i < propertyList.size(); i++) {
				propertiesMap.put(propertyList.get(i).getAttributeValue("name"), propertyList.get(i).getAttributeValue("value"));
			}
		}
		return propertiesMap;
	}

	public String cntr = "";
	
	private void handleTile(int gid, int layer, int posX, int posY, HashMap<String, String> propertiesMap) {
//		System.out.println("[" + posX + "|" + posY + "]-> " + gid + " (layer: " + layer + ")");
//		System.out.println("  " + propertiesMap.toString());
		
		cntr += layer + ",";
		cntr += posX + ",";
		cntr += posY + ",";
		cntr += gid;
		cntr += " : ";
	}

	private void parseData(int layerPos, Element layerdata, int layerWidth, HashMap<String, String> propertiesMap) throws ParsingException, IOException, DataFormatException {
		String encoding = layerdata.getAttributeValue("encoding");
		String compression = layerdata.getAttributeValue("compression");
		
		if (encoding == null) encoding = "xml";
		
		if (encoding.equalsIgnoreCase("xml") && compression == null) 
			parseDataXML(layerPos, layerdata, layerWidth, propertiesMap);
		else if (encoding.equalsIgnoreCase("csv") && compression == null) 
			parseDataCSV(layerPos, layerdata, layerWidth, propertiesMap);
		else if (encoding.equalsIgnoreCase("base64") && compression == null) 
			parseDataBase64_uncompressed(layerPos, layerdata, layerWidth, propertiesMap);
		else if (encoding.equalsIgnoreCase("base64") && compression.equals("gzip")) 
			parseDataBase64_gzip(layerPos, layerdata, layerWidth, propertiesMap);
		else if (encoding.equalsIgnoreCase("base64") && compression.equals("zlib")) 
			parseDataBase64_zlib(layerPos, layerdata, layerWidth, propertiesMap);
		else 
			throw new ParsingException("Unknown Encoding+compresison: " + encoding + " :: " + compression);
	}
	
	private void parseDataXML(int layerPos, Element layerdata, int layerWidth, HashMap<String, String> propertiesMap) {
		Elements tileList = layerdata.getChildElements("tile");
		
		for (int tilePos = 0; tilePos < tileList.size(); tilePos++) {
			Element tile = tileList.get(tilePos);
			
			int posX = tilePos % layerWidth;
			int posY = tilePos / layerWidth;
			int gid = Integer.parseInt(tile.getAttributeValue("gid"));
			
			if (gid == 0) continue;
			
			handleTile(gid, layerPos, posX, posY, propertiesMap);
		}
	}
	
	private void parseDataCSV(int layerPos, Element layerdata, int layerWidth, HashMap<String, String> propertiesMap) {
		String[] csvContent = layerdata.getValue().replaceAll("[\r\n]", "").split(",");
		
		for (int tilePos = 0; tilePos < csvContent.length; tilePos++) {
			int posX = tilePos % layerWidth;
			int posY = tilePos / layerWidth;
			int gid = Integer.parseInt(csvContent[tilePos]);
			
			if (gid == 0) continue;
			
			handleTile(gid, layerPos, posX, posY, propertiesMap);
		}
	}
	
	private void parseDataBase64_uncompressed(int layerPos, Element layerdata, int layerWidth, HashMap<String, String> propertiesMap) {
		parseDataBase64(layerPos, Base64.decodeBase64(layerdata.getValue().trim()), layerWidth, propertiesMap);
	}

	private void parseDataBase64_gzip(int layerPos, Element layerdata, int layerWidth, HashMap<String, String> propertiesMap) throws IOException {
		byte[] compressed_data = Base64.decodeBase64(layerdata.getValue().trim());

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		IOUtils.copy(new GZIPInputStream(new ByteArrayInputStream(compressed_data)), out);
		out.close();
		
		byte[] uncompressed_data = out.toByteArray();

		parseDataBase64(layerPos, uncompressed_data, layerWidth, propertiesMap);
	}
	
	private void parseDataBase64_zlib(int layerPos, Element layerdata, int layerWidth, HashMap<String, String> propertiesMap) throws IOException, DataFormatException {
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
		
		parseDataBase64(layerPos, uncompressed_data, layerWidth, propertiesMap);
	}
	
	private void parseDataBase64(int layerPos, byte[] content, int layerWidth, HashMap<String, String> propertiesMap) {
		IntBuffer data = ByteBuffer.wrap(content).order(ByteOrder.LITTLE_ENDIAN).asIntBuffer();

		int tilePos = 0;
		while (data.hasRemaining()) {
			int posX = tilePos % layerWidth;
			int posY = tilePos / layerWidth;
			int gid = data.get();

			tilePos++;
			if (gid == 0) continue;
	
			handleTile(gid, layerPos, posX, posY, propertiesMap);
		}
	}
}
