package card.executor;

import game.PlayingState;
import room.Direction;

public class GoExecutor implements CardExecutor {
	
	public Direction direction;
	public PlayingState playingState;
	
	public GoExecutor(PlayingState playingState, Direction direction) {
		this.playingState = playingState;
		this.direction = direction;
	}

	@Override
	public boolean execute() {
		return playingState.go(direction);
	}

}
