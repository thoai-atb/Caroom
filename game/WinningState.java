package game;

import card.Card;
import card.CardFactory;
import card.Pile;
import card.executor.CardExecutor;
import processing.core.PApplet;

public class WinningState implements GameState {
	
	private PlayingState playingState;
	private Pile pile;
	
	public WinningState(PlayingState playingState) {
		this.playingState = playingState;
		setup();
	}

	@Override
	public void setup() {
		float x = Game.game.width/2;
		float y = Game.game.height * 2/3;
		pile = new Pile(x, y);
		pile.clear();
		pile.setDistBetweenCards(Game.game.width * 0.3f);
		pile.setRaiseHeight(5);
		
		Card replayCard = CardFactory.createButtonCard(x, y, 120, 120, 30, "Replay\nLevel");
		replayCard.setExecutor(new CardExecutor() {
			@Override
			public boolean execute() {
				replayLevel();
				return true;
			}
		});
		pile.addCard(replayCard);
		
		Card menuCard = CardFactory.createButtonCard(x, y, 120, 120, 30, "Main\nMenu");
		menuCard.setExecutor(new CardExecutor() {
			@Override
			public boolean execute() {
				mainMenu();
				return true;
			}
		});
		pile.addCard(menuCard);
		
		if(Game.game.levelManager.hasNextLevel()) {
			Card nextCard = CardFactory.createButtonCard(x, y, 120, 120, 30, "Next\nLevel");
			nextCard.setExecutor(new CardExecutor() {
				@Override
				public boolean execute() {
					nextLevel();
					return true;
				}
			});
			pile.addCard(nextCard);
		}
		
		pile.spread();
	}

	@Override
	public void update() {
		playingState.character.update();
		pile.update();
	}

	@Override
	public void show() {
		playingState.show();
		Game.game.fill(0, 200);
		Game.game.rectMode(PApplet.CORNER);
		Game.game.rect(0, 0, Game.game.width, Game.game.height);
		Game.game.textSize(50);
		Game.game.fill(255);
		Game.game.textAlign(PApplet.CENTER, PApplet.CENTER);
		Game.game.text("Level Completed", Game.game.width/2, 200);
		pile.show();
	}

	@Override
	public void mousePressed() {
		pile.mousePressed();
	}

	@Override
	public void keyPressed() {
	}
	
	private void replayLevel() {
		Game.game.setGameState(new PlayingState());
	}
	
	private void nextLevel() {
		if(Game.game.levelManager.nextLevel())
			Game.game.setGameState(new PlayingState());
		else {
			Game.game.levelManager.reset();
			mainMenu();
		}
	}
	
	private void mainMenu() {
		if(Game.game.levelManager.hasNextLevel())
			Game.game.levelManager.nextLevel();
		else
			Game.game.levelManager.reset();
		Game.game.setGameState(new MenuState());
	}

}
