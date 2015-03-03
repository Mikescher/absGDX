package de.samdev.absgdx.example.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.framework.layer.MenuLayer;
import de.samdev.absgdx.framework.menu.attributes.HorzAlign;
import de.samdev.absgdx.framework.menu.attributes.VertAlign;
import de.samdev.absgdx.framework.menu.elements.MenuButton;
import de.samdev.absgdx.framework.menu.elements.MenuLabel;
import de.samdev.absgdx.framework.menu.elements.MenuPanel;

public class ManualMenu extends MenuLayer {

	public ManualMenu(AgdxGame owner) {
		super(owner, new BitmapFont(Gdx.files.internal("consolefont.fnt")));

		MenuPanel p = new MenuPanel();
		p.setPosition(450, 50);
		p.setSize(550, 350);
		
		MenuButton b = new MenuButton();
		b.setPosition(25, 150);
		b.setContent("Click Me");
		b.setSize(80, 40);
		
		MenuLabel l = new MenuLabel();
		l.setPosition(25, 25);
		l.setSize(250, 40);
		l.setFontScale(0.8f);
		l.setAutoScale(false);
		l.setAlign(HorzAlign.CENTER, VertAlign.CENTER);
		l.setContent("Hello World!");

		p.addChildren(b);
		p.addChildren(l);
		getRoot().addChildren(p);
	}

	@Override
	public void onResize() {
		// TODO Auto-generated method stub

	}

}
