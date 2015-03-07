package de.samdev.absgdx.framework.menu.elements;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import de.samdev.absgdx.framework.layer.MenuLayer;
import de.samdev.absgdx.framework.menu.GUITextureProvider;
import de.samdev.absgdx.framework.menu.attributes.RectangleRadius;
import de.samdev.absgdx.framework.menu.attributes.TristateBoolean;
import de.samdev.absgdx.framework.menu.attributes.VisualButtonState;
import de.samdev.absgdx.framework.menu.events.MenuSettingsTreeListener;
import de.samdev.absgdx.framework.util.dependentProperties.BooleanProperty;
import de.samdev.absgdx.framework.util.dependentProperties.DependentProperty;
import de.samdev.absgdx.framework.util.dependentProperties.RootProperty;

/**
 * A click-able Button
 */
public class MenuSettingsTree extends MenuElement {
	/**
	 * Class to represent a single row in the MenuSettingsTree
	 */
	private class MenuSettingsTreeRow extends MenuCheckBox {
		private final List<MenuSettingsTreeRow> children;
		private final MenuSettingsTree treeowner;
		private final DependentProperty property;
		
		private MenuImage innerImageRight;
		
		public MenuSettingsTreeRow(String rootidentifier, GUITextureProvider texprovider, DependentProperty property, MenuSettingsTree owner) {
			super(rootidentifier + "+" + property.name, texprovider);
			this.treeowner = owner;
			this.children = new ArrayList<MenuSettingsTreeRow>();
			this.property = property;
			
			setContent(property.name);
			
			for (DependentProperty child : property.getChildren()) {
				children.add(new MenuSettingsTreeRow(identifier, texprovider, child, owner));
			}
			
			setChecked(true);
			setLabelPadding(new RectangleRadius(5, 10, 5, 0));
			
			innerImageRight = new MenuImage();
		}

		@Override
		protected TextureRegion getCheckTexture() {
			if (children.isEmpty()) {
				return getTextureProvider().get(treeowner.getClass(), GUITextureProvider.IDENT_TEX_DEPTREE_LEAF);
			} else {				
				return getTextureProvider().get(treeowner.getClass(), GUITextureProvider.IDENT_TEX_CHECK_IMG, isChecked());
			}
		}	
		
		@Override
		public MenuElement getElementAt(int x, int y) {
			if (isChecked()) {
				for (MenuSettingsTreeRow element : children) {
					if (element.getElementAt(x, y) != null)
						return element.getElementAt(x, y);
				}				
			}

			if (this.getBoundaries().contains(x, y))
				return this;
			
			return null;
		}
		
		@Override
		public List<MenuElement> getDirectInnerElements() {
			List<MenuElement> result = new ArrayList<MenuElement>();
			result.add(innerImage);
			result.add(innerLabel);
			result.add(innerImageRight);
			result.addAll(children);
			return result;
		}

		public List<MenuElement> getAllVisibleChildren() {
			List<MenuElement> result = new ArrayList<MenuElement>();
			result.add(this);
			
			if (!isChecked())
				return result;
				
			for (MenuSettingsTreeRow child : children) {
				result.addAll(child.getAllVisibleChildren());
			}
			
			return result;
		}
		
		@Override
		public void onPointerClicked() {
			if (innerImage.getBoundaries().contains(Gdx.input.getX() - getCoordinateOffsetX(), Gdx.input.getY() - getCoordinateOffsetY())) {
				super.onPointerClicked();
			} else if (innerLabel.getBoundaries().contains(Gdx.input.getX() - getCoordinateOffsetX(), Gdx.input.getY() - getCoordinateOffsetY())) {
				if (property instanceof BooleanProperty) {
					((BooleanProperty)property).set(! property.getBooleanValue());
				}
			}
		}
		
		public int innerRender(SpriteBatch sbatch, ShapeRenderer srenderer, BitmapFont font, int positionY, int depth) {
			setUpInnerElements(positionY, depth);
			
			if (positionY >= treeowner.padding.top) {
				super.render(sbatch, srenderer, font);
				innerImageRight.render(sbatch, srenderer, font);
			}
			
			positionY += getHeight() + treeowner.rowGap;
			
			if (isChecked()) {
				for (MenuSettingsTreeRow child : children) {
					if (positionY + treeowner.rowHeight > treeowner.getHeight() - treeowner.padding.bottom)
						return positionY;
					
					positionY = child.innerRender(sbatch, srenderer, font, positionY, depth+1);
				}
			}
			
			return positionY;
		}
		
		public int innerDebugRender(ShapeRenderer srenderer, int positionY, int depth) {
			setUpInnerElements(positionY, depth);
			
			if (positionY >= treeowner.padding.top) {
				srenderer.begin(ShapeType.Line);
				{
					srenderer.setColor(treeowner.layer.owner.settings.debugMenuBordersColorL2.get());
					srenderer.rect(getPositionX(), getPositionY(), getWidth(), getHeight());
				}
				srenderer.end();
			}

			positionY += getHeight() + treeowner.rowGap;
			
			if (isChecked()) {
				for (MenuSettingsTreeRow child : children) {
					positionY = child.innerDebugRender(srenderer, positionY, depth+1);
					
					if (positionY + treeowner.rowHeight > treeowner.getHeight() - treeowner.padding.bottom)
						return positionY;
				}
			}
			
			return positionY;
		}

		private void setUpInnerElements(int positionY, int depth) {
			setWidth(treeowner.getWidth() - treeowner.padding.getHorizontalSum() - treeowner.rowHeight*depth);
			setHeight(treeowner.rowHeight);
			
			setPositionX(treeowner.padding.left + treeowner.rowHeight*depth);
			setPositionY(positionY);
			
			setColor(treeowner.color);
			
			innerImageRight.setHeight(treeowner.rowHeight);
			innerImageRight.setWidth(treeowner.rowHeight);
			innerImageRight.setPositionX(treeowner.getWidth() - treeowner.padding.left - treeowner.rowHeight);
			innerImageRight.setPositionY(positionY);
			innerImageRight.setImage(getTextureProvider().get(treeowner.getClass(), GUITextureProvider.IDENT_TEX_DEPTREE_BOOL, property.isActive() ? TristateBoolean.TRUE : (property.getBooleanValue() ? TristateBoolean.INTERMEDIATE : TristateBoolean.FALSE)));
		}
	}

	private final MenuSettingsTreeRow root; 
	
	private RectangleRadius padding = new RectangleRadius(15, 15, 15, 15);
	private int rowGap = 5;
	private int rowHeight = 20;
	private Color color = Color.WHITE;
	private Color scrollbarColor = Color.LIGHT_GRAY;
	private int scrollbarWidth = 5;
	
	private int scroll = 0;
	
	/**
	 * Creates a new MenuButton
	 * 
	 * @param rootproperty The DependentProperties root node
	 */
	public MenuSettingsTree(RootProperty rootproperty) {
		super();
		
		root = new MenuSettingsTreeRow(identifier, textureprovider, rootproperty, this);

		if (getTextureProvider().hasGeneric9SideTextures(getClass(), VisualButtonState.NORMAL))
			setPadding(get9SidePadding(VisualButtonState.NORMAL));
	}
	
	/**
	 * Creates a new MenuButton
	 * 
	 * @param texprovider the texture provider for this element
	 * @param rootproperty The DependentProperties root node
	 */
	public MenuSettingsTree(GUITextureProvider texprovider, RootProperty rootproperty) {
		super(texprovider);
		
		root = new MenuSettingsTreeRow(identifier + "+" + rootproperty.name, textureprovider, rootproperty, this);

		if (getTextureProvider().hasGeneric9SideTextures(getClass(), VisualButtonState.NORMAL))
			setPadding(get9SidePadding(VisualButtonState.NORMAL));
	}
	
	/**
	 * Creates a new MenuButton
	 * 
	 * @param identifier the unique button identifier
	 * @param texprovider the texture provider for this element
	 * @param rootproperty The DependentProperties root node
	 */
	public MenuSettingsTree(String identifier, GUITextureProvider texprovider, RootProperty rootproperty) {
		super(identifier, texprovider);
		
		root = new MenuSettingsTreeRow(identifier + "+" + rootproperty.name, textureprovider, rootproperty, this);

		if (getTextureProvider().hasGeneric9SideTextures(getClass(), VisualButtonState.NORMAL))
			setPadding(get9SidePadding(VisualButtonState.NORMAL));
	}

	@Override
	public void renderElement(SpriteBatch sbatch, ShapeRenderer srenderer, BitmapFont defaultfont, MenuLayer owner) {
		super.renderElement(sbatch, srenderer, defaultfont, owner);
		
		if (layer != null && layer.owner.settings.debugMenuBorders.isActive()) {
			srenderer.translate(getPositionX(), getPositionY(), 0);
			root.innerDebugRender(srenderer, padding.top - scroll * (rowHeight + rowGap), 0);
			srenderer.translate(-getPositionX(), -getPositionY(), 0);
		}
	}

	@Override
	public void render(SpriteBatch sbatch, ShapeRenderer srenderer, BitmapFont font) {
		render9SideTexture(sbatch);

		srenderer.translate(getPositionX(), getPositionY(), 0);
		sbatch.getTransformMatrix().translate(getPositionX(), getPositionY(), 0);
		{
			root.innerRender(sbatch, srenderer, font, padding.top - scroll * (rowHeight + rowGap), 0);
			
			int rows = root.getAllVisibleChildren().size();
			int vrows = (getHeight() - padding.getVerticalSum()) / (rowHeight + rowGap);
			
			if (vrows < rows) {
				float sbheight = (vrows * 1f / rows) * getHeight();
				float sbfreespace = getHeight() - sbheight;
				float sbperc = scroll * 1f / (rows - vrows);
				float sby = sbperc * sbfreespace;
				
				srenderer.begin(ShapeType.Filled);
				srenderer.setColor(getScrollbarColor());
				srenderer.rect(getWidth() - 1, sby + 1, -scrollbarWidth, sbheight - 2);
				srenderer.end();
			}
		}
		sbatch.getTransformMatrix().translate(-getPositionX(), -getPositionY(), 0);
		srenderer.translate(-getPositionX(), -getPositionY(), 0);
	}

	@Override
	public void update(float delta) {
		onScroll(0);
	}
	
	/**
	 * Adds a new listener
	 * 
	 * @param l the new listener
	 */
	public void addSettingsTreeListener(MenuSettingsTreeListener l) {
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

	@Override
	public MenuElement getElementAt(int x, int y) {
		return this;
	}
	
	@Override
	public void onPointerClicked() {
		super.onPointerClicked();
		
		MenuElement element = root.getElementAt(Gdx.input.getX() - root.getCoordinateOffsetX(), Gdx.input.getY() - root.getCoordinateOffsetY());
		
		if (element != null)
			element.onPointerClicked();
	}

	/**
	 * @return the gap between the single rows
	 */
	public int getRowGap() {
		return rowGap;
	}

	/**
	 * Set the gap between the rows
	 * 
	 * @param rowGap the gap (in pixel)
	 */
	public void setRowGap(int rowGap) {
		this.rowGap = rowGap;
	}

	/**
	 * @return the font color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Set the font color
	 * 
	 * @param color the new color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return the height of a single row
	 */
	public int getRowHeight() {
		return rowHeight;
	}

	/**
	 * Set the height of a single row
	 * 
	 * @param height the row height (in pixel)
	 */
	public void setRowHeight(int height) {
		this.rowHeight = height;
	}

	/**
	 * @return the color of the scrollbar
	 */
	public Color getScrollbarColor() {
		return scrollbarColor;
	}

	/**
	 * Set the color of the scrollbar
	 * 
	 * @param scrollbarColor the new color
	 */
	public void setScrollbarColor(Color scrollbarColor) {
		this.scrollbarColor = scrollbarColor;
	}
	
	/**
	 * @return the width of the scrollbar
	 */
	public int getScrollbarWidth() {
		return scrollbarWidth;
	}

	/**
	 * Set the width of the scrollbar
	 * 
	 * @param scrollbarWidth the scrollbar width (in pixel)
	 */
	public void setScrollbarWidth(int scrollbarWidth) {
		this.scrollbarWidth = scrollbarWidth;
	}

	@Override
	public void onScroll(int amount) {
		scroll += Math.signum(amount);
		
		int rows = root.getAllVisibleChildren().size();
		int vrows = (getHeight() - padding.getVerticalSum()) / (rowHeight + rowGap);
		
		if (scroll + vrows > rows) scroll = rows - vrows;
		if (scroll < 0) scroll = 0;
	}
	
	@Override
	public List<MenuElement> getDirectInnerElements() {
		List<MenuElement> result = new ArrayList<MenuElement>();
		result.add(root);
		return result;
	}
}
