package de.samdev.absgdx.example.chessgame.piece;

import java.util.ArrayList;
import java.util.List;

import de.samdev.absgdx.example.Textures;
import de.samdev.absgdx.example.chessgame.ChessMoveType;
import de.samdev.absgdx.example.chessgame.Vector2i;

public class ChessKnight extends ChessPiece {

	public ChessKnight(int player, int x, int y) {
		super(Textures.tex_chess_figures[player][3], player, x, y);
	}

	@Override
	public List<Vector2i> getMoves() {
		List<Vector2i> moves = new ArrayList<Vector2i>();
		
		if (isValidMove(new Vector2i(2, 1), ChessMoveType.ANY)) moves.add(new Vector2i(+2, +1));
		if (isValidMove(new Vector2i(2, 1), ChessMoveType.ANY)) moves.add(new Vector2i(+1, +2));
		if (isValidMove(new Vector2i(2, 1), ChessMoveType.ANY)) moves.add(new Vector2i(-2, +1));
		if (isValidMove(new Vector2i(2, 1), ChessMoveType.ANY)) moves.add(new Vector2i(-1, +2));
		if (isValidMove(new Vector2i(2, 1), ChessMoveType.ANY)) moves.add(new Vector2i(+2, -1));
		if (isValidMove(new Vector2i(2, 1), ChessMoveType.ANY)) moves.add(new Vector2i(+1, -2));
		if (isValidMove(new Vector2i(2, 1), ChessMoveType.ANY)) moves.add(new Vector2i(-2, -1));
		if (isValidMove(new Vector2i(2, 1), ChessMoveType.ANY)) moves.add(new Vector2i(-1, -2));
		
		return moves;
	}
}
