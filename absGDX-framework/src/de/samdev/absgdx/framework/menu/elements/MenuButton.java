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
public class MenuButton extends MenuBaseElement {
	
	//######## STATE ########
	
	private final MenuLabel innerLabel;
	
	private TextureRegion image = null;
	private int imagePadding = 0;
	
	private RectangleRadius padding = new RectangleRadius(5, 5, 5, 5);
	
	private VisualButtonState visualState = VisualButtonState.NORMAL;

	//######## CACHE ########
	
	private int imageWidth = 0;
	private int imageHeight = 0;
	private int imageX = 0;
	private int imageY = 0;
	
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

	private void updateLayout() {
		if (hasImage())	{
			imageHeight = getHeight() - padding.getVerticalSum();
			imageWidth = (int)((image.getRegionWidth()*imageHeight) / (image.getRegionHeight() * 1f));
			
			imageX = getPositionX() + padding.left;
			imageY = getPositionY() + padding.top;
			
			innerLabel.setPosition(getPositionX() + padding.left + imageWidth + imagePadding, getPositionY() + padding.top);
			innerLabel.setSize(getWidth() - (padding.getHorizontalSum() + imageWidth + imagePadding), getHeight() - padding.getVerticalSum());
		} else {
			innerLabel.setPosition(getPositionX() + padding.left, getPositionY() + padding.top);
			innerLabel.setSize(getWidth() - padding.getHorizontalSum(), getHeight() - padding.getVerticalSum());
			
			imageWidth = 0;
		}
	}
	
	@Override
	public void renderDebugGridLines(ShapeRenderer srenderer, GameSettings settings) {
		super.renderDebugGridLines(srenderer, settings);
		
		updateLayout();

		srenderer.begin(ShapeType.Line);
		{
			srenderer.setColor(settings.debugMenuBordersColorL2.get());
			srenderer.rect(innerLabel.getPositionX(), innerLabel.getPositionY(), innerLabel.getWidth(), innerLabel.getHeight());
			
			if (hasImage())
				srenderer.rect(imageX, imageY, imageWidth, imageHeight);
		}
		srenderer.end();
	}

	@Override
	public void render(SpriteBatch sbatch, BitmapFont font, int offX, int offY) {
		updateLayout();
		
		if (getTextureProvider().hasGeneric9SideTextures(getClass(), visualState)) {
			render9SideTexture(sbatch, visualState, offX, offY);
		}
		
		if (getTextureProvider().hasPaddingTextures(getClass(), visualState)) {
			renderPaddingTexture(sbatch, visualState, offX, offY);
		} else if (getTextureProvider().hasPaddingTextures(getClass())) {
			renderPaddingTexture(sbatch, offX, offY);
		}
		
		if (hasImage()) {
			MenuRenderHelper.drawTextureStretched(sbatch, image, offX + imageX, offY + imageY, imageWidth, imageHeight);
		}
	
		innerLabel.render(sbatch, font, offX, offY);
	}

	@Override
	public void renderDebug(ShapeRenderer srenderer) {
		innerLabel.renderElementDebug(srenderer, owner);
	}

	@Override
	public void renderCustom(SpriteBatch sbatch, ShapeRenderer srenderer, BitmapFont font, int offX, int offY) {
		innerLabel.renderElementCustom(sbatch, srenderer, font, owner, offX, offY);
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

	/**
	 * Set the image padding (distance between image and text)
	 * 
	 * @param pad the padding
	 */
	public void setImagePadding(int pad) {
		imagePadding = pad;
	}

	/**
	 * Set an image to display in the button
	 * 
	 * @param texture the image
	 */
	public void setImage(TextureRegion texture) {
		image = texture;
	}

	/**
	 * Clear the displayed image (only show text)
	 */
	public void clearImage() {
		setImage(null);
	}
	
	/**
	 * If this button has an image set
	 * 
	 * @return true if image is set
	 */
	public boolean hasImage() {
		return image != null;
	}
}
