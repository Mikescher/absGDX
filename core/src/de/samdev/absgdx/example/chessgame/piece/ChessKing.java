package de.samdev.absgdx.example.chessgame.piece;

import java.util.ArrayList;
import java.util.List;

import de.samdev.absgdx.example.Textures;
import de.samdev.absgdx.example.chessgame.ChessLayer;
import de.samdev.absgdx.example.chessgame.ChessMoveType;
import de.samdev.absgdx.example.chessgame.Vector2i;

public class ChessKing extends ChessPiece {

	public ChessKing(int player, int x, int y) {
		super(Textures.tex_chess_figures[player][0], player, x, y);
	}

	@Override
	public List<Vector2i> getMoves(boolean simple) {
		List<Vector2i> moves = new ArrayList<Vector2i>();
		
		if (isValidKingMove(new Vector2i(00, +1), simple)) moves.add(new Vector2i(00, +1));
		if (isValidKingMove(new Vector2i(+1, +1), simple)) moves.add(new Vector2i(+1, +1));
		if (isValidKingMove(new Vector2i(+1, 00), simple)) moves.add(new Vector2i(+1, 00));
		if (isValidKingMove(new Vector2i(+1, -1), simple)) moves.add(new Vector2i(+1, -1));
		if (isValidKingMove(new Vector2i(00, -1), simple)) moves.add(new Vector2i(00, -1));
		if (isValidKingMove(new Vector2i(-1, -1), simple)) moves.add(new Vector2i(-1, -1));
		if (isValidKingMove(new Vector2i(-1, 00), simple)) moves.add(new Vector2i(-1, 00));
		if (isValidKingMove(new Vector2i(-1, +1), simple)) moves.add(new Vector2i(-1, +1));

		return moves;
	}
	
	private boolean isValidKingMove(Vector2i m, boolean simple) {
		if (! isValidMove(m, ChessMoveType.ANY, simple)) return false;
		if (simple) return true;
		
		ChessLayer clayer = (ChessLayer)this.layer;
		
		ChessPiece prev = clayer.board[boardPosX + m.x][boardPosY + m.y];
		clayer.board[boardPosX + m.x][boardPosY + m.y] = this;
		clayer.board[boardPosX][boardPosY] = null;

		boardPosX += m.x;
		boardPosY += m.y;
		
		//##########################
		
		boolean underAttack = isUnderAttack();
		
		//##########################

		boardPosX -= m.x;
		boardPosY -= m.y;
		
		clayer.board[boardPosX][boardPosY] = this;
		clayer.board[boardPosX + m.x][boardPosY + m.y] = prev;
		
		return ! underAttack;
	}

}
