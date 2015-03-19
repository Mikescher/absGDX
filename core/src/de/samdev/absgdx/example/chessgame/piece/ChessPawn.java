package de.samdev.absgdx.example.chessgame.piece;

import java.util.ArrayList;
import java.util.List;

import de.samdev.absgdx.example.Textures;
import de.samdev.absgdx.example.chessgame.ChessLayer;
import de.samdev.absgdx.example.chessgame.ChessMoveType;
import de.samdev.absgdx.example.chessgame.Vector2i;

public class ChessPawn extends ChessPiece {

	public ChessPawn(int player, int x, int y) {
		super(Textures.tex_chess_figures[player][5], player, x, y);
	}

	@Override
	public List<Vector2i> getMoves(boolean simple) {
		int dir = 1 - player*2;
		boolean baseline = (boardPosY - dir)%7 == 0;
		
		List<Vector2i> moves = new ArrayList<Vector2i>();
		
		if (isValidMove(new Vector2i(0, dir), ChessMoveType.MOVE, simple)) 
			moves.add(new Vector2i(0, dir));
		
		if (isValidMove(new Vector2i(0, dir), ChessMoveType.MOVE, true) && baseline && isValidMove(new Vector2i(0, 2*dir), ChessMoveType.MOVE, simple)) 
			moves.add(new Vector2i(0, 2*dir));
		
		if (isValidMove(new Vector2i(+1, dir), ChessMoveType.KILL, simple)) moves.add(new Vector2i(+1, dir));
		if (isValidMove(new Vector2i(-1, dir), ChessMoveType.KILL, simple)) moves.add(new Vector2i(-1, dir));

		return moves;
	}
	

	@Override
	public void beforeUpdate(float delta) {
		super.beforeUpdate(delta);
		
		if (speed.isZero() && boardPosY%7 == 0) {
			ChessLayer clayer = (ChessLayer)this.layer;
			
			killProcess = -1;
			alive = false;
			
			clayer.addPiece(new ChessQueen(player, boardPosX, boardPosY));
		}
	}
}
