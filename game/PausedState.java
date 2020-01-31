package game;

import java.awt.event.KeyEvent;

import processing.core.PApplet;

public class PausedState implements GameState {
	
	private PlayingState playingState;
	
	public PausedState(PlayingState playingState) {
		this.playingState = playingState;
	}

	@Override
	public void setup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		playingState.show();
		Game.game.fill(0, 100);
		Game.game.rectMode(PApplet.CORNER);
		Game.game.rect(0, 0, Game.game.width, Game.game.height);
		Game.game.fill(200);
		Game.game.textAlign(PApplet.CENTER, PApplet.CENTER);
		Game.game.textSize(50);
		Game.game.text("Paused", Game.game.width/2, Game.game.height*0.25f);
	}

	@Override
	public void mousePressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed() {
		if(Game.game.keyCode == KeyEvent.VK_SPACE)
			Game.game.setGameState(playingState);
	}

}
