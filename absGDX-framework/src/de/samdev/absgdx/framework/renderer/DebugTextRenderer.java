package de.samdev.absgdx.framework.renderer;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import de.samdev.absgdx.framework.AgdxGame;

/**
 * An class to render debug text in the top-left corner
 */
public class DebugTextRenderer {
	private final static float LINE_DISTANCE = 4;
	
	private final float positionX;
	private final float positionY;
	
	private final BitmapFont renderFont;
	private final SpriteBatch renderBatch;
	private final ShapeRenderer renderShape;
	private final AgdxGame owner;
	
	private final List<String> text = new ArrayList<String>();
	
	/**
	 * Creates a new DebugTextRenderer
	 * 
	 * @param owner the owner of the DebugText
	 * @param font the used font
	 * @param batch the BatchRenderer to use
	 * @param shape the ShapeRenderer to use
	 * @param x the initial x position
	 * @param y the initial y position
	 */
	public DebugTextRenderer(AgdxGame owner, BitmapFont font, SpriteBatch batch, ShapeRenderer shape, float x, float y) {
		super();
		
		this.owner = owner;
		
		this.renderBatch = batch;
		this.renderFont = font;
		this.renderShape = shape;
		
		this.positionX = x;
		this.positionY = -y;
	}

	/**
	 * Starts the rendering
	 * 
	 * @param fontsize the font size
	 */
	public void begin(float fontsize) {
		renderFont.setScale(fontsize);
		
		text.clear();
	}
	
	/**
	 * Adds a formatted line
	 * 
	 * @param format
	 * @param args
	 */
	public void drawFormatted(String format, Object... args) {
		this.draw(String.format(format, args));
	}

	/**
	 * Adds an empty line (= delimiter)
	 */
	public void draw() {
		draw("");
	}
	
	/**
	 * Adds a single line
	 * 
	 * @param txt
	 */
	public void draw(String txt) {
		text.add(txt);
	}
	
	/**
	 * Ends the rendering
	 * 
	 * Effectively here is where all the rendering is done
	 */
	public void end() {
		renderShape.begin(ShapeType.Filled);
		renderShape.setColor(Color.WHITE);
		for (int i = 0; i < text.size(); i++) {
			if (text.get(i).isEmpty()) continue;
			
			float x = positionX;
			float y = positionY + owner.getScreenHeight() - i * (renderFont.getLineHeight() + LINE_DISTANCE);
			
			TextBounds b = renderFont.getBounds(text.get(i));
			
			renderShape.rect(x - 1, y + 2, b.width + 2, -(b.height + 5));
		}
		renderShape.end();
		
		renderBatch.begin();
		for (int i = 0; i < text.size(); i++) {
			if (text.get(i).isEmpty()) continue;
			
			float x = positionX;
			float y = positionY + owner.getScreenHeight() - i * (renderFont.getLineHeight() + LINE_DISTANCE);
			
			renderFont.draw(renderBatch, text.get(i), x, y);
		}
		renderBatch.end();
	}
}
