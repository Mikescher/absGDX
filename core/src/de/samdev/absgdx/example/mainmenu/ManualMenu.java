package de.samdev.absgdx.example.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import de.samdev.absgdx.example.Textures;
import de.samdev.absgdx.example.sidescrollergame.SidescrollerGameLayer;
import de.samdev.absgdx.example.topdowngame.TopDownGameLayer;
import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.framework.layer.MenuLayer;
import de.samdev.absgdx.framework.menu.attributes.HorzAlign;
import de.samdev.absgdx.framework.menu.attributes.TextAutoScaleMode;
import de.samdev.absgdx.framework.menu.attributes.VertAlign;
import de.samdev.absgdx.framework.menu.elements.MenuButton;
import de.samdev.absgdx.framework.menu.elements.MenuEdit;
import de.samdev.absgdx.framework.menu.elements.MenuElement;
import de.samdev.absgdx.framework.menu.elements.MenuImage;
import de.samdev.absgdx.framework.menu.elements.MenuLabel;
import de.samdev.absgdx.framework.menu.elements.MenuPanel;
import de.samdev.absgdx.framework.menu.events.MenuButtonListener;

public class ManualMenu extends MenuLayer {

	public ManualMenu(AgdxGame owner) {
		super(owner, new BitmapFont(Gdx.files.internal("consolefont.fnt")));

		final MenuPanel p = new MenuPanel();
		p.setBoundaries(450, 50, 600, 310);
		
		final MenuLabel l2 = new MenuLabel();
		l2.setBoundaries(115, 130, 300, 40);
		l2.setAlign(HorzAlign.LEFT, VertAlign.CENTER);
		p.addChildren(l2);

		final MenuButton b = new MenuButton();
		b.setBoundaries(25, 130, 80, 40);
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
		
		final MenuImage i1 = new MenuImage();
		i1.setImage(Textures.tex_player_td[1][0]);
		i1.setBoundaries(10, 10, 65, 80);
		p.addChildren(i1);

		final MenuImage i2 = new MenuImage();
		i2.setImage(Textures.tex_player_td[3][0]);
		i2.setBoundaries(525, 10, 65, 80);
		p.addChildren(i2);
		
		final MenuLabel l = new MenuLabel();
		l.setBoundaries(75, 30, 450, 40);
		l.setFontScale(0.8f);
		l.setAutoScale(TextAutoScaleMode.BOTH);
		l.setAlign(HorzAlign.CENTER, VertAlign.CENTER);
		l.setContent("absGDX Example Menu");
		l.setColor(Color.MAROON);
		p.addChildren(l);

		final MenuImage i3 = new MenuImage();
		i3.setBoundaries(440, 145, 145, 145);
		i3.setImage(Textures.tex_animation, 3500);
		p.addChildren(i3);
		
		final MenuEdit e = new MenuEdit();
		e.setBoundaries(25, 200, 400, 40);
		e.setContent("Edit Me");
		p.addChildren(e);
		
		final MenuButton b2 = new MenuButton();
		b2.setBoundaries(25, 250, 175, 40);
		b2.setContent("Top-Down Game");
		b2.addButtonListener(new MenuButtonListener() {
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
			public void onClicked(MenuElement element, String identifier) {ManualMenu.this.owner.pushLayer(new TopDownGameLayer(ManualMenu.this.owner));}
		});
		p.addChildren(b2);

		final MenuButton b3 = new MenuButton();
		b3.setBoundaries(250, 250, 175, 40);
		b3.setContent("Side-Scroll Game");
		b3.addButtonListener(new MenuButtonListener() {
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
			public void onClicked(MenuElement element, String identifier) {ManualMenu.this.owner.pushLayer(new SidescrollerGameLayer(ManualMenu.this.owner));}
		});
		p.addChildren(b3);
		
		getRoot().addChildren(p);
	}

	@Override
	public void onResize() {
		// TODO Auto-generated method stub

	}

}
