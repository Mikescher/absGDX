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
import de.samdev.absgdx.framework.menu.attributes.VisualButtonState;
import de.samdev.absgdx.framework.menu.events.MenuButtonListener;
import de.samdev.absgdx.framework.util.MenuRenderHelper;

/**
 * A click-able Button
 */
public class MenuButton extends MenuElement {
	private final MenuLabel innerLabel;
	
	private RectangleRadius padding = new RectangleRadius(5, 5, 5, 5);
	
	private VisualButtonState visualState = VisualButtonState.NORMAL;
	
	/**
	 * Creates a new MenuButton
	 */
	public MenuButton() {
		super();
		
		innerLabel = new MenuLabel();
		innerLabel.setAutoScale(TextAutoScaleMode.BOTH);
		innerLabel.setAlign(HorzAlign.CENTER, VertAlign.CENTER);
	}
	
	/**
	 * Creates a new MenuButton
	 * 
	 * @param texprovider the texture provider for this element
	 */
	public MenuButton(GUITextureProvider texprovider) {
		super(texprovider);
		
		innerLabel = new MenuLabel();
		innerLabel.setAutoScale(TextAutoScaleMode.BOTH);
		innerLabel.setAlign(HorzAlign.CENTER, VertAlign.CENTER);

		if (getTextureProvider().hasButtonTextures(VisualButtonState.NORMAL))
			autocalcPadding();
	}
	
	/**
	 * Creates a new MenuButton
	 * 
	 * @param identifier the unique button identifier
	 * @param texprovider the texture provider for this element
	 */
	public MenuButton(String identifier, GUITextureProvider texprovider) {
		super(identifier, texprovider);
		
		innerLabel = new MenuLabel();
		innerLabel.setAutoScale(TextAutoScaleMode.BOTH);
		innerLabel.setAlign(HorzAlign.CENTER, VertAlign.CENTER);

		if (getTextureProvider().hasButtonTextures(VisualButtonState.NORMAL))
			autocalcPadding();
	}

	private void autocalcPadding() {
		int pad_top_1 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_BUTTON_TL + "#" + VisualButtonState.NORMAL).getRegionHeight();
		int pad_top_2 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_BUTTON_TT + "#" + VisualButtonState.NORMAL).getRegionHeight();
		int pad_top_3 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_BUTTON_TR + "#" + VisualButtonState.NORMAL).getRegionHeight();

		int pad_lef_1 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_BUTTON_LL + "#" + VisualButtonState.NORMAL).getRegionWidth();
		int pad_lef_2 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_BUTTON_TL + "#" + VisualButtonState.NORMAL).getRegionWidth();
		int pad_lef_3 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_BUTTON_BL + "#" + VisualButtonState.NORMAL).getRegionWidth();
		
		int pad_bot_1 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_BUTTON_BL + "#" + VisualButtonState.NORMAL).getRegionHeight();
		int pad_bot_2 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_BUTTON_BB + "#" + VisualButtonState.NORMAL).getRegionHeight();
		int pad_bot_3 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_BUTTON_BR + "#" + VisualButtonState.NORMAL).getRegionHeight();

		int pad_rig_1 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_BUTTON_RR + "#" + VisualButtonState.NORMAL).getRegionWidth();
		int pad_rig_2 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_BUTTON_TR + "#" + VisualButtonState.NORMAL).getRegionWidth();
		int pad_rig_3 = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_BUTTON_BR + "#" + VisualButtonState.NORMAL).getRegionWidth();
		
		int pad_top = Math.max(Math.max(pad_top_1, pad_top_2), pad_top_3);
		int pad_lef = Math.max(Math.max(pad_lef_1, pad_lef_2), pad_lef_3);
		int pad_bot = Math.max(Math.max(pad_bot_1, pad_bot_2), pad_bot_3);
		int pad_rig = Math.max(Math.max(pad_rig_1, pad_rig_2), pad_rig_3);
		
		setPadding(pad_top, pad_lef, pad_bot, pad_rig);
	}

	@Override
	public void render(SpriteBatch sbatch, ShapeRenderer srenderer, BitmapFont font) {
		innerLabel.setPosition(getPositionX() + padding.left, getPositionY() + padding.top);
		innerLabel.setSize(getWidth() - padding.getHorizontalSum(), getHeight() - padding.getVerticalSum());
		
		if (getTextureProvider().hasButtonTextures(visualState)) {
			renderTextured(sbatch);
		} else {
			renderSimple(srenderer);
		}
	
		innerLabel.render(sbatch, srenderer, font);
	}

	private void renderSimple(ShapeRenderer srenderer) {
		srenderer.begin(ShapeType.Filled);
		{
			float grayValue = 1f - (getDepth() % 16) / 15f;
			srenderer.setColor(grayValue, grayValue, visualState.ordinal() /3f, 1f);
			srenderer.rect(getPositionX(), getPositionY(), getWidth(), getHeight());
		}
		srenderer.end();
		
		srenderer.begin(ShapeType.Line);
		{
			srenderer.setColor(Color.BLACK);
			srenderer.rect(getPositionX(), getPositionY(), getWidth(), getHeight());
		}
		srenderer.end();
	}

	private void renderTextured(SpriteBatch sbatch) {
		TextureRegion tex_TL = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_BUTTON_TL + "#" + visualState);
		TextureRegion tex_TT = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_BUTTON_TT + "#" + visualState);
		TextureRegion tex_TR = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_BUTTON_TR + "#" + visualState);
		TextureRegion tex_LL = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_BUTTON_LL + "#" + visualState);
		TextureRegion tex_CC = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_BUTTON_CC + "#" + visualState);
		TextureRegion tex_RR = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_BUTTON_RR + "#" + visualState);
		TextureRegion tex_BL = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_BUTTON_BL + "#" + visualState);
		TextureRegion tex_BB = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_BUTTON_BB + "#" + visualState);
		TextureRegion tex_BR = getTextureProvider().get(getClass(), GUITextureProvider.IDENT_TEX_BUTTON_BR + "#" + visualState);
		
		sbatch.getTransformMatrix().translate(getPositionX(), getPositionY(), 0);
		sbatch.begin();
		
		// Corners
		
		MenuRenderHelper.drawTexture(sbatch, tex_TL, 0, 0);
		MenuRenderHelper.drawTexture(sbatch, tex_TR, getWidth() - tex_TR.getRegionWidth(), 0);
		MenuRenderHelper.drawTexture(sbatch, tex_BL, 0, getHeight() - tex_BL.getRegionHeight());
		MenuRenderHelper.drawTexture(sbatch, tex_BR, getWidth() - tex_BR.getRegionWidth(), getHeight() - tex_BR.getRegionHeight());
		
		// Edges
		
		MenuRenderHelper.drawTextureRepeated(sbatch, tex_TT, tex_TL.getRegionWidth(), 0, getWidth() - tex_TL.getRegionWidth() - tex_TR.getRegionWidth(), tex_TT.getRegionHeight() + 20);
		MenuRenderHelper.drawTextureRepeated(sbatch, tex_LL, 0, tex_TL.getRegionHeight(), tex_LL.getRegionWidth(), getHeight() - tex_TL.getRegionHeight() - tex_BL.getRegionHeight());
		MenuRenderHelper.drawTextureRepeated(sbatch, tex_BB, tex_TL.getRegionWidth(), getHeight() -tex_BB.getRegionHeight(), getWidth() - tex_TL.getRegionWidth() - tex_TR.getRegionWidth(), tex_BB.getRegionHeight());
		MenuRenderHelper.drawTextureRepeated(sbatch, tex_RR, getWidth() - tex_RR.getRegionWidth(), tex_TL.getRegionHeight(), tex_LL.getRegionWidth(), getHeight() - tex_TL.getRegionHeight() - tex_BL.getRegionHeight());

		// center
		
		MenuRenderHelper.drawTextureRepeated(sbatch, tex_CC, tex_TL.getRegionWidth(), tex_TL.getRegionHeight(), getWidth() - tex_BR.getRegionWidth() - tex_TL.getRegionWidth(), getHeight() - tex_BR.getRegionHeight() - tex_TL.getRegionHeight());
		
		sbatch.end();
		sbatch.getTransformMatrix().translate(-getPositionX(), -getPositionY(), 0);
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
	public void onPointerDown() {
		super.onPointerDown();
		
		visualState = VisualButtonState.PRESSED;
	}

	@Override
	public void onPointerUp() {
		super.onPointerUp();
		
		visualState = isHovered() ? VisualButtonState.HOVERED : VisualButtonState.NORMAL;
	}

	@Override
	public void onStartHover() {
		super.onStartHover();
		
		visualState = VisualButtonState.HOVERED;
	}

	@Override
	public void onEndHover() {
		super.onEndHover();
		
		visualState = VisualButtonState.NORMAL;
	}
}
