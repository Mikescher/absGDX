package de.samdev.absgdx.framework.menu.elements;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import de.samdev.absgdx.framework.layer.MenuLayer;
import de.samdev.absgdx.framework.menu.GUITextureProvider;
import de.samdev.absgdx.framework.menu.attributes.HorzAlign;
import de.samdev.absgdx.framework.menu.attributes.RectangleRadius;
import de.samdev.absgdx.framework.menu.attributes.TextAutoScaleMode;
import de.samdev.absgdx.framework.menu.attributes.VertAlign;
import de.samdev.absgdx.framework.menu.attributes.VisualButtonState;
import de.samdev.absgdx.framework.menu.events.MenuButtonListener;

/**
 * A click-able Button
 */
public class MenuButton extends MenuBaseElement {
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

		if (getTextureProvider().hasGeneric9SideTextures(getClass(), VisualButtonState.NORMAL))
			setPadding(get9SidePadding(VisualButtonState.NORMAL));
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

		if (getTextureProvider().hasGeneric9SideTextures(getClass(), VisualButtonState.NORMAL))
			setPadding(get9SidePadding(VisualButtonState.NORMAL));
	}

	@Override
	public void renderElement(SpriteBatch sbatch, ShapeRenderer srenderer, BitmapFont defaultfont, MenuLayer owner) {
		super.renderElement(sbatch, srenderer, defaultfont, owner);
		
		if (owner.owner.settings.debugMenuBorders.isActive()) {
			srenderer.begin(ShapeType.Line);
			{
				srenderer.setColor(owner.owner.settings.debugMenuBordersColorL2.get());
				srenderer.rect(innerLabel.getPositionX(), innerLabel.getPositionY(), innerLabel.getWidth(), innerLabel.getHeight());
			}
			srenderer.end();
		}
	}

	@Override
	public void render(SpriteBatch sbatch, ShapeRenderer srenderer, BitmapFont font) {
		innerLabel.setPosition(getPositionX() + padding.left, getPositionY() + padding.top);
		innerLabel.setSize(getWidth() - padding.getHorizontalSum(), getHeight() - padding.getVerticalSum());
		
		if (getTextureProvider().hasGeneric9SideTextures(getClass(), visualState)) {
			render9SideTexture(sbatch, visualState);
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
	public MenuBaseElement getElementAt(int x, int y) {
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

	@Override
	public List<MenuBaseElement> getDirectInnerElements() {
		List<MenuBaseElement> result = new ArrayList<MenuBaseElement>();
		result.add(innerLabel);
		return result;
	}

	@Override
	public MenuBaseElement getElementByID(String id) {
		return identifier.equals(id) ? this : null;
	}
}
