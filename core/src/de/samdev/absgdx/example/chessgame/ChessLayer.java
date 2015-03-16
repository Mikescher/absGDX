package de.samdev.absgdx.example.chessgame;

import java.util.Random;

import de.samdev.absgdx.example.Textures;
import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.framework.layer.GameLayer;
import de.samdev.absgdx.framework.map.TileMap;
import de.samdev.absgdx.framework.map.background.RepeatingBackground;

public class ChessLayer extends GameLayer {

	float deltasum = 0;
	float targettime = 2500;
	int targetplayer = 0;
	Random random = new Random();
	
	public ChessPiece[][] board = new ChessPiece[8][8];
	
	public ChessLayer(AgdxGame owner) {
		super(owner, createMap());
		
		init();
	}

	private static TileMap createMap() {
		TileMap tm = new TileMap(0XA, 0xA);
		
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				if ((x%9) * (y%9) == 0) tm.setTile(x, y, new ChessTile(2));
				else tm.setTile(x, y, new ChessTile((x%2) ^ (y%2)));
			}
		}
		
		return tm;
	}

	private void init() {
		addPiece(new ChessPiece(0, 0, 1, 1));
		addPiece(new ChessPiece(0, 1, 2, 1));
		addPiece(new ChessPiece(0, 4, 3, 1));
		addPiece(new ChessPiece(0, 3, 4, 1));
		addPiece(new ChessPiece(0, 2, 5, 1));
		addPiece(new ChessPiece(0, 4, 6, 1));
		addPiece(new ChessPiece(0, 1, 7, 1));
		addPiece(new ChessPiece(0, 0, 8, 1));

		addPiece(new ChessPiece(0, 5, 1, 2));
		addPiece(new ChessPiece(0, 5, 2, 2));
		addPiece(new ChessPiece(0, 5, 3, 2));
		addPiece(new ChessPiece(0, 5, 4, 2));
		addPiece(new ChessPiece(0, 5, 5, 2));
		addPiece(new ChessPiece(0, 5, 6, 2));
		addPiece(new ChessPiece(0, 5, 7, 2));
		addPiece(new ChessPiece(0, 5, 8, 2));

		addPiece(new ChessPiece(1, 0, 1, 8));
		addPiece(new ChessPiece(1, 1, 2, 8));
		addPiece(new ChessPiece(1, 4, 3, 8));
		addPiece(new ChessPiece(1, 3, 4, 8));
		addPiece(new ChessPiece(1, 2, 5, 8));
		addPiece(new ChessPiece(1, 4, 6, 8));
		addPiece(new ChessPiece(1, 1, 7, 8));
		addPiece(new ChessPiece(1, 0, 8, 8));

		addPiece(new ChessPiece(1, 5, 1, 7));
		addPiece(new ChessPiece(1, 5, 2, 7));
		addPiece(new ChessPiece(1, 5, 3, 7));
		addPiece(new ChessPiece(1, 5, 4, 7));
		addPiece(new ChessPiece(1, 5, 5, 7));
		addPiece(new ChessPiece(1, 5, 6, 7));
		addPiece(new ChessPiece(1, 5, 7, 7));
		addPiece(new ChessPiece(1, 5, 8, 7));
		
		addBackground(new RepeatingBackground(Textures.tex_chess_tiles[2], 1));
	}

	private void addPiece(ChessPiece chessPiece) {
		addEntity(chessPiece);
		
		board[(int) chessPiece.getPositionX() - 1][(int) chessPiece.getPositionY() - 1] = chessPiece;
	}

	@Override
	public void onUpdate(float delta) {
		deltasum += delta;
		
		if (deltasum > targettime) {
			deltasum = 0;
			targettime = random.nextInt(500)+1000;
			targetplayer = (1-(targetplayer*2-1))/2;

			int dx = random.nextInt(8);
			int dy = random.nextInt(8);
			
			for (int rx = 0; rx < 8; rx++) {
				for (int ry = 0; ry < 8; ry++) {
					int x = (rx + dx) % 8;
					int y = (ry + dy) % 8;

					if (board[x][y] == null) continue;
					if (board[x][y].player != targetplayer) continue;
					
					int dm = 1 - board[x][y].player*2;
					
					if (y+dm < 8 && y+dm >= 0 && board[x][y+dm] == null) {
						board[x][y].movePiece(+dm);
						
						board[x][y+dm] = board[x][y];
						board[x][y] = null;
						
						return;
					} else if (y-dm < 8 && y-dm >= 0 && board[x][y-dm] == null){
						board[x][y].movePiece(-dm);
						
						board[x][y-dm] = board[x][y];
						board[x][y] = null;
						
						return;
					}
				}
			}
		}
	}

}
