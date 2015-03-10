package de.samdev.absgdx.example.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import de.samdev.absgdx.example.Textures;
import de.samdev.absgdx.example.sidescrollergame.SidescrollerGameLayer;
import de.samdev.absgdx.example.topdowngame.TopDownGameLayer;
import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.framework.layer.MenuLayer;
import de.samdev.absgdx.framework.menu.GUITextureProvider;
import de.samdev.absgdx.framework.menu.attributes.HorzAlign;
import de.samdev.absgdx.framework.menu.attributes.TextAutoScaleMode;
import de.samdev.absgdx.framework.menu.attributes.TristateBoolean;
import de.samdev.absgdx.framework.menu.attributes.VertAlign;
import de.samdev.absgdx.framework.menu.attributes.VisualButtonState;
import de.samdev.absgdx.framework.menu.elements.MenuButton;
import de.samdev.absgdx.framework.menu.elements.MenuCheckBox;
import de.samdev.absgdx.framework.menu.elements.MenuEdit;
import de.samdev.absgdx.framework.menu.elements.MenuElement;
import de.samdev.absgdx.framework.menu.elements.MenuImage;
import de.samdev.absgdx.framework.menu.elements.MenuLabel;
import de.samdev.absgdx.framework.menu.elements.MenuPanel;
import de.samdev.absgdx.framework.menu.elements.MenuRadioButton;
import de.samdev.absgdx.framework.menu.elements.MenuSettingsTree;
import de.samdev.absgdx.framework.menu.events.MenuButtonListener;
import de.samdev.absgdx.framework.util.exceptions.AgdxmlParsingException;

public class ManualMenu extends MenuLayer {

	public ManualMenu(AgdxGame owner) {
		super(owner, new BitmapFont(Gdx.files.internal("consolefont.fnt")));

		GUITextureProvider prov = new GUITextureProvider();
		prov.setMenuButtonTexture(Textures.tex_buttongui[0], VisualButtonState.NORMAL);
		prov.setMenuButtonTexture(Textures.tex_buttongui[1], VisualButtonState.HOVERED);
		prov.setMenuButtonTexture(Textures.tex_buttongui[2], VisualButtonState.PRESSED);
		prov.setMenuButtonTexture(Textures.tex_buttongui[3], VisualButtonState.DISABLED);

		prov.setMenuPanelTexture(Textures.tex_panelgui);

		prov.setMenuEditTexture(Textures.tex_textfield, false);
		prov.setMenuEditTexture(Textures.tex_textfield_focus, true);

		prov.setMenuCheckBoxTexture(Textures.tex_gui_checkers[0][0], true);
		prov.setMenuCheckBoxTexture(Textures.tex_gui_checkers[0][1], false);

		prov.setMenuRadioButtonTexture(Textures.tex_gui_checkers[3][0], true);
		prov.setMenuRadioButtonTexture(Textures.tex_gui_checkers[3][1], false);

		prov.setMenuSettingsTreeTexture(Textures.tex_panelgui);
		prov.setMenuSettingsTreeButtonTexture(Textures.tex_gui_checkers[2][0], true);
		prov.setMenuSettingsTreeButtonTexture(Textures.tex_gui_checkers[2][1], false);
		prov.setMenuSettingsTreeValueTexture(Textures.tex_gui_checkers[1][0], TristateBoolean.FALSE);
		prov.setMenuSettingsTreeValueTexture(Textures.tex_gui_checkers[0][2], TristateBoolean.INTERMEDIATE);
		prov.setMenuSettingsTreeValueTexture(Textures.tex_gui_checkers[0][0], TristateBoolean.TRUE);
		prov.setMenuSettingsTreeLeafTexture(Textures.tex_gui_checkers[0][1]);

		prov.set(MenuProgressbar.class, "0", Textures.tex_gui_progressbar[0]);
		prov.set(MenuProgressbar.class, "1", Textures.tex_gui_progressbar[1]);
		prov.set(MenuProgressbar.class, "2", Textures.tex_gui_progressbar[2]);
		prov.set(MenuProgressbar.class, "3", Textures.tex_gui_progressbar[3]);
		prov.set(MenuProgressbar.class, "4", Textures.tex_gui_progressbar[4]);
		
		GUITextureProvider prov2 = new GUITextureProvider();
		prov2.setMenuButtonTexture(Textures.tex_buttongui[4], VisualButtonState.NORMAL);
		prov2.setMenuButtonTexture(Textures.tex_buttongui[5], VisualButtonState.HOVERED);
		prov2.setMenuButtonTexture(Textures.tex_buttongui[6], VisualButtonState.PRESSED);
		prov2.setMenuButtonTexture(Textures.tex_buttongui[7], VisualButtonState.DISABLED);
		
		final MenuPanel p = new MenuPanel(prov);
		p.setBoundaries(50, 50, 600, 310);
		
		final MenuLabel l2 = new MenuLabel();
		l2.setBoundaries(150, 100, 275, 40);
		l2.setAlign(HorzAlign.LEFT, VertAlign.CENTER);
		l2.setContent("Press F1 !");
		l2.setColor(Color.LIGHT_GRAY);
		l2.setVisible(false);
		p.addChildren(l2);

		final MenuButton b = new MenuButton(prov);
		b.setBoundaries(25, 100, 120, 40);
		b.setContent("Debug Mode");
		b.setColor(Color.WHITE);
		p.addChildren(b);
		
		final MenuCheckBox cb = new MenuCheckBox(prov);
		cb.setBoundaries(25, 160, 400, 20);
		cb.setContent("Grey Face (No Space)");
		cb.setColor(Color.LIGHT_GRAY);
		cb.setLabelPadding(5, 5, 5, 0);
		p.addChildren(cb);
		
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
		l.setColor(Color.TEAL);
		p.addChildren(l);

		final MenuImage i3 = new MenuImage();
		i3.setBoundaries(440, 145, 145, 145);
		i3.setImage(Textures.tex_animation, 3500);
		p.addChildren(i3);
		
		final MenuEdit e = new MenuEdit(prov);
		e.setBoundaries(25, 200, 400, 40);
		e.setContent("Edit Me");
		e.setPadding(10, 10, 10, 10);
		e.setColor(Color.WHITE);
		p.addChildren(e);
		
		final MenuButton b2 = new MenuButton(prov2);
		b2.setBoundaries(25, 250, 175, 40);
		b2.setContent("Top-Down Game");
		b2.setColor(Color.WHITE);
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

		final MenuButton b3 = new MenuButton(prov2);
		b3.setBoundaries(250, 250, 175, 40);
		b3.setContent("Side-Scroll Game");
		b3.setColor(Color.WHITE);
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
		
		//#######################################################
		
		final MenuPanel p2 = new MenuPanel(prov);
		p2.setBoundaries(50, 410, 600, 240);
		
		final MenuRadioButton rb1 = new MenuRadioButton(prov);
		rb1.setBoundaries(25, 25, 250, 30);
		rb1.setContent("Easy");
		rb1.setColor(Color.LIGHT_GRAY);
		rb1.setImagePadding(5, 5, 5, 5);
		rb1.setLabelPadding(5, 0, 5, 0);
		p2.addChildren(rb1);
		
		final MenuRadioButton rb2 = new MenuRadioButton(prov);
		rb2.setBoundaries(25, 65, 250, 30);
		rb2.setContent("Normal");
		rb2.setColor(Color.LIGHT_GRAY);
		rb2.setImagePadding(5, 5, 5, 5);
		rb2.setLabelPadding(5, 0, 5, 0);
		p2.addChildren(rb2);
		
		final MenuRadioButton rb3 = new MenuRadioButton(prov);
		rb3.setBoundaries(25, 105, 250, 30);
		rb3.setContent("Hard");
		rb3.setColor(Color.LIGHT_GRAY);
		rb3.setImagePadding(5, 5, 5, 5);
		rb3.setLabelPadding(5, 0, 5, 0);
		p2.addChildren(rb3);
		
		final MenuRadioButton rb4 = new MenuRadioButton(prov);
		rb4.setBoundaries(25, 145, 250, 30);
		rb4.setContent("Kappa");
		rb4.setColor(Color.LIGHT_GRAY);
		rb4.setImagePadding(5, 5, 5, 5);
		rb4.setLabelPadding(5, 0, 5, 0);
		p2.addChildren(rb4);
		
		final MenuButton bnext = new MenuButton(prov);
		bnext.setBoundaries(400, 25, 175, 40);
		bnext.setContent("AGDXML");
		bnext.setColor(Color.WHITE);
		bnext.addButtonListener(new MenuButtonListener() {
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
			public void onClicked(MenuElement element, String identifier) { try { ManualMenu.this.owner.pushLayer(new DemoMenu(ManualMenu.this.owner)); } catch (AgdxmlParsingException e) { e.printStackTrace(); }}});
		p2.addChildren(bnext);
		
		final MenuProgressbar pb = new MenuProgressbar(prov);
		pb.setBoundaries(25, 200, 550, 30);
		p2.addChildren(pb);

		getRoot().addChildren(p2);
		
		//#######################################################
		
		final MenuSettingsTree p3 = new MenuSettingsTree(prov, owner.settings.root);
		p3.setBoundaries(700, 50, 550, 600);
		p3.setColor(Color.WHITE);
		p3.setVisible(true);
		
		getRoot().addChildren(p3);
		
		//#######################################################
		//#######################################################
		
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
			public void onClicked(MenuElement element, String identifier) {l2.setVisible(true); p3.setVisible(! p3.isVisible()); b.setContent("Press again");}
		});
	}

	@Override
	public void onResize() {
		// Kinda ... nothing
	}

}
