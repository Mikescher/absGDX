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

import de.samdev.absgdx.framework.GameSettings;
import de.samdev.absgdx.framework.menu.GUITextureProvider;
import de.samdev.absgdx.framework.menu.attributes.CheckState;
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
public class MenuSettingsTree extends MenuBaseElement {
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
				return getTextureProvider().get(treeowner.getClass(), GUITextureProvider.IDENT_TEX_CHECK_IMG, CheckState.fromBoolean(isChecked()));
			}
		}	
		
		@Override
		public MenuBaseElement getElementAt(int x, int y) {
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
		public List<MenuBaseElement> getDirectInnerElements() {
			List<MenuBaseElement> result = new ArrayList<MenuBaseElement>();
			result.add(innerImage);
			result.add(innerLabel);
			result.add(innerImageRight);
			return result;
		}

		public List<MenuBaseElement> getAllVisibleChildren() {
			List<MenuBaseElement> result = new ArrayList<MenuBaseElement>();
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
		
		public List<MenuBaseElement> getTree() {
			List<MenuBaseElement> result = new ArrayList<MenuBaseElement>();
			result.add(this);
			for (MenuSettingsTreeRow child : children) {
				result.addAll(child.getTree());
			}
			return result;
		}
		
		public int innerRender(SpriteBatch sbatch, BitmapFont font, int positionY, int depth, int offX, int offY) {
			setUpInnerElements(positionY, depth);
			
			if (positionY >= treeowner.padding.top) {
				super.render(sbatch, font, offX, offY);
				innerImageRight.render(sbatch, font, offX, offY);
			}
			
			positionY += getHeight() + treeowner.rowGap;
			
			if (isChecked()) {
				for (MenuSettingsTreeRow child : children) {
					if (positionY + treeowner.rowHeight > treeowner.getHeight() - treeowner.padding.bottom)
						return positionY;
					
					positionY = child.innerRender(sbatch, font, positionY, depth+1, offX, offY);
				}
			}
			
			return positionY;
		}
		
		public int innerDebugRender(ShapeRenderer srenderer, int positionY, int depth, int offX, int offY) {
			setUpInnerElements(positionY, depth);
			
			if (positionY >= treeowner.padding.top) {
				srenderer.begin(ShapeType.Line);
				{
					srenderer.setColor(treeowner.owner.getAgdxGame().settings.debugMenuBordersColorL2.get());
					srenderer.rect(offX + getPositionX(), offY + getPositionY(), getWidth(), getHeight());
				}
				srenderer.end();
			}

			positionY += getHeight() + treeowner.rowGap;
			
			if (isChecked()) {
				for (MenuSettingsTreeRow child : children) {
					if (positionY + treeowner.rowHeight > treeowner.getHeight() - treeowner.padding.bottom)
						return positionY;
					
					positionY = child.innerDebugRender(srenderer, positionY, depth+1, offX, offY);
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
	public void render(SpriteBatch sbatch, BitmapFont font, int offX, int offY) {
		if (getTextureProvider().hasGeneric9SideTextures(getClass())) {
			render9SideTexture(sbatch, offX, offY);
		}
		
		if (getTextureProvider().hasPaddingTextures(getClass())) {
			renderPaddingTexture(sbatch, offX, offY);
		}
		
		root.innerRender(sbatch, font, padding.top - scroll * (rowHeight + rowGap), 0, offX + getPositionX(), offY + getPositionY());
		}

	@Override
	public void renderCustom(SpriteBatch sbatch, ShapeRenderer srenderer, BitmapFont font, int offX, int offY) {
		int rows = root.getAllVisibleChildren().size();
		int vrows = (getHeight() - padding.getVerticalSum()) / (rowHeight + rowGap);
		
		if (vrows < rows) {
			float sbheight = (vrows * 1f / rows) * getHeight();
			float sbfreespace = getHeight() - sbheight;
			float sbperc = scroll * 1f / (rows - vrows);
			float sby = sbperc * sbfreespace;
			
			srenderer.begin(ShapeType.Filled);
			{
				srenderer.setColor(getScrollbarColor());
				srenderer.rect(offX + getPositionX() + getWidth() - 1, offY + getPositionY() + sby + 1, -scrollbarWidth, sbheight - 2);
			}
			srenderer.end();
		}
	}

	@Override
	public void renderDebug(ShapeRenderer srenderer, GameSettings settings, int offX, int offY) {
		if (settings.debugMenuBorders.isActive())
		{
			root.innerDebugRender(srenderer, padding.top - scroll * (rowHeight + rowGap), 0, offX + getPositionX(), offY + getPositionY());
		}
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
	public MenuBaseElement getElementAt(int x, int y) {
		return this;
	}
	
	@Override
	public void onPointerClicked() {
		super.onPointerClicked();
		
		MenuBaseElement element = root.getElementAt(Gdx.input.getX() - root.getCoordinateOffsetX(), Gdx.input.getY() - root.getCoordinateOffsetY());
		
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
	public List<MenuBaseElement> getDirectInnerElements() {
		return root.getTree();
	}

	@Override
	public MenuBaseElement getElementByID(String id) {
		return identifier.equals(id) ? this : null;
	}
}
