package de.samdev.absgdx.framework.menu.elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import de.samdev.absgdx.framework.menu.attributes.HorzAlign;
import de.samdev.absgdx.framework.menu.attributes.VertAlign;

/**
 * A simple Text Display
 *
 */
public class MenuLabel extends MenuElement {
	private String content = "";

	private Color color = Color.BLACK;
	private float fontscale = 1f;
	private boolean autoScale = false;
	
	private HorzAlign hAlign = HorzAlign.LEFT;
	private VertAlign vAlign = VertAlign.TOP;
	
	/**
	 * Create a new Label
	 */
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
		float scale = Math.min(getWidth() / Math.abs(bounds.width), getHeight() / Math.abs(bounds.height));
		
		font.setScale(scale, -scale);
	}

	@Override
	public void update(float delta) {
		// Do nothing
	}

	/**
	 * @return the displayed content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Set the displayed content
	 * 
	 * @param content the content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the horizontal alignment
	 */
	public HorzAlign getHorizontalAlign() {
		return hAlign;
	}

	/**
	 * Set the horizontal alignment
	 * 
	 * @param hAlign the horizontal Alignment
	 */
	public void setHorizontalAlign(HorzAlign hAlign) {
		this.hAlign = hAlign;
	}

	/**
	 * @return the vertical alignment
	 */
	public VertAlign getVerticalAlign() {
		return vAlign;
	}

	/**
	 * Set the vertical alignment
	 * 
	 * @param vAlign the vertical alignment
	 */
	public void setVerticalAlign(VertAlign vAlign) {
		this.vAlign = vAlign;
	}
	
	/**
	 * Set both (vertical & horizontal) alignment
	 * 
	 * @param hAlign the horizontal Alignment
	 * @param vAlign the vertical alignment
	 */
	public void setAlign(HorzAlign hAlign, VertAlign vAlign) {
		setHorizontalAlign(hAlign);
		setVerticalAlign(vAlign);
	}

	/**
	 * Get the current font scaling 
	 * >1 means scaling up
	 * <1 means scaling down
	 * 
	 * @return the current scaling of the displayed font
	 */
	public float getFontScale() {
		return fontscale;
	}

	/**
	 * Set the current font scaling 
	 * >1 means scaling up
	 * <1 means scaling down
	 * 
	 * @param fontscale the new font scale
	 */
	public void setFontScale(float fontscale) {
		this.fontscale = fontscale;
	}

	/**
	 * @return the foreground color of the displayed text
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Set the foreground color of the displayed text
	 * 
	 * @param color the text color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Return if autoscale is enabled
	 * 
	 * in autoscale mode the font scale is ignored and the text is displayed as big as possible
	 * 
	 * @return true if enabled
	 */
	public boolean getAutoScale() {
		return autoScale;
	}

	/**
	 * Enable/Disable the autoscale mode
	 * 
	 * in autoscale mode the font scale is ignored and the text is displayed as big as possible
	 * 
	 * @param autoScale true means "autoscale enabled"
	 */
	public void setAutoScale(boolean autoScale) {
		this.autoScale = autoScale;
	}

}
