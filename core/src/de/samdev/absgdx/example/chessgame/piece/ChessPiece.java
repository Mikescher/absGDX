package de.samdev.absgdx.example.chessgame.piece;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.example.chessgame.ChessLayer;
import de.samdev.absgdx.example.chessgame.ChessMoveType;
import de.samdev.absgdx.example.chessgame.ChessMovementMarker;
import de.samdev.absgdx.example.chessgame.Vector2i;
import de.samdev.absgdx.framework.entities.SimpleEntity;

public abstract class ChessPiece extends SimpleEntity {

	public final int player;
	
	private float moveSignumY;
	private float moveSignumX;

	protected int boardPosX;
	protected int boardPosY;
	
	public float killProcess = 0f;
	
	public ChessPiece(TextureRegion tex, int player, int x, int y) {
		super(tex, 1, 2);
		moveSignumY = 0;
		moveSignumX = 0;
		
		boardPosX = x;
		boardPosY = y;
		
		this.player = player;
		
		this.setPosition(x+1, y+1);
		
		this.setZLayer(-boardPosY);
	}

	@Override
	public void beforeUpdate(float delta) {
		if (layer == null) return;

		float targetY = (boardPosY+1);
		float targetX = (boardPosX+1);
		
		if (getPositionY() != targetY) {
			if (Math.signum(targetY - getPositionY()) != moveSignumY) {
				setPositionY(targetY);
				moveSignumY = 0;
				speed.y = 0;
			} else {
				speed.y = Math.signum(targetY - getPositionY());				
			}
		} else {
			speed.y = 0;
		}
		
		if (getPositionX() != targetX) {
			if (Math.signum(targetX - getPositionX()) != moveSignumX) {
				setPositionX(targetX);
				moveSignumX = 0;
				speed.x = 0;
			} else {
				speed.x = Math.signum(targetX - getPositionX());				
			}
		} else {
			speed.x = 0;
		}
		
		float distance = new Vector2(targetX, targetY).sub(getPositionX(), getPositionY()).len();
		if (distance > 0) {
			speed.scl(0.005f / speed.len());
			
			speed.scl(Math.max(distance, 0.5f));
		}
		
		if (killProcess > 0) {
			killProcess -= delta;
			
			if (killProcess <= 0){
				alive = false;
				killProcess = 0;
			}
		}
	}

	public void movePiece(int dx, int dy) {
		ChessLayer clayer = (ChessLayer)this.layer;

		clayer.selection = null;
		
		setPosition(boardPosX+1, boardPosY+1);
		
		clayer.board[boardPosX][boardPosY] = null;
		if (clayer.board[boardPosX+dx][boardPosY+dy] != null) clayer.board[boardPosX+dx][boardPosY+dy].killPiece();
		clayer.board[boardPosX+dx][boardPosY+dy] = this;
		
		boardPosX += dx;
		boardPosY += dy;

		moveSignumX = Math.signum((boardPosX+1) - getPositionX());
		moveSignumY = Math.signum((boardPosY+1) - getPositionY());

		this.setZLayer(-boardPosY);
	}
	
	public Vector2i getBoardPos() {
		return new Vector2i(boardPosX, boardPosY);
	}
	
	public boolean isValidMove(Vector2i pos, ChessMoveType mtype) {
		ChessLayer clayer = (ChessLayer)this.layer;
		
		Vector2i newpos = getBoardPos().add(pos);
		
		if (isValidPosition(newpos)) {
			ChessPiece cp = clayer.board[newpos.x][newpos.y];
			
			if ((mtype == ChessMoveType.ANY || mtype == ChessMoveType.MOVE) && cp == null) return true;
			if (mtype == ChessMoveType.KILL && cp == null) return false;
			
			if ((mtype == ChessMoveType.ANY || mtype == ChessMoveType.KILL) && cp.player != this.player && !(cp instanceof ChessKing)) return true;
		}
		
		return false;
	}
	
	public boolean isValidPosition(Vector2i pos) {
		return pos.x >= 0 && pos.y >= 0 && pos.x < 8 && pos.y < 8;
	}
	
	public abstract List<Vector2i> getMoves();

	public void onBaseClicked() {
		if (isCPMoving()) return;
		
		ChessLayer clayer = (ChessLayer)this.layer;
		
		if (clayer.isHuman[player] && clayer.currentPlayer == this.player && clayer.selection != this) {
			clayer.selection = this;
			List<Vector2i> moves = getMoves();
			
			for (Vector2i move : moves) {
				clayer.addEntity(new ChessMovementMarker(clayer, this, move));
			}
		} else {
			clayer.selection = null;
		}
	}
	
	public void killPiece() {
		ChessLayer clayer = (ChessLayer)this.layer;
		clayer.selection = null;
		
		setZLayer(-9999);
		
		killProcess = 1000;
	}
	
	@Override
	public float getTextureScaleX() {
		if (killProcess == 0f) return 1;
		
		return Math.max(killProcess/1000, 0.01f);
	}

	@Override
	public float getTextureScaleY() {
		if (killProcess == 0f) return 1;

		return Math.max(killProcess/1000, 0.01f);
	}

	@Override
	public float getTextureRotation() {
		if (killProcess == 0f) return 0;
		
		return (killProcess + 80)% 360;
	}

	public boolean isCPMoving() {
		float targetY = (boardPosY+1);
		float targetX = (boardPosX+1);
		
		return (getPositionX() != targetX) || (getPositionY() != targetY);
	}
}
