package game;

import card.Card;
import card.CardFactory;
import card.Pile;
import card.executor.CardExecutor;
import processing.core.PApplet;

public class MenuState implements GameState {
	
	protected Pile pile;
	
	public MenuState() {
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
		
		Card startCard = CardFactory.createButtonCard(x, y, 120, 180, 30, "Start\nGame");
		startCard.setExecutor(new CardExecutor() {
			@Override
			public boolean execute() {
				startGame();
				return true;
			}
		});
		pile.addCard(startCard);
		
		Card exitCard = CardFactory.createButtonCard(x, y, 120, 180, 30, "Exit\nGame");
		exitCard.setExecutor(new CardExecutor() {
			@Override
			public boolean execute() {
				exitGame();
				return true;
			}
		});
		pile.addCard(exitCard);
		
		pile.spread();
	}

	@Override
	public void update() {
		pile.update();
	}

	@Override
	public void show() {
		Game.game.background(100);
		Game.game.textSize(100);
		Game.game.fill(255);
		Game.game.textAlign(PApplet.CENTER, PApplet.CENTER);
		Game.game.text("Caroom", Game.game.width/2, Game.game.height/3);
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
	
	public void startGame() {
		Game.game.setGameState(new PlayingState());
	}
	
	public void exitGame() {
		System.exit(0);
	}

}
