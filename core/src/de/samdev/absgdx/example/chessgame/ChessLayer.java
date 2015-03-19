package de.samdev.absgdx.example.chessgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.example.Textures;
import de.samdev.absgdx.example.chessgame.piece.ChessBishop;
import de.samdev.absgdx.example.chessgame.piece.ChessKing;
import de.samdev.absgdx.example.chessgame.piece.ChessKnight;
import de.samdev.absgdx.example.chessgame.piece.ChessPawn;
import de.samdev.absgdx.example.chessgame.piece.ChessPiece;
import de.samdev.absgdx.example.chessgame.piece.ChessQueen;
import de.samdev.absgdx.example.chessgame.piece.ChessRook;
import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.framework.layer.GameLayer;
import de.samdev.absgdx.framework.map.TileMap;
import de.samdev.absgdx.framework.map.background.RepeatingBackground;
import de.samdev.absgdx.framework.map.mapscaleresolver.ShowCompleteMapScaleResolver;

public class ChessLayer extends GameLayer {

	private float deltasum = 0;
	private float targettime = 2500;
	public int currentPlayer = 0;
	private Random random = new Random();
	
	public ChessPiece[][] board = new ChessPiece[8][8];
	
	public List<List<ChessPiece>> pieces = new ArrayList<List<ChessPiece>>();
		
	public final Boolean[] isHuman;
	
	public ChessPiece selection = null;
	
	public ChessLayer(AgdxGame owner, boolean p1human, boolean p2human) {
		super(owner, createMap());

		this.isHuman = new Boolean[] {p1human, p2human};
		
		pieces.add(new ArrayList<ChessPiece>());
		pieces.add(new ArrayList<ChessPiece>());
		
		init();
		
		setMapScaleResolver(new ShowCompleteMapScaleResolver());

		setRawOffset(new Vector2(-(getVisibleMapBox().width - getMap().width)/2, -(getVisibleMapBox().height - getMap().height)/2));
	}
	
	@Override
	public void onResize() {
		super.onResize();
		
		setRawOffset(new Vector2(-(getVisibleMapBox().width - getMap().width)/2, -(getVisibleMapBox().height - getMap().height)/2));
	}

	private static TileMap createMap() {
		TileMap tm = new TileMap(0XA, 0xA);
		
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				if ((x%9) * (y%9) == 0) tm.setTile(x, y, new ChessTile((x%9 == y%9)?6:7, x-1, y-1));
				else tm.setTile(x, y, new ChessTile((x%2) ^ (y%2), x-1, y-1));
			}
		}
		
		return tm;
	}

	private void init() {
		for (int x = 1; x < 9; x++) {
			for (int y = 1; y < 9; y++) {
				((ChessTile)getMap().getTile(x, y)).clayer = this;
			}
		}
		
		addPiece(new ChessRook  (0, 0, 0));
		addPiece(new ChessKnight(0, 1, 0));
		addPiece(new ChessBishop(0, 2, 0));
		addPiece(new ChessQueen (0, 3, 0));
		addPiece(new ChessKing  (0, 4, 0));
		addPiece(new ChessBishop(0, 5, 0));
		addPiece(new ChessKnight(0, 6, 0));
		addPiece(new ChessRook  (0, 7, 0));

		addPiece(new ChessPawn(0, 0, 1));
		addPiece(new ChessPawn(0, 1, 1));
		addPiece(new ChessPawn(0, 2, 1));
		addPiece(new ChessPawn(0, 3, 1));
		addPiece(new ChessPawn(0, 4, 1));
		addPiece(new ChessPawn(0, 5, 1));
		addPiece(new ChessPawn(0, 6, 1));
		addPiece(new ChessPawn(0, 7, 1));

		addPiece(new ChessRook  (1, 0, 7));
		addPiece(new ChessKnight(1, 1, 7));
		addPiece(new ChessBishop(1, 2, 7));
		addPiece(new ChessQueen (1, 3, 7));
		addPiece(new ChessKing  (1, 4, 7));
		addPiece(new ChessBishop(1, 5, 7));
		addPiece(new ChessKnight(1, 6, 7));
		addPiece(new ChessRook  (1, 7, 7));

		addPiece(new ChessPawn(1, 0, 6));
		addPiece(new ChessPawn(1, 1, 6));
		addPiece(new ChessPawn(1, 2, 6));
		addPiece(new ChessPawn(1, 3, 6));
		addPiece(new ChessPawn(1, 4, 6));
		addPiece(new ChessPawn(1, 5, 6));
		addPiece(new ChessPawn(1, 6, 6));
		addPiece(new ChessPawn(1, 7, 6));
		
		addBackground(new RepeatingBackground(Textures.tex_chess_tiles[4], 128));
	}

	public void addPiece(ChessPiece chessPiece) {
		addEntity(chessPiece);
		
		pieces.get(chessPiece.player).add(chessPiece);
		
		board[chessPiece.getBoardPos().x][chessPiece.getBoardPos().y] = chessPiece;
	}

	@Override
	public void onUpdate(float delta) {
		if (! isHuman[currentPlayer]) {
			deltasum += delta;
			
			if (deltasum > targettime) {
				doPlayerMove(currentPlayer);
				
				changePlayer();				
			}
		} else {
			deltasum = 0;
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE) || Gdx.input.isKeyJustPressed(Keys.BACKSPACE))
			owner.popLayer();
	}

	public void changePlayer() {
		deltasum = 0;
		targettime = random.nextInt(500)+1000;
		currentPlayer = (1-(currentPlayer*2-1))/2;
	}

	private void doPlayerMove(int player) {
		Collections.shuffle(pieces.get(player));
		
		// KILLING MOVES
		
		for (ChessPiece cp : pieces.get(player)) {
			if (! cp.alive || cp.killProcess > 0f) continue;
			
			List<Vector2i> moves = cp.getMoves(false);
			
			Collections.shuffle(moves);
			
			for (Vector2i move : moves) {
				if (board[cp.getBoardPos().add(move).x][cp.getBoardPos().add(move).y] != null) {
					cp.movePiece(move.x, move.y);
					
					return;
				}
			}
		}

		// NORMAL MOVES
		
		for (ChessPiece cp : pieces.get(player)) {
			if (! cp.alive || cp.killProcess > 0f) continue;
			
			List<Vector2i> moves = cp.getMoves(false);
			
			Collections.shuffle(moves);
			
			for (Vector2i move : moves) {
				cp.movePiece(move.x, move.y);
					
				return;
			}
		}
		
		System.out.println("CHECKMATE");
	}

}
