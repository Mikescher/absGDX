package de.samdev.absgdx.example.chessgame;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.samdev.absgdx.example.Textures;
import de.samdev.absgdx.example.chessgame.piece.ChessPiece;
import de.samdev.absgdx.framework.entities.SimpleEntity;

public class ChessMovementMarker extends SimpleEntity {

	private final ChessPiece cpiece;
	private final ChessLayer clayer;
	
	private final Vector2i move;
	
	public ChessMovementMarker(ChessLayer layer, ChessPiece chessPiece, Vector2i move) {
		super(createTex(layer, chessPiece, move), 1, 1);
		
		setZLayer(-100);
		
		Vector2i pos = chessPiece.getBoardPos().add(move).add(new Vector2i(1, 1));
		setPosition(pos.x, pos.y);
		
		this.cpiece = chessPiece;
		this.clayer = layer;
		this.move = move;
	}

	private static TextureRegion createTex(ChessLayer layer, ChessPiece chessPiece, Vector2i move) {
		ChessPiece target = layer.board[chessPiece.getBoardPos().x + move.x][chessPiece.getBoardPos().y + move.y];
		
		if (target == null) return Textures.tex_chess_tiles[3];
		return Textures.tex_chess_tiles[4];
	}

	@Override
	public void beforeUpdate(float delta) {
		alive = (clayer.selection == cpiece);
	}

	@Override
	public void onPointerUp() {
		if (clayer == null) return;
		
		if (clayer.currentPlayer == cpiece.player) {
			cpiece.movePiece(move.x, move.y);
			clayer.changePlayer();
		}
	}
}
