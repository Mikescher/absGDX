package de.samdev.absgdx.menudesigner;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.badlogic.gdx.math.Rectangle;

import de.samdev.absgdx.framework.layer.AgdxmlLayer;
import de.samdev.absgdx.framework.menu.agdxml.AgdxmlLayerBoundaryElement;
import de.samdev.absgdx.framework.menu.elements.MenuElement;
import de.samdev.absgdx.framework.util.exceptions.AgdxmlParsingException;

public class GUIPreviewPanel extends JPanel {
	private static final long serialVersionUID = -6314540108950473238L;

	private BufferedImage buffer;
	private AgdxmlLayer layer;

	public GUIPreviewPanel(int renderWidth, int renderHeight, AgdxmlLayer mlayer) {
		super();

		this.layer = mlayer;
		this.setBackground(new java.awt.Color(255, 255, 255));
		this.setBorder(BorderFactory.createEmptyBorder());

		setRenderSize(renderWidth, renderHeight);
	}

	public void setMenuLayer(String xml) throws AgdxmlParsingException {
		layer = new AgdxmlLayer(new AgdxPreviewGameDummy(), null, xml){ @Override public void initialize() {/**/}};

		redraw();
	}
	
	public void setRenderSize(int renderWidth, int renderHeight) {
		this.buffer = new BufferedImage(renderWidth, renderHeight, BufferedImage.TYPE_INT_ARGB);
		
		if (layer != null) {
			((AgdxPreviewGameDummy)layer.owner).width = renderWidth;
			((AgdxPreviewGameDummy)layer.owner).height = renderHeight;
			layer.onResize();
		}
		
		redraw();
	}

	protected void redraw() {
		Graphics g = buffer.getGraphics();

		Color col = new Color(255, 255, 255);
		
		g.setColor(col);
		g.fillRect(0, 0, buffer.getWidth(), buffer.getHeight());
		
		if (layer != null) {
			Queue<AgdxmlLayerBoundaryElement> elements = new LinkedList<AgdxmlLayerBoundaryElement>();
			elements.add(layer.getBoundaryRootElement());
			
			while (! elements.isEmpty()) {
				AgdxmlLayerBoundaryElement childbound = elements.poll();
				MenuElement child = childbound.target;
				
				Rectangle bound = new Rectangle(child.getCoordinateOffsetX() + child.getPositionX(), child.getCoordinateOffsetY() + child.getPositionY(), child.getWidth(), child.getHeight());
				
				{
					int gray = Math.max(255 - child.getDepth()*32, 0);
					g.setColor(new Color(gray, gray, gray));
					g.fillRect((int)bound.x, (int)bound.y, (int)bound.width, (int)bound.height);
					
					g.setColor(Color.BLACK);
					g.drawRect((int)bound.x, (int)bound.y, (int)bound.width-1, (int)bound.height-1);
					
					for (int x = 1; x < childbound.gridDefinitions.columns.size(); x++) {
						try {
							Rectangle bounds = childbound.gridDefinitions.getBoundaries(x, 0, child.getBoundaries());
							g.setColor(Color.MAGENTA);
							g.drawLine(
									(int)(child.getCoordinateOffsetX() + child.getPositionX() + bounds.x), 
									child.getCoordinateOffsetY() + child.getPositionY(), 
									(int)(child.getCoordinateOffsetX() + child.getPositionX() + bounds.x), 
									child.getCoordinateOffsetY() + child.getPositionY() + child.getHeight());
						} catch (AgdxmlParsingException e) {/* ignore - just don't show */}
					}
					
					for (int y = 1; y < childbound.gridDefinitions.rows.size(); y++) {
						try {
							Rectangle bounds = childbound.gridDefinitions.getBoundaries(0, y, child.getBoundaries());
							g.setColor(Color.MAGENTA);
							g.drawLine(
									child.getCoordinateOffsetX() + child.getPositionX(), 
									(int)(child.getCoordinateOffsetY() + child.getPositionY() + bounds.y), 
									child.getCoordinateOffsetX() + child.getPositionX() + child.getWidth(),
									(int)(child.getCoordinateOffsetY() + child.getPositionY() + bounds.y));
						} catch (AgdxmlParsingException e) {/* ignore - just don't show */}
					}

					g.setColor(Color.BLACK);
					g.setFont(new Font("Arial", Font.PLAIN, 12));
					g.drawString(child.getClass().getSimpleName(), (int)bound.x + 3, (int)bound.y + 12);
				}
				
				elements.addAll(childbound.children);
			}			
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

	public void drawError() {
		layer = null;
		
		redraw();
	}
}
