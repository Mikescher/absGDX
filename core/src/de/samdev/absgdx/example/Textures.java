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

	public static void init() {
		texmap = new Texture("map.png");
		texsidemap = new Texture("side.png");
		texplayerset_td = new Texture("tdplayer.png");
		texchinaset_td = new Texture("chinese.png");
		
		texParallax_1 = new Texture("parallax_1.png");
		texParallax_2 = new Texture("parallax_2.png");
		
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
		
		tex_player_td = new TextureRegion[4][7];
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 7; x++) {
				tex_player_td[y][x] = new TextureRegion(texplayerset_td, x*150 + 30, y*117 + 25, 75, 92);
			}
		}
		
		tex_china_td = new TextureRegion[4][7];
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 7; x++) {
				tex_china_td[y][x] = new TextureRegion(texchinaset_td, x*150 + 30, y*117 + 25, 75, 92);
			}
		}
	}
}
