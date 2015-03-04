package de.samdev.absgdx.example.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.framework.layer.MenuLayer;
import de.samdev.absgdx.framework.menu.attributes.HorzAlign;
import de.samdev.absgdx.framework.menu.attributes.TextAutoScaleMode;
import de.samdev.absgdx.framework.menu.attributes.VertAlign;
import de.samdev.absgdx.framework.menu.elements.MenuButton;
import de.samdev.absgdx.framework.menu.elements.MenuEdit;
import de.samdev.absgdx.framework.menu.elements.MenuLabel;
import de.samdev.absgdx.framework.menu.elements.MenuPanel;

public class ManualMenu extends MenuLayer {

	public ManualMenu(AgdxGame owner) {
		super(owner, new BitmapFont(Gdx.files.internal("consolefont.fnt")));

		MenuPanel p = new MenuPanel();
		p.setPosition(450, 50);
		p.setSize(550, 350);
		
		MenuButton b = new MenuButton();
		b.setBoundaries(25, 150, 80, 40);
		b.setContent("Click Me");
		
		MenuLabel l = new MenuLabel();
		l.setBoundaries(25, 25, 250, 40);
		l.setFontScale(0.8f);
		l.setAutoScale(TextAutoScaleMode.BOTH);
		l.setAlign(HorzAlign.CENTER, VertAlign.CENTER);
		l.setContent("Hello [ World!");
		
		MenuEdit e = new MenuEdit();
		e.setBoundaries(25, 200, 350, 40);
		e.setContent("Edit Me");

		p.addChildren(b);
		p.addChildren(l);
		p.addChildren(e);
		getRoot().addChildren(p);
	}

	@Override
	public void onResize() {
		// TODO Auto-generated method stub

	}

}
