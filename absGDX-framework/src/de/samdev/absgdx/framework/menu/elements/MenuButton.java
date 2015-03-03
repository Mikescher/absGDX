package de.samdev.absgdx.framework.menu.elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import de.samdev.absgdx.framework.menu.attributes.HorzAlign;
import de.samdev.absgdx.framework.menu.attributes.RectangleRadius;
import de.samdev.absgdx.framework.menu.attributes.VertAlign;

/**
 * A click-able Button
 */
public class MenuButton extends MenuElement {

	private final MenuLabel innerLabel;
	
	private RectangleRadius padding = new RectangleRadius(5, 5, 5, 5);
	
	/**
	 * Creates a new MenuButton
	 */
	public MenuButton() {
		super();
		
		innerLabel = new MenuLabel();
		innerLabel.setAutoScale(true);
		innerLabel.setAlign(HorzAlign.CENTER, VertAlign.CENTER);
	}

	@Override
	public void render(SpriteBatch sbatch, ShapeRenderer srenderer, BitmapFont font) {
		innerLabel.setPosition(getPositionX() + padding.left, getPositionY() + padding.top);
		innerLabel.setSize(getWidth() - padding.getHorizontalSum(), getHeight() - padding.getVerticalSum());
		
		srenderer.begin(ShapeType.Filled);
		{
			float grayValue = 1f - (getDepth() % 16) / 15f;
			srenderer.setColor(grayValue, grayValue, grayValue, 1f);
			srenderer.rect(getPositionX(), getPositionY(), getWidth(), getHeight());
		}
		srenderer.end();
		
//		srenderer.begin(ShapeType.Line);
//		{
//			srenderer.setColor(Color.BLACK);
//			srenderer.rect(getPositionX(), getPositionY(), getWidth(), getHeight());
//			
//			srenderer.rect(getPositionX() + 05, getPositionY() + 05, getWidth() - 10, getHeight() - 10);
//			srenderer.rect(getPositionX() + 10, getPositionY() + 10, getWidth() - 20, getHeight() - 20);
//		}
//		srenderer.end();
		
		innerLabel.render(sbatch, srenderer, font);
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the text padding
	 */
	public RectangleRadius getPadding() {
		return padding;
	}

	/**
	 * Set the padding of the text
	 * 
	 * @param padding the new padding
	 */
	public void setPadding(RectangleRadius padding) {
		this.padding = padding;
	}

	/**
	 * Set the padding of the text
	 * 
	 * @param top the top padding
	 * @param left the left padding
	 * @param bottom the bottom padding
	 * @param right the right padding
	 */
	public void setPadding(int top, int left, int bottom, int right) {
		this.padding = new RectangleRadius(top, left, bottom, right);
	}

	/**
	 * @return the displayed content
	 */
	public String getContent() {
		return innerLabel.getContent();
	}

	/**
	 * Set the displayed content
	 * 
	 * @param content the content
	 */
	public void setContent(String content) {
		innerLabel.setContent(content);
	}

	/**
	 * @return the horizontal alignment
	 */
	public HorzAlign getHorizontalAlign() {
		return innerLabel.getHorizontalAlign();
	}

	/**
	 * Set the horizontal alignment
	 * 
	 * @param hAlign the horizontal Alignment
	 */
	public void setHorizontalAlign(HorzAlign hAlign) {
		innerLabel.setHorizontalAlign(hAlign);
	}

	/**
	 * @return the vertical alignment
	 */
	public VertAlign getVerticalAlign() {
		return innerLabel.getVerticalAlign();
	}

	/**
	 * Set the vertical alignment
	 * 
	 * @param vAlign the vertical alignment
	 */
	public void setVerticalAlign(VertAlign vAlign) {
		innerLabel.setVerticalAlign(vAlign);
	}
	
	/**
	 * Set both (vertical & horizontal) alignment
	 * 
	 * @param hAlign the horizontal Alignment
	 * @param vAlign the vertical alignment
	 */
	public void setAlign(HorzAlign hAlign, VertAlign vAlign) {
		innerLabel.setAlign(hAlign, vAlign);
	}

	/**
	 * Get the current font scaling 
	 * >1 means scaling up
	 * <1 means scaling down
	 * 
	 * @return the current scaling of the displayed font
	 */
	public float getFontScale() {
		return innerLabel.getFontScale();
	}

	/**
	 * Set the current font scaling 
	 * >1 means scaling up
	 * <1 means scaling down
	 * 
	 * @param fontscale the new font scale
	 */
	public void setFontScale(float fontscale) {
		innerLabel.setFontScale(fontscale);
	}

	/**
	 * @return the foreground color of the displayed text
	 */
	public Color getColor() {
		return innerLabel.getColor();
	}

	/**
	 * Set the foreground color of the displayed text
	 * 
	 * @param color the text color
	 */
	public void setColor(Color color) {
		innerLabel.setColor(color);
	}

	/**
	 * Return if autoscale is enabled
	 * 
	 * in autoscale mode the font scale is ignored and the text is displayed as big as possible
	 * 
	 * @return true if enabled
	 */
	public boolean getAutoScale() {
		return innerLabel.getAutoScale();
	}

	/**
	 * Enable/Disable the autoscale mode
	 * 
	 * in autoscale mode the font scale is ignored and the text is displayed as big as possible
	 * 
	 * @param autoScale true means "autoscale enabled"
	 */
	public void setAutoScale(boolean autoScale) {
		innerLabel.setAutoScale(autoScale);
	}
}
