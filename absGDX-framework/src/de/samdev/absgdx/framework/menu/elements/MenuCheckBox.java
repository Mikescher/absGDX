package de.samdev.absgdx.framework.menu.elements;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import de.samdev.absgdx.framework.GameSettings;
import de.samdev.absgdx.framework.menu.GUITextureProvider;
import de.samdev.absgdx.framework.menu.attributes.CheckState;
import de.samdev.absgdx.framework.menu.attributes.HorzAlign;
import de.samdev.absgdx.framework.menu.attributes.RectangleRadius;
import de.samdev.absgdx.framework.menu.attributes.TextAutoScaleMode;
import de.samdev.absgdx.framework.menu.attributes.VertAlign;
import de.samdev.absgdx.framework.menu.events.MenuCheckboxListener;
import de.samdev.absgdx.framework.util.MenuRenderHelper;

/**
 * A switch-able Button
 */
public class MenuCheckBox extends MenuBaseElement {
	protected final MenuLabel innerLabel;
	protected final MenuImage innerImage;
	
	private boolean checked = false;
	private RectangleRadius lbl_padding = new RectangleRadius(5, 0, 0, 0);
	private RectangleRadius img_padding = new RectangleRadius();
	private boolean renderImage = true;
	private boolean renderLabel = true;
	
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
	public void renderDebugGridLines(ShapeRenderer srenderer, GameSettings settings) {
		super.renderDebugGridLines(srenderer, settings);
		
		srenderer.begin(ShapeType.Line);
		{
			srenderer.setColor(settings.debugMenuBordersColorL2.get());
			srenderer.rect(innerLabel.getPositionX(), innerLabel.getPositionY(), innerLabel.getWidth(), innerLabel.getHeight());
			srenderer.rect(innerImage.getPositionX(), innerImage.getPositionY(), innerImage.getWidth(), innerImage.getHeight());
		}
		srenderer.end();
	}
	
	@Override
	public void render(SpriteBatch sbatch, BitmapFont font, int offX, int offY) {
		TextureRegion tex = getCheckTexture();
		
		innerImage.setPosition(getPositionX() + img_padding.left, getPositionY() + img_padding.top);
		innerImage.setSize(getHeight() - img_padding.getHorizontalSum(), getHeight() - img_padding.getVerticalSum());
		
		innerLabel.setPosition(getPositionX() + getHeight() + img_padding.getHorizontalSum() + lbl_padding.left, getPositionY() + lbl_padding.top);
		innerLabel.setSize(getWidth() - getHeight() - img_padding.getHorizontalSum() - lbl_padding.getHorizontalSum(), getHeight() - lbl_padding.getVerticalSum());
		
		if (tex != null) {
			renderTextured(sbatch, tex, offX, offY);
		}
	
		if (renderLabel) {
			innerLabel.render(sbatch, font, offX, offY);			
		}
	}

	@Override
	public void renderCustom(SpriteBatch sbatch, ShapeRenderer srenderer, BitmapFont font) {
		if (renderLabel) {
			innerLabel.renderElementCustom(sbatch, srenderer, font, owner);		
		}
	}
	
	@Override
	public void renderDebug(ShapeRenderer srenderer) {
		if (renderLabel) {
			innerLabel.renderElementDebug(srenderer, owner);		
		}
	}

	protected TextureRegion getCheckTexture() {
		return getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_CHECK_IMG, CheckState.fromBoolean(isChecked()));
	}

	private void renderTextured(SpriteBatch sbatch, TextureRegion texture, int offX, int offY) {
		if (renderImage) {
			MenuRenderHelper.drawTextureStretched(sbatch, texture, offX + getPositionX() + img_padding.left, offY + getPositionY() + img_padding.top, getHeight() - img_padding.getVerticalSum(), getHeight() - img_padding.getHorizontalSum());
		}
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
	public void addCheckboxListener(MenuCheckboxListener l) {
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
	public MenuBaseElement getElementAt(int x, int y) {
		return this;
	}

	@Override
	public void onPointerClicked() {
		super.onPointerClicked();

		setChecked(! isChecked());
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

	/**
	 * Set if the check image is rendered
	 * 
	 * @param renderImage true if image shall be rendered
	 */
	public void setRenderImage(boolean renderImage) {
		this.renderImage = renderImage;
	}

	/**
	 * Set if the label is rendered
	 * 
	 * @param renderLabel true if label shall be rendered
	 */
	public void setRenderLabel(boolean renderLabel) {
		this.renderLabel = renderLabel;
	}

	@Override
	public List<MenuBaseElement> getDirectInnerElements() {
		List<MenuBaseElement> result = new ArrayList<MenuBaseElement>();
		result.add(innerLabel);
		result.add(innerImage);
		return result;
	}

	@Override
	public MenuBaseElement getElementByID(String id) {
		return identifier.equals(id) ? this : null;
	}
}
