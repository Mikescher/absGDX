package de.samdev.absgdx.framework.menu;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import de.samdev.absgdx.framework.layer.MenuLayer;

public class MenuPanel extends MenuElement {
	private List<MenuElement> elements;
	
	public MenuPanel(List<MenuElement> children) {
		super();
		
		this.elements = children;
	}

	public MenuPanel() {
		super();
		
		this.elements = new ArrayList<MenuElement>();
	}
	
	@Override
	public void renderElement(SpriteBatch sbatch, ShapeRenderer srenderer, BitmapFont defaultfont, MenuLayer owner) {
		super.renderElement(sbatch, srenderer, defaultfont, owner);
		
		srenderer.translate(getPositionX(), getPositionY(), 0);
		sbatch.getTransformMatrix().translate(getPositionX(), getPositionY(), 0);
		
		for (MenuElement element : elements) {
			element.renderElement(sbatch, srenderer, defaultfont, owner);
		}

		sbatch.getTransformMatrix().translate(-getPositionX(), -getPositionY(), 0);
		srenderer.translate(-getPositionX(), -getPositionY(), 0);
	}
	
	@Override
	public void render(SpriteBatch sbatch, ShapeRenderer srenderer, BitmapFont font) {
		srenderer.begin(ShapeType.Filled);
		{
			float grayValue = 1f - (getDepth() % 16) / 15f;
			srenderer.setColor(grayValue, grayValue, grayValue, 1f);
			srenderer.rect(getPositionX(), getPositionY(), getWidth(), getHeight());
		}
		srenderer.end();
	}

	@Override
	public void update(float delta) {
		for (MenuElement element : elements) {
			element.update(delta);
		}
	}

	@Override
	public void setDepth(int elementdepth) {
		super.setDepth(elementdepth);
		
		for (MenuElement element : elements) {
			element.setDepth(elementdepth + 1);
		}
	}
	
	public void pack() {
		setDepth(getDepth());
	}
	
	public void addChildren(MenuElement element) {
		elements.add(element);
		
		pack();
	}
	
	public boolean removeChildren(MenuElement element) {
		boolean success = elements.remove(element);
		
		pack();
		
		return success;
	}
}
