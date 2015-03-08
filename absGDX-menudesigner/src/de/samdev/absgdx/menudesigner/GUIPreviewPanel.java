package de.samdev.absgdx.menudesigner;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.badlogic.gdx.math.Rectangle;

import de.samdev.absgdx.framework.layer.AgdxmlLayer;
import de.samdev.absgdx.framework.layer.MenuLayer;
import de.samdev.absgdx.framework.menu.elements.MenuElement;
import de.samdev.absgdx.framework.util.exceptions.AgdxmlParsingException;

public class GUIPreviewPanel extends JPanel {
	private static final long serialVersionUID = -6314540108950473238L;

	private BufferedImage buffer;
	private MenuLayer layer;

	public GUIPreviewPanel(int renderWidth, int renderHeight, MenuLayer mlayer) {
		super();

		this.layer = mlayer;
		this.setBackground(new java.awt.Color(255, 255, 255));
		this.setBorder(BorderFactory.createEmptyBorder());

		setRenderSize(renderWidth, renderHeight);
	}

	public void setMenuLayer(String xml) throws AgdxmlParsingException {
		layer = new AgdxmlLayer(new AgdxPreviewGameDummy(), null, xml);

		redraw();
	}
	
	public void setRenderSize(int renderWidth, int renderHeight) {
		this.buffer = new BufferedImage(renderWidth, renderHeight, BufferedImage.TYPE_INT_ARGB);
		
		((AgdxPreviewGameDummy)layer.owner).width = renderWidth;
		((AgdxPreviewGameDummy)layer.owner).height = renderHeight;
		layer.onResize();

		redraw();
	}

	protected void redraw() {
		Graphics g = buffer.getGraphics();

		Color col = new Color(255, 255, 255);
		
		g.setColor(col);
		g.fillRect(0, 0, buffer.getWidth(), buffer.getHeight());
		
		for (MenuElement child : layer.getRoot().getAllChildElements()) {
			Rectangle bound = new Rectangle(child.getCoordinateOffsetX() + child.getPositionX(), child.getCoordinateOffsetY() + child.getPositionY(), child.getWidth(), child.getHeight());
			
			int gray = Math.max(255 - child.getDepth()*32, 0);
			g.setColor(new Color(gray, gray, gray));
			g.fillRect((int)bound.x, (int)bound.y, (int)bound.width, (int)bound.height);

			g.setColor(Color.BLACK);
			g.drawRect((int)bound.x, (int)bound.y, (int)bound.width, (int)bound.height);
			
			g.setFont(new Font("Arial", Font.PLAIN, 12));
			g.drawString(child.getClass().getSimpleName(), (int)bound.x + 3, (int)bound.y + 12);
		}
		
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, getWidth(), getHeight());

		if (buffer.getWidth() * 1f / buffer.getHeight() > getWidth() * 1f / getHeight()) {
			g.drawImage(buffer, 0, 0, getWidth(), (int) (getWidth() * (buffer.getHeight() * 1f / buffer.getWidth())), null);
		} else {
			g.drawImage(buffer, 0, 0, (int) (getHeight() * (buffer.getWidth() * 1f / buffer.getHeight())), getHeight(), null);
		}
	}
}
