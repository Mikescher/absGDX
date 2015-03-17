package de.samdev.absgdx.example.chessgame.piece;

import java.util.ArrayList;
import java.util.List;

import de.samdev.absgdx.example.Textures;
import de.samdev.absgdx.example.chessgame.ChessMoveType;
import de.samdev.absgdx.example.chessgame.Vector2i;

public class ChessBishop extends ChessPiece {

	public ChessBishop(int player, int x, int y) {
		super(Textures.tex_chess_figures[player][2], player, x, y);
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
		
		return moves;
	}
}
