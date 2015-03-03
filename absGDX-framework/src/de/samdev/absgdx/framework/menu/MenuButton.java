package de.samdev.absgdx.framework.menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class MenuButton extends MenuElement {

	public MenuButton() {
		// TODO Auto-generated constructor stub
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
		
		srenderer.begin(ShapeType.Line);
		{
			srenderer.setColor(Color.BLACK);
			srenderer.rect(getPositionX(), getPositionY(), getWidth(), getHeight());
			
			srenderer.rect(getPositionX() + 05, getPositionY() + 05, getWidth() - 10, getHeight() - 10);
			srenderer.rect(getPositionX() + 10, getPositionY() + 10, getWidth() - 20, getHeight() - 20);
		}
		srenderer.end();
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub

	}

}
