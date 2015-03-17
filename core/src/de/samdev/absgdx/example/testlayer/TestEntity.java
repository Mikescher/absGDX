package de.samdev.absgdx.example.testlayer;

import java.util.Random;

import de.samdev.absgdx.example.Textures;
import de.samdev.absgdx.framework.entities.SimpleEntity;

public class TestEntity extends SimpleEntity {

	static Random rand = new Random();
	
	public TestEntity(float x, float y) {
		super(Textures.tex_Bush_full, 1, 1);
		
		setPosition(x, y);
		
		setZLayer(rand.nextInt(9999));
	}

}
