package level;

import game.PlayingState;

public class LevelManager {
	
	public static final int NUMBER_OF_LEVELS = 3;
	
	private int current = 1;
	
	public LevelManager() {
	}
	
	public void reset() {
		current = 1;
	}
	
	public Level getLevel(PlayingState playingState) {
		switch(current) {
			case 1:
				return new Level1(playingState);
			case 2:
				return new Level2(playingState);
			case 3:
				return new Level3(playingState);
			default:
				return null;
		}
	}
	
	public boolean nextLevel() {
		current ++;
		return current <= NUMBER_OF_LEVELS;
	}
	
	public boolean hasNextLevel() {
		return current < NUMBER_OF_LEVELS;
	}
}
