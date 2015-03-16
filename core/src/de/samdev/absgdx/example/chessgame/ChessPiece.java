package de.samdev.absgdx.example.chessgame;

import de.samdev.absgdx.example.Textures;
import de.samdev.absgdx.framework.entities.SimpleEntity;

public class ChessPiece extends SimpleEntity {

	public final int player;
	public final int type;
	
	private float targetY;
	private float moveSignum;
	
	public ChessPiece(int player, int type, int x, int y) {
		super(Textures.tex_chess_figures[player][type], 1, 2);
		targetY = y;
		moveSignum = 0;
		
		this.player = player;
		this.type = type;
		
		this.setPosition(x, y);
		
		this.setZLayer(-(int)getPositionY());
	}

	@Override
	public void beforeUpdate(float delta) {
		if (layer == null) return;
		
		if (getPositionY() != targetY) {
			if (Math.signum(targetY - getPositionY()) != moveSignum) {
				setPositionY(targetY);
				moveSignum = 0;
				speed.setZero();
			} else {
				speed.set(0, Math.signum(targetY - getPositionY()) * 0.005f);				
			}
		} else {
			speed.set(0, 0);
		}
	}

	public void movePiece(int dy) {
		targetY = getPositionY() + dy;
		moveSignum = Math.signum(targetY - getPositionY());
	}
}
