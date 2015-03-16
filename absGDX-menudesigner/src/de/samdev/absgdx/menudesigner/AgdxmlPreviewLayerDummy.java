package de.samdev.absgdx.menudesigner;

import com.badlogic.gdx.utils.reflect.Method;
import com.badlogic.gdx.utils.reflect.ReflectionException;

import de.samdev.absgdx.framework.layer.AgdxmlLayer;
import de.samdev.absgdx.framework.util.exceptions.AgdxmlParsingException;

public class AgdxmlPreviewLayerDummy extends AgdxmlLayer {

	public AgdxmlPreviewLayerDummy(String agdxmlFileContent) throws AgdxmlParsingException {
		super(new AgdxPreviewGameDummy(), null, agdxmlFileContent);
	}

	@Override
	public void initialize() {
		// NOP
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Method getDeclaredMethod(String name, Class... parameterTypes) throws ReflectionException {
		return null;
	}
}
