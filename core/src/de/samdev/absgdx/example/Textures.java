package de.samdev.absgdx.example;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.samdev.absgdx.framework.util.TextureHelper;

public final class Textures {
	public static Texture texmap;
	public static Texture texsidemap;
	
	public static Texture texplayerset_td;
	public static Texture texchinaset_td;

	public static Texture texParallax_1;
	public static Texture texParallax_2;
	public static Texture tex_gui;
	
	public static TextureRegion tex_dirt;
	
	public static TextureRegion tex_GroundTile_TL;
	public static TextureRegion tex_GroundTile_TR;
	public static TextureRegion tex_GroundTile_BL;
	public static TextureRegion tex_GroundTile_BR;
	
	public static TextureRegion tex_Bucket_empty;
	public static TextureRegion tex_Bucket_full;
	public static TextureRegion tex_Bucket_hay;

	public static TextureRegion tex_Flowers_empty;

	public static TextureRegion tex_Bush_empty;
	public static TextureRegion tex_Bush_full;

	public static TextureRegion tex_Anchorpoint_empty;
	public static TextureRegion tex_Anchorpoint_full;

	public static TextureRegion tex_Angel;

	public static TextureRegion tex_AbyssTile;
	public static TextureRegion texSpawnTile;
	public static TextureRegion texSlideTile;

	public static TextureRegion[] tex_player;
	
	public static TextureRegion[] tex_bulletbill;
	public static TextureRegion[] tex_animation;

	public static TextureRegion[][] tex_player_td;
	public static TextureRegion[][] tex_china_td;
	public static TextureRegion[] tex_player_still;
	public static TextureRegion[] tex_china_still;

	public static TextureRegion[][][] tex_buttongui;
	public static TextureRegion[][] tex_framegui;
	public static TextureRegion[][] tex_panelgui;
	public static TextureRegion[][] tex_textfield;
	public static TextureRegion[][] tex_textfield_focus;
	public static TextureRegion[][] tex_gui_checkers;
	public static TextureRegion[] tex_gui_progressbar;

	public static void init() {
		texmap = new Texture("map.png");
		texsidemap = new Texture("side.png");
		texplayerset_td = new Texture("tdplayer.png");
		texchinaset_td = new Texture("chinese.png");
		
		texParallax_1 = new Texture("parallax_1.png");
		texParallax_2 = new Texture("parallax_2.png");
		tex_gui = new Texture("gui.png");
		
		tex_dirt          = TextureHelper.loadSingleTile(texmap,  9,  9, 16, 16);
		tex_GroundTile_TL = TextureHelper.loadSingleTile(texmap,  0,  6, 16, 16);
		tex_GroundTile_TR = TextureHelper.loadSingleTile(texmap,  1,  6, 16, 16);
		tex_GroundTile_BL = TextureHelper.loadSingleTile(texmap,  0,  7, 16, 16);
		tex_GroundTile_BR = TextureHelper.loadSingleTile(texmap,  1,  7, 16, 16);
		tex_AbyssTile     = TextureHelper.loadSingleTile(texmap, 10, 40, 16, 16);

		texSpawnTile = TextureHelper.loadSingleTile(texsidemap,  4,  7, 70, 70);
		texSlideTile = TextureHelper.loadSingleTile(texsidemap,  2, 11, 70, 70);
		
		tex_Bucket_empty      = TextureHelper.loadSingleTile(texmap, 10, 24, 32, 32);
		tex_Bucket_full       = TextureHelper.loadSingleTile(texmap, 11, 24, 32, 32);
		tex_Bucket_hay        = TextureHelper.loadSingleTile(texmap, 12, 24, 32, 32);
		tex_Flowers_empty     = TextureHelper.loadSingleTile(texmap,  8, 25, 32, 32);
		tex_Bush_empty        = TextureHelper.loadSingleTile(texmap, 10, 26, 32, 32);
		tex_Bush_full         = TextureHelper.loadSingleTile(texmap, 10, 27, 32, 32);
		tex_Anchorpoint_empty = TextureHelper.loadSingleTile(texmap,  8, 24, 32, 32);
		tex_Anchorpoint_full  = TextureHelper.loadSingleTile(texmap,  9, 24, 32, 32);
		
		tex_Angel         = TextureHelper.loadSingleTile(texmap,  8, 13, 32, 64);
		
		tex_player     = TextureHelper.load1DArray("playerSet.png", 72, 97, 10);
		tex_bulletbill = TextureHelper.load1DArray("bullbill.png",  27, 14);
		tex_animation  = TextureHelper.load1DArray("animation.png", 400, 400);
		
		tex_player_td = new TextureRegion[4][6];
		for (int y = 0; y < 4; y++) {
			for (int x = 1; x < 7; x++) {
				tex_player_td[y][x-1] = new TextureRegion(texplayerset_td, x*150 + 30, y*117 + 25, 75, 92);
			}
		}
		tex_player_still = new TextureRegion[4];
		for (int y = 0; y < 4; y++) {
			tex_player_still[y] = new TextureRegion(texplayerset_td, 0*150 + 30, y*117 + 25, 75, 92);
		}
		
		tex_china_td = new TextureRegion[4][6];
		for (int y = 0; y < 4; y++) {
			for (int x = 1; x < 7; x++) {
				tex_china_td[y][x-1] = new TextureRegion(texchinaset_td, x*150 + 30, y*117 + 25, 75, 92);
			}
		}
		tex_china_still = new TextureRegion[4];
		for (int y = 0; y < 4; y++) {
			tex_china_still[y] = new TextureRegion(texchinaset_td, 0*150 + 30, y*117 + 25, 75, 92);
		}
		
		tex_buttongui = new TextureRegion[8][3][3];
		
		loadSingleButtonGuiTex(0, 00, 00);
		loadSingleButtonGuiTex(1, 12, 00);
		loadSingleButtonGuiTex(2, 24, 00);
		loadSingleButtonGuiTex(3, 36, 16);
		loadSingleButtonGuiTex(4, 00, 16);
		loadSingleButtonGuiTex(5, 12, 16);
		loadSingleButtonGuiTex(6, 24, 16);
		loadSingleButtonGuiTex(7, 36, 16);
		
		tex_panelgui = new TextureRegion[3][3];
		tex_panelgui[0][0] = new TextureRegion(tex_gui, 48, 0, 1, 1);
		tex_panelgui[1][0] = new TextureRegion(tex_gui, 48, 2, 1, 1);
		tex_panelgui[2][0] = new TextureRegion(tex_gui, 48, 4, 1, 3);
		tex_panelgui[0][1] = new TextureRegion(tex_gui, 50, 0, 1, 1);
		tex_panelgui[1][1] = new TextureRegion(tex_gui, 50, 2, 1, 1);
		tex_panelgui[2][1] = new TextureRegion(tex_gui, 50, 4, 1, 3);
		tex_panelgui[0][2] = new TextureRegion(tex_gui, 52, 0, 1, 1);
		tex_panelgui[1][2] = new TextureRegion(tex_gui, 52, 2, 1, 1);
		tex_panelgui[2][2] = new TextureRegion(tex_gui, 52, 4, 1, 3);
		
//		tex_framegui = new TextureRegion[3][3];                       
//		tex_framegui[0][0] = new TextureRegion(tex_gui, 48,  8, 1, 1); 
//		tex_framegui[0][1] = new TextureRegion(tex_gui, 48, 10, 1, 1); 
//		tex_framegui[0][2] = new TextureRegion(tex_gui, 48, 12, 1, 1); 
//		tex_framegui[1][0] = new TextureRegion(tex_gui, 50,  8, 1, 1); 
//		tex_framegui[1][1] = new TextureRegion(tex_gui, 50, 10, 1, 1); 
//		tex_framegui[1][2] = new TextureRegion(tex_gui, 50, 12, 1, 1); 
//		tex_framegui[2][0] = new TextureRegion(tex_gui, 52,  8, 1, 1); 
//		tex_framegui[2][1] = new TextureRegion(tex_gui, 52, 10, 1, 1); 
//		tex_framegui[2][2] = new TextureRegion(tex_gui, 52, 12, 1, 1);
		

		tex_textfield = new TextureRegion[3][3];
		tex_textfield[0][0] = new TextureRegion(tex_gui, 48, 16, 1, 1);
		tex_textfield[1][0] = new TextureRegion(tex_gui, 48, 18, 1, 1);
		tex_textfield[2][0] = new TextureRegion(tex_gui, 48, 20, 1, 1);
		tex_textfield[0][1] = new TextureRegion(tex_gui, 50, 16, 1, 1);
		tex_textfield[1][1] = new TextureRegion(tex_gui, 50, 18, 1, 1);
		tex_textfield[2][1] = new TextureRegion(tex_gui, 50, 20, 1, 1);
		tex_textfield[0][2] = new TextureRegion(tex_gui, 52, 16, 1, 1);
		tex_textfield[1][2] = new TextureRegion(tex_gui, 52, 18, 1, 1);
		tex_textfield[2][2] = new TextureRegion(tex_gui, 52, 20, 1, 1);

		tex_textfield_focus = new TextureRegion[3][3];
		tex_textfield_focus[0][0] = new TextureRegion(tex_gui, 48, 22, 1, 1);
		tex_textfield_focus[1][0] = new TextureRegion(tex_gui, 48, 24, 1, 1);
		tex_textfield_focus[2][0] = new TextureRegion(tex_gui, 48, 26, 1, 1);
		tex_textfield_focus[0][1] = new TextureRegion(tex_gui, 50, 22, 1, 1);
		tex_textfield_focus[1][1] = new TextureRegion(tex_gui, 50, 24, 1, 1);
		tex_textfield_focus[2][1] = new TextureRegion(tex_gui, 50, 26, 1, 1);
		tex_textfield_focus[0][2] = new TextureRegion(tex_gui, 52, 22, 1, 1);
		tex_textfield_focus[1][2] = new TextureRegion(tex_gui, 52, 24, 1, 1);
		tex_textfield_focus[2][2] = new TextureRegion(tex_gui, 52, 26, 1, 1);
		
		tex_gui_checkers = new TextureRegion[4][3];
		tex_gui_checkers[0][0] = new TextureRegion(tex_gui, 00, 32,  22, 22);
		tex_gui_checkers[0][1] = new TextureRegion(tex_gui, 24, 32,  22, 22);
		tex_gui_checkers[0][2] = new TextureRegion(tex_gui, 48, 32,  22, 22);
		tex_gui_checkers[1][0] = new TextureRegion(tex_gui, 00, 55,  22, 22);
		tex_gui_checkers[1][1] = new TextureRegion(tex_gui, 24, 55,  22, 22);
		tex_gui_checkers[1][2] = new TextureRegion(tex_gui, 48, 55,  22, 22);
		tex_gui_checkers[2][0] = new TextureRegion(tex_gui, 00, 78,  22, 22);
		tex_gui_checkers[2][1] = new TextureRegion(tex_gui, 24, 78,  22, 22);
		tex_gui_checkers[2][2] = new TextureRegion(tex_gui, 48, 78,  22, 22);
		tex_gui_checkers[3][0] = new TextureRegion(tex_gui, 00, 101, 22, 22);
		tex_gui_checkers[3][1] = new TextureRegion(tex_gui, 24, 101, 22, 22);
		tex_gui_checkers[3][2] = new TextureRegion(tex_gui, 48, 101, 22, 22);
		
		tex_gui_progressbar = new TextureRegion[5];
		tex_gui_progressbar[0] = new TextureRegion(tex_gui, 80, 32, 16, 30);
		tex_gui_progressbar[1] = new TextureRegion(tex_gui, 98, 32, 16, 30);
		tex_gui_progressbar[2] = new TextureRegion(tex_gui, 95, 64, 1, 1);
		tex_gui_progressbar[3] = new TextureRegion(tex_gui, 98, 64, 1, 1);
		tex_gui_progressbar[4] = new TextureRegion(tex_gui, 64, 78, 59, 30);
	}

	private static void loadSingleButtonGuiTex(int id, int ox, int oy) {
		int scl = 1;
		
		tex_buttongui[id][0][0] = new TextureRegion(tex_gui, (ox + 0)*scl, (oy + 0)*scl, 4*scl, 4*scl);
		tex_buttongui[id][0][1] = new TextureRegion(tex_gui, (ox + 5)*scl, (oy + 0)*scl, 1*scl, 4*scl);
		tex_buttongui[id][0][2] = new TextureRegion(tex_gui, (ox + 7)*scl, (oy + 0)*scl, 4*scl, 4*scl);
		tex_buttongui[id][1][0] = new TextureRegion(tex_gui, (ox + 0)*scl, (oy + 5)*scl, 4*scl, 1*scl);
		tex_buttongui[id][1][1] = new TextureRegion(tex_gui, (ox + 5)*scl, (oy + 5)*scl, 1*scl, 1*scl);
		tex_buttongui[id][1][2] = new TextureRegion(tex_gui, (ox + 7)*scl, (oy + 5)*scl, 4*scl, 1*scl);
		tex_buttongui[id][2][0] = new TextureRegion(tex_gui, (ox + 0)*scl, (oy + 7)*scl, 4*scl, 6*scl);
		tex_buttongui[id][2][1] = new TextureRegion(tex_gui, (ox + 5)*scl, (oy + 7)*scl, 1*scl, 6*scl);
		tex_buttongui[id][2][2] = new TextureRegion(tex_gui, (ox + 7)*scl, (oy + 7)*scl, 4*scl, 6*scl);
	}
}
