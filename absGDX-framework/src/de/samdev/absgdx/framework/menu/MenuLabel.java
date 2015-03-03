package de.samdev.absgdx.framework.menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import de.samdev.absgdx.framework.menu.attributes.HorzAlign;
import de.samdev.absgdx.framework.menu.attributes.VertAlign;

public class MenuLabel extends MenuElement {
	private String content = "";

	private Color color = Color.BLACK;
	private float fontscale = 1f;
	private boolean autoScale = false;
	
	private HorzAlign hAlign = HorzAlign.LEFT;
	private VertAlign vAlign = VertAlign.TOP;
	
	public MenuLabel() {
		super();
	}

	@Override
	public void render(SpriteBatch sbatch, ShapeRenderer srenderer, BitmapFont font) {
		font.setScale(fontscale, -fontscale);
		if (getAutoScale()) doAutoScale(font);
		font.setColor(color);
		
		TextBounds bounds = font.getBounds(content);
		float bwidth = Math.abs(bounds.width);
		float bheight = Math.abs(bounds.height);
		
		float x = getPositionX();
		switch (hAlign) {
		case LEFT:
			x += 0;
			break;
		case RIGHT:
			x += getWidth() - bwidth;
			break;
		case CENTER:
			x += (getWidth() - bwidth) / 2f;
			break;
		default: 
			return;
		}
		
		float y = getPositionY();
		switch (vAlign) {
		case TOP:
			y += 0;
			break;
		case BOTTOM:
			y += getHeight() - bheight;
			break;
		case CENTER:
			y += (getHeight() - bheight) / 2f;
			break;
		default: 
			return;
		}
		
		sbatch.enableBlending();
		sbatch.begin();
		font.draw(sbatch, content, x, y);
		sbatch.end();
		sbatch.disableBlending();
	}

	private void doAutoScale(BitmapFont font) {
		font.setScale(1, -1);
		
		TextBounds bounds = font.getBounds(content);
		float scale = Math.min(getWidth() / bounds.width, getHeight() / bounds.height);
		
		font.setScale(scale, -scale);
	}

	@Override
	public void update(float delta) {
		// Do nothing
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public HorzAlign getHorizontalAlign() {
		return hAlign;
	}

	public void setHorizontalAlign(HorzAlign hAlign) {
		this.hAlign = hAlign;
	}

	public VertAlign getVerticalAlign() {
		return vAlign;
	}

	public void setVerticalAlign(VertAlign vAlign) {
		this.vAlign = vAlign;
	}
	
	public void setAlign(HorzAlign hAlign, VertAlign vAlign) {
		setHorizontalAlign(hAlign);
		setVerticalAlign(vAlign);
	}

	public float getFontScale() {
		return fontscale;
	}

	public void setFontScale(float fontscale) {
		this.fontscale = fontscale;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean getAutoScale() {
		return autoScale;
	}

	public void setAutoScale(boolean autoScale) {
		this.autoScale = autoScale;
	}

}
