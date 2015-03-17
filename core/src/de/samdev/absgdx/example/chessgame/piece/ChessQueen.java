package de.samdev.absgdx.example.chessgame.piece;

import java.util.ArrayList;
import java.util.List;

import de.samdev.absgdx.example.Textures;
import de.samdev.absgdx.example.chessgame.ChessMoveType;
import de.samdev.absgdx.example.chessgame.Vector2i;

public class ChessQueen extends ChessPiece {

	public ChessQueen(int player, int x, int y) {
		super(Textures.tex_chess_figures[player][1], player, x, y);
	}

	@Override
	public List<Vector2i> getMoves() {
		List<Vector2i> moves = new ArrayList<Vector2i>();
		
		for (int i = 1; isValidMove(new Vector2i(+i, +i), ChessMoveType.ANY); i++) {
			moves.add(new Vector2i(+i, +i));
			if (isValidMove(new Vector2i(+i, +i), ChessMoveType.KILL)) break;
		}
		
		for (int i = 1; isValidMove(new Vector2i(-i, +i), ChessMoveType.ANY); i++) {
			moves.add(new Vector2i(-i, +i));
			if (isValidMove(new Vector2i(-i, +i), ChessMoveType.KILL)) break;
		}
		
		for (int i = 1; isValidMove(new Vector2i(-i, -i), ChessMoveType.ANY); i++) {
			moves.add(new Vector2i(-i, -i));
			if (isValidMove(new Vector2i(-i, -i), ChessMoveType.KILL)) break;
		}
		
		for (int i = 1; isValidMove(new Vector2i(+i, -i), ChessMoveType.ANY); i++) {
			moves.add(new Vector2i(+i, -i));
			if (isValidMove(new Vector2i(+i, -i), ChessMoveType.KILL)) break;
		}

		for (int i = 1; isValidMove(new Vector2i(+i, 00), ChessMoveType.ANY); i++) {
			moves.add(new Vector2i(+i, 00));
			if (isValidMove(new Vector2i(+i, 00), ChessMoveType.KILL)) break;
		}
		
		for (int i = 1; isValidMove(new Vector2i(-i, 00), ChessMoveType.ANY); i++) {
			moves.add(new Vector2i(-i, 00));
			if (isValidMove(new Vector2i(-i, 00), ChessMoveType.KILL)) break;
		}
		
		for (int i = 1; isValidMove(new Vector2i(00, +i), ChessMoveType.ANY); i++) {
			moves.add(new Vector2i(00, +i));
			if (isValidMove(new Vector2i(00, +i), ChessMoveType.KILL)) break;
		}
		
		for (int i = 1; isValidMove(new Vector2i(00, -i), ChessMoveType.ANY); i++) {
			moves.add(new Vector2i(00, -i));
			if (isValidMove(new Vector2i(00, -i), ChessMoveType.KILL)) break;
		}

		return moves;
	}
}
