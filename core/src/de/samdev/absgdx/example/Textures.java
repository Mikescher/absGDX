package de.samdev.absgdx.example;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.samdev.absgdx.framework.menu.texdef.AgdxTextureDefinitionLoader;
import de.samdev.absgdx.framework.util.TextureHelper;
import de.samdev.absgdx.framework.util.exceptions.AgdtexdefParsingException;

public final class Textures {
	public static Texture texmap;
	public static Texture texsidemap;
	public static Texture texchess;
	
	public static Texture texplayerset_td;
	public static Texture texchinaset_td;

	public static Texture texParallax_1;
	public static Texture texParallax_2;
	public static Texture tex_gui;
	
	public static AgdxTextureDefinitionLoader texdef_gui;
	
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
	public static TextureRegion texDoorBottomTile;
	public static TextureRegion texDoorTopTile;
	public static TextureRegion texSlideTile;
	public static TextureRegion texSpike;
	public static TextureRegion texBush;
	public static TextureRegion texLever;
	public static TextureRegion texCactus;
	public static TextureRegion texLadder;
	public static TextureRegion texLadderTop;
	public static TextureRegion texJumpPad;
	public static TextureRegion texJumpPadEx;
	public static TextureRegion texTorch1;
	public static TextureRegion texTorch2;

	public static TextureRegion[] tex_player;
	
	public static TextureRegion[] tex_bulletbill;
	public static TextureRegion[] tex_animation;

	public static TextureRegion[][] tex_player_td;
	public static TextureRegion[][] tex_china_td;
	public static TextureRegion[] tex_player_still;
	public static TextureRegion[] tex_china_still;

	public static TextureRegion[] tex_chess_tiles;
	public static TextureRegion[][] tex_chess_figures;

	public static void init() throws AgdtexdefParsingException {
		texmap = new Texture("map.png");
		texsidemap = new Texture("side.png");
		texplayerset_td = new Texture("tdplayer.png");
		texchinaset_td = new Texture("chinese.png");
		texchess = new Texture("chessset.png");
		
		texParallax_1 = new Texture("parallax_1.png");
		texParallax_2 = new Texture("parallax_2.png");
		tex_gui = new Texture("gui.png");

		//#################################################################################################
		
		texdef_gui = new AgdxTextureDefinitionLoader(Gdx.files.internal("gui.agdtexdef"), Textures.tex_gui);
		texdef_gui.parse();
		
		//#################################################################################################
		
		tex_dirt          = TextureHelper.loadSingleTile(texmap,  9,  9, 16, 16);
		tex_GroundTile_TL = TextureHelper.loadSingleTile(texmap,  0,  6, 16, 16);
		tex_GroundTile_TR = TextureHelper.loadSingleTile(texmap,  1,  6, 16, 16);
		tex_GroundTile_BL = TextureHelper.loadSingleTile(texmap,  0,  7, 16, 16);
		tex_GroundTile_BR = TextureHelper.loadSingleTile(texmap,  1,  7, 16, 16);
		tex_AbyssTile     = TextureHelper.loadSingleTile(texmap, 10, 40, 16, 16);

		texDoorBottomTile = TextureHelper.loadSingleTile(texsidemap,  4,  7, 70, 70);
		texDoorTopTile    = TextureHelper.loadSingleTile(texsidemap,  3,  8, 70, 70);
		texSlideTile      = TextureHelper.loadSingleTile(texsidemap,  2, 11, 70, 70);
		texSpike          = TextureHelper.loadSingleTile(texsidemap,  6,  4, 70, 70);
		texBush           = TextureHelper.loadSingleTile(texsidemap,  2,  0, 70, 70);
		texLever          = TextureHelper.loadSingleTile(texsidemap,  6,  5, 70, 70);
		texCactus         = TextureHelper.loadSingleTile(texsidemap,  4,  0, 70, 70);
		texLadder         = TextureHelper.loadSingleTile(texsidemap,  7,  7, 70, 70);
		texLadderTop      = TextureHelper.loadSingleTile(texsidemap,  2,  9, 70, 70);
		texJumpPad        = TextureHelper.loadSingleTile(texsidemap,  5,  5, 70, 70);
		texJumpPadEx      = TextureHelper.loadSingleTile(texsidemap,  4,  6, 70, 70);
		texTorch1         = TextureHelper.loadSingleTile(texsidemap, 17,  3, 70, 70);
		texTorch2         = TextureHelper.loadSingleTile(texsidemap, 20,  0, 70, 70);
		
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
		
		tex_chess_tiles = new TextureRegion[]
		{
			TextureHelper.loadSingleTile(texchess, 6, 0, 128, 128), 
			TextureHelper.loadSingleTile(texchess, 7, 0, 128, 128), 
			TextureHelper.loadSingleTile(texchess, 6, 1, 128, 128),
			TextureHelper.loadSingleTile(texchess, 7, 1, 128, 128),
			TextureHelper.loadSingleTile(texchess, 6, 2, 128, 128),
			TextureHelper.loadSingleTile(texchess, 7, 2, 128, 128),
			TextureHelper.loadSingleTile(texchess, 6, 3, 128, 128),
			TextureHelper.loadSingleTile(texchess, 7, 3, 128, 128),
		};
		tex_chess_figures = TextureHelper.load2DArray(texchess, 128, 256);
	}
}
