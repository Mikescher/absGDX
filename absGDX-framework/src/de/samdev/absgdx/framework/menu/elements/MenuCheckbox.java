package de.samdev.absgdx.framework.menu.elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import de.samdev.absgdx.framework.layer.MenuLayer;
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
public class MenuCheckBox extends MenuElement {
	private final MenuLabel innerLabel;
	private final MenuImage innerImage;
	
	private boolean checked = false;
	private RectangleRadius lbl_padding = new RectangleRadius(5, 0, 0, 0);
	private RectangleRadius img_padding = new RectangleRadius();
	
	/**
	 * Creates a new MenuButton
	 */
	public MenuCheckBox() {
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
	public MenuCheckBox(GUITextureProvider texprovider) {
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
	public MenuCheckBox(String identifier, GUITextureProvider texprovider) {
		super(identifier, texprovider);
		
		innerLabel = new MenuLabel();
		innerImage = new MenuImage();
		
		innerLabel.setAutoScale(TextAutoScaleMode.VERTICAL);
		innerLabel.setAlign(HorzAlign.LEFT, VertAlign.CENTER);
	}

	@Override
	public void renderElement(SpriteBatch sbatch, ShapeRenderer srenderer, BitmapFont defaultfont, MenuLayer owner) {
		super.renderElement(sbatch, srenderer, defaultfont, owner);
		
		if (owner.owner.settings.debugMenuBorders.isActive()) {
			srenderer.begin(ShapeType.Line);
			{
				srenderer.setColor(owner.owner.settings.debugMenuBordersColorL2.get());
				srenderer.rect(innerLabel.getPositionX(), innerLabel.getPositionY(), innerLabel.getWidth(), innerLabel.getHeight());
				srenderer.rect(innerImage.getPositionX(), innerImage.getPositionY(), innerImage.getWidth(), innerImage.getHeight());
			}
			srenderer.end();
		}
	}
	
	@Override
	public void render(SpriteBatch sbatch, ShapeRenderer srenderer, BitmapFont font) {
		TextureRegion tex = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_CHECK_IMG, isChecked());
		
		innerImage.setPosition(getPositionX() + img_padding.left, getPositionY() + img_padding.top);
		innerImage.setSize(getHeight() - img_padding.getHorizontalSum(), getHeight() - img_padding.getVerticalSum());
		
		innerLabel.setPosition(getPositionX() + getHeight() + img_padding.getHorizontalSum() + lbl_padding.left, getPositionY() + lbl_padding.top);
		innerLabel.setSize(getWidth() - getHeight() - img_padding.getHorizontalSum() - lbl_padding.getHorizontalSum(), getHeight() - lbl_padding.getVerticalSum());
		
		if (tex != null) {
			renderTextured(sbatch, tex);
		} else {
			renderSimple(srenderer);			
		}
	
		innerLabel.render(sbatch, srenderer, font);
	}

	private void renderTextured(SpriteBatch sbatch, TextureRegion texture) {
		sbatch.begin();
		
		MenuRenderHelper.drawTextureStretched(sbatch, texture, getPositionX() + img_padding.left, getPositionY() + img_padding.top, getHeight() - img_padding.getVerticalSum(), getHeight() - img_padding.getHorizontalSum());
		
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
	public RectangleRadius getLabelPadding() {
		return lbl_padding;
	}

	/**
	 * Set the padding of the text
	 * 
	 * @param padding the new padding
	 */
	public void setLabelPadding(RectangleRadius padding) {
		this.lbl_padding = padding;
	}

	/**
	 * Set the padding of the check image
	 * 
	 * @param top the top padding
	 * @param left the left padding
	 * @param bottom the bottom padding
	 * @param right the right padding
	 */
	public void setLabelPadding(int top, int left, int bottom, int right) {
		this.lbl_padding = new RectangleRadius(top, left, bottom, right);
	}

	/**
	 * @return the text padding
	 */
	public RectangleRadius getImagePadding() {
		return img_padding;
	}

	/**
	 * Set the padding of the check image
	 * 
	 * @param padding the new padding
	 */
	public void setImagePadding(RectangleRadius padding) {
		this.img_padding = padding;
	}

	/**
	 * Set the padding of the check image
	 * 
	 * @param top the top padding
	 * @param left the left padding
	 * @param bottom the bottom padding
	 * @param right the right padding
	 */
	public void setImagePadding(int top, int left, int bottom, int right) {
		this.img_padding = new RectangleRadius(top, left, bottom, right);
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
