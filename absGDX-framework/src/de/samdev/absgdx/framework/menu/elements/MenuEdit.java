package de.samdev.absgdx.framework.menu.elements;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import de.samdev.absgdx.framework.GameSettings;
import de.samdev.absgdx.framework.menu.GUITextureProvider;
import de.samdev.absgdx.framework.menu.attributes.FocusState;
import de.samdev.absgdx.framework.menu.attributes.HorzAlign;
import de.samdev.absgdx.framework.menu.attributes.RectangleRadius;
import de.samdev.absgdx.framework.menu.attributes.TextAutoScaleMode;
import de.samdev.absgdx.framework.menu.attributes.VertAlign;
import de.samdev.absgdx.framework.menu.events.MenuBaseElementListener;
import de.samdev.absgdx.framework.menu.events.MenuEditListener;

/**
 * A edit-able Text Field
 */
public class MenuEdit extends MenuBaseElement { //TODO Does not work on mobile - missing on screen keyboard
	private final static float BLINK_DELAY = 850f;
	
	private final MenuLabel innerLabel;
	
	private RectangleRadius padding = new RectangleRadius(5, 5, 5, 5);
	
	private String content = "";
	private float blinkCounter = 0;
	
	/**
	 * Creates a new MenuButton
	 */
	public MenuEdit() {
		super();
		
		innerLabel = new MenuLabel();
		innerLabel.setAlign(HorzAlign.LEFT, VertAlign.CENTER);

		innerLabel.setAutoScale(TextAutoScaleMode.VERTICAL);
		innerLabel.setContent("");
	}
	
	/**
	 * Creates a new MenuButton
	 * 
	 * @param texprovider the texture provider for this element
	 */
	public MenuEdit(GUITextureProvider texprovider) {
		super(texprovider);
		
		innerLabel = new MenuLabel();
		innerLabel.setAlign(HorzAlign.LEFT, VertAlign.CENTER);

		innerLabel.setAutoScale(TextAutoScaleMode.VERTICAL);
		innerLabel.setContent("");

		if (getTextureProvider().hasGeneric9SideTextures(getClass(), false))
			setPadding(get9SidePadding(false));
	}
	
	/**
	 * Creates a new MenuButton
	 * 
	 * @param identifier the unique button identifier
	 * @param texprovider the texture provider for this element
	 */
	public MenuEdit(String identifier, GUITextureProvider texprovider) {
		super(identifier, texprovider);
		
		innerLabel = new MenuLabel();
		innerLabel.setAlign(HorzAlign.LEFT, VertAlign.CENTER);

		innerLabel.setAutoScale(TextAutoScaleMode.VERTICAL);
		innerLabel.setContent("");

		if (getTextureProvider().hasGeneric9SideTextures(getClass(), false))
			setPadding(get9SidePadding(false));
	}

	@Override
	public void renderDebugGridLines(ShapeRenderer srenderer, GameSettings settings) {
		super.renderDebugGridLines(srenderer, settings);
		
		srenderer.begin(ShapeType.Line);
		{
			srenderer.setColor(settings.debugMenuBordersColorL2.get());
			srenderer.rect(innerLabel.getPositionX(), innerLabel.getPositionY(), innerLabel.getWidth(), innerLabel.getHeight());
		}
		srenderer.end();
	}

	@Override
	public void render(SpriteBatch sbatch, BitmapFont font, int offX, int offY) {
		FocusState fState = FocusState.fromBoolean(isFocused());
		
		innerLabel.setPosition(getPositionX() + padding.left, getPositionY() + padding.top);
		innerLabel.setSize(getWidth() - padding.getHorizontalSum(), getHeight() - padding.getVerticalSum());
		
		innerLabel.setContent(content);

		float innerScale = innerLabel.getRealFontScale(font);
		font.setScale(innerScale, -innerScale);

		float cbWidth = innerLabel.getHeight() / 9f;
		
		String disp = content;
		
		while (font.getBounds(disp).width + 2*cbWidth > innerLabel.getWidth() && disp.length() > 1) {
			disp = disp.substring(1);
		}
		
		innerLabel.setContent(disp);
		
		if (getTextureProvider().hasGeneric9SideTextures(getClass(), fState)) {
			render9SideTexture(sbatch, fState, offX, offY);
		}
		
		if (getTextureProvider().hasPaddingTextures(getClass(), fState)) {
			renderPaddingTexture(sbatch, fState, offX, offY);
		} else if (getTextureProvider().hasPaddingTextures(getClass())) {
			renderPaddingTexture(sbatch, offX, offY);
		} 
		
		innerLabel.render(sbatch, font, offX, offY);
	}

	@Override
	public void renderCustom(SpriteBatch sbatch, ShapeRenderer srenderer, BitmapFont font, int offX, int offY) {
		if (blinkCounter * 2 < BLINK_DELAY && isFocused()) {
			float cbWidth = innerLabel.getHeight() / 9f;
			float innerScale = innerLabel.getRealFontScale(font);
			font.setScale(innerScale, -innerScale);
						
			srenderer.begin(ShapeType.Filled);
			{
				srenderer.setColor(getColor());
	
				srenderer.rect(offX + innerLabel.getPositionX() + font.getBounds(innerLabel.getContent()).width + cbWidth, offY + innerLabel.getPositionY(), cbWidth, innerLabel.getHeight());
			}
			srenderer.end();
		}

		innerLabel.renderCustom(sbatch, srenderer, font, offX, offY);
	}

	@Override
	public void renderDebug(ShapeRenderer srenderer) {
		innerLabel.renderElementDebug(srenderer, owner);
	}

	@Override
	public void update(float delta) {
		blinkCounter += delta;
		while (blinkCounter > BLINK_DELAY)
			blinkCounter -= BLINK_DELAY;
	}
	
	/**
	 * Adds a new listener
	 * 
	 * @param l the new listener
	 */
	public void addEditListener(MenuEditListener l) {
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
		return content;
	}

	/**
	 * Set the displayed content
	 * 
	 * @param content the content
	 */
	public void setContent(String content) {
		this.content = content;

		for (MenuBaseElementListener lst : listeners) {
			((MenuEditListener)lst).onTextChanged(this, this.identifier, content);
		}
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

	@Override
	public MenuBaseElement getElementAt(int x, int y) {
		return this;
	}

	@Override
	public void onKeyTyped(char key) {
		super.onKeyTyped(key);
		
		if (key >= 32) {
			content += key;
			blinkCounter = 0;
		}
	}

	@Override
	public void onKeyDown(int keycode) {
		super.onKeyDown(keycode);
		
		if (keycode == Keys.BACKSPACE && content.length() > 0) {
			content = content.substring(0, content.length() - 1);
			blinkCounter = 0;
		}
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
