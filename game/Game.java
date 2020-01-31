package game;

import level.LevelManager;
import processing.core.PApplet;
import processing.core.PVector;

public class Game extends PApplet {
	
	public static Game game;
	
	public static void main(String[] args) {
		game = new Game();
		String[] pArgs = {"pArgs"};
		PApplet.runSketch(pArgs, game);
	}
	
	protected LevelManager levelManager = new LevelManager();
	
	private GameState gameState;
	
	@Override
	public void settings() {
		size(500, 650);
	}
	
	@Override
	public void setup() {
		gameState = new MenuState();
	}
	
	@Override
	public void draw() {
		gameState.update();
		gameState.show();
	}
	
	@Override
	public void mousePressed() {
		gameState.mousePressed();
	}
	
	@Override
	public void keyPressed() {
		gameState.keyPressed();
	}
	
	// ======== //
	
	public void setGameState(GameState state) {
		gameState = state;
	}
	
	public PVector getCenter() {
		return new PVector(width/2, width/2);
	}
}
