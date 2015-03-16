package de.samdev.absgdx.example.chessgame;

public class Vector2i {
	public int x;
	public int y;
	
	public Vector2i(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2i add(Vector2i other) {
		return new Vector2i(x + other.x, y + other.y);
	}
}
