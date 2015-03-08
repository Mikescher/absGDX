package de.samdev.absgdx.menudesigner;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import de.samdev.absgdx.framework.layer.MenuLayer;

public class GUIPreviewPanel extends JPanel {
	private static final long serialVersionUID = -6314540108950473238L;

	private BufferedImage buffer;
	private final MenuLayer layer;

	public GUIPreviewPanel(int renderWidth, int renderHeight, MenuLayer mlayer) {
		super();

		this.layer = mlayer;
		this.setBackground(new java.awt.Color(255, 255, 255));
		this.setBorder(BorderFactory.createEmptyBorder());

		setRenderSize(renderWidth, renderHeight);
	}

	public void setRenderSize(int renderWidth, int renderHeight) {
		this.buffer = new BufferedImage(renderWidth, renderHeight, BufferedImage.TYPE_INT_ARGB);

		Graphics g = buffer.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, renderWidth, renderHeight);
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
