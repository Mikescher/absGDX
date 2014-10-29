package de.samdev.absgdx.framework.tmxloader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class TMXLoader {
	public static TileMapData readTMX(String filename) throws SAXException {
		TileMapData t = null;

		try {
	
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser parser = spf.newSAXParser();
			
			// husch husch verschwindet :D
//			XMLReader reader = new parser.getXMLReader();

//			reader.parse((new InputSource(filename)));

		} catch (ParserConfigurationException pce) {

		}
		return t;
	}

}
