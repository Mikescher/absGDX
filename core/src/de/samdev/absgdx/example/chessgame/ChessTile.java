package de.samdev.absgdx.example.chessgame;

import de.samdev.absgdx.example.Textures;
import de.samdev.absgdx.example.chessgame.piece.ChessPiece;
import de.samdev.absgdx.framework.map.StaticTile;

public class ChessTile extends StaticTile {

	public ChessLayer clayer;
	private final int boardX;
	private final int boardY;
	
	public ChessTile(int type, int bx, int by) {
		super(Textures.tex_chess_tiles[type]);
		
		this.boardX = bx;
		this.boardY = by;
	}

	@Override
	public void onPointerUp() {
		if (clayer == null) return;
		
		ChessPiece t = clayer.board[boardX][boardY];
		
		if (t != null)
			t.onBaseClicked();
		else
			clayer.selection = null;
	}
}
