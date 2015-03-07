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
	private class MenuSettingsTreeRow extends MenuCheckBox {
		private final List<MenuSettingsTreeRow> children;
		private final MenuSettingsTree treeowner;
		private final DependentProperty property;
		
		private MenuImage valueImage;
		
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
			
			valueImage = new MenuImage();
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
		public int getElementCount() {
			int c = 1;
			for (MenuSettingsTreeRow child : children) c += child.getElementCount();
			return c;
		}
		
		@Override
		public List<MenuElement> getChildren() {
			List<MenuElement> result = new ArrayList<MenuElement>();
			result.addAll(children);
			return result;
		}
		
		public List<MenuElement> getTree() {
			List<MenuElement> result = new ArrayList<MenuElement>();
			result.add(this);
			for (MenuSettingsTreeRow child : children) {
				result.addAll(child.getTree());
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
			
			super.render(sbatch, srenderer, font);
			valueImage.render(sbatch, srenderer, font);
			
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
			
			srenderer.begin(ShapeType.Line);
			{
				srenderer.setColor(treeowner.layer.owner.settings.debugMenuBordersColorL2.get());
				srenderer.rect(getPositionX(), getPositionY(), getWidth(), getHeight());
			}
			srenderer.end();

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
			
			valueImage.setHeight(treeowner.rowHeight);
			valueImage.setWidth(treeowner.rowHeight);
			valueImage.setPositionX(treeowner.getWidth() - treeowner.padding.left - treeowner.rowHeight);
			valueImage.setPositionY(positionY);
			valueImage.setImage(getTextureProvider().get(treeowner.getClass(), GUITextureProvider.IDENT_TEX_DEPTREE_BOOL, property.isActive() ? TristateBoolean.TRUE : (property.getBooleanValue() ? TristateBoolean.INTERMEDIATE : TristateBoolean.FALSE)));
		}
	}

	private final MenuSettingsTreeRow root; 
	
	private RectangleRadius padding = new RectangleRadius(15, 15, 15, 15);
	private int rowGap = 5;
	private int rowHeight = 20;
	private Color color;
	
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
			root.innerDebugRender(srenderer, padding.top, 0);
			srenderer.translate(-getPositionX(), -getPositionY(), 0);
		}
	}

	@Override
	public void render(SpriteBatch sbatch, ShapeRenderer srenderer, BitmapFont font) {
		render9SideTexture(sbatch);

		srenderer.translate(getPositionX(), getPositionY(), 0);
		sbatch.getTransformMatrix().translate(getPositionX(), getPositionY(), 0);
		root.innerRender(sbatch, srenderer, font, padding.top, 0);
		sbatch.getTransformMatrix().translate(-getPositionX(), -getPositionY(), 0);
		srenderer.translate(-getPositionX(), -getPositionY(), 0);
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
		MenuElement element = root.getElementAt(x, y);
		
		return element != null ? element : this;
	}

	@Override
	public int getElementCount() {
		return 1 + root.getElementCount();
	}

	public int getRowGap() {
		return rowGap;
	}

	public void setRowGap(int rowGap) {
		this.rowGap = rowGap;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getRowHeight() {
		return rowHeight;
	}

	public void setRowHeight(int height) {
		this.rowHeight = height;
	}
	
	@Override
	public List<MenuElement> getChildren() {
		return root.getTree();
	}
}
