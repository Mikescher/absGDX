package de.samdev.absgdx.example;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.samdev.absgdx.framework.menu.texdef.AgdxTextureDefinitionLoader;
import de.samdev.absgdx.framework.util.TextureHelper;
import de.samdev.absgdx.framework.util.exceptions.AgdtexdefLoadException;
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
	public static AgdxTextureDefinitionLoader texdef_map;
	public static AgdxTextureDefinitionLoader texdef_side;
	public static AgdxTextureDefinitionLoader texdef_chesset;
	
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

	public static void init() throws AgdtexdefParsingException, AgdtexdefLoadException {
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

		texdef_map = new AgdxTextureDefinitionLoader(Gdx.files.internal("map.agdtexdef"), Textures.texmap);
		texdef_map.parse();

		texdef_side = new AgdxTextureDefinitionLoader(Gdx.files.internal("side.agdtexdef"), Textures.texsidemap);
		texdef_side.parse();

		texdef_chesset = new AgdxTextureDefinitionLoader(Gdx.files.internal("chessset.agdtexdef"), Textures.texchess);
		texdef_chesset.parse();
		
		//#################################################################################################
		
		tex_dirt              = texdef_map.getSingleTexture("tex_dirt");
		tex_GroundTile_TL     = texdef_map.getSingleTexture("tex_GroundTile_TL");
		tex_GroundTile_TR     = texdef_map.getSingleTexture("tex_GroundTile_TR");
		tex_GroundTile_BL     = texdef_map.getSingleTexture("tex_GroundTile_BL");
		tex_GroundTile_BR     = texdef_map.getSingleTexture("tex_GroundTile_BR");
		tex_AbyssTile         = texdef_map.getSingleTexture("tex_AbyssTile");

		tex_Bucket_empty      = texdef_map.getSingleTexture("tex_Bucket_empty");
		tex_Bucket_full       = texdef_map.getSingleTexture("tex_Bucket_full");
		tex_Bucket_hay        = texdef_map.getSingleTexture("tex_Bucket_hay");
		tex_Flowers_empty     = texdef_map.getSingleTexture("tex_Flowers_empty");
		tex_Bush_empty        = texdef_map.getSingleTexture("tex_Bush_empty");
		tex_Bush_full         = texdef_map.getSingleTexture("tex_Bush_full");
		tex_Anchorpoint_empty = texdef_map.getSingleTexture("tex_Anchorpoint_empty");
		tex_Anchorpoint_full  = texdef_map.getSingleTexture("tex_Anchorpoint_full");
		tex_Angel             = texdef_map.getSingleTexture("tex_Angel");

		texDoorBottomTile     = texdef_side.getSingleTexture("texDoorBottomTile");
		texDoorTopTile        = texdef_side.getSingleTexture("texDoorTopTile");
		texSlideTile          = texdef_side.getSingleTexture("texSlideTile");
		texSpike              = texdef_side.getSingleTexture("texSpike");
		texBush               = texdef_side.getSingleTexture("texBush");
		texLever              = texdef_side.getSingleTexture("texLever");
		texCactus             = texdef_side.getSingleTexture("texCactus");
		texLadder             = texdef_side.getSingleTexture("texLadder");
		texLadderTop          = texdef_side.getSingleTexture("texLadderTop");
		texJumpPad            = texdef_side.getSingleTexture("texJumpPad");
		texJumpPadEx          = texdef_side.getSingleTexture("texJumpPadEx");
		texTorch1             = texdef_side.getSingleTexture("texTorch1");
		texTorch2             = texdef_side.getSingleTexture("texTorch2");

		tex_chess_tiles = texdef_chesset.getTextureArray("tex_chess_tiles");
		tex_chess_figures = texdef_chesset.getTextureArray2D("tex_chess_figures");
		
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

	}
}
