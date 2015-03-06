package de.samdev.absgdx.framework.menu.elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import de.samdev.absgdx.framework.menu.GUITextureProvider;
import de.samdev.absgdx.framework.menu.attributes.HorzAlign;
import de.samdev.absgdx.framework.menu.attributes.RectangleRadius;
import de.samdev.absgdx.framework.menu.attributes.TextAutoScaleMode;
import de.samdev.absgdx.framework.menu.attributes.VertAlign;
import de.samdev.absgdx.framework.menu.events.MenuButtonListener;
import de.samdev.absgdx.framework.util.MenuRenderHelper;

/**
 * A switch-able Button
 */
public class MenuCheckbox extends MenuElement {
	private final MenuLabel innerLabel;
	private final MenuImage innerImage;
	
	private boolean checked = false;
	private RectangleRadius padding = new RectangleRadius(5, 5, 5, 5);
	
	/**
	 * Creates a new MenuButton
	 */
	public MenuCheckbox() {
		super();
		
		innerLabel = new MenuLabel();
		innerImage = new MenuImage();
		
		innerLabel.setAutoScale(TextAutoScaleMode.VERTICAL);
		innerLabel.setAlign(HorzAlign.LEFT, VertAlign.CENTER);
	}
	
	/**
	 * Creates a new MenuButton
	 * 
	 * @param texprovider the texture provider for this element
	 */
	public MenuCheckbox(GUITextureProvider texprovider) {
		super(texprovider);
		
		innerLabel = new MenuLabel();
		innerImage = new MenuImage();
		
		innerLabel.setAutoScale(TextAutoScaleMode.VERTICAL);
		innerLabel.setAlign(HorzAlign.LEFT, VertAlign.CENTER);
	}
	
	/**
	 * Creates a new MenuButton
	 * 
	 * @param identifier the unique button identifier
	 * @param texprovider the texture provider for this element
	 */
	public MenuCheckbox(String identifier, GUITextureProvider texprovider) {
		super(identifier, texprovider);
		
		innerLabel = new MenuLabel();
		innerImage = new MenuImage();
		
		innerLabel.setAutoScale(TextAutoScaleMode.VERTICAL);
		innerLabel.setAlign(HorzAlign.LEFT, VertAlign.CENTER);
	}

	@Override
	public void render(SpriteBatch sbatch, ShapeRenderer srenderer, BitmapFont font) {
		TextureRegion tex = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_CHECKBOX_IMG, isChecked());
		
		innerImage.setPosition(getPositionX(), getPositionY());
		innerImage.setSize(getWidth() - getHeight(), getHeight());
		
		innerLabel.setPosition(getPositionX() + getHeight() + padding.left, getPositionY() + padding.top);
		innerLabel.setSize(getWidth() - getHeight() - padding.left - padding.right, getHeight() - padding.top - padding.bottom);
		
		if (tex != null) {
			renderTextured(sbatch, tex);
		} else {
			renderSimple(srenderer);			
		}
	
		innerLabel.render(sbatch, srenderer, font);
	}

	private void renderTextured(SpriteBatch sbatch, TextureRegion texture) {
		sbatch.begin();
		
		MenuRenderHelper.drawTextureStretched(sbatch, texture, getPositionX(), getPositionY(), getHeight(), getHeight());
		
		sbatch.end();
	}

	private void renderSimple(ShapeRenderer srenderer) {
		srenderer.begin(ShapeType.Filled);
		{
			float grayValue = 1f - (getDepth() % 16) / 15f;
			srenderer.setColor(grayValue, grayValue, grayValue, 1f);
			srenderer.rect(getPositionX(), getPositionY(), getHeight(), getHeight());
		}
		srenderer.end();
		
		srenderer.begin(ShapeType.Line);
		{
			srenderer.setColor(Color.BLACK);
			srenderer.rect(getPositionX(), getPositionY(), getHeight(), getHeight());
			
			if (isChecked()) {
				srenderer.line(getPositionX(), getPositionY(), getPositionX() + getHeight(), getPositionY() + getHeight());
				srenderer.line(getPositionX() + getHeight(), getPositionY(), getPositionX(), getPositionY() + getHeight());
			}
		}
		srenderer.end();
	}

	@Override
	public void update(float delta) {
		// NOP
	}
	
	/**
	 * Adds a new listener
	 * 
	 * @param l the new listener
	 */
	public void addButtonListener(MenuButtonListener l) {
		super.addElementListener(l);
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
	 * Return the Autoscale Mode
	 * 
	 * in autoscale mode the font scale is ignored and the text is displayed as big as possible
	 * 
	 * @return the current mode
	 */
	public TextAutoScaleMode getAutoScale() {
		return innerLabel.getAutoScale();
	}

	/**
	 * Set the autoscale mode
	 * 
	 * in autoscale mode the font scale is ignored and the text is displayed as big as possible
	 * 
	 * @param autoScale the desired mode
	 */
	public void setAutoScale(TextAutoScaleMode autoScale) {
		innerLabel.setAutoScale(autoScale);
	}

	@Override
	public MenuElement getElementAt(int x, int y) {
		return this;
	}

	@Override
	public void onPointerClicked() {
		super.onPointerClicked();

		setChecked(! isChecked());
	}


	@Override
	public int getElementCount() {
		return 1;
	}

	/**
	 * @return if checkbox is checked
	 */
	public boolean isChecked() {
		return checked;
	}

	/**
	 * Ckeck/Uncheck the checkbox
	 * 
	 * @param checked the new state
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
