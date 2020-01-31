package game;

import card.Card;
import card.CardFactory;
import card.Pile;
import card.executor.CardExecutor;
import processing.core.PApplet;

public class GameOverState implements GameState {
	
	private Pile pile;
	private PlayingState playingState;
	
	public GameOverState(PlayingState playingState) {
		this.playingState = playingState;
		setup();
	}

	@Override
	public void setup() {
		float x = Game.game.width/2;
		float y = Game.game.height * 2/3;
		pile = new Pile(x, y);
		pile.clear();
		pile.setDistBetweenCards(200);
		pile.setRaiseHeight(5);
		
		Card startCard = CardFactory.createButtonCard(x, y, 120, 120, 30, "Main\nMenu");
		startCard.setExecutor(new CardExecutor() {
			@Override
			public boolean execute() {
				mainMenu();
				return true;
			}
		});
		pile.addCard(startCard);
		
		Card replayCard = CardFactory.createButtonCard(x, y, 120, 120, 30, "Replay\nLevel");
		replayCard.setExecutor(new CardExecutor() {
			@Override
			public boolean execute() {
				replayLevel();
				return true;
			}
		});
		pile.addCard(replayCard);
		
		pile.spread();
	}

	@Override
	public void update() {
		pile.update();
	}

	@Override
	public void show() {
		playingState.show();
		Game.game.fill(0, 200);
		Game.game.rectMode(PApplet.CORNER);
		Game.game.rect(0, 0, Game.game.width, Game.game.height);
		Game.game.textSize(70);
		Game.game.fill(255);
		Game.game.textAlign(PApplet.CENTER, PApplet.CENTER);
		Game.game.text("Game Over", Game.game.width/2, Game.game.height/3);
		pile.show();
	}

	@Override
	public void mousePressed() {
		pile.mousePressed();
	}

	@Override
	public void keyPressed() {
		// TODO Auto-generated method stub
		
	}
	
	private void mainMenu() {
		Game.game.setGameState(new MenuState());
	}
	
	private void replayLevel() {
		Game.game.setGameState(new PlayingState());
	}
}
