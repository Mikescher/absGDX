package de.samdev.absgdx.framework.renderer;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.CharSequenceUtils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import de.samdev.absgdx.framework.AgdxGame;

public class DebugTextRenderer {
	private final static float LINE_DISTANCE = 4;
	
	private final float positionX;
	private final float positionY;
	
	private final BitmapFont renderFont;
	private final SpriteBatch renderBatch;
	private final ShapeRenderer renderShape;
	private final AgdxGame owner;
	
	private final List<String> text = new ArrayList<String>();
	
	public DebugTextRenderer(AgdxGame owner, BitmapFont font, SpriteBatch batch, ShapeRenderer shape, float x, float y) {
		super();
		
		this.owner = owner;
		
		this.renderBatch = batch;
		this.renderFont = font;
		this.renderShape = shape;
		
		this.positionX = x;
		this.positionY = -y;
	}

	public void begin(float fontsize) {
		renderFont.setScale(fontsize);
		
		text.clear();
	}
	
	public void draw(String txt) {
		text.add(txt);
	}
	
	public void end() {
		renderShape.begin(ShapeType.Filled);
		renderShape.setColor(Color.WHITE);
		for (int i = 0; i < text.size(); i++) {
			float x = positionX;
			float y = positionY + owner.getScreenHeight() - i * (renderFont.getLineHeight() + LINE_DISTANCE);
			
			renderShape.rect(x, y, renderFont.getSpaceWidth() * text.get(i).length(), -renderFont.getLineHeight());
			System.out.println(renderFont.getSpaceWidth());
		}
		renderShape.end();
		
		renderBatch.begin();
		for (int i = 0; i < text.size(); i++) {
			float x = positionX;
			float y = positionY + owner.getScreenHeight() - i * (renderFont.getLineHeight() + LINE_DISTANCE);
			
			renderFont.draw(renderBatch, text.get(i), x, y);
		}
		renderBatch.end();
	}
}
