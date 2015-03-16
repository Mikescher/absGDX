package de.samdev.absgdx.example.chessgame;

import de.samdev.absgdx.example.Textures;
import de.samdev.absgdx.framework.map.StaticTile;

public class ChessTile extends StaticTile {

	public ChessTile(int type) {
		super(Textures.tex_chess_tiles[type]);
	}
}
