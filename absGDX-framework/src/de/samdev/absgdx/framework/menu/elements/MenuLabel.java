package de.samdev.absgdx.framework.menu.elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import de.samdev.absgdx.framework.menu.attributes.HorzAlign;
import de.samdev.absgdx.framework.menu.attributes.TextAutoScaleMode;
import de.samdev.absgdx.framework.menu.attributes.VertAlign;
import de.samdev.absgdx.framework.menu.events.MenuElementListener;
import de.samdev.absgdx.framework.menu.events.MenuLabelListener;

/**
 * A simple Text Display
 *
 */
public class MenuLabel extends MenuElement {
	private String content = "";

	private Color color = Color.BLACK;
	private float fontscale = 1f;
	private TextAutoScaleMode autoScale = TextAutoScaleMode.NONE;
	
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
		doAutoScale(font);
		
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
		
		sbatch.begin();
		font.draw(sbatch, content, x, y);
		sbatch.end();
	}

	private float doAutoScale(BitmapFont font) {
		font.setScale(1, -1);
		
		float scaleX = getWidth() / Math.abs(font.getBounds(content).width);
		float scaleY = getHeight() / Math.abs(font.getBounds(content + "|AMXYZ*^._").height);
		
		float scale = Math.min(scaleX, scaleY);
		
		switch (autoScale) {
		case NONE:
			font.setScale(fontscale, -fontscale);
			return fontscale;
		case VERTICAL:
			font.setScale(scaleY, -scaleY);
			return scaleY;
		case HORIZONTAL:
			font.setScale(scaleX, -scaleX);
			return scaleX;
		case BOTH:
			font.setScale(scale, -scale);
			return scale;
		default:
			font.setScale(1, -1);
			return 1;
		}
	}

	@Override
	public void update(float delta) {
		// Do nothing
	}
	
	/**
	 * Adds a new listener
	 * 
	 * @param l the new listener
	 */
	public void addLabelListener(MenuLabelListener l) {
		super.addElementListener(l);
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
		
		for (MenuElementListener lst : listeners) {
			((MenuLabelListener)lst).onContentChanged(this, this.identifier, content);
		}
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
	 * Get the current  (real) font scaling 
	 * If Autoscale is [on], the autoscale value is returned
	 * 
	 * @param font the used font
	 * 
	 * @return the real current scaling of the displayed font
	 */
	public float getRealFontScale(BitmapFont font) {
		return doAutoScale(font);
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
	 * Return the Autoscale Mode
	 * 
	 * in autoscale mode the font scale is ignored and the text is displayed as big as possible
	 * 
	 * @return the current mode
	 */
	public TextAutoScaleMode getAutoScale() {
		return autoScale;
	}

	/**
	 * Set the autoscale mode
	 * 
	 * in autoscale mode the font scale is ignored and the text is displayed as big as possible
	 * 
	 * @param autoScale the desired mode
	 */
	public void setAutoScale(TextAutoScaleMode autoScale) {
		this.autoScale = autoScale;
	}

	@Override
	public MenuElement getElementAt(int x, int y) {
		return this;
	}
}
