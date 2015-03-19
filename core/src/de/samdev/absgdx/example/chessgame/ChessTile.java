package de.samdev.absgdx.example.chessgame;

import de.samdev.absgdx.example.Textures;
import de.samdev.absgdx.example.chessgame.piece.ChessPiece;
import de.samdev.absgdx.framework.map.StaticTile;

public class ChessTile extends StaticTile {

	public ChessLayer clayer;
	private final int boardX;
	private final int boardY;
	private final int type;
	
	public ChessTile(int type, int bx, int by) {
		super(Textures.tex_chess_tiles[type]);
		
		this.boardX = bx;
		this.boardY = by;
		this.type = type;
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
	
	@Override
	public float getTextureRotation() {
		if (type < 2) return 0;
		
		if (type == 6) {
			if (boardX < 0 && boardY < 0) return 90;
			if (boardX > 0 && boardY < 0) return 180;
			if (boardX < 0 && boardY > 0) return 0;
			if (boardX > 0 && boardY > 0) return 270;
		} else if (type == 7){
			if (boardX >= 0 && boardY < 0 ) return 180;
			if (boardX < 0  && boardY >= 0) return 90;
			if (boardX < 8  && boardY > 7 ) return 0;
			if (boardX > 7  && boardY < 8 ) return 270;
		}
		
		throw new RuntimeException();
	}
}
