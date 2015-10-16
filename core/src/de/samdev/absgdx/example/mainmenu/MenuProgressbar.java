package de.samdev.absgdx.example.mainmenu;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import de.samdev.absgdx.framework.GameSettings;
import de.samdev.absgdx.framework.menu.GUITextureProvider;
import de.samdev.absgdx.framework.menu.attributes.HorzAlign;
import de.samdev.absgdx.framework.menu.attributes.RectangleRadius;
import de.samdev.absgdx.framework.menu.attributes.TextAutoScaleMode;
import de.samdev.absgdx.framework.menu.attributes.VertAlign;
import de.samdev.absgdx.framework.menu.elements.MenuBaseElement;
import de.samdev.absgdx.framework.menu.elements.MenuLabel;
import de.samdev.absgdx.framework.util.MenuRenderHelper;

public class MenuProgressbar extends MenuBaseElement {

	private MenuLabel innerLabel = new MenuLabel();
	
	public float perc = 0.65f;
	
	public MenuProgressbar() {
		super();
	}

	public MenuProgressbar(GUITextureProvider texprovider) {
		super(texprovider);
	}

	public MenuProgressbar(String ident) {
		super(ident);
	}

	public MenuProgressbar(String ident, GUITextureProvider texprovider) {
		super(ident, texprovider);
	}

	@Override
	public void render(SpriteBatch sbatch, BitmapFont font, int offX, int offY) {
		
		TextureRegion tex0 = getTextureProvider().get(getClass(), "0");
		float tex0_w = getHeight() * (tex0.getRegionWidth() * 1f/ tex0.getRegionHeight());
		
		TextureRegion tex1 = getTextureProvider().get(getClass(), "1");
		float tex1_w = getHeight() * (tex1.getRegionWidth() * 1f/ tex1.getRegionHeight());
		
		TextureRegion tex2 = getTextureProvider().get(getClass(), "2");
		TextureRegion tex3 = getTextureProvider().get(getClass(), "3");
		TextureRegion tex4 = getTextureProvider().get(getClass(), "4");
		
		float percpos = (getWidth() - tex4.getRegionWidth()) * perc + tex4.getRegionWidth()/2;
		RectangleRadius pad = new RectangleRadius(10, 10, 10, 10);
		
		innerLabel.setBoundaries((int)(getPositionX() + percpos - tex4.getRegionWidth()/2f) + pad.left, getPositionY() + pad.top, tex4.getRegionWidth() - pad.getHorizontalSum(), tex4.getRegionHeight() - pad.getVerticalSum());
		innerLabel.setContent((int)(perc*100) + "%");
		innerLabel.setColor(Color.WHITE);
		innerLabel.setAutoScale(TextAutoScaleMode.BOTH);
		innerLabel.setAlign(HorzAlign.CENTER, VertAlign.CENTER);

		MenuRenderHelper.drawTextureStretched(sbatch, tex0, offX + getPositionX(), offY + getPositionY(), tex0_w, getHeight());
		MenuRenderHelper.drawTextureStretched(sbatch, tex1, offX + getPositionX() + getWidth() - tex1_w, offY + getPositionY(), tex1_w, getHeight());

		if (percpos > tex0_w)
			MenuRenderHelper.drawTextureStretched(sbatch, tex2, offX + getPositionX() + tex0_w, offY + getPositionY(), percpos - tex0_w, getHeight());

		if (percpos > tex0_w)
			MenuRenderHelper.drawTextureStretched(sbatch, tex3, offX + getPositionX() + percpos, offY + getPositionY(), getWidth() - percpos - tex1_w, getHeight());
		
		MenuRenderHelper.drawTexture(sbatch, tex4, offX + getPositionX() + percpos - tex4.getRegionWidth()/2, offY + getPositionY());
		
		innerLabel.render(sbatch, font, offX, offY);
	}

	@Override
	public void renderCustom(SpriteBatch sbatch, ShapeRenderer srenderer, BitmapFont font, int offX, int offY) {
		// NOP
	}

	@Override
	public void renderDebug(ShapeRenderer srenderer, GameSettings settings, int offX, int offY) {
		innerLabel.renderDebug(srenderer, settings, offX, offY);
	}

	public int direction = 1;
	@Override
	public void update(float delta) {
		perc += (delta/7500)*direction;
		
		if (perc < 0) {
			perc = 0;
			direction *= -1;
		}
		if (perc > 1) {
			perc = 1;
			direction *= -1;
		}
	}

	@Override
	public MenuBaseElement getElementAt(int x, int y) {
		return this;
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
