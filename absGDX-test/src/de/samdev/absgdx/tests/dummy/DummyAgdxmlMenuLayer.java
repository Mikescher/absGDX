package de.samdev.absgdx.tests.dummy;

import de.samdev.absgdx.framework.layer.AgdxmlLayer;
import de.samdev.absgdx.framework.util.exceptions.AgdxmlParsingException;


public class DummyAgdxmlMenuLayer extends AgdxmlLayer {

	public DummyAgdxmlMenuLayer(int screenwidth, int screenheight, String agdxml) throws AgdxmlParsingException {
		super(new DummyAgdxGame(screenwidth, screenheight), null, agdxml);
	}

	@Override
	public void onResize() {
		// NOP
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

}
