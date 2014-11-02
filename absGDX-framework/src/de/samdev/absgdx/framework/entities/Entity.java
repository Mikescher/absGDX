package de.samdev.absgdx.framework.entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Entity {
	
	private float x = 0f;
	private float y = 0f;
	
	private float width;
	private float height;
	
	public Entity(float w, float h) {
		super();
		
		this.width = w;
		this.height = h;
	}

	public Rectangle getBoundings() {
		return new Rectangle(x, y, width, height);
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public float getPositionX() {
		return x;
	}

	public float getPositionY() {
		return y;
	}
	
	public Vector2 getPosition() {
		return new Vector2(x, y);
	}
}
