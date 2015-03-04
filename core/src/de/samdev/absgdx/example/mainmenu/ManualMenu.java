package de.samdev.absgdx.example.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.framework.layer.MenuLayer;
import de.samdev.absgdx.framework.menu.attributes.HorzAlign;
import de.samdev.absgdx.framework.menu.attributes.TextAutoScaleMode;
import de.samdev.absgdx.framework.menu.attributes.VertAlign;
import de.samdev.absgdx.framework.menu.elements.MenuButton;
import de.samdev.absgdx.framework.menu.elements.MenuEdit;
import de.samdev.absgdx.framework.menu.elements.MenuElement;
import de.samdev.absgdx.framework.menu.elements.MenuLabel;
import de.samdev.absgdx.framework.menu.elements.MenuPanel;
import de.samdev.absgdx.framework.menu.events.MenuButtonListener;

public class ManualMenu extends MenuLayer {

	public ManualMenu(AgdxGame owner) {
		super(owner, new BitmapFont(Gdx.files.internal("consolefont.fnt")));

		final MenuPanel p = new MenuPanel();
		p.setBoundaries(450, 50, 550, 350);
		
		final MenuLabel l2 = new MenuLabel();
		l2.setBoundaries(115, 150, 300, 40);
		l2.setAlign(HorzAlign.LEFT, VertAlign.CENTER);
		p.addChildren(l2);

		final MenuButton b = new MenuButton();
		b.setBoundaries(25, 150, 80, 40);
		b.setContent("Click Me");
		b.addButtonListener(new MenuButtonListener() {
			
			@Override
			public void onPointerUp(MenuElement element, String identifier) {/**/}
			@Override
			public void onPointerDown(MenuElement element, String identifier) {/**/}
			@Override
			public void onHoverEnd(MenuElement element, String identifier) {/**/}
			@Override
			public void onHover(MenuElement element, String identifier) {/**/}
			@Override
			public void onFocusLost(MenuElement element, String identifier) {/**/}
			@Override
			public void onFocus(MenuElement element, String identifier) {/**/}
			@Override
			public void onClicked(MenuElement element, String identifier) {l2.setContent("Thank You !");}
		});
		p.addChildren(b);
		
		final MenuLabel l = new MenuLabel();
		l.setBoundaries(25, 25, 450, 40);
		l.setFontScale(0.8f);
		l.setAutoScale(TextAutoScaleMode.BOTH);
		l.setAlign(HorzAlign.CENTER, VertAlign.CENTER);
		l.setContent("absGDX Example Menu");
		l.setColor(Color.MAROON);
		p.addChildren(l);
		
		final MenuEdit e = new MenuEdit();
		e.setBoundaries(25, 200, 350, 40);
		e.setContent("Edit Me");
		p.addChildren(e);

		getRoot().addChildren(p);
	}

	@Override
	public void onResize() {
		// TODO Auto-generated method stub

	}

}
