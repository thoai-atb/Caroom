package game;

import java.awt.event.KeyEvent;

import card.Card;
import card.CardFactory;
import card.CardType;
import card.Pile;
import card.executor.CardExecutor;
import level.Level;
import processing.core.PApplet;
import processing.core.PVector;
import room.Character;
import room.Chest;
import room.Direction;
import room.FinishRoom;
import room.Room;
import room.RoomTransition;
import timer.Timer;
import timer.TimerTask;

public class PlayingState implements GameState {
	
	protected Level level;
	protected Room room;
	protected RoomTransition roomTransition;
	protected Timer roomTimer = new Timer(0.003f);
	
	protected Pile pile;
	protected Character character;
	
	private Card skipButton;
	private boolean skipButtonSelected;
	
	public PlayingState(){
		setup();
	}
	
	public Room getRoom() {
		return room;
	}
	
	@Override
	public void setup() {
		skipButton = CardFactory.createButtonCard(30, 30, 30, 30, 10, ">>");
		skipButton.setExecutor(new CardExecutor() {
			@Override
			public boolean execute() {
				skip();
				return true;
			}
		});
		
		level = Game.game.levelManager.getLevel(this);
		room = level.getFirstRoom();
		character = new Character(room);
		pile = new Pile(Game.game.width/2,Game.game.width + (Game.game.height-Game.game.width)/2);
		for(CardType type : level.getInitialCards())
			pile.addCard(CardFactory.createCard(this, type, pile.getPosition()));
		pile.resetCardPositions();
		pile.spread();
		
		resetTimer();
	}

	@Override
	public void update() {
		roomTimer.update();
		pile.update();
		character.update();
		
		if(roomTransition != null) {
			roomTransition.update();
		}
		
		skipButtonSelected = skipButton.containsMouse();
	}

	@Override
	public void show() {
		Game.game.background(200);
		if(roomTransition != null)
			roomTransition.nextRoom().show();
		room.show();
		character.show();
		
		float r = roomTimer.getCurrent();
		int green = Game.game.color(0, 255, 0);
		int red = Game.game.color(255, 0, 0);
		Game.game.stroke(Game.game.lerpColor(green, red, r));
		
		Game.game.strokeWeight(10);
		Game.game.line(0, Game.game.width, Game.game.width * r, Game.game.width);
		
		Game.game.fill(200);
		Game.game.stroke(0);
		Game.game.strokeWeight(3);
		Game.game.rectMode(PApplet.CORNER);
		Game.game.rect(0, Game.game.width, Game.game.width, Game.game.height-Game.game.width);
		pile.show();
		
		skipButton.show(skipButtonSelected);
	}

	@Override
	public void mousePressed() {
		if(roomTransition == null)
			pile.mousePressed();
		
		if(skipButton.containsMouse())
			skipButton.execute();
	}

	@Override
	public void keyPressed() {
		if(Game.game.keyCode == KeyEvent.VK_SPACE)
			Game.game.setGameState(new PausedState(this));
	}
	
	public void skip() {
		roomTimer.skip();
	}
	
	public void gameOver() {
		Game.game.setGameState(new GameOverState(this));
	}
	
	public boolean go(Direction direction) {
		if(!room.doorState.get(direction))
			return false;
		
		roomTransition = level.createTransition(this, room, direction);
		character.updateRoom(roomTransition.nextRoom(), direction);
		
		roomTimer.cancel();
		return true;
	}
	

	public void doneTransition() {
		room = roomTransition.nextRoom();
		roomTransition = null;
		
		Chest chest = room.takeChest();
		if(chest != null) {
			PVector center = Game.game.getCenter();
			for(Card c : chest.getCards())
				c.setPosition(center);
			pile.addCards(chest.getCards());
			pile.spread();
		}
		
		if(room instanceof FinishRoom) {
			Game.game.setGameState(new WinningState(this));
		}else {
			resetTimer();
		}
	}
	
	public void resetTimer() {
		roomTimer.setTask(new TimerTask() {
			@Override
			public void run() {
				gameOver();
			}
		});
	}

}
