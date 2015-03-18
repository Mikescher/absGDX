package de.samdev.absgdx.example.chessgame.piece;

import java.util.ArrayList;
import java.util.List;

import de.samdev.absgdx.example.Textures;
import de.samdev.absgdx.example.chessgame.ChessMoveType;
import de.samdev.absgdx.example.chessgame.Vector2i;

public class ChessRook extends ChessPiece {

	public ChessRook(int player, int x, int y) {
		super(Textures.tex_chess_figures[player][4], player, x, y);
	}

	@Override
	public List<Vector2i> getMoves(boolean simple) {
		List<Vector2i> moves = new ArrayList<Vector2i>();
		
		for (int i = 1; isValidMove(new Vector2i(+i, 00), ChessMoveType.ANY, simple); i++) {
			moves.add(new Vector2i(+i, 00));
			if (isValidMove(new Vector2i(+i, 00), ChessMoveType.KILL, simple)) break;
		}
		
		for (int i = 1; isValidMove(new Vector2i(-i, 00), ChessMoveType.ANY, simple); i++) {
			moves.add(new Vector2i(-i, 00));
			if (isValidMove(new Vector2i(-i, 00), ChessMoveType.KILL, simple)) break;
		}
		
		for (int i = 1; isValidMove(new Vector2i(00, +i), ChessMoveType.ANY, simple); i++) {
			moves.add(new Vector2i(00, +i));
			if (isValidMove(new Vector2i(00, +i), ChessMoveType.KILL, simple)) break;
		}
		
		for (int i = 1; isValidMove(new Vector2i(00, -i), ChessMoveType.ANY, simple); i++) {
			moves.add(new Vector2i(00, -i));
			if (isValidMove(new Vector2i(00, -i), ChessMoveType.KILL, simple)) break;
		}

		return moves;
	}
}
